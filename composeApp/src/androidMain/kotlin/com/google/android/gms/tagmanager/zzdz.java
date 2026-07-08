package com.google.android.gms.tagmanager;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzdz extends zzff {
    private static final String zza = zza.REGEX.toString();
    private static final String zzb = zzb.IGNORE_CASE.toString();

    public zzdz() {
        super(zza);
    }

    
    public boolean zzc(String str, String str2, Map map) {
        try {
            return Pattern.compile(str2, !zzfp.zzf(zzfp.zzk((zzap) map.get(zzb))).booleanValue() ? 64 : 66).matcher(str).find();
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }
}
