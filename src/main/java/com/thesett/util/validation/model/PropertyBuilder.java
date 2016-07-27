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

/**
 * PropertyBuilder is a builder for {@link JsonSchema}s, that allows properties to be added to an object.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th>
 * <tr><td> Define properties of an object. </td></tr>
 * </table></pre>
 */
public class PropertyBuilder
{
    /** The root of the schema being built. */
    protected final JsonSchema rootSchema;

    /** The parent schema that object properties are being added to. */
    protected final JsonSchema parentSchema;

    /**
     * Creates a property builder on a parent schema.
     *
     * @param rootSchema   The root of the schema being built.
     * @param parentSchema The parent schema that object properties are being added to.
     */
    public PropertyBuilder(JsonSchema rootSchema, JsonSchema parentSchema)
    {
        this.rootSchema = rootSchema;
        this.parentSchema = parentSchema;
    }

    /**
     * Adds a property to an object schema.
     *
     * @param  name The name of the property to add.
     *
     * @return A builder to add schema constraints to the property.
     */
    public ConstraintBuilder property(String name)
    {
        JsonSchema propertySchema = new JsonSchema();

        if (parentSchema.getProperties() == null)
        {
            parentSchema.initProperties();
        }

        parentSchema.getProperties().put(name, propertySchema);

        return new ConstraintBuilder(rootSchema, parentSchema, propertySchema, name);
    }

    /**
     * Builds the json schema.
     *
     * @return The json schema.
     */
    public JsonSchema build()
    {
        return rootSchema;
    }
}
