package com.fasterxml.jackson.core.json.async;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import org.kxml2.wap.Wbxml;

import java.io.IOException;
import java.io.OutputStream;

public abstract class NonBlockingJsonParserBase extends ParserBase {
    protected static final String[] NON_STD_TOKENS = {"NaN", "Infinity", "+Infinity", "-Infinity"};
    protected static final double[] NON_STD_TOKEN_VALUES = {Double.NaN, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
    protected int _currBufferStart;
    protected int _currInputRowAlt;
    protected boolean _endOfInput;
    protected int _majorState;
    protected int _majorStateAfterValue;
    protected int _minorState;
    protected int _minorStateAfterSplit;
    protected int _nonStdTokenType;
    protected int _pending32;
    protected int _pendingBytes;
    protected int _quad1;
    protected int[] _quadBuffer;
    protected int _quadLength;
    protected int _quoted32;
    protected int _quotedDigits;
    protected final ByteQuadsCanonicalizer _symbols;

    protected static final int _padLastQuad(final int i2, final int i3) {
        return 4 == i3 ? i2 : i2 | ((-1) << (i3 << 3));
    }
    public ObjectCodec getCodec() {
        return null;
    }

    protected NonBlockingJsonParserBase(final IOContext iOContext, final int i2, final ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        super(iOContext, i2);
        _quadBuffer = new int[8];
        _endOfInput = false;
        _currBufferStart = 0;
        _currInputRowAlt = 1;
        _symbols = byteQuadsCanonicalizer;
        _currToken = null;
        _majorState = 0;
        _majorStateAfterValue = 1;
    }
    public JacksonFeatureSet<StreamReadCapability> getReadCapabilities() {
        return JSON_READ_CAPABILITIES;
    }
    protected void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        _symbols.release();
    }
    protected void _closeInput() throws IOException {
        _currBufferStart = 0;
        _inputEnd = 0;
    }
    public boolean hasTextCharacters() {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_STRING == jsonToken) {
            return _textBuffer.hasTextAsCharacters();
        }
        if (JsonToken.FIELD_NAME == jsonToken) {
            return _nameCopied;
        }
        return false;
    }
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._getSourceReference(), _currInputProcessed + (_inputPtr - _currBufferStart), -1L, Math.max(_currInputRow, _currInputRowAlt), (_inputPtr - _currInputRowStart) + 1);
    }
    public JsonLocation getTokenLocation() {
        return new JsonLocation(this._getSourceReference(), _tokenInputTotal, -1L, _tokenInputRow, _tokenInputCol);
    }
    public String getText() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_STRING == jsonToken) {
            return _textBuffer.contentsAsString();
        }
        return this._getText2(jsonToken);
    }

    protected final String _getText2(final JsonToken jsonToken) {
        final int iM307id;
        if (null == jsonToken || -1 == (iM307id = jsonToken.m307id())) {
            return null;
        }
        if (5 == iM307id) {
            return _parsingContext.getCurrentName();
        }
        if (6 == iM307id || 7 == iM307id || 8 == iM307id) {
            return _textBuffer.contentsAsString();
        }
        return jsonToken.asString();
    }

    public String getValueAsString() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_STRING == jsonToken) {
            return _textBuffer.contentsAsString();
        }
        if (JsonToken.FIELD_NAME == jsonToken) {
            return this.getCurrentName();
        }
        return super.getValueAsString(null);
    }

    public String getValueAsString(final String str) throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_STRING == jsonToken) {
            return _textBuffer.contentsAsString();
        }
        if (JsonToken.FIELD_NAME == jsonToken) {
            return this.getCurrentName();
        }
        return super.getValueAsString(str);
    }

    
    public char[] getTextCharacters() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (null == jsonToken) {
            return null;
        }
        final int iM307id = jsonToken.m307id();
        if (5 != iM307id) {
            if (6 == iM307id || 7 == iM307id || 8 == iM307id) {
                return _textBuffer.getTextBuffer();
            }
            return _currToken.asCharArray();
        }
        if (!_nameCopied) {
            final String currentName = _parsingContext.getCurrentName();
            final int length = currentName.length();
            final char[] cArr = _nameCopyBuffer;
            if (null == cArr) {
                _nameCopyBuffer = _ioContext.allocNameCopyBuffer(length);
            } else if (cArr.length < length) {
                _nameCopyBuffer = new char[length];
            }
            currentName.getChars(0, length, _nameCopyBuffer, 0);
            _nameCopied = true;
        }
        return _nameCopyBuffer;
    }

    
    public int getTextLength() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (null == jsonToken) {
            return 0;
        }
        final int iM307id = jsonToken.m307id();
        if (5 == iM307id) {
            return _parsingContext.getCurrentName().length();
        }
        if (6 == iM307id || 7 == iM307id || 8 == iM307id) {
            return _textBuffer.size();
        }
        return _currToken.asCharArray().length;
    }

    
    public int getTextOffset() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (null == jsonToken) {
            return 0;
        }
        final int iM307id = jsonToken.m307id();
        if (6 == iM307id || 7 == iM307id || 8 == iM307id) {
            return _textBuffer.getTextOffset();
        }
        return 0;
    }
    public byte[] getBinaryValue(final Base64Variant base64Variant) throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_STRING != jsonToken) {
            this._reportError("Current token (%s) not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary", jsonToken);
        }
        if (null == this._binaryValue) {
            final ByteArrayBuilder byteArrayBuilder_getByteArrayBuilder = this._getByteArrayBuilder();
            this._decodeBase64(this.getText(), byteArrayBuilder_getByteArrayBuilder, base64Variant);
            _binaryValue = byteArrayBuilder_getByteArrayBuilder.toByteArray();
        }
        return _binaryValue;
    }

    
    public int readBinaryValue(final Base64Variant base64Variant, final OutputStream outputStream) throws IOException {
        final byte[] binaryValue = this.getBinaryValue(base64Variant);
        outputStream.write(binaryValue);
        return binaryValue.length;
    }

    
    public Object getEmbeddedObject() throws IOException {
        if (JsonToken.VALUE_EMBEDDED_OBJECT == this._currToken) {
            return _binaryValue;
        }
        return null;
    }

    protected final JsonToken _startArrayScope() throws IOException {
        _parsingContext = _parsingContext.createChildArrayContext(-1, -1);
        _majorState = 5;
        _majorStateAfterValue = 6;
        final JsonToken jsonToken = JsonToken.START_ARRAY;
        _currToken = jsonToken;
        return jsonToken;
    }

    protected final JsonToken _startObjectScope() throws IOException {
        _parsingContext = _parsingContext.createChildObjectContext(-1, -1);
        _majorState = 2;
        _majorStateAfterValue = 3;
        final JsonToken jsonToken = JsonToken.START_OBJECT;
        _currToken = jsonToken;
        return jsonToken;
    }

    protected final JsonToken _closeArrayScope() throws IOException {
        final int i2;
        if (!_parsingContext.inArray()) {
            this._reportMismatchedEndMarker(93, '}');
        }
        final JsonReadContext parent = _parsingContext.getParent();
        _parsingContext = parent;
        if (parent.inObject()) {
            i2 = 3;
        } else {
            i2 = parent.inArray() ? 6 : 1;
        }
        _majorState = i2;
        _majorStateAfterValue = i2;
        final JsonToken jsonToken = JsonToken.END_ARRAY;
        _currToken = jsonToken;
        return jsonToken;
    }

    protected final JsonToken _closeObjectScope() throws IOException {
        final int i2;
        if (!_parsingContext.inObject()) {
            this._reportMismatchedEndMarker(125, ']');
        }
        final JsonReadContext parent = _parsingContext.getParent();
        _parsingContext = parent;
        if (parent.inObject()) {
            i2 = 3;
        } else {
            i2 = parent.inArray() ? 6 : 1;
        }
        _majorState = i2;
        _majorStateAfterValue = i2;
        final JsonToken jsonToken = JsonToken.END_OBJECT;
        _currToken = jsonToken;
        return jsonToken;
    }

    protected final String _findName(final int i2, final int i3) throws JsonParseException {
        final int i_padLastQuad = NonBlockingJsonParserBase._padLastQuad(i2, i3);
        final String strFindName = _symbols.findName(i_padLastQuad);
        if (null != strFindName) {
            return strFindName;
        }
        final int[] iArr = _quadBuffer;
        iArr[0] = i_padLastQuad;
        return this._addName(iArr, 1, i3);
    }

    protected final String _findName(final int i2, final int i3, final int i4) throws JsonParseException {
        final int i_padLastQuad = NonBlockingJsonParserBase._padLastQuad(i3, i4);
        final String strFindName = _symbols.findName(i2, i_padLastQuad);
        if (null != strFindName) {
            return strFindName;
        }
        final int[] iArr = _quadBuffer;
        iArr[0] = i2;
        iArr[1] = i_padLastQuad;
        return this._addName(iArr, 2, i4);
    }

    protected final String _findName(final int i2, final int i3, final int i4, final int i5) throws JsonParseException {
        final int i_padLastQuad = NonBlockingJsonParserBase._padLastQuad(i4, i5);
        final String strFindName = _symbols.findName(i2, i3, i_padLastQuad);
        if (null != strFindName) {
            return strFindName;
        }
        final int[] iArr = _quadBuffer;
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = NonBlockingJsonParserBase._padLastQuad(i_padLastQuad, i5);
        return this._addName(iArr, 3, i5);
    }

    protected final String _addName(final int[] iArr, final int i2, final int i3) throws JsonParseException {
        final int i4;
        int i5;
        int i6 = 0;
        int i7;
        int i8;
        final int i9 = ((i2 << 2) - 4) + i3;
        if (4 > i3) {
            final int i10 = i2 - 1;
            i4 = iArr[i10];
            iArr[i10] = i4 << ((4 - i3) << 3);
        } else {
            i4 = 0;
        }
        char[] cArrEmptyAndGetCurrentSegment = _textBuffer.emptyAndGetCurrentSegment();
        int i11 = 0;
        int i12 = 0;
        while (i11 < i9) {
            final int i13 = iArr[i11 >> 2] >> ((3 - (i11 & 3)) << 3);
            int i14 = i13 & 255;
            int i15 = i11 + 1;
            if (127 < i14) {
                if (192 == (i13 & 224)) {
                    i5 = i13 & 31;
                } else {
                    if (224 == (i13 & 240)) {
                        i5 = i13 & 15;
                        i6 = 2;
                    } else if (240 == (i13 & 248)) {
                        i5 = i13 & 7;
                        i6 = 3;
                    } else {
                        this._reportInvalidInitial(i14);
                        i5 = 1;
                    }
                    if (i15 + i6 > i9) {
                        this._reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
                    }
                    i7 = iArr[i15 >> 2] >> ((3 - (i15 & 3)) << 3);
                    i15 = i11 + 2;
                    if (128 != (i7 & Wbxml.EXT_0)) {
                        this._reportInvalidOther(i7);
                    }
                    int i16 = (i5 << 6) | (i7 & 63);
                    if (1 >= i6) {
                        final int i17 = iArr[i15 >> 2] >> ((3 - (i15 & 3)) << 3);
                        i15 = i11 + 3;
                        if (128 != (i17 & Wbxml.EXT_0)) {
                            this._reportInvalidOther(i17);
                        }
                        final int i18 = (i16 << 6) | (i17 & 63);
                        if (2 < i6) {
                            final int i19 = iArr[i15 >> 2] >> ((3 - (i15 & 3)) << 3);
                            i15 = i11 + 4;
                            if (128 != (i19 & Wbxml.EXT_0)) {
                                this._reportInvalidOther(i19 & 255);
                            }
                            i16 = (i18 << 6) | (i19 & 63);
                            i14 = i16;
                            i8 = 2;
                            if (i6 > i8) {
                                final int i20 = i14 - 65536;
                                if (i12 >= cArrEmptyAndGetCurrentSegment.length) {
                                    cArrEmptyAndGetCurrentSegment = _textBuffer.expandCurrentSegment();
                                }
                                cArrEmptyAndGetCurrentSegment[i12] = (char) ((i20 >> 10) + 55296);
                                i14 = (i20 & 1023) | 56320;
                                i12++;
                            }
                        } else {
                            i14 = i18;
                            i8 = 2;
                            if (i6 > i8) {
                            }
                        }
                    } else {
                        i14 = i16;
                        i8 = 2;
                        if (i6 > i8) {
                        }
                    }
                }
                i6 = 1;
                if (i15 + i6 > i9) {
                }
                i7 = iArr[i15 >> 2] >> ((3 - (i15 & 3)) << 3);
                i15 = i11 + 2;
                if (128 != (i7 & Wbxml.EXT_0)) {
                }
                final int i162 = (i5 << 6) | (i7 & 63);
                if (1 >= i6) {
                }
            }
            i11 = i15;
            if (i12 >= cArrEmptyAndGetCurrentSegment.length) {
                cArrEmptyAndGetCurrentSegment = _textBuffer.expandCurrentSegment();
            }
            cArrEmptyAndGetCurrentSegment[i12] = (char) i14;
            i12++;
        }
        final String str = new String(cArrEmptyAndGetCurrentSegment, 0, i12);
        if (4 > i3) {
            iArr[i2 - 1] = i4;
        }
        return _symbols.addName(str, iArr, i2);
    }

    protected final JsonToken _eofAsNextToken() throws IOException {
        _majorState = 7;
        if (!_parsingContext.inRoot()) {
            this._handleEOF();
        }
        this.close();
        _currToken = null;
        return null;
    }

    protected final JsonToken _fieldComplete(final String str) throws IOException {
        _majorState = 4;
        _parsingContext.setCurrentName(str);
        final JsonToken jsonToken = JsonToken.FIELD_NAME;
        _currToken = jsonToken;
        return jsonToken;
    }

    protected final JsonToken _valueComplete(final JsonToken jsonToken) throws IOException {
        _majorState = _majorStateAfterValue;
        _currToken = jsonToken;
        return jsonToken;
    }

    protected final JsonToken _valueCompleteInt(final int i2, final String str) throws IOException {
        _textBuffer.resetWithString(str);
        _intLength = str.length();
        _numTypesValid = 1;
        _numberInt = i2;
        _majorState = _majorStateAfterValue;
        final JsonToken jsonToken = JsonToken.VALUE_NUMBER_INT;
        _currToken = jsonToken;
        return jsonToken;
    }

    protected final JsonToken _valueNonStdNumberComplete(final int i2) throws IOException {
        final String str = NonBlockingJsonParserBase.NON_STD_TOKENS[i2];
        _textBuffer.resetWithString(str);
        if (!this.isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            this._reportError("Non-standard token '%s': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow", str);
        }
        _intLength = 0;
        _numTypesValid = 8;
        _numberDouble = NonBlockingJsonParserBase.NON_STD_TOKEN_VALUES[i2];
        _majorState = _majorStateAfterValue;
        final JsonToken jsonToken = JsonToken.VALUE_NUMBER_FLOAT;
        _currToken = jsonToken;
        return jsonToken;
    }

    protected final String _nonStdToken(final int i2) {
        return NonBlockingJsonParserBase.NON_STD_TOKENS[i2];
    }

    protected final void _updateTokenLocation() {
        _tokenInputRow = Math.max(_currInputRow, _currInputRowAlt);
        _tokenInputCol = _inputPtr - _currInputRowStart;
        final long r0 = 0;
        _tokenInputTotal = _currInputProcessed + (r0 - _currBufferStart);
    }
    protected void _reportInvalidChar(final int i2) throws JsonParseException {
        if (32 > i2) {
            this._throwInvalidSpace(i2);
        }
        this._reportInvalidInitial(i2);
    }
    protected void _reportInvalidInitial(final int i2) throws JsonParseException {
        this._reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(i2));
    }

    protected void _reportInvalidOther(final int i2, final int i3) throws JsonParseException {
        _inputPtr = i3;
        this._reportInvalidOther(i2);
    }

    protected void _reportInvalidOther(final int i2) throws JsonParseException {
        this._reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i2));
    }
}
