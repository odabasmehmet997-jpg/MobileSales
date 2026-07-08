package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public abstract class DeserializationProblemHandler {
    public static final Object NOT_HANDLED = new Object();
    public JavaType handleMissingTypeId(final DeserializationContext deserializationContext, final JavaType javaType, final TypeIdResolver typeIdResolver, final String str) throws IOException {
        return null;
    }
    public boolean handleUnknownProperty(final DeserializationContext deserializationContext, final JsonParser jsonParser, final JsonDeserializer<?> jsonDeserializer, final Object obj, final String str) throws IOException {
        return false;
    }
    public JavaType handleUnknownTypeId(final DeserializationContext deserializationContext, final JavaType javaType, final String str, final TypeIdResolver typeIdResolver, final String str2) throws IOException {
        return null;
    }
    public Object handleWeirdKey(final DeserializationContext deserializationContext, final Class<?> cls, final String str, final String str2) throws IOException {
        return DeserializationProblemHandler.NOT_HANDLED;
    }
    public Object handleWeirdStringValue(final DeserializationContext deserializationContext, final Class<?> cls, final String str, final String str2) throws IOException {
        return DeserializationProblemHandler.NOT_HANDLED;
    }
    public Object handleWeirdNumberValue(final DeserializationContext deserializationContext, final Class<?> cls, final Number number, final String str) throws IOException {
        return DeserializationProblemHandler.NOT_HANDLED;
    }
    public Object handleWeirdNativeValue(final DeserializationContext deserializationContext, final JavaType javaType, final Object obj, final JsonParser jsonParser) throws IOException {
        return DeserializationProblemHandler.NOT_HANDLED;
    }
    public Object handleUnexpectedToken(final DeserializationContext deserializationContext, final Class<?> cls, final JsonToken jsonToken, final JsonParser jsonParser, final String str) throws IOException {
        return DeserializationProblemHandler.NOT_HANDLED;
    }
    public Object handleUnexpectedToken(final DeserializationContext deserializationContext, final JavaType javaType, final JsonToken jsonToken, final JsonParser jsonParser, final String str) throws IOException {
        return this.handleUnexpectedToken(deserializationContext, javaType.getRawClass(), jsonToken, jsonParser, str);
    }
    public Object handleInstantiationProblem(final DeserializationContext deserializationContext, final Class<?> cls, final Object obj, final Throwable th) throws IOException {
        return DeserializationProblemHandler.NOT_HANDLED;
    }
    public Object handleMissingInstantiator(final DeserializationContext deserializationContext, final Class<?> cls, final ValueInstantiator valueInstantiator, final JsonParser jsonParser, final String str) throws IOException {
        return this.handleMissingInstantiator(deserializationContext, cls, jsonParser, str);
    }
    public Object handleMissingInstantiator(final DeserializationContext deserializationContext, final Class<?> cls, final JsonParser jsonParser, final String str) throws IOException {
        return DeserializationProblemHandler.NOT_HANDLED;
    }
}
