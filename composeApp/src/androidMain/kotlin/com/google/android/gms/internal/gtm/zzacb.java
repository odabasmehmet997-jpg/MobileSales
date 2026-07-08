package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public class zzacb extends zzaca {
    protected zzacb(final zzacc zzacc) {
        super(zzacc);
    }

    
    public final void zzH() {
        super.zzH();
        if (((zzacc) zza).zza != zzabv.zze()) {
            final zzacc zzacc = (zzacc) zza;
            zzacc.zza = zzacc.zza.clone();
        }
    }

    /* renamed from: zzJ */
    public final zzacc zzE() {
        if (!zza.zzar()) {
            return (zzacc) zza;
        }
        ((zzacc) zza).zza.zzi();
        return (zzacc) super.zzE();
    }
}
