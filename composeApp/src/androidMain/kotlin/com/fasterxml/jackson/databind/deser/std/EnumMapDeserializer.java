package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.*;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;

import java.io.IOException;
import java.util.EnumMap;

public class EnumMapDeserializer extends ContainerDeserializerBase<EnumMap<?, ?>> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected final Class<?> _enumClass;
    protected KeyDeserializer _keyDeserializer;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;

    public EnumMapDeserializer(final JavaType javaType, final ValueInstantiator valueInstantiator, final KeyDeserializer keyDeserializer, final JsonDeserializer<?> jsonDeserializer, final TypeDeserializer typeDeserializer, final NullValueProvider nullValueProvider) {
        super(javaType, nullValueProvider, null);
        _enumClass = javaType.getKeyType().getRawClass();
        _keyDeserializer = keyDeserializer;
        _valueDeserializer = (JsonDeserializer<Object>) jsonDeserializer;
        _valueTypeDeserializer = typeDeserializer;
        _valueInstantiator = valueInstantiator;
    }

    protected EnumMapDeserializer(final EnumMapDeserializer enumMapDeserializer, final KeyDeserializer keyDeserializer, final JsonDeserializer<?> jsonDeserializer, final TypeDeserializer typeDeserializer, final NullValueProvider nullValueProvider) {
        super(enumMapDeserializer, nullValueProvider, enumMapDeserializer._unwrapSingle);
        _enumClass = enumMapDeserializer._enumClass;
        _keyDeserializer = keyDeserializer;
        _valueDeserializer = (JsonDeserializer<Object>) jsonDeserializer;
        _valueTypeDeserializer = typeDeserializer;
        _valueInstantiator = enumMapDeserializer._valueInstantiator;
        _delegateDeserializer = enumMapDeserializer._delegateDeserializer;
        _propertyBasedCreator = enumMapDeserializer._propertyBasedCreator;
    }

    @Deprecated
    public EnumMapDeserializer(final JavaType javaType, final KeyDeserializer keyDeserializer, final JsonDeserializer<?> jsonDeserializer, final TypeDeserializer typeDeserializer) {
        this(javaType, null, keyDeserializer, jsonDeserializer, typeDeserializer, null);
    }

    public EnumMapDeserializer withResolved(final KeyDeserializer keyDeserializer, final JsonDeserializer<?> jsonDeserializer, final TypeDeserializer typeDeserializer, final NullValueProvider nullValueProvider) {
        return (keyDeserializer == _keyDeserializer && nullValueProvider == _nullProvider && jsonDeserializer == _valueDeserializer && typeDeserializer == _valueTypeDeserializer) ? this : new EnumMapDeserializer(this, keyDeserializer, jsonDeserializer, typeDeserializer, nullValueProvider);
    }

    @Override // com.fasterxml.jackson.databind.deser.ResolvableDeserializer
    public void resolve(final DeserializationContext deserializationContext) throws JsonMappingException {
        final ValueInstantiator valueInstantiator = _valueInstantiator;
        if (null != valueInstantiator) {
            if (valueInstantiator.canCreateUsingDelegate()) {
                final JavaType delegateType = _valueInstantiator.getDelegateType(deserializationContext.getConfig());
                if (null == delegateType) {
                    final JavaType javaType = _containerType;
                    deserializationContext.reportBadDefinition(javaType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", javaType, _valueInstantiator.getClass().getName()));
                }
                _delegateDeserializer = this.findDeserializer(deserializationContext, delegateType, null);
                return;
            }
            if (_valueInstantiator.canCreateUsingArrayDelegate()) {
                final JavaType arrayDelegateType = _valueInstantiator.getArrayDelegateType(deserializationContext.getConfig());
                if (null == arrayDelegateType) {
                    final JavaType javaType2 = _containerType;
                    deserializationContext.reportBadDefinition(javaType2, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", javaType2, _valueInstantiator.getClass().getName()));
                }
                _delegateDeserializer = this.findDeserializer(deserializationContext, arrayDelegateType, null);
                return;
            }
            if (_valueInstantiator.canCreateFromObjectWith()) {
                _propertyBasedCreator = PropertyBasedCreator.construct(deserializationContext, _valueInstantiator, _valueInstantiator.getFromObjectArguments(deserializationContext.getConfig()), deserializationContext.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        final JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization;
        KeyDeserializer keyDeserializerFindKeyDeserializer = _keyDeserializer;
        if (null == keyDeserializerFindKeyDeserializer) {
            keyDeserializerFindKeyDeserializer = deserializationContext.findKeyDeserializer(_containerType.getKeyType(), beanProperty);
        }
        final JsonDeserializer<?> jsonDeserializer = _valueDeserializer;
        final JavaType contentType = _containerType.getContentType();
        if (null == jsonDeserializer) {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(contentType, beanProperty);
        } else {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, contentType);
        }
        TypeDeserializer typeDeserializerForProperty = _valueTypeDeserializer;
        if (null != typeDeserializerForProperty) {
            typeDeserializerForProperty = typeDeserializerForProperty.forProperty(beanProperty);
        }
        return this.withResolved(keyDeserializerFindKeyDeserializer, jsonDeserializerHandleSecondaryContextualization, typeDeserializerForProperty, this.findContentNullProvider(deserializationContext, beanProperty, jsonDeserializerHandleSecondaryContextualization));
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return null == this._valueDeserializer && null == this._keyDeserializer && null == this._valueTypeDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Map;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public JsonDeserializer<Object> getContentDeserializer() {
        return _valueDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public ValueInstantiator getValueInstantiator() {
        return _valueInstantiator;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return this.constructMap(deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public EnumMap<?, ?> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (null != this._propertyBasedCreator) {
            return this._deserializeUsingProperties(jsonParser, deserializationContext);
        }
        final JsonDeserializer<Object> jsonDeserializer = _delegateDeserializer;
        if (null != jsonDeserializer) {
            return (EnumMap) _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 != iCurrentTokenId && 2 != iCurrentTokenId) {
            if (3 == iCurrentTokenId) {
                return this._deserializeFromArray(jsonParser, deserializationContext);
            }
            if (5 != iCurrentTokenId) {
                if (6 == iCurrentTokenId) {
                    return this._deserializeFromString(jsonParser, deserializationContext);
                }
                return (EnumMap) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
            }
        }
        return this.deserialize(jsonParser, deserializationContext, this.constructMap(deserializationContext));
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public EnumMap<?, ?> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final EnumMap enumMap) throws IOException {
        String strCurrentName;
        Object objDeserializeWithType = null;
        jsonParser.setCurrentValue(enumMap);
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
        if (jsonParser.isExpectedStartObjectToken()) {
            strCurrentName = jsonParser.nextFieldName();
        } else {
            final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            final JsonToken jsonToken = JsonToken.FIELD_NAME;
            if (jsonTokenCurrentToken != jsonToken) {
                if (JsonToken.END_OBJECT == jsonTokenCurrentToken) {
                    return enumMap;
                }
                deserializationContext.reportWrongTokenException(this, jsonToken, (String) null);
            }
            strCurrentName = jsonParser.currentName();
        }
        while (null != strCurrentName) {
            final Enum r3 = (Enum) _keyDeserializer.deserializeKey(strCurrentName, deserializationContext);
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (null == r3) {
                if (!deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    return (EnumMap) deserializationContext.handleWeirdStringValue(_enumClass, strCurrentName, "value not one of declared Enum instance names for %s", _containerType.getKeyType());
                }
                jsonParser.skipChildren();
            } else {
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
                    enumMap.put((EnumMap) r3, (Enum) objDeserializeWithType);
                } catch (final Exception e2) {
                    return this.wrapAndThrow(deserializationContext, e2, enumMap, strCurrentName);
                }
            }
            strCurrentName = jsonParser.nextFieldName();
        }
        return enumMap;
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    protected EnumMap<?, ?> constructMap(final DeserializationContext deserializationContext) throws JsonMappingException {
        final ValueInstantiator valueInstantiator = _valueInstantiator;
        if (null == valueInstantiator) {
            return new EnumMap<>(_enumClass);
        }
        try {
            if (!valueInstantiator.canCreateUsingDefault()) {
                return (EnumMap) deserializationContext.handleMissingInstantiator(this.handledType(), this._valueInstantiator, null, "no default constructor found", new Object[0]);
            }
            return (EnumMap) _valueInstantiator.createUsingDefault(deserializationContext);
        } catch (final IOException e2) {
            return ClassUtil.throwAsMappingException(deserializationContext, e2);
        }
    }

    public EnumMap<?, ?> _deserializeUsingProperties(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        String strCurrentName;
        Object objDeserializeWithType = null;
        final PropertyBasedCreator propertyBasedCreator = _propertyBasedCreator;
        final PropertyValueBuffer propertyValueBufferStartBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, null);
        if (jsonParser.isExpectedStartObjectToken()) {
            strCurrentName = jsonParser.nextFieldName();
        } else {
            strCurrentName = jsonParser.hasToken(JsonToken.FIELD_NAME) ? jsonParser.currentName() : null;
        }
        while (null != strCurrentName) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFindCreatorProperty = propertyBasedCreator.findCreatorProperty(strCurrentName);
            if (null != settableBeanPropertyFindCreatorProperty) {
                if (propertyValueBufferStartBuilding.assignParameter(settableBeanPropertyFindCreatorProperty, settableBeanPropertyFindCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                    jsonParser.nextToken();
                    try {
                        return this.deserialize(jsonParser, deserializationContext, (EnumMap) propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding));
                    } catch (final Exception e2) {
                        return this.wrapAndThrow(deserializationContext, e2, _containerType.getRawClass(), strCurrentName);
                    }
                }
            } else {
                final Enum r5 = (Enum) _keyDeserializer.deserializeKey(strCurrentName, deserializationContext);
                if (null == r5) {
                    if (!deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                        return (EnumMap) deserializationContext.handleWeirdStringValue(_enumClass, strCurrentName, "value not one of declared Enum instance names for %s", _containerType.getKeyType());
                    }
                    jsonParser.nextToken();
                    jsonParser.skipChildren();
                } else {
                    try {
                        if (JsonToken.VALUE_NULL == jsonTokenNextToken) {
                            if (!_skipNullValues) {
                                objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
                            }
                        } else {
                            final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
                            if (null == typeDeserializer) {
                                objDeserializeWithType = _valueDeserializer.deserialize(jsonParser, deserializationContext);
                            } else {
                                objDeserializeWithType = _valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                            }
                        }
                        propertyValueBufferStartBuilding.bufferMapProperty(r5, objDeserializeWithType);
                    } catch (final Exception e3) {
                        final Object o = this.wrapAndThrow(deserializationContext, e3, _containerType.getRawClass(), strCurrentName);
                        return null;
                    }
                }
            }
            strCurrentName = jsonParser.nextFieldName();
        }
        try {
            return (EnumMap) propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
        } catch (final Exception e4) {
            this.wrapAndThrow(deserializationContext, e4, _containerType.getRawClass(), strCurrentName);
            return null;
        }
    }
}
