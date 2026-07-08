package com.google.zxing.pdf417;

public final class PDF417ResultMetadata {
    private String fileId;
    private boolean lastSegment;
    private int[] optionalData;
    private int segmentIndex;

    public void setSegmentIndex(final int i2) {
        segmentIndex = i2;
    }

    public void setFileId(final String str) {
        fileId = str;
    }

    public void setOptionalData(final int[] iArr) {
        optionalData = iArr;
    }

    public void setLastSegment(final boolean z) {
        lastSegment = z;
    }
}
