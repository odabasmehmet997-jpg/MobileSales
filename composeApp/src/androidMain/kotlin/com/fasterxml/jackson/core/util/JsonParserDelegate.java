package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.*;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonParserDelegate extends JsonParser {
    protected JsonParser delegate;

    public JsonParserDelegate(final JsonParser jsonParser) {
        delegate = jsonParser;
    }
    public void setCurrentValue(final Object obj) {
        delegate.setCurrentValue(obj);
    }
    public ObjectCodec getCodec() {
        return delegate.getCodec();
    }
    public JsonParser enable(final JsonParser.Feature feature) {
        delegate.enable(feature);
        return this;
    }
    public JsonParser setFeatureMask(final int i2) {
        delegate.setFeatureMask(i2);
        return this;
    }
    public JsonParser overrideStdFeatures(final int i2, final int i3) {
        delegate.overrideStdFeatures(i2, i3);
        return this;
    }
    public JsonParser overrideFormatFeatures(final int i2, final int i3) {
        delegate.overrideFormatFeatures(i2, i3);
        return this;
    }
    public void setSchema(final FormatSchema formatSchema) {
        delegate.setSchema(formatSchema);
    }
    public boolean requiresCustomCodec() {
        return delegate.requiresCustomCodec();
    }

    
    public JacksonFeatureSet<StreamReadCapability> getReadCapabilities() {
        return delegate.getReadCapabilities();
    }
    public void close() throws IOException {
        delegate.close();
    }

    
    public JsonToken currentToken() {
        return delegate.currentToken();
    }

    
    public int currentTokenId() {
        return delegate.currentTokenId();
    }

    
    public JsonToken getCurrentToken() {
        return delegate.getCurrentToken();
    }
    public int getCurrentTokenId() {
        return delegate.getCurrentTokenId();
    }
    public boolean hasCurrentToken() {
        return delegate.hasCurrentToken();
    }
    public boolean hasTokenId(final int i2) {
        return delegate.hasTokenId(i2);
    }
    public boolean hasToken(final JsonToken jsonToken) {
        return delegate.hasToken(jsonToken);
    }
    public String getCurrentName() throws IOException {
        return delegate.getCurrentName();
    }

    
    public JsonLocation getCurrentLocation() {
        return delegate.getCurrentLocation();
    }

    
    public JsonStreamContext getParsingContext() {
        return delegate.getParsingContext();
    }

    
    public boolean isExpectedStartArrayToken() {
        return delegate.isExpectedStartArrayToken();
    }

    
    public boolean isExpectedStartObjectToken() {
        return delegate.isExpectedStartObjectToken();
    }

    
    public boolean isExpectedNumberIntToken() {
        return delegate.isExpectedNumberIntToken();
    }

    
    public boolean isNaN() throws IOException {
        return delegate.isNaN();
    }

    
    public void clearCurrentToken() {
        delegate.clearCurrentToken();
    }

    
    public String getText() throws IOException {
        return delegate.getText();
    }

    
    public boolean hasTextCharacters() {
        return delegate.hasTextCharacters();
    }

    
    public char[] getTextCharacters() throws IOException {
        return delegate.getTextCharacters();
    }

    
    public int getTextLength() throws IOException {
        return delegate.getTextLength();
    }

    
    public int getTextOffset() throws IOException {
        return delegate.getTextOffset();
    }

    
    public BigInteger getBigIntegerValue() throws IOException {
        return delegate.getBigIntegerValue();
    }

    
    public byte getByteValue() throws IOException {
        return delegate.getByteValue();
    }

    
    public short getShortValue() throws IOException {
        return delegate.getShortValue();
    }

    
    public BigDecimal getDecimalValue() throws IOException {
        return delegate.getDecimalValue();
    }

    
    public double getDoubleValue() throws IOException {
        return delegate.getDoubleValue();
    }

    
    public float getFloatValue() throws IOException {
        return delegate.getFloatValue();
    }

    
    public int getIntValue() throws IOException {
        return delegate.getIntValue();
    }

    
    public long getLongValue() throws IOException {
        return delegate.getLongValue();
    }

    
    public JsonParser.NumberType getNumberType() throws IOException {
        return delegate.getNumberType();
    }

    
    public Number getNumberValue() throws IOException {
        return delegate.getNumberValue();
    }

    
    public Number getNumberValueExact() throws IOException {
        return delegate.getNumberValueExact();
    }

    
    public int getValueAsInt() throws IOException {
        return delegate.getValueAsInt();
    }

    
    public int getValueAsInt(final int i2) throws IOException {
        return delegate.getValueAsInt(i2);
    }

    
    public long getValueAsLong() throws IOException {
        return delegate.getValueAsLong();
    }

    
    public long getValueAsLong(final long j2) throws IOException {
        return delegate.getValueAsLong(j2);
    }

    
    public String getValueAsString() throws IOException {
        return delegate.getValueAsString();
    }

    
    public String getValueAsString(final String str) throws IOException {
        return delegate.getValueAsString(str);
    }

    
    public Object getEmbeddedObject() throws IOException {
        return delegate.getEmbeddedObject();
    }

    
    public byte[] getBinaryValue(final Base64Variant base64Variant) throws IOException {
        return delegate.getBinaryValue(base64Variant);
    }

    
    public int readBinaryValue(final Base64Variant base64Variant, final OutputStream outputStream) throws IOException {
        return delegate.readBinaryValue(base64Variant, outputStream);
    }

    
    public JsonLocation getTokenLocation() {
        return delegate.getTokenLocation();
    }

    
    public JsonToken nextToken() throws IOException {
        return delegate.nextToken();
    }

    
    public JsonToken nextValue() throws IOException {
        return delegate.nextValue();
    }

    
    public JsonParser skipChildren() throws IOException {
        delegate.skipChildren();
        return this;
    }

    
    public boolean canReadObjectId() {
        return delegate.canReadObjectId();
    }

    
    public boolean canReadTypeId() {
        return delegate.canReadTypeId();
    }

    
    public Object getObjectId() throws IOException {
        return delegate.getObjectId();
    }

    
    public Object getTypeId() throws IOException {
        return delegate.getTypeId();
    }
}
