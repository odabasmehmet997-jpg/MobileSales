package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.kxml2.wap.Wbxml;

public final class Encoder {
    private static final int[] ALPHANUMERIC_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
    private Encoder() {
    }
    private static int calculateMaskPenalty(ByteMatrix byteMatrix) {
        return MaskUtil.applyMaskPenaltyRule1(byteMatrix) + MaskUtil.applyMaskPenaltyRule2(byteMatrix) + MaskUtil.applyMaskPenaltyRule3(byteMatrix) + MaskUtil.applyMaskPenaltyRule4(byteMatrix);
    }
    public static QRCode encode(String str, ErrorCorrectionLevel errorCorrectionLevel, Map<EncodeHintType, ?> map) throws WriterException, UnsupportedEncodingException {
        String string;
        Version versionRecommendVersion = null;
        CharacterSetECI characterSetECIByName;
        if (map != null) {
            EncodeHintType encodeHintType = EncodeHintType.CHARACTER_SET;
            string = map.containsKey(encodeHintType) ? map.get(encodeHintType).toString() : "ISO-8859-1";
        } else {
            string = "ISO-8859-1";
        }
        Mode modeChooseMode = chooseMode(str, string);
        BitArray bitArray = new BitArray();
        Mode mode = Mode.BYTE;
        if (modeChooseMode == mode && !"ISO-8859-1".equals(string) && (characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(string)) != null) {
            appendECI(characterSetECIByName, bitArray);
        }
        appendModeInfo(modeChooseMode, bitArray);
        BitArray bitArray2 = new BitArray();
        appendBytes(str, modeChooseMode, bitArray2, string);
        if (map != null) {
            EncodeHintType encodeHintType2 = EncodeHintType.QR_VERSION;
            if (map.containsKey(encodeHintType2)) {
                versionRecommendVersion = Version.getVersionForNumber(Integer.parseInt(map.get(encodeHintType2).toString()));
                if (!willFit(calculateBitsNeeded(modeChooseMode, bitArray, bitArray2, versionRecommendVersion), versionRecommendVersion, errorCorrectionLevel)) {
                    throw new WriterException("Data too big for requested version");
                }
            } else {
                versionRecommendVersion = recommendVersion(errorCorrectionLevel, modeChooseMode, bitArray, bitArray2);
            }
        }
        BitArray bitArray3 = new BitArray();
        bitArray3.appendBitArray(bitArray);
        appendLengthInfo(modeChooseMode == mode ? bitArray2.getSizeInBytes() : str.length(), versionRecommendVersion, modeChooseMode, bitArray3);
        bitArray3.appendBitArray(bitArray2);
        Version.ECBlocks eCBlocksForLevel = versionRecommendVersion.getECBlocksForLevel(errorCorrectionLevel);
        int totalCodewords = versionRecommendVersion.getTotalCodewords() - eCBlocksForLevel.getTotalECCodewords();
        terminateBits(totalCodewords, bitArray3);
        BitArray bitArrayInterleaveWithECBytes = interleaveWithECBytes(bitArray3, versionRecommendVersion.getTotalCodewords(), totalCodewords, eCBlocksForLevel.getNumBlocks());
        QRCode qRCode = new QRCode();
        qRCode.setECLevel(errorCorrectionLevel);
        qRCode.setMode(modeChooseMode);
        qRCode.setVersion(versionRecommendVersion);
        int dimensionForVersion = versionRecommendVersion.getDimensionForVersion();
        ByteMatrix byteMatrix = new ByteMatrix(dimensionForVersion, dimensionForVersion);
        int r1 = chooseMaskPattern(bitArrayInterleaveWithECBytes, errorCorrectionLevel, versionRecommendVersion, byteMatrix);
        qRCode.setMaskPattern(r1);
        MatrixUtil.buildMatrix(bitArrayInterleaveWithECBytes, errorCorrectionLevel, versionRecommendVersion, r1, byteMatrix);
        qRCode.setMatrix(byteMatrix);
        return qRCode;
    }
    private static Version recommendVersion(ErrorCorrectionLevel errorCorrectionLevel, Mode mode, BitArray bitArray, BitArray bitArray2) throws WriterException {
        return chooseVersion(calculateBitsNeeded(mode, bitArray, bitArray2, chooseVersion(calculateBitsNeeded(mode, bitArray, bitArray2, Version.getVersionForNumber(1)), errorCorrectionLevel)), errorCorrectionLevel);
    }
    private static int calculateBitsNeeded(Mode mode, BitArray bitArray, BitArray bitArray2, Version version) {
        return bitArray.getSize() + mode.getCharacterCountBits(version) + bitArray2.getSize();
    }
    static int getAlphanumericCode(int r2) {
        int[] r0 = ALPHANUMERIC_TABLE;
        if (r2 < r0.length) {
            return r0[r2];
        }
        return -1;
    }
    private static Mode chooseMode(String str, String str2) throws UnsupportedEncodingException {
        if ("Shift_JIS".equals(str2) && isOnlyDoubleByteKanji(str)) {
            return Mode.KANJI;
        }
        boolean z = false;
        boolean z2 = false;
        for (int r6 = 0; r6 < str.length(); r6++) {
            char cCharAt = str.charAt(r6);
            if (cCharAt >= '0' && cCharAt <= '9') {
                z2 = true;
            } else {
                if (getAlphanumericCode(cCharAt) == -1) {
                    return Mode.BYTE;
                }
                z = true;
            }
        }
        if (z) {
            return Mode.ALPHANUMERIC;
        }
        if (z2) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }
    private static boolean isOnlyDoubleByteKanji(String str) throws UnsupportedEncodingException {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int r2 = 0; r2 < length; r2 += 2) {
                int r3 = bytes[r2] & 255;
                if ((r3 < 129 || r3 > 159) && (r3 < 224 || r3 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }
    private static int chooseMaskPattern(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, ByteMatrix byteMatrix) throws WriterException {
        int r0 = Integer.MAX_VALUE;
        int r1 = -1;
        for (int r2 = 0; r2 < 8; r2++) {
            MatrixUtil.buildMatrix(bitArray, errorCorrectionLevel, version, r2, byteMatrix);
            int r3 = calculateMaskPenalty(byteMatrix);
            if (r3 < r0) {
                r1 = r2;
                r0 = r3;
            }
        }
        return r1;
    }
    private static Version chooseVersion(int r3, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        for (int r0 = 1; r0 <= 40; r0++) {
            Version versionForNumber = Version.getVersionForNumber(r0);
            if (willFit(r3, versionForNumber, errorCorrectionLevel)) {
                return versionForNumber;
            }
        }
        throw new WriterException("Data too big");
    }
    private static boolean willFit(int r1, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        return version.getTotalCodewords() - version.getECBlocksForLevel(errorCorrectionLevel).getTotalECCodewords() >= (r1 + 7) / 8;
    }
    static void terminateBits(int r4, BitArray bitArray) throws WriterException {
        int r0 = r4 << 3;
        if (bitArray.getSize() > r0) {
            throw new WriterException("data bits cannot fit in the QR Code" + bitArray.getSize() + " > " + r0);
        }
        for (int r2 = 0; r2 < 4 && bitArray.getSize() < r0; r2++) {
            bitArray.appendBit(false);
        }
        int size = bitArray.getSize() & 7;
        if (size > 0) {
            while (size < 8) {
                bitArray.appendBit(false);
                size++;
            }
        }
        int sizeInBytes = r4 - bitArray.getSizeInBytes();
        for (int r1 = 0; r1 < sizeInBytes; r1++) {
            bitArray.appendBits((r1 & 1) == 0 ? 236 : 17, 8);
        }
        if (bitArray.getSize() != r0) {
            throw new WriterException("Bits size does not equal capacity");
        }
    }
    static void getNumDataBytesAndNumECBytesForBlockID(int r6, int r7, int r8, int r9, int[] r10, int[] r11) throws WriterException {
        if (r9 >= r8) {
            throw new WriterException("Block ID too large");
        }
        int r0 = r6 % r8;
        int r1 = r8 - r0;
        int r2 = r6 / r8;
        int r3 = r2 + 1;
        int r72 = r7 / r8;
        int r4 = r72 + 1;
        int r22 = r2 - r72;
        int r32 = r3 - r4;
        if (r22 != r32) {
            throw new WriterException("EC bytes mismatch");
        }
        if (r8 != r1 + r0) {
            throw new WriterException("RS blocks mismatch");
        }
        if (r6 != ((r72 + r22) * r1) + ((r4 + r32) * r0)) {
            throw new WriterException("Total bytes mismatch");
        }
        if (r9 < r1) {
            r10[0] = r72;
            r11[0] = r22;
        } else {
            r10[0] = r4;
            r11[0] = r32;
        }
    }
    static BitArray interleaveWithECBytes(BitArray bitArray, int r18, int r19, int r20) throws WriterException {
        if (bitArray.getSizeInBytes() != r19) {
            throw new WriterException("Number of bits and data bytes does not match");
        }
        ArrayList arrayList = new ArrayList(r20);
        int r12 = 0;
        int r13 = 0;
        int r14 = 0;
        for (int r11 = 0; r11 < r20; r11++) {
            int[] r15 = new int[1];
            int[] r5 = new int[1];
            getNumDataBytesAndNumECBytesForBlockID(r18, r19, r20, r11, r15, r5);
            int r0 = r15[0];
            byte[] bArr = new byte[r0];
            bitArray.toBytes(r12 << 3, bArr, 0, r0);
            byte[] bArrGenerateECBytes = generateECBytes(bArr, r5[0]);
            arrayList.add(new BlockPair(bArr, bArrGenerateECBytes));
            r13 = Math.max(r13, r0);
            r14 = Math.max(r14, bArrGenerateECBytes.length);
            r12 += r15[0];
        }
        if (r19 != r12) {
            throw new WriterException("Data bytes does not match offset");
        }
        BitArray bitArray2 = new BitArray();
        for (int r1 = 0; r1 < r13; r1++) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                byte[] dataBytes = ((BlockPair) it.next()).dataBytes();
                if (r1 < dataBytes.length) {
                    bitArray2.appendBits(dataBytes[r1], 8);
                }
            }
        }
        for (int r10 = 0; r10 < r14; r10++) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                byte[] errorCorrectionBytes = ((BlockPair) it2.next()).errorCorrectionBytes();
                if (r10 < errorCorrectionBytes.length) {
                    bitArray2.appendBits(errorCorrectionBytes[r10], 8);
                }
            }
        }
        if (r18 == bitArray2.getSizeInBytes()) {
            return bitArray2;
        }
        throw new WriterException("Interleaving error: " + r18 + " and " + bitArray2.getSizeInBytes() + " differ.");
    }
    static byte[] generateECBytes(byte[] bArr, int r6) {
        int length = bArr.length;
        int[] r1 = new int[length + r6];
        for (int r3 = 0; r3 < length; r3++) {
            r1[r3] = bArr[r3] & 255;
        }
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(r1, r6);
        byte[] bArr2 = new byte[r6];
        for (int r2 = 0; r2 < r6; r2++) {
            bArr2[r2] = (byte) r1[length + r2];
        }
        return bArr2;
    }
    static void appendModeInfo(Mode mode, BitArray bitArray) {
        bitArray.appendBits(mode.getBits(), 4);
    }
    static void appendLengthInfo(int r1, Version version, Mode mode, BitArray bitArray) throws WriterException {
        int characterCountBits = mode.getCharacterCountBits(version);
        int r0 = 1 << characterCountBits;
        if (r1 >= r0) {
            throw new WriterException(r1 + " is bigger than " + (r0 - 1));
        }
        bitArray.appendBits(r1, characterCountBits);
    }
    static class C25541 {
        static final /* synthetic */ int[] SwitchMapcomgooglezxingqrcodedecoderMode;

        static {
            int[] r0 = new int[Mode.values().length];
            SwitchMapcomgooglezxingqrcodedecoderMode = r0;
            try {
                r0[Mode.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                SwitchMapcomgooglezxingqrcodedecoderMode[Mode.ALPHANUMERIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                SwitchMapcomgooglezxingqrcodedecoderMode[Mode.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                SwitchMapcomgooglezxingqrcodedecoderMode[Mode.KANJI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
    static void appendBytes(String str, Mode mode, BitArray bitArray, String str2) throws WriterException, UnsupportedEncodingException {
        int r0 = C25541.SwitchMapcomgooglezxingqrcodedecoderMode[mode.ordinal()];
        if (r0 == 1) {
            appendNumericBytes(str, bitArray);
            return;
        }
        if (r0 == 2) {
            appendAlphanumericBytes(str, bitArray);
            return;
        }
        if (r0 == 3) {
            append8BitBytes(str, bitArray, str2);
        } else if (r0 == 4) {
            appendKanjiBytes(str, bitArray);
        } else {
            throw new WriterException("Invalid mode: " + mode);
        }
    }
    static void appendNumericBytes(CharSequence charSequence, BitArray bitArray) {
        int length = charSequence.length();
        int r1 = 0;
        while (r1 < length) {
            int r2 = charSequence.charAt(r1) - '0';
            int r3 = r1 + 2;
            if (r3 < length) {
                bitArray.appendBits((r2 * 100) + ((charSequence.charAt(r1 + 1) - '0') * 10) + (charSequence.charAt(r3) - '0'), 10);
                r1 += 3;
            } else {
                r1++;
                if (r1 < length) {
                    bitArray.appendBits((r2 * 10) + (charSequence.charAt(r1) - '0'), 7);
                    r1 = r3;
                } else {
                    bitArray.appendBits(r2, 4);
                }
            }
        }
    }
    static void appendAlphanumericBytes(CharSequence charSequence, BitArray bitArray) throws WriterException {
        int length = charSequence.length();
        int r1 = 0;
        while (r1 < length) {
            int alphanumericCode = getAlphanumericCode(charSequence.charAt(r1));
            if (alphanumericCode == -1) {
                throw new WriterException();
            }
            int r4 = r1 + 1;
            if (r4 < length) {
                int alphanumericCode2 = getAlphanumericCode(charSequence.charAt(r4));
                if (alphanumericCode2 == -1) {
                    throw new WriterException();
                }
                bitArray.appendBits((alphanumericCode * 45) + alphanumericCode2, 11);
                r1 += 2;
            } else {
                bitArray.appendBits(alphanumericCode, 6);
                r1 = r4;
            }
        }
    }
    static void append8BitBytes(String str, BitArray bitArray, String str2) throws WriterException, UnsupportedEncodingException {
        try {
            for (byte b2 : str.getBytes(str2)) {
                bitArray.appendBits(b2, 8);
            }
        } catch (UnsupportedEncodingException e2) {
            throw new WriterException(e2);
        }
    }
    static void appendKanjiBytes(String str, BitArray bitArray) throws WriterException, UnsupportedEncodingException {
        int r2;
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            for (int r1 = 0; r1 < length; r1 += 2) {
                int r22 = ((bytes[r1] & 255) << 8) | (bytes[r1 + 1] & 255);
                int r3 = 33088;
                if (r22 >= 33088 && r22 <= 40956) {
                    r2 = r22 - r3;
                } else if (r22 < 57408 || r22 > 60351) {
                    r2 = -1;
                } else {
                    r3 = 49472;
                    r2 = r22 - r3;
                }
                if (r2 == -1) {
                    throw new WriterException("Invalid byte sequence");
                }
                bitArray.appendBits(((r2 >> 8) * Wbxml.EXT_0) + (r2 & 255), 13);
            }
        } catch (UnsupportedEncodingException e2) {
            throw new WriterException(e2);
        }
    }
    private static void appendECI(CharacterSetECI characterSetECI, BitArray bitArray) {
        bitArray.appendBits(Mode.ECI.getBits(), 4);
        bitArray.appendBits(characterSetECI.getValue(), 8);
    }
}
