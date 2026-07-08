package javax.mail.internet;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;

class AsciiOutputStream extends OutputStream {
    private int ascii;
    private boolean badEOL;
    private final boolean breakOnNonAscii;
    private final boolean checkEOL;
    private int lastb;
    private int linelen;
    private boolean longLine;
    private int non_ascii;
    private int ret;
    public AsciiOutputStream(boolean z, boolean z2) {
        boolean z3 = false;
        this.breakOnNonAscii = z;
        if (z2 && z) {
            z3 = true;
        }
        this.checkEOL = z3;
    }
    public void write(int i2) throws IOException {
        check(i2);
    }
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }
    public void write(byte[] bArr, int i2, int i3) throws IOException {
        int i4 = i3 + i2;
        while (i2 < i4) {
            check(bArr[i2]);
            i2++;
        }
    }
    private void check(int i2) throws IOException {
        int i3;
        int i4 = i2 & 255;
        if (this.checkEOL && ((13 == (i3 = lastb) && 10 != i4) || (13 != i3 && 10 == i4))) {
            this.badEOL = true;
        }
        if (13 == i4 || 10 == i4) {
            this.linelen = 0;
        } else {
            int i5 = this.linelen + 1;
            this.linelen = i5;
            if (998 < i5) {
                this.longLine = true;
            }
        }
        if (MimeUtility.nonascii(i4)) {
            this.non_ascii++;
            if (this.breakOnNonAscii) {
                this.ret = 3;
                throw new EOFException();
            }
        } else {
            this.ascii++;
        }
        this.lastb = i4;
    }
    public int getAscii() {
        int i2 = this.ret;
        if (0 != i2) {
            return i2;
        }
        if (this.badEOL) {
            return 3;
        }
        int i3 = this.non_ascii;
        if (0 == i3) {
            if (this.longLine) {
                return 2;
            }
            return 1;
        }
        if (this.ascii > i3) {
            return 2;
        }
        return 3;
    }
}
