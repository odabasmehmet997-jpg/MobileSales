package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import org.kxml2.wap.Wbxml;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class UTF8DataInputJsonParser extends ParserBase {
    protected DataInput _inputData;
    protected int _nextByte;
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
    private static final int pad(final int i2, final int i3) {
        return 4 == i3 ? i2 : i2 | ((-1) << (i3 << 3));
    }
    protected void _closeInput() throws IOException {
    }
    public UTF8DataInputJsonParser(final IOContext iOContext, final int i2, final DataInput dataInput, final ObjectCodec objectCodec, final ByteQuadsCanonicalizer byteQuadsCanonicalizer, final int i3) {
        super(iOContext, i2);
        _quadBuffer = new int[16];
        _objectCodec = objectCodec;
        _symbols = byteQuadsCanonicalizer;
        _inputData = dataInput;
        _nextByte = i3;
    }
    public ObjectCodec getCodec() {
        return _objectCodec;
    }
    public JacksonFeatureSet<StreamReadCapability> getReadCapabilities() {
        return JSON_READ_CAPABILITIES;
    }
    protected void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        _symbols.release();
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
        if (JsonToken.VALUE_STRING == jsonToken) {
            if (_tokenIncomplete) {
                _tokenIncomplete = false;
                this._finishString();
            }
            return _textBuffer.size();
        }
        if (JsonToken.FIELD_NAME == jsonToken) {
            return _parsingContext.getCurrentName().length();
        }
        if (null == jsonToken) {
            return 0;
        }
        if (jsonToken.isNumeric()) {
            return _textBuffer.size();
        }
        return _currToken.asCharArray().length;
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
        final int length = bArr.length - 3;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            final int unsignedByte = _inputData.readUnsignedByte();
            if (32 < unsignedByte) {
                int iDecodeBase64Char = base64Variant.decodeBase64Char(unsignedByte);
                if (0 > iDecodeBase64Char) {
                    if (34 == unsignedByte) {
                        break;
                    }
                    iDecodeBase64Char = this._decodeBase64Escape(base64Variant, unsignedByte, 0);
                    if (0 > iDecodeBase64Char) {
                        continue;
                    }
                }
                if (i2 > length) {
                    i3 += i2;
                    outputStream.write(bArr, 0, i2);
                    i2 = 0;
                }
                final int unsignedByte2 = _inputData.readUnsignedByte();
                int iDecodeBase64Char2 = base64Variant.decodeBase64Char(unsignedByte2);
                if (0 > iDecodeBase64Char2) {
                    iDecodeBase64Char2 = this._decodeBase64Escape(base64Variant, unsignedByte2, 1);
                }
                final int i4 = (iDecodeBase64Char << 6) | iDecodeBase64Char2;
                final int unsignedByte3 = _inputData.readUnsignedByte();
                int iDecodeBase64Char3 = base64Variant.decodeBase64Char(unsignedByte3);
                if (0 > iDecodeBase64Char3) {
                    if (-2 != iDecodeBase64Char3) {
                        if (34 == unsignedByte3) {
                            final int i5 = i2 + 1;
                            bArr[i2] = (byte) (i4 >> 4);
                            if (base64Variant.usesPadding()) {
                                this._handleBase64MissingPadding(base64Variant);
                            }
                            i2 = i5;
                        } else {
                            iDecodeBase64Char3 = this._decodeBase64Escape(base64Variant, unsignedByte3, 2);
                        }
                    }
                    if (-2 == iDecodeBase64Char3) {
                        final int unsignedByte4 = _inputData.readUnsignedByte();
                        if (!base64Variant.usesPaddingChar(unsignedByte4) && (92 != unsignedByte4 || -2 != _decodeBase64Escape(base64Variant, unsignedByte4, 3))) {
                            break;
                        }
                        bArr[i2] = (byte) (i4 >> 4);
                        i2++;
                    }
                }
                final int i6 = (i4 << 6) | iDecodeBase64Char3;
                final int unsignedByte5 = _inputData.readUnsignedByte();
                int iDecodeBase64Char4 = base64Variant.decodeBase64Char(unsignedByte5);
                if (0 > iDecodeBase64Char4) {
                    if (-2 != iDecodeBase64Char4) {
                        if (34 == unsignedByte5) {
                            final int i7 = i2 + 1;
                            bArr[i2] = (byte) (i6 >> 10);
                            i2 += 2;
                            bArr[i7] = (byte) (i6 >> 2);
                            if (base64Variant.usesPadding()) {
                                this._handleBase64MissingPadding(base64Variant);
                            }
                        } else {
                            iDecodeBase64Char4 = this._decodeBase64Escape(base64Variant, unsignedByte5, 3);
                        }
                    }
                    if (-2 == iDecodeBase64Char4) {
                        final int i8 = i2 + 1;
                        bArr[i2] = (byte) (i6 >> 10);
                        i2 += 2;
                        bArr[i8] = (byte) (i6 >> 2);
                    }
                }
                final int i9 = (i6 << 6) | iDecodeBase64Char4;
                bArr[i2] = (byte) (i9 >> 16);
                final int i10 = i2 + 2;
                bArr[i2 + 1] = (byte) (i9 >> 8);
                i2 += 3;
                bArr[i10] = (byte) i9;
            }
        }
        _tokenIncomplete = false;
        if (0 >= i2) {
            return i3;
        }
        final int i11 = i3 + i2;
        outputStream.write(bArr, 0, i2);
        return i11;
    }
    public JsonToken nextToken() throws IOException {
        final JsonToken jsonToken_parseNegNumber;
        if (_closed) {
            return null;
        }
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
        _tokenInputRow = _currInputRow;
        if (93 == i_skipWSOrEnd || 125 == i_skipWSOrEnd) {
            this._closeScope(i_skipWSOrEnd);
            return _currToken;
        }
        if (_parsingContext.expectComma()) {
            if (44 != i_skipWSOrEnd) {
                this._reportUnexpectedChar(i_skipWSOrEnd, "was expecting comma to separate " + _parsingContext.typeDesc() + " entries");
            }
            i_skipWSOrEnd = this._skipWS();
            if (0 != (this._features & FEAT_MASK_TRAILING_COMMA) && (93 == i_skipWSOrEnd || 125 == i_skipWSOrEnd)) {
                this._closeScope(i_skipWSOrEnd);
                return _currToken;
            }
        }
        if (!_parsingContext.inObject()) {
            return this._nextTokenNotInObject(i_skipWSOrEnd);
        }
        _parsingContext.setCurrentName(this._parseName(i_skipWSOrEnd));
        _currToken = jsonToken2;
        final int i_skipColon = this._skipColon();
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
            this._matchToken("false", 1);
            jsonToken_parseNegNumber = JsonToken.VALUE_FALSE;
        } else if (110 == i_skipColon) {
            this._matchToken("null", 1);
            jsonToken_parseNegNumber = JsonToken.VALUE_NULL;
        } else if (116 == i_skipColon) {
            this._matchToken("true", 1);
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
        JsonToken jsonToken_parseNegNumber;
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
        int i_skipWS = this._skipWS();
        _binaryValue = null;
        _tokenInputRow = _currInputRow;
        if (93 == i_skipWS || 125 == i_skipWS) {
            this._closeScope(i_skipWS);
            return null;
        }
        if (_parsingContext.expectComma()) {
            if (44 != i_skipWS) {
                this._reportUnexpectedChar(i_skipWS, "was expecting comma to separate " + _parsingContext.typeDesc() + " entries");
            }
            i_skipWS = this._skipWS();
            if (0 != (this._features & FEAT_MASK_TRAILING_COMMA) && (93 == i_skipWS || 125 == i_skipWS)) {
                this._closeScope(i_skipWS);
                return null;
            }
        }
        if (!_parsingContext.inObject()) {
            this._nextTokenNotInObject(i_skipWS);
            return null;
        }
        final String str_parseName = this._parseName(i_skipWS);
        _parsingContext.setCurrentName(str_parseName);
        _currToken = jsonToken2;
        final int i_skipColon = this._skipColon();
        if (34 == i_skipColon) {
            _tokenIncomplete = true;
            _nextToken = JsonToken.VALUE_STRING;
            return str_parseName;
        }
        if (45 == i_skipColon) {
            jsonToken_parseNegNumber = this._parseNegNumber();
        } else {
            if (46 == i_skipColon) {
                this._parseFloatThatStartsWithPeriod();
            } else if (91 == i_skipColon) {
                jsonToken_parseNegNumber = JsonToken.START_ARRAY;
            } else if (102 == i_skipColon) {
                this._matchToken("false", 1);
                jsonToken_parseNegNumber = JsonToken.VALUE_FALSE;
            } else if (110 == i_skipColon) {
                this._matchToken("null", 1);
                jsonToken_parseNegNumber = JsonToken.VALUE_NULL;
            } else if (116 == i_skipColon) {
                this._matchToken("true", 1);
                jsonToken_parseNegNumber = JsonToken.VALUE_TRUE;
            } else if (123 == i_skipColon) {
                jsonToken_parseNegNumber = JsonToken.START_OBJECT;
            } else {
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
                        break;
                    default:
                        jsonToken_parseNegNumber = this._handleUnexpectedValue(i_skipColon);
                        break;
                }
            }
            jsonToken_parseNegNumber = this._parsePosNumber(i_skipColon);
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
    protected JsonToken _parsePosNumber(final int i2) throws IOException {
        final int unsignedByte;
        final char[] cArrEmptyAndGetCurrentSegment = _textBuffer.emptyAndGetCurrentSegment();
        int i3 = 1;
        if (48 == i2) {
            unsignedByte = this._handleLeadingZeroes();
            if (57 < unsignedByte || 48 > unsignedByte) {
                cArrEmptyAndGetCurrentSegment[0] = '0';
            } else {
                i3 = 0;
            }
        } else {
            cArrEmptyAndGetCurrentSegment[0] = (char) i2;
            unsignedByte = _inputData.readUnsignedByte();
        }
        int unsignedByte2 = unsignedByte;
        char[] cArrFinishCurrentSegment = cArrEmptyAndGetCurrentSegment;
        int i4 = i3;
        int i5 = i4;
        while (57 >= unsignedByte2 && 48 <= unsignedByte2) {
            i5++;
            if (i4 >= cArrFinishCurrentSegment.length) {
                cArrFinishCurrentSegment = _textBuffer.finishCurrentSegment();
                i4 = 0;
            }
            cArrFinishCurrentSegment[i4] = (char) unsignedByte2;
            unsignedByte2 = _inputData.readUnsignedByte();
            i4++;
        }
        if (46 == unsignedByte2 || 101 == unsignedByte2 || 69 == unsignedByte2) {
            return this._parseFloat(cArrFinishCurrentSegment, i4, unsignedByte2, false, i5);
        }
        _textBuffer.setCurrentLength(i4);
        if (_parsingContext.inRoot()) {
            this._verifyRootSpace();
        } else {
            _nextByte = unsignedByte2;
        }
        return this.resetInt(false, i5);
    }
    protected JsonToken _parseNegNumber() throws IOException {
        int unsignedByte;
        final char[] cArrEmptyAndGetCurrentSegment = _textBuffer.emptyAndGetCurrentSegment();
        cArrEmptyAndGetCurrentSegment[0] = '-';
        final int unsignedByte2 = _inputData.readUnsignedByte();
        cArrEmptyAndGetCurrentSegment[1] = (char) unsignedByte2;
        if (48 >= unsignedByte2) {
            if (48 == unsignedByte2) {
                unsignedByte = this._handleLeadingZeroes();
            } else {
                return this._handleInvalidNumberStart(unsignedByte2, true);
            }
        } else {
            if (57 < unsignedByte2) {
                return this._handleInvalidNumberStart(unsignedByte2, true);
            }
            unsignedByte = _inputData.readUnsignedByte();
        }
        int i2 = 2;
        int i3 = 1;
        while (57 >= unsignedByte && 48 <= unsignedByte) {
            i3++;
            cArrEmptyAndGetCurrentSegment[i2] = (char) unsignedByte;
            unsignedByte = _inputData.readUnsignedByte();
            i2++;
        }
        if (46 == unsignedByte || 101 == unsignedByte || 69 == unsignedByte) {
            return this._parseFloat(cArrEmptyAndGetCurrentSegment, i2, unsignedByte, true, i3);
        }
        _textBuffer.setCurrentLength(i2);
        _nextByte = unsignedByte;
        if (_parsingContext.inRoot()) {
            this._verifyRootSpace();
        }
        return this.resetInt(true, i3);
    }
    private int _handleLeadingZeroes() throws IOException {
        int unsignedByte = _inputData.readUnsignedByte();
        if (48 <= unsignedByte && 57 >= unsignedByte) {
            if (0 == (this._features & FEAT_MASK_LEADING_ZEROS)) {
                this.reportInvalidNumber("Leading zeroes not allowed");
            }
            while (48 == unsignedByte) {
                unsignedByte = _inputData.readUnsignedByte();
            }
        }
        return unsignedByte;
    }
    private JsonToken _parseFloat(char[] cArr, int i2, int i3, final boolean z, final int i4) throws IOException {
        final int i5;
        int i6;
        int unsignedByte;
        int i7 = 0;
        if (46 == i3) {
            cArr[i2] = (char) 46;
            i2++;
            int i8 = 0;
            while (true) {
                unsignedByte = _inputData.readUnsignedByte();
                if (48 > unsignedByte || 57 < unsignedByte) {
                    break;
                }
                i8++;
                if (i2 >= cArr.length) {
                    cArr = _textBuffer.finishCurrentSegment();
                    i2 = 0;
                }
                cArr[i2] = (char) unsignedByte;
                i2++;
            }
            if (0 == i8) {
                this.reportUnexpectedNumberChar(unsignedByte, "Decimal point not followed by a digit");
            }
            i5 = i8;
            i3 = unsignedByte;
        } else {
            i5 = 0;
        }
        if (101 == i3 || 69 == i3) {
            if (i2 >= cArr.length) {
                cArr = _textBuffer.finishCurrentSegment();
                i2 = 0;
            }
            int i9 = i2 + 1;
            cArr[i2] = (char) i3;
            final int unsignedByte2 = _inputData.readUnsignedByte();
            if (45 == unsignedByte2 || 43 == unsignedByte2) {
                if (i9 >= cArr.length) {
                    cArr = _textBuffer.finishCurrentSegment();
                    i9 = 0;
                }
                final int i10 = i9 + 1;
                cArr[i9] = (char) unsignedByte2;
                i6 = 0;
                i3 = _inputData.readUnsignedByte();
                i2 = i10;
            } else {
                i3 = unsignedByte2;
                i2 = i9;
                i6 = 0;
            }
            while (57 >= i3 && 48 <= i3) {
                i6++;
                if (i2 >= cArr.length) {
                    cArr = _textBuffer.finishCurrentSegment();
                    i2 = 0;
                }
                cArr[i2] = (char) i3;
                i3 = _inputData.readUnsignedByte();
                i2++;
            }
            if (0 == i6) {
                this.reportUnexpectedNumberChar(i3, "Exponent indicator not followed by a digit");
            }
            i7 = i6;
        }
        _nextByte = i3;
        if (_parsingContext.inRoot()) {
            this._verifyRootSpace();
        }
        _textBuffer.setCurrentLength(i2);
        return this.resetFloat(z, i4, i5, i7);
    }
    private void _verifyRootSpace() throws IOException {
        final int i2 = _nextByte;
        if (32 >= i2) {
            _nextByte = -1;
            if (13 == i2 || 10 == i2) {
                _currInputRow++;
                return;
            }
            return;
        }
        this._reportMissingRootWS(i2);
    }
    protected final String _parseName(final int i2) throws IOException {
        if (34 != i2) {
            return this._handleOddName(i2);
        }
        final int[] iArr = UTF8DataInputJsonParser._icLatin1;
        final int unsignedByte = _inputData.readUnsignedByte();
        if (0 != iArr[unsignedByte]) {
            if (34 == unsignedByte) {
                return "";
            }
            return this.parseName(0, unsignedByte, 0);
        }
        final int unsignedByte2 = _inputData.readUnsignedByte();
        if (0 != iArr[unsignedByte2]) {
            if (34 == unsignedByte2) {
                return this.findName(unsignedByte, 1);
            }
            return this.parseName(unsignedByte, unsignedByte2, 1);
        }
        final int i3 = (unsignedByte << 8) | unsignedByte2;
        final int unsignedByte3 = _inputData.readUnsignedByte();
        if (0 != iArr[unsignedByte3]) {
            if (34 == unsignedByte3) {
                return this.findName(i3, 2);
            }
            return this.parseName(i3, unsignedByte3, 2);
        }
        final int i4 = (i3 << 8) | unsignedByte3;
        final int unsignedByte4 = _inputData.readUnsignedByte();
        if (0 != iArr[unsignedByte4]) {
            if (34 == unsignedByte4) {
                return this.findName(i4, 3);
            }
            return this.parseName(i4, unsignedByte4, 3);
        }
        final int i5 = (i4 << 8) | unsignedByte4;
        final int unsignedByte5 = _inputData.readUnsignedByte();
        if (0 == iArr[unsignedByte5]) {
            _quad1 = i5;
            return this._parseMediumName(unsignedByte5);
        }
        if (34 == unsignedByte5) {
            return this.findName(i5, 4);
        }
        return this.parseName(i5, unsignedByte5, 4);
    }
    private String _parseMediumName(final int i2) throws IOException {
        final int[] iArr = UTF8DataInputJsonParser._icLatin1;
        final int unsignedByte = _inputData.readUnsignedByte();
        if (0 != iArr[unsignedByte]) {
            if (34 == unsignedByte) {
                return this.findName(_quad1, i2, 1);
            }
            return this.parseName(_quad1, i2, unsignedByte, 1);
        }
        final int i3 = (i2 << 8) | unsignedByte;
        final int unsignedByte2 = _inputData.readUnsignedByte();
        if (0 != iArr[unsignedByte2]) {
            if (34 == unsignedByte2) {
                return this.findName(_quad1, i3, 2);
            }
            return this.parseName(_quad1, i3, unsignedByte2, 2);
        }
        final int i4 = (i3 << 8) | unsignedByte2;
        final int unsignedByte3 = _inputData.readUnsignedByte();
        if (0 != iArr[unsignedByte3]) {
            if (34 == unsignedByte3) {
                return this.findName(_quad1, i4, 3);
            }
            return this.parseName(_quad1, i4, unsignedByte3, 3);
        }
        final int i5 = (i4 << 8) | unsignedByte3;
        final int unsignedByte4 = _inputData.readUnsignedByte();
        if (0 == iArr[unsignedByte4]) {
            return this._parseMediumName2(unsignedByte4, i5);
        }
        if (34 == unsignedByte4) {
            return this.findName(_quad1, i5, 4);
        }
        return this.parseName(_quad1, i5, unsignedByte4, 4);
    }
    private String _parseMediumName2(final int i2, final int i3) throws IOException {
        final int[] iArr = UTF8DataInputJsonParser._icLatin1;
        final int unsignedByte = _inputData.readUnsignedByte();
        if (0 != iArr[unsignedByte]) {
            if (34 == unsignedByte) {
                return this.findName(_quad1, i3, i2, 1);
            }
            return this.parseName(_quad1, i3, i2, unsignedByte, 1);
        }
        final int i4 = (i2 << 8) | unsignedByte;
        final int unsignedByte2 = _inputData.readUnsignedByte();
        if (0 != iArr[unsignedByte2]) {
            if (34 == unsignedByte2) {
                return this.findName(_quad1, i3, i4, 2);
            }
            return this.parseName(_quad1, i3, i4, unsignedByte2, 2);
        }
        final int i5 = (i4 << 8) | unsignedByte2;
        final int unsignedByte3 = _inputData.readUnsignedByte();
        if (0 != iArr[unsignedByte3]) {
            if (34 == unsignedByte3) {
                return this.findName(_quad1, i3, i5, 3);
            }
            return this.parseName(_quad1, i3, i5, unsignedByte3, 3);
        }
        final int i6 = (i5 << 8) | unsignedByte3;
        final int unsignedByte4 = _inputData.readUnsignedByte();
        if (0 == iArr[unsignedByte4]) {
            return this._parseLongName(unsignedByte4, i3, i6);
        }
        if (34 == unsignedByte4) {
            return this.findName(_quad1, i3, i6, 4);
        }
        return this.parseName(_quad1, i3, i6, unsignedByte4, 4);
    }
    private String _parseLongName(final int i2, final int i3, final int i4) throws IOException {
        final int[] iArr = _quadBuffer;
        iArr[0] = _quad1;
        iArr[1] = i3;
        iArr[2] = i4;
        final int[] iArr2 = UTF8DataInputJsonParser._icLatin1;
        int i5 = i2;
        int i6 = 3;
        while (true) {
            final int unsignedByte = _inputData.readUnsignedByte();
            if (0 != iArr2[unsignedByte]) {
                if (34 == unsignedByte) {
                    return this.findName(_quadBuffer, i6, i5, 1);
                }
                return this.parseEscapedName(_quadBuffer, i6, i5, unsignedByte, 1);
            }
            final int i7 = (i5 << 8) | unsignedByte;
            final int unsignedByte2 = _inputData.readUnsignedByte();
            if (0 != iArr2[unsignedByte2]) {
                if (34 == unsignedByte2) {
                    return this.findName(_quadBuffer, i6, i7, 2);
                }
                return this.parseEscapedName(_quadBuffer, i6, i7, unsignedByte2, 2);
            }
            final int i8 = (i7 << 8) | unsignedByte2;
            final int unsignedByte3 = _inputData.readUnsignedByte();
            if (0 != iArr2[unsignedByte3]) {
                if (34 == unsignedByte3) {
                    return this.findName(_quadBuffer, i6, i8, 3);
                }
                return this.parseEscapedName(_quadBuffer, i6, i8, unsignedByte3, 3);
            }
            final int i9 = (i8 << 8) | unsignedByte3;
            final int unsignedByte4 = _inputData.readUnsignedByte();
            if (0 != iArr2[unsignedByte4]) {
                if (34 == unsignedByte4) {
                    return this.findName(_quadBuffer, i6, i9, 4);
                }
                return this.parseEscapedName(_quadBuffer, i6, i9, unsignedByte4, 4);
            }
            final int[] iArr3 = _quadBuffer;
            if (i6 >= iArr3.length) {
                _quadBuffer = UTF8DataInputJsonParser._growArrayBy(iArr3, i6);
            }
            _quadBuffer[i6] = i9;
            i6++;
            i5 = unsignedByte4;
        }
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
        final int[] iArr2 = UTF8DataInputJsonParser._icLatin1;
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
                            iArr = UTF8DataInputJsonParser._growArrayBy(iArr, iArr.length);
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
                                iArr = UTF8DataInputJsonParser._growArrayBy(iArr, iArr.length);
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
                    iArr = UTF8DataInputJsonParser._growArrayBy(iArr, iArr.length);
                    _quadBuffer = iArr;
                }
                iArr[i2] = i3;
                i3 = i4;
                i2++;
                i5 = 1;
            }
            i4 = _inputData.readUnsignedByte();
        }
        if (0 < i5) {
            if (i2 >= iArr.length) {
                iArr = UTF8DataInputJsonParser._growArrayBy(iArr, iArr.length);
                _quadBuffer = iArr;
            }
            iArr[i2] = UTF8DataInputJsonParser.pad(i3, i5);
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
        int[] iArr_growArrayBy = _quadBuffer;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        do {
            if (4 > i3) {
                i3++;
                i5 = i2 | (i5 << 8);
            } else {
                if (i4 >= iArr_growArrayBy.length) {
                    iArr_growArrayBy = UTF8DataInputJsonParser._growArrayBy(iArr_growArrayBy, iArr_growArrayBy.length);
                    _quadBuffer = iArr_growArrayBy;
                }
                iArr_growArrayBy[i4] = i5;
                i5 = i2;
                i4++;
                i3 = 1;
            }
            i2 = _inputData.readUnsignedByte();
        } while (0 == inputCodeUtf8JsNames[i2]);
        _nextByte = i2;
        if (0 < i3) {
            if (i4 >= iArr_growArrayBy.length) {
                iArr_growArrayBy = UTF8DataInputJsonParser._growArrayBy(iArr_growArrayBy, iArr_growArrayBy.length);
                _quadBuffer = iArr_growArrayBy;
            }
            iArr_growArrayBy[i4] = i5;
            i4++;
        }
        final String strFindName = _symbols.findName(iArr_growArrayBy, i4);
        return null == strFindName ? this.addName(iArr_growArrayBy, i4, i3) : strFindName;
    }
    protected String _parseAposName() throws IOException {
        int unsignedByte = _inputData.readUnsignedByte();
        if (39 == unsignedByte) {
            return "";
        }
        int[] iArr_growArrayBy = _quadBuffer;
        final int[] iArr = UTF8DataInputJsonParser._icLatin1;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (39 != unsignedByte) {
            if (34 != unsignedByte && 0 != iArr[unsignedByte]) {
                if (92 != unsignedByte) {
                    this._throwUnquotedSpace(unsignedByte, "name");
                } else {
                    unsignedByte = this._decodeEscaped();
                }
                if (127 < unsignedByte) {
                    if (4 <= i2) {
                        if (i3 >= iArr_growArrayBy.length) {
                            iArr_growArrayBy = UTF8DataInputJsonParser._growArrayBy(iArr_growArrayBy, iArr_growArrayBy.length);
                            _quadBuffer = iArr_growArrayBy;
                        }
                        iArr_growArrayBy[i3] = i4;
                        i4 = 0;
                        i3++;
                        i2 = 0;
                    }
                    if (2048 > unsignedByte) {
                        i4 = (i4 << 8) | (unsignedByte >> 6) | Wbxml.EXT_0;
                        i2++;
                    } else {
                        int i5 = (i4 << 8) | (unsignedByte >> 12) | 224;
                        int i6 = i2 + 1;
                        if (4 <= i6) {
                            if (i3 >= iArr_growArrayBy.length) {
                                iArr_growArrayBy = UTF8DataInputJsonParser._growArrayBy(iArr_growArrayBy, iArr_growArrayBy.length);
                                _quadBuffer = iArr_growArrayBy;
                            }
                            iArr_growArrayBy[i3] = i5;
                            i5 = 0;
                            i3++;
                            i6 = 0;
                        }
                        i4 = (i5 << 8) | ((unsignedByte >> 6) & 63) | 128;
                        i2 = i6 + 1;
                    }
                    unsignedByte = (unsignedByte & 63) | 128;
                }
            }
            if (4 > i2) {
                i2++;
                i4 = unsignedByte | (i4 << 8);
            } else {
                if (i3 >= iArr_growArrayBy.length) {
                    iArr_growArrayBy = UTF8DataInputJsonParser._growArrayBy(iArr_growArrayBy, iArr_growArrayBy.length);
                    _quadBuffer = iArr_growArrayBy;
                }
                iArr_growArrayBy[i3] = i4;
                i4 = unsignedByte;
                i3++;
                i2 = 1;
            }
            unsignedByte = _inputData.readUnsignedByte();
        }
        if (0 < i2) {
            if (i3 >= iArr_growArrayBy.length) {
                iArr_growArrayBy = UTF8DataInputJsonParser._growArrayBy(iArr_growArrayBy, iArr_growArrayBy.length);
                _quadBuffer = iArr_growArrayBy;
            }
            iArr_growArrayBy[i3] = UTF8DataInputJsonParser.pad(i4, i2);
            i3++;
        }
        final String strFindName = _symbols.findName(iArr_growArrayBy, i3);
        return null == strFindName ? this.addName(iArr_growArrayBy, i3, i2) : strFindName;
    }
    private String findName(final int i2, final int i3) throws JsonParseException {
        final int iPad = UTF8DataInputJsonParser.pad(i2, i3);
        final String strFindName = _symbols.findName(iPad);
        if (null != strFindName) {
            return strFindName;
        }
        final int[] iArr = _quadBuffer;
        iArr[0] = iPad;
        return this.addName(iArr, 1, i3);
    }
    private String findName(final int i2, final int i3, final int i4) throws JsonParseException {
        final int iPad = UTF8DataInputJsonParser.pad(i3, i4);
        final String strFindName = _symbols.findName(i2, iPad);
        if (null != strFindName) {
            return strFindName;
        }
        final int[] iArr = _quadBuffer;
        iArr[0] = i2;
        iArr[1] = iPad;
        return this.addName(iArr, 2, i4);
    }
    private String findName(final int i2, final int i3, final int i4, final int i5) throws JsonParseException {
        final int iPad = UTF8DataInputJsonParser.pad(i4, i5);
        final String strFindName = _symbols.findName(i2, i3, iPad);
        if (null != strFindName) {
            return strFindName;
        }
        final int[] iArr = _quadBuffer;
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = UTF8DataInputJsonParser.pad(iPad, i5);
        return this.addName(iArr, 3, i5);
    }
    private String findName(int[] iArr, final int i2, final int i3, final int i4) throws JsonParseException {
        if (i2 >= iArr.length) {
            iArr = UTF8DataInputJsonParser._growArrayBy(iArr, iArr.length);
            _quadBuffer = iArr;
        }
        final int i5 = i2 + 1;
        iArr[i2] = UTF8DataInputJsonParser.pad(i3, i4);
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
    protected void _finishString() throws IOException {
        final char[] cArrEmptyAndGetCurrentSegment = _textBuffer.emptyAndGetCurrentSegment();
        final int[] iArr = UTF8DataInputJsonParser._icUTF8;
        final int length = cArrEmptyAndGetCurrentSegment.length;
        int i2 = 0;
        while (true) {
            final int unsignedByte = _inputData.readUnsignedByte();
            if (0 != iArr[unsignedByte]) {
                if (34 == unsignedByte) {
                    _textBuffer.setCurrentLength(i2);
                    return;
                } else {
                    this._finishString2(cArrEmptyAndGetCurrentSegment, i2, unsignedByte);
                    return;
                }
            }
            final int i3 = i2 + 1;
            cArrEmptyAndGetCurrentSegment[i2] = (char) unsignedByte;
            if (i3 >= length) {
                this._finishString2(cArrEmptyAndGetCurrentSegment, i3, _inputData.readUnsignedByte());
                return;
            }
            i2 = i3;
        }
    }
    private String _finishAndReturnString() throws IOException {
        final char[] cArrEmptyAndGetCurrentSegment = _textBuffer.emptyAndGetCurrentSegment();
        final int[] iArr = UTF8DataInputJsonParser._icUTF8;
        final int length = cArrEmptyAndGetCurrentSegment.length;
        int i2 = 0;
        while (true) {
            final int unsignedByte = _inputData.readUnsignedByte();
            if (0 != iArr[unsignedByte]) {
                if (34 == unsignedByte) {
                    return _textBuffer.setCurrentAndReturn(i2);
                }
                this._finishString2(cArrEmptyAndGetCurrentSegment, i2, unsignedByte);
                return _textBuffer.contentsAsString();
            }
            final int i3 = i2 + 1;
            cArrEmptyAndGetCurrentSegment[i2] = (char) unsignedByte;
            if (i3 >= length) {
                this._finishString2(cArrEmptyAndGetCurrentSegment, i3, _inputData.readUnsignedByte());
                return _textBuffer.contentsAsString();
            }
            i2 = i3;
        }
    }
    private void _finishString2(char[] cArr, int i2, int i3) throws IOException {
        final int[] iArr = UTF8DataInputJsonParser._icUTF8;
        int length = cArr.length;
        while (true) {
            final int i4 = iArr[i3];
            int i5 = 0;
            if (0 == i4) {
                if (i2 >= length) {
                    cArr = _textBuffer.finishCurrentSegment();
                    length = cArr.length;
                    i2 = 0;
                }
                cArr[i2] = (char) i3;
                i3 = _inputData.readUnsignedByte();
                i2++;
            } else if (34 != i3) {
                if (1 == i4) {
                    i3 = this._decodeEscaped();
                } else if (2 == i4) {
                    i3 = this._decodeUtf8_2(i3);
                } else if (3 == i4) {
                    i3 = this._decodeUtf8_3(i3);
                } else if (4 == i4) {
                    final int i_decodeUtf8_4 = this._decodeUtf8_4(i3);
                    if (i2 >= cArr.length) {
                        cArr = _textBuffer.finishCurrentSegment();
                        length = cArr.length;
                        i2 = 0;
                    }
                    cArr[i2] = (char) ((i_decodeUtf8_4 >> 10) | 55296);
                    i3 = 56320 | (i_decodeUtf8_4 & 1023);
                    i2++;
                } else if (32 > i3) {
                    this._throwUnquotedSpace(i3, "string value");
                } else {
                    this._reportInvalidChar(i3);
                }
                if (i2 >= cArr.length) {
                    cArr = _textBuffer.finishCurrentSegment();
                    length = cArr.length;
                } else {
                    i5 = i2;
                }
                i2 = i5 + 1;
                cArr[i5] = (char) i3;
                i3 = _inputData.readUnsignedByte();
            } else {
                _textBuffer.setCurrentLength(i2);
                return;
            }
        }
    }
    protected void _skipString() throws IOException {
        _tokenIncomplete = false;
        final int[] iArr = UTF8DataInputJsonParser._icUTF8;
        while (true) {
            final int unsignedByte = _inputData.readUnsignedByte();
            final int i2 = iArr[unsignedByte];
            if (0 != i2) {
                if (34 == unsignedByte) {
                    return;
                }
                if (1 == i2) {
                    this._decodeEscaped();
                } else if (2 == i2) {
                    this._skipUtf8_2();
                } else if (3 == i2) {
                    this._skipUtf8_3();
                } else if (4 == i2) {
                    this._skipUtf8_4();
                } else if (32 > unsignedByte) {
                    this._throwUnquotedSpace(unsignedByte, "string value");
                } else {
                    this._reportInvalidChar(unsignedByte);
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
                        return this._handleInvalidNumberStart(_inputData.readUnsignedByte(), false);
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
            this._reportInvalidToken(i2, "" + ((char) i2), this._validJsonTokenList());
        }
        this._reportUnexpectedChar(i2, "expected a valid value " + this._validJsonValueList());
        return null;
    }
    protected JsonToken _handleApos() throws IOException {
        char[] cArrEmptyAndGetCurrentSegment = _textBuffer.emptyAndGetCurrentSegment();
        final int[] iArr = UTF8DataInputJsonParser._icUTF8;
        int i2 = 0;
        while (true) {
            int length = cArrEmptyAndGetCurrentSegment.length;
            if (i2 >= cArrEmptyAndGetCurrentSegment.length) {
                cArrEmptyAndGetCurrentSegment = _textBuffer.finishCurrentSegment();
                length = cArrEmptyAndGetCurrentSegment.length;
                i2 = 0;
            }
            while (true) {
                int unsignedByte = _inputData.readUnsignedByte();
                if (39 != unsignedByte) {
                    final int i3 = iArr[unsignedByte];
                    if (0 == i3) {
                        final int i4 = i2 + 1;
                        cArrEmptyAndGetCurrentSegment[i2] = (char) unsignedByte;
                        i2 = i4;
                        if (i4 >= length) {
                            break;
                        }
                    } else {
                        if (1 == i3) {
                            unsignedByte = this._decodeEscaped();
                        } else if (2 == i3) {
                            unsignedByte = this._decodeUtf8_2(unsignedByte);
                        } else if (3 == i3) {
                            unsignedByte = this._decodeUtf8_3(unsignedByte);
                        } else if (4 == i3) {
                            final int i_decodeUtf8_4 = this._decodeUtf8_4(unsignedByte);
                            final int i5 = i2 + 1;
                            cArrEmptyAndGetCurrentSegment[i2] = (char) ((i_decodeUtf8_4 >> 10) | 55296);
                            if (i5 >= cArrEmptyAndGetCurrentSegment.length) {
                                cArrEmptyAndGetCurrentSegment = _textBuffer.finishCurrentSegment();
                                i2 = 0;
                            } else {
                                i2 = i5;
                            }
                            unsignedByte = 56320 | (i_decodeUtf8_4 & 1023);
                        } else {
                            if (32 > unsignedByte) {
                                this._throwUnquotedSpace(unsignedByte, "string value");
                            }
                            this._reportInvalidChar(unsignedByte);
                        }
                        if (i2 >= cArrEmptyAndGetCurrentSegment.length) {
                            cArrEmptyAndGetCurrentSegment = _textBuffer.finishCurrentSegment();
                            i2 = 0;
                        }
                        cArrEmptyAndGetCurrentSegment[i2] = (char) unsignedByte;
                        i2++;
                    }
                } else {
                    _textBuffer.setCurrentLength(i2);
                    return JsonToken.VALUE_STRING;
                }
            }
        }
    }
    protected JsonToken _handleInvalidNumberStart(int i2, final boolean z) throws IOException {
        String str;
        while (73 == i2) {
            i2 = _inputData.readUnsignedByte();
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
            this._reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        }
        this.reportUnexpectedNumberChar(i2, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }
    protected final void _matchToken(final String str, int i2) throws IOException {
        final int length = str.length();
        do {
            final int unsignedByte = _inputData.readUnsignedByte();
            if (unsignedByte != str.charAt(i2)) {
                this._reportInvalidToken(unsignedByte, str.substring(0, i2));
            }
            i2++;
        } while (i2 < length);
        final int unsignedByte2 = _inputData.readUnsignedByte();
        if (48 <= unsignedByte2 && 93 != unsignedByte2 && 125 != unsignedByte2) {
            this._checkMatchEnd(str, i2, unsignedByte2);
        }
        _nextByte = unsignedByte2;
    }
    private void _checkMatchEnd(final String str, final int i2, final int i3) throws IOException {
        final char c_decodeCharForError = (char) this._decodeCharForError(i3);
        if (Character.isJavaIdentifierPart(c_decodeCharForError)) {
            this._reportInvalidToken(c_decodeCharForError, str.substring(0, i2));
        }
    }
    private int _skipWS() throws IOException {
        int unsignedByte = _nextByte;
        if (0 > unsignedByte) {
            unsignedByte = _inputData.readUnsignedByte();
        } else {
            _nextByte = -1;
        }
        while (32 >= unsignedByte) {
            if (13 == unsignedByte || 10 == unsignedByte) {
                _currInputRow++;
            }
            unsignedByte = _inputData.readUnsignedByte();
        }
        return (47 == unsignedByte || 35 == unsignedByte) ? this._skipWSComment(unsignedByte) : unsignedByte;
    }
    private int _skipWSOrEnd() throws IOException {
        int unsignedByte = _nextByte;
        if (0 > unsignedByte) {
            try {
                unsignedByte = _inputData.readUnsignedByte();
            } catch (final EOFException unused) {
                return this._eofAsNextChar();
            }
        } else {
            _nextByte = -1;
        }
        while (32 >= unsignedByte) {
            if (13 == unsignedByte || 10 == unsignedByte) {
                _currInputRow++;
            }
            try {
                unsignedByte = _inputData.readUnsignedByte();
            } catch (final EOFException unused2) {
                return this._eofAsNextChar();
            }
        }
        return (47 == unsignedByte || 35 == unsignedByte) ? this._skipWSComment(unsignedByte) : unsignedByte;
    }
    private int _skipWSComment(int i2) throws IOException {
        while (true) {
            if (32 < i2) {
                if (47 == i2) {
                    this._skipComment();
                } else if (35 != i2 || !this._skipYAMLComment()) {
                    break;
                }
            } else if (13 == i2 || 10 == i2) {
                _currInputRow++;
            }
            i2 = _inputData.readUnsignedByte();
        }
        return i2;
    }
    private int _skipColon() throws IOException {
        int unsignedByte = _nextByte;
        if (0 > unsignedByte) {
            unsignedByte = _inputData.readUnsignedByte();
        } else {
            _nextByte = -1;
        }
        if (58 == unsignedByte) {
            int unsignedByte2 = _inputData.readUnsignedByte();
            if (32 < unsignedByte2) {
                return (47 == unsignedByte2 || 35 == unsignedByte2) ? this._skipColon2(unsignedByte2, true) : unsignedByte2;
            }
            if ((32 == unsignedByte2 || 9 == unsignedByte2) && 32 < (unsignedByte2 = this._inputData.readUnsignedByte())) {
                return (47 == unsignedByte2 || 35 == unsignedByte2) ? this._skipColon2(unsignedByte2, true) : unsignedByte2;
            }
            return this._skipColon2(unsignedByte2, true);
        }
        if (32 == unsignedByte || 9 == unsignedByte) {
            unsignedByte = _inputData.readUnsignedByte();
        }
        if (58 == unsignedByte) {
            int unsignedByte3 = _inputData.readUnsignedByte();
            if (32 < unsignedByte3) {
                return (47 == unsignedByte3 || 35 == unsignedByte3) ? this._skipColon2(unsignedByte3, true) : unsignedByte3;
            }
            if ((32 == unsignedByte3 || 9 == unsignedByte3) && 32 < (unsignedByte3 = this._inputData.readUnsignedByte())) {
                return (47 == unsignedByte3 || 35 == unsignedByte3) ? this._skipColon2(unsignedByte3, true) : unsignedByte3;
            }
            return this._skipColon2(unsignedByte3, true);
        }
        return this._skipColon2(unsignedByte, false);
    }
    private int _skipColon2(int i2, boolean z) throws IOException {
        while (true) {
            if (32 < i2) {
                if (47 == i2) {
                    this._skipComment();
                } else if (35 != i2 || !this._skipYAMLComment()) {
                    if (z) {
                        return i2;
                    }
                    if (58 != i2) {
                        this._reportUnexpectedChar(i2, "was expecting a colon to separate field name and value");
                    }
                    z = true;
                }
            } else if (13 == i2 || 10 == i2) {
                _currInputRow++;
            }
            i2 = _inputData.readUnsignedByte();
        }
    }
    private void _skipComment() throws IOException {
        if (0 == (this._features & FEAT_MASK_ALLOW_JAVA_COMMENTS)) {
            this._reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        final int unsignedByte = _inputData.readUnsignedByte();
        if (47 == unsignedByte) {
            this._skipLine();
        } else if (42 == unsignedByte) {
            this._skipCComment();
        } else {
            this._reportUnexpectedChar(unsignedByte, "was expecting either '*' or '/' for a comment");
        }
    }
    private void _skipCComment() throws IOException {
        final int[] inputCodeComment = CharTypes.getInputCodeComment();
        int unsignedByte = _inputData.readUnsignedByte();
        while (true) {
            final int i2 = inputCodeComment[unsignedByte];
            if (0 != i2) {
                if (2 == i2) {
                    this._skipUtf8_2();
                } else if (3 == i2) {
                    this._skipUtf8_3();
                } else if (4 == i2) {
                    this._skipUtf8_4();
                } else if (10 == i2 || 13 == i2) {
                    _currInputRow++;
                } else if (42 == i2) {
                    unsignedByte = _inputData.readUnsignedByte();
                    if (47 == unsignedByte) {
                        return;
                    }
                } else {
                    this._reportInvalidChar(unsignedByte);
                }
            }
            unsignedByte = _inputData.readUnsignedByte();
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
            final int unsignedByte = _inputData.readUnsignedByte();
            final int i2 = inputCodeComment[unsignedByte];
            if (0 != i2) {
                if (2 == i2) {
                    this._skipUtf8_2();
                } else if (3 == i2) {
                    this._skipUtf8_3();
                } else if (4 == i2) {
                    this._skipUtf8_4();
                } else if (10 == i2 || 13 == i2) {
                    break;
                } else if (42 != i2 && 0 > i2) {
                    this._reportInvalidChar(unsignedByte);
                }
            }
        }
        _currInputRow++;
    }
    protected char _decodeEscaped() throws IOException {
        final int unsignedByte = _inputData.readUnsignedByte();
        if (34 == unsignedByte || 47 == unsignedByte || 92 == unsignedByte) {
            return (char) unsignedByte;
        }
        if (98 == unsignedByte) {
            return '\b';
        }
        if (102 == unsignedByte) {
            return '\f';
        }
        if (110 == unsignedByte) {
            return '\n';
        }
        if (114 == unsignedByte) {
            return '\r';
        }
        if (116 == unsignedByte) {
            return '\t';
        }
        if (117 != unsignedByte) {
            return this._handleUnrecognizedCharacterEscape((char) this._decodeCharForError(unsignedByte));
        }
        int i2 = 0;
        for (int i3 = 0; 4 > i3; i3++) {
            final int unsignedByte2 = _inputData.readUnsignedByte();
            final int iCharToHex = CharTypes.charToHex(unsignedByte2);
            if (0 > iCharToHex) {
                this._reportUnexpectedChar(unsignedByte2, "expected a hex-digit for character escape sequence");
            }
            i2 = (i2 << 4) | iCharToHex;
        }
        return (char) i2;
    }
    protected int _decodeCharForError(final int i2) throws IOException {
        char c2 = 0;
        final int unsignedByte;
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
            unsignedByte = _inputData.readUnsignedByte();
            if (128 != (unsignedByte & Wbxml.EXT_0)) {
                this._reportInvalidOther(unsignedByte & 255);
            }
            final int i4 = (i3 << 6) | (unsignedByte & 63);
            if (1 < c2) {
                return i4;
            }
            final int unsignedByte2 = _inputData.readUnsignedByte();
            if (128 != (unsignedByte2 & Wbxml.EXT_0)) {
                this._reportInvalidOther(unsignedByte2 & 255);
            }
            final int i5 = (i4 << 6) | (unsignedByte2 & 63);
            if (2 >= c2) {
                return i5;
            }
            final int unsignedByte3 = _inputData.readUnsignedByte();
            if (128 != (unsignedByte3 & Wbxml.EXT_0)) {
                this._reportInvalidOther(unsignedByte3 & 255);
            }
            return (i5 << 6) | (unsignedByte3 & 63);
        }
        i3 = i2 & 31;
        c2 = 1;
        unsignedByte = _inputData.readUnsignedByte();
        if (128 != (unsignedByte & Wbxml.EXT_0)) {
        }
        final int i42 = (i3 << 6) | (unsignedByte & 63);
        if (1 < c2) {
        }
        return unsignedByte;
    }
    private int _decodeUtf8_2(final int i2) throws IOException {
        final int unsignedByte = _inputData.readUnsignedByte();
        if (128 != (unsignedByte & Wbxml.EXT_0)) {
            this._reportInvalidOther(unsignedByte & 255);
        }
        return ((i2 & 31) << 6) | (unsignedByte & 63);
    }
    private int _decodeUtf8_3(final int i2) throws IOException {
        final int i3 = i2 & 15;
        final int unsignedByte = _inputData.readUnsignedByte();
        if (128 != (unsignedByte & Wbxml.EXT_0)) {
            this._reportInvalidOther(unsignedByte & 255);
        }
        final int i4 = (i3 << 6) | (unsignedByte & 63);
        final int unsignedByte2 = _inputData.readUnsignedByte();
        if (128 != (unsignedByte2 & Wbxml.EXT_0)) {
            this._reportInvalidOther(unsignedByte2 & 255);
        }
        return (i4 << 6) | (unsignedByte2 & 63);
    }
    private int _decodeUtf8_4(final int i2) throws IOException {
        final int unsignedByte = _inputData.readUnsignedByte();
        if (128 != (unsignedByte & Wbxml.EXT_0)) {
            this._reportInvalidOther(unsignedByte & 255);
        }
        final int i3 = ((i2 & 7) << 6) | (unsignedByte & 63);
        final int unsignedByte2 = _inputData.readUnsignedByte();
        if (128 != (unsignedByte2 & Wbxml.EXT_0)) {
            this._reportInvalidOther(unsignedByte2 & 255);
        }
        final int i4 = (i3 << 6) | (unsignedByte2 & 63);
        final int unsignedByte3 = _inputData.readUnsignedByte();
        if (128 != (unsignedByte3 & Wbxml.EXT_0)) {
            this._reportInvalidOther(unsignedByte3 & 255);
        }
        return ((i4 << 6) | (unsignedByte3 & 63)) - 65536;
    }
    private void _skipUtf8_2() throws IOException {
        final int unsignedByte = _inputData.readUnsignedByte();
        if (128 != (unsignedByte & Wbxml.EXT_0)) {
            this._reportInvalidOther(unsignedByte & 255);
        }
    }
    private void _skipUtf8_3() throws IOException {
        final int unsignedByte = _inputData.readUnsignedByte();
        if (128 != (unsignedByte & Wbxml.EXT_0)) {
            this._reportInvalidOther(unsignedByte & 255);
        }
        final int unsignedByte2 = _inputData.readUnsignedByte();
        if (128 != (unsignedByte2 & Wbxml.EXT_0)) {
            this._reportInvalidOther(unsignedByte2 & 255);
        }
    }
    private void _skipUtf8_4() throws IOException {
        final int unsignedByte = _inputData.readUnsignedByte();
        if (128 != (unsignedByte & Wbxml.EXT_0)) {
            this._reportInvalidOther(unsignedByte & 255);
        }
        final int unsignedByte2 = _inputData.readUnsignedByte();
        if (128 != (unsignedByte2 & Wbxml.EXT_0)) {
            this._reportInvalidOther(unsignedByte2 & 255);
        }
        final int unsignedByte3 = _inputData.readUnsignedByte();
        if (128 != (unsignedByte3 & Wbxml.EXT_0)) {
            this._reportInvalidOther(unsignedByte3 & 255);
        }
    }
    protected void _reportInvalidToken(final int i2, final String str) throws IOException {
        this._reportInvalidToken(i2, str, this._validJsonTokenList());
    }
    protected void _reportInvalidToken(int i2, final String str, final String str2) throws IOException {
        final StringBuilder sb = new StringBuilder(str);
        while (true) {
            final char c_decodeCharForError = (char) this._decodeCharForError(i2);
            if (Character.isJavaIdentifierPart(c_decodeCharForError)) {
                sb.append(c_decodeCharForError);
                i2 = _inputData.readUnsignedByte();
            } else {
                this._reportError("Unrecognized token '" + sb + "': was expecting " + str2);
                return;
            }
        }
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
    private void _reportInvalidOther(final int i2) throws JsonParseException {
        this._reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i2));
    }
    private static int[] _growArrayBy(final int[] iArr, final int i2) {
        if (null == iArr) {
            return new int[i2];
        }
        return Arrays.copyOf(iArr, iArr.length + i2);
    }
    protected final byte[] _decodeBase64(final Base64Variant base64Variant) throws IOException {
        final ByteArrayBuilder byteArrayBuilder_getByteArrayBuilder = this._getByteArrayBuilder();
        while (true) {
            final int unsignedByte = _inputData.readUnsignedByte();
            if (32 < unsignedByte) {
                int iDecodeBase64Char = base64Variant.decodeBase64Char(unsignedByte);
                if (0 > iDecodeBase64Char) {
                    if (34 == unsignedByte) {
                        return byteArrayBuilder_getByteArrayBuilder.toByteArray();
                    }
                    iDecodeBase64Char = this._decodeBase64Escape(base64Variant, unsignedByte, 0);
                    if (0 > iDecodeBase64Char) {
                        continue;
                    }
                }
                final int unsignedByte2 = _inputData.readUnsignedByte();
                int iDecodeBase64Char2 = base64Variant.decodeBase64Char(unsignedByte2);
                if (0 > iDecodeBase64Char2) {
                    iDecodeBase64Char2 = this._decodeBase64Escape(base64Variant, unsignedByte2, 1);
                }
                final int i2 = (iDecodeBase64Char << 6) | iDecodeBase64Char2;
                final int unsignedByte3 = _inputData.readUnsignedByte();
                int iDecodeBase64Char3 = base64Variant.decodeBase64Char(unsignedByte3);
                if (0 > iDecodeBase64Char3) {
                    if (-2 != iDecodeBase64Char3) {
                        if (34 == unsignedByte3) {
                            byteArrayBuilder_getByteArrayBuilder.append(i2 >> 4);
                            if (base64Variant.usesPadding()) {
                                this._handleBase64MissingPadding(base64Variant);
                            }
                            return byteArrayBuilder_getByteArrayBuilder.toByteArray();
                        }
                        iDecodeBase64Char3 = this._decodeBase64Escape(base64Variant, unsignedByte3, 2);
                    }
                    if (-2 == iDecodeBase64Char3) {
                        final int unsignedByte4 = _inputData.readUnsignedByte();
                        if (!base64Variant.usesPaddingChar(unsignedByte4) && (92 != unsignedByte4 || -2 != _decodeBase64Escape(base64Variant, unsignedByte4, 3))) {
                            break;
                        }
                        byteArrayBuilder_getByteArrayBuilder.append(i2 >> 4);
                    }
                }
                final int i3 = (i2 << 6) | iDecodeBase64Char3;
                final int unsignedByte5 = _inputData.readUnsignedByte();
                int iDecodeBase64Char4 = base64Variant.decodeBase64Char(unsignedByte5);
                if (0 > iDecodeBase64Char4) {
                    if (-2 != iDecodeBase64Char4) {
                        if (34 == unsignedByte5) {
                            byteArrayBuilder_getByteArrayBuilder.appendTwoBytes(i3 >> 2);
                            if (base64Variant.usesPadding()) {
                                this._handleBase64MissingPadding(base64Variant);
                            }
                            return byteArrayBuilder_getByteArrayBuilder.toByteArray();
                        }
                        iDecodeBase64Char4 = this._decodeBase64Escape(base64Variant, unsignedByte5, 3);
                    }
                    if (-2 == iDecodeBase64Char4) {
                        byteArrayBuilder_getByteArrayBuilder.appendTwoBytes(i3 >> 2);
                    }
                }
                byteArrayBuilder_getByteArrayBuilder.appendThreeBytes((i3 << 6) | iDecodeBase64Char4);
            }
        }
        return new byte[0];
    }
    public JsonLocation getTokenLocation() {
        return new JsonLocation(this._getSourceReference(), -1L, -1L, _tokenInputRow, -1);
    }
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._getSourceReference(), -1L, -1L, _currInputRow, -1);
    }
    private void _closeScope(final int i2) throws JsonParseException {
        if (93 == i2) {
            if (!_parsingContext.inArray()) {
                this._reportMismatchedEndMarker(93, '}');
            }
            _parsingContext = _parsingContext.clearAndGetParent();
            _currToken = JsonToken.END_ARRAY;
        }
        if (125 == i2) {
            if (!_parsingContext.inObject()) {
                this._reportMismatchedEndMarker(125, ']');
            }
            _parsingContext = _parsingContext.clearAndGetParent();
            _currToken = JsonToken.END_OBJECT;
        }
    }
}
