package com.google.zxing.oned.rss.expanded.decoders;

final class DecodedChar extends DecodedObject {
    private final char value;

    DecodedChar(final int i2, final char c2) {
        super(i2);
        value = c2;
    }

    
    public char getValue() {
        return value;
    }

    
    public boolean isFNC1() {
        return '' == this.value;
    }
}
