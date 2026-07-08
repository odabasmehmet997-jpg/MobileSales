package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.*;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import io.reactivex.disposables.CompositeDisposable;

import java.io.IOException;
import java.util.*;


public class CollectionDeserializer extends ContainerDeserializerBase<Collection<Object>> implements ContextualDeserializer {
    private static final long serialVersionUID = -1;
    protected final JsonDeserializer<Object> _delegateDeserializer;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;

    public CollectionDeserializer(final JavaType javaType, final JsonDeserializer<Object> jsonDeserializer, final TypeDeserializer typeDeserializer, final ValueInstantiator valueInstantiator) {
        this(javaType, jsonDeserializer, typeDeserializer, valueInstantiator, null, null, null);
    }

    protected CollectionDeserializer(final JavaType javaType, final JsonDeserializer<Object> jsonDeserializer, final TypeDeserializer typeDeserializer, final ValueInstantiator valueInstantiator, final JsonDeserializer<Object> jsonDeserializer2, final NullValueProvider nullValueProvider, final Boolean bool) {
        super(javaType, nullValueProvider, bool);
        _valueDeserializer = jsonDeserializer;
        _valueTypeDeserializer = typeDeserializer;
        _valueInstantiator = valueInstantiator;
        _delegateDeserializer = jsonDeserializer2;
    }

    protected CollectionDeserializer(final CollectionDeserializer collectionDeserializer) {
        super(collectionDeserializer);
        _valueDeserializer = collectionDeserializer._valueDeserializer;
        _valueTypeDeserializer = collectionDeserializer._valueTypeDeserializer;
        _valueInstantiator = collectionDeserializer._valueInstantiator;
        _delegateDeserializer = collectionDeserializer._delegateDeserializer;
    }

