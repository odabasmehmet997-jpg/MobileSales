package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import org.kxml2.wap.Wbxml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UTF8StreamJsonParser extends ParserBase {
    protected boolean _bufferRecyclable;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;
    protected int _nameStartCol;
    protected int _nameStartOffset;
    protected int _nameStartRow;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer;
    protected final ByteQuadsCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    private static final int FEAT_MASK_TRAILING_COMMA = Feature.ALLOW_TRAILING_COMMA.getMask();
    private static final int FEAT_MASK_LEADING_ZEROS = Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
    private static final int FEAT_MASK_NON_NUM_NUMBERS = Feature.ALLOW_NON_NUMERIC_NUMBERS.getMask();
    private static final int FEAT_MASK_ALLOW_MISSING = Feature.ALLOW_MISSING_VALUES.getMask();
    private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = Feature.ALLOW_SINGLE_QUOTES.getMask();
    private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
    private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = Feature.ALLOW_COMMENTS.getMask();
    private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = Feature.ALLOW_YAML_COMMENTS.getMask();
    private static final int[] _icUTF8 = CharTypes.getInputCodeUtf8();
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
    private static final int _padLastQuad(final int i2, final int i3) {
        return 4 == i3 ? i2 : i2 | ((-1) << (i3 << 3));
    }
    public UTF8StreamJsonParser(final IOContext iOContext, final int i2, final InputStream inputStream, final ObjectCodec objectCodec, final ByteQuadsCanonicalizer byteQuadsCanonicalizer, final byte[] bArr, final int i3, final int i4, final int i5, final boolean z) {
        super(iOContext, i2);
        _quadBuffer = new int[16];
        _inputStream = inputStream;
        _objectCodec = objectCodec;
        _symbols = byteQuadsCanonicalizer;
        _inputBuffer = bArr;
        _inputPtr = i3;
        _inputEnd = i4;
        _currInputRowStart = i3 - i5;
        _currInputProcessed = (-i3) + i5;
        _bufferRecyclable = z;
    }
    public ObjectCodec getCodec() {
        return _objectCodec;
    }
    public JacksonFeatureSet<StreamReadCapability> getReadCapabilities() {
        return JSON_READ_CAPABILITIES;
    }
    protected final boolean _loadMore() throws IOException {
        final byte[] bArr;
        final int length;
        final InputStream inputStream = _inputStream;
        if (null == inputStream || 0 == (length = (bArr = this._inputBuffer).length)) {
            return false;
        }
        final int i2 = inputStream.read(bArr, 0, length);
        if (0 < i2) {
            final int i3 = _inputEnd;
            _currInputProcessed += i3;
            _currInputRowStart -= i3;
            _nameStartOffset -= i3;
            _inputPtr = 0;
            _inputEnd = i2;
            return true;
        }
        this._closeInput();
        if (0 == i2) {
            throw new IOException("InputStream.read() returned 0 characters when trying to read " + _inputBuffer.length + " bytes");
        }
        return false;
    }
    protected void _closeInput() throws IOException {
        if (null != this._inputStream) {
            if (_ioContext.isResourceManaged() || this.isEnabled(Feature.AUTO_CLOSE_SOURCE)) {
                _inputStream.close();
            }
            _inputStream = null;
        }
    }
    protected void _releaseBuffers() throws IOException {
        final byte[] bArr;
        final byte[] bArr2;
        super._releaseBuffers();
        _symbols.release();
        if (!_bufferRecyclable || null == (bArr = this._inputBuffer) || bArr == (bArr2 = NO_BYTES)) {
            return;
        }
        _inputBuffer = bArr2;
        _ioContext.releaseReadIOBuffer(bArr);
    }
    public String getText() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_STRING == jsonToken) {
            if (_tokenIncomplete) {
                _tokenIncomplete = false;
                return this._finishAndReturnString();
            }
            return _textBuffer.contentsAsString();
        }
        return this._getText2(jsonToken);
    }
    public String getValueAsString() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_STRING == jsonToken) {
            if (_tokenIncomplete) {
                _tokenIncomplete = false;
                return this._finishAndReturnString();
            }
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
            if (_tokenIncomplete) {
                _tokenIncomplete = false;
                return this._finishAndReturnString();
            }
            return _textBuffer.contentsAsString();
        }
        if (JsonToken.FIELD_NAME == jsonToken) {
            return this.getCurrentName();
        }
        return super.getValueAsString(str);
    }
    public int getValueAsInt() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_NUMBER_INT == jsonToken || JsonToken.VALUE_NUMBER_FLOAT == jsonToken) {
            final int i2 = _numTypesValid;
            if (0 == (i2 & 1)) {
                if (0 == i2) {
                    return this._parseIntValue();
                }
                if (0 == (i2 & 1)) {
                    this.convertNumberToInt();
                }
            }
            return _numberInt;
        }
        return super.getValueAsInt(0);
    }
    public int getValueAsInt(final int i2) throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_NUMBER_INT == jsonToken || JsonToken.VALUE_NUMBER_FLOAT == jsonToken) {
            final int i3 = _numTypesValid;
            if (0 == (i3 & 1)) {
                if (0 == i3) {
                    return this._parseIntValue();
                }
                if (0 == (i3 & 1)) {
                    this.convertNumberToInt();
                }
            }
            return _numberInt;
        }
        return super.getValueAsInt(i2);
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

    public char[] getTextCharacters() throws IOException {
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

    public int getTextLength() throws IOException {
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

    public int getTextOffset() throws IOException {
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
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_STRING != jsonToken && (JsonToken.VALUE_EMBEDDED_OBJECT != jsonToken || null == this._binaryValue)) {
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
            final byte[] bArr2 = _inputBuffer;
            final int i6 = _inputPtr;
            _inputPtr = i6 + 1;
            final int i7 = bArr2[i6] & 255;
            if (32 < i7) {
                int iDecodeBase64Char4 = base64Variant.decodeBase64Char(i7);
                if (0 > iDecodeBase64Char4) {
                    if (34 == i7) {
                        break;
                    }
                    iDecodeBase64Char4 = this._decodeBase64Escape(base64Variant, i7, 0);
                    if (0 > iDecodeBase64Char4) {
                    }
                    if (i4 > length) {
                    }
                    if (_inputPtr >= _inputEnd) {
                    }
                    final byte[] bArr3 = _inputBuffer;
                    final int i8 = _inputPtr;
                    _inputPtr = i8 + 1;
                    final int i9 = bArr3[i8] & 255;
                    iDecodeBase64Char = base64Variant.decodeBase64Char(i9);
                    if (0 > iDecodeBase64Char) {
                    }
                    final int i10 = (iDecodeBase64Char4 << 6) | iDecodeBase64Char;
                    if (_inputPtr >= _inputEnd) {
                    }
                    final byte[] bArr4 = _inputBuffer;
                    final int i11 = _inputPtr;
                    _inputPtr = i11 + 1;
                    final int i12 = bArr4[i11] & 255;
                    iDecodeBase64Char2 = base64Variant.decodeBase64Char(i12);
                    if (0 > iDecodeBase64Char2) {
                    }
                    final int i13 = (i10 << 6) | iDecodeBase64Char2;
                    if (_inputPtr >= _inputEnd) {
                    }
                    final byte[] bArr5 = _inputBuffer;
                    final int i14 = _inputPtr;
                    _inputPtr = i14 + 1;
                    final int i15 = bArr5[i14] & 255;
                    iDecodeBase64Char3 = base64Variant.decodeBase64Char(i15);
                    if (0 <= iDecodeBase64Char3) {
                    }
                    final int i16 = (i13 << 6) | iDecodeBase64Char3;
                    bArr[i4] = (byte) (i16 >> 16);
                    final int i17 = i4 + 2;
                    bArr[i4 + 1] = (byte) (i16 >> 8);
                    i4 += 3;
                    bArr[i17] = (byte) i16;
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
                    final byte[] bArr32 = _inputBuffer;
                    final int i82 = _inputPtr;
                    _inputPtr = i82 + 1;
                    final int i92 = bArr32[i82] & 255;
                    iDecodeBase64Char = base64Variant.decodeBase64Char(i92);
                    if (0 > iDecodeBase64Char) {
                        iDecodeBase64Char = this._decodeBase64Escape(base64Variant, i92, 1);
                    }
                    final int i102 = (iDecodeBase64Char4 << 6) | iDecodeBase64Char;
                    if (_inputPtr >= _inputEnd) {
                        this._loadMoreGuaranteed();
                    }
                    final byte[] bArr42 = _inputBuffer;
                    final int i112 = _inputPtr;
                    _inputPtr = i112 + 1;
                    final int i122 = bArr42[i112] & 255;
                    iDecodeBase64Char2 = base64Variant.decodeBase64Char(i122);
                    if (0 > iDecodeBase64Char2) {
                        if (-2 != iDecodeBase64Char2) {
                            if (34 == i122) {
                                final int i18 = i4 + 1;
                                bArr[i4] = (byte) (i102 >> 4);
                                if (base64Variant.usesPadding()) {
                                    _inputPtr--;
                                    this._handleBase64MissingPadding(base64Variant);
                                }
                                i4 = i18;
                            } else {
                                iDecodeBase64Char2 = this._decodeBase64Escape(base64Variant, i122, 2);
                            }
                        }
                        if (-2 == iDecodeBase64Char2) {
                            if (_inputPtr >= _inputEnd) {
                                this._loadMoreGuaranteed();
                            }
                            final byte[] bArr6 = _inputBuffer;
                            final int i19 = _inputPtr;
                            _inputPtr = i19 + 1;
                            final int i20 = bArr6[i19] & 255;
                            if (!base64Variant.usesPaddingChar(i20) && -2 != _decodeBase64Escape(base64Variant, i20, i3)) {
                                throw this.reportInvalidBase64Char(base64Variant, i20, i3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                            }
                            bArr[i4] = (byte) (i102 >> 4);
                            i4++;
                        }
                    }
                    final int i132 = (i102 << 6) | iDecodeBase64Char2;
                    if (_inputPtr >= _inputEnd) {
                        this._loadMoreGuaranteed();
                    }
                    final byte[] bArr52 = _inputBuffer;
                    final int i142 = _inputPtr;
                    _inputPtr = i142 + 1;
                    final int i152 = bArr52[i142] & 255;
                    iDecodeBase64Char3 = base64Variant.decodeBase64Char(i152);
                    if (0 <= iDecodeBase64Char3) {
                        if (-2 == iDecodeBase64Char3) {
                            i2 = 3;
                        } else if (34 == i152) {
                            final int i21 = i4 + 1;
                            bArr[i4] = (byte) (i132 >> 10);
                            i4 += 2;
                            bArr[i21] = (byte) (i132 >> 2);
                            if (base64Variant.usesPadding()) {
                                _inputPtr--;
                                this._handleBase64MissingPadding(base64Variant);
                            }
                        } else {
                            i2 = 3;
                            iDecodeBase64Char3 = this._decodeBase64Escape(base64Variant, i152, 3);
                        }
                        if (-2 == iDecodeBase64Char3) {
                            final int i22 = i4 + 1;
                            bArr[i4] = (byte) (i132 >> 10);
                            i4 += 2;
                            bArr[i22] = (byte) (i132 >> 2);
                        }
                        i3 = i2;
                    } else {
                        i2 = 3;
                    }
                    final int i162 = (i132 << 6) | iDecodeBase64Char3;
                    bArr[i4] = (byte) (i162 >> 16);
                    final int i172 = i4 + 2;
                    bArr[i4 + 1] = (byte) (i162 >> 8);
                    i4 += 3;
                    bArr[i172] = (byte) i162;
                    i3 = i2;
                }
            }
            i2 = i3;
            i3 = i2;
        }
        _tokenIncomplete = false;
        if (0 >= i4) {
            return i5;
        }
        final int i23 = i5 + i4;
        outputStream.write(bArr, 0, i4);
        return i23;
    }

    public JsonToken nextToken() throws IOException {
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
        if (93 == i_skipWSOrEnd) {
            this._closeArrayScope();
            final JsonToken jsonToken3 = JsonToken.END_ARRAY;
            _currToken = jsonToken3;
            return jsonToken3;
        }
        if (125 == i_skipWSOrEnd) {
            this._closeObjectScope();
            final JsonToken jsonToken4 = JsonToken.END_OBJECT;
            _currToken = jsonToken4;
            return jsonToken4;
        }
        if (_parsingContext.expectComma()) {
            if (44 != i_skipWSOrEnd) {
                this._reportUnexpectedChar(i_skipWSOrEnd, "was expecting comma to separate " + _parsingContext.typeDesc() + " entries");
            }
            i_skipWSOrEnd = this._skipWS();
            if (0 != (this._features & FEAT_MASK_TRAILING_COMMA) && (93 == i_skipWSOrEnd || 125 == i_skipWSOrEnd)) {
                return this._closeScope(i_skipWSOrEnd);
            }
        }
        if (!_parsingContext.inObject()) {
            this._updateLocation();
            return this._nextTokenNotInObject(i_skipWSOrEnd);
        }
        this._updateNameLocation();
        _parsingContext.setCurrentName(this._parseName(i_skipWSOrEnd));
        _currToken = jsonToken2;
        final int i_skipColon = this._skipColon();
        this._updateLocation();
        if (34 == i_skipColon) {
            _tokenIncomplete = true;
            _nextToken = JsonToken.VALUE_STRING;
            return _currToken;
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
                    jsonToken_parseNegNumber = this._handleUnexpectedValue(i_skipColon);
                    break;
            }
        } else {
            jsonToken_parseNegNumber = JsonToken.START_OBJECT;
        }
        _nextToken = jsonToken_parseNegNumber;
        return _currToken;
    }

    private JsonToken _nextTokenNotInObject(final int i2) throws IOException {
        if (34 == i2) {
            _tokenIncomplete = true;
            final JsonToken jsonToken = JsonToken.VALUE_STRING;
            _currToken = jsonToken;
            return jsonToken;
        }
        if (45 == i2) {
            final JsonToken jsonToken_parseNegNumber = this._parseNegNumber();
            _currToken = jsonToken_parseNegNumber;
            return jsonToken_parseNegNumber;
        }
        if (46 == i2) {
            final JsonToken jsonToken_parseFloatThatStartsWithPeriod = this._parseFloatThatStartsWithPeriod();
            _currToken = jsonToken_parseFloatThatStartsWithPeriod;
            return jsonToken_parseFloatThatStartsWithPeriod;
        }
        if (91 == i2) {
            _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
            final JsonToken jsonToken2 = JsonToken.START_ARRAY;
            _currToken = jsonToken2;
            return jsonToken2;
        }
        if (102 == i2) {
            this._matchFalse();
            final JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
            _currToken = jsonToken3;
            return jsonToken3;
        }
        if (110 == i2) {
            this._matchNull();
            final JsonToken jsonToken4 = JsonToken.VALUE_NULL;
            _currToken = jsonToken4;
            return jsonToken4;
        }
        if (116 == i2) {
            this._matchTrue();
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
            default:
                final JsonToken jsonToken_handleUnexpectedValue = this._handleUnexpectedValue(i2);
                _currToken = jsonToken_handleUnexpectedValue;
                return jsonToken_handleUnexpectedValue;
        }
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
        if (93 == i_skipWSOrEnd) {
            this._closeArrayScope();
            _currToken = JsonToken.END_ARRAY;
            return null;
        }
        if (125 == i_skipWSOrEnd) {
            this._closeObjectScope();
            _currToken = JsonToken.END_OBJECT;
            return null;
        }
        if (_parsingContext.expectComma()) {
            if (44 != i_skipWSOrEnd) {
                this._reportUnexpectedChar(i_skipWSOrEnd, "was expecting comma to separate " + _parsingContext.typeDesc() + " entries");
            }
            i_skipWSOrEnd = this._skipWS();
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
        final String str_parseName = this._parseName(i_skipWSOrEnd);
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
                    jsonToken_parseNegNumber = this._handleUnexpectedValue(i_skipColon);
                    break;
            }
        } else {
            jsonToken_parseNegNumber = JsonToken.START_OBJECT;
        }
        _nextToken = jsonToken_parseNegNumber;
        return str_parseName;
    }

    public String nextTextValue() throws IOException {
        if (JsonToken.FIELD_NAME == this._currToken) {
            _nameCopied = false;
            final JsonToken jsonToken = _nextToken;
            _nextToken = null;
            _currToken = jsonToken;
            if (JsonToken.VALUE_STRING == jsonToken) {
                if (_tokenIncomplete) {
                    _tokenIncomplete = false;
                    return this._finishAndReturnString();
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
            return this._handleUnexpectedValue(46);
        }
        return this._parseFloat(_textBuffer.emptyAndGetCurrentSegment(), 0, 46, false, 0);
    }

    protected JsonToken _parsePosNumber(int i2) throws IOException {
        int i3;
        int i4;
        final char[] cArrEmptyAndGetCurrentSegment = _textBuffer.emptyAndGetCurrentSegment();
        if (48 == i2) {
            i2 = this._verifyNoLeadingZeroes();
        }
        cArrEmptyAndGetCurrentSegment[0] = (char) i2;
        final int iMin = Math.min(_inputEnd, (_inputPtr + cArrEmptyAndGetCurrentSegment.length) - 1);
        int i5 = 1;
        int i6 = 1;
        while (true) {
            i3 = _inputPtr;
            if (i3 >= iMin) {
                return this._parseNumber2(cArrEmptyAndGetCurrentSegment, i5, false, i6);
            }
            final byte[] bArr = _inputBuffer;
            _inputPtr = i3 + 1;
            i4 = bArr[i3] & 255;
            if (48 > i4 || 57 < i4) {
                break;
            }
            i6++;
            cArrEmptyAndGetCurrentSegment[i5] = (char) i4;
            i5++;
        }
        if (46 == i4 || 101 == i4 || 69 == i4) {
            return this._parseFloat(cArrEmptyAndGetCurrentSegment, i5, i4, false, i6);
        }
        _inputPtr = i3;
        _textBuffer.setCurrentLength(i5);
        if (_parsingContext.inRoot()) {
            this._verifyRootSpace(i4);
        }
        return this.resetInt(false, i6);
    }

    protected JsonToken _parseNegNumber() throws IOException {
        int i2;
        int i3;
        final char[] cArrEmptyAndGetCurrentSegment = _textBuffer.emptyAndGetCurrentSegment();
        cArrEmptyAndGetCurrentSegment[0] = '-';
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final byte[] bArr = _inputBuffer;
        final int i4 = _inputPtr;
        _inputPtr = i4 + 1;
        int i_verifyNoLeadingZeroes = bArr[i4] & 255;
        if (48 >= i_verifyNoLeadingZeroes) {
            if (48 != i_verifyNoLeadingZeroes) {
                return this._handleInvalidNumberStart(i_verifyNoLeadingZeroes, true);
            }
            i_verifyNoLeadingZeroes = this._verifyNoLeadingZeroes();
        } else if (57 < i_verifyNoLeadingZeroes) {
            return this._handleInvalidNumberStart(i_verifyNoLeadingZeroes, true);
        }
        cArrEmptyAndGetCurrentSegment[1] = (char) i_verifyNoLeadingZeroes;
        int i5 = 2;
        final int iMin = Math.min(_inputEnd, (_inputPtr + cArrEmptyAndGetCurrentSegment.length) - 2);
        int i6 = 1;
        while (true) {
            i2 = _inputPtr;
            if (i2 >= iMin) {
                return this._parseNumber2(cArrEmptyAndGetCurrentSegment, i5, true, i6);
            }
            final byte[] bArr2 = _inputBuffer;
            _inputPtr = i2 + 1;
            i3 = bArr2[i2] & 255;
            if (48 > i3 || 57 < i3) {
                break;
            }
            i6++;
            cArrEmptyAndGetCurrentSegment[i5] = (char) i3;
            i5++;
        }
        if (46 == i3 || 101 == i3 || 69 == i3) {
            return this._parseFloat(cArrEmptyAndGetCurrentSegment, i5, i3, true, i6);
        }
        _inputPtr = i2;
        _textBuffer.setCurrentLength(i5);
        if (_parsingContext.inRoot()) {
            this._verifyRootSpace(i3);
        }
        return this.resetInt(true, i6);
    }

    private JsonToken _parseNumber2(final char[] cArr, final int i2, final boolean z, final int i3) throws IOException {
        int i4;
        int i5;
        char[] cArrFinishCurrentSegment = cArr;
        int i6 = i2;
        int i7 = i3;
        while (true) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                _textBuffer.setCurrentLength(i6);
                return this.resetInt(z, i7);
            }
            final byte[] bArr = _inputBuffer;
            i4 = _inputPtr;
            _inputPtr = i4 + 1;
            i5 = bArr[i4] & 255;
            if (57 < i5 || 48 > i5) {
                break;
            }
            if (i6 >= cArrFinishCurrentSegment.length) {
                i6 = 0;
                cArrFinishCurrentSegment = _textBuffer.finishCurrentSegment();
            }
            cArrFinishCurrentSegment[i6] = (char) i5;
            i7++;
            i6++;
        }
        if (46 == i5 || 101 == i5 || 69 == i5) {
            return this._parseFloat(cArrFinishCurrentSegment, i6, i5, z, i7);
        }
        _inputPtr = i4;
        _textBuffer.setCurrentLength(i6);
        if (_parsingContext.inRoot()) {
            this._verifyRootSpace(_inputBuffer[_inputPtr] & 255);
        }
        return this.resetInt(z, i7);
    }

    private int _verifyNoLeadingZeroes() throws IOException {
        int i2;
        if ((_inputPtr >= _inputEnd && !this._loadMore()) || 48 > (i2 = this._inputBuffer[this._inputPtr] & 255) || 57 < i2) {
            return 48;
        }
        if (0 == (this._features & FEAT_MASK_LEADING_ZEROS)) {
            this.reportInvalidNumber("Leading zeroes not allowed");
        }
        _inputPtr++;
        if (48 == i2) {
            do {
                if (_inputPtr >= _inputEnd && !this._loadMore()) {
                    break;
                }
                final byte[] bArr = _inputBuffer;
                final int i3 = _inputPtr;
                i2 = bArr[i3] & 255;
                if (48 > i2 || 57 < i2) {
                    return 48;
                }
                _inputPtr = i3 + 1;
            } while (48 == i2);
        }
        return i2;
    }

    private JsonToken _parseFloat(char[] cArr, int i2, int i3, final boolean z, final int i4) throws IOException {
        int i5;
        boolean z2;
        int i6 = 0;
        if (46 == i3) {
            if (i2 >= cArr.length) {
                cArr = _textBuffer.finishCurrentSegment();
                i2 = 0;
            }
            cArr[i2] = (char) 46;
            i2++;
            i5 = 0;
            while (true) {
                if (_inputPtr >= _inputEnd && !this._loadMore()) {
                    z2 = true;
                    break;
                }
                final byte[] bArr = _inputBuffer;
                final int i7 = _inputPtr;
                _inputPtr = i7 + 1;
                i3 = bArr[i7] & 255;
                if (48 > i3 || 57 < i3) {
                    break;
                }
                i5++;
                if (i2 >= cArr.length) {
                    cArr = _textBuffer.finishCurrentSegment();
                    i2 = 0;
                }
                cArr[i2] = (char) i3;
                i2++;
            }
            z2 = false;
            if (0 == i5) {
                this.reportUnexpectedNumberChar(i3, "Decimal point not followed by a digit");
            }
        } else {
            i5 = 0;
            z2 = false;
        }
        if (101 == i3 || 69 == i3) {
            if (i2 >= cArr.length) {
                cArr = _textBuffer.finishCurrentSegment();
                i2 = 0;
            }
            int i8 = i2 + 1;
            cArr[i2] = (char) i3;
            if (_inputPtr >= _inputEnd) {
                this._loadMoreGuaranteed();
            }
            final byte[] bArr2 = _inputBuffer;
            final int i9 = _inputPtr;
            _inputPtr = i9 + 1;
            int i10 = bArr2[i9] & 255;
            if (45 == i10 || 43 == i10) {
                if (i8 >= cArr.length) {
                    cArr = _textBuffer.finishCurrentSegment();
                    i8 = 0;
                }
                final int i11 = i8 + 1;
                cArr[i8] = (char) i10;
                if (_inputPtr >= _inputEnd) {
                    this._loadMoreGuaranteed();
                }
                final byte[] bArr3 = _inputBuffer;
                final int i12 = _inputPtr;
                _inputPtr = i12 + 1;
                i10 = bArr3[i12] & 255;
                i8 = i11;
            }
            i3 = i10;
            int i13 = 0;
            while (48 <= i3 && 57 >= i3) {
                i13++;
                if (i8 >= cArr.length) {
                    cArr = _textBuffer.finishCurrentSegment();
                    i8 = 0;
                }
                final int i14 = i8 + 1;
                cArr[i8] = (char) i3;
                if (_inputPtr >= _inputEnd && !this._loadMore()) {
                    i6 = i13;
                    z2 = true;
                    i2 = i14;
                    break;
                }
                final byte[] bArr4 = _inputBuffer;
                final int i15 = _inputPtr;
                _inputPtr = i15 + 1;
                i3 = bArr4[i15] & 255;
                i8 = i14;
            }
            i6 = i13;
            i2 = i8;
            if (0 == i6) {
                this.reportUnexpectedNumberChar(i3, "Exponent indicator not followed by a digit");
            }
        }
        if (!z2) {
            _inputPtr--;
            if (_parsingContext.inRoot()) {
                this._verifyRootSpace(i3);
            }
        }
        _textBuffer.setCurrentLength(i2);
        return this.resetFloat(z, i4, i5, i6);
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

    protected final String _parseName(final int i2) throws IOException {
        if (34 != i2) {
            return this._handleOddName(i2);
        }
        final int i3 = _inputPtr;
        if (i3 + 13 > _inputEnd) {
            return this.slowParseName();
        }
        final byte[] bArr = _inputBuffer;
        final int[] iArr = UTF8StreamJsonParser._icLatin1;
        final int i4 = i3 + 1;
        _inputPtr = i4;
        final int i5 = bArr[i3] & 255;
        if (0 != iArr[i5]) {
            if (34 == i5) {
                return "";
            }
            return this.parseName(0, i5, 0);
        }
        final int i6 = i3 + 2;
        _inputPtr = i6;
        final int i7 = bArr[i4] & 255;
        if (0 != iArr[i7]) {
            if (34 == i7) {
                return this.findName(i5, 1);
            }
            return this.parseName(i5, i7, 1);
        }
        final int i8 = i7 | (i5 << 8);
        final int i9 = i3 + 3;
        _inputPtr = i9;
        final int i10 = bArr[i6] & 255;
        if (0 != iArr[i10]) {
            if (34 == i10) {
                return this.findName(i8, 2);
            }
            return this.parseName(i8, i10, 2);
        }
        final int i11 = (i8 << 8) | i10;
        final int i12 = i3 + 4;
        _inputPtr = i12;
        final int i13 = bArr[i9] & 255;
        if (0 != iArr[i13]) {
            if (34 == i13) {
                return this.findName(i11, 3);
            }
            return this.parseName(i11, i13, 3);
        }
        final int i14 = (i11 << 8) | i13;
        _inputPtr = i3 + 5;
        final int i15 = bArr[i12] & 255;
        if (0 == iArr[i15]) {
            _quad1 = i14;
            return this.parseMediumName(i15);
        }
        if (34 == i15) {
            return this.findName(i14, 4);
        }
        return this.parseName(i14, i15, 4);
    }

    protected final String parseMediumName(final int i2) throws IOException {
        final byte[] bArr = _inputBuffer;
        final int[] iArr = UTF8StreamJsonParser._icLatin1;
        final int i3 = _inputPtr;
        final int i4 = i3 + 1;
        _inputPtr = i4;
        final int i5 = bArr[i3] & 255;
        if (0 != iArr[i5]) {
            if (34 == i5) {
                return this.findName(_quad1, i2, 1);
            }
            return this.parseName(_quad1, i2, i5, 1);
        }
        final int i6 = (i2 << 8) | i5;
        final int i7 = i3 + 2;
        _inputPtr = i7;
        final int i8 = bArr[i4] & 255;
        if (0 != iArr[i8]) {
            if (34 == i8) {
                return this.findName(_quad1, i6, 2);
            }
            return this.parseName(_quad1, i6, i8, 2);
        }
        final int i9 = (i6 << 8) | i8;
        final int i10 = i3 + 3;
        _inputPtr = i10;
        final int i11 = bArr[i7] & 255;
        if (0 != iArr[i11]) {
            if (34 == i11) {
                return this.findName(_quad1, i9, 3);
            }
            return this.parseName(_quad1, i9, i11, 3);
        }
        final int i12 = (i9 << 8) | i11;
        _inputPtr = i3 + 4;
        final int i13 = bArr[i10] & 255;
        if (0 == iArr[i13]) {
            return this.parseMediumName2(i13, i12);
        }
        if (34 == i13) {
            return this.findName(_quad1, i12, 4);
        }
        return this.parseName(_quad1, i12, i13, 4);
    }

    protected final String parseMediumName2(final int i2, final int i3) throws IOException {
        final byte[] bArr = _inputBuffer;
        final int[] iArr = UTF8StreamJsonParser._icLatin1;
        final int i4 = _inputPtr;
        final int i5 = i4 + 1;
        _inputPtr = i5;
        final int i6 = bArr[i4] & 255;
        if (0 != iArr[i6]) {
            if (34 == i6) {
                return this.findName(_quad1, i3, i2, 1);
            }
            return this.parseName(_quad1, i3, i2, i6, 1);
        }
        final int i7 = (i2 << 8) | i6;
        final int i8 = i4 + 2;
        _inputPtr = i8;
        final int i9 = bArr[i5] & 255;
        if (0 != iArr[i9]) {
            if (34 == i9) {
                return this.findName(_quad1, i3, i7, 2);
            }
            return this.parseName(_quad1, i3, i7, i9, 2);
        }
        final int i10 = (i7 << 8) | i9;
        final int i11 = i4 + 3;
        _inputPtr = i11;
        final int i12 = bArr[i8] & 255;
        if (0 != iArr[i12]) {
            if (34 == i12) {
                return this.findName(_quad1, i3, i10, 3);
            }
            return this.parseName(_quad1, i3, i10, i12, 3);
        }
        final int i13 = (i10 << 8) | i12;
        _inputPtr = i4 + 4;
        final int i14 = bArr[i11] & 255;
        if (0 == iArr[i14]) {
            return this.parseLongName(i14, i3, i13);
        }
        if (34 == i14) {
            return this.findName(_quad1, i3, i13, 4);
        }
        return this.parseName(_quad1, i3, i13, i14, 4);
    }

    protected final String parseLongName(final int i2, final int i3, final int i4) throws IOException {
        final int[] iArr = _quadBuffer;
        iArr[0] = _quad1;
        iArr[1] = i3;
        iArr[2] = i4;
        final byte[] bArr = _inputBuffer;
        final int[] iArr2 = UTF8StreamJsonParser._icLatin1;
        int i5 = i2;
        int i6 = 3;
        while (true) {
            final int i7 = _inputPtr;
            if (i7 + 4 <= _inputEnd) {
                final int i8 = i7 + 1;
                _inputPtr = i8;
                final int i9 = bArr[i7] & 255;
                if (0 != iArr2[i9]) {
                    if (34 == i9) {
                        return this.findName(_quadBuffer, i6, i5, 1);
                    }
                    return this.parseEscapedName(_quadBuffer, i6, i5, i9, 1);
                }
                final int i10 = (i5 << 8) | i9;
                final int i11 = i7 + 2;
                _inputPtr = i11;
                final int i12 = bArr[i8] & 255;
                if (0 != iArr2[i12]) {
                    if (34 == i12) {
                        return this.findName(_quadBuffer, i6, i10, 2);
                    }
                    return this.parseEscapedName(_quadBuffer, i6, i10, i12, 2);
                }
                final int i13 = (i10 << 8) | i12;
                final int i14 = i7 + 3;
                _inputPtr = i14;
                final int i15 = bArr[i11] & 255;
                if (0 != iArr2[i15]) {
                    if (34 == i15) {
                        return this.findName(_quadBuffer, i6, i13, 3);
                    }
                    return this.parseEscapedName(_quadBuffer, i6, i13, i15, 3);
                }
                final int i16 = (i13 << 8) | i15;
                _inputPtr = i7 + 4;
                final int i17 = bArr[i14] & 255;
                if (0 != iArr2[i17]) {
                    if (34 == i17) {
                        return this.findName(_quadBuffer, i6, i16, 4);
                    }
                    return this.parseEscapedName(_quadBuffer, i6, i16, i17, 4);
                }
                final int[] iArr3 = _quadBuffer;
                if (i6 >= iArr3.length) {
                    _quadBuffer = growArrayBy(iArr3, i6);
                }
                _quadBuffer[i6] = i16;
                i5 = i17;
                i6++;
            } else {
                return this.parseEscapedName(_quadBuffer, i6, 0, i5, 0);
            }
        }
    }

    protected String slowParseName() throws IOException {
        if (_inputPtr >= _inputEnd && !this._loadMore()) {
            this._reportInvalidEOF(": was expecting closing '\"' for name", JsonToken.FIELD_NAME);
        }
        final byte[] bArr = _inputBuffer;
        final int i2 = _inputPtr;
        _inputPtr = i2 + 1;
        final int i3 = bArr[i2] & 255;
        if (34 == i3) {
            return "";
        }
        return this.parseEscapedName(_quadBuffer, 0, 0, i3, 0);
    }

    private String parseName(final int i2, final int i3, final int i4) throws IOException {
        return this.parseEscapedName(_quadBuffer, 0, i2, i3, i4);
    }

    private String parseName(final int i2, final int i3, final int i4, final int i5) throws IOException {
        final int[] iArr = _quadBuffer;
        iArr[0] = i2;
        return this.parseEscapedName(iArr, 1, i3, i4, i5);
    }

    private String parseName(final int i2, final int i3, final int i4, final int i5, final int i6) throws IOException {
        final int[] iArr = _quadBuffer;
        iArr[0] = i2;
        iArr[1] = i3;
        return this.parseEscapedName(iArr, 2, i4, i5, i6);
    }

    protected final String parseEscapedName(int[] iArr, int i2, int i3, int i4, int i5) throws IOException {
        final int[] iArr2 = UTF8StreamJsonParser._icLatin1;
        while (true) {
            if (0 != iArr2[i4]) {
                if (34 == i4) {
                    break;
                }
                if (92 != i4) {
                    this._throwUnquotedSpace(i4, "name");
                } else {
                    i4 = this._decodeEscaped();
                }
                if (127 < i4) {
                    int i6 = 0;
                    if (4 <= i5) {
                        if (i2 >= iArr.length) {
                            iArr = growArrayBy(iArr, iArr.length);
                            _quadBuffer = iArr;
                        }
                        iArr[i2] = i3;
                        i2++;
                        i3 = 0;
                        i5 = 0;
                    }
                    if (2048 > i4) {
                        i3 = (i3 << 8) | (i4 >> 6) | Wbxml.EXT_0;
                        i5++;
                    } else {
                        final int i7 = (i3 << 8) | (i4 >> 12) | 224;
                        int i8 = i5 + 1;
                        if (4 <= i8) {
                            if (i2 >= iArr.length) {
                                iArr = growArrayBy(iArr, iArr.length);
                                _quadBuffer = iArr;
                            }
                            iArr[i2] = i7;
                            i2++;
                            i8 = 0;
                        } else {
                            i6 = i7;
                        }
                        i3 = (i6 << 8) | ((i4 >> 6) & 63) | 128;
                        i5 = i8 + 1;
                    }
                    i4 = (i4 & 63) | 128;
                }
            }
            if (4 > i5) {
                i5++;
                i3 = (i3 << 8) | i4;
            } else {
                if (i2 >= iArr.length) {
                    iArr = growArrayBy(iArr, iArr.length);
                    _quadBuffer = iArr;
                }
                iArr[i2] = i3;
                i3 = i4;
                i2++;
                i5 = 1;
            }
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                this._reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
            }
            final byte[] bArr = _inputBuffer;
            final int i9 = _inputPtr;
            _inputPtr = i9 + 1;
            i4 = bArr[i9] & 255;
        }
        if (0 < i5) {
            if (i2 >= iArr.length) {
                iArr = growArrayBy(iArr, iArr.length);
                _quadBuffer = iArr;
            }
            iArr[i2] = UTF8StreamJsonParser._padLastQuad(i3, i5);
            i2++;
        }
        final String strFindName = _symbols.findName(iArr, i2);
        return null == strFindName ? this.addName(iArr, i2, i5) : strFindName;
    }

    protected String _handleOddName(int i2) throws IOException {
        if (39 == i2 && 0 != (this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES)) {
            return this._parseAposName();
        }
        if (0 == (this._features & FEAT_MASK_ALLOW_UNQUOTED_NAMES)) {
            this._reportUnexpectedChar((char) this._decodeCharForError(i2), "was expecting double-quote to start field name");
        }
        final int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (0 != inputCodeUtf8JsNames[i2]) {
            this._reportUnexpectedChar(i2, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int[] iArrGrowArrayBy = _quadBuffer;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (4 > i3) {
                i3++;
                i5 = i2 | (i5 << 8);
            } else {
                if (i4 >= iArrGrowArrayBy.length) {
                    iArrGrowArrayBy = growArrayBy(iArrGrowArrayBy, iArrGrowArrayBy.length);
                    _quadBuffer = iArrGrowArrayBy;
                }
                iArrGrowArrayBy[i4] = i5;
                i5 = i2;
                i4++;
                i3 = 1;
            }
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                this._reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
            }
            final byte[] bArr = _inputBuffer;
            final int i6 = _inputPtr;
            i2 = bArr[i6] & 255;
            if (0 != inputCodeUtf8JsNames[i2]) {
                break;
            }
            _inputPtr = i6 + 1;
        }
        if (0 < i3) {
            if (i4 >= iArrGrowArrayBy.length) {
                iArrGrowArrayBy = growArrayBy(iArrGrowArrayBy, iArrGrowArrayBy.length);
                _quadBuffer = iArrGrowArrayBy;
            }
            iArrGrowArrayBy[i4] = i5;
            i4++;
        }
        final String strFindName = _symbols.findName(iArrGrowArrayBy, i4);
        return null == strFindName ? this.addName(iArrGrowArrayBy, i4, i3) : strFindName;
    }

    protected String _parseAposName() throws IOException {
        if (_inputPtr >= _inputEnd && !this._loadMore()) {
            this._reportInvalidEOF(": was expecting closing ''' for field name", JsonToken.FIELD_NAME);
        }
        final byte[] bArr = _inputBuffer;
        final int i2 = _inputPtr;
        _inputPtr = i2 + 1;
        int i_decodeEscaped = bArr[i2] & 255;
        if (39 == i_decodeEscaped) {
            return "";
        }
        int[] iArrGrowArrayBy = _quadBuffer;
        final int[] iArr = UTF8StreamJsonParser._icLatin1;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (39 != i_decodeEscaped) {
            if (0 != iArr[i_decodeEscaped] && 34 != i_decodeEscaped) {
                if (92 != i_decodeEscaped) {
                    this._throwUnquotedSpace(i_decodeEscaped, "name");
                } else {
                    i_decodeEscaped = this._decodeEscaped();
                }
                if (127 < i_decodeEscaped) {
                    if (4 <= i3) {
                        if (i4 >= iArrGrowArrayBy.length) {
                            iArrGrowArrayBy = growArrayBy(iArrGrowArrayBy, iArrGrowArrayBy.length);
                            _quadBuffer = iArrGrowArrayBy;
                        }
                        iArrGrowArrayBy[i4] = i5;
                        i5 = 0;
                        i4++;
                        i3 = 0;
                    }
                    if (2048 > i_decodeEscaped) {
                        i5 = (i5 << 8) | (i_decodeEscaped >> 6) | Wbxml.EXT_0;
                        i3++;
                    } else {
                        int i6 = (i5 << 8) | (i_decodeEscaped >> 12) | 224;
                        int i7 = i3 + 1;
                        if (4 <= i7) {
                            if (i4 >= iArrGrowArrayBy.length) {
                                iArrGrowArrayBy = growArrayBy(iArrGrowArrayBy, iArrGrowArrayBy.length);
                                _quadBuffer = iArrGrowArrayBy;
                            }
                            iArrGrowArrayBy[i4] = i6;
                            i6 = 0;
                            i4++;
                            i7 = 0;
                        }
                        i5 = (i6 << 8) | ((i_decodeEscaped >> 6) & 63) | 128;
                        i3 = i7 + 1;
                    }
                    i_decodeEscaped = (i_decodeEscaped & 63) | 128;
                }
            }
            if (4 > i3) {
                i3++;
                i5 = i_decodeEscaped | (i5 << 8);
            } else {
                if (i4 >= iArrGrowArrayBy.length) {
                    iArrGrowArrayBy = growArrayBy(iArrGrowArrayBy, iArrGrowArrayBy.length);
                    _quadBuffer = iArrGrowArrayBy;
                }
                iArrGrowArrayBy[i4] = i5;
                i5 = i_decodeEscaped;
                i4++;
                i3 = 1;
            }
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                this._reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
            }
            final byte[] bArr2 = _inputBuffer;
            final int i8 = _inputPtr;
            _inputPtr = i8 + 1;
            i_decodeEscaped = bArr2[i8] & 255;
        }
        if (0 < i3) {
            if (i4 >= iArrGrowArrayBy.length) {
                iArrGrowArrayBy = growArrayBy(iArrGrowArrayBy, iArrGrowArrayBy.length);
                _quadBuffer = iArrGrowArrayBy;
            }
            iArrGrowArrayBy[i4] = UTF8StreamJsonParser._padLastQuad(i5, i3);
            i4++;
        }
        final String strFindName = _symbols.findName(iArrGrowArrayBy, i4);
        return null == strFindName ? this.addName(iArrGrowArrayBy, i4, i3) : strFindName;
    }

    private String findName(final int i2, final int i3) throws JsonParseException {
        final int i_padLastQuad = UTF8StreamJsonParser._padLastQuad(i2, i3);
        final String strFindName = _symbols.findName(i_padLastQuad);
        if (null != strFindName) {
            return strFindName;
        }
        final int[] iArr = _quadBuffer;
        iArr[0] = i_padLastQuad;
        return this.addName(iArr, 1, i3);
    }

    private String findName(final int i2, final int i3, final int i4) throws JsonParseException {
        final int i_padLastQuad = UTF8StreamJsonParser._padLastQuad(i3, i4);
        final String strFindName = _symbols.findName(i2, i_padLastQuad);
        if (null != strFindName) {
            return strFindName;
        }
        final int[] iArr = _quadBuffer;
        iArr[0] = i2;
        iArr[1] = i_padLastQuad;
        return this.addName(iArr, 2, i4);
    }

    private String findName(final int i2, final int i3, final int i4, final int i5) throws JsonParseException {
        final int i_padLastQuad = UTF8StreamJsonParser._padLastQuad(i4, i5);
        final String strFindName = _symbols.findName(i2, i3, i_padLastQuad);
        if (null != strFindName) {
            return strFindName;
        }
        final int[] iArr = _quadBuffer;
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = UTF8StreamJsonParser._padLastQuad(i_padLastQuad, i5);
        return this.addName(iArr, 3, i5);
    }

    private String findName(int[] iArr, final int i2, final int i3, final int i4) throws JsonParseException {
        if (i2 >= iArr.length) {
            iArr = growArrayBy(iArr, iArr.length);
            _quadBuffer = iArr;
        }
        final int i5 = i2 + 1;
        iArr[i2] = UTF8StreamJsonParser._padLastQuad(i3, i4);
        final String strFindName = _symbols.findName(iArr, i5);
        return null == strFindName ? this.addName(iArr, i5, i4) : strFindName;
    }
    private String addName(final int[] iArr, final int i2, final int i3) throws JsonParseException {
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

    protected void _loadMoreGuaranteed() throws IOException {
        if (this._loadMore()) {
            return;
        }
        this._reportInvalidEOF();
    }

    protected void _finishString() throws IOException {
        int i2 = _inputPtr;
        if (i2 >= _inputEnd) {
            this._loadMoreGuaranteed();
            i2 = _inputPtr;
        }
        final char[] cArrEmptyAndGetCurrentSegment = _textBuffer.emptyAndGetCurrentSegment();
        final int[] iArr = UTF8StreamJsonParser._icUTF8;
        final int iMin = Math.min(_inputEnd, cArrEmptyAndGetCurrentSegment.length + i2);
        final byte[] bArr = _inputBuffer;
        int i3 = 0;
        while (true) {
            if (i2 >= iMin) {
                break;
            }
            final int i4 = bArr[i2] & 255;
            if (0 == iArr[i4]) {
                i2++;
                cArrEmptyAndGetCurrentSegment[i3] = (char) i4;
                i3++;
            } else if (34 == i4) {
                _inputPtr = i2 + 1;
                _textBuffer.setCurrentLength(i3);
                return;
            }
        }
        _inputPtr = i2;
        this._finishString2(cArrEmptyAndGetCurrentSegment, i3);
    }

    protected String _finishAndReturnString() throws IOException {
        int i2 = _inputPtr;
        if (i2 >= _inputEnd) {
            this._loadMoreGuaranteed();
            i2 = _inputPtr;
        }
        final char[] cArrEmptyAndGetCurrentSegment = _textBuffer.emptyAndGetCurrentSegment();
        final int[] iArr = UTF8StreamJsonParser._icUTF8;
        final int iMin = Math.min(_inputEnd, cArrEmptyAndGetCurrentSegment.length + i2);
        final byte[] bArr = _inputBuffer;
        int i3 = 0;
        while (true) {
            if (i2 >= iMin) {
                break;
            }
            final int i4 = bArr[i2] & 255;
            if (0 == iArr[i4]) {
                i2++;
                cArrEmptyAndGetCurrentSegment[i3] = (char) i4;
                i3++;
            } else if (34 == i4) {
                _inputPtr = i2 + 1;
                return _textBuffer.setCurrentAndReturn(i3);
            }
        }
        _inputPtr = i2;
        this._finishString2(cArrEmptyAndGetCurrentSegment, i3);
        return _textBuffer.contentsAsString();
    }

    private void _finishString2(char[] cArr, int i2) throws IOException {
        final int[] iArr = UTF8StreamJsonParser._icUTF8;
        final byte[] bArr = _inputBuffer;
        while (true) {
            int i3 = _inputPtr;
            if (i3 >= _inputEnd) {
                this._loadMoreGuaranteed();
                i3 = _inputPtr;
            }
            int i4 = 0;
            if (i2 >= cArr.length) {
                cArr = _textBuffer.finishCurrentSegment();
                i2 = 0;
            }
            final int iMin = Math.min(_inputEnd, (cArr.length - i2) + i3);
            while (true) {
                if (i3 < iMin) {
                    final int i5 = i3 + 1;
                    int i_decodeEscaped = bArr[i3] & 255;
                    final int i6 = iArr[i_decodeEscaped];
                    if (0 != i6) {
                        _inputPtr = i5;
                        if (34 != i_decodeEscaped) {
                            if (1 == i6) {
                                i_decodeEscaped = this._decodeEscaped();
                            } else if (2 == i6) {
                                i_decodeEscaped = this._decodeUtf8_2(i_decodeEscaped);
                            } else if (3 == i6) {
                                i_decodeEscaped = 2 <= this._inputEnd - i5 ? this._decodeUtf8_3fast(i_decodeEscaped) : this._decodeUtf8_3(i_decodeEscaped);
                            } else if (4 == i6) {
                                final int i_decodeUtf8_4 = this._decodeUtf8_4(i_decodeEscaped);
                                final int i7 = i2 + 1;
                                cArr[i2] = (char) ((i_decodeUtf8_4 >> 10) | 55296);
                                if (i7 >= cArr.length) {
                                    cArr = _textBuffer.finishCurrentSegment();
                                    i2 = 0;
                                } else {
                                    i2 = i7;
                                }
                                i_decodeEscaped = (i_decodeUtf8_4 & 1023) | 56320;
                            } else if (32 > i_decodeEscaped) {
                                this._throwUnquotedSpace(i_decodeEscaped, "string value");
                            } else {
                                this._reportInvalidChar(i_decodeEscaped);
                            }
                            if (i2 >= cArr.length) {
                                cArr = _textBuffer.finishCurrentSegment();
                            } else {
                                i4 = i2;
                            }
                            i2 = i4 + 1;
                            cArr[i4] = (char) i_decodeEscaped;
                        } else {
                            _textBuffer.setCurrentLength(i2);
                            return;
                        }
                    } else {
                        cArr[i2] = (char) i_decodeEscaped;
                        i3 = i5;
                        i2++;
                    }
                } else {
                    _inputPtr = i3;
                    break;
                }
            }
        }
    }

    protected void _skipString() throws IOException {
        _tokenIncomplete = false;
        final int[] iArr = UTF8StreamJsonParser._icUTF8;
        final byte[] bArr = _inputBuffer;
        while (true) {
            int i2 = _inputPtr;
            int i3 = _inputEnd;
            if (i2 >= i3) {
                this._loadMoreGuaranteed();
                i2 = _inputPtr;
                i3 = _inputEnd;
            }
            while (true) {
                if (i2 < i3) {
                    final int i4 = i2 + 1;
                    final int i5 = bArr[i2] & 255;
                    final int i6 = iArr[i5];
                    if (0 != i6) {
                        _inputPtr = i4;
                        if (34 == i5) {
                            return;
                        }
                        if (1 == i6) {
                            this._decodeEscaped();
                        } else if (2 == i6) {
                            this._skipUtf8_2();
                        } else if (3 == i6) {
                            this._skipUtf8_3();
                        } else if (4 == i6) {
                            this._skipUtf8_4(i5);
                        } else if (32 > i5) {
                            this._throwUnquotedSpace(i5, "string value");
                        } else {
                            this._reportInvalidChar(i5);
                        }
                    } else {
                        i2 = i4;
                    }
                } else {
                    _inputPtr = i2;
                    break;
                }
            }
        }
    }

    protected JsonToken _handleUnexpectedValue(final int i2) throws IOException {
        if (39 == i2) {
            if (0 != (this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES)) {
                return this._handleApos();
            }
        } else if (73 == i2) {
            this._matchToken("Infinity", 1);
            if (0 != (this._features & FEAT_MASK_NON_NUM_NUMBERS)) {
                return this.resetAsNaN("Infinity", Double.POSITIVE_INFINITY);
            }
            this._reportError("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        } else if (78 != i2) {
            if (93 != i2) {
                if (125 != i2) {
                    if (43 == i2) {
                        if (_inputPtr >= _inputEnd && !this._loadMore()) {
                            this._reportInvalidEOFInValue(JsonToken.VALUE_NUMBER_INT);
                        }
                        final byte[] bArr = _inputBuffer;
                        final int i3 = _inputPtr;
                        _inputPtr = i3 + 1;
                        return this._handleInvalidNumberStart(bArr[i3] & 255, false);
                    }
                }
            }
            this._reportUnexpectedChar(i2, "expected a value");
            if (0 != (this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES)) {
            }
        } else {
            this._matchToken("NaN", 1);
            if (0 != (this._features & FEAT_MASK_NON_NUM_NUMBERS)) {
                return this.resetAsNaN("NaN", Double.NaN);
            }
            this._reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        }
        if (Character.isJavaIdentifierStart(i2)) {
            this._reportInvalidToken("" + ((char) i2), this._validJsonTokenList());
        }
        this._reportUnexpectedChar(i2, "expected a valid value " + this._validJsonValueList());
        return null;
    }

    protected JsonToken _handleApos() throws IOException {
        int i2;
        int i_decodeEscaped;
        char[] cArrEmptyAndGetCurrentSegment = _textBuffer.emptyAndGetCurrentSegment();
        final int[] iArr = UTF8StreamJsonParser._icUTF8;
        final byte[] bArr = _inputBuffer;
        int i3 = 0;
        while (true) {
            if (_inputPtr >= _inputEnd) {
                this._loadMoreGuaranteed();
            }
            if (i3 >= cArrEmptyAndGetCurrentSegment.length) {
                cArrEmptyAndGetCurrentSegment = _textBuffer.finishCurrentSegment();
                i3 = 0;
            }
            int i4 = _inputEnd;
            final int length = _inputPtr + (cArrEmptyAndGetCurrentSegment.length - i3);
            if (length < i4) {
                i4 = length;
            }
            while (true) {
                final int i5 = _inputPtr;
                if (i5 < i4) {
                    i2 = i5 + 1;
                    _inputPtr = i2;
                    i_decodeEscaped = bArr[i5] & 255;
                    if (39 == i_decodeEscaped || 0 != iArr[i_decodeEscaped]) {
                        break;
                    }
                    cArrEmptyAndGetCurrentSegment[i3] = (char) i_decodeEscaped;
                    i3++;
                }
            }
            if (39 != i_decodeEscaped) {
                final int i6 = iArr[i_decodeEscaped];
                if (1 == i6) {
                    i_decodeEscaped = this._decodeEscaped();
                } else if (2 == i6) {
                    i_decodeEscaped = this._decodeUtf8_2(i_decodeEscaped);
                } else if (3 != i6) {
                    if (4 == i6) {
                        final int i_decodeUtf8_4 = this._decodeUtf8_4(i_decodeEscaped);
                        final int i7 = i3 + 1;
                        cArrEmptyAndGetCurrentSegment[i3] = (char) ((i_decodeUtf8_4 >> 10) | 55296);
                        if (i7 >= cArrEmptyAndGetCurrentSegment.length) {
                            cArrEmptyAndGetCurrentSegment = _textBuffer.finishCurrentSegment();
                            i3 = 0;
                        } else {
                            i3 = i7;
                        }
                        i_decodeEscaped = 56320 | (i_decodeUtf8_4 & 1023);
                    } else {
                        if (32 > i_decodeEscaped) {
                            this._throwUnquotedSpace(i_decodeEscaped, "string value");
                        }
                        this._reportInvalidChar(i_decodeEscaped);
                    }
                } else if (2 <= this._inputEnd - i2) {
                    i_decodeEscaped = this._decodeUtf8_3fast(i_decodeEscaped);
                } else {
                    i_decodeEscaped = this._decodeUtf8_3(i_decodeEscaped);
                }
                if (i3 >= cArrEmptyAndGetCurrentSegment.length) {
                    cArrEmptyAndGetCurrentSegment = _textBuffer.finishCurrentSegment();
                    i3 = 0;
                }
                cArrEmptyAndGetCurrentSegment[i3] = (char) i_decodeEscaped;
                i3++;
            } else {
                _textBuffer.setCurrentLength(i3);
                return JsonToken.VALUE_STRING;
            }
        }
    }

    protected JsonToken _handleInvalidNumberStart(int i2, final boolean z) throws IOException {
        String str;
        while (73 == i2) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                this._reportInvalidEOFInValue(JsonToken.VALUE_NUMBER_FLOAT);
            }
            final byte[] bArr = _inputBuffer;
            final int i3 = _inputPtr;
            _inputPtr = i3 + 1;
            i2 = bArr[i3];
            if (78 != i2) {
                if (110 != i2) {
                    break;
                }
                str = z ? "-Infinity" : "+Infinity";
            } else {
                str = z ? "-INF" : "+INF";
            }
            this._matchToken(str, 3);
            if (0 != (this._features & FEAT_MASK_NON_NUM_NUMBERS)) {
                return this.resetAsNaN(str, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
            }
            this._reportError("Non-standard token '%s': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow", str);
        }
        this.reportUnexpectedNumberChar(i2, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    protected final void _matchTrue() throws IOException {
        final int i2;
        final int i3 = _inputPtr;
        if (i3 + 3 < _inputEnd) {
            final byte[] bArr = _inputBuffer;
            final int i4 = i3 + 1;
            if (114 == bArr[i3]) {
                final int i5 = i3 + 2;
                if (117 == bArr[i4]) {
                    final int i6 = i3 + 3;
                    if (101 == bArr[i5] && (48 > (i2 = bArr[i6] & 255) || 93 == i2 || 125 == i2)) {
                        _inputPtr = i6;
                        return;
                    }
                }
            }
        }
        this._matchToken2("true", 1);
    }

    protected final void _matchFalse() throws IOException {
        final int i2;
        final int i3 = _inputPtr;
        if (i3 + 4 < _inputEnd) {
            final byte[] bArr = _inputBuffer;
            final int i4 = i3 + 1;
            if (97 == bArr[i3]) {
                final int i5 = i3 + 2;
                if (108 == bArr[i4]) {
                    final int i6 = i3 + 3;
                    if (115 == bArr[i5]) {
                        final int i7 = i3 + 4;
                        if (101 == bArr[i6] && (48 > (i2 = bArr[i7] & 255) || 93 == i2 || 125 == i2)) {
                            _inputPtr = i7;
                            return;
                        }
                    }
                }
            }
        }
        this._matchToken2("false", 1);
    }

    protected final void _matchNull() throws IOException {
        final int i2;
        final int i3 = _inputPtr;
        if (i3 + 3 < _inputEnd) {
            final byte[] bArr = _inputBuffer;
            final int i4 = i3 + 1;
            if (117 == bArr[i3]) {
                final int i5 = i3 + 2;
                if (108 == bArr[i4]) {
                    final int i6 = i3 + 3;
                    if (108 == bArr[i5] && (48 > (i2 = bArr[i6] & 255) || 93 == i2 || 125 == i2)) {
                        _inputPtr = i6;
                        return;
                    }
                }
            }
        }
        this._matchToken2("null", 1);
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
        final int i4 = _inputBuffer[i3] & 255;
        if (48 > i4 || 93 == i4 || 125 == i4) {
            return;
        }
        this._checkMatchEnd(str, i2, i4);
    }

    private void _matchToken2(final String str, int i2) throws IOException {
        int i3;
        final int i4;
        final int length = str.length();
        do {
            if ((_inputPtr >= _inputEnd && !this._loadMore()) || _inputBuffer[_inputPtr] != str.charAt(i2)) {
                this._reportInvalidToken(str.substring(0, i2));
            }
            i3 = _inputPtr + 1;
            _inputPtr = i3;
            i2++;
        } while (i2 < length);
        if ((i3 < _inputEnd || this._loadMore()) && 48 <= (i4 = this._inputBuffer[this._inputPtr] & 255) && 93 != i4 && 125 != i4) {
            this._checkMatchEnd(str, i2, i4);
        }
    }

    private void _checkMatchEnd(final String str, final int i2, final int i3) throws IOException {
        if (Character.isJavaIdentifierPart((char) this._decodeCharForError(i3))) {
            this._reportInvalidToken(str.substring(0, i2));
        }
    }

    private int _skipWS() throws IOException {
        while (true) {
            final int i2 = _inputPtr;
            if (i2 < _inputEnd) {
                final byte[] bArr = _inputBuffer;
                final int i3 = i2 + 1;
                _inputPtr = i3;
                final int i4 = bArr[i2] & 255;
                if (32 < i4) {
                    if (47 != i4 && 35 != i4) {
                        return i4;
                    }
                    _inputPtr = i2;
                    return this._skipWS2();
                }
                if (32 != i4) {
                    if (10 == i4) {
                        _currInputRow++;
                        _currInputRowStart = i3;
                    } else if (13 == i4) {
                        this._skipCR();
                    } else if (9 != i4) {
                        this._throwInvalidSpace(i4);
                    }
                }
            } else {
                return this._skipWS2();
            }
        }
    }

    private int _skipWS2() throws IOException {
        int i2;
        while (true) {
            if (_inputPtr < _inputEnd || this._loadMore()) {
                final byte[] bArr = _inputBuffer;
                final int i3 = _inputPtr;
                final int i4 = i3 + 1;
                _inputPtr = i4;
                i2 = bArr[i3] & 255;
                if (32 < i2) {
                    if (47 == i2) {
                        this._skipComment();
                    } else if (35 != i2 || !this._skipYAMLComment()) {
                        break;
                    }
                } else if (32 != i2) {
                    if (10 == i2) {
                        _currInputRow++;
                        _currInputRowStart = i4;
                    } else if (13 == i2) {
                        this._skipCR();
                    } else if (9 != i2) {
                        this._throwInvalidSpace(i2);
                    }
                }
            } else {
                throw this._constructError("Unexpected end-of-input within/between " + _parsingContext.typeDesc() + " entries");
            }
        }
        return i2;
    }

    private  int _skipWSOrEnd() throws IOException {
        if (_inputPtr >= _inputEnd && !this._loadMore()) {
            return this._eofAsNextChar();
        }
        final byte[] bArr = _inputBuffer;
        final int i2 = _inputPtr;
        final int i3 = i2 + 1;
        _inputPtr = i3;
        final int i4 = bArr[i2] & 255;
        if (32 < i4) {
            if (47 != i4 && 35 != i4) {
                return i4;
            }
            _inputPtr = i2;
            return this._skipWSOrEnd2();
        }
        if (32 != i4) {
            if (10 == i4) {
                _currInputRow++;
                _currInputRowStart = i3;
            } else if (13 == i4) {
                this._skipCR();
            } else if (9 != i4) {
                this._throwInvalidSpace(i4);
            }
        }
        while (true) {
            final int i5 = _inputPtr;
            if (i5 < _inputEnd) {
                final byte[] bArr2 = _inputBuffer;
                final int i6 = i5 + 1;
                _inputPtr = i6;
                final int i7 = bArr2[i5] & 255;
                if (32 < i7) {
                    if (47 != i7 && 35 != i7) {
                        return i7;
                    }
                    _inputPtr = i5;
                    return this._skipWSOrEnd2();
                }
                if (32 != i7) {
                    if (10 == i7) {
                        _currInputRow++;
                        _currInputRowStart = i6;
                    } else if (13 == i7) {
                        this._skipCR();
                    } else if (9 != i7) {
                        this._throwInvalidSpace(i7);
                    }
                }
            } else {
                return this._skipWSOrEnd2();
            }
        }
    }

    private int _skipWSOrEnd2() throws IOException {
        int i2;
        while (true) {
            if (_inputPtr < _inputEnd || this._loadMore()) {
                final byte[] bArr = _inputBuffer;
                final int i3 = _inputPtr;
                final int i4 = i3 + 1;
                _inputPtr = i4;
                i2 = bArr[i3] & 255;
                if (32 < i2) {
                    if (47 == i2) {
                        this._skipComment();
                    } else if (35 != i2 || !this._skipYAMLComment()) {
                        break;
                    }
                } else if (32 != i2) {
                    if (10 == i2) {
                        _currInputRow++;
                        _currInputRowStart = i4;
                    } else if (13 == i2) {
                        this._skipCR();
                    } else if (9 != i2) {
                        this._throwInvalidSpace(i2);
                    }
                }
            } else {
                return this._eofAsNextChar();
            }
        }
        return i2;
    }

    private int _skipColon() throws IOException {
        final int i2 = _inputPtr;
        if (i2 + 4 >= _inputEnd) {
            return this._skipColon2(false);
        }
        final byte[] bArr = _inputBuffer;
        byte b2 = bArr[i2];
        if (58 == b2) {
            final int i3 = i2 + 1;
            _inputPtr = i3;
            final byte b3 = bArr[i3];
            if (32 < b3) {
                if (47 == b3 || 35 == b3) {
                    return this._skipColon2(true);
                }
                _inputPtr = i2 + 2;
                return b3;
            }
            if (32 == b3 || 9 == b3) {
                final int i4 = i2 + 2;
                _inputPtr = i4;
                final byte b4 = bArr[i4];
                if (32 < b4) {
                    if (47 == b4 || 35 == b4) {
                        return this._skipColon2(true);
                    }
                    _inputPtr = i2 + 3;
                    return b4;
                }
            }
            return this._skipColon2(true);
        }
        if (32 == b2 || 9 == b2) {
            final int i5 = i2 + 1;
            _inputPtr = i5;
            b2 = bArr[i5];
        }
        if (58 == b2) {
            final int i6 = _inputPtr;
            final int i7 = i6 + 1;
            _inputPtr = i7;
            final byte b5 = bArr[i7];
            if (32 < b5) {
                if (47 == b5 || 35 == b5) {
                    return this._skipColon2(true);
                }
                _inputPtr = i6 + 2;
                return b5;
            }
            if (32 == b5 || 9 == b5) {
                final int i8 = i6 + 2;
                _inputPtr = i8;
                final byte b6 = bArr[i8];
                if (32 < b6) {
                    if (47 == b6 || 35 == b6) {
                        return this._skipColon2(true);
                    }
                    _inputPtr = i6 + 3;
                    return b6;
                }
            }
            return this._skipColon2(true);
        }
        return this._skipColon2(false);
    }

    private int _skipColon2(boolean z) throws IOException {
        while (true) {
            if (_inputPtr < _inputEnd || this._loadMore()) {
                final byte[] bArr = _inputBuffer;
                final int i2 = _inputPtr;
                final int i3 = i2 + 1;
                _inputPtr = i3;
                final int i4 = bArr[i2] & 255;
                if (32 < i4) {
                    if (47 == i4) {
                        this._skipComment();
                    } else if (35 != i4 || !this._skipYAMLComment()) {
                        if (z) {
                            return i4;
                        }
                        if (58 != i4) {
                            this._reportUnexpectedChar(i4, "was expecting a colon to separate field name and value");
                        }
                        z = true;
                    }
                } else if (32 != i4) {
                    if (10 == i4) {
                        _currInputRow++;
                        _currInputRowStart = i3;
                    } else if (13 == i4) {
                        this._skipCR();
                    } else if (9 != i4) {
                        this._throwInvalidSpace(i4);
                    }
                }
            } else {
                this._reportInvalidEOF(" within/between " + _parsingContext.typeDesc() + " entries", null);
                return -1;
            }
        }
    }

    private void _skipComment() throws IOException {
        if (0 == (this._features & FEAT_MASK_ALLOW_JAVA_COMMENTS)) {
            this._reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (_inputPtr >= _inputEnd && !this._loadMore()) {
            this._reportInvalidEOF(" in a comment", null);
        }
        final byte[] bArr = _inputBuffer;
        final int i2 = _inputPtr;
        _inputPtr = i2 + 1;
        final int i3 = bArr[i2] & 255;
        if (47 == i3) {
            this._skipLine();
        } else if (42 == i3) {
            this._skipCComment();
        } else {
            this._reportUnexpectedChar(i3, "was expecting either '*' or '/' for a comment");
        }
    }

    private void _skipCComment() throws IOException {
        final int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                break;
            }
            final byte[] bArr = _inputBuffer;
            final int i2 = _inputPtr;
            final int i3 = i2 + 1;
            _inputPtr = i3;
            final int i4 = bArr[i2] & 255;
            final int i5 = inputCodeComment[i4];
            if (0 != i5) {
                if (2 == i5) {
                    this._skipUtf8_2();
                } else if (3 == i5) {
                    this._skipUtf8_3();
                } else if (4 == i5) {
                    this._skipUtf8_4(i4);
                } else if (10 == i5) {
                    _currInputRow++;
                    _currInputRowStart = i3;
                } else if (13 == i5) {
                    this._skipCR();
                } else if (42 == i5) {
                    if (i3 >= _inputEnd && !this._loadMore()) {
                        break;
                    }
                    final byte[] bArr2 = _inputBuffer;
                    final int i6 = _inputPtr;
                    if (47 == bArr2[i6]) {
                        _inputPtr = i6 + 1;
                        return;
                    }
                } else {
                    this._reportInvalidChar(i4);
                }
            }
        }
    }

    private boolean _skipYAMLComment() throws IOException {
        if (0 == (this._features & FEAT_MASK_ALLOW_YAML_COMMENTS)) {
            return false;
        }
        this._skipLine();
        return true;
    }

    private void _skipLine() throws IOException {
        final int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                return;
            }
            final byte[] bArr = _inputBuffer;
            final int i2 = _inputPtr;
            final int i3 = i2 + 1;
            _inputPtr = i3;
            final int i4 = bArr[i2] & 255;
            final int i5 = inputCodeComment[i4];
            if (0 != i5) {
                if (2 == i5) {
                    this._skipUtf8_2();
                } else if (3 == i5) {
                    this._skipUtf8_3();
                } else if (4 == i5) {
                    this._skipUtf8_4(i4);
                } else if (10 == i5) {
                    _currInputRow++;
                    _currInputRowStart = i3;
                    return;
                } else if (13 == i5) {
                    this._skipCR();
                    return;
                } else if (42 != i5 && 0 > i5) {
                    this._reportInvalidChar(i4);
                }
            }
        }
    }

    protected char _decodeEscaped() throws IOException {
        if (_inputPtr >= _inputEnd && !this._loadMore()) {
            this._reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
        }
        final byte[] bArr = _inputBuffer;
        final int i2 = _inputPtr;
        _inputPtr = i2 + 1;
        final byte b2 = bArr[i2];
        if (34 == b2 || 47 == b2 || 92 == b2) {
            return (char) b2;
        }
        if (98 == b2) {
            return '\b';
        }
        if (102 == b2) {
            return '\f';
        }
        if (110 == b2) {
            return '\n';
        }
        if (114 == b2) {
            return '\r';
        }
        if (116 == b2) {
            return '\t';
        }
        if (117 != b2) {
            return this._handleUnrecognizedCharacterEscape((char) this._decodeCharForError(b2));
        }
        int i3 = 0;
        for (int i4 = 0; 4 > i4; i4++) {
            if (_inputPtr >= _inputEnd && !this._loadMore()) {
                this._reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
            }
            final byte[] bArr2 = _inputBuffer;
            final int i5 = _inputPtr;
            _inputPtr = i5 + 1;
            final byte b3 = bArr2[i5];
            final int iCharToHex = CharTypes.charToHex(b3);
            if (0 > iCharToHex) {
                this._reportUnexpectedChar(b3 & 255, "expected a hex-digit for character escape sequence");
            }
            i3 = (i3 << 4) | iCharToHex;
        }
        return (char) i3;
    }

    protected int _decodeCharForError(final int i2) throws IOException {
        char c2 = 0;
        final int iNextByte;
        int i3 = i2 & 255;
        if (127 >= i3) {
            return i3;
        }
        if (192 != (i2 & 224)) {
            if (224 == (i2 & 240)) {
                i3 = i2 & 15;
                c2 = 2;
            } else if (240 == (i2 & 248)) {
                i3 = i2 & 7;
                c2 = 3;
            } else {
                this._reportInvalidInitial(i2 & 255);
            }
            iNextByte = this.nextByte();
            if (128 != (iNextByte & Wbxml.EXT_0)) {
                this._reportInvalidOther(iNextByte & 255);
            }
            final int i4 = (i3 << 6) | (iNextByte & 63);
            if (1 < c2) {
                return i4;
            }
            final int iNextByte2 = this.nextByte();
            if (128 != (iNextByte2 & Wbxml.EXT_0)) {
                this._reportInvalidOther(iNextByte2 & 255);
            }
            final int i5 = (i4 << 6) | (iNextByte2 & 63);
            if (2 >= c2) {
                return i5;
            }
            final int iNextByte3 = this.nextByte();
            if (128 != (iNextByte3 & Wbxml.EXT_0)) {
                this._reportInvalidOther(iNextByte3 & 255);
            }
            return (i5 << 6) | (iNextByte3 & 63);
        }
        i3 = i2 & 31;
        c2 = 1;
        iNextByte = this.nextByte();
        if (128 != (iNextByte & Wbxml.EXT_0)) {
        }
        final int i42 = (i3 << 6) | (iNextByte & 63);
        if (1 < c2) {
        }
        return iNextByte;
    }

    private int _decodeUtf8_2(final int i2) throws IOException {
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final byte[] bArr = _inputBuffer;
        final int i3 = _inputPtr;
        final int i4 = i3 + 1;
        _inputPtr = i4;
        final byte b2 = bArr[i3];
        if (128 != (b2 & 192)) {
            this._reportInvalidOther(b2 & 255, i4);
        }
        return ((i2 & 31) << 6) | (b2 & 63);
    }

    private int _decodeUtf8_3(final int i2) throws IOException {
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final int i3 = i2 & 15;
        final byte[] bArr = _inputBuffer;
        final int i4 = _inputPtr;
        final int i5 = i4 + 1;
        _inputPtr = i5;
        final byte b2 = bArr[i4];
        if (128 != (b2 & 192)) {
            this._reportInvalidOther(b2 & 255, i5);
        }
        final int i6 = (i3 << 6) | (b2 & 63);
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final byte[] bArr2 = _inputBuffer;
        final int i7 = _inputPtr;
        final int i8 = i7 + 1;
        _inputPtr = i8;
        final byte b3 = bArr2[i7];
        if (128 != (b3 & 192)) {
            this._reportInvalidOther(b3 & 255, i8);
        }
        return (i6 << 6) | (b3 & 63);
    }

    private int _decodeUtf8_3fast(final int i2) throws IOException {
        final int i3 = i2 & 15;
        final byte[] bArr = _inputBuffer;
        final int i4 = _inputPtr;
        final int i5 = i4 + 1;
        _inputPtr = i5;
        final byte b2 = bArr[i4];
        if (128 != (b2 & 192)) {
            this._reportInvalidOther(b2 & 255, i5);
        }
        final int i6 = (i3 << 6) | (b2 & 63);
        final byte[] bArr2 = _inputBuffer;
        final int i7 = _inputPtr;
        final int i8 = i7 + 1;
        _inputPtr = i8;
        final byte b3 = bArr2[i7];
        if (128 != (b3 & 192)) {
            this._reportInvalidOther(b3 & 255, i8);
        }
        return (i6 << 6) | (b3 & 63);
    }

    private int _decodeUtf8_4(final int i2) throws IOException {
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final byte[] bArr = _inputBuffer;
        final int i3 = _inputPtr;
        final int i4 = i3 + 1;
        _inputPtr = i4;
        final byte b2 = bArr[i3];
        if (128 != (b2 & 192)) {
            this._reportInvalidOther(b2 & 255, i4);
        }
        final int i5 = ((i2 & 7) << 6) | (b2 & 63);
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final byte[] bArr2 = _inputBuffer;
        final int i6 = _inputPtr;
        final int i7 = i6 + 1;
        _inputPtr = i7;
        final byte b3 = bArr2[i6];
        if (128 != (b3 & 192)) {
            this._reportInvalidOther(b3 & 255, i7);
        }
        final int i8 = (i5 << 6) | (b3 & 63);
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final byte[] bArr3 = _inputBuffer;
        final int i9 = _inputPtr;
        final int i10 = i9 + 1;
        _inputPtr = i10;
        final byte b4 = bArr3[i9];
        if (128 != (b4 & 192)) {
            this._reportInvalidOther(b4 & 255, i10);
        }
        return ((i8 << 6) | (b4 & 63)) - 65536;
    }

    private void _skipUtf8_2() throws IOException {
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final byte[] bArr = _inputBuffer;
        final int i2 = _inputPtr;
        final int i3 = i2 + 1;
        _inputPtr = i3;
        final byte b2 = bArr[i2];
        if (128 != (b2 & 192)) {
            this._reportInvalidOther(b2 & 255, i3);
        }
    }

    private void _skipUtf8_3() throws IOException {
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final byte[] bArr = _inputBuffer;
        final int i2 = _inputPtr;
        final int i3 = i2 + 1;
        _inputPtr = i3;
        final byte b2 = bArr[i2];
        if (128 != (b2 & 192)) {
            this._reportInvalidOther(b2 & 255, i3);
        }
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final byte[] bArr2 = _inputBuffer;
        final int i4 = _inputPtr;
        final int i5 = i4 + 1;
        _inputPtr = i5;
        final byte b3 = bArr2[i4];
        if (128 != (b3 & 192)) {
            this._reportInvalidOther(b3 & 255, i5);
        }
    }

    private void _skipUtf8_4(final int i2) throws IOException {
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final byte[] bArr = _inputBuffer;
        final int i3 = _inputPtr;
        final int i4 = i3 + 1;
        _inputPtr = i4;
        final byte b2 = bArr[i3];
        if (128 != (b2 & 192)) {
            this._reportInvalidOther(b2 & 255, i4);
        }
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final byte[] bArr2 = _inputBuffer;
        final int i5 = _inputPtr;
        final int i6 = i5 + 1;
        _inputPtr = i6;
        final byte b3 = bArr2[i5];
        if (128 != (b3 & 192)) {
            this._reportInvalidOther(b3 & 255, i6);
        }
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final byte[] bArr3 = _inputBuffer;
        final int i7 = _inputPtr;
        final int i8 = i7 + 1;
        _inputPtr = i8;
        final byte b4 = bArr3[i7];
        if (128 != (b4 & 192)) {
            this._reportInvalidOther(b4 & 255, i8);
        }
    }

    protected final void _skipCR() throws IOException {
        if (_inputPtr < _inputEnd || this._loadMore()) {
            final byte[] bArr = _inputBuffer;
            final int i2 = _inputPtr;
            if (10 == bArr[i2]) {
                _inputPtr = i2 + 1;
            }
        }
        _currInputRow++;
        _currInputRowStart = _inputPtr;
    }

    private int nextByte() throws IOException {
        if (_inputPtr >= _inputEnd) {
            this._loadMoreGuaranteed();
        }
        final byte[] bArr = _inputBuffer;
        final int i2 = _inputPtr;
        _inputPtr = i2 + 1;
        return bArr[i2] & 255;
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
            final byte[] bArr = _inputBuffer;
            final int i2 = _inputPtr;
            _inputPtr = i2 + 1;
            final char c_decodeCharForError = (char) this._decodeCharForError(bArr[i2]);
            if (!Character.isJavaIdentifierPart(c_decodeCharForError)) {
                break;
            }
            sb.append(c_decodeCharForError);
            if (256 <= sb.length()) {
                sb.append("...");
                break;
            }
        }
        this._reportError("Unrecognized token '%s': was expecting %s", sb, str2);
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

    protected void _reportInvalidOther(final int i2) throws JsonParseException {
        this._reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i2));
    }

    protected void _reportInvalidOther(final int i2, final int i3) throws JsonParseException {
        _inputPtr = i3;
        this._reportInvalidOther(i2);
    }

    protected final byte[] _decodeBase64(final Base64Variant base64Variant) throws IOException {
        final ByteArrayBuilder byteArrayBuilder_getByteArrayBuilder = this._getByteArrayBuilder();
        while (true) {
            if (_inputPtr >= _inputEnd) {
                this._loadMoreGuaranteed();
            }
            final byte[] bArr = _inputBuffer;
            final int i2 = _inputPtr;
            _inputPtr = i2 + 1;
            final int i3 = bArr[i2] & 255;
            if (32 < i3) {
                int iDecodeBase64Char = base64Variant.decodeBase64Char(i3);
                if (0 > iDecodeBase64Char) {
                    if (34 == i3) {
                        return byteArrayBuilder_getByteArrayBuilder.toByteArray();
                    }
                    iDecodeBase64Char = this._decodeBase64Escape(base64Variant, i3, 0);
                    if (0 > iDecodeBase64Char) {
                        continue;
                    }
                }
                if (_inputPtr >= _inputEnd) {
                    this._loadMoreGuaranteed();
                }
                final byte[] bArr2 = _inputBuffer;
                final int i4 = _inputPtr;
                _inputPtr = i4 + 1;
                final int i5 = bArr2[i4] & 255;
                int iDecodeBase64Char2 = base64Variant.decodeBase64Char(i5);
                if (0 > iDecodeBase64Char2) {
                    iDecodeBase64Char2 = this._decodeBase64Escape(base64Variant, i5, 1);
                }
                final int i6 = (iDecodeBase64Char << 6) | iDecodeBase64Char2;
                if (_inputPtr >= _inputEnd) {
                    this._loadMoreGuaranteed();
                }
                final byte[] bArr3 = _inputBuffer;
                final int i7 = _inputPtr;
                _inputPtr = i7 + 1;
                final int i8 = bArr3[i7] & 255;
                int iDecodeBase64Char3 = base64Variant.decodeBase64Char(i8);
                if (0 > iDecodeBase64Char3) {
                    if (-2 != iDecodeBase64Char3) {
                        if (34 == i8) {
                            byteArrayBuilder_getByteArrayBuilder.append(i6 >> 4);
                            if (base64Variant.usesPadding()) {
                                _inputPtr--;
                                this._handleBase64MissingPadding(base64Variant);
                            }
                            return byteArrayBuilder_getByteArrayBuilder.toByteArray();
                        }
                        iDecodeBase64Char3 = this._decodeBase64Escape(base64Variant, i8, 2);
                    }
                    if (-2 == iDecodeBase64Char3) {
                        if (_inputPtr >= _inputEnd) {
                            this._loadMoreGuaranteed();
                        }
                        final byte[] bArr4 = _inputBuffer;
                        final int i9 = _inputPtr;
                        _inputPtr = i9 + 1;
                        final int i10 = bArr4[i9] & 255;
                        if (!base64Variant.usesPaddingChar(i10) && -2 != _decodeBase64Escape(base64Variant, i10, 3)) {
                            throw this.reportInvalidBase64Char(base64Variant, i10, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        byteArrayBuilder_getByteArrayBuilder.append(i6 >> 4);
                    }
                }
                final int i11 = (i6 << 6) | iDecodeBase64Char3;
                if (_inputPtr >= _inputEnd) {
                    this._loadMoreGuaranteed();
                }
                final byte[] bArr5 = _inputBuffer;
                final int i12 = _inputPtr;
                _inputPtr = i12 + 1;
                final int i13 = bArr5[i12] & 255;
                int iDecodeBase64Char4 = base64Variant.decodeBase64Char(i13);
                if (0 > iDecodeBase64Char4) {
                    if (-2 != iDecodeBase64Char4) {
                        if (34 == i13) {
                            byteArrayBuilder_getByteArrayBuilder.appendTwoBytes(i11 >> 2);
                            if (base64Variant.usesPadding()) {
                                _inputPtr--;
                                this._handleBase64MissingPadding(base64Variant);
                            }
                            return byteArrayBuilder_getByteArrayBuilder.toByteArray();
                        }
                        iDecodeBase64Char4 = this._decodeBase64Escape(base64Variant, i13, 3);
                    }
                    if (-2 == iDecodeBase64Char4) {
                        byteArrayBuilder_getByteArrayBuilder.appendTwoBytes(i11 >> 2);
                    }
                }
                byteArrayBuilder_getByteArrayBuilder.appendThreeBytes((i11 << 6) | iDecodeBase64Char4);
            }
        }
    }

    public JsonLocation getTokenLocation() {
        if (JsonToken.FIELD_NAME == this._currToken) {
            return new JsonLocation(this._getSourceReference(), _currInputProcessed + (_nameStartOffset - 1), -1L, _nameStartRow, _nameStartCol);
        }
        return new JsonLocation(this._getSourceReference(), _tokenInputTotal - 1, -1L, _tokenInputRow, _tokenInputCol);
    }

    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._getSourceReference(), _currInputProcessed + _inputPtr, -1L, _currInputRow, (_inputPtr - _currInputRowStart) + 1);
    }

    private void _updateLocation() {
        _tokenInputRow = _currInputRow;
        final int i2 = _inputPtr;
        _tokenInputTotal = _currInputProcessed + i2;
        _tokenInputCol = i2 - _currInputRowStart;
    }

    private void _updateNameLocation() {
        _nameStartRow = _currInputRow;
        final int i2 = _inputPtr;
        _nameStartOffset = i2;
        _nameStartCol = i2 - _currInputRowStart;
    }

    private JsonToken _closeScope(final int i2) throws JsonParseException {
        if (125 == i2) {
            this._closeObjectScope();
            final JsonToken jsonToken = JsonToken.END_OBJECT;
            _currToken = jsonToken;
            return jsonToken;
        }
        this._closeArrayScope();
        final JsonToken jsonToken2 = JsonToken.END_ARRAY;
        _currToken = jsonToken2;
        return jsonToken2;
    }

    private void _closeArrayScope() throws JsonParseException {
        this._updateLocation();
        if (!_parsingContext.inArray()) {
            this._reportMismatchedEndMarker(93, '}');
        }
        _parsingContext = _parsingContext.clearAndGetParent();
    }

    private void _closeObjectScope() throws JsonParseException {
        this._updateLocation();
        if (!_parsingContext.inObject()) {
            this._reportMismatchedEndMarker(125, ']');
        }
        _parsingContext = _parsingContext.clearAndGetParent();
    }
}
