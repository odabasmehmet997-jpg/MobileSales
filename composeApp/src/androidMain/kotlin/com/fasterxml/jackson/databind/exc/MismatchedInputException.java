package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.util.ClassUtil;

public class MismatchedInputException extends JsonMappingException {
    protected Class<?> _targetType;

    protected MismatchedInputException(final JsonParser jsonParser, final String str) {
        this(jsonParser, str, (JavaType) null);
    }

    protected MismatchedInputException(final JsonParser jsonParser, final String str, final JsonLocation jsonLocation) {
        super(jsonParser, str, jsonLocation);
    }

    protected MismatchedInputException(final JsonParser jsonParser, final String str, final Class<?> cls) {
        super(jsonParser, str);
        _targetType = cls;
    }

    protected MismatchedInputException(final JsonParser jsonParser, final String str, final JavaType javaType) {
        super(jsonParser, str);
        _targetType = ClassUtil.rawClass(javaType);
    }

    @Deprecated
    public static MismatchedInputException from(final JsonParser jsonParser, final String str) {
        return MismatchedInputException.from(jsonParser, (Class<?>) null, str);
    }

    public static MismatchedInputException from(final JsonParser jsonParser, final JavaType javaType, final String str) {
        return new MismatchedInputException(jsonParser, str, javaType);
    }

    public static MismatchedInputException from(final JsonParser jsonParser, final Class<?> cls, final String str) {
        return new MismatchedInputException(jsonParser, str, cls);
    }

    public MismatchedInputException setTargetType(final JavaType javaType) {
        _targetType = javaType.getRawClass();
        return this;
    }

    public Class<?> getTargetType() {
        return _targetType;
    }
}
