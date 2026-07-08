package com.fasterxml.jackson.core.io;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
public class UTF32Reader extends Reader {
    protected final boolean _bigEndian;
    protected byte[] _buffer;
    protected int _byteCount;
    protected int _charCount;
    protected final IOContext _context;
    protected InputStream _in;
    protected int _length;
    protected final boolean _managedBuffers;
    protected int _ptr;
    protected char _surrogate;
    protected char[] _tmpBuf;

    public UTF32Reader(final IOContext iOContext, final InputStream inputStream, final byte[] bArr, final int i2, final int i3, final boolean z) {
        _context = iOContext;
        _in = inputStream;
        _buffer = bArr;
        _ptr = i2;
        _length = i3;
        _bigEndian = z;
        _managedBuffers = null != inputStream;
    }
    public void close() throws IOException {
        final InputStream inputStream = _in;
        if (null != inputStream) {
            _in = null;
            this.freeBuffers();
            inputStream.close();
        }
    }
    public int read() throws IOException {
        if (null == this._tmpBuf) {
            _tmpBuf = new char[1];
        }
        if (1 > read(this._tmpBuf, 0, 1)) {
            return -1;
        }
        return _tmpBuf[0];
    }
    public int read(final char[] cArr, final int i2, final int i3) throws IOException {
        int i4;
        int i5;
        int i6;
        int i7;
        if (null == this._buffer) {
            return -1;
        }
        if (1 > i3) {
            return i3;
        }
        if (0 > i2 || i2 + i3 > cArr.length) {
            this.reportBounds(cArr, i2, i3);
        }
        final int i8 = i3 + i2;
        final char c2 = _surrogate;
        if (0 != c2) {
            i4 = i2 + 1;
            cArr[i2] = c2;
            _surrogate = 0;
        } else {
            final int i9 = _length - _ptr;
            if (4 > i9 && !this.loadMore(i9)) {
                if (0 == i9) {
                    return -1;
                }
                this.reportUnexpectedEOF(_length - _ptr, 4);
            }
            i4 = i2;
        }
        final int i10 = _length - 4;
        while (i4 < i8) {
            final int i11 = _ptr;
            if (_bigEndian) {
                final byte[] bArr = _buffer;
                i5 = (bArr[i11] << 8) | (bArr[i11 + 1] & 255);
                i6 = (bArr[i11 + 3] & 255) | ((bArr[i11 + 2] & 255) << 8);
            } else {
                final byte[] bArr2 = _buffer;
                final int i12 = (bArr2[i11] & 255) | ((bArr2[i11 + 1] & 255) << 8);
                i5 = (bArr2[i11 + 3] << 8) | (bArr2[i11 + 2] & 255);
                i6 = i12;
            }
            _ptr = i11 + 4;
            if (0 != i5) {
                final int i13 = 65535 & i5;
                final int i14 = i6 | ((i13 - 1) << 16);
                if (16 < i13) {
                    this.reportInvalid(i14, i4 - i2, String.format(" (above 0x%08x)", 1114111));
                }
                i7 = i4 + 1;
                cArr[i4] = (char) ((i14 >> 10) + 55296);
                final int i15 = (i14 & 1023) | 56320;
                if (i7 >= i8) {
                    _surrogate = (char) i14;
                    i4 = i7;
                    break;
                }
                i6 = i15;
                i4 = i7;
                i7 = i4 + 1;
                cArr[i4] = (char) i6;
                if (_ptr <= i10) {
                    i4 = i7;
                    break;
                }
                i4 = i7;
            } else {
                i7 = i4 + 1;
                cArr[i4] = (char) i6;
                if (_ptr <= i10) {
                }
            }
        }
        final int i16 = i4 - i2;
        _charCount += i16;
        return i16;
    }
    private void reportUnexpectedEOF(final int i2, final int i3) throws IOException {
        final int i4 = _byteCount + i2;
        throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + i2 + ", needed " + i3 + ", at char #" + _charCount + ", byte #" + i4 + ")");
    }
    private void reportInvalid(final int i2, final int i3, final String str) throws IOException {
        final int i4 = (_byteCount + _ptr) - 1;
        throw new CharConversionException("Invalid UTF-32 character 0x" + Integer.toHexString(i2) + str + " at char #" + (_charCount + i3) + ", byte #" + i4 + ")");
    }
    private boolean loadMore(final int i2) throws IOException {
        int i3;
        _byteCount += _length - i2;
        if (0 < i2) {
            final int i4 = _ptr;
            if (0 < i4) {
                final byte[] bArr = _buffer;
                System.arraycopy(bArr, i4, bArr, 0, i2);
                _ptr = 0;
            }
            _length = i2;
        } else {
            _ptr = 0;
            final InputStream inputStream = _in;
            final int i5 = null == inputStream ? -1 : inputStream.read(_buffer);
            if (1 > i5) {
                _length = 0;
                if (0 > i5) {
                    if (_managedBuffers) {
                        this.freeBuffers();
                    }
                    return false;
                }
                this.reportStrangeStream();
            }
            _length = i5;
        }
        while (true) {
            final int i6 = _length;
            if (4 <= i6) {
                return true;
            }
            final InputStream inputStream2 = _in;
            if (null == inputStream2) {
                i3 = -1;
            } else {
                final byte[] bArr2 = _buffer;
                i3 = inputStream2.read(bArr2, i6, bArr2.length - i6);
            }
            if (1 > i3) {
                if (0 > i3) {
                    if (_managedBuffers) {
                        this.freeBuffers();
                    }
                    this.reportUnexpectedEOF(_length, 4);
                }
                this.reportStrangeStream();
            }
            _length += i3;
        }
    }
    private void freeBuffers() {
        final byte[] bArr = _buffer;
        if (null != bArr) {
            _buffer = null;
            _context.releaseReadIOBuffer(bArr);
        }
    }
    private void reportBounds(final char[] cArr, final int i2, final int i3) throws IOException {
        throw new ArrayIndexOutOfBoundsException("read(buf," + i2 + "," + i3 + "), cbuf[" + cArr.length + "]");
    }
    private void reportStrangeStream() throws IOException {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }
}
