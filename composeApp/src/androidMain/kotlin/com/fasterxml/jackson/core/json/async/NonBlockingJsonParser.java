package com.fasterxml.jackson.core.json.async;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.TextBuffer;
import com.fasterxml.jackson.core.util.VersionUtil;

import java.io.IOException;

public class NonBlockingJsonParser extends NonBlockingJsonParserBase {
    private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS;

    private static final int FEAT_MASK_ALLOW_MISSING;

    private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES;

    private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES;

    private static final int FEAT_MASK_ALLOW_YAML_COMMENTS;

    private static final int FEAT_MASK_LEADING_ZEROS;

    private static final int FEAT_MASK_TRAILING_COMMA = Feature.ALLOW_TRAILING_COMMA.getMask();

    protected static final int[] _icLatin1;

    private static final int[] _icUTF8;

    protected byte[] _inputBuffer;

    static {
        FEAT_MASK_LEADING_ZEROS = Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
        FEAT_MASK_ALLOW_MISSING = Feature.ALLOW_MISSING_VALUES.getMask();
        FEAT_MASK_ALLOW_SINGLE_QUOTES = Feature.ALLOW_SINGLE_QUOTES.getMask();
        FEAT_MASK_ALLOW_UNQUOTED_NAMES = Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
        FEAT_MASK_ALLOW_JAVA_COMMENTS = Feature.ALLOW_COMMENTS.getMask();
        FEAT_MASK_ALLOW_YAML_COMMENTS = Feature.ALLOW_YAML_COMMENTS.getMask();
        _icUTF8 = CharTypes.getInputCodeUtf8();
        _icLatin1 = CharTypes.getInputCodeLatin1();
    }

    public NonBlockingJsonParser(final IOContext paramIOContext, final int paramInt, final ByteQuadsCanonicalizer paramByteQuadsCanonicalizer) {
        super(paramIOContext, paramInt, paramByteQuadsCanonicalizer);
        final byte[] arrayOfByte = NO_BYTES;
        _inputBuffer = arrayOfByte;
    }

    private final int _decodeCharEscape() {
        int i = _inputEnd;
        int j = _inputPtr;
        i -= j;
        j = 5;
        return (i < j) ? this._decodeSplitEscaped(0, -1) : this._decodeFastCharEscape();
    }

