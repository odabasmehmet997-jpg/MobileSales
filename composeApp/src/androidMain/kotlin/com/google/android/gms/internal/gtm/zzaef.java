package com.google.android.gms.internal.gtm;

import java.util.*;
class zzaef extends AbstractMap {
    public Object[] zza;
    public int zzb;
    public Map zzc = Collections.emptyMap();
    private boolean zzd;
    private volatile zzaed zze;
    private Map zzf = Collections.emptyMap();

    private zzaef() {
    }

    private final int zzl(Comparable comparable) {
        int i2 = this.zzb;
        int i3 = i2 - 1;
        int i4 = 0;
        if (0 <= i3) {
            int compareTo = comparable.compareTo(((zzaeb) this.zza[i3]).zza());
            if (0 < compareTo) {
                return -(i2 + 1);
            }
            if (0 == compareTo) {
                return i3;
            }
        }
        while (i4 <= i3) {
            int i5 = (i4 + i3) / 2;
            int compareTo2 = comparable.compareTo(((zzaeb) this.zza[i5]).zza());
            if (0 > compareTo2) {
                i3 = i5 - 1;
            } else if (0 >= compareTo2) {
                return i5;
            } else {
                i4 = i5 + 1;
            }
        }
        return -(i4 + 1);
    }

    
    public final Object zzm(int i2) {
        zzo();
        Object value = ((zzaeb) this.zza[i2]).getValue();
        Object[] objArr = this.zza;
        System.arraycopy(objArr, i2 + 1, objArr, i2, (this.zzb - i2) - 1);
        this.zzb--;
        if (!this.zzc.isEmpty()) {
            Iterator it = zzn().entrySet().iterator();
            Object[] objArr2 = this.zza;
            int i3 = this.zzb;
            Map.Entry entry = (Map.Entry) it.next();
            objArr2[i3] = new zzaeb(this, (Comparable) entry.getKey(), entry.getValue());
            this.zzb++;
            it.remove();
        }
        return value;
    }

    private final SortedMap zzn() {
        zzo();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzc = treeMap;
            this.zzf = treeMap.descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    public final void zzo() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        zzo();
        if (0 != zzb) {
            this.zza = null;
            this.zzb = 0;
        }
        if (!this.zzc.isEmpty()) {
            this.zzc.clear();
        }
    }

    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return 0 <= this.zzl(comparable) || this.zzc.containsKey(comparable);
    }

    public final Set entrySet() {
        if (null == zze) {
            this.zze = new zzaed(this, null);
        }
        return this.zze;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof final zzaef zzaef)) {
            return super.equals(obj);
        }
        int size = size();
        if (size != zzaef.size()) {
            return false;
        }
        int i2 = this.zzb;
        if (i2 != zzaef.zzb) {
            return entrySet().equals(zzaef.entrySet());
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (!zzg(i3).equals(zzaef.zzg(i3))) {
                return false;
            }
        }
        if (i2 != size) {
            return this.zzc.equals(zzaef.zzc);
        }
        return true;
    }

    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zzl = zzl(comparable);
        if (0 <= zzl) {
            return ((zzaeb) this.zza[zzl]).getValue();
        }
        return this.zzc.get(comparable);
    }

    public final int hashCode() {
        int i2 = this.zzb;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += this.zza[i4].hashCode();
        }
        return 0 < zzc.size() ? i3 + this.zzc.hashCode() : i3;
    }

    public final Object remove(Object obj) {
        zzo();
        Comparable comparable = (Comparable) obj;
        int zzl = zzl(comparable);
        if (0 <= zzl) {
            return zzm(zzl);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    public final int size() {
        return this.zzb + this.zzc.size();
    }

    public void zza() {
        Map map;
        Map map2;
        if (!this.zzd) {
            if (this.zzc.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.zzc);
            }
            this.zzc = map;
            if (this.zzf.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(this.zzf);
            }
            this.zzf = map2;
            this.zzd = true;
        }
    }

    public final int zzc() {
        return this.zzb;
    }

    public final Iterable zzd() {
        if (this.zzc.isEmpty()) {
            return Collections.emptySet();
        }
        return this.zzc.entrySet();
    }

    /* renamed from: zzf */
    public final Object put(Comparable comparable, Object obj) {
        zzo();
        int zzl = zzl(comparable);
        if (0 <= zzl) {
            return ((zzaeb) this.zza[zzl]).setValue(obj);
        }
        zzo();
        if (null == zza) {
            this.zza = new Object[16];
        }
        int i2 = -(zzl + 1);
        if (16 <= i2) {
            return zzn().put(comparable, obj);
        }
        if (16 == zzb) {
            zzaeb zzaeb = (zzaeb) this.zza[15];
            this.zzb = 15;
            zzn().put(zzaeb.zza(), zzaeb.getValue());
        }
        Object[] objArr = this.zza;
        int length = objArr.length;
        System.arraycopy(objArr, i2, objArr, i2 + 1, 15 - i2);
        this.zza[i2] = new zzaeb(this, comparable, obj);
        this.zzb++;
        return null;
    }

    public final Map.Entry zzg(int i2) {
        if (i2 < this.zzb) {
            return (zzaeb) this.zza[i2];
        }
        throw new ArrayIndexOutOfBoundsException(i2);
    }

    public final boolean zzj() {
        return this.zzd;
    }

    zzaef(zzaee zzaee) {
    }
}
