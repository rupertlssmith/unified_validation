package com.thesett.util.validation.test;

public class InnerTestBean {
    private Integer testInt;

    public Integer getTestInt() {
        return testInt;
    }

    public void setTestInt(Integer testInt) {
        this.testInt = testInt;
    }

    public InnerTestBean withTestInt(Integer testInt) {
        this.testInt = testInt;

        return this;
    }
}