    private final int _decodeFastCharEscape() {
        byte[] arrayOfByte = _inputBuffer;
        int i = _inputPtr;
        int j = i + 1;
        _inputPtr = j;
        int k = arrayOfByte[i];
        byte b = 34;
        if (k != b) {
            b = 47;
            if (k != b) {
                b = 92;
                if (k != b) {
                    b = 98;
                    if (k != b) {
                        b = 102;
                        if (k != b) {
                            b = 110;
                            if (k != b) {
                                b = 114;
                                if (k != b) {
                                    b = 116;
                                    if (k != b) {
                                        b = 117;
                                        if (k != b) {
                                            final char c = (char)k;
                                            try {
                                                return this._handleUnrecognizedCharacterEscape(c);
                                            } catch (final JsonProcessingException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                        i += 2;
                                        _inputPtr = i;
                                        int m = arrayOfByte[j];
                                        i = CharTypes.charToHex(m);
                                        if (0 <= i) {
                                            arrayOfByte = _inputBuffer;
                                            j = _inputPtr;
                                            k = j + 1;
                                            _inputPtr = k;
                                            m = arrayOfByte[j];
                                            j = CharTypes.charToHex(m);
                                            if (0 <= j) {
                                                m = i << 4 | j;
                                                byte[] arrayOfByte1 = _inputBuffer;
                                                j = _inputPtr;
                                                k = j + 1;
                                                _inputPtr = k;
                                                i = arrayOfByte1[j];
                                                j = CharTypes.charToHex(i);
                                                if (0 <= j) {
                                                    m = m << 4 | j;
                                                    arrayOfByte1 = _inputBuffer;
                                                    j = _inputPtr;
                                                    k = j + 1;
                                                    _inputPtr = k;
                                                    i = arrayOfByte1[j];
                                                    j = CharTypes.charToHex(i);
                                                    if (0 <= j)
                                                        return m << 4 | j;
                                                }
                                                m = i;
                                            }
                                        }
                                        m &= 0xFF;
                                        try {
                                            this._reportUnexpectedChar(m, "expected a hex-digit for character escape sequence");
                                        } catch (final JsonParseException e) {
                                            throw new RuntimeException(e);
                                        }
                                        return -1;
                                    }
                                    return 9;
                                }
                                return 13;
                            }
                            return 10;
                        }
                        return 12;
                    }
                    return 8;
                }
            }
        }
        return (char)k;
    }

    private int _decodeSplitEscaped(int paramInt1, int paramInt2) {
        int i = _inputPtr;
        int j = _inputEnd;
        final byte b = -1;
        if (i >= j) {
            _quoted32 = paramInt1;
            _quotedDigits = paramInt2;
            return b;
        }
        final byte[] arrayOfByte = _inputBuffer;
        final int k = i + 1;
        _inputPtr = k;
        byte b1 = arrayOfByte[i];
        if (paramInt2 == b) {
            paramInt2 = 34;
            if (b1 != paramInt2) {
                paramInt2 = 47;
                if (b1 != paramInt2) {
                    paramInt2 = 92;
                    if (b1 != paramInt2) {
                        paramInt2 = 98;
                        if (b1 != paramInt2) {
                            paramInt2 = 102;
                            if (b1 != paramInt2) {
                                paramInt2 = 110;
                                if (b1 != paramInt2) {
                                    paramInt2 = 114;
                                    if (b1 != paramInt2) {
                                        paramInt2 = 116;
                                        if (b1 != paramInt2) {
                                            paramInt2 = 117;
                                            if (b1 != paramInt2) {
                                                paramInt1 = (char) b1;
                                                try {
                                                    return this._handleUnrecognizedCharacterEscape((char) paramInt1);
                                                } catch (final JsonProcessingException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                            paramInt2 = 0;
                                            if (k >= j) {
                                                _quotedDigits = 0;
                                                _quoted32 = 0;
                                                return b;
                                            }
                                            i += 2;
                                            _inputPtr = i;
                                            b1 = arrayOfByte[k];
                                        } else {
                                            return 9;
                                        }
                                    } else {
                                        return 13;
                                    }
                                } else {
                                    return 10;
                                }
                            } else {
                                return 12;
                            }
                        } else {
                            return 8;
                        }
                    } else {
                        return b1;
                    }
                } else {
                    return b1;
                }
            } else {
                return b1;
            }
        }
        byte[] arrayOfByte1;
        for (i = b1 & 0xFF; ; i = arrayOfByte1[i] & 0xFF) {
            j = CharTypes.charToHex(i);
            if (0 > j) {
                i &= 0xFF;
                final String str = "expected a hex-digit for character escape sequence";
                try {
                    this._reportUnexpectedChar(i, str);
                } catch (final JsonParseException e) {
                    throw new RuntimeException(e);
                }
            }
            i = 4;
            paramInt1 = paramInt1 << i | j;
            ++paramInt2;
            if (paramInt2 == i)
                return paramInt1;
            i = _inputPtr;
            j = _inputEnd;
            if (i >= j) {
                _quotedDigits = paramInt2;
                _quoted32 = paramInt1;
                return b;
            }
            arrayOfByte1 = _inputBuffer;
            final int m = i + 1;
            _inputPtr = m;
        }
    }

    private final boolean _decodeSplitMultiByte(int paramInt1, int paramInt2, final boolean paramBoolean) {
        int i = 0;
        final byte b = 1;
        if (paramInt2 != b) {
            int j = 0;
            byte b1 = 2;
            if (paramInt2 != b1) {
                b1 = 3;
                if (paramInt2 != b1) {
                    b1 = 4;
                    if (paramInt2 != b1) {
                        paramInt2 = 32;
                        if (paramInt1 < paramInt2) {
                            final String str = "string value";
                            try {
                                this._throwUnquotedSpace(paramInt1, str);
                            } catch (final JsonParseException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            try {
                                this._reportInvalidChar(paramInt1);
                            } catch (final JsonParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        final TextBuffer textBuffer1 = _textBuffer;
                        paramInt1 = (char)paramInt1;
                        textBuffer1.append((char) paramInt1);
                        return b;
                    }
                    paramInt1 &= 0x7;
                    if (paramBoolean) {
                        final byte[] arrayOfByte = _inputBuffer;
                        j = _inputPtr;
                        i = j + 1;
                        _inputPtr = i;
                        paramInt2 = arrayOfByte[j];
                        return this._decodeSplitUTF8_4(paramInt1, b, paramInt2);
                    }
                    _pending32 = paramInt1;
                    _pendingBytes = b;
                    _minorState = 44;
                    return false;
                }
                paramInt1 &= 0xF;
                if (0 != j) {
                    final byte[] arrayOfByte = _inputBuffer;
                    j = _inputPtr;
                    i = j + 1;
                    _inputPtr = i;
                    paramInt2 = arrayOfByte[j];
                    return this._decodeSplitUTF8_3(paramInt1, b, paramInt2);
                }
                _minorState = 43;
                _pending32 = paramInt1;
                _pendingBytes = b;
                return false;
            }
            if (0 != j) {
                final byte[] arrayOfByte = _inputBuffer;
                j = _inputPtr;
                i = j + 1;
                _inputPtr = i;
                paramInt2 = arrayOfByte[j];
                paramInt1 = this._decodeUTF8_2(paramInt1, paramInt2);
                final TextBuffer textBuffer1 = _textBuffer;
                paramInt1 = (char)paramInt1;
                textBuffer1.append(paramInt1);
                return b;
            }
            _minorState = 42;
            _pending32 = paramInt1;
            return false;
        }
        paramInt1 = this._decodeSplitEscaped(0, -1);
        if (0 > paramInt1) {
            _minorState = 41;
            return false;
        }
        final TextBuffer textBuffer = _textBuffer;
        paramInt1 = (char)paramInt1;
        textBuffer.append(paramInt1);
        return b;
    }

    private final boolean _decodeSplitUTF8_3(int paramInt1, int paramInt2, int paramInt3) {
        int i = 128;
        final byte b = 1;
        if (paramInt2 == b) {
            paramInt2 = paramInt3 & 0xC0;
            if (paramInt2 != i) {
                paramInt2 = paramInt3 & 0xFF;
                final int k = _inputPtr;
                this._reportInvalidOther(paramInt2, k);
            }
            paramInt1 <<= 6;
            paramInt2 = paramInt3 & 0x3F;
            paramInt1 |= paramInt2;
            paramInt2 = _inputPtr;
            paramInt3 = _inputEnd;
            if (paramInt2 >= paramInt3) {
                _minorState = 43;
                _pending32 = paramInt1;
                _pendingBytes = 2;
                return false;
            }
            final byte[] arrayOfByte = _inputBuffer;
            final int j = paramInt2 + 1;
            _inputPtr = j;
            paramInt3 = arrayOfByte[paramInt2];
        }
        paramInt2 = paramInt3 & 0xC0;
        if (paramInt2 != i) {
            paramInt2 = paramInt3 & 0xFF;
            i = _inputPtr;
            this._reportInvalidOther(paramInt2, i);
        }
        final TextBuffer textBuffer = _textBuffer;
        paramInt1 <<= 6;
        paramInt3 &= 0x3F;
        paramInt1 = (char)(paramInt1 | paramInt3);
        textBuffer.append(paramInt1);
        return b;
    }

    private final boolean _decodeSplitUTF8_4(int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        final byte b1 = 44;
        int j = 2;
        final char c = '\u0080';
        final byte b2 = 1;
        if (paramInt2 == b2) {
            paramInt2 = paramInt3 & 0xC0;
            if (paramInt2 != c) {
                paramInt2 = paramInt3 & 0xFF;
                final int m = _inputPtr;
                this._reportInvalidOther(paramInt2, m);
            }
            paramInt1 <<= 6;
            paramInt2 = paramInt3 & 0x3F;
            paramInt1 |= paramInt2;
            paramInt2 = _inputPtr;
            paramInt3 = _inputEnd;
            if (paramInt2 >= paramInt3) {
                _minorState = b1;
                _pending32 = paramInt1;
                _pendingBytes = j;
                return false;
            }
            final byte[] arrayOfByte = _inputBuffer;
            final int k = paramInt2 + 1;
            _inputPtr = k;
            paramInt3 = arrayOfByte[paramInt2];
            paramInt2 = j;
        }
        if (paramInt2 == j) {
            paramInt2 = paramInt3 & 0xC0;
            if (paramInt2 != c) {
                paramInt2 = paramInt3 & 0xFF;
                j = _inputPtr;
                this._reportInvalidOther(paramInt2, j);
            }
            paramInt1 <<= 6;
            paramInt2 = paramInt3 & 0x3F;
            paramInt1 |= paramInt2;
            paramInt2 = _inputPtr;
            paramInt3 = _inputEnd;
            if (paramInt2 >= paramInt3) {
                _minorState = b1;
                _pending32 = paramInt1;
                _pendingBytes = 3;
                return false;
            }
            final byte[] arrayOfByte = _inputBuffer;
            i = paramInt2 + 1;
            _inputPtr = i;
            paramInt3 = arrayOfByte[paramInt2];
        }
        paramInt2 = paramInt3 & 0xC0;
        if (paramInt2 != c) {
            paramInt2 = paramInt3 & 0xFF;
            i = _inputPtr;
            this._reportInvalidOther(paramInt2, i);
        }
        paramInt1 <<= 6;
        paramInt2 = paramInt3 & 0x3F;
        paramInt1 = (paramInt1 | paramInt2) - 65536;
        TextBuffer textBuffer = _textBuffer;
        paramInt3 = (char)(paramInt1 >> 10 | 0xD800);
        textBuffer.append(paramInt3);
        paramInt1 = paramInt1 & 0x3FF | 0xDC00;
        textBuffer = _textBuffer;
        paramInt1 = (char)paramInt1;
        textBuffer.append(paramInt1);
        return b2;
    }

    private final int _decodeUTF8_2(int paramInt1, int paramInt2) {
        int i = paramInt2 & 0xC0;
        int j = 128;
        if (i != j) {
            i = paramInt2 & 0xFF;
            j = _inputPtr;
            this._reportInvalidOther(i, j);
        }
        paramInt1 = (paramInt1 & 0x1F) << 6;
        paramInt2 &= 0x3F;
        return paramInt1 | paramInt2;
    }

    private final int _decodeUTF8_3(int paramInt1, int paramInt2, final int paramInt3) {
        paramInt1 &= 0xF;
        int i = paramInt2 & 0xC0;
        final char c = '\u0080';
        if (i != c) {
            i = paramInt2 & 0xFF;
            final int j = _inputPtr;
            this._reportInvalidOther(i, j);
        }
        paramInt1 <<= 6;
        paramInt2 &= 0x3F;
        paramInt1 |= paramInt2;
        paramInt2 = paramInt3 & 0xC0;
        if (paramInt2 != c) {
            paramInt2 = paramInt3 & 0xFF;
            i = _inputPtr;
            this._reportInvalidOther(paramInt2, i);
        }
        paramInt1 <<= 6;
        paramInt2 = paramInt3 & 0x3F;
        return paramInt1 | paramInt2;
    }

    private final int _decodeUTF8_4(int paramInt1, int paramInt2, int paramInt3, final int paramInt4) {
        int i = paramInt2 & 0xC0;
        final char c = '\u0080';
        if (i != c) {
            i = paramInt2 & 0xFF;
            final int j = _inputPtr;
            this._reportInvalidOther(i, j);
        }
        paramInt1 = (paramInt1 & 0x7) << 6;
        paramInt2 &= 0x3F;
        paramInt1 |= paramInt2;
        paramInt2 = paramInt3 & 0xC0;
        if (paramInt2 != c) {
            paramInt2 = paramInt3 & 0xFF;
            i = _inputPtr;
            this._reportInvalidOther(paramInt2, i);
        }
        paramInt1 <<= 6;
        paramInt2 = paramInt3 & 0x3F;
        paramInt1 |= paramInt2;
        paramInt2 = paramInt4 & 0xC0;
        if (paramInt2 != c) {
            paramInt2 = paramInt4 & 0xFF;
            paramInt3 = _inputPtr;
            this._reportInvalidOther(paramInt2, paramInt3);
        }
        paramInt1 <<= 6;
        paramInt2 = paramInt4 & 0x3F;
        return (paramInt1 | paramInt2) - 65536;
    }

    private final String _fastParseName() {
        final byte[] arrayOfByte = _inputBuffer;
        final int[] arrayOfInt = NonBlockingJsonParser._icLatin1;
        int i = _inputPtr;
        int j = i + 1;
        int k = arrayOfByte[i] & 0xFF;
        int m = arrayOfInt[k];
        final byte b = 34;
        if (0 == m) {
            m = i + 2;
            j = arrayOfByte[j] & 0xFF;
            int n = arrayOfInt[j];
            if (0 == n) {
                k <<= 8;
                j |= k;
                k = i + 3;
                m = arrayOfByte[m] & 0xFF;
                n = arrayOfInt[m];
                if (0 == n) {
                    j = j << 8 | m;
                    m = i + 4;
                    k = arrayOfByte[k] & 0xFF;
                    n = arrayOfInt[k];
                    if (0 == n) {
                        j = j << 8 | k;
                        i += 5;
                        final int i1 = arrayOfByte[m] & 0xFF;
                        final int i2 = arrayOfInt[i1];
                        if (0 == i2) {
                            _quad1 = j;
                            return this._parseMediumName(i, i1);
                        }
                        if (i1 == b) {
                            _inputPtr = i;
                            return this._findName(j, 4);
                        }
                        return null;
                    }
                    if (k == b) {
                        _inputPtr = m;
                        return this._findName(j, 3);
                    }
                    return null;
                }
                if (m == b) {
                    _inputPtr = k;
                    return this._findName(j, 2);
                }
                return null;
            }
            if (j == b) {
                _inputPtr = m;
                return this._findName(k, 1);
            }
            return null;
        }
        if (k == b) {
            _inputPtr = j;
            return "";
        }
        return null;
    }

    private JsonToken _finishAposName(int paramInt1, int paramInt2, int paramInt3) {
        int[] arrayOfInt1 = _quadBuffer;
        final int[] arrayOfInt2 = NonBlockingJsonParser._icLatin1;
        while (true) {
            int i = _inputPtr;
            int j = _inputEnd;
            int k = 9;
            if (i >= j) {
                _quadLength = paramInt1;
                _pending32 = paramInt2;
                _pendingBytes = paramInt3;
                _minorState = k;
                final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            byte[] arrayOfByte = _inputBuffer;
            int m = i + 1;
            _inputPtr = m;
            i = arrayOfByte[i] & 0xFF;
            j = 39;
            if (i == j) {
                if (0 < paramInt3) {
                    int n = arrayOfInt1.length;
                    if (paramInt1 >= n) {
                        n = arrayOfInt1.length;
                        arrayOfInt1 = growArrayBy(arrayOfInt1, n);
                        _quadBuffer = arrayOfInt1;
                    }
                    n = paramInt1 + 1;
                    paramInt2 = _padLastQuad(paramInt2, paramInt3);
                    arrayOfInt1[paramInt1] = paramInt2;
                    paramInt1 = n;
                } else if (0 == paramInt1) {
                    return this._fieldComplete("");
                }
                String str = _symbols.findName(arrayOfInt1, paramInt1);
                if (null == str)
                    str = this._addName(arrayOfInt1, paramInt1, paramInt3);
                return this._fieldComplete(str);
            }
            j = 34;
            m = 4;
            final byte b = 1;
            if (i != j) {
                j = arrayOfInt2[i];
                if (0 != j) {
                    j = 92;
                    final byte b1 = 8;
                    if (i != j) {
                        final String str = "name";
                        this._throwUnquotedSpace(i, str);
                    } else {
                        i = this._decodeCharEscape();
                        if (0 > i) {
                            _minorState = b1;
                            _minorStateAfterSplit = k;
                            _quadLength = paramInt1;
                            _pending32 = paramInt2;
                            _pendingBytes = paramInt3;
                            final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                            this._currToken = jsonToken;
                            return jsonToken;
                        }
                    }
                    j = 127;
                    if (i > j) {
                        j = 0;
                        arrayOfByte = null;
                        if (paramInt3 >= m) {
                            paramInt3 = arrayOfInt1.length;
                            if (paramInt1 >= paramInt3) {
                                paramInt3 = arrayOfInt1.length;
                                arrayOfInt1 = growArrayBy(arrayOfInt1, paramInt3);
                                _quadBuffer = arrayOfInt1;
                            }
                            paramInt3 = paramInt1 + 1;
                            arrayOfInt1[paramInt1] = paramInt2;
                            paramInt1 = paramInt3;
                            paramInt2 = 0;
                            final Object object = null;
                            paramInt3 = 0;
                        }
                        k = 2048;
                        if (i < k) {
                            paramInt2 <<= 8;
                            j = i >> 6 | 0xC0;
                            paramInt2 |= j;
                            paramInt3++;
                        } else {
                            paramInt2 <<= 8;
                            k = i >> 12 | 0xE0;
                            paramInt2 |= k;
                            ++paramInt3;
                            if (paramInt3 >= m) {
                                paramInt3 = arrayOfInt1.length;
                                if (paramInt1 >= paramInt3) {
                                    paramInt3 = arrayOfInt1.length;
                                    arrayOfInt1 = growArrayBy(arrayOfInt1, paramInt3);
                                    _quadBuffer = arrayOfInt1;
                                }
                                paramInt3 = paramInt1 + 1;
                                arrayOfInt1[paramInt1] = paramInt2;
                                paramInt1 = paramInt3;
                                paramInt3 = 0;
                            } else {
                                j = paramInt2;
                            }
                            paramInt2 = j << 8;
                            j = i >> 6 & 0x3F | 0x80;
                            paramInt2 |= j;
                            paramInt3 += b;
                        }
                        i = i & 0x3F | 0x80;
                    }
                }
            }
            if (paramInt3 < m) {
                paramInt3++;
                paramInt2 = paramInt2 << 8 | i;
                continue;
            }
            paramInt3 = arrayOfInt1.length;
            if (paramInt1 >= paramInt3) {
                paramInt3 = arrayOfInt1.length;
                arrayOfInt1 = growArrayBy(arrayOfInt1, paramInt3);
                _quadBuffer = arrayOfInt1;
            }
            paramInt3 = paramInt1 + 1;
            arrayOfInt1[paramInt1] = paramInt2;
            paramInt1 = paramInt3;
            paramInt2 = i;
            paramInt3 = b;
        }
    }

    private final JsonToken _finishAposString() {
        final int[] arrayOfInt = NonBlockingJsonParser._icUTF8;
        final byte[] arrayOfByte = _inputBuffer;
        char[] arrayOfChar = _textBuffer.getBufferWithoutReset();
        TextBuffer textBuffer = _textBuffer;
        int i = textBuffer.getCurrentSegmentSize();
        int j = _inputPtr;
        final int k = _inputEnd + -5;
        while (true) {
            JsonToken jsonToken = null;
            int m = _inputEnd;
            int n = 45;
            if (j >= m) {
                _inputPtr = j;
                _minorState = n;
                _textBuffer.setCurrentLength(i);
                jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            m = arrayOfChar.length;
            int i1 = 0;
            if (i >= m) {
                arrayOfChar = _textBuffer.finishCurrentSegment();
                i = 0;
                textBuffer = null;
            }
            m = _inputEnd;
            int i2 = arrayOfChar.length - i + j;
            m = Math.min(m, i2);
            while (j < m) {
                char[] arrayOfChar1 = new char[0];
                i2 = j + 1;
                int i3 = arrayOfByte[j] & 0xFF;
                final JsonToken jsonToken1 = jsonToken[i3];
                if (null != jsonToken1) {
                    final byte b = 34;
                    if (i3 != b) {
                        m = 1;
                        if (i2 >= k) {
                            _inputPtr = i2;
                            final TextBuffer textBuffer1 = _textBuffer;
                            textBuffer1.setCurrentLength(i);
                            final JsonToken jsonToken2 = jsonToken[i3];
                            i = _inputEnd;
                            if (i2 < i)
                                i1 = m;
                            final boolean bool = this._decodeSplitMultiByte(i3, jsonToken2, i1);
                            if (!bool) {
                                _minorStateAfterSplit = n;
                                jsonToken = JsonToken.NOT_AVAILABLE;
                                this._currToken = jsonToken;
                                return jsonToken;
                            }
                            arrayOfChar1 = _textBuffer.getBufferWithoutReset();
                            textBuffer = _textBuffer;
                            i = textBuffer.getCurrentSegmentSize();
                            j = _inputPtr;
                            break;
                        }
                        if (jsonToken1 != m) {
                            m = 2;
                            if (jsonToken1 != m) {
                                m = 3;
                                if (jsonToken1 != m) {
                                    m = 4;
                                    if (jsonToken1 != m) {
                                        j = 32;
                                        if (i3 < j) {
                                            final String str = "string value";
                                            this._throwUnquotedSpace(i3, str);
                                        } else {
                                            this._reportInvalidChar(i3);
                                        }
                                        j = i2;
                                    } else {
                                        final byte[] arrayOfByte1 = _inputBuffer;
                                        n = j + 2;
                                        i2 = arrayOfByte1[i2];
                                        final int i4 = j + 3;
                                        n = arrayOfByte1[n];
                                        j += 4;
                                        m = arrayOfByte1[i4];
                                        m = this._decodeUTF8_4(i3, i2, n, m);
                                        n = i + 1;
                                        i2 = m >> 10;
                                        i3 = 55296;
                                        i2 = (char)(i2 | i3);
                                        arrayOfChar1[i] = i2;
                                        i = arrayOfChar1.length;
                                        if (n >= i) {
                                            arrayOfChar1 = _textBuffer.finishCurrentSegment();
                                            i = 0;
                                            textBuffer = null;
                                        } else {
                                            i = n;
                                        }
                                        m &= 0x3FF;
                                        n = 56320;
                                        i3 = m | n;
                                    }
                                } else {
                                    final byte[] arrayOfByte1 = _inputBuffer;
                                    n = j + 2;
                                    i2 = arrayOfByte1[i2];
                                    j += 3;
                                    m = arrayOfByte1[n];
                                    i3 = this._decodeUTF8_3(i3, i2, m);
                                }
                            } else {
                                final byte[] arrayOfByte1 = _inputBuffer;
                                j += 2;
                                m = arrayOfByte1[i2];
                                i3 = this._decodeUTF8_2(i3, m);
                            }
                        } else {
                            _inputPtr = i2;
                            i3 = this._decodeFastCharEscape();
                            j = _inputPtr;
                        }
                        m = arrayOfChar1.length;
                        if (i >= m) {
                            arrayOfChar1 = _textBuffer.finishCurrentSegment();
                        } else {
                            i1 = i;
                        }
                        i = i1 + 1;
                        m = (char)i3;
                        arrayOfChar1[i1] = m;
                        break;
                    }
                }
                j = 39;
                if (i3 == j) {
                    _inputPtr = i2;
                    _textBuffer.setCurrentLength(i);
                    jsonToken = JsonToken.VALUE_STRING;
                    return this._valueComplete(jsonToken);
                }
                j = i + 1;
                i3 = (char)i3;
                arrayOfChar1[i] = i3;
                i = j;
                j = i2;
            }
        }
    }

    private final JsonToken _finishBOM(int paramInt) {
        while (true) {
            int i = _inputPtr;
            int j = _inputEnd;
            final byte b = 1;
            if (i < j) {
                final byte[] arrayOfByte = _inputBuffer;
                final int k = i + 1;
                _inputPtr = k;
                i = arrayOfByte[i] & 0xFF;
                if (paramInt != b) {
                    j = 2;
                    if (paramInt != j) {
                        j = 3;
                        if (paramInt == j) {
                            final long l = _currInputProcessed - 3L;
                            _currInputProcessed = l;
                            return this._startDocument(i);
                        }
                    } else {
                        j = 191;
                        if (i != j) {
                            final Integer integer = Integer.valueOf(i);
                            final String str = "Unexpected byte 0x%02x following 0xEF 0xBB; should get 0xBF as third byte of UTF-8 BOM";
                            this._reportError(str, integer);
                        }
                    }
                } else {
                    j = 187;
                    if (i != j) {
                        final Integer integer = Integer.valueOf(i);
                        final String str = "Unexpected byte 0x%02x following 0xEF; should get 0xBB as second byte UTF-8 BOM";
                        this._reportError(str, integer);
                    }
                }
                paramInt++;
                continue;
            }
            _pending32 = paramInt;
            _minorState = b;
            final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
    }

    private final JsonToken _finishCComment(final int paramInt, boolean paramBoolean) {
        while (true) {
            int i = _inputPtr;
            int j = _inputEnd;
            if (i >= j) {
                final byte b1;
                if (paramBoolean) {
                    b1 = 52;
                } else {
                    b1 = 53;
                }
                _minorState = b1;
                _pending32 = paramInt;
                final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            final byte[] arrayOfByte = _inputBuffer;
            final int k = i + 1;
            _inputPtr = k;
            i = arrayOfByte[i] & 0xFF;
            j = 32;
            final byte b = 1;
            if (i < j) {
                int m = 10;
                if (i == m) {
                    m = _currInputRow + b;
                    _currInputRow = m;
                    _currInputRowStart = k;
                } else {
                    m = 13;
                    if (i == m) {
                        m = _currInputRowAlt + b;
                        _currInputRowAlt = m;
                        _currInputRowStart = k;
                    } else {
                        m = 9;
                        if (i != m)
                            this._throwInvalidSpace(i);
                    }
                }
            } else {
                byte b1 = 0;
                j = 42;
                if (i == j) {
                    b1 = b;
                    continue;
                }
                j = 47;
                if (i == j && 0 != b1)
                    return this._startAfterComment(paramInt);
            }
            paramBoolean = false;
        }
    }

    private final JsonToken _finishCppComment(final int paramInt) {
        while (true) {
            int i = _inputPtr;
            int j = _inputEnd;
            if (i >= j) {
                _minorState = 54;
                _pending32 = paramInt;
                final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            final byte[] arrayOfByte = _inputBuffer;
            final int k = i + 1;
            _inputPtr = k;
            i = arrayOfByte[i] & 0xFF;
            j = 32;
            if (i < j) {
                j = 10;
                if (i == j) {
                    i = _currInputRow + 1;
                    _currInputRow = i;
                    _currInputRowStart = k;
                } else {
                    j = 13;
                    if (i == j) {
                        i = _currInputRowAlt + 1;
                        _currInputRowAlt = i;
                        _currInputRowStart = k;
                        return this._startAfterComment(paramInt);
                    }
                    j = 9;
                    if (i != j) {
                        try {
                            this._throwInvalidSpace(i);
                        } catch (final JsonParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    continue;
                }
                return this._startAfterComment(paramInt);
            }
        }
    }

    private final JsonToken _finishHashComment(final int paramInt) {
        int i = this._features;
        int j = NonBlockingJsonParser.FEAT_MASK_ALLOW_YAML_COMMENTS;
        i &= j;
        if (0 == i) {
            i = 35;
            final String str = "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_YAML_COMMENTS' not enabled for parser)";
            try {
                this._reportUnexpectedChar(i, str);
            } catch (final JsonParseException e) {
                throw new RuntimeException(e);
            }
        }
        while (true) {
            i = _inputPtr;
            j = _inputEnd;
            if (i >= j) {
                _minorState = 55;
                _pending32 = paramInt;
                final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            final byte[] arrayOfByte = _inputBuffer;
            final int k = i + 1;
            _inputPtr = k;
            i = arrayOfByte[i] & 0xFF;
            j = 32;
            if (i < j) {
                j = 10;
                if (i == j) {
                    i = _currInputRow + 1;
                    _currInputRow = i;
                    _currInputRowStart = k;
                } else {
                    j = 13;
                    if (i == j) {
                        i = _currInputRowAlt + 1;
                        _currInputRowAlt = i;
                        _currInputRowStart = k;
                        return this._startAfterComment(paramInt);
                    }
                    j = 9;
                    if (i != j)
                        this._throwInvalidSpace(i);
                    continue;
                }
                return this._startAfterComment(paramInt);
            }
        }
    }

    private final JsonToken _finishRegularString() {
        final int[] arrayOfInt = NonBlockingJsonParser._icUTF8;
        final byte[] arrayOfByte = _inputBuffer;
        char[] arrayOfChar = _textBuffer.getBufferWithoutReset();
        TextBuffer textBuffer = _textBuffer;
        int i = textBuffer.getCurrentSegmentSize();
        int j = _inputPtr;
        final int k = _inputEnd + -5;
        while (true) {
            JsonToken jsonToken;
            int m = _inputEnd;
            int n = 40;
            if (j >= m) {
                _inputPtr = j;
                _minorState = n;
                _textBuffer.setCurrentLength(i);
                jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            m = arrayOfChar.length;
            int i1 = 0;
            if (i >= m) {
                arrayOfChar = _textBuffer.finishCurrentSegment();
                i = 0;
                textBuffer = null;
            }
            m = _inputEnd;
            int i2 = arrayOfChar.length - i + j;
            m = Math.min(m, i2);
            while (j < m) {
                char[] arrayOfChar1;
                i2 = j + 1;
                int i3 = arrayOfByte[j] & 0xFF;
                final JsonToken jsonToken1 = jsonToken[i3];
                if (null != jsonToken1) {
                    m = 34;
                    if (i3 == m) {
                        _inputPtr = i2;
                        _textBuffer.setCurrentLength(i);
                        jsonToken = JsonToken.VALUE_STRING;
                        return this._valueComplete(jsonToken);
                    }
                    m = 1;
                    if (i2 >= k) {
                        _inputPtr = i2;
                        final TextBuffer textBuffer1 = _textBuffer;
                        textBuffer1.setCurrentLength(i);
                        final JsonToken jsonToken2 = jsonToken[i3];
                        i = _inputEnd;
                        if (i2 < i)
                            i1 = m;
                        final boolean bool = this._decodeSplitMultiByte(i3, jsonToken2, i1);
                        if (!bool) {
                            _minorStateAfterSplit = n;
                            jsonToken = JsonToken.NOT_AVAILABLE;
                            this._currToken = jsonToken;
                            return jsonToken;
                        }
                        arrayOfChar1 = _textBuffer.getBufferWithoutReset();
                        textBuffer = _textBuffer;
                        i = textBuffer.getCurrentSegmentSize();
                        j = _inputPtr;
                        break;
                    }
                    if (jsonToken1 != m) {
                        m = 2;
                        if (jsonToken1 != m) {
                            m = 3;
                            if (jsonToken1 != m) {
                                m = 4;
                                if (jsonToken1 != m) {
                                    j = 32;
                                    if (i3 < j) {
                                        final String str = "string value";
                                        this._throwUnquotedSpace(i3, str);
                                    } else {
                                        this._reportInvalidChar(i3);
                                    }
                                    j = i2;
                                } else {
                                    final byte[] arrayOfByte1 = _inputBuffer;
                                    n = j + 2;
                                    i2 = arrayOfByte1[i2];
                                    final int i4 = j + 3;
                                    n = arrayOfByte1[n];
                                    j += 4;
                                    m = arrayOfByte1[i4];
                                    m = this._decodeUTF8_4(i3, i2, n, m);
                                    n = i + 1;
                                    i2 = m >> 10;
                                    i3 = 55296;
                                    i2 = (char)(i2 | i3);
                                    arrayOfChar1[i] = i2;
                                    i = arrayOfChar1.length;
                                    if (n >= i) {
                                        arrayOfChar1 = _textBuffer.finishCurrentSegment();
                                        i = 0;
                                        textBuffer = null;
                                    } else {
                                        i = n;
                                    }
                                    m &= 0x3FF;
                                    n = 56320;
                                    i3 = m | n;
                                }
                            } else {
                                final byte[] arrayOfByte1 = _inputBuffer;
                                n = j + 2;
                                i2 = arrayOfByte1[i2];
                                j += 3;
                                m = arrayOfByte1[n];
                                i3 = this._decodeUTF8_3(i3, i2, m);
                            }
                        } else {
                            final byte[] arrayOfByte1 = _inputBuffer;
                            j += 2;
                            m = arrayOfByte1[i2];
                            i3 = this._decodeUTF8_2(i3, m);
                        }
                    } else {
                        _inputPtr = i2;
                        i3 = this._decodeFastCharEscape();
                        j = _inputPtr;
                    }
                    m = arrayOfChar1.length;
                    if (i >= m) {
                        arrayOfChar1 = _textBuffer.finishCurrentSegment();
                    } else {
                        i1 = i;
                    }
                    i = i1 + 1;
                    m = (char)i3;
                    arrayOfChar1[i1] = m;
                    break;
                }
                j = i + 1;
                i3 = (char)i3;
                arrayOfChar1[i] = i3;
                i = j;
                j = i2;
            }
        }
    }

    private JsonToken _finishUnquotedName(int paramInt1, int paramInt2, int paramInt3) {
        int[] arrayOfInt1 = _quadBuffer;
        final int[] arrayOfInt2 = CharTypes.getInputCodeUtf8JsNames();
        while (true) {
            int i = _inputPtr;
            int j = _inputEnd;
            if (i >= j) {
                _quadLength = paramInt1;
                _pending32 = paramInt2;
                _pendingBytes = paramInt3;
                _minorState = 10;
                final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            final byte[] arrayOfByte = _inputBuffer;
            j = arrayOfByte[i] & 0xFF;
            final int k = arrayOfInt2[j];
            if (0 != k) {
                if (0 < paramInt3) {
                    int n = arrayOfInt1.length;
                    if (paramInt1 >= n) {
                        n = arrayOfInt1.length;
                        arrayOfInt1 = growArrayBy(arrayOfInt1, n);
                        _quadBuffer = arrayOfInt1;
                    }
                    n = paramInt1 + 1;
                    arrayOfInt1[paramInt1] = paramInt2;
                    paramInt1 = n;
                }
                String str = _symbols.findName(arrayOfInt1, paramInt1);
                if (null == str)
                    str = this._addName(arrayOfInt1, paramInt1, paramInt3);
                return this._fieldComplete(str);
            }
            ++i;
            _inputPtr = i;
            i = 4;
            if (paramInt3 < i) {
                paramInt3++;
                paramInt2 = paramInt2 << 8 | j;
                continue;
            }
            paramInt3 = arrayOfInt1.length;
            if (paramInt1 >= paramInt3) {
                paramInt3 = arrayOfInt1.length;
                arrayOfInt1 = growArrayBy(arrayOfInt1, paramInt3);
                _quadBuffer = arrayOfInt1;
            }
            paramInt3 = paramInt1 + 1;
            arrayOfInt1[paramInt1] = paramInt2;
            paramInt2 = j;
            final int m = paramInt3;
            paramInt3 = 1;
            paramInt1 = m;
        }
    }

    private JsonToken _handleOddName(final int paramInt) {
        int i = 35;
        int j = 4;
        if (paramInt != i) {
            i = 39;
            if (paramInt != i) {
                i = 47;
                if (paramInt != i) {
                    i = 93;
                    if (paramInt == i)
                        return this._closeArrayScope();
                } else {
                    return this._startSlashComment(j);
                }
            } else {
                i = this._features;
                j = NonBlockingJsonParser.FEAT_MASK_ALLOW_SINGLE_QUOTES;
                i &= j;
                if (0 != i)
                    return this._finishAposName(0, 0, 0);
            }
        } else {
            i = this._features;
            final int k = NonBlockingJsonParser.FEAT_MASK_ALLOW_YAML_COMMENTS;
            i &= k;
            if (0 != i)
                return this._finishHashComment(j);
        }
        i = this._features;
        j = NonBlockingJsonParser.FEAT_MASK_ALLOW_UNQUOTED_NAMES;
        i &= j;
        if (0 == i) {
            i = (char)paramInt;
            final String str = "was expecting double-quote to start field name";
            this._reportUnexpectedChar(i, str);
        }
        final int[] arrayOfInt = CharTypes.getInputCodeUtf8JsNames();
        i = arrayOfInt[paramInt];
        if (0 != i) {
            final String str = "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name";
            this._reportUnexpectedChar(paramInt, str);
        }
        return this._finishUnquotedName(0, paramInt, 1);
    }

    private final JsonToken _parseEscapedName(final int paramInt1, final int paramInt2, final int paramInt3) {
        // Byte code:
        //   0: aload_0
        //   1: getfield _quadBuffer : [I
        //   4: astore #4
        //   6: getstatic com/fasterxml/jackson/core/json/async/NonBlockingJsonParser._icLatin1 : [I
        //   9: astore #5
        //   11: aload_0
        //   12: getfield _inputPtr : I
        //   15: istore #6
        //   17: aload_0
        //   18: getfield _inputEnd : I
        //   21: istore #7
        //   23: bipush #7
        //   25: istore #8
        //   27: iload #6
        //   29: iload #7
        //   31: if_icmplt -> 69
        //   34: aload_0
        //   35: iload_1
        //   36: putfield _quadLength : I
        //   39: aload_0
        //   40: iload_2
        //   41: putfield _pending32 : I
        //   44: aload_0
        //   45: iload_3
        //   46: putfield _pendingBytes : I
        //   49: aload_0
        //   50: iload #8
        //   52: putfield _minorState : I
        //   55: getstatic com/fasterxml/jackson/core/JsonToken.NOT_AVAILABLE : Lcom/fasterxml/jackson/core/JsonToken;
        //   58: astore #9
        //   60: aload_0
        //   61: aload #9
        //   63: putfield _currToken : Lcom/fasterxml/jackson/core/JsonToken;
        //   66: aload #9
        //   68: areturn
        //   69: aload_0
        //   70: getfield _inputBuffer : [B
        //   73: astore #10
        //   75: iload #6
        //   77: iconst_1
        //   78: iadd
        //   79: istore #11
        //   81: aload_0
        //   82: iload #11
        //   84: putfield _inputPtr : I
        //   87: aload #10
        //   89: iload #6
        //   91: baload
        //   92: sipush #255
        //   95: iand
        //   96: istore #6
        //   98: aload #5
        //   100: iload #6
        //   102: iaload
        //   103: istore #7
        //   105: iconst_4
        //   106: istore #11
        //   108: iconst_1
        //   109: istore #12
        //   111: iload #7
        //   113: ifne -> 188
        //   116: iload_3
        //   117: iload #11
        //   119: if_icmpge -> 137
        //   122: iload_3
        //   123: iconst_1
        //   124: iadd
        //   125: istore_3
        //   126: iload_2
        //   127: bipush #8
        //   129: ishl
        //   130: iload #6
        //   132: ior
        //   133: istore_2
        //   134: goto -> 11
        //   137: aload #4
        //   139: arraylength
        //   140: istore_3
        //   141: iload_1
        //   142: iload_3
        //   143: if_icmplt -> 168
        //   146: aload #4
        //   148: arraylength
        //   149: istore_3
        //   150: aload #4
        //   152: iload_3
        //   153: invokestatic growArrayBy : ([II)[I
        //   156: astore #13
        //   158: aload_0
        //   159: aload #13
        //   161: putfield _quadBuffer : [I
        //   164: aload #13
        //   166: astore #4
        //   168: iload_1
        //   169: iconst_1
        //   170: iadd
        //   171: istore_3
        //   172: aload #4
        //   174: iload_1
        //   175: iload_2
        //   176: iastore
        //   177: iload_3
        //   178: istore_1
        //   179: iload #6
        //   181: istore_2
        //   182: iload #12
        //   184: istore_3
        //   185: goto -> 11
        //   188: bipush #34
        //   190: istore #7
        //   192: iload #6
        //   194: iload #7
        //   196: if_icmpne -> 301
        //   199: iload_3
        //   200: ifle -> 256
        //   203: aload #4
        //   205: arraylength
        //   206: istore #14
        //   208: iload_1
        //   209: iload #14
        //   211: if_icmplt -> 234
        //   214: aload #4
        //   216: arraylength
        //   217: istore #14
        //   219: aload #4
        //   221: iload #14
        //   223: invokestatic growArrayBy : ([II)[I
        //   226: astore #4
        //   228: aload_0
        //   229: aload #4
        //   231: putfield _quadBuffer : [I
        //   234: iload_1
        //   235: iconst_1
        //   236: iadd
        //   237: istore #14
        //   239: iload_2
        //   240: iload_3
        //   241: invokestatic _padLastQuad : (II)I
        //   244: istore_2
        //   245: aload #4
        //   247: iload_1
        //   248: iload_2
        //   249: iastore
        //   250: iload #14
        //   252: istore_1
        //   253: goto -> 267
        //   256: iload_1
        //   257: ifne -> 267
        //   260: aload_0
        //   261: ldc ''
        //   263: invokevirtual _fieldComplete : (Ljava/lang/String;)Lcom/fasterxml/jackson/core/JsonToken;
        //   266: areturn
        //   267: aload_0
        //   268: getfield _symbols : Lcom/fasterxml/jackson/core/sym/ByteQuadsCanonicalizer;
        //   271: aload #4
        //   273: iload_1
        //   274: invokevirtual findName : ([II)Ljava/lang/String;
        //   277: astore #15
        //   279: aload #15
        //   281: ifnonnull -> 294
        //   284: aload_0
        //   285: aload #4
        //   287: iload_1
        //   288: iload_3
        //   289: invokevirtual _addName : ([III)Ljava/lang/String;
        //   292: astore #15
        //   294: aload_0
        //   295: aload #15
        //   297: invokevirtual _fieldComplete : (Ljava/lang/String;)Lcom/fasterxml/jackson/core/JsonToken;
        //   300: areturn
        //   301: bipush #92
        //   303: istore #7
        //   305: bipush #8
        //   307: istore #16
        //   309: iload #6
        //   311: iload #7
        //   313: if_icmpeq -> 331
        //   316: ldc 'name'
        //   318: astore #10
        //   320: aload_0
        //   321: iload #6
        //   323: aload #10
        //   325: invokevirtual _throwUnquotedSpace : (ILjava/lang/String;)V
        //   328: goto -> 383
        //   331: aload_0
        //   332: invokespecial _decodeCharEscape : ()I
        //   335: istore #6
        //   337: iload #6
        //   339: ifge -> 383
        //   342: aload_0
        //   343: iload #16
        //   345: putfield _minorState : I
        //   348: aload_0
        //   349: iload #8
        //   351: putfield _minorStateAfterSplit : I
        //   354: aload_0
        //   355: iload_1
        //   356: putfield _quadLength : I
        //   359: aload_0
        //   360: iload_2
        //   361: putfield _pending32 : I
        //   364: aload_0
        //   365: iload_3
        //   366: putfield _pendingBytes : I
        //   369: getstatic com/fasterxml/jackson/core/JsonToken.NOT_AVAILABLE : Lcom/fasterxml/jackson/core/JsonToken;
        //   372: astore #9
        //   374: aload_0
        //   375: aload #9
        //   377: putfield _currToken : Lcom/fasterxml/jackson/core/JsonToken;
        //   380: aload #9
        //   382: areturn
        //   383: aload #4
        //   385: arraylength
        //   386: istore #7
        //   388: iload_1
        //   389: iload #7
        //   391: if_icmplt -> 414
        //   394: aload #4
        //   396: arraylength
        //   397: istore #7
        //   399: aload #4
        //   401: iload #7
        //   403: invokestatic growArrayBy : ([II)[I
        //   406: astore #4
        //   408: aload_0
        //   409: aload #4
        //   411: putfield _quadBuffer : [I
        //   414: bipush #127
        //   416: istore #7
        //   418: iload #6
        //   420: iload #7
        //   422: if_icmple -> 592
        //   425: iconst_0
        //   426: istore #7
        //   428: aconst_null
        //   429: astore #10
        //   431: iload_3
        //   432: iload #11
        //   434: if_icmplt -> 458
        //   437: iload_1
        //   438: iconst_1
        //   439: iadd
        //   440: istore_3
        //   441: aload #4
        //   443: iload_1
        //   444: iload_2
        //   445: iastore
        //   446: iload_3
        //   447: istore_1
        //   448: iconst_0
        //   449: istore_2
        //   450: aconst_null
        //   451: astore #15
        //   453: iconst_0
        //   454: istore_3
        //   455: aconst_null
        //   456: astore #13
        //   458: sipush #2048
        //   461: istore #8
        //   463: iload #6
        //   465: iload #8
        //   467: if_icmpge -> 498
        //   470: iload_2
        //   471: bipush #8
        //   473: ishl
        //   474: istore_2
        //   475: iload #6
        //   477: bipush #6
        //   479: ishr
        //   480: sipush #192
        //   483: ior
        //   484: istore #7
        //   486: iload_2
        //   487: iload #7
        //   489: ior
        //   490: istore_2
        //   491: iload_3
        //   492: iconst_1
        //   493: iadd
        //   494: istore_3
        //   495: goto -> 581
        //   498: iload_2
        //   499: bipush #8
        //   501: ishl
        //   502: istore_2
        //   503: iload #6
        //   505: bipush #12
        //   507: ishr
        //   508: sipush #224
        //   511: ior
        //   512: istore #8
        //   514: iload_2
        //   515: iload #8
        //   517: ior
        //   518: istore_2
        //   519: iload_3
        //   520: iconst_1
        //   521: iadd
        //   522: istore_3
        //   523: iload_3
        //   524: iload #11
        //   526: if_icmplt -> 548
        //   529: iload_1
        //   530: iconst_1
        //   531: iadd
        //   532: istore_3
        //   533: aload #4
        //   535: iload_1
        //   536: iload_2
        //   537: iastore
        //   538: iload_3
        //   539: istore_1
        //   540: iconst_0
        //   541: istore_3
        //   542: aconst_null
        //   543: astore #13
        //   545: goto -> 551
        //   548: iload_2
        //   549: istore #7
        //   551: iload #7
        //   553: bipush #8
        //   555: ishl
        //   556: istore_2
        //   557: iload #6
        //   559: bipush #6
        //   561: ishr
        //   562: bipush #63
        //   564: iand
        //   565: sipush #128
        //   568: ior
        //   569: istore #7
        //   571: iload_2
        //   572: iload #7
        //   574: ior
        //   575: istore_2
        //   576: iload_3
        //   577: iload #12
        //   579: iadd
        //   580: istore_3
        //   581: iload #6
        //   583: bipush #63
        //   585: iand
        //   586: sipush #128
        //   589: ior
        //   590: istore #6
        //   592: iload_3
        //   593: iload #11
        //   595: if_icmpge -> 601
        //   598: goto -> 122
        //   601: iload_1
        //   602: iconst_1
        //   603: iadd
        //   604: istore_3
        //   605: aload #4
        //   607: iload_1
        //   608: iload_2
        //   609: iastore
        //   610: goto -> 177
    }

    private final String _parseMediumName(int paramInt1, int paramInt2) {
        final byte[] arrayOfByte = _inputBuffer;
        final int[] arrayOfInt = NonBlockingJsonParser._icLatin1;
        int i = paramInt1 + 1;
        int j = arrayOfByte[paramInt1] & 0xFF;
        int k = arrayOfInt[j];
        final byte b = 34;
        if (0 == k) {
            paramInt2 = paramInt2 << 8 | j;
            j = paramInt1 + 2;
            i = arrayOfByte[i] & 0xFF;
            k = arrayOfInt[i];
            if (0 == k) {
                paramInt2 = paramInt2 << 8 | i;
                i = paramInt1 + 3;
                j = arrayOfByte[j] & 0xFF;
                k = arrayOfInt[j];
                if (0 == k) {
                    paramInt2 = paramInt2 << 8 | j;
                    j = 4;
                    paramInt1 += j;
                    final int m = arrayOfByte[i] & 0xFF;
                    final int n = arrayOfInt[m];
                    if (0 == n)
                        return this._parseMediumName2(paramInt1, m, paramInt2);
                    if (m == b) {
                        _inputPtr = paramInt1;
                        paramInt1 = _quad1;
                        return this._findName(paramInt1, paramInt2, j);
                    }
                    return null;
                }
                if (j == b) {
                    _inputPtr = i;
                    paramInt1 = _quad1;
                    return this._findName(paramInt1, paramInt2, 3);
                }
                return null;
            }
            if (i == b) {
                _inputPtr = j;
                paramInt1 = _quad1;
                return this._findName(paramInt1, paramInt2, 2);
            }
            return null;
        }
        if (j == b) {
            _inputPtr = i;
            paramInt1 = _quad1;
            return this._findName(paramInt1, paramInt2, 1);
        }
        return null;
    }

    private final String _parseMediumName2(int paramInt1, int paramInt2, final int paramInt3) {
        final byte[] arrayOfByte = _inputBuffer;
        final int[] arrayOfInt = NonBlockingJsonParser._icLatin1;
        int i = paramInt1 + 1;
        int j = arrayOfByte[paramInt1] & 0xFF;
        int k = arrayOfInt[j];
        final byte b = 34;
        if (0 != k) {
            if (j == b) {
                _inputPtr = i;
                paramInt1 = _quad1;
                return this._findName(paramInt1, paramInt3, paramInt2, 1);
            }
            return null;
        }
        paramInt2 = paramInt2 << 8 | j;
        j = paramInt1 + 2;
        i = arrayOfByte[i] & 0xFF;
        k = arrayOfInt[i];
        if (0 != k) {
            if (i == b) {
                _inputPtr = j;
                paramInt1 = _quad1;
                return this._findName(paramInt1, paramInt3, paramInt2, 2);
            }
            return null;
        }
        paramInt2 = paramInt2 << 8 | i;
        i = paramInt1 + 3;
        j = arrayOfByte[j] & 0xFF;
        int m = arrayOfInt[j];
        if (0 != m) {
            if (j == b) {
                _inputPtr = i;
                paramInt1 = _quad1;
                return this._findName(paramInt1, paramInt3, paramInt2, 3);
            }
            return null;
        }
        paramInt2 = paramInt2 << 8 | j;
        m = 4;
        paramInt1 += m;
        final int n = arrayOfByte[i] & 0xFF;
        if (n == b) {
            _inputPtr = paramInt1;
            paramInt1 = _quad1;
            return this._findName(paramInt1, paramInt3, paramInt2, m);
        }
        return null;
    }

    private final int _skipWS(int paramInt) {
        while (true) {
            final byte b = 32;
            if (paramInt != b) {
                byte b1 = 10;
                if (paramInt == b1) {
                    paramInt = _currInputRow + 1;
                    _currInputRow = paramInt;
                    paramInt = _inputPtr;
                    _currInputRowStart = paramInt;
                } else {
                    b1 = 13;
                    if (paramInt == b1) {
                        paramInt = _currInputRowAlt + 1;
                        _currInputRowAlt = paramInt;
                        paramInt = _inputPtr;
                        _currInputRowStart = paramInt;
                    } else {
                        b1 = 9;
                        if (paramInt != b1)
                            this._throwInvalidSpace(paramInt);
                    }
                }
            }
            paramInt = _inputPtr;
            final int i = _inputEnd;
            if (paramInt >= i) {
                final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return 0;
            }
            final byte[] arrayOfByte = _inputBuffer;
            final int j = paramInt + 1;
            _inputPtr = j;
            paramInt = arrayOfByte[paramInt] & 0xFF;
            if (paramInt > b)
                return paramInt;
        }
    }

    private final JsonToken _startAfterComment(final int paramInt) {
        int i = _inputPtr;
        int j = _inputEnd;
        if (i >= j) {
            _minorState = paramInt;
            final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        final byte[] arrayOfByte = _inputBuffer;
        final int k = i + 1;
        _inputPtr = k;
        i = arrayOfByte[i] & 0xFF;
        j = 4;
        if (paramInt != j) {
            j = 5;
            if (paramInt != j) {
                switch (paramInt) {
                    default:
                        VersionUtil.throwInternal();
                        return null;
                    case 15:
                        return this._startValueAfterComma(i);
                    case 14:
                        return this._startValueExpectColon(i);
                    case 13:
                        return this._startValueExpectComma(i);
                    case 12:
                        break;
                }
                return this._startValue(i);
            }
            return this._startFieldNameAfterComma(i);
        }
        return this._startFieldName(i);
    }

    private final JsonToken _startDocument(int paramInt) {
        paramInt &= 0xFF;
        int i = 239;
        final byte b = 1;
        if (paramInt == i) {
            i = _minorState;
            if (i != b)
                return this._finishBOM(b);
        }
        while (true) {
            int j;
            i = 32;
            if (paramInt <= i) {
                boolean bool;
                if (paramInt != i) {
                    i = 10;
                    if (paramInt == i) {
                        paramInt = _currInputRow + b;
                        _currInputRow = paramInt;
                        paramInt = _inputPtr;
                        _currInputRowStart = paramInt;
                    } else {
                        i = 13;
                        if (paramInt == i) {
                            paramInt = _currInputRowAlt + b;
                            _currInputRowAlt = paramInt;
                            paramInt = _inputPtr;
                            _currInputRowStart = paramInt;
                        } else {
                            i = 9;
                            if (paramInt != i)
                                this._throwInvalidSpace(paramInt);
                        }
                    }
                }
                paramInt = _inputPtr;
                i = _inputEnd;
                if (paramInt >= i) {
                    _minorState = 3;
                    bool = _closed;
                    if (bool)
                        return null;
                    bool = _endOfInput;
                    return bool ? this._eofAsNextToken() : JsonToken.NOT_AVAILABLE;
                }
                final byte[] arrayOfByte = _inputBuffer;
                final int k = bool + 1;
                _inputPtr = k;
                j = arrayOfByte[bool] & 0xFF;
                continue;
            }
            return this._startValue(j);
        }
    }

    private final JsonToken _startFieldName(int paramInt) {
        int i = 32;
        if (paramInt <= i) {
            paramInt = this._skipWS(paramInt);
            if (0 >= paramInt) {
                _minorState = 4;
                return this._currToken;
            }
        }
        this._updateTokenLocation();
        i = 34;
        if (paramInt != i) {
            i = 125;
            return (paramInt == i) ? this._closeObjectScope() : this._handleOddName(paramInt);
        }
        paramInt = _inputPtr + 13;
        i = _inputEnd;
        if (paramInt <= i) {
            final String str = this._fastParseName();
            if (null != str)
                return this._fieldComplete(str);
        }
        return this._parseEscapedName(0, 0, 0);
    }

    private final JsonToken _startFieldNameAfterComma(int paramInt) {
        int i = 5;
        int j = 32;
        if (paramInt <= j) {
            paramInt = this._skipWS(paramInt);
            if (0 >= paramInt) {
                _minorState = i;
                return this._currToken;
            }
        }
        byte b1 = 44;
        final byte b2 = 125;
        if (paramInt != b1) {
            if (paramInt == b2)
                return this._closeObjectScope();
            b1 = 35;
            if (paramInt == b1)
                return this._finishHashComment(i);
            b1 = 47;
            if (paramInt == b1)
                return this._startSlashComment(i);
            final StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("was expecting comma to separate ");
            String str2 = _parsingContext.typeDesc();
            stringBuilder.append(str2);
            str2 = " entries";
            stringBuilder.append(str2);
            final String str1 = stringBuilder.toString();
            this._reportUnexpectedChar(paramInt, str1);
        }
        paramInt = _inputPtr;
        i = _inputEnd;
        b1 = 4;
        if (paramInt >= i) {
            _minorState = b1;
            final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        final byte[] arrayOfByte = _inputBuffer;
        i = arrayOfByte[paramInt];
        ++paramInt;
        _inputPtr = paramInt;
        if (i <= j) {
            i = this._skipWS(i);
            if (0 >= i) {
                _minorState = b1;
                return this._currToken;
            }
        }
        this._updateTokenLocation();
        paramInt = 34;
        if (i != paramInt) {
            if (i == b2) {
                paramInt = this._features;
                j = NonBlockingJsonParser.FEAT_MASK_TRAILING_COMMA;
                paramInt &= j;
                if (0 != paramInt)
                    return this._closeObjectScope();
            }
            return this._handleOddName(i);
        }
        paramInt = _inputPtr + 13;
        i = _inputEnd;
        if (paramInt <= i) {
            final String str = this._fastParseName();
            if (null != str)
                return this._fieldComplete(str);
        }
        return this._parseEscapedName(0, 0, 0);
    }

    private final JsonToken _startSlashComment(int paramInt) {
        int i = this._features;
        int j = NonBlockingJsonParser.FEAT_MASK_ALLOW_JAVA_COMMENTS;
        i &= j;
        j = 47;
        if (0 == i) {
            final String str = "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)";
            this._reportUnexpectedChar(j, str);
        }
        i = _inputPtr;
        int k = _inputEnd;
        if (i >= k) {
            _pending32 = paramInt;
            _minorState = 51;
            final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        final byte[] arrayOfByte = _inputBuffer;
        final int m = i + 1;
        _inputPtr = m;
        i = arrayOfByte[i];
        k = 42;
        if (i == k)
            return this._finishCComment(paramInt, false);
        if (i == j)
            return this._finishCppComment(paramInt);
        paramInt = i & 0xFF;
        this._reportUnexpectedChar(paramInt, "was expecting either '*' or '/' for a comment");
        return null;
    }

    private final JsonToken _startValue(int paramInt) {
        byte b1 = 32;
        final byte b2 = 12;
        if (paramInt <= b1) {
            paramInt = this._skipWS(paramInt);
            if (0 >= paramInt) {
                _minorState = b2;
                return this._currToken;
            }
        }
        this._updateTokenLocation();
        final JsonReadContext jsonReadContext = _parsingContext;
        jsonReadContext.expectComma();
        b1 = 34;
        if (paramInt == b1)
            return this._startString();
        b1 = 35;
        if (paramInt != b1) {
            b1 = 91;
            if (paramInt != b1) {
                b1 = 93;
                if (paramInt != b1) {
                    b1 = 102;
                    if (paramInt != b1) {
                        b1 = 110;
                        if (paramInt != b1) {
                            b1 = 116;
                            if (paramInt != b1) {
                                b1 = 123;
                                if (paramInt != b1) {
                                    b1 = 125;
                                    if (paramInt != b1) {
                                        final boolean bool;
                                        final Feature feature;
                                        switch (paramInt) {
                                            default:
                                                return this._startUnexpectedValue(false, paramInt);
                                            case 49:
                                            case 50:
                                            case 51:
                                            case 52:
                                            case 53:
                                            case 54:
                                            case 55:
                                            case 56:
                                            case 57:
                                                return this._startPositiveNumber(paramInt);
                                            case 48:
                                                return this._startNumberLeadingZero();
                                            case 47:
                                                return this._startSlashComment(b2);
                                            case 46:
                                                feature = JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature();
                                                bool = this.isEnabled(feature);
                                                if (bool)
                                                    return this._startFloatThatStartsWithPeriod();
                                            case 45:
                                                break;
                                        }
                                        return this._startNegativeNumber();
                                    }
                                    return this._closeObjectScope();
                                }
                                return this._startObjectScope();
                            }
                            return this._startTrueToken();
                        }
                        return this._startNullToken();
                    }
                    return this._startFalseToken();
                }
                return this._closeArrayScope();
            }
            return this._startArrayScope();
        }
        return this._finishHashComment(b2);
    }

    private final JsonToken _startValueAfterComma(int paramInt) {
        int i = 32;
        int j = 15;
        if (paramInt <= i) {
            paramInt = this._skipWS(paramInt);
            if (0 >= paramInt) {
                _minorState = j;
                return this._currToken;
            }
        }
        this._updateTokenLocation();
        i = 34;
        if (paramInt == i)
            return this._startString();
        i = 35;
        if (paramInt != i) {
            i = 45;
            if (paramInt != i) {
                i = 91;
                if (paramInt != i) {
                    i = 93;
                    if (paramInt != i) {
                        i = 102;
                        if (paramInt != i) {
                            i = 110;
                            if (paramInt != i) {
                                i = 116;
                                if (paramInt != i) {
                                    i = 123;
                                    if (paramInt != i) {
                                        i = 125;
                                        if (paramInt != i) {
                                            switch (paramInt) {
                                                default:
                                                    return this._startUnexpectedValue(true, paramInt);
                                                case 49:
                                                case 50:
                                                case 51:
                                                case 52:
                                                case 53:
                                                case 54:
                                                case 55:
                                                case 56:
                                                case 57:
                                                    return this._startPositiveNumber(paramInt);
                                                case 48:
                                                    return this._startNumberLeadingZero();
                                                case 47:
                                                    break;
                                            }
                                            return this._startSlashComment(j);
                                        }
                                        i = this._features;
                                        j = NonBlockingJsonParser.FEAT_MASK_TRAILING_COMMA;
                                        i &= j;
                                        if (0 != i)
                                            return this._closeObjectScope();
                                    }
                                    return this._startObjectScope();
                                }
                                return this._startTrueToken();
                            }
                            return this._startNullToken();
                        }
                        return this._startFalseToken();
                    }
                    i = this._features;
                    j = NonBlockingJsonParser.FEAT_MASK_TRAILING_COMMA;
                    i &= j;
                    if (0 != i)
                        return this._closeArrayScope();
                }
                return this._startArrayScope();
            }
            return this._startNegativeNumber();
        }
        return this._finishHashComment(j);
    }

    private final JsonToken _startValueExpectColon(int paramInt) {
        int i = 14;
        final byte b1 = 32;
        if (paramInt <= b1) {
            paramInt = this._skipWS(paramInt);
            if (0 >= paramInt) {
                _minorState = i;
                return this._currToken;
            }
        }
        byte b2 = 58;
        final byte b3 = 35;
        if (paramInt != b2) {
            b2 = 47;
            if (paramInt == b2)
                return this._startSlashComment(i);
            if (paramInt == b3)
                return this._finishHashComment(i);
            final String str = "was expecting a colon to separate field name and value";
            this._reportUnexpectedChar(paramInt, str);
        }
        paramInt = _inputPtr;
        i = _inputEnd;
        b2 = 12;
        if (paramInt >= i) {
            _minorState = b2;
            final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        final byte[] arrayOfByte = _inputBuffer;
        i = arrayOfByte[paramInt];
        ++paramInt;
        _inputPtr = paramInt;
        if (i <= b1) {
            i = this._skipWS(i);
            if (0 >= i) {
                _minorState = b2;
                return this._currToken;
            }
        }
        this._updateTokenLocation();
        paramInt = 34;
        if (i == paramInt)
            return this._startString();
        if (i != b3) {
            paramInt = 45;
            if (i != paramInt) {
                paramInt = 91;
                if (i != paramInt) {
                    paramInt = 102;
                    if (i != paramInt) {
                        paramInt = 110;
                        if (i != paramInt) {
                            paramInt = 116;
                            if (i != paramInt) {
                                paramInt = 123;
                                if (i != paramInt) {
                                    switch (i) {
                                        default:
                                            return this._startUnexpectedValue(false, i);
                                        case 49:
                                        case 50:
                                        case 51:
                                        case 52:
                                        case 53:
                                        case 54:
                                        case 55:
                                        case 56:
                                        case 57:
                                            return this._startPositiveNumber(i);
                                        case 48:
                                            return this._startNumberLeadingZero();
                                        case 47:
                                            break;
                                    }
                                    return this._startSlashComment(b2);
                                }
                                return this._startObjectScope();
                            }
                            return this._startTrueToken();
                        }
                        return this._startNullToken();
                    }
                    return this._startFalseToken();
                }
                return this._startArrayScope();
            }
            return this._startNegativeNumber();
        }
        return this._finishHashComment(b2);
    }

    private final JsonToken _startValueExpectComma(int paramInt) {
        int i = 13;
        int j = 32;
        if (paramInt <= j) {
            paramInt = this._skipWS(paramInt);
            if (0 >= paramInt) {
                _minorState = i;
                return this._currToken;
            }
        }
        byte b1 = 44;
        final byte b2 = 35;
        final byte b3 = 125;
        final byte b4 = 93;
        if (paramInt != b1) {
            if (paramInt == b4)
                return this._closeArrayScope();
            if (paramInt == b3)
                return this._closeObjectScope();
            b1 = 47;
            if (paramInt == b1)
                return this._startSlashComment(i);
            if (paramInt == b2)
                return this._finishHashComment(i);
            final StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("was expecting comma to separate ");
            String str2 = _parsingContext.typeDesc();
            stringBuilder.append(str2);
            str2 = " entries";
            stringBuilder.append(str2);
            final String str1 = stringBuilder.toString();
            this._reportUnexpectedChar(paramInt, str1);
        }
        final JsonReadContext jsonReadContext = _parsingContext;
        jsonReadContext.expectComma();
        paramInt = _inputPtr;
        i = _inputEnd;
        b1 = 15;
        if (paramInt >= i) {
            _minorState = b1;
            final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        final byte[] arrayOfByte = _inputBuffer;
        i = arrayOfByte[paramInt];
        final byte b5 = 1;
        paramInt += b5;
        _inputPtr = paramInt;
        if (i <= j) {
            i = this._skipWS(i);
            if (0 >= i) {
                _minorState = b1;
                return this._currToken;
            }
        }
        this._updateTokenLocation();
        paramInt = 34;
        if (i == paramInt)
            return this._startString();
        if (i != b2) {
            paramInt = 45;
            if (i != paramInt) {
                paramInt = 91;
                if (i != paramInt) {
                    if (i != b4) {
                        paramInt = 102;
                        if (i != paramInt) {
                            paramInt = 110;
                            if (i != paramInt) {
                                paramInt = 116;
                                if (i != paramInt) {
                                    paramInt = 123;
                                    if (i != paramInt) {
                                        if (i != b3) {
                                            switch (i) {
                                                default:
                                                    return this._startUnexpectedValue(b5, i);
                                                case 49:
                                                case 50:
                                                case 51:
                                                case 52:
                                                case 53:
                                                case 54:
                                                case 55:
                                                case 56:
                                                case 57:
                                                    return this._startPositiveNumber(i);
                                                case 48:
                                                    return this._startNumberLeadingZero();
                                                case 47:
                                                    break;
                                            }
                                            return this._startSlashComment(b1);
                                        }
                                        paramInt = this._features;
                                        j = NonBlockingJsonParser.FEAT_MASK_TRAILING_COMMA;
                                        paramInt &= j;
                                        if (0 != paramInt)
                                            return this._closeObjectScope();
                                    }
                                    return this._startObjectScope();
                                }
                                return this._startTrueToken();
                            }
                            return this._startNullToken();
                        }
                        return this._startFalseToken();
                    }
                    paramInt = this._features;
                    j = NonBlockingJsonParser.FEAT_MASK_TRAILING_COMMA;
                    paramInt &= j;
                    if (0 != paramInt)
                        return this._closeArrayScope();
                }
                return this._startArrayScope();
            }
            return this._startNegativeNumber();
        }
        return this._finishHashComment(b1);
    }

    protected char _decodeEscaped() {
        VersionUtil.throwInternal();
        return ' ';
    }

    protected JsonToken _finishErrorToken() {
        while (true) {
            int i = _inputPtr;
            final int j = _inputEnd;
            if (i < j) {
                final byte[] arrayOfByte = _inputBuffer;
                final int k = i + 1;
                _inputPtr = k;
                i = (char)arrayOfByte[i];
                final boolean bool = Character.isJavaIdentifierPart(i);
                if (bool) {
                    final TextBuffer textBuffer1 = _textBuffer;
                    textBuffer1.append(i);
                    final TextBuffer textBuffer2 = _textBuffer;
                    i = textBuffer2.size();
                    final char c = '\u0100';
                    if (i < c)
                        continue;
                    continue;
                }
                final String str = _textBuffer.contentsAsString();
                return this._reportErrorToken(str);
            }
            final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
    }

    protected JsonToken _finishErrorTokenWithEOF() {
        final String str = _textBuffer.contentsAsString();
        return this._reportErrorToken(str);
    }

    protected final JsonToken _finishFieldWithEscape() {
        int i = _quoted32;
        int j = _quotedDigits;
        i = this._decodeSplitEscaped(i, j);
        j = 8;
        if (0 > i) {
            _minorState = j;
            return JsonToken.NOT_AVAILABLE;
        }
        int k = _quadLength;
        int[] arrayOfInt = _quadBuffer;
        int m = arrayOfInt.length;
        if (k >= m) {
            k = 32;
            final int[] arrayOfInt1 = growArrayBy(arrayOfInt, k);
            _quadBuffer = arrayOfInt1;
        }
        k = _pending32;
        int n = _pendingBytes;
        m = 127;
        final byte b = 4;
        int i1 = 1;
        if (i > m) {
            m = 0;
            if (n >= b) {
                arrayOfInt = _quadBuffer;
                final int i3 = _quadLength;
                final int i4 = i3 + 1;
                _quadLength = i4;
                arrayOfInt[i3] = k;
                k = 0;
                final Object object = null;
                n = 0;
                arrayOfInt = null;
            }
            int i2 = 2048;
            if (i < i2) {
                k <<= j;
                m = i >> 6 | 0xC0;
            } else {
                k <<= j;
                i2 = i >> 12 | 0xE0;
                k |= i2;
                n += i1;
                if (n >= b) {
                    arrayOfInt = _quadBuffer;
                    i2 = _quadLength;
                    final int i3 = i2 + 1;
                    _quadLength = i3;
                    arrayOfInt[i2] = k;
                    n = 0;
                    arrayOfInt = null;
                } else {
                    m = k;
                }
                k = m << 8;
                m = i >> 6 & 0x3F | 0x80;
            }
            k |= m;
            n += i1;
            i = i & 0x3F | 0x80;
        }
        if (n < b) {
            i1 += n;
            j = k << 8;
            i |= j;
        } else {
            final int[] arrayOfInt1 = _quadBuffer;
            n = _quadLength;
            m = n + 1;
            _quadLength = m;
            arrayOfInt1[n] = k;
        }
        j = _minorStateAfterSplit;
        k = 9;
        if (j == k) {
            j = _quadLength;
            return this._finishAposName(j, i, i1);
        }
        j = _quadLength;
        return this._parseEscapedName(j, i, i1);
    }

    protected JsonToken _finishFloatExponent(final boolean paramBoolean, final int paramInt) {
        // Byte code:
        //   0: iload_1
        //   1: ifeq -> 92
        //   4: bipush #32
        //   6: istore_1
        //   7: aload_0
        //   8: iload_1
        //   9: putfield _minorState : I
        //   12: bipush #45
        //   14: istore_3
        //   15: iload_2
        //   16: iload_3
        //   17: if_icmpeq -> 28
        //   20: bipush #43
        //   22: istore_3
        //   23: iload_2
        //   24: iload_3
        //   25: if_icmpne -> 92
        //   28: aload_0
        //   29: getfield _textBuffer : Lcom/fasterxml/jackson/core/util/TextBuffer;
        //   32: astore #4
        //   34: iload_2
        //   35: i2c
        //   36: istore_2
        //   37: aload #4
        //   39: iload_2
        //   40: invokevirtual append : (C)V
        //   43: aload_0
        //   44: getfield _inputPtr : I
        //   47: istore_2
        //   48: aload_0
        //   49: getfield _inputEnd : I
        //   52: istore_3
        //   53: iload_2
        //   54: iload_3
        //   55: if_icmplt -> 72
        //   58: aload_0
        //   59: iload_1
        //   60: putfield _minorState : I
        //   63: aload_0
        //   64: iconst_0
        //   65: putfield _expLength : I
        //   68: getstatic com/fasterxml/jackson/core/JsonToken.NOT_AVAILABLE : Lcom/fasterxml/jackson/core/JsonToken;
        //   71: areturn
        //   72: aload_0
        //   73: getfield _inputBuffer : [B
        //   76: astore #5
        //   78: iload_2
        //   79: iconst_1
        //   80: iadd
        //   81: istore_3
        //   82: aload_0
        //   83: iload_3
        //   84: putfield _inputPtr : I
        //   87: aload #5
        //   89: iload_2
        //   90: baload
        //   91: istore_2
        //   92: aload_0
        //   93: getfield _textBuffer : Lcom/fasterxml/jackson/core/util/TextBuffer;
        //   96: invokevirtual getBufferWithoutReset : ()[C
        //   99: astore #5
        //   101: aload_0
        //   102: getfield _textBuffer : Lcom/fasterxml/jackson/core/util/TextBuffer;
        //   105: astore #4
        //   107: aload #4
        //   109: invokevirtual getCurrentSegmentSize : ()I
        //   112: istore_3
        //   113: aload_0
        //   114: getfield _expLength : I
        //   117: istore #6
        //   119: bipush #48
        //   121: istore #7
        //   123: iload_2
        //   124: iload #7
        //   126: if_icmplt -> 240
        //   129: bipush #57
        //   131: istore #7
        //   133: iload_2
        //   134: iload #7
        //   136: if_icmpgt -> 240
        //   139: iload #6
        //   141: iconst_1
        //   142: iadd
        //   143: istore #6
        //   145: aload #5
        //   147: arraylength
        //   148: istore #7
        //   150: iload_3
        //   151: iload #7
        //   153: if_icmplt -> 165
        //   156: aload_0
        //   157: getfield _textBuffer : Lcom/fasterxml/jackson/core/util/TextBuffer;
        //   160: invokevirtual expandCurrentSegment : ()[C
        //   163: astore #5
        //   165: iload_3
        //   166: iconst_1
        //   167: iadd
        //   168: istore #7
        //   170: iload_2
        //   171: i2c
        //   172: istore_2
        //   173: aload #5
        //   175: iload_3
        //   176: iload_2
        //   177: castore
        //   178: aload_0
        //   179: getfield _inputPtr : I
        //   182: istore_2
        //   183: aload_0
        //   184: getfield _inputEnd : I
        //   187: istore_3
        //   188: iload_2
        //   189: iload_3
        //   190: if_icmplt -> 212
        //   193: aload_0
        //   194: getfield _textBuffer : Lcom/fasterxml/jackson/core/util/TextBuffer;
        //   197: iload #7
        //   199: invokevirtual setCurrentLength : (I)V
        //   202: aload_0
        //   203: iload #6
        //   205: putfield _expLength : I
        //   208: getstatic com/fasterxml/jackson/core/JsonToken.NOT_AVAILABLE : Lcom/fasterxml/jackson/core/JsonToken;
        //   211: areturn
        //   212: aload_0
        //   213: getfield _inputBuffer : [B
        //   216: astore #4
        //   218: iload_2
        //   219: iconst_1
        //   220: iadd
        //   221: istore #8
        //   223: aload_0
        //   224: iload #8
        //   226: putfield _inputPtr : I
        //   229: aload #4
        //   231: iload_2
        //   232: baload
        //   233: istore_2
        //   234: iload #7
        //   236: istore_3
        //   237: goto -> 119
        //   240: iload_2
        //   241: sipush #255
        //   244: iand
        //   245: istore_1
        //   246: iload #6
        //   248: ifne -> 263
        //   251: ldc_w 'Exponent indicator not followed by a digit'
        //   254: astore #9
        //   256: aload_0
        //   257: iload_1
        //   258: aload #9
        //   260: invokevirtual reportUnexpectedNumberChar : (ILjava/lang/String;)V
        //   263: aload_0
        //   264: getfield _inputPtr : I
        //   267: iconst_m1
        //   268: iadd
        //   269: istore_1
        //   270: aload_0
        //   271: iload_1
        //   272: putfield _inputPtr : I
        //   275: aload_0
        //   276: getfield _textBuffer : Lcom/fasterxml/jackson/core/util/TextBuffer;
        //   279: iload_3
        //   280: invokevirtual setCurrentLength : (I)V
        //   283: aload_0
        //   284: iload #6
        //   286: putfield _expLength : I
        //   289: getstatic com/fasterxml/jackson/core/JsonToken.VALUE_NUMBER_FLOAT : Lcom/fasterxml/jackson/core/JsonToken;
        //   292: astore #5
        //   294: aload_0
        //   295: aload #5
        //   297: invokevirtual _valueComplete : (Lcom/fasterxml/jackson/core/JsonToken;)Lcom/fasterxml/jackson/core/JsonToken;
        //   300: areturn
    }

    protected JsonToken _finishFloatFraction() {
        int k;
        int i = _fractLength;
        char[] arrayOfChar = _textBuffer.getBufferWithoutReset();
        final TextBuffer textBuffer1 = _textBuffer;
        int j = textBuffer1.getCurrentSegmentSize();
        while (true) {
            final byte[] arrayOfByte1 = _inputBuffer;
            int n = _inputPtr;
            final int i1 = n + 1;
            _inputPtr = i1;
            k = arrayOfByte1[n];
            n = 48;
            if (k >= n) {
                n = 57;
                if (k <= n) {
                    i++;
                    n = arrayOfChar.length;
                    if (j >= n)
                        arrayOfChar = _textBuffer.expandCurrentSegment();
                    n = j + 1;
                    final char c = (char)k;
                    arrayOfChar[j] = c;
                    j = _inputPtr;
                    k = _inputEnd;
                    if (j >= k) {
                        _textBuffer.setCurrentLength(n);
                        _fractLength = i;
                        return JsonToken.NOT_AVAILABLE;
                    }
                    j = n;
                    continue;
                }
            }
            break;
        }
        if (0 == i) {
            final String str = "Decimal point not followed by a digit";
            this.reportUnexpectedNumberChar(k, str);
        }
        _fractLength = i;
        TextBuffer textBuffer2 = _textBuffer;
        textBuffer2.setCurrentLength(j);
        i = 101;
        int m = 0;
        arrayOfChar = null;
        final byte b = 1;
        if (k != i) {
            i = 69;
            if (k != i) {
                i = _inputPtr - b;
                _inputPtr = i;
                _textBuffer.setCurrentLength(j);
                _expLength = 0;
                final JsonToken jsonToken = JsonToken.VALUE_NUMBER_FLOAT;
                return this._valueComplete(jsonToken);
            }
        }
        textBuffer2 = _textBuffer;
        j = (char)k;
        textBuffer2.append(j);
        _expLength = 0;
        i = _inputPtr;
        m = _inputEnd;
        if (i >= m) {
            _minorState = 31;
            return JsonToken.NOT_AVAILABLE;
        }
        _minorState = 32;
        final byte[] arrayOfByte = _inputBuffer;
        j = i + 1;
        _inputPtr = j;
        i = arrayOfByte[i] & 0xFF;
        return this._finishFloatExponent(b, i);
    }

    protected JsonToken _finishKeywordToken(final String paramString, int paramInt, final JsonToken paramJsonToken) {
        int i = paramString.length();
        while (true) {
            JsonToken jsonToken = null;
            int j = _inputPtr;
            int k = _inputEnd;
            if (j >= k) {
                _pending32 = paramInt;
                jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            final byte[] arrayOfByte = _inputBuffer;
            j = arrayOfByte[j];
            if (paramInt == i) {
                i = 48;
                if (j >= i) {
                    i = 93;
                    if (j != i) {
                        i = 125;
                        if (j == i) {
                            try {
                                return this._valueComplete(paramJsonToken);
                            } catch (final IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else {
                        try {
                            return this._valueComplete(paramJsonToken);
                        } catch (final IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    try {
                        return this._valueComplete(paramJsonToken);
                    } catch (final IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                k = jsonToken.charAt(paramInt);
                if (j == k) {
                    paramInt++;
                    j = _inputPtr + 1;
                    _inputPtr = j;
                    continue;
                }
            }
            _minorState = 50;
            _textBuffer.resetWithCopy((String)jsonToken, 0, paramInt);
            return this._finishErrorToken();
        }
    }

    protected JsonToken _finishKeywordTokenWithEOF(final String paramString, final int paramInt, final JsonToken paramJsonToken) {
        final int i = paramString.length();
        if (paramInt == i) {
            this._currToken = paramJsonToken;
            return paramJsonToken;
        }
        _textBuffer.resetWithCopy(paramString, 0, paramInt);
        return this._finishErrorTokenWithEOF();
    }
    protected JsonToken _finishNonStdToken(final int paramInt1, int paramInt2) {
        final String str = this._nonStdToken(paramInt1);
        int i = str.length();
        while (true) {
            int j = _inputPtr;
            int k = _inputEnd;
            if (j >= k) {
                _nonStdTokenType = paramInt1;
                _pending32 = paramInt2;
                _minorState = 19;
                final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            final byte[] arrayOfByte = _inputBuffer;
            j = arrayOfByte[j];
            if (paramInt2 == i) {
                i = 48;
                if (j >= i) {
                    i = 93;
                    if (j != i) {
                        i = 125;
                        if (j == i) {
                            try {
                                return this._valueNonStdNumberComplete(paramInt1);
                            } catch (final IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else {
                        try {
                            return this._valueNonStdNumberComplete(paramInt1);
                        } catch (final IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    try {
                        return this._valueNonStdNumberComplete(paramInt1);
                    } catch (final IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                k = str.charAt(paramInt2);
                if (j == k) {
                    paramInt2++;
                    j = _inputPtr + 1;
                    _inputPtr = j;
                    continue;
                }
            }
            _minorState = 50;
            _textBuffer.resetWithCopy(str, 0, paramInt2);
            return this._finishErrorToken();
        }
    }
    protected JsonToken _finishNonStdTokenWithEOF(final int paramInt1, final int paramInt2) {
        final String str = this._nonStdToken(paramInt1);
        final int i = str.length();
        if (paramInt2 == i) {
            try {
                return this._valueNonStdNumberComplete(paramInt1);
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }
        _textBuffer.resetWithCopy(str, 0, paramInt2);
        return this._finishErrorTokenWithEOF();
    }

    protected JsonToken _finishNumberIntegralPart(final char[] paramArrayOfchar, int paramInt) {
        boolean bool = _numberNegative;
        if (bool) {
            final byte b = -1;
        } else {
            bool = false;
        }
        while (true) {
            int i;
            int j = _inputPtr;
            int k = _inputEnd;
            JsonToken jsonToken;
            if (j >= k) {
                _minorState = 26;
                _textBuffer.setCurrentLength(paramInt);
                jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            final byte[] arrayOfByte = _inputBuffer;
            k = arrayOfByte[j] & 0xFF;
            byte b = 48;
            if (k < b) {
                b = 46;
                if (k == b) {
                    i = bool + paramInt;
                    _intLength = i;
                    ++j;
                    _inputPtr = j;
                    return _startFloat((char[])jsonToken, paramInt, k);
                }
            } else {
                char[] arrayOfChar;
                b = 57;
                if (k > b) {
                    b = 101;
                    if (k != b) {
                        b = 69;
                        if (k == b) {
                            i += paramInt;
                            _intLength = i;
                            ++j;
                            _inputPtr = j;
                            return _startFloat((char[])jsonToken, paramInt, k);
                        }
                        i += paramInt;
                        _intLength = i;
                        _textBuffer.setCurrentLength(paramInt);
                        jsonToken = JsonToken.VALUE_NUMBER_INT;
                        return this._valueComplete(jsonToken);
                    }
                    i += paramInt;
                    _intLength = i;
                    ++j;
                    _inputPtr = j;
                    return _startFloat((char[])jsonToken, paramInt, k);
                }
                ++j;
                _inputPtr = j;
                j = jsonToken.length;
                if (paramInt >= j)
                    arrayOfChar = _textBuffer.expandCurrentSegment();
                j = paramInt + 1;
                k = (char)k;
                arrayOfChar[paramInt] = k;
                paramInt = j;
                continue;
            }
            i += paramInt;
            _intLength = i;
            _textBuffer.setCurrentLength(paramInt);
            final JsonToken jsonToken = JsonToken.VALUE_NUMBER_INT;
            return this._valueComplete(jsonToken);
        }
    }

    protected JsonToken _finishNumberLeadingNegZeroes() {
        while (true) {
            int i = _inputPtr;
            int j = _inputEnd;
            if (i >= j) {
                _minorState = 25;
                final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            final byte[] arrayOfByte = _inputBuffer;
            int k = i + 1;
            _inputPtr = k;
            i = arrayOfByte[i] & 0xFF;
            j = 2;
            k = 45;
            final byte b1 = 48;
            final byte b2 = 1;
            if (i < b1) {
                final byte b = 46;
                if (i == b) {
                    final char[] arrayOfChar = _textBuffer.emptyAndGetCurrentSegment();
                    arrayOfChar[0] = k;
                    arrayOfChar[b2] = b1;
                    _intLength = b2;
                    return _startFloat(arrayOfChar, j, i);
                }
            } else {
                int m = 57;
                if (i > m) {
                    m = 101;
                    if (i != m) {
                        m = 69;
                        if (i == m) {
                            final char[] arrayOfChar2 = _textBuffer.emptyAndGetCurrentSegment();
                            arrayOfChar2[0] = k;
                            arrayOfChar2[b2] = b1;
                            _intLength = b2;
                            return _startFloat(arrayOfChar2, j, i);
                        }
                        j = 93;
                        if (i != j) {
                            j = 125;
                            if (i != j) {
                                final String str = "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'";
                                this.reportUnexpectedNumberChar(i, str);
                            }
                        }
                        i = _inputPtr - b2;
                        _inputPtr = i;
                        return this._valueCompleteInt(0, "0");
                    }
                    final char[] arrayOfChar1 = _textBuffer.emptyAndGetCurrentSegment();
                    arrayOfChar1[0] = k;
                    arrayOfChar1[b2] = b1;
                    _intLength = b2;
                    return _startFloat(arrayOfChar1, j, i);
                }
                m = this._features;
                final int n = NonBlockingJsonParser.FEAT_MASK_LEADING_ZEROS;
                m &= n;
                if (0 == m) {
                    final String str = "Leading zeroes not allowed";
                    this.reportInvalidNumber(str);
                }
                if (i == b1)
                    continue;
                final char[] arrayOfChar = _textBuffer.emptyAndGetCurrentSegment();
                arrayOfChar[0] = k;
                i = (char)i;
                arrayOfChar[b2] = i;
                _intLength = b2;
                return this._finishNumberIntegralPart(arrayOfChar, j);
            }
            i = _inputPtr - b2;
            _inputPtr = i;
            return this._valueCompleteInt(0, "0");
        }
    }

    protected JsonToken _finishNumberLeadingZeroes() {
        while (true) {
            int i = _inputPtr;
            int j = _inputEnd;
            if (i >= j) {
                _minorState = 24;
                final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            byte[] arrayOfByte = _inputBuffer;
            int k = i + 1;
            _inputPtr = k;
            i = arrayOfByte[i] & 0xFF;
            j = 0;
            arrayOfByte = null;
            k = 48;
            final byte b = 1;
            if (i < k) {
                final byte b1 = 46;
                if (i == b1) {
                    final char[] arrayOfChar = _textBuffer.emptyAndGetCurrentSegment();
                    arrayOfChar[0] = k;
                    _intLength = b;
                    return _startFloat(arrayOfChar, b, i);
                }
            } else {
                int m = 57;
                if (i > m) {
                    m = 101;
                    if (i != m) {
                        m = 69;
                        if (i == m) {
                            final char[] arrayOfChar2 = _textBuffer.emptyAndGetCurrentSegment();
                            arrayOfChar2[0] = k;
                            _intLength = b;
                            return _startFloat(arrayOfChar2, b, i);
                        }
                        k = 93;
                        if (i != k) {
                            k = 125;
                            if (i != k) {
                                final String str = "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'";
                                this.reportUnexpectedNumberChar(i, str);
                            }
                        }
                        i = _inputPtr - b;
                        _inputPtr = i;
                        return this._valueCompleteInt(0, "0");
                    }
                    final char[] arrayOfChar1 = _textBuffer.emptyAndGetCurrentSegment();
                    arrayOfChar1[0] = k;
                    _intLength = b;
                    return _startFloat(arrayOfChar1, b, i);
                }
                m = this._features;
                final int n = NonBlockingJsonParser.FEAT_MASK_LEADING_ZEROS;
                m &= n;
                if (0 == m) {
                    final String str = "Leading zeroes not allowed";
                    this.reportInvalidNumber(str);
                }
                if (i == k)
                    continue;
                final char[] arrayOfChar = _textBuffer.emptyAndGetCurrentSegment();
                i = (char)i;
                arrayOfChar[0] = i;
                _intLength = b;
                return this._finishNumberIntegralPart(arrayOfChar, b);
            }
            i = _inputPtr - b;
            _inputPtr = i;
            return this._valueCompleteInt(0, "0");
        }
    }

    protected JsonToken _finishNumberMinus(int paramInt) {
        final byte b1 = 2;
        final String str = "expected digit (0-9) to follow minus sign, for valid numeric value";
        byte b2 = 48;
        if (paramInt <= b2) {
            if (paramInt == b2)
                return this._finishNumberLeadingNegZeroes();
            this.reportUnexpectedNumberChar(paramInt, str);
        } else {
            b2 = 57;
            if (paramInt > b2) {
                b2 = 73;
                if (paramInt == b2)
                    return this._finishNonStdToken(3, b1);
                this.reportUnexpectedNumberChar(paramInt, str);
            }
        }
        final char[] arrayOfChar = _textBuffer.emptyAndGetCurrentSegment();
        arrayOfChar[0] = '-';
        paramInt = (char)paramInt;
        b2 = 1;
        arrayOfChar[b2] = paramInt;
        _intLength = b2;
        return this._finishNumberIntegralPart(arrayOfChar, b1);
    }

    protected final JsonToken _finishToken() {
        int i = _minorState;
        int j = 1;
        if (i != j) {
            int k = 4;
            if (i != k) {
                k = 5;
                if (i != k) {
                    final boolean bool2;
                    final int n;
                    final boolean bool1;
                    byte[] arrayOfByte2;
                    final int i1;
                    final int i2;
                    final int i3;
                    final TextBuffer textBuffer1;
                    final byte[] arrayOfByte4;
                    final char[] arrayOfChar;
                    final byte[] arrayOfByte3;
                    final TextBuffer textBuffer2;
                    final JsonToken jsonToken;
                    switch (i) {
                        default:
                            switch (i) {
                                default:
                                    switch (i) {
                                        default:
                                            k = 0;
                                            arrayOfByte2 = null;
                                            switch (i) {
                                                default:
                                                    i1 = 45;
                                                    switch (i) {
                                                        default:
                                                            switch (i) {
                                                                default:
                                                                    VersionUtil.throwInternal();
                                                                    return null;
                                                                case 55:
                                                                    i = _pending32;
                                                                    return this._finishHashComment(i);
                                                                case 54:
                                                                    i = _pending32;
                                                                    return this._finishCppComment(i);
                                                                case 53:
                                                                    i = _pending32;
                                                                    return this._finishCComment(i, false);
                                                                case 52:
                                                                    i = _pending32;
                                                                    return this._finishCComment(i, j);
                                                                case 51:
                                                                    i = _pending32;
                                                                    return this._startSlashComment(i);
                                                                case 50:
                                                                    break;
                                                            }
                                                            return this._finishErrorToken();
                                                        case 45:
                                                            return this._finishAposString();
                                                        case 44:
                                                            i = _pending32;
                                                            j = _pendingBytes;
                                                            arrayOfByte2 = _inputBuffer;
                                                            i2 = _inputPtr;
                                                            i3 = i2 + 1;
                                                            _inputPtr = i3;
                                                            k = arrayOfByte2[i2];
                                                            bool2 = this._decodeSplitUTF8_4(i, j, k);
                                                            if (!bool2)
                                                                return JsonToken.NOT_AVAILABLE;
                                                            n = _minorStateAfterSplit;
                                                            return (n == i1) ? this._finishAposString() : this._finishRegularString();
                                                        case 43:
                                                            n = _pending32;
                                                            j = _pendingBytes;
                                                            arrayOfByte2 = _inputBuffer;
                                                            i2 = _inputPtr;
                                                            i3 = i2 + 1;
                                                            _inputPtr = i3;
                                                            k = arrayOfByte2[i2];
                                                            bool1 = this._decodeSplitUTF8_3(n, j, k);
                                                            if (!bool1)
                                                                return JsonToken.NOT_AVAILABLE;
                                                            m = _minorStateAfterSplit;
                                                            return (m == i1) ? this._finishAposString() : this._finishRegularString();
                                                        case 42:
                                                            textBuffer1 = _textBuffer;
                                                            j = _pending32;
                                                            arrayOfByte2 = _inputBuffer;
                                                            i2 = _inputPtr;
                                                            i3 = i2 + 1;
                                                            _inputPtr = i3;
                                                            k = arrayOfByte2[i2];
                                                            j = (char) this._decodeUTF8_2(j, k);
                                                            textBuffer1.append(j);
                                                            m = _minorStateAfterSplit;
                                                            return (m == i1) ? this._finishAposString() : this._finishRegularString();
                                                        case 41:
                                                            m = _quoted32;
                                                            j = _quotedDigits;
                                                            m = this._decodeSplitEscaped(m, j);
                                                            if (0 > m)
                                                                return JsonToken.NOT_AVAILABLE;
                                                            textBuffer2 = _textBuffer;
                                                            m = (char)m;
                                                            textBuffer2.append(m);
                                                            m = _minorStateAfterSplit;
                                                            return (m == i1) ? this._finishAposString() : this._finishRegularString();
                                                        case 40:
                                                            break;
                                                    }
                                                    return this._finishRegularString();
                                                case 32:
                                                    arrayOfByte4 = _inputBuffer;
                                                    j = _inputPtr;
                                                    i1 = j + 1;
                                                    _inputPtr = i1;
                                                    m = arrayOfByte4[j] & 0xFF;
                                                    return this._finishFloatExponent(false, m);
                                                case 31:
                                                    arrayOfByte4 = _inputBuffer;
                                                    k = _inputPtr;
                                                    i1 = k + 1;
                                                    _inputPtr = i1;
                                                    m = arrayOfByte4[k] & 0xFF;
                                                    return this._finishFloatExponent(j, m);
                                                case 30:
                                                    break;
                                            }
                                            return this._finishFloatFraction();
                                        case 26:
                                            arrayOfChar = _textBuffer.getBufferWithoutReset();
                                            j = _textBuffer.getCurrentSegmentSize();
                                            return this._finishNumberIntegralPart(arrayOfChar, j);
                                        case 25:
                                            return this._finishNumberLeadingNegZeroes();
                                        case 24:
                                            return this._finishNumberLeadingZeroes();
                                        case 23:
                                            break;
                                    }
                                    arrayOfByte3 = _inputBuffer;
                                    j = _inputPtr;
                                    k = j + 1;
                                    _inputPtr = k;
                                    m = arrayOfByte3[j] & 0xFF;
                                    return this._finishNumberMinus(m);
                                case 19:
                                    m = _nonStdTokenType;
                                    j = _pending32;
                                    return this._finishNonStdToken(m, j);
                                case 18:
                                    m = _pending32;
                                    jsonToken = JsonToken.VALUE_FALSE;
                                    return this._finishKeywordToken("false", m, jsonToken);
                                case 17:
                                    m = _pending32;
                                    jsonToken = JsonToken.VALUE_TRUE;
                                    return this._finishKeywordToken("true", m, jsonToken);
                                case 16:
                                    m = _pending32;
                                    jsonToken = JsonToken.VALUE_NULL;
                                    return this._finishKeywordToken("null", m, jsonToken);
                                case 15:
                                    arrayOfByte3 = _inputBuffer;
                                    j = _inputPtr;
                                    k = j + 1;
                                    _inputPtr = k;
                                    m = arrayOfByte3[j] & 0xFF;
                                    return this._startValueAfterComma(m);
                                case 14:
                                    arrayOfByte3 = _inputBuffer;
                                    j = _inputPtr;
                                    k = j + 1;
                                    _inputPtr = k;
                                    m = arrayOfByte3[j] & 0xFF;
                                    return this._startValueExpectColon(m);
                                case 13:
                                    arrayOfByte3 = _inputBuffer;
                                    j = _inputPtr;
                                    k = j + 1;
                                    _inputPtr = k;
                                    m = arrayOfByte3[j] & 0xFF;
                                    return this._startValueExpectComma(m);
                                case 12:
                                    break;
                            }
                            arrayOfByte3 = _inputBuffer;
                            j = _inputPtr;
                            k = j + 1;
                            _inputPtr = k;
                            m = arrayOfByte3[j] & 0xFF;
                            return this._startValue(m);
                        case 10:
                            m = _quadLength;
                            j = _pending32;
                            k = _pendingBytes;
                            return this._finishUnquotedName(m, j, k);
                        case 9:
                            m = _quadLength;
                            j = _pending32;
                            k = _pendingBytes;
                            return this._finishAposName(m, j, k);
                        case 8:
                            return this._finishFieldWithEscape();
                        case 7:
                            break;
                    }
                    final int m = _quadLength;
                    j = _pending32;
                    k = _pendingBytes;
                    return this._parseEscapedName(m, j, k);
                }
                final byte[] arrayOfByte1 = _inputBuffer;
                j = _inputPtr;
                k = j + 1;
                _inputPtr = k;
                i = arrayOfByte1[j] & 0xFF;
                return this._startFieldNameAfterComma(i);
            }
            final byte[] arrayOfByte = _inputBuffer;
            j = _inputPtr;
            k = j + 1;
            _inputPtr = k;
            i = arrayOfByte[j] & 0xFF;
            return this._startFieldName(i);
        }
        i = _pending32;
        return this._finishBOM(i);
    }

    protected final JsonToken _finishTokenWithEOF() {
        final JsonToken jsonToken = this._currToken;
        final int i = _minorState;
        int j = 3;
        if (i != j) {
            j = 12;
            if (i != j) {
                j = 50;
                if (i != j) {
                    final String str1;
                    final JsonToken jsonToken2;
                    final TextBuffer textBuffer;
                    final JsonToken jsonToken1;
                    final boolean bool;
                    final int k;
                    JsonToken jsonToken3;
                    final StringBuilder stringBuilder;
                    final String str2;
                    switch (i) {
                        default:
                            j = 0;
                            jsonToken3 = null;
                            switch (i) {
                                default:
                                    switch (i) {
                                        default:
                                            switch (i) {
                                                default:
                                                    stringBuilder = new StringBuilder();
                                                    this();
                                                    stringBuilder.append(": was expecting rest of token (internal state: ");
                                                    j = _minorState;
                                                    stringBuilder.append(j);
                                                    stringBuilder.append(")");
                                                    str2 = stringBuilder.toString();
                                                    jsonToken3 = this._currToken;
                                                    this._reportInvalidEOF(str2, jsonToken3);
                                                    return jsonToken;
                                                case 52:
                                                case 53:
                                                    str1 = ": was expecting closing '*/' for comment";
                                                    jsonToken4 = JsonToken.NOT_AVAILABLE;
                                                    this._reportInvalidEOF(str1, jsonToken4);
                                                    break;
                                                case 54:
                                                case 55:
                                                    break;
                                            }
                                            return this._eofAsNextToken();
                                        case 31:
                                            str1 = ": was expecting fraction after exponent marker";
                                            jsonToken4 = JsonToken.VALUE_NUMBER_FLOAT;
                                            this._reportInvalidEOF(str1, jsonToken4);
                                        case 30:
                                            _expLength = 0;
                                            break;
                                        case 32:
                                            break;
                                    }
                                    jsonToken2 = JsonToken.VALUE_NUMBER_FLOAT;
                                    return this._valueComplete(jsonToken2);
                                case 26:
                                    textBuffer = _textBuffer;
                                    m = textBuffer.getCurrentSegmentSize();
                                    bool = _numberNegative;
                                    if (bool)
                                        m--;
                                    _intLength = m;
                                    jsonToken1 = JsonToken.VALUE_NUMBER_INT;
                                    return this._valueComplete(jsonToken1);
                                case 24:
                                case 25:
                                    break;
                            }
                            return this._valueCompleteInt(0, "0");
                        case 19:
                            m = _nonStdTokenType;
                            k = _pending32;
                            return this._finishNonStdTokenWithEOF(m, k);
                        case 18:
                            m = _pending32;
                            jsonToken4 = JsonToken.VALUE_FALSE;
                            return this._finishKeywordTokenWithEOF("false", m, jsonToken4);
                        case 17:
                            m = _pending32;
                            jsonToken4 = JsonToken.VALUE_TRUE;
                            return this._finishKeywordTokenWithEOF("true", m, jsonToken4);
                        case 16:
                            break;
                    }
                    final int m = _pending32;
                    final JsonToken jsonToken4 = JsonToken.VALUE_NULL;
                    return this._finishKeywordTokenWithEOF("null", m, jsonToken4);
                }
                return this._finishErrorTokenWithEOF();
            }
            return this._eofAsNextToken();
        }
        return this._eofAsNextToken();
    }

    protected JsonToken _reportErrorToken(String paramString) {
        paramString = _textBuffer.contentsAsString();
        String str = null;
        try {
            str = this._validJsonTokenList();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this._reportError("Unrecognized token '%s': was expecting %s", paramString, str);
        } catch (final JsonParseException e) {
            throw new RuntimeException(e);
        }
        return JsonToken.NOT_AVAILABLE;
    }
    protected JsonToken _startAposString() {
        int i = _inputPtr;
        final char[] arrayOfChar = _textBuffer.emptyAndGetCurrentSegment();
        final int[] arrayOfInt = NonBlockingJsonParser._icUTF8;
        int j = _inputEnd;
        final int k = arrayOfChar.length + i;
        j = Math.min(j, k);
        final byte[] arrayOfByte = _inputBuffer;
        int m;
        for (m = 0; i < j; m = i1) {
            int n = arrayOfByte[i] & 0xFF;
            int i1 = 39;
            if (n == i1) {
                ++i;
                _inputPtr = i;
                _textBuffer.setCurrentLength(m);
                final JsonToken jsonToken = JsonToken.VALUE_STRING;
                try {
                    return this._valueComplete(jsonToken);
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
            i1 = arrayOfInt[n];
            if (0 != i1)
                break;
            i++;
            i1 = m + 1;
            n = (char)n;
            arrayOfChar[m] = (char) n;
        }
        _textBuffer.setCurrentLength(m);
        _inputPtr = i;
        return this._finishAposString();
    }
    protected JsonToken _startFalseToken() {
        int i = _inputPtr;
        int j = i + 4;
        int k = _inputEnd;
        if (j < k) {
            final byte[] arrayOfByte = _inputBuffer;
            k = i + 1;
            final byte b = arrayOfByte[i];
            byte b1 = 97;
            if (b == b1) {
                int m = i + 2;
                k = arrayOfByte[k];
                b1 = 108;
                if (k == b1) {
                    k = i + 3;
                    m = arrayOfByte[m];
                    b1 = 115;
                    if (m == b1) {
                        i += 4;
                        k = arrayOfByte[k];
                        m = 101;
                        if (k == m) {
                            j = arrayOfByte[i] & 0xFF;
                            k = 48;
                            if (j >= k) {
                                k = 93;
                                if (j != k) {
                                    k = 125;
                                    if (j == k) {
                                        _inputPtr = i;
                                        final JsonToken jsonToken1 = JsonToken.VALUE_FALSE;
                                        try {
                                            return this._valueComplete(jsonToken1);
                                        } catch (final IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                } else {
                                    _inputPtr = i;
                                    final JsonToken jsonToken1 = JsonToken.VALUE_FALSE;
                                    try {
                                        return this._valueComplete(jsonToken1);
                                    } catch (final IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            } else {
                                _inputPtr = i;
                                final JsonToken jsonToken1 = JsonToken.VALUE_FALSE;
                                try {
                                    return this._valueComplete(jsonToken1);
                                } catch (final IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }
            }
        }
        _minorState = 18;
        final JsonToken jsonToken = JsonToken.VALUE_FALSE;
        return this._finishKeywordToken("false", 1, jsonToken);
    }
    protected JsonToken _startFloatThatStartsWithPeriod() {
        _numberNegative = false;
        _intLength = 0;
        final char[] arrayOfChar = _textBuffer.emptyAndGetCurrentSegment();
        return this._startFloatThatStartsWithPeriod();
    }
    protected JsonToken _startNegativeNumber() {
        int i = 1;
        _numberNegative = i;
        int j = _inputPtr;
        int k = _inputEnd;
        if (j >= k) {
            _minorState = 23;
            final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        final byte[] arrayOfByte1 = _inputBuffer;
        final int m = j + 1;
        _inputPtr = m;
        j = arrayOfByte1[j] & 0xFF;
        k = 57;
        final String str = "expected digit (0-9) to follow minus sign, for valid numeric value";
        int n = 2;
        final byte b1 = 48;
        if (j <= b1) {
            if (j == b1)
                return this._finishNumberLeadingNegZeroes();
            try {
                this.reportUnexpectedNumberChar(j, str);
            } catch (final JsonParseException e) {
                throw new RuntimeException(e);
            }
        } else if (j > k) {
            final byte b = 73;
            if (j == b)
                return this._finishNonStdToken(3, n);
            try {
                this.reportUnexpectedNumberChar(j, str);
            } catch (final JsonParseException e) {
                throw new RuntimeException(e);
            }
        }
        char[] arrayOfChar = _textBuffer.emptyAndGetCurrentSegment();
        byte[] arrayOfByte2 = null;
        arrayOfChar[0] = '-';
        j = (char)j;
        arrayOfChar[i] = (char) j;
        j = _inputPtr;
        int i1 = _inputEnd;
        final byte b2 = 26;
        if (j >= i1) {
            _minorState = b2;
            _textBuffer.setCurrentLength(n);
            _intLength = i;
            final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        arrayOfByte2 = _inputBuffer;
        j = arrayOfByte2[j];
        while (true) {
            if (j < b1) {
                k = 46;
                if (j == k) {
                    k = n + -1;
                    _intLength = k;
                    k = _inputPtr + i;
                    _inputPtr = k;
                    return this._startFloatThatStartsWithPeriod();
                }
            } else {
                if (j > k) {
                    k = 101;
                    if (j != k) {
                        k = 69;
                        if (j == k) {
                            k = n + -1;
                            _intLength = k;
                            k = _inputPtr + i;
                            _inputPtr = k;
                            return this._startFloatThatStartsWithPeriod(arrayOfChar, n, j);
                        }
                        i = n + -1;
                        _intLength = i;
                        _textBuffer.setCurrentLength(n);
                        final JsonToken jsonToken1 = JsonToken.VALUE_NUMBER_INT;
                        return this._valueComplete(jsonToken1);
                    }
                    k = n + -1;
                    _intLength = k;
                    k = _inputPtr + i;
                    _inputPtr = k;
                    return this._startFloatThatStartsWithPeriod(arrayOfChar, n, j);
                }
                i1 = arrayOfChar.length;
                if (n >= i1)
                    arrayOfChar = _textBuffer.expandCurrentSegment();
                i1 = n + 1;
                j = (char)j;
                arrayOfChar[n] = j;
                j = _inputPtr + i;
                _inputPtr = j;
                n = _inputEnd;
                if (j >= n) {
                    _minorState = b2;
                    _textBuffer.setCurrentLength(i1);
                    final JsonToken jsonToken1 = JsonToken.NOT_AVAILABLE;
                    this._currToken = jsonToken1;
                    return jsonToken1;
                }
                final byte[] arrayOfByte = _inputBuffer;
                j = arrayOfByte[j] & 0xFF;
                n = i1;
                continue;
            }
            i = n + -1;
            _intLength = i;
            _textBuffer.setCurrentLength(n);
            final JsonToken jsonToken = JsonToken.VALUE_NUMBER_INT;
            return this._valueComplete(jsonToken);
        }
    }
    protected JsonToken _startNullToken() {
        int i = _inputPtr;
        int j = i + 3;
        int k = _inputEnd;
        if (j < k) {
            final byte[] arrayOfByte = _inputBuffer;
            k = i + 1;
            final byte b = arrayOfByte[i];
            byte b1 = 117;
            if (b == b1) {
                final int m = i + 2;
                k = arrayOfByte[k];
                b1 = 108;
                if (k == b1) {
                    i += 3;
                    k = arrayOfByte[m];
                    if (k == b1) {
                        j = arrayOfByte[i] & 0xFF;
                        k = 48;
                        if (j >= k) {
                            k = 93;
                            if (j != k) {
                                k = 125;
                                if (j == k) {
                                    _inputPtr = i;
                                    final JsonToken jsonToken1 = JsonToken.VALUE_NULL;
                                    try {
                                        return this._valueComplete(jsonToken1);
                                    } catch (final IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            } else {
                                _inputPtr = i;
                                final JsonToken jsonToken1 = JsonToken.VALUE_NULL;
                                try {
                                    return this._valueComplete(jsonToken1);
                                } catch (final IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        } else {
                            _inputPtr = i;
                            final JsonToken jsonToken1 = JsonToken.VALUE_NULL;
                            try {
                                return this._valueComplete(jsonToken1);
                            } catch (final IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        }
        _minorState = 16;
        final JsonToken jsonToken = JsonToken.VALUE_NULL;
        return this._finishKeywordToken("null", 1, jsonToken);
    }
    protected JsonToken _startNumberLeadingZero() {
        int i = _inputPtr;
        int j = _inputEnd;
        if (i >= j) {
            _minorState = 24;
            final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        byte[] arrayOfByte = _inputBuffer;
        int k = i + 1;
        i = arrayOfByte[i] & 0xFF;
        j = 0;
        arrayOfByte = null;
        final byte b = 48;
        final int bool = true;
        if (i < b) {
            final byte b1 = 46;
            if (i == b1) {
                _inputPtr = k;
                _intLength = bool;
                final char[] arrayOfChar = _textBuffer.emptyAndGetCurrentSegment();
                arrayOfChar[0] = (char) b;
                return this._startFloatThatStartsWithPeriod();
            }
        } else {
            byte b1 = 57;
            if (i > b1) {
                b1 = 101;
                if (i != b1) {
                    b1 = 69;
                    if (i == b1) {
                        _inputPtr = k;
                        _intLength = bool;
                        final char[] arrayOfChar1 = _textBuffer.emptyAndGetCurrentSegment();
                        arrayOfChar1[0] = b;
                        return this._startFloatThatStartsWithPeriod(arrayOfChar1, bool, i);
                    }
                    k = 93;
                    if (i != k) {
                        k = 125;
                        if (i != k) {
                            final String str = "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'";
                            this.reportUnexpectedNumberChar(i, str);
                        }
                    }
                    return this._valueCompleteInt(0, "0");
                }
                _inputPtr = k;
                _intLength = bool;
                final char[] arrayOfChar = _textBuffer.emptyAndGetCurrentSegment();
                arrayOfChar[0] = b;
                return this._startFloatThatStartsWithPeriod(arrayOfChar, bool, i);
            }
            return this._finishNumberLeadingZeroes();
        }
        return this._valueCompleteInt(0, "0");
    }
    protected JsonToken _startPositiveNumber(int paramInt) {
        byte[] arrayOfByte = null;
        _numberNegative = false;
        char[] arrayOfChar = _textBuffer.emptyAndGetCurrentSegment();
        paramInt = (char)paramInt;
        arrayOfChar[0] = paramInt;
        paramInt = _inputPtr;
        int i = _inputEnd;
        int j = 26;
        final byte b = 1;
        if (paramInt >= i) {
            _minorState = j;
            _textBuffer.setCurrentLength(b);
            final JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        arrayOfByte = _inputBuffer;
        paramInt = arrayOfByte[paramInt] & 0xFF;
        i = b;
        while (true) {
            int k = 48;
            if (paramInt < k) {
                j = 46;
                if (paramInt == j) {
                    _intLength = i;
                    j = _inputPtr + b;
                    _inputPtr = j;
                    return _startFloat(arrayOfChar, i, paramInt);
                }
            } else {
                k = 57;
                if (paramInt > k) {
                    j = 101;
                    if (paramInt != j) {
                        j = 69;
                        if (paramInt == j) {
                            _intLength = i;
                            j = _inputPtr + b;
                            _inputPtr = j;
                            return _startFloat(arrayOfChar, i, paramInt);
                        }
                        _intLength = i;
                        _textBuffer.setCurrentLength(i);
                        final JsonToken jsonToken1 = JsonToken.VALUE_NUMBER_INT;
                        return this._valueComplete(jsonToken1);
                    }
                    _intLength = i;
                    j = _inputPtr + b;
                    _inputPtr = j;
                    return _startFloat(arrayOfChar, i, paramInt);
                }
                k = arrayOfChar.length;
                if (i >= k)
                    arrayOfChar = _textBuffer.expandCurrentSegment();
                k = i + 1;
                paramInt = (char)paramInt;
                arrayOfChar[i] = paramInt;
                paramInt = _inputPtr + b;
                _inputPtr = paramInt;
                i = _inputEnd;
                if (paramInt >= i) {
                    _minorState = j;
                    _textBuffer.setCurrentLength(k);
                    final JsonToken jsonToken1 = JsonToken.NOT_AVAILABLE;
                    this._currToken = jsonToken1;
                    return jsonToken1;
                }
                arrayOfByte = _inputBuffer;
                paramInt = arrayOfByte[paramInt] & 0xFF;
                i = k;
                continue;
            }
            _intLength = i;
            _textBuffer.setCurrentLength(i);
            final JsonToken jsonToken = JsonToken.VALUE_NUMBER_INT;
            return this._valueComplete(jsonToken);
        }
    }
    protected JsonToken _startString() {
        int i = _inputPtr;
        final char[] arrayOfChar = _textBuffer.emptyAndGetCurrentSegment();
        final int[] arrayOfInt = NonBlockingJsonParser._icUTF8;
        int j = _inputEnd;
        final int k = arrayOfChar.length + i;
        j = Math.min(j, k);
        final byte[] arrayOfByte = _inputBuffer;
        int m;
        for (m = 0; i < j; m = i1) {
            int n = arrayOfByte[i] & 0xFF;
            int i1 = arrayOfInt[n];
            if (0 != i1) {
                final byte b = 34;
                if (n == b) {
                    ++i;
                    _inputPtr = i;
                    _textBuffer.setCurrentLength(m);
                    final JsonToken jsonToken = JsonToken.VALUE_STRING;
                    return this._valueComplete(jsonToken);
                }
                break;
            }
            i++;
            i1 = m + 1;
            n = (char)n;
            arrayOfChar[m] = n;
        }
        _textBuffer.setCurrentLength(m);
        _inputPtr = i;
        return this._finishRegularString();
    }
    protected JsonToken _startTrueToken() {
        int i = _inputPtr;
        int j = i + 3;
        int k = _inputEnd;
        if (j < k) {
            final byte[] arrayOfByte = _inputBuffer;
            k = i + 1;
            final byte b = arrayOfByte[i];
            byte b1 = 114;
            if (b == b1) {
                int m = i + 2;
                k = arrayOfByte[k];
                b1 = 117;
                if (k == b1) {
                    i += 3;
                    k = arrayOfByte[m];
                    m = 101;
                    if (k == m) {
                        j = arrayOfByte[i] & 0xFF;
                        k = 48;
                        if (j >= k) {
                            k = 93;
                            if (j != k) {
                                k = 125;
                                if (j == k) {
                                    _inputPtr = i;
                                    final JsonToken jsonToken1 = JsonToken.VALUE_TRUE;
                                    return this._valueComplete(jsonToken1);
                                }
                            } else {
                                _inputPtr = i;
                                final JsonToken jsonToken1 = JsonToken.VALUE_TRUE;
                                return this._valueComplete(jsonToken1);
                            }
                        } else {
                            _inputPtr = i;
                            final JsonToken jsonToken1 = JsonToken.VALUE_TRUE;
                            return this._valueComplete(jsonToken1);
                        }
                    }
                }
            }
        }
        _minorState = 17;
        final JsonToken jsonToken = JsonToken.VALUE_TRUE;
        return this._finishKeywordToken("true", 1, jsonToken);
    }
    protected JsonToken _startUnexpectedValue(final boolean paramBoolean, final int paramInt) {
        int i = 39;
        if (paramInt != i) {
            i = 73;
            final byte b = 1;
            if (paramInt != i) {
                i = 78;
                if (paramInt != i) {
                    i = 93;
                    if (paramInt != i) {
                        i = 125;
                        if (paramInt != i) {
                            i = 43;
                            if (paramInt != i) {
                                i = 44;
                                if (paramInt != i) {
                                    final StringBuilder stringBuilder1 = new StringBuilder();
                                    this();
                                    stringBuilder1.append("expected a valid value ");
                                    final String str4 = this._validJsonValueList();
                                    stringBuilder1.append(str4);
                                    final String str3 = stringBuilder1.toString();
                                    this._reportUnexpectedChar(paramInt, str3);
                                    return null;
                                }
                            } else {
                                return this._finishNonStdToken(2, b);
                            }
                        } else {
                            final StringBuilder stringBuilder1 = new StringBuilder();
                            this();
                            stringBuilder1.append("expected a valid value ");
                            final String str4 = this._validJsonValueList();
                            stringBuilder1.append(str4);
                            final String str3 = stringBuilder1.toString();
                            this._reportUnexpectedChar(paramInt, str3);
                            return null;
                        }
                    } else {
                        final JsonReadContext jsonReadContext1 = _parsingContext;
                        final boolean bool1 = jsonReadContext1.inArray();
                        if (!bool1) {
                            final StringBuilder stringBuilder1 = new StringBuilder();
                            this();
                            stringBuilder1.append("expected a valid value ");
                            final String str4 = this._validJsonValueList();
                            stringBuilder1.append(str4);
                            final String str3 = stringBuilder1.toString();
                            this._reportUnexpectedChar(paramInt, str3);
                            return null;
                        }
                    }
                    final JsonReadContext jsonReadContext = _parsingContext;
                    final boolean bool = jsonReadContext.inRoot();
                    if (!bool) {
                        int j = this._features;
                        final int k = NonBlockingJsonParser.FEAT_MASK_ALLOW_MISSING;
                        j &= k;
                        if (0 != j) {
                            j = _inputPtr - b;
                            _inputPtr = j;
                            final JsonToken jsonToken = JsonToken.VALUE_NULL;
                            return this._valueComplete(jsonToken);
                        }
                    }
                } else {
                    return this._finishNonStdToken(0, b);
                }
            } else {
                return this._finishNonStdToken(b, b);
            }
        } else {
            i = this._features;
            final int j = NonBlockingJsonParser.FEAT_MASK_ALLOW_SINGLE_QUOTES;
            i &= j;
            if (0 != i)
                return this._startAposString();
        }
        final StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("expected a valid value ");
        final String str2 = this._validJsonValueList();
        stringBuilder.append(str2);
        final String str1 = stringBuilder.toString();
        this._reportUnexpectedChar(paramInt, str1);
        return null;
    }
    public JsonToken nextToken() {
        boolean bool;
        final int j = _inputPtr;
        int k = _inputEnd;
        if (j >= k) {
            bool = _closed;
            if (bool)
                return null;
            bool = _endOfInput;
            if (bool) {
                final JsonToken jsonToken3 = this._currToken;
                final JsonToken jsonToken4 = JsonToken.NOT_AVAILABLE;
                return (jsonToken3 == jsonToken4) ? this._finishTokenWithEOF() : this._eofAsNextToken();
            }
            return JsonToken.NOT_AVAILABLE;
        }
        final JsonToken jsonToken1 = this._currToken;
        final JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
        if (jsonToken1 == jsonToken2)
            return this._finishToken();
        _numTypesValid = 0;
        long l1 = _currInputProcessed;
        final long l2 = bool;
        l1 += l2;
        _tokenInputTotal = l1;
        _binaryValue = null;
        final byte[] arrayOfByte = _inputBuffer;
        final int m = bool + 1;
        _inputPtr = m;
        final int i = arrayOfByte[bool] & 0xFF;
        k = _majorState;
        switch (k) {
            default:
                VersionUtil.throwInternal();
                return null;
            case 6:
                return this._startValueExpectComma(i);
            case 5:
                return this._startValue(i);
            case 4:
                return this._startValueExpectColon(i);
            case 3:
                return this._startFieldNameAfterComma(i);
            case 2:
                return this._startFieldName(i);
            case 1:
                return this._startValue(i);
            case 0:
                break;
        }
        return this._startDocument(i);
    }
}
