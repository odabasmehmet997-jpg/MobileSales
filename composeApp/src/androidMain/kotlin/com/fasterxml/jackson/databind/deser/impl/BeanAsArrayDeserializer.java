package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.Set;

public class BeanAsArrayDeserializer extends BeanDeserializerBase {
    private static final long serialVersionUID = 1;
    protected final BeanDeserializerBase _delegate;
    protected final SettableBeanProperty[] _orderedProperties;
    protected BeanDeserializerBase asArrayDeserializer() {
        return this;
    }
    public BeanAsArrayDeserializer(final BeanDeserializerBase beanDeserializerBase, final SettableBeanProperty[] settableBeanPropertyArr) {
        super(beanDeserializerBase);
        _delegate = beanDeserializerBase;
        _orderedProperties = settableBeanPropertyArr;
    }
    public JsonDeserializer<Object> unwrappingDeserializer(final NameTransformer nameTransformer) {
        return _delegate.unwrappingDeserializer(nameTransformer);
    }
    public BeanDeserializerBase withObjectIdReader(final ObjectIdReader objectIdReader) {
        return new BeanAsArrayDeserializer(_delegate.withObjectIdReader(objectIdReader), _orderedProperties);
    }
    public BeanDeserializerBase withByNameInclusion(final Set<String> set, final Set<String> set2) {
        return new BeanAsArrayDeserializer(_delegate.withByNameInclusion(set, set2), _orderedProperties);
    }
    public BeanDeserializerBase withIgnoreAllUnknown(final boolean z) {
        return new BeanAsArrayDeserializer(_delegate.withIgnoreAllUnknown(z), _orderedProperties);
    }
    public BeanDeserializerBase withBeanProperties(final BeanPropertyMap beanPropertyMap) {
        return new BeanAsArrayDeserializer(_delegate.withBeanProperties(beanPropertyMap), _orderedProperties);
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return this._deserializeFromNonArray(jsonParser, deserializationContext);
        }
        if (!_vanillaProcessing) {
            return this._deserializeNonVanilla(jsonParser, deserializationContext);
        }
        final Object objCreateUsingDefault = _valueInstantiator.createUsingDefault(deserializationContext);
        jsonParser.setCurrentValue(objCreateUsingDefault);
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
                if (null != settableBeanProperty) {
                    try {
                        settableBeanProperty.deserializeAndSet(jsonParser, deserializationContext, objCreateUsingDefault);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, objCreateUsingDefault, settableBeanProperty.getName(), deserializationContext);
                    }
                } else {
                    jsonParser.skipChildren();
                }
                i2++;
            } else {
                if (!_ignoreAllUnknown && deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
                    deserializationContext.reportWrongTokenException(this, jsonToken, "Unexpected JSON values; expected at most %d properties (in JSON Array)", Integer.valueOf(length));
                }
                do {
                    jsonParser.skipChildren();
                } while (JsonToken.END_ARRAY != jsonParser.nextToken());
                return objCreateUsingDefault;
            }
        }
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        jsonParser.setCurrentValue(obj);
        if (!jsonParser.isExpectedStartArrayToken()) {
            return this._deserializeFromNonArray(jsonParser, deserializationContext);
        }
        if (null != this._injectables) {
            this.injectValues(deserializationContext, obj);
        }
        final SettableBeanProperty[] settableBeanPropertyArr = _orderedProperties;
        final int length = settableBeanPropertyArr.length;
        int i2 = 0;
        while (true) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final JsonToken jsonToken = JsonToken.END_ARRAY;
            if (jsonTokenNextToken == jsonToken) {
                return obj;
            }
            if (i2 != length) {
                final SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i2];
                if (null != settableBeanProperty) {
                    try {
                        settableBeanProperty.deserializeAndSet(jsonParser, deserializationContext, obj);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, obj, settableBeanProperty.getName(), deserializationContext);
                    }
                } else {
                    jsonParser.skipChildren();
                }
                i2++;
            } else {
                if (!_ignoreAllUnknown && deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
                    deserializationContext.reportWrongTokenException(this, jsonToken, "Unexpected JSON values; expected at most %d properties (in JSON Array)", Integer.valueOf(length));
                }
                do {
                    jsonParser.skipChildren();
                } while (JsonToken.END_ARRAY != jsonParser.nextToken());
                return obj;
            }
        }
    }
    public Object deserializeFromObject(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return this._deserializeFromNonArray(jsonParser, deserializationContext);
    }
    protected Object _deserializeNonVanilla(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        if (_nonStandardCreation) {
            return this.deserializeFromObjectUsingNonDefault(jsonParser, deserializationContext);
        }
        final Object objCreateUsingDefault = _valueInstantiator.createUsingDefault(deserializationContext);
        jsonParser.setCurrentValue(objCreateUsingDefault);
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
                        settableBeanProperty.deserializeAndSet(jsonParser, deserializationContext, objCreateUsingDefault);
                    } catch (final Exception e2) {
                        this.wrapAndThrow(e2, objCreateUsingDefault, settableBeanProperty.getName(), deserializationContext);
                    }
                } else {
                    jsonParser.skipChildren();
                }
            } else {
                if (!_ignoreAllUnknown) {
                    deserializationContext.reportWrongTokenException(this, jsonToken, "Unexpected JSON values; expected at most %d properties (in JSON Array)", Integer.valueOf(length));
                }
                do {
                    jsonParser.skipChildren();
                } while (JsonToken.END_ARRAY != jsonParser.nextToken());
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
        Object objBuild = null;
        while (JsonToken.END_ARRAY != jsonParser.nextToken()) {
            final SettableBeanProperty settableBeanProperty = i2 < length ? settableBeanPropertyArr[i2] : null;
            if (null == settableBeanProperty) {
                jsonParser.skipChildren();
            } else if (null != activeView && !settableBeanProperty.visibleInView(activeView)) {
                jsonParser.skipChildren();
            } else if (null != objBuild) {
                try {
                    settableBeanProperty.deserializeAndSet(jsonParser, deserializationContext, objBuild);
                } catch (final Exception e2) {
                    this.wrapAndThrow(e2, objBuild, settableBeanProperty.getName(), deserializationContext);
                }
            } else {
                final String name = settableBeanProperty.getName();
                final SettableBeanProperty settableBeanPropertyFindCreatorProperty = propertyBasedCreator.findCreatorProperty(name);
                if (!propertyValueBufferStartBuilding.readIdProperty(name) || null != settableBeanPropertyFindCreatorProperty) {
                    if (null != settableBeanPropertyFindCreatorProperty) {
                        if (propertyValueBufferStartBuilding.assignParameter(settableBeanPropertyFindCreatorProperty, settableBeanPropertyFindCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                            try {
                                objBuild = propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
                                jsonParser.setCurrentValue(objBuild);
                                if (objBuild.getClass() != _beanType.getRawClass()) {
                                    final JavaType javaType = _beanType;
                                    deserializationContext.reportBadDefinition(javaType, String.format("Cannot support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type %s, actual type %s", ClassUtil.getTypeDescription(javaType), ClassUtil.getClassDescription(objBuild)));
                                }
                            } catch (final Exception e3) {
                                this.wrapAndThrow(e3, _beanType.getRawClass(), name, deserializationContext);
                            }
                        }
                    } else {
                        propertyValueBufferStartBuilding.bufferProperty(settableBeanProperty, settableBeanProperty.deserialize(jsonParser, deserializationContext));
                    }
                }
            }
            i2++;
        }
        if (null != objBuild) {
            return objBuild;
        }
        try {
            return propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
        } catch (final Exception e4) {
            return this.wrapInstantiationProblem(e4, deserializationContext);
        }
    }
    protected Object _deserializeFromNonArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser.currentToken(), jsonParser, "Cannot deserialize a POJO (of type %s) from non-Array representation (token: %s): type/property designed to be serialized as JSON Array", ClassUtil.getTypeDescription(_beanType), jsonParser.currentToken());
    }
}
