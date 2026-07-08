package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.Collection;

public abstract class JsonDeserializer<T> implements NullValueProvider {
    public abstract T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;
    public JsonDeserializer<?> getDelegatee() {
        return null;
    }
    public Collection<Object> getKnownPropertyNames() {
        return null;
    }
    public T getNullValue() {
        return null;
    }
    public ObjectIdReader getObjectIdReader() {
        return null;
    }
    public Class<?> handledType() {
        return null;
    }
    public boolean isCachable() {
        return false;
    }
    public LogicalType logicalType() {
        return null;
    }
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return null;
    }
    public JsonDeserializer<T> unwrappingDeserializer(final NameTransformer nameTransformer) {
        return this;
    }
    public T deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final T t) throws IOException {
        deserializationContext.handleBadMerge(this);
        return this.deserialize(jsonParser, deserializationContext);
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer, final T t) throws IOException {
        deserializationContext.handleBadMerge(this);
        return this.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
    }
    public JsonDeserializer<?> replaceDelegatee(final JsonDeserializer<?> jsonDeserializer) {
        throw new UnsupportedOperationException();
    }
    public T getNullValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return this.getNullValue();
    }
    public AccessPattern getNullAccessPattern() {
        return AccessPattern.CONSTANT;
    }
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.DYNAMIC;
    }
    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return this.getNullValue(deserializationContext);
    }
    public SettableBeanProperty findBackReference(final String str) {
        throw new IllegalArgumentException("Cannot handle managed/back reference '" + str + "': type: value deserializer of type " + this.getClass().getName() + " does not support them");
    }
    public Object getEmptyValue() {
        return this.getNullValue();
    }
    public static abstract class None extends JsonDeserializer<Object> {
        private None() {
        }
    }
}
