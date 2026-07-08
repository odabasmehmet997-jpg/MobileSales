package com.google.android.gms.internal.gtm;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzaeb implements Map.Entry, Comparable {
    final zzaef zza;
    private final Comparable zzb;
    private Object zzc;

    zzaeb(final zzaef zzaef, final Comparable comparable, final Object obj) {
        zza = zzaef;
        zzb = comparable;
        zzc = obj;
    }

    private static boolean zzb(final Object obj, final Object obj2) {
        if (null == obj) {
            return null == obj2;
        }
        return obj.equals(obj2);
    }

    public int compareTo(final Object obj) {
        return zzb.compareTo(((zzaeb) obj).zzb);
    }

    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry entry)) {
            return false;
        }
        return zzaeb.zzb(zzb, entry.getKey()) && zzaeb.zzb(zzc, entry.getValue());
    }

    public Object getKey() {
        return zzb;
    }

    public Object getValue() {
        return zzc;
    }

    public int hashCode() {
        final Comparable comparable = zzb;
        int i2 = 0;
        final int hashCode = null == comparable ? 0 : comparable.hashCode();
        final Object obj = zzc;
        if (null != obj) {
            i2 = obj.hashCode();
        }
        return hashCode ^ i2;
    }

    public Object setValue(final Object obj) {
        zza.zzo();
        final Object obj2 = zzc;
        zzc = obj;
        return obj2;
    }

    public String toString() {
        final String valueOf = String.valueOf(zzb);
        final String valueOf2 = String.valueOf(zzc);
        return valueOf + "=" + valueOf2;
    }

    public Comparable zza() {
        return zzb;
    }
}
