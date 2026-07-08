package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public abstract class ObjectIdReferenceProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    private final SettableBeanProperty _forward;
    protected ObjectIdReferenceProperty(final SettableBeanProperty settableBeanProperty, final ObjectIdInfo objectIdInfo) {
        super(settableBeanProperty);
        _forward = settableBeanProperty;
        _objectIdInfo = objectIdInfo;
    }
    protected ObjectIdReferenceProperty(final ObjectIdReferenceProperty objectIdReferenceProperty, final JsonDeserializer<?> jsonDeserializer, final NullValueProvider nullValueProvider) {
        super(objectIdReferenceProperty, jsonDeserializer, nullValueProvider);
        _forward = objectIdReferenceProperty._forward;
        _objectIdInfo = objectIdReferenceProperty._objectIdInfo;
    }
    protected ObjectIdReferenceProperty(final ObjectIdReferenceProperty objectIdReferenceProperty, final PropertyName propertyName) {
        super(objectIdReferenceProperty, propertyName);
        _forward = objectIdReferenceProperty._forward;
        _objectIdInfo = objectIdReferenceProperty._objectIdInfo;
    }
    public SettableBeanProperty withName(final PropertyName propertyName) {
        return new ObjectIdReferenceProperty(this, propertyName) {
            public AnnotatedMethod getSetter() {
                return null;
            }
            public ObjectIdReferenceProperty withName(final PropertyName propertyName) {
                return new ObjectIdReferenceProperty(this, propertyName) {
                    public AnnotatedMethod getSetter() {
                        return null;
                    }
                };
            }
        };
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
        return new ObjectIdReferenceProperty(this, jsonDeserializer, nullValueProvider) {
            @Override
            public AnnotatedMethod getSetter() {
                return null;
            }
        };
    }
    public SettableBeanProperty withNullProvider(final NullValueProvider nullValueProvider) {
        return new ObjectIdReferenceProperty(this, _valueDeserializer, nullValueProvider) {
            @Override
            public AnnotatedMethod getSetter() {
                return null;
            }
        };
    }
    public void fixAccess(final DeserializationConfig deserializationConfig) {
        final SettableBeanProperty settableBeanProperty = _forward;
        if (null != settableBeanProperty) {
            settableBeanProperty.fixAccess(deserializationConfig);
        }
    }
    public <A extends Annotation> A getAnnotation() {
        return this.getAnnotation(null);
    }
    public <A extends Annotation> A getAnnotation(final Class cls) {
        return _forward.getAnnotation(cls);
    }
    public AnnotatedMember getMember() {
        return _forward.getMember();
    }
    public int getCreatorIndex() {
        return _forward.getCreatorIndex();
    }
    public void deserializeAndSet(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        this.deserializeSetAndReturn(jsonParser, deserializationContext, obj);
    }
    public Object deserializeSetAndReturn(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        try {
            return this.setAndReturn(obj, this.deserialize(jsonParser, deserializationContext));
        } catch (final UnresolvedForwardReference e2) {
            if (null == this._objectIdInfo && null == this._valueDeserializer.getObjectIdReader()) {
                throw JsonMappingException.from(jsonParser, "Unresolved forward reference but no identity info", e2);
            }
            e2.getRoid().appendReferring(new PropertyReferring(this, e2, _type.getRawClass(), obj));
            return null;
        }
    }
    public void set(final Object obj, final Object obj2) throws IOException {
        try {
            _forward.set(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    public Object setAndReturn(final Object obj, final Object obj2) throws IOException {
        try {
            return _forward.setAndReturn(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    public static final class PropertyReferring extends ReadableObjectId.Referring {
        private final ObjectIdReferenceProperty _parent;
        public final Object _pojo;
        public PropertyReferring(final ObjectIdReferenceProperty objectIdReferenceProperty, final UnresolvedForwardReference unresolvedForwardReference, final Class<?> cls, final Object obj) {
            super(unresolvedForwardReference, cls);
            _parent = objectIdReferenceProperty;
            _pojo = obj;
        }
        public void handleResolvedForwardReference(final Object obj, final Object obj2) throws IOException {
            if (!this.hasId(obj)) {
                throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj + "] that wasn't previously seen as unresolved.");
            }
            _parent.set(_pojo, obj2);
        }
    }
}
