package com.thesett.util.validation.core;

import com.thesett.util.validation.model.JsonSchema;

/**
 * Merge defines operations that can be performed on json schemas in order to combine multiple schemas together. This
 * allows validation from multiple sources to be combined together into a single validation definition.
 *
 * <p/>The effect of merging two schemas together and then validating against them, should be the same as running two
 * validations against the schemas sequentially. For server side validation, this could be used to validate against
 * multiple schemas. The merge operation is convenient, when presenting validation to an external audience, for example
 * a UI, where a unified view of the validation against a particular data type or service end-point is to be presented;
 * it is simpler to present the validation as a single schema.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th>
 * <tr><td> Merge multiple json schemas together to form one schema. </td></tr>
 * </table></pre>
 */
public interface JsonSchemaMerge {
    /**
     * Merges two json schemas together.
     *
     * @param  schema1 The first schema to merge.
     * @param  schema2 The second schema to merge.
     *
     * @return A unified schema formed by merging the two schemas together.
     */
    JsonSchema merge(JsonSchema schema1, JsonSchema schema2);

    /**
     * Merges many json schemas together.
     *
     * @param  schemas The schemas to merge.
     *
     * @return A unified schema formed by merging all the schemas together.
     */
    JsonSchema merge(JsonSchema... schemas);
}
