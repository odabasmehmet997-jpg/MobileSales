package com.google.android.gms.internal.measurement;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public final class zzmn extends AbstractList implements RandomAccess, zzko {
    
    public final zzko zza;

    public zzmn(zzko zzko) {
        this.zza = zzko;
    }

    public Object get(int i2) {
        return ((zzkn) this.zza).get(i2);
    }

    public Iterator iterator() {
        return new zzmm(this);
    }

    public ListIterator listIterator(int i2) {
        return new zzml(this, i2);
    }

    public int size() {
        return this.zza.size();
    }

    public zzko zze() {
        return this;
    }

    public Object zzf(int i2) {
        return this.zza.zzf(i2);
    }

    public List zzh() {
        return this.zza.zzh();
    }

    public void zzi(zziy zziy) {
        throw new UnsupportedOperationException();
    }
}
