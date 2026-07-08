package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.util.ClassUtil;

public class InvalidNullException extends MismatchedInputException {
    private static final long serialVersionUID = 1;
    protected final PropertyName _propertyName;

    protected InvalidNullException(final DeserializationContext deserializationContext, final String str, final PropertyName propertyName) {
        super(deserializationContext.getParser(), str);
        _propertyName = propertyName;
    }

    public static InvalidNullException from(final DeserializationContext deserializationContext, final PropertyName propertyName, final JavaType javaType) {
        final InvalidNullException invalidNullException = new InvalidNullException(deserializationContext, String.format("Invalid `null` value encountered for property %s", ClassUtil.quotedOr(propertyName, "<UNKNOWN>")), propertyName);
        if (null != javaType) {
            invalidNullException.setTargetType(javaType);
        }
        return invalidNullException;
    }

    public PropertyName getPropertyName() {
        return _propertyName;
    }
}
