package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.Serializable;
import java.util.Arrays;
public final class Base64Variant implements Serializable {
    public static final int BASE64_VALUE_INVALID = -1;
    public static final int BASE64_VALUE_PADDING = -2;
    private static final int INT_SPACE = 32;
    private static final char PADDING_CHAR_NONE = 0;
    private static final long serialVersionUID = 1;
    private final transient int[] _asciiToBase64;
    private final transient byte[] _base64ToAsciiB;
    private final transient char[] _base64ToAsciiC;
    private final int _maxLineLength;
    final String _name;
    private final char _paddingChar;
    private final PaddingReadBehaviour _paddingReadBehaviour;
    private final boolean _writePadding;

    public enum PaddingReadBehaviour {
        PADDING_FORBIDDEN,
        PADDING_REQUIRED,
        PADDING_ALLOWED
    }

    public Base64Variant(final String str, final String str2, final boolean z, final char c2, final int i2) {
        final int[] iArr = new int[128];
        _asciiToBase64 = iArr;
        final char[] cArr = new char[64];
        _base64ToAsciiC = cArr;
        _base64ToAsciiB = new byte[64];
        _name = str;
        _writePadding = z;
        _paddingChar = c2;
        _maxLineLength = i2;
        final int length = str2.length();
        if (64 != length) {
            throw new IllegalArgumentException("Base64Alphabet length must be exactly 64 (was " + length + ")");
        }
        str2.getChars(0, length, cArr, 0);
        Arrays.fill(iArr, -1);
        for (int i3 = 0; i3 < length; i3++) {
            final char c3 = _base64ToAsciiC[i3];
            _base64ToAsciiB[i3] = (byte) c3;
            _asciiToBase64[c3] = i3;
        }
        if (z) {
            _asciiToBase64[c2] = -2;
        }
        _paddingReadBehaviour = z ? PaddingReadBehaviour.PADDING_REQUIRED : PaddingReadBehaviour.PADDING_FORBIDDEN;
    }

    public Base64Variant(final Base64Variant base64Variant, final String str, final int i2) {
        this(base64Variant, str, base64Variant._writePadding, base64Variant._paddingChar, i2);
    }

    public Base64Variant(final Base64Variant base64Variant, final String str, final boolean z, final char c2, final int i2) {
        this(base64Variant, str, z, c2, base64Variant._paddingReadBehaviour, i2);
    }

    private Base64Variant(final Base64Variant base64Variant, final String str, final boolean z, final char c2, final PaddingReadBehaviour paddingReadBehaviour, final int i2) {
        final int[] iArr = new int[128];
        _asciiToBase64 = iArr;
        final char[] cArr = new char[64];
        _base64ToAsciiC = cArr;
        final byte[] bArr = new byte[64];
        _base64ToAsciiB = bArr;
        _name = str;
        final byte[] bArr2 = base64Variant._base64ToAsciiB;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        final char[] cArr2 = base64Variant._base64ToAsciiC;
        System.arraycopy(cArr2, 0, cArr, 0, cArr2.length);
        final int[] iArr2 = base64Variant._asciiToBase64;
        System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
        _writePadding = z;
        _paddingChar = c2;
        _maxLineLength = i2;
        _paddingReadBehaviour = paddingReadBehaviour;
    }

    private Base64Variant(final Base64Variant base64Variant, final PaddingReadBehaviour paddingReadBehaviour) {
        this(base64Variant, base64Variant._name, base64Variant._writePadding, base64Variant._paddingChar, paddingReadBehaviour, base64Variant._maxLineLength);
    }

    public Base64Variant withPaddingAllowed() {
        return this.withReadPadding(PaddingReadBehaviour.PADDING_ALLOWED);
    }

    public Base64Variant withPaddingRequired() {
        return this.withReadPadding(PaddingReadBehaviour.PADDING_REQUIRED);
    }

    public Base64Variant withPaddingForbidden() {
        return this.withReadPadding(PaddingReadBehaviour.PADDING_FORBIDDEN);
    }

    public Base64Variant withReadPadding(final PaddingReadBehaviour paddingReadBehaviour) {
        return paddingReadBehaviour == _paddingReadBehaviour ? this : new Base64Variant(this, paddingReadBehaviour);
    }

    public Base64Variant withWritePadding(final boolean z) {
        return z == _writePadding ? this : new Base64Variant(this, _name, z, _paddingChar, _maxLineLength);
    }

