package com.google.zxing;

public final class Dimension {
    private final int height = 0;
    private final int width = 0;

    public boolean equals(final Object obj) {
        if (obj instanceof Dimension dimension) {
            return width == dimension.width && height == dimension.height;
        }
        return false;
    }
    public int hashCode() {
        return (width * 32713) + height;
    }
    public String toString() {
        return width + "x" + height;
    }
}
