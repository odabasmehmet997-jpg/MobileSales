package com.sun.mail.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class QPEncoderStream extends FilterOutputStream {
    private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final int bytesPerLine;
    private int count;
    private boolean gotCR;
    private boolean gotSpace;
    public QPEncoderStream(OutputStream outputStream, int i2) {
        super(outputStream);
        this.count = 0;
        this.gotSpace = false;
        this.gotCR = false;
        this.bytesPerLine = i2 - 1;
    }
    public QPEncoderStream(OutputStream outputStream) {
        this(outputStream, 76);
    }
    public void write(byte[] bArr, int i2, int i3) throws IOException {
        for (int i4 = 0; i4 < i3; i4++) {
            write(bArr[i2 + i4]);
        }
    }
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }
    public void write(int i2) throws IOException {
        int i3 = i2 & 255;
        if (this.gotSpace) {
            output(32, 13 == i3 || 10 == i3);
            this.gotSpace = false;
        }
        if (13 == i3) {
            this.gotCR = true;
            outputCRLF();
            return;
        }
        if (10 == i3) {
            if (!this.gotCR) {
                outputCRLF();
            }
        } else if (32 == i3) {
            this.gotSpace = true;
        } else output(i3, 32 > i3 || 127 <= i3 || 61 == i3);
        this.gotCR = false;
    }
    public void flush() throws IOException {
        this.out.flush();
    }
    public void close() throws IOException {
        if (this.gotSpace) {
            output(32, true);
            this.gotSpace = false;
        }
        this.out.close();
    }
    private void outputCRLF() throws IOException {
        this.out.write(13);
        this.out.write(10);
        this.count = 0;
    }
    public void output(int i2, boolean z) throws IOException {
        if (z) {
            int i3 = this.count + 3;
            this.count = i3;
            if (i3 > this.bytesPerLine) {
                this.out.write(61);
                this.out.write(13);
                this.out.write(10);
                this.count = 3;
            }
            this.out.write(61);
            OutputStream outputStream = this.out;
            char[] cArr = hex;
            outputStream.write(cArr[i2 >> 4]);
            this.out.write(cArr[i2 & 15]);
            return;
        }
        int i4 = this.count + 1;
        this.count = i4;
        if (i4 > this.bytesPerLine) {
            this.out.write(61);
            this.out.write(13);
            this.out.write(10);
            this.count = 1;
        }
        this.out.write(i2);
    }
}
