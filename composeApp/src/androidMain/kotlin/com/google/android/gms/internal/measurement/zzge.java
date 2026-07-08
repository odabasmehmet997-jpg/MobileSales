package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzge extends zzjv {
    private zzge() {
        super(zzgf.zza);
    }

    public zzge zza(Iterable iterable) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzgf.zzh((zzgf) this.zza, iterable);
        return this;
    }

    public zzge zzb(int i2) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzgf.zzg((zzgf) this.zza, i2);
        return this;
    }

    zzge(zzff zzff) {
        super(zzgf.zza);
    }
}
