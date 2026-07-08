package com.google.zxing.pdf417;

import com.google.zxing.*;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.pdf417.decoder.PDF417ScanningDecoder;
import com.google.zxing.pdf417.detector.Detector;
import com.google.zxing.pdf417.detector.PDF417DetectorResult;

import java.util.ArrayList;
import java.util.Map;

public final class PDF417Reader implements Reader, MultipleBarcodeReader {
    public void reset() {
    }

    public Result decode(final BinaryBitmap binaryBitmap) throws NotFoundException, FormatException, ChecksumException {
        return this.decode(binaryBitmap, null);
    }

    public Result decode(final BinaryBitmap binaryBitmap, final Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        final Result result;
        final Result[] decode = PDF417Reader.decode(binaryBitmap, map, false);
        if (null != decode && 0 != decode.length && null != (result = decode[0])) {
            return result;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static Result[] decode(final BinaryBitmap binaryBitmap, final Map<DecodeHintType, ?> map, final boolean z) throws NotFoundException, FormatException, ChecksumException {
        final ArrayList arrayList = new ArrayList();
        final PDF417DetectorResult detect = Detector.detect(binaryBitmap, map, z);
        for (final ResultPoint[] next : detect.points()) {
            final DecoderResult decode = PDF417ScanningDecoder.decode(detect.bits(), next[4], next[5], next[6], next[7], PDF417Reader.getMinCodewordWidth(next), PDF417Reader.getMaxCodewordWidth(next));
            final Result result = new Result(decode.getText(), decode.getRawBytes(), next, BarcodeFormat.PDF_417);
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decode.getECLevel());
            final PDF417ResultMetadata pDF417ResultMetadata = (PDF417ResultMetadata) decode.getOther();
            if (null != pDF417ResultMetadata) {
                result.putMetadata(ResultMetadataType.PDF417_EXTRA_METADATA, pDF417ResultMetadata);
            }
            arrayList.add(result);
        }
        return (Result[]) arrayList.toArray(new Result[arrayList.size()]);
    }

    private static int getMaxWidth(final ResultPoint resultPoint, final ResultPoint resultPoint2) {
        if (null == resultPoint || null == resultPoint2) {
            return 0;
        }
        return (int) Math.abs(resultPoint.getX() - resultPoint2.getX());
    }

    private static int getMinWidth(final ResultPoint resultPoint, final ResultPoint resultPoint2) {
        if (null == resultPoint || null == resultPoint2) {
            return Integer.MAX_VALUE;
        }
        return (int) Math.abs(resultPoint.getX() - resultPoint2.getX());
    }

    private static int getMaxCodewordWidth(final ResultPoint[] resultPointArr) {
        return Math.max(Math.max(PDF417Reader.getMaxWidth(resultPointArr[0], resultPointArr[4]), (PDF417Reader.getMaxWidth(resultPointArr[6], resultPointArr[2]) * 17) / 18), Math.max(PDF417Reader.getMaxWidth(resultPointArr[1], resultPointArr[5]), (PDF417Reader.getMaxWidth(resultPointArr[7], resultPointArr[3]) * 17) / 18));
    }

    private static int getMinCodewordWidth(final ResultPoint[] resultPointArr) {
        return Math.min(Math.min(PDF417Reader.getMinWidth(resultPointArr[0], resultPointArr[4]), (PDF417Reader.getMinWidth(resultPointArr[6], resultPointArr[2]) * 17) / 18), Math.min(PDF417Reader.getMinWidth(resultPointArr[1], resultPointArr[5]), (PDF417Reader.getMinWidth(resultPointArr[7], resultPointArr[3]) * 17) / 18));
    }
}
