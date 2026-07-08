package com.google.android.gms.internal.gtm;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzadt {
    public static final int r8clinit = 0;
    private static final zzadt zzb = new zzadt();
    private final zzady zzc = new zzadd();
    private final ConcurrentMap zzd = new ConcurrentHashMap();

    private zzadt() {
    }

    public static zzadt zza() {
        return zzadt.zzb;
    }

    public zzadx zzb(final Class cls) {
        zzaco.zzc(cls, "messageType");
        zzadx zzadx = (zzadx) zzd.get(cls);
        if (null == zzadx) {
            zzadx = zzc.zza(cls);
            zzaco.zzc(cls, "messageType");
            final zzadx zzadx2 = (zzadx) zzd.putIfAbsent(cls, zzadx);
            return null == zzadx2 ? zzadx : zzadx2;
        }
    }
}
