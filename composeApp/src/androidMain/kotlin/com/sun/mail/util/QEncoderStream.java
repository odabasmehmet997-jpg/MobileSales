package com.sun.mail.util;

import java.io.IOException;
import java.io.OutputStream;

public class QEncoderStream extends QPEncoderStream {
    private static final String TEXT_SPECIALS = "=_?";
    private static final String WORD_SPECIALS = "=_?\"#%&'(),.:;<>@[\\]^`{|}~";
    private final String specials;

    public QEncoderStream(final OutputStream outputStream, final boolean z) {
        super(outputStream, Integer.MAX_VALUE);
        specials = z ? QEncoderStream.WORD_SPECIALS : QEncoderStream.TEXT_SPECIALS;
    }

    public void write(final int i2) throws IOException {
        final int i3 = i2 & 255;
        if (32 == i3) {
            this.output(95, false);
        } else this.output(i3, 32 > i3 || 127 <= i3 || 0 <= this.specials.indexOf(i3));
    }

    public static int encodedLength(final byte[] bArr, final boolean z) {
        final String str = z ? QEncoderStream.WORD_SPECIALS : QEncoderStream.TEXT_SPECIALS;
        int i2 = 0;
        for (final byte b2 : bArr) {
            final byte b3 = ( byte ) (b2 & 255);
            i2 = (32 > b3 || Byte.MAX_VALUE <= b3 || 0 <= str.indexOf(b3)) ? i2 + 3 : i2 + 1;
        }
        return i2;
    }
}
