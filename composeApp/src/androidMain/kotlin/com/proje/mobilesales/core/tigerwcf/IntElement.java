package com.proje.mobilesales.core.tigerwcf;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;



public class IntElement {

    @Attribute(empty = "d:int", name = "i:type", required = false)
    private String type;

    @Text(required = false)
    private int value;

    public int getValue() {
        return this.value;
    }

    public void setValue(int i2) {
        this.value = i2;
    }

    public IntElement(int i2) {
        this.value = i2;
    }

    public IntElement() {
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }
}
