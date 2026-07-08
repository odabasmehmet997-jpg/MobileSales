package com.google.android.gms.internal.location;

import java.io.Serializable;
import java.util.*;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public abstract class zzeu extends AbstractCollection implements Serializable {
    private static final Object[] zza = new Object[0];

    zzeu() {
    }

    @Deprecated
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final Spliterator spliterator() {
        return Spliterators.spliterator(this, 1296);
    }

    public final Object[] toArray() {
        return toArray(zza);
    }

    /* renamed from: zza */
    public abstract zzez iterator();

    
    public Object[] zzb() {
        throw null;
    }

    
    public int zzc() {
        throw null;
    }

    
    public int zzd() {
        throw null;
    }

    public zzex zze() {
        throw null;
    }

    
    public abstract boolean zzf();

    
    public int zzg(Object[] objArr, int i2) {
        throw null;
    }

    public final Object[] toArray(Object[] objArr) {
        objArr.getClass();
        int size = size();
        int length = objArr.length;
        if (length < size) {
            Object[] zzb = zzb();
            if (null != zzb) {
                return Arrays.copyOfRange(zzb, zzc(), zzd(), objArr.getClass());
            }
            if (0 != length) {
                objArr = Arrays.copyOf(objArr, 0);
            }
            objArr = Arrays.copyOf(objArr, size);
        } else if (length > size) {
            objArr[size] = null;
        }
        zzg(objArr, 0);
        return objArr;
    }
}
