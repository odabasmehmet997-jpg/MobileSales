package com.google.zxing.pdf417.decoder;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

final class BoundingBox {
    private ResultPoint bottomLeft;
    private ResultPoint bottomRight;
    private BitMatrix image;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;
    private ResultPoint topLeft;
    private ResultPoint topRight;

    BoundingBox(final BitMatrix bitMatrix, final ResultPoint resultPoint, final ResultPoint resultPoint2, final ResultPoint resultPoint3, final ResultPoint resultPoint4) throws NotFoundException {
        if (!(null == resultPoint && null == resultPoint3) && (!(null == resultPoint2 && null == resultPoint4) && ((null == resultPoint || null != resultPoint2) && (null == resultPoint3 || null != resultPoint4)))) {
            this.init(bitMatrix, resultPoint, resultPoint2, resultPoint3, resultPoint4);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    BoundingBox(final BoundingBox boundingBox) {
        this.init(boundingBox.image, boundingBox.topLeft, boundingBox.bottomLeft, boundingBox.topRight, boundingBox.bottomRight);
    }

    private void init(final BitMatrix bitMatrix, final ResultPoint resultPoint, final ResultPoint resultPoint2, final ResultPoint resultPoint3, final ResultPoint resultPoint4) {
        image = bitMatrix;
        topLeft = resultPoint;
        bottomLeft = resultPoint2;
        topRight = resultPoint3;
        bottomRight = resultPoint4;
        this.calculateMinMaxValues();
    }

    static BoundingBox merge(final BoundingBox boundingBox, final BoundingBox boundingBox2) throws NotFoundException {
        if (null == boundingBox) {
            return boundingBox2;
        }
        return null == boundingBox2 ? boundingBox : new BoundingBox(boundingBox.image, boundingBox.topLeft, boundingBox.bottomLeft, boundingBox2.topRight, boundingBox2.bottomRight);
    }

    public com.google.zxing.pdf417.decoder.BoundingBox addMissingRows(final int r13, final int r14, final boolean r15) throws com.google.zxing.NotFoundException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.BoundingBox.addMissingRows(int, int, boolean):com.google.zxing.pdf417.decoder.BoundingBox");
    }

    private void calculateMinMaxValues() {
        if (null == this.topLeft) {
            topLeft = new ResultPoint(0.0f, topRight.getY());
            bottomLeft = new ResultPoint(0.0f, bottomRight.getY());
        } else if (null == this.topRight) {
            topRight = new ResultPoint((image.getWidth() - 1), topLeft.getY());
            bottomRight = new ResultPoint((image.getWidth() - 1), bottomLeft.getY());
        }
        minX = (int) Math.min(topLeft.getX(), bottomLeft.getX());
        maxX = (int) Math.max(topRight.getX(), bottomRight.getX());
        minY = (int) Math.min(topLeft.getY(), topRight.getY());
        maxY = (int) Math.max(bottomLeft.getY(), bottomRight.getY());
    }

    
    public int getMinX() {
        return minX;
    }

    
    public int getMaxX() {
        return maxX;
    }

    
    public int getMinY() {
        return minY;
    }

    
    public int getMaxY() {
        return maxY;
    }

    
    public ResultPoint getTopLeft() {
        return topLeft;
    }

    
    public ResultPoint getTopRight() {
        return topRight;
    }


    public ResultPoint getBottomLeft() {
        return bottomLeft;
    }

    public ResultPoint getBottomRight() {
        return bottomRight;
    }
}
