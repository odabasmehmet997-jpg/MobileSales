package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.core.util.TextBuffer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public abstract class ParserBase extends ParserMinimalBase {
    protected static final JacksonFeatureSet<StreamReadCapability> JSON_READ_CAPABILITIES = JsonParser.DEFAULT_READ_CAPABILITIES;
    protected byte[] _binaryValue;
    protected ByteArrayBuilder _byteArrayBuilder;
    protected boolean _closed;
    protected long _currInputProcessed;
    protected int _currInputRow;
    protected int _currInputRowStart;
    protected int _expLength;
    protected int _fractLength;
    protected int _inputEnd;
    protected int _inputPtr;
    protected int _intLength;
    protected final IOContext _ioContext;
    protected boolean _nameCopied;
    protected char[] _nameCopyBuffer;
    protected JsonToken _nextToken;
    protected int _numTypesValid;
    protected BigDecimal _numberBigDecimal;
    protected BigInteger _numberBigInt;
    protected double _numberDouble;
    protected int _numberInt;
    protected long _numberLong;
    protected boolean _numberNegative;
    protected JsonReadContext _parsingContext;
    protected final TextBuffer _textBuffer;
    protected int _tokenInputCol;
    protected int _tokenInputRow;
    protected long _tokenInputTotal;
    protected abstract void _closeInput() throws IOException;
    protected ParserBase(IOContext iOContext, int i2) {
        super(i2);
        this._currInputRow = 1;
        this._tokenInputRow = 1;
        this._numTypesValid = 0;
        this._ioContext = iOContext;
        this._textBuffer = iOContext.constructTextBuffer();
        this._parsingContext = JsonReadContext.createRootContext(JsonParser.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i2) ? DupDetector.rootDetector(this) : null);
    }
    public void setCurrentValue(Object obj) {
        this._parsingContext.setCurrentValue(obj);
    }
    public JsonParser enable(JsonParser.Feature feature) {
        this._features |= feature.getMask();
        if (feature == JsonParser.Feature.STRICT_DUPLICATE_DETECTION && this._parsingContext.getDupDetector() == null) {
            this._parsingContext = this._parsingContext.withDupDetector(DupDetector.rootDetector(this));
        }
        return this;
    }
    public JsonParser setFeatureMask(int i2) {
        int i3 = this._features ^ i2;
        if (i3 != 0) {
            this._features = i2;
            _checkStdFeatureChanges(i2, i3);
        }
        return this;
    }
    public JsonParser overrideStdFeatures(int i2, int i3) {
        int i4 = this._features;
        int i5 = (i2 & i3) | ((~i3) & i4);
        int i6 = i4 ^ i5;
        if (i6 != 0) {
            this._features = i5;
            _checkStdFeatureChanges(i5, i6);
        }
        return this;
    }
    protected void _checkStdFeatureChanges(int i2, int i3) {
        int mask = JsonParser.Feature.STRICT_DUPLICATE_DETECTION.getMask();
        if ((i3 & mask) == 0 || (i2 & mask) == 0) {
            return;
        }
        if (this._parsingContext.getDupDetector() == null) {
            this._parsingContext = this._parsingContext.withDupDetector(DupDetector.rootDetector(this));
        } else {
            this._parsingContext = this._parsingContext.withDupDetector(null);
        }
    }
    public String getCurrentName() throws IOException {
        JsonReadContext parent;
        JsonToken jsonToken = this._currToken;
        if ((jsonToken == JsonToken.START_OBJECT || jsonToken == JsonToken.START_ARRAY) && (parent = this._parsingContext.getParent()) != null) {
            return parent.getCurrentName();
        }
        return this._parsingContext.getCurrentName();
    }
    public void close() throws IOException {
        if (this._closed) {
            return;
        }
        this._inputPtr = Math.max(this._inputPtr, this._inputEnd);
        this._closed = true;
        try {
            _closeInput();
        } finally {
            _releaseBuffers();
        }
    }
    public JsonReadContext getParsingContext() {
        return this._parsingContext;
    }
    public JsonLocation getTokenLocation() {
        return new JsonLocation(_getSourceReference(), -1L, getTokenCharacterOffset(), getTokenLineNr(), getTokenColumnNr());
    }
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(_getSourceReference(), -1L, this._inputPtr + this._currInputProcessed, this._currInputRow, (this._inputPtr - this._currInputRowStart) + 1);
    }
    public boolean hasTextCharacters() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return true;
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return this._nameCopied;
        }
        return false;
    }
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        if (this._binaryValue == null) {
            if (this._currToken != JsonToken.VALUE_STRING) {
                _reportError("Current token (" + this._currToken + ") not VALUE_STRING, can not access as binary");
            }
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }
    public long getTokenCharacterOffset() {
        return this._tokenInputTotal;
    }
    public int getTokenLineNr() {
        return this._tokenInputRow;
    }
    public int getTokenColumnNr() {
        int i2 = this._tokenInputCol;
        return i2 < 0 ? i2 : i2 + 1;
    }
    protected void _releaseBuffers() throws IOException {
        this._textBuffer.releaseBuffers();
        char[] cArr = this._nameCopyBuffer;
        if (cArr != null) {
            this._nameCopyBuffer = null;
            this._ioContext.releaseNameCopyBuffer(cArr);
        }
    }
    protected void _handleEOF() throws JsonParseException {
        if (this._parsingContext.inRoot()) {
            return;
        }
        _reportInvalidEOF(String.format(": expected close marker for %s (start marker at %s)", this._parsingContext.inArray() ? "Array" : "Object", this._parsingContext.getStartLocation(_getSourceReference())), null);
    }
    protected final int _eofAsNextChar() throws JsonParseException {
        _handleEOF();
        return -1;
    }
    public ByteArrayBuilder _getByteArrayBuilder() {
        ByteArrayBuilder byteArrayBuilder = this._byteArrayBuilder;
        if (byteArrayBuilder == null) {
            this._byteArrayBuilder = new ByteArrayBuilder();
        } else {
            byteArrayBuilder.reset();
        }
        return this._byteArrayBuilder;
    }
    protected final JsonToken reset(boolean z, int i2, int i3, int i4) {
        if (i3 < 1 && i4 < 1) {
            return resetInt(z, i2);
        }
        return resetFloat(z, i2, i3, i4);
    }
    protected final JsonToken resetInt(boolean z, int i2) {
        this._numberNegative = z;
        this._intLength = i2;
        this._fractLength = 0;
        this._expLength = 0;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_INT;
    }
    protected final JsonToken resetFloat(boolean z, int i2, int i3, int i4) {
        this._numberNegative = z;
        this._intLength = i2;
        this._fractLength = i3;
        this._expLength = i4;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }
    protected final JsonToken resetAsNaN(String str, double d2) {
        this._textBuffer.resetWithString(str);
        this._numberDouble = d2;
        this._numTypesValid = 8;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }
    public boolean isNaN() {
        if (this._currToken != JsonToken.VALUE_NUMBER_FLOAT || (this._numTypesValid & 8) == 0) {
            return false;
        }
        double d2 = this._numberDouble;
        return Double.isNaN(d2) || Double.isInfinite(d2);
    }
    public Number getNumberValue() throws IOException {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            int i2 = this._numTypesValid;
            if ((i2 & 1) != 0) {
                return Integer.valueOf(this._numberInt);
            }
            if ((i2 & 2) != 0) {
                return Long.valueOf(this._numberLong);
            }
            if ((i2 & 4) != 0) {
                return this._numberBigInt;
            }
            _throwInternal();
        }
        int i3 = this._numTypesValid;
        if ((i3 & 16) != 0) {
            return this._numberBigDecimal;
        }
        if ((i3 & 8) == 0) {
            _throwInternal();
        }
        return Double.valueOf(this._numberDouble);
    }
    public Number getNumberValueExact() throws IOException {
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(0);
            }
            int i2 = this._numTypesValid;
            if ((i2 & 1) != 0) {
                return Integer.valueOf(this._numberInt);
            }
            if ((i2 & 2) != 0) {
                return Long.valueOf(this._numberLong);
            }
            if ((i2 & 4) != 0) {
                return this._numberBigInt;
            }
            _throwInternal();
        }
        if (this._numTypesValid == 0) {
            _parseNumericValue(16);
        }
        int i3 = this._numTypesValid;
        if ((i3 & 16) != 0) {
            return this._numberBigDecimal;
        }
        if ((i3 & 8) == 0) {
            _throwInternal();
        }
        return Double.valueOf(this._numberDouble);
    }
    public JsonParser.NumberType getNumberType() throws IOException {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            int i2 = this._numTypesValid;
            if ((i2 & 1) != 0) {
                return JsonParser.NumberType.INT;
            }
            if ((i2 & 2) != 0) {
                return JsonParser.NumberType.LONG;
            }
            return JsonParser.NumberType.BIG_INTEGER;
        }
        if ((this._numTypesValid & 16) != 0) {
            return JsonParser.NumberType.BIG_DECIMAL;
        }
        return JsonParser.NumberType.DOUBLE;
    }
    public int getIntValue() throws IOException {
        int i2 = this._numTypesValid;
        if ((i2 & 1) == 0) {
            if (i2 == 0) {
                return _parseIntValue();
            }
            if ((i2 & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }
    public long getLongValue() throws IOException {
        int i2 = this._numTypesValid;
        if ((i2 & 2) == 0) {
            if (i2 == 0) {
                _parseNumericValue(2);
            }
            if ((this._numTypesValid & 2) == 0) {
                convertNumberToLong();
            }
        }
        return this._numberLong;
    }
    public BigInteger getBigIntegerValue() throws IOException {
        int i2 = this._numTypesValid;
        if ((i2 & 4) == 0) {
            if (i2 == 0) {
                _parseNumericValue(4);
            }
            if ((this._numTypesValid & 4) == 0) {
                convertNumberToBigInteger();
            }
        }
        return this._numberBigInt;
    }
    public float getFloatValue() throws IOException {
        return (float) getDoubleValue();
    }
    public double getDoubleValue() throws IOException {
        int i2 = this._numTypesValid;
        if ((i2 & 8) == 0) {
            if (i2 == 0) {
                _parseNumericValue(8);
            }
            if ((this._numTypesValid & 8) == 0) {
                convertNumberToDouble();
            }
        }
        return this._numberDouble;
    }
    public BigDecimal getDecimalValue() throws IOException {
        int i2 = this._numTypesValid;
        if ((i2 & 16) == 0) {
            if (i2 == 0) {
                _parseNumericValue(16);
            }
            if ((this._numTypesValid & 16) == 0) {
                convertNumberToBigDecimal();
            }
        }
        return this._numberBigDecimal;
    }
    protected void _parseNumericValue(int i2) throws IOException {
        if (this._closed) {
            _reportError("Internal error: _parseNumericValue called when parser instance closed");
        }
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            int i3 = this._intLength;
            if (i3 <= 9) {
                this._numberInt = this._textBuffer.contentsAsInt(this._numberNegative);
                this._numTypesValid = 1;
                return;
            }
            if (i3 <= 18) {
                long contentsAsLong = this._textBuffer.contentsAsLong(this._numberNegative);
                if (i3 == 10) {
                    if (this._numberNegative) {
                        if (contentsAsLong >= -2147483648L) {
                            this._numberInt = (int) contentsAsLong;
                            this._numTypesValid = 1;
                            return;
                        }
                    } else if (contentsAsLong <= 2147483647L) {
                        this._numberInt = (int) contentsAsLong;
                        this._numTypesValid = 1;
                        return;
                    }
                }
                this._numberLong = contentsAsLong;
                this._numTypesValid = 2;
                return;
            }
            _parseSlowInt(i2);
            return;
        }
        if (jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            _parseSlowFloat(i2);
        } else {
            _reportError("Current token (%s) not numeric, can not use numeric value accessors", jsonToken);
        }
    }
    protected int _parseIntValue() throws IOException {
        if (this._closed) {
            _reportError("Internal error: _parseNumericValue called when parser instance closed");
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT && this._intLength <= 9) {
            int contentsAsInt = this._textBuffer.contentsAsInt(this._numberNegative);
            this._numberInt = contentsAsInt;
            this._numTypesValid = 1;
            return contentsAsInt;
        }
        _parseNumericValue(1);
        if ((this._numTypesValid & 1) == 0) {
            convertNumberToInt();
        }
        return this._numberInt;
    }
    private void _parseSlowFloat(int i2) throws IOException {
        try {
            if (i2 == 16) {
                this._numberBigDecimal = this._textBuffer.contentsAsDecimal();
                this._numTypesValid = 16;
            } else {
                this._numberDouble = this._textBuffer.contentsAsDouble();
                this._numTypesValid = 8;
            }
        } catch (NumberFormatException e2) {
            _wrapError("Malformed numeric value (" + _longNumberDesc(this._textBuffer.contentsAsString()) + ")", e2);
        }
    }
    private void _parseSlowInt(int i2) throws IOException {
        String contentsAsString = this._textBuffer.contentsAsString();
        try {
            int i3 = this._intLength;
            char[] textBuffer = this._textBuffer.getTextBuffer();
            int textOffset = this._textBuffer.getTextOffset();
            boolean z = this._numberNegative;
            if (z) {
                textOffset++;
            }
            if (NumberInput.inLongRange(textBuffer, textOffset, i3, z)) {
                this._numberLong = Long.parseLong(contentsAsString);
                this._numTypesValid = 2;
                return;
            }
            if (i2 == 1 || i2 == 2) {
                _reportTooLongIntegral(i2, contentsAsString);
            }
            if (i2 != 8 && i2 != 32) {
                this._numberBigInt = new BigInteger(contentsAsString);
                this._numTypesValid = 4;
                return;
            }
            this._numberDouble = NumberInput.parseDouble(contentsAsString);
            this._numTypesValid = 8;
        } catch (NumberFormatException e2) {
            _wrapError("Malformed numeric value (" + _longNumberDesc(contentsAsString) + ")", e2);
        }
    }
    protected void _reportTooLongIntegral(int i2, String str) throws IOException {
        if (i2 == 1) {
            reportOverflowInt(str);
        } else {
            reportOverflowLong(str);
        }
    }
    protected void convertNumberToInt() throws IOException {
        int i2 = this._numTypesValid;
        if ((i2 & 2) != 0) {
            long j2 = this._numberLong;
            if (j2 < Integer.MIN_VALUE || j2 > Integer.MAX_VALUE) {
                reportOverflowInt(getText(), currentToken());
            }
            this._numberInt = (int) j2;
        } else if ((i2 & 4) != 0) {
            if (ParserMinimalBase.BI_MIN_INT.compareTo(this._numberBigInt) > 0 || ParserMinimalBase.BI_MAX_INT.compareTo(this._numberBigInt) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigInt.intValue();
        } else if ((i2 & 8) != 0) {
            double d2 = this._numberDouble;
            if (d2 < -2.147483648E9d || d2 > 2.147483647E9d) {
                reportOverflowInt();
            }
            this._numberInt = (int) d2;
        } else if ((i2 & 16) != 0) {
            if (ParserMinimalBase.BD_MIN_INT.compareTo(this._numberBigDecimal) > 0 || ParserMinimalBase.BD_MAX_INT.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigDecimal.intValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 1;
    }
    protected void convertNumberToLong() throws IOException {
        int i2 = this._numTypesValid;
        if ((i2 & 1) != 0) {
            this._numberLong = this._numberInt;
        } else if ((i2 & 4) != 0) {
            if (ParserMinimalBase.BI_MIN_LONG.compareTo(this._numberBigInt) > 0 || ParserMinimalBase.BI_MAX_LONG.compareTo(this._numberBigInt) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigInt.longValue();
        } else if ((i2 & 8) != 0) {
            double d2 = this._numberDouble;
            if (d2 < -9.223372036854776E18d || d2 > 9.223372036854776E18d) {
                reportOverflowLong();
            }
            this._numberLong = (long) this._numberDouble;
        } else if ((i2 & 16) != 0) {
            if (ParserMinimalBase.BD_MIN_LONG.compareTo(this._numberBigDecimal) > 0 || ParserMinimalBase.BD_MAX_LONG.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigDecimal.longValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 2;
    }
    protected void convertNumberToBigInteger() throws IOException {
        int i2 = this._numTypesValid;
        if ((i2 & 16) != 0) {
            this._numberBigInt = this._numberBigDecimal.toBigInteger();
        } else if ((i2 & 2) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberLong);
        } else if ((i2 & 1) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberInt);
        } else if ((i2 & 8) != 0) {
            this._numberBigInt = BigDecimal.valueOf(this._numberDouble).toBigInteger();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 4;
    }
    protected void convertNumberToDouble() throws IOException {
        int i2 = this._numTypesValid;
        if ((i2 & 16) != 0) {
            this._numberDouble = this._numberBigDecimal.doubleValue();
        } else if ((i2 & 4) != 0) {
            this._numberDouble = this._numberBigInt.doubleValue();
        } else if ((i2 & 2) != 0) {
            this._numberDouble = this._numberLong;
        } else if ((i2 & 1) != 0) {
            this._numberDouble = this._numberInt;
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 8;
    }
    protected void convertNumberToBigDecimal() throws IOException {
        int i2 = this._numTypesValid;
        if ((i2 & 8) != 0) {
            this._numberBigDecimal = NumberInput.parseBigDecimal(getText());
        } else if ((i2 & 4) != 0) {
            this._numberBigDecimal = new BigDecimal(this._numberBigInt);
        } else if ((i2 & 2) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberLong);
        } else if ((i2 & 1) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberInt);
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 16;
    }
    protected void _reportMismatchedEndMarker(int i2, char c2) throws JsonParseException {
        JsonReadContext parsingContext = getParsingContext();
        _reportError(String.format("Unexpected close marker '%s': expected '%c' (for %s starting at %s)", Character.valueOf((char) i2), Character.valueOf(c2), parsingContext.typeDesc(), parsingContext.getStartLocation(_getSourceReference())));
    }
    protected char _handleUnrecognizedCharacterEscape(char c2) throws JsonProcessingException {
        if (isEnabled(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {
            return c2;
        }
        if (c2 == '\'' && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return c2;
        }
        _reportError("Unrecognized character escape " + ParserMinimalBase._getCharDesc(c2));
        return c2;
    }
    protected void _throwUnquotedSpace(int i2, String str) throws JsonParseException {
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i2 > 32) {
            _reportError("Illegal unquoted character (" + ParserMinimalBase._getCharDesc((char) i2) + "): has to be escaped using backslash to be included in " + str);
        }
    }
    protected String _validJsonTokenList() throws IOException {
        return _validJsonValueList();
    }
    protected String _validJsonValueList() throws IOException {
        if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            return "(JSON String, Number (or 'NaN'/'INF'/'+INF'), Array, Object or token 'null', 'true' or 'false')";
        }
        return "(JSON String, Number, Array, Object or token 'null', 'true' or 'false')";
    }
    protected char _decodeEscaped() throws IOException {
        throw new UnsupportedOperationException();
    }
    protected final int _decodeBase64Escape(Base64Variant base64Variant, int i2, int i3) throws IOException {
        if (i2 != 92) {
            throw reportInvalidBase64Char(base64Variant, i2, i3);
        }
        char _decodeEscaped = _decodeEscaped();
        if (_decodeEscaped <= ' ' && i3 == 0) {
            return -1;
        }
        int decodeBase64Char = base64Variant.decodeBase64Char((int) _decodeEscaped);
        if (decodeBase64Char >= 0 || decodeBase64Char == -2) {
            return decodeBase64Char;
        }
        throw reportInvalidBase64Char(base64Variant, _decodeEscaped, i3);
    }
    protected final int _decodeBase64Escape(Base64Variant base64Variant, char c2, int i2) throws IOException {
        if (c2 != '\\') {
            throw reportInvalidBase64Char(base64Variant, c2, i2);
        }
        char _decodeEscaped = _decodeEscaped();
        if (_decodeEscaped <= ' ' && i2 == 0) {
            return -1;
        }
        int decodeBase64Char = base64Variant.decodeBase64Char(_decodeEscaped);
        if (decodeBase64Char >= 0 || (decodeBase64Char == -2 && i2 >= 2)) {
            return decodeBase64Char;
        }
        throw reportInvalidBase64Char(base64Variant, _decodeEscaped, i2);
    }
    protected IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i2, int i3) throws IllegalArgumentException {
        return reportInvalidBase64Char(base64Variant, i2, i3, null);
    }
    protected IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i2, int i3, String str) throws IllegalArgumentException {
        String str2;
        if (i2 <= 32) {
            str2 = String.format("Illegal white space character (code 0x%s) as character #%d of 4-char base64 unit: can only used between units", Integer.toHexString(i2), Integer.valueOf(i3 + 1));
        } else if (base64Variant.usesPaddingChar(i2)) {
            str2 = "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (i3 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(i2) || Character.isISOControl(i2)) {
            str2 = "Illegal character (code 0x" + Integer.toHexString(i2) + ") in base64 content";
        } else {
            str2 = "Illegal character '" + ((char) i2) + "' (code 0x" + Integer.toHexString(i2) + ") in base64 content";
        }
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        return new IllegalArgumentException(str2);
    }
    protected void _handleBase64MissingPadding(Base64Variant base64Variant) throws IOException {
        _reportError(base64Variant.missingPaddingMessage());
    }
    protected Object _getSourceReference() {
        if (JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION.enabledIn(this._features)) {
            return this._ioContext.getSourceReference();
        }
        return null;
    }
    protected static int[] growArrayBy(int[] iArr, int i2) {
        if (iArr == null) {
            return new int[i2];
        }
        return Arrays.copyOf(iArr, iArr.length + i2);
    }
}
