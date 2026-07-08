package com.google.android.gms.common;

import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum zzd {
    ;

    static int zza(final int i2) {
        final int[] iArr = {1, 2, 3};
        int i3 = 0;
        while (3 > i3) {
            final int i4 = iArr[i3];
            final int i5 = i4 - 1;
            if (0 == i4) {
                throw null;
            } else if (i5 == i2) {
                return i4;
            } else {
                i3++;
            }
        }
        return 1;
    }
}
