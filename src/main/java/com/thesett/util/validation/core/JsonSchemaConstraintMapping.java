package com.thesett.util.validation.core;

import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ValidationException;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.cfg.context.PropertyConstraintMappingContext;
import org.hibernate.validator.cfg.context.TypeConstraintMappingContext;
import org.hibernate.validator.cfg.defs.DecimalMaxDef;
import org.hibernate.validator.cfg.defs.DecimalMinDef;
import org.hibernate.validator.cfg.defs.LengthDef;
import org.hibernate.validator.cfg.defs.NotNullDef;
import org.hibernate.validator.cfg.defs.PatternDef;
import org.hibernate.validator.internal.cfg.DefaultConstraintMapping;
import com.thesett.util.validation.model.JsonSchema;
import com.thesett.util.validation.model.SchemaType;

/**
 * JsonSchemaConstraintMapping translates a {@link JsonSchema} into a
 * {@link org.hibernate.validator.cfg.ConstraintMapping}. This is accomplished by creating equivalent validation
 * constraints that match those defined in the schema, and setting those up against the specified class.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th>
 * <tr><td> Map a json schema into bean validation constraints. </td></tr>
 * </table></pre>
 */
class JsonSchemaConstraintMapping extends DefaultConstraintMapping {
    /**
     * Adds constraints in a json schema to the mapping, against the specified class.
     *
     * @param type       The class to add constraints to.
     * @param jsonSchema The json schema to add constraints from.
     * @param <C>        The type of the class to add constraints to.
     */
    public <C> void addSchema(Class<C> type, JsonSchema jsonSchema) {
        TypeConstraintMappingContext<C> typeContext = type(type);

        // Scan all of the fields of the class being added to, to check if there are any @JsonProperty mappings,
        // translating field names between Java and JSON.
        Map<String, String> jsonToJava = new HashMap<>();

        for (Field field : type.getDeclaredFields()) {
            JsonProperty annotation = field.getAnnotation(JsonProperty.class);

            if (annotation != null) {
                jsonToJava.put(annotation.value(), field.getName());
            }
        }

        if (jsonSchema.getProperties() != null) {
            for (Map.Entry<String, JsonSchema> property : jsonSchema.getProperties().entrySet()) {
                String jsonPropertyName = property.getKey();
                String javaPropertyName;

                if (jsonToJava.containsKey(jsonPropertyName)) {
                    javaPropertyName = jsonToJava.get(jsonPropertyName);
                } else {
                    javaPropertyName = jsonPropertyName;
                }

                PropertyConstraintMappingContext propertyContext =
                    typeContext.property(javaPropertyName, ElementType.FIELD);

                addConstraints(propertyContext, javaPropertyName, jsonPropertyName, property.getValue(), type);

                convertRequired(propertyContext, javaPropertyName, jsonPropertyName, jsonSchema);
            }
        }
    }

    /**
     * Creates constraints against a field, matching those found in a json schema.
     *
     * @param propertyContext  The context of the field to create constraints for.
     * @param javaPropertyName The name of the property that constraints are being added to.
     * @param jsonPropertyName The name of the property in the json-schema.
     * @param value            The json schema definition for the field, to take additional constraints from.
     * @param type             The type of the class that property constraints are being added to.
     */
    private void addConstraints(PropertyConstraintMappingContext propertyContext, String javaPropertyName,
        String jsonPropertyName, JsonSchema value, Class type) {
        convertMinimum(propertyContext, value);
        convertMaximum(propertyContext, value);
        convertMaxLength(propertyContext, value);
        convertMinLength(propertyContext, value);
        convertPattern(propertyContext, value);
        convertTitle(propertyContext, value);
        convertDescription(propertyContext, value);

        if (SchemaType.OBJECT.equals(value.getType())) {
            Class<?> fieldClass = null;

            try {
                fieldClass = type.getDeclaredField(javaPropertyName).getType();
            } catch (NoSuchFieldException e) {
                throw new ValidationException("No matching field found with name: " + javaPropertyName, e);
            }

            propertyContext.valid();

            addSchema(fieldClass, value);
        }
    }

    private void convertRequired(PropertyConstraintMappingContext propertyContext, String javaPropertyName,
        String jsonPropertyName, JsonSchema jsonSchema) {
        List<String> required = jsonSchema.getRequired();

        if (required != null && !required.isEmpty() && required.contains(jsonPropertyName)) {
            propertyContext.constraint(new NotNullDef().message(" is mandatory."));
        }
    }

    private void convertDescription(PropertyConstraintMappingContext propertyContext, JsonSchema value) {
        if (value.getDescription() != null) {
            propertyContext.constraint(new DescriptionDef().description(value.getDescription()));
        }
    }

    private void convertTitle(PropertyConstraintMappingContext propertyContext, JsonSchema value) {
        if (value.getTitle() != null) {
            propertyContext.constraint(new TitleDef().title(value.getTitle()));
        }
    }

    private void convertPattern(PropertyConstraintMappingContext propertyContext, JsonSchema value) {
        if (value.getPattern() != null) {
            propertyContext.constraint(new PatternDef().regexp(value.getPattern()));
        }
    }

    private void convertMinLength(PropertyConstraintMappingContext propertyContext, JsonSchema value) {
        if (value.getMinLength() != null) {
            propertyContext.constraint(new LengthDef().min(value.getMinLength()));
        }
    }

    private void convertMaxLength(PropertyConstraintMappingContext propertyContext, JsonSchema value) {
        if (value.getMaxLength() != null) {
            propertyContext.constraint(new LengthDef().max(value.getMaxLength()));
        }
    }

    private void convertMaximum(PropertyConstraintMappingContext propertyContext, JsonSchema value) {
        if (value.getMaximum() != null) {
            boolean isInclusive = !Boolean.TRUE.equals(value.getExclusiveMaximum());

            propertyContext.constraint(new DecimalMaxDef().value(value.getMaximum().toString()).inclusive(isInclusive));
        }
    }

    private void convertMinimum(PropertyConstraintMappingContext propertyContext, JsonSchema value) {
        if (value.getMinimum() != null) {
            boolean isInclusive = !Boolean.TRUE.equals(value.getExclusiveMinimum());

            propertyContext.constraint(new DecimalMinDef().value(value.getMinimum().toString()).inclusive(isInclusive));
        }
    }
}
