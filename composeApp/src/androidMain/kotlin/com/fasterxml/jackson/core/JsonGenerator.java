package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class JsonGenerator implements Closeable, Flushable {
    protected static final JacksonFeatureSet<StreamWriteCapability> DEFAULT_BINARY_WRITE_CAPABILITIES;
    protected static final JacksonFeatureSet<StreamWriteCapability> DEFAULT_TEXTUAL_WRITE_CAPABILITIES;
    protected static final JacksonFeatureSet<StreamWriteCapability> DEFAULT_WRITE_CAPABILITIES;
    protected PrettyPrinter _cfgPrettyPrinter;

    public boolean canOmitFields() {
        return true;
    }

    public boolean canWriteBinaryNatively() {
        return false;
    }

    public boolean canWriteObjectId() {
        return false;
    }

    public boolean canWriteTypeId() {
        return false;
    }
    public abstract void close() throws IOException;
    public abstract JsonGenerator disable(Feature feature);
    public abstract void flush() throws IOException;

    public abstract int getFeatureMask();

    public abstract JsonStreamContext getOutputContext();

    public abstract boolean isEnabled(Feature feature);

    public JsonGenerator overrideFormatFeatures(final int i2, final int i3) {
        return this;
    }

    public JsonGenerator setCharacterEscapes(final CharacterEscapes characterEscapes) {
        return this;
    }
    public abstract JsonGenerator setFeatureMask(int i2);

    public JsonGenerator setHighestNonEscapedChar(final int i2) {
        return this;
    }

    public abstract int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i2) throws IOException;

    public abstract void writeBinary(Base64Variant base64Variant, byte[] bArr, int i2, int i3) throws IOException;

    public abstract void writeBoolean(boolean z) throws IOException;

    public abstract void writeEndArray() throws IOException;

    public abstract void writeEndObject() throws IOException;

    public abstract void writeFieldName(SerializableString serializableString) throws IOException;

    public abstract void writeFieldName(String str) throws IOException;

    public abstract void writeNull() throws IOException;

    public abstract void writeNumber(double d2) throws IOException;

    public abstract void writeNumber(float f2) throws IOException;

    public abstract void writeNumber(int i2) throws IOException;

    public abstract void writeNumber(long j2) throws IOException;

    public abstract void writeNumber(String str) throws IOException;

    public abstract void writeNumber(BigDecimal bigDecimal) throws IOException;

    public abstract void writeNumber(BigInteger bigInteger) throws IOException;

    public abstract void writeObject(Object obj) throws IOException;

    public void writeOmittedField(final String str) throws IOException {
    }

    public abstract void writeRaw(char c2) throws IOException;

    public abstract void writeRaw(String str) throws IOException;

    public abstract void writeRaw(char[] cArr, int i2, int i3) throws IOException;

    public abstract void writeRawValue(String str) throws IOException;

    public abstract void writeStartArray() throws IOException;

    public abstract void writeStartObject() throws IOException;

    public abstract void writeString(SerializableString serializableString) throws IOException;

    public abstract void writeString(String str) throws IOException;

    public abstract void writeString(char[] cArr, int i2, int i3) throws IOException;

    static {
        final JacksonFeatureSet<StreamWriteCapability> jacksonFeatureSetFromDefaults = JacksonFeatureSet.fromDefaults(StreamWriteCapability.values());
        DEFAULT_WRITE_CAPABILITIES = jacksonFeatureSetFromDefaults;
        DEFAULT_TEXTUAL_WRITE_CAPABILITIES = jacksonFeatureSetFromDefaults.with(StreamWriteCapability.CAN_WRITE_FORMATTED_NUMBERS);
        DEFAULT_BINARY_WRITE_CAPABILITIES = jacksonFeatureSetFromDefaults.with(StreamWriteCapability.CAN_WRITE_BINARY_NATIVELY);
    }

    public enum Feature {
        AUTO_CLOSE_TARGET(true),
        AUTO_CLOSE_JSON_CONTENT(true),
        FLUSH_PASSED_TO_STREAM(true),
        QUOTE_FIELD_NAMES(true),
        QUOTE_NON_NUMERIC_NUMBERS(true),
        ESCAPE_NON_ASCII(false),
        WRITE_NUMBERS_AS_STRINGS(false),
        WRITE_BIGDECIMAL_AS_PLAIN(false),
        STRICT_DUPLICATE_DETECTION(false),
        IGNORE_UNKNOWN(false);

        private final boolean _defaultState;
        private final int _mask = 1 << this.ordinal();

        public static int collectDefaults() {
            int mask = 0;
            for (final Feature feature : Feature.values()) {
                if (feature.enabledByDefault()) {
                    mask |= feature._mask;
                }
            }
            return mask;
        }

        Feature(final boolean z) {
            _defaultState = z;
        }

        public boolean enabledByDefault() {
            return _defaultState;
        }

        public boolean enabledIn(final int i2) {
            return 0 != (i2 & this._mask);
        }

        public int getMask() {
            return _mask;
        }
    }

    protected JsonGenerator() {
    }

    public void setCurrentValue(final Object obj) {
        final JsonStreamContext outputContext = this.getOutputContext();
        if (null != outputContext) {
            outputContext.setCurrentValue(obj);
        }
    }

    public JsonGenerator overrideStdFeatures(final int i2, final int i3) {
        return this.setFeatureMask((i2 & i3) | (this.getFeatureMask() & (~i3)));
    }

    public JsonGenerator setPrettyPrinter(final PrettyPrinter prettyPrinter) {
        _cfgPrettyPrinter = prettyPrinter;
        return this;
    }

    public PrettyPrinter getPrettyPrinter() {
        return _cfgPrettyPrinter;
    }

    public JsonGenerator setRootValueSeparator(final SerializableString serializableString) {
        throw new UnsupportedOperationException();
    }
    public void writeStartArray(final int i2) throws IOException {
        this.writeStartArray();
    }

    public void writeStartArray(final Object obj) throws IOException {
        this.writeStartArray();
        this.setCurrentValue(obj);
    }

    public void writeStartArray(final Object obj, final int i2) throws IOException {
        this.writeStartArray(i2);
        this.setCurrentValue(obj);
    }

    public void writeStartObject(final Object obj) throws IOException {
        this.writeStartObject();
        this.setCurrentValue(obj);
    }

    public void writeStartObject(final Object obj, final int i2) throws IOException {
        this.writeStartObject();
        this.setCurrentValue(obj);
    }

    public void writeFieldId(final long j2) throws IOException {
        this.writeFieldName(Long.toString(j2));
    }

    public void writeArray(final int[] iArr, int i2, final int i3) throws IOException {
        if (null == iArr) {
            throw new IllegalArgumentException("null array");
        }
        this._verifyOffsets(iArr.length, i2, i3);
        this.writeStartArray(iArr, i3);
        final int i4 = i3 + i2;
        while (i2 < i4) {
            this.writeNumber(iArr[i2]);
            i2++;
        }
        this.writeEndArray();
    }

    public void writeArray(final long[] jArr, int i2, final int i3) throws IOException {
        if (null == jArr) {
            throw new IllegalArgumentException("null array");
        }
        this._verifyOffsets(jArr.length, i2, i3);
        this.writeStartArray(jArr, i3);
        final int i4 = i3 + i2;
        while (i2 < i4) {
            this.writeNumber(jArr[i2]);
            i2++;
        }
        this.writeEndArray();
    }

    public void writeArray(final double[] dArr, int i2, final int i3) throws IOException {
        if (null == dArr) {
            throw new IllegalArgumentException("null array");
        }
        this._verifyOffsets(dArr.length, i2, i3);
        this.writeStartArray(dArr, i3);
        final int i4 = i3 + i2;
        while (i2 < i4) {
            this.writeNumber(dArr[i2]);
            i2++;
        }
        this.writeEndArray();
    }

    public void writeRaw(final SerializableString serializableString) throws IOException {
        this.writeRaw(serializableString.getValue());
    }

    public void writeRawValue(final SerializableString serializableString) throws IOException {
        this.writeRawValue(serializableString.getValue());
    }

    public void writeBinary(final byte[] bArr, final int i2, final int i3) throws IOException {
        this.writeBinary(Base64Variants.getDefaultVariant(), bArr, i2, i3);
    }

    public void writeBinary(final byte[] bArr) throws IOException {
        this.writeBinary(Base64Variants.getDefaultVariant(), bArr, 0, bArr.length);
    }

    public int writeBinary(final InputStream inputStream, final int i2) throws IOException {
        return this.writeBinary(Base64Variants.getDefaultVariant(), inputStream, i2);
    }

    public void writeNumber(final short s) throws IOException {
        this.writeNumber((int) s);
    }

    public void writeEmbeddedObject(final Object obj) throws IOException {
        if (null == obj) {
            this.writeNull();
        } else {
            if (obj instanceof byte[]) {
                this.writeBinary((byte[]) obj);
                return;
            }
            throw new JsonGenerationException("No native support for writing embedded objects of type " + obj.getClass().getName(), this);
        }
    }

    public void writeObjectId(final Object obj) throws IOException {
        throw new JsonGenerationException("No native support for writing Object Ids", this);
    }

    public void writeObjectRef(final Object obj) throws IOException {
        throw new JsonGenerationException("No native support for writing Object Ids", this);
    }

    public void writeTypeId(final Object obj) throws IOException {
        throw new JsonGenerationException("No native support for writing Type Ids", this);
    }

    public WritableTypeId writeTypePrefix(final WritableTypeId writableTypeId) throws IOException {
        final Object obj = writableTypeId.f798id;
        final JsonToken jsonToken = writableTypeId.valueShape;
        if (this.canWriteTypeId()) {
            writableTypeId.wrapperWritten = false;
            this.writeTypeId(obj);
        } else {
            final String strValueOf = obj instanceof String ? (String) obj : String.valueOf(obj);
            writableTypeId.wrapperWritten = true;
            WritableTypeId.Inclusion inclusion = writableTypeId.include;
            if (JsonToken.START_OBJECT != jsonToken && inclusion.requiresObjectContext()) {
                inclusion = WritableTypeId.Inclusion.WRAPPER_ARRAY;
                writableTypeId.include = inclusion;
            }
            final int i2 = C11861.f791xa51b40dc[inclusion.ordinal()];
            if (1 != i2 && 2 != i2) {
                if (3 == i2) {
                    this.writeStartObject(writableTypeId.forValue);
                    this.writeStringField(writableTypeId.asProperty, strValueOf);
                    return writableTypeId;
                }
                if (4 == i2) {
                    this.writeStartObject();
                    this.writeFieldName(strValueOf);
                } else {
                    this.writeStartArray();
                    this.writeString(strValueOf);
                }
            }
        }
        if (JsonToken.START_OBJECT == jsonToken) {
            this.writeStartObject(writableTypeId.forValue);
        } else if (JsonToken.START_ARRAY == jsonToken) {
            this.writeStartArray();
        }
        return writableTypeId;
    }

    enum C11861 {
        ;

        /* renamed from: SwitchMapcomfasterxmljacksoncoretypeWritableTypeIdInclusion */
        static final int[] f791xa51b40dc;

        static {
            final int[] iArr = new int[WritableTypeId.Inclusion.values().length];
            f791xa51b40dc = iArr;
            try {
                iArr[WritableTypeId.Inclusion.PARENT_PROPERTY.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C11861.f791xa51b40dc[WritableTypeId.Inclusion.PAYLOAD_PROPERTY.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C11861.f791xa51b40dc[WritableTypeId.Inclusion.METADATA_PROPERTY.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C11861.f791xa51b40dc[WritableTypeId.Inclusion.WRAPPER_OBJECT.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C11861.f791xa51b40dc[WritableTypeId.Inclusion.WRAPPER_ARRAY.ordinal()] = 5;
            } catch (final NoSuchFieldError unused5) {
            }
        }
    }

    public WritableTypeId writeTypeSuffix(final WritableTypeId writableTypeId) throws IOException {
        final JsonToken jsonToken = writableTypeId.valueShape;
        if (JsonToken.START_OBJECT == jsonToken) {
            this.writeEndObject();
        } else if (JsonToken.START_ARRAY == jsonToken) {
            this.writeEndArray();
        }
        if (writableTypeId.wrapperWritten) {
            final int i2 = C11861.f791xa51b40dc[writableTypeId.include.ordinal()];
            if (1 == i2) {
                final Object obj = writableTypeId.f798id;
                this.writeStringField(writableTypeId.asProperty, obj instanceof String ? (String) obj : String.valueOf(obj));
            } else if (2 != i2 && 3 != i2) {
                if (5 == i2) {
                    this.writeEndArray();
                } else {
                    this.writeEndObject();
                }
            }
        }
        return writableTypeId;
    }

    public void writeStringField(final String str, final String str2) throws IOException {
        this.writeFieldName(str);
        this.writeString(str2);
    }

    protected void _reportError(final String str) throws JsonGenerationException {
        throw new JsonGenerationException(str, this);
    }

    protected final void _throwInternal() {
        VersionUtil.throwInternal();
    }

    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Operation not supported by generator of type " + this.getClass().getName());
    }

    protected final void _verifyOffsets(final int i2, final int i3, final int i4) {
        if (0 > i3 || i3 + i4 > i2) {
            throw new IllegalArgumentException(String.format("invalid argument(s) (offset=%d, length=%d) for input array of %d element", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i2)));
        }
    }

    protected void _writeSimpleObject(final Object obj) throws IOException {
        if (null == obj) {
            this.writeNull();
            return;
        }
        if (obj instanceof String) {
            this.writeString((String) obj);
            return;
        }
        if (obj instanceof Number number) {
            if (number instanceof Integer) {
                this.writeNumber(number.intValue());
                return;
            }
            if (number instanceof Long) {
                this.writeNumber(number.longValue());
                return;
            }
            if (number instanceof Double) {
                this.writeNumber(number.doubleValue());
                return;
            }
            if (number instanceof Float) {
                this.writeNumber(number.floatValue());
                return;
            }
            if (number instanceof Short) {
                this.writeNumber(number.shortValue());
                return;
            }
            if (number instanceof Byte) {
                this.writeNumber(number.byteValue());
                return;
            }
            if (number instanceof BigInteger) {
                this.writeNumber((BigInteger) number);
                return;
            }
            if (number instanceof BigDecimal) {
                this.writeNumber((BigDecimal) number);
                return;
            } else if (number instanceof AtomicInteger) {
                this.writeNumber(((AtomicInteger) number).get());
                return;
            } else if (number instanceof AtomicLong) {
                this.writeNumber(((AtomicLong) number).get());
                return;
            }
        } else if (obj instanceof byte[]) {
            this.writeBinary((byte[]) obj);
            return;
        } else if (obj instanceof Boolean) {
            this.writeBoolean(((Boolean) obj).booleanValue());
            return;
        } else if (obj instanceof AtomicBoolean) {
            this.writeBoolean(((AtomicBoolean) obj).get());
            return;
        }
        throw new IllegalStateException("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed " + obj.getClass().getName() + ")");
    }
}
