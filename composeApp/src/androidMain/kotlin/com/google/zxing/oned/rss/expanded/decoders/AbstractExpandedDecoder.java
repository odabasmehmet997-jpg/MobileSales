package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public abstract class AbstractExpandedDecoder {
    private final GeneralAppIdDecoder generalDecoder;
    private final BitArray information;

    public abstract String parseInformation() throws NotFoundException, FormatException;

    AbstractExpandedDecoder(final BitArray bitArray) {
        information = bitArray;
        generalDecoder = new GeneralAppIdDecoder(bitArray);
    }

    
    public final BitArray getInformation() {
        return information;
    }

    
    public final GeneralAppIdDecoder getGeneralDecoder() {
        return generalDecoder;
    }

    public static AbstractExpandedDecoder createDecoder(final BitArray bitArray) {
        if (bitArray.get(1)) {
            return new AI01AndOtherAIs(bitArray);
        }
        if (!bitArray.get(2)) {
            return new AnyAIDecoder(bitArray);
        }
        final int extractNumericValueFromBitArray = GeneralAppIdDecoder.extractNumericValueFromBitArray(bitArray, 1, 4);
        if (4 == extractNumericValueFromBitArray) {
            return new AI013103decoder(bitArray);
        }
        if (5 == extractNumericValueFromBitArray) {
            return new AI01320xDecoder(bitArray);
        }
        final int extractNumericValueFromBitArray2 = GeneralAppIdDecoder.extractNumericValueFromBitArray(bitArray, 1, 5);
        if (12 == extractNumericValueFromBitArray2) {
            return new AI01392xDecoder(bitArray);
        }
        if (13 == extractNumericValueFromBitArray2) {
            return new AI01393xDecoder(bitArray);
        }
        switch (GeneralAppIdDecoder.extractNumericValueFromBitArray(bitArray, 1, 7)) {
            case 56:
                return new AI013x0x1xDecoder(bitArray, "310", "11");
            case 57:
                return new AI013x0x1xDecoder(bitArray, "320", "11");
            case 58:
                return new AI013x0x1xDecoder(bitArray, "310", "13");
            case 59:
                return new AI013x0x1xDecoder(bitArray, "320", "13");
            case 60:
                return new AI013x0x1xDecoder(bitArray, "310", "15");
            case 61:
                return new AI013x0x1xDecoder(bitArray, "320", "15");
            case 62:
                return new AI013x0x1xDecoder(bitArray, "310", "17");
            case 63:
                return new AI013x0x1xDecoder(bitArray, "320", "17");
            default:
                throw new IllegalStateException("unknown decoder: " + bitArray);
        }
    }
}
