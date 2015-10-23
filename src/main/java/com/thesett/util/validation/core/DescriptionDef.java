package com.thesett.util.validation.core;

import org.hibernate.validator.cfg.ConstraintDef;

/**
 * DescriptionDef is a dummy constraint definition, which is used to hold the value of the json-schema 'description'
 * field.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th>
 * <tr><td> Hold the json-schema 'description' field in a dummy constraint definition. </td></tr>
 * </table></pre>
 */
public class DescriptionDef extends ConstraintDef<DescriptionDef, Description> {
    public DescriptionDef() {
        super(Description.class);
    }

    public DescriptionDef description(String description) {
        addParameter("description", description);

        return this;
    }
}
