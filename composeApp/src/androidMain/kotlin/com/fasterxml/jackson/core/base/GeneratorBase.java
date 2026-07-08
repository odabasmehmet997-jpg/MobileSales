package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonWriteContext;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

public abstract class GeneratorBase extends JsonGenerator {
    protected static final int DERIVED_FEATURES_MASK = (JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.getMask() | JsonGenerator.Feature.ESCAPE_NON_ASCII.getMask()) | JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.getMask();
    protected boolean _cfgNumbersAsStrings;
    protected boolean _closed;
    protected int _features;
    protected ObjectCodec _objectCodec;
    protected JsonWriteContext _writeContext;
    protected abstract void _verifyValueWrite(String str) throws IOException;
    protected GeneratorBase(final int i2, final ObjectCodec objectCodec) {
        _features = i2;
        _objectCodec = objectCodec;
        _writeContext = JsonWriteContext.createRootContext(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i2) ? DupDetector.rootDetector(this) : null);
        _cfgNumbersAsStrings = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(i2);
    }
    public void setCurrentValue(final Object obj) {
        final JsonWriteContext jsonWriteContext = _writeContext;
        if (null != jsonWriteContext) {
            jsonWriteContext.setCurrentValue(obj);
        }
    }
    public final boolean isEnabled(final JsonGenerator.Feature feature) {
        return 0 != (feature.getMask() & this._features);
    }
    public int getFeatureMask() {
        return _features;
    }
    public JsonGenerator disable(final JsonGenerator.Feature feature) {
        final int mask = feature.getMask();
        _features &= ~mask;
        if (0 != (mask & DERIVED_FEATURES_MASK)) {
            if (Feature.WRITE_NUMBERS_AS_STRINGS == feature) {
                _cfgNumbersAsStrings = false;
            } else if (Feature.ESCAPE_NON_ASCII == feature) {
                this.setHighestNonEscapedChar(0);
            } else if (Feature.STRICT_DUPLICATE_DETECTION == feature) {
                _writeContext = _writeContext.withDupDetector(null);
            }
        }
        return this;
    }
    public JsonGenerator setFeatureMask(final int i2) {
        final int i3 = _features ^ i2;
        _features = i2;
        if (0 != i3) {
            this._checkStdFeatureChanges(i2, i3);
        }
        return this;
    }
    public JsonGenerator overrideStdFeatures(final int i2, final int i3) {
        final int i4 = _features;
        final int i5 = (i2 & i3) | ((~i3) & i4);
        final int i6 = i4 ^ i5;
        if (0 != i6) {
            _features = i5;
            this._checkStdFeatureChanges(i5, i6);
        }
        return this;
    }
    protected void _checkStdFeatureChanges(final int i2, final int i3) {
        if (0 == (DERIVED_FEATURES_MASK & i3)) {
            return;
        }
        _cfgNumbersAsStrings = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(i2);
        final JsonGenerator.Feature feature = JsonGenerator.Feature.ESCAPE_NON_ASCII;
        if (feature.enabledIn(i3)) {
            if (feature.enabledIn(i2)) {
                this.setHighestNonEscapedChar(127);
            } else {
                this.setHighestNonEscapedChar(0);
            }
        }
        final JsonGenerator.Feature feature2 = JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION;
        if (feature2.enabledIn(i3)) {
            if (feature2.enabledIn(i2)) {
                if (null == this._writeContext.getDupDetector()) {
                    _writeContext = _writeContext.withDupDetector(DupDetector.rootDetector(this));
                    return;
                }
                return;
            }
            _writeContext = _writeContext.withDupDetector(null);
        }
    }
    public JsonStreamContext getOutputContext() {
        return _writeContext;
    }
    public void writeStartObject(final Object obj) throws IOException {
        this.writeStartObject();
        if (null != obj) {
            this.setCurrentValue(obj);
        }
    }
    public void writeFieldName(final SerializableString serializableString) throws IOException {
        this.writeFieldName(serializableString.getValue());
    }
    public void writeString(final SerializableString serializableString) throws IOException {
        this.writeString(serializableString.getValue());
    }
    public void writeRawValue(final String str) throws IOException {
        this._verifyValueWrite("write raw value");
        this.writeRaw(str);
    }
    public void writeRawValue(final SerializableString serializableString) throws IOException {
        this._verifyValueWrite("write raw value");
        this.writeRaw(serializableString);
    }
    public int writeBinary(final Base64Variant base64Variant, final InputStream inputStream, final int i2) throws IOException {
        this._reportUnsupportedOperation();
        return 0;
    }
    public void writeObject(final Object obj) throws IOException {
        if (null == obj) {
            this.writeNull();
            return;
        }
        final ObjectCodec objectCodec = _objectCodec;
        if (null != objectCodec) {
            objectCodec.writeValue(this, obj);
        } else {
            this._writeSimpleObject(obj);
        }
    }
    public void close() throws IOException {
        _closed = true;
    }
    protected String _asString(final BigDecimal bigDecimal) throws IOException {
        if (JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN.enabledIn(_features)) {
            final int iScale = bigDecimal.scale();
            if (-9999 > iScale || 9999 < iScale) {
                this._reportError(String.format("Attempt to write plain `java.math.BigDecimal` (see JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN) with illegal scale (%d): needs to be between [-%d, %d]", iScale, 9999, 9999));
            }
            return bigDecimal.toPlainString();
        }
        return bigDecimal.toString();
    }
    protected final int _decodeSurrogate(final int i2, final int i3) throws IOException {
        if (56320 > i3 || 57343 < i3) {
            this._reportError(String.format("Incomplete surrogate pair: first char 0x%X, second 0x%X", i2, i3));
        }
        return ((i2 - 55296) << 10) + 65536 + (i3 - 56320);
    }
}
