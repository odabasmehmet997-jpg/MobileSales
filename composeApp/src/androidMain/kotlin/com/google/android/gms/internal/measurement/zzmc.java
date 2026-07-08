package com.google.android.gms.internal.measurement;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzmc extends AbstractSet {
    final zzme zza;

    zzmc(final zzme zzme, final zzmb zzmb) {
        zza = zzme;
    }

    public boolean add(final Object obj) {
        final Map.Entry entry = (Map.Entry) obj;
        if (this.contains(entry)) {
            return false;
        }
        zza.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    public void clear() {
        zza.clear();
    }

    public boolean contains(final Object obj) {
        final Map.Entry entry = (Map.Entry) obj;
        final Object obj2 = zza.get(entry.getKey());
        final Object value = entry.getValue();
        if (obj2 == value) {
            return true;
        }
        if (null != obj2) {
            return obj2.equals(value);
        }
        return false;
    }

    public Iterator iterator() {
        return new zzma(zza, null);
    }

    public boolean remove(final Object obj) {
        final Map.Entry entry = (Map.Entry) obj;
        if (!this.contains(entry)) {
            return false;
        }
        zza.remove(entry.getKey());
        return true;
    }

    public int size() {
        return zza.size();
    }
}
