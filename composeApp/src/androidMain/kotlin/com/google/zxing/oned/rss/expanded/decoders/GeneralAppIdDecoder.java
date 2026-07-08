package com.google.zxing.oned.rss.expanded.decoders;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.fasterxml.jackson.core.JsonFactory;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class GeneralAppIdDecoder {
    private final StringBuilder buffer = new StringBuilder();
    private final CurrentParsingState current = new CurrentParsingState();
    private final BitArray information;

    GeneralAppIdDecoder(final BitArray bitArray) {
        information = bitArray;
    }

    
    public String decodeAllCodes(final StringBuilder sb, int i2) throws NotFoundException, FormatException {
        String str = null;
        while (true) {
            final DecodedInformation decodeGeneralPurposeField = this.decodeGeneralPurposeField(i2, str);
            final String parseFieldsInGeneralPurpose = FieldParser.parseFieldsInGeneralPurpose(decodeGeneralPurposeField.getNewString());
            if (null != parseFieldsInGeneralPurpose) {
                sb.append(parseFieldsInGeneralPurpose);
            }
            final String valueOf = decodeGeneralPurposeField.isRemaining() ? String.valueOf(decodeGeneralPurposeField.getRemainingValue()) : null;
            if (i2 == decodeGeneralPurposeField.getNewPosition()) {
                return sb.toString();
            }
            i2 = decodeGeneralPurposeField.getNewPosition();
            str = valueOf;
        }
    }

    private boolean isStillNumeric(final int i2) {
        if (i2 + 7 <= information.getSize()) {
            int i3 = i2;
            while (true) {
                final int i4 = i2 + 3;
                if (i3 >= i4) {
                    return information.get(i4);
                }
                if (information.get(i3)) {
                    return true;
                }
                i3++;
            }
        } else return i2 + 4 <= information.getSize();
    }

    private DecodedNumeric decodeNumeric(final int i2) throws FormatException {
        final int i3 = i2 + 7;
        if (i3 > information.getSize()) {
            final int extractNumericValueFromBitArray = this.extractNumericValueFromBitArray(i2, 4);
            if (0 == extractNumericValueFromBitArray) {
                return new DecodedNumeric(information.getSize(), 10, 10);
            }
            return new DecodedNumeric(information.getSize(), extractNumericValueFromBitArray - 1, 10);
        }
        final int extractNumericValueFromBitArray2 = this.extractNumericValueFromBitArray(i2, 7) - 8;
        return new DecodedNumeric(i3, extractNumericValueFromBitArray2 / 11, extractNumericValueFromBitArray2 % 11);
    }

    
    public int extractNumericValueFromBitArray(final int i2, final int i3) {
        return GeneralAppIdDecoder.extractNumericValueFromBitArray(information, i2, i3);
    }

    static int extractNumericValueFromBitArray(final BitArray bitArray, final int i2, final int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            if (bitArray.get(i2 + i5)) {
                i4 |= 1 << ((i3 - i5) - 1);
            }
        }
        return i4;
    }

    
    public DecodedInformation decodeGeneralPurposeField(final int i2, final String str) throws FormatException {
        buffer.setLength(0);
        if (null != str) {
            buffer.append(str);
        }
        current.setPosition(i2);
        final DecodedInformation parseBlocks = this.parseBlocks();
        if (null == parseBlocks || !parseBlocks.isRemaining()) {
            return new DecodedInformation(current.getPosition(), buffer.toString());
        }
        return new DecodedInformation(current.getPosition(), buffer.toString(), parseBlocks.getRemainingValue());
    }

    private DecodedInformation parseBlocks() throws FormatException {
        final boolean z;
        final BlockParsedResult blockParsedResult;
        do {
            final int position = current.getPosition();
            if (current.isAlpha()) {
                blockParsedResult = this.parseAlphaBlock();
                z = blockParsedResult.finished();
            } else if (current.isIsoIec646()) {
                blockParsedResult = this.parseIsoIec646Block();
                z = blockParsedResult.finished();
            } else {
                blockParsedResult = this.parseNumericBlock();
                z = blockParsedResult.finished();
            }
            if (position != current.getPosition() || z) {
                break;
                break;
            }
            break;
        } while (!z);
        return blockParsedResult.decodedInformation();
    }

    private BlockParsedResult parseNumericBlock() throws FormatException {
        final DecodedInformation decodedInformation;
        while (this.isStillNumeric(current.getPosition())) {
            final DecodedNumeric decodeNumeric = this.decodeNumeric(current.getPosition());
            current.setPosition(decodeNumeric.getNewPosition());
            if (decodeNumeric.isFirstDigitFNC1()) {
                if (decodeNumeric.isSecondDigitFNC1()) {
                    decodedInformation = new DecodedInformation(current.getPosition(), buffer.toString());
                } else {
                    decodedInformation = new DecodedInformation(current.getPosition(), buffer.toString(), decodeNumeric.getSecondDigit());
                }
                return new BlockParsedResult(decodedInformation, true);
            }
            buffer.append(decodeNumeric.getFirstDigit());
            if (decodeNumeric.isSecondDigitFNC1()) {
                return new BlockParsedResult(new DecodedInformation(current.getPosition(), buffer.toString()), true);
            }
            buffer.append(decodeNumeric.getSecondDigit());
        }
        if (this.isNumericToAlphaNumericLatch(current.getPosition())) {
            current.setAlpha();
            current.incrementPosition(4);
        }
        return new BlockParsedResult(false);
    }

    private BlockParsedResult parseIsoIec646Block() throws FormatException {
        while (this.isStillIsoIec646(current.getPosition())) {
            final DecodedChar decodeIsoIec646 = this.decodeIsoIec646(current.getPosition());
            current.setPosition(decodeIsoIec646.getNewPosition());
            if (decodeIsoIec646.isFNC1()) {
                return new BlockParsedResult(new DecodedInformation(current.getPosition(), buffer.toString()), true);
            }
            buffer.append(decodeIsoIec646.getValue());
        }
        if (this.isAlphaOr646ToNumericLatch(current.getPosition())) {
            current.incrementPosition(3);
            current.setNumeric();
        } else if (this.isAlphaTo646ToAlphaLatch(current.getPosition())) {
            if (current.getPosition() + 5 < information.getSize()) {
                current.incrementPosition(5);
            } else {
                current.setPosition(information.getSize());
            }
            current.setAlpha();
        }
        return new BlockParsedResult(false);
    }

    private BlockParsedResult parseAlphaBlock() {
        while (this.isStillAlpha(current.getPosition())) {
            final DecodedChar decodeAlphanumeric = this.decodeAlphanumeric(current.getPosition());
            current.setPosition(decodeAlphanumeric.getNewPosition());
            if (decodeAlphanumeric.isFNC1()) {
                return new BlockParsedResult(new DecodedInformation(current.getPosition(), buffer.toString()), true);
            }
            buffer.append(decodeAlphanumeric.getValue());
        }
        if (this.isAlphaOr646ToNumericLatch(current.getPosition())) {
            current.incrementPosition(3);
            current.setNumeric();
        } else if (this.isAlphaTo646ToAlphaLatch(current.getPosition())) {
            if (current.getPosition() + 5 < information.getSize()) {
                current.incrementPosition(5);
            } else {
                current.setPosition(information.getSize());
            }
            current.setIsoIec646();
        }
        return new BlockParsedResult(false);
    }

    private boolean isStillIsoIec646(final int i2) {
        final int extractNumericValueFromBitArray;
        if (i2 + 5 > information.getSize()) {
            return false;
        }
        final int extractNumericValueFromBitArray2 = this.extractNumericValueFromBitArray(i2, 5);
        if (5 <= extractNumericValueFromBitArray2 && 16 > extractNumericValueFromBitArray2) {
            return true;
        }
        if (i2 + 7 > information.getSize()) {
            return false;
        }
        final int extractNumericValueFromBitArray3 = this.extractNumericValueFromBitArray(i2, 7);
        if (64 <= extractNumericValueFromBitArray3 && 116 > extractNumericValueFromBitArray3) {
            return true;
        }
        return i2 + 8 <= information.getSize() && 232 <= (extractNumericValueFromBitArray = extractNumericValueFromBitArray(i2, 8)) && 253 > extractNumericValueFromBitArray;
    }

    private DecodedChar decodeIsoIec646(final int i2) throws FormatException {
        final int extractNumericValueFromBitArray = this.extractNumericValueFromBitArray(i2, 5);
        if (15 == extractNumericValueFromBitArray) {
            return new DecodedChar(i2 + 5, '');
        }
        char c2 = '+';
        if (5 <= extractNumericValueFromBitArray && 15 > extractNumericValueFromBitArray) {
            return new DecodedChar(i2 + 5, (char) (extractNumericValueFromBitArray + 43));
        }
        final int extractNumericValueFromBitArray2 = this.extractNumericValueFromBitArray(i2, 7);
        if (64 <= extractNumericValueFromBitArray2 && 90 > extractNumericValueFromBitArray2) {
            return new DecodedChar(i2 + 7, (char) (extractNumericValueFromBitArray2 + 1));
        }
        if (90 <= extractNumericValueFromBitArray2 && 116 > extractNumericValueFromBitArray2) {
            return new DecodedChar(i2 + 7, (char) (extractNumericValueFromBitArray2 + 7));
        }
        switch (this.extractNumericValueFromBitArray(i2, 8)) {
            case 232:
                c2 = '!';
                break;
            case 233:
                c2 = JsonFactory.DEFAULT_QUOTE_CHAR;
                break;
            case 234:
                c2 = '%';
                break;
            case 235:
                c2 = '&';
                break;
            case 236:
                c2 = '\'';
                break;
            case 237:
                c2 = '(';
                break;
            case 238:
                c2 = ')';
                break;
            case 239:
                c2 = '*';
                break;
            case 240:
                break;
            case 241:
                c2 = ',';
                break;
            case 242:
                c2 = '-';
                break;
            case 243:
                c2 = '.';
                break;
            case 244:
                c2 = '/';
                break;
            case 245:
                c2 = ':';
                break;
            case 246:
                c2 = ';';
                break;
            case 247:
                c2 = '<';
                break;
            case 248:
                c2 = '=';
                break;
            case 249:
                c2 = '>';
                break;
            case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION:
                c2 = '?';
                break;
            case 251:
                c2 = '_';
                break;
            case 252:
                c2 = ' ';
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        return new DecodedChar(i2 + 8, c2);
    }

    private boolean isStillAlpha(final int i2) {
        final int extractNumericValueFromBitArray;
        if (i2 + 5 > information.getSize()) {
            return false;
        }
        final int extractNumericValueFromBitArray2 = this.extractNumericValueFromBitArray(i2, 5);
        if (5 <= extractNumericValueFromBitArray2 && 16 > extractNumericValueFromBitArray2) {
            return true;
        }
        return i2 + 6 <= information.getSize() && 16 <= (extractNumericValueFromBitArray = extractNumericValueFromBitArray(i2, 6)) && 63 > extractNumericValueFromBitArray;
    }

    private DecodedChar decodeAlphanumeric(final int i2) {
        final char c2;
        final int extractNumericValueFromBitArray = this.extractNumericValueFromBitArray(i2, 5);
        if (15 == extractNumericValueFromBitArray) {
            return new DecodedChar(i2 + 5, '');
        }
        if (5 <= extractNumericValueFromBitArray && 15 > extractNumericValueFromBitArray) {
            return new DecodedChar(i2 + 5, (char) (extractNumericValueFromBitArray + 43));
        }
        final int extractNumericValueFromBitArray2 = this.extractNumericValueFromBitArray(i2, 6);
        if (32 <= extractNumericValueFromBitArray2 && 58 > extractNumericValueFromBitArray2) {
            return new DecodedChar(i2 + 6, (char) (extractNumericValueFromBitArray2 + 33));
        }
        switch (extractNumericValueFromBitArray2) {
            case 58:
                c2 = '*';
                break;
            case 59:
                c2 = ',';
                break;
            case 60:
                c2 = '-';
                break;
            case 61:
                c2 = '.';
                break;
            case 62:
                c2 = '/';
                break;
            default:
                throw new IllegalStateException("Decoding invalid alphanumeric value: " + extractNumericValueFromBitArray2);
        }
        return new DecodedChar(i2 + 6, c2);
    }

    private boolean isAlphaTo646ToAlphaLatch(final int i2) {
        int i3;
        if (i2 + 1 > information.getSize()) {
            return false;
        }
        int i4 = 0;
        while (5 > i4 && (i3 = i4 + i2) < information.getSize()) {
            if (2 == i4) {
                if (!information.get(i2 + 2)) {
                    return false;
                }
            } else if (information.get(i3)) {
                return false;
            }
            i4++;
        }
        return true;
    }

    private boolean isAlphaOr646ToNumericLatch(int i2) {
        final int i3 = i2 + 3;
        if (i3 > information.getSize()) {
            return false;
        }
        while (i2 < i3) {
            if (information.get(i2)) {
                return false;
            }
            i2++;
        }
        return true;
    }

    private boolean isNumericToAlphaNumericLatch(final int i2) {
        int i3;
        if (i2 + 1 > information.getSize()) {
            return false;
        }
        int i4 = 0;
        while (4 > i4 && (i3 = i4 + i2) < information.getSize()) {
            if (information.get(i3)) {
                return false;
            }
            i4++;
        }
        return true;
    }
}
