package com.google.zxing.integration.android;

import android.content.Intent;

public final class IntentResult {
    private final String barcodeImagePath;
    private final String contents;
    private final String errorCorrectionLevel;
    private final String formatName;
    private final Integer orientation;
    private final Intent originalIntent;
    private final byte[] rawBytes;

    IntentResult() {
        this(null, null, null, null, null, null, null);
    }

    IntentResult(final Intent intent) {
        this(null, null, null, null, null, null, intent);
    }

    IntentResult(final String str, final String str2, final byte[] bArr, final Integer num, final String str3, final String str4, final Intent intent) {
        contents = str;
        formatName = str2;
        rawBytes = bArr;
        orientation = num;
        errorCorrectionLevel = str3;
        barcodeImagePath = str4;
        originalIntent = intent;
    }

    public String getContents() {
        return contents;
    }

    public String toString() {
        final byte[] bArr = rawBytes;
        final int length = null == bArr ? 0 : bArr.length;
        return "Format: " + formatName + 10 + "Contents: " + contents + 10 + "Raw bytes: (" + length + " bytes)\nOrientation: " + orientation + 10 + "EC level: " + errorCorrectionLevel + 10 + "Barcode image: " + barcodeImagePath + 10 + "Original intent: " + originalIntent + 10;
    }
}
