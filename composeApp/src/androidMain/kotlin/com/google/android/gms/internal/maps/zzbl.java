package com.google.android.gms.internal.maps;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class zzbl implements Map, Serializable {
    private transient zzbm zza;
    private transient zzbm zzb;
    private transient zzbf zzc;

    zzbl() {
    }

    public static com.google.android.gms.internal.maps.zzbl zzc(final java.lang.Iterable r2) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.maps.zzbl.zzc(java.lang.Iterable):com.google.android.gms.internal.maps.zzbl");
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean containsKey(final Object obj) {
        return null != get(obj);
    }

    public final boolean containsValue(final Object obj) {
        return this.containsValue(obj);
    }

    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return this.entrySet().equals(((Map) obj).entrySet());
    }

    public abstract Object get(Object obj);

    public final Object getOrDefault(final Object obj, final Object obj2) {
        final Object obj3 = this.get(obj);
        return null != obj3 ? obj3 : obj2;
    }

    public final int hashCode() {
        return zzbv.zza(this.entrySet());
    }

    public final boolean isEmpty() {
        return 0 == size();
    }

    public final Set keySet() {
        final zzbm zzbm = zzb;
        if (null != zzbm) {
            return zzbm;
        }
        final zzbm zze = this.zze();
        zzb = zze;
        return zze;
    }

    @Deprecated
    public final Object put(final Object obj, final Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(final Map map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object remove(final Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        final int size = this.size();
        if (0 <= size) {
            final StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824));
            sb.append('{');
            boolean z = true;
            for (final Map.Entry entry : this.entrySet()) {
                if (!z) {
                    sb.append(", ");
                }
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
                z = false;
            }
            sb.append('}');
            return sb.toString();
        }
        throw new IllegalArgumentException("size cannot be negative but was: " + size);
    }

    
    public abstract zzbf zza();

    /* renamed from: zzb */
    public final zzbf values() {
        final zzbf zzbf = zzc;
        if (null != zzbf) {
            return zzbf;
        }
        final zzbf zza2 = this.zza();
        zzc = zza2;
        return zza2;
    }

    
    public abstract zzbm zzd();

    
    public abstract zzbm zze();

    /* renamed from: zzf */
    public final zzbm entrySet() {
        final zzbm zzbm = zza;
        if (null != zzbm) {
            return zzbm;
        }
        final zzbm zzd = this.zzd();
        zza = zzd;
        return zzd;
    }
}
