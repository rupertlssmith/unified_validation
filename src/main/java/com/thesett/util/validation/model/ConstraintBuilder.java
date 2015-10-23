package com.thesett.util.validation.model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Constraint builder is a builder that allows validation constraints to be added to a json field.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th>
 * <tr><td> Define field constraints. </td></tr>
 * </table></pre>
 */
public class ConstraintBuilder extends PropertyBuilder {
    /** The schema for the field to add constraints to. */
    private final JsonSchema propertySchema;

    /** The name of the property that this is building constraints for. */
    private final String propertyName;

    /**
     * Creates a constraint builder on the specified field schema, with the specified parent schema.
     *
     * @param rootSchema     The root of the schema being built.
     * @param parentSchema   The parent schema.
     * @param propertySchema The field schema to add constraints to.
     * @param propertyName   The name of the property that this is building constraints for.
     */
    public ConstraintBuilder(JsonSchema rootSchema, JsonSchema parentSchema, JsonSchema propertySchema,
        String propertyName) {
        super(rootSchema, parentSchema);
        this.propertySchema = propertySchema;
        this.propertyName = propertyName;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder title(String title) {
        propertySchema.setTitle(title);

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder description(String description) {
        propertySchema.setDescription(description);

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder multipleOf(Number multiple) {
        propertySchema.setMultipleOf(new BigDecimal(multiple.toString()));

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder maximum(Number max) {
        propertySchema.setMaximum(new BigDecimal(max.toString()));

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder exclusiveMaximum(Boolean flag) {
        propertySchema.setExclusiveMaximum(flag);

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder minimum(Number min) {
        propertySchema.setMinimum(new BigDecimal(min.toString()));

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder exclusiveMinimum(Boolean flag) {
        propertySchema.setExclusiveMinimum(flag);

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder maxLength(Integer max) {
        propertySchema.setMaxLength(max);

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder minLength(Integer min) {
        propertySchema.setMinLength(min);

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder pattern(String pattern) {
        propertySchema.setPattern(pattern);

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder isArray() {
        propertySchema.setType(SchemaType.ARRAY);

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder isBoolean() {
        propertySchema.setType(SchemaType.BOOLEAN);

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder isInteger() {
        propertySchema.setType(SchemaType.INTEGER);

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder isNumber() {
        propertySchema.setType(SchemaType.NUMBER);

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder isNull() {
        propertySchema.setType(SchemaType.NULL);

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder isString() {
        propertySchema.setType(SchemaType.STRING);

        return this;
    }

    /** @return This constraint builder. */
    public ConstraintBuilder isRequired() {
        List<String> required = parentSchema.getRequired();

        if (required == null) {
            required = new LinkedList<String>();
            parentSchema.setRequired(required);
        }

        required.add(propertyName);

        return this;
    }

    /**
     * Constrains the field to be an object, and supplies a builder to add properties to it.
     *
     * @return A builder to add properties to a schema.
     */
    public PropertyBuilder object() {
        propertySchema.setType(SchemaType.OBJECT);

        return new PropertyBuilder(rootSchema, propertySchema);
    }
}
