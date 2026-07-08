package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.MergedStream;
import com.fasterxml.jackson.core.io.UTF32Reader;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;

import java.io.*;

public final class ByteSourceJsonBootstrapper {
    private boolean _bigEndian;
    private final boolean _bufferRecyclable;
    private int _bytesPerChar;
    private final IOContext _context;
    private final InputStream _in;
    private final byte[] _inputBuffer;
    private int _inputEnd;
    private int _inputPtr;

    public ByteSourceJsonBootstrapper(final IOContext iOContext, final InputStream inputStream) {
        _bigEndian = true;
        _context = iOContext;
        _in = inputStream;
        _inputBuffer = iOContext.allocReadIOBuffer();
        _inputPtr = 0;
        _inputEnd = 0;
        _bufferRecyclable = true;
    }

    public ByteSourceJsonBootstrapper(final IOContext iOContext, final byte[] bArr, final int i2, final int i3) {
        _bigEndian = true;
        _context = iOContext;
        _in = null;
        _inputBuffer = bArr;
        _inputPtr = i2;
        _inputEnd = i2 + i3;
        _bufferRecyclable = false;
    }

    public JsonEncoding detectEncoding() throws IOException {
        final JsonEncoding jsonEncoding;
        if (this.ensureLoaded(4)) {
            final byte[] bArr = _inputBuffer;
            final int i2 = _inputPtr;
            final int i3 = (bArr[i2 + 3] & 255) | (bArr[i2] << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
            if (!this.handleBOM(i3) && !this.checkUTF32(i3) && !this.checkUTF16(i3 >>> 16)) {
                jsonEncoding = JsonEncoding.UTF8;
            } else {
                final int i4 = _bytesPerChar;
                if (1 == i4) {
                    jsonEncoding = JsonEncoding.UTF8;
                } else if (2 == i4) {
                    jsonEncoding = _bigEndian ? JsonEncoding.UTF16_BE : JsonEncoding.UTF16_LE;
                } else if (4 == i4) {
                    jsonEncoding = _bigEndian ? JsonEncoding.UTF32_BE : JsonEncoding.UTF32_LE;
                } else {
                    throw new RuntimeException("Internal error");
                }
            }
        } else {
            if (this.ensureLoaded(2)) {
                final byte[] bArr2 = _inputBuffer;
                final int i5 = _inputPtr;
                if (this.checkUTF16((bArr2[i5 + 1] & 255) | ((bArr2[i5] & 255) << 8))) {
                }
            }
            jsonEncoding = JsonEncoding.UTF8;
        }
        _context.setEncoding(jsonEncoding);
        return jsonEncoding;
    }

    public static int skipUTF8BOM(final DataInput dataInput) throws IOException {
        final int unsignedByte = dataInput.readUnsignedByte();
        if (239 != unsignedByte) {
            return unsignedByte;
        }
        final int unsignedByte2 = dataInput.readUnsignedByte();
        if (187 != unsignedByte2) {
            throw new IOException("Unexpected byte 0x" + Integer.toHexString(unsignedByte2) + " following 0xEF; should get 0xBB as part of UTF-8 BOM");
        }
        final int unsignedByte3 = dataInput.readUnsignedByte();
        if (191 != unsignedByte3) {
            throw new IOException("Unexpected byte 0x" + Integer.toHexString(unsignedByte3) + " following 0xEF 0xBB; should get 0xBF as part of UTF-8 BOM");
        }
        return dataInput.readUnsignedByte();
    }

    public Reader constructReader() throws IOException {
        final JsonEncoding encoding = _context.getEncoding();
        final int iBits = encoding.bits();
        if (8 != iBits && 16 != iBits) {
            if (32 == iBits) {
                final IOContext iOContext = _context;
                return new UTF32Reader(iOContext, _in, _inputBuffer, _inputPtr, _inputEnd, iOContext.getEncoding().isBigEndian());
            }
            throw new RuntimeException("Internal error");
        }
        InputStream mergedStream = _in;
        if (null == mergedStream) {
            mergedStream = new ByteArrayInputStream(_inputBuffer, _inputPtr, _inputEnd);
        } else if (_inputPtr < _inputEnd) {
            mergedStream = new MergedStream(_context, mergedStream, _inputBuffer, _inputPtr, _inputEnd);
        }
        return new InputStreamReader(mergedStream, encoding.getJavaName());
    }

    public JsonParser constructParser(final int i2, final ObjectCodec objectCodec, final ByteQuadsCanonicalizer byteQuadsCanonicalizer, final CharsToNameCanonicalizer charsToNameCanonicalizer, final int i3) throws IOException {
        final int i4 = _inputPtr;
        final JsonEncoding jsonEncodingDetectEncoding = this.detectEncoding();
        final int i5 = _inputPtr - i4;
        if (JsonEncoding.UTF8 == jsonEncodingDetectEncoding && JsonFactory.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i3)) {
            return new UTF8StreamJsonParser(_context, i2, _in, objectCodec, byteQuadsCanonicalizer.makeChild(i3), _inputBuffer, _inputPtr, _inputEnd, i5, _bufferRecyclable);
        }
        return new ReaderBasedJsonParser(_context, i2, this.constructReader(), objectCodec, charsToNameCanonicalizer.makeChild(i3));
    }

