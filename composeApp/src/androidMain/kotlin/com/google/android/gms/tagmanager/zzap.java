package com.google.android.gms.tagmanager;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
record zzap(String zza, @Nullable Object zzb) {

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof zzap zzap)) {
            return false;
        }
        if (!this.zza.equals(zzap.zza)) {
            return false;
        }
        Object obj2 = this.zzb;
        if (obj2 == null && zzap.zzb == null) {
            return true;
        }
        return obj2 != null && obj2.equals(zzap.zzb);
    }

    public int hashCode() {
        Preconditions.checkNotNull(this.zzb);
        return Arrays.hashCode(new Integer[]{Integer.valueOf(this.zza.hashCode()), Integer.valueOf(this.zzb.hashCode())});
    }

    public String toString() {
        String valueOf = String.valueOf(this.zzb);
        return "Key: " + this.zza + " value: " + valueOf;
    }
}
