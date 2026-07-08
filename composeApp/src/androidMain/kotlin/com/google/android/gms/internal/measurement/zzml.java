package com.google.android.gms.internal.measurement;

import java.util.ListIterator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzml implements ListIterator {
    final ListIterator zza;
    final int zzb;
    final zzmn zzc;

    zzml(zzmn zzmn, int i2) {
        this.zzc = zzmn;
        this.zzb = i2;
        this.zza = zzmn.zza.listIterator(i2);
    }

    public void add(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    public boolean hasNext() {
        return this.zza.hasNext();
    }

    public boolean hasPrevious() {
        return this.zza.hasPrevious();
    }

    public Object next() {
        return this.zza.next();
    }

    public int nextIndex() {
        return this.zza.nextIndex();
    }

    public Object previous() {
        return this.zza.previous();
    }

    public int previousIndex() {
        return this.zza.previousIndex();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void set(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }
}
