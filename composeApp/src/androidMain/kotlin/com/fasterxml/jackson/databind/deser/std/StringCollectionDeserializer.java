package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
 
public final class StringCollectionDeserializer extends ContainerDeserializerBase<Collection<String>> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    private final JsonDeserializer<Object> _delegateDeserializer;
    private final JsonDeserializer<String> _valueDeserializer;
    private final ValueInstantiator _valueInstantiator;

    public StringCollectionDeserializer(final JavaType javaType, final JsonDeserializer<?> jsonDeserializer, final ValueInstantiator valueInstantiator) {
        this(javaType, valueInstantiator, null, jsonDeserializer, jsonDeserializer, null);
    }

    
    private StringCollectionDeserializer(final JavaType javaType, final ValueInstantiator valueInstantiator, final JsonDeserializer<?> jsonDeserializer, final JsonDeserializer<?> jsonDeserializer2, final NullValueProvider nullValueProvider, final Boolean bool) {
        super(javaType, nullValueProvider, bool);
        _valueDeserializer = (JsonDeserializer<String>) jsonDeserializer2;
        _valueInstantiator = valueInstantiator;
        _delegateDeserializer = (JsonDeserializer<Object>) jsonDeserializer;
    }

    private StringCollectionDeserializer withResolved(final JsonDeserializer<?> jsonDeserializer, final JsonDeserializer<?> jsonDeserializer2, final NullValueProvider nullValueProvider, final Boolean bool) {
        return (Objects.equals(_unwrapSingle, bool) && _nullProvider == nullValueProvider && _valueDeserializer == jsonDeserializer2 && _delegateDeserializer == jsonDeserializer) ? this : new StringCollectionDeserializer(_containerType, _valueInstantiator, jsonDeserializer, jsonDeserializer2, nullValueProvider, bool);
    } 
    public boolean isCachable() {
        return null == this._valueDeserializer && null == this._delegateDeserializer;
    }
 
    public LogicalType logicalType() {
        return LogicalType.Collection;
    }
 
    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializerFindDeserializer = null;
        JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization;
        final ValueInstantiator valueInstantiator = _valueInstantiator;
        if (null == valueInstantiator) {
            jsonDeserializerFindDeserializer = null;
        } else if (null != valueInstantiator.getArrayDelegateCreator()) {
            jsonDeserializerFindDeserializer = this.findDeserializer(deserializationContext, _valueInstantiator.getArrayDelegateType(deserializationContext.getConfig()), beanProperty);
        } else if (null != this._valueInstantiator.getDelegateCreator()) {
            jsonDeserializerFindDeserializer = this.findDeserializer(deserializationContext, _valueInstantiator.getDelegateType(deserializationContext.getConfig()), beanProperty);
        }
        final JsonDeserializer<String> jsonDeserializer = _valueDeserializer;
        final JavaType contentType = _containerType.getContentType();
        if (null == jsonDeserializer) {
            jsonDeserializerHandleSecondaryContextualization = this.findConvertingContentDeserializer(deserializationContext, beanProperty, jsonDeserializer);
            if (null == jsonDeserializerHandleSecondaryContextualization) {
                jsonDeserializerHandleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(contentType, beanProperty);
            }
        } else {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, contentType);
        }
        return this.withResolved(jsonDeserializerFindDeserializer, this.isDefaultDeserializer(jsonDeserializerHandleSecondaryContextualization) ? null : jsonDeserializerHandleSecondaryContextualization, this.findContentNullProvider(deserializationContext, beanProperty, jsonDeserializerHandleSecondaryContextualization), this.findFormatFeature(deserializationContext, beanProperty, Collection.class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY));
    }
 
    public JsonDeserializer<String> getContentDeserializer() {
        return _valueDeserializer;
    }
 
    public ValueInstantiator getValueInstantiator() {
        return _valueInstantiator;
    }
 
    public Collection<String> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonDeserializer<Object> jsonDeserializer = _delegateDeserializer;
        if (null != jsonDeserializer) {
            return (Collection) _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
        return this.deserialize(jsonParser, deserializationContext, (Collection<String>) _valueInstantiator.createUsingDefault(deserializationContext));
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Collection<String> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Collection<String> collection) throws IOException {
        String str_parseString;
        if (!jsonParser.isExpectedStartArrayToken()) {
            return this.handleNonArray(jsonParser, deserializationContext, collection);
        }
        final JsonDeserializer<String> jsonDeserializer = _valueDeserializer;
        if (null != jsonDeserializer) {
            return this.deserializeUsingCustom(jsonParser, deserializationContext, collection, jsonDeserializer);
        }
        while (true) {
            try {
                final String strNextTextValue = jsonParser.nextTextValue();
                if (null != strNextTextValue) {
                    collection.add(strNextTextValue);
                } else {
                    final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
                    if (JsonToken.END_ARRAY == jsonTokenCurrentToken) {
                        return collection;
                    }
                    if (JsonToken.VALUE_NULL == jsonTokenCurrentToken) {
                        if (!_skipNullValues) {
                            str_parseString = (String) _nullProvider.getNullValue(deserializationContext);
                        }
                    } else {
                        str_parseString = this._parseString(jsonParser, deserializationContext);
                    }
                    collection.add(str_parseString);
                }
            } catch (final Exception e2) {
                throw JsonMappingException.wrapWithPath(e2, collection, collection.size());
            }
        }
    }

    private Collection<String> deserializeUsingCustom(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Collection<String> collection, final JsonDeserializer<String> jsonDeserializer) throws IOException {
        String strDeserialize;
        while (true) {
            try {
                if (null == jsonParser.nextTextValue()) {
                    final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
                    if (JsonToken.END_ARRAY == jsonTokenCurrentToken) {
                        return collection;
                    }
                    if (JsonToken.VALUE_NULL == jsonTokenCurrentToken) {
                        if (!_skipNullValues) {
                            strDeserialize = (String) _nullProvider.getNullValue(deserializationContext);
                        }
                    } else {
                        strDeserialize = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                    }
                } else {
                    strDeserialize = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                }
                collection.add(strDeserialize);
            } catch (final Exception e2) {
                throw JsonMappingException.wrapWithPath(e2, collection, collection.size());
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    private Collection<String> handleNonArray(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Collection<String> collection) throws IOException {
        final String str_parseString;
        final Boolean bool = _unwrapSingle;
        if (bool != Boolean.TRUE && (null != bool || !deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
                return this._deserializeFromString(jsonParser, deserializationContext);
            }
            return (Collection) deserializationContext.handleUnexpectedToken(_containerType, jsonParser);
        }
        final JsonDeserializer<String> jsonDeserializer = _valueDeserializer;
        if (JsonToken.VALUE_NULL == jsonParser.currentToken()) {
            if (_skipNullValues) {
                return collection;
            }
            str_parseString = (String) _nullProvider.getNullValue(deserializationContext);
        } else {
            try {
                str_parseString = null == jsonDeserializer ? this._parseString(jsonParser, deserializationContext) : jsonDeserializer.deserialize(jsonParser, deserializationContext);
            } catch (final Exception e2) {
                throw JsonMappingException.wrapWithPath(e2, collection, collection.size());
            }
        }
        collection.add(str_parseString);
        return collection;
    }
}
