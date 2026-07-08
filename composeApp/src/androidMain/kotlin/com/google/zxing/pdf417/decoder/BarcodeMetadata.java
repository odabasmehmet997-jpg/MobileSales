package com.google.zxing.pdf417.decoder;

final class BarcodeMetadata {
    private final int columnCount;
    private final int errorCorrectionLevel;
    private final int rowCount;
    private final int rowCountLowerPart;
    private final int rowCountUpperPart;

    BarcodeMetadata(final int i2, final int i3, final int i4, final int i5) {
        columnCount = i2;
        errorCorrectionLevel = i5;
        rowCountUpperPart = i3;
        rowCountLowerPart = i4;
        rowCount = i3 + i4;
    }

    
    public int getColumnCount() {
        return columnCount;
    }

    
    public int getErrorCorrectionLevel() {
        return errorCorrectionLevel;
    }

    
    public int getRowCount() {
        return rowCount;
    }

    
    public int getRowCountUpperPart() {
        return rowCountUpperPart;
    }

    
    public int getRowCountLowerPart() {
        return rowCountLowerPart;
    }
}
