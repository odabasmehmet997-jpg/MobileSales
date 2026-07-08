package com.fasterxml.jackson.core.io;

import java.util.Arrays;

public enum CharTypes {
    ;

    private static final byte[] f793HB;
    private static final char[] f794HC;
    private static final int[] sHexValues;
    private static final int[] sInputCodes;
    private static final int[] sInputCodesComment;
    private static final int[] sInputCodesJsNames;
    private static final int[] sInputCodesUTF8;
    private static final int[] sInputCodesUtf8JsNames;
    private static final int[] sInputCodesWS;
    private static final int[] sOutputEscapes128;
    static {
        final char[] charArray = "0123456789ABCDEF".toCharArray();
        f794HC = charArray;
        final int length = charArray.length;
        f793HB = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            CharTypes.f793HB[i2] = (byte) CharTypes.f794HC[i2];
        }
        final int[] iArr = new int[256];
        for (int i3 = 0; 32 > i3; i3++) {
            iArr[i3] = -1;
        }
        iArr[34] = 1;
        iArr[92] = 1;
        sInputCodes = iArr;
        final int length2 = iArr.length;
        final int[] iArr2 = new int[length2];
        System.arraycopy(iArr, 0, iArr2, 0, length2);
        for (int i4 = 128; 256 > i4; i4++) {
            iArr2[i4] = 192 == (i4 & 224) ? 2 : 224 == (i4 & 240) ? 3 : 240 == (i4 & 248) ? 4 : -1;
        }
        sInputCodesUTF8 = iArr2;
        final int[] iArr3 = new int[256];
        Arrays.fill(iArr3, -1);
        for (int i5 = 33; 256 > i5; i5++) {
            if (Character.isJavaIdentifierPart((char) i5)) {
                iArr3[i5] = 0;
            }
        }
        iArr3[64] = 0;
        iArr3[35] = 0;
        iArr3[42] = 0;
        iArr3[45] = 0;
        iArr3[43] = 0;
        sInputCodesJsNames = iArr3;
        final int[] iArr4 = new int[256];
        System.arraycopy(iArr3, 0, iArr4, 0, 256);
        Arrays.fill(iArr4, 128, 128, 0);
        sInputCodesUtf8JsNames = iArr4;
        final int[] iArr5 = new int[256];
        final int[] iArr6 = CharTypes.sInputCodesUTF8;
        System.arraycopy(iArr6, 128, iArr5, 128, 128);
        Arrays.fill(iArr5, 0, 32, -1);
        iArr5[9] = 0;
        iArr5[10] = 10;
        iArr5[13] = 13;
        iArr5[42] = 42;
        sInputCodesComment = iArr5;
        final int[] iArr7 = new int[256];
        System.arraycopy(iArr6, 128, iArr7, 128, 128);
        Arrays.fill(iArr7, 0, 32, -1);
        iArr7[32] = 1;
        iArr7[9] = 1;
        iArr7[10] = 10;
        iArr7[13] = 13;
        iArr7[47] = 47;
        iArr7[35] = 35;
        sInputCodesWS = iArr7;
        final int[] iArr8 = new int[128];
        for (int i6 = 0; 32 > i6; i6++) {
            iArr8[i6] = -1;
        }
        iArr8[34] = 34;
        iArr8[92] = 92;
        iArr8[8] = 98;
        iArr8[9] = 116;
        iArr8[12] = 102;
        iArr8[10] = 110;
        iArr8[13] = 114;
        sOutputEscapes128 = iArr8;
        final int[] iArr9 = new int[256];
        sHexValues = iArr9;
        Arrays.fill(iArr9, -1);
        for (int i7 = 0; 10 > i7; i7++) {
            CharTypes.sHexValues[i7 + 48] = i7;
        }
        for (int i8 = 0; 6 > i8; i8++) {
            final int[] iArr10 = CharTypes.sHexValues;
            final int i9 = i8 + 10;
            iArr10[i8 + 97] = i9;
            iArr10[i8 + 65] = i9;
        }
    }
    public static int[] getInputCodeLatin1() {
        return CharTypes.sInputCodes;
    }
    public static int[] getInputCodeUtf8() {
        return CharTypes.sInputCodesUTF8;
    }
    public static int[] getInputCodeLatin1JsNames() {
        return CharTypes.sInputCodesJsNames;
    }
    public static int[] getInputCodeUtf8JsNames() {
        return CharTypes.sInputCodesUtf8JsNames;
    }
    public static int[] getInputCodeComment() {
        return CharTypes.sInputCodesComment;
    }
    public static int[] get7BitOutputEscapes() {
        return CharTypes.sOutputEscapes128;
    }
    public static int[] get7BitOutputEscapes(final int i2) {
        if (34 == i2) {
            return CharTypes.sOutputEscapes128;
        }
        return AltEscapes.instance.escapesFor(i2);
    }
    public static int charToHex(final int i2) {
        return CharTypes.sHexValues[i2 & 255];
    }
    public static void appendQuoted(final StringBuilder sb, final String str) {
        final int[] iArr = CharTypes.sOutputEscapes128;
        final int length = iArr.length;
        final int length2 = str.length();
        for (int i2 = 0; i2 < length2; i2++) {
            final char cCharAt = str.charAt(i2);
            if (cCharAt >= length || 0 == iArr[cCharAt]) {
                sb.append(cCharAt);
            } else {
                sb.append('\\');
                final int i3 = iArr[cCharAt];
                if (0 > i3) {
                    sb.append('u');
                    sb.append('0');
                    sb.append('0');
                    final char[] cArr = CharTypes.f794HC;
                    sb.append(cArr[cCharAt >> 4]);
                    sb.append(cArr[cCharAt & 15]);
                } else {
                    sb.append((char) i3);
                }
            }
        }
    }
    public static char[] copyHexChars() {
        return CharTypes.f794HC.clone();
    }
    public static byte[] copyHexBytes() {
        return CharTypes.f793HB.clone();
    }
    private static class AltEscapes {
        public static final AltEscapes instance = new AltEscapes();
        private final int[][] _altEscapes = new int[128][];

        private AltEscapes() {
        }

        public int[] escapesFor(final int i2) {
            int[] iArrCopyOf = _altEscapes[i2];
            if (null == iArrCopyOf) {
                iArrCopyOf = Arrays.copyOf(sOutputEscapes128, 128);
                if (0 == iArrCopyOf[i2]) {
                    iArrCopyOf[i2] = -1;
                }
                _altEscapes[i2] = iArrCopyOf;
            }
            return iArrCopyOf;
        }
    }
}
