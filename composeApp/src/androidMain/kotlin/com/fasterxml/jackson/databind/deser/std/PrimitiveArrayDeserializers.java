package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsFailProvider;
import com.fasterxml.jackson.databind.exc.InvalidNullException;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public abstract class PrimitiveArrayDeserializers<T> extends StdDeserializer<T> implements ContextualDeserializer {
    private transient Object _emptyValue;
    protected final NullValueProvider _nuller;
    protected final Boolean _unwrapSingle;
    protected abstract T _concat(T t, T t2);
    protected abstract T _constructEmpty();
    protected abstract T handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;
    protected abstract PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool);
    protected PrimitiveArrayDeserializers(Class<T> cls) {
        super(cls);
        this._unwrapSingle = null;
        this._nuller = null;
    }
    protected PrimitiveArrayDeserializers(PrimitiveArrayDeserializers<?> primitiveArrayDeserializers, NullValueProvider nullValueProvider, Boolean bool) {
        super(primitiveArrayDeserializers._valueClass);
        this._unwrapSingle = bool;
        this._nuller = nullValueProvider;
    }
    public static JsonDeserializer<?> forType(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return IntDeser.instance;
        }
        if (cls == Long.TYPE) {
            return LongDeser.instance;
        }
        if (cls == Byte.TYPE) {
            return new ByteDeser();
        }
        if (cls == Short.TYPE) {
            return new ShortDeser();
        }
        if (cls == Float.TYPE) {
            return new FloatDeser();
        }
        if (cls == Double.TYPE) {
            return new DoubleDeser();
        }
        if (cls == Boolean.TYPE) {
            return new BooleanDeser();
        }
        if (cls == Character.TYPE) {
            return new CharDeser();
        }
        throw new IllegalStateException();
    }
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        NullValueProvider nullValueProviderConstructForProperty;
        Boolean boolFindFormatFeature = findFormatFeature(deserializationContext, beanProperty, this._valueClass, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        Nulls nullsFindContentNullStyle = findContentNullStyle(deserializationContext, beanProperty);
        if (nullsFindContentNullStyle == Nulls.SKIP) {
            nullValueProviderConstructForProperty = NullsConstantProvider.skipper();
        } else if (nullsFindContentNullStyle != Nulls.FAIL) {
            nullValueProviderConstructForProperty = null;
        } else if (beanProperty == null) {
            nullValueProviderConstructForProperty = NullsFailProvider.constructForRootValue(deserializationContext.constructType(this._valueClass.getComponentType()));
        } else {
            nullValueProviderConstructForProperty = NullsFailProvider.constructForProperty(beanProperty, beanProperty.getType().getContentType());
        }
        return (Objects.equals(boolFindFormatFeature, this._unwrapSingle) && nullValueProviderConstructForProperty == this._nuller) ? this : withResolved(nullValueProviderConstructForProperty, boolFindFormatFeature);
    }
    public LogicalType logicalType() {
        return LogicalType.Array;
    }
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return Boolean.TRUE;
    }
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.CONSTANT;
    }
    public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
        Object obj = this._emptyValue;
        if (obj != null) {
            return obj;
        }
        T t_constructEmpty = _constructEmpty();
        this._emptyValue = t_constructEmpty;
        return t_constructEmpty;
    }
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, T t) throws IOException {
        T tDeserialize = deserialize(jsonParser, deserializationContext);
        return (t == null || Array.getLength(t) == 0) ? tDeserialize : _concat(t, tDeserialize);
    }
    protected T handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            return _deserializeFromString(jsonParser, deserializationContext);
        }
        Boolean bool = this._unwrapSingle;
        if (bool == Boolean.TRUE || (bool == null && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            return handleSingleElementUnwrapped(jsonParser, deserializationContext);
        }
        return (T) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
    }
    protected void _failOnNull(DeserializationContext deserializationContext) throws IOException {
        throw InvalidNullException.from(deserializationContext, null, deserializationContext.constructType(this._valueClass));
    }
    static final class CharDeser extends PrimitiveArrayDeserializers<char[]> {
        private static final long serialVersionUID = 1;

        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return this;
        }

        public CharDeser() {
            super(char[].class);
        }

        private CharDeser(CharDeser charDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(charDeser, nullValueProvider, bool);
        }

        public char[] _constructEmpty() {
            return new char[0];
        }

        public char[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String text = "";
            if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
                char[] textCharacters = jsonParser.getTextCharacters();
                int textOffset = jsonParser.getTextOffset();
                int textLength = jsonParser.getTextLength();
                char[] cArr = new char[textLength];
                System.arraycopy(textCharacters, textOffset, cArr, 0, textLength);
                return cArr;
            }
            if (jsonParser.isExpectedStartArrayToken()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    JsonToken jsonTokenNextToken = jsonParser.nextToken();
                    if (jsonTokenNextToken != JsonToken.END_ARRAY) {
                        if (jsonTokenNextToken == JsonToken.VALUE_STRING) {
                            text = jsonParser.getText();
                        } else if (jsonTokenNextToken == JsonToken.VALUE_NULL) {
                            NullValueProvider nullValueProvider = this._nuller;
                            if (nullValueProvider != null) {
                                nullValueProvider.getNullValue(deserializationContext);
                            } else {
                                _verifyNullForPrimitive(deserializationContext);
                                text = "\u0000";
                            }
                        } else {
                            text = deserializationContext.handleUnexpectedToken(Character.TYPE, jsonParser).toString();
                        }
                        if (text.length() != 1) {
                            deserializationContext.reportInputMismatch(this, "Cannot convert a JSON String of length %d into a char element of char array", Integer.valueOf(text.length()));
                        }
                        sb.append(text.charAt(0));
                    } else {
                        return sb.toString().toCharArray();
                    }
                }
            } else {
                if (jsonParser.hasToken(JsonToken.VALUE_EMBEDDED_OBJECT)) {
                    Object embeddedObject = jsonParser.getEmbeddedObject();
                    if (embeddedObject == null) {
                        return null;
                    }
                    if (embeddedObject instanceof char[]) {
                        return (char[]) embeddedObject;
                    }
                    if (embeddedObject instanceof String) {
                        return ((String) embeddedObject).toCharArray();
                    }
                    if (embeddedObject instanceof byte[]) {
                        return Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false).toCharArray();
                    }
                }
                return (char[]) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
            }
        }

        public char[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return (char[]) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
        }

        public char[] _concat(char[] cArr, char[] cArr2) {
            int length = cArr.length;
            int length2 = cArr2.length;
            char[] cArrCopyOf = Arrays.copyOf(cArr, length + length2);
            System.arraycopy(cArr2, 0, cArrCopyOf, length, length2);
            return cArrCopyOf;
        }

        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    static final class BooleanDeser extends PrimitiveArrayDeserializers<boolean[]> {
        private static final long serialVersionUID = 1;

        public BooleanDeser() {
            super(boolean[].class);
        }

        private BooleanDeser(BooleanDeser booleanDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(booleanDeser, nullValueProvider, bool);
        }

        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new BooleanDeser(this, nullValueProvider, bool);
        }

        public boolean[] _constructEmpty() {
            return new boolean[0];
        }

        public boolean[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            boolean z_parseBooleanPrimitive;
            int r5 = 0;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.BooleanBuilder booleanBuilder = deserializationContext.getArrayBuilders().getBooleanBuilder();
            boolean[] zArrResetAndStart = booleanBuilder.resetAndStart();
            int r3 = 0;
            while (true) {
                Exception e;
                try {
                    JsonToken jsonTokenNextToken = jsonParser.nextToken();
                    if (jsonTokenNextToken != JsonToken.END_ARRAY) {
                        try {
                            if (jsonTokenNextToken == JsonToken.VALUE_TRUE) {
                                z_parseBooleanPrimitive = true;
                            } else {
                                if (jsonTokenNextToken != JsonToken.VALUE_FALSE) {
                                    if (jsonTokenNextToken == JsonToken.VALUE_NULL) {
                                        NullValueProvider nullValueProvider = this._nuller;
                                        if (nullValueProvider != null) {
                                            nullValueProvider.getNullValue(deserializationContext);
                                        } else {
                                            _verifyNullForPrimitive(deserializationContext);
                                        }
                                    } else {
                                        z_parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser, deserializationContext);
                                    }
                                }
                                z_parseBooleanPrimitive = false;
                            }
                            zArrResetAndStart[r3] = z_parseBooleanPrimitive;
                            r3 = r5;
                        } catch (Exception e2) {
                            e = e2;
                            r3 = r5;
                            throw JsonMappingException.wrapWithPath(e, zArrResetAndStart, booleanBuilder.bufferedSize() + r3);
                        }
                        if (r3 >= zArrResetAndStart.length) {
                            boolean[] zArrAppendCompletedChunk = booleanBuilder.appendCompletedChunk(zArrResetAndStart, r3);
                            r3 = 0;
                            zArrResetAndStart = zArrAppendCompletedChunk;
                        }
                        r5 = r3 + 1;
                    } else {
                        return booleanBuilder.completeAndClearBuffer(zArrResetAndStart, r3);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }

        public boolean[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new boolean[]{_parseBooleanPrimitive(jsonParser, deserializationContext)};
        }

        public boolean[] _concat(boolean[] zArr, boolean[] zArr2) {
            int length = zArr.length;
            int length2 = zArr2.length;
            boolean[] zArrCopyOf = Arrays.copyOf(zArr, length + length2);
            System.arraycopy(zArr2, 0, zArrCopyOf, length, length2);
            return zArrCopyOf;
        }

        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    static final class ByteDeser extends PrimitiveArrayDeserializers<byte[]> {
        private static final long serialVersionUID = 1;

        public ByteDeser() {
            super(byte[].class);
        }

        private ByteDeser(ByteDeser byteDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(byteDeser, nullValueProvider, bool);
        }

        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new ByteDeser(this, nullValueProvider, bool);
        }

        public byte[] _constructEmpty() {
            return new byte[0];
        }

        public LogicalType logicalType() {
            return LogicalType.Binary;
        }

        public byte[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            byte byteValue = 0;
            int r5 = 0;
            JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            if (jsonTokenCurrentToken == JsonToken.VALUE_STRING) {
                try {
                    return jsonParser.getBinaryValue(deserializationContext.getBase64Variant());
                } catch (JsonParseException e2) {
                    String originalMessage = e2.getOriginalMessage();
                    if (originalMessage.contains("base64")) {
                        return (byte[]) deserializationContext.handleWeirdStringValue(byte[].class, jsonParser.getText(), originalMessage, new Object[0]);
                    }
                }
            }
            if (jsonTokenCurrentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object embeddedObject = jsonParser.getEmbeddedObject();
                if (embeddedObject == null) {
                    return null;
                }
                if (embeddedObject instanceof byte[]) {
                    return (byte[]) embeddedObject;
                }
            }
            if (!jsonParser.isExpectedStartArrayToken()) {
                return this.handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.ByteBuilder byteBuilder = deserializationContext.getArrayBuilders().getByteBuilder();
            byte[] bArrResetAndStart = byteBuilder.resetAndStart();
            int r3 = 0;
            while (true) {
                try {
                    JsonToken jsonTokenNextToken = jsonParser.nextToken();
                    if (jsonTokenNextToken != JsonToken.END_ARRAY) {
                        try {
                            if (jsonTokenNextToken == JsonToken.VALUE_NUMBER_INT) {
                                byteValue = jsonParser.getByteValue();
                            } else if (jsonTokenNextToken == JsonToken.VALUE_NULL) {
                                NullValueProvider nullValueProvider = this._nuller;
                                if (nullValueProvider != null) {
                                    nullValueProvider.getNullValue(deserializationContext);
                                } else {
                                    this._verifyNullForPrimitive(deserializationContext);
                                    byteValue = 0;
                                }
                            } else {
                                byteValue = this._parseBytePrimitive(jsonParser, deserializationContext);
                            }
                            bArrResetAndStart[r3] = byteValue;
                            r3 = r5;
                        } catch (Exception e3) {
                            e = e3;
                            r3 = r5;
                            throw JsonMappingException.wrapWithPath(e, bArrResetAndStart, byteBuilder.bufferedSize() + r3);
                        }
                        if (r3 >= bArrResetAndStart.length) {
                            byte[] bArrAppendCompletedChunk = byteBuilder.appendCompletedChunk(bArrResetAndStart, r3);
                            r3 = 0;
                            bArrResetAndStart = bArrAppendCompletedChunk;
                        }
                        r5 = r3 + 1;
                    } else {
                        return byteBuilder.completeAndClearBuffer(bArrResetAndStart, r3);
                    }
                } catch (Exception e4) {
                    e = e4;
                }
            }
        }

        public byte[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            byte bByteValue;
            JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            if (jsonTokenCurrentToken == JsonToken.VALUE_NUMBER_INT) {
                bByteValue = jsonParser.getByteValue();
            } else {
                if (jsonTokenCurrentToken == JsonToken.VALUE_NULL) {
                    NullValueProvider nullValueProvider = this._nuller;
                    if (nullValueProvider != null) {
                        nullValueProvider.getNullValue(deserializationContext);
                        return (byte[]) getEmptyValue(deserializationContext);
                    }
                    _verifyNullForPrimitive(deserializationContext);
                    return null;
                }
                bByteValue = ((Number) deserializationContext.handleUnexpectedToken(this._valueClass.getComponentType(), jsonParser)).byteValue();
            }
            return new byte[]{bByteValue};
        }

        public byte[] _concat(byte[] bArr, byte[] bArr2) {
            int length = bArr.length;
            int length2 = bArr2.length;
            byte[] bArrCopyOf = Arrays.copyOf(bArr, length + length2);
            System.arraycopy(bArr2, 0, bArrCopyOf, length, length2);
            return bArrCopyOf;
        }

        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    static final class ShortDeser extends PrimitiveArrayDeserializers<short[]> {
        private static final long serialVersionUID = 1;

        public ShortDeser() {
            super(short[].class);
        }

        private ShortDeser(ShortDeser shortDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(shortDeser, nullValueProvider, bool);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new ShortDeser(this, nullValueProvider, bool);
        }

        /*  INFO: Access modifiers changed from: protected */
        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        public short[] _constructEmpty() {
            return new short[0];
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public short[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            short s_parseShortPrimitive = 0;
            int r5 = 0;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.ShortBuilder shortBuilder = deserializationContext.getArrayBuilders().getShortBuilder();
            short[] sArrResetAndStart = shortBuilder.resetAndStart();
            int r3 = 0;
            while (true) {
                Exception e;
                try {
                    JsonToken jsonTokenNextToken = jsonParser.nextToken();
                    if (jsonTokenNextToken != JsonToken.END_ARRAY) {
                        try {
                            if (jsonTokenNextToken == JsonToken.VALUE_NULL) {
                                NullValueProvider nullValueProvider = this._nuller;
                                if (nullValueProvider != null) {
                                    nullValueProvider.getNullValue(deserializationContext);
                                } else {
                                    _verifyNullForPrimitive(deserializationContext);
                                    s_parseShortPrimitive = 0;
                                }
                            } else {
                                s_parseShortPrimitive = _parseShortPrimitive(jsonParser, deserializationContext);
                            }
                            sArrResetAndStart[r3] = s_parseShortPrimitive;
                            r3 = r5;
                        } catch (Exception e2) {
                            e = e2;
                            r3 = r5;
                            throw JsonMappingException.wrapWithPath(e, sArrResetAndStart, shortBuilder.bufferedSize() + r3);
                        }
                        if (r3 >= sArrResetAndStart.length) {
                            short[] sArrAppendCompletedChunk = shortBuilder.appendCompletedChunk(sArrResetAndStart, r3);
                            r3 = 0;
                            sArrResetAndStart = sArrAppendCompletedChunk;
                        }
                        r5 = r3 + 1;
                    } else {
                        return shortBuilder.completeAndClearBuffer(sArrResetAndStart, r3);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }
        public short[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new short[]{_parseShortPrimitive(jsonParser, deserializationContext)};
        }
        public short[] _concat(short[] sArr, short[] sArr2) {
            int length = sArr.length;
            int length2 = sArr2.length;
            short[] sArrCopyOf = Arrays.copyOf(sArr, length + length2);
            System.arraycopy(sArr2, 0, sArrCopyOf, length, length2);
            return sArrCopyOf;
        }
        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    static final class IntDeser extends PrimitiveArrayDeserializers<int[]> {
        public static final IntDeser instance = new IntDeser();
        private static final long serialVersionUID = 1;

        public IntDeser() {
            super(int[].class);
        }

        private IntDeser(IntDeser intDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(intDeser, nullValueProvider, bool);
        }

        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new IntDeser(this, nullValueProvider, bool);
        }

        public int[] _constructEmpty() {
            return new int[0];
        }
        public int[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            int intValue = 0;
            int r5 = 0;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.IntBuilder intBuilder = deserializationContext.getArrayBuilders().getIntBuilder();
            int[] r1 = intBuilder.resetAndStart();
            int r3 = 0;
            while (true) {
                Exception e;
                try {
                    JsonToken jsonTokenNextToken = jsonParser.nextToken();
                    if (jsonTokenNextToken != JsonToken.END_ARRAY) {
                        try {
                            if (jsonTokenNextToken == JsonToken.VALUE_NUMBER_INT) {
                                intValue = jsonParser.getIntValue();
                            } else if (jsonTokenNextToken == JsonToken.VALUE_NULL) {
                                NullValueProvider nullValueProvider = this._nuller;
                                if (nullValueProvider != null) {
                                    nullValueProvider.getNullValue(deserializationContext);
                                } else {
                                    _verifyNullForPrimitive(deserializationContext);
                                    intValue = 0;
                                }
                            } else {
                                intValue = _parseIntPrimitive(jsonParser, deserializationContext);
                            }
                            r1[r3] = intValue;
                            r3 = r5;
                        } catch (Exception e2) {
                            e = e2;
                            r3 = r5;
                            throw JsonMappingException.wrapWithPath(e, r1, intBuilder.bufferedSize() + r3);
                        }
                        if (r3 >= r1.length) {
                            int[] r52 = intBuilder.appendCompletedChunk(r1, r3);
                            r3 = 0;
                            r1 = r52;
                        }
                        r5 = r3 + 1;
                    } else {
                        return intBuilder.completeAndClearBuffer(r1, r3);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }
        public int[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new int[]{_parseIntPrimitive(jsonParser, deserializationContext)};
        }
        public int[] _concat(int[] r3, int[] r4) {
            int length = r3.length;
            int length2 = r4.length;
            int[] r32 = Arrays.copyOf(r3, length + length2);
            System.arraycopy(r4, 0, r32, length, length2);
            return r32;
        }

        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    static final class LongDeser extends PrimitiveArrayDeserializers<long[]> {
        public static final LongDeser instance = new LongDeser();
        private static final long serialVersionUID = 1;

        public LongDeser() {
            super(long[].class);
        }

        private LongDeser(LongDeser longDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(longDeser, nullValueProvider, bool);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new LongDeser(this, nullValueProvider, bool);
        }

        public long[] _constructEmpty() {
            return new long[0];
        }
        public long[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            long longValue = 0;
            int r6 = 0;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.LongBuilder longBuilder = deserializationContext.getArrayBuilders().getLongBuilder();
            long[] jArr = longBuilder.resetAndStart();
            int r3 = 0;
            while (true) {
                try {
                    JsonToken jsonTokenNextToken = jsonParser.nextToken();
                    if (jsonTokenNextToken != JsonToken.END_ARRAY) {
                        try {
                            if (jsonTokenNextToken == JsonToken.VALUE_NUMBER_INT) {
                                longValue = jsonParser.getLongValue();
                            } else if (jsonTokenNextToken == JsonToken.VALUE_NULL) {
                                NullValueProvider nullValueProvider = this._nuller;
                                if (nullValueProvider != null) {
                                    nullValueProvider.getNullValue(deserializationContext);
                                } else {
                                    _verifyNullForPrimitive(deserializationContext);
                                    longValue = 0;
                                }
                            } else {
                                longValue = _parseLongPrimitive(jsonParser, deserializationContext);
                            }
                            jArr[r3] = longValue;
                            r3 = r6;
                        } catch (Exception e2) {
                            e = e2;
                            r3 = r6;
                            throw JsonMappingException.wrapWithPath(e, jArr, longBuilder.bufferedSize() + r3);
                        }
                        if (r3 >= jArr.length) {
                            long[] jArr2 = longBuilder.appendCompletedChunk(jArr, r3);
                            r3 = 0;
                            jArr = jArr2;
                        }
                        r6 = r3 + 1;
                    } else {
                        return longBuilder.completeAndClearBuffer(jArr, r3);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }

        public long[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new long[]{_parseLongPrimitive(jsonParser, deserializationContext)};
        }

        public long[] _concat(long[] jArr, long[] jArr2) {
            int length = jArr.length;
            int length2 = jArr2.length;
            long[] jArrCopyOf = Arrays.copyOf(jArr, length + length2);
            System.arraycopy(jArr2, 0, jArrCopyOf, length, length2);
            return jArrCopyOf;
        }

        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    static final class FloatDeser extends PrimitiveArrayDeserializers<float[]> {
        private static final long serialVersionUID = 1;

        public FloatDeser() {
            super(float[].class);
        }

        private FloatDeser(FloatDeser floatDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(floatDeser, nullValueProvider, bool);
        }

        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new FloatDeser(this, nullValueProvider, bool);
        }

        public float[] _constructEmpty() {
            return new float[0];
        }
        public float[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            NullValueProvider nullValueProvider;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.FloatBuilder floatBuilder = deserializationContext.getArrayBuilders().getFloatBuilder();
            float[] fArr = floatBuilder.resetAndStart();
            int r3 = 0;
            while (true) {
                Exception e;
                try {
                    JsonToken jsonTokenNextToken = jsonParser.nextToken();
                    if (jsonTokenNextToken != JsonToken.END_ARRAY) {
                        if (jsonTokenNextToken == JsonToken.VALUE_NULL && (nullValueProvider = this._nuller) != null) {
                            nullValueProvider.getNullValue(deserializationContext);
                        } else {
                            float f_parseFloatPrimitive = _parseFloatPrimitive(jsonParser, deserializationContext);
                            if (r3 >= fArr.length) {
                                float[] fArr2 = floatBuilder.appendCompletedChunk(fArr, r3);
                                r3 = 0;
                                fArr = fArr2;
                            }
                            int r5 = r3 + 1;
                            try {
                                fArr[r3] = f_parseFloatPrimitive;
                                r3 = r5;
                            } catch (Exception e2) {
                                e = e2;
                                r3 = r5;
                                throw JsonMappingException.wrapWithPath(e, fArr, floatBuilder.bufferedSize() + r3);
                            }
                        }
                    } else {
                        return floatBuilder.completeAndClearBuffer(fArr, r3);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }
        public float[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new float[]{_parseFloatPrimitive(jsonParser, deserializationContext)};
        }

        public float[] _concat(float[] fArr, float[] fArr2) {
            int length = fArr.length;
            int length2 = fArr2.length;
            float[] fArrCopyOf = Arrays.copyOf(fArr, length + length2);
            System.arraycopy(fArr2, 0, fArrCopyOf, length, length2);
            return fArrCopyOf;
        }

        @Override
        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
    static final class DoubleDeser extends PrimitiveArrayDeserializers<double[]> {
        private static final long serialVersionUID = 1;

        public DoubleDeser() {
            super(double[].class);
        }

        private DoubleDeser(DoubleDeser doubleDeser, NullValueProvider nullValueProvider, Boolean bool) {
            super(doubleDeser, nullValueProvider, bool);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers
        protected PrimitiveArrayDeserializers<?> withResolved(NullValueProvider nullValueProvider, Boolean bool) {
            return new DoubleDeser(this, nullValueProvider, bool);
        }

        public double[] _constructEmpty() {
            return new double[0];
        }

        public double[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            NullValueProvider nullValueProvider;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.DoubleBuilder doubleBuilder = deserializationContext.getArrayBuilders().getDoubleBuilder();
            double[] dArr = doubleBuilder.resetAndStart();
            int r3 = 0;
            while (true) {
                Exception e;
                try {
                    JsonToken jsonTokenNextToken = jsonParser.nextToken();
                    if (jsonTokenNextToken != JsonToken.END_ARRAY) {
                        if (jsonTokenNextToken == JsonToken.VALUE_NULL && (nullValueProvider = this._nuller) != null) {
                            nullValueProvider.getNullValue(deserializationContext);
                        } else {
                            double d_parseDoublePrimitive = _parseDoublePrimitive(jsonParser, deserializationContext);
                            if (r3 >= dArr.length) {
                                double[] dArr2 = doubleBuilder.appendCompletedChunk(dArr, r3);
                                r3 = 0;
                                dArr = dArr2;
                            }
                            int r6 = r3 + 1;
                            try {
                                dArr[r3] = d_parseDoublePrimitive;
                                r3 = r6;
                            } catch (Exception e2) {
                                e = e2;
                                r3 = r6;
                                throw JsonMappingException.wrapWithPath(e, dArr, doubleBuilder.bufferedSize() + r3);
                            }
                        }
                    } else {
                        return doubleBuilder.completeAndClearBuffer(dArr, r3);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
        }

        public double[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new double[]{_parseDoublePrimitive(jsonParser, deserializationContext)};
        }

        public double[] _concat(double[] dArr, double[] dArr2) {
            int length = dArr.length;
            int length2 = dArr2.length;
            double[] dArrCopyOf = Arrays.copyOf(dArr, length + length2);
            System.arraycopy(dArr2, 0, dArrCopyOf, length, length2);
            return dArrCopyOf;
        }

        protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
            return null;
        }
    }
}
