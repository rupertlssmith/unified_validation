Unified Validation
==================

This 'Unified Validation' library is aimed at bridging the gap between server side validation in Java, usuing the JSR303 standard, and front end validation in javascript using json-schema (json-schema.org). In order to do this, greater control over the JSR303 side is needed than is available through its standard API, so this implementation is specific to the Hibernate Bean Validation implementation.

The following capabilities are addressed:

* A json-schema can be converted into a JSR303 Validator.
* Validations specified in json-schema can be overlayed on top of validations already defined with JSR303 annotations, XML configuration or using Hibernates programmatic API to specify validations.

The following capabilities will soon be addressed:

* Multiple json-schemas can be stacked together to produce a single validation.
* A Bean Validation configuration can be converted into a json-schema.

### Build it

Once you have obtained the source code:

    > mvn clean install

### Use it

A fluent API exists to help with building json-schemas in code. For example:
                
    JsonSchema minValueSchema = JsonSchema.object().property("testInt").minimum(1).build();

The JsonSchema object can also be deserialized from JSON using Jackson or gson in the usual way.

A Validator can be constructed, that adds the validations defined in json-schemas on top of the validations defined using annotations, XML or Hibernates programmatic API, with the JsonSchemaValidatorFactory. Here is an example:

    JsonSchemaValidatorFactory.ValidatorBuilder builder = validatorFactory.getBuilder();
    builder.addSchema(myBean.getClass(), schema);

    Validator validator = builder.build();

This Validator can then be used to perform server side validation in the usual way:
        
    Set<ConstraintViolation> errors = validator.validate(myBean);
