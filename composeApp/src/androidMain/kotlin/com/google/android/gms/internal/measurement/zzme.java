package com.google.android.gms.internal.measurement;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
class zzme extends AbstractMap {
    private final int zza;
    
    public List zzb = Collections.emptyList();
    
    public Map zzc = Collections.emptyMap();
    private boolean zzd;
    private volatile zzmc zze;
    private Map zzf = Collections.emptyMap();

    zzme(final int i2, final zzmd zzmd) {
        zza = i2;
    }

    private final int zzk(final Comparable comparable) {
        final int size = zzb.size();
        int i2 = size - 1;
        int i3 = 0;
        if (0 <= i2) {
            final int compareTo = comparable.compareTo(((zzly) zzb.get(i2)).zza());
            if (0 < compareTo) {
                return -(size + 1);
            }
            if (0 == compareTo) {
                return i2;
            }
        }
        while (i3 <= i2) {
            final int i4 = (i3 + i2) / 2;
            final int compareTo2 = comparable.compareTo(((zzly) zzb.get(i4)).zza());
            if (0 > compareTo2) {
                i2 = i4 - 1;
            } else if (0 >= compareTo2) {
                return i4;
            } else {
                i3 = i4 + 1;
            }
        }
        return -(i3 + 1);
    }

    
    public final Object zzl(final int i2) {
        this.zzn();
        final Object value = ((zzly) zzb.remove(i2)).getValue();
        if (!zzc.isEmpty()) {
            final Iterator it = this.zzm().entrySet().iterator();
            final List list = zzb;
            final Map.Entry entry = (Map.Entry) it.next();
            list.add(new zzly(this, (Comparable) entry.getKey(), entry.getValue()));
            it.remove();
        }
        return value;
    }

    private final SortedMap zzm() {
        this.zzn();
        if (zzc.isEmpty() && !(zzc instanceof TreeMap)) {
            final TreeMap treeMap = new TreeMap();
            zzc = treeMap;
            zzf = treeMap.descendingMap();
        }
        return (SortedMap) zzc;
    }

    
    public final void zzn() {
        if (zzd) {
            throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        this.zzn();
        if (!zzb.isEmpty()) {
            zzb.clear();
        }
        if (!zzc.isEmpty()) {
            zzc.clear();
        }
    }

    public final boolean containsKey(final Object obj) {
        final Comparable comparable = (Comparable) obj;
        return 0 <= zzk(comparable) || zzc.containsKey(comparable);
    }

    public final Set entrySet() {
        if (null == this.zze) {
            zze = new zzmc(this, null);
        }
        return zze;
    }

    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzme zzme)) {
            return super.equals(obj);
        }
        final int size = this.size();
        if (size != zzme.size()) {
            return false;
        }
        final int zzb2 = this.zzb();
        if (zzb2 != zzme.zzb()) {
            return this.entrySet().equals(zzme.entrySet());
        }
        for (int i2 = 0; i2 < zzb2; i2++) {
            if (!this.zzg(i2).equals(zzme.zzg(i2))) {
                return false;
            }
        }
        if (zzb2 != size) {
            return zzc.equals(zzme.zzc);
        }
        return true;
    }

    public final Object get(final Object obj) {
        final Comparable comparable = (Comparable) obj;
        final int zzk = this.zzk(comparable);
        if (0 <= zzk) {
            return ((zzly) zzb.get(zzk)).getValue();
        }
        return zzc.get(comparable);
    }

    public final int hashCode() {
        final int zzb2 = this.zzb();
        int i2 = 0;
        for (int i3 = 0; i3 < zzb2; i3++) {
            i2 += zzb.get(i3).hashCode();
        }
        return 0 < this.zzc.size() ? i2 + zzc.hashCode() : i2;
    }

    public final Object remove(final Object obj) {
        this.zzn();
        final Comparable comparable = (Comparable) obj;
        final int zzk = this.zzk(comparable);
        if (0 <= zzk) {
            return this.zzl(zzk);
        }
        if (zzc.isEmpty()) {
            return null;
        }
        return zzc.remove(comparable);
    }

    public final int size() {
        return zzb.size() + zzc.size();
    }

    public void zza() {
        final Map map;
        final Map map2;
        if (!zzd) {
            if (zzc.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(zzc);
            }
            zzc = map;
            if (zzf.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(zzf);
            }
            zzf = map2;
            zzd = true;
        }
    }

    public final int zzb() {
        return zzb.size();
    }

    public final Iterable zzc() {
        if (zzc.isEmpty()) {
            return zzlx.zza();
        }
        return zzc.entrySet();
    }

    /* renamed from: zze */
    public final Object put(final Comparable comparable, final Object obj) {
        this.zzn();
        final int zzk = this.zzk(comparable);
        if (0 <= zzk) {
            return ((zzly) zzb.get(zzk)).setValue(obj);
        }
        this.zzn();
        if (zzb.isEmpty() && !(zzb instanceof ArrayList)) {
            zzb = new ArrayList(zza);
        }
        final int i2 = -(zzk + 1);
        if (i2 >= zza) {
            return this.zzm().put(comparable, obj);
        }
        final int size = zzb.size();
        final int i3 = zza;
        if (size == i3) {
            final zzly zzly = (zzly) zzb.remove(i3 - 1);
            this.zzm().put(zzly.zza(), zzly.getValue());
        }
        zzb.add(i2, new zzly(this, comparable, obj));
        return null;
    }

    public final Map.Entry zzg(final int i2) {
        return (Map.Entry) zzb.get(i2);
    }

    public final boolean zzj() {
        return zzd;
    }
}
