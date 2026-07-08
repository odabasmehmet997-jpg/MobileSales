package com.fasterxml.jackson.core.base;

import androidx.core.location.LocationRequestCompat;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
 
public abstract class ParserMinimalBase extends JsonParser {
    protected static final BigDecimal BD_MAX_INT;
    protected static final BigDecimal BD_MAX_LONG;
    protected static final BigDecimal BD_MIN_INT;
    protected static final BigDecimal BD_MIN_LONG;
    protected static final BigInteger BI_MAX_INT;
    protected static final BigInteger BI_MAX_LONG;
    protected static final BigInteger BI_MIN_INT;
    protected static final BigInteger BI_MIN_LONG;
    protected static final byte[] NO_BYTES = new byte[0];
    protected static final int[] NO_INTS = new int[0];
    public JsonToken _currToken;
    protected JsonToken _lastClearedToken;
    protected abstract void _handleEOF() throws JsonParseException;
    public abstract String getCurrentName() throws IOException;
    public abstract String getText() throws IOException;
    public abstract JsonToken nextToken() throws IOException;
    static {
        final BigInteger bigIntegerValueOf = BigInteger.valueOf(-2147483648L);
        BI_MIN_INT = bigIntegerValueOf;
        final BigInteger bigIntegerValueOf2 = BigInteger.valueOf(2147483647L);
        BI_MAX_INT = bigIntegerValueOf2;
        final BigInteger bigIntegerValueOf3 = BigInteger.valueOf(Long.MIN_VALUE);
        BI_MIN_LONG = bigIntegerValueOf3;
        final BigInteger bigIntegerValueOf4 = BigInteger.valueOf(LocationRequestCompat.PASSIVE_INTERVAL);
        BI_MAX_LONG = bigIntegerValueOf4;
        BD_MIN_LONG = new BigDecimal(bigIntegerValueOf3);
        BD_MAX_LONG = new BigDecimal(bigIntegerValueOf4);
        BD_MIN_INT = new BigDecimal(bigIntegerValueOf);
        BD_MAX_INT = new BigDecimal(bigIntegerValueOf2);
    }
    protected ParserMinimalBase() {
    }
    protected ParserMinimalBase(final int i2) {
        super(i2);
    }
    public JsonToken currentToken() {
        return _currToken;
    }
    public int currentTokenId() {
        final JsonToken jsonToken = _currToken;
        if (null == jsonToken) {
            return 0;
        }
        return jsonToken.m307id();
    }
    public JsonToken getCurrentToken() {
        return _currToken;
    }
    public int getCurrentTokenId() {
        final JsonToken jsonToken = _currToken;
        if (null == jsonToken) {
            return 0;
        }
        return jsonToken.m307id();
    }
    public boolean hasCurrentToken() {
        return null != this._currToken;
    }
    public boolean hasTokenId(final int i2) {
        final JsonToken jsonToken = _currToken;
        return null == jsonToken ? 0 == i2 : jsonToken.m307id() == i2;
    }
    public boolean hasToken(final JsonToken jsonToken) {
        return _currToken == jsonToken;
    }
    public boolean isExpectedStartArrayToken() {
        return JsonToken.START_ARRAY == this._currToken;
    }
    public boolean isExpectedStartObjectToken() {
        return JsonToken.START_OBJECT == this._currToken;
    }
    public boolean isExpectedNumberIntToken() {
        return JsonToken.VALUE_NUMBER_INT == this._currToken;
    }
    public JsonToken nextValue() throws IOException {
        final JsonToken jsonTokenNextToken = this.nextToken();
        return JsonToken.FIELD_NAME == jsonTokenNextToken ? this.nextToken() : jsonTokenNextToken;
    }
    public JsonParser skipChildren() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.START_OBJECT != jsonToken && JsonToken.START_ARRAY != jsonToken) {
            return this;
        }
        int i2 = 1;
        while (true) {
            final JsonToken jsonTokenNextToken = this.nextToken();
            if (null == jsonTokenNextToken) {
                this._handleEOF();
                return this;
            }
            if (jsonTokenNextToken.isStructStart()) {
                i2++;
            } else if (jsonTokenNextToken.isStructEnd()) {
                i2--;
                if (0 == i2) {
                    return this;
                }
            } else if (JsonToken.NOT_AVAILABLE == jsonTokenNextToken) {
                this._reportError("Not enough content available for `skipChildren()`: non-blocking parser? (%s)", this.getClass().getName());
            }
        }
    }
    public void clearCurrentToken() {
        final JsonToken jsonToken = _currToken;
        if (null != jsonToken) {
            _lastClearedToken = jsonToken;
            _currToken = null;
        }
    }
    public int getValueAsInt() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_NUMBER_INT == jsonToken || JsonToken.VALUE_NUMBER_FLOAT == jsonToken) {
            return this.getIntValue();
        }
        return this.getValueAsInt(0);
    }
    public int getValueAsInt(final int i2) throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_NUMBER_INT == jsonToken || JsonToken.VALUE_NUMBER_FLOAT == jsonToken) {
            return this.getIntValue();
        }
        if (null == jsonToken) {
            return i2;
        }
        final int iM307id = jsonToken.m307id();
        if (6 == iM307id) {
            final String text = this.getText();
            if (this._hasTextualNull(text)) {
                return 0;
            }
            return NumberInput.parseAsInt(text, i2);
        }
        switch (iM307id) {
            case 9:
                return 1;
            case 10:
            case 11:
                return 0;
            case 12:
                final Object embeddedObject = this.getEmbeddedObject();
                return embeddedObject instanceof Number ? ((Number) embeddedObject).intValue() : i2;
            default:
                return i2;
        }
    }
    public long getValueAsLong() throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_NUMBER_INT == jsonToken || JsonToken.VALUE_NUMBER_FLOAT == jsonToken) {
            return this.getLongValue();
        }
        return this.getValueAsLong(0L);
    }
    public long getValueAsLong(final long j2) throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_NUMBER_INT == jsonToken || JsonToken.VALUE_NUMBER_FLOAT == jsonToken) {
            return this.getLongValue();
        }
        if (null == jsonToken) {
            return j2;
        }
        final int iM307id = jsonToken.m307id();
        if (6 == iM307id) {
            final String text = this.getText();
            if (this._hasTextualNull(text)) {
                return 0L;
            }
            return NumberInput.parseAsLong(text, j2);
        }
        switch (iM307id) {
            case 9:
                return 1L;
            case 10:
            case 11:
                return 0L;
            case 12:
                final Object embeddedObject = this.getEmbeddedObject();
                return embeddedObject instanceof Number ? ((Number) embeddedObject).longValue() : j2;
            default:
                return j2;
        }
    }
    public String getValueAsString() throws IOException {
        return this.getValueAsString(null);
    }
    public String getValueAsString(final String str) throws IOException {
        final JsonToken jsonToken = _currToken;
        if (JsonToken.VALUE_STRING == jsonToken) {
            return this.getText();
        }
        if (JsonToken.FIELD_NAME == jsonToken) {
            return this.getCurrentName();
        }
        return (null == jsonToken || JsonToken.VALUE_NULL == jsonToken || !jsonToken.isScalarValue()) ? str : this.getText();
    }
    protected void _decodeBase64(final String str, final ByteArrayBuilder byteArrayBuilder, final Base64Variant base64Variant) throws IOException {
        try {
            base64Variant.decode(str, byteArrayBuilder);
        } catch (final IllegalArgumentException e2) {
            this._reportError(e2.getMessage());
        }
    }
    protected boolean _hasTextualNull(final String str) {
        return "null".equals(str);
    }
    protected void reportUnexpectedNumberChar(final int i2, final String str) throws JsonParseException {
        String str2 = String.format("Unexpected character (%s) in numeric value", ParserMinimalBase._getCharDesc(i2));
        if (null != str) {
            str2 = str2 + ": " + str;
        }
        this._reportError(str2);
    }
    protected void reportInvalidNumber(final String str) throws JsonParseException {
        this._reportError("Invalid numeric value: " + str);
    }
    protected void reportOverflowInt() throws IOException {
        this.reportOverflowInt(this.getText());
    }
    protected void reportOverflowInt(final String str) throws IOException {
        this.reportOverflowInt(str, this.currentToken());
    }
    protected void reportOverflowInt(final String str, final JsonToken jsonToken) throws IOException {
        this._reportInputCoercion(String.format("Numeric value (%s) out of range of int (%d - %s)", this._longIntegerDesc(str), Integer.MIN_VALUE, Integer.MAX_VALUE), jsonToken, Integer.TYPE);
    }
    protected void reportOverflowLong(final String str) throws IOException {
        this.reportOverflowLong(str, this.currentToken());
    }
    protected void reportOverflowLong(final String str, final JsonToken jsonToken) throws IOException {
        this._reportInputCoercion(String.format("Numeric value (%s) out of range of long (%d - %s)", this._longIntegerDesc(str), Long.MIN_VALUE, Long.MAX_VALUE), jsonToken, Long.TYPE);
    }
    protected void _reportInputCoercion(final String str, final JsonToken jsonToken, final Class<?> cls) throws InputCoercionException {
        throw new InputCoercionException(this, str, jsonToken, cls);
    }
    protected String _longIntegerDesc(final String str) {
        int length = str.length();
        if (1000 > length) {
            return str;
        }
        if (str.startsWith("-")) {
            length--;
        }
        return String.format("[Integer with %d digits]", Integer.valueOf(length));
    }
    protected String _longNumberDesc(final String str) {
        int length = str.length();
        if (1000 > length) {
            return str;
        }
        if (str.startsWith("-")) {
            length--;
        }
        return String.format("[number with %d characters]", Integer.valueOf(length));
    }
    protected void _reportUnexpectedChar(final int i2, final String str) throws JsonParseException {
        if (0 > i2) {
            this._reportInvalidEOF();
        }
        String str2 = String.format("Unexpected character (%s)", ParserMinimalBase._getCharDesc(i2));
        if (null != str) {
            str2 = str2 + ": " + str;
        }
        this._reportError(str2);
    }
    protected void _reportInvalidEOF() throws JsonParseException {
        this._reportInvalidEOF(" in " + _currToken, _currToken);
    }
    protected void _reportInvalidEOFInValue(final JsonToken jsonToken) throws JsonParseException {
        final String str;
        if (JsonToken.VALUE_STRING == jsonToken) {
            str = " in a String value";
        } else if (JsonToken.VALUE_NUMBER_INT == jsonToken || JsonToken.VALUE_NUMBER_FLOAT == jsonToken) {
            str = " in a Number value";
        } else {
            str = " in a value";
        }
        this._reportInvalidEOF(str, jsonToken);
    }
    protected void _reportInvalidEOF(final String str, final JsonToken jsonToken) throws JsonParseException {
        throw new JsonEOFException(this, jsonToken, "Unexpected end-of-input" + str);
    }
    protected void _reportMissingRootWS(final int i2) throws JsonParseException {
        this._reportUnexpectedChar(i2, "Expected space separating root-level values");
    }
    protected void _throwInvalidSpace(final int i2) throws JsonParseException {
        this._reportError("Illegal character (" + ParserMinimalBase._getCharDesc((char) i2) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
    }
    protected static final String _getCharDesc(final int i2) {
        final char c2 = (char) i2;
        if (Character.isISOControl(c2)) {
            return "(CTRL-CHAR, code " + i2 + ")";
        }
        if (255 < i2) {
            return "'" + c2 + "' (code " + i2 + " / 0x" + Integer.toHexString(i2) + ")";
        }
        return "'" + c2 + "' (code " + i2 + ")";
    }
    protected final void _reportError(final String str, final Object... args) throws JsonParseException {
        throw this._constructError(String.format(str, args));
    }
    protected final void _wrapError(final String str, final Throwable th) throws JsonParseException {
        throw this._constructError(str, th);
    }
    protected final void _throwInternal() {
        VersionUtil.throwInternal();
    }
    protected final JsonParseException _constructError(final String str, final Throwable th) {
        return new JsonParseException(this, str, th);
    }
}
