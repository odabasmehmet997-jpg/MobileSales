package com.google.zxing.qrcode.encoder;

import java.util.Arrays;

record BlockPair(byte[] dataBytes, byte[] errorCorrectionBytes) {
    BlockPair {
        if (dataBytes == null || errorCorrectionBytes == null) {
            throw new IllegalArgumentException("Data and error correction bytes cannot be null");
        }
    }
    public String toString() {
        return "BlockPair{" +
                "dataBytes=" + Arrays.toString(dataBytes) +
                ", errorCorrectionBytes=" + Arrays.toString(errorCorrectionBytes) +
                '}';
    }
}
