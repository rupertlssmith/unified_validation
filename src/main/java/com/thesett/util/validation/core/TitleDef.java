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

import org.hibernate.validator.cfg.ConstraintDef;

/**
 * TitleDef is a dummy constraint definition, which is used to hold the value of the json-schema 'title' field.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th>
 * <tr><td> Hold the json-schema 'title' field in a dummy constraint definition. </td></tr>
 * </table></pre>
 */
public class TitleDef extends ConstraintDef<TitleDef, Title>
{
    public TitleDef()
    {
        super(Title.class);
    }

    public TitleDef title(String title)
    {
        addParameter("title", title);

        return this;
    }
}
