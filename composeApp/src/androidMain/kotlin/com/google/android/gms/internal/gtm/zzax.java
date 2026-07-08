package com.google.android.gms.internal.gtm;

import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzax extends zzj {
    private final Map zza = new HashMap(4);

    public String toString() {
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : this.zza.entrySet()) {
            String valueOf = String.valueOf(entry.getKey());
            hashMap.put("metric" + valueOf, entry.getValue());
        }
        return zzj.zza(hashMap);
    }

    public void zzc(zzj zzj) {
        ((zzax) zzj).zza.putAll(this.zza);
    }

    public Map zzd() {
        return Collections.unmodifiableMap(this.zza);
    }
}
