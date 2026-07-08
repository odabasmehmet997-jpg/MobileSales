package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
class zzaa extends zzab {
    Object[] zza = new Object[4];
    int zzb;
    boolean zzc;

    zzaa(int i2) {
    }

    @CanIgnoreReturnValue
    public final zzaa zza(Object obj) {
        obj.getClass();
        int i2 = this.zzb;
        int i3 = i2 + 1;
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (length < i3) {
            int i4 = length + (length >> 1) + 1;
            if (i4 < i3) {
                int highestOneBit = Integer.highestOneBit(i2);
                i4 = highestOneBit + highestOneBit;
            }
            if (0 > i4) {
                i4 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i4);
            this.zzc = false;
        } else if (this.zzc) {
            this.zza = objArr.clone();
            this.zzc = false;
        }
        Object[] objArr2 = this.zza;
        int i5 = this.zzb;
        this.zzb = i5 + 1;
        objArr2[i5] = obj;
        return this;
    }
}
