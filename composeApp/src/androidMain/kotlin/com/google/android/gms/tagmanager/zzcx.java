package com.google.android.gms.tagmanager;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Locale;
import java.util.Map;

@VisibleForTesting
@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzcx extends zzbp {
    private static final String zza = zza.LANGUAGE.toString();

    public zzcx() {
        super(zza);
    }

    public zzap zza(Map map) {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return zzfp.zza();
        }
        String language = locale.getLanguage();
        if (language == null) {
            return zzfp.zza();
        }
        return zzfp.zzb(language.toLowerCase());
    }

    public boolean zzb() {
        return false;
    }
}
