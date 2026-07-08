package com.google.android.gms.internal.maps;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzbr extends zzbm {
    private final transient zzbl zza;
    private final transient zzbi zzb;

    zzbr(final zzbl zzbl, final zzbi zzbi) {
        zza = zzbl;
        zzb = zzbi;
    }

    public boolean contains(final Object obj) {
        return null != this.zza.get(obj);
    }

    public Iterator iterator() {
        return zzb.listIterator(0);
    }

    public int size() {
        return zza.size();
    }

    
    public int zza(final Object[] objArr, final int i2) {
        return zzb.zza(objArr, 0);
    }

    public zzbx zzd() {
        return zzb.listIterator(0);
    }
}
