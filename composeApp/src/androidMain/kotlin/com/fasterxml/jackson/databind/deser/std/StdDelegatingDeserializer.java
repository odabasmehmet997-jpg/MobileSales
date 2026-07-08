package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;

import java.io.IOException;
import java.util.Set;

public class StdDelegatingDeserializer<T> extends StdDeserializer<T> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected final Converter<Object, T> _converter;
    protected final JsonDeserializer<Object> _delegateDeserializer;
    protected final JavaType _delegateType;

    public StdDelegatingDeserializer(final Converter<?, T> converter) {
        super(Object.class);
        _converter = (Converter<Object, T>) converter;
        _delegateType = null;
        _delegateDeserializer = null;
    }

    public StdDelegatingDeserializer(final Converter<Object, T> converter, final JavaType javaType, final JsonDeserializer<?> jsonDeserializer) {
        super(javaType);
        _converter = converter;
        _delegateType = javaType;
        _delegateDeserializer = (JsonDeserializer<Object>) jsonDeserializer;
    }

    protected StdDelegatingDeserializer(final StdDelegatingDeserializer<T> stdDelegatingDeserializer) {
        super(stdDelegatingDeserializer);
        _converter = stdDelegatingDeserializer._converter;
        _delegateType = stdDelegatingDeserializer._delegateType;
        _delegateDeserializer = stdDelegatingDeserializer._delegateDeserializer;
    }

    protected StdDelegatingDeserializer<T> withDelegate(final Converter<Object, T> converter, final JavaType javaType, final JsonDeserializer<?> jsonDeserializer) {
        ClassUtil.verifyMustOverride(StdDelegatingDeserializer.class, this, "withDelegate");
        return new StdDelegatingDeserializer<>(converter, javaType, jsonDeserializer);
    }
    public void resolve(final DeserializationContext deserializationContext) throws JsonMappingException {
        final NullValueProvider nullValueProvider = _delegateDeserializer;
        if (null == nullValueProvider || !(nullValueProvider instanceof ResolvableDeserializer)) {
            return;
        }
        ((ResolvableDeserializer) nullValueProvider).resolve(deserializationContext);
    }

    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        final JsonDeserializer<?> jsonDeserializer = _delegateDeserializer;
        if (null != jsonDeserializer) {
            final JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, _delegateType);
            return jsonDeserializerHandleSecondaryContextualization != _delegateDeserializer ? this.withDelegate(_converter, _delegateType, jsonDeserializerHandleSecondaryContextualization) : this;
        }
        final JavaType inputType = _converter.getInputType(deserializationContext.getTypeFactory());
        return this.withDelegate(_converter, inputType, deserializationContext.findContextualValueDeserializer(inputType, beanProperty));
    }
    public JsonDeserializer<?> getDelegatee() {
        return _delegateDeserializer;
    }
    public Class<?> handledType() {
        return _delegateDeserializer.handledType();
    }
    public LogicalType logicalType() {
        return _delegateDeserializer.logicalType();
    }
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return _delegateDeserializer.supportsUpdate(deserializationConfig);
    }
    public T deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final Object objDeserialize = _delegateDeserializer.deserialize(jsonParser, deserializationContext);
        if (null == objDeserialize) {
            return null;
        }
        return this.convertValue(objDeserialize);
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        final Object objDeserialize = _delegateDeserializer.deserialize(jsonParser, deserializationContext);
        if (null == objDeserialize) {
            return null;
        }
        return this.convertValue(objDeserialize);
    }

    @Override
    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }

    public T deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        if (_delegateType.getRawClass().isAssignableFrom(obj.getClass())) {
            return (T) _delegateDeserializer.deserialize(jsonParser, deserializationContext, obj);
        }
        return (T) this._handleIncompatibleUpdateValue(jsonParser, deserializationContext, obj);
    }

    protected Object _handleIncompatibleUpdateValue(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        throw new UnsupportedOperationException(String.format("Cannot update object of type %s (using deserializer for type %s)" + obj.getClass().getName(), _delegateType));
    }

    protected T convertValue(final Object obj) {
        return _converter.convert(obj);
    }
}
