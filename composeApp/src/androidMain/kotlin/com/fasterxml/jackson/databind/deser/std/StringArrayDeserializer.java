package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ObjectBuffer;

import java.io.IOException;
import java.util.Objects;

public final class StringArrayDeserializer extends StdDeserializer<String[]> implements ContextualDeserializer {
    private static final String[] NO_STRINGS = new String[0];
    public static final StringArrayDeserializer instance = new StringArrayDeserializer();
    private static final long serialVersionUID = 2;
    private final JsonDeserializer<String> _elementDeserializer;
    private final NullValueProvider _nullProvider;
    private final boolean _skipNullValues;
    private final Boolean _unwrapSingle;

    public StringArrayDeserializer() {
        this(null, null, null);
    }
    private StringArrayDeserializer(final JsonDeserializer<?> jsonDeserializer, final NullValueProvider nullValueProvider, final Boolean bool) {
        super(String[].class);
        _elementDeserializer = (JsonDeserializer<String>) jsonDeserializer;
        _nullProvider = nullValueProvider;
        _unwrapSingle = bool;
        _skipNullValues = NullsConstantProvider.isSkipper(nullValueProvider);
    }       
    public LogicalType logicalType() {
        return LogicalType.Array;
    }
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return Boolean.TRUE;
    }
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.CONSTANT;
    }
    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return StringArrayDeserializer.NO_STRINGS;
    }
    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization;
        final JsonDeserializer<?> jsonDeserializerFindConvertingContentDeserializer = this.findConvertingContentDeserializer(deserializationContext, beanProperty, _elementDeserializer);
        final JavaType javaTypeConstructType = deserializationContext.constructType(String.class);
        if (null == jsonDeserializerFindConvertingContentDeserializer) {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(javaTypeConstructType, beanProperty);
        } else {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializerFindConvertingContentDeserializer, beanProperty, javaTypeConstructType);
        }
        final Boolean boolFindFormatFeature = this.findFormatFeature(deserializationContext, beanProperty, String[].class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        final NullValueProvider nullValueProviderFindContentNullProvider = this.findContentNullProvider(deserializationContext, beanProperty, jsonDeserializerHandleSecondaryContextualization);
        if (null != jsonDeserializerHandleSecondaryContextualization && this.isDefaultDeserializer(jsonDeserializerHandleSecondaryContextualization)) {
            jsonDeserializerHandleSecondaryContextualization = null;
        }
        return (_elementDeserializer == jsonDeserializerHandleSecondaryContextualization && Objects.equals(_unwrapSingle, boolFindFormatFeature) && _nullProvider == nullValueProviderFindContentNullProvider) ? this : new StringArrayDeserializer(jsonDeserializerHandleSecondaryContextualization, nullValueProviderFindContentNullProvider, boolFindFormatFeature);
    }
    public String[] deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        String strNextTextValue = "";
        int i2;
        if (!jsonParser.isExpectedStartArrayToken()) {
            return this.handleNonArray(jsonParser, deserializationContext);
        }
        if (null != this._elementDeserializer) {
            return this._deserializeCustom(jsonParser, deserializationContext, null);
        }
        final ObjectBuffer objectBufferLeaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] objArrResetAndStart = objectBufferLeaseObjectBuffer.resetAndStart();
        int i3 = 0;
        while (true) {
            Exception e;
            try {
                strNextTextValue = jsonParser.nextTextValue();
            } catch (final Exception e2) {
                e = e2;
            }
            try {
                if (null == strNextTextValue) {
                    final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
                    if (JsonToken.END_ARRAY != jsonTokenCurrentToken) {
                        if (JsonToken.VALUE_NULL == jsonTokenCurrentToken) {
                            if (!_skipNullValues) {
                                strNextTextValue = (String) _nullProvider.getNullValue(deserializationContext);
                            }
                        } else {
                            strNextTextValue = this._parseString(jsonParser, deserializationContext);
                        }
                    } else {
                        final String[] strArr = objectBufferLeaseObjectBuffer.completeAndClearBuffer(objArrResetAndStart, i3, String.class);
                        deserializationContext.returnObjectBuffer(objectBufferLeaseObjectBuffer);
                        return strArr;
                    }
                }
                objArrResetAndStart[i3] = strNextTextValue;
                i3 = i2;
            } catch (final Exception e3) {
                e = e3;
                i3 = i2;
                throw JsonMappingException.wrapWithPath(e, objArrResetAndStart, objectBufferLeaseObjectBuffer.bufferedSize() + i3);
            }
            if (i3 >= objArrResetAndStart.length) {
                objArrResetAndStart = objectBufferLeaseObjectBuffer.appendCompletedChunk(objArrResetAndStart);
                i3 = 0;
            }
            i2 = i3 + 1;
        }
    }
    private String[] _deserializeCustom(final JsonParser jsonParser, final DeserializationContext deserializationContext, final String[] strArr) throws IOException {
        int length;
        Object[] objArrResetAndStart;
        String strDeserialize = "";
        int i2 = 0;
        final ObjectBuffer objectBufferLeaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        if (null == strArr) {
            objArrResetAndStart = objectBufferLeaseObjectBuffer.resetAndStart();
            length = 0;
        } else {
            length = strArr.length;
            objArrResetAndStart = objectBufferLeaseObjectBuffer.resetAndStart(strArr, length);
        }
        final JsonDeserializer<String> jsonDeserializer = _elementDeserializer;
        while (true) {
            Exception e;
            try {
                try {
                    if (null == jsonParser.nextTextValue()) {
                        final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
                        if (JsonToken.END_ARRAY != jsonTokenCurrentToken) {
                            if (JsonToken.VALUE_NULL == jsonTokenCurrentToken) {
                                if (!_skipNullValues) {
                                    strDeserialize = (String) _nullProvider.getNullValue(deserializationContext);
                                }
                            } else {
                                strDeserialize = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                            }
                        } else {
                            final String[] strArr2 = objectBufferLeaseObjectBuffer.completeAndClearBuffer(objArrResetAndStart, length, String.class);
                            deserializationContext.returnObjectBuffer(objectBufferLeaseObjectBuffer);
                            return strArr2;
                        }
                    } else {
                        strDeserialize = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                    }
                    objArrResetAndStart[length] = strDeserialize;
                    length = i2;
                } catch (final Exception e2) {
                    e = e2;
                    length = i2;
                    throw JsonMappingException.wrapWithPath(e, String.class, length);
                }
                if (length >= objArrResetAndStart.length) {
                    objArrResetAndStart = objectBufferLeaseObjectBuffer.appendCompletedChunk(objArrResetAndStart);
                    length = 0;
                }
                i2 = length + 1;
            } catch (final Exception e3) {
                e = e3;
            }
        }
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }
    public String[] deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final String[] strArr) throws IOException {
        String strNextTextValue;
        int i2 = 0;
        if (!jsonParser.isExpectedStartArrayToken()) {
            final String[] strArrHandleNonArray = this.handleNonArray(jsonParser, deserializationContext);
            if (null == strArrHandleNonArray) {
                return strArr;
            }
            final int length = strArr.length;
            final String[] strArr2 = new String[strArrHandleNonArray.length + length];
            System.arraycopy(strArr, 0, strArr2, 0, length);
            System.arraycopy(strArrHandleNonArray, 0, strArr2, length, strArrHandleNonArray.length);
            return strArr2;
        }
        if (null != this._elementDeserializer) {
            return this._deserializeCustom(jsonParser, deserializationContext, strArr);
        }
        final ObjectBuffer objectBufferLeaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        int length2 = strArr.length;
        Object[] objArrResetAndStart = objectBufferLeaseObjectBuffer.resetAndStart(strArr, length2);
        while (true) {
            try {
                strNextTextValue = jsonParser.nextTextValue();
                if (null == strNextTextValue) {
                    final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
                    if (JsonToken.END_ARRAY != jsonTokenCurrentToken) {
                        if (JsonToken.VALUE_NULL == jsonTokenCurrentToken) {
                            if (_skipNullValues) {
                                return StringArrayDeserializer.NO_STRINGS;
                            }
                            strNextTextValue = (String) _nullProvider.getNullValue(deserializationContext);
                        } else {
                            strNextTextValue = this._parseString(jsonParser, deserializationContext);
                        }
                    } else {
                        final String[] strArr3 = objectBufferLeaseObjectBuffer.completeAndClearBuffer(objArrResetAndStart, length2, String.class);
                        deserializationContext.returnObjectBuffer(objectBufferLeaseObjectBuffer);
                        return strArr3;
                    }
                }
                if (length2 >= objArrResetAndStart.length) {
                    objArrResetAndStart = objectBufferLeaseObjectBuffer.appendCompletedChunk(objArrResetAndStart);
                    length2 = 0;
                }
                i2 = length2 + 1;
            } catch (final Exception e2) {
                e = e2;
            }
            try {
                objArrResetAndStart[length2] = strNextTextValue;
                length2 = i2;
            } catch (final Exception e3) {
                e = e3;
                length2 = i2;
                throw JsonMappingException.wrapWithPath(e, objArrResetAndStart, objectBufferLeaseObjectBuffer.bufferedSize() + length2);
            }
        }
    }

    private String[] handleNonArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final String str_parseString;
        final Boolean bool = _unwrapSingle;
        if (bool == Boolean.TRUE || (null == bool && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
                str_parseString = (String) _nullProvider.getNullValue(deserializationContext);
            } else {
                str_parseString = this._parseString(jsonParser, deserializationContext);
            }
            return new String[]{str_parseString};
        }
        if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            return this._deserializeFromString(jsonParser, deserializationContext);
        }
        return (String[]) deserializationContext.handleUnexpectedToken(_valueClass, jsonParser);
    }
}
