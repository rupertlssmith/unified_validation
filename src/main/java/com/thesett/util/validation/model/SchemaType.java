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
package com.thesett.util.validation.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * SchemaType defines the various types in both json and Java that json schema can represent.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th>
 * <tr><td> Describe the core type model for json schema. </td></tr>
 * </table></pre>
 */
public enum SchemaType
{
    /** Represents array and collection types. */
    ARRAY("array"),

    /** Represents boolean types. */
    BOOLEAN("boolean"),

    /** Represents integer types. */
    INTEGER("integer"),

    /** Represents number types. */
    NUMBER("number"),

    /** Represents explicit nulls. */
    NULL("null"),

    /** Represents struct types. */
    OBJECT("object"),

    /** Represents strings. */
    STRING("string");

    /** The value of the type descriptors as rendered in json. */
    private final String jsonValue;

    /**
     * Creates a json type descriptor.
     *
     * @param jsonValue The value of the type descriptors as rendered in json.
     */
    SchemaType(String jsonValue)
    {
        this.jsonValue = jsonValue;
    }

    /**
     * Provides the json representation as a string.
     *
     * @return The json representation as a string.
     */
    @JsonValue
    public String toString()
    {
        return jsonValue;
    }
}
