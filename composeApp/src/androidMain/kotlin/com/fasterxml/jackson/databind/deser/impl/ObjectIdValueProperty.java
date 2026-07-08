package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public final class ObjectIdValueProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    private final ObjectIdReader _objectIdReader;
    public <A extends Annotation> A getAnnotation(final Class<A> cls) {
        return null;
    }
    public AnnotatedMember getMember() {
        return null;
    }
    public AnnotatedMethod getSetter() {
        return null;
    }
    public ObjectIdValueProperty(final ObjectIdReader objectIdReader, final PropertyMetadata propertyMetadata) {
        super(objectIdReader.propertyName, objectIdReader.getIdType(), propertyMetadata, objectIdReader.getDeserializer());
        _objectIdReader = objectIdReader;
    }
    private ObjectIdValueProperty(final ObjectIdValueProperty objectIdValueProperty, final JsonDeserializer<?> jsonDeserializer, final NullValueProvider nullValueProvider) {
        super(objectIdValueProperty, jsonDeserializer, nullValueProvider);
        _objectIdReader = objectIdValueProperty._objectIdReader;
    }
    private ObjectIdValueProperty(final ObjectIdValueProperty objectIdValueProperty, final PropertyName propertyName) {
        super(objectIdValueProperty, propertyName);
        _objectIdReader = objectIdValueProperty._objectIdReader;
    }
    public SettableBeanProperty withName(final PropertyName propertyName) {
        return new ObjectIdValueProperty(this, propertyName);
    }
    public SettableBeanProperty withValueDeserializer(final JsonDeserializer<?> jsonDeserializer) {
        final JsonDeserializer<?> jsonDeserializer2 = _valueDeserializer;
        if (jsonDeserializer2 == jsonDeserializer) {
            return this;
        }
        NullValueProvider nullValueProvider = _nullProvider;
        if (jsonDeserializer2 == nullValueProvider) {
            nullValueProvider = jsonDeserializer;
        }
        return new ObjectIdValueProperty(this, jsonDeserializer, nullValueProvider);
    }
    public SettableBeanProperty withNullProvider(final NullValueProvider nullValueProvider) {
        return new ObjectIdValueProperty(this, _valueDeserializer, nullValueProvider);
    }
    public void deserializeAndSet(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        this.deserializeSetAndReturn(jsonParser, deserializationContext, obj);
    }
    public Object deserializeSetAndReturn(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            return null;
        }
        final Object objDeserialize = _valueDeserializer.deserialize(jsonParser, deserializationContext);
        final ObjectIdReader objectIdReader = _objectIdReader;
        deserializationContext.findObjectId(objDeserialize, objectIdReader.generator, objectIdReader.resolver).bindItem(obj);
        final SettableBeanProperty settableBeanProperty = _objectIdReader.idProperty;
        try {
            return null != settableBeanProperty ? settableBeanProperty.setAndReturn(obj, objDeserialize) : obj;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    public void set(final Object obj, final Object obj2) throws IOException {
        this.setAndReturn(obj, obj2);
    }
    public Object setAndReturn(final Object obj, final Object obj2) throws IOException {
        final SettableBeanProperty settableBeanProperty = _objectIdReader.idProperty;
        if (null == settableBeanProperty) {
            throw new UnsupportedOperationException("Should not call set() on ObjectIdProperty that has no SettableBeanProperty");
        }
        try {
            return settableBeanProperty.setAndReturn(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
