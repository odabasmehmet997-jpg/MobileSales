package com.google.zxing;

public final class InvertedLuminanceSource extends LuminanceSource {
    private final LuminanceSource delegate;
    public InvertedLuminanceSource(final LuminanceSource luminanceSource) {
        super(luminanceSource.getWidth(), luminanceSource.getHeight());
        delegate = luminanceSource;
    }
    public byte[] getRow(final int i2, final byte[] bArr) {
        final byte[] row = delegate.getRow(i2, bArr);
        final int width = this.getWidth();
        for (int i3 = 0; i3 < width; i3++) {
            row[i3] = (byte) (255 - (row[i3] & 255));
        }
        return row;
    }
    public byte[] getMatrix() {
        final byte[] matrix = delegate.getMatrix();
        final int width = this.getWidth() * this.getHeight();
        final byte[] bArr = new byte[width];
        for (int i2 = 0; i2 < width; i2++) {
            bArr[i2] = (byte) (255 - (matrix[i2] & 255));
        }
        return bArr;
    }
    public LuminanceSource crop(final int i2, final int i3, final int i4, final int i5) {
        return new InvertedLuminanceSource(delegate.crop(i2, i3, i4, i5));
    }
    public boolean isRotateSupported() {
        return delegate.isRotateSupported();
    }
    public LuminanceSource invert() {
        return delegate;
    }
    public LuminanceSource rotateCounterClockwise() {
        return new InvertedLuminanceSource(delegate.rotateCounterClockwise());
    }
}
