package com.sun.mail.util;

import java.io.IOException;
import java.io.InputStream;

public class QDecoderStream extends QPDecoderStream {
    public QDecoderStream(InputStream inputStream) {
        super(inputStream);
    }
    public int read() throws IOException {
        int read = this.in.read();
        if (95 == read) {
            return 32;
        }
        if (61 != read) {
            return read;
        }
        this.ba[0] = (byte) this.in.read();
        this.ba[1] = (byte) this.in.read();
        try {
            return ASCIIUtility.parseInt(this.ba, 0, 2, 16);
        } catch (NumberFormatException e2) {
            final String stringBuffer = "QDecoder: Error in QP stream " +
                    e2.getMessage();
            throw new DecodingException(stringBuffer);
        }
    }
}
