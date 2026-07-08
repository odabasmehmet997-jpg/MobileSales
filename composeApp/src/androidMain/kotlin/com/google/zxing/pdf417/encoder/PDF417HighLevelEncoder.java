package com.google.zxing.pdf417.encoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

final class PDF417HighLevelEncoder {
    private static final Charset DEFAULT_ENCODING = StandardCharsets.ISO_8859_1;
    private static final byte[] MIXED;
    private static final byte[] PUNCTUATION = new byte[128];
    private static final byte[] TEXT_MIXED_RAW = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0};
    private static final byte[] TEXT_PUNCTUATION_RAW = {59, 60, 62, 64, 91, 92, 93, 95, 96, 126, 33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, 125, 39, 0};

    static {
        int i2 = 0;
        final byte[] bArr = new byte[128];
        MIXED = bArr;
        Arrays.fill(bArr, (byte) -1);
        int i3 = 0;
        while (true) {
            final byte[] bArr2 = PDF417HighLevelEncoder.TEXT_MIXED_RAW;
            if (i3 >= bArr2.length) {
                break;
            }
            final byte b2 = bArr2[i3];
            if (0 < b2) {
                PDF417HighLevelEncoder.MIXED[b2] = (byte) i3;
            }
            i3++;
        }
        Arrays.fill(PDF417HighLevelEncoder.PUNCTUATION, (byte) -1);
        while (true) {
            final byte[] bArr3 = PDF417HighLevelEncoder.TEXT_PUNCTUATION_RAW;
            if (i2 < bArr3.length) {
                final byte b3 = bArr3[i2];
                if (0 < b3) {
                    PDF417HighLevelEncoder.PUNCTUATION[b3] = (byte) i2;
                }
                i2++;
            } else {
                return;
            }
        }
    }

    private PDF417HighLevelEncoder() {
    }
}