    protected CollectionDeserializer withResolved(final JsonDeserializer<?> jsonDeserializer, final JsonDeserializer<?> jsonDeserializer2, final TypeDeserializer typeDeserializer, final NullValueProvider nullValueProvider, final Boolean bool) {
        return new CollectionDeserializer(_containerType, (JsonDeserializer<Object>) jsonDeserializer2, typeDeserializer, _valueInstantiator, (JsonDeserializer<Object>) jsonDeserializer, nullValueProvider, bool);
    }
    public boolean isCachable() {
        return null == this._valueDeserializer && null == this._valueTypeDeserializer && null == this._delegateDeserializer;
    }
    public LogicalType logicalType() {
        return LogicalType.Collection;
    }
    public CollectionDeserializer createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializerFindDeserializer = null;
        final JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization;
        final ValueInstantiator valueInstantiator = _valueInstantiator;
        if (null == valueInstantiator) {
            jsonDeserializerFindDeserializer = null;
        } else if (valueInstantiator.canCreateUsingDelegate()) {
            final JavaType delegateType = _valueInstantiator.getDelegateType(deserializationContext.getConfig());
            if (null == delegateType) {
                final JavaType javaType = _containerType;
                deserializationContext.reportBadDefinition(javaType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", javaType, _valueInstantiator.getClass().getName()));
            }
            jsonDeserializerFindDeserializer = this.findDeserializer(deserializationContext, delegateType, beanProperty);
        } else if (_valueInstantiator.canCreateUsingArrayDelegate()) {
            final JavaType arrayDelegateType = _valueInstantiator.getArrayDelegateType(deserializationContext.getConfig());
            if (null == arrayDelegateType) {
                final JavaType javaType2 = _containerType;
                deserializationContext.reportBadDefinition(javaType2, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", javaType2, _valueInstantiator.getClass().getName()));
            }
            jsonDeserializerFindDeserializer = this.findDeserializer(deserializationContext, arrayDelegateType, beanProperty);
        }
        final JsonDeserializer<Object> jsonDeserializer = jsonDeserializerFindDeserializer;
        final Boolean boolFindFormatFeature = this.findFormatFeature(deserializationContext, beanProperty, Collection.class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        final JsonDeserializer<?> jsonDeserializerFindConvertingContentDeserializer = this.findConvertingContentDeserializer(deserializationContext, beanProperty, _valueDeserializer);
        final JavaType contentType = _containerType.getContentType();
        if (null == jsonDeserializerFindConvertingContentDeserializer) {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(contentType, beanProperty);
        } else {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializerFindConvertingContentDeserializer, beanProperty, contentType);
        }
        final JsonDeserializer<?> jsonDeserializer2 = jsonDeserializerHandleSecondaryContextualization;
        TypeDeserializer typeDeserializerForProperty = _valueTypeDeserializer;
        if (null != typeDeserializerForProperty) {
            typeDeserializerForProperty = typeDeserializerForProperty.forProperty(beanProperty);
        }
        final TypeDeserializer typeDeserializer = typeDeserializerForProperty;
        final NullValueProvider nullValueProviderFindContentNullProvider = this.findContentNullProvider(deserializationContext, beanProperty, jsonDeserializer2);
        return (Objects.equals(boolFindFormatFeature, _unwrapSingle) && nullValueProviderFindContentNullProvider == _nullProvider && jsonDeserializer == _delegateDeserializer && jsonDeserializer2 == _valueDeserializer && typeDeserializer == _valueTypeDeserializer) ? this : this.withResolved(jsonDeserializer, jsonDeserializer2, typeDeserializer, nullValueProviderFindContentNullProvider, boolFindFormatFeature);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public JsonDeserializer<Object> getContentDeserializer() {
        return _valueDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public ValueInstantiator getValueInstantiator() {
        return _valueInstantiator;
    }
    public Collection<Object> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonDeserializer<Object> jsonDeserializer = _delegateDeserializer;
        if (null != jsonDeserializer) {
            return (Collection) _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (jsonParser.isExpectedStartArrayToken()) {
            return this._deserializeFromArray(jsonParser, deserializationContext, this.createDefaultInstance(deserializationContext));
        }
        if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            return this._deserializeFromString(jsonParser, deserializationContext, jsonParser.getText());
        }
        return this.handleNonArray(jsonParser, deserializationContext, this.createDefaultInstance(deserializationContext));
    }

    protected Collection<Object> createDefaultInstance(final DeserializationContext deserializationContext) throws IOException {
        return (Collection) _valueInstantiator.createUsingDefault(deserializationContext);
    }
    public Collection<Object> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Collection<Object> collection) throws IOException {
        if (jsonParser.isExpectedStartArrayToken()) {
            return this._deserializeFromArray(jsonParser, deserializationContext, collection);
        }
        return this.handleNonArray(jsonParser, deserializationContext, collection);
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    @Override
    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }

    protected Collection<Object> _deserializeFromString(final JsonParser jsonParser, final DeserializationContext deserializationContext, final String str) throws IOException {
        final CoercionAction coercionAction_checkCoercionFail;
        final Class<?> clsHandledType = this.handledType();
        if (str.isEmpty() && null != (coercionAction_checkCoercionFail = _checkCoercionFail(deserializationContext, deserializationContext.findCoercionAction(logicalType(), clsHandledType, CoercionInputShape.EmptyString), clsHandledType, str, "empty String (\"\")"))) {
            return (Collection) this._deserializeFromEmptyString(jsonParser, deserializationContext, coercionAction_checkCoercionFail, clsHandledType, "empty String (\"\")");
        }
        return this.handleNonArray(jsonParser, deserializationContext, this.createDefaultInstance(deserializationContext));
    }

    protected Collection<Object> _deserializeFromArray(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Collection<Object> collection) throws IOException {
        Object objDeserializeWithType = null;
        jsonParser.setCurrentValue(collection);
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        if (null != jsonDeserializer.getObjectIdReader()) {
            return this._deserializeWithObjectId(jsonParser, deserializationContext, collection);
        }
        final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
        while (true) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (JsonToken.END_ARRAY == jsonTokenNextToken) {
                return collection;
            }
            try {
                if (JsonToken.VALUE_NULL == jsonTokenNextToken) {
                    if (!_skipNullValues) {
                        objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
                    }
                } else if (null == typeDeserializer) {
                    objDeserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                } else {
                    objDeserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                }
                collection.add(objDeserializeWithType);
            } catch (final Exception e2) {
                if (null != deserializationContext && !deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
                    ClassUtil.throwIfRTE(e2);
                }
                throw JsonMappingException.wrapWithPath(e2, collection, collection.size());
            }
        }
    }

