package com.sun.mail.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CRLFOutputStream extends FilterOutputStream {
    private static final byte[] newline = {13, 10};
    protected boolean atBOL = true;
    protected int lastb = -1;
    public CRLFOutputStream(final OutputStream outputStream) {
        super(outputStream);
    }
    public void write(final int i2) throws IOException {
        if (13 == i2) {
            this.writeln();
        } else if (10 != i2) {
            out.write(i2);
            atBOL = false;
        } else if (13 != this.lastb) {
            this.writeln();
        }
        lastb = i2;
    }
    public void write(final byte[] bArr) throws IOException {
        this.write(bArr, 0, bArr.length);
    }
    public void write(final byte[] bArr, int i2, final int i3) throws IOException {
        final int i4 = i3 + i2;
        int i5 = i2;
        while (i2 < i4) {
            final byte b2 = bArr[i2];
            if (13 == b2) {
                out.write(bArr, i5, i2 - i5);
                this.writeln();
            } else if (10 != b2) {
                lastb = bArr[i2];
                i2++;
            } else if (13 != this.lastb) {
                out.write(bArr, i5, i2 - i5);
                this.writeln();
            }
            i5 = i2 + 1;
            lastb = bArr[i2];
            i2++;
        }
        final int i6 = i4 - i5;
        if (0 < i6) {
            out.write(bArr, i5, i6);
            atBOL = false;
        }
    }
    public void writeln() throws IOException {
        out.write(CRLFOutputStream.newline);
        atBOL = true;
    }
}
