package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.StreamWriteCapability;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import java.io.IOException;

public abstract class JsonGeneratorImpl extends GeneratorBase {
    protected boolean _cfgUnqNames;
    protected CharacterEscapes _characterEscapes;
    protected final IOContext _ioContext;
    protected int _maximumNonEscapedChar;
    protected int[] _outputEscapes;
    protected SerializableString _rootValueSeparator;
    protected static final int[] sOutputEscapes = CharTypes.get7BitOutputEscapes();
    protected static final JacksonFeatureSet<StreamWriteCapability> JSON_WRITE_CAPABILITIES = DEFAULT_TEXTUAL_WRITE_CAPABILITIES;
    protected JsonGeneratorImpl(final IOContext iOContext, final int i2, final ObjectCodec objectCodec) {
        super(i2, objectCodec);
        _outputEscapes = JsonGeneratorImpl.sOutputEscapes;
        _rootValueSeparator = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        _ioContext = iOContext;
        if (JsonGenerator.Feature.ESCAPE_NON_ASCII.enabledIn(i2)) {
            _maximumNonEscapedChar = 127;
        }
        _cfgUnqNames = !JsonGenerator.Feature.QUOTE_FIELD_NAMES.enabledIn(i2);
    }
    public JsonGenerator disable(final JsonGenerator.Feature feature) {
        super.disable(feature);
        if (Feature.QUOTE_FIELD_NAMES == feature) {
            _cfgUnqNames = true;
        }
        return this;
    }
    protected void _checkStdFeatureChanges(final int i2, final int i3) {
        super._checkStdFeatureChanges(i2, i3);
        _cfgUnqNames = !JsonGenerator.Feature.QUOTE_FIELD_NAMES.enabledIn(i2);
    }
    public JsonGenerator setHighestNonEscapedChar(int i2) {
        if (0 > i2) {
            i2 = 0;
        }
        _maximumNonEscapedChar = i2;
        return this;
    }
    public JsonGenerator setCharacterEscapes(final CharacterEscapes characterEscapes) {
        _characterEscapes = characterEscapes;
        if (null == characterEscapes) {
            _outputEscapes = JsonGeneratorImpl.sOutputEscapes;
        } else {
            _outputEscapes = characterEscapes.getEscapeCodesForAscii();
        }
        return this;
    }
    public JsonGenerator setRootValueSeparator(final SerializableString serializableString) {
        _rootValueSeparator = serializableString;
        return this;
    }
    protected void _verifyPrettyValueWrite(final String str, final int i2) throws IOException {
        if (0 == i2) {
            if (_writeContext.inArray()) {
                _cfgPrettyPrinter.beforeArrayValues(this);
                return;
            } else {
                if (_writeContext.inObject()) {
                    _cfgPrettyPrinter.beforeObjectEntries(this);
                    return;
                }
                return;
            }
        }
        if (1 == i2) {
            _cfgPrettyPrinter.writeArrayValueSeparator(this);
            return;
        }
        if (2 == i2) {
            _cfgPrettyPrinter.writeObjectFieldValueSeparator(this);
            return;
        }
        if (3 == i2) {
            _cfgPrettyPrinter.writeRootValueSeparator(this);
        } else if (5 == i2) {
            this._reportCantWriteValueExpectName(str);
        } else {
            this._throwInternal();
        }
    }
    protected void _reportCantWriteValueExpectName(final String str) throws IOException {
        this._reportError(String.format("Can not %s, expecting field name (context: %s)", str, _writeContext.typeDesc()));
    }
}
