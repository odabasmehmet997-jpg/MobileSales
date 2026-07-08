package com.google.android.gms.internal.gtm;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzadf extends LinkedHashMap {
    private static final zzadf zza;
    private boolean zzb = true;

    static {
        final zzadf zzadf = new zzadf();
        zza = zzadf;
        zzadf.zzb = false;
    }

    private zzadf() {
    }

    public static zzadf zza() {
        return zzadf.zza;
    }

    private static int zzf(final Object obj) {
        if (obj instanceof byte[] bArr) {
            final byte[] bArr2 = zzaco.zzb;
            final int length = bArr.length;
            final int zzb2 = zzaco.zzb(length, bArr, 0, length);
            if (0 == zzb2) {
                return 1;
            }
            return zzb2;
        } else if (!(obj instanceof zzach)) {
            return obj.hashCode();
        } else {
            throw new UnsupportedOperationException();
        }
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
            i2 += zzadf.zzf(entry.getValue()) ^ zzadf.zzf(entry.getKey());
        }
        return i2;
    }

    public Object put(final Object obj, final Object obj2) {
        this.zzg();
        final byte[] bArr = zzaco.zzb;
        obj.getClass();
        obj2.getClass();
        return super.put(obj, obj2);
    }

    public void putAll(final Map map) {
        this.zzg();
        for (final Object next : map.keySet()) {
            final byte[] bArr = zzaco.zzb;
            next.getClass();
            map.get(next).getClass();
        }
        super.putAll(map);
    }

    public Object remove(final Object obj) {
        this.zzg();
        return super.remove(obj);
    }

    public zzadf zzb() {
        return this.isEmpty() ? new zzadf() : new zzadf(this);
    }

    public void zzc() {
        zzb = false;
    }

    public void zzd(final zzadf zzadf) {
        this.zzg();
        if (!zzadf.isEmpty()) {
            this.putAll(zzadf);
        }
    }

    public boolean zze() {
        return zzb;
    }

    private zzadf(final Map map) {
        super(map);
    }
}
