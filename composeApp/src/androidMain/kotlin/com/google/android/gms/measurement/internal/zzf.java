package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
abstract class zzf extends zze {
    private boolean zza;

    zzf(zzft zzft) {
        super(zzft);
        this.zzs.zzD();
    }

    
    public final void zza() {
        if (!zze()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzb() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzf()) {
            this.zzs.zzB();
            this.zza = true;
        }
    }

    public final void zzc() {
        if (!this.zza) {
            zzd();
            this.zzs.zzB();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    
    @WorkerThread
    public void zzd() {
    }

    
    public final boolean zze() {
        return this.zza;
    }

    
    public abstract boolean zzf();
}
