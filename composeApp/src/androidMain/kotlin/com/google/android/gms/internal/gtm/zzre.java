package com.google.android.gms.internal.gtm;

import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzre {
    private final Map zza;
    private final zzap zzb;

    zzre(Map map, zzap zzap, zzrl zzrl) {
        this.zza = map;
        this.zzb = zzap;
    }

    public String toString() {
        String valueOf = String.valueOf(Collections.unmodifiableMap(this.zza));
        String valueOf2 = String.valueOf(this.zzb);
        return "Properties: " + valueOf + " pushAfterEvaluate: " + valueOf2;
    }

    public zzap zza() {
        return this.zzb;
    }

    public Map zzc() {
        return Collections.unmodifiableMap(this.zza);
    }

    public void zzd(String str, zzap zzap) {
        this.zza.put(str, zzap);
    }
}
