package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzabp {
    private final Object zza;
    private final int zzb;

    zzabp(Object obj, int i2) {
        this.zza = obj;
        this.zzb = i2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof final zzabp zzabp)) {
            return false;
        }
        return this.zza == zzabp.zza && this.zzb == zzabp.zzb;
    }

    public int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
