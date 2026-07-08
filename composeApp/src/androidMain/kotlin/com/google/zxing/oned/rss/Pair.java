package com.google.zxing.oned.rss;

final class Pair extends DataCharacter {
    private int count;
    private final FinderPattern finderPattern;

    Pair(final int i2, final int i3, final FinderPattern finderPattern2) {
        super(i2, i3);
        finderPattern = finderPattern2;
    }

    
    public FinderPattern getFinderPattern() {
        return finderPattern;
    }

    
    public int getCount() {
        return count;
    }

    
    public void incrementCount() {
        count++;
    }
}
