package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzcr extends zzbp {
    private static final String zza = zza.INSTALL_REFERRER.toString();
    private static final String zzb = zzb.COMPONENT.toString();
    private final Context zzc;

    public zzcr(Context context) {
        super(zza);
        this.zzc = context;
    }

    public zzap zza(Map map) {
        String str = zzb;
        String zzb2 = zzcs.zzb(this.zzc, map.get(str) != null ? zzfp.zzm(zzfp.zzk((zzap) map.get(str))) : null);
        return zzb2 != null ? zzfp.zzb(zzb2) : zzfp.zza();
    }

    public boolean zzb() {
        return true;
    }
}