    protected final Collection<Object> handleNonArray(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Collection<Object> collection) throws IOException {
        final Object objDeserializeWithType;
        final Boolean bool = _unwrapSingle;
        if (bool != Boolean.TRUE && (null != bool || !deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            return (Collection) deserializationContext.handleUnexpectedToken(_containerType, jsonParser);
        }
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
        try {
            if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
                if (_skipNullValues) {
                    return collection;
                }
                objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
            } else if (null == typeDeserializer) {
                objDeserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext);
            } else {
                objDeserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
            collection.add(objDeserializeWithType);
            return collection;
        } catch (final Exception e2) {
            if (!deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
                ClassUtil.throwIfRTE(e2);
            }
            throw JsonMappingException.wrapWithPath(e2, Object.class, collection.size());
        }
    }

    protected Collection<Object> _deserializeWithObjectId(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Collection<Object> collection) throws IOException {
        Object objDeserializeWithType = null;
        if (!jsonParser.isExpectedStartArrayToken()) {
            return this.handleNonArray(jsonParser, deserializationContext, collection);
        }
        jsonParser.setCurrentValue(collection);
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
        final CollectionReferringAccumulator collectionReferringAccumulator = new CollectionReferringAccumulator(_containerType.getContentType().getRawClass(), collection);
        while (true) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (JsonToken.END_ARRAY == jsonTokenNextToken) {
                return collection;
            }
            try {
            } catch (final Exception e3) {
                if (null != deserializationContext && !deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
                    ClassUtil.throwIfRTE(e3);
                }
                throw JsonMappingException.wrapWithPath(e3, collection, collection.size());
            }
            if (JsonToken.VALUE_NULL == jsonTokenNextToken) {
                if (!_skipNullValues) {
                    objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
                }
            } else if (null == typeDeserializer) {
                objDeserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext);
            } else {
                objDeserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
            collectionReferringAccumulator.add(objDeserializeWithType);
        }
    }

    public static class CollectionReferringAccumulator {
        private final List<CollectionReferring> _accumulator = new ArrayList();
        private final Class<?> _elementType;
        private final Collection<Object> _result;
        private CompositeDisposable r0;

        public CollectionReferringAccumulator(final Class<?> cls, final Collection<Object> collection) {
            _elementType = cls;
            _result = collection;
        }

        public void add(final Object obj) {
            if (_accumulator.isEmpty()) {
                _result.add(obj);
            } else {
                _accumulator.get(r0.size() - 1).next.add(obj);
            }
        }

        public ReadableObjectId.Referring handleUnresolvedReference(final UnresolvedForwardReference unresolvedForwardReference) {
            final CollectionReferring collectionReferring = new CollectionReferring(this, unresolvedForwardReference, _elementType);
            _accumulator.add(collectionReferring);
            return collectionReferring;
        }

        public void resolveForwardReference(final Object obj, final Object obj2) throws IOException {
            final Iterator<CollectionReferring> it = _accumulator.iterator();
            Collection collection = _result;
            while (it.hasNext()) {
                final CollectionReferring next = it.next();
                if (next.hasId(obj)) {
                    it.remove();
                    collection.add(obj2);
                    collection.addAll(next.next);
                    return;
                }
                collection = next.next;
            }
            throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj + "] that wasn't previously seen as unresolved.");
        }
    }
    private static final class CollectionReferring extends ReadableObjectId.Referring {
        private final CollectionReferringAccumulator _parent;
        public final List<Object> next;

        CollectionReferring(final CollectionReferringAccumulator collectionReferringAccumulator, final UnresolvedForwardReference unresolvedForwardReference, final Class<?> cls) {
            super(unresolvedForwardReference, cls);
            next = new ArrayList();
            _parent = collectionReferringAccumulator;
        }
        public void handleResolvedForwardReference(final Object obj, final Object obj2) throws IOException {
            _parent.resolveForwardReference(obj, obj2);
        }
    }
}
