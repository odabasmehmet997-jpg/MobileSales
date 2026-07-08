package com.proje.mobilesales.features.dbmodel;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

public class Param {
    public JacksonInject.Value injection;
    public AnnotatedParameter annotated;
    public BeanPropertyDefinition propDef;
    private String paramNo; 
    private String paramValue;
    public String getParamNo() {
        return this.paramNo;
    }
    public void setParamNo(String str) {
        this.paramNo = str;
    }
    public String getParamValue() {
        return this.paramValue;
    }
    public void setParamValue(String str) {
        this.paramValue = str;
    }
}
