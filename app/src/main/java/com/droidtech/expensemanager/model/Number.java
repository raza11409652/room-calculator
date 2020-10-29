package com.droidtech.expensemanager.model;

public class Number {
    String value  ;
    boolean isDigit ;

    public Number(String value, boolean isDigit) {
        this.value = value;
        this.isDigit = isDigit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isDigit() {
        return isDigit;
    }

    public void setDigit(boolean digit) {
        isDigit = digit;
    }
}
