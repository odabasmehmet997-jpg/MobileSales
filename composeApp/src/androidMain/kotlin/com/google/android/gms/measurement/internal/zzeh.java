package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzeh {
    final zzej zza;
    private final int zzb;
    private final boolean zzc;
    private final boolean zzd;

    zzeh(zzej zzej, int i2, boolean z, boolean z2) {
        this.zza = zzej;
        this.zzb = i2;
        this.zzc = z;
        this.zzd = z2;
    }

    public void zza(String str) {
        this.zza.zzt(this.zzb, this.zzc, this.zzd, str, null, null, null);
    }

    public void zzb(String str, Object obj) {
        this.zza.zzt(this.zzb, this.zzc, this.zzd, str, obj, null, null);
    }

    public void zzc(String str, Object obj, Object obj2) {
        this.zza.zzt(this.zzb, this.zzc, this.zzd, str, obj, obj2, null);
    }

    public void zzd(String str, Object obj, Object obj2, Object obj3) {
        this.zza.zzt(this.zzb, this.zzc, this.zzd, str, obj, obj2, obj3);
    }
}