    private Object readResolve() throws IllegalArgumentException {
        final Base64Variant base64VariantValueOf = Base64Variants.valueOf(_name);
        final boolean z = _writePadding;
        final boolean z2 = base64VariantValueOf._writePadding;
        return (z == z2 && _paddingChar == base64VariantValueOf._paddingChar && _paddingReadBehaviour == base64VariantValueOf._paddingReadBehaviour && _maxLineLength == base64VariantValueOf._maxLineLength && z == z2) ? base64VariantValueOf : new Base64Variant(base64VariantValueOf, _name, z, _paddingChar, _paddingReadBehaviour, _maxLineLength);
    }

    public String getName() {
        return _name;
    }

    public boolean usesPadding() {
        return _writePadding;
    }

    public boolean requiresPaddingOnRead() {
        return PaddingReadBehaviour.PADDING_REQUIRED == this._paddingReadBehaviour;
    }

    public boolean acceptsPaddingOnRead() {
        return PaddingReadBehaviour.PADDING_FORBIDDEN != this._paddingReadBehaviour;
    }

    public boolean usesPaddingChar(final char c2) {
        return c2 == _paddingChar;
    }

    public boolean usesPaddingChar(final int i2) {
        return i2 == _paddingChar;
    }

    public PaddingReadBehaviour paddingReadBehaviour() {
        return _paddingReadBehaviour;
    }

    public char getPaddingChar() {
        return _paddingChar;
    }

    public byte getPaddingByte() {
        return (byte) _paddingChar;
    }

    public int getMaxLineLength() {
        return _maxLineLength;
    }

    public int decodeBase64Char(final char c2) {
        if ('\u007f' >= c2) {
            return _asciiToBase64[c2];
        }
        return -1;
    }

    public int decodeBase64Char(final int i2) {
        if (127 >= i2) {
            return _asciiToBase64[i2];
        }
        return -1;
    }

    public int decodeBase64Byte(final byte b2) {
        if (0 > b2) {
            return -1;
        }
        return _asciiToBase64[b2];
    }

    public char encodeBase64BitsAsChar(final int i2) {
        return _base64ToAsciiC[i2];
    }

    public int encodeBase64Chunk(final int i2, final char[] cArr, final int i3) {
        final char[] cArr2 = _base64ToAsciiC;
        cArr[i3] = cArr2[(i2 >> 18) & 63];
        cArr[i3 + 1] = cArr2[(i2 >> 12) & 63];
        final int i4 = i3 + 3;
        cArr[i3 + 2] = cArr2[(i2 >> 6) & 63];
        final int i5 = i3 + 4;
        cArr[i4] = cArr2[i2 & 63];
        return i5;
    }

    public void encodeBase64Chunk(final StringBuilder sb, final int i2) {
        sb.append(_base64ToAsciiC[(i2 >> 18) & 63]);
        sb.append(_base64ToAsciiC[(i2 >> 12) & 63]);
        sb.append(_base64ToAsciiC[(i2 >> 6) & 63]);
        sb.append(_base64ToAsciiC[i2 & 63]);
    }

    public int encodeBase64Partial(final int i2, final int i3, final char[] cArr, final int i4) {
        final char[] cArr2 = _base64ToAsciiC;
        cArr[i4] = cArr2[(i2 >> 18) & 63];
        final int i5 = i4 + 2;
        cArr[i4 + 1] = cArr2[(i2 >> 12) & 63];
        if (this.usesPadding()) {
            final int i6 = i4 + 3;
            cArr[i5] = 2 == i3 ? _base64ToAsciiC[(i2 >> 6) & 63] : _paddingChar;
            final int i7 = i4 + 4;
            cArr[i6] = _paddingChar;
            return i7;
        }
        if (2 != i3) {
            return i5;
        }
        final int i8 = i4 + 3;
        cArr[i5] = _base64ToAsciiC[(i2 >> 6) & 63];
        return i8;
    }

    public void encodeBase64Partial(final StringBuilder sb, final int i2, final int i3) {
        sb.append(_base64ToAsciiC[(i2 >> 18) & 63]);
        sb.append(_base64ToAsciiC[(i2 >> 12) & 63]);
        if (this.usesPadding()) {
            sb.append(2 == i3 ? _base64ToAsciiC[(i2 >> 6) & 63] : _paddingChar);
            sb.append(_paddingChar);
        } else if (2 == i3) {
            sb.append(_base64ToAsciiC[(i2 >> 6) & 63]);
        }
    }

    public byte encodeBase64BitsAsByte(final int i2) {
        return _base64ToAsciiB[i2];
    }

