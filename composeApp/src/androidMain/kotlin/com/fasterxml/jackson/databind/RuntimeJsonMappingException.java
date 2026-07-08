package com.fasterxml.jackson.databind;

public class RuntimeJsonMappingException extends RuntimeException {
    public RuntimeJsonMappingException(final JsonMappingException jsonMappingException) {
        super(jsonMappingException);
    }
    public RuntimeJsonMappingException(final String str) {
        super(str);
    }
    public RuntimeJsonMappingException(final String str, final JsonMappingException jsonMappingException) {
        super(str, jsonMappingException);
    }
}
