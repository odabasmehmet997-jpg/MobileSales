package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;

public class InvalidTypeIdException extends MismatchedInputException {
    private static final long serialVersionUID = 1;
    protected final JavaType _baseType;
    protected final String _typeId;

    public InvalidTypeIdException(final JsonParser jsonParser, final String str, final JavaType javaType, final String str2) {
        super(jsonParser, str);
        _baseType = javaType;
        _typeId = str2;
    }

    public static InvalidTypeIdException from(final JsonParser jsonParser, final String str, final JavaType javaType, final String str2) {
        return new InvalidTypeIdException(jsonParser, str, javaType, str2);
    }

    public JavaType getBaseType() {
        return _baseType;
    }

    public String getTypeId() {
        return _typeId;
    }
}
