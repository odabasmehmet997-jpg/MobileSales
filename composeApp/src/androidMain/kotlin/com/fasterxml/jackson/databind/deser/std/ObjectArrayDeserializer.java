package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ObjectBuffer;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Set;

public class ObjectArrayDeserializer extends ContainerDeserializerBase<Object[]> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final Class<?> _elementClass;
    protected JsonDeserializer<Object> _elementDeserializer;
    protected final TypeDeserializer _elementTypeDeserializer;
    protected final Object[] _emptyValue;
    protected final boolean _untyped;

    public ObjectArrayDeserializer(final JavaType javaType, final JsonDeserializer<Object> jsonDeserializer, final TypeDeserializer typeDeserializer) {
        super(javaType, null, null);
        final ArrayType arrayType = (ArrayType) javaType;
        final Class<?> rawClass = arrayType.getContentType().getRawClass();
        _elementClass = rawClass;
        _untyped = Object.class == rawClass;
        _elementDeserializer = jsonDeserializer;
        _elementTypeDeserializer = typeDeserializer;
        _emptyValue = arrayType.getEmptyArray();
    }

    protected ObjectArrayDeserializer(final ObjectArrayDeserializer objectArrayDeserializer, final JsonDeserializer<Object> jsonDeserializer, final TypeDeserializer typeDeserializer, final NullValueProvider nullValueProvider, final Boolean bool) {
        super(objectArrayDeserializer, nullValueProvider, bool);
        _elementClass = objectArrayDeserializer._elementClass;
        _untyped = objectArrayDeserializer._untyped;
        _emptyValue = objectArrayDeserializer._emptyValue;
        _elementDeserializer = jsonDeserializer;
        _elementTypeDeserializer = typeDeserializer;
    }

    public ObjectArrayDeserializer withDeserializer(final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) {
        return this.withResolved(typeDeserializer, jsonDeserializer, _nullProvider, _unwrapSingle);
    }

    public ObjectArrayDeserializer withResolved(final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer, final NullValueProvider nullValueProvider, final Boolean bool) {
        return (Objects.equals(bool, _unwrapSingle) && nullValueProvider == _nullProvider && jsonDeserializer == _elementDeserializer && typeDeserializer == _elementTypeDeserializer) ? this : new ObjectArrayDeserializer(this, (JsonDeserializer<Object>) jsonDeserializer, typeDeserializer, nullValueProvider, bool);
    }

    
    public boolean isCachable() {
        return null == this._elementDeserializer && null == this._elementTypeDeserializer;
    }

    
    public LogicalType logicalType() {
        return LogicalType.Array;
    }

    
    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        final JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization;
        final JsonDeserializer<?> jsonDeserializer = _elementDeserializer;
        final Boolean boolFindFormatFeature = this.findFormatFeature(deserializationContext, beanProperty, _containerType.getRawClass(), JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        final JsonDeserializer<?> jsonDeserializerFindConvertingContentDeserializer = this.findConvertingContentDeserializer(deserializationContext, beanProperty, jsonDeserializer);
        final JavaType contentType = _containerType.getContentType();
        if (null == jsonDeserializerFindConvertingContentDeserializer) {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(contentType, beanProperty);
        } else {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializerFindConvertingContentDeserializer, beanProperty, contentType);
        }
        TypeDeserializer typeDeserializerForProperty = _elementTypeDeserializer;
        if (null != typeDeserializerForProperty) {
            typeDeserializerForProperty = typeDeserializerForProperty.forProperty(beanProperty);
        }
        return this.withResolved(typeDeserializerForProperty, jsonDeserializerHandleSecondaryContextualization, this.findContentNullProvider(deserializationContext, beanProperty, jsonDeserializerHandleSecondaryContextualization), boolFindFormatFeature);
    }
    public JsonDeserializer<Object> getContentDeserializer() {
        return _elementDeserializer;
    }
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.CONSTANT;
    }
    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return _emptyValue;
    }

    
    public Object[] deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final Object[] objArrCompleteAndClearBuffer;
        Object objDeserializeWithType = null;
        int i2 = 0;
        if (!jsonParser.isExpectedStartArrayToken()) {
            return this.handleNonArray(jsonParser, deserializationContext);
        }
        final ObjectBuffer objectBufferLeaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] objArrResetAndStart = objectBufferLeaseObjectBuffer.resetAndStart();
        final TypeDeserializer typeDeserializer = _elementTypeDeserializer;
        int i3 = 0;
        while (true) {
            Exception e;
            try {
                final JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (JsonToken.END_ARRAY == jsonTokenNextToken) {
                    break;
                }
                try {
                    if (JsonToken.VALUE_NULL == jsonTokenNextToken) {
                        if (!_skipNullValues) {
                            objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
                        }
                    } else if (null == typeDeserializer) {
                        objDeserializeWithType = _elementDeserializer.deserialize(jsonParser, deserializationContext);
                    } else {
                        objDeserializeWithType = _elementDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                    }
                    objArrResetAndStart[i3] = objDeserializeWithType;
                    i3 = i2;
                } catch (final Exception e2) {
                    e = e2;
                    i3 = i2;
                    throw JsonMappingException.wrapWithPath(e, objArrResetAndStart, objectBufferLeaseObjectBuffer.bufferedSize() + i3);
                }
                if (i3 >= objArrResetAndStart.length) {
                    objArrResetAndStart = objectBufferLeaseObjectBuffer.appendCompletedChunk(objArrResetAndStart);
                    i3 = 0;
                }
                i2 = i3 + 1;
            } catch (final Exception e3) {
                e = e3;
            }
        }
        if (_untyped) {
            objArrCompleteAndClearBuffer = objectBufferLeaseObjectBuffer.completeAndClearBuffer(objArrResetAndStart, i3);
        } else {
            objArrCompleteAndClearBuffer = objectBufferLeaseObjectBuffer.completeAndClearBuffer(objArrResetAndStart, i3, _elementClass);
        }
        deserializationContext.returnObjectBuffer(objectBufferLeaseObjectBuffer);
        return objArrCompleteAndClearBuffer;
    }
    public Object[] deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return (Object[]) typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    @Override
    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }


    public Object[] deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object[] objArr) throws IOException {
        final Object[] objArrCompleteAndClearBuffer;
        Object objDeserializeWithType = null;
        int i2 = 0;
        if (!jsonParser.isExpectedStartArrayToken()) {
            final Object[] objArrHandleNonArray = this.handleNonArray(jsonParser, deserializationContext);
            if (null == objArrHandleNonArray) {
                return objArr;
            }
            final int length = objArr.length;
            final Object[] objArr2 = new Object[objArrHandleNonArray.length + length];
            System.arraycopy(objArr, 0, objArr2, 0, length);
            System.arraycopy(objArrHandleNonArray, 0, objArr2, length, objArrHandleNonArray.length);
            return objArr2;
        }
        final ObjectBuffer objectBufferLeaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        int length2 = objArr.length;
        Object[] objArrResetAndStart = objectBufferLeaseObjectBuffer.resetAndStart(objArr, length2);
        final TypeDeserializer typeDeserializer = _elementTypeDeserializer;
        while (true) {
            Exception e;
            try {
                final JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (JsonToken.END_ARRAY == jsonTokenNextToken) {
                    break;
                }
                try {
                    if (JsonToken.VALUE_NULL == jsonTokenNextToken) {
                        if (!_skipNullValues) {
                            objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
                        }
                    } else if (null == typeDeserializer) {
                        objDeserializeWithType = _elementDeserializer.deserialize(jsonParser, deserializationContext);
                    } else {
                        objDeserializeWithType = _elementDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                    }
                    objArrResetAndStart[length2] = objDeserializeWithType;
                    length2 = i2;
                } catch (final Exception e2) {
                    e = e2;
                    length2 = i2;
                    throw JsonMappingException.wrapWithPath(e, objArrResetAndStart, objectBufferLeaseObjectBuffer.bufferedSize() + length2);
                }
                if (length2 >= objArrResetAndStart.length) {
                    objArrResetAndStart = objectBufferLeaseObjectBuffer.appendCompletedChunk(objArrResetAndStart);
                    length2 = 0;
                }
                i2 = length2 + 1;
            } catch (final Exception e3) {
                e = e3;
            }
        }
        if (_untyped) {
            objArrCompleteAndClearBuffer = objectBufferLeaseObjectBuffer.completeAndClearBuffer(objArrResetAndStart, length2);
        } else {
            objArrCompleteAndClearBuffer = objectBufferLeaseObjectBuffer.completeAndClearBuffer(objArrResetAndStart, length2, _elementClass);
        }
        deserializationContext.returnObjectBuffer(objectBufferLeaseObjectBuffer);
        return objArrCompleteAndClearBuffer;
    }

    protected Byte[] deserializeFromBase64(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final byte[] binaryValue = jsonParser.getBinaryValue(deserializationContext.getBase64Variant());
        final Byte[] bArr = new Byte[binaryValue.length];
        final int length = binaryValue.length;
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = Byte.valueOf(binaryValue[i2]);
        }
        return bArr;
    }

    protected Object[] handleNonArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final Object objDeserializeWithType;
        final Object[] objArr;
        final Boolean bool = _unwrapSingle;
        if (bool != Boolean.TRUE && (null != bool || !deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
                if (Byte.class == this._elementClass) {
                    return this.deserializeFromBase64(jsonParser, deserializationContext);
                }
                return this._deserializeFromString(jsonParser, deserializationContext);
            }
            return (Object[]) deserializationContext.handleUnexpectedToken(_containerType, jsonParser);
        }
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            if (_skipNullValues) {
                return _emptyValue;
            }
            objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
        } else {
            final TypeDeserializer typeDeserializer = _elementTypeDeserializer;
            if (null == typeDeserializer) {
                objDeserializeWithType = _elementDeserializer.deserialize(jsonParser, deserializationContext);
            } else {
                objDeserializeWithType = _elementDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
        }
        if (_untyped) {
            objArr = new Object[1];
        } else {
            objArr = (Object[]) Array.newInstance(_elementClass, 1);
        }
        objArr[0] = objDeserializeWithType;
        return objArr;
    }
}
