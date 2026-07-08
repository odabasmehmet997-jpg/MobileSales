package com.sun.mail.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class QPDecoderStream extends FilterInputStream {
    protected byte[] ba = new byte[2];
    protected int spaces;

    public boolean markSupported() {
        return false;
    }

    public QPDecoderStream(InputStream inputStream) {
        super(new PushbackInputStream(inputStream, 2));
    }

    public int read() throws IOException {
        int read;
        int i2 = this.spaces;
        if (0 < i2) {
            this.spaces = i2 - 1;
            return 32;
        }
        int read2 = this.in.read();
        if (32 == read2) {
            while (true) {
                read = this.in.read();
                if (32 != read) {
                    break;
                }
                this.spaces++;
            }
            if (13 == read || 10 == read || -1 == read) {
                this.spaces = 0;
                return read;
            }
            ((PushbackInputStream) this.in).unread(read);
            return 32;
        }
        if (61 == read2) {
            int read3 = this.in.read();
            if (10 == read3) {
                return read();
            }
            if (13 == read3) {
                int read4 = this.in.read();
                if (10 != read4) {
                    ((PushbackInputStream) this.in).unread(read4);
                }
                return read();
            } else if (-1 == read3) {
                return -1;
            } else {
                byte[] bArr = this.ba;
                bArr[0] = (byte) read3;
                bArr[1] = (byte) this.in.read();
                try {
                    return ASCIIUtility.parseInt(this.ba, 0, 2, 16);
                } catch (NumberFormatException unused) {
                    ((PushbackInputStream) this.in).unread(this.ba);
                }
            }
        }
        return read2;
    }

    public int read(byte[] bArr, int i2, int i3) throws IOException {
        int i4 = 0;
        while (i4 < i3) {
            int read = read();
            if (-1 != read) {
                bArr[i2 + i4] = (byte) read;
                i4++;
            } else if (0 == i4) {
                return -1;
            } else {
                return i4;
            }
        }
        return i4;
    }

    public long skip(long j2) throws IOException {
        long j3 = 0;
        while (true) {
            long j4 = j2 - 1;
            if (0 >= j2 || 0 > this.read()) {
                return j3;
            }
            j3++;
            j2 = j4;
        }
    }

    public int available() throws IOException {
        return this.in.available();
    }
}
