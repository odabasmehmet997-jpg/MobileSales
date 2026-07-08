package com.google.zxing.common;

import java.util.List;

public final class DecoderResult {
    private final List<byte[]> byteSegments;
    private final String ecLevel;
    private Integer erasures;
    private Integer errorsCorrected;
    private int numBits;
    private Object other;
    private final byte[] rawBytes;
    private final int structuredAppendParity;
    private final int structuredAppendSequenceNumber;
    private final String text;

    public DecoderResult(final byte[] bArr, final String str, final List<byte[]> list, final String str2) {
        this(bArr, str, list, str2, -1, -1);
    }

    public DecoderResult(final byte[] bArr, final String str, final List<byte[]> list, final String str2, final int i2, final int i3) {
        final int i4;
        rawBytes = bArr;
        if (null == bArr) {
            i4 = 0;
        } else {
            i4 = bArr.length * 8;
        }
        numBits = i4;
        text = str;
        byteSegments = list;
        ecLevel = str2;
        structuredAppendParity = i3;
        structuredAppendSequenceNumber = i2;
    }

    public byte[] getRawBytes() {
        return rawBytes;
    }

    public int getNumBits() {
        return numBits;
    }

    public void setNumBits(final int i2) {
        numBits = i2;
    }

    public String getText() {
        return text;
    }

    public List<byte[]> getByteSegments() {
        return byteSegments;
    }

    public String getECLevel() {
        return ecLevel;
    }

    public void setErrorsCorrected(final Integer num) {
        errorsCorrected = num;
    }

    public void setErasures(final Integer num) {
        erasures = num;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(final Object obj) {
        other = obj;
    }

    public boolean hasStructuredAppend() {
        return 0 <= this.structuredAppendParity && 0 <= this.structuredAppendSequenceNumber;
    }

    public int getStructuredAppendParity() {
        return structuredAppendParity;
    }

    public int getStructuredAppendSequenceNumber() {
        return structuredAppendSequenceNumber;
    }
}