    public int encodeBase64Chunk(final int i2, final byte[] bArr, final int i3) {
        final byte[] bArr2 = _base64ToAsciiB;
        bArr[i3] = bArr2[(i2 >> 18) & 63];
        bArr[i3 + 1] = bArr2[(i2 >> 12) & 63];
        final int i4 = i3 + 3;
        bArr[i3 + 2] = bArr2[(i2 >> 6) & 63];
        final int i5 = i3 + 4;
        bArr[i4] = bArr2[i2 & 63];
        return i5;
    }

    public int encodeBase64Partial(final int i2, final int i3, final byte[] bArr, final int i4) {
        final byte[] bArr2 = _base64ToAsciiB;
        bArr[i4] = bArr2[(i2 >> 18) & 63];
        final int i5 = i4 + 2;
        bArr[i4 + 1] = bArr2[(i2 >> 12) & 63];
        if (!this.usesPadding()) {
            if (2 != i3) {
                return i5;
            }
            final int i6 = i4 + 3;
            bArr[i5] = _base64ToAsciiB[(i2 >> 6) & 63];
            return i6;
        }
        final byte b2 = (byte) _paddingChar;
        final int i7 = i4 + 3;
        bArr[i5] = 2 == i3 ? _base64ToAsciiB[(i2 >> 6) & 63] : b2;
        final int i8 = i4 + 4;
        bArr[i7] = b2;
        return i8;
    }

    public String encode(final byte[] bArr) {
        return this.encode(bArr, false);
    }

    public String encode(final byte[] bArr, final boolean z) {
        final int length = bArr.length;
        final StringBuilder sb = new StringBuilder((length >> 2) + length + (length >> 3));
        if (z) {
            sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        }
        int maxLineLength = this._maxLineLength >> 2;
        final int i2 = length - 3;
        int i3 = 0;
        while (i3 <= i2) {
            final int i4 = i3 + 2;
            final int i5 = ((bArr[i3 + 1] & 255) | (bArr[i3] << 8)) << 8;
            i3 += 3;
            this.encodeBase64Chunk(sb, i5 | (bArr[i4] & 255));
            maxLineLength--;
            if (0 >= maxLineLength) {
                sb.append('\\');
                sb.append('n');
                maxLineLength = this._maxLineLength >> 2;
            }
        }
        final int i6 = length - i3;
        if (0 < i6) {
            final int i7 = i3 + 1;
            int i8 = bArr[i3] << 16;
            if (2 == i6) {
                i8 |= (bArr[i7] & 255) << 8;
            }
            this.encodeBase64Partial(sb, i8, i6);
        }
        if (z) {
            sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        }
        return sb.toString();
    }

    public String encode(final byte[] bArr, final boolean z, final String str) {
        final int length = bArr.length;
        final StringBuilder sb = new StringBuilder((length >> 2) + length + (length >> 3));
        if (z) {
            sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        }
        int maxLineLength = this._maxLineLength >> 2;
        final int i2 = length - 3;
        int i3 = 0;
        while (i3 <= i2) {
            final int i4 = i3 + 2;
            final int i5 = ((bArr[i3 + 1] & 255) | (bArr[i3] << 8)) << 8;
            i3 += 3;
            this.encodeBase64Chunk(sb, i5 | (bArr[i4] & 255));
            maxLineLength--;
            if (0 >= maxLineLength) {
                sb.append(str);
                maxLineLength = this._maxLineLength >> 2;
            }
        }
        final int i6 = length - i3;
        if (0 < i6) {
            final int i7 = i3 + 1;
            int i8 = bArr[i3] << 16;
            if (2 == i6) {
                i8 |= (bArr[i7] & 255) << 8;
            }
            this.encodeBase64Partial(sb, i8, i6);
        }
        if (z) {
            sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        }
        return sb.toString();
    }

    public byte[] decode(final String str) throws IllegalArgumentException {
        final ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();
        this.decode(str, byteArrayBuilder);
        return byteArrayBuilder.toByteArray();
    }

