package com.google.zxing.datamatrix.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

public final class Decoder {
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.DATA_MATRIX_FIELD_256);

    public DecoderResult decode(final BitMatrix bitMatrix) throws FormatException, ChecksumException {
        final BitMatrixParser bitMatrixParser = new BitMatrixParser(bitMatrix);
        final DataBlock[] dataBlocks = DataBlock.getDataBlocks(bitMatrixParser.readCodewords(), bitMatrixParser.getVersion());
        int i2 = 0;
        for (final DataBlock numDataCodewords : dataBlocks) {
            i2 += numDataCodewords.getNumDataCodewords();
        }
        final byte[] bArr = new byte[i2];
        final int length = dataBlocks.length;
        for (int i3 = 0; i3 < length; i3++) {
            final DataBlock dataBlock = dataBlocks[i3];
            final byte[] codewords = dataBlock.getCodewords();
            final int numDataCodewords2 = dataBlock.getNumDataCodewords();
            this.correctErrors(codewords, numDataCodewords2);
            for (int i4 = 0; i4 < numDataCodewords2; i4++) {
                bArr[(i4 * length) + i3] = codewords[i4];
            }
        }
        return DecodedBitStreamParser.decode(bArr);
    }

    private void correctErrors(final byte[] bArr, final int i2) throws ChecksumException {
        final int length = bArr.length;
        final int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = bArr[i3] & 255;
        }
        try {
            rsDecoder.decode(iArr, bArr.length - i2);
            for (int i4 = 0; i4 < i2; i4++) {
                bArr[i4] = (byte) iArr[i4];
            }
        } catch (final ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
