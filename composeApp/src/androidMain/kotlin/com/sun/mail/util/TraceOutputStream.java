package com.sun.mail.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;

public class TraceOutputStream extends FilterOutputStream {
    private boolean quote;
    private boolean trace;
    private final OutputStream traceOut;
    public TraceOutputStream(OutputStream outputStream, MailLogger mailLogger) {
        super(outputStream);
        this.trace = mailLogger.isLoggable(Level.FINEST);
        this.traceOut = new LogOutputStream(mailLogger);
    }
    public TraceOutputStream(OutputStream outputStream, OutputStream outputStream2) {
        super(outputStream);
        this.traceOut = outputStream2;
    }
    public void setTrace(boolean z) {
        this.trace = z;
    }
    public void setQuote(boolean z) {
        this.quote = z;
    }
    public void write(int i2) throws IOException {
        if (this.trace) {
            if (this.quote) {
                writeByte(i2);
            } else {
                this.traceOut.write(i2);
            }
        }
        this.out.write(i2);
    }
    public void write(byte[] bArr, int i2, int i3) throws IOException {
        if (this.trace) {
            if (this.quote) {
                for (int i4 = 0; i4 < i3; i4++) {
                    writeByte(bArr[i2 + i4]);
                }
            } else {
                this.traceOut.write(bArr, i2, i3);
            }
        }
        this.out.write(bArr, i2, i3);
    }
    private final void writeByte(int i2) throws IOException {
        int i3 = i2 & 255;
        if (127 < i3) {
            this.traceOut.write(77);
            this.traceOut.write(45);
            i3 = i2 & 127;
        }
        if (13 == i3) {
            this.traceOut.write(92);
            this.traceOut.write(114);
        } else if (10 == i3) {
            this.traceOut.write(92);
            this.traceOut.write(110);
            this.traceOut.write(10);
        } else if (9 == i3) {
            this.traceOut.write(92);
            this.traceOut.write(116);
        } else if (32 > i3) {
            this.traceOut.write(94);
            this.traceOut.write(i3 + 64);
        } else {
            this.traceOut.write(i3);
        }
    }
}
