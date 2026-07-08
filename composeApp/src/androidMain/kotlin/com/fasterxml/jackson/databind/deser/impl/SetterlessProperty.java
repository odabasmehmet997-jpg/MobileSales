package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class SetterlessProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    private final AnnotatedMethod _annotated;
    private final Method _getter;
    public SetterlessProperty(final BeanPropertyDefinition beanPropertyDefinition, final JavaType javaType, final TypeDeserializer typeDeserializer, final Annotations annotations, final AnnotatedMethod annotatedMethod) {
        super(beanPropertyDefinition, javaType, typeDeserializer, annotations);
        _annotated = annotatedMethod;
        _getter = annotatedMethod.getAnnotated();
    }
    private SetterlessProperty(final SetterlessProperty setterlessProperty, final JsonDeserializer<?> jsonDeserializer, final NullValueProvider nullValueProvider) {
        super(setterlessProperty, jsonDeserializer, nullValueProvider);
        _annotated = setterlessProperty._annotated;
        _getter = setterlessProperty._getter;
    }
    private SetterlessProperty(final SetterlessProperty setterlessProperty, final PropertyName propertyName) {
        super(setterlessProperty, propertyName);
        _annotated = setterlessProperty._annotated;
        _getter = setterlessProperty._getter;
    }
    public SettableBeanProperty withName(final PropertyName propertyName) {
        return new SetterlessProperty(this, propertyName);
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
        return new SetterlessProperty(this, jsonDeserializer, nullValueProvider);
    }
    public SettableBeanProperty withNullProvider(final NullValueProvider nullValueProvider) {
        return new SetterlessProperty(this, _valueDeserializer, nullValueProvider);
    }
    public void fixAccess(final DeserializationConfig deserializationConfig) {
        _annotated.fixAccess(deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }
    public <A extends Annotation> A getAnnotation(final Class cls) {
        return (A) _annotated.getAnnotation(cls);
    }
    public AnnotatedMember getMember() {
        return _annotated;
    }
    public void deserializeAndSet(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            return;
        }
        if (null != this._valueTypeDeserializer) {
            deserializationContext.reportBadDefinition(this.getType(), String.format("Problem deserializing 'setterless' property (\"%s\"): no way to handle typed deser with setterless yet", this.getName()));
        }
        try {
            final Object objInvoke = _getter.invoke(obj, null);
            if (null == objInvoke) {
                deserializationContext.reportBadDefinition(this.getType(), String.format("Problem deserializing 'setterless' property '%s': get method returned null", this.getName()));
            }
            _valueDeserializer.deserialize(jsonParser, deserializationContext, objInvoke);
        } catch (final Exception e2) {
            this._throwAsIOE(jsonParser, e2);
        }
    }
    public Object deserializeSetAndReturn(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        this.deserializeAndSet(jsonParser, deserializationContext, obj);
        return obj;
    }
    public void set(final Object obj, final Object obj2) throws IOException {
        throw new UnsupportedOperationException("Should never call `set()` on setterless property ('" + this.getName() + "')");
    }
    public Object setAndReturn(final Object obj, final Object obj2) throws IOException {
        this.set(obj, obj2);
        return obj;
    }
    public AnnotatedMethod getSetter() {
        return null;
    }
}
