package com.google.zxing.oned.rss.expanded.decoders;

final class DecodedInformation extends DecodedObject {
    private final String newString;
    private final boolean remaining;
    private final int remainingValue;

    DecodedInformation(final int i2, final String str) {
        super(i2);
        newString = str;
        remaining = false;
        remainingValue = 0;
    }

    DecodedInformation(final int i2, final String str, final int i3) {
        super(i2);
        remaining = true;
        remainingValue = i3;
        newString = str;
    }

    
    public String getNewString() {
        return newString;
    }

    
    public boolean isRemaining() {
        return remaining;
    }

    
    public int getRemainingValue() {
        return remainingValue;
    }
}
