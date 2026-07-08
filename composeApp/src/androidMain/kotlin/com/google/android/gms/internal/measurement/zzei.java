package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzei extends zzjv {
    private zzei() {
        super(zzej.zza);
    }

    public int zza() {
        return ((zzej) this.zza).zza();
    }

    public zzei zzb(String str) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzej.zzi((zzej) this.zza, str);
        return this;
    }

    public zzei zzc(int i2, zzel zzel) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzej.zzj((zzej) this.zza, i2, zzel);
        return this;
    }

    public zzel zzd(int i2) {
        return ((zzej) this.zza).zze(i2);
    }

    public String zze() {
        return ((zzej) this.zza).zzg();
    }

    zzei(zzef zzef) {
        super(zzej.zza);
    }
}
