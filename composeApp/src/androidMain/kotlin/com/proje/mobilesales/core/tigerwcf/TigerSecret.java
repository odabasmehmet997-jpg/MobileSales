package com.proje.mobilesales.core.tigerwcf;

import android.content.Context;
import com.proje.mobilesales.BuildConfig;
import kotlin.internal.progressionUtil;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;


/* compiled from: TigerSecret.kt */

public final class TigerSecret {
    public static final TigerSecret INSTANCE = new TigerSecret();

    /* renamed from: C */
    private static final int[] f1214C = {247, 80, 87, 134, 116, 7, 8, 96, 255, 45, 155, 134, 164, 185, 38, 172, 95, 60, 49, 89};

    /* renamed from: M */
    private static final int[] f1215M = {164, 29, 7, 190, 71, 63, 58, 36, 189, 27, 174, 190, 229, 140, 23, 152, 109, 9, 8, 24};

    private TigerSecret() {
    }

    public static String get(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!Intrinsics.areEqual(context.getPackageName(), BuildConfig.APPLICATION_ID)) {
            throw new SecurityException();
        }
        int[] iArr = f1214C;
        int length = iArr.length;
        byte[] bArr = new byte[length];
        int length2 = iArr.length;
        for (int i2 = 0; i2 < length2; i2++) {
            bArr[i2] = (byte) (f1214C[i2] ^ f1215M[i2]);
        }
        int progressionLastElement = progressionUtil.getProgressionLastElement(0, length - 1, 2);
        if (progressionLastElement >= 0) {
            int i3 = 0;
            while (true) {
                int i4 = ((i3 * 3) + 7) % length;
                byte b2 = bArr[i3];
                bArr[i3] = bArr[i4];
                bArr[i4] = b2;
                if (i3 == progressionLastElement) {
                    break;
                }
                i3 += 2;
            }
        }
        int i5 = length - 2;
        int progressionLastElement2 = progressionUtil.getProgressionLastElement(i5, 0, -2);
        if (progressionLastElement2 <= i5) {
            while (true) {
                int i6 = ((i5 * 3) + 7) % length;
                byte b3 = bArr[i5];
                bArr[i5] = bArr[i6];
                bArr[i6] = b3;
                if (i5 == progressionLastElement2) {
                    break;
                }
                i5 -= 2;
            }
        }
        return new String(bArr, Charsets.US_ASCII);
    }
}
