package com.thesett.util.validation.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.thesett.util.validation.core.JsonSchemaValidatorFactory;
import com.thesett.util.validation.model.JsonSchema;

@RunWith(Parameterized.class)
public class ValidationTest {
    private final Object toTest;
    private final boolean shouldPass;
    private final JsonSchema schema;
    private final JsonSchemaValidatorFactory validatorFactory;

    public ValidationTest(Object toTest, boolean shouldPass, JsonSchema schema) {
        this.toTest = toTest;
        this.shouldPass = shouldPass;
        this.schema = schema;
        this.validatorFactory = new JsonSchemaValidatorFactory();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<Object[]>();

        data.add(new Object[] { new TestBean(), true, null });

        // Simple validations on properties.
        JsonSchema minValueSchema = JsonSchema.object().property("testInt").minimum(1).build();
        data.add(new Object[] { new TestBean().withTestInt(0), false, minValueSchema });
        data.add(new Object[] { new TestBean().withTestInt(1), true, minValueSchema });

        JsonSchema minValueExclusiveSchema =
            JsonSchema.object().property("testInt").minimum(1).exclusiveMinimum(true).build();
        data.add(new Object[] { new TestBean().withTestInt(1), false, minValueExclusiveSchema });
        data.add(new Object[] { new TestBean().withTestInt(2), true, minValueExclusiveSchema });

        JsonSchema maxValueSchema = JsonSchema.object().property("testInt").maximum(10).build();
        data.add(new Object[] { new TestBean().withTestInt(11), false, maxValueSchema });
        data.add(new Object[] { new TestBean().withTestInt(10), true, maxValueSchema });

        JsonSchema maxValueExclusiveSchema =
            JsonSchema.object().property("testInt").maximum(10).exclusiveMaximum(true).build();
        data.add(new Object[] { new TestBean().withTestInt(10), false, maxValueExclusiveSchema });
        data.add(new Object[] { new TestBean().withTestInt(9), true, maxValueExclusiveSchema });

        JsonSchema maxLengthSchema = JsonSchema.object().property("testString").maxLength(5).build();
        data.add(new Object[] { new TestBean().withTestString("abcde"), true, maxLengthSchema });
        data.add(new Object[] { new TestBean().withTestString("abcdef"), false, maxLengthSchema });

        JsonSchema minLengthSchema = JsonSchema.object().property("testString").minLength(5).build();
        data.add(new Object[] { new TestBean().withTestString("abcde"), true, minLengthSchema });
        data.add(new Object[] { new TestBean().withTestString("abcd"), false, minLengthSchema });

        JsonSchema patternSchema = JsonSchema.object().property("testString").pattern("pass").build();
        data.add(new Object[] { new TestBean().withTestString("fail"), false, patternSchema });
        data.add(new Object[] { new TestBean().withTestString("pass"), true, patternSchema });

        // Present and not present checks.
        JsonSchema notNullSchema = JsonSchema.object().property("testString").isRequired().build();
        data.add(new Object[] { new TestBean().withTestString("pass"), true, notNullSchema });
        data.add(new Object[] { new TestBean(), false, notNullSchema });

        JsonSchema notNullCombinedSchema =
            JsonSchema.object().property("testString").isRequired().maxLength(5).build();
        data.add(new Object[] { new TestBean().withTestString("pass"), true, notNullCombinedSchema });
        data.add(new Object[] { new TestBean().withTestString("abcdef"), false, notNullCombinedSchema });
        data.add(new Object[] { new TestBean(), false, notNullCombinedSchema });

        // Validations on a nested object.
        JsonSchema nestedMinValueSchema =
            JsonSchema.object().property("innerTestBean").object().property("testInt").minimum(1).build();
        data.add(
            new Object[] {
                new TestBean().withInnerTestBean(new InnerTestBean().withTestInt(0)), false, nestedMinValueSchema
            });
        data.add(
            new Object[] {
                new TestBean().withInnerTestBean(new InnerTestBean().withTestInt(1)), true, nestedMinValueSchema
            });

        return data;
    }

    @Test
    public void testValidation() {
        JsonSchemaValidatorFactory.ValidatorBuilder builder = validatorFactory.getBuilder();

        if (schema != null) {
            builder.addSchema(toTest.getClass(), schema);
        }

        Validator validator = builder.build();
        Set<ConstraintViolation<Object>> errors = validator.validate(toTest);

        if (shouldPass) {
            Assert.assertEquals("There should be no validation errors.", 0, errors.size());
        } else {
            Assert.assertTrue("There should be validation errors.", errors.size() > 0);
        }
    }
}
