package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

final class DetectionResult {
    private final int barcodeColumnCount;
    private final BarcodeMetadata barcodeMetadata;
    private BoundingBox boundingBox;
    private final DetectionResultColumn[] detectionResultColumns;

    DetectionResult(final BarcodeMetadata barcodeMetadata2, final BoundingBox boundingBox2) {
        barcodeMetadata = barcodeMetadata2;
        final int columnCount = barcodeMetadata2.getColumnCount();
        barcodeColumnCount = columnCount;
        boundingBox = boundingBox2;
        detectionResultColumns = new DetectionResultColumn[(columnCount + 2)];
    }

    
    public DetectionResultColumn[] getDetectionResultColumns() {
        this.adjustIndicatorColumnRowNumbers(detectionResultColumns[0]);
        this.adjustIndicatorColumnRowNumbers(detectionResultColumns[barcodeColumnCount + 1]);
        int i2 = 928;
        while (true) {
            final int adjustRowNumbers = this.adjustRowNumbers();
            if (0 < adjustRowNumbers && adjustRowNumbers < i2) {
                i2 = adjustRowNumbers;
            }
        }
        return detectionResultColumns;
    }

    private void adjustIndicatorColumnRowNumbers(final DetectionResultColumn detectionResultColumn) {
        if (null != detectionResultColumn) {
            ((DetectionResultRowIndicatorColumn) detectionResultColumn).adjustCompleteIndicatorColumnRowNumbers(barcodeMetadata);
        }
    }

    private int adjustRowNumbers() {
        final int adjustRowNumbersByRow = this.adjustRowNumbersByRow();
        if (0 == adjustRowNumbersByRow) {
            return 0;
        }
        for (int i2 = 1; i2 < barcodeColumnCount + 1; i2++) {
            final Codeword[] codewords = detectionResultColumns[i2].getCodewords();
            for (int i3 = 0; i3 < codewords.length; i3++) {
                final Codeword codeword = codewords[i3];
                if (null != codeword && !codeword.hasValidRowNumber()) {
                    this.adjustRowNumbers(i2, i3, codewords);
                }
            }
        }
        return adjustRowNumbersByRow;
    }

    private int adjustRowNumbersByRow() {
        this.adjustRowNumbersFromBothRI();
        return this.adjustRowNumbersFromLRI() + this.adjustRowNumbersFromRRI();
    }

    private void adjustRowNumbersFromBothRI() {
        final DetectionResultColumn[] detectionResultColumnArr = detectionResultColumns;
        final DetectionResultColumn detectionResultColumn = detectionResultColumnArr[0];
        if (null != detectionResultColumn && null != detectionResultColumnArr[this.barcodeColumnCount + 1]) {
            final Codeword[] codewords = detectionResultColumn.getCodewords();
            final Codeword[] codewords2 = detectionResultColumns[barcodeColumnCount + 1].getCodewords();
            for (int i2 = 0; i2 < codewords.length; i2++) {
                final Codeword codeword = codewords[i2];
                if (!(null == codeword || null == codewords2[i2] || codeword.getRowNumber() != codewords2[i2].getRowNumber())) {
                    for (int i3 = 1; i3 <= barcodeColumnCount; i3++) {
                        final Codeword codeword2 = detectionResultColumns[i3].getCodewords()[i2];
                        if (null != codeword2) {
                            codeword2.setRowNumber(codewords[i2].getRowNumber());
                            if (!codeword2.hasValidRowNumber()) {
                                detectionResultColumns[i3].getCodewords()[i2] = null;
                            }
                        }
                    }
                }
            }
        }
    }

    private int adjustRowNumbersFromRRI() {
        final DetectionResultColumn[] detectionResultColumnArr = detectionResultColumns;
        final int i2 = barcodeColumnCount;
        if (null == detectionResultColumnArr[i2 + 1]) {
            return 0;
        }
        final Codeword[] codewords = detectionResultColumnArr[i2 + 1].getCodewords();
        int i3 = 0;
        for (int i4 = 0; i4 < codewords.length; i4++) {
            final Codeword codeword = codewords[i4];
            if (null != codeword) {
                final int rowNumber = codeword.getRowNumber();
                int i5 = 0;
                for (int i6 = barcodeColumnCount + 1; 0 < i6 && 2 > i5; i6--) {
                    final Codeword codeword2 = detectionResultColumns[i6].getCodewords()[i4];
                    if (null != codeword2) {
                        i5 = DetectionResult.adjustRowNumberIfValid(rowNumber, i5, codeword2);
                        if (!codeword2.hasValidRowNumber()) {
                            i3++;
                        }
                    }
                }
            }
        }
        return i3;
    }

