package com.google.android.gms.internal.gtm;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
abstract class zzyj extends AbstractList implements zzacn {
    private boolean zza;

    zzyj(final boolean z) {
        zza = z;
    }

    public void add(final int i2, final Object obj) {
        this.zza();
        super.add(i2, obj);
    }

    public final boolean addAll(final int i2, final Collection collection) {
        this.zza();
        return super.addAll(i2, collection);
    }

    public final void clear() {
        this.zza();
        super.clear();
    }

    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List list)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        final int size = this.size();
        if (size != list.size()) {
            return false;
        }
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.get(i2).equals(list.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        final int size = this.size();
        int i2 = 1;
        for (int i3 = 0; i3 < size; i3++) {
            i2 = (i2 * 31) + this.get(i3).hashCode();
        }
        return i2;
    }

    public Object remove(final int i2) {
        this.zza();
        return super.remove(i2);
    }

    public final boolean removeAll(final Collection collection) {
        this.zza();
        return super.removeAll(collection);
    }

    public final boolean retainAll(final Collection collection) {
        this.zza();
        return super.retainAll(collection);
    }

    public Object set(final int i2, final Object obj) {
        this.zza();
        return super.set(i2, obj);
    }

    
    public final void zza() {
        if (!zza) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzb() {
        if (zza) {
            zza = false;
        }
    }

    public final boolean zzc() {
        return zza;
    }

    public boolean add(final Object obj) {
        this.zza();
        return super.add(obj);
    }

    public boolean addAll(final Collection collection) {
        this.zza();
        return super.addAll(collection);
    }

    public final boolean remove(final Object obj) {
        this.zza();
        final int indexOf = this.indexOf(obj);
        if (-1 == indexOf) {
            return false;
        }
        this.remove(indexOf);
        return true;
    }
}
