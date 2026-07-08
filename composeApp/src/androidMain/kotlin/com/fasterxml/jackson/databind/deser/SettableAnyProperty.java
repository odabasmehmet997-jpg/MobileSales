package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class SettableAnyProperty implements Serializable {
    private static final long serialVersionUID = 1;
    protected final KeyDeserializer _keyDeserializer;
    protected final BeanProperty _property;
    protected final AnnotatedMember _setter;
    final boolean _setterIsField;
    protected final JavaType _type;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;
    public SettableAnyProperty(final BeanProperty beanProperty, final AnnotatedMember annotatedMember, final JavaType javaType, final KeyDeserializer keyDeserializer, final JsonDeserializer<Object> jsonDeserializer, final TypeDeserializer typeDeserializer) {
        _property = beanProperty;
        _setter = annotatedMember;
        _type = javaType;
        _valueDeserializer = jsonDeserializer;
        _valueTypeDeserializer = typeDeserializer;
        _keyDeserializer = keyDeserializer;
        _setterIsField = annotatedMember instanceof AnnotatedField;
    }
    public SettableAnyProperty(final BeanProperty beanProperty, final AnnotatedMember annotatedMember, final JavaType javaType, final JsonDeserializer<Object> jsonDeserializer, final TypeDeserializer typeDeserializer) {
        this(beanProperty, annotatedMember, javaType, null, jsonDeserializer, typeDeserializer);
    }
    public SettableAnyProperty withValueDeserializer(final JsonDeserializer<Object> jsonDeserializer) {
        return new SettableAnyProperty(_property, _setter, _type, _keyDeserializer, jsonDeserializer, _valueTypeDeserializer);
    }
    public void fixAccess(final DeserializationConfig deserializationConfig) {
        _setter.fixAccess(deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }
    Object readResolve() {
        final AnnotatedMember annotatedMember = _setter;
        if (null == annotatedMember || null == annotatedMember.getAnnotated()) {
            throw new IllegalArgumentException("Missing method (broken JDK (de)serialization?)");
        }
        return this;
    }
    public BeanProperty getProperty() {
        return _property;
    }
    public boolean hasValueDeserializer() {
        return null != this._valueDeserializer;
    }
    public JavaType getType() {
        return _type;
    }
    public final void deserializeAndSet(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj, final String str) throws IOException {
        try {
            final KeyDeserializer keyDeserializer = _keyDeserializer;
            this.set(obj, null == keyDeserializer ? str : keyDeserializer.deserializeKey(str, deserializationContext), this.deserialize(jsonParser, deserializationContext));
        } catch (final UnresolvedForwardReference e2) {
            if (null == this._valueDeserializer.getObjectIdReader()) {
                throw JsonMappingException.from(jsonParser, "Unresolved forward reference but no identity info.", e2);
            }
            e2.getRoid().appendReferring(new AnySetterReferring(this, e2, _type.getRawClass(), obj, str));
        }
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            return _valueDeserializer.getNullValue(deserializationContext);
        }
        final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
        if (null != typeDeserializer) {
            return _valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        }
        return _valueDeserializer.deserialize(jsonParser, deserializationContext);
    }
    public void set(final Object obj, final Object obj2, final Object obj3) throws IOException {
        try {
            if (_setterIsField) {
                final Map map = (Map) _setter.getValue(obj);
                if (null != map) {
                    map.put(obj2, obj3);
                }
            } else {
                ((AnnotatedMethod) _setter).callOnWith(obj, obj2, obj3);
            }
        } catch (final Exception e2) {
            this._throwAsIOE(e2, obj2, obj3);
        }
    }
    protected void _throwAsIOE(final Exception exc, final Object obj, final Object obj2) throws IOException {
        if (exc instanceof IllegalArgumentException) {
            final String strClassNameOf = ClassUtil.classNameOf(obj2);
            final StringBuilder sb = new StringBuilder("Problem deserializing \"any\" property '");
            sb.append(obj);
            sb.append("' of class " + this.getClassName() + " (expected type: ");
            sb.append(_type);
            sb.append("; actual type: ");
            sb.append(strClassNameOf);
            sb.append(")");
            final String strExceptionMessage = ClassUtil.exceptionMessage(exc);
            if (null != strExceptionMessage) {
                sb.append(", problem: ");
                sb.append(strExceptionMessage);
            } else {
                sb.append(" (no error message provided)");
            }
            throw new JsonMappingException(null, sb.toString(), exc);
        }
        ClassUtil.throwIfIOE(exc);
        ClassUtil.throwIfRTE(exc);
        final Throwable rootCause = ClassUtil.getRootCause(exc);
        throw new JsonMappingException(null, ClassUtil.exceptionMessage(rootCause), rootCause);
    }
    private String getClassName() {
        return _setter.getDeclaringClass().getName();
    }
    public String toString() {
        return "[any property on class " + this.getClassName() + "]";
    }
    private static class AnySetterReferring extends ReadableObjectId.Referring {
        private final SettableAnyProperty _parent;
        private final Object _pojo;
        private final String _propName;

        public AnySetterReferring(final SettableAnyProperty settableAnyProperty, final UnresolvedForwardReference unresolvedForwardReference, final Class<?> cls, final Object obj, final String str) {
            super(unresolvedForwardReference, cls);
            _parent = settableAnyProperty;
            _pojo = obj;
            _propName = str;
        }
        public void handleResolvedForwardReference(final Object obj, final Object obj2) throws IOException {
            if (!this.hasId(obj)) {
                throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj + "] that wasn't previously registered.");
            }
            _parent.set(_pojo, _propName, obj2);
        }
    }
}
