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

public final class MethodProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    private final AnnotatedMethod _annotated;
    private final transient Method _setter;
    private final boolean _skipNulls;
    public MethodProperty(final BeanPropertyDefinition beanPropertyDefinition, final JavaType javaType, final TypeDeserializer typeDeserializer, final Annotations annotations, final AnnotatedMethod annotatedMethod) {
        super(beanPropertyDefinition, javaType, typeDeserializer, annotations);
        _annotated = annotatedMethod;
        _setter = annotatedMethod.getAnnotated();
        _skipNulls = NullsConstantProvider.isSkipper(_nullProvider);
    }
    private MethodProperty(final MethodProperty methodProperty, final JsonDeserializer<?> jsonDeserializer, final NullValueProvider nullValueProvider) {
        super(methodProperty, jsonDeserializer, nullValueProvider);
        _annotated = methodProperty._annotated;
        _setter = methodProperty._setter;
        _skipNulls = NullsConstantProvider.isSkipper(nullValueProvider);
    }
    private MethodProperty(final MethodProperty methodProperty, final PropertyName propertyName) {
        super(methodProperty, propertyName);
        _annotated = methodProperty._annotated;
        _setter = methodProperty._setter;
        _skipNulls = methodProperty._skipNulls;
    }
    private MethodProperty(final MethodProperty methodProperty, final Method method) {
        super(methodProperty);
        _annotated = methodProperty._annotated;
        _setter = method;
        _skipNulls = methodProperty._skipNulls;
    }
    public SettableBeanProperty withName(final PropertyName propertyName) {
        return new MethodProperty(this, propertyName);
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
        return new MethodProperty(this, jsonDeserializer, nullValueProvider);
    }
    public SettableBeanProperty withNullProvider(final NullValueProvider nullValueProvider) {
        return new MethodProperty(this, _valueDeserializer, nullValueProvider);
    }
    public void fixAccess(final DeserializationConfig deserializationConfig) {
        _annotated.fixAccess(deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }
    public <A extends Annotation> A getAnnotation(final Class cls) {
        final AnnotatedMethod annotatedMethod = _annotated;
        if (null == annotatedMethod) {
            return null;
        }
        return (A) annotatedMethod.getAnnotation(cls);
    }
    public AnnotatedMember getMember() {
        return _annotated;
    }
    public void deserializeAndSet(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        final Object objDeserializeWithType;
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            if (_skipNulls) {
                return;
            } else {
                objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
            }
        } else {
            final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
            if (null == typeDeserializer) {
                final Object objDeserialize = _valueDeserializer.deserialize(jsonParser, deserializationContext);
                if (null != objDeserialize) {
                    objDeserializeWithType = objDeserialize;
                } else if (_skipNulls) {
                    return;
                } else {
                    objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
                }
            } else {
                objDeserializeWithType = _valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
        }
        try {
            _setter.invoke(obj, objDeserializeWithType);
        } catch (final Exception e2) {
            this._throwAsIOE(jsonParser, e2, objDeserializeWithType);
        }
    }
    public Object deserializeSetAndReturn(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        final Object objDeserializeWithType;
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            if (_skipNulls) {
                return obj;
            }
            objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
        } else {
            final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
            if (null == typeDeserializer) {
                final Object objDeserialize = _valueDeserializer.deserialize(jsonParser, deserializationContext);
                if (null != objDeserialize) {
                    objDeserializeWithType = objDeserialize;
                } else {
                    if (_skipNulls) {
                        return obj;
                    }
                    objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
                }
            } else {
                objDeserializeWithType = _valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
        }
        try {
            final Object objInvoke = _setter.invoke(obj, objDeserializeWithType);
            return null == objInvoke ? obj : objInvoke;
        } catch (final Exception e2) {
            this._throwAsIOE(jsonParser, e2, objDeserializeWithType);
            return null;
        }
    }
    public void set(final Object obj, final Object obj2) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        try {
            _setter.invoke(obj, obj2);
        } catch (final Exception e2) {
            this._throwAsIOE(e2, obj2);
        }
    }
    public Object setAndReturn(final Object obj, final Object obj2) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        try {
            final Object objInvoke = _setter.invoke(obj, obj2);
            return null == objInvoke ? obj : objInvoke;
        } catch (final Exception e2) {
            this._throwAsIOE(e2, obj2);
            return null;
        }
    }
    Object readResolve() {
        return new MethodProperty(this, _annotated.getAnnotated());
    }
}
