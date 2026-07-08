package com.google.android.gms.internal.gtm;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zztz extends zztp {
    final transient Object zza;

    zztz(final Object obj) {
        obj.getClass();
        zza = obj;
    }

    public boolean contains(final Object obj) {
        return zza.equals(obj);
    }

    public int hashCode() {
        return zza.hashCode();
    }

    public Iterator iterator() {
        return new zztq(zza);
    }

    public int size() {
        return 1;
    }

    public String toString() {
        final String obj = zza.toString();
        return "[" + obj + "]";
    }

    
    public int zza(final Object[] objArr, final int i2) {
        objArr[0] = zza;
        return 1;
    }

    public zzua zzd() {
        return new zztq(zza);
    }
}
