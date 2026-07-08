package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.Iterator;

public abstract class JsonSerializer<T> {
    public static abstract class None extends JsonSerializer<Object> {
    }
    public JsonSerializer<?> getDelegatee() {
        return null;
    }
    public Class<T> handledType() {
        return null;
    }
    public boolean isEmpty(final SerializerProvider serializerProvider, final T t) {
        return null == t;
    }
    public boolean isUnwrappingSerializer() {
        return false;
    }
    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;
    public JsonSerializer<T> unwrappingSerializer(final NameTransformer nameTransformer) {
        return this;
    }
    public boolean usesObjectId() {
        return false;
    }
    public JsonSerializer<?> withFilterId(final Object obj) {
        return this;
    }
    public JsonSerializer<T> replaceDelegatee(final JsonSerializer<?> jsonSerializer) {
        throw new UnsupportedOperationException();
    }
    public void serializeWithType(final T t, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider, final TypeSerializer typeSerializer) throws IOException {
        Class<?> clsHandledType = this.handledType();
        if (null == clsHandledType) {
            clsHandledType = t.getClass();
        }
        serializerProvider.reportBadDefinition((Class<?>) clsHandledType, String.format("Type id handling not implemented for type %s (by serializer of type %s)", clsHandledType.getName(), this.getClass().getName()));
    }
    public boolean isEmpty(final T t) {
        return this.isEmpty(null, t);
    }
    public Iterator<PropertyWriter> properties() {
        return ClassUtil.emptyIterator();
    }
    public void acceptJsonFormatVisitor(final JsonFormatVisitorWrapper jsonFormatVisitorWrapper, final JavaType javaType) throws JsonMappingException {
        jsonFormatVisitorWrapper.expectAnyFormat(javaType);
    }
}
