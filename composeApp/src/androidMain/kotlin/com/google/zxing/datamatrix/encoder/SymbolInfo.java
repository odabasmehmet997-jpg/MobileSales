package com.google.zxing.datamatrix.encoder;

import org.kxml2.wap.Wbxml;

public class SymbolInfo {
    static final SymbolInfo[] PROD_SYMBOLS;
    private static final SymbolInfo[] symbols;
    private final int dataCapacity;
    private final int dataRegions;
    private final int errorCodewords;
    public final int matrixHeight;
    public final int matrixWidth;
    private final boolean rectangular;
    private final int rsBlockData;
    private final int rsBlockError;

    static {
        final SymbolInfo[] symbolInfoArr = {new SymbolInfo(false, 3, 5, 8, 8, 1), new SymbolInfo(false, 5, 7, 10, 10, 1), new SymbolInfo(true, 5, 7, 16, 6, 1), new SymbolInfo(false, 8, 10, 12, 12, 1), new SymbolInfo(true, 10, 11, 14, 6, 2), new SymbolInfo(false, 12, 12, 14, 14, 1), new SymbolInfo(true, 16, 14, 24, 10, 1), new SymbolInfo(false, 18, 14, 16, 16, 1), new SymbolInfo(false, 22, 18, 18, 18, 1), new SymbolInfo(true, 22, 18, 16, 10, 2), new SymbolInfo(false, 30, 20, 20, 20, 1), new SymbolInfo(true, 32, 24, 16, 14, 2), new SymbolInfo(false, 36, 24, 22, 22, 1), new SymbolInfo(false, 44, 28, 24, 24, 1), new SymbolInfo(true, 49, 28, 22, 14, 2), new SymbolInfo(false, 62, 36, 14, 14, 4), new SymbolInfo(false, 86, 42, 16, 16, 4), new SymbolInfo(false, 114, 48, 18, 18, 4), new SymbolInfo(false, 144, 56, 20, 20, 4), new SymbolInfo(false, 174, 68, 22, 22, 4), new SymbolInfo(false, 204, 84, 24, 24, 4, 102, 42), new SymbolInfo(false, 280, 112, 14, 14, 16, 140, 56), new SymbolInfo(false, 368, 144, 16, 16, 16, 92, 36), new SymbolInfo(false, 456, Wbxml.EXT_0, 18, 18, 16, 114, 48), new SymbolInfo(false, 576, 224, 20, 20, 16, 144, 56), new SymbolInfo(false, 696, 272, 22, 22, 16, 174, 68), new SymbolInfo(false, 816, 336, 24, 24, 16, 136, 56), new SymbolInfo(false, 1050, 408, 18, 18, 36, 175, 68), new SymbolInfo(false, 1304, 496, 20, 20, 36, 163, 62), new DataMatrixSymbolInfo144()};
        PROD_SYMBOLS = symbolInfoArr;
        symbols = symbolInfoArr;
    }

    public SymbolInfo(final boolean z, final int i2, final int i3, final int i4, final int i5, final int i6) {
        this(z, i2, i3, i4, i5, i6, i2, i3);
    }

    SymbolInfo(final boolean z, final int i2, final int i3, final int i4, final int i5, final int i6, final int i7, final int i8) {
        rectangular = z;
        dataCapacity = i2;
        errorCodewords = i3;
        matrixWidth = i4;
        matrixHeight = i5;
        dataRegions = i6;
        rsBlockData = i7;
        rsBlockError = i8;
    }

    private int getHorizontalDataRegions() {
        final int i2 = dataRegions;
        int i3 = 1;
        if (1 != i2) {
            i3 = 2;
            if (!(2 == i2 || 4 == i2)) {
                if (16 == i2) {
                    return 4;
                }
                if (36 == i2) {
                    return 6;
                }
                throw new IllegalStateException("Cannot handle this number of data regions");
            }
        }
        return i3;
    }

    private int getVerticalDataRegions() {
        final int i2 = dataRegions;
        if (1 == i2 || 2 == i2) {
            return 1;
        }
        if (4 == i2) {
            return 2;
        }
        if (16 == i2) {
            return 4;
        }
        if (36 == i2) {
            return 6;
        }
        throw new IllegalStateException("Cannot handle this number of data regions");
    }

    public final int getSymbolDataWidth() {
        return this.getHorizontalDataRegions() * matrixWidth;
    }

    public final int getSymbolDataHeight() {
        return this.getVerticalDataRegions() * matrixHeight;
    }

    public final int getSymbolWidth() {
        return this.getSymbolDataWidth() + (this.getHorizontalDataRegions() << 1);
    }

    public final int getSymbolHeight() {
        return this.getSymbolDataHeight() + (this.getVerticalDataRegions() << 1);
    }

    public final String toString() {
        String sb = (rectangular ? "Rectangular Symbol:" : "Square Symbol:") +
                " data region " +
                matrixWidth +
                'x' +
                matrixHeight +
                ", symbol size " +
                this.getSymbolWidth() +
                'x' +
                this.getSymbolHeight() +
                ", symbol data size " +
                this.getSymbolDataWidth() +
                'x' +
                this.getSymbolDataHeight() +
                ", codewords " +
                dataCapacity +
                '+' +
                errorCodewords;
        return sb;
    }
}
