package com.google.zxing;

public final class RGBLuminanceSource extends LuminanceSource {
    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final byte[] luminances;
    private final int top;
    private RGBLuminanceSource(final byte[] bArr, final int i2, final int i3, final int i4, final int i5, final int i6, final int i7) {
        super(i6, i7);
        if (i6 + i4 > i2 || i7 + i5 > i3) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        luminances = bArr;
        dataWidth = i2;
        dataHeight = i3;
        left = i4;
        top = i5;
    }
    public byte[] getRow(final int i2, byte[] bArr) {
        if (0 > i2 || i2 >= this.getHeight()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + i2);
        }
        final int width = this.getWidth();
        if (null == bArr || bArr.length < width) {
            bArr = new byte[width];
        }
        System.arraycopy(luminances, ((i2 + top) * dataWidth) + left, bArr, 0, width);
        return bArr;
    }
    public byte[] getMatrix() {
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int i2 = dataWidth;
        if (width == i2 && height == dataHeight) {
            return luminances;
        }
        final int i3 = width * height;
        final byte[] bArr = new byte[i3];
        int i4 = (top * i2) + left;
        if (width == i2) {
            System.arraycopy(luminances, i4, bArr, 0, i3);
            return bArr;
        }
        for (int i5 = 0; i5 < height; i5++) {
            System.arraycopy(luminances, i4, bArr, i5 * width, width);
            i4 += dataWidth;
        }
        return bArr;
    }
    public LuminanceSource crop(final int i2, final int i3, final int i4, final int i5) {
        return new RGBLuminanceSource(luminances, dataWidth, dataHeight, left + i2, top + i3, i4, i5);
    }
}
