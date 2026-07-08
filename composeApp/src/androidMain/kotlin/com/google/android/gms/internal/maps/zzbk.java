package com.google.android.gms.internal.maps;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzbk {
    Object[] zza;
    int zzb;
    zzbj zzc;

    public zzbk() {
        this(4);
    }

    private void zzb(int i2) {
        Object[] objArr = this.zza;
        int length = objArr.length;
        int i3 = i2 + i2;
        if (i3 > length) {
            int i4 = length + (length >> 1) + 1;
            if (i4 < i3) {
                int highestOneBit = Integer.highestOneBit(i3 - 1);
                i4 = highestOneBit + highestOneBit;
            }
            if (0 > i4) {
                i4 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i4);
        }
    }

    public com.google.android.gms.internal.maps.zzbk zza(java.lang.Iterable r6) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.maps.zzbk.zza(java.lang.Iterable):com.google.android.gms.internal.maps.zzbk");
    }

    zzbk(int i2) {
        this.zza = new Object[(i2 + i2)];
        this.zzb = 0;
    }
}
