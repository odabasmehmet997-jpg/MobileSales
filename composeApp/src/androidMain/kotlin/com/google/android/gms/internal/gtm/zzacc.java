package com.google.android.gms.internal.gtm;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public abstract class zzacc extends zzacf {
    protected zzabv zza = zzabv.zze();

    private final void zzc(final zzace zzace) {
        if (zzace.zza != this.zzb(6, null, null)) {
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        }
    }

    
    public final zzabv zzU() {
        if (zza.zzl()) {
            zza = zza.clone();
        }
        return zza;
    }

    public final Object zzV(final zzabo zzabo) {
        final zzace zzace = (zzace) zzabo;
        this.zzc(zzace);
        final Object zzf = zza.zzf(zzace.zzd);
        if (null == zzf) {
            return zzace.zzb;
        }
        final zzacd zzacd = zzace.zzd;
        if (!zzacd.zzd) {
            return zzace.zza(zzf);
        }
        if (zzaey.ENUM != zzacd.zzc.zza()) {
            return zzf;
        }
        final zzadu zzadu = new zzadu();
        final List<Object> list = (List) zzf;
        zzadu.zzf(list.size());
        for (final Object zza2 : list) {
            zzadu.add(zzace.zza(zza2));
        }
        zzadu.zzb();
        return zzadu;
    }

    public final boolean zzW(final zzabo zzabo) {
        final zzace zzace = (zzace) zzabo;
        this.zzc(zzace);
        final zzabv zzabv = zza;
        final zzacd zzacd = zzace.zzd;
        if (!zzacd.zzd) {
            return null != zzabv.zza.get(zzacd);
        }
        throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
    }
}
