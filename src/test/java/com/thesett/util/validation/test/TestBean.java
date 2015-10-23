package com.thesett.util.validation.test;

import java.math.BigDecimal;

public class TestBean {
    private String testString;
    private Integer testInt;
    private Float testFloat;
    private Double testDouble;
    private BigDecimal testDecimal;
    private InnerTestBean innerTestBean;

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public TestBean withTestString(String testString) {
        this.testString = testString;

        return this;
    }

    public Integer getTestInt() {
        return testInt;
    }

    public void setTestInt(Integer testInt) {
        this.testInt = testInt;
    }

    public TestBean withTestInt(Integer testInt) {
        this.testInt = testInt;

        return this;
    }

    public Float getTestFloat() {
        return testFloat;
    }

    public void setTestFloat(Float testFloat) {
        this.testFloat = testFloat;
    }

    public TestBean withTestFloat(Float testFloat) {
        this.testFloat = testFloat;

        return this;
    }

    public Double getTestDouble() {
        return testDouble;
    }

    public void setTestDouble(Double testDouble) {
        this.testDouble = testDouble;
    }

    public TestBean withTestDouble(Double testDouble) {
        this.testDouble = testDouble;

        return this;
    }

    public BigDecimal getTestDecimal() {
        return testDecimal;
    }

    public void setTestDecimal(BigDecimal testDecimal) {
        this.testDecimal = testDecimal;
    }

    public TestBean withTestDecimal(BigDecimal testDecimal) {
        this.testDecimal = testDecimal;

        return this;
    }

    public InnerTestBean getInnerTestBean() {
        return innerTestBean;
    }

    public void setInnerTestBean(InnerTestBean innerTestBean) {
        this.innerTestBean = innerTestBean;
    }

    public TestBean withInnerTestBean(InnerTestBean testBean) {
        this.innerTestBean = testBean;

        return this;
    }
}
