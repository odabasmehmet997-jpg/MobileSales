package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzeg extends zzjv {
    private zzeg() {
        super(zzeh.zza);
    }

    public int zza() {
        return ((zzeh) this.zza).zzb();
    }

    public int zzb() {
        return ((zzeh) this.zza).zzc();
    }

    public zzeg zzc(int i2, zzei zzei) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzeh.zzj((zzeh) this.zza, i2, (zzej) zzei.zzay());
        return this;
    }

    public zzeg zzd(int i2, zzer zzer) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzeh.zzi((zzeh) this.zza, i2, (zzes) zzer.zzay());
        return this;
    }

    public zzej zze(int i2) {
        return ((zzeh) this.zza).zze(i2);
    }

    public zzes zzf(int i2) {
        return ((zzeh) this.zza).zzf(i2);
    }

    zzeg(zzef zzef) {
        super(zzeh.zza);
    }
}
