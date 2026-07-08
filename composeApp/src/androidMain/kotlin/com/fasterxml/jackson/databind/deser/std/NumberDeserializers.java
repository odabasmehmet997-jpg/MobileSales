package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class NumberDeserializers {
    private static final HashSet<String> _classNames = new HashSet<>();
    static {
        final Class[] clsArr = {Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class, Number.class, BigDecimal.class, BigInteger.class};
        for (final Class<?> cls : clsArr) {
            NumberDeserializers._classNames.add(cls.getName());
        }
    }
    public static JsonDeserializer<?> find(final Class<?> cls, final String str) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return IntegerDeserializer.primitiveInstance;
            }
            if (cls == Boolean.TYPE) {
                return BooleanDeserializer.primitiveInstance;
            }
            if (cls == Long.TYPE) {
                return LongDeserializer.primitiveInstance;
            }
            if (cls == Double.TYPE) {
                return DoubleDeserializer.primitiveInstance;
            }
            if (cls == Character.TYPE) {
                return CharacterDeserializer.primitiveInstance;
            }
            if (cls == Byte.TYPE) {
                return ByteDeserializer.primitiveInstance;
            }
            if (cls == Short.TYPE) {
                return ShortDeserializer.primitiveInstance;
            }
            if (cls == Float.TYPE) {
                return FloatDeserializer.primitiveInstance;
            }
            if (cls == Void.TYPE) {
                return NullifyingDeserializer.instance;
            }
        } else {
            if (!NumberDeserializers._classNames.contains(str)) {
                return null;
            }
            if (Integer.class == cls) {
                return IntegerDeserializer.wrapperInstance;
            }
            if (Boolean.class == cls) {
                return BooleanDeserializer.wrapperInstance;
            }
            if (Long.class == cls) {
                return LongDeserializer.wrapperInstance;
            }
            if (Double.class == cls) {
                return DoubleDeserializer.wrapperInstance;
            }
            if (Character.class == cls) {
                return CharacterDeserializer.wrapperInstance;
            }
            if (Byte.class == cls) {
                return ByteDeserializer.wrapperInstance;
            }
            if (Short.class == cls) {
                return ShortDeserializer.wrapperInstance;
            }
            if (Float.class == cls) {
                return FloatDeserializer.wrapperInstance;
            }
            if (Number.class == cls) {
                return NumberDeserializer.instance;
            }
            if (BigDecimal.class == cls) {
                return BigDecimalDeserializer.instance;
            }
            if (BigInteger.class == cls) {
                return BigIntegerDeserializer.instance;
            }
        }
        throw new IllegalArgumentException("Internal error: can't find deserializer for " + cls.getName());
    }
    protected static abstract class PrimitiveOrWrapperDeserializer<T> extends StdScalarDeserializer<T> {
        private static final long serialVersionUID = 1;
        protected final T _emptyValue;
        protected final LogicalType _logicalType;
        protected final T _nullValue;
        protected final boolean _primitive;

        protected PrimitiveOrWrapperDeserializer(final Class<T> cls, final LogicalType logicalType, final T t, final T t2) {
            super(cls);
            _logicalType = logicalType;
            _nullValue = t;
            _emptyValue = t2;
            _primitive = cls.isPrimitive();
        }

        @Deprecated
        protected PrimitiveOrWrapperDeserializer(final Class<T> cls, final T t, final T t2) {
            this(cls, LogicalType.OtherScalar, t, t2);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public AccessPattern getNullAccessPattern() {
            if (_primitive) {
                return AccessPattern.DYNAMIC;
            }
            if (null == this._nullValue) {
                return AccessPattern.ALWAYS_NULL;
            }
            return AccessPattern.CONSTANT;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.NullValueProvider
        public final T getNullValue(final DeserializationContext deserializationContext) throws JsonMappingException {
            if (_primitive && deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                deserializationContext.reportInputMismatch(this, "Cannot map `null` into type %s (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)", ClassUtil.classNameOf(this.handledType()));
            }
            return _nullValue;
        }
        public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
            return _emptyValue;
        }
        public final LogicalType logicalType() {
            return _logicalType;
        }
    }
    public static final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer<Boolean> {
        private static final long serialVersionUID = 1;
        static final BooleanDeserializer primitiveInstance = new BooleanDeserializer(Boolean.TYPE, Boolean.FALSE);
        static final BooleanDeserializer wrapperInstance = new BooleanDeserializer(Boolean.class, null);
        public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }
        public AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }
        public BooleanDeserializer(final Class<Boolean> cls, final Boolean bool) {
            super(cls, LogicalType.Boolean, bool, Boolean.FALSE);
        }
        public Boolean deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            if (JsonToken.VALUE_TRUE == jsonTokenCurrentToken) {
                return Boolean.TRUE;
            }
            if (JsonToken.VALUE_FALSE == jsonTokenCurrentToken) {
                return Boolean.FALSE;
            }
            if (_primitive) {
                return Boolean.valueOf(this._parseBooleanPrimitive(jsonParser, deserializationContext));
            }
            return this._parseBoolean(jsonParser, deserializationContext, _valueClass);
        }
        public Boolean deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
            final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            if (JsonToken.VALUE_TRUE == jsonTokenCurrentToken) {
                return Boolean.TRUE;
            }
            if (JsonToken.VALUE_FALSE == jsonTokenCurrentToken) {
                return Boolean.FALSE;
            }
            if (_primitive) {
                return Boolean.valueOf(this._parseBooleanPrimitive(jsonParser, deserializationContext));
            }
            return this._parseBoolean(jsonParser, deserializationContext, _valueClass);
        }

        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    public static class ByteDeserializer extends PrimitiveOrWrapperDeserializer<Byte> {
        private static final long serialVersionUID = 1;
        static final ByteDeserializer primitiveInstance = new ByteDeserializer(Byte.TYPE, (byte) 0);
        static final ByteDeserializer wrapperInstance = new ByteDeserializer(Byte.class, null);

         public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }
         public AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }
        public ByteDeserializer(final Class<Byte> cls, final Byte b2) {
            super(cls, LogicalType.Integer, b2, (byte) 0);
        }
        public Byte deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.isExpectedNumberIntToken()) {
                return Byte.valueOf(jsonParser.getByteValue());
            }
            if (_primitive) {
                return Byte.valueOf(this._parseBytePrimitive(jsonParser, deserializationContext));
            }
            return this._parseByte(jsonParser, deserializationContext);
        }

        protected Byte _parseByte(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            final String strExtractScalarFromObject;
            final int iCurrentTokenId = jsonParser.currentTokenId();
            if (1 == iCurrentTokenId) {
                strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, _valueClass);
            } else {
                if (3 == iCurrentTokenId) {
                    return this._deserializeFromArray(jsonParser, deserializationContext);
                }
                if (11 == iCurrentTokenId) {
                    return this.getNullValue(deserializationContext);
                }
                if (6 != iCurrentTokenId) {
                    if (7 == iCurrentTokenId) {
                        return Byte.valueOf(jsonParser.getByteValue());
                    }
                    if (8 == iCurrentTokenId) {
                        final CoercionAction coercionAction_checkFloatToIntCoercion = this._checkFloatToIntCoercion(jsonParser, deserializationContext, _valueClass);
                        if (CoercionAction.AsNull == coercionAction_checkFloatToIntCoercion) {
                            return this.getNullValue(deserializationContext);
                        }
                        if (CoercionAction.AsEmpty == coercionAction_checkFloatToIntCoercion) {
                            return (Byte) this.getEmptyValue(deserializationContext);
                        }
                        return Byte.valueOf(jsonParser.getByteValue());
                    }
                    return (Byte) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
                }
                strExtractScalarFromObject = jsonParser.getText();
            }
            final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject);
            if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion) {
                return this.getNullValue(deserializationContext);
            }
            if (CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
                return (Byte) this.getEmptyValue(deserializationContext);
            }
            final String strTrim = strExtractScalarFromObject.trim();
            if (this._checkTextualNull(deserializationContext, strTrim)) {
                return this.getNullValue(deserializationContext);
            }
            try {
                final int i2 = NumberInput.parseInt(strTrim);
                if (this._byteOverflow(i2)) {
                    return (Byte) deserializationContext.handleWeirdStringValue(_valueClass, strTrim, "overflow, value cannot be represented as 8-bit value", new Object[0]);
                }
                return Byte.valueOf((byte) i2);
            } catch (final IllegalArgumentException unused) {
                return (Byte) deserializationContext.handleWeirdStringValue(_valueClass, strTrim, "not a valid Byte value", new Object[0]);
            }
        }

        @Override
        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    public static class ShortDeserializer extends PrimitiveOrWrapperDeserializer<Short> {
        private static final long serialVersionUID = 1;
        static final ShortDeserializer primitiveInstance = new ShortDeserializer(Short.TYPE, (short) 0);
        static final ShortDeserializer wrapperInstance = new ShortDeserializer(Short.class, null);
        public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }

        public AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }

        public ShortDeserializer(final Class<Short> cls, final Short sh) {
            super(cls, LogicalType.Integer, sh, (short) 0);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Short deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.isExpectedNumberIntToken()) {
                return Short.valueOf(jsonParser.getShortValue());
            }
            if (_primitive) {
                return Short.valueOf(this._parseShortPrimitive(jsonParser, deserializationContext));
            }
            return this._parseShort(jsonParser, deserializationContext);
        }

        protected Short _parseShort(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            final String strExtractScalarFromObject;
            final int iCurrentTokenId = jsonParser.currentTokenId();
            if (1 == iCurrentTokenId) {
                strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, _valueClass);
            } else {
                if (3 == iCurrentTokenId) {
                    return this._deserializeFromArray(jsonParser, deserializationContext);
                }
                if (11 == iCurrentTokenId) {
                    return this.getNullValue(deserializationContext);
                }
                if (6 != iCurrentTokenId) {
                    if (7 == iCurrentTokenId) {
                        return Short.valueOf(jsonParser.getShortValue());
                    }
                    if (8 == iCurrentTokenId) {
                        final CoercionAction coercionAction_checkFloatToIntCoercion = this._checkFloatToIntCoercion(jsonParser, deserializationContext, _valueClass);
                        if (CoercionAction.AsNull == coercionAction_checkFloatToIntCoercion) {
                            return this.getNullValue(deserializationContext);
                        }
                        if (CoercionAction.AsEmpty == coercionAction_checkFloatToIntCoercion) {
                            return (Short) this.getEmptyValue(deserializationContext);
                        }
                        return Short.valueOf(jsonParser.getShortValue());
                    }
                    return (Short) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
                }
                strExtractScalarFromObject = jsonParser.getText();
            }
            final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject);
            if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion) {
                return this.getNullValue(deserializationContext);
            }
            if (CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
                return (Short) this.getEmptyValue(deserializationContext);
            }
            final String strTrim = strExtractScalarFromObject.trim();
            if (this._checkTextualNull(deserializationContext, strTrim)) {
                return this.getNullValue(deserializationContext);
            }
            try {
                final int i2 = NumberInput.parseInt(strTrim);
                if (this._shortOverflow(i2)) {
                    return (Short) deserializationContext.handleWeirdStringValue(_valueClass, strTrim, "overflow, value cannot be represented as 16-bit value", new Object[0]);
                }
                return Short.valueOf((short) i2);
            } catch (final IllegalArgumentException unused) {
                return (Short) deserializationContext.handleWeirdStringValue(_valueClass, strTrim, "not a valid Short value", new Object[0]);
            }
        }

        @Override
        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    public static class CharacterDeserializer extends PrimitiveOrWrapperDeserializer<Character> {
        private static final long serialVersionUID = 1;
        static final CharacterDeserializer primitiveInstance = new CharacterDeserializer(Character.TYPE, (char) 0);
        static final CharacterDeserializer wrapperInstance = new CharacterDeserializer(Character.class, null);

        public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }
        public AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }

        public CharacterDeserializer(final Class<Character> cls, final Character ch) {
            super(cls, LogicalType.Integer, ch, (char) 0);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Character deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            final String strExtractScalarFromObject;
            final int iCurrentTokenId = jsonParser.currentTokenId();
            if (1 == iCurrentTokenId) {
                strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, _valueClass);
            } else {
                if (3 == iCurrentTokenId) {
                    return this._deserializeFromArray(jsonParser, deserializationContext);
                }
                if (11 == iCurrentTokenId) {
                    if (_primitive) {
                        this._verifyNullForPrimitive(deserializationContext);
                    }
                    return this.getNullValue(deserializationContext);
                }
                if (6 != iCurrentTokenId) {
                    if (7 == iCurrentTokenId) {
                        final CoercionAction coercionActionFindCoercionAction = deserializationContext.findCoercionAction(this.logicalType(), _valueClass, CoercionInputShape.Integer);
                        final int i2 = C11981.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[coercionActionFindCoercionAction.ordinal()];
                        if (1 == i2) {
                            this._checkCoercionFail(deserializationContext, coercionActionFindCoercionAction, _valueClass, jsonParser.getNumberValue(), "Integer value (" + jsonParser.getText() + ")");
                        } else if (2 != i2) {
                            if (3 == i2) {
                                return (Character) this.getEmptyValue(deserializationContext);
                            }
                            final int intValue = jsonParser.getIntValue();
                            if (0 <= intValue && 65535 >= intValue) {
                                return Character.valueOf((char) intValue);
                            }
                            return (Character) deserializationContext.handleWeirdNumberValue(this.handledType(), Integer.valueOf(intValue), "value outside valid Character range (0x0000 - 0xFFFF)", new Object[0]);
                        }
                        return this.getNullValue(deserializationContext);
                    }
                    return (Character) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
                }
                strExtractScalarFromObject = jsonParser.getText();
            }
            if (1 == strExtractScalarFromObject.length()) {
                return Character.valueOf(strExtractScalarFromObject.charAt(0));
            }
            final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject);
            if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion) {
                return this.getNullValue(deserializationContext);
            }
            if (CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
                return (Character) this.getEmptyValue(deserializationContext);
            }
            final String strTrim = strExtractScalarFromObject.trim();
            if (this._checkTextualNull(deserializationContext, strTrim)) {
                return this.getNullValue(deserializationContext);
            }
            return (Character) deserializationContext.handleWeirdStringValue(this.handledType(), strTrim, "Expected either Integer value code or 1-character String", new Object[0]);
        }

        @Override
        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    enum C11981 {
        ;
        static final int[] SwitchMapcomfasterxmljacksondatabindcfgCoercionAction;

        static {
            final int[] iArr = new int[CoercionAction.values().length];
            SwitchMapcomfasterxmljacksondatabindcfgCoercionAction = iArr;
            try {
                iArr[CoercionAction.Fail.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C11981.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[CoercionAction.AsNull.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C11981.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[CoercionAction.AsEmpty.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
        }
    }
    public static final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer<Integer> {
        private static final long serialVersionUID = 1;
        static final IntegerDeserializer primitiveInstance = new IntegerDeserializer(Integer.TYPE, 0);
        static final IntegerDeserializer wrapperInstance = new IntegerDeserializer(Integer.class, null);

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public boolean isCachable() {
            return true;
        }

        @Override // com.fasterxml.jackson.databind.deser.std.NumberDeserializers.PrimitiveOrWrapperDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.NumberDeserializers.PrimitiveOrWrapperDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }

        public IntegerDeserializer(final Class<Integer> cls, final Integer num) {
            super(cls, LogicalType.Integer, num, 0);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Integer deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.isExpectedNumberIntToken()) {
                return Integer.valueOf(jsonParser.getIntValue());
            }
            if (_primitive) {
                return Integer.valueOf(this._parseIntPrimitive(jsonParser, deserializationContext));
            }
            return this._parseInteger(jsonParser, deserializationContext, Integer.class);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public Integer deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
            if (jsonParser.isExpectedNumberIntToken()) {
                return Integer.valueOf(jsonParser.getIntValue());
            }
            if (_primitive) {
                return Integer.valueOf(this._parseIntPrimitive(jsonParser, deserializationContext));
            }
            return this._parseInteger(jsonParser, deserializationContext, Integer.class);
        }

        @Override
        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    public static final class LongDeserializer extends PrimitiveOrWrapperDeserializer<Long> {
        private static final long serialVersionUID = 1;
        static final LongDeserializer primitiveInstance = new LongDeserializer(Long.TYPE, 0L);
        static final LongDeserializer wrapperInstance = new LongDeserializer(Long.class, null);
        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
        public boolean isCachable() {
            return true;
        }
        public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }
        public AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }
        public LongDeserializer(final Class<Long> cls, final Long l) {
            super(cls, LogicalType.Integer, l, 0L);
        }
        public Long deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.isExpectedNumberIntToken()) {
                return Long.valueOf(jsonParser.getLongValue());
            }
            if (_primitive) {
                return Long.valueOf(this._parseLongPrimitive(jsonParser, deserializationContext));
            }
            return this._parseLong(jsonParser, deserializationContext, Long.class);
        }
    }
    public static class FloatDeserializer extends PrimitiveOrWrapperDeserializer<Float> {
        private static final long serialVersionUID = 1;
        static final FloatDeserializer primitiveInstance = new FloatDeserializer(Float.TYPE, Float.valueOf(0.0f));
        static final FloatDeserializer wrapperInstance = new FloatDeserializer(Float.class, null);

        @Override // com.fasterxml.jackson.databind.deser.std.NumberDeserializers.PrimitiveOrWrapperDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.NumberDeserializers.PrimitiveOrWrapperDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }

        public FloatDeserializer(final Class<Float> cls, final Float f2) {
            super(cls, LogicalType.Float, f2, Float.valueOf(0.0f));
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Float deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
                return Float.valueOf(jsonParser.getFloatValue());
            }
            if (_primitive) {
                return Float.valueOf(this._parseFloatPrimitive(jsonParser, deserializationContext));
            }
            return this._parseFloat(jsonParser, deserializationContext);
        }

        protected final Float _parseFloat(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            final String strExtractScalarFromObject;
            final int iCurrentTokenId = jsonParser.currentTokenId();
            if (1 == iCurrentTokenId) {
                strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, _valueClass);
            } else {
                if (3 == iCurrentTokenId) {
                    return this._deserializeFromArray(jsonParser, deserializationContext);
                }
                if (11 == iCurrentTokenId) {
                    return this.getNullValue(deserializationContext);
                }
                if (6 != iCurrentTokenId) {
                    if (7 == iCurrentTokenId || 8 == iCurrentTokenId) {
                        return Float.valueOf(jsonParser.getFloatValue());
                    }
                    return (Float) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
                }
                strExtractScalarFromObject = jsonParser.getText();
            }
            final Float f_checkFloatSpecialValue = this._checkFloatSpecialValue(strExtractScalarFromObject);
            if (null != f_checkFloatSpecialValue) {
                return f_checkFloatSpecialValue;
            }
            final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject);
            if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion) {
                return this.getNullValue(deserializationContext);
            }
            if (CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
                return (Float) this.getEmptyValue(deserializationContext);
            }
            final String strTrim = strExtractScalarFromObject.trim();
            if (this._checkTextualNull(deserializationContext, strTrim)) {
                return this.getNullValue(deserializationContext);
            }
            try {
                return Float.valueOf(Float.parseFloat(strTrim));
            } catch (final IllegalArgumentException unused) {
                return (Float) deserializationContext.handleWeirdStringValue(_valueClass, strTrim, "not a valid `Float` value", new Object[0]);
            }
        }

        @Override
        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    public static class DoubleDeserializer extends PrimitiveOrWrapperDeserializer<Double> {
        private static final long serialVersionUID = 1;
        static final DoubleDeserializer primitiveInstance = new DoubleDeserializer(Double.TYPE, Double.valueOf(0.0d));
        static final DoubleDeserializer wrapperInstance = new DoubleDeserializer(Double.class, null);

        @Override // com.fasterxml.jackson.databind.deser.std.NumberDeserializers.PrimitiveOrWrapperDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
            return super.getEmptyValue(deserializationContext);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.NumberDeserializers.PrimitiveOrWrapperDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public AccessPattern getNullAccessPattern() {
            return super.getNullAccessPattern();
        }

        public DoubleDeserializer(final Class<Double> cls, final Double d2) {
            super(cls, LogicalType.Float, d2, Double.valueOf(0.0d));
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Double deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
                return Double.valueOf(jsonParser.getDoubleValue());
            }
            if (_primitive) {
                return Double.valueOf(this._parseDoublePrimitive(jsonParser, deserializationContext));
            }
            return this._parseDouble(jsonParser, deserializationContext);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public Double deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
            if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
                return Double.valueOf(jsonParser.getDoubleValue());
            }
            if (_primitive) {
                return Double.valueOf(this._parseDoublePrimitive(jsonParser, deserializationContext));
            }
            return this._parseDouble(jsonParser, deserializationContext);
        }

        @Override
        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }

        protected final Double _parseDouble(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            final String strExtractScalarFromObject;
            final int iCurrentTokenId = jsonParser.currentTokenId();
            if (1 == iCurrentTokenId) {
                strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, _valueClass);
            } else {
                if (3 == iCurrentTokenId) {
                    return this._deserializeFromArray(jsonParser, deserializationContext);
                }
                if (11 == iCurrentTokenId) {
                    return this.getNullValue(deserializationContext);
                }
                if (6 != iCurrentTokenId) {
                    if (7 == iCurrentTokenId || 8 == iCurrentTokenId) {
                        return Double.valueOf(jsonParser.getDoubleValue());
                    }
                    return (Double) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
                }
                strExtractScalarFromObject = jsonParser.getText();
            }
            final Double d_checkDoubleSpecialValue = this._checkDoubleSpecialValue(strExtractScalarFromObject);
            if (null != d_checkDoubleSpecialValue) {
                return d_checkDoubleSpecialValue;
            }
            final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject);
            if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion) {
                return this.getNullValue(deserializationContext);
            }
            if (CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
                return (Double) this.getEmptyValue(deserializationContext);
            }
            final String strTrim = strExtractScalarFromObject.trim();
            if (this._checkTextualNull(deserializationContext, strTrim)) {
                return this.getNullValue(deserializationContext);
            }
            try {
                return Double.valueOf(_parseDouble(strTrim));
            } catch (final IllegalArgumentException unused) {
                return (Double) deserializationContext.handleWeirdStringValue(_valueClass, strTrim, "not a valid `Double` value", new Object[0]);
            }
        }
    }
    public static class NumberDeserializer extends StdScalarDeserializer<Object> {
        public static final NumberDeserializer instance = new NumberDeserializer();

        public NumberDeserializer() {
            super(Number.class);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public final LogicalType logicalType() {
            return LogicalType.Integer;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException, NumberFormatException {
            final String strExtractScalarFromObject;
            final int iCurrentTokenId = jsonParser.currentTokenId();
            if (1 == iCurrentTokenId) {
                strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, _valueClass);
            } else {
                if (3 == iCurrentTokenId) {
                    return this._deserializeFromArray(jsonParser, deserializationContext);
                }
                if (6 != iCurrentTokenId) {
                    if (7 == iCurrentTokenId) {
                        if (deserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                            return this._coerceIntegral(jsonParser, deserializationContext);
                        }
                        return jsonParser.getNumberValue();
                    }
                    if (8 == iCurrentTokenId) {
                        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS) && !jsonParser.isNaN()) {
                            return jsonParser.getDecimalValue();
                        }
                        return jsonParser.getNumberValue();
                    }
                    return deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
                }
                strExtractScalarFromObject = jsonParser.getText();
            }
            final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject);
            if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion) {
                return this.getNullValue(deserializationContext);
            }
            if (CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
                return this.getEmptyValue(deserializationContext);
            }
            final String strTrim = strExtractScalarFromObject.trim();
            if (this._hasTextualNull(strTrim)) {
                return this.getNullValue(deserializationContext);
            }
            if (this._isPosInf(strTrim)) {
                return Double.valueOf(Double.POSITIVE_INFINITY);
            }
            if (this._isNegInf(strTrim)) {
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            if (this._isNaN(strTrim)) {
                return Double.valueOf(Double.NaN);
            }
            try {
                if (!this._isIntNumber(strTrim)) {
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return new BigDecimal(strTrim);
                    }
                    return Double.valueOf(strTrim);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                    return new BigInteger(strTrim);
                }
                final long j2 = Long.parseLong(strTrim);
                if (!deserializationContext.isEnabled(DeserializationFeature.USE_LONG_FOR_INTS) && 2147483647L >= j2 && -2147483648L <= j2) {
                    return Integer.valueOf((int) j2);
                }
                return Long.valueOf(j2);
            } catch (final IllegalArgumentException unused) {
                return deserializationContext.handleWeirdStringValue(_valueClass, strTrim, "not a valid number");
            }
        }
         public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
            final int iCurrentTokenId = jsonParser.currentTokenId();
            if (6 == iCurrentTokenId || 7 == iCurrentTokenId || 8 == iCurrentTokenId) {
                return this.deserialize(jsonParser, deserializationContext);
            }
            return typeDeserializer.deserializeTypedFromScalar(jsonParser, deserializationContext);
        }
        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    public static class BigIntegerDeserializer extends StdScalarDeserializer<BigInteger> {
        public static final BigIntegerDeserializer instance = new BigIntegerDeserializer();

        public BigIntegerDeserializer() {
            super(BigInteger.class);
        }

        public Object getEmptyValue(final DeserializationContext deserializationContext) {
            return BigInteger.ZERO;
        }

        public final LogicalType logicalType() {
            return LogicalType.Integer;
        }

        public BigInteger deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            final String strExtractScalarFromObject;
            if (jsonParser.isExpectedNumberIntToken()) {
                return jsonParser.getBigIntegerValue();
            }
            final int iCurrentTokenId = jsonParser.currentTokenId();
            if (1 == iCurrentTokenId) {
                strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, _valueClass);
            } else {
                if (3 == iCurrentTokenId) {
                    return this._deserializeFromArray(jsonParser, deserializationContext);
                }
                if (6 != iCurrentTokenId) {
                    if (8 == iCurrentTokenId) {
                        final CoercionAction coercionAction_checkFloatToIntCoercion = this._checkFloatToIntCoercion(jsonParser, deserializationContext, _valueClass);
                        if (CoercionAction.AsNull == coercionAction_checkFloatToIntCoercion) {
                            return this.getNullValue(deserializationContext);
                        }
                        if (CoercionAction.AsEmpty == coercionAction_checkFloatToIntCoercion) {
                            return (BigInteger) this.getEmptyValue(deserializationContext);
                        }
                        return jsonParser.getDecimalValue().toBigInteger();
                    }
                    return (BigInteger) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
                }
                strExtractScalarFromObject = jsonParser.getText();
            }
            final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject);
            if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion) {
                return this.getNullValue(deserializationContext);
            }
            if (CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
                return (BigInteger) this.getEmptyValue(deserializationContext);
            }
            final String strTrim = strExtractScalarFromObject.trim();
            if (this._hasTextualNull(strTrim)) {
                return this.getNullValue(deserializationContext);
            }
            try {
                return new BigInteger(strTrim);
            } catch (final IllegalArgumentException unused) {
                return (BigInteger) deserializationContext.handleWeirdStringValue(_valueClass, strTrim, "not a valid representation", new Object[0]);
            }
        }

        @Override
        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    public static class BigDecimalDeserializer extends StdScalarDeserializer<BigDecimal> {
        public static final BigDecimalDeserializer instance = new BigDecimalDeserializer();

        public BigDecimalDeserializer() {
            super(BigDecimal.class);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Object getEmptyValue(final DeserializationContext deserializationContext) {
            return BigDecimal.ZERO;
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public final LogicalType logicalType() {
            return LogicalType.Float;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public BigDecimal deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            final String strExtractScalarFromObject;
            final int iCurrentTokenId = jsonParser.currentTokenId();
            if (1 == iCurrentTokenId) {
                strExtractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, _valueClass);
            } else {
                if (3 == iCurrentTokenId) {
                    return this._deserializeFromArray(jsonParser, deserializationContext);
                }
                if (6 != iCurrentTokenId) {
                    if (7 == iCurrentTokenId || 8 == iCurrentTokenId) {
                        return jsonParser.getDecimalValue();
                    }
                    return (BigDecimal) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
                }
                strExtractScalarFromObject = jsonParser.getText();
            }
            final CoercionAction coercionAction_checkFromStringCoercion = this._checkFromStringCoercion(deserializationContext, strExtractScalarFromObject);
            if (CoercionAction.AsNull == coercionAction_checkFromStringCoercion) {
                return this.getNullValue(deserializationContext);
            }
            if (CoercionAction.AsEmpty == coercionAction_checkFromStringCoercion) {
                return (BigDecimal) this.getEmptyValue(deserializationContext);
            }
            final String strTrim = strExtractScalarFromObject.trim();
            if (this._hasTextualNull(strTrim)) {
                return this.getNullValue(deserializationContext);
            }
            try {
                return new BigDecimal(strTrim);
            } catch (final IllegalArgumentException unused) {
                return (BigDecimal) deserializationContext.handleWeirdStringValue(_valueClass, strTrim, "not a valid representation", new Object[0]);
            }
        }

        @Override
        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
}
