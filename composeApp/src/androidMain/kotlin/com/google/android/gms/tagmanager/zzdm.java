package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
abstract class zzdm extends zzdt {
    public zzdm(String str) {
        super(str);
    }

    
    public abstract boolean zzc(zzfo zzfo, zzfo zzfo2, Map map);

    
    public final boolean zzd(zzap zzap, zzap zzap2, Map map) {
        zzfo zzd = zzfp.zzd(zzfp.zzk(zzap));
        zzfo zzd2 = zzfp.zzd(zzfp.zzk(zzap2));
        if (zzd == zzfp.zzc() || zzd2 == zzfp.zzc()) {
            return false;
        }
        return zzc(zzd, zzd2, map);
    }
}
