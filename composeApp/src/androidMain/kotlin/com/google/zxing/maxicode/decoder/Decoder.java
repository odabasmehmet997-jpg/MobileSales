package com.google.zxing.maxicode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Map;

public final class Decoder {
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.MAXICODE_FIELD_64);

    public DecoderResult decode(final BitMatrix bitMatrix, final Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        final byte[] bArr;
        final byte[] readCodewords = new BitMatrixParser(bitMatrix).readCodewords();
        this.correctErrors(readCodewords, 0, 10, 10, 0);
        final byte b2 = readCodewords[0] & 15;
        if (2 == b2 || 3 == b2 || 4 == b2) {
            final byte[] bArr2 = readCodewords;
            this.correctErrors(bArr2, 20, 84, 40, 1);
            this.correctErrors(bArr2, 20, 84, 40, 2);
            bArr = new byte[94];
        } else if (5 == b2) {
            final byte[] bArr3 = readCodewords;
            this.correctErrors(bArr3, 20, 68, 56, 1);
            this.correctErrors(bArr3, 20, 68, 56, 2);
            bArr = new byte[78];
        } else {
            throw FormatException.getFormatInstance();
        }
        System.arraycopy(readCodewords, 0, bArr, 0, 10);
        System.arraycopy(readCodewords, 20, bArr, 10, bArr.length - 10);
        return DecodedBitStreamParser.decode(bArr, b2);
    }

    private void correctErrors(final byte[] bArr, final int i2, final int i3, final int i4, final int i5) throws ChecksumException {
        final int i6 = i3 + i4;
        final int i7 = 0 == i5 ? 1 : 2;
        final int[] iArr = new int[(i6 / i7)];
        for (int i8 = 0; i8 < i6; i8++) {
            if (0 == i5 || i8 % 2 == i5 - 1) {
                iArr[i8 / i7] = bArr[i8 + i2] & 255;
            }
        }
        try {
            rsDecoder.decode(iArr, i4 / i7);
            for (int i9 = 0; i9 < i3; i9++) {
                if (0 == i5 || i9 % 2 == i5 - 1) {
                    bArr[i9 + i2] = (byte) iArr[i9 / i7];
                }
            }
        } catch (final ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
