package com.google.zxing.qrcode.decoder;

import com.google.zxing.ResultPoint;

public final class QRCodeDecoderMetaData {
    private final boolean mirrored;
    QRCodeDecoderMetaData(boolean mirrored) {
        this.mirrored = mirrored;
    }
    public void applyMirroredCorrection(ResultPoint[] resultPoints) {
        if (!this.mirrored || resultPoints == null || resultPoints.length < 3) {
            return;
        }
        ResultPoint resultPoint = resultPoints[0];
        resultPoints[0] = resultPoints[2];
        resultPoints[2] = resultPoint;
    }
}
