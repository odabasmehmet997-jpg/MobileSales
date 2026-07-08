package com.google.android.gms.internal.gtm;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public abstract class zztl extends zzti implements List, RandomAccess {
    private static final zzub zza = new zztj(zztr.zza, 0);
    public static final int zzd = 0;

    zztl() {
    }

    static zztl zzg(Object[] objArr, int i2) {
        if (0 == i2) {
            return zztr.zza;
        }
        return new zztr(objArr, i2);
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
        return this.contains(obj);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof final List list) {
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    int i2 = 0;
                    while (i2 < size) {
                        if (zztc.zza(get(i2), list.get(i2))) {
                            i2++;
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it2.hasNext()) {
                            if (!zztc.zza(it.next(), it2.next())) {
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
        int size = size();
        int i2 = 1;
        for (int i3 = 0; i3 < size; i3++) {
            i2 = (i2 * 31) + get(i3).hashCode();
        }
        return i2;
    }

    public final int indexOf(Object obj) {
        if (null == obj) {
            return -1;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (obj.equals(get(i2))) {
                return i2;
            }
        }
        return -1;
    }

    public final Iterator iterator() {
        return listIterator(0);
    }

    public final int lastIndexOf(Object obj) {
        if (null == obj) {
            return -1;
        }
        for (int size = size() - 1; 0 <= size; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Deprecated
    public final Object remove(int i2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object set(int i2, Object obj) {
        throw new UnsupportedOperationException();
    }

    
    public int zza(Object[] objArr, int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            objArr[i3] = get(i3);
        }
        return size;
    }

    public final zzua zzd() {
        return listIterator(0);
    }

    /* renamed from: zzf */
    public zztl subList(int i2, int i3) {
        zztd.zzc(i2, i3, size());
        int i4 = i3 - i2;
        if (i4 == size()) {
            return this;
        }
        if (0 == i4) {
            return zztr.zza;
        }
        return new zztk(this, i2, i4);
    }

    /* renamed from: zzh */
    public final zzub listIterator(int i2) {
        zztd.zzb(i2, size(), FirebaseAnalytics.Param.INDEX);
        if (isEmpty()) {
            return zza;
        }
        return new zztj(this, i2);
    }
}
