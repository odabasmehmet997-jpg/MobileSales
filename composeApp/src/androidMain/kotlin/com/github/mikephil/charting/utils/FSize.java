package com.github.mikephil.charting.utils;

public final class FSize {

    final float width;
    final float height;

    public FSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FSize fSize)) {
            return false;
        }
        return width == fSize.width && height == fSize.height;
    }


    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        float f = width;
        stringBuilder.append(f);
        stringBuilder.append("x");
        f = height;
        stringBuilder.append(f);
        return stringBuilder.toString();
    }
    
    public int hashCode() {
        return Float.floatToIntBits(width) ^ Float.floatToIntBits(height);
    }

    public float width() {
        return 0;
    }
}
