package com.google.android.gms.internal.gtm;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public abstract class zzto implements Map, Serializable {
    private transient zztp zza;
    private transient zztp zzb;
    private transient zzti zzc;

    zzto() {
    }

    public static zzto zzc(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12) {
        zztg.zza(obj, obj2);
        zztg.zza(obj3, obj4);
        zztg.zza(obj5, obj6);
        zztg.zza(obj7, obj8);
        zztg.zza(obj9, obj10);
        Object obj13 = obj11;
        zztg.zza(obj13, "&cu");
        return zztw.zzg(6, new Object[]{obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj13, "&cu"}, null);
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean containsKey(Object obj) {
        return null != this.get(obj);
    }

    public final boolean containsValue(Object obj) {
        return containsValue(obj);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return entrySet().equals(((Map) obj).entrySet());
    }

    public abstract Object get(Object obj);

    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return null != obj3 ? obj3 : obj2;
    }

    public final int hashCode() {
        return zzty.zza(entrySet());
    }

    public final boolean isEmpty() {
        return false;
    }

    public final Set keySet() {
        zztp zztp = this.zzb;
        if (null != zztp) {
            return zztp;
        }
        zztp zze = zze();
        this.zzb = zze;
        return zze;
    }

    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        if (0 <= size) {
            StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824));
            sb.append('{');
            boolean z = true;
            for (Map.Entry entry : entrySet()) {
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

    
    public abstract zzti zza();

    /* renamed from: zzb */
    public final zzti values() {
        zzti zzti = this.zzc;
        if (null != zzti) {
            return zzti;
        }
        zzti zza2 = zza();
        this.zzc = zza2;
        return zza2;
    }

    
    public abstract zztp zzd();

    
    public abstract zztp zze();

    /* renamed from: zzf */
    public final zztp entrySet() {
        zztp zztp = this.zza;
        if (null != zztp) {
            return zztp;
        }
        zztp zzd = zzd();
        this.zza = zzd;
        return zzd;
    }
}
