package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

class DetectionResultColumn {
    private final BoundingBox boundingBox;
    private final Codeword[] codewords;
    DetectionResultColumn(final BoundingBox boundingBox2) {
        boundingBox = new BoundingBox(boundingBox2);
        codewords = new Codeword[((boundingBox2.getMaxY() - boundingBox2.getMinY()) + 1)];
    }
    public final Codeword getCodewordNearby(final int i2) {
        Codeword codeword;
        Codeword codeword2;
        final Codeword codeword3 = this.getCodeword(i2);
        if (null != codeword3) {
            return codeword3;
        }
        for (int i3 = 1; 5 > i3; i3++) {
            final int imageRowToCodewordIndex = this.imageRowToCodewordIndex(i2) - i3;
            if (0 <= imageRowToCodewordIndex && null != (codeword2 = this.codewords[imageRowToCodewordIndex])) {
                return codeword2;
            }
            final int imageRowToCodewordIndex2 = this.imageRowToCodewordIndex(i2) + i3;
            final Codeword[] codewordArr = codewords;
            if (imageRowToCodewordIndex2 < codewordArr.length && null != (codeword = codewordArr[imageRowToCodewordIndex2])) {
                return codeword;
            }
        }
        return null;
    }
    public final int imageRowToCodewordIndex(final int i2) {
        return i2 - boundingBox.getMinY();
    }
    public final void setCodeword(final int i2, final Codeword codeword) {
        codewords[this.imageRowToCodewordIndex(i2)] = codeword;
    }
    public final Codeword getCodeword(final int i2) {
        return codewords[this.imageRowToCodewordIndex(i2)];
    }
    public final BoundingBox getBoundingBox() {
        return boundingBox;
    }
    public final Codeword[] getCodewords() {
        return codewords;
    }
    public String toString() {
        final Formatter formatter = new Formatter();
        int i2 = 0;
        for (final Codeword codeword : codewords) {
            if (null == codeword) {
                formatter.format("%3d:    |   %n", i2);
                i2++;
            } else {
                formatter.format("%3d: %3d|%3d%n", Integer.valueOf(i2), Integer.valueOf(codeword.getRowNumber()), Integer.valueOf(codeword.getValue()));
                i2++;
            }
        }
        final String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}
