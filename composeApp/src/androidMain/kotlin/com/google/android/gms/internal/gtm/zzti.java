package com.google.android.gms.internal.gtm;

import java.io.Serializable;
import java.util.*;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public abstract class zzti extends AbstractCollection implements Serializable {
    private static final Object[] zza = new Object[0];

    zzti() {
    }

    @Deprecated
    public final boolean add(final Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(final Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean contains(Object obj);

    @Deprecated
    public final boolean remove(final Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean removeAll(final Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean retainAll(final Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final Spliterator spliterator() {
        return Spliterators.spliterator(this, 1296);
    }

    public final Object[] toArray() {
        return this.toArray(zzti.zza);
    }

    
    public int zza(final Object[] objArr, final int i2) {
        throw null;
    }

    
    public int zzb() {
        throw new UnsupportedOperationException();
    }

    
    public int zzc() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: zzd */
    public abstract zzua iterator();

    
    public Object[] zze() {
        return null;
    }

    public final Object[] toArray(Object[] objArr) {
        objArr.getClass();
        final int size = this.size();
        final int length = objArr.length;
        if (length < size) {
            final Object[] zze = this.zze();
            if (null != zze) {
                return Arrays.copyOfRange(zze, this.zzc(), this.zzb(), objArr.getClass());
            }
            if (0 != length) {
                objArr = Arrays.copyOf(objArr, 0);
            }
            objArr = Arrays.copyOf(objArr, size);
        } else if (length > size) {
            objArr[size] = null;
        }
        this.zza(objArr, 0);
        return objArr;
    }
}