    private int adjustRowNumbersFromLRI() {
        final DetectionResultColumn detectionResultColumn = detectionResultColumns[0];
        if (null == detectionResultColumn) {
            return 0;
        }
        final Codeword[] codewords = detectionResultColumn.getCodewords();
        int i2 = 0;
        for (int i3 = 0; i3 < codewords.length; i3++) {
            final Codeword codeword = codewords[i3];
            if (null != codeword) {
                final int rowNumber = codeword.getRowNumber();
                int i4 = 0;
                for (int i5 = 1; i5 < barcodeColumnCount + 1 && 2 > i4; i5++) {
                    final Codeword codeword2 = detectionResultColumns[i5].getCodewords()[i3];
                    if (null != codeword2) {
                        i4 = DetectionResult.adjustRowNumberIfValid(rowNumber, i4, codeword2);
                        if (!codeword2.hasValidRowNumber()) {
                            i2++;
                        }
                    }
                }
            }
        }
        return i2;
    }

    private static int adjustRowNumberIfValid(final int i2, final int i3, final Codeword codeword) {
        if (null == codeword || codeword.hasValidRowNumber()) {
            return i3;
        }
        if (!codeword.isValidRowNumber(i2)) {
            return i3 + 1;
        }
        codeword.setRowNumber(i2);
        return 0;
    }

    private void adjustRowNumbers(final int i2, final int i3, final Codeword[] codewordArr) {
        final Codeword codeword = codewordArr[i3];
        final Codeword[] codewords = detectionResultColumns[i2 - 1].getCodewords();
        final DetectionResultColumn detectionResultColumn = detectionResultColumns[i2 + 1];
        final Codeword[] codewords2 = null != detectionResultColumn ? detectionResultColumn.getCodewords() : codewords;
        final Codeword[] codewordArr2 = new Codeword[14];
        codewordArr2[2] = codewords[i3];
        codewordArr2[3] = codewords2[i3];
        int i4 = 0;
        if (0 < i3) {
            final int i5 = i3 - 1;
            codewordArr2[0] = codewordArr[i5];
            codewordArr2[4] = codewords[i5];
            codewordArr2[5] = codewords2[i5];
        }
        if (1 < i3) {
            final int i6 = i3 - 2;
            codewordArr2[8] = codewordArr[i6];
            codewordArr2[10] = codewords[i6];
            codewordArr2[11] = codewords2[i6];
        }
        if (i3 < codewordArr.length - 1) {
            final int i7 = i3 + 1;
            codewordArr2[1] = codewordArr[i7];
            codewordArr2[6] = codewords[i7];
            codewordArr2[7] = codewords2[i7];
        }
        if (i3 < codewordArr.length - 2) {
            final int i8 = i3 + 2;
            codewordArr2[9] = codewordArr[i8];
            codewordArr2[12] = codewords[i8];
            codewordArr2[13] = codewords2[i8];
        }
        while (14 > i4 && !DetectionResult.adjustRowNumber(codeword, codewordArr2[i4])) {
            i4++;
        }
    }

    private static boolean adjustRowNumber(final Codeword codeword, final Codeword codeword2) {
        if (null == codeword2 || !codeword2.hasValidRowNumber() || codeword2.getBucket() != codeword.getBucket()) {
            return false;
        }
        codeword.setRowNumber(codeword2.getRowNumber());
        return true;
    }

    
    public int getBarcodeColumnCount() {
        return barcodeColumnCount;
    }

    
    public int getBarcodeRowCount() {
        return barcodeMetadata.getRowCount();
    }

    
    public int getBarcodeECLevel() {
        return barcodeMetadata.getErrorCorrectionLevel();
    }

    public void setBoundingBox(final BoundingBox boundingBox2) {
        boundingBox = boundingBox2;
    }

    
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    
    public void setDetectionResultColumn(final int i2, final DetectionResultColumn detectionResultColumn) {
        detectionResultColumns[i2] = detectionResultColumn;
    }

    
    public DetectionResultColumn getDetectionResultColumn(final int i2) {
        return detectionResultColumns[i2];
    }

    public String toString() {
        final DetectionResultColumn[] detectionResultColumnArr = detectionResultColumns;
        DetectionResultColumn detectionResultColumn = detectionResultColumnArr[0];
        if (null == detectionResultColumn) {
            detectionResultColumn = detectionResultColumnArr[barcodeColumnCount + 1];
        }
        final Formatter formatter = new Formatter();
        for (int i2 = 0; i2 < detectionResultColumn.getCodewords().length; i2++) {
            formatter.format("CW %3d:", Integer.valueOf(i2));
            for (int i3 = 0; i3 < barcodeColumnCount + 2; i3++) {
                final DetectionResultColumn detectionResultColumn2 = detectionResultColumns[i3];
                if (null == detectionResultColumn2) {
                    formatter.format("    |   ");
                } else {
                    final Codeword codeword = detectionResultColumn2.getCodewords()[i2];
                    if (null == codeword) {
                        formatter.format("    |   ");
                    } else {
                        formatter.format(" %3d|%3d", Integer.valueOf(codeword.getRowNumber()), Integer.valueOf(codeword.getValue()));
                    }
                }
            }
            formatter.format("%n");
        }
        final String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}
