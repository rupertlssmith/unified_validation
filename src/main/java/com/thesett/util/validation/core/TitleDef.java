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
public class TitleDef extends ConstraintDef<TitleDef, Title> {
    public TitleDef() {
        super(Title.class);
    }

    public TitleDef title(String title) {
        addParameter("title", title);

        return this;
    }
}
