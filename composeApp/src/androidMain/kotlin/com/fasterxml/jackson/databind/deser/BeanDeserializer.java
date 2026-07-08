package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.deser.impl.*;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanDeserializer extends BeanDeserializerBase implements Serializable {
    private static final long serialVersionUID = 1;
    private volatile transient NameTransformer _currentlyTransforming;
    protected transient Exception _nullFromCreator;
    public BeanDeserializer(final BeanDeserializerBuilder beanDeserializerBuilder, final BeanDescription beanDescription, final BeanPropertyMap beanPropertyMap, final Map<String, SettableBeanProperty> map, final HashSet<String> hashSet, final boolean z, final boolean z2) {
        super(beanDeserializerBuilder, beanDescription, beanPropertyMap, map, hashSet, z, null, z2);
    }
    public BeanDeserializer(final BeanDeserializerBuilder beanDeserializerBuilder, final BeanDescription beanDescription, final BeanPropertyMap beanPropertyMap, final Map<String, SettableBeanProperty> map, final HashSet<String> hashSet, final boolean z, final Set<String> set, final boolean z2) {
        super(beanDeserializerBuilder, beanDescription, beanPropertyMap, map, hashSet, z, set, z2);
    }
    protected BeanDeserializer(final BeanDeserializerBase beanDeserializerBase) {
        super(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
    }
    protected BeanDeserializer(final BeanDeserializerBase beanDeserializerBase, final boolean z) {
        super(beanDeserializerBase, z);
    }
    protected BeanDeserializer(final BeanDeserializerBase beanDeserializerBase, final NameTransformer nameTransformer) {
        super(beanDeserializerBase, nameTransformer);
    }
    public BeanDeserializer(final BeanDeserializerBase beanDeserializerBase, final ObjectIdReader objectIdReader) {
        super(beanDeserializerBase, objectIdReader);
    }
    public BeanDeserializer(final BeanDeserializerBase beanDeserializerBase, final Set<String> set) {
        super(beanDeserializerBase, set);
    }
    public BeanDeserializer(final BeanDeserializerBase beanDeserializerBase, final Set<String> set, final Set<String> set2) {
        super(beanDeserializerBase, set, set2);
    }
    public BeanDeserializer(final BeanDeserializerBase beanDeserializerBase, final BeanPropertyMap beanPropertyMap) {
        super(beanDeserializerBase, beanPropertyMap);
    }
    public JsonDeserializer<Object> unwrappingDeserializer(final NameTransformer nameTransformer) {
        if (BeanDeserializer.class != getClass() || _currentlyTransforming == nameTransformer) {
            return this;
        }
        _currentlyTransforming = nameTransformer;
        try {
            return new BeanDeserializer(this, nameTransformer);
        } finally {
            _currentlyTransforming = null;
        }
    }
    public BeanDeserializer withObjectIdReader(final ObjectIdReader objectIdReader) {
        return new BeanDeserializer(this, objectIdReader);
    }
    public BeanDeserializer withByNameInclusion(final Set<String> set, final Set<String> set2) {
        return new BeanDeserializer(this, set, set2);
    }
    public BeanDeserializerBase withIgnoreAllUnknown(final boolean z) {
        return new BeanDeserializer(this, z);
    }
    public BeanDeserializerBase withBeanProperties(final BeanPropertyMap beanPropertyMap) {
        return new BeanDeserializer(this, beanPropertyMap);
    }
    protected BeanDeserializerBase asArrayDeserializer() {
        return new BeanAsArrayDeserializer(this, _beanProperties.getPropertiesInInsertionOrder());
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.isExpectedStartObjectToken()) {
            if (_vanillaProcessing) {
                return this.vanillaDeserialize(jsonParser, deserializationContext, jsonParser.nextToken());
            }
            jsonParser.nextToken();
            if (null != this._objectIdReader) {
                return this.deserializeWithObjectId(jsonParser, deserializationContext);
            }
            return this.deserializeFromObject(jsonParser, deserializationContext);
        }
        return this._deserializeOther(jsonParser, deserializationContext, jsonParser.currentToken());
    }
    protected final Object _deserializeOther(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JsonToken jsonToken) throws IOException {
        if (null != jsonToken) {
            switch (C11941.SwitchMapcomfasterxmljacksoncoreJsonToken[jsonToken.ordinal()]) {
                case 1:
                    return this.deserializeFromString(jsonParser, deserializationContext);
                case 2:
                    return this.deserializeFromNumber(jsonParser, deserializationContext);
                case 3:
                    return this.deserializeFromDouble(jsonParser, deserializationContext);
                case 4:
                    return this.deserializeFromEmbedded(jsonParser, deserializationContext);
                case 5:
                case 6:
                    return this.deserializeFromBoolean(jsonParser, deserializationContext);
                case 7:
                    return this.deserializeFromNull(jsonParser, deserializationContext);
                case 8:
                    return this._deserializeFromArray(jsonParser, deserializationContext);
                case 9:
                case 10:
                    if (_vanillaProcessing) {
                        return this.vanillaDeserialize(jsonParser, deserializationContext, jsonToken);
                    }
                    if (null != this._objectIdReader) {
                        return this.deserializeWithObjectId(jsonParser, deserializationContext);
                    }
                    return this.deserializeFromObject(jsonParser, deserializationContext);
            }
        }
        return deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
    }
    protected Object _missingToken(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        throw deserializationContext.endOfInputException(this.handledType());
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        String strCurrentName;
        final Class<?> activeView;
        jsonParser.setCurrentValue(obj);
        if (null != this._injectables) {
            this.injectValues(deserializationContext, obj);
        }
        if (null != this._unwrappedPropertyHandler) {
            return this.deserializeWithUnwrapped(jsonParser, deserializationContext, obj);
        }
        if (null != this._externalTypeIdHandler) {
            return this.deserializeWithExternalTypeId(jsonParser, deserializationContext, obj);
        }
        if (jsonParser.isExpectedStartObjectToken()) {
            strCurrentName = jsonParser.nextFieldName();
            if (null == strCurrentName) {
                return obj;
            }
        } else {
            if (jsonParser.hasTokenId(5)) {
                strCurrentName = jsonParser.currentName();
            }
            return obj;
        }
        if (_needViewProcesing && null != (activeView = deserializationContext.getActiveView())) {
            return this.deserializeWithView(jsonParser, deserializationContext, obj, activeView);
        }
        do {
            jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
            if (null != settableBeanPropertyFind) {
                try {
                    settableBeanPropertyFind.deserializeAndSet(jsonParser, deserializationContext, obj);
                } catch (final Exception e2) {
                    this.wrapAndThrow(e2, obj, strCurrentName, deserializationContext);
                }
            } else {
                this.handleUnknownVanilla(jsonParser, deserializationContext, obj, strCurrentName);
            }
            strCurrentName = jsonParser.nextFieldName();
        } while (null != strCurrentName);
        return obj;
    }
    private final Object vanillaDeserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JsonToken jsonToken) throws IOException {
        final Object objCreateUsingDefault = _valueInstantiator.createUsingDefault(deserializationContext);
        jsonParser.setCurrentValue(objCreateUsingDefault);
        if (jsonParser.hasTokenId(5)) {
            String strCurrentName = jsonParser.currentName();
            do {
                jsonParser.nextToken();
                final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
                if (null != settableBeanPropertyFind) {
                    try {
                        settableBeanPropertyFind.deserializeAndSet(jsonParser, deserializationContext, objCreateUsingDefault);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, objCreateUsingDefault, strCurrentName, deserializationContext);
                    }
                } else {
                    this.handleUnknownVanilla(jsonParser, deserializationContext, objCreateUsingDefault, strCurrentName);
                }
                strCurrentName = jsonParser.nextFieldName();
            } while (null != strCurrentName);
        }
        return objCreateUsingDefault;
    }
    public Object deserializeFromObject(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        final Class<?> activeView;
        final Object objectId;
        final ObjectIdReader objectIdReader = _objectIdReader;
        if (null != objectIdReader && objectIdReader.maySerializeAsObject() && jsonParser.hasTokenId(5) && _objectIdReader.isValidReferencePropertyName(jsonParser.currentName(), jsonParser)) {
            return this.deserializeFromObjectId(jsonParser, deserializationContext);
        }
        if (_nonStandardCreation) {
            if (null != this._unwrappedPropertyHandler) {
                return this.deserializeWithUnwrapped(jsonParser, deserializationContext);
            }
            if (null != this._externalTypeIdHandler) {
                return this.deserializeWithExternalTypeId(jsonParser, deserializationContext);
            }
            return this.deserializeFromObjectUsingNonDefault(jsonParser, deserializationContext);
        }
        final Object objCreateUsingDefault = _valueInstantiator.createUsingDefault(deserializationContext);
        jsonParser.setCurrentValue(objCreateUsingDefault);
        if (jsonParser.canReadObjectId() && null != (objectId = jsonParser.getObjectId())) {
            this._handleTypedObjectId(jsonParser, deserializationContext, objCreateUsingDefault, objectId);
        }
        if (null != this._injectables) {
            this.injectValues(deserializationContext, objCreateUsingDefault);
        }
        if (_needViewProcesing && null != (activeView = deserializationContext.getActiveView())) {
            return this.deserializeWithView(jsonParser, deserializationContext, objCreateUsingDefault, activeView);
        }
        if (jsonParser.hasTokenId(5)) {
            String strCurrentName = jsonParser.currentName();
            do {
                jsonParser.nextToken();
                final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
                if (null != settableBeanPropertyFind) {
                    try {
                        settableBeanPropertyFind.deserializeAndSet(jsonParser, deserializationContext, objCreateUsingDefault);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, objCreateUsingDefault, strCurrentName, deserializationContext);
                    }
                } else {
                    this.handleUnknownVanilla(jsonParser, deserializationContext, objCreateUsingDefault, strCurrentName);
                }
                strCurrentName = jsonParser.nextFieldName();
            } while (null != strCurrentName);
        }
        return objCreateUsingDefault;
    }
    protected Object _deserializeUsingPropertyBased(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        Object objBuild;
        Object objWrapInstantiationProblem;
        final PropertyBasedCreator propertyBasedCreator = _propertyBasedCreator;
        final PropertyValueBuffer propertyValueBufferStartBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, _objectIdReader);
        final Class<?> activeView = _needViewProcesing ? deserializationContext.getActiveView() : null;
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        ArrayList arrayList = null;
        TokenBuffer tokenBuffer = null;
        while (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            final String strCurrentName = jsonParser.currentName();
            jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFindCreatorProperty = propertyBasedCreator.findCreatorProperty(strCurrentName);
            if (!propertyValueBufferStartBuilding.readIdProperty(strCurrentName) || null != settableBeanPropertyFindCreatorProperty) {
                if (null != settableBeanPropertyFindCreatorProperty) {
                    if (null != activeView && !settableBeanPropertyFindCreatorProperty.visibleInView(activeView)) {
                        jsonParser.skipChildren();
                    } else if (propertyValueBufferStartBuilding.assignParameter(settableBeanPropertyFindCreatorProperty, this._deserializeWithErrorWrapping(jsonParser, deserializationContext, settableBeanPropertyFindCreatorProperty))) {
                        jsonParser.nextToken();
                        try {
                            objWrapInstantiationProblem = propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
                        } catch (final Exception e2) {
                            objWrapInstantiationProblem = this.wrapInstantiationProblem(e2, deserializationContext);
                        }
                        if (null == objWrapInstantiationProblem) {
                            return deserializationContext.handleInstantiationProblem(this.handledType(), null, this._creatorReturnedNullException());
                        }
                        jsonParser.setCurrentValue(objWrapInstantiationProblem);
                        if (objWrapInstantiationProblem.getClass() != _beanType.getRawClass()) {
                            return this.handlePolymorphic(jsonParser, deserializationContext, objWrapInstantiationProblem, tokenBuffer);
                        }
                        if (null != tokenBuffer) {
                            objWrapInstantiationProblem = this.handleUnknownProperties(deserializationContext, objWrapInstantiationProblem, tokenBuffer);
                        }
                        return this.deserialize(jsonParser, deserializationContext, objWrapInstantiationProblem);
                    }
                } else {
                    final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
                    if (null != settableBeanPropertyFind) {
                        try {
                            propertyValueBufferStartBuilding.bufferProperty(settableBeanPropertyFind, this._deserializeWithErrorWrapping(jsonParser, deserializationContext, settableBeanPropertyFind));
                        } catch (final UnresolvedForwardReference e3) {
                            final BeanReferring beanReferringHandleUnresolvedReference = this.handleUnresolvedReference(deserializationContext, settableBeanPropertyFind, propertyValueBufferStartBuilding, e3);
                            if (null == arrayList) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(beanReferringHandleUnresolvedReference);
                        }
                    } else if (IgnorePropertiesUtil.shouldIgnore(strCurrentName, _ignorableProps, _includableProps)) {
                        this.handleIgnoredProperty(jsonParser, deserializationContext, this.handledType(), strCurrentName);
                    } else {
                        final SettableAnyProperty settableAnyProperty = _anySetter;
                        if (null != settableAnyProperty) {
                            try {
                                propertyValueBufferStartBuilding.bufferAnyProperty(settableAnyProperty, strCurrentName, settableAnyProperty.deserialize(jsonParser, deserializationContext));
                            } catch (final Exception e4) {
                                this.wrapAndThrow(e4, _beanType.getRawClass(), strCurrentName, deserializationContext);
                            }
                        } else {
                            if (null == tokenBuffer) {
                                tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
                            }
                            tokenBuffer.writeFieldName(strCurrentName);
                            tokenBuffer.copyCurrentStructure(jsonParser);
                        }
                    }
                }
            }
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        try {
            objBuild = propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
        } catch (final Exception e5) {
            this.wrapInstantiationProblem(e5, deserializationContext);
            objBuild = null;
        }
        if (null != this._injectables) {
            this.injectValues(deserializationContext, objBuild);
        }
        if (null != arrayList) {
            final Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((BeanReferring) it.next()).setBean(objBuild);
            }
        }
        if (null == tokenBuffer) {
            return objBuild;
        }
        if (objBuild.getClass() != _beanType.getRawClass()) {
            return this.handlePolymorphic(null, deserializationContext, objBuild, tokenBuffer);
        }
        return this.handleUnknownProperties(deserializationContext, objBuild, tokenBuffer);
    }
    private BeanReferring handleUnresolvedReference(final DeserializationContext deserializationContext, final SettableBeanProperty settableBeanProperty, final PropertyValueBuffer propertyValueBuffer, final UnresolvedForwardReference unresolvedForwardReference) throws JsonMappingException {
        final BeanReferring beanReferring = new BeanReferring(deserializationContext, unresolvedForwardReference, settableBeanProperty.getType(), propertyValueBuffer, settableBeanProperty);
        unresolvedForwardReference.getRoid().appendReferring(beanReferring);
        return beanReferring;
    }
    protected final Object _deserializeWithErrorWrapping(final JsonParser jsonParser, final DeserializationContext deserializationContext, final SettableBeanProperty settableBeanProperty) throws IOException {
        try {
            return settableBeanProperty.deserialize(jsonParser, deserializationContext);
        } catch (final Exception e2) {
            this.wrapAndThrow(e2, _beanType.getRawClass(), settableBeanProperty.getName(), deserializationContext);
            return null;
        }
    }
    protected Object deserializeFromNull(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.requiresCustomCodec()) {
            final TokenBuffer tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
            tokenBuffer.writeEndObject();
            final JsonParser jsonParserAsParser = tokenBuffer.asParser(jsonParser);
            jsonParserAsParser.nextToken();
            final Object objVanillaDeserialize = _vanillaProcessing ? this.vanillaDeserialize(jsonParserAsParser, deserializationContext, JsonToken.END_OBJECT) : this.deserializeFromObject(jsonParserAsParser, deserializationContext);
            jsonParserAsParser.close();
            return objVanillaDeserialize;
        }
        return deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
    }
    protected Object _deserializeFromArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        JsonDeserializer<Object> jsonDeserializer = _arrayDelegateDeserializer;
        if (null != jsonDeserializer || null != (jsonDeserializer = this._delegateDeserializer)) {
            final Object objCreateUsingArrayDelegate = _valueInstantiator.createUsingArrayDelegate(deserializationContext, jsonDeserializer.deserialize(jsonParser, deserializationContext));
            if (null != this._injectables) {
                this.injectValues(deserializationContext, objCreateUsingArrayDelegate);
            }
            return objCreateUsingArrayDelegate;
        }
        final CoercionAction coercionAction_findCoercionFromEmptyArray = this._findCoercionFromEmptyArray(deserializationContext);
        final boolean zIsEnabled = deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        if (zIsEnabled || CoercionAction.Fail != coercionAction_findCoercionFromEmptyArray) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final JsonToken jsonToken = JsonToken.END_ARRAY;
            if (jsonTokenNextToken == jsonToken) {
                final int i2 = C11941.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[coercionAction_findCoercionFromEmptyArray.ordinal()];
                if (1 == i2) {
                    return this.getEmptyValue(deserializationContext);
                }
                if (2 == i2 || 3 == i2) {
                    return this.getNullValue(deserializationContext);
                }
                return deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), JsonToken.START_ARRAY, jsonParser, null);
            }
            if (zIsEnabled) {
                final Object objDeserialize = this.deserialize(jsonParser, deserializationContext);
                if (jsonParser.nextToken() != jsonToken) {
                    this.handleMissingEndArrayForSingle(jsonParser, deserializationContext);
                }
                return objDeserialize;
            }
        }
        return deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
    }
    enum C11941 {
        ;
        static final int[] SwitchMapcomfasterxmljacksoncoreJsonToken;
        static final int[] SwitchMapcomfasterxmljacksondatabindcfgCoercionAction;

        static {
            final int[] iArr = new int[CoercionAction.values().length];
            SwitchMapcomfasterxmljacksondatabindcfgCoercionAction = iArr;
            try {
                iArr[CoercionAction.AsEmpty.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C11941.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[CoercionAction.AsNull.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C11941.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[CoercionAction.TryConvert.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            final int[] iArr2 = new int[JsonToken.values().length];
            SwitchMapcomfasterxmljacksoncoreJsonToken = iArr2;
            try {
                iArr2[JsonToken.VALUE_STRING.ordinal()] = 1;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C11941.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 2;
            } catch (final NoSuchFieldError unused5) {
            }
            try {
                C11941.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 3;
            } catch (final NoSuchFieldError unused6) {
            }
            try {
                C11941.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 4;
            } catch (final NoSuchFieldError unused7) {
            }
            try {
                C11941.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_TRUE.ordinal()] = 5;
            } catch (final NoSuchFieldError unused8) {
            }
            try {
                C11941.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_FALSE.ordinal()] = 6;
            } catch (final NoSuchFieldError unused9) {
            }
            try {
                C11941.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_NULL.ordinal()] = 7;
            } catch (final NoSuchFieldError unused10) {
            }
            try {
                C11941.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.START_ARRAY.ordinal()] = 8;
            } catch (final NoSuchFieldError unused11) {
            }
            try {
                C11941.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.FIELD_NAME.ordinal()] = 9;
            } catch (final NoSuchFieldError unused12) {
            }
            try {
                C11941.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.END_OBJECT.ordinal()] = 10;
            } catch (final NoSuchFieldError unused13) {
            }
        }
    }
    protected final Object deserializeWithView(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj, final Class<?> cls) throws IOException {
        if (jsonParser.hasTokenId(5)) {
            String strCurrentName = jsonParser.currentName();
            do {
                jsonParser.nextToken();
                final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
                if (null != settableBeanPropertyFind) {
                    if (!settableBeanPropertyFind.visibleInView(cls)) {
                        jsonParser.skipChildren();
                    } else {
                        try {
                            settableBeanPropertyFind.deserializeAndSet(jsonParser, deserializationContext, obj);
                        } catch (final Exception e2) {
                            this.wrapAndThrow(e2, obj, strCurrentName, deserializationContext);
                        }
                    }
                } else {
                    this.handleUnknownVanilla(jsonParser, deserializationContext, obj, strCurrentName);
                }
                strCurrentName = jsonParser.nextFieldName();
            } while (null != strCurrentName);
        }
        return obj;
    }
    protected Object deserializeWithUnwrapped(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        final JsonDeserializer<Object> jsonDeserializer = _delegateDeserializer;
        if (null != jsonDeserializer) {
            return _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (null != this._propertyBasedCreator) {
            return this.deserializeUsingPropertyBasedWithUnwrapped(jsonParser, deserializationContext);
        }
        final TokenBuffer tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
        tokenBuffer.writeStartObject();
        final Object objCreateUsingDefault = _valueInstantiator.createUsingDefault(deserializationContext);
        jsonParser.setCurrentValue(objCreateUsingDefault);
        if (null != this._injectables) {
            this.injectValues(deserializationContext, objCreateUsingDefault);
        }
        final Class<?> activeView = _needViewProcesing ? deserializationContext.getActiveView() : null;
        String strCurrentName = jsonParser.hasTokenId(5) ? jsonParser.currentName() : null;
        while (null != strCurrentName) {
            jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
            if (null != settableBeanPropertyFind) {
                if (null != activeView && !settableBeanPropertyFind.visibleInView(activeView)) {
                    jsonParser.skipChildren();
                } else {
                    try {
                        settableBeanPropertyFind.deserializeAndSet(jsonParser, deserializationContext, objCreateUsingDefault);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, objCreateUsingDefault, strCurrentName, deserializationContext);
                    }
                }
            } else if (IgnorePropertiesUtil.shouldIgnore(strCurrentName, _ignorableProps, _includableProps)) {
                this.handleIgnoredProperty(jsonParser, deserializationContext, objCreateUsingDefault, strCurrentName);
            } else if (null == this._anySetter) {
                tokenBuffer.writeFieldName(strCurrentName);
                tokenBuffer.copyCurrentStructure(jsonParser);
            } else {
                final TokenBuffer tokenBufferAsCopyOfValue = TokenBuffer.asCopyOfValue(jsonParser);
                tokenBuffer.writeFieldName(strCurrentName);
                tokenBuffer.append(tokenBufferAsCopyOfValue);
                try {
                    _anySetter.deserializeAndSet(tokenBufferAsCopyOfValue.asParserOnFirstToken(), deserializationContext, objCreateUsingDefault, strCurrentName);
                } catch (final Exception e3) {
                    this.wrapAndThrow(e3, objCreateUsingDefault, strCurrentName, deserializationContext);
                }
            }
            strCurrentName = jsonParser.nextFieldName();
        }
        tokenBuffer.writeEndObject();
        _unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, objCreateUsingDefault, tokenBuffer);
        return objCreateUsingDefault;
    }
    protected Object deserializeWithUnwrapped(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (JsonToken.START_OBJECT == jsonTokenCurrentToken) {
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        final TokenBuffer tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
        tokenBuffer.writeStartObject();
        final Class<?> activeView = _needViewProcesing ? deserializationContext.getActiveView() : null;
        while (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            final String strCurrentName = jsonParser.currentName();
            final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
            jsonParser.nextToken();
            if (null != settableBeanPropertyFind) {
                if (null != activeView && !settableBeanPropertyFind.visibleInView(activeView)) {
                    jsonParser.skipChildren();
                } else {
                    try {
                        settableBeanPropertyFind.deserializeAndSet(jsonParser, deserializationContext, obj);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, obj, strCurrentName, deserializationContext);
                    }
                }
            } else if (IgnorePropertiesUtil.shouldIgnore(strCurrentName, _ignorableProps, _includableProps)) {
                this.handleIgnoredProperty(jsonParser, deserializationContext, obj, strCurrentName);
            } else if (null == this._anySetter) {
                tokenBuffer.writeFieldName(strCurrentName);
                tokenBuffer.copyCurrentStructure(jsonParser);
            } else {
                final TokenBuffer tokenBufferAsCopyOfValue = TokenBuffer.asCopyOfValue(jsonParser);
                tokenBuffer.writeFieldName(strCurrentName);
                tokenBuffer.append(tokenBufferAsCopyOfValue);
                try {
                    _anySetter.deserializeAndSet(tokenBufferAsCopyOfValue.asParserOnFirstToken(), deserializationContext, obj, strCurrentName);
                } catch (final Exception e3) {
                    this.wrapAndThrow(e3, obj, strCurrentName, deserializationContext);
                }
            }
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        tokenBuffer.writeEndObject();
        _unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, obj, tokenBuffer);
        return obj;
    }
    protected Object deserializeUsingPropertyBasedWithUnwrapped(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        Object objWrapInstantiationProblem;
        final PropertyBasedCreator propertyBasedCreator = _propertyBasedCreator;
        final PropertyValueBuffer propertyValueBufferStartBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, _objectIdReader);
        final TokenBuffer tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
        tokenBuffer.writeStartObject();
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        while (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            final String strCurrentName = jsonParser.currentName();
            jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFindCreatorProperty = propertyBasedCreator.findCreatorProperty(strCurrentName);
            if (!propertyValueBufferStartBuilding.readIdProperty(strCurrentName) || null != settableBeanPropertyFindCreatorProperty) {
                if (null != settableBeanPropertyFindCreatorProperty) {
                    if (propertyValueBufferStartBuilding.assignParameter(settableBeanPropertyFindCreatorProperty, this._deserializeWithErrorWrapping(jsonParser, deserializationContext, settableBeanPropertyFindCreatorProperty))) {
                        JsonToken jsonTokenNextToken = jsonParser.nextToken();
                        try {
                            objWrapInstantiationProblem = propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
                        } catch (final Exception e2) {
                            objWrapInstantiationProblem = this.wrapInstantiationProblem(e2, deserializationContext);
                        }
                        jsonParser.setCurrentValue(objWrapInstantiationProblem);
                        while (JsonToken.FIELD_NAME == jsonTokenNextToken) {
                            tokenBuffer.copyCurrentStructure(jsonParser);
                            jsonTokenNextToken = jsonParser.nextToken();
                        }
                        final JsonToken jsonToken = JsonToken.END_OBJECT;
                        if (jsonTokenNextToken != jsonToken) {
                            deserializationContext.reportWrongTokenException(this, jsonToken, "Attempted to unwrap '%s' value", this.handledType().getName());
                        }
                        tokenBuffer.writeEndObject();
                        if (objWrapInstantiationProblem.getClass() != _beanType.getRawClass()) {
                            deserializationContext.reportInputMismatch(settableBeanPropertyFindCreatorProperty, "Cannot create polymorphic instances with unwrapped values");
                            return null;
                        }
                        return _unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, objWrapInstantiationProblem, tokenBuffer);
                    }
                } else {
                    final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
                    if (null != settableBeanPropertyFind) {
                        propertyValueBufferStartBuilding.bufferProperty(settableBeanPropertyFind, this._deserializeWithErrorWrapping(jsonParser, deserializationContext, settableBeanPropertyFind));
                    } else if (IgnorePropertiesUtil.shouldIgnore(strCurrentName, _ignorableProps, _includableProps)) {
                        this.handleIgnoredProperty(jsonParser, deserializationContext, this.handledType(), strCurrentName);
                    } else if (null == this._anySetter) {
                        tokenBuffer.writeFieldName(strCurrentName);
                        tokenBuffer.copyCurrentStructure(jsonParser);
                    } else {
                        final TokenBuffer tokenBufferAsCopyOfValue = TokenBuffer.asCopyOfValue(jsonParser);
                        tokenBuffer.writeFieldName(strCurrentName);
                        tokenBuffer.append(tokenBufferAsCopyOfValue);
                        try {
                            final SettableAnyProperty settableAnyProperty = _anySetter;
                            propertyValueBufferStartBuilding.bufferAnyProperty(settableAnyProperty, strCurrentName, settableAnyProperty.deserialize(tokenBufferAsCopyOfValue.asParserOnFirstToken(), deserializationContext));
                        } catch (final Exception e3) {
                            this.wrapAndThrow(e3, _beanType.getRawClass(), strCurrentName, deserializationContext);
                        }
                    }
                }
            }
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        try {
            return _unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding), tokenBuffer);
        } catch (final Exception e4) {
            this.wrapInstantiationProblem(e4, deserializationContext);
            return null;
        }
    }
    protected Object deserializeWithExternalTypeId(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (null != this._propertyBasedCreator) {
            return this.deserializeUsingPropertyBasedWithExternalTypeId(jsonParser, deserializationContext);
        }
        final JsonDeserializer<Object> jsonDeserializer = _delegateDeserializer;
        if (null != jsonDeserializer) {
            return _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
        return this.deserializeWithExternalTypeId(jsonParser, deserializationContext, _valueInstantiator.createUsingDefault(deserializationContext));
    }
    protected Object deserializeWithExternalTypeId(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        return this._deserializeWithExternalTypeId(jsonParser, deserializationContext, obj, _externalTypeIdHandler.start());
    }
    protected Object _deserializeWithExternalTypeId(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj, final ExternalTypeHandler externalTypeHandler) throws IOException {
        final Class<?> activeView = _needViewProcesing ? deserializationContext.getActiveView() : null;
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        while (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            final String strCurrentName = jsonParser.currentName();
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
            if (null != settableBeanPropertyFind) {
                if (jsonTokenNextToken.isScalarValue()) {
                    externalTypeHandler.handleTypePropertyValue(jsonParser, deserializationContext, strCurrentName, obj);
                }
                if (null != activeView && !settableBeanPropertyFind.visibleInView(activeView)) {
                    jsonParser.skipChildren();
                } else {
                    try {
                        settableBeanPropertyFind.deserializeAndSet(jsonParser, deserializationContext, obj);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, obj, strCurrentName, deserializationContext);
                    }
                }
            } else if (IgnorePropertiesUtil.shouldIgnore(strCurrentName, _ignorableProps, _includableProps)) {
                this.handleIgnoredProperty(jsonParser, deserializationContext, obj, strCurrentName);
            } else if (!externalTypeHandler.handlePropertyValue(jsonParser, deserializationContext, strCurrentName, obj)) {
                final SettableAnyProperty settableAnyProperty = _anySetter;
                if (null != settableAnyProperty) {
                    try {
                        settableAnyProperty.deserializeAndSet(jsonParser, deserializationContext, obj, strCurrentName);
                    } catch (final Exception e3) {
                        this.wrapAndThrow(e3, obj, strCurrentName, deserializationContext);
                    }
                } else {
                    this.handleUnknownProperty(jsonParser, deserializationContext, obj, strCurrentName);
                }
            }
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        return externalTypeHandler.complete(jsonParser, deserializationContext, obj);
    }
    protected Object deserializeUsingPropertyBasedWithExternalTypeId(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final ExternalTypeHandler externalTypeHandlerStart = _externalTypeIdHandler.start();
        final PropertyBasedCreator propertyBasedCreator = _propertyBasedCreator;
        final PropertyValueBuffer propertyValueBufferStartBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, _objectIdReader);
        final Class<?> activeView = _needViewProcesing ? deserializationContext.getActiveView() : null;
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        while (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            final String strCurrentName = jsonParser.currentName();
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFindCreatorProperty = propertyBasedCreator.findCreatorProperty(strCurrentName);
            if (!propertyValueBufferStartBuilding.readIdProperty(strCurrentName) || null != settableBeanPropertyFindCreatorProperty) {
                if (null != settableBeanPropertyFindCreatorProperty) {
                    if (!externalTypeHandlerStart.handlePropertyValue(jsonParser, deserializationContext, strCurrentName, null) && propertyValueBufferStartBuilding.assignParameter(settableBeanPropertyFindCreatorProperty, this._deserializeWithErrorWrapping(jsonParser, deserializationContext, settableBeanPropertyFindCreatorProperty))) {
                        jsonParser.nextToken();
                        try {
                            final Object objBuild = propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
                            if (objBuild.getClass() != _beanType.getRawClass()) {
                                final JavaType javaType = _beanType;
                                return deserializationContext.reportBadDefinition(javaType, String.format("Cannot create polymorphic instances with external type ids (%s -> %s)", javaType, objBuild.getClass()));
                            }
                            return this._deserializeWithExternalTypeId(jsonParser, deserializationContext, objBuild, externalTypeHandlerStart);
                        } catch (final Exception e2) {
                            this.wrapAndThrow(e2, _beanType.getRawClass(), strCurrentName, deserializationContext);
                        }
                    }
                } else {
                    final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
                    if (null != settableBeanPropertyFind) {
                        if (jsonTokenNextToken.isScalarValue()) {
                            externalTypeHandlerStart.handleTypePropertyValue(jsonParser, deserializationContext, strCurrentName, null);
                        }
                        if (null != activeView && !settableBeanPropertyFind.visibleInView(activeView)) {
                            jsonParser.skipChildren();
                        } else {
                            propertyValueBufferStartBuilding.bufferProperty(settableBeanPropertyFind, settableBeanPropertyFind.deserialize(jsonParser, deserializationContext));
                        }
                    } else if (!externalTypeHandlerStart.handlePropertyValue(jsonParser, deserializationContext, strCurrentName, null)) {
                        if (IgnorePropertiesUtil.shouldIgnore(strCurrentName, _ignorableProps, _includableProps)) {
                            this.handleIgnoredProperty(jsonParser, deserializationContext, this.handledType(), strCurrentName);
                        } else {
                            final SettableAnyProperty settableAnyProperty = _anySetter;
                            if (null != settableAnyProperty) {
                                propertyValueBufferStartBuilding.bufferAnyProperty(settableAnyProperty, strCurrentName, settableAnyProperty.deserialize(jsonParser, deserializationContext));
                            } else {
                                this.handleUnknownProperty(jsonParser, deserializationContext, _valueClass, strCurrentName);
                            }
                        }
                    }
                }
            }
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        try {
            return externalTypeHandlerStart.complete(jsonParser, deserializationContext, propertyValueBufferStartBuilding, propertyBasedCreator);
        } catch (final Exception e3) {
            return this.wrapInstantiationProblem(e3, deserializationContext);
        }
    }
    protected Exception _creatorReturnedNullException() {
        if (null == this._nullFromCreator) {
            _nullFromCreator = new NullPointerException("JSON Creator returned null");
        }
        return _nullFromCreator;
    }
    static class BeanReferring extends ReadableObjectId.Referring {
        private Object _bean;
        private final DeserializationContext _context;
        private final SettableBeanProperty _prop;
        BeanReferring(final DeserializationContext deserializationContext, final UnresolvedForwardReference unresolvedForwardReference, final JavaType javaType, final PropertyValueBuffer propertyValueBuffer, final SettableBeanProperty settableBeanProperty) {
            super(unresolvedForwardReference, javaType);
            _context = deserializationContext;
            _prop = settableBeanProperty;
        }
        public void setBean(final Object obj) {
            _bean = obj;
        }
        public void handleResolvedForwardReference(final Object obj, final Object obj2) throws IOException {
            if (null == this._bean) {
                final DeserializationContext deserializationContext = _context;
                final SettableBeanProperty settableBeanProperty = _prop;
                deserializationContext.reportInputMismatch(settableBeanProperty, "Cannot resolve ObjectId forward reference using property '%s' (of type %s): Bean not yet resolved", settableBeanProperty.getName(), _prop.getDeclaringClass().getName());
            }
            try {
                _prop.set(_bean, obj2);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
