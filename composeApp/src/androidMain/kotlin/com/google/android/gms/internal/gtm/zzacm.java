package com.google.android.gms.internal.gtm;

import java.util.AbstractList;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzacm extends AbstractList {
    private final zzack zza;

    public zzacm(final zzack zzack, final zzacl zzacl) {
        zza = zzack;
    }

    public Object get(final int i2) {
        final zzam zzb = zzam.zzb(zza.zze(i2));
        return null == zzb ? zzam.ESCAPE_HTML : zzb;
    }

    public int size() {
        return zza.size();
    }
}
