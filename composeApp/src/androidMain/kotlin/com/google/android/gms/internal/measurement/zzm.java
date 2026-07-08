package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzm extends zzai {
    final zzo zza;

    
    zzm(final zzn zzn, final String str, final zzo zzo) {
        super("getValue");
        zza = zzo;
    }

    public zzap zza(final zzg zzg, final List list) {
        zzap.zzh.zzh("getValue", 2, list);
        final zzap zzb = zzg.zzb((zzap) list.get(0));
        final zzap zzb2 = zzg.zzb((zzap) list.get(1));
        final String zza2 = zza.zza(zzb.zzi());
        return null != zza2 ? new zzat(zza2) : zzb2;
    }
}
