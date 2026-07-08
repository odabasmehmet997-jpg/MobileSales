package com.google.zxing.common;

import com.proje.mobilesales.core.sql.SqlLiteVariable;
import java.util.Arrays;

public final class BitMatrix implements Cloneable {
    private final int[] bits;
    private final int height;
    private final int rowSize;
    private final int width;

    public BitMatrix(final int i2) {
        this(i2, i2);
    }

    public BitMatrix(final int i2, final int i3) {
        if (0 >= i2 || 0 >= i3) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        width = i2;
        height = i3;
        final int i4 = (i2 + 31) / 32;
        rowSize = i4;
        bits = new int[(i4 * i3)];
    }

    private BitMatrix(final int i2, final int i3, final int i4, final int[] iArr) {
        width = i2;
        height = i3;
        rowSize = i4;
        bits = iArr;
    }

    public boolean get(final int i2, final int i3) {
        return 0 != ((this.bits[(i3 * this.rowSize) + (i2 / 32)] >>> (i2 & 31)) & 1);
    }

    public void set(final int i2, final int i3) {
        final int i4 = (i3 * rowSize) + (i2 / 32);
        final int[] iArr = bits;
        iArr[i4] = (1 << (i2 & 31)) | iArr[i4];
    }

    public void flip(final int i2, final int i3) {
        final int i4 = (i3 * rowSize) + (i2 / 32);
        final int[] iArr = bits;
        iArr[i4] = (1 << (i2 & 31)) ^ iArr[i4];
    }

    public void setRegion(final int i2, int i3, final int i4, final int i5) {
        if (0 > i3 || 0 > i2) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        } else if (0 >= i5 || 0 >= i4) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        } else {
            final int i6 = i4 + i2;
            final int i7 = i5 + i3;
            if (i7 > height || i6 > width) {
                throw new IllegalArgumentException("The region must fit inside the matrix");
            }
            while (i3 < i7) {
                final int i8 = rowSize * i3;
                for (int i9 = i2; i9 < i6; i9++) {
                    final int[] iArr = bits;
                    final int i10 = (i9 / 32) + i8;
                    iArr[i10] = iArr[i10] | (1 << (i9 & 31));
                }
                i3++;
            }
        }
    }

    public BitArray getRow(final int i2, BitArray bitArray) {
        if (null == bitArray || bitArray.getSize() < width) {
            bitArray = new BitArray(width);
        } else {
            bitArray.clear();
        }
        final int i3 = i2 * rowSize;
        for (int i4 = 0; i4 < rowSize; i4++) {
            bitArray.setBulk(i4 << 5, bits[i3 + i4]);
        }
        return bitArray;
    }

    public void setRow(final int i2, final BitArray bitArray) {
        final int[] bitArray2 = bitArray.getBitArray();
        final int[] iArr = bits;
        final int i3 = rowSize;
        System.arraycopy(bitArray2, 0, iArr, i2 * i3, i3);
    }

    public void rotate180() {
        final int width2 = this.width;
        final int height2 = this.height;
        BitArray bitArray = new BitArray(width2);
        BitArray bitArray2 = new BitArray(width2);
        for (int i2 = 0; i2 < (height2 + 1) / 2; i2++) {
            bitArray = this.getRow(i2, bitArray);
            final int i3 = (height2 - 1) - i2;
            bitArray2 = this.getRow(i3, bitArray2);
            bitArray.reverse();
            bitArray2.reverse();
            this.setRow(i2, bitArray2);
            this.setRow(i3, bitArray);
        }
    }

    public int[] getEnclosingRectangle() {
        int i2 = width;
        int i3 = height;
        int i4 = -1;
        int i5 = -1;
        for (int i6 = 0; i6 < height; i6++) {
            int i7 = 0;
            while (true) {
                final int i8 = rowSize;
                if (i7 >= i8) {
                    break;
                }
                final int i9 = bits[(i8 * i6) + i7];
                if (0 != i9) {
                    if (i6 < i3) {
                        i3 = i6;
                    }
                    if (i6 > i5) {
                        i5 = i6;
                    }
                    final int i10 = i7 << 5;
                    if (i10 < i2) {
                        int i11 = 0;
                        while (0 == (i9 << (31 - i11))) {
                            i11++;
                        }
                        final int i12 = i11 + i10;
                        if (i12 < i2) {
                            i2 = i12;
                        }
                    }
                    if (i10 + 31 > i4) {
                        int i13 = 31;
                        while (0 == (i9 >>> i13)) {
                            i13--;
                        }
                        final int i14 = i10 + i13;
                        if (i14 > i4) {
                            i4 = i14;
                        }
                    }
                }
                i7++;
            }
        }
        if (i4 < i2 || i5 < i3) {
            return null;
        }
        return new int[]{i2, i3, (i4 - i2) + 1, (i5 - i3) + 1};
    }

    public int[] getTopLeftOnBit() {
        int[] iArr;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            iArr = bits;
            if (i3 < iArr.length && 0 == iArr[i3]) {
                i3++;
            }
        }
        if (i3 == iArr.length) {
            return null;
        }
        final int i4 = rowSize;
        final int i5 = i3 / i4;
        final int i6 = (i3 % i4) << 5;
        while (0 == (iArr[i3] << (31 - i2))) {
            i2++;
        }
        return new int[]{i6 + i2, i5};
    }

    public int[] getBottomRightOnBit() {
        int length = bits.length - 1;
        while (0 <= length && 0 == this.bits[length]) {
            length--;
        }
        if (0 > length) {
            return null;
        }
        final int i2 = rowSize;
        final int i3 = length / i2;
        final int i4 = (length % i2) << 5;
        int i5 = 31;
        while (0 == (this.bits[length] >>> i5)) {
            i5--;
        }
        return new int[]{i4 + i5, i3};
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean equals(final Object obj) {
        if (!(obj instanceof BitMatrix bitMatrix)) {
            return false;
        }
        return width == bitMatrix.width && height == bitMatrix.height && rowSize == bitMatrix.rowSize && Arrays.equals(bits, bitMatrix.bits);
    }

    public int hashCode() {
        final int i2 = width;
        return (((((((i2 * 31) + i2) * 31) + height) * 31) + rowSize) * 31) + Arrays.hashCode(bits);
    }

    public String toString() {
        return this.toString("X ", "  ");
    }

    public String toString(final String str, final String str2) {
        return this.buildToString(str, str2, SqlLiteVariable._NEW_LINE);
    }

    private String buildToString(final String str, final String str2, final String str3) {
        final StringBuilder sb = new StringBuilder(height * (width + 1));
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                sb.append(this.get(i3, i2) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }

    public BitMatrix clone() {
        return new BitMatrix(width, height, rowSize, bits.clone());
    }
}
