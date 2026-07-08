package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/*  INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class QuantizerWsmeans {
    private static final int MAX_ITERATIONS = 10;
    private static final double MIN_MOVEMENT_DISTANCE = 3.0d;

    private QuantizerWsmeans() {
    }

    private static final class Distance implements Comparable<Distance> {
        int index = -1;
        double distance = -1.0d;

        Distance() {
        }

        @Override // java.lang.Comparable
        public int compareTo(Distance distance) {
            return Double.valueOf(this.distance).compareTo(Double.valueOf(distance.distance));
        }
    }

    public static Map<Integer, Integer> quantize(int[] iArr, int[] iArr2, int i2) {
        int[] iArr3;
        int i3;
        int i4;
        int i5;
        int i6 = 1;
        Random random = new Random(272008L);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        double[][] dArr = new double[iArr.length][];
        int[] iArr4 = new int[iArr.length];
        PointProviderLab pointProviderLab = new PointProviderLab();
        int i7 = 0;
        for (int i8 : iArr) {
            Integer num = (Integer) linkedHashMap.get(Integer.valueOf(i8));
            if (num == null) {
                dArr[i7] = pointProviderLab.fromInt(i8);
                iArr4[i7] = i8;
                i7++;
                linkedHashMap.put(Integer.valueOf(i8), 1);
            } else {
                linkedHashMap.put(Integer.valueOf(i8), Integer.valueOf(num.intValue() + 1));
            }
        }
        int[] iArr5 = new int[i7];
        for (int i9 = 0; i9 < i7; i9++) {
            iArr5[i9] = ((Integer) linkedHashMap.get(Integer.valueOf(iArr4[i9]))).intValue();
        }
        int iMin = Math.min(i2, i7);
        if (iArr2.length != 0) {
            iMin = Math.min(iMin, iArr2.length);
        }
        double[][] dArr2 = new double[iMin][];
        int i10 = 0;
        for (int i11 = 0; i11 < iArr2.length; i11++) {
            dArr2[i11] = pointProviderLab.fromInt(iArr2[i11]);
            i10++;
        }
        int i12 = iMin - i10;
        if (i12 > 0) {
            for (int i13 = 0; i13 < i12; i13++) {
            }
        }
        int[] iArr6 = new int[i7];
        for (int i14 = 0; i14 < i7; i14++) {
            iArr6[i14] = random.nextInt(iMin);
        }
        int[][] iArr7 = new int[iMin][];
        for (int i15 = 0; i15 < iMin; i15++) {
            iArr7[i15] = new int[iMin];
        }
        Distance[][] distanceArr = new Distance[iMin][];
        for (int i16 = 0; i16 < iMin; i16++) {
            distanceArr[i16] = new Distance[iMin];
            for (int i17 = 0; i17 < iMin; i17++) {
                distanceArr[i16][i17] = new Distance();
            }
        }
        int[] iArr8 = new int[iMin];
        int i18 = 0;
        while (true) {
            if (i18 >= 10) {
                iArr3 = iArr8;
                i3 = 0;
                break;
            }
            int i19 = 0;
            while (i19 < iMin) {
                int i20 = i19 + 1;
                int i21 = i20;
                while (i21 < iMin) {
                    int[] iArr9 = iArr8;
                    double dDistance = pointProviderLab.distance(dArr2[i19], dArr2[i21]);
                    Distance distance = distanceArr[i21][i19];
                    distance.distance = dDistance;
                    distance.index = i19;
                    Distance distance2 = distanceArr[i19][i21];
                    distance2.distance = dDistance;
                    distance2.index = i21;
                    i6 = 1;
                    i21++;
                    iArr8 = iArr9;
                    i18 = i18;
                }
                int[] iArr10 = iArr8;
                int i22 = i18;
                Arrays.sort(distanceArr[i19]);
                for (int i23 = 0; i23 < iMin; i23 += i6) {
                    iArr7[i19][i23] = distanceArr[i19][i23].index;
                }
                iArr8 = iArr10;
                i18 = i22;
                i19 = i20;
            }
            int[] iArr11 = iArr8;
            int i24 = i18;
            int i25 = 0;
            int i26 = 0;
            while (i25 < i7) {
                double[] dArr3 = dArr[i25];
                int i27 = iArr6[i25];
                double dDistance2 = pointProviderLab.distance(dArr3, dArr2[i27]);
                int[][] iArr12 = iArr7;
                double d2 = dDistance2;
                int i28 = -1;
                int i29 = 0;
                while (i29 < iMin) {
                    Distance[][] distanceArr2 = distanceArr;
                    int i30 = i7;
                    if (distanceArr[i27][i29].distance < 4.0d * dDistance2) {
                        double dDistance3 = pointProviderLab.distance(dArr3, dArr2[i29]);
                        if (dDistance3 < d2) {
                            d2 = dDistance3;
                            i28 = i29;
                        }
                    }
                    i29++;
                    i7 = i30;
                    distanceArr = distanceArr2;
                }
                Distance[][] distanceArr3 = distanceArr;
                int i31 = i7;
                if (i28 != -1 && Math.abs(Math.sqrt(d2) - Math.sqrt(dDistance2)) > 3.0d) {
                    i26++;
                    iArr6[i25] = i28;
                }
                i25++;
                iArr7 = iArr12;
                i7 = i31;
                distanceArr = distanceArr3;
            }
            int[][] iArr13 = iArr7;
            Distance[][] distanceArr4 = distanceArr;
            int i32 = i7;
            if (i26 == 0 && i24 != 0) {
                i3 = 0;
                iArr3 = iArr11;
                break;
            }
            double[] dArr4 = new double[iMin];
            double[] dArr5 = new double[iMin];
            double[] dArr6 = new double[iMin];
            char c2 = 0;
            Arrays.fill(iArr11, 0);
            int i33 = 0;
            while (true) {
                i4 = i32;
                if (i33 >= i4) {
                    break;
                }
                int i34 = iArr6[i33];
                double[] dArr7 = dArr[i33];
                int i35 = iArr5[i33];
                iArr11[i34] = iArr11[i34] + i35;
                double d3 = dArr4[i34];
                double d4 = dArr7[c2];
                int[] iArr14 = iArr5;
                double d5 = i35;
                dArr4[i34] = d3 + (d4 * d5);
                dArr5[i34] = dArr5[i34] + (dArr7[1] * d5);
                dArr6[i34] = dArr6[i34] + (dArr7[2] * d5);
                i33++;
                iArr5 = iArr14;
                iArr6 = iArr6;
                c2 = 0;
                i32 = i4;
            }
            int[] iArr15 = iArr5;
            int[] iArr16 = iArr6;
            int i36 = 0;
            while (i36 < iMin) {
                int i37 = iArr11[i36];
                if (i37 == 0) {
                    dArr2[i36] = new double[]{0.0d, 0.0d, 0.0d};
                    i5 = 1;
                } else {
                    double d6 = i37;
                    double d7 = dArr4[i36] / d6;
                    double d8 = dArr5[i36] / d6;
                    double d9 = dArr6[i36] / d6;
                    double[] dArr8 = dArr2[i36];
                    dArr8[0] = d7;
                    i5 = 1;
                    dArr8[1] = d8;
                    dArr8[2] = d9;
                }
                i36 += i5;
            }
            i18 = i24 + 1;
            iArr5 = iArr15;
            i6 = 1;
            iArr7 = iArr13;
            iArr6 = iArr16;
            distanceArr = distanceArr4;
            iArr8 = iArr11;
            i7 = i4;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (int i38 = i3; i38 < iMin; i38++) {
            int i39 = iArr3[i38];
            if (i39 != 0) {
                int i40 = pointProviderLab.toInt(dArr2[i38]);
                if (!linkedHashMap2.containsKey(Integer.valueOf(i40))) {
                    linkedHashMap2.put(Integer.valueOf(i40), Integer.valueOf(i39));
                }
            }
        }
        return linkedHashMap2;
    }
}
