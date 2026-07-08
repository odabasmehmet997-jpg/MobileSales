package com.google.zxing.qrcode.detector;

public final class FinderPatternInfo {
    private final FinderPattern bottomLeft;
    private final FinderPattern topLeft;
    private final FinderPattern topRight;

    public FinderPatternInfo(FinderPattern[] finderPatternArr) {
        if (finderPatternArr.length != 3) {
            throw new IllegalArgumentException("Array must contain exactly 3 finder patterns");
        }
        this.bottomLeft = finderPatternArr[0];
        this.topLeft = finderPatternArr[1];
        this.topRight = finderPatternArr[2];
    }
    public FinderPattern getBottomLeft() {
        return this.bottomLeft;
    }
    public FinderPattern getTopLeft() {
        return this.topLeft;
    }
    public FinderPattern getTopRight() {
        return this.topRight;
    }
    @Override
    public String toString() {
        return "FinderPatternInfo{" +
                "bottomLeft=" + bottomLeft +
                ", topLeft=" + topLeft +
                ", topRight=" + topRight +
                '}';
    }
}
