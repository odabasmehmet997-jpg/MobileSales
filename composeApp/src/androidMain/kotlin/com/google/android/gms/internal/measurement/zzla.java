package com.google.android.gms.internal.measurement;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public final class zzla extends LinkedHashMap {
    private static final zzla zza;
    private boolean zzb = true;

    static {
        final zzla zzla = new zzla();
        zza = zzla;
        zzla.zzb = false;
    }

    private zzla() {
    }

    public static zzla zza() {
        return zzla.zza;
    }

    private static int zzf(final Object obj) {
        if (obj instanceof byte[]) {
            return zzkh.zzb((byte[]) obj);
        }
        if (!(obj instanceof zzkb)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    private void zzg() {
        if (!zzb) {
            throw new UnsupportedOperationException();
        }
    }

    public void clear() {
        this.zzg();
        super.clear();
    }

    public Set entrySet() {
        return this.isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public boolean equals(final Object obj) {
        boolean z;
        if (!(obj instanceof Map map)) {
            return false;
        }
        if (this == map) {
            return true;
        }
        if (this.size() != map.size()) {
            return false;
        }
        for (final Map.Entry entry : this.entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            final Object value = entry.getValue();
            final Object obj2 = map.get(entry.getKey());
            if (!(value instanceof byte[]) || !(obj2 instanceof byte[])) {
                z = value.equals(obj2);
                continue;
            } else {
                z = Arrays.equals((byte[]) value, (byte[]) obj2);
                continue;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i2 = 0;
        for (final Map.Entry entry : this.entrySet()) {
            i2 += zzla.zzf(entry.getValue()) ^ zzla.zzf(entry.getKey());
        }
        return i2;
    }

    public Object put(final Object obj, final Object obj2) {
        this.zzg();
        zzkh.zze(obj);
        zzkh.zze(obj2);
        return super.put(obj, obj2);
    }

    public void putAll(final Map map) {
        this.zzg();
        for (final Object next : map.keySet()) {
            zzkh.zze(next);
            zzkh.zze(map.get(next));
        }
        super.putAll(map);
    }

    public Object remove(final Object obj) {
        this.zzg();
        return super.remove(obj);
    }

    public zzla zzb() {
        return this.isEmpty() ? new zzla() : new zzla(this);
    }

    public void zzc() {
        zzb = false;
    }

    public void zzd(final zzla zzla) {
        this.zzg();
        if (!zzla.isEmpty()) {
            this.putAll(zzla);
        }
    }

    public boolean zze() {
        return zzb;
    }

    private zzla(final Map map) {
        super(map);
    }
}
