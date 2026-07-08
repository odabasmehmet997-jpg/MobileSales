package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.deser.impl.*;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class BuilderBasedDeserializer extends BeanDeserializerBase {
    private static final long serialVersionUID = 1;
    protected final AnnotatedMethod _buildMethod;
    protected final JavaType _targetType;

    public BuilderBasedDeserializer(final BeanDeserializerBuilder beanDeserializerBuilder, final BeanDescription beanDescription, final JavaType javaType, final BeanPropertyMap beanPropertyMap, final Map<String, SettableBeanProperty> map, final Set<String> set, final boolean z, final boolean z2) {
        this(beanDeserializerBuilder, beanDescription, javaType, beanPropertyMap, map, set, z, null, z2);
    }

    public BuilderBasedDeserializer(final BeanDeserializerBuilder beanDeserializerBuilder, final BeanDescription beanDescription, final JavaType javaType, final BeanPropertyMap beanPropertyMap, final Map<String, SettableBeanProperty> map, final Set<String> set, final boolean z, final Set<String> set2, final boolean z2) {
        super(beanDeserializerBuilder, beanDescription, beanPropertyMap, map, set, z, set2, z2);
        _targetType = javaType;
        _buildMethod = beanDeserializerBuilder.getBuildMethod();
        if (null == this._objectIdReader) {
            return;
        }
        throw new IllegalArgumentException("Cannot use Object Id with Builder-based deserialization (type " + beanDescription.getType() + ")");
    }

    @Deprecated
    public BuilderBasedDeserializer(final BeanDeserializerBuilder beanDeserializerBuilder, final BeanDescription beanDescription, final BeanPropertyMap beanPropertyMap, final Map<String, SettableBeanProperty> map, final Set<String> set, final boolean z, final boolean z2) {
        this(beanDeserializerBuilder, beanDescription, beanDescription.getType(), beanPropertyMap, map, set, z, z2);
    }

    protected BuilderBasedDeserializer(final BuilderBasedDeserializer builderBasedDeserializer) {
        this(builderBasedDeserializer, builderBasedDeserializer._ignoreAllUnknown);
    }

    protected BuilderBasedDeserializer(final BuilderBasedDeserializer builderBasedDeserializer, final boolean z) {
        super(builderBasedDeserializer, z);
        _buildMethod = builderBasedDeserializer._buildMethod;
        _targetType = builderBasedDeserializer._targetType;
    }

    protected BuilderBasedDeserializer(final BuilderBasedDeserializer builderBasedDeserializer, final NameTransformer nameTransformer) {
        super(builderBasedDeserializer, nameTransformer);
        _buildMethod = builderBasedDeserializer._buildMethod;
        _targetType = builderBasedDeserializer._targetType;
    }

    public BuilderBasedDeserializer(final BuilderBasedDeserializer builderBasedDeserializer, final ObjectIdReader objectIdReader) {
        super(builderBasedDeserializer, objectIdReader);
        _buildMethod = builderBasedDeserializer._buildMethod;
        _targetType = builderBasedDeserializer._targetType;
    }

    public BuilderBasedDeserializer(final BuilderBasedDeserializer builderBasedDeserializer, final Set<String> set) {
        this(builderBasedDeserializer, set, builderBasedDeserializer._includableProps);
    }

    public BuilderBasedDeserializer(final BuilderBasedDeserializer builderBasedDeserializer, final Set<String> set, final Set<String> set2) {
        super(builderBasedDeserializer, set, set2);
        _buildMethod = builderBasedDeserializer._buildMethod;
        _targetType = builderBasedDeserializer._targetType;
    }

    public BuilderBasedDeserializer(final BuilderBasedDeserializer builderBasedDeserializer, final BeanPropertyMap beanPropertyMap) {
        super(builderBasedDeserializer, beanPropertyMap);
        _buildMethod = builderBasedDeserializer._buildMethod;
        _targetType = builderBasedDeserializer._targetType;
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public JsonDeserializer<Object> unwrappingDeserializer(final NameTransformer nameTransformer) {
        return new BuilderBasedDeserializer(this, nameTransformer);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public BeanDeserializerBase withObjectIdReader(final ObjectIdReader objectIdReader) {
        return new BuilderBasedDeserializer(this, objectIdReader);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public BeanDeserializerBase withByNameInclusion(final Set<String> set, final Set<String> set2) {
        return new BuilderBasedDeserializer(this, set, set2);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public BeanDeserializerBase withIgnoreAllUnknown(final boolean z) {
        return new BuilderBasedDeserializer(this, z);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public BeanDeserializerBase withBeanProperties(final BeanPropertyMap beanPropertyMap) {
        return new BuilderBasedDeserializer(this, beanPropertyMap);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    protected BeanDeserializerBase asArrayDeserializer() {
        return new BeanAsArrayBuilderDeserializer(this, _targetType, _beanProperties.getPropertiesInInsertionOrder(), _buildMethod);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return Boolean.FALSE;
    }

    protected Object finishBuild(final DeserializationContext deserializationContext, final Object obj) throws IOException {
        final AnnotatedMethod annotatedMethod = _buildMethod;
        if (null == annotatedMethod) {
            return obj;
        }
        try {
            return annotatedMethod.getMember().invoke(obj, null);
        } catch (final Exception e2) {
            return this.wrapInstantiationProblem(e2, deserializationContext);
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.isExpectedStartObjectToken()) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (_vanillaProcessing) {
                return this.finishBuild(deserializationContext, this.vanillaDeserialize(jsonParser, deserializationContext, jsonTokenNextToken));
            }
            return this.finishBuild(deserializationContext, this.deserializeFromObject(jsonParser, deserializationContext));
        }
        switch (jsonParser.currentTokenId()) {
            case 2:
            case 5:
                return this.finishBuild(deserializationContext, this.deserializeFromObject(jsonParser, deserializationContext));
            case 3:
                return this._deserializeFromArray(jsonParser, deserializationContext);
            case 4:
            case 11:
            default:
                return deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
            case 6:
                return this.finishBuild(deserializationContext, this.deserializeFromString(jsonParser, deserializationContext));
            case 7:
                return this.finishBuild(deserializationContext, this.deserializeFromNumber(jsonParser, deserializationContext));
            case 8:
                return this.finishBuild(deserializationContext, this.deserializeFromDouble(jsonParser, deserializationContext));
            case 9:
            case 10:
                return this.finishBuild(deserializationContext, this.deserializeFromBoolean(jsonParser, deserializationContext));
            case 12:
                return jsonParser.getEmbeddedObject();
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        final JavaType javaType = _targetType;
        final Class<?> clsHandledType = this.handledType();
        final Class<?> cls = obj.getClass();
        if (clsHandledType.isAssignableFrom(cls)) {
            return deserializationContext.reportBadDefinition(javaType, String.format("Deserialization of %s by passing existing Builder (%s) instance not supported", javaType, clsHandledType.getName()));
        }
        return deserializationContext.reportBadDefinition(javaType, String.format("Deserialization of %s by passing existing instance (of %s) not supported", javaType, cls.getName()));
    }

    private final Object vanillaDeserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JsonToken jsonToken) throws IOException {
        Object objCreateUsingDefault = _valueInstantiator.createUsingDefault(deserializationContext);
        while (JsonToken.FIELD_NAME == jsonParser.currentToken()) {
            final String strCurrentName = jsonParser.currentName();
            jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
            if (null != settableBeanPropertyFind) {
                try {
                    objCreateUsingDefault = settableBeanPropertyFind.deserializeSetAndReturn(jsonParser, deserializationContext, objCreateUsingDefault);
                } catch (final Exception e2) {
                    this.wrapAndThrow(e2, objCreateUsingDefault, strCurrentName, deserializationContext);
                }
            } else {
                this.handleUnknownVanilla(jsonParser, deserializationContext, objCreateUsingDefault, strCurrentName);
            }
            jsonParser.nextToken();
        }
        return objCreateUsingDefault;
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    public Object deserializeFromObject(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        final Class<?> activeView;
        if (_nonStandardCreation) {
            if (null != this._unwrappedPropertyHandler) {
                return this.deserializeWithUnwrapped(jsonParser, deserializationContext);
            }
            if (null != this._externalTypeIdHandler) {
                return this.deserializeWithExternalTypeId(jsonParser, deserializationContext);
            }
            return this.deserializeFromObjectUsingNonDefault(jsonParser, deserializationContext);
        }
        Object objCreateUsingDefault = _valueInstantiator.createUsingDefault(deserializationContext);
        if (null != this._injectables) {
            this.injectValues(deserializationContext, objCreateUsingDefault);
        }
        if (_needViewProcesing && null != (activeView = deserializationContext.getActiveView())) {
            return this.deserializeWithView(jsonParser, deserializationContext, objCreateUsingDefault, activeView);
        }
        while (JsonToken.FIELD_NAME == jsonParser.currentToken()) {
            final String strCurrentName = jsonParser.currentName();
            jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
            if (null != settableBeanPropertyFind) {
                try {
                    objCreateUsingDefault = settableBeanPropertyFind.deserializeSetAndReturn(jsonParser, deserializationContext, objCreateUsingDefault);
                } catch (final Exception e2) {
                    this.wrapAndThrow(e2, objCreateUsingDefault, strCurrentName, deserializationContext);
                }
            } else {
                this.handleUnknownVanilla(jsonParser, deserializationContext, objCreateUsingDefault, strCurrentName);
            }
            jsonParser.nextToken();
        }
        return objCreateUsingDefault;
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    protected Object _deserializeUsingPropertyBased(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        Object objWrapInstantiationProblem;
        final PropertyBasedCreator propertyBasedCreator = _propertyBasedCreator;
        final PropertyValueBuffer propertyValueBufferStartBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, _objectIdReader);
        final Class<?> activeView = _needViewProcesing ? deserializationContext.getActiveView() : null;
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        TokenBuffer tokenBuffer = null;
        while (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            final String strCurrentName = jsonParser.currentName();
            jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFindCreatorProperty = propertyBasedCreator.findCreatorProperty(strCurrentName);
            if (!propertyValueBufferStartBuilding.readIdProperty(strCurrentName) || null != settableBeanPropertyFindCreatorProperty) {
                if (null != settableBeanPropertyFindCreatorProperty) {
                    if (null != activeView && !settableBeanPropertyFindCreatorProperty.visibleInView(activeView)) {
                        jsonParser.skipChildren();
                    } else if (propertyValueBufferStartBuilding.assignParameter(settableBeanPropertyFindCreatorProperty, settableBeanPropertyFindCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                        jsonParser.nextToken();
                        try {
                            Object objBuild = propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
                            if (objBuild.getClass() != _beanType.getRawClass()) {
                                return this.handlePolymorphic(jsonParser, deserializationContext, objBuild, tokenBuffer);
                            }
                            if (null != tokenBuffer) {
                                objBuild = this.handleUnknownProperties(deserializationContext, objBuild, tokenBuffer);
                            }
                            return this._deserialize(jsonParser, deserializationContext, objBuild);
                        } catch (final Exception e2) {
                            this.wrapAndThrow(e2, _beanType.getRawClass(), strCurrentName, deserializationContext);
                        }
                    } else {
                        continue;
                    }
                } else {
                    final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
                    if (null != settableBeanPropertyFind) {
                        propertyValueBufferStartBuilding.bufferProperty(settableBeanPropertyFind, settableBeanPropertyFind.deserialize(jsonParser, deserializationContext));
                    } else if (IgnorePropertiesUtil.shouldIgnore(strCurrentName, _ignorableProps, _includableProps)) {
                        this.handleIgnoredProperty(jsonParser, deserializationContext, this.handledType(), strCurrentName);
                    } else {
                        final SettableAnyProperty settableAnyProperty = _anySetter;
                        if (null != settableAnyProperty) {
                            propertyValueBufferStartBuilding.bufferAnyProperty(settableAnyProperty, strCurrentName, settableAnyProperty.deserialize(jsonParser, deserializationContext));
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
            objWrapInstantiationProblem = propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
        } catch (final Exception e3) {
            objWrapInstantiationProblem = this.wrapInstantiationProblem(e3, deserializationContext);
        }
        if (null == tokenBuffer) {
            return objWrapInstantiationProblem;
        }
        if (objWrapInstantiationProblem.getClass() != _beanType.getRawClass()) {
            return this.handlePolymorphic(null, deserializationContext, objWrapInstantiationProblem, tokenBuffer);
        }
        return this.handleUnknownProperties(deserializationContext, objWrapInstantiationProblem, tokenBuffer);
    }

    protected final Object _deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, Object obj) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        final Class<?> activeView;
        if (null != this._injectables) {
            this.injectValues(deserializationContext, obj);
        }
        if (null != this._unwrappedPropertyHandler) {
            if (jsonParser.hasToken(JsonToken.START_OBJECT)) {
                jsonParser.nextToken();
            }
            final TokenBuffer tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
            tokenBuffer.writeStartObject();
            return this.deserializeWithUnwrapped(jsonParser, deserializationContext, obj, tokenBuffer);
        }
        if (null != this._externalTypeIdHandler) {
            return this.deserializeWithExternalTypeId(jsonParser, deserializationContext, obj);
        }
        if (_needViewProcesing && null != (activeView = deserializationContext.getActiveView())) {
            return this.deserializeWithView(jsonParser, deserializationContext, obj, activeView);
        }
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (JsonToken.START_OBJECT == jsonTokenCurrentToken) {
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        while (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            final String strCurrentName = jsonParser.currentName();
            jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
            if (null != settableBeanPropertyFind) {
                try {
                    obj = settableBeanPropertyFind.deserializeSetAndReturn(jsonParser, deserializationContext, obj);
                } catch (final Exception e2) {
                    this.wrapAndThrow(e2, obj, strCurrentName, deserializationContext);
                }
            } else {
                this.handleUnknownVanilla(jsonParser, deserializationContext, obj, strCurrentName);
            }
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        return obj;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    protected Object _deserializeFromArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        JsonDeserializer<Object> jsonDeserializer = _arrayDelegateDeserializer;
        if (null != jsonDeserializer || null != (jsonDeserializer = this._delegateDeserializer)) {
            final Object objCreateUsingArrayDelegate = _valueInstantiator.createUsingArrayDelegate(deserializationContext, jsonDeserializer.deserialize(jsonParser, deserializationContext));
            if (null != this._injectables) {
                this.injectValues(deserializationContext, objCreateUsingArrayDelegate);
            }
            return this.finishBuild(deserializationContext, objCreateUsingArrayDelegate);
        }
        final CoercionAction coercionAction_findCoercionFromEmptyArray = this._findCoercionFromEmptyArray(deserializationContext);
        final boolean zIsEnabled = deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        if (zIsEnabled || CoercionAction.Fail != coercionAction_findCoercionFromEmptyArray) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final JsonToken jsonToken = JsonToken.END_ARRAY;
            if (jsonTokenNextToken == jsonToken) {
                final int i2 = C11951.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[coercionAction_findCoercionFromEmptyArray.ordinal()];
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

    /* renamed from: com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer1 */
    enum C11951 {
        ;
        static final int[] SwitchMapcomfasterxmljacksondatabindcfgCoercionAction;

        static {
            final int[] iArr = new int[CoercionAction.values().length];
            SwitchMapcomfasterxmljacksondatabindcfgCoercionAction = iArr;
            try {
                iArr[CoercionAction.AsEmpty.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C11951.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[CoercionAction.AsNull.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C11951.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[CoercionAction.TryConvert.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
        }
    }

    protected final Object deserializeWithView(final JsonParser jsonParser, final DeserializationContext deserializationContext, Object obj, final Class<?> cls) throws IOException {
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        while (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            final String strCurrentName = jsonParser.currentName();
            jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
            if (null != settableBeanPropertyFind) {
                if (!settableBeanPropertyFind.visibleInView(cls)) {
                    jsonParser.skipChildren();
                } else {
                    try {
                        obj = settableBeanPropertyFind.deserializeSetAndReturn(jsonParser, deserializationContext, obj);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, obj, strCurrentName, deserializationContext);
                    }
                }
            } else {
                this.handleUnknownVanilla(jsonParser, deserializationContext, obj, strCurrentName);
            }
            jsonTokenCurrentToken = jsonParser.nextToken();
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
        Object objCreateUsingDefault = _valueInstantiator.createUsingDefault(deserializationContext);
        if (null != this._injectables) {
            this.injectValues(deserializationContext, objCreateUsingDefault);
        }
        final Class<?> activeView = _needViewProcesing ? deserializationContext.getActiveView() : null;
        while (JsonToken.FIELD_NAME == jsonParser.currentToken()) {
            final String strCurrentName = jsonParser.currentName();
            jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
            if (null != settableBeanPropertyFind) {
                if (null != activeView && !settableBeanPropertyFind.visibleInView(activeView)) {
                    jsonParser.skipChildren();
                } else {
                    try {
                        objCreateUsingDefault = settableBeanPropertyFind.deserializeSetAndReturn(jsonParser, deserializationContext, objCreateUsingDefault);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, objCreateUsingDefault, strCurrentName, deserializationContext);
                    }
                }
            } else if (IgnorePropertiesUtil.shouldIgnore(strCurrentName, _ignorableProps, _includableProps)) {
                this.handleIgnoredProperty(jsonParser, deserializationContext, objCreateUsingDefault, strCurrentName);
            } else {
                tokenBuffer.writeFieldName(strCurrentName);
                tokenBuffer.copyCurrentStructure(jsonParser);
                final SettableAnyProperty settableAnyProperty = _anySetter;
                if (null != settableAnyProperty) {
                    try {
                        settableAnyProperty.deserializeAndSet(jsonParser, deserializationContext, objCreateUsingDefault, strCurrentName);
                    } catch (final Exception e3) {
                        this.wrapAndThrow(e3, objCreateUsingDefault, strCurrentName, deserializationContext);
                    }
                }
            }
            jsonParser.nextToken();
        }
        tokenBuffer.writeEndObject();
        return _unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, objCreateUsingDefault, tokenBuffer);
    }

    protected Object deserializeUsingPropertyBasedWithUnwrapped(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
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
                    if (propertyValueBufferStartBuilding.assignParameter(settableBeanPropertyFindCreatorProperty, settableBeanPropertyFindCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                        jsonParser.nextToken();
                        try {
                            final Object objBuild = propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
                            if (objBuild.getClass() != _beanType.getRawClass()) {
                                return this.handlePolymorphic(jsonParser, deserializationContext, objBuild, tokenBuffer);
                            }
                            return this.deserializeWithUnwrapped(jsonParser, deserializationContext, objBuild, tokenBuffer);
                        } catch (final Exception e2) {
                            this.wrapAndThrow(e2, _beanType.getRawClass(), strCurrentName, deserializationContext);
                        }
                    } else {
                        continue;
                    }
                } else {
                    final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
                    if (null != settableBeanPropertyFind) {
                        propertyValueBufferStartBuilding.bufferProperty(settableBeanPropertyFind, settableBeanPropertyFind.deserialize(jsonParser, deserializationContext));
                    } else if (IgnorePropertiesUtil.shouldIgnore(strCurrentName, _ignorableProps, _includableProps)) {
                        this.handleIgnoredProperty(jsonParser, deserializationContext, this.handledType(), strCurrentName);
                    } else {
                        tokenBuffer.writeFieldName(strCurrentName);
                        tokenBuffer.copyCurrentStructure(jsonParser);
                        final SettableAnyProperty settableAnyProperty = _anySetter;
                        if (null != settableAnyProperty) {
                            propertyValueBufferStartBuilding.bufferAnyProperty(settableAnyProperty, strCurrentName, settableAnyProperty.deserialize(jsonParser, deserializationContext));
                        }
                    }
                }
            }
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        tokenBuffer.writeEndObject();
        try {
            return _unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding), tokenBuffer);
        } catch (final Exception e3) {
            return this.wrapInstantiationProblem(e3, deserializationContext);
        }
    }

    protected Object deserializeWithUnwrapped(final JsonParser jsonParser, final DeserializationContext deserializationContext, Object obj, final TokenBuffer tokenBuffer) throws IOException {
        final Class<?> activeView = _needViewProcesing ? deserializationContext.getActiveView() : null;
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        while (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            final String strCurrentName = jsonParser.currentName();
            final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
            jsonParser.nextToken();
            if (null != settableBeanPropertyFind) {
                if (null != activeView && !settableBeanPropertyFind.visibleInView(activeView)) {
                    jsonParser.skipChildren();
                } else {
                    try {
                        obj = settableBeanPropertyFind.deserializeSetAndReturn(jsonParser, deserializationContext, obj);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, obj, strCurrentName, deserializationContext);
                    }
                }
            } else if (IgnorePropertiesUtil.shouldIgnore(strCurrentName, _ignorableProps, _includableProps)) {
                this.handleIgnoredProperty(jsonParser, deserializationContext, obj, strCurrentName);
            } else {
                tokenBuffer.writeFieldName(strCurrentName);
                tokenBuffer.copyCurrentStructure(jsonParser);
                final SettableAnyProperty settableAnyProperty = _anySetter;
                if (null != settableAnyProperty) {
                    settableAnyProperty.deserializeAndSet(jsonParser, deserializationContext, obj, strCurrentName);
                }
            }
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        tokenBuffer.writeEndObject();
        return _unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, obj, tokenBuffer);
    }

    protected Object deserializeWithExternalTypeId(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (null != this._propertyBasedCreator) {
            return this.deserializeUsingPropertyBasedWithExternalTypeId(jsonParser, deserializationContext);
        }
        return this.deserializeWithExternalTypeId(jsonParser, deserializationContext, _valueInstantiator.createUsingDefault(deserializationContext));
    }

    protected Object deserializeWithExternalTypeId(final JsonParser jsonParser, final DeserializationContext deserializationContext, Object obj) throws IOException {
        final Class<?> activeView = _needViewProcesing ? deserializationContext.getActiveView() : null;
        final ExternalTypeHandler externalTypeHandlerStart = _externalTypeIdHandler.start();
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        while (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            final String strCurrentName = jsonParser.currentName();
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFind = _beanProperties.find(strCurrentName);
            if (null != settableBeanPropertyFind) {
                if (jsonTokenNextToken.isScalarValue()) {
                    externalTypeHandlerStart.handleTypePropertyValue(jsonParser, deserializationContext, strCurrentName, obj);
                }
                if (null != activeView && !settableBeanPropertyFind.visibleInView(activeView)) {
                    jsonParser.skipChildren();
                } else {
                    try {
                        obj = settableBeanPropertyFind.deserializeSetAndReturn(jsonParser, deserializationContext, obj);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, obj, strCurrentName, deserializationContext);
                    }
                }
            } else if (IgnorePropertiesUtil.shouldIgnore(strCurrentName, _ignorableProps, _includableProps)) {
                this.handleIgnoredProperty(jsonParser, deserializationContext, obj, strCurrentName);
            } else if (!externalTypeHandlerStart.handlePropertyValue(jsonParser, deserializationContext, strCurrentName, obj)) {
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
        return externalTypeHandlerStart.complete(jsonParser, deserializationContext, obj);
    }

    protected Object deserializeUsingPropertyBasedWithExternalTypeId(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JavaType javaType = _targetType;
        return deserializationContext.reportBadDefinition(javaType, String.format("Deserialization (of %s) with Builder, External type id, @JsonCreator not yet implemented", javaType));
    }
}
