package com.sun.mail.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UUDecoderStream extends FilterInputStream {
    private final byte[] buffer = new byte[45];
    private int bufsize;
    private boolean gotEnd;
    private boolean gotPrefix;
    private final boolean ignoreErrors;
    private final boolean ignoreMissingBeginEnd;
    private int index;
    private final LineInputStream lin;
    private int mode;
    private String name;
    private String readAhead;
    public boolean markSupported() {
        return false;
    }
    public UUDecoderStream(final InputStream inputStream) {
        super(inputStream);
        lin = new LineInputStream(inputStream);
        ignoreErrors = PropUtil.getBooleanSystemProperty("mail.mime.uudecode.ignoreerrors", false);
        ignoreMissingBeginEnd = PropUtil.getBooleanSystemProperty("mail.mime.uudecode.ignoremissingbeginend", false);
    }
    public UUDecoderStream(final InputStream inputStream, final boolean z, final boolean z2) {
        super(inputStream);
        lin = new LineInputStream(inputStream);
        ignoreErrors = z;
        ignoreMissingBeginEnd = z2;
    }
    public int read() throws IOException {
        if (index >= bufsize) {
            this.readPrefix();
            if (!this.decode()) {
                return -1;
            }
            index = 0;
        }
        final byte[] bArr = buffer;
        final int i2 = index;
        index = i2 + 1;
        return bArr[i2] & 255;
    }
    public int read(final byte[] bArr, final int i2, final int i3) throws IOException {
        int i4 = 0;
        while (i4 < i3) {
            final int read = this.read();
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
    public int available() throws IOException {
        return ((in.available() * 3) / 4) + (bufsize - index);
    }
    public String getName() throws IOException {
        this.readPrefix();
        return name;
    }
    public int getMode() throws IOException {
        this.readPrefix();
        return mode;
    }
    private void readPrefix() throws IOException {
        String readLine;
        if (!gotPrefix) {
            mode = 438;
            name = "encoder.buf";
            while (true) {
                readLine = lin.readLine();
                if (null == readLine) {
                    if (ignoreMissingBeginEnd) {
                        gotPrefix = true;
                        gotEnd = true;
                        return;
                    }
                    throw new DecodingException("UUDecoder: Missing begin");
                } else if (readLine.regionMatches(false, 0, "begin", 0, 5)) {
                    try {
                        mode = Integer.parseInt(readLine.substring(6, 9));
                    } catch (final NumberFormatException e2) {
                        if (!ignoreErrors) {
                            String stringBuffer = "UUDecoder: Error in mode: " +
                                    e2;
                            throw new DecodingException(stringBuffer);
                        }
                    }
                    if (10 < readLine.length()) {
                        name = readLine.substring(10);
                    } else if (!ignoreErrors) {
                        String stringBuffer2 = "UUDecoder: Missing name: " +
                                readLine;
                        throw new DecodingException(stringBuffer2);
                    }
                    gotPrefix = true;
                    return;
                } else if (ignoreMissingBeginEnd && 0 != readLine.length()) {
                    final int charAt = ((((readLine.charAt(0) - ' ') & 63) * 8) + 5) / 6;
                    if (0 == charAt || readLine.length() >= charAt + 1) {
                        readAhead = readLine;
                        gotPrefix = true;
                    }
                }
            }
        }
    }
    private boolean decode() throws IOException {
        if (gotEnd) {
            return false;
        }
        bufsize = 0;
        while (true) {
            String str = readAhead;
            if (null != str) {
                readAhead = null;
            } else {
                str = lin.readLine();
            }
            if (null == str) {
                if (ignoreMissingBeginEnd) {
                    gotEnd = true;
                    return false;
                }
                throw new DecodingException("UUDecoder: Missing end at EOF");
            } else if ("end".equals(str)) {
                gotEnd = true;
                return false;
            } else if (0 != str.length()) {
                final char charAt = str.charAt(0);
                if (' ' <= charAt) {
                    final int i2 = (charAt - ' ') & 63;
                    if (0 == i2) {
                        final String readLine = lin.readLine();
                        if ((!"end".equals(readLine)) && !ignoreMissingBeginEnd) {
                            throw new DecodingException("UUDecoder: Missing End after count 0 line");
                        }
                        gotEnd = true;
                        return false;
                    } else if (str.length() >= (((i2 * 8) + 5) / 6) + 1) {
                        int i3 = 1;
                        while (bufsize < i2) {
                            final int i4 = i3 + 2;
                            byte charAt2 = (byte) ((str.charAt(i3 + 1) - ' ') & 63);
                            final byte[] bArr = buffer;
                            final int i5 = bufsize;
                            final int i6 = i5 + 1;
                            bufsize = i6;
                            bArr[i5] = (byte) (((((byte) ((str.charAt(i3) - ' ') & 63)) << 2) & 252) | ((charAt2 >>> 4) & 3));
                            if (i6 < i2) {
                                i3 += 3;
                                final byte charAt3 = (byte) ((str.charAt(i4) - ' ') & 63);
                                final byte[] bArr2 = buffer;
                                final int i7 = bufsize;
                                bufsize = i7 + 1;
                                bArr2[i7] = (byte) (((charAt2 << 4) & 240) | ((charAt3 >>> 2) & 15));
                                charAt2 = charAt3;
                            } else {
                                i3 = i4;
                            }
                            if (bufsize < i2) {
                                final byte[] bArr3 = buffer;
                                final int i8 = bufsize;
                                bufsize = i8 + 1;
                                bArr3[i8] = (byte) ((((byte) ((str.charAt(i3) - ' ') & 63)) & 63) | ((charAt2 << 6) & 192));
                                i3++;
                            }
                        }
                        return true;
                    } else if (!ignoreErrors) {
                        throw new DecodingException("UUDecoder: Short buffer error");
                    }
                } else if (!ignoreErrors) {
                    throw new DecodingException("UUDecoder: Buffer format error");
                }
            }
        }
    }
}
