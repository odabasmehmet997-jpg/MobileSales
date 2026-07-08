package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;

import java.util.Map;

public final class UPCAReader extends UPCEANReader {
    private final UPCEANReader ean13Reader = new EAN13Reader();

    public Result decodeRow(final int i2, final BitArray bitArray, final int[] iArr, final Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        return UPCAReader.maybeReturnResult(ean13Reader.decodeRow(i2, bitArray, iArr, map));
    }

    public Result decodeRow(final int i2, final BitArray bitArray, final Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        return UPCAReader.maybeReturnResult(ean13Reader.decodeRow(i2, bitArray, map));
    }

    public Result decode(final BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return UPCAReader.maybeReturnResult(ean13Reader.decode(binaryBitmap));
    }

    public Result decode(final BinaryBitmap binaryBitmap, final Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        return UPCAReader.maybeReturnResult(ean13Reader.decode(binaryBitmap, map));
    }

    
    public BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.UPC_A;
    }

    
    public int decodeMiddle(final BitArray bitArray, final int[] iArr, final StringBuilder sb) throws NotFoundException {
        return ean13Reader.decodeMiddle(bitArray, iArr, sb);
    }

    private static Result maybeReturnResult(final Result result) throws FormatException {
        final String text = result.getText();
        if ('0' == text.charAt(0)) {
            return new Result(text.substring(1), null, result.getResultPoints(), BarcodeFormat.UPC_A);
        }
        throw FormatException.getFormatInstance();
    }
}
