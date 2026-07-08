package com.google.android.gms.internal.gtm;

import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzur {
    private static final zzuu zza = new zzup();
    private static final zzut zzb = new zzuq();
    
    public final Map zzc = new HashMap();
    
    public final Map zzd = new HashMap();

    zzur(final zzuu zzuu, final zzuv zzuv) {
    }

    public zzuw zza() {
        return new zzus(this, null);
    }

    
    public void zzd(final zzui zzui) {
        zzwe.zza(zzui, "key");
        if (zzui.zzb()) {
            final zzut zzut = zzur.zzb;
            zzwe.zza(zzui, "key");
            if (zzui.zzb()) {
                zzc.remove(zzui);
                zzd.put(zzui, zzut);
                return;
            }
            throw new IllegalArgumentException("key must be repeating");
        }
        final zzuu zzuu = zzur.zza;
        zzwe.zza(zzui, "key");
        zzd.remove(zzui);
        zzc.put(zzui, zzuu);
    }
}
