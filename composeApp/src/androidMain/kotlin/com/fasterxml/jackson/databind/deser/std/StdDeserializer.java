package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.NullsAsEmptyProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsFailProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;


public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Class<?> _valueClass;
    protected final JavaType _valueType;
    protected static final int F_MASK_INT_COERCIONS = DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.getMask() | DeserializationFeature.USE_LONG_FOR_INTS.getMask();
    protected static final int F_MASK_ACCEPT_ARRAYS = DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS.getMask() | DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT.getMask();
    protected static final boolean _neitherNull(final Object obj, final Object obj2) {
        return null != obj && null != obj2;
    }
    protected final boolean _byteOverflow(final int i2) {
        return -128 > i2 || 255 < i2;
    }
    protected final boolean _intOverflow(final long j2) {
        return -2147483648L > j2 || 2147483647L < j2;
    }
    protected final boolean _shortOverflow(final int i2) {
        return -32768 > i2 || 32767 < i2;
    }
    public ValueInstantiator getValueInstantiator() {
        return null;
    }
    protected StdDeserializer(final Class<?> cls) {
        _valueClass = cls;
        _valueType = null;
    }
    protected StdDeserializer(final JavaType javaType) {
        _valueClass = null == javaType ? Object.class : javaType.getRawClass();
        _valueType = javaType;
    }
    protected StdDeserializer(final StdDeserializer<?> stdDeserializer) {
        _valueClass = stdDeserializer._valueClass;
        _valueType = stdDeserializer._valueType;
    }
    public Class<?> handledType() {
        return _valueClass;
    }
    public final Class<?> getValueClass() {
        return _valueClass;
    }
    public JavaType getValueType() {
        return _valueType;
    }
    public JavaType getValueType(final DeserializationContext deserializationContext) {
        final JavaType javaType = _valueType;
        return null != javaType ? javaType : deserializationContext.constructType(_valueClass);
    }
    protected boolean isDefaultDeserializer(final JsonDeserializer<?> jsonDeserializer) {
        return ClassUtil.isJacksonStdImpl(jsonDeserializer);
    }

    protected boolean isDefaultKeyDeserializer(final KeyDeserializer keyDeserializer) {
        return ClassUtil.isJacksonStdImpl(keyDeserializer);
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    protected T _deserializeFromArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final CoercionAction coercionAction_findCoercionFromEmptyArray = this._findCoercionFromEmptyArray(deserializationContext);
        final boolean zIsEnabled = deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        if (zIsEnabled || CoercionAction.Fail != coercionAction_findCoercionFromEmptyArray) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final JsonToken jsonToken = JsonToken.END_ARRAY;
            if (jsonTokenNextToken == jsonToken) {
                final int i2 = C11991.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[coercionAction_findCoercionFromEmptyArray.ordinal()];
                if (1 == i2) {
                    return (T) this.getEmptyValue(deserializationContext);
                }
                if (2 == i2 || 3 == i2) {
                    return this.getNullValue(deserializationContext);
                }
            } else if (zIsEnabled) {
                final T t_deserializeWrappedValue = this._deserializeWrappedValue(jsonParser, deserializationContext);
                if (jsonParser.nextToken() != jsonToken) {
                    this.handleMissingEndArrayForSingle(jsonParser, deserializationContext);
                }
                return t_deserializeWrappedValue;
            }
        }
        return (T) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), JsonToken.START_ARRAY, jsonParser, (String) null, new Object[0]);
    }

    protected abstract BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2);

    enum C11991 {
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
                C11991.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[CoercionAction.AsNull.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C11991.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[CoercionAction.TryConvert.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C11991.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[CoercionAction.Fail.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
        }
    }

    protected T _deserializeFromEmpty(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.START_ARRAY) && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
            if (JsonToken.END_ARRAY == jsonParser.nextToken()) {
                return null;
            }
            return (T) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
        }
        return (T) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
    }

    protected T _deserializeFromString(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final ValueInstantiator valueInstantiator = this.getValueInstantiator();
        final Class<?> clsHandledType = this.handledType();
        String valueAsString = jsonParser.getValueAsString();
        if (null != valueInstantiator && valueInstantiator.canCreateFromString()) {
            return (T) valueInstantiator.createFromString(deserializationContext, valueAsString);
        }
        if (valueAsString.isEmpty()) {
            return (T) this._deserializeFromEmptyString(jsonParser, deserializationContext, deserializationContext.findCoercionAction(this.logicalType(), clsHandledType, CoercionInputShape.EmptyString), clsHandledType, "empty String (\"\")");
        }
        if (StdDeserializer._isBlank(valueAsString)) {
            return (T) this._deserializeFromEmptyString(jsonParser, deserializationContext, deserializationContext.findCoercionFromBlankString(this.logicalType(), clsHandledType, CoercionAction.Fail), clsHandledType, "blank String (all whitespace)");
        }
        if (null != valueInstantiator) {
            valueAsString = valueAsString.trim();
            if (valueInstantiator.canCreateFromInt() && CoercionAction.TryConvert == deserializationContext.findCoercionAction(LogicalType.Integer, Integer.class, CoercionInputShape.String)) {
                return (T) valueInstantiator.createFromInt(deserializationContext, this._parseIntPrimitive(deserializationContext, valueAsString));
            }
            if (valueInstantiator.canCreateFromLong() && CoercionAction.TryConvert == deserializationContext.findCoercionAction(LogicalType.Integer, Long.class, CoercionInputShape.String)) {
                return (T) valueInstantiator.createFromLong(deserializationContext, this._parseLongPrimitive(deserializationContext, valueAsString));
            }
            if (valueInstantiator.canCreateFromBoolean() && CoercionAction.TryConvert == deserializationContext.findCoercionAction(LogicalType.Boolean, Boolean.class, CoercionInputShape.String)) {
                final String strTrim = valueAsString.trim();
                if ("true".equals(strTrim)) {
                    return (T) valueInstantiator.createFromBoolean(deserializationContext, true);
                }
                if ("false".equals(strTrim)) {
                    return (T) valueInstantiator.createFromBoolean(deserializationContext, false);
                }
            }
        }
        return (T) deserializationContext.handleMissingInstantiator(clsHandledType, valueInstantiator, deserializationContext.getParser(), "no String-argument constructor/factory method to deserialize from String value ('%s')", valueAsString);
    }

    protected Object _deserializeFromEmptyString(final JsonParser jsonParser, final DeserializationContext deserializationContext, final CoercionAction coercionAction, final Class<?> cls, final String str) throws IOException {
        final int i2 = C11991.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[coercionAction.ordinal()];
        if (1 == i2) {
            return this.getEmptyValue(deserializationContext);
        }
        if (4 != i2) {
            return null;
        }
        this._checkCoercionFail(deserializationContext, coercionAction, cls, "", "empty String (\"\")");
        return null;
    }

    protected T _deserializeWrappedValue(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonToken jsonToken = JsonToken.START_ARRAY;
        if (jsonParser.hasToken(jsonToken)) {
            return (T) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser.currentToken(), jsonParser, String.format("Cannot deserialize instance of %s out of %s token: nested Arrays not allowed with %s", ClassUtil.nameOf(_valueClass), jsonToken, "DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS"), new Object[0]);
        }
        return this.deserialize(jsonParser, deserializationContext);
    }
 
    protected final boolean _parseBooleanPrimitive(final DeserializationContext deserializationContext, final JsonParser jsonParser, final Class<?> cls) throws IOException {
        return this._parseBooleanPrimitive(jsonParser, deserializationContext);
    }

    protected final boolean _parseBooleanPrimitive(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final String strExtractScalarFromObject;
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 == iCurrentTokenId) {
            strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Boolean.TYPE);
        } else {
            if (3 != iCurrentTokenId) {
                if (6 == iCurrentTokenId) {
                    strExtractScalarFromObject = jsonParser.getText();
                } else {
                    if (7 == iCurrentTokenId) {
                        return Boolean.TRUE.equals(this._coerceBooleanFromInt(jsonParser, deserializationContext, Boolean.TYPE));
                    }
                    switch (iCurrentTokenId) {
                        case 9:
                            return true;
                        case 11:
                            this._verifyNullForPrimitive(deserializationContext);
                        case 10:
                            return false;
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                final boolean z_parseBooleanPrimitive = this._parseBooleanPrimitive(jsonParser, deserializationContext);
                this._verifyEndArrayForSingle(jsonParser, deserializationContext);
                return z_parseBooleanPrimitive;
            }
            return ((Boolean) deserializationContext.handleUnexpectedToken(Boolean.TYPE, jsonParser)).booleanValue();
        }
        final LogicalType logicalType = LogicalType.Boolean;
        final Class<?> cls = Boolean.TYPE;
        final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject, logicalType, cls);
        if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion) {
            this._verifyNullForPrimitive(deserializationContext);
            return false;
        }
        if (CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
            return false;
        }
        final String strTrim = strExtractScalarFromObject.trim();
        final int length = strTrim.length();
        if (4 == length) {
            if (this._isTrue(strTrim)) {
                return true;
            }
        } else if (5 == length && this._isFalse(strTrim)) {
            return false;
        }
        if (this._hasTextualNull(strTrim)) {
            this._verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
            return false;
        }
        return Boolean.TRUE.equals(deserializationContext.handleWeirdStringValue(cls, strTrim, "only \"true\"/\"True\"/\"TRUE\" or \"false\"/\"False\"/\"FALSE\" recognized"));
    }

    protected boolean _isTrue(final String str) {
        final char cCharAt = str.charAt(0);
        if ('t' == cCharAt) {
            return "true".equals(str);
        }
        if ('T' == cCharAt) {
            return "TRUE".equals(str) || "True".equals(str);
        }
        return false;
    }

    protected boolean _isFalse(final String str) {
        final char cCharAt = str.charAt(0);
        if ('f' == cCharAt) {
            return "false".equals(str);
        }
        if ('F' == cCharAt) {
            return "FALSE".equals(str) || "False".equals(str);
        }
        return false;
    }

    protected final Boolean _parseBoolean(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Class<?> cls) throws IOException {
        final String strExtractScalarFromObject;
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 == iCurrentTokenId) {
            strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, cls);
        } else {
            if (3 == iCurrentTokenId) {
                return (Boolean) this._deserializeFromArray(jsonParser, deserializationContext);
            }
            if (6 != iCurrentTokenId) {
                if (7 == iCurrentTokenId) {
                    return this._coerceBooleanFromInt(jsonParser, deserializationContext, cls);
                }
                switch (iCurrentTokenId) {
                    case 9:
                        return Boolean.TRUE;
                    case 10:
                        return Boolean.FALSE;
                    case 11:
                        return null;
                    default:
                        return (Boolean) deserializationContext.handleUnexpectedToken(cls, jsonParser);
                }
            }
            strExtractScalarFromObject = jsonParser.getText();
        }
        final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject, LogicalType.Boolean, cls);
        if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion) {
            return null;
        }
        if (CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
            return Boolean.FALSE;
        }
        final String strTrim = strExtractScalarFromObject.trim();
        final int length = strTrim.length();
        if (4 == length) {
            if (this._isTrue(strTrim)) {
                return Boolean.TRUE;
            }
        } else if (5 == length && this._isFalse(strTrim)) {
            return Boolean.FALSE;
        }
        if (this._checkTextualNull(deserializationContext, strTrim)) {
            return null;
        }
        return (Boolean) deserializationContext.handleWeirdStringValue(cls, strTrim, "only \"true\" or \"false\" recognized", new Object[0]);
    }

    protected final byte _parseBytePrimitive(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final String strExtractScalarFromObject;
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 == iCurrentTokenId) {
            strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Byte.TYPE);
        } else {
            if (3 != iCurrentTokenId) {
                if (11 == iCurrentTokenId) {
                    this._verifyNullForPrimitive(deserializationContext);
                    return (byte) 0;
                }
                if (6 == iCurrentTokenId) {
                    strExtractScalarFromObject = jsonParser.getText();
                } else {
                    if (7 == iCurrentTokenId) {
                        return jsonParser.getByteValue();
                    }
                    if (8 == iCurrentTokenId) {
                        final CoercionAction coercionAction_checkFloatToIntCoercion = this._checkFloatToIntCoercion(jsonParser, deserializationContext, Byte.TYPE);
                        if (CoercionAction.AsNull == coercionAction_checkFloatToIntCoercion || CoercionAction.AsEmpty == coercionAction_checkFloatToIntCoercion) {
                            return (byte) 0;
                        }
                        return jsonParser.getByteValue();
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                final byte b_parseBytePrimitive = this._parseBytePrimitive(jsonParser, deserializationContext);
                this._verifyEndArrayForSingle(jsonParser, deserializationContext);
                return b_parseBytePrimitive;
            }
            return ((Byte) deserializationContext.handleUnexpectedToken(deserializationContext.constructType(Byte.TYPE), jsonParser)).byteValue();
        }
        final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject, LogicalType.Integer, Byte.TYPE);
        if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion || CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
            return (byte) 0;
        }
        final String strTrim = strExtractScalarFromObject.trim();
        if (this._hasTextualNull(strTrim)) {
            this._verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
            return (byte) 0;
        }
        try {
            final int i2 = NumberInput.parseInt(strTrim);
            return this._byteOverflow(i2) ? ((Byte) deserializationContext.handleWeirdStringValue(_valueClass, strTrim, "overflow, value cannot be represented as 8-bit value", new Object[0])).byteValue() : (byte) i2;
        } catch (final IllegalArgumentException unused) {
            return ((Byte) deserializationContext.handleWeirdStringValue(_valueClass, strTrim, "not a valid `byte` value", new Object[0])).byteValue();
        }
    }

    protected final short _parseShortPrimitive(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final String strExtractScalarFromObject;
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 == iCurrentTokenId) {
            strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Short.TYPE);
        } else {
            if (3 != iCurrentTokenId) {
                if (11 == iCurrentTokenId) {
                    this._verifyNullForPrimitive(deserializationContext);
                    return (short) 0;
                }
                if (6 == iCurrentTokenId) {
                    strExtractScalarFromObject = jsonParser.getText();
                } else {
                    if (7 == iCurrentTokenId) {
                        return jsonParser.getShortValue();
                    }
                    if (8 == iCurrentTokenId) {
                        final CoercionAction coercionAction_checkFloatToIntCoercion = this._checkFloatToIntCoercion(jsonParser, deserializationContext, Short.TYPE);
                        if (CoercionAction.AsNull == coercionAction_checkFloatToIntCoercion || CoercionAction.AsEmpty == coercionAction_checkFloatToIntCoercion) {
                            return (short) 0;
                        }
                        return jsonParser.getShortValue();
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                final short s_parseShortPrimitive = this._parseShortPrimitive(jsonParser, deserializationContext);
                this._verifyEndArrayForSingle(jsonParser, deserializationContext);
                return s_parseShortPrimitive;
            }
            return ((Short) deserializationContext.handleUnexpectedToken(deserializationContext.constructType(Short.TYPE), jsonParser)).shortValue();
        }
        final LogicalType logicalType = LogicalType.Integer;
        final Class<?> cls = Short.TYPE;
        final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject, logicalType, cls);
        if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion || CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
            return (short) 0;
        }
        final String strTrim = strExtractScalarFromObject.trim();
        if (this._hasTextualNull(strTrim)) {
            this._verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
            return (short) 0;
        }
        try {
            final int i2 = NumberInput.parseInt(strTrim);
            return this._shortOverflow(i2) ? ((Short) deserializationContext.handleWeirdStringValue(cls, strTrim, "overflow, value cannot be represented as 16-bit value", new Object[0])).shortValue() : (short) i2;
        } catch (final IllegalArgumentException unused) {
            return ((Short) deserializationContext.handleWeirdStringValue(Short.TYPE, strTrim, "not a valid `short` value", new Object[0])).shortValue();
        }
    }

    protected final int _parseIntPrimitive(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final String strExtractScalarFromObject;
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 == iCurrentTokenId) {
            strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Integer.TYPE);
        } else {
            if (3 != iCurrentTokenId) {
                if (11 == iCurrentTokenId) {
                    this._verifyNullForPrimitive(deserializationContext);
                    return 0;
                }
                if (6 == iCurrentTokenId) {
                    strExtractScalarFromObject = jsonParser.getText();
                } else {
                    if (7 == iCurrentTokenId) {
                        return jsonParser.getIntValue();
                    }
                    if (8 == iCurrentTokenId) {
                        final CoercionAction coercionAction_checkFloatToIntCoercion = this._checkFloatToIntCoercion(jsonParser, deserializationContext, Integer.TYPE);
                        if (CoercionAction.AsNull == coercionAction_checkFloatToIntCoercion || CoercionAction.AsEmpty == coercionAction_checkFloatToIntCoercion) {
                            return 0;
                        }
                        return jsonParser.getValueAsInt();
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                final int i_parseIntPrimitive = this._parseIntPrimitive(jsonParser, deserializationContext);
                this._verifyEndArrayForSingle(jsonParser, deserializationContext);
                return i_parseIntPrimitive;
            }
            return ((Number) deserializationContext.handleUnexpectedToken(Integer.TYPE, jsonParser)).intValue();
        }
        final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject, LogicalType.Integer, Integer.TYPE);
        if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion || CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
            return 0;
        }
        final String strTrim = strExtractScalarFromObject.trim();
        if (this._hasTextualNull(strTrim)) {
            this._verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
            return 0;
        }
        return this._parseIntPrimitive(deserializationContext, strTrim);
    }

    protected final int _parseIntPrimitive(final DeserializationContext deserializationContext, final String str) throws NumberFormatException, IOException {
        try {
            if (9 < str.length()) {
                final long j2 = Long.parseLong(str);
                return this._intOverflow(j2) ? this._nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Integer.TYPE, str, "Overflow: numeric value (%s) out of range of int (%d -%d)", str, Integer.MIN_VALUE, Integer.MAX_VALUE)).intValue() : (int) j2;
            }
            return NumberInput.parseInt(str);
        } catch (final IllegalArgumentException unused) {
            return this._nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Integer.TYPE, str, "not a valid `int` value", new Object[0])).intValue();
        }
    }

    protected final Integer _parseInteger(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Class<?> cls) throws IOException {
        final String strExtractScalarFromObject;
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 == iCurrentTokenId) {
            strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, cls);
        } else {
            if (3 == iCurrentTokenId) {
                return (Integer) this._deserializeFromArray(jsonParser, deserializationContext);
            }
            if (11 == iCurrentTokenId) {
                return (Integer) this.getNullValue(deserializationContext);
            }
            if (6 != iCurrentTokenId) {
                if (7 == iCurrentTokenId) {
                    return Integer.valueOf(jsonParser.getIntValue());
                }
                if (8 == iCurrentTokenId) {
                    final CoercionAction coercionAction_checkFloatToIntCoercion = this._checkFloatToIntCoercion(jsonParser, deserializationContext, cls);
                    if (CoercionAction.AsNull == coercionAction_checkFloatToIntCoercion) {
                        return (Integer) this.getNullValue(deserializationContext);
                    }
                    if (CoercionAction.AsEmpty == coercionAction_checkFloatToIntCoercion) {
                        return (Integer) this.getEmptyValue(deserializationContext);
                    }
                    return Integer.valueOf(jsonParser.getValueAsInt());
                }
                return (Integer) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
            }
            strExtractScalarFromObject = jsonParser.getText();
        }
        final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject);
        if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion) {
            return (Integer) this.getNullValue(deserializationContext);
        }
        if (CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
            return (Integer) this.getEmptyValue(deserializationContext);
        }
        final String strTrim = strExtractScalarFromObject.trim();
        if (this._checkTextualNull(deserializationContext, strTrim)) {
            return (Integer) this.getNullValue(deserializationContext);
        }
        return Integer.valueOf(this._parseIntPrimitive(deserializationContext, strTrim));
    }

    protected final long _parseLongPrimitive(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final String strExtractScalarFromObject;
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 == iCurrentTokenId) {
            strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Long.TYPE);
        } else {
            if (3 != iCurrentTokenId) {
                if (11 == iCurrentTokenId) {
                    this._verifyNullForPrimitive(deserializationContext);
                    return 0L;
                }
                if (6 == iCurrentTokenId) {
                    strExtractScalarFromObject = jsonParser.getText();
                } else {
                    if (7 == iCurrentTokenId) {
                        return jsonParser.getLongValue();
                    }
                    if (8 == iCurrentTokenId) {
                        final CoercionAction coercionAction_checkFloatToIntCoercion = this._checkFloatToIntCoercion(jsonParser, deserializationContext, Long.TYPE);
                        if (CoercionAction.AsNull == coercionAction_checkFloatToIntCoercion || CoercionAction.AsEmpty == coercionAction_checkFloatToIntCoercion) {
                            return 0L;
                        }
                        return jsonParser.getValueAsLong();
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                final long j_parseLongPrimitive = this._parseLongPrimitive(jsonParser, deserializationContext);
                this._verifyEndArrayForSingle(jsonParser, deserializationContext);
                return j_parseLongPrimitive;
            }
            return ((Number) deserializationContext.handleUnexpectedToken(Long.TYPE, jsonParser)).longValue();
        }
        final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject, LogicalType.Integer, Long.TYPE);
        if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion || CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
            return 0L;
        }
        final String strTrim = strExtractScalarFromObject.trim();
        if (this._hasTextualNull(strTrim)) {
            this._verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
            return 0L;
        }
        return this._parseLongPrimitive(deserializationContext, strTrim);
    }

    protected final long _parseLongPrimitive(final DeserializationContext deserializationContext, final String str) throws IOException {
        try {
            return NumberInput.parseLong(str);
        } catch (final IllegalArgumentException unused) {
            return this._nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Long.TYPE, str, "not a valid `long` value", new Object[0])).longValue();
        }
    }

    protected final Long _parseLong(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Class<?> cls) throws IOException {
        final String strExtractScalarFromObject;
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 == iCurrentTokenId) {
            strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, cls);
        } else {
            if (3 == iCurrentTokenId) {
                return (Long) this._deserializeFromArray(jsonParser, deserializationContext);
            }
            if (11 == iCurrentTokenId) {
                return (Long) this.getNullValue(deserializationContext);
            }
            if (6 != iCurrentTokenId) {
                if (7 == iCurrentTokenId) {
                    return Long.valueOf(jsonParser.getLongValue());
                }
                if (8 == iCurrentTokenId) {
                    final CoercionAction coercionAction_checkFloatToIntCoercion = this._checkFloatToIntCoercion(jsonParser, deserializationContext, cls);
                    if (CoercionAction.AsNull == coercionAction_checkFloatToIntCoercion) {
                        return (Long) this.getNullValue(deserializationContext);
                    }
                    if (CoercionAction.AsEmpty == coercionAction_checkFloatToIntCoercion) {
                        return (Long) this.getEmptyValue(deserializationContext);
                    }
                    return Long.valueOf(jsonParser.getValueAsLong());
                }
                return (Long) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
            }
            strExtractScalarFromObject = jsonParser.getText();
        }
        final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject);
        if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion) {
            return (Long) this.getNullValue(deserializationContext);
        }
        if (CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
            return (Long) this.getEmptyValue(deserializationContext);
        }
        final String strTrim = strExtractScalarFromObject.trim();
        if (this._checkTextualNull(deserializationContext, strTrim)) {
            return (Long) this.getNullValue(deserializationContext);
        }
        return Long.valueOf(this._parseLongPrimitive(deserializationContext, strTrim));
    }

    protected final float _parseFloatPrimitive(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final String strExtractScalarFromObject;
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 == iCurrentTokenId) {
            strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Float.TYPE);
        } else {
            if (3 != iCurrentTokenId) {
                if (11 == iCurrentTokenId) {
                    this._verifyNullForPrimitive(deserializationContext);
                    return 0.0f;
                }
                if (6 == iCurrentTokenId) {
                    strExtractScalarFromObject = jsonParser.getText();
                } else if (7 == iCurrentTokenId || 8 == iCurrentTokenId) {
                    return jsonParser.getFloatValue();
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                final float f_parseFloatPrimitive = this._parseFloatPrimitive(jsonParser, deserializationContext);
                this._verifyEndArrayForSingle(jsonParser, deserializationContext);
                return f_parseFloatPrimitive;
            }
            return ((Number) deserializationContext.handleUnexpectedToken(Float.TYPE, jsonParser)).floatValue();
        }
        final Float f_checkFloatSpecialValue = this._checkFloatSpecialValue(strExtractScalarFromObject);
        if (null != f_checkFloatSpecialValue) {
            return f_checkFloatSpecialValue.floatValue();
        }
        final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject, LogicalType.Integer, Float.TYPE);
        if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion || CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
            return 0.0f;
        }
        final String strTrim = strExtractScalarFromObject.trim();
        if (this._hasTextualNull(strTrim)) {
            this._verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
            return 0.0f;
        }
        return this._parseFloatPrimitive(deserializationContext, strTrim);
    }

    protected final float _parseFloatPrimitive(final DeserializationContext deserializationContext, final String str) throws IOException {
        try {
            return Float.parseFloat(str);
        } catch (final IllegalArgumentException unused) {
            return this._nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Float.TYPE, str, "not a valid `float` value", new Object[0])).floatValue();
        }
    }

    protected Float _checkFloatSpecialValue(final String str) {
        if (str.isEmpty()) {
            return null;
        }
        final char cCharAt = str.charAt(0);
        if ('-' == cCharAt) {
            if (this._isNegInf(str)) {
                return Float.valueOf(Float.NEGATIVE_INFINITY);
            }
            return null;
        }
        if ('I' == cCharAt) {
            if (this._isPosInf(str)) {
                return Float.valueOf(Float.POSITIVE_INFINITY);
            }
            return null;
        }
        if ('N' == cCharAt && this._isNaN(str)) {
            return Float.valueOf(Float.NaN);
        }
        return null;
    }

    protected final double _parseDoublePrimitive(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final String strExtractScalarFromObject;
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 == iCurrentTokenId) {
            strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Double.TYPE);
        } else {
            if (3 != iCurrentTokenId) {
                if (11 == iCurrentTokenId) {
                    this._verifyNullForPrimitive(deserializationContext);
                    return 0.0d;
                }
                if (6 == iCurrentTokenId) {
                    strExtractScalarFromObject = jsonParser.getText();
                } else if (7 == iCurrentTokenId || 8 == iCurrentTokenId) {
                    return jsonParser.getDoubleValue();
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                final double d_parseDoublePrimitive = this._parseDoublePrimitive(jsonParser, deserializationContext);
                this._verifyEndArrayForSingle(jsonParser, deserializationContext);
                return d_parseDoublePrimitive;
            }
            return ((Number) deserializationContext.handleUnexpectedToken(Double.TYPE, jsonParser)).doubleValue();
        }
        final Double d_checkDoubleSpecialValue = this._checkDoubleSpecialValue(strExtractScalarFromObject);
        if (null != d_checkDoubleSpecialValue) {
            return d_checkDoubleSpecialValue.doubleValue();
        }
        final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject, LogicalType.Integer, Double.TYPE);
        if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion || CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
            return 0.0d;
        }
        final String strTrim = strExtractScalarFromObject.trim();
        if (this._hasTextualNull(strTrim)) {
            this._verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
            return 0.0d;
        }
        return this._parseDoublePrimitive(deserializationContext, strTrim);
    }

    protected final double _parseDoublePrimitive(final DeserializationContext deserializationContext, final String str) throws IOException {
        try {
            return StdDeserializer._parseDouble(str);
        } catch (final IllegalArgumentException unused) {
            return this._nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Double.TYPE, str, "not a valid `double` value (as String to convert)", new Object[0])).doubleValue();
        }
    }

    protected static final double _parseDouble(final String str) throws NumberFormatException {
        if ("2.2250738585072012e-308".equals(str)) {
            return Double.MIN_NORMAL;
        }
        return Double.parseDouble(str);
    }

    protected Double _checkDoubleSpecialValue(final String str) {
        if (str.isEmpty()) {
            return null;
        }
        final char cCharAt = str.charAt(0);
        if ('-' == cCharAt) {
            if (this._isNegInf(str)) {
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            return null;
        }
        if ('I' == cCharAt) {
            if (this._isPosInf(str)) {
                return Double.valueOf(Double.POSITIVE_INFINITY);
            }
            return null;
        }
        if ('N' == cCharAt && this._isNaN(str)) {
            return Double.valueOf(Double.NaN);
        }
        return null;
    }

    protected Date _parseDate(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final String strExtractScalarFromObject;
        long jLongValue;
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 == iCurrentTokenId) {
            strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, _valueClass);
        } else {
            if (3 == iCurrentTokenId) {
                return this._parseDateFromArray(jsonParser, deserializationContext);
            }
            if (11 == iCurrentTokenId) {
                return (Date) this.getNullValue(deserializationContext);
            }
            if (6 != iCurrentTokenId) {
                if (7 == iCurrentTokenId) {
                    try {
                        jLongValue = jsonParser.getLongValue();
                    } catch (final JsonParseException | InputCoercionException unused) {
                        jLongValue = ((Number) deserializationContext.handleWeirdNumberValue(_valueClass, jsonParser.getNumberValue(), "not a valid 64-bit `long` for creating `java.util.Date`", new Object[0])).longValue();
                    }
                    return new Date(jLongValue);
                }
                return (Date) deserializationContext.handleUnexpectedToken(_valueClass, jsonParser);
            }
            strExtractScalarFromObject = jsonParser.getText();
        }
        return this._parseDate(strExtractScalarFromObject.trim(), deserializationContext);
    }

    protected Date _parseDateFromArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final CoercionAction coercionAction_findCoercionFromEmptyArray = this._findCoercionFromEmptyArray(deserializationContext);
        final boolean zIsEnabled = deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        if (zIsEnabled || CoercionAction.Fail != coercionAction_findCoercionFromEmptyArray) {
            if (JsonToken.END_ARRAY == jsonParser.nextToken()) {
                final int i2 = C11991.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[coercionAction_findCoercionFromEmptyArray.ordinal()];
                if (1 == i2) {
                    return (Date) this.getEmptyValue(deserializationContext);
                }
                if (2 == i2 || 3 == i2) {
                    return (Date) this.getNullValue(deserializationContext);
                }
            } else if (zIsEnabled) {
                final Date date_parseDate = this._parseDate(jsonParser, deserializationContext);
                this._verifyEndArrayForSingle(jsonParser, deserializationContext);
                return date_parseDate;
            }
        }
        return (Date) deserializationContext.handleUnexpectedToken(_valueClass, JsonToken.START_ARRAY, jsonParser, (String) null, new Object[0]);
    }

    protected Date _parseDate(final String str, final DeserializationContext deserializationContext) throws IOException {
        try {
            if (str.isEmpty()) {
                if (1 != C11991.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[_checkFromStringCoercion(deserializationContext, str).ordinal()]) {
                    return null;
                }
                return new Date(0L);
            }
            if (this._hasTextualNull(str)) {
                return null;
            }
            return deserializationContext.parseDate(str);
        } catch (final IllegalArgumentException e2) {
            return (Date) deserializationContext.handleWeirdStringValue(_valueClass, str, "not a valid representation (error: %s)", ClassUtil.exceptionMessage(e2));
        }
    }

    protected final String _parseString(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            return jsonParser.getText();
        }
        if (jsonParser.hasToken(JsonToken.VALUE_EMBEDDED_OBJECT)) {
            final Object embeddedObject = jsonParser.getEmbeddedObject();
            if (embeddedObject instanceof byte[]) {
                return deserializationContext.getBase64Variant().encode((byte[]) embeddedObject, false);
            }
            if (null == embeddedObject) {
                return null;
            }
            return embeddedObject.toString();
        }
        if (jsonParser.hasToken(JsonToken.START_OBJECT)) {
            return deserializationContext.extractScalarFromObject(jsonParser, this, _valueClass);
        }
        final String valueAsString = jsonParser.getValueAsString();
        return null != valueAsString ? valueAsString : (String) deserializationContext.handleUnexpectedToken(String.class, jsonParser);
    }

    protected boolean _hasTextualNull(final String str) {
        return "null".equals(str);
    }

    protected final boolean _isNegInf(final String str) {
        return "-Infinity".equals(str) || "-INF".equals(str);
    }

    protected final boolean _isPosInf(final String str) {
        return "Infinity".equals(str) || "INF".equals(str);
    }

    protected final boolean _isNaN(final String str) {
        return "NaN".equals(str);
    }

    protected static final boolean _isBlank(final String str) {
        final int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (' ' < str.charAt(i2)) {
                return false;
            }
        }
        return true;
    }

    protected CoercionAction _checkFromStringCoercion(final DeserializationContext deserializationContext, final String str) throws IOException {
        return this._checkFromStringCoercion(deserializationContext, str, this.logicalType(), this.handledType());
    }

    protected CoercionAction _checkFromStringCoercion(final DeserializationContext deserializationContext, final String str, final LogicalType logicalType, final Class<?> cls) throws IOException {
        if (str.isEmpty()) {
            return this._checkCoercionFail(deserializationContext, deserializationContext.findCoercionAction(logicalType, cls, CoercionInputShape.EmptyString), cls, str, "empty String (\"\")");
        }
        if (StdDeserializer._isBlank(str)) {
            return this._checkCoercionFail(deserializationContext, deserializationContext.findCoercionFromBlankString(logicalType, cls, CoercionAction.Fail), cls, str, "blank String (all whitespace)");
        }
        if (deserializationContext.isEnabled(StreamReadCapability.UNTYPED_SCALARS)) {
            return CoercionAction.TryConvert;
        }
        final CoercionAction coercionActionFindCoercionAction = deserializationContext.findCoercionAction(logicalType, cls, CoercionInputShape.String);
        if (CoercionAction.Fail == coercionActionFindCoercionAction) {
            deserializationContext.reportInputMismatch(this, "Cannot coerce String value (\"%s\") to %s (but might if coercion using `CoercionConfig` was enabled)", str, this._coercedTypeDesc());
        }
        return coercionActionFindCoercionAction;
    }

    protected CoercionAction _checkFloatToIntCoercion(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Class<?> cls) throws IOException {
        final CoercionAction coercionActionFindCoercionAction = deserializationContext.findCoercionAction(LogicalType.Integer, cls, CoercionInputShape.Float);
        if (CoercionAction.Fail != coercionActionFindCoercionAction) {
            return coercionActionFindCoercionAction;
        }
        return this._checkCoercionFail(deserializationContext, coercionActionFindCoercionAction, cls, jsonParser.getNumberValue(), "Floating-point value (" + jsonParser.getText() + ")");
    }

    protected Boolean _coerceBooleanFromInt(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Class<?> cls) throws IOException {
        final CoercionAction coercionActionFindCoercionAction = deserializationContext.findCoercionAction(LogicalType.Boolean, cls, CoercionInputShape.Integer);
        final int i2 = C11991.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[coercionActionFindCoercionAction.ordinal()];
        if (1 == i2) {
            return Boolean.FALSE;
        }
        if (2 == i2) {
            return null;
        }
        if (4 == i2) {
            this._checkCoercionFail(deserializationContext, coercionActionFindCoercionAction, cls, jsonParser.getNumberValue(), "Integer value (" + jsonParser.getText() + ")");
            return Boolean.FALSE;
        }
        if (JsonParser.NumberType.INT == jsonParser.getNumberType()) {
            return Boolean.valueOf(0 != jsonParser.getIntValue());
        }
        return Boolean.valueOf(!"0".equals(jsonParser.getText()));
    }

    protected CoercionAction _checkCoercionFail(final DeserializationContext deserializationContext, final CoercionAction coercionAction, final Class<?> cls, final Object obj, final String str) throws IOException {
        if (CoercionAction.Fail == coercionAction) {
            deserializationContext.reportBadCoercion(this, cls, obj, "Cannot coerce %s to %s (but could if coercion was enabled using `CoercionConfig`)", str, this._coercedTypeDesc());
        }
        return coercionAction;
    }

    protected boolean _checkTextualNull(final DeserializationContext deserializationContext, final String str) throws JsonMappingException {
        if (!this._hasTextualNull(str)) {
            return false;
        }
        final MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (!deserializationContext.isEnabled(mapperFeature)) {
            this._reportFailedNullCoerce(deserializationContext, true, mapperFeature, "String \"null\"");
        }
        return true;
    }

    protected Object _coerceIntegral(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final int deserializationFeatures = deserializationContext.getDeserializationFeatures();
        if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(deserializationFeatures)) {
            return jsonParser.getBigIntegerValue();
        }
        if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(deserializationFeatures)) {
            return Long.valueOf(jsonParser.getLongValue());
        }
        return jsonParser.getNumberValue();
    }

    protected final void _verifyNullForPrimitive(final DeserializationContext deserializationContext) throws JsonMappingException {
        if (deserializationContext.isEnabled(FAIL_ON_NULL_FOR_PRIMITIVES)) {
            deserializationContext.reportInputMismatch(this, "Cannot coerce `null` to %s (disable `DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES` to allow)", this._coercedTypeDesc());
        }
    }

    protected final void _verifyNullForPrimitiveCoercion(final DeserializationContext deserializationContext, final String str) throws JsonMappingException {
        final boolean z;
        MapperFeature mapperFeature = null;
        final MapperFeature mapperFeature2 = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (deserializationContext.isEnabled(mapperFeature2)) {
            final DeserializationFeature deserializationFeature = FAIL_ON_NULL_FOR_PRIMITIVES;
            if (!deserializationContext.isEnabled(deserializationFeature)) {
                return;
            }
            z = false;

        } else {
            z = true;
            mapperFeature    = mapperFeature2;
        }
        this._reportFailedNullCoerce(deserializationContext, z, mapperFeature, str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", str));
    }

    protected void _reportFailedNullCoerce(final DeserializationContext deserializationContext, final boolean z, final Enum<?> r5, final String str) throws JsonMappingException {
        deserializationContext.reportInputMismatch(this, "Cannot coerce %s to Null value as %s (%s `%s.%s` to allow)", str, this._coercedTypeDesc(), z ? "enable" : "disable", r5.getDeclaringClass().getSimpleName(), r5.name());
    }

    protected String _coercedTypeDesc() {
        final String classDescription;
        final JavaType valueType = this.getValueType();
        boolean z = true;
        if (null != valueType && !valueType.isPrimitive()) {
            if (!valueType.isContainerType() && !valueType.isReferenceType()) {
                z = false;
            }
            classDescription = ClassUtil.getTypeDescription(valueType);
        } else {
            final Class<?> clsHandledType = this.handledType();
            if (!clsHandledType.isArray() && !Collection.class.isAssignableFrom(clsHandledType) && !Map.class.isAssignableFrom(clsHandledType)) {
                z = false;
            }
            classDescription = ClassUtil.getClassDescription(clsHandledType);
        }
        if (z) {
            return "element of " + classDescription;
        }
        return classDescription + " value";
    }
    protected boolean _parseBooleanFromInt(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        this._verifyNumberForScalarCoercion(deserializationContext, jsonParser);
        return !"0".equals(jsonParser.getText());
    }
    protected void _verifyStringForScalarCoercion(final DeserializationContext deserializationContext, final String str) throws JsonMappingException {
        final MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (deserializationContext.isEnabled(mapperFeature)) {
            return;
        }
        deserializationContext.reportInputMismatch(this, "Cannot coerce String \"%s\" to %s (enable `%s.%s` to allow)", str, this._coercedTypeDesc(), mapperFeature.getDeclaringClass().getSimpleName(), mapperFeature.name());
    }
    protected Object _coerceEmptyString(final DeserializationContext deserializationContext, final boolean z) throws JsonMappingException {
        final boolean z2;
        final MapperFeature mapperFeature;
        final MapperFeature mapperFeature2 = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (deserializationContext.isEnabled(mapperFeature2)) {
            if (z) {
                final DeserializationFeature deserializationFeature = FAIL_ON_NULL_FOR_PRIMITIVES;
                if (deserializationContext.isEnabled(deserializationFeature)) {
                    z2 = false;

                }
            }
            return this.getNullValue(deserializationContext);
        }
        z2 = true;
        mapperFeature = mapperFeature2;
        this._reportFailedNullCoerce(deserializationContext, z2, mapperFeature, "empty String (\"\")");
        return null;
    }
    protected void _failDoubleToIntCoercion(final JsonParser jsonParser, final DeserializationContext deserializationContext, final String str) throws IOException {
        deserializationContext.reportInputMismatch(this.handledType(), "Cannot coerce a floating-point value ('%s') into %s (enable `DeserializationFeature.ACCEPT_FLOAT_AS_INT` to allow)", jsonParser.getValueAsString(), str);
    }
    protected final void _verifyNullForScalarCoercion(final DeserializationContext deserializationContext, final String str) throws JsonMappingException {
        final MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (deserializationContext.isEnabled(mapperFeature)) {
            return;
        }
        this._reportFailedNullCoerce(deserializationContext, true, mapperFeature, str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", str));
    }
    protected void _verifyNumberForScalarCoercion(final DeserializationContext deserializationContext, final JsonParser jsonParser) throws IOException {
        final MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (deserializationContext.isEnabled(mapperFeature)) {
            return;
        }
        deserializationContext.reportInputMismatch(this, "Cannot coerce Number (%s) to %s (enable `%s.%s` to allow)", jsonParser.getText(), this._coercedTypeDesc(), mapperFeature.getDeclaringClass().getSimpleName(), mapperFeature.name());
    }

    protected Object _coerceNullToken(final DeserializationContext deserializationContext, final boolean z) throws JsonMappingException {
        if (z) {
            this._verifyNullForPrimitive(deserializationContext);
        }
        return this.getNullValue(deserializationContext);
    }

    protected Object _coerceTextualNull(final DeserializationContext deserializationContext, final boolean z) throws JsonMappingException {
        final MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (!deserializationContext.isEnabled(mapperFeature)) {
            this._reportFailedNullCoerce(deserializationContext, true, mapperFeature, "String \"null\"");
        }
        return this.getNullValue(deserializationContext);
    }

    @Deprecated
    protected boolean _isEmptyOrTextualNull(final String str) {
        return str.isEmpty() || "null".equals(str);
    }

    protected JsonDeserializer<Object> findDeserializer(final DeserializationContext deserializationContext, final JavaType javaType, final BeanProperty beanProperty) throws JsonMappingException {
        return deserializationContext.findContextualValueDeserializer(javaType, beanProperty);
    }

    protected final boolean _isIntNumber(final String str) {
        int i2;
        final int length = str.length();
        if (0 >= length) {
            return false;
        }
        final char cCharAt = str.charAt(0);
        if ('-' != cCharAt && '+' != cCharAt) {
            i2 = 0;
        } else {
            if (1 == length) {
                return false;
            }
            i2 = 1;
        }
        while (i2 < length) {
            final char cCharAt2 = str.charAt(i2);
            if ('9' < cCharAt2 || '0' > cCharAt2) {
                return false;
            }
            i2++;
        }
        return true;
    }

    protected JsonDeserializer<?> findConvertingContentDeserializer(final DeserializationContext deserializationContext, final BeanProperty beanProperty, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        final AnnotatedMember member;
        final Object objFindDeserializationContentConverter;
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (!StdDeserializer._neitherNull(annotationIntrospector, beanProperty) || null == (member = beanProperty.getMember()) || null == (objFindDeserializationContentConverter = annotationIntrospector.findDeserializationContentConverter(member))) {
            return jsonDeserializer;
        }
        final Converter<Object, Object> converterConverterInstance = deserializationContext.converterInstance(beanProperty.getMember(), objFindDeserializationContentConverter);
        final JavaType inputType = converterConverterInstance.getInputType(deserializationContext.getTypeFactory());
        if (null == jsonDeserializer) {
            jsonDeserializer = deserializationContext.findContextualValueDeserializer(inputType, beanProperty);
        }
        return new StdDelegatingDeserializer(converterConverterInstance, inputType, jsonDeserializer);
    }

    protected JsonFormat.Value findFormatOverrides(final DeserializationContext deserializationContext, final BeanProperty beanProperty, final Class<?> cls) {
        if (null != beanProperty) {
            return beanProperty.findPropertyFormat(deserializationContext.getConfig(), cls);
        }
        return deserializationContext.getDefaultPropertyFormat(cls);
    }

    protected Boolean findFormatFeature(final DeserializationContext deserializationContext, final BeanProperty beanProperty, final Class<?> cls, final JsonFormat.Feature feature) {
        final JsonFormat.Value valueFindFormatOverrides = this.findFormatOverrides(deserializationContext, beanProperty, cls);
        if (null != valueFindFormatOverrides) {
            return valueFindFormatOverrides.getFeature(feature);
        }
        return null;
    }

    protected final NullValueProvider findValueNullProvider(final DeserializationContext deserializationContext, final SettableBeanProperty settableBeanProperty, final PropertyMetadata propertyMetadata) throws JsonMappingException {
        if (null != settableBeanProperty) {
            return this._findNullProvider(deserializationContext, settableBeanProperty, propertyMetadata.getValueNulls(), settableBeanProperty.getValueDeserializer());
        }
        return null;
    }

    protected NullValueProvider findContentNullProvider(final DeserializationContext deserializationContext, final BeanProperty beanProperty, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        final Nulls nullsFindContentNullStyle = this.findContentNullStyle(deserializationContext, beanProperty);
        if (Nulls.SKIP == nullsFindContentNullStyle) {
            return NullsConstantProvider.skipper();
        }
        if (Nulls.FAIL != nullsFindContentNullStyle) {
            final NullValueProvider nullValueProvider_findNullProvider = this._findNullProvider(deserializationContext, beanProperty, nullsFindContentNullStyle, jsonDeserializer);
            return null != nullValueProvider_findNullProvider ? nullValueProvider_findNullProvider : jsonDeserializer;
        }
        if (null == beanProperty) {
            JavaType javaTypeConstructType = deserializationContext.constructType(jsonDeserializer.handledType());
            if (javaTypeConstructType.isContainerType()) {
                javaTypeConstructType = javaTypeConstructType.getContentType();
            }
            return NullsFailProvider.constructForRootValue(javaTypeConstructType);
        }
        return NullsFailProvider.constructForProperty(beanProperty, beanProperty.getType().getContentType());
    }

    protected Nulls findContentNullStyle(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        if (null != beanProperty) {
            return beanProperty.getMetadata().getContentNulls();
        }
        return null;
    }

    protected final NullValueProvider _findNullProvider(final DeserializationContext deserializationContext, final BeanProperty beanProperty, final Nulls nulls, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        if (Nulls.FAIL == nulls) {
            if (null == beanProperty) {
                return NullsFailProvider.constructForRootValue(deserializationContext.constructType(jsonDeserializer.handledType()));
            }
            return NullsFailProvider.constructForProperty(beanProperty);
        }
        if (Nulls.AS_EMPTY != nulls) {
            if (Nulls.SKIP == nulls) {
                return NullsConstantProvider.skipper();
            }
            return null;
        }
        if (null == jsonDeserializer) {
            return null;
        }
        if ((jsonDeserializer instanceof BeanDeserializerBase) && !((BeanDeserializerBase) jsonDeserializer).getValueInstantiator().canCreateUsingDefault()) {
            final JavaType type = beanProperty.getType();
            deserializationContext.reportBadDefinition(type, String.format("Cannot create empty instance of %s, no default Creator", type));
        }
        final AccessPattern emptyAccessPattern = jsonDeserializer.getEmptyAccessPattern();
        if (AccessPattern.ALWAYS_NULL == emptyAccessPattern) {
            return NullsConstantProvider.nuller();
        }
        if (AccessPattern.CONSTANT == emptyAccessPattern) {
            return NullsConstantProvider.forValue(jsonDeserializer.getEmptyValue(deserializationContext));
        }
        return new NullsAsEmptyProvider(jsonDeserializer);
    }

    protected CoercionAction _findCoercionFromEmptyString(final DeserializationContext deserializationContext) {
        return deserializationContext.findCoercionAction(this.logicalType(), this.handledType(), CoercionInputShape.EmptyString);
    }

    protected CoercionAction _findCoercionFromEmptyArray(final DeserializationContext deserializationContext) {
        return deserializationContext.findCoercionAction(this.logicalType(), this.handledType(), CoercionInputShape.EmptyArray);
    }

    protected CoercionAction _findCoercionFromBlankString(final DeserializationContext deserializationContext) {
        return deserializationContext.findCoercionFromBlankString(this.logicalType(), this.handledType(), CoercionAction.Fail);
    }

    protected void handleUnknownProperty(final JsonParser jsonParser, final DeserializationContext deserializationContext, Object obj, final String str) throws IOException {
        if (null == obj) {
            obj = this.handledType();
        }
        if (deserializationContext.handleUnknownProperty(jsonParser, this, obj, str)) {
            return;
        }
        jsonParser.skipChildren();
    }

    protected void handleMissingEndArrayForSingle(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        deserializationContext.reportWrongTokenException(this, JsonToken.END_ARRAY, "Attempted to unwrap '%s' value from an array (with `DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS`) but it contains more than one value", this.handledType().getName());
    }

    protected void _verifyEndArrayForSingle(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (JsonToken.END_ARRAY != jsonParser.nextToken()) {
            this.handleMissingEndArrayForSingle(jsonParser, deserializationContext);
        }
    }

    protected Number _nonNullNumber(final Number number) {
        if (null == number) {
            return 0;
        }
        return number;
    }
}
