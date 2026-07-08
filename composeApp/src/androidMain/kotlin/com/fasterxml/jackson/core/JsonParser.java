package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.core.util.RequestPayload;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class JsonParser implements Closeable {
    protected static final JacksonFeatureSet<StreamReadCapability> DEFAULT_READ_CAPABILITIES = JacksonFeatureSet.fromDefaults(StreamReadCapability.values());
    public int _features;
    protected RequestPayload _requestPayload;
    public enum NumberType {
        INT,
        LONG,
        BIG_INTEGER,
        FLOAT,
        DOUBLE,
        BIG_DECIMAL
    }
    public boolean canReadObjectId() {
        return false;
    }
    public boolean canReadTypeId() {
        return false;
    }
    public abstract void clearCurrentToken();
    public abstract void close() throws IOException;
    public abstract BigInteger getBigIntegerValue() throws IOException;
    public abstract byte[] getBinaryValue(Base64Variant base64Variant) throws IOException;
    public abstract ObjectCodec getCodec();
    public abstract JsonLocation getCurrentLocation();
    public abstract String getCurrentName() throws IOException;
    public abstract JsonToken getCurrentToken();
    public abstract int getCurrentTokenId();
    public abstract BigDecimal getDecimalValue() throws IOException;
    public abstract double getDoubleValue() throws IOException;
    public Object getEmbeddedObject() throws IOException {
        return null;
    }
    public abstract float getFloatValue() throws IOException;
    public abstract int getIntValue() throws IOException;
    public abstract long getLongValue() throws IOException;
    public abstract NumberType getNumberType() throws IOException;
    public abstract Number getNumberValue() throws IOException;
    public Object getObjectId() throws IOException {
        return null;
    }
    public abstract JsonStreamContext getParsingContext();
    public abstract String getText() throws IOException;
    public abstract char[] getTextCharacters() throws IOException;
    public abstract int getTextLength() throws IOException;
    public abstract int getTextOffset() throws IOException;
    public abstract JsonLocation getTokenLocation();
    public Object getTypeId() throws IOException {
        return null;
    }
    public int getValueAsInt(final int i2) throws IOException {
        return i2;
    }
    public long getValueAsLong(final long j2) throws IOException {
        return j2;
    }
    public abstract String getValueAsString(String str) throws IOException;
    public abstract boolean hasCurrentToken();
    public abstract boolean hasTextCharacters();
    public abstract boolean hasToken(JsonToken jsonToken);
    public abstract boolean hasTokenId(int i2);
    public boolean isNaN() throws IOException {
        return false;
    }
    public abstract JsonToken nextToken() throws IOException;
    public abstract JsonToken nextValue() throws IOException;
    public JsonParser overrideFormatFeatures(final int i2, final int i3) {
        return this;
    }
    public boolean requiresCustomCodec() {
        return false;
    }
    public abstract JsonParser skipChildren() throws IOException;
    public enum Feature {
        AUTO_CLOSE_SOURCE(true),
        ALLOW_COMMENTS(false),
        ALLOW_YAML_COMMENTS(false),
        ALLOW_UNQUOTED_FIELD_NAMES(false),
        ALLOW_SINGLE_QUOTES(false),
        ALLOW_UNQUOTED_CONTROL_CHARS(false),
        ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
        ALLOW_NUMERIC_LEADING_ZEROS(false),
        ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS(false),
        ALLOW_NON_NUMERIC_NUMBERS(false),
        ALLOW_MISSING_VALUES(false),
        ALLOW_TRAILING_COMMA(false),
        STRICT_DUPLICATE_DETECTION(false),
        IGNORE_UNDEFINED(false),
        INCLUDE_SOURCE_IN_LOCATION(true);

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
    protected JsonParser() {
    }
    protected JsonParser(final int i2) {
        _features = i2;
    }
    public void setCurrentValue(final Object obj) {
        final JsonStreamContext parsingContext = this.getParsingContext();
        if (null != parsingContext) {
            parsingContext.setCurrentValue(obj);
        }
    }
    public void setSchema(final FormatSchema formatSchema) {
        throw new UnsupportedOperationException("Parser of type " + this.getClass().getName() + " does not support schema of type '" + formatSchema.getSchemaType() + "'");
    }
    public JacksonFeatureSet<StreamReadCapability> getReadCapabilities() {
        return JsonParser.DEFAULT_READ_CAPABILITIES;
    }
    public JsonParser enable(final Feature feature) {
        _features = feature.getMask() | _features;
        return this;
    }
    public boolean isEnabled(final Feature feature) {
        return feature.enabledIn(_features);
    }
    public JsonParser setFeatureMask(final int i2) {
        _features = i2;
        return this;
    }
    public JsonParser overrideStdFeatures(final int i2, final int i3) {
        return this.setFeatureMask((i2 & i3) | (_features & (~i3)));
    }
    public String nextFieldName() throws IOException {
        if (JsonToken.FIELD_NAME == nextToken()) {
            return this.getCurrentName();
        }
        return null;
    }
    public String nextTextValue() throws IOException {
        if (JsonToken.VALUE_STRING == nextToken()) {
            return this.getText();
        }
        return null;
    }
    public JsonToken currentToken() {
        return this.getCurrentToken();
    }
    public int currentTokenId() {
        return this.getCurrentTokenId();
    }
    public boolean isExpectedStartArrayToken() {
        return JsonToken.START_ARRAY == currentToken();
    }
    public boolean isExpectedStartObjectToken() {
        return JsonToken.START_OBJECT == currentToken();
    }
    public boolean isExpectedNumberIntToken() {
        return JsonToken.VALUE_NUMBER_INT == currentToken();
    }
    public String currentName() throws IOException {
        return this.getCurrentName();
    }
    public Number getNumberValueExact() throws IOException {
        return this.getNumberValue();
    }
    public byte getByteValue() throws IOException {
        final int intValue = this.getIntValue();
        if (-128 > intValue || 255 < intValue) {
            throw new InputCoercionException(this, String.format("Numeric value (%s) out of range of Java byte", this.getText()), JsonToken.VALUE_NUMBER_INT, Byte.TYPE);
        }
        return (byte) intValue;
    }
    public short getShortValue() throws IOException {
        final int intValue = this.getIntValue();
        if (-32768 > intValue || 32767 < intValue) {
            throw new InputCoercionException(this, String.format("Numeric value (%s) out of range of Java short", this.getText()), JsonToken.VALUE_NUMBER_INT, Short.TYPE);
        }
        return (short) intValue;
    }
    public byte[] getBinaryValue() throws IOException {
        return this.getBinaryValue(Base64Variants.getDefaultVariant());
    }
    public int readBinaryValue(final Base64Variant base64Variant, final OutputStream outputStream) throws IOException {
        this._reportUnsupportedOperation();
        return 0;
    }
    public int getValueAsInt() throws IOException {
        return this.getValueAsInt(0);
    }
    public long getValueAsLong() throws IOException {
        return this.getValueAsLong(0L);
    }
    public String getValueAsString() throws IOException {
        return this.getValueAsString(null);
    }
    protected JsonParseException _constructError(final String str) {
        return new JsonParseException(this, str).withRequestPayload(_requestPayload);
    }
    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Operation not supported by parser of type " + this.getClass().getName());
    }
}
