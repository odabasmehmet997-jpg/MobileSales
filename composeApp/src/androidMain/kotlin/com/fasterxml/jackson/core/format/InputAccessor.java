package com.fasterxml.jackson.core.format;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public interface InputAccessor {
    boolean hasMoreBytes() throws IOException;
    byte nextByte() throws IOException;
    class Std implements InputAccessor {
        protected final byte[] _buffer;
        protected int _bufferedEnd;
        protected final int _bufferedStart;
        protected final InputStream _in;
        protected int _ptr;

        public Std(final InputStream inputStream, final byte[] bArr) {
            _in = inputStream;
            _buffer = bArr;
            _bufferedStart = 0;
            _ptr = 0;
            _bufferedEnd = 0;
        }

        public Std(final byte[] bArr, final int i2, final int i3) {
            _in = null;
            _buffer = bArr;
            _ptr = i2;
            _bufferedStart = i2;
            _bufferedEnd = i2 + i3;
        }
        public boolean hasMoreBytes() throws IOException {
            final int i2;
            final int i3 = _ptr;
            if (i3 < _bufferedEnd) {
                return true;
            }
            final InputStream inputStream = _in;
            if (null == inputStream) {
                return false;
            }
            final byte[] bArr = _buffer;
            final int length = bArr.length - i3;
            if (1 > length || 0 >= (i2 = inputStream.read(bArr, i3, length))) {
                return false;
            }
            _bufferedEnd += i2;
            return true;
        }

        public byte nextByte() throws IOException {
            if (_ptr >= _bufferedEnd && !this.hasMoreBytes()) {
                throw new EOFException("Failed auto-detect: could not read more than " + _ptr + " bytes (max buffer size: " + _buffer.length + ")");
            }
            final byte[] bArr = _buffer;
            final int i2 = _ptr;
            _ptr = i2 + 1;
            return bArr[i2];
        }

        public void reset() {
            _ptr = _bufferedStart;
        }
    }
}
