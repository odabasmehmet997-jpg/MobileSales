package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;
import java.io.Serializable;
public class ObjectIdReader implements Serializable {
    private static final long serialVersionUID = 1;
    protected final JsonDeserializer<Object> _deserializer;
    protected final JavaType _idType;
    public final ObjectIdGenerator<?> generator;
    public final SettableBeanProperty idProperty;
    public final PropertyName propertyName;
    public final ObjectIdResolver resolver;
    protected ObjectIdReader(final JavaType javaType, final PropertyName propertyName, final ObjectIdGenerator<?> objectIdGenerator, final JsonDeserializer<?> jsonDeserializer, final SettableBeanProperty settableBeanProperty, final ObjectIdResolver objectIdResolver) {
        _idType = javaType;
        this.propertyName = propertyName;
        generator = objectIdGenerator;
        resolver = objectIdResolver;
        _deserializer = (JsonDeserializer<Object>) jsonDeserializer;
        idProperty = settableBeanProperty;
    }
    public static ObjectIdReader construct(final JavaType javaType, final PropertyName propertyName, final ObjectIdGenerator<?> objectIdGenerator, final JsonDeserializer<?> jsonDeserializer, final SettableBeanProperty settableBeanProperty, final ObjectIdResolver objectIdResolver) {
        return new ObjectIdReader(javaType, propertyName, objectIdGenerator, jsonDeserializer, settableBeanProperty, objectIdResolver);
    }
    public JsonDeserializer<Object> getDeserializer() {
        return _deserializer;
    }
    public JavaType getIdType() {
        return _idType;
    }
    public boolean maySerializeAsObject() {
        return generator.maySerializeAsObject();
    }
    public boolean isValidReferencePropertyName(final String str, final JsonParser jsonParser) {
        return generator.isValidReferencePropertyName(str, jsonParser);
    }
    public Object readObjectReference(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return _deserializer.deserialize(jsonParser, deserializationContext);
    }
}
