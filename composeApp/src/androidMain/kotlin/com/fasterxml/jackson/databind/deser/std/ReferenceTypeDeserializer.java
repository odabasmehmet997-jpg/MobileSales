package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;

import java.io.IOException;

public abstract class ReferenceTypeDeserializer<T> extends StdDeserializer<T> implements ContextualDeserializer {
    private static final long serialVersionUID = 2;
    protected final JavaType _fullType;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;
    public abstract T getNullValue(DeserializationContext deserializationContext) throws JsonMappingException;
    public abstract Object getReferenced(T t);
    public abstract T referenceValue(Object obj);
    public abstract T updateReference(T t, Object obj);
    protected abstract ReferenceTypeDeserializer<T> withResolved(TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);
    protected ReferenceTypeDeserializer(final JavaType javaType, final ValueInstantiator valueInstantiator, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) {
        super(javaType);
        _valueInstantiator = valueInstantiator;
        _fullType = javaType;
        _valueDeserializer = (JsonDeserializer<Object>) jsonDeserializer;
        _valueTypeDeserializer = typeDeserializer;
    }
    protected ReferenceTypeDeserializer(final JavaType javaType, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) {
        this(javaType, null, typeDeserializer, jsonDeserializer);
    }
    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        final JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization;
        final JsonDeserializer<?> jsonDeserializer = _valueDeserializer;
        if (null == jsonDeserializer) {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(_fullType.getReferencedType(), beanProperty);
        } else {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, _fullType.getReferencedType());
        }
        TypeDeserializer typeDeserializerForProperty = _valueTypeDeserializer;
        if (null != typeDeserializerForProperty) {
            typeDeserializerForProperty = typeDeserializerForProperty.forProperty(beanProperty);
        }
        return (jsonDeserializerHandleSecondaryContextualization == _valueDeserializer && typeDeserializerForProperty == _valueTypeDeserializer) ? this : this.withResolved(typeDeserializerForProperty, jsonDeserializerHandleSecondaryContextualization);
    }
    public AccessPattern getNullAccessPattern() {
        return AccessPattern.DYNAMIC;
    }
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.DYNAMIC;
    }
    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return this.getNullValue(deserializationContext);
    }
    public ValueInstantiator getValueInstantiator() {
        return _valueInstantiator;
    }
    public JavaType getValueType() {
        return _fullType;
    }
    public LogicalType logicalType() {
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        if (null != jsonDeserializer) {
            return jsonDeserializer.logicalType();
        }
        return super.logicalType();
    }
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        if (null == jsonDeserializer) {
            return null;
        }
        return jsonDeserializer.supportsUpdate(deserializationConfig);
    }
    public T deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final Object objDeserializeWithType;
        final ValueInstantiator valueInstantiator = _valueInstantiator;
        if (null != valueInstantiator) {
            return this.deserialize(jsonParser, deserializationContext, (T) valueInstantiator.createUsingDefault(deserializationContext));
        }
        final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
        if (null == typeDeserializer) {
            objDeserializeWithType = _valueDeserializer.deserialize(jsonParser, deserializationContext);
        } else {
            objDeserializeWithType = _valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        }
        return this.referenceValue(objDeserializeWithType);
    }
    public T deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final T t) throws IOException {
        final Object objDeserializeWithType;
        final Object objDeserializeWithType2;
        if (_valueDeserializer.supportsUpdate(deserializationContext.getConfig()).equals(Boolean.FALSE) || null != this._valueTypeDeserializer) {
            final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
            if (null == typeDeserializer) {
                objDeserializeWithType = _valueDeserializer.deserialize(jsonParser, deserializationContext);
            } else {
                objDeserializeWithType = _valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
        } else {
            final Object referenced = this.getReferenced(t);
            if (null == referenced) {
                final TypeDeserializer typeDeserializer2 = _valueTypeDeserializer;
                if (null == typeDeserializer2) {
                    objDeserializeWithType2 = _valueDeserializer.deserialize(jsonParser, deserializationContext);
                } else {
                    objDeserializeWithType2 = _valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer2);
                }
                return this.referenceValue(objDeserializeWithType2);
            }
            objDeserializeWithType = _valueDeserializer.deserialize(jsonParser, deserializationContext, referenced);
        }
        return this.updateReference(t, objDeserializeWithType);
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            return this.getNullValue(deserializationContext);
        }
        final TypeDeserializer typeDeserializer2 = _valueTypeDeserializer;
        if (null == typeDeserializer2) {
            return this.deserialize(jsonParser, deserializationContext);
        }
        return this.referenceValue(typeDeserializer2.deserializeTypedFromAny(jsonParser, deserializationContext));
    }
}
