package com.google.android.gms.internal.gtm;

import androidx.annotation.VisibleForTesting;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzrg {
    private final List zza;
    private final Map zzb;
    private final String zzc;

    zzrg(List list, Map map, String str, int i2, zzrl zzrl) {
        this.zza = Collections.unmodifiableList(list);
        this.zzb = Collections.unmodifiableMap(map);
        this.zzc = str;
    }

    public String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        return "Rules: " + valueOf + "  Macros: " + valueOf2;
    }

    public String zzb() {
        return this.zzc;
    }

    public List zzc() {
        return this.zza;
    }

    public Map zzd() {
        return this.zzb;
    }
}
