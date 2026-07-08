package com.google.zxing.maxicode;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.maxicode.decoder.Decoder;

import java.util.Map;

public final class MaxiCodeReader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    public void reset() {
    }

    public Result decode(final BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return this.decode(binaryBitmap, null);
    }

    public Result decode(final BinaryBitmap binaryBitmap, final Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        if (null == map || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            throw NotFoundException.getNotFoundInstance();
        }
        final DecoderResult decode = decoder.decode(MaxiCodeReader.extractPureBits(binaryBitmap.getBlackMatrix()), map);
        final Result result = new Result(decode.getText(), decode.getRawBytes(), MaxiCodeReader.NO_POINTS, BarcodeFormat.MAXICODE);
        final String eCLevel = decode.getECLevel();
        if (null != eCLevel) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, eCLevel);
        }
        return result;
    }

    private static BitMatrix extractPureBits(final BitMatrix bitMatrix) throws NotFoundException {
        final int[] enclosingRectangle = bitMatrix.getEnclosingRectangle();
        if (null != enclosingRectangle) {
            final int i2 = enclosingRectangle[0];
            final int i3 = enclosingRectangle[1];
            final int i4 = enclosingRectangle[2];
            final int i5 = enclosingRectangle[3];
            final BitMatrix bitMatrix2 = new BitMatrix(30, 33);
            for (int i6 = 0; 33 > i6; i6++) {
                final int i7 = (((i6 * i5) + (i5 / 2)) / 33) + i3;
                for (int i8 = 0; 30 > i8; i8++) {
                    if (bitMatrix.get(((((i8 * i4) + (i4 / 2)) + (((i6 & 1) * i4) / 2)) / 30) + i2, i7)) {
                        bitMatrix2.set(i8, i6);
                    }
                }
            }
            return bitMatrix2;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
