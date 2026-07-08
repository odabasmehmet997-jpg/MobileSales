package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzdd extends zzbp {
    private static final String zza = zza.LOWERCASE_STRING.toString();
    private static final String zzb = zzb.ARG0.toString();

    public zzdd() {
        super(zza, zzb);
    }

    public zzap zza(Map map) {
        return zzfp.zzb(zzfp.zzm(zzfp.zzk((zzap) map.get(zzb))).toLowerCase());
    }

    public boolean zzb() {
        return true;
    }
}
