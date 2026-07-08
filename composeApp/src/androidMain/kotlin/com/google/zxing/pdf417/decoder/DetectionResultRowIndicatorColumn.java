package com.google.zxing.pdf417.decoder;

import com.google.zxing.ResultPoint;

final class DetectionResultRowIndicatorColumn extends DetectionResultColumn {
    private final boolean isLeft;

    DetectionResultRowIndicatorColumn(final BoundingBox boundingBox, final boolean z) {
        super(boundingBox);
        isLeft = z;
    }

    private void setRowNumbers() {
        for (final Codeword codeword : this.getCodewords()) {
            if (null != codeword) {
                codeword.setRowNumberAsRowIndicatorColumn();
            }
        }
    }

    
    public void adjustCompleteIndicatorColumnRowNumbers(final BarcodeMetadata barcodeMetadata) {
        final Codeword[] codewords = this.getCodewords();
        this.setRowNumbers();
        this.removeIncorrectCodewords(codewords, barcodeMetadata);
        final BoundingBox boundingBox = this.getBoundingBox();
        final ResultPoint topLeft = isLeft ? boundingBox.getTopLeft() : boundingBox.getTopRight();
        final ResultPoint bottomLeft = isLeft ? boundingBox.getBottomLeft() : boundingBox.getBottomRight();
        int imageRowToCodewordIndex = this.imageRowToCodewordIndex((int) topLeft.getY());
        final int imageRowToCodewordIndex2 = this.imageRowToCodewordIndex((int) bottomLeft.getY());
        int i2 = -1;
        int i3 = 0;
        int i4 = 1;
        while (imageRowToCodewordIndex < imageRowToCodewordIndex2) {
            final Codeword codeword = codewords[imageRowToCodewordIndex];
            if (null != codeword) {
                int rowNumber = codeword.getRowNumber() - i2;
                if (0 == rowNumber) {
                    i3++;
                } else {
                    if (1 == rowNumber) {
                        i4 = Math.max(i4, i3);
                        i2 = codeword.getRowNumber();
                    } else if (0 > rowNumber || codeword.getRowNumber() >= barcodeMetadata.getRowCount() || rowNumber > imageRowToCodewordIndex) {
                        codewords[imageRowToCodewordIndex] = null;
                    } else {
                        if (2 < i4) {
                            rowNumber *= i4 - 2;
                        }
                        boolean z = rowNumber >= imageRowToCodewordIndex;
                        for (int i5 = 1; i5 <= rowNumber && !z; i5++) {
                            z = null != codewords[imageRowToCodewordIndex - i5];
                        }
                        if (z) {
                            codewords[imageRowToCodewordIndex] = null;
                        } else {
                            i2 = codeword.getRowNumber();
                        }
                    }
                    i3 = 1;
                }
            }
            imageRowToCodewordIndex++;
        }
    }

    
    public int[] getRowHeights() {
        int rowNumber;
        final BarcodeMetadata barcodeMetadata = this.getBarcodeMetadata();
        if (null == barcodeMetadata) {
            return null;
        }
        this.adjustIncompleteIndicatorColumnRowNumbers(barcodeMetadata);
        final int rowCount = barcodeMetadata.getRowCount();
        final int[] iArr = new int[rowCount];
        for (final Codeword codeword : this.getCodewords()) {
            if (null != codeword && (rowNumber = codeword.getRowNumber()) < rowCount) {
                iArr[rowNumber] = iArr[rowNumber] + 1;
            }
        }
        return iArr;
    }

