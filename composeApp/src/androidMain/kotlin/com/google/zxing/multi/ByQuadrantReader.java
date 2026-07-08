package com.google.zxing.multi;

import com.google.zxing.*;

import java.util.Map;

public final class ByQuadrantReader implements Reader {
    private final Reader delegate = null;
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return decode(binaryBitmap, null);
    }
    public Result decode(BinaryBitmap r6, Map<DecodeHintType, ?> r7) throws NotFoundException, ChecksumException, FormatException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.multi.ByQuadrantReader.decode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }
    public void reset() {
        this.delegate.reset();
    }
    private static void makeAbsolute(ResultPoint[] resultPointArr, int i2, int i3) {
        if (null != resultPointArr) {
            for (int i4 = 0; i4 < resultPointArr.length; i4++) {
                ResultPoint resultPoint = resultPointArr[i4];
                resultPointArr[i4] = new ResultPoint(resultPoint.getX() + i2, resultPoint.getY() + i3);
            }
        }
    }
}
