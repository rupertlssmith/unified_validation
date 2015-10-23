package com.thesett.util.validation.core;

import javax.validation.Validator;

import com.thesett.util.validation.model.JsonSchema;

/**
 * BeanValidationToJsonSchema describes operations to extract a json schema from bean validation annotations.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th>
 * <tr><td> Extract a json schema from bean validation annotations. </td></tr>
 * </table></pre>
 */
public interface BeanValidationToJsonSchema {
    /**
     * Extracts a json schema from a class which has been annotated for validation.
     *
     * @param  validator The validator to extract the meta-data from.
     * @param  clazz     The annotated class to extract the schema for.
     *
     * @return A json schema describing the validation applied by the annotations on the class.
     */
    JsonSchema toJsonSchema(Validator validator, Class clazz);

    /**
     * Extracts a json schema from a class which has been annotated for validation, under a particular validation group.
     *
     * @param  validator The validator to extract the meta-data from.
     * @param  clazz     The annotated class to extract the schema for.
     * @param  group     The validation group to extract.
     *
     * @return A json schema describing the validation applied by the annotations on the class, under the specified
     *         validation group.
     */
    JsonSchema toJsonSchema(Validator validator, Class clazz, Class group);
}