    private void adjustIncompleteIndicatorColumnRowNumbers(final BarcodeMetadata barcodeMetadata) {
        final BoundingBox boundingBox = this.getBoundingBox();
        final ResultPoint topLeft = isLeft ? boundingBox.getTopLeft() : boundingBox.getTopRight();
        final ResultPoint bottomLeft = isLeft ? boundingBox.getBottomLeft() : boundingBox.getBottomRight();
        final int imageRowToCodewordIndex = this.imageRowToCodewordIndex((int) bottomLeft.getY());
        final Codeword[] codewords = this.getCodewords();
        int i2 = -1;
        int i3 = 0;
        int i4 = 1;
        for (int imageRowToCodewordIndex2 = this.imageRowToCodewordIndex((int) topLeft.getY()); imageRowToCodewordIndex2 < imageRowToCodewordIndex; imageRowToCodewordIndex2++) {
            final Codeword codeword = codewords[imageRowToCodewordIndex2];
            if (null != codeword) {
                codeword.setRowNumberAsRowIndicatorColumn();
                final int rowNumber = codeword.getRowNumber() - i2;
                if (0 == rowNumber) {
                    i3++;
                } else {
                    if (1 == rowNumber) {
                        i4 = Math.max(i4, i3);
                        i2 = codeword.getRowNumber();
                    } else if (codeword.getRowNumber() >= barcodeMetadata.getRowCount()) {
                        codewords[imageRowToCodewordIndex2] = null;
                    } else {
                        i2 = codeword.getRowNumber();
                    }
                    i3 = 1;
                }
            }
        }
    }

    
    public BarcodeMetadata getBarcodeMetadata() {
        final Codeword[] codewords = this.getCodewords();
        final BarcodeValue barcodeValue = new BarcodeValue();
        final BarcodeValue barcodeValue2 = new BarcodeValue();
        final BarcodeValue barcodeValue3 = new BarcodeValue();
        final BarcodeValue barcodeValue4 = new BarcodeValue();
        for (final Codeword codeword : codewords) {
            if (null != codeword) {
                codeword.setRowNumberAsRowIndicatorColumn();
                final int value = codeword.getValue() % 30;
                int rowNumber = codeword.getRowNumber();
                if (!isLeft) {
                    rowNumber += 2;
                }
                final int i2 = rowNumber % 3;
                if (0 == i2) {
                    barcodeValue2.setValue((value * 3) + 1);
                } else if (1 == i2) {
                    barcodeValue4.setValue(value / 3);
                    barcodeValue3.setValue(value % 3);
                } else if (2 == i2) {
                    barcodeValue.setValue(value + 1);
                }
            }
        }
        if (0 == barcodeValue.getValue().length || 0 == barcodeValue2.getValue().length || 0 == barcodeValue3.getValue().length || 0 == barcodeValue4.getValue().length || 0 >= barcodeValue.getValue()[0] || 3 > barcodeValue2.getValue()[0] + barcodeValue3.getValue()[0] || 90 < barcodeValue2.getValue()[0] + barcodeValue3.getValue()[0]) {
            return null;
        }
        final BarcodeMetadata barcodeMetadata = new BarcodeMetadata(barcodeValue.getValue()[0], barcodeValue2.getValue()[0], barcodeValue3.getValue()[0], barcodeValue4.getValue()[0]);
        this.removeIncorrectCodewords(codewords, barcodeMetadata);
        return barcodeMetadata;
    }

    private void removeIncorrectCodewords(final Codeword[] codewordArr, final BarcodeMetadata barcodeMetadata) {
        for (int i2 = 0; i2 < codewordArr.length; i2++) {
            final Codeword codeword = codewordArr[i2];
            if (null != codeword) {
                final int value = codeword.getValue() % 30;
                int rowNumber = codeword.getRowNumber();
                if (rowNumber > barcodeMetadata.getRowCount()) {
                    codewordArr[i2] = null;
                } else {
                    if (!isLeft) {
                        rowNumber += 2;
                    }
                    final int i3 = rowNumber % 3;
                    if (0 != i3) {
                        if (1 != i3) {
                            if (2 == i3 && value + 1 != barcodeMetadata.getColumnCount()) {
                                codewordArr[i2] = null;
                            }
                        } else if (value / 3 != barcodeMetadata.getErrorCorrectionLevel() || value % 3 != barcodeMetadata.getRowCountLowerPart()) {
                            codewordArr[i2] = null;
                        }
                    } else if ((value * 3) + 1 != barcodeMetadata.getRowCountUpperPart()) {
                        codewordArr[i2] = null;
                    }
                }
            }
        }
    }

    
    public boolean isLeft() {
        return isLeft;
    }

    public String toString() {
        return "IsLeft: " + isLeft + 10 + super.toString();
    }
}