    public static MatchStrength hasJSONFormat(final InputAccessor inputAccessor) throws IOException {
        if (!inputAccessor.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte bNextByte = inputAccessor.nextByte();
        if (-17 == bNextByte) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (-69 != inputAccessor.nextByte()) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (-65 != inputAccessor.nextByte()) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            bNextByte = inputAccessor.nextByte();
        }
        final int iSkipSpace = ByteSourceJsonBootstrapper.skipSpace(inputAccessor, bNextByte);
        if (0 > iSkipSpace) {
            return MatchStrength.INCONCLUSIVE;
        }
        if (123 == iSkipSpace) {
            final int iSkipSpace2 = ByteSourceJsonBootstrapper.skipSpace(inputAccessor);
            if (0 > iSkipSpace2) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (34 == iSkipSpace2 || 125 == iSkipSpace2) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.NO_MATCH;
        }
        if (91 == iSkipSpace) {
            final int iSkipSpace3 = ByteSourceJsonBootstrapper.skipSpace(inputAccessor);
            if (0 > iSkipSpace3) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (93 == iSkipSpace3 || 91 == iSkipSpace3) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.SOLID_MATCH;
        }
        final MatchStrength matchStrength = MatchStrength.WEAK_MATCH;
        if (34 == iSkipSpace) {
            return matchStrength;
        }
        if (57 >= iSkipSpace && 48 <= iSkipSpace) {
            return matchStrength;
        }
        if (45 == iSkipSpace) {
            final int iSkipSpace4 = ByteSourceJsonBootstrapper.skipSpace(inputAccessor);
            if (0 > iSkipSpace4) {
                return MatchStrength.INCONCLUSIVE;
            }
            return (57 < iSkipSpace4 || 48 > iSkipSpace4) ? MatchStrength.NO_MATCH : matchStrength;
        }
        if (110 == iSkipSpace) {
            return ByteSourceJsonBootstrapper.tryMatch(inputAccessor, "ull", matchStrength);
        }
        if (116 == iSkipSpace) {
            return ByteSourceJsonBootstrapper.tryMatch(inputAccessor, "rue", matchStrength);
        }
        if (102 == iSkipSpace) {
            return ByteSourceJsonBootstrapper.tryMatch(inputAccessor, "alse", matchStrength);
        }
        return MatchStrength.NO_MATCH;
    }

    private static MatchStrength tryMatch(final InputAccessor inputAccessor, final String str, final MatchStrength matchStrength) throws IOException {
        final int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != str.charAt(i2)) {
                return MatchStrength.NO_MATCH;
            }
        }
        return matchStrength;
    }

    private static int skipSpace(final InputAccessor inputAccessor) throws IOException {
        if (inputAccessor.hasMoreBytes()) {
            return ByteSourceJsonBootstrapper.skipSpace(inputAccessor, inputAccessor.nextByte());
        }
        return -1;
    }

    private static int skipSpace(final InputAccessor inputAccessor, byte b2) throws IOException {
        while (true) {
            final int i2 = b2 & 255;
            if (32 != i2 && 13 != i2 && 10 != i2 && 9 != i2) {
                return i2;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return -1;
            }
            b2 = inputAccessor.nextByte();
        }
    }

    private boolean handleBOM(final int i2) throws IOException {
        if (-16842752 == i2) {
            this.reportWeirdUCS4("3412");
        } else {
            if (-131072 == i2) {
                _inputPtr += 4;
                _bytesPerChar = 4;
                _bigEndian = false;
                return true;
            }
            if (65279 == i2) {
                _bigEndian = true;
                _inputPtr += 4;
                _bytesPerChar = 4;
                return true;
            }
            if (65534 == i2) {
                this.reportWeirdUCS4("2143");
            }
        }
        final int i3 = i2 >>> 16;
        if (65279 == i3) {
            _inputPtr += 2;
            _bytesPerChar = 2;
            _bigEndian = true;
            return true;
        }
        if (65534 == i3) {
            _inputPtr += 2;
            _bytesPerChar = 2;
            _bigEndian = false;
            return true;
        }
        if (15711167 != (i2 >>> 8)) {
            return false;
        }
        _inputPtr += 3;
        _bytesPerChar = 1;
        _bigEndian = true;
        return true;
    }

    private boolean checkUTF32(final int i2) throws IOException {
        if (0 == (i2 >> 8)) {
            _bigEndian = true;
        } else if (0 == (16777215 & i2)) {
            _bigEndian = false;
        } else if (0 == ((-16711681) & i2)) {
            this.reportWeirdUCS4("3412");
        } else {
            if (0 != (i2 & (-65281))) {
                return false;
            }
            this.reportWeirdUCS4("2143");
        }
        _bytesPerChar = 4;
        return true;
    }

    private boolean checkUTF16(final int i2) {
        if (0 == (65280 & i2)) {
            _bigEndian = true;
        } else {
            if (0 != (i2 & 255)) {
                return false;
            }
            _bigEndian = false;
        }
        _bytesPerChar = 2;
        return true;
    }

    private void reportWeirdUCS4(final String str) throws IOException {
        throw new CharConversionException("Unsupported UCS-4 endianness (" + str + ") detected");
    }

    private boolean ensureLoaded(final int i2) throws IOException {
        int i3;
        int i4 = _inputEnd - _inputPtr;
        while (i4 < i2) {
            final InputStream inputStream = _in;
            if (null == inputStream) {
                i3 = -1;
            } else {
                final byte[] bArr = _inputBuffer;
                final int i5 = _inputEnd;
                i3 = inputStream.read(bArr, i5, bArr.length - i5);
            }
            if (1 > i3) {
                return false;
            }
            _inputEnd += i3;
            i4 += i3;
        }
        return true;
    }
}
