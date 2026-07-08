package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;

final class DecodedNumeric extends DecodedObject {
    private final int firstDigit;
    private final int secondDigit;

    DecodedNumeric(final int i2, final int i3, final int i4) throws FormatException {
        super(i2);
        if (0 > i3 || 10 < i3 || 0 > i4 || 10 < i4) {
            throw FormatException.getFormatInstance();
        }
        firstDigit = i3;
        secondDigit = i4;
    }

    
    public int getFirstDigit() {
        return firstDigit;
    }

    
    public int getSecondDigit() {
        return secondDigit;
    }

    
    public boolean isFirstDigitFNC1() {
        return 10 == this.firstDigit;
    }

    
    public boolean isSecondDigitFNC1() {
        return 10 == this.secondDigit;
    }
}
