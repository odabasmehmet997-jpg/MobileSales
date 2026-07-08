package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;

import java.io.IOException;
import java.util.Set;

public class BeanAsArrayBuilderDeserializer extends BeanDeserializerBase {
    private static final long serialVersionUID = 1;
    protected final AnnotatedMethod _buildMethod;
    protected final BeanDeserializerBase _delegate;
    protected final SettableBeanProperty[] _orderedProperties;
    protected final JavaType _targetType;
    protected BeanDeserializerBase asArrayDeserializer() {
        return this;
    }
    public BeanAsArrayBuilderDeserializer(final BeanDeserializerBase beanDeserializerBase, final JavaType javaType, final SettableBeanProperty[] settableBeanPropertyArr, final AnnotatedMethod annotatedMethod) {
        super(beanDeserializerBase);
        _delegate = beanDeserializerBase;
        _targetType = javaType;
        _orderedProperties = settableBeanPropertyArr;
        _buildMethod = annotatedMethod;
    }
    public JsonDeserializer<Object> unwrappingDeserializer(final NameTransformer nameTransformer) {
        return _delegate.unwrappingDeserializer(nameTransformer);
    }
    public BeanDeserializerBase withObjectIdReader(final ObjectIdReader objectIdReader) {
        return new BeanAsArrayBuilderDeserializer(_delegate.withObjectIdReader(objectIdReader), _targetType, _orderedProperties, _buildMethod);
    }
    public BeanDeserializerBase withByNameInclusion(final Set<String> set, final Set<String> set2) {
        return new BeanAsArrayBuilderDeserializer(_delegate.withByNameInclusion(set, set2), _targetType, _orderedProperties, _buildMethod);
    }
    public BeanDeserializerBase withIgnoreAllUnknown(final boolean z) {
        return new BeanAsArrayBuilderDeserializer(_delegate.withIgnoreAllUnknown(z), _targetType, _orderedProperties, _buildMethod);
    }
    public BeanDeserializerBase withBeanProperties(final BeanPropertyMap beanPropertyMap) {
        return new BeanAsArrayBuilderDeserializer(_delegate.withBeanProperties(beanPropertyMap), _targetType, _orderedProperties, _buildMethod);
    }
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return Boolean.FALSE;
    }
    protected final Object finishBuild(final DeserializationContext deserializationContext, final Object obj) throws IOException {
        try {
            return _buildMethod.getMember().invoke(obj, null);
        } catch (final Exception e2) {
            return this.wrapInstantiationProblem(e2, deserializationContext);
        }
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return this.finishBuild(deserializationContext, this._deserializeFromNonArray(jsonParser, deserializationContext));
        }
        if (!_vanillaProcessing) {
            return this.finishBuild(deserializationContext, this._deserializeNonVanilla(jsonParser, deserializationContext));
        }
        Object objCreateUsingDefault = _valueInstantiator.createUsingDefault(deserializationContext);
        final SettableBeanProperty[] settableBeanPropertyArr = _orderedProperties;
        final int length = settableBeanPropertyArr.length;
        int i2 = 0;
        while (JsonToken.END_ARRAY != jsonParser.nextToken()) {
            if (i2 != length) {
                final SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i2];
                if (null != settableBeanProperty) {
                    try {
                        objCreateUsingDefault = settableBeanProperty.deserializeSetAndReturn(jsonParser, deserializationContext, objCreateUsingDefault);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, objCreateUsingDefault, settableBeanProperty.getName(), deserializationContext);
                    }
                } else {
                    jsonParser.skipChildren();
                }
                i2++;
            } else {
                if (!_ignoreAllUnknown && deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
                    deserializationContext.reportInputMismatch(this.handledType(), "Unexpected JSON values; expected at most %d properties (in JSON Array)", Integer.valueOf(length));
                }
                while (JsonToken.END_ARRAY != jsonParser.nextToken()) {
                    jsonParser.skipChildren();
                }
                return this.finishBuild(deserializationContext, objCreateUsingDefault);
            }
        }
        return this.finishBuild(deserializationContext, objCreateUsingDefault);
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        return _delegate.deserialize(jsonParser, deserializationContext, obj);
    }
    public Object deserializeFromObject(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return this._deserializeFromNonArray(jsonParser, deserializationContext);
    }
    protected Object _deserializeNonVanilla(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        if (_nonStandardCreation) {
            return this.deserializeFromObjectUsingNonDefault(jsonParser, deserializationContext);
        }
        final Object objCreateUsingDefault = _valueInstantiator.createUsingDefault(deserializationContext);
        if (null != this._injectables) {
            this.injectValues(deserializationContext, objCreateUsingDefault);
        }
        final Class<?> activeView = _needViewProcesing ? deserializationContext.getActiveView() : null;
        final SettableBeanProperty[] settableBeanPropertyArr = _orderedProperties;
        final int length = settableBeanPropertyArr.length;
        int i2 = 0;
        while (true) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final JsonToken jsonToken = JsonToken.END_ARRAY;
            if (jsonTokenNextToken == jsonToken) {
                return objCreateUsingDefault;
            }
            if (i2 != length) {
                final SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i2];
                i2++;
                if (null != settableBeanProperty && (null == activeView || settableBeanProperty.visibleInView(activeView))) {
                    try {
                        settableBeanProperty.deserializeSetAndReturn(jsonParser, deserializationContext, objCreateUsingDefault);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, objCreateUsingDefault, settableBeanProperty.getName(), deserializationContext);
                    }
                } else {
                    jsonParser.skipChildren();
                }
            } else {
                if (!_ignoreAllUnknown && deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
                    deserializationContext.reportWrongTokenException(this, jsonToken, "Unexpected JSON value(s); expected at most %d properties (in JSON Array)", Integer.valueOf(length));
                }
                while (JsonToken.END_ARRAY != jsonParser.nextToken()) {
                    jsonParser.skipChildren();
                }
                return objCreateUsingDefault;
            }
        }
    }
    protected final Object _deserializeUsingPropertyBased(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final PropertyBasedCreator propertyBasedCreator = _propertyBasedCreator;
        final PropertyValueBuffer propertyValueBufferStartBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, _objectIdReader);
        final SettableBeanProperty[] settableBeanPropertyArr = _orderedProperties;
        final int length = settableBeanPropertyArr.length;
        final Class<?> activeView = _needViewProcesing ? deserializationContext.getActiveView() : null;
        int i2 = 0;
        Object objDeserializeSetAndReturn = null;
        while (JsonToken.END_ARRAY != jsonParser.nextToken()) {
            final SettableBeanProperty settableBeanProperty = i2 < length ? settableBeanPropertyArr[i2] : null;
            if (null == settableBeanProperty) {
                jsonParser.skipChildren();
            } else if (null != activeView && !settableBeanProperty.visibleInView(activeView)) {
                jsonParser.skipChildren();
            } else if (null != objDeserializeSetAndReturn) {
                try {
                    objDeserializeSetAndReturn = settableBeanProperty.deserializeSetAndReturn(jsonParser, deserializationContext, objDeserializeSetAndReturn);
                } catch (final Exception e2) {
                    this.wrapAndThrow(e2, objDeserializeSetAndReturn, settableBeanProperty.getName(), deserializationContext);
                }
            } else {
                final String name = settableBeanProperty.getName();
                final SettableBeanProperty settableBeanPropertyFindCreatorProperty = propertyBasedCreator.findCreatorProperty(name);
                if (!propertyValueBufferStartBuilding.readIdProperty(name) || null != settableBeanPropertyFindCreatorProperty) {
                    if (null != settableBeanPropertyFindCreatorProperty) {
                        if (propertyValueBufferStartBuilding.assignParameter(settableBeanPropertyFindCreatorProperty, settableBeanPropertyFindCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                            try {
                                objDeserializeSetAndReturn = propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
                                if (objDeserializeSetAndReturn.getClass() != _beanType.getRawClass()) {
                                    final JavaType javaType = _beanType;
                                    return deserializationContext.reportBadDefinition(javaType, String.format("Cannot support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type %s, actual type %s", ClassUtil.getTypeDescription(javaType), objDeserializeSetAndReturn.getClass().getName()));
                                }
                            } catch (final Exception e3) {
                                this.wrapAndThrow(e3, _beanType.getRawClass(), name, deserializationContext);
                            }
                        } else {
                            continue;
                        }
                    } else {
                        propertyValueBufferStartBuilding.bufferProperty(settableBeanProperty, settableBeanProperty.deserialize(jsonParser, deserializationContext));
                    }
                }
            }
            i2++;
        }
        if (null != objDeserializeSetAndReturn) {
            return objDeserializeSetAndReturn;
        }
        try {
            return propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
        } catch (final Exception e4) {
            return this.wrapInstantiationProblem(e4, deserializationContext);
        }
    }
    protected Object _deserializeFromNonArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser.currentToken(), jsonParser, "Cannot deserialize a POJO (of type %s) from non-Array representation (token: %s): type/property designed to be serialized as JSON Array", _beanType.getRawClass().getName(), jsonParser.currentToken());
    }
}
