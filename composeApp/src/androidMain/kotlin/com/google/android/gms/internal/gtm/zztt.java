package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zztt extends zztp {
    private final transient zzto zza;
    
    public final transient Object[] zzb;
    
    public final transient int zzc = 6;

    zztt(final zzto zzto, final Object[] objArr, final int i2, final int i3) {
        zza = zzto;
        zzb = objArr;
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

    public zzua zzd() {
        return this.zzg().listIterator(0);
    }

    
    public zztl zzh() {
        return new zzts(this);
    }
}
