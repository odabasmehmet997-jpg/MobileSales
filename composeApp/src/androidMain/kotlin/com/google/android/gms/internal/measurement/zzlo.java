package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzlo {
    private static final zzlo zza = new zzlo();
    private final zzls zzb = new zzky();
    private final ConcurrentMap zzc = new ConcurrentHashMap();

    private zzlo() {
    }

    public static zzlo zza() {
        return zza;
    }

    public zzlr zzb(Class cls) {
        zzkh.zzf(cls, "messageType");
        zzlr zzlr = (zzlr) this.zzc.get(cls);
        if (null == zzlr) {
            zzlr = this.zzb.zza(cls);
            zzkh.zzf(cls, "messageType");
            zzkh.zzf(zzlr, "schema");
            zzlr zzlr2 = (zzlr) this.zzc.putIfAbsent(cls, zzlr);
            return null == zzlr2 ? zzlr : zzlr2;
        }
    }
}
