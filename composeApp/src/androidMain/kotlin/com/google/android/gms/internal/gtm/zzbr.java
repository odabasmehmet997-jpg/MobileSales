package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public abstract class zzbr extends zzbq {
    private boolean zza;

    protected zzbr(zzbu zzbu) {
        super(zzbu);
    }

    
    public final void zzV() {
        if (!zzX()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzW() {
        zzd();
        this.zza = true;
    }

    public final boolean zzX() {
        return this.zza;
    }

    
    public abstract void zzd();
}
