package com.google.android.gms.internal.measurement;

import android.net.Uri;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzhi {
    private final Map zza;

    zzhi(Map map) {
        this.zza = map;
    }

    public String zza(Uri uri, String str, String str2, String str3) {
        if (null == uri) {
            return null;
        }
        Map map = (Map) this.zza.get(uri.toString());
        if (null == map) {
            return null;
        }
        String valueOf = String.valueOf(str3);
        return (String) map.get(0 != valueOf.length() ? valueOf : "");
    }
}
