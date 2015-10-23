
package com.thesett.util.validation.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * JsonSchema defines a schema that can be applied to a json or Java object model, following the model described in the
 * jsonSchema specification.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th>
 * <tr><td> Describe a schema that can be applied to a json model. </td></tr>
 * </table></pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class JsonSchema implements Serializable {
    /** Holds the title property. */
    protected String title;

    /** Holds the type property. */
    protected SchemaType type;

    /** Holds the description property. */
    protected String description;

    /** Holds the multipleOf property. */
    protected BigDecimal multipleOf;

    /** Holds the maximum property. */
    protected BigDecimal maximum;

    /** Holds the exclusiveMaximum property. */
    protected Boolean exclusiveMaximum;

    /** Holds the minimum property. */
    protected BigDecimal minimum;

    /** Holds the exclusiveMinimum property. */
    protected Boolean exclusiveMinimum;

    /** Holds the maxLength property. */
    protected Integer maxLength;

    /** Holds the minLength property. */
    protected Integer minLength;

    /** Holds the pattern property. */
    protected String pattern;

    /** Holds the items property. */
    protected List<JsonSchema> items;

    /** Holds the additionalItems property. */
    protected Boolean additionalItems;

    /** Holds the maxItems property. */
    protected Integer maxItems;

    /** Holds the minItems property. */
    protected Integer minItems;

    /** Holds the uniqueItems property. */
    protected Integer uniqueItems;

    /** Holds the properties property. */
    protected Map<String, JsonSchema> properties;

    /** Holds the additionalProperties property. */
    protected Boolean additionalProperties;

    /** Holds the maxProperties property. */
    protected Integer maxProperties;

    /** Holds the minProperties property. */
    protected Integer minProperties;

    /** Holds the required property. */
    protected List<String> required;

    /** Holds the patternProperties property. */
    protected List<String> patternProperties;

    /** Holds the enums property. */
    protected List<String> enums;

    /** No-arg constructor for serialization. */
    public JsonSchema() {
    }

    /**
     * Creates a fluent structured builder to help with creating json schemas on object types.
     *
     * @return A builder to add properties to a schema.
     */
    public static PropertyBuilder object() {
        JsonSchema rootSchema = new JsonSchema().withType(SchemaType.OBJECT);

        return new PropertyBuilder(rootSchema, rootSchema);
    }

    /**
     * Accepts a new value for the title property.
     *
     * @param title The title property.
     */
    public JsonSchema withTitle(String title) {
        this.title = title;

        return this;
    }

    /**
     * Accepts a new value for the type property.
     *
     * @param type The type property.
     */
    public JsonSchema withType(SchemaType type) {
        this.type = type;

        return this;
    }

    /**
     * Accepts a new value for the description property.
     *
     * @param description The description property.
     */
    public JsonSchema withDescription(String description) {
        this.description = description;

        return this;
    }

    /**
     * Accepts a new value for the multipleOf property.
     *
     * @param multipleOf The multipleOf property.
     */
    public JsonSchema withMultipleOf(BigDecimal multipleOf) {
        this.multipleOf = multipleOf;

        return this;
    }

    /**
     * Accepts a new value for the maximum property.
     *
     * @param maximum The maximum property.
     */
    public JsonSchema withMaximum(BigDecimal maximum) {
        this.maximum = maximum;

        return this;
    }

    /**
     * Accepts a new value for the exclusiveMaximum property.
     *
     * @param exclusiveMaximum The exclusiveMaximum property.
     */
    public JsonSchema withExclusiveMaximum(Boolean exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;

        return this;
    }

    /**
     * Accepts a new value for the minimum property.
     *
     * @param minimum The minimum property.
     */
    public JsonSchema withMinimum(BigDecimal minimum) {
        this.minimum = minimum;

        return this;
    }

    /**
     * Accepts a new value for the exclusiveMinimum property.
     *
     * @param exclusiveMinimum The exclusiveMinimum property.
     */
    public JsonSchema withExclusiveMinimum(Boolean exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;

        return this;
    }

    /**
     * Accepts a new value for the maxLength property.
     *
     * @param maxLength The maxLength property.
     */
    public JsonSchema withMaxLength(Integer maxLength) {
        this.maxLength = maxLength;

        return this;
    }

    /**
     * Accepts a new value for the minLength property.
     *
     * @param minLength The minLength property.
     */
    public JsonSchema withMinLength(Integer minLength) {
        this.minLength = minLength;

        return this;
    }

    /**
     * Accepts a new value for the pattern property.
     *
     * @param pattern The pattern property.
     */
    public JsonSchema withPattern(String pattern) {
        this.pattern = pattern;

        return this;
    }

    /**
     * Accepts a new value for the items property.
     *
     * @param items The items property.
     */
    public JsonSchema withItems(List<JsonSchema> items) {
        this.items = items;

        return this;
    }

    /**
     * Accepts a new value for the additionalItems property.
     *
     * @param additionalItems The additionalItems property.
     */
    public JsonSchema withAdditionalItems(Boolean additionalItems) {
        this.additionalItems = additionalItems;

        return this;
    }

    /**
     * Accepts a new value for the maxItems property.
     *
     * @param maxItems The maxItems property.
     */
    public JsonSchema withMaxItems(Integer maxItems) {
        this.maxItems = maxItems;

        return this;
    }

    /**
     * Accepts a new value for the minItems property.
     *
     * @param minItems The minItems property.
     */
    public JsonSchema withMinItems(Integer minItems) {
        this.minItems = minItems;

        return this;
    }

    /**
     * Accepts a new value for the uniqueItems property.
     *
     * @param uniqueItems The uniqueItems property.
     */
    public JsonSchema withUniqueItems(Integer uniqueItems) {
        this.uniqueItems = uniqueItems;

        return this;
    }

    /**
     * Accepts a new value for the properties property.
     *
     * @param properties The properties property.
     */
    public JsonSchema withProperties(Map<String, JsonSchema> properties) {
        this.properties = properties;

        return this;
    }

    /**
     * Accepts a new value for the additionalProperties property.
     *
     * @param additionalProperties The additionalProperties property.
     */
    public JsonSchema withAdditionalProperties(Boolean additionalProperties) {
        this.additionalProperties = additionalProperties;

        return this;
    }

    /**
     * Accepts a new value for the maxProperties property.
     *
     * @param maxProperties The maxProperties property.
     */
    public JsonSchema withMaxProperties(Integer maxProperties) {
        this.maxProperties = maxProperties;

        return this;
    }

    /**
     * Accepts a new value for the minProperties property.
     *
     * @param minProperties The minProperties property.
     */
    public JsonSchema withMinProperties(Integer minProperties) {
        this.minProperties = minProperties;

        return this;
    }

    /**
     * Accepts a new value for the required property.
     *
     * @param required The required property.
     */
    public JsonSchema withRequired(List<String> required) {
        this.required = required;

        return this;
    }

    /**
     * Accepts a new value for the patternProperties property.
     *
     * @param patternProperties The patternProperties property.
     */
    public JsonSchema withPatternProperties(List<String> patternProperties) {
        this.patternProperties = patternProperties;

        return this;
    }

    /**
     * Accepts a new value for the enums property.
     *
     * @param enums The enums property.
     */
    public JsonSchema withEnums(List<String> enums) {
        this.enums = enums;

        return this;
    }

    /**
     * Provides the title property.
     *
     * @return The title property.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Provides the type property.
     *
     * @return The type property.
     */
    public SchemaType getType() {
        return type;
    }

    /**
     * Provides the description property.
     *
     * @return The description property.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Provides the multipleOf property.
     *
     * @return The multipleOf property.
     */
    public BigDecimal getMultipleOf() {
        return multipleOf;
    }

    /**
     * Provides the maximum property.
     *
     * @return The maximum property.
     */
    public BigDecimal getMaximum() {
        return maximum;
    }

    /**
     * Provides the exclusiveMaximum property.
     *
     * @return The exclusiveMaximum property.
     */
    public Boolean getExclusiveMaximum() {
        return exclusiveMaximum;
    }

    /**
     * Provides the minimum property.
     *
     * @return The minimum property.
     */
    public BigDecimal getMinimum() {
        return minimum;
    }

    /**
     * Provides the exclusiveMinimum property.
     *
     * @return The exclusiveMinimum property.
     */
    public Boolean getExclusiveMinimum() {
        return exclusiveMinimum;
    }

    /**
     * Provides the maxLength property.
     *
     * @return The maxLength property.
     */
    public Integer getMaxLength() {
        return maxLength;
    }

    /**
     * Provides the minLength property.
     *
     * @return The minLength property.
     */
    public Integer getMinLength() {
        return minLength;
    }

    /**
     * Provides the pattern property.
     *
     * @return The pattern property.
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Provides the items property.
     *
     * @return The items property.
     */
    public List<JsonSchema> getItems() {
        return items;
    }

    /**
     * Provides the additionalItems property.
     *
     * @return The additionalItems property.
     */
    public Boolean getAdditionalItems() {
        return additionalItems;
    }

    /**
     * Provides the maxItems property.
     *
     * @return The maxItems property.
     */
    public Integer getMaxItems() {
        return maxItems;
    }

    /**
     * Provides the minItems property.
     *
     * @return The minItems property.
     */
    public Integer getMinItems() {
        return minItems;
    }

    /**
     * Provides the uniqueItems property.
     *
     * @return The uniqueItems property.
     */
    public Integer getUniqueItems() {
        return uniqueItems;
    }

    /**
     * Provides the properties property.
     *
     * @return The properties property.
     */
    public Map<String, JsonSchema> getProperties() {
        return properties;
    }

    /**
     * Provides the additionalProperties property.
     *
     * @return The additionalProperties property.
     */
    public Boolean getAdditionalProperties() {
        return additionalProperties;
    }

    /**
     * Provides the maxProperties property.
     *
     * @return The maxProperties property.
     */
    public Integer getMaxProperties() {
        return maxProperties;
    }

    /**
     * Provides the minProperties property.
     *
     * @return The minProperties property.
     */
    public Integer getMinProperties() {
        return minProperties;
    }

    /**
     * Provides the required property.
     *
     * @return The required property.
     */
    public List<String> getRequired() {
        return required;
    }

    /**
     * Provides the patternProperties property.
     *
     * @return The patternProperties property.
     */
    public List<String> getPatternProperties() {
        return patternProperties;
    }

    /**
     * Provides the enums property.
     *
     * @return The enums property.
     */
    public List<String> getEnums() {
        return enums;
    }

    /**
     * Accepts a new value for the title property.
     *
     * @param title The title property.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Accepts a new value for the type property.
     *
     * @param type The type property.
     */
    public void setType(SchemaType type) {
        this.type = type;
    }

    /**
     * Accepts a new value for the description property.
     *
     * @param description The description property.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Accepts a new value for the multipleOf property.
     *
     * @param multipleOf The multipleOf property.
     */
    public void setMultipleOf(BigDecimal multipleOf) {
        this.multipleOf = multipleOf;
    }

    /**
     * Accepts a new value for the maximum property.
     *
     * @param maximum The maximum property.
     */
    public void setMaximum(BigDecimal maximum) {
        this.maximum = maximum;
    }

    /**
     * Accepts a new value for the exclusiveMaximum property.
     *
     * @param exclusiveMaximum The exclusiveMaximum property.
     */
    public void setExclusiveMaximum(Boolean exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
    }

    /**
     * Accepts a new value for the minimum property.
     *
     * @param minimum The minimum property.
     */
    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    /**
     * Accepts a new value for the exclusiveMinimum property.
     *
     * @param exclusiveMinimum The exclusiveMinimum property.
     */
    public void setExclusiveMinimum(Boolean exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
    }

    /**
     * Accepts a new value for the maxLength property.
     *
     * @param maxLength The maxLength property.
     */
    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Accepts a new value for the minLength property.
     *
     * @param minLength The minLength property.
     */
    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    /**
     * Accepts a new value for the pattern property.
     *
     * @param pattern The pattern property.
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Accepts a new value for the items property.
     *
     * @param items The items property.
     */
    public void setItems(List<JsonSchema> items) {
        this.items = items;
    }

    /**
     * Accepts a new value for the additionalItems property.
     *
     * @param additionalItems The additionalItems property.
     */
    public void setAdditionalItems(Boolean additionalItems) {
        this.additionalItems = additionalItems;
    }

    /**
     * Accepts a new value for the maxItems property.
     *
     * @param maxItems The maxItems property.
     */
    public void setMaxItems(Integer maxItems) {
        this.maxItems = maxItems;
    }

    /**
     * Accepts a new value for the minItems property.
     *
     * @param minItems The minItems property.
     */
    public void setMinItems(Integer minItems) {
        this.minItems = minItems;
    }

    /**
     * Accepts a new value for the uniqueItems property.
     *
     * @param uniqueItems The uniqueItems property.
     */
    public void setUniqueItems(Integer uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    /**
     * Accepts a new value for the properties property.
     *
     * @param properties The properties property.
     */
    public void setProperties(Map<String, JsonSchema> properties) {
        this.properties = properties;
    }

    /**
     * Accepts a new value for the additionalProperties property.
     *
     * @param additionalProperties The additionalProperties property.
     */
    public void setAdditionalProperties(Boolean additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    /**
     * Accepts a new value for the maxProperties property.
     *
     * @param maxProperties The maxProperties property.
     */
    public void setMaxProperties(Integer maxProperties) {
        this.maxProperties = maxProperties;
    }

    /**
     * Accepts a new value for the minProperties property.
     *
     * @param minProperties The minProperties property.
     */
    public void setMinProperties(Integer minProperties) {
        this.minProperties = minProperties;
    }

    /**
     * Accepts a new value for the required property.
     *
     * @param required The required property.
     */
    public void setRequired(List<String> required) {
        this.required = required;
    }

    /**
     * Accepts a new value for the patternProperties property.
     *
     * @param patternProperties The patternProperties property.
     */
    public void setPatternProperties(List<String> patternProperties) {
        this.patternProperties = patternProperties;
    }

    /**
     * Accepts a new value for the enums property.
     *
     * @param enums The enums property.
     */
    public void setEnums(List<String> enums) {
        this.enums = enums;
    }

    /** Pretty printing for debugging purposes. */
    public String toString() {
        return "JsonSchema: [ " + " ]";
    }

    /** Initialized the properties map. */
    public void initProperties() {
        properties = new LinkedHashMap<>();
    }
}
