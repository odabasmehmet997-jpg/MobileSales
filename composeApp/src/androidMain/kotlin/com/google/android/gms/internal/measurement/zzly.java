package com.google.android.gms.internal.measurement;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzly implements Map.Entry, Comparable {
    final zzme zza;
    private final Comparable zzb;
    private Object zzc;

    zzly(final zzme zzme, final Comparable comparable, final Object obj) {
        zza = zzme;
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
        return zzb.compareTo(((zzly) obj).zzb);
    }

    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry entry)) {
            return false;
        }
        return zzly.zzb(zzb, entry.getKey()) && zzly.zzb(zzc, entry.getValue());
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
        zza.zzn();
        final Object obj2 = zzc;
        zzc = obj;
        return obj2;
    }

    public String toString() {
        final String valueOf = String.valueOf(zzb);
        final String valueOf2 = String.valueOf(zzc);
        String sb = valueOf +
                "=" +
                valueOf2;
        return sb;
    }

    public Comparable zza() {
        return zzb;
    }
}
