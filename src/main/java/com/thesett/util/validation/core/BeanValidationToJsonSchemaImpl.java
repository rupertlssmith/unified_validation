package com.thesett.util.validation.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;

import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.PropertyDescriptor;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.Length;
import com.thesett.util.validation.model.ConstraintBuilder;
import com.thesett.util.validation.model.JsonSchema;
import com.thesett.util.validation.model.PropertyBuilder;

/**
 * BeanValidationToJsonSchemaImpl uses meta-data held against classes to be validated, to derive a json-schema
 * equivalent for the JSON serialized version of the class. The meta-data is queried using Hibernate Validations API.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th>
 * <tr><td> Extract a json schema from bean validation annotations. </td></tr>
 * </table></pre>
 */
public class BeanValidationToJsonSchemaImpl implements BeanValidationToJsonSchema {
    /** {@inheritDoc} */
    public JsonSchema toJsonSchema(Validator validator, Class clazz) {
        PropertyBuilder propertyBuilder = JsonSchema.object();
        toJsonSchema(validator, clazz, propertyBuilder);

        return propertyBuilder.build();
    }

    /** {@inheritDoc} */
    public JsonSchema toJsonSchema(Validator validator, Class clazz, Class group) {
        throw new UnsupportedOperationException();
    }

    private void toJsonSchema(Validator validator, Class clazz, PropertyBuilder propertyBuilder) {
        BeanDescriptor constraintsForClass = validator.getConstraintsForClass(clazz);

        for (Field field : clazz.getDeclaredFields()) {
            String javaFieldName = field.getName();

            // Check if there is a JsonProperty annotation overriding the field name in json, and apply the override
            // if there is.
            JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);

            String fieldName;

            if (jsonProperty != null) {
                fieldName = jsonProperty.value();
            } else {
                fieldName = javaFieldName;
            }

            if ("this$0".equals(fieldName)) {
                continue;
            }

            ConstraintBuilder constraintBuilder = propertyBuilder.property(fieldName);

            Class<?> propertyType = field.getType();

            // Used to track when a type has been assigned, so that object type is only assigned later when no
            // other type has been matched.
            boolean typeAssigned = assignType(constraintBuilder, propertyType);

            PropertyDescriptor property = constraintsForClass.getConstraintsForProperty(fieldName);

            if (property != null) {
                // Assign object type and recursively expand the object, only when another type did not already match.
                if (!typeAssigned && Object.class.isAssignableFrom(propertyType)) {
                    toJsonSchema(validator, propertyType, constraintBuilder.object());
                }

                for (ConstraintDescriptor constraintDescriptor : property.findConstraints().getConstraintDescriptors()) {
                    Annotation annotation = constraintDescriptor.getAnnotation();
                    convertMin(constraintBuilder, annotation);
                    convertMax(constraintBuilder, annotation);
                    convertLength(constraintBuilder, annotation);
                    convertPattern(constraintBuilder, annotation);
                    convertTitle(constraintBuilder, annotation);
                    convertDescription(constraintBuilder, annotation);
                }
            }
        }
    }

    private boolean assignType(ConstraintBuilder constraintBuilder, Class<?> propertyType) { // NOSONAR

        boolean typeAssigned = false;

        if (propertyType.isArray()) {
            constraintBuilder.isArray();
            typeAssigned = true;
        } else if (Collection.class.isAssignableFrom(propertyType)) {
            constraintBuilder.isArray();
            typeAssigned = true;
        } else if (propertyType.equals(String.class)) {
            constraintBuilder.isString();
            typeAssigned = true;
        } else if (propertyType.equals(int.class) || propertyType.equals(Integer.class)) {
            constraintBuilder.isInteger();
            typeAssigned = true;
        } else if (propertyType.equals(long.class) || propertyType.equals(Long.class)) {
            constraintBuilder.isInteger();
            typeAssigned = true;
        } else if (propertyType.equals(float.class) || propertyType.equals(Float.class)) {
            constraintBuilder.isNumber();
            typeAssigned = true;
        } else if (propertyType.equals(double.class) || propertyType.equals(Double.class)) {
            constraintBuilder.isNumber();
            typeAssigned = true;
        } else if (propertyType.equals(boolean.class) || propertyType.equals(Boolean.class)) {
            constraintBuilder.isBoolean();
            typeAssigned = true;
        } else if (BigDecimal.class.isAssignableFrom(propertyType)) {
            constraintBuilder.isNumber();
            typeAssigned = true;
        } else if (Calendar.class.isAssignableFrom(propertyType)) {
            constraintBuilder.isString();
            typeAssigned = true;
        }

        return typeAssigned;
    }

    private void convertDescription(ConstraintBuilder constraintBuilder, Annotation annotation) {
        if (annotation instanceof Description) {
            Description description = (Description) annotation;

            constraintBuilder.description(description.description());
        }
    }

    private void convertTitle(ConstraintBuilder constraintBuilder, Annotation annotation) {
        if (annotation instanceof Title) {
            Title title = (Title) annotation;

            constraintBuilder.title(title.title());
        }
    }

    private void convertPattern(ConstraintBuilder constraintBuilder, Annotation annotation) {
        if (annotation instanceof Pattern) {
            Pattern pattern = (Pattern) annotation;

            constraintBuilder.pattern(pattern.regexp());
        }
    }

    private void convertLength(ConstraintBuilder constraintBuilder, Annotation annotation) {
        if (annotation instanceof Length) {
            Length length = (Length) annotation;

            if (length.min() != 0) {
                constraintBuilder.minLength(length.min());
            }

            if (length.max() != Integer.MAX_VALUE) {
                constraintBuilder.maxLength(length.max());
            }
        }
    }

    private void convertMax(ConstraintBuilder constraintBuilder, Annotation annotation) {
        if (annotation instanceof Max) {
            Max max = (Max) annotation;

            constraintBuilder.maximum(max.value());
        }
    }

    private void convertMin(ConstraintBuilder constraintBuilder, Annotation annotation) {
        if (annotation instanceof Min) {
            Min min = (Min) annotation;

            constraintBuilder.minimum(min.value());
        }
    }
}
