package com.google.zxing;

import java.util.EnumMap;
import java.util.Map;

public final class Result {
    private final BarcodeFormat format;
    private final int numBits;
    private final byte[] rawBytes;
    private Map<ResultMetadataType, Object> resultMetadata;
    private ResultPoint[] resultPoints;
    private final String text;
    private final long timestamp;
    public Result(final String str, final byte[] bArr, final ResultPoint[] resultPointArr, final BarcodeFormat barcodeFormat) {
        this(str, bArr, resultPointArr, barcodeFormat, System.currentTimeMillis());
    }
    public Result(final String str, final byte[] bArr, final ResultPoint[] resultPointArr, final BarcodeFormat barcodeFormat, final long j2) {
        this(str, bArr, null == bArr ? 0 : bArr.length * 8, resultPointArr, barcodeFormat, j2);
    }
    public Result(final String str, final byte[] bArr, final int i2, final ResultPoint[] resultPointArr, final BarcodeFormat barcodeFormat, final long j2) {
        text = str;
        rawBytes = bArr;
        numBits = i2;
        resultPoints = resultPointArr;
        format = barcodeFormat;
        resultMetadata = null;
        timestamp = j2;
    }
    public String getText() {
        return text;
    }
    public byte[] getRawBytes() {
        return rawBytes;
    }
    public ResultPoint[] getResultPoints() {
        return resultPoints;
    }
    public BarcodeFormat getBarcodeFormat() {
        return format;
    }
    public Map<ResultMetadataType, Object> getResultMetadata() {
        return resultMetadata;
    }
    public void putMetadata(final ResultMetadataType resultMetadataType, final Object obj) {
        if (null == this.resultMetadata) {
            resultMetadata = new EnumMap(ResultMetadataType.class);
        }
        resultMetadata.put(resultMetadataType, obj);
    }
    public void putAllMetadata(final Map<ResultMetadataType, Object> map) {
        if (null != map) {
            final Map<ResultMetadataType, Object> map2 = resultMetadata;
            if (null == map2) {
                resultMetadata = map;
            } else {
                map2.putAll(map);
            }
        }
    }
    public void addResultPoints(final ResultPoint[] resultPointArr) {
        final ResultPoint[] resultPointArr2 = resultPoints;
        if (null == resultPointArr2) {
            resultPoints = resultPointArr;
        } else if (null != resultPointArr && 0 < resultPointArr.length) {
            final ResultPoint[] resultPointArr3 = new ResultPoint[(resultPointArr2.length + resultPointArr.length)];
            System.arraycopy(resultPointArr2, 0, resultPointArr3, 0, resultPointArr2.length);
            System.arraycopy(resultPointArr, 0, resultPointArr3, resultPointArr2.length, resultPointArr.length);
            resultPoints = resultPointArr3;
        }
    }
    public String toString() {
        return text;
    }
}
