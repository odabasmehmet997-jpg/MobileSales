package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

import java.io.UnsupportedEncodingException;
import java.util.Map;
public final class QRCodeWriter implements Writer {
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r5, int r6, Map<EncodeHintType, ?> map) throws NumberFormatException, WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (barcodeFormat != BarcodeFormat.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + barcodeFormat);
        }
        if (r5 < 0 || r6 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + r5 + 'x' + r6);
        }
        ErrorCorrectionLevel errorCorrectionLevelValueOf = ErrorCorrectionLevel.L;
        int r4 = 4;
        if (map != null) {
            EncodeHintType encodeHintType = EncodeHintType.ERROR_CORRECTION;
            if (map.containsKey(encodeHintType)) {
                errorCorrectionLevelValueOf = ErrorCorrectionLevel.valueOf(map.get(encodeHintType).toString());
            }
            EncodeHintType encodeHintType2 = EncodeHintType.MARGIN;
            if (map.containsKey(encodeHintType2)) {
                r4 = Integer.parseInt(map.get(encodeHintType2).toString());
            }
        }
        try {
            return renderResult(Encoder.encode(str, errorCorrectionLevelValueOf, map), r5, r6, r4);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    private static BitMatrix renderResult(QRCode qRCode, int r10, int r11, int r12) {
        ByteMatrix matrix = qRCode.getMatrix();
        if (matrix == null) {
            throw new IllegalStateException();
        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int r122 = r12 << 1;
        int r3 = width + r122;
        int r123 = r122 + height;
        int r102 = Math.max(r10, r3);
        int r112 = Math.max(r11, r123);
        int r124 = Math.min(r102 / r3, r112 / r123);
        int r32 = (r102 - (width * r124)) / 2;
        int r4 = (r112 - (height * r124)) / 2;
        BitMatrix bitMatrix = new BitMatrix(r102, r112);
        int r113 = 0;
        while (r113 < height) {
            int r6 = 0;
            int r7 = r32;
            while (r6 < width) {
                if (matrix.get(r6, r113) == 1) {
                    bitMatrix.setRegion(r7, r4, r124, r124);
                }
                r6++;
                r7 += r124;
            }
            r113++;
            r4 += r124;
        }
        return bitMatrix;
    }
}