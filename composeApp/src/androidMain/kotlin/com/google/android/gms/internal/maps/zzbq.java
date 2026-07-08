package com.google.android.gms.internal.maps;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzbq extends zzbm {
    private final transient zzbl zza;
    
    public final transient Object[] zzb;
    
    public final transient int zzc;

    zzbq(final zzbl zzbl, final Object[] objArr, final int i2, final int i3) {
        zza = zzbl;
        zzb = objArr;
        zzc = i3;
    }

    public boolean contains(final Object obj) {
        if (obj instanceof Map.Entry entry) {
            final Object key = entry.getKey();
            final Object value = entry.getValue();
            return null != value && value.equals(zza.get(key));
        }
        return false;
    }

    public Iterator iterator() {
        return this.zzg().listIterator(0);
    }

    public int size() {
        return zzc;
    }

    
    public int zza(final Object[] objArr, final int i2) {
        return this.zzg().zza(objArr, 0);
    }

    public zzbx zzd() {
        return this.zzg().listIterator(0);
    }

    
    public zzbi zzh() {
        return new zzbp(this);
    }
}
