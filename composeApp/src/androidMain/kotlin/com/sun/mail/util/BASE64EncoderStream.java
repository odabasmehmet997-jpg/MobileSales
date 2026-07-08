package com.sun.mail.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BASE64EncoderStream extends FilterOutputStream {
    private static final byte[] newline = {13, 10};
    private static final char[] pem_array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private final byte[] buffer;
    private int bufsize;
    private final int bytesPerLine;
    private int count;
    private final int lineLimit;
    private boolean noCRLF;
    private final byte[] outbuf;
    public BASE64EncoderStream(OutputStream outputStream, int i2) {
        super(outputStream);
        this.bufsize = 0;
        this.count = 0;
        this.noCRLF = false;
        this.buffer = new byte[3];
        if (Integer.MAX_VALUE == i2 || 4 > i2) {
            this.noCRLF = true;
            i2 = 76;
        }
        int i3 = (i2 / 4) * 4;
        this.bytesPerLine = i3;
        this.lineLimit = (i3 / 4) * 3;
        if (this.noCRLF) {
            this.outbuf = new byte[i3];
            return;
        }
        byte[] bArr = new byte[(i3 + 2)];
        this.outbuf = bArr;
        bArr[i3] = 13;
        bArr[i3 + 1] = 10;
    }
    public BASE64EncoderStream(OutputStream outputStream) {
        this(outputStream, 76);
    }
    public synchronized void write(byte[] bArr, int i2, int i3) throws IOException {
        int i4 = i3 + i2;
        while (0 != bufsize && i2 < i4) {
            try {
                write(bArr[i2]);
                i2++;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        int i5 = ((this.bytesPerLine - this.count) / 4) * 3;
        int i6 = i2 + i5;
        if (i6 <= i4) {
            int encodedSize = encodedSize(i5);
            if (!this.noCRLF) {
                byte[] bArr2 = this.outbuf;
                int i7 = encodedSize + 1;
                bArr2[encodedSize] = 13;
                encodedSize += 2;
                bArr2[i7] = 10;
            }
            this.out.write(encode(bArr, i2, i5, this.outbuf), 0, encodedSize);
            this.count = 0;
            i2 = i6;
        }
        while (true) {
            int i8 = this.lineLimit;
            if (i2 + i8 > i4) {
                break;
            }
            this.out.write(encode(bArr, i2, i8, this.outbuf));
            i2 += this.lineLimit;
        }
        if (i2 + 3 <= i4) {
            int i9 = ((i4 - i2) / 3) * 3;
            int encodedSize2 = encodedSize(i9);
            this.out.write(encode(bArr, i2, i9, this.outbuf), 0, encodedSize2);
            i2 += i9;
            this.count += encodedSize2;
        }
        while (i2 < i4) {
            write(bArr[i2]);
            i2++;
        }
    }
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }
    public synchronized void write(int i2) throws IOException {
        byte[] bArr = this.buffer;
        int i3 = this.bufsize;
        int i4 = i3 + 1;
        this.bufsize = i4;
        bArr[i3] = (byte) i2;
        if (3 == i4) {
            encode();
            this.bufsize = 0;
        }
    }
    public synchronized void flush() throws IOException {
        try {
            if (0 < bufsize) {
                encode();
                this.bufsize = 0;
            }
            this.out.flush();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    public synchronized void close() throws IOException {
        try {
            flush();
            if (0 < count && !this.noCRLF) {
                this.out.write(newline);
                this.out.flush();
            }
            this.out.close();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    private void encode() throws IOException {
        int encodedSize = encodedSize(this.bufsize);
        this.out.write(encode(this.buffer, 0, this.bufsize, this.outbuf), 0, encodedSize);
        int i2 = this.count + encodedSize;
        this.count = i2;
        if (i2 >= this.bytesPerLine) {
            if (!this.noCRLF) {
                this.out.write(newline);
            }
            this.count = 0;
        }
    }
    public static byte[] encode(byte[] bArr) {
        if (0 == bArr.length) {
            return bArr;
        }
        return encode(bArr, 0, bArr.length, null);
    }
    private static byte[] encode(byte[] bArr, int i2, int i3, byte[] bArr2) {
        if (null == bArr2) {
            bArr2 = new byte[encodedSize(i3)];
        }
        int i4 = 0;
        while (3 <= i3) {
            int i5 = i2 + 2;
            byte b2 = ( byte ) (bArr[i2 + 1] & 255);
            i2 += 3;
            byte b3 = ( byte ) (((b2 | ((bArr[i2] & 255) << 8)) << 8) | (bArr[i5] & 255));
            char[] cArr = pem_array;
            bArr2[i4 + 3] = (byte) cArr[b3 & 63];
            bArr2[i4 + 2] = (byte) cArr[(b3 >> 6) & 63];
            bArr2[i4 + 1] = (byte) cArr[(b3 >> 12) & 63];
            bArr2[i4] = (byte) cArr[(b3 >> 18) & 63];
            i3 -= 3;
            i4 += 4;
        }
        if (1 == i3) {
            int i6 = (bArr[i2] & 255) << 4;
            bArr2[i4 + 3] = 61;
            bArr2[i4 + 2] = 61;
            char[] cArr2 = pem_array;
            bArr2[i4 + 1] = (byte) cArr2[i6 & 63];
            bArr2[i4] = (byte) cArr2[(i6 >> 6) & 63];
        } else if (2 == i3) {
            int i7 = ((bArr[i2 + 1] & 255) | ((bArr[i2] & 255) << 8)) << 2;
            bArr2[i4 + 3] = 61;
            char[] cArr3 = pem_array;
            bArr2[i4 + 2] = (byte) cArr3[i7 & 63];
            bArr2[i4 + 1] = (byte) cArr3[(i7 >> 6) & 63];
            bArr2[i4] = (byte) cArr3[(i7 >> 12) & 63];
        }
        return bArr2;
    }
    private static int encodedSize(int i2) {
        return ((i2 + 2) / 3) * 4;
    }
}
