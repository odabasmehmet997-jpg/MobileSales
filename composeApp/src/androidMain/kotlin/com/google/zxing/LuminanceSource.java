package com.google.zxing;


public abstract class LuminanceSource {
    private final int height;
    private final int width;
    public abstract byte[] getMatrix();
    public abstract byte[] getRow(int i2, byte[] bArr);
    public boolean isRotateSupported() {
        return false;
    }
    protected LuminanceSource(final int i2, final int i3) {
        width = i2;
        height = i3;
    }
    public final int getWidth() {
        return width;
    }
    public final int getHeight() {
        return height;
    }
    public LuminanceSource crop(final int i2, final int i3, final int i4, final int i5) {
        throw new UnsupportedOperationException("This luminance source does not support cropping.");
    }
    public LuminanceSource invert() {
        return new InvertedLuminanceSource(this);
    }
    public LuminanceSource rotateCounterClockwise() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 90 degrees.");
    }

    public final String toString() {
        final int i2 = width;
        byte[] bArr = new byte[i2];
        final StringBuilder sb = new StringBuilder(height * (i2 + 1));
        for (int i3 = 0; i3 < height; i3++) {
            bArr = this.getRow(i3, bArr);
            for (int i4 = 0; i4 < width; i4++) {
                final byte b2 = (byte) (bArr[i4] & 255);
                sb.append(64 > b2 ? '#' : '+');
            }
            sb.append(10);
        }
        return sb.toString();
    }
}
