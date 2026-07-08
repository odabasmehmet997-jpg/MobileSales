package com.google.zxing.oned.rss;

public class DataCharacter {
    private final int checksumPortion;
    private final int value;

    public DataCharacter(final int i2, final int i3) {
        value = i2;
        checksumPortion = i3;
    }

    public final int getValue() {
        return value;
    }

    public final int getChecksumPortion() {
        return checksumPortion;
    }

    public final String toString() {
        return value + "(" + checksumPortion + ')';
    }

    public final boolean equals(final Object obj) {
        if (!(obj instanceof DataCharacter dataCharacter)) {
            return false;
        }
        return value == dataCharacter.value && checksumPortion == dataCharacter.checksumPortion;
    }

    public final int hashCode() {
        return value ^ checksumPortion;
    }
}
