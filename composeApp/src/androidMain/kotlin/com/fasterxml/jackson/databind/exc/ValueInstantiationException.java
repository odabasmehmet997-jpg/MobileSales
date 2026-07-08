package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ValueInstantiationException extends JsonMappingException {
    protected final JavaType _type;

    protected ValueInstantiationException(final JsonParser jsonParser, final String str, final JavaType javaType, final Throwable th) {
        super(jsonParser, str, th);
        _type = javaType;
    }

    protected ValueInstantiationException(final JsonParser jsonParser, final String str, final JavaType javaType) {
        super(jsonParser, str);
        _type = javaType;
    }

    public static ValueInstantiationException from(final JsonParser jsonParser, final String str, final JavaType javaType) {
        return new ValueInstantiationException(jsonParser, str, javaType);
    }

    public static ValueInstantiationException from(final JsonParser jsonParser, final String str, final JavaType javaType, final Throwable th) {
        return new ValueInstantiationException(jsonParser, str, javaType, th);
    }

    public JavaType getType() {
        return _type;
    }
}
