package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
abstract class zzkh extends zzkg {
    private boolean zza;

    zzkh(final zzkr zzkr) {
        super(zzkr);
        zzf.zzL();
    }

    
    public final void zzW() {
        if (!this.zzY()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzX() {
        if (!zza) {
            this.zzb();
            zzf.zzG();
            zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    
    public final boolean zzY() {
        return zza;
    }

    
    public abstract boolean zzb();
}
