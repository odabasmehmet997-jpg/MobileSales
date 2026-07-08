package com.google.zxing.datamatrix;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.datamatrix.decoder.Decoder;
import com.google.zxing.datamatrix.detector.Detector;

import java.util.List;
import java.util.Map;

public final class DataMatrixReader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    public void reset() {
    }

    public Result decode(final BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return this.decode(binaryBitmap, null);
    }

    public Result decode(final BinaryBitmap binaryBitmap, final Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        final ResultPoint[] resultPointArr;
        final DecoderResult decoderResult;
        if (null == map || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            final DetectorResult detect = new Detector(binaryBitmap.getBlackMatrix()).detect();
            final DecoderResult decode = decoder.decode(detect.getBits());
            resultPointArr = detect.getPoints();
            decoderResult = decode;
        } else {
            decoderResult = decoder.decode(DataMatrixReader.extractPureBits(binaryBitmap.getBlackMatrix()));
            resultPointArr = DataMatrixReader.NO_POINTS;
        }
        final Result result = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.DATA_MATRIX);
        final List<byte[]> byteSegments = decoderResult.getByteSegments();
        if (null != byteSegments) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, byteSegments);
        }
        final String eCLevel = decoderResult.getECLevel();
        if (null != eCLevel) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, eCLevel);
        }
        return result;
    }

    private static BitMatrix extractPureBits(final BitMatrix bitMatrix) throws NotFoundException {
        final int[] topLeftOnBit = bitMatrix.getTopLeftOnBit();
        final int[] bottomRightOnBit = bitMatrix.getBottomRightOnBit();
        if (null == topLeftOnBit || null == bottomRightOnBit) {
            throw NotFoundException.getNotFoundInstance();
        }
        final int moduleSize = DataMatrixReader.moduleSize(topLeftOnBit, bitMatrix);
        final int i2 = topLeftOnBit[1];
        final int i3 = bottomRightOnBit[1];
        final int i4 = topLeftOnBit[0];
        final int i5 = ((bottomRightOnBit[0] - i4) + 1) / moduleSize;
        final int i6 = ((i3 - i2) + 1) / moduleSize;
        if (0 >= i5 || 0 >= i6) {
            throw NotFoundException.getNotFoundInstance();
        }
        final int i7 = moduleSize / 2;
        final int i8 = i2 + i7;
        final int i9 = i4 + i7;
        final BitMatrix bitMatrix2 = new BitMatrix(i5, i6);
        for (int i10 = 0; i10 < i6; i10++) {
            final int i11 = (i10 * moduleSize) + i8;
            for (int i12 = 0; i12 < i5; i12++) {
                if (bitMatrix.get((i12 * moduleSize) + i9, i11)) {
                    bitMatrix2.set(i12, i10);
                }
            }
        }
        return bitMatrix2;
    }

    private static int moduleSize(final int[] iArr, final BitMatrix bitMatrix) throws NotFoundException {
        final int width = bitMatrix.getWidth();
        int i2 = iArr[0];
        final int i3 = iArr[1];
        while (i2 < width && bitMatrix.get(i2, i3)) {
            i2++;
        }
        if (i2 != width) {
            final int i4 = i2 - iArr[0];
            if (0 != i4) {
                return i4;
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
