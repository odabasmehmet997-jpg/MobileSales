package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzdw extends zzbp {
    private static final String zza = zza.RANDOM.toString();
    private static final String zzb = zzb.MIN.toString();
    private static final String zzc = zzb.MAX.toString();

    public zzdw() {
        super(zza);
    }

    public zzap zza(Map map) {
        zzap zzap = (zzap) map.get(zzb);
        zzap zzap2 = (zzap) map.get(zzc);
        double d2 = 0.0d;
        double d3 = 2.147483647E9d;
        if (!(zzap == null || zzap == zzfp.zza() || zzap2 == null || zzap2 == zzfp.zza())) {
            zzfo zzd = zzfp.zzd(zzfp.zzk(zzap));
            zzfo zzd2 = zzfp.zzd(zzfp.zzk(zzap2));
            if (!(zzd == zzfp.zzc() || zzd2 == zzfp.zzc())) {
                double doubleValue = zzd.doubleValue();
                double doubleValue2 = zzd2.doubleValue();
                if (doubleValue <= doubleValue2) {
                    d2 = doubleValue;
                    d3 = doubleValue2;
                }
            }
        }
        return zzfp.zzb(Long.valueOf(Math.round((Math.random() * (d3 - d2)) + d2)));
    }

    public boolean zzb() {
        return false;
    }
}
