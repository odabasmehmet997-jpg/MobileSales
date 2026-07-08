package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;

final class UPCEANExtensionSupport {
    private static final int[] EXTENSION_START_PATTERN = {1, 1, 2};
    private final UPCEANExtension5Support fiveSupport = new UPCEANExtension5Support();
    private final UPCEANExtension2Support twoSupport = new UPCEANExtension2Support();

    UPCEANExtensionSupport() {
    }

    
    public Result decodeRow(final int i2, final BitArray bitArray, final int i3) throws NotFoundException {
        final int[] findGuardPattern = UPCEANReader.findGuardPattern(bitArray, i3, false, UPCEANExtensionSupport.EXTENSION_START_PATTERN);
        try {
            return fiveSupport.decodeRow(i2, bitArray, findGuardPattern);
        } catch (final ReaderException unused) {
            return twoSupport.decodeRow(i2, bitArray, findGuardPattern);
        }
    }
}
