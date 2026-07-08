package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
abstract class zzff extends zzdt {
    public zzff(String str) {
        super(str);
    }

    
    public abstract boolean zzc(String str, String str2, Map map);

    
    public final boolean zzd(zzap zzap, zzap zzap2, Map map) {
        String zzm = zzfp.zzm(zzfp.zzk(zzap));
        String zzm2 = zzfp.zzm(zzfp.zzk(zzap2));
        if (zzm == zzfp.zzl() || zzm2 == zzfp.zzl()) {
            return false;
        }
        return zzc(zzm, zzm2, map);
    }
}
