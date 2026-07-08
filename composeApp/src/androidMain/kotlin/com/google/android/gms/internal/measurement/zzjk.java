package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzjk {
    private final Object zza;
    private final int zzb;

    zzjk(Object obj, int i2) {
        this.zza = obj;
        this.zzb = i2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof final zzjk zzjk)) {
            return false;
        }
        return this.zza == zzjk.zza && this.zzb == zzjk.zzb;
    }

    public int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
