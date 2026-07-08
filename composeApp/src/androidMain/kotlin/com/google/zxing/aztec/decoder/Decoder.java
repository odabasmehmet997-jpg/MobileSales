package com.google.zxing.aztec.decoder;

import androidx.exifinterface.media.ExifInterface;
import androidx.webkit.ProxyConfig;
import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.sql.SqlLiteVariable;

import java.util.Arrays;

public final class Decoder {
    private static final String[] DIGIT_TABLE = {"CTRL_PS", " ", "0", BuildConfig.NETSIS_DEMO_PASSWORD, ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};
    private static final String[] LOWER_TABLE = {"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", SqlLiteVariable._NEW_LINE, "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "", "%", "&", "'", "(", ")", ProxyConfig.MATCH_ALL_SCHEMES, "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] UPPER_TABLE = {"CTRL_PS", " ", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C", "D", ExifInterface.LONGITUDE_EAST, "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "U", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private AztecDetectorResult ddata;

    private enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    private static int totalBitsInLayer(int i2, boolean z) {
        return ((z ? 88 : 112) + (i2 << 4)) * i2;
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.ddata = aztecDetectorResult;
        boolean[] correctBits = correctBits(extractBits(aztecDetectorResult.getBits()));
        DecoderResult decoderResult = new DecoderResult(convertBoolArrayToByteArray(correctBits), getEncodedData(correctBits), null, null);
        decoderResult.setNumBits(correctBits.length);
        return decoderResult;
    }

    private static String getEncodedData(boolean[] zArr) {
        int length = zArr.length;
        Table table = Table.UPPER;
        StringBuilder sb = new StringBuilder(20);
        Table table2 = table;
        int i2 = 0;
        while (i2 < length) {
            if (Table.BINARY == table) {
                if (5 > length - i2) {
                    break;
                }
                int readCode = readCode(zArr, i2, 5);
                int i3 = i2 + 5;
                if (0 == readCode) {
                    if (11 > length - i3) {
                        break;
                    }
                    readCode = readCode(zArr, i3, 11) + 31;
                    i3 = i2 + 16;
                }
                int i4 = 0;
                while (true) {
                    if (i4 >= readCode) {
                        i2 = i3;
                        break;
                    } else if (8 > length - i3) {
                        i2 = length;
                        break;
                    } else {
                        sb.append((char) readCode(zArr, i3, 8));
                        i3 += 8;
                        i4++;
                    }
                }
            } else {
                int i5 = Table.DIGIT == table ? 4 : 5;
                if (length - i2 < i5) {
                    break;
                }
                int readCode2 = readCode(zArr, i2, i5);
                i2 += i5;
                String character = getCharacter(table, readCode2);
                if (character.startsWith("CTRL_")) {
                    table2 = getTable(character.charAt(5));
                    if ('L' != character.charAt(6)) {
                        Table table3 = table2;
                        table2 = table;
                        table = table3;
                    }
                } else {
                    sb.append(character);
                }
            }
            table = table2;
        }
        return sb.toString();
    }

    private static Table getTable(char c2) {
        if ('B' == c2) {
            return Table.BINARY;
        }
        if ('D' == c2) {
            return Table.DIGIT;
        }
        if ('P' == c2) {
            return Table.PUNCT;
        }
        if ('L' == c2) {
            return Table.LOWER;
        }
        if ('M' != c2) {
            return Table.UPPER;
        }
        return Table.MIXED;
    }

    private static String getCharacter(Table table, int i2) {
        int i3 = AnonymousClass1.SwitchMapcomgooglezxingaztecdecoderDecoderTable[table.ordinal()];
        if (1 == i3) {
            return UPPER_TABLE[i2];
        }
        if (2 == i3) {
            return LOWER_TABLE[i2];
        }
        if (3 == i3) {
            return MIXED_TABLE[i2];
        }
        if (4 == i3) {
            return PUNCT_TABLE[i2];
        }
        if (5 == i3) {
            return DIGIT_TABLE[i2];
        }
        throw new IllegalStateException("Bad table");
    }

    private boolean[] correctBits(boolean[] zArr) throws FormatException {
        int i2;
        GenericGF genericGF;
        if (2 >= ddata.getNbLayers()) {
            genericGF = GenericGF.AZTEC_DATA_6;
            i2 = 6;
        } else {
            i2 = 8;
            if (8 >= ddata.getNbLayers()) {
                genericGF = GenericGF.AZTEC_DATA_8;
            } else if (22 >= ddata.getNbLayers()) {
                genericGF = GenericGF.AZTEC_DATA_10;
                i2 = 10;
            } else {
                genericGF = GenericGF.AZTEC_DATA_12;
                i2 = 12;
            }
        }
        int nbDatablocks = this.ddata.getNbDatablocks();
        int length = zArr.length / i2;
        if (length >= nbDatablocks) {
            int length2 = zArr.length % i2;
            int[] iArr = new int[length];
            int i3 = 0;
            while (i3 < length) {
                iArr[i3] = readCode(zArr, length2, i2);
                i3++;
                length2 += i2;
            }
            try {
                new ReedSolomonDecoder(genericGF).decode(iArr, length - nbDatablocks);
                int i4 = 1 << i2;
                int i5 = i4 - 1;
                int i6 = 0;
                for (int i7 = 0; i7 < nbDatablocks; i7++) {
                    int i8 = iArr[i7];
                    if (0 == i8 || i8 == i5) {
                        throw FormatException.getFormatInstance();
                    }
                    if (1 == i8 || i8 == i4 - 2) {
                        i6++;
                    }
                }
                boolean[] zArr2 = new boolean[((nbDatablocks * i2) - i6)];
                int i9 = 0;
                for (int i10 = 0; i10 < nbDatablocks; i10++) {
                    int i11 = iArr[i10];
                    if (1 == i11 || i11 == i4 - 2) {
                        Arrays.fill(zArr2, i9, (i9 + i2) - 1, 1 < i11);
                        i9 += i2 - 1;
                    } else {
                        int i12 = i2 - 1;
                        while (0 <= i12) {
                            int i13 = i9 + 1;
                            zArr2[i9] = 0 != ((1 << i12) & i11);
                            i12--;
                            i9 = i13;
                        }
                    }
                }
                return zArr2;
            } catch (ReedSolomonException e2) {
                throw FormatException.getFormatInstance(e2);
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private boolean[] extractBits(BitMatrix bitMatrix) {
        BitMatrix bitMatrix2 = bitMatrix;
        boolean isCompact = this.ddata.isCompact();
        int nbLayers = this.ddata.getNbLayers();
        int i2 = (isCompact ? 11 : 14) + (nbLayers << 2);
        int[] iArr = new int[i2];
        boolean[] zArr = new boolean[totalBitsInLayer(nbLayers, isCompact)];
        int i3 = 2;
        if (isCompact) {
            for (int i4 = 0; i4 < i2; i4++) {
                iArr[i4] = i4;
            }
        } else {
            int i5 = i2 / 2;
            int i6 = ((i2 + 1) + (((i5 - 1) / 15) * 2)) / 2;
            for (int i7 = 0; i7 < i5; i7++) {
                int i8 = (i7 / 15) + i7;
                iArr[(i5 - i7) - 1] = (i6 - i8) - 1;
                iArr[i5 + i7] = i8 + i6 + 1;
            }
        }
        int i9 = 0;
        int i10 = 0;
        while (i9 < nbLayers) {
            int i11 = ((nbLayers - i9) << i3) + (isCompact ? 9 : 12);
            int i12 = i9 << 1;
            int i13 = (i2 - 1) - i12;
            int i14 = 0;
            while (i14 < i11) {
                int i15 = i14 << 1;
                int i16 = 0;
                while (i16 < i3) {
                    int i17 = i12 + i16;
                    int i18 = i12 + i14;
                    zArr[i10 + i15 + i16] = bitMatrix2.get(iArr[i17], iArr[i18]);
                    int i19 = iArr[i18];
                    int i20 = i13 - i16;
                    zArr[(i11 * 2) + i10 + i15 + i16] = bitMatrix2.get(i19, iArr[i20]);
                    int i21 = i13 - i14;
                    zArr[(i11 * 4) + i10 + i15 + i16] = bitMatrix2.get(iArr[i20], iArr[i21]);
                    zArr[(i11 * 6) + i10 + i15 + i16] = bitMatrix2.get(iArr[i21], iArr[i17]);
                    i16++;
                    nbLayers = nbLayers;
                    isCompact = isCompact;
                    i3 = 2;
                }
                boolean z = isCompact;
                int i22 = nbLayers;
                i14++;
                i3 = 2;
            }
            boolean z2 = isCompact;
            int i23 = nbLayers;
            i10 += i11 << 3;
            i9++;
            i3 = 2;
        }
        return zArr;
    }

    private static int readCode(boolean[] zArr, int i2, int i3) {
        int i4 = 0;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 <<= 1;
            if (zArr[i5]) {
                i4 |= 1;
            }
        }
        return i4;
    }

    private static byte readByte(boolean[] zArr, int i2) {
        int readCode;
        int length = zArr.length - i2;
        if (8 <= length) {
            readCode = readCode(zArr, i2, 8);
        } else {
            readCode = readCode(zArr, i2, length) << (8 - length);
        }
        return (byte) readCode;
    }

    static byte[] convertBoolArrayToByteArray(boolean[] zArr) {
        int length = (zArr.length + 7) / 8;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = readByte(zArr, i2 << 3);
        }
        return bArr;
    }
}
