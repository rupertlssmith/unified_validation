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

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.thesett.util.validation.model.JsonSchema;

/**
 * JsonSchemaMergeImpl merges multiple json-schemas together to arrive at a single schema which is equivalent to
 * validating against each of the individual schemas.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th>
 * <tr><td> Merge multiple json schemas together to form one schema. </td></tr>
 * </table></pre>
 */
public class JsonSchemaMergeImpl implements JsonSchemaMerge
{
    /** {@inheritDoc} */
    public JsonSchema merge(JsonSchema schema1, JsonSchema schema2)
    {
        JsonSchema result = JsonSchema.object().build();

        mergeInto(result, schema1);
        mergeInto(result, schema2);

        return result;
    }

    /** {@inheritDoc} */
    public JsonSchema merge(JsonSchema... schemas)
    {
        return null;
    }

    private void mergeInto(JsonSchema target, JsonSchema source)
    {
        mergeDescription(target, source);
        mergeTitle(target, source);
        mergeType(target, source);
        mergeMinLength(target, source);
        mergeMaxLength(target, source);
        mergeMinimum(target, source);
        mergeMaximum(target, source);
        mergeExclusiveMaximum(target, source);
        mergeExclusiveMinimum(target, source);
        mergePattern(target, source);

        mergeRequiredFields(target, source);

        // Recursively merge in properties of objects.
        if (source.getProperties() != null)
        {
            for (Map.Entry<String, JsonSchema> entry : source.getProperties().entrySet())
            {
                String propertyName = entry.getKey();
                JsonSchema sourcePropertySchema = entry.getValue();

                mergeProperty(propertyName, sourcePropertySchema, target);
            }
        }
    }

    /**
     * Checks if the named property exists on the target, and merge the source schema into it if so. Otherwise, creates
     * a new property on the target with an empty schema an merges into that.
     *
     * @param propertyName The name of the property to merge.
     * @param sourceSchema The source schema to merge the property from.
     * @param targetSchema The target schema to merge the property into.
     */
    private void mergeProperty(String propertyName, JsonSchema sourceSchema, JsonSchema targetSchema)
    {
        if ((targetSchema.getProperties() != null) && targetSchema.getProperties().containsKey(propertyName))
        {
            JsonSchema targetPropertySchema = targetSchema.getProperties().get(propertyName);

            mergeInto(targetPropertySchema, sourceSchema);
        }
        else
        {
            JsonSchema targetPropertySchema = JsonSchema.object().build();
            Map<String, JsonSchema> targetProperties = targetSchema.getProperties();

            if (targetProperties == null)
            {
                targetProperties = new LinkedHashMap<>();
                targetSchema.setProperties(targetProperties);
            }

            targetProperties.put(propertyName, targetPropertySchema);

            mergeInto(targetPropertySchema, sourceSchema);
        }
    }

    private void mergeRequiredFields(JsonSchema target, JsonSchema source)
    {
        // Merge in any required fields.
        List<String> sourceRequired = source.getRequired();
        List<String> targetRequired = target.getRequired();

        if ((sourceRequired != null) && !sourceRequired.isEmpty())
        {
            if (targetRequired == null)
            {
                targetRequired = new LinkedList<String>();
                target.setRequired(targetRequired);
            }

            targetRequired.addAll(sourceRequired);
        }
    }

    private void mergePattern(JsonSchema target, JsonSchema source)
    {
        if (source.getPattern() != null)
        {
            target.setPattern(source.getPattern());
        }
    }

    private void mergeExclusiveMinimum(JsonSchema target, JsonSchema source)
    {
        if (source.getExclusiveMaximum() != null)
        {
            target.setExclusiveMaximum(source.getExclusiveMaximum());
        }
    }

    private void mergeExclusiveMaximum(JsonSchema target, JsonSchema source)
    {
        if (source.getExclusiveMinimum() != null)
        {
            target.setExclusiveMinimum(source.getExclusiveMinimum());
        }
    }

    private void mergeMaximum(JsonSchema target, JsonSchema source)
    {
        if ((source.getMaximum() != null) &&
                (((target.getMaximum() != null) && (target.getMaximum().compareTo(source.getMaximum()) > 0)) ||
                    (target.getMaximum() == null)))
        {
            target.setMaximum(source.getMaximum());
        }
    }

    private void mergeMinimum(JsonSchema target, JsonSchema source)
    {
        if ((source.getMinimum() != null) &&
                (((target.getMinimum() != null) && (target.getMinimum().compareTo(source.getMinimum()) < 0)) ||
                    (target.getMaxLength() == null)))
        {
            target.setMinimum(source.getMinimum());
        }
    }

    private void mergeMaxLength(JsonSchema target, JsonSchema source)
    {
        if ((source.getMaxLength() != null) &&
                (((target.getMaxLength() != null) && (target.getMaxLength() >= source.getMaxLength())) ||
                    (target.getMaxLength() == null)))
        {
            target.setMaxLength(source.getMaxLength());
        }
    }

    private void mergeMinLength(JsonSchema target, JsonSchema source)
    {
        if ((source.getMinLength() != null) &&
                (((target.getMinLength() != null) && (target.getMinLength() <= source.getMinLength())) ||
                    (target.getMinLength() == null)))
        {
            target.setMinLength(source.getMinLength());
        }
    }

    private void mergeType(JsonSchema target, JsonSchema source)
    {
        if (source.getType() != null)
        {
            target.setType(source.getType());
        }
    }

    private void mergeTitle(JsonSchema target, JsonSchema source)
    {
        if (source.getTitle() != null)
        {
            target.setTitle(source.getTitle());
        }
    }

    private void mergeDescription(JsonSchema target, JsonSchema source)
    {
        if (source.getDescription() != null)
        {
            target.setDescription(source.getDescription());
        }
    }
}
