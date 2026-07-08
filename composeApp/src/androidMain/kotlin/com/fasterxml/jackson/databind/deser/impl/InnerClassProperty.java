package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class InnerClassProperty extends SettableBeanProperty.Delegating {
    private static final long serialVersionUID = 1;
    private AnnotatedConstructor _annotated;
    private final transient Constructor<?> _creator;
    public InnerClassProperty(final SettableBeanProperty settableBeanProperty, final Constructor<?> constructor) {
        super(settableBeanProperty);
        _creator = constructor;
    }
    private InnerClassProperty(final SettableBeanProperty settableBeanProperty, final AnnotatedConstructor annotatedConstructor) {
        super(settableBeanProperty);
        _annotated = annotatedConstructor;
        final Constructor<?> annotated = null == annotatedConstructor ? null : annotatedConstructor.getAnnotated();
        _creator = annotated;
        if (null == annotated) {
            throw new IllegalArgumentException("Missing constructor (broken JDK (de)serialization?)");
        }
    }
    protected SettableBeanProperty withDelegate(final SettableBeanProperty settableBeanProperty) {
        return settableBeanProperty == delegate ? this : new InnerClassProperty(settableBeanProperty, _creator);
    }
    public void deserializeAndSet(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IllegalAccessException, InstantiationException, IOException, IllegalArgumentException, InvocationTargetException {
        Object objNewInstance;
        final Object objDeserializeWithType;
        if (JsonToken.VALUE_NULL == jsonParser.currentToken()) {
            objDeserializeWithType = _valueDeserializer.getNullValue(deserializationContext);
        } else {
            final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
            if (null != typeDeserializer) {
                objDeserializeWithType = _valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            } else {
                try {
                    objNewInstance = _creator.newInstance(obj);
                } catch (final Exception e2) {
                    ClassUtil.unwrapAndThrowAsIAE(e2, String.format("Failed to instantiate class %s, problem: %s", _creator.getDeclaringClass().getName(), e2.getMessage()));
                    objNewInstance = null;
                }
                _valueDeserializer.deserialize(jsonParser, deserializationContext, objNewInstance);
                objDeserializeWithType = objNewInstance;
            }
        }
        this.set(obj, objDeserializeWithType);
    }
    Object readResolve() {
        return new InnerClassProperty(this, _annotated);
    }
    Object writeReplace() {
        return null == this._annotated ? new InnerClassProperty(this, new AnnotatedConstructor(null, _creator, null, null)) : this;
    }
    public AnnotatedMethod getSetter() {
        return null;
    }
    public AnnotatedMember getMember() {
        return _annotated;
    }
}
