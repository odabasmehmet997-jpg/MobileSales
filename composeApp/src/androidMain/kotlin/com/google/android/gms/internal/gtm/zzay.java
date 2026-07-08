package com.google.android.gms.internal.gtm;

import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzay extends zzj {
    private final Map zza = new HashMap();

    public String toString() {
        return zza(zza);
    }

    public void zzc(final zzj zzj) {
        final zzay zzay = (zzay) zzj;
        Preconditions.checkNotNull(zzay);
        zzay.zza.putAll(zza);
    }

    public Map zzd() {
        return Collections.unmodifiableMap(zza);
    }

    public void zze(String str, final String str2) {
        Preconditions.checkNotEmpty(str);
        if (null != str && str.startsWith("&")) {
            str = str.substring(1);
        }
        Preconditions.checkNotEmpty(str, "Name can not be empty or \"&\"");
        zza.put(str, str2);
    }
}
