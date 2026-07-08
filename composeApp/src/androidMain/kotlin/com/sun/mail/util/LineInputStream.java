package com.sun.mail.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class LineInputStream extends FilterInputStream {
    private static final int MAX_INCR = 1048576;
    private char[] lineBuffer;

    public LineInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public String readLine() throws IOException {
        char[] cArr = new char[0];
        int read;
        char[] cArr2 = this.lineBuffer;
        if (null == cArr2) {
            cArr2 = new char[128];
            this.lineBuffer = cArr2;
        }
        int length = cArr.length;
        int i2 = 0;
        while (true) {
            read = this.in.read();
            if (-1 == read || 10 == read) {
                break;
            }
            boolean z = true;
            if (13 == read) {
                if (this.in.markSupported()) {
                    this.in.mark(2);
                }
                int read2 = this.in.read();
                if (13 == read2) {
                    read2 = this.in.read();
                } else {
                    z = false;
                }
                if (10 != read2) {
                    if (this.in.markSupported()) {
                        this.in.reset();
                    } else {
                        if (!(this.in instanceof PushbackInputStream)) {
                            this.in = new PushbackInputStream(this.in, 2);
                        }
                        if (-1 != read2) {
                            ((PushbackInputStream) this.in).unread(read2);
                        }
                        if (z) {
                            ((PushbackInputStream) this.in).unread(13);
                        }
                    }
                }
            } else {
                length--;
                if (0 > length) {
                    int length2 = cArr.length;
                    final int i3 = MAX_INCR;
                    if (i3 > length2) {
                        cArr = new char[(cArr.length * 2)];
                    } else {
                        cArr = new char[(cArr.length + i3)];
                    }
                    length = (cArr.length - i2) - 1;
                    System.arraycopy(this.lineBuffer, 0, cArr, 0, i2);
                    this.lineBuffer = cArr;
                }
                cArr[i2] = (char) read;
                i2++;
            }
        }
        if (-1 == read && 0 == i2) {
            return null;
        }
        return String.copyValueOf(cArr, 0, i2);
    }
}
