package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzfr extends zzbp {
    private static final String zza = zza.UPPERCASE_STRING.toString();
    private static final String zzb = zzb.ARG0.toString();

    public zzfr() {
        super(zza, zzb);
    }

    public zzap zza(Map map) {
        return zzfp.zzb(zzfp.zzm(zzfp.zzk((zzap) map.get(zzb))).toUpperCase());
    }

    public boolean zzb() {
        return true;
    }
}
