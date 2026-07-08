package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzh extends zzbp {
    private static final String zza = zza.ADWORDS_CLICK_REFERRER.toString();
    private static final String zzb = zzb.COMPONENT.toString();
    private static final String zzc = zzb.CONVERSION_ID.toString();
    private final Context zzd;

    public zzh(Context context) {
        super(zza, zzc);
        this.zzd = context;
    }

    public zzap zza(Map map) {
        zzap zzap = (zzap) map.get(zzc);
        if (zzap == null) {
            return zzfp.zza();
        }
        String zzm = zzfp.zzm(zzfp.zzk(zzap));
        zzap zzap2 = (zzap) map.get(zzb);
        String zzm2 = zzap2 != null ? zzfp.zzm(zzfp.zzk(zzap2)) : null;
        Context context = this.zzd;
        Map map2 = zzcs.zza;
        String str = (String) map2.get(zzm);
        if (str == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            str = "";
            if (sharedPreferences != null) {
                str = sharedPreferences.getString(zzm, str);
            }
            map2.put(zzm, str);
        }
        String zza2 = zzcs.zza(str, zzm2);
        return zza2 != null ? zzfp.zzb(zza2) : zzfp.zza();
    }

    public boolean zzb() {
        return true;
    }
}
