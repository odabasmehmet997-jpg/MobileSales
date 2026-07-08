package com.google.zxing.aztec.encoder;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public enum HighLevelEncoder {
    ;
    private static final int[][] CHAR_MAP;
    static final int[][] LATCH_TABLE = {new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};
    static final String[] MODE_NAMES = {"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
    static final int[][] SHIFT_TABLE;

    static {
        int[] iArr = new int[2];
        iArr[1] = 256;
        iArr[0] = 5;
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        CHAR_MAP = iArr2;
        iArr2[0][32] = 1;
        for (int i2 = 65; 90 >= i2; i2++) {
            CHAR_MAP[0][i2] = i2 - 63;
        }
        CHAR_MAP[1][32] = 1;
        for (int i3 = 97; 122 >= i3; i3++) {
            CHAR_MAP[1][i3] = i3 - 95;
        }
        CHAR_MAP[2][32] = 1;
        for (int i4 = 48; 57 >= i4; i4++) {
            CHAR_MAP[2][i4] = i4 - 46;
        }
        int[] iArr3 = CHAR_MAP[2];
        iArr3[44] = 12;
        iArr3[46] = 13;
        int[] iArr4 = {0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, 126, 127};
        for (int i5 = 0; 28 > i5; i5++) {
            CHAR_MAP[3][iArr4[i5]] = i5;
        }
        int[] iArr5 = {0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (int i6 = 0; 31 > i6; i6++) {
            int i7 = iArr5[i6];
            if (0 < i7) {
                CHAR_MAP[4][i7] = i6;
            }
        }
        int[] iArr6 = new int[2];
        iArr6[1] = 6;
        iArr6[0] = 6;
        int[][] iArr7 = (int[][]) Array.newInstance(Integer.TYPE, iArr6);
        SHIFT_TABLE = iArr7;
        for (int[] fill : iArr7) {
            Arrays.fill(fill, -1);
        }
        int[][] iArr8 = SHIFT_TABLE;
        iArr8[0][4] = 0;
        int[] iArr9 = iArr8[1];
        iArr9[4] = 0;
        iArr9[0] = 28;
        iArr8[3][4] = 0;
        int[] iArr10 = iArr8[2];
        iArr10[4] = 0;
        iArr10[0] = 15;
    }

    /* renamed from: com.google.zxing.aztec.encoder.HighLevelEncoder1  reason: invalid class name */
    class AnonymousClass1 implements Comparator<State>, Serializable {
        public int compare(State state, State state2) {
            return state.getBitCount() - state2.getBitCount();
        }
    }
}
