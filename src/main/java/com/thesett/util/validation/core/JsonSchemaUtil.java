/*
 * Copyright The Sett Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.thesett.util.validation.core;

import javax.validation.Validator;

import com.thesett.util.validation.model.JsonSchema;

/**
 * JsonSchemaUtil provides some helper methods for working with json-schema.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th>
 * <tr><td> Provide a json-schema for a class. </td></tr>
 * </table></pre>
 */
public class JsonSchemaUtil
{
    /** Private constructor to prevent instantiation of utility class. */
    private JsonSchemaUtil()
    {
    }

    /**
     * Provides a json-schema for a class. A {@link JsonSchemaValidatorFactory} is used to create a schema based on any
     * validation attached to the class, and the json-schema is generated from this.
     *
     * @param  clazz The class to get a json-schema for.
     *
     * @return A json-schema for the specified class.
     */
    public static JsonSchema getJsonSchema(Class<?> clazz)
    {
        JsonSchemaValidatorFactory validatorFactory = new JsonSchemaValidatorFactory();

        JsonSchemaValidatorFactory.ValidatorBuilder builder = validatorFactory.getBuilder();
        Validator validator = builder.build();

        return new BeanValidationToJsonSchemaImpl().toJsonSchema(validator, clazz);
    }
}
