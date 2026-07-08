package com.sun.mail.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LineOutputStream extends FilterOutputStream {
    private static final byte[] newline;
    static {
        final byte[] bArr = new byte[2];
        newline = bArr;
        bArr[0] = 13;
        bArr[1] = 10;
    }
    public LineOutputStream(final OutputStream outputStream) {
        super(outputStream);
    }
    public void writeln(final String str) throws IOException {
        out.write(ASCIIUtility.getBytes(str));
        out.write(LineOutputStream.newline);
    }
    public void writeln() throws IOException {
        out.write(LineOutputStream.newline);
    }
}
