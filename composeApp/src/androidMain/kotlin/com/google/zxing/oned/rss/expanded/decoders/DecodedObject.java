package com.google.zxing.oned.rss.expanded.decoders;

abstract class DecodedObject {
    private final int newPosition;

    DecodedObject(final int i2) {
        newPosition = i2;
    }

    
    public final int getNewPosition() {
        return newPosition;
    }
}
