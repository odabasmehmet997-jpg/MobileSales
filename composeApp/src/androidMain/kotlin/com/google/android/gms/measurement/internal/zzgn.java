package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
abstract class zzgn extends zzgm {
    private boolean zza;

    zzgn(zzft zzft) {
        super(zzft);
        this.zzs.zzD();
    }

    
    public void zzaA() {
    }

    
    public abstract boolean zzf();

    
    public final void zzu() {
        if (!zzx()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzv() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzf()) {
            this.zzs.zzB();
            this.zza = true;
        }
    }

    public final void zzw() {
        if (!this.zza) {
            zzaA();
            this.zzs.zzB();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    
    public final boolean zzx() {
        return this.zza;
    }
}
