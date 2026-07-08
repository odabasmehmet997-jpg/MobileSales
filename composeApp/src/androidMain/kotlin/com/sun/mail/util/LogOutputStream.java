package com.sun.mail.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

public class LogOutputStream extends OutputStream {
    private byte[] buf = new byte[80];
    private int lastb = -1;
    protected Level level;
    protected MailLogger logger;
    private int pos;
    public LogOutputStream(final MailLogger mailLogger) {
        logger = mailLogger;
        level = Level.FINEST;
    }
    public void write(final int i2) throws IOException {
        if (logger.isLoggable(level)) {
            if (13 == i2) {
                this.logBuf();
            } else if (10 != i2) {
                this.expandCapacity(1);
                final byte[] bArr = buf;
                final int i3 = pos;
                pos = i3 + 1;
                bArr[i3] = (byte) i2;
            } else if (13 != this.lastb) {
                this.logBuf();
            }
            lastb = i2;
        }
    }
    public void write(final byte[] bArr) throws IOException {
        this.write(bArr, 0, bArr.length);
    }
    public void write(final byte[] bArr, int i2, final int i3) throws IOException {
        if (logger.isLoggable(level)) {
            final int i4 = i3 + i2;
            int i5 = i2;
            while (i2 < i4) {
                final byte b2 = bArr[i2];
                if (13 == b2) {
                    final int i6 = i2 - i5;
                    this.expandCapacity(i6);
                    System.arraycopy(bArr, i5, buf, pos, i6);
                    pos += i6;
                    this.logBuf();
                } else if (10 != b2) {
                    lastb = bArr[i2];
                    i2++;
                } else if (13 != this.lastb) {
                    final int i7 = i2 - i5;
                    this.expandCapacity(i7);
                    System.arraycopy(bArr, i5, buf, pos, i7);
                    pos += i7;
                    this.logBuf();
                }
                i5 = i2 + 1;
                lastb = bArr[i2];
                i2++;
            }
            final int i8 = i4 - i5;
            if (0 < i8) {
                this.expandCapacity(i8);
                System.arraycopy(bArr, i5, buf, pos, i8);
                pos += i8;
            }
        }
    }
    private void logBuf() {
        final String str = new String(buf, 0, pos, StandardCharsets.UTF_8);
        pos = 0;
        this.log(str);
    }
    private void log(String str) {
    }
    private void expandCapacity(final int capacity) {
        while (true) {
            final int currentPos = pos;
            final int newLength = currentPos + capacity;
            final byte[] currentBuf = buf;
            if (newLength > currentBuf.length) {
                final byte[] newBuf = new byte[currentBuf.length * 2];
                System.arraycopy(currentBuf, 0, newBuf, 0, currentPos);
                buf = newBuf;
            } else {
                return;
            }
        }
    }
}
