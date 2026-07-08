package com.sun.mail.util;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class UUEncoderStream extends FilterOutputStream {
    private final byte[] buffer;
    private int bufsize;
    protected int mode;
    protected String name;
    private boolean wrotePrefix;
    public UUEncoderStream(OutputStream outputStream) {
        this(outputStream, "encoder.buf", 644);
    }
    public UUEncoderStream(OutputStream outputStream, String str) {
        this(outputStream, str, 644);
    }
    public UUEncoderStream(OutputStream outputStream, String str, int i2) {
        super(outputStream);
        this.bufsize = 0;
        this.wrotePrefix = false;
        this.name = str;
        this.mode = i2;
        this.buffer = new byte[45];
    }
    public void setNameMode(String str, int i2) {
        this.name = str;
        this.mode = i2;
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
        byte[] bArr = this.buffer;
        int i3 = this.bufsize;
        int i4 = i3 + 1;
        this.bufsize = i4;
        bArr[i3] = (byte) i2;
        if (45 == i4) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                writePrefix();
            }
            encode();
            this.bufsize = 0;
        }
    }
    public void flush() throws IOException {
        if (0 < bufsize) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                writePrefix();
            }
            encode();
        }
        writeSuffix();
        this.out.flush();
    }
    public void close() throws IOException {
        flush();
        this.out.close();
    }
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void writePrefix() throws IOException {
        if (!this.wrotePrefix) {
            try (PrintStream printStream = new PrintStream(this.out, false, StandardCharsets.UTF_8)) {
                final String stringBuffer = "begin " +
                        this.mode +
                        " " +
                        this.name;
                printStream.println(stringBuffer);
                printStream.flush();
            }
            this.wrotePrefix = true;
        }
    }
    private void writeSuffix() throws IOException {
        PrintStream printStream = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            printStream = new PrintStream(this.out, false, StandardCharsets.US_ASCII);
        }
        printStream.println(" \nend");
        printStream.flush();
    }
    private void encode() throws IOException {
        byte b2;
        this.out.write((this.bufsize & 63) + 32);
        int i2 = 0;
        while (true) {
            int i3 = this.bufsize;
            if (i2 < i3) {
                byte[] bArr = this.buffer;
                int i4 = i2 + 1;
                byte b3 = bArr[i2];
                byte b4 = 1;
                if (i4 < i3) {
                    int i5 = i2 + 2;
                    byte b5 = bArr[i4];
                    if (i5 < i3) {
                        i2 += 3;
                        b2 = bArr[i5];
                    } else {
                        b2 = 1;
                        i2 = i5;
                    }
                    b4 = b5;
                } else {
                    i2 = i4;
                    b2 = 1;
                }
                this.out.write(((b3 >>> 2) & 63) + 32);
                this.out.write((((b3 << 4) & 48) | ((b4 >>> 4) & 15)) + 32);
                this.out.write((((b4 << 2) & 60) | ((b2 >>> 6) & 3)) + 32);
                this.out.write((b2 & 63) + 32);
            } else {
                this.out.write(10);
                return;
            }
        }
    }
}
