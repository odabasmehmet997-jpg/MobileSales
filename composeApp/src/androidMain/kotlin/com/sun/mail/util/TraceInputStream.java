package com.sun.mail.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

public class TraceInputStream extends FilterInputStream {
    private boolean quote;
    private boolean trace;
    private final OutputStream traceOut;
    public TraceInputStream(final InputStream inputStream, final MailLogger mailLogger) {
        super(inputStream);
        trace = mailLogger.isLoggable(Level.FINEST);
        traceOut = new LogOutputStream(mailLogger);
    }
    public TraceInputStream(final InputStream inputStream, final OutputStream outputStream) {
        super(inputStream);
        traceOut = outputStream;
    }
    public void setTrace(final boolean z) {
        trace = z;
    }
    public void setQuote(final boolean z) {
        quote = z;
    }
    public int read() throws IOException {
        final int read = in.read();
        if (trace && -1 != read) {
            if (quote) {
                this.writeByte(read);
            } else {
                traceOut.write(read);
            }
        }
        return read;
    }
    public int read(final byte[] bArr, final int i2, final int i3) throws IOException {
        final int read = in.read(bArr, i2, i3);
        if (trace && -1 != read) {
            if (quote) {
                for (int i4 = 0; i4 < read; i4++) {
                    this.writeByte(bArr[i2 + i4]);
                }
            } else {
                traceOut.write(bArr, i2, read);
            }
        }
        return read;
    }
    private void writeByte(final int i2) throws IOException {
        int i3 = i2 & 255;
        if (127 < i3) {
            traceOut.write(77);
            traceOut.write(45);
            i3 = i2 & 127;
        }
        if (13 == i3) {
            traceOut.write(92);
            traceOut.write(114);
        } else if (10 == i3) {
            traceOut.write(92);
            traceOut.write(110);
            traceOut.write(10);
        } else if (9 == i3) {
            traceOut.write(92);
            traceOut.write(116);
        } else if (32 > i3) {
            traceOut.write(94);
            traceOut.write(i3 + 64);
        } else {
            traceOut.write(i3);
        }
    }
}
