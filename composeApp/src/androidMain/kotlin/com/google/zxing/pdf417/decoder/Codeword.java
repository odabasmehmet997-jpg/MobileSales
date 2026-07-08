package com.google.zxing.pdf417.decoder;

final class Codeword {
    private final int bucket;
    private final int endX;
    private int rowNumber = -1;
    private final int startX;
    private final int value;

    Codeword(final int i2, final int i3, final int i4, final int i5) {
        startX = i2;
        endX = i3;
        bucket = i4;
        value = i5;
    }

    
    public boolean hasValidRowNumber() {
        return this.isValidRowNumber(rowNumber);
    }

    
    public boolean isValidRowNumber(final int i2) {
        return -1 != i2 && bucket == (i2 % 3) * 3;
    }

    
    public void setRowNumberAsRowIndicatorColumn() {
        rowNumber = ((value / 30) * 3) + (bucket / 3);
    }

    
    public int getWidth() {
        return endX - startX;
    }

    
    public int getStartX() {
        return startX;
    }

    
    public int getEndX() {
        return endX;
    }

    
    public int getBucket() {
        return bucket;
    }

    
    public int getValue() {
        return value;
    }

    
    public int getRowNumber() {
        return rowNumber;
    }

    
    public void setRowNumber(final int i2) {
        rowNumber = i2;
    }

    public String toString() {
        return rowNumber + "|" + value;
    }
}
