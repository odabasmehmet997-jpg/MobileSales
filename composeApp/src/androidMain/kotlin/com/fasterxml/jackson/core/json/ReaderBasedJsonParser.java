package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.core.util.TextBuffer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;

public class ReaderBasedJsonParser extends ParserBase {
    protected boolean _bufferRecyclable;
    protected final int _hashSeed;
    protected char[] _inputBuffer;
    protected int _nameStartCol;
    protected long _nameStartOffset;
    protected int _nameStartRow;
    protected ObjectCodec _objectCodec;
    protected Reader _reader;
    protected final CharsToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    private static final int FEAT_MASK_TRAILING_COMMA = Feature.ALLOW_TRAILING_COMMA.getMask();
    private static final int FEAT_MASK_LEADING_ZEROS = Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
    private static final int FEAT_MASK_NON_NUM_NUMBERS = Feature.ALLOW_NON_NUMERIC_NUMBERS.getMask();
    private static final int FEAT_MASK_ALLOW_MISSING = Feature.ALLOW_MISSING_VALUES.getMask();
    private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = Feature.ALLOW_SINGLE_QUOTES.getMask();
    private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
    private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = Feature.ALLOW_COMMENTS.getMask();
    private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = Feature.ALLOW_YAML_COMMENTS.getMask();
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
    public ReaderBasedJsonParser(final IOContext iOContext, final int i2, final Reader reader, final ObjectCodec objectCodec, final CharsToNameCanonicalizer charsToNameCanonicalizer, final char[] cArr, final int i3, final int i4, final boolean z) {
        super(iOContext, i2);
        _reader = reader;
        _inputBuffer = cArr;
        _inputPtr = i3;
        _inputEnd = i4;
        _objectCodec = objectCodec;
        _symbols = charsToNameCanonicalizer;
        _hashSeed = charsToNameCanonicalizer.hashSeed();
        _bufferRecyclable = z;
    }
    public ReaderBasedJsonParser(final IOContext iOContext, final int i2, final Reader reader, final ObjectCodec objectCodec, final CharsToNameCanonicalizer charsToNameCanonicalizer) {
        super(iOContext, i2);
        _reader = reader;
        _inputBuffer = iOContext.allocTokenBuffer();
        _inputPtr = 0;
        _inputEnd = 0;
        _objectCodec = objectCodec;
        _symbols = charsToNameCanonicalizer;
        _hashSeed = charsToNameCanonicalizer.hashSeed();
        _bufferRecyclable = true;
    }
    public ObjectCodec getCodec() {
        return _objectCodec;
    }
    public JacksonFeatureSet<StreamReadCapability> getReadCapabilities() {
        return JSON_READ_CAPABILITIES;
    }
    protected char getNextChar(final String str) throws IOException {
        return this.getNextChar(str, null);
    }
    protected char getNextChar(final String str, final JsonToken jsonToken) throws IOException {
        if (_inputPtr >= _inputEnd && !this._loadMore()) {
            this._reportInvalidEOF(str, jsonToken);
        }
        final char[] cArr = _inputBuffer;
        final int i2 = _inputPtr;
        _inputPtr = i2 + 1;
        return cArr[i2];
    }
    protected void _closeInput() throws IOException {
        if (null != this._reader) {
            if (_ioContext.isResourceManaged() || this.isEnabled(Feature.AUTO_CLOSE_SOURCE)) {
                _reader.close();
            }
            _reader = null;
        }
    }
    protected void _releaseBuffers() throws IOException {
        final char[] cArr;
        super._releaseBuffers();
        _symbols.release();
        if (!_bufferRecyclable || null == (cArr = this._inputBuffer)) {
            return;
        }
        _inputBuffer = null;
        _ioContext.releaseTokenBuffer(cArr);
    }
    protected void _loadMoreGuaranteed() throws IOException {
        if (this._loadMore()) {
            return;
        }
        this._reportInvalidEOF();
    }
    protected boolean _loadMore() throws IOException {
        final Reader reader = _reader;
        if (null != reader) {
            final char[] cArr = _inputBuffer;
            final int i2 = reader.read(cArr, 0, cArr.length);
            if (0 < i2) {
                final int i3 = _inputEnd;
                final long j2 = i3;
                _currInputProcessed += j2;
                _currInputRowStart -= i3;
                _nameStartOffset -= j2;
                _inputPtr = 0;
                _inputEnd = i2;
                return true;
            }
            this._closeInput();
            if (0 == i2) {
                throw new IOException("Reader returned 0 characters when trying to read " + _inputEnd);
            }
        }
        return false;
    }
    public final String getText() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_STRING == jsonToken) {
            if (_tokenIncomplete) {
                _tokenIncomplete = false;
                this._finishString();
            }
            return _textBuffer.contentsAsString();
        }
        return this._getText2(jsonToken);
    }
    public final String getValueAsString() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_STRING == jsonToken) {
            if (_tokenIncomplete) {
                _tokenIncomplete = false;
                this._finishString();
            }
            return _textBuffer.contentsAsString();
        }
        if (JsonToken.FIELD_NAME == jsonToken) {
            return this.getCurrentName();
        }
        return super.getValueAsString(null);
    }
    public final String getValueAsString(final String str) throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_STRING == jsonToken) {
            if (_tokenIncomplete) {
                _tokenIncomplete = false;
                this._finishString();
            }
            return _textBuffer.contentsAsString();
        }
        if (JsonToken.FIELD_NAME == jsonToken) {
            return this.getCurrentName();
        }
        return super.getValueAsString(str);
    }
    protected final String _getText2(final JsonToken jsonToken) {
        if (null == jsonToken) {
            return null;
        }
        final int iM307id = jsonToken.m307id();
        if (5 == iM307id) {
            return _parsingContext.getCurrentName();
        }
        if (6 == iM307id || 7 == iM307id || 8 == iM307id) {
            return _textBuffer.contentsAsString();
        }
        return jsonToken.asString();
    }
    public final char[] getTextCharacters() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (null == jsonToken) {
            return null;
        }
        final int iM307id = jsonToken.m307id();
        if (5 == iM307id) {
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
        if (6 != iM307id) {
            if (7 != iM307id && 8 != iM307id) {
                return _currToken.asCharArray();
            }
        } else if (_tokenIncomplete) {
            _tokenIncomplete = false;
            this._finishString();
        }
        return _textBuffer.getTextBuffer();
    }
    public final int getTextLength() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (null == jsonToken) {
            return 0;
        }
        final int iM307id = jsonToken.m307id();
        if (5 == iM307id) {
            return _parsingContext.getCurrentName().length();
        }
        if (6 != iM307id) {
            if (7 != iM307id && 8 != iM307id) {
                return _currToken.asCharArray().length;
            }
        } else if (_tokenIncomplete) {
            _tokenIncomplete = false;
            this._finishString();
        }
        return _textBuffer.size();
    }
    public final int getTextOffset() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (null != jsonToken) {
            final int iM307id = jsonToken.m307id();
            if (6 != iM307id) {
                if (7 != iM307id) {
                }
            } else if (_tokenIncomplete) {
                _tokenIncomplete = false;
                this._finishString();
            }
            return _textBuffer.getTextOffset();
        }
        return 0;
    }
    public byte[] getBinaryValue(final Base64Variant base64Variant) throws IOException {
        final byte[] bArr;
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_EMBEDDED_OBJECT == jsonToken && null != (bArr = this._binaryValue)) {
            return bArr;
        }
        if (JsonToken.VALUE_STRING != jsonToken) {
            this._reportError("Current token (" + _currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (_tokenIncomplete) {
            try {
                _binaryValue = this._decodeBase64(base64Variant);
                _tokenIncomplete = false;
            } catch (final IllegalArgumentException e2) {
                throw this._constructError("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + e2.getMessage());
            }
        } else if (null == this._binaryValue) {
            final ByteArrayBuilder byteArrayBuilder_getByteArrayBuilder = this._getByteArrayBuilder();
            this._decodeBase64(this.getText(), byteArrayBuilder_getByteArrayBuilder, base64Variant);
            _binaryValue = byteArrayBuilder_getByteArrayBuilder.toByteArray();
        }
        return _binaryValue;
    }
    public int readBinaryValue(final Base64Variant base64Variant, final OutputStream outputStream) throws IOException {
        if (!_tokenIncomplete || JsonToken.VALUE_STRING != this._currToken) {
            final byte[] binaryValue = this.getBinaryValue(base64Variant);
            outputStream.write(binaryValue);
            return binaryValue.length;
        }
        final byte[] bArrAllocBase64Buffer = _ioContext.allocBase64Buffer();
        try {
            return this._readBinary(base64Variant, outputStream, bArrAllocBase64Buffer);
        } finally {
            _ioContext.releaseBase64Buffer(bArrAllocBase64Buffer);
        }
    }
    protected int _readBinary(final Base64Variant base64Variant, final OutputStream outputStream, final byte[] bArr) throws IOException {
        int i2 = 0;
        int iDecodeBase64Char;
        int iDecodeBase64Char2;
        int iDecodeBase64Char3;
        int i3 = 3;
        final int length = bArr.length - 3;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (_inputPtr >= _inputEnd) {
                this._loadMoreGuaranteed();
            }
            final char[] cArr = _inputBuffer;
            final int i6 = _inputPtr;
            _inputPtr = i6 + 1;
            final char c2 = cArr[i6];
            if (' ' < c2) {
                int iDecodeBase64Char4 = base64Variant.decodeBase64Char(c2);
                if (0 > iDecodeBase64Char4) {
                    if ('\"' == c2) {
                        break;
                    }
                    iDecodeBase64Char4 = this._decodeBase64Escape(base64Variant, c2, 0);
                    if (0 > iDecodeBase64Char4) {
                    }
                    if (i4 > length) {
                    }
                    if (_inputPtr >= _inputEnd) {
                    }
                    final char[] cArr2 = _inputBuffer;
                    final int i7 = _inputPtr;
                    _inputPtr = i7 + 1;
                    final char c3 = cArr2[i7];
                    iDecodeBase64Char = base64Variant.decodeBase64Char(c3);
                    if (0 > iDecodeBase64Char) {
                    }
                    final int i8 = (iDecodeBase64Char4 << 6) | iDecodeBase64Char;
                    if (_inputPtr >= _inputEnd) {
                    }
                    final char[] cArr3 = _inputBuffer;
                    final int i9 = _inputPtr;
                    _inputPtr = i9 + 1;
                    final char c4 = cArr3[i9];
                    iDecodeBase64Char2 = base64Variant.decodeBase64Char(c4);
                    if (0 > iDecodeBase64Char2) {
                    }
                    final int i10 = (i8 << 6) | iDecodeBase64Char2;
                    if (_inputPtr >= _inputEnd) {
                    }
                    final char[] cArr4 = _inputBuffer;
                    final int i11 = _inputPtr;
                    _inputPtr = i11 + 1;
                    final char c5 = cArr4[i11];
                    iDecodeBase64Char3 = base64Variant.decodeBase64Char(c5);
                    if (0 <= iDecodeBase64Char3) {
                    }
                    final int i12 = (i10 << 6) | iDecodeBase64Char3;
                    bArr[i4] = (byte) (i12 >> 16);
                    final int i13 = i4 + 2;
                    bArr[i4 + 1] = (byte) (i12 >> 8);
                    i4 += 3;
                    bArr[i13] = (byte) i12;
                    i3 = i2;
                } else {
                    if (i4 > length) {
                        i5 += i4;
                        outputStream.write(bArr, 0, i4);
                        i4 = 0;
                    }
                    if (_inputPtr >= _inputEnd) {
                        this._loadMoreGuaranteed();
                    }
                    final char[] cArr22 = _inputBuffer;
                    final int i72 = _inputPtr;
                    _inputPtr = i72 + 1;
                    final char c32 = cArr22[i72];
                    iDecodeBase64Char = base64Variant.decodeBase64Char(c32);
                    if (0 > iDecodeBase64Char) {
                        iDecodeBase64Char = this._decodeBase64Escape(base64Variant, c32, 1);
                    }
                    final int i82 = (iDecodeBase64Char4 << 6) | iDecodeBase64Char;
                    if (_inputPtr >= _inputEnd) {
                        this._loadMoreGuaranteed();
                    }
                    final char[] cArr32 = _inputBuffer;
                    final int i92 = _inputPtr;
                    _inputPtr = i92 + 1;
                    final char c42 = cArr32[i92];
                    iDecodeBase64Char2 = base64Variant.decodeBase64Char(c42);
                    if (0 > iDecodeBase64Char2) {
                        if (-2 != iDecodeBase64Char2) {
                            if ('\"' == c42) {
                                final int i14 = i4 + 1;
                                bArr[i4] = (byte) (i82 >> 4);
                                if (base64Variant.usesPadding()) {
                                    _inputPtr--;
                                    this._handleBase64MissingPadding(base64Variant);
                                }
                                i4 = i14;
                            } else {
                                iDecodeBase64Char2 = this._decodeBase64Escape(base64Variant, c42, 2);
                            }
                        }
                        if (-2 == iDecodeBase64Char2) {
                            if (_inputPtr >= _inputEnd) {
                                this._loadMoreGuaranteed();
                            }
                            final char[] cArr5 = _inputBuffer;
                            final int i15 = _inputPtr;
                            _inputPtr = i15 + 1;
                            final char c6 = cArr5[i15];
                            if (!base64Variant.usesPaddingChar(c6) && -2 != _decodeBase64Escape(base64Variant, c6, i3)) {
                                throw this.reportInvalidBase64Char(base64Variant, c6, i3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                            }
                            bArr[i4] = (byte) (i82 >> 4);
                            i4++;
                        }
                    }
                    final int i102 = (i82 << 6) | iDecodeBase64Char2;
                    if (_inputPtr >= _inputEnd) {
                        this._loadMoreGuaranteed();
                    }
                    final char[] cArr42 = _inputBuffer;
                    final int i112 = _inputPtr;
                    _inputPtr = i112 + 1;
                    final char c52 = cArr42[i112];
                    iDecodeBase64Char3 = base64Variant.decodeBase64Char(c52);
                    if (0 <= iDecodeBase64Char3) {
                        if (-2 == iDecodeBase64Char3) {
                            i2 = 3;
                        } else if ('\"' == c52) {
                            final int i16 = i4 + 1;
                            bArr[i4] = (byte) (i102 >> 10);
                            i4 += 2;
                            bArr[i16] = (byte) (i102 >> 2);
                            if (base64Variant.usesPadding()) {
                                _inputPtr--;
                                this._handleBase64MissingPadding(base64Variant);
                            }
                        } else {
                            i2 = 3;
                            iDecodeBase64Char3 = this._decodeBase64Escape(base64Variant, c52, 3);
                        }
                        if (-2 == iDecodeBase64Char3) {
                            final int i17 = i4 + 1;
                            bArr[i4] = (byte) (i102 >> 10);
                            i4 += 2;
                            bArr[i17] = (byte) (i102 >> 2);
                        }
                        i3 = i2;
                    } else {
                        i2 = 3;
                    }
                    final int i122 = (i102 << 6) | iDecodeBase64Char3;
                    bArr[i4] = (byte) (i122 >> 16);
                    final int i132 = i4 + 2;
                    bArr[i4 + 1] = (byte) (i122 >> 8);
                    i4 += 3;
                    bArr[i132] = (byte) i122;
                    i3 = i2;
                }
            }
            i2 = i3;
            i3 = i2;
        }
        return i2;
    }
    public final JsonToken nextToken() throws IOException {
        final JsonToken jsonToken_parseNegNumber;
        final JsonToken jsonToken = _currToken;
        final JsonToken jsonToken2 = JsonToken.FIELD_NAME;
        if (jsonToken == jsonToken2) {
            return this._nextAfterName();
        }
        _numTypesValid = 0;
        if (_tokenIncomplete) {
            this._skipString();
        }
        int i_skipWSOrEnd = this._skipWSOrEnd();
        if (0 > i_skipWSOrEnd) {
            this.close();
            _currToken = null;
            return null;
        }
        _binaryValue = null;
        if (93 == i_skipWSOrEnd || 125 == i_skipWSOrEnd) {
            this._closeScope(i_skipWSOrEnd);
            return _currToken;
        }
        if (_parsingContext.expectComma()) {
            i_skipWSOrEnd = this._skipComma(i_skipWSOrEnd);
            if (0 != (this._features & FEAT_MASK_TRAILING_COMMA) && (93 == i_skipWSOrEnd || 125 == i_skipWSOrEnd)) {
                this._closeScope(i_skipWSOrEnd);
                return _currToken;
            }
        }
        final boolean zInObject = _parsingContext.inObject();
        if (zInObject) {
            this._updateNameLocation();
            _parsingContext.setCurrentName(34 == i_skipWSOrEnd ? this._parseName() : this._handleOddName(i_skipWSOrEnd));
            _currToken = jsonToken2;
            i_skipWSOrEnd = this._skipColon();
        }
        this._updateLocation();
        if (34 == i_skipWSOrEnd) {
            _tokenIncomplete = true;
            jsonToken_parseNegNumber = JsonToken.VALUE_STRING;
        } else if (91 == i_skipWSOrEnd) {
            if (!zInObject) {
                _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
            }
            jsonToken_parseNegNumber = JsonToken.START_ARRAY;
        } else if (102 == i_skipWSOrEnd) {
            this._matchFalse();
            jsonToken_parseNegNumber = JsonToken.VALUE_FALSE;
        } else if (110 == i_skipWSOrEnd) {
            this._matchNull();
            jsonToken_parseNegNumber = JsonToken.VALUE_NULL;
        } else if (116 == i_skipWSOrEnd) {
            this._matchTrue();
            jsonToken_parseNegNumber = JsonToken.VALUE_TRUE;
        } else if (123 == i_skipWSOrEnd) {
            if (!zInObject) {
                _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
            }
            jsonToken_parseNegNumber = JsonToken.START_OBJECT;
        } else if (125 == i_skipWSOrEnd) {
            this._reportUnexpectedChar(125, "expected a value");
            this._matchTrue();
            jsonToken_parseNegNumber = JsonToken.VALUE_TRUE;
        } else if (45 == i_skipWSOrEnd) {
            jsonToken_parseNegNumber = this._parseNegNumber();
        } else if (46 == i_skipWSOrEnd) {
            jsonToken_parseNegNumber = this._parseFloatThatStartsWithPeriod();
        } else {
            switch (i_skipWSOrEnd) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    jsonToken_parseNegNumber = this._parsePosNumber(i_skipWSOrEnd);
                    break;
                default:
                    jsonToken_parseNegNumber = this._handleOddValue(i_skipWSOrEnd);
                    break;
            }
        }
        if (zInObject) {
            _nextToken = jsonToken_parseNegNumber;
            return _currToken;
        }
        _currToken = jsonToken_parseNegNumber;
        return jsonToken_parseNegNumber;
    }
    private JsonToken _nextAfterName() {
        _nameCopied = false;
        final JsonToken jsonToken = _nextToken;
        _nextToken = null;
        if (JsonToken.START_ARRAY == jsonToken) {
            _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
        } else if (JsonToken.START_OBJECT == jsonToken) {
            _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
        }
        _currToken = jsonToken;
        return jsonToken;
    }
    public String nextFieldName() throws IOException {
        final JsonToken jsonToken_parseNegNumber;
        _numTypesValid = 0;
        final JsonToken jsonToken = _currToken;
        final JsonToken jsonToken2 = JsonToken.FIELD_NAME;
        if (jsonToken == jsonToken2) {
            this._nextAfterName();
            return null;
        }
        if (_tokenIncomplete) {
            this._skipString();
        }
        int i_skipWSOrEnd = this._skipWSOrEnd();
        if (0 > i_skipWSOrEnd) {
            this.close();
            _currToken = null;
            return null;
        }
        _binaryValue = null;
        if (93 == i_skipWSOrEnd || 125 == i_skipWSOrEnd) {
            this._closeScope(i_skipWSOrEnd);
            return null;
        }
        if (_parsingContext.expectComma()) {
            i_skipWSOrEnd = this._skipComma(i_skipWSOrEnd);
            if (0 != (this._features & FEAT_MASK_TRAILING_COMMA) && (93 == i_skipWSOrEnd || 125 == i_skipWSOrEnd)) {
                this._closeScope(i_skipWSOrEnd);
                return null;
            }
        }
        if (!_parsingContext.inObject()) {
            this._updateLocation();
            this._nextTokenNotInObject(i_skipWSOrEnd);
            return null;
        }
        this._updateNameLocation();
        final String str_parseName = 34 == i_skipWSOrEnd ? this._parseName() : this._handleOddName(i_skipWSOrEnd);
        _parsingContext.setCurrentName(str_parseName);
        _currToken = jsonToken2;
        final int i_skipColon = this._skipColon();
        this._updateLocation();
        if (34 == i_skipColon) {
            _tokenIncomplete = true;
            _nextToken = JsonToken.VALUE_STRING;
            return str_parseName;
        }
        if (45 == i_skipColon) {
            jsonToken_parseNegNumber = this._parseNegNumber();
        } else if (46 == i_skipColon) {
            jsonToken_parseNegNumber = this._parseFloatThatStartsWithPeriod();
        } else if (91 == i_skipColon) {
            jsonToken_parseNegNumber = JsonToken.START_ARRAY;
        } else if (102 == i_skipColon) {
            this._matchFalse();
            jsonToken_parseNegNumber = JsonToken.VALUE_FALSE;
        } else if (110 == i_skipColon) {
            this._matchNull();
            jsonToken_parseNegNumber = JsonToken.VALUE_NULL;
        } else if (116 == i_skipColon) {
            this._matchTrue();
            jsonToken_parseNegNumber = JsonToken.VALUE_TRUE;
        } else if (123 != i_skipColon) {
            switch (i_skipColon) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    jsonToken_parseNegNumber = this._parsePosNumber(i_skipColon);
                    break;
                default:
                    jsonToken_parseNegNumber = this._handleOddValue(i_skipColon);
                    break;
            }
        } else {
            jsonToken_parseNegNumber = JsonToken.START_OBJECT;
        }
        _nextToken = jsonToken_parseNegNumber;
        return str_parseName;
    }
    private JsonToken _nextTokenNotInObject(final int i2) throws IOException {
        if (34 == i2) {
            _tokenIncomplete = true;
            final JsonToken jsonToken = JsonToken.VALUE_STRING;
            _currToken = jsonToken;
            return jsonToken;
        }
        if (91 == i2) {
            _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
            final JsonToken jsonToken2 = JsonToken.START_ARRAY;
            _currToken = jsonToken2;
            return jsonToken2;
        }
        if (102 == i2) {
            this._matchToken("false", 1);
            final JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
            _currToken = jsonToken3;
            return jsonToken3;
        }
        if (110 == i2) {
            this._matchToken("null", 1);
            final JsonToken jsonToken4 = JsonToken.VALUE_NULL;
            _currToken = jsonToken4;
            return jsonToken4;
        }
        if (116 == i2) {
            this._matchToken("true", 1);
            final JsonToken jsonToken5 = JsonToken.VALUE_TRUE;
            _currToken = jsonToken5;
            return jsonToken5;
        }
        if (123 == i2) {
            _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
            final JsonToken jsonToken6 = JsonToken.START_OBJECT;
            _currToken = jsonToken6;
            return jsonToken6;
        }
        switch (i2) {
            case 44:
                if (!_parsingContext.inRoot() && 0 != (this._features & FEAT_MASK_ALLOW_MISSING)) {
                    _inputPtr--;
                    final JsonToken jsonToken7 = JsonToken.VALUE_NULL;
                    _currToken = jsonToken7;
                    return jsonToken7;
                }
                break;
            case 45:
                final JsonToken jsonToken_parseNegNumber = this._parseNegNumber();
                _currToken = jsonToken_parseNegNumber;
                return jsonToken_parseNegNumber;
            case 46:
                final JsonToken jsonToken_parseFloatThatStartsWithPeriod = this._parseFloatThatStartsWithPeriod();
                _currToken = jsonToken_parseFloatThatStartsWithPeriod;
                return jsonToken_parseFloatThatStartsWithPeriod;
            default:
                switch (i2) {
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        final JsonToken jsonToken_parsePosNumber = this._parsePosNumber(i2);
                        _currToken = jsonToken_parsePosNumber;
                        return jsonToken_parsePosNumber;
                }
        }
        final JsonToken jsonToken_handleOddValue = this._handleOddValue(i2);
        _currToken = jsonToken_handleOddValue;
        return jsonToken_handleOddValue;
    }
    public final String nextTextValue() throws IOException {
        if (JsonToken.FIELD_NAME == this._currToken) {
            _nameCopied = false;
            final JsonToken jsonToken = _nextToken;
            _nextToken = null;
            _currToken = jsonToken;
            if (JsonToken.VALUE_STRING == jsonToken) {
                if (_tokenIncomplete) {
                    _tokenIncomplete = false;
                    this._finishString();
                }
                return _textBuffer.contentsAsString();
            }
            if (JsonToken.START_ARRAY == jsonToken) {
                _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
            } else if (JsonToken.START_OBJECT == jsonToken) {
                _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
            }
            return null;
        }
        if (JsonToken.VALUE_STRING == nextToken()) {
            return this.getText();
        }
        return null;
    }
    protected final JsonToken _parseFloatThatStartsWithPeriod() throws IOException {
        if (!this.isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
            return this._handleOddValue(46);
        }
        final int i2 = _inputPtr;
        return this._parseFloat(46, i2 - 1, i2, false, 0);
    }
    private JsonToken _parseFloat(final int i, final int i1, final int i2, final boolean b, final int i3) {
        return null;
    }
    protected final JsonToken _parsePosNumber(final int i2) throws IOException {
        int i3 = _inputPtr;
        final int i4 = i3 - 1;
        final int i5 = _inputEnd;
        if (48 == i2) {
            return this._parseNumber2(false, i4);
        }
        int i6 = 1;
        while (i3 < i5) {
            final int i7 = i3 + 1;
            final char c2 = _inputBuffer[i3];
            if ('0' > c2 || '9' < c2) {
                if ('.' == c2 || 'e' == c2 || 'E' == c2) {
                    _inputPtr = i7;
                    return this._parseFloat(c2, i4, i7, false, i6);
                }
                _inputPtr = i3;
                if (_parsingContext.inRoot()) {
                    this._verifyRootSpace(c2);
                }
                _textBuffer.resetWithShared(_inputBuffer, i4, i3 - i4);
                return this.resetInt(false, i6);
            }
            i6++;
            i3 = i7;
        }
        _inputPtr = i4;
        return this._parseNumber2(false, i4);
    }
    private void _verifyRootSpace(final char c2) {
    }
    protected final JsonToken _parseNegNumber() throws IOException {
        final int i2 = _inputPtr;
        final int i3 = i2 - 1;
        final int i4 = _inputEnd;
        if (i2 >= i4) {
            return this._parseNumber2(true, i3);
        }
        int i5 = i2 + 1;
        final char c2 = _inputBuffer[i2];
        if ('9' < c2 || '0' > c2) {
            _inputPtr = i5;
            return this._handleInvalidNumberStart(c2, true);
        }
        if ('0' == c2) {
            return this._parseNumber2(true, i3);
        }
        int i6 = 1;
        while (i5 < i4) {
            final int i7 = i5 + 1;
            final char c3 = _inputBuffer[i5];
            if ('0' > c3 || '9' < c3) {
                if ('.' == c3 || 'e' == c3 || 'E' == c3) {
                    _inputPtr = i7;
                    return this._parseFloat(c3, i3, i7, true, i6);
                }
                _inputPtr = i5;
                if (_parsingContext.inRoot()) {
                    this._verifyRootSpace(c3);
                }
                _textBuffer.resetWithShared(_inputBuffer, i3, i5 - i3);
                return this.resetInt(true, i6);
            }
            i6++;
            i5 = i7;
        }
        return this._parseNumber2(true, i3);
    }
    private JsonToken _handleInvalidNumberStart(final char c2, final boolean b) {
        return null;
    }
    private JsonToken _parseNumber2(final boolean z, int i2) throws IOException {
        int i3;
        char nextChar;
        boolean z2;
        int i4;
        char nextChar2;
        if (z) {
            i2++;
        }
        _inputPtr = i2;
        char[] cArrEmptyAndGetCurrentSegment = _textBuffer.emptyAndGetCurrentSegment();
        int i5 = 0;
        if (z) {
            cArrEmptyAndGetCurrentSegment[0] = '-';
            i3 = 1;
        } else {
            i3 = 0;
        }
        final int i6 = _inputPtr;
        if (i6 < _inputEnd) {
            final char[] cArr = _inputBuffer;
            _inputPtr = i6 + 1;
            nextChar = cArr[i6];
        } else {
            nextChar = this.getNextChar("No digit following minus sign", JsonToken.VALUE_NUMBER_INT);
        }
        if ('0' == nextChar) {
            nextChar = this._verifyNoLeadingZeroes();
        }
        int i7 = 0;
        while ('0' <= nextChar && '9' >= nextChar) {
            i7++;
            if (i3 >= cArrEmptyAndGetCurrentSegment.length) {
                cArrEmptyAndGetCurrentSegment = _textBuffer.finishCurrentSegment();
                i3 = 0;
            }
            final int i8 = i3 + 1;
            cArrEmptyAndGetCurrentSegment[i3] = nextChar;
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                nextChar = 0;
                i3 = i8;
                z2 = true;
                break;
            }
            final char[] cArr2 = _inputBuffer;
            final int i9 = _inputPtr;
            _inputPtr = i9 + 1;
            nextChar = cArr2[i9];
            i3 = i8;
        }
        z2 = false;
        if (0 == i7) {
            return this._handleInvalidNumberStart(nextChar, z);
        }
        if ('.' == nextChar) {
            if (i3 >= cArrEmptyAndGetCurrentSegment.length) {
                cArrEmptyAndGetCurrentSegment = _textBuffer.finishCurrentSegment();
                i3 = 0;
            }
            cArrEmptyAndGetCurrentSegment[i3] = '.';
            i3++;
            i4 = 0;
            while (true) {
                if (_inputPtr >= _inputEnd && !this._loadMore()) {
                    z2 = true;
                    break;
                }
                final char[] cArr3 = _inputBuffer;
                final int i10 = _inputPtr;
                _inputPtr = i10 + 1;
                nextChar = cArr3[i10];
                if ('0' > nextChar || '9' < nextChar) {
                    break;
                }
                i4++;
                if (i3 >= cArrEmptyAndGetCurrentSegment.length) {
                    cArrEmptyAndGetCurrentSegment = _textBuffer.finishCurrentSegment();
                    i3 = 0;
                }
                cArrEmptyAndGetCurrentSegment[i3] = nextChar;
                i3++;
            }
            if (0 == i4) {
                this.reportUnexpectedNumberChar(nextChar, "Decimal point not followed by a digit");
            }
        } else {
            i4 = 0;
        }
        if ('e' == nextChar || 'E' == nextChar) {
            if (i3 >= cArrEmptyAndGetCurrentSegment.length) {
                cArrEmptyAndGetCurrentSegment = _textBuffer.finishCurrentSegment();
                i3 = 0;
            }
            int i11 = i3 + 1;
            cArrEmptyAndGetCurrentSegment[i3] = nextChar;
            final int i12 = _inputPtr;
            if (i12 < _inputEnd) {
                final char[] cArr4 = _inputBuffer;
                _inputPtr = i12 + 1;
                nextChar2 = cArr4[i12];
            } else {
                nextChar2 = this.getNextChar("expected a digit for number exponent");
            }
            if ('-' == nextChar2 || '+' == nextChar2) {
                if (i11 >= cArrEmptyAndGetCurrentSegment.length) {
                    cArrEmptyAndGetCurrentSegment = _textBuffer.finishCurrentSegment();
                    i11 = 0;
                }
                final int i13 = i11 + 1;
                cArrEmptyAndGetCurrentSegment[i11] = nextChar2;
                final int i14 = _inputPtr;
                if (i14 < _inputEnd) {
                    final char[] cArr5 = _inputBuffer;
                    _inputPtr = i14 + 1;
                    nextChar2 = cArr5[i14];
                } else {
                    nextChar2 = this.getNextChar("expected a digit for number exponent");
                }
                i11 = i13;
            }
            int i15 = 0;
            nextChar = nextChar2;
            while ('9' >= nextChar && '0' <= nextChar) {
                i15++;
                if (i11 >= cArrEmptyAndGetCurrentSegment.length) {
                    cArrEmptyAndGetCurrentSegment = _textBuffer.finishCurrentSegment();
                    i11 = 0;
                }
                i3 = i11 + 1;
                cArrEmptyAndGetCurrentSegment[i11] = nextChar;
                if (_inputPtr >= _inputEnd && !this._loadMore()) {
                    i5 = i15;
                    z2 = true;
                    break;
                }
                final char[] cArr6 = _inputBuffer;
                final int i16 = _inputPtr;
                _inputPtr = i16 + 1;
                nextChar = cArr6[i16];
                i11 = i3;
            }
            i5 = i15;
            i3 = i11;
            if (0 == i5) {
                this.reportUnexpectedNumberChar(nextChar, "Exponent indicator not followed by a digit");
            }
        }
        if (!z2) {
            _inputPtr--;
            if (_parsingContext.inRoot()) {
                this._verifyRootSpace(nextChar);
            }
        }
        _textBuffer.setCurrentLength(i3);
        return this.reset(z, i7, i4, i5);
    }
    private char _verifyNoLeadingZeroes() throws IOException {
        final char c2;
        final int i2 = _inputPtr;
        if (i2 >= _inputEnd || ('0' <= (c2 = this._inputBuffer[i2]) && '9' >= c2)) {
            return this._verifyNLZ2();
        }
        return '0';
    }
    private char _verifyNLZ2() throws IOException {
        char c2;
        if ((_inputPtr >= _inputEnd && !this._loadMore()) || '0' > (c2 = this._inputBuffer[this._inputPtr]) || '9' < c2) {
            return '0';
        }
        if (0 == (this._features & FEAT_MASK_LEADING_ZEROS)) {
            this.reportInvalidNumber("Leading zeroes not allowed");
        }
        _inputPtr++;
        if ('0' == c2) {
            do {
                if (_inputPtr >= _inputEnd && !this._loadMore()) {
                    break;
                }
                final char[] cArr = _inputBuffer;
                final int i2 = _inputPtr;
                c2 = cArr[i2];
                if ('0' > c2 || '9' < c2) {
                    return '0';
                }
                _inputPtr = i2 + 1;
            } while ('0' == c2);
        }
        return c2;
    }
    private void _verifyRootSpace(final int i2) throws IOException {
        final int i3 = _inputPtr + 1;
        _inputPtr = i3;
        if (9 != i2) {
            if (10 == i2) {
                _currInputRow++;
                _currInputRowStart = i3;
            } else if (13 == i2) {
                this._skipCR();
            } else if (32 != i2) {
                this._reportMissingRootWS(i2);
            }
        }
    }
    protected final String _parseName() throws IOException {
        int i2 = _inputPtr;
        int i3 = _hashSeed;
        final int[] iArr = ReaderBasedJsonParser._icLatin1;
        while (true) {
            if (i2 >= _inputEnd) {
                break;
            }
            final char[] cArr = _inputBuffer;
            final char c2 = cArr[i2];
            if (c2 >= iArr.length || 0 == iArr[c2]) {
                i3 = (i3 * 33) + c2;
                i2++;
            } else if ('\"' == c2) {
                final int i4 = _inputPtr;
                _inputPtr = i2 + 1;
                return _symbols.findSymbol(cArr, i4, i2 - i4, i3);
            }
        }
        final int i5 = _inputPtr;
        _inputPtr = i2;
        return this._parseName2(i5, i3, 34);
    }
    private String _parseName2(final int i2, int i3, final int i4) throws IOException {
        _textBuffer.resetWithShared(_inputBuffer, i2, _inputPtr - i2);
        char[] currentSegment = _textBuffer.getCurrentSegment();
        int currentSegmentSize = _textBuffer.getCurrentSegmentSize();
        while (true) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                this._reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
            }
            final char[] cArr = _inputBuffer;
            final int i5 = _inputPtr;
            _inputPtr = i5 + 1;
            char c_decodeEscaped = cArr[i5];
            if ('\\' >= c_decodeEscaped) {
                if ('\\' == c_decodeEscaped) {
                    c_decodeEscaped = this._decodeEscaped();
                } else if (c_decodeEscaped <= i4) {
                    if (c_decodeEscaped == i4) {
                        _textBuffer.setCurrentLength(currentSegmentSize);
                        final TextBuffer textBuffer = _textBuffer;
                        return _symbols.findSymbol(textBuffer.getTextBuffer(), textBuffer.getTextOffset(), textBuffer.size(), i3);
                    }
                    if (' ' > c_decodeEscaped) {
                        this._throwUnquotedSpace(c_decodeEscaped, "name");
                    }
                }
            }
            i3 = (i3 * 33) + c_decodeEscaped;
            final int i6 = currentSegmentSize + 1;
            currentSegment[currentSegmentSize] = c_decodeEscaped;
            if (i6 >= currentSegment.length) {
                currentSegment = _textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            } else {
                currentSegmentSize = i6;
            }
        }
    }
    protected String _handleOddName(final int i2) throws IOException {
        final boolean zIsJavaIdentifierPart;
        if (39 == i2 && 0 != (this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES)) {
            return this._parseAposName();
        }
        if (0 == (this._features & FEAT_MASK_ALLOW_UNQUOTED_NAMES)) {
            this._reportUnexpectedChar(i2, "was expecting double-quote to start field name");
        }
        final int[] inputCodeLatin1JsNames = CharTypes.getInputCodeLatin1JsNames();
        final int length = inputCodeLatin1JsNames.length;
        if (i2 < length) {
            zIsJavaIdentifierPart = 0 == inputCodeLatin1JsNames[i2];
        } else {
            zIsJavaIdentifierPart = Character.isJavaIdentifierPart((char) i2);
        }
        if (!zIsJavaIdentifierPart) {
            this._reportUnexpectedChar(i2, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int i3 = _inputPtr;
        int i4 = _hashSeed;
        final int i5 = _inputEnd;
        if (i3 < i5) {
            do {
                final char[] cArr = _inputBuffer;
                final char c2 = cArr[i3];
                if (c2 < length) {
                    if (0 != inputCodeLatin1JsNames[c2]) {
                        final int i6 = _inputPtr - 1;
                        _inputPtr = i3;
                        return _symbols.findSymbol(cArr, i6, i3 - i6, i4);
                    }
                } else if (!Character.isJavaIdentifierPart(c2)) {
                    final int i7 = _inputPtr - 1;
                    _inputPtr = i3;
                    return _symbols.findSymbol(_inputBuffer, i7, i3 - i7, i4);
                }
                i4 = (i4 * 33) + c2;
                i3++;
            } while (i3 < i5);
        }
        final int i8 = _inputPtr - 1;
        _inputPtr = i3;
        return this._handleOddName2(i8, i4, inputCodeLatin1JsNames);
    }
    protected String _parseAposName() throws IOException {
        int i2 = _inputPtr;
        int i3 = _hashSeed;
        final int i4 = _inputEnd;
        if (i2 < i4) {
            final int[] iArr = ReaderBasedJsonParser._icLatin1;
            final int length = iArr.length;
            do {
                final char[] cArr = _inputBuffer;
                final char c2 = cArr[i2];
                if ('\'' == c2) {
                    final int i5 = _inputPtr;
                    _inputPtr = i2 + 1;
                    return _symbols.findSymbol(cArr, i5, i2 - i5, i3);
                }
                if (c2 < length && 0 != iArr[c2]) {
                    break;
                }
                i3 = (i3 * 33) + c2;
                i2++;
            } while (i2 < i4);
        }
        final int i6 = _inputPtr;
        _inputPtr = i2;
        return this._parseName2(i6, i3, 39);
    }
    protected JsonToken _handleOddValue(final int i2) throws IOException {
        if (39 != i2) {
            if (73 == i2) {
                this._matchToken("Infinity", 1);
                if (0 != (this._features & FEAT_MASK_NON_NUM_NUMBERS)) {
                    return this.resetAsNaN("Infinity", Double.POSITIVE_INFINITY);
                }
                this._reportError("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            } else if (78 == i2) {
                this._matchToken("NaN", 1);
                if (0 != (this._features & FEAT_MASK_NON_NUM_NUMBERS)) {
                    return this.resetAsNaN("NaN", Double.NaN);
                }
                this._reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            } else if (93 != i2) {
                if (43 == i2) {
                    if (_inputPtr >= _inputEnd && !this._loadMore()) {
                        this._reportInvalidEOFInValue(JsonToken.VALUE_NUMBER_INT);
                    }
                    final char[] cArr = _inputBuffer;
                    final int i3 = _inputPtr;
                    _inputPtr = i3 + 1;
                    return this._handleInvalidNumberStart(cArr[i3], false);
                }
                if (44 == i2) {
                    if (!_parsingContext.inRoot() && 0 != (this._features & FEAT_MASK_ALLOW_MISSING)) {
                        _inputPtr--;
                        return JsonToken.VALUE_NULL;
                    }
                }
            } else if (_parsingContext.inArray()) {
            }
        } else if (0 != (this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES)) {
            return this._handleApos();
        }
        if (Character.isJavaIdentifierStart(i2)) {
            this._reportInvalidToken("" + ((char) i2), this._validJsonTokenList());
        }
        this._reportUnexpectedChar(i2, "expected a valid value " + this._validJsonValueList());
        return null;
    }
    protected JsonToken _handleApos() throws IOException {
        char[] cArrEmptyAndGetCurrentSegment = _textBuffer.emptyAndGetCurrentSegment();
        int currentSegmentSize = _textBuffer.getCurrentSegmentSize();
        while (true) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                this._reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
            }
            final char[] cArr = _inputBuffer;
            final int i2 = _inputPtr;
            _inputPtr = i2 + 1;
            char c_decodeEscaped = cArr[i2];
            if ('\\' >= c_decodeEscaped) {
                if ('\\' == c_decodeEscaped) {
                    c_decodeEscaped = this._decodeEscaped();
                } else if ('\'' >= c_decodeEscaped) {
                    if ('\'' == c_decodeEscaped) {
                        _textBuffer.setCurrentLength(currentSegmentSize);
                        return JsonToken.VALUE_STRING;
                    }
                    if (' ' > c_decodeEscaped) {
                        this._throwUnquotedSpace(c_decodeEscaped, "string value");
                    }
                }
            }
            if (currentSegmentSize >= cArrEmptyAndGetCurrentSegment.length) {
                cArrEmptyAndGetCurrentSegment = _textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            cArrEmptyAndGetCurrentSegment[currentSegmentSize] = c_decodeEscaped;
            currentSegmentSize++;
        }
    }
    private String _handleOddName2(final int i2, int i3, final int[] iArr) throws IOException {
        int i4;
        _textBuffer.resetWithShared(_inputBuffer, i2, _inputPtr - i2);
        char[] currentSegment = _textBuffer.getCurrentSegment();
        int currentSegmentSize = _textBuffer.getCurrentSegmentSize();
        final int length = iArr.length;
        while (true) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                break;
            }
            final char c2 = _inputBuffer[_inputPtr];
            if (c2 < length) {
                if (0 != iArr[c2]) {
                    break;
                }
                _inputPtr++;
                i3 = (i3 * 33) + c2;
                i4 = currentSegmentSize + 1;
                currentSegment[currentSegmentSize] = c2;
                if (i4 < currentSegment.length) {
                    currentSegment = _textBuffer.finishCurrentSegment();
                    currentSegmentSize = 0;
                } else {
                    currentSegmentSize = i4;
                }
            } else {
                if (!Character.isJavaIdentifierPart(c2)) {
                    break;
                }
                _inputPtr++;
                i3 = (i3 * 33) + c2;
                i4 = currentSegmentSize + 1;
                currentSegment[currentSegmentSize] = c2;
                if (i4 < currentSegment.length) {
                }
            }
        }
        _textBuffer.setCurrentLength(currentSegmentSize);
        final TextBuffer textBuffer = _textBuffer;
        return _symbols.findSymbol(textBuffer.getTextBuffer(), textBuffer.getTextOffset(), textBuffer.size(), i3);
    }
    protected final void _finishString() throws IOException {
        int i2 = _inputPtr;
        final int i3 = _inputEnd;
        if (i2 < i3) {
            final int[] iArr = ReaderBasedJsonParser._icLatin1;
            final int length = iArr.length;
            while (true) {
                final char[] cArr = _inputBuffer;
                final char c2 = cArr[i2];
                if (c2 >= length || 0 == iArr[c2]) {
                    i2++;
                    if (i2 >= i3) {
                        break;
                    }
                } else if ('\"' == c2) {
                    final TextBuffer textBuffer = _textBuffer;
                    final int i4 = _inputPtr;
                    textBuffer.resetWithShared(cArr, i4, i2 - i4);
                    _inputPtr = i2 + 1;
                    return;
                }
            }
        }
        final TextBuffer textBuffer2 = _textBuffer;
        final char[] cArr2 = _inputBuffer;
        final int i5 = _inputPtr;
        textBuffer2.resetWithCopy(cArr2, i5, i2 - i5);
        _inputPtr = i2;
        this._finishString2();
    }
    protected void _finishString2() throws IOException {
        char[] currentSegment = _textBuffer.getCurrentSegment();
        int currentSegmentSize = _textBuffer.getCurrentSegmentSize();
        final int[] iArr = ReaderBasedJsonParser._icLatin1;
        final int length = iArr.length;
        while (true) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                this._reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
            }
            final char[] cArr = _inputBuffer;
            final int i2 = _inputPtr;
            _inputPtr = i2 + 1;
            char c_decodeEscaped = cArr[i2];
            if (c_decodeEscaped < length && 0 != iArr[c_decodeEscaped]) {
                if ('\"' == c_decodeEscaped) {
                    _textBuffer.setCurrentLength(currentSegmentSize);
                    return;
                } else if ('\\' == c_decodeEscaped) {
                    c_decodeEscaped = this._decodeEscaped();
                } else if (' ' > c_decodeEscaped) {
                    this._throwUnquotedSpace(c_decodeEscaped, "string value");
                }
            }
            if (currentSegmentSize >= currentSegment.length) {
                currentSegment = _textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            currentSegment[currentSegmentSize] = c_decodeEscaped;
            currentSegmentSize++;
        }
    }
    protected final void _skipString() throws IOException {
        _tokenIncomplete = false;
        int i2 = _inputPtr;
        int i3 = _inputEnd;
        final char[] cArr = _inputBuffer;
        while (true) {
            if (i2 >= i3) {
                _inputPtr = i2;
                if (!this._loadMore()) {
                    this._reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
                }
                i2 = _inputPtr;
                i3 = _inputEnd;
            }
            final int i4 = i2 + 1;
            final char c2 = cArr[i2];
            if ('\\' >= c2) {
                if ('\\' == c2) {
                    _inputPtr = i4;
                    this._decodeEscaped();
                    i2 = _inputPtr;
                    i3 = _inputEnd;
                } else if ('\"' >= c2) {
                    if ('\"' == c2) {
                        _inputPtr = i4;
                        return;
                    } else if (' ' > c2) {
                        _inputPtr = i4;
                        this._throwUnquotedSpace(c2, "string value");
                    }
                }
            }
            i2 = i4;
        }
    }
    protected final void _skipCR() throws IOException {
        if (_inputPtr < _inputEnd || this._loadMore()) {
            final char[] cArr = _inputBuffer;
            final int i2 = _inputPtr;
            if ('\n' == cArr[i2]) {
                _inputPtr = i2 + 1;
            }
        }
        _currInputRow++;
        _currInputRowStart = _inputPtr;
    }
    private int _skipColon() throws IOException {
        final int i2 = _inputPtr;
        if (i2 + 4 >= _inputEnd) {
            return this._skipColon2(false);
        }
        final char[] cArr = _inputBuffer;
        char c2 = cArr[i2];
        if (':' == c2) {
            final int i3 = i2 + 1;
            _inputPtr = i3;
            final char c3 = cArr[i3];
            if (' ' < c3) {
                if ('/' == c3 || '#' == c3) {
                    return this._skipColon2(true);
                }
                _inputPtr = i2 + 2;
                return c3;
            }
            if (' ' == c3 || '\t' == c3) {
                final int i4 = i2 + 2;
                _inputPtr = i4;
                final char c4 = cArr[i4];
                if (' ' < c4) {
                    if ('/' == c4 || '#' == c4) {
                        return this._skipColon2(true);
                    }
                    _inputPtr = i2 + 3;
                    return c4;
                }
            }
            return this._skipColon2(true);
        }
        if (' ' == c2 || '\t' == c2) {
            final int i5 = i2 + 1;
            _inputPtr = i5;
            c2 = cArr[i5];
        }
        if (':' == c2) {
            final int i6 = _inputPtr;
            final int i7 = i6 + 1;
            _inputPtr = i7;
            final char c5 = cArr[i7];
            if (' ' < c5) {
                if ('/' == c5 || '#' == c5) {
                    return this._skipColon2(true);
                }
                _inputPtr = i6 + 2;
                return c5;
            }
            if (' ' == c5 || '\t' == c5) {
                final int i8 = i6 + 2;
                _inputPtr = i8;
                final char c6 = cArr[i8];
                if (' ' < c6) {
                    if ('/' == c6 || '#' == c6) {
                        return this._skipColon2(true);
                    }
                    _inputPtr = i6 + 3;
                    return c6;
                }
            }
            return this._skipColon2(true);
        }
        return this._skipColon2(false);
    }
    private int _skipColon2(boolean z) throws IOException {
        while (true) {
            if (_inputPtr < _inputEnd || this._loadMore()) {
                final char[] cArr = _inputBuffer;
                final int i2 = _inputPtr;
                final int i3 = i2 + 1;
                _inputPtr = i3;
                final char c2 = cArr[i2];
                if (' ' < c2) {
                    if ('/' == c2) {
                        this._skipComment();
                    } else if ('#' != c2 || !this._skipYAMLComment()) {
                        if (z) {
                            return c2;
                        }
                        if (':' != c2) {
                            this._reportUnexpectedChar(c2, "was expecting a colon to separate field name and value");
                        }
                        z = true;
                    }
                } else if (' ' > c2) {
                    if ('\n' == c2) {
                        _currInputRow++;
                        _currInputRowStart = i3;
                    } else if ('\r' == c2) {
                        this._skipCR();
                    } else if ('\t' != c2) {
                        this._throwInvalidSpace(c2);
                    }
                }
            } else {
                this._reportInvalidEOF(" within/between " + _parsingContext.typeDesc() + " entries", null);
                return -1;
            }
        }
    }
    private int _skipComma(final int i2) throws IOException {
        if (44 != i2) {
            this._reportUnexpectedChar(i2, "was expecting comma to separate " + _parsingContext.typeDesc() + " entries");
        }
        while (true) {
            final int i3 = _inputPtr;
            if (i3 < _inputEnd) {
                final char[] cArr = _inputBuffer;
                final int i4 = i3 + 1;
                _inputPtr = i4;
                final char c2 = cArr[i3];
                if (' ' < c2) {
                    if ('/' != c2 && '#' != c2) {
                        return c2;
                    }
                    _inputPtr = i3;
                    return this._skipAfterComma2();
                }
                if (' ' > c2) {
                    if ('\n' == c2) {
                        _currInputRow++;
                        _currInputRowStart = i4;
                    } else if ('\r' == c2) {
                        this._skipCR();
                    } else if ('\t' != c2) {
                        this._throwInvalidSpace(c2);
                    }
                }
            } else {
                return this._skipAfterComma2();
            }
        }
    }
    private int _skipAfterComma2() throws IOException {
        while (true) {
            if (_inputPtr < _inputEnd || this._loadMore()) {
                final char[] cArr = _inputBuffer;
                final int i2 = _inputPtr;
                final int i3 = i2 + 1;
                _inputPtr = i3;
                final char c2 = cArr[i2];
                if (' ' < c2) {
                    if ('/' == c2) {
                        this._skipComment();
                    } else if ('#' != c2 || !this._skipYAMLComment()) {
                        break;
                    }
                } else if (' ' > c2) {
                    if ('\n' == c2) {
                        _currInputRow++;
                        _currInputRowStart = i3;
                    } else if ('\r' == c2) {
                        this._skipCR();
                    } else if ('\t' != c2) {
                        this._throwInvalidSpace(c2);
                    }
                }
            } else {
                throw this._constructError("Unexpected end-of-input within/between " + _parsingContext.typeDesc() + " entries");
            }
        }
        return 0;
    }
    private int _skipWSOrEnd() throws IOException {
        if (_inputPtr >= _inputEnd && !this._loadMore()) {
            return this._eofAsNextChar();
        }
        final char[] cArr = _inputBuffer;
        final int i2 = _inputPtr;
        final int i3 = i2 + 1;
        _inputPtr = i3;
        final char c2 = cArr[i2];
        if (' ' < c2) {
            if ('/' != c2 && '#' != c2) {
                return c2;
            }
            _inputPtr = i2;
            return this._skipWSOrEnd2();
        }
        if (' ' != c2) {
            if ('\n' == c2) {
                _currInputRow++;
                _currInputRowStart = i3;
            } else if ('\r' == c2) {
                this._skipCR();
            } else if ('\t' != c2) {
                this._throwInvalidSpace(c2);
            }
        }
        while (true) {
            final int i4 = _inputPtr;
            if (i4 < _inputEnd) {
                final char[] cArr2 = _inputBuffer;
                final int i5 = i4 + 1;
                _inputPtr = i5;
                final char c3 = cArr2[i4];
                if (' ' < c3) {
                    if ('/' != c3 && '#' != c3) {
                        return c3;
                    }
                    _inputPtr = i4;
                    return this._skipWSOrEnd2();
                }
                if (' ' != c3) {
                    if ('\n' == c3) {
                        _currInputRow++;
                        _currInputRowStart = i5;
                    } else if ('\r' == c3) {
                        this._skipCR();
                    } else if ('\t' != c3) {
                        this._throwInvalidSpace(c3);
                    }
                }
            } else {
                return this._skipWSOrEnd2();
            }
        }
    }
    private int _skipWSOrEnd2() throws IOException {
        while (true) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                return this._eofAsNextChar();
            }
            final char[] cArr = _inputBuffer;
            final int i2 = _inputPtr;
            final int i3 = i2 + 1;
            _inputPtr = i3;
            final char c2 = cArr[i2];
            if (' ' < c2) {
                if ('/' == c2) {
                    this._skipComment();
                } else if ('#' != c2 || !this._skipYAMLComment()) {
                    break;
                }
            } else if (' ' != c2) {
                if ('\n' == c2) {
                    _currInputRow++;
                    _currInputRowStart = i3;
                } else if ('\r' == c2) {
                    this._skipCR();
                } else if ('\t' != c2) {
                    this._throwInvalidSpace(c2);
                }
            }
        }
        return 0;
    }
    private void _skipComment() throws IOException {
        if (0 == (this._features & FEAT_MASK_ALLOW_JAVA_COMMENTS)) {
            this._reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (_inputPtr >= _inputEnd && !this._loadMore()) {
            this._reportInvalidEOF(" in a comment", null);
        }
        final char[] cArr = _inputBuffer;
        final int i2 = _inputPtr;
        _inputPtr = i2 + 1;
        final char c2 = cArr[i2];
        if ('/' == c2) {
            this._skipLine();
        } else if ('*' == c2) {
            this._skipCComment();
        } else {
            this._reportUnexpectedChar(c2, "was expecting either '*' or '/' for a comment");
        }
    }
    private void _skipCComment() throws IOException {
        while (true) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                break;
            }
            final char[] cArr = _inputBuffer;
            final int i2 = _inputPtr;
            final int i3 = i2 + 1;
            _inputPtr = i3;
            final char c2 = cArr[i2];
            if ('*' >= c2) {
                if ('*' == c2) {
                    if (i3 >= _inputEnd && !this._loadMore()) {
                        break;
                    }
                    final char[] cArr2 = _inputBuffer;
                    final int i4 = _inputPtr;
                    if ('/' == cArr2[i4]) {
                        _inputPtr = i4 + 1;
                        return;
                    }
                } else if (' ' > c2) {
                    if ('\n' == c2) {
                        _currInputRow++;
                        _currInputRowStart = i3;
                    } else if ('\r' == c2) {
                        this._skipCR();
                    } else if ('\t' != c2) {
                        this._throwInvalidSpace(c2);
                    }
                }
            }
        }
        this._reportInvalidEOF(" in a comment", null);
    }
    private boolean _skipYAMLComment() throws IOException {
        if (0 == (this._features & FEAT_MASK_ALLOW_YAML_COMMENTS)) {
            return false;
        }
        this._skipLine();
        return true;
    }
    private void _skipLine() throws IOException {
        while (true) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                return;
            }
            final char[] cArr = _inputBuffer;
            final int i2 = _inputPtr;
            final int i3 = i2 + 1;
            _inputPtr = i3;
            final char c2 = cArr[i2];
            if (' ' > c2) {
                if ('\n' == c2) {
                    _currInputRow++;
                    _currInputRowStart = i3;
                    return;
                } else if ('\r' == c2) {
                    this._skipCR();
                    return;
                } else if ('\t' != c2) {
                    this._throwInvalidSpace(c2);
                }
            }
        }
    }
    protected char _decodeEscaped() throws IOException {
        if (_inputPtr >= _inputEnd && !this._loadMore()) {
            this._reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
        }
        final char[] cArr = _inputBuffer;
        final int i2 = _inputPtr;
        _inputPtr = i2 + 1;
        final char c2 = cArr[i2];
        if ('\"' == c2 || '/' == c2 || '\\' == c2) {
            return c2;
        }
        if ('b' == c2) {
            return '\b';
        }
        if ('f' == c2) {
            return '\f';
        }
        if ('n' == c2) {
            return '\n';
        }
        if ('r' == c2) {
            return '\r';
        }
        if ('t' == c2) {
            return '\t';
        }
        if ('u' != c2) {
            return this._handleUnrecognizedCharacterEscape(c2);
        }
        int i3 = 0;
        for (int i4 = 0; 4 > i4; i4++) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                this._reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
            }
            final char[] cArr2 = _inputBuffer;
            final int i5 = _inputPtr;
            _inputPtr = i5 + 1;
            final char c3 = cArr2[i5];
            final int iCharToHex = CharTypes.charToHex(c3);
            if (0 > iCharToHex) {
                this._reportUnexpectedChar(c3, "expected a hex-digit for character escape sequence");
            }
            i3 = (i3 << 4) | iCharToHex;
        }
        return (char) i3;
    }
    private void _matchTrue() throws IOException {
        final int i2;
        final char c2;
        final int i3 = _inputPtr;
        if (i3 + 3 < _inputEnd) {
            final char[] cArr = _inputBuffer;
            if ('r' == cArr[i3] && 'u' == cArr[i3 + 1] && 'e' == cArr[i3 + 2] && ('0' > (c2 = cArr[(i2 = i3 + 3)]) || ']' == c2 || '}' == c2)) {
                _inputPtr = i2;
                return;
            }
        }
        this._matchToken("true", 1);
    }
    private void _matchFalse() throws IOException {
        final int i2;
        final char c2;
        final int i3 = _inputPtr;
        if (i3 + 4 < _inputEnd) {
            final char[] cArr = _inputBuffer;
            if ('a' == cArr[i3] && 'l' == cArr[i3 + 1] && 's' == cArr[i3 + 2] && 'e' == cArr[i3 + 3] && ('0' > (c2 = cArr[(i2 = i3 + 4)]) || ']' == c2 || '}' == c2)) {
                _inputPtr = i2;
                return;
            }
        }
        this._matchToken("false", 1);
    }
    private void _matchNull() throws IOException {
        final int i2;
        final char c2;
        final int i3 = _inputPtr;
        if (i3 + 3 < _inputEnd) {
            final char[] cArr = _inputBuffer;
            if ('u' == cArr[i3] && 'l' == cArr[i3 + 1] && 'l' == cArr[i3 + 2] && ('0' > (c2 = cArr[(i2 = i3 + 3)]) || ']' == c2 || '}' == c2)) {
                _inputPtr = i2;
                return;
            }
        }
        this._matchToken("null", 1);
    }
    protected final void _matchToken(final String str, int i2) throws IOException {
        int i3;
        final int length = str.length();
        if (_inputPtr + length >= _inputEnd) {
            this._matchToken2(str, i2);
            return;
        }
        do {
            if (_inputBuffer[_inputPtr] != str.charAt(i2)) {
                this._reportInvalidToken(str.substring(0, i2));
            }
            i3 = _inputPtr + 1;
            _inputPtr = i3;
            i2++;
        } while (i2 < length);
        final char c2 = _inputBuffer[i3];
        if ('0' > c2 || ']' == c2 || '}' == c2) {
            return;
        }
        this._checkMatchEnd(str, i2, c2);
    }
    private void _matchToken2(final String str, int i2) throws IOException {
        int i3;
        final char c2;
        final int length = str.length();
        do {
            if ((_inputPtr >= _inputEnd && !this._loadMore()) || _inputBuffer[_inputPtr] != str.charAt(i2)) {
                this._reportInvalidToken(str.substring(0, i2));
            }
            i3 = _inputPtr + 1;
            _inputPtr = i3;
            i2++;
        } while (i2 < length);
        if ((i3 < _inputEnd || this._loadMore()) && '0' <= (c2 = this._inputBuffer[this._inputPtr]) && ']' != c2 && '}' != c2) {
            this._checkMatchEnd(str, i2, c2);
        }
    }
    private void _checkMatchEnd(final String str, final int i2, final int i3) throws IOException {
        if (Character.isJavaIdentifierPart((char) i3)) {
            this._reportInvalidToken(str.substring(0, i2));
        }
    }
    protected byte[] _decodeBase64(final Base64Variant base64Variant) throws IOException {
        final ByteArrayBuilder byteArrayBuilder_getByteArrayBuilder = this._getByteArrayBuilder();
        while (true) {
            if (_inputPtr >= _inputEnd) {
                this._loadMoreGuaranteed();
            }
            final char[] cArr = _inputBuffer;
            final int i2 = _inputPtr;
            _inputPtr = i2 + 1;
            final char c2 = cArr[i2];
            if (' ' < c2) {
                int iDecodeBase64Char = base64Variant.decodeBase64Char(c2);
                if (0 > iDecodeBase64Char) {
                    if ('\"' == c2) {
                        return byteArrayBuilder_getByteArrayBuilder.toByteArray();
                    }
                    iDecodeBase64Char = this._decodeBase64Escape(base64Variant, c2, 0);
                    if (0 > iDecodeBase64Char) {
                        continue;
                    }
                }
                if (_inputPtr >= _inputEnd) {
                    this._loadMoreGuaranteed();
                }
                final char[] cArr2 = _inputBuffer;
                final int i3 = _inputPtr;
                _inputPtr = i3 + 1;
                final char c3 = cArr2[i3];
                int iDecodeBase64Char2 = base64Variant.decodeBase64Char(c3);
                if (0 > iDecodeBase64Char2) {
                    iDecodeBase64Char2 = this._decodeBase64Escape(base64Variant, c3, 1);
                }
                final int i4 = (iDecodeBase64Char << 6) | iDecodeBase64Char2;
                if (_inputPtr >= _inputEnd) {
                    this._loadMoreGuaranteed();
                }
                final char[] cArr3 = _inputBuffer;
                final int i5 = _inputPtr;
                _inputPtr = i5 + 1;
                final char c4 = cArr3[i5];
                int iDecodeBase64Char3 = base64Variant.decodeBase64Char(c4);
                if (0 > iDecodeBase64Char3) {
                    if (-2 != iDecodeBase64Char3) {
                        if ('\"' == c4) {
                            byteArrayBuilder_getByteArrayBuilder.append(i4 >> 4);
                            if (base64Variant.usesPadding()) {
                                _inputPtr--;
                                this._handleBase64MissingPadding(base64Variant);
                            }
                            return byteArrayBuilder_getByteArrayBuilder.toByteArray();
                        }
                        iDecodeBase64Char3 = this._decodeBase64Escape(base64Variant, c4, 2);
                    }
                    if (-2 == iDecodeBase64Char3) {
                        if (_inputPtr >= _inputEnd) {
                            this._loadMoreGuaranteed();
                        }
                        final char[] cArr4 = _inputBuffer;
                        final int i6 = _inputPtr;
                        _inputPtr = i6 + 1;
                        final char c5 = cArr4[i6];
                        if (!base64Variant.usesPaddingChar(c5) && -2 != _decodeBase64Escape(base64Variant, c5, 3)) {
                            throw this.reportInvalidBase64Char(base64Variant, c5, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        byteArrayBuilder_getByteArrayBuilder.append(i4 >> 4);
                    }
                }
                final int i7 = (i4 << 6) | iDecodeBase64Char3;
                if (_inputPtr >= _inputEnd) {
                    this._loadMoreGuaranteed();
                }
                final char[] cArr5 = _inputBuffer;
                final int i8 = _inputPtr;
                _inputPtr = i8 + 1;
                final char c6 = cArr5[i8];
                int iDecodeBase64Char4 = base64Variant.decodeBase64Char(c6);
                if (0 > iDecodeBase64Char4) {
                    if (-2 != iDecodeBase64Char4) {
                        if ('\"' == c6) {
                            byteArrayBuilder_getByteArrayBuilder.appendTwoBytes(i7 >> 2);
                            if (base64Variant.usesPadding()) {
                                _inputPtr--;
                                this._handleBase64MissingPadding(base64Variant);
                            }
                            return byteArrayBuilder_getByteArrayBuilder.toByteArray();
                        }
                        iDecodeBase64Char4 = this._decodeBase64Escape(base64Variant, c6, 3);
                    }
                    if (-2 == iDecodeBase64Char4) {
                        byteArrayBuilder_getByteArrayBuilder.appendTwoBytes(i7 >> 2);
                    }
                }
                byteArrayBuilder_getByteArrayBuilder.appendThreeBytes((i7 << 6) | iDecodeBase64Char4);
            }
        }
    }
    public JsonLocation getTokenLocation() {
        if (JsonToken.FIELD_NAME == this._currToken) {
            return new JsonLocation(this._getSourceReference(), -1L, _currInputProcessed + (_nameStartOffset - 1), _nameStartRow, _nameStartCol);
        }
        return new JsonLocation(this._getSourceReference(), -1L, _tokenInputTotal - 1, _tokenInputRow, _tokenInputCol);
    }
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._getSourceReference(), -1L, _inputPtr + _currInputProcessed, _currInputRow, (_inputPtr - _currInputRowStart) + 1);
    }
    private void _updateLocation() {
        final int i2 = _inputPtr;
        _tokenInputTotal = _currInputProcessed + i2;
        _tokenInputRow = _currInputRow;
        _tokenInputCol = i2 - _currInputRowStart;
    }
    private void _updateNameLocation() {
        final int i2 = _inputPtr;
        _nameStartOffset = i2;
        _nameStartRow = _currInputRow;
        _nameStartCol = i2 - _currInputRowStart;
    }
    protected void _reportInvalidToken(final String str) throws IOException {
        this._reportInvalidToken(str, this._validJsonTokenList());
    }
    protected void _reportInvalidToken(final String str, final String str2) throws IOException {
        final StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                break;
            }
            final char c2 = _inputBuffer[_inputPtr];
            if (!Character.isJavaIdentifierPart(c2)) {
                break;
            }
            _inputPtr++;
            sb.append(c2);
            if (256 <= sb.length()) {
                sb.append("...");
                break;
            }
        }
        this._reportError("Unrecognized token '%s': was expecting %s", sb, str2);
    }
    private void _closeScope(final int i2) throws JsonParseException {
        if (93 == i2) {
            this._updateLocation();
            if (!_parsingContext.inArray()) {
                this._reportMismatchedEndMarker(93, '}');
            }
            _parsingContext = _parsingContext.clearAndGetParent();
            _currToken = JsonToken.END_ARRAY;
        }
        if (125 == i2) {
            this._updateLocation();
            if (!_parsingContext.inObject()) {
                this._reportMismatchedEndMarker(125, ']');
            }
            _parsingContext = _parsingContext.clearAndGetParent();
            _currToken = JsonToken.END_OBJECT;
        }
    }
}
