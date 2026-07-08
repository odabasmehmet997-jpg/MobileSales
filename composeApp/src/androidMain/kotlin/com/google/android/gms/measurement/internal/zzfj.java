package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzo;
import java.util.Map;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
record zzfj(zzfk zzb, String zza) implements zzo {

    public String zza(String str) {
        Map map = (Map) this.zzb.zze.get(this.zza);
        if (null == map || !map.containsKey(str)) {
            return null;
        }
        return (String) map.get(str);
    }
}
