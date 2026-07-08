package com.google.zxing.pdf417.decoder;

import android.os.Build;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.zxing.FormatException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
final class DecodedBitStreamParser {
    private static final Charset DEFAULT_ENCODING = StandardCharsets.ISO_8859_1;
    private static final BigInteger[] EXP900;
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-./+%*=^".toCharArray();
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-./\"|*()?{}'".toCharArray();
    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }
    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger bigIntegerValueOf = BigInteger.valueOf(900L);
        bigIntegerArr[1] = bigIntegerValueOf;
        int i2 = 2;
        while (i2 < bigIntegerArr.length) {
            EXP900[i2] = EXP900[i2 - 1].multiply(bigIntegerValueOf);
            i2++;
        }
    }
    static DecoderResult decode(final int[] iArr, final String str) throws FormatException {
        int i2;
        final StringBuilder sb = new StringBuilder(iArr.length << 1);
        Charset charset = DEFAULT_ENCODING;
        int i3 = iArr[1];
        final PDF417ResultMetadata pDF417ResultMetadata = new PDF417ResultMetadata();
        int i4 = 2;
        while (i4 < iArr[0]) {
            if (913 != i3) {
                switch (i3) {
                    case TypedValues.Custom.TYPE_INT:
                        i2 = textCompaction(iArr, i4, sb);
                        break;
                    case TypedValues.Custom.TYPE_FLOAT:
                        i2 = AnonymousClass1.byteCompaction(i3, iArr, charset, i4, sb);
                        break;
                    case TypedValues.Custom.TYPE_COLOR:
                        i2 = AnonymousClass1.numericCompaction(iArr, i4, sb);
                        break;
                    default:
                        switch (i3) {
                            case 922:
                            case 923:
                                throw FormatException.getFormatInstance();
                            case 924:
                                break;
                            case 925:
                                i2 = i4 + 1;
                                break;
                            case 926:
                                i2 = i4 + 2;
                                break;
                            case 927:
                                i2 = i4 + 1;
                                charset = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(iArr[i4]).name());
                                break;
                            case 928:
                                i2 = DecodedBitStreamParser.decodeMacroBlock(iArr, i4, pDF417ResultMetadata);
                                break;
                            default:
                                i2 = DecodedBitStreamParser.textCompaction(iArr, i4 - 1, sb);
                                break;
                        }
                        i2 = AnonymousClass1.byteCompaction(i3, iArr, charset, i4, sb);
                        break;
                }
            } else {
                i2 = i4 + 1;
                sb.append((char) iArr[i4]);
            }
            if (i2 < iArr.length) {
                i4 = i2 + 1;
                i3 = iArr[i2];
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (0 != sb.length()) {
            final DecoderResult decoderResult = new DecoderResult(null, sb.toString(), null, str);
            decoderResult.setOther(pDF417ResultMetadata);
            return decoderResult;
        }
        throw FormatException.getFormatInstance();
    }
    private static int decodeMacroBlock(final int[] iArr, int i2, final PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i2 + 2 <= iArr[0]) {
            final int[] iArr2 = new int[2];
            int i3 = 0;
            while (2 > i3) {
                iArr2[i3] = iArr[i2];
                i3++;
                i2++;
            }
            pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(AnonymousClass1.decodeBase900toBase10(iArr2, 2)));
            final StringBuilder sb = new StringBuilder();
            final int textCompaction = AnonymousClass1.textCompaction(iArr, i2, sb);
            pDF417ResultMetadata.setFileId(sb.toString());
            final int i4 = iArr[textCompaction];
            if (923 == i4) {
                int i5 = textCompaction + 1;
                final int[] iArr3 = new int[(iArr[0] - i5)];
                boolean z = false;
                int i6 = 0;
                while (i5 < iArr[0] && !z) {
                    final int i7 = i5 + 1;
                    final int i8 = iArr[i5];
                    if (900 > i8) {
                        iArr3[i6] = i8;
                        i6++;
                        i5 = i7;
                    } else if (922 == i8) {
                        pDF417ResultMetadata.setLastSegment(true);
                        i5 += 2;
                        z = true;
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                pDF417ResultMetadata.setOptionalData(Arrays.copyOf(iArr3, i6));
                return i5;
            } else if (922 != i4) {
                return textCompaction;
            } else {
                pDF417ResultMetadata.setLastSegment(true);
                return textCompaction + 1;
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }
    private static int textCompaction(final int[] iArr, int i2, final StringBuilder sb) {
        final int i3 = iArr[0];
        final int[] iArr2 = new int[((i3 - i2) << 1)];
        final int[] iArr3 = new int[((i3 - i2) << 1)];
        boolean z = false;
        int i4 = 0;
        while (i2 < iArr[0] && !z) {
            final int i5 = i2 + 1;
            final int i6 = iArr[i2];
            if (900 > i6) {
                iArr2[i4] = i6 / 30;
                iArr2[i4 + 1] = i6 % 30;
                i4 += 2;
            } else if (913 != i6) {
                if (928 != i6) {
                    switch (i6) {
                        case TypedValues.Custom.TYPE_INT:
                            iArr2[i4] = 900;
                            i4++;
                            break;
                        case TypedValues.Custom.TYPE_FLOAT:
                        case TypedValues.Custom.TYPE_COLOR:
                            break;
                        default:
                            switch (i6) {
                                case 922:
                                case 923:
                                case 924:
                                    break;
                            }
                    }
                }
                z = true;
            } else {
                iArr2[i4] = 913;
                i2 += 2;
                iArr3[i4] = iArr[i5];
                i4++;
            }
            i2 = i5;
        }
        DecodedBitStreamParser.decodeTextCompaction(iArr2, iArr3, i4, sb);
        return i2;
    }
    private static void decodeTextCompaction(final int[] r16, final int[] r17, final int r18, final java.lang.StringBuilder r19) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decodeTextCompaction(int[], int[], int, java.lang.StringBuilder):void");
    }
    static class AnonymousClass1 {
        static final int[] SwitchMapcomgooglezxingpdf417decoderDecodedBitStreamParserMode;

        static {
            int[] iArr = new int[Mode.values().length];
            SwitchMapcomgooglezxingpdf417decoderDecodedBitStreamParserMode = iArr;
            try {
                iArr[Mode.ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                SwitchMapcomgooglezxingpdf417decoderDecodedBitStreamParserMode[Mode.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                SwitchMapcomgooglezxingpdf417decoderDecodedBitStreamParserMode[Mode.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                SwitchMapcomgooglezxingpdf417decoderDecodedBitStreamParserMode[Mode.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                SwitchMapcomgooglezxingpdf417decoderDecodedBitStreamParserMode[Mode.ALPHA_SHIFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                SwitchMapcomgooglezxingpdf417decoderDecodedBitStreamParserMode[Mode.PUNCT_SHIFT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }

        private static int byteCompaction(final int i2, final int[] iArr, final Charset charset, final int i3, final StringBuilder sb) {
            int i4;
            int i5;
            int i6;
            final int i7 = i2;
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i8 = 922;
            int i9 = 923;
            int i10 = 928;
            long j2 = 900;
            if (901 == i7) {
                final int[] iArr2 = new int[6];
                i4 = i3 + 1;
                int i11 = iArr[i3];
                long j3 = 0;
                boolean z = false;
                int i12 = 0;
                while (true) {
                    i5 = iArr[0];
                    if (i4 < i5 && !z) {
                        final int i13 = i12 + 1;
                        iArr2[i12] = i11;
                        j3 = (j3 * j2) + i11;
                        final int i14 = i4 + 1;
                        i11 = iArr[i4];
                        if (900 == i11 || 901 == i11 || 902 == i11 || 924 == i11 || 928 == i11 || i11 == i9 || i11 == i8) {
                            i12 = i13;
                            i8 = 922;
                            i9 = 923;
                            j2 = 900;
                            z = true;
                        } else if (0 != i13 % 5 || 0 >= i13) {
                            i4 = i14;
                            i12 = i13;
                            i8 = 922;
                            i9 = 923;
                            j2 = 900;
                        } else {
                            int i15 = 0;
                            while (6 > i15) {
                                byteArrayOutputStream.write((byte) ((int) (j3 >> ((5 - i15) * 8))));
                                i15++;
                                i8 = 922;
                                i9 = 923;
                            }
                            i4 = i14;
                            i12 = 0;
                            j2 = 900;
                            j3 = 0;
                        }
                    } else if (i4 == i5 || 900 <= i11) {
                        i6 = i12;
                    } else {
                        i6 = i12 + 1;
                        iArr2[i12] = i11;
                    }
                }
            } else if (924 == i7) {
                int i17 = i3;
                boolean z2 = false;
                int i18 = 0;
                long j4 = 0;
                while (i17 < iArr[0] && !z2) {
                    final int i19 = i17 + 1;
                    final int i20 = iArr[i17];
                    if (900 > i20) {
                        i18++;
                        j4 = (j4 * 900) + i20;
                        i17 = i19;
                    } else {
                        if (900 != i20 && 901 != i20 && 902 != i20 && 924 != i20 && i20 != i10) {
                            if (!(923 == i20 || 922 == i20)) {
                                i17 = i19;
                            }
                        }
                        z2 = true;
                    }
                    if (0 == i18 % 5 && 0 < i18) {
                        for (int i21 = 0; 6 > i21; i21++) {
                            byteArrayOutputStream.write((byte) ((int) (j4 >> ((5 - i21) * 8))));
                        }
                        i18 = 0;
                        j4 = 0;
                    }
                    i10 = 928;
                }
                i4 = i17;
            } else {
                i4 = i3;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                sb.append(byteArrayOutputStream.toString(charset));
            }
            return i4;
        }

        private static int numericCompaction(final int[] iArr, int i2, final StringBuilder sb) throws FormatException {
            final int[] iArr2 = new int[15];
            boolean z = false;
            int i3 = 0;
            while (true) {
                final int i4 = iArr[0];
                if (i2 >= i4 || z) {
                    return i2;
                }
                final int i5 = i2 + 1;
                final int i6 = iArr[i2];
                if (i5 == i4) {
                    z = true;
                }
                if (900 > i6) {
                    iArr2[i3] = i6;
                    i3++;
                } else if (900 == i6 || 901 == i6 || 924 == i6 || 928 == i6 || 923 == i6 || 922 == i6) {
                    z = true;
                }
                i2 = i5;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    sb.append(decodeBase900toBase10(iArr2, i3));
                }
                i3 = 0;
            }
        }

        private static String decodeBase900toBase10(final int[] iArr, final int i2) throws FormatException {
            BigInteger bigInteger = BigInteger.ZERO;
            for (int i3 = 0; i3 < i2; i3++) {
                bigInteger = bigInteger.add(DecodedBitStreamParser.EXP900[(i2 - i3) - 1].multiply(BigInteger.valueOf(iArr[i3])));
            }
            final String bigInteger2 = bigInteger.toString();
            if ('1' == bigInteger2.charAt(0)) {
                return bigInteger2.substring(1);
            }
            throw FormatException.getFormatInstance();
        }

        public static int textCompaction(int[] iArr, int i2, StringBuilder sb) {
            return i2;
        }
    }
}
