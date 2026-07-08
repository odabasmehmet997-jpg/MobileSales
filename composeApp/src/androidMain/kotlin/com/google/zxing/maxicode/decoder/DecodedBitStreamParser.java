package com.google.zxing.maxicode.decoder;

import com.google.zxing.common.DecoderResult;

import java.text.DecimalFormat;

enum DecodedBitStreamParser {
    ;
    private static final String[] SETS = {"\nABCDEFGHIJKLMNOPQRSTUVWXYZ￺\u001c\u001d\u001e￻ ￼\"#%&'()*+,-./0123456789:￱￲￳￴￸", "`abcdefghijklmnopqrstuvwxyz￺\u001c\u001d\u001e￻{￼}~;<=>?[\\]^_ ,./:@!|￼￵￶￼￰￲￳￴￷", "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚ￺\u001c\u001d\u001eÛÜÝÞßª¬±²³µ¹º¼½¾￷ ￹￳￴￸", "àáâãäåæçèéêëìíîïðñòóôõö÷øùú￺\u001c\u001d\u001e￻ûüýþÿ¡¨«¯°´·¸»¿￷ ￲￹￴￸", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a￺￼￼\u001b￻\u001c\u001d\u001e\u001f ¢£¤¥¦§©­®¶￷ ￲￳￹￸", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#%&'()*+,-./0123456789:;<=>?"};

    static DecoderResult decode(final byte[] bArr, final int i2) {
        final String str;
        final StringBuilder sb = new StringBuilder(144);
        if (2 == i2 || 3 == i2) {
            if (2 == i2) {
                str = new DecimalFormat("0000000000".substring(0, DecodedBitStreamParser.getPostCode2Length(bArr))).format(DecodedBitStreamParser.getPostCode2(bArr));
            } else {
                str = DecodedBitStreamParser.getPostCode3(bArr);
            }
            final DecimalFormat decimalFormat = new DecimalFormat("000");
            final String format = decimalFormat.format(DecodedBitStreamParser.getCountry(bArr));
            final String format2 = decimalFormat.format(DecodedBitStreamParser.getServiceClass(bArr));
            sb.append(DecodedBitStreamParser.getMessage(bArr, 10, 84));
            if (sb.toString().startsWith("[)>\u001e01\u001d")) {
                sb.insert(9, str + 29 + format + 29 + format2 + 29);
            } else {
                sb.insert(0, str + 29 + format + 29 + format2 + 29);
            }
        } else if (4 == i2) {
            sb.append(DecodedBitStreamParser.getMessage(bArr, 1, 93));
        } else if (5 == i2) {
            sb.append(DecodedBitStreamParser.getMessage(bArr, 1, 77));
        }
        return new DecoderResult(bArr, sb.toString(), null, String.valueOf(i2));
    }

    private static int getBit(final int i2, final byte[] bArr) {
        final int i3 = i2 - 1;
        return 0 == ((1 << (5 - (i3 % 6))) & bArr[i3 / 6]) ? 0 : 1;
    }

    private static int getInt(final byte[] bArr, final byte[] bArr2) {
        if (0 != bArr2.length) {
            int i2 = 0;
            for (int i3 = 0; i3 < bArr2.length; i3++) {
                i2 += DecodedBitStreamParser.getBit(bArr2[i3], bArr) << ((bArr2.length - i3) - 1);
            }
            return i2;
        }
        throw new IllegalArgumentException();
    }

    private static int getCountry(final byte[] bArr) {
        return DecodedBitStreamParser.getInt(bArr, new byte[]{53, 54, 43, 44, 45, 46, 47, 48, 37, 38});
    }

    private static int getServiceClass(final byte[] bArr) {
        return DecodedBitStreamParser.getInt(bArr, new byte[]{55, 56, 57, 58, 59, 60, 49, 50, 51, 52});
    }

    private static int getPostCode2Length(final byte[] bArr) {
        return DecodedBitStreamParser.getInt(bArr, new byte[]{39, 40, 41, 42, 31, 32});
    }

    private static int getPostCode2(final byte[] bArr) {
        return DecodedBitStreamParser.getInt(bArr, new byte[]{33, 34, 35, 36, 25, 26, 27, 28, 29, 30, 19, 20, 21, 22, 23, 24, 13, 14, 15, 16, 17, 18, 7, 8, 9, 10, 11, 12, 1, 2});
    }

    private static String getPostCode3(final byte[] bArr) {
        final String[] strArr = DecodedBitStreamParser.SETS;
        return String.valueOf(new char[]{strArr[0].charAt(DecodedBitStreamParser.getInt(bArr, new byte[]{39, 40, 41, 42, 31, 32})), strArr[0].charAt(DecodedBitStreamParser.getInt(bArr, new byte[]{33, 34, 35, 36, 25, 26})), strArr[0].charAt(DecodedBitStreamParser.getInt(bArr, new byte[]{27, 28, 29, 30, 19, 20})), strArr[0].charAt(DecodedBitStreamParser.getInt(bArr, new byte[]{21, 22, 23, 24, 13, 14})), strArr[0].charAt(DecodedBitStreamParser.getInt(bArr, new byte[]{15, 16, 17, 18, 7, 8})), strArr[0].charAt(DecodedBitStreamParser.getInt(bArr, new byte[]{9, 10, 11, 12, 1, 2}))});
    }

    private static String getMessage(final byte[] bArr, final int i2, final int i3) {
        int i4;
        final StringBuilder sb = new StringBuilder();
        int i5 = i2;
        int i6 = -1;
        int i7 = 0;
        int i8 = 0;
        while (i5 < i2 + i3) {
            final char charAt = DecodedBitStreamParser.SETS[i7].charAt(bArr[i5]);
            switch (charAt) {
                case 65520:
                case 65521:
                case 65522:
                case 65523:
                case 65524:
                    i8 = i7;
                    i7 = charAt - 65520;
                    i4 = 1;
                    break;
                case 65525:
                    i4 = 2;
                    break;
                case 65526:
                    i4 = 3;
                    break;
                case 65527:
                    i4 = -1;
                    break;
                case 65528:
                    i4 = -1;
                    i7 = 1;
                    break;
                case 65529:
                    i4 = -1;
                    break;
                case 65531:
                    i5 += 5;
                    sb.append(new DecimalFormat("000000000").format((bArr[i5 + 1] << 24) + (bArr[i5 + 2] << 18) + (bArr[i5 + 3] << 12) + (bArr[i5 + 4] << 6) + bArr[i5]));
                    break;
                default:
                    sb.append(charAt);
                    break;
            }
            i8 = i7;
            i7 = 0;
            final int i9 = i4 - 1;
            if (0 == i4) {
                i7 = i8;
            }
            i5++;
            i6 = i9;
        }
        while (0 < sb.length() && 65532 == sb.charAt(sb.length() - 1)) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
