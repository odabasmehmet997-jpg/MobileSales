package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzadc implements zzadj {
    private final zzadj[] zza;

    zzadc(zzadj... zzadjArr) {
        this.zza = zzadjArr;
    }

    public zzadi zzb(Class cls) {
        for (int i2 = 0; 2 > i2; i2++) {
            zzadj zzadj = this.zza[i2];
            if (zzadj.zzc(cls)) {
                return zzadj.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: " + cls.getName());
    }

    public boolean zzc(Class cls) {
        for (int i2 = 0; 2 > i2; i2++) {
            if (this.zza[i2].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
