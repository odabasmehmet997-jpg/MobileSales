package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzdy extends zzbp {
    private static final String zza = zza.REGEX_GROUP.toString();
    private static final String zzb = zzb.ARG0.toString();
    private static final String zzc = zzb.ARG1.toString();
    private static final String zzd = zzb.IGNORE_CASE.toString();
    private static final String zze = zzb.GROUP.toString();

    public zzdy() {
        super(zza, zzb, zzc);
    }

    public zzap zza(Map map) {
        Long zzj;
        zzap zzap = (zzap) map.get(zzb);
        zzap zzap2 = (zzap) map.get(zzc);
        if (zzap == null || zzap == zzfp.zza() || zzap2 == null || zzap2 == zzfp.zza()) {
            return zzfp.zza();
        }
        int i2 = 1;
        int i3 = !zzfp.zzf(zzfp.zzk((zzap) map.get(zzd))).booleanValue() ? 64 : 66;
        zzap zzap3 = (zzap) map.get(zze);
        if (zzap3 == null || ((zzj = zzfp.zzj(zzfp.zzk(zzap3))) != zzfp.zzi() && (i2 = zzj.intValue()) >= 0)) {
            try {
                Matcher matcher = Pattern.compile(zzfp.zzm(zzfp.zzk(zzap2)), i3).matcher(zzfp.zzm(zzfp.zzk(zzap)));
                String str = null;
                if (matcher.find() && matcher.groupCount() >= i2) {
                    str = matcher.group(i2);
                }
                if (str == null) {
                    return zzfp.zza();
                }
                return zzfp.zzb(str);
            } catch (PatternSyntaxException unused) {
            }
        }
        return zzfp.zza();
    }

    public boolean zzb() {
        return true;
    }
}
