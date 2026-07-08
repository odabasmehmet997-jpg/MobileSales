package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;

public class InvalidFormatException extends MismatchedInputException {
    private static final long serialVersionUID = 1;
    protected final Object _value;

    @Deprecated
    public InvalidFormatException(final String str, final Object obj, final Class<?> cls) {
        super(null, str);
        _value = obj;
        _targetType = cls;
    }

    @Deprecated
    public InvalidFormatException(final String str, final JsonLocation jsonLocation, final Object obj, final Class<?> cls) {
        super(null, str, jsonLocation);
        _value = obj;
        _targetType = cls;
    }

    public InvalidFormatException(final JsonParser jsonParser, final String str, final Object obj, final Class<?> cls) {
        super(jsonParser, str, cls);
        _value = obj;
    }

    public static InvalidFormatException from(final JsonParser jsonParser, final String str, final Object obj, final Class<?> cls) {
        return new InvalidFormatException(jsonParser, str, obj, cls);
    }

    public Object getValue() {
        return _value;
    }
}
