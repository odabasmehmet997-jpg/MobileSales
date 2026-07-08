package com.fasterxml.jackson.databind.deser.std;

import ads_mobile_sdk.r0;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.*;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;

import java.io.IOException;
import java.util.*;

public class MapDeserializer extends ContainerDeserializerBase<Map<Object, Object>> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected final boolean _hasDefaultCreator;
    protected Set<String> _ignorableProperties;
    protected Set<String> _includableProperties;
    protected IgnorePropertiesUtil.Checker _inclusionChecker;
    protected final KeyDeserializer _keyDeserializer;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected boolean _standardStringKey;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;
    public MapDeserializer(final JavaType javaType, final ValueInstantiator valueInstantiator, final KeyDeserializer keyDeserializer, final JsonDeserializer<Object> jsonDeserializer, final TypeDeserializer typeDeserializer) {
        super(javaType, null, null);
        _keyDeserializer = keyDeserializer;
        _valueDeserializer = jsonDeserializer;
        _valueTypeDeserializer = typeDeserializer;
        _valueInstantiator = valueInstantiator;
        _hasDefaultCreator = valueInstantiator.canCreateUsingDefault();
        _delegateDeserializer = null;
        _propertyBasedCreator = null;
        _standardStringKey = this._isStdKeyDeser(javaType, keyDeserializer);
        _inclusionChecker = null;
    }
    protected MapDeserializer(final MapDeserializer mapDeserializer) {
        super(mapDeserializer);
        _keyDeserializer = mapDeserializer._keyDeserializer;
        _valueDeserializer = mapDeserializer._valueDeserializer;
        _valueTypeDeserializer = mapDeserializer._valueTypeDeserializer;
        _valueInstantiator = mapDeserializer._valueInstantiator;
        _propertyBasedCreator = mapDeserializer._propertyBasedCreator;
        _delegateDeserializer = mapDeserializer._delegateDeserializer;
        _hasDefaultCreator = mapDeserializer._hasDefaultCreator;
        _ignorableProperties = mapDeserializer._ignorableProperties;
        _includableProperties = mapDeserializer._includableProperties;
        _inclusionChecker = mapDeserializer._inclusionChecker;
        _standardStringKey = mapDeserializer._standardStringKey;
    }
    protected MapDeserializer(final MapDeserializer mapDeserializer, final KeyDeserializer keyDeserializer, final JsonDeserializer<Object> jsonDeserializer, final TypeDeserializer typeDeserializer, final NullValueProvider nullValueProvider, final Set<String> set) {
        this(mapDeserializer, keyDeserializer, jsonDeserializer, typeDeserializer, nullValueProvider, set, null);
    }
    protected MapDeserializer(final MapDeserializer mapDeserializer, final KeyDeserializer keyDeserializer, final JsonDeserializer<Object> jsonDeserializer, final TypeDeserializer typeDeserializer, final NullValueProvider nullValueProvider, final Set<String> set, final Set<String> set2) {
        super(mapDeserializer, nullValueProvider, mapDeserializer._unwrapSingle);
        _keyDeserializer = keyDeserializer;
        _valueDeserializer = jsonDeserializer;
        _valueTypeDeserializer = typeDeserializer;
        _valueInstantiator = mapDeserializer._valueInstantiator;
        _propertyBasedCreator = mapDeserializer._propertyBasedCreator;
        _delegateDeserializer = mapDeserializer._delegateDeserializer;
        _hasDefaultCreator = mapDeserializer._hasDefaultCreator;
        _ignorableProperties = set;
        _includableProperties = set2;
        _inclusionChecker = IgnorePropertiesUtil.buildCheckerIfNeeded(set, set2);
        _standardStringKey = this._isStdKeyDeser(_containerType, keyDeserializer);
    }
    protected MapDeserializer withResolved(final KeyDeserializer keyDeserializer, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer, final NullValueProvider nullValueProvider, final Set<String> set) {
        return this.withResolved(keyDeserializer, typeDeserializer, jsonDeserializer, nullValueProvider, set, _includableProperties);
    }
    protected MapDeserializer withResolved(final KeyDeserializer keyDeserializer, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer, final NullValueProvider nullValueProvider, final Set<String> set, final Set<String> set2) {
        return (_keyDeserializer == keyDeserializer && _valueDeserializer == jsonDeserializer && _valueTypeDeserializer == typeDeserializer && _nullProvider == nullValueProvider && _ignorableProperties == set && _includableProperties == set2) ? this : new MapDeserializer(this, keyDeserializer, (JsonDeserializer<Object>) jsonDeserializer, typeDeserializer, nullValueProvider, set, set2);
    }
    protected final boolean _isStdKeyDeser(final JavaType javaType, final KeyDeserializer keyDeserializer) {
        final JavaType keyType;
        if (null == keyDeserializer || null == (keyType = javaType.getKeyType())) {
            return true;
        }
        final Class<?> rawClass = keyType.getRawClass();
        return (String.class == rawClass || Object.class == rawClass) && this.isDefaultKeyDeserializer(keyDeserializer);
    }
    public void setIgnorableProperties(final String[] strArr) {
        final HashSet hashSetArrayToSet = (null == strArr || 0 == strArr.length) ? null : ArrayBuilders.arrayToSet(strArr);
        _ignorableProperties = hashSetArrayToSet;
        _inclusionChecker = IgnorePropertiesUtil.buildCheckerIfNeeded(hashSetArrayToSet, _includableProperties);
    }
    public void setIgnorableProperties(Set<String> set) {
        if (null == set || 0 == set.size()) {
            set = null;
        }
        _ignorableProperties = set;
        _inclusionChecker = IgnorePropertiesUtil.buildCheckerIfNeeded(set, _includableProperties);
    }
    public void setIncludableProperties(final Set<String> set) {
        _includableProperties = set;
        _inclusionChecker = IgnorePropertiesUtil.buildCheckerIfNeeded(_ignorableProperties, set);
    }
    public void resolve(final DeserializationContext deserializationContext) throws JsonMappingException {
        if (_valueInstantiator.canCreateUsingDelegate()) {
            final JavaType delegateType = _valueInstantiator.getDelegateType(deserializationContext.getConfig());
            if (null == delegateType) {
                final JavaType javaType = _containerType;
                deserializationContext.reportBadDefinition(javaType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", javaType, _valueInstantiator.getClass().getName()));
            }
            _delegateDeserializer = this.findDeserializer(deserializationContext, delegateType, null);
        } else if (_valueInstantiator.canCreateUsingArrayDelegate()) {
            final JavaType arrayDelegateType = _valueInstantiator.getArrayDelegateType(deserializationContext.getConfig());
            if (null == arrayDelegateType) {
                final JavaType javaType2 = _containerType;
                deserializationContext.reportBadDefinition(javaType2, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", javaType2, _valueInstantiator.getClass().getName()));
            }
            _delegateDeserializer = this.findDeserializer(deserializationContext, arrayDelegateType, null);
        }
        if (_valueInstantiator.canCreateFromObjectWith()) {
            _propertyBasedCreator = PropertyBasedCreator.construct(deserializationContext, _valueInstantiator, _valueInstantiator.getFromObjectArguments(deserializationContext.getConfig()), deserializationContext.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
        }
        _standardStringKey = this._isStdKeyDeser(_containerType, _keyDeserializer);
    }
    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        KeyDeserializer keyDeserializerCreateContextual;
        final JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization;
        Set<String> set = Collections.emptySet()    ;
        Set<String> set2 = Collections.emptySet();
        final AnnotatedMember member;
        final Set<String> included;
        final KeyDeserializer keyDeserializer = _keyDeserializer;
        if (false) {
            keyDeserializerCreateContextual = deserializationContext.findKeyDeserializer(_containerType.getKeyType(), beanProperty);
        } else {
            final boolean z = keyDeserializer instanceof ContextualKeyDeserializer;
            keyDeserializerCreateContextual = keyDeserializer;
            if (z) {
                keyDeserializerCreateContextual = ((ContextualKeyDeserializer) keyDeserializer).createContextual(deserializationContext, beanProperty);
            }
        }
        final KeyDeserializer keyDeserializer2 = keyDeserializerCreateContextual;
        JsonDeserializer<?> jsonDeserializerFindConvertingContentDeserializer = _valueDeserializer;
        if (null != beanProperty) {
            jsonDeserializerFindConvertingContentDeserializer = this.findConvertingContentDeserializer(deserializationContext, beanProperty, jsonDeserializerFindConvertingContentDeserializer);
        }
        final JavaType contentType = _containerType.getContentType();
        if (null == jsonDeserializerFindConvertingContentDeserializer) {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(contentType, beanProperty);
        } else {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializerFindConvertingContentDeserializer, beanProperty, contentType);
        }
        final JsonDeserializer<?> jsonDeserializer = jsonDeserializerHandleSecondaryContextualization;
        TypeDeserializer typeDeserializerForProperty = _valueTypeDeserializer;
        if (null != typeDeserializerForProperty) {
            typeDeserializerForProperty = typeDeserializerForProperty.forProperty(beanProperty);
        }
        final TypeDeserializer typeDeserializer = typeDeserializerForProperty;
        Set<String> hashSet = _ignorableProperties;
        final Set<String> set3 = _includableProperties;
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (!_neitherNull(annotationIntrospector, beanProperty) || null == (member = beanProperty.getMember())) {
            set = hashSet;
            set2 = set3;
        } else {
            final DeserializationConfig config = deserializationContext.getConfig();
            final JsonIgnoreProperties.Value valueFindPropertyIgnoralByName = annotationIntrospector.findPropertyIgnoralByName(config, member);
            if (null != valueFindPropertyIgnoralByName) {
                final Set<String> setFindIgnoredForDeserialization = valueFindPropertyIgnoralByName.findIgnoredForDeserialization();
                if (!setFindIgnoredForDeserialization.isEmpty()) {
                    hashSet = null == hashSet ? new HashSet<>() : new HashSet(hashSet);
                    final Iterator<String> it = setFindIgnoredForDeserialization.iterator();
                    while (it.hasNext()) {
                        hashSet.add(it.next());
                    }
                }
            }
            final JsonIncludeProperties.Value valueFindPropertyInclusionByName = annotationIntrospector.findPropertyInclusionByName(config, member);
            if (null != valueFindPropertyInclusionByName && null != (included = valueFindPropertyInclusionByName.getIncluded())) {
                HashSet hashSet2 = new HashSet();
                if (null == set3) {
                    hashSet2 = new HashSet(included);
                } else {
                    for (final String str : included) {
                        if (set3.contains(str)) {
                            hashSet2.add(str);
                        }
                    }
                }
                set2 = hashSet2;
                set = hashSet;
            }
        }
        return this.withResolved(keyDeserializer2, typeDeserializer, jsonDeserializer, this.findContentNullProvider(deserializationContext, beanProperty, jsonDeserializer), set, set2);
    }
    public JsonDeserializer<Object> getContentDeserializer() {
        return _valueDeserializer;
    }
    public ValueInstantiator getValueInstantiator() {
        return _valueInstantiator;
    }
    public boolean isCachable() {
        return null == this._valueDeserializer && null == this._keyDeserializer && null == this._valueTypeDeserializer && null == this._ignorableProperties && null == this._includableProperties;
    }
    public LogicalType logicalType() {
        return LogicalType.Map;
    }
    public Map<Object, Object> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (null != this._propertyBasedCreator) {
            return this._deserializeUsingCreator(jsonParser, deserializationContext);
        }
        final JsonDeserializer<Object> jsonDeserializer = _delegateDeserializer;
        if (null != jsonDeserializer) {
            return (Map) _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (!_hasDefaultCreator) {
            return (Map) deserializationContext.handleMissingInstantiator(this.getMapClass(), this._valueInstantiator, jsonParser, "no default constructor found", new Object[0]);
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
                return (Map) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
            }
        }
        final Map<Object, Object> map = (Map) _valueInstantiator.createUsingDefault(deserializationContext);
        if (_standardStringKey) {
            this._readAndBindStringKeyMap(jsonParser, deserializationContext, map);
            return map;
        }
        this._readAndBind(jsonParser, deserializationContext, map);
        return map;
    }
    public Map<Object, Object> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Map<Object, Object> map) throws IOException {
        jsonParser.setCurrentValue(map);
        final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (JsonToken.START_OBJECT != jsonTokenCurrentToken && JsonToken.FIELD_NAME != jsonTokenCurrentToken) {
            return (Map) deserializationContext.handleUnexpectedToken(this.getMapClass(), jsonParser);
        }
        if (_standardStringKey) {
            this._readAndUpdateStringKeyMap(jsonParser, deserializationContext, map);
            return map;
        }
        this._readAndUpdate(jsonParser, deserializationContext, map);
        return map;
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }
    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }
    public final Class<?> getMapClass() {
        return _containerType.getRawClass();
    }
    public JavaType getValueType() {
        return _containerType;
    }
    protected final void _readAndBind(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Map<Object, Object> map) throws IOException {
        String strCurrentName;
        Object objDeserializeWithType = null    ;
        final KeyDeserializer keyDeserializer = _keyDeserializer;
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
        final boolean z = null != jsonDeserializer.getObjectIdReader();
        final MapReferringAccumulator mapReferringAccumulator = z ? new MapReferringAccumulator(_containerType.getContentType().getRawClass(), map) : null;
        if (jsonParser.isExpectedStartObjectToken()) {
            strCurrentName = jsonParser.nextFieldName();
        } else {
            final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            final JsonToken jsonToken = JsonToken.FIELD_NAME;
            if (jsonTokenCurrentToken != jsonToken) {
                if (JsonToken.END_OBJECT == jsonTokenCurrentToken) {
                    return;
                } else {
                    deserializationContext.reportWrongTokenException(this, jsonToken, (String) null);
                }
            }
            strCurrentName = jsonParser.currentName();
        }
        while (null != strCurrentName) {
            final Object objDeserializeKey = keyDeserializer.deserializeKey(strCurrentName, deserializationContext);
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final IgnorePropertiesUtil.Checker checker = _inclusionChecker;
            if (null != checker && checker.shouldIgnore(strCurrentName)) {
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
                    if (z) {
                        mapReferringAccumulator.put(objDeserializeKey, objDeserializeWithType);
                    } else {
                        map.put(objDeserializeKey, objDeserializeWithType);
                    }
                } catch (final UnresolvedForwardReference e2) {
                    this.handleUnresolvedReference(deserializationContext, mapReferringAccumulator, objDeserializeKey, e2);
                } catch (final Exception e3) {
                    this.wrapAndThrow(deserializationContext, e3, map, strCurrentName);
                }
            }
            strCurrentName = jsonParser.nextFieldName();
        }
    }
    protected final void _readAndBindStringKeyMap(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Map<Object, Object> map) throws IOException {
        String strCurrentName;
        Object objDeserializeWithType = null    ;
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
        final boolean z = null != jsonDeserializer.getObjectIdReader();
        final MapReferringAccumulator mapReferringAccumulator = z ? new MapReferringAccumulator(_containerType.getContentType().getRawClass(), map) : null;
        if (jsonParser.isExpectedStartObjectToken()) {
            strCurrentName = jsonParser.nextFieldName();
        } else {
            final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            if (JsonToken.END_OBJECT == jsonTokenCurrentToken) {
                return;
            }
            final JsonToken jsonToken = JsonToken.FIELD_NAME;
            if (jsonTokenCurrentToken != jsonToken) {
                deserializationContext.reportWrongTokenException(this, jsonToken, (String) null);
            }
            strCurrentName = jsonParser.currentName();
        }
        while (null != strCurrentName) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final IgnorePropertiesUtil.Checker checker = _inclusionChecker;
            if (null != checker && checker.shouldIgnore(strCurrentName)) {
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
                    if (z) {
                        mapReferringAccumulator.put(strCurrentName, objDeserializeWithType);
                    } else {
                        map.put(strCurrentName, objDeserializeWithType);
                    }
                } catch (final UnresolvedForwardReference e2) {
                    this.handleUnresolvedReference(deserializationContext, mapReferringAccumulator, strCurrentName, e2);
                } catch (final Exception e3) {
                    this.wrapAndThrow(deserializationContext, e3, map, strCurrentName);
                }
            }
            strCurrentName = jsonParser.nextFieldName();
        }
    }
    public Map<Object, Object> _deserializeUsingCreator(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        String strCurrentName;
        Object objDeserializeWithType = null;
        final PropertyBasedCreator propertyBasedCreator = _propertyBasedCreator;
        final PropertyValueBuffer propertyValueBufferStartBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, null);
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
        if (jsonParser.isExpectedStartObjectToken()) {
            strCurrentName = jsonParser.nextFieldName();
        } else {
            strCurrentName = jsonParser.hasToken(JsonToken.FIELD_NAME) ? jsonParser.currentName() : null;
        }
        while (null != strCurrentName) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final IgnorePropertiesUtil.Checker checker = _inclusionChecker;
            if (null != checker && checker.shouldIgnore(strCurrentName)) {
                jsonParser.skipChildren();
            } else {
                final SettableBeanProperty settableBeanPropertyFindCreatorProperty = propertyBasedCreator.findCreatorProperty(strCurrentName);
                if (null != settableBeanPropertyFindCreatorProperty) {
                    if (propertyValueBufferStartBuilding.assignParameter(settableBeanPropertyFindCreatorProperty, settableBeanPropertyFindCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                        jsonParser.nextToken();
                        try {
                            final Map<Object, Object> map = (Map) propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
                            this._readAndBind(jsonParser, deserializationContext, map);
                            return map;
                        } catch (final Exception e2) {
                            return this.wrapAndThrow(deserializationContext, e2, _containerType.getRawClass(), strCurrentName);
                        }
                    }
                } else {
                    final Object objDeserializeKey = _keyDeserializer.deserializeKey(strCurrentName, deserializationContext);
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
                        propertyValueBufferStartBuilding.bufferMapProperty(objDeserializeKey, objDeserializeWithType);
                    } catch (final Exception e3) {
                        this.wrapAndThrow(deserializationContext, e3, _containerType.getRawClass(), strCurrentName);
                        return null;
                    }
                }
            }
            strCurrentName = jsonParser.nextFieldName();
        }
        try {
            return (Map) propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
        } catch (final Exception e4) {
            this.wrapAndThrow(deserializationContext, e4, _containerType.getRawClass(), strCurrentName);
            return null;
        }
    }
    protected final void _readAndUpdate(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Map<Object, Object> map) throws IOException {
        String strCurrentName;
        Object objDeserializeWithType;
        final KeyDeserializer keyDeserializer = _keyDeserializer;
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
        if (jsonParser.isExpectedStartObjectToken()) {
            strCurrentName = jsonParser.nextFieldName();
        } else {
            final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            if (JsonToken.END_OBJECT == jsonTokenCurrentToken) {
                return;
            }
            final JsonToken jsonToken = JsonToken.FIELD_NAME;
            if (jsonTokenCurrentToken != jsonToken) {
                deserializationContext.reportWrongTokenException(this, jsonToken, (String) null);
            }
            strCurrentName = jsonParser.currentName();
        }
        while (null != strCurrentName) {
            final Object objDeserializeKey = keyDeserializer.deserializeKey(strCurrentName, deserializationContext);
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final IgnorePropertiesUtil.Checker checker = _inclusionChecker;
            if (null != checker && checker.shouldIgnore(strCurrentName)) {
                jsonParser.skipChildren();
            } else {
                try {
                    if (JsonToken.VALUE_NULL == jsonTokenNextToken) {
                        if (!_skipNullValues) {
                            map.put(objDeserializeKey, _nullProvider.getNullValue(deserializationContext));
                        }
                    } else {
                        final Object obj = map.get(objDeserializeKey);
                        if (null != obj) {
                            if (null == typeDeserializer) {
                                objDeserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext, obj);
                            } else {
                                objDeserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer, obj);
                            }
                        } else if (null == typeDeserializer) {
                            objDeserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                        } else {
                            objDeserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                        }
                        if (objDeserializeWithType != obj) {
                            map.put(objDeserializeKey, objDeserializeWithType);
                        }
                    }
                } catch (final Exception e2) {
                    this.wrapAndThrow(deserializationContext, e2, map, strCurrentName);
                }
            }
            strCurrentName = jsonParser.nextFieldName();
        }
    }
    protected final void _readAndUpdateStringKeyMap(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Map<Object, Object> map) throws IOException {
        String strCurrentName;
        Object objDeserializeWithType;
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
        if (jsonParser.isExpectedStartObjectToken()) {
            strCurrentName = jsonParser.nextFieldName();
        } else {
            final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            if (JsonToken.END_OBJECT == jsonTokenCurrentToken) {
                return;
            }
            final JsonToken jsonToken = JsonToken.FIELD_NAME;
            if (jsonTokenCurrentToken != jsonToken) {
                deserializationContext.reportWrongTokenException(this, jsonToken, (String) null);
            }
            strCurrentName = jsonParser.currentName();
        }
        while (null != strCurrentName) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final IgnorePropertiesUtil.Checker checker = _inclusionChecker;
            if (null != checker && checker.shouldIgnore(strCurrentName)) {
                jsonParser.skipChildren();
            } else {
                try {
                    if (JsonToken.VALUE_NULL == jsonTokenNextToken) {
                        if (!_skipNullValues) {
                            map.put(strCurrentName, _nullProvider.getNullValue(deserializationContext));
                        }
                    } else {
                        final Object obj = map.get(strCurrentName);
                        if (null != obj) {
                            if (null == typeDeserializer) {
                                objDeserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext, obj);
                            } else {
                                objDeserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer, obj);
                            }
                        } else if (null == typeDeserializer) {
                            objDeserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                        } else {
                            objDeserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                        }
                        if (objDeserializeWithType != obj) {
                            map.put(strCurrentName, objDeserializeWithType);
                        }
                    }
                } catch (final Exception e2) {
                    this.wrapAndThrow(deserializationContext, e2, map, strCurrentName);
                }
            }
            strCurrentName = jsonParser.nextFieldName();
        }
    }
    private void handleUnresolvedReference(final DeserializationContext deserializationContext, final MapReferringAccumulator mapReferringAccumulator, final Object obj, final UnresolvedForwardReference unresolvedForwardReference) throws JsonMappingException {
        if (null == mapReferringAccumulator) {
            deserializationContext.reportInputMismatch(this, "Unresolved forward reference but no identity info: " + unresolvedForwardReference);
        }
        unresolvedForwardReference.getRoid().appendReferring(mapReferringAccumulator.handleUnresolvedReference(unresolvedForwardReference, obj));
    }
    private static final class MapReferringAccumulator {
        private final List<MapReferring> _accumulator = new ArrayList();
        private final Map<Object, Object> _result;
        private final Class<?> _valueType;
        public MapReferringAccumulator(final Class<?> cls, final Map<Object, Object> map) {
            _valueType = cls;
            _result = map;
        }
        public void put(final Object obj, final Object obj2) {
            if (_accumulator.isEmpty()) {
                _result.put(obj, obj2);
            } else {
                _accumulator.get(_accumulator.size() - 1).next.put(obj, obj2);
            }
        }
        public ReadableObjectId.Referring handleUnresolvedReference(final UnresolvedForwardReference unresolvedForwardReference, final Object obj) {
            final MapReferring mapReferring = new MapReferring(this, unresolvedForwardReference, _valueType, obj);
            _accumulator.add(mapReferring);
            return mapReferring;
        }
        public void resolveForwardReference(final Object obj, final Object obj2) throws IOException {
            final Iterator<MapReferring> it = _accumulator.iterator();
            Map<Object, Object> map = _result;
            while (it.hasNext()) {
                final MapReferring next = it.next();
                if (next.hasId(obj)) {
                    it.remove();
                    map.put(next.key, obj2);
                    map.putAll(next.next);
                    return;
                }
                map = next.next;
            }
            throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj + "] that wasn't previously seen as unresolved.");
        }
    }
    static class MapReferring extends ReadableObjectId.Referring {
        private final MapReferringAccumulator _parent;
        public final Object key;
        public final Map<Object, Object> next;

        MapReferring(final MapReferringAccumulator mapReferringAccumulator, final UnresolvedForwardReference unresolvedForwardReference, final Class<?> cls, final Object obj) {
            super(unresolvedForwardReference, cls);
            next = new LinkedHashMap();
            _parent = mapReferringAccumulator;
            key = obj;
        }

        @Override // com.fasterxml.jackson.databind.deser.impl.ReadableObjectId.Referring
        public void handleResolvedForwardReference(final Object obj, final Object obj2) throws IOException {
            _parent.resolveForwardReference(obj, obj2);
        }
    }
}
