package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

public final class TypeWrappedDeserializer extends JsonDeserializer<Object> implements Serializable {
    private static final long serialVersionUID = 1;
    private final JsonDeserializer<Object> _deserializer;
    private final TypeDeserializer _typeDeserializer;
    public TypeWrappedDeserializer(final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) {
        _typeDeserializer = typeDeserializer;
        _deserializer = (JsonDeserializer<Object>) jsonDeserializer;
    }
    public LogicalType logicalType() {
        return _deserializer.logicalType();
    }
    public Class<?> handledType() {
        return _deserializer.handledType();
    }
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return _deserializer.supportsUpdate(deserializationConfig);
    }
    public JsonDeserializer<?> getDelegatee() {
        return _deserializer.getDelegatee();
    }
    public Collection<Object> getKnownPropertyNames() {
        return _deserializer.getKnownPropertyNames();
    }
    public Object getNullValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return _deserializer.getNullValue(deserializationContext);
    }
    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return _deserializer.getEmptyValue(deserializationContext);
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return _deserializer.deserializeWithType(jsonParser, deserializationContext, _typeDeserializer);
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        return _deserializer.deserialize(jsonParser, deserializationContext, obj);
    }
}