    public void decode(final String str, final ByteArrayBuilder byteArrayBuilder) throws IllegalArgumentException {
        final int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            final int i3 = i2 + 1;
            final char cCharAt = str.charAt(i2);
            if (' ' < cCharAt) {
                final int iDecodeBase64Char = this.decodeBase64Char(cCharAt);
                if (0 > iDecodeBase64Char) {
                    this._reportInvalidBase64(cCharAt, 0, null);
                }
                if (i3 >= length) {
                    this._reportBase64EOF();
                }
                final int i4 = i2 + 2;
                final char cCharAt2 = str.charAt(i3);
                final int iDecodeBase64Char2 = this.decodeBase64Char(cCharAt2);
                if (0 > iDecodeBase64Char2) {
                    this._reportInvalidBase64(cCharAt2, 1, null);
                }
                final int i5 = (iDecodeBase64Char << 6) | iDecodeBase64Char2;
                if (i4 >= length) {
                    if (!this.requiresPaddingOnRead()) {
                        byteArrayBuilder.append(i5 >> 4);
                        return;
                    }
                    this._reportBase64EOF();
                }
                final int i6 = i2 + 3;
                final char cCharAt3 = str.charAt(i4);
                final int iDecodeBase64Char3 = this.decodeBase64Char(cCharAt3);
                if (0 > iDecodeBase64Char3) {
                    if (-2 != iDecodeBase64Char3) {
                        this._reportInvalidBase64(cCharAt3, 2, null);
                    }
                    if (!this.acceptsPaddingOnRead()) {
                        this._reportBase64UnexpectedPadding();
                    }
                    if (i6 >= length) {
                        this._reportBase64EOF();
                    }
                    i2 += 4;
                    final char cCharAt4 = str.charAt(i6);
                    if (!this.usesPaddingChar(cCharAt4)) {
                        this._reportInvalidBase64(cCharAt4, 3, "expected padding character '" + this._paddingChar + "'");
                    }
                    byteArrayBuilder.append(i5 >> 4);
                } else {
                    final int i7 = (i5 << 6) | iDecodeBase64Char3;
                    if (i6 >= length) {
                        if (!this.requiresPaddingOnRead()) {
                            byteArrayBuilder.appendTwoBytes(i7 >> 2);
                            return;
                        }
                        this._reportBase64EOF();
                    }
                    i2 += 4;
                    final char cCharAt5 = str.charAt(i6);
                    final int iDecodeBase64Char4 = this.decodeBase64Char(cCharAt5);
                    if (0 > iDecodeBase64Char4) {
                        if (-2 != iDecodeBase64Char4) {
                            this._reportInvalidBase64(cCharAt5, 3, null);
                        }
                        if (!this.acceptsPaddingOnRead()) {
                            this._reportBase64UnexpectedPadding();
                        }
                        byteArrayBuilder.appendTwoBytes(i7 >> 2);
                    } else {
                        byteArrayBuilder.appendThreeBytes((i7 << 6) | iDecodeBase64Char4);
                    }
                }
            } else {
                i2 = i3;
            }
        }
    }

    public String toString() {
        return _name;
    }

    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (null == obj || Base64Variant.class != obj.getClass()) {
            return false;
        }
        final Base64Variant base64Variant = (Base64Variant) obj;
        return base64Variant._paddingChar == _paddingChar && base64Variant._maxLineLength == _maxLineLength && base64Variant._writePadding == _writePadding && base64Variant._paddingReadBehaviour == _paddingReadBehaviour && _name.equals(base64Variant._name);
    }

    public int hashCode() {
        return _name.hashCode();
    }

    private void _reportInvalidBase64(final char c2, final int i2, final String str) throws IllegalArgumentException {
        String str2;
        if (' ' >= c2) {
            str2 = "Illegal white space character (code 0x" + Integer.toHexString(c2) + ") as character #" + (i2 + 1) + " of 4-char base64 unit: can only used between units";
        } else if (this.usesPaddingChar(c2)) {
            str2 = "Unexpected padding character ('" + this._paddingChar + "') as character #" + (i2 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(c2) || Character.isISOControl(c2)) {
            str2 = "Illegal character (code 0x" + Integer.toHexString(c2) + ") in base64 content";
        } else {
            str2 = "Illegal character '" + c2 + "' (code 0x" + Integer.toHexString(c2) + ") in base64 content";
        }
        if (null != str) {
            str2 = str2 + ": " + str;
        }
        throw new IllegalArgumentException(str2);
    }

    private void _reportBase64EOF() throws IllegalArgumentException {
        throw new IllegalArgumentException(this.missingPaddingMessage());
    }

    private void _reportBase64UnexpectedPadding() throws IllegalArgumentException {
        throw new IllegalArgumentException(this.unexpectedPaddingMessage());
    }

    private String unexpectedPaddingMessage() {
        return String.format("Unexpected end of base64-encoded String: base64 variant '%s' expects no padding at the end while decoding. This Base64Variant might have been incorrectly configured", this._name);
    }

    public String missingPaddingMessage() {
        return String.format("Unexpected end of base64-encoded String: base64 variant '%s' expects padding (one or more '%c' characters) at the end. This Base64Variant might have been incorrectly configured", this._name, Character.valueOf(this._paddingChar));
    }
}
