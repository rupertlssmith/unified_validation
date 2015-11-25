package com.thesett.util.validation.core;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;

import com.thesett.util.validation.model.JsonSchema;

/**
 * JsonSchemaValidatorFactory is a factory for building validators, built on top of Hibernate Bean Validator. In
 * addition to validation already defined using annotations on a class, a json schema may also be applied to a class to
 * create additional validation on it.
 *
 * <p/>Each time a validator is to be built, {@link #getBuilder()} should be called to obtain a fresh builder. This is
 * due to the way that Hibernate Bean Validator will only hold a single validation definition per class, implying that
 * different schemas applied to the same class may conflict. The mechanism used to differentiate different validations
 * for different contexts on the same class, is to group them with interfaces, but dynamically applying json schemas to
 * classes at runtime does not permit for defining new interfaces to 'group' validations by.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th>
 * <tr><td>  </td></tr>
 * </table></pre>
 */
public class JsonSchemaValidatorFactory {
    /**
     * Obtains a fresh builder to apply schemas to a class.
     *
     * @return A fresh builder to apply schemas to a class.
     */
    public ValidatorBuilder getBuilder() {
        return new ValidatorBuilder();
    }

    /**
     * Defines a builder for validators based on json schemas.
     */
    public static class ValidatorBuilder {
        /**
         * Holds a fresh validator configuration, so the schema does not conflict with others defined on the same class.
         */
        private final HibernateValidatorConfiguration config;

        /** Creates a fresh validator builder. */
        public ValidatorBuilder() {
            config = Validation.byProvider(HibernateValidator.class).configure();
        }

        /**
         * Adds a json schema to a class as additional validation on that class.
         *
         * @param  type   The class to apply the json schema to.
         * @param  schema The json schema.
         * @param  <C>    The type of the class to apply the json schema to.
         *
         * @return This builder for continuation.
         */
        public <C> ValidatorBuilder addSchema(Class<C> type, JsonSchema schema) {
            JsonSchemaConstraintMapping constraintMapping = new JsonSchemaConstraintMapping();
            constraintMapping.addSchema(type, schema);

            config.addMapping(constraintMapping);

            return this;
        }

        /**
         * Creates a validator instance configured with the json schemas on top of other validations.
         *
         * @return A validator instance configured with the json schemas on top of other validations.
         */
        public Validator build() {
            ValidatorFactory validatorFactory = config.buildValidatorFactory();

            return validatorFactory.getValidator();
        }
    }
}
