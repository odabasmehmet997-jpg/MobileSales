package com.google.android.gms.internal.maps;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class zzbi extends zzbf implements List, RandomAccess {
    private static final zzby zza = new zzbg(zzbo.zza, 0);
    public static final int zzd = 0;

    zzbi() {
    }

    static zzbi zzg(Object[] objArr, int i2) {
        if (0 == i2) {
            return zzbo.zza;
        }
        return new zzbo(objArr, i2);
    }

    @Deprecated
    public final void add(int i2, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(int i2, Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean contains(Object obj) {
        return 0 <= indexOf(obj);
    }

    public final boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List list) {
            final int size = this.size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    int i2 = 0;
                    while (i2 < size) {
                        if (zzaz.zza(this.get(i2), list.get(i2))) {
                            i2++;
                        }
                    }
                    return true;
                }
                final Iterator it = this.iterator();
                final Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it2.hasNext()) {
                            if (!zzaz.zza(it.next(), it2.next())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        final int size = this.size();
        int i2 = 1;
        for (int i3 = 0; i3 < size; i3++) {
            i2 = (i2 * 31) + this.get(i3).hashCode();
        }
        return i2;
    }

    public final int indexOf(final Object obj) {
        if (null == obj) {
            return -1;
        }
        final int size = this.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (obj.equals(this.get(i2))) {
                return i2;
            }
        }
        return -1;
    }

    public final Iterator iterator() {
        return this.listIterator(0);
    }

    public final int lastIndexOf(final Object obj) {
        if (null == obj) {
            return -1;
        }
        for (int size = this.size() - 1; 0 <= size; size--) {
            if (obj.equals(this.get(size))) {
                return size;
            }
        }
        return -1;
    }

    public final ListIterator listIterator() {
        return this.listIterator(0);
    }

    @Deprecated
    public final Object remove(final int i2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object set(final int i2, final Object obj) {
        throw new UnsupportedOperationException();
    }

    
    public int zza(final Object[] objArr, final int i2) {
        final int size = this.size();
        for (int i3 = 0; i3 < size; i3++) {
            objArr[i3] = this.get(i3);
        }
        return size;
    }

    public final zzbx zzd() {
        return this.listIterator(0);
    }

    /* renamed from: zzf */
    public zzbi subList(final int i2, final int i3) {
        zzba.zzc(i2, i3, this.size());
        final int i4 = i3 - i2;
        if (i4 == this.size()) {
            return this;
        }
        if (0 == i4) {
            return zzbo.zza;
        }
        return new zzbh(this, i2, i4);
    }

    /* renamed from: zzh */
    public final zzby listIterator(final int i2) {
        zzba.zzb(i2, this.size(), FirebaseAnalytics.Param.INDEX);
        if (this.isEmpty()) {
            return zzbi.zza;
        }
        return new zzbg(this, i2);
    }
}
