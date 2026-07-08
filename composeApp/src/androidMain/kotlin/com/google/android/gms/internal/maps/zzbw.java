package com.google.android.gms.internal.maps;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzbw extends zzbm {
    final transient Object zza;

    zzbw(Object obj) {
        obj.getClass();
        this.zza = obj;
    }

    public boolean contains(Object obj) {
        return this.zza.equals(obj);
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public Iterator iterator() {
        return new zzbn(this.zza);
    }

    public int size() {
        return 1;
    }

    public String toString() {
        String obj = this.zza.toString();
        return "[" + obj + "]";
    }

    
    public int zza(Object[] objArr, int i2) {
        objArr[0] = this.zza;
        return 1;
    }

    public zzbx zzd() {
        return new zzbn(this.zza);
    }
}
