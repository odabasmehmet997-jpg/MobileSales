package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

final class BitMatrixParser {
    private final BitMatrix mappingBitMatrix;
    private final BitMatrix readMappingMatrix;
    private final Version version;

    BitMatrixParser(final BitMatrix bitMatrix) throws FormatException {
        final int height = bitMatrix.getHeight();
        if (8 > height || 144 < height || 0 != (height & 1)) {
            throw FormatException.getFormatInstance();
        }
        version = BitMatrixParser.readVersion(bitMatrix);
        final BitMatrix extractDataRegion = this.extractDataRegion(bitMatrix);
        mappingBitMatrix = extractDataRegion;
        readMappingMatrix = new BitMatrix(extractDataRegion.getWidth(), extractDataRegion.getHeight());
    }

    
    public Version getVersion() {
        return version;
    }

    private static Version readVersion(final BitMatrix bitMatrix) throws FormatException {
        return Version.getVersionForDimensions(bitMatrix.getHeight(), bitMatrix.getWidth());
    }

    
    public byte[] readCodewords() throws FormatException {
        final byte[] bArr = new byte[version.getTotalCodewords()];
        final int height = mappingBitMatrix.getHeight();
        final int width = mappingBitMatrix.getWidth();
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        int i4 = 4;
        while (true) {
            if (i4 == height && 0 == i2 && !z) {
                bArr[i3] = (byte) this.readCorner1(height, width);
                i4 -= 2;
                i2 += 2;
                i3++;
                z = true;
            } else {
                final int i5 = height - 2;
                if (i4 == i5 && 0 == i2 && 0 != (width & 3) && !z2) {
                    bArr[i3] = (byte) this.readCorner2(height, width);
                    i4 -= 2;
                    i2 += 2;
                    i3++;
                    z2 = true;
                } else if (i4 == height + 4 && 2 == i2 && 0 == (width & 7) && !z3) {
                    bArr[i3] = (byte) this.readCorner3(height, width);
                    i4 -= 2;
                    i2 += 2;
                    i3++;
                    z3 = true;
                } else if (i4 == i5 && 0 == i2 && 4 == (width & 7) && !z4) {
                    bArr[i3] = (byte) this.readCorner4(height, width);
                    i4 -= 2;
                    i2 += 2;
                    i3++;
                    z4 = true;
                } else {
                    while (true) {
                        if (i4 < height && 0 <= i2 && !readMappingMatrix.get(i2, i4)) {
                            bArr[i3] = (byte) this.readUtah(i4, i2, height, width);
                            i3++;
                        }
                        final int i6 = i4 - 2;
                        final int i7 = i2 + 2;
                        if (0 > i6 || i7 >= width) {
                            final int i8 = i4 - 1;
                            final int i9 = i2 + 5;
                        } else {
                            i4 = i6;
                            i2 = i7;
                        }
                    }
                    int i82 = i4 - 1;
                    int i92 = i2 + 5;
                    while (true) {
                        if (0 <= i82 && i92 < width && !readMappingMatrix.get(i92, i82)) {
                            bArr[i3] = (byte) this.readUtah(i82, i92, height, width);
                            i3++;
                        }
                        final int i10 = i82 + 2;
                        final int i11 = i92 - 2;
                        if (i10 >= height || 0 > i11) {
                            i4 = i82 + 5;
                            i2 = i92 - 1;
                        } else {
                            i82 = i10;
                            i92 = i11;
                        }
                    }
                    i4 = i82 + 5;
                    i2 = i92 - 1;
                }
            }
            if (i4 >= height && i2 >= width) {
                break;
            }
        }
        if (i3 == version.getTotalCodewords()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }

    private boolean readModule(int i2, int i3, final int i4, final int i5) {
        if (0 > i2) {
            i2 += i4;
            i3 += 4 - ((i4 + 4) & 7);
        }
        if (0 > i3) {
            i3 += i5;
            i2 += 4 - ((i5 + 4) & 7);
        }
        readMappingMatrix.set(i3, i2);
        return mappingBitMatrix.get(i3, i2);
    }

    private int readUtah(final int i2, final int i3, final int i4, final int i5) {
        final int i6 = i2 - 2;
        final int i7 = i3 - 2;
        int i8 = (this.readModule(i6, i7, i4, i5) ? 1 : 0) << true;
        final int i9 = i3 - 1;
        if (this.readModule(i6, i9, i4, i5)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        final int i11 = i2 - 1;
        if (this.readModule(i11, i7, i4, i5)) {
            i10 |= 1;
        }
        int i12 = i10 << 1;
        if (this.readModule(i11, i9, i4, i5)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        if (this.readModule(i11, i3, i4, i5)) {
            i13 |= 1;
        }
        int i14 = i13 << 1;
        if (this.readModule(i2, i7, i4, i5)) {
            i14 |= 1;
        }
        int i15 = i14 << 1;
        if (this.readModule(i2, i9, i4, i5)) {
            i15 |= 1;
        }
        final int i16 = i15 << 1;
        return this.readModule(i2, i3, i4, i5) ? i16 | 1 : i16;
    }

    private int readCorner1(final int i2, final int i3) {
        final int i4 = i2 - 1;
        int i5 = (this.readModule(i4, 0, i2, i3) ? 1 : 0) << true;
        if (this.readModule(i4, 1, i2, i3)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (this.readModule(i4, 2, i2, i3)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        if (this.readModule(0, i3 - 2, i2, i3)) {
            i7 |= 1;
        }
        int i8 = i7 << 1;
        final int i9 = i3 - 1;
        if (this.readModule(0, i9, i2, i3)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        if (this.readModule(1, i9, i2, i3)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        if (this.readModule(2, i9, i2, i3)) {
            i11 |= 1;
        }
        final int i12 = i11 << 1;
        return this.readModule(3, i9, i2, i3) ? i12 | 1 : i12;
    }

    private int readCorner2(final int i2, final int i3) {
        int i4 = (this.readModule(i2 + -3, 0, i2, i3) ? 1 : 0) << true;
        if (this.readModule(i2 - 2, 0, i2, i3)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (this.readModule(i2 - 1, 0, i2, i3)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (this.readModule(0, i3 - 4, i2, i3)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        if (this.readModule(0, i3 - 3, i2, i3)) {
            i7 |= 1;
        }
        int i8 = i7 << 1;
        if (this.readModule(0, i3 - 2, i2, i3)) {
            i8 |= 1;
        }
        int i9 = i8 << 1;
        final int i10 = i3 - 1;
        if (this.readModule(0, i10, i2, i3)) {
            i9 |= 1;
        }
        final int i11 = i9 << 1;
        return this.readModule(1, i10, i2, i3) ? i11 | 1 : i11;
    }

    private int readCorner3(final int i2, final int i3) {
        final int i4 = i2 - 1;
        int i5 = (this.readModule(i4, 0, i2, i3) ? 1 : 0) << true;
        final int i6 = i3 - 1;
        if (this.readModule(i4, i6, i2, i3)) {
            i5 |= 1;
        }
        int i7 = i5 << 1;
        final int i8 = i3 - 3;
        if (this.readModule(0, i8, i2, i3)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        final int i10 = i3 - 2;
        if (this.readModule(0, i10, i2, i3)) {
            i9 |= 1;
        }
        int i11 = i9 << 1;
        if (this.readModule(0, i6, i2, i3)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        if (this.readModule(1, i8, i2, i3)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        if (this.readModule(1, i10, i2, i3)) {
            i13 |= 1;
        }
        final int i14 = i13 << 1;
        return this.readModule(1, i6, i2, i3) ? i14 | 1 : i14;
    }

    private int readCorner4(final int i2, final int i3) {
        int i4 = (this.readModule(i2 + -3, 0, i2, i3) ? 1 : 0) << true;
        if (this.readModule(i2 - 2, 0, i2, i3)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (this.readModule(i2 - 1, 0, i2, i3)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (this.readModule(0, i3 - 2, i2, i3)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        final int i8 = i3 - 1;
        if (this.readModule(0, i8, i2, i3)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        if (this.readModule(1, i8, i2, i3)) {
            i9 |= 1;
        }
        int i10 = i9 << 1;
        if (this.readModule(2, i8, i2, i3)) {
            i10 |= 1;
        }
        final int i11 = i10 << 1;
        return this.readModule(3, i8, i2, i3) ? i11 | 1 : i11;
    }

    private BitMatrix extractDataRegion(final BitMatrix bitMatrix) {
        final int symbolSizeRows = version.getSymbolSizeRows();
        final int symbolSizeColumns = version.getSymbolSizeColumns();
        if (bitMatrix.getHeight() == symbolSizeRows) {
            final int dataRegionSizeRows = version.getDataRegionSizeRows();
            final int dataRegionSizeColumns = version.getDataRegionSizeColumns();
            final int i2 = symbolSizeRows / dataRegionSizeRows;
            final int i3 = symbolSizeColumns / dataRegionSizeColumns;
            final BitMatrix bitMatrix2 = new BitMatrix(i3 * dataRegionSizeColumns, i2 * dataRegionSizeRows);
            for (int i4 = 0; i4 < i2; i4++) {
                final int i5 = i4 * dataRegionSizeRows;
                for (int i6 = 0; i6 < i3; i6++) {
                    final int i7 = i6 * dataRegionSizeColumns;
                    for (int i8 = 0; i8 < dataRegionSizeRows; i8++) {
                        final int i9 = ((dataRegionSizeRows + 2) * i4) + 1 + i8;
                        final int i10 = i5 + i8;
                        for (int i11 = 0; i11 < dataRegionSizeColumns; i11++) {
                            if (bitMatrix.get(((dataRegionSizeColumns + 2) * i6) + 1 + i11, i9)) {
                                bitMatrix2.set(i7 + i11, i10);
                            }
                        }
                        final BitMatrix bitMatrix3 = bitMatrix;
                    }
                    final BitMatrix bitMatrix4 = bitMatrix;
                }
                final BitMatrix bitMatrix5 = bitMatrix;
            }
            return bitMatrix2;
        }
        throw new IllegalArgumentException("Dimension of bitMarix must match the version size");
    }
}
