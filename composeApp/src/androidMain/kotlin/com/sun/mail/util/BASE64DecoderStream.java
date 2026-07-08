package com.sun.mail.util;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BASE64DecoderStream extends FilterInputStream {
    private static final char[] pem_array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] pem_convert_array = new byte[256];
    private final byte[] buffer;
    private int bufsize;
    private boolean ignoreErrors;
    private int index;
    private final byte[] input_buffer;
    private int input_len;
    private int input_pos;
    public boolean markSupported() {
        return false;
    }
    public BASE64DecoderStream(InputStream inputStream) {
        super(inputStream);
        this.buffer = new byte[3];
        this.bufsize = 0;
        this.index = 0;
        this.input_buffer = new byte[8190];
        this.input_pos = 0;
        this.input_len = 0;
        this.ignoreErrors = false;
        this.ignoreErrors = PropUtil.getBooleanSystemProperty("mail.mime.base64.ignoreerrors", false);
    }
    public BASE64DecoderStream(InputStream inputStream, boolean z) {
        super(inputStream);
        this.buffer = new byte[3];
        this.bufsize = 0;
        this.index = 0;
        this.input_buffer = new byte[8190];
        this.input_pos = 0;
        this.input_len = 0;
        this.ignoreErrors = z;
    }
    public int read() throws IOException {
        if (this.index >= this.bufsize) {
            byte[] bArr = this.buffer;
            int decode = decode(bArr, 0, bArr.length);
            this.bufsize = decode;
            if (0 >= decode) {
                return -1;
            }
            this.index = 0;
        }
        byte[] bArr2 = this.buffer;
        int i2 = this.index;
        this.index = i2 + 1;
        return bArr2[i2] & 255;
    }
    public int read(byte[] r6, int r7, int r8) throws IOException {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.BASE64DecoderStream.read(byte[], int, int):int");
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
        return ((this.in.available() * 3) / 4) + (this.bufsize - this.index);
    }
    static {
        int i2 = 0;
        for (int i3 = 0; 255 > i3; i3++) {
            pem_convert_array[i3] = -1;
        }
        while (true) {
            char[] cArr = pem_array;
            if (i2 < cArr.length) {
                pem_convert_array[cArr[i2]] = (byte) i2;
                i2++;
            } else {
                break;
            }
        }
    }
    private int decode(byte[] bArr, int i2, int i3) throws IOException {
        int i4 = i2;
        while (3 <= i3) {
            boolean z = false;
            int i5 = 0;
            int i6 = 0;
            while (4 > i5) {
                int i7 = getByte();
                if (-1 == i7 || -2 == i7) {
                    if (-1 == i7) {
                        if (0 == i5) {
                            return i4 - i2;
                        }
                        if (this.ignoreErrors) {
                            z = true;
                        } else {
                            final String stringBuffer = "BASE64Decoder: Error in encoded stream: needed 4 valid base64 characters but only got " +
                                    i5 +
                                    " before EOF" +
                                    recentChars();
                            throw new DecodingException(stringBuffer);
                        }
                    } else if (2 > i5 && !this.ignoreErrors) {
                        final String stringBuffer2 = "BASE64Decoder: Error in encoded stream: needed at least 2 valid base64 characters, but only got " +
                                i5 +
                                " before padding character (=)" +
                                recentChars();
                        throw new DecodingException(stringBuffer2);
                    } else if (0 == i5) {
                        return i4 - i2;
                    }
                    int i8 = i5 - 1;
                    if (0 == i8) {
                        i8 = 1;
                    }
                    int i9 = i6 << 6;
                    for (int i10 = i5 + 1; 4 > i10; i10++) {
                        if (!z) {
                            int i11 = getByte();
                            if (-1 == i11) {
                                if (!this.ignoreErrors) {
                                    final String stringBuffer3 = "BASE64Decoder: Error in encoded stream: hit EOF while looking for padding characters (=)" +
                                            recentChars();
                                    throw new DecodingException(stringBuffer3);
                                }
                            } else if (-2 != i11 && !this.ignoreErrors) {
                                final String stringBuffer4 = "BASE64Decoder: Error in encoded stream: found valid base64 character after a padding character (=)" +
                                        recentChars();
                                throw new DecodingException(stringBuffer4);
                            }
                        }
                        i9 <<= 6;
                    }
                    int i12 = i9 >> 8;
                    if (2 == i8) {
                        bArr[i4 + 1] = (byte) (i12 & 255);
                    }
                    bArr[i4] = (byte) ((i9 >> 16) & 255);
                    return (i4 + i8) - i2;
                }
                i5++;
                i6 = (i6 << 6) | i7;
            }
            bArr[i4 + 2] = (byte) (i6 & 255);
            bArr[i4 + 1] = (byte) ((i6 >> 8) & 255);
            bArr[i4] = (byte) ((i6 >> 16) & 255);
            i3 -= 3;
            i4 += 3;
        }
        return i4 - i2;
    }
    private int getByte() throws IOException {
        byte b2;
        do {
            if (this.input_pos >= this.input_len) {
                try {
                    int read = this.in.read(this.input_buffer);
                    this.input_len = read;
                    if (0 >= read) {
                        return -1;
                    }
                    this.input_pos = 0;
                } catch (EOFException unused) {
                    return -1;
                }
            }
            byte[] bArr = this.input_buffer;
            int i2 = this.input_pos;
            this.input_pos = i2 + 1;
            byte b3 = ( byte ) (bArr[i2] & 255);
            if (61 == b3) {
                return -2;
            }
            b2 = pem_convert_array[b3];
        } while (-1 == b2);
        return b2;
    }
    private String recentChars() {
        String stringBuffer;
        int i2 = this.input_pos;
        if (10 < i2) {
            i2 = 10;
        }
        if (0 >= i2) {
            return "";
        }
        String stringBuffer3 = ", the " +
                i2 +
                " most recent characters were: \"";
        for (int i3 = this.input_pos - i2; i3 < this.input_pos; i3++) {
            char c2 = (char) (this.input_buffer[i3] & 255);
            if (9 == c2) {
                final String stringBuffer4 = stringBuffer3 +
                        "\\t";
                stringBuffer = stringBuffer4;
            } else if (10 == c2) {
                final String stringBuffer5 = stringBuffer3 +
                        "\\n";
                stringBuffer = stringBuffer5;
            } else if (13 == c2) {
                final String stringBuffer6 = stringBuffer3 +
                        "\\r";
                stringBuffer = stringBuffer6;
            } else if (' ' > c2 || 127 <= c2) {
                final String stringBuffer7 = stringBuffer3 +
                        "\\" +
                        c2;
                stringBuffer = stringBuffer7;
            } else {
                final String stringBuffer8 = stringBuffer3 +
                        c2;
                stringBuffer = stringBuffer8;
            }
            stringBuffer3 = stringBuffer;
        }
        final String stringBuffer9 = stringBuffer3 +
                "\"";
        return stringBuffer9;
    }
    public static byte[] decode(byte[] bArr) {
        int i2;
        int length = (bArr.length / 4) * 3;
        if (0 == length) {
            return bArr;
        }
        if (61 == bArr[bArr.length - 1]) {
            length = 61 == bArr[bArr.length - 2] ? length - 2 : length - 1;
        }
        byte[] bArr2 = new byte[length];
        int length2 = bArr.length;
        int i3 = 0;
        int i4 = 0;
        while (0 < length2) {
            byte[] bArr3 = pem_convert_array;
            int i5 = i3 + 2;
            int i6 = (bArr3[bArr[i3 + 1] & 255] | (bArr3[bArr[i3] & 255] << 6)) << 6;
            byte b2 = bArr[i5];
            if (61 != b2) {
                i5 = i3 + 3;
                i6 |= bArr3[b2 & 255];
                i2 = 3;
            } else {
                i2 = 2;
            }
            int i7 = i6 << 6;
            byte b3 = bArr[i5];
            if (61 != b3) {
                i5++;
                i7 |= bArr3[b3 & 255];
            } else {
                i2--;
            }
            if (2 < i2) {
                bArr2[i4 + 2] = (byte) (i7 & 255);
            }
            int i8 = i7 >> 8;
            if (1 < i2) {
                bArr2[i4 + 1] = (byte) (i8 & 255);
            }
            bArr2[i4] = (byte) ((i7 >> 16) & 255);
            i4 += i2;
            length2 -= 4;
            i3 = i5;
        }
        return bArr2;
    }
}
