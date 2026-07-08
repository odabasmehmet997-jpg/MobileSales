package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzact implements Iterator {
    private final Iterator zza;

    public zzact(Iterator it) {
        this.zza = it;
    }

    public boolean hasNext() {
        return this.zza.hasNext();
    }

    public Object next() {
        Map.Entry entry = (Map.Entry) this.zza.next();
        return entry.getValue() instanceof zzacv ? new zzacs(entry, null) : entry;
    }

    public void remove() {
        this.zza.remove();
    }
}
