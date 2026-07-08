package com.google.android.gms.internal.gtm;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zztu extends zztp {
    private final transient zzto zza;
    private final transient zztl zzb;

    zztu(zzto zzto, zztl zztl) {
        this.zza = zzto;
        this.zzb = zztl;
    }

    public boolean contains(Object obj) {
        return null != zza.get(obj);
    }

    public Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    public int size() {
        return 6;
    }

    
    public int zza(Object[] objArr, int i2) {
        return this.zzb.zza(objArr, 0);
    }

    public zzua zzd() {
        return this.zzb.listIterator(0);
    }
}
