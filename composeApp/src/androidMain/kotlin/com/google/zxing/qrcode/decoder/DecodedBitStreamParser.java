package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.kxml2.wap.Wbxml;

final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ %*+-./:".toCharArray();
    private DecodedBitStreamParser() {
    }
    static DecoderResult decode(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel, Map<DecodeHintType, ?> map) throws FormatException {
        Mode modeForBits;
        Mode mode;
        Mode mode2;
        BitSource bitSource = new BitSource(bArr);
        StringBuilder sb = new StringBuilder(50);
        int r11 = 1;
        ArrayList arrayList = new ArrayList(1);
        int r13 = -1;
        int bits = -1;
        boolean z = false;
        CharacterSetECI characterSetECIByValue = null;
        while (true) {
            try {
                if (bitSource.available() < 4) {
                    modeForBits = Mode.TERMINATOR;
                } else {
                    modeForBits = Mode.forBits(bitSource.readBits(4));
                }
                Mode mode3 = modeForBits;
                Mode mode4 = Mode.TERMINATOR;
                if (mode3 == mode4) {
                    mode = mode4;
                    mode2 = mode3;
                } else if (mode3 == Mode.FNC1_FIRST_POSITION || mode3 == Mode.FNC1_SECOND_POSITION) {
                    mode = mode4;
                    mode2 = mode3;
                    z = true;
                } else {
                    if (mode3 == Mode.STRUCTURED_APPEND) {
                        if (bitSource.available() < 16) {
                            throw FormatException.getFormatInstance();
                        }
                        int bits2 = bitSource.readBits(8);
                        bits = bitSource.readBits(8);
                        r13 = bits2;
                    } else if (mode3 == Mode.ECI) {
                        characterSetECIByValue = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(bitSource));
                        if (characterSetECIByValue == null) {
                            throw FormatException.getFormatInstance();
                        }
                    } else if (mode3 == Mode.HANZI) {
                        int bits3 = bitSource.readBits(4);
                        int bits4 = bitSource.readBits(mode3.getCharacterCountBits(version));
                        if (bits3 == r11) {
                            decodeHanziSegment(bitSource, sb, bits4);
                        }
                    } else {
                        int bits5 = bitSource.readBits(mode3.getCharacterCountBits(version));
                        if (mode3 == Mode.NUMERIC) {
                            decodeNumericSegment(bitSource, sb, bits5);
                        } else if (mode3 == Mode.ALPHANUMERIC) {
                            decodeAlphanumericSegment(bitSource, sb, bits5, z);
                        } else if (mode3 == Mode.BYTE) {
                            mode = mode4;
                            mode2 = mode3;
                            decodeByteSegment(bitSource, sb, bits5, characterSetECIByValue, arrayList, map);
                        } else {
                            mode = mode4;
                            mode2 = mode3;
                            if (mode2 == Mode.KANJI) {
                                decodeKanjiSegment(bitSource, sb, bits5);
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                        }
                    }
                    mode = mode4;
                    mode2 = mode3;
                }
                if (mode2 == mode) {
                    return new DecoderResult(bArr, sb.toString(), arrayList.isEmpty() ? null : arrayList, errorCorrectionLevel == null ? null : errorCorrectionLevel.toString(), r13, bits);
                }
                r11 = 1;
            } catch (IllegalArgumentException unused) {
                throw FormatException.getFormatInstance();
            }
        }
    }
    private static void decodeHanziSegment(BitSource bitSource, StringBuilder sb, int r6) throws FormatException {
        if (r6 * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[r6 * 2];
        int r1 = 0;
        while (r6 > 0) {
            int bits = bitSource.readBits(13);
            int r2 = (bits % 96) | ((bits / 96) << 8);
            int r22 = r2 + (r2 < 959 ? 41377 : 42657);
            bArr[r1] = (byte) (r22 >> 8);
            bArr[r1 + 1] = (byte) r22;
            r1 += 2;
            r6--;
        }
        try {
            sb.append(new String(bArr, "GB2312"));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }
    private static void decodeKanjiSegment(BitSource bitSource, StringBuilder sb, int r6) throws FormatException {
        if (r6 * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[r6 * 2];
        int r1 = 0;
        while (r6 > 0) {
            int bits = bitSource.readBits(13);
            int r2 = (bits % Wbxml.EXT_0) | ((bits / Wbxml.EXT_0) << 8);
            int r22 = r2 + (r2 < 7936 ? 33088 : 49472);
            bArr[r1] = (byte) (r22 >> 8);
            bArr[r1 + 1] = (byte) r22;
            r1 += 2;
            r6--;
        }
        try {
            sb.append(new String(bArr, "SJIS"));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }
    private static void decodeByteSegment(BitSource bitSource, StringBuilder sb, int r5, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        String strName;
        if ((r5 << 3) > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[r5];
        for (int r1 = 0; r1 < r5; r1++) {
            bArr[r1] = (byte) bitSource.readBits(8);
        }
        if (characterSetECI == null) {
            strName = StringUtils.guessEncoding(bArr, map);
        } else {
            strName = characterSetECI.name();
        }
        try {
            sb.append(new String(bArr, strName));
            collection.add(bArr);
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }
    private static char toAlphaNumericChar(int r2) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (r2 >= cArr.length) {
            throw FormatException.getFormatInstance();
        }
        return cArr[r2];
    }
    private static void decodeAlphanumericSegment(BitSource bitSource, StringBuilder sb, int r5, boolean z) throws FormatException {
        while (r5 > 1) {
            if (bitSource.available() < 11) {
                throw FormatException.getFormatInstance();
            }
            int bits = bitSource.readBits(11);
            sb.append(toAlphaNumericChar(bits / 45));
            sb.append(toAlphaNumericChar(bits % 45));
            r5 -= 2;
        }
        if (r5 == 1) {
            if (bitSource.available() < 6) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bitSource.readBits(6)));
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int r3 = length + 1;
                        if (sb.charAt(r3) == '%') {
                            sb.deleteCharAt(r3);
                        } else {
                            sb.setCharAt(length, (char) 29);
                        }
                    }
                }
            }
        }
    }
    private static void decodeNumericSegment(BitSource bitSource, StringBuilder sb, int r5) throws FormatException {
        while (r5 >= 3) {
            if (bitSource.available() < 10) {
                throw FormatException.getFormatInstance();
            }
            int bits = bitSource.readBits(10);
            if (bits >= 1000) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bits / 100));
            sb.append(toAlphaNumericChar((bits / 10) % 10));
            sb.append(toAlphaNumericChar(bits % 10));
            r5 -= 3;
        }
        if (r5 == 2) {
            if (bitSource.available() < 7) {
                throw FormatException.getFormatInstance();
            }
            int bits2 = bitSource.readBits(7);
            if (bits2 >= 100) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bits2 / 10));
            sb.append(toAlphaNumericChar(bits2 % 10));
            return;
        }
        if (r5 == 1) {
            if (bitSource.available() < 4) {
                throw FormatException.getFormatInstance();
            }
            int bits3 = bitSource.readBits(4);
            if (bits3 >= 10) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bits3));
        }
    }
    private static int parseECIValue(BitSource bitSource) throws FormatException {
        int bits = bitSource.readBits(8);
        if ((bits & 128) == 0) {
            return bits & 127;
        }
        if ((bits & Wbxml.EXT_0) == 128) {
            return bitSource.readBits(8) | ((bits & 63) << 8);
        }
        if ((bits & 224) == 192) {
            return bitSource.readBits(16) | ((bits & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }
}
