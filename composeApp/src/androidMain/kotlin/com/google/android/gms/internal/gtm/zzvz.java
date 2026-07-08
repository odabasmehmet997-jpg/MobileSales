package com.google.android.gms.internal.gtm;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzvz extends AbstractSet {
    final int zza = -1;
    final zzwa zzb;

    zzvz(zzwa zzwa, int i2) {
        this.zzb = zzwa;
    }

    public boolean contains(Object obj) {
        Comparator comparator;
        final int i2 = this.zza;
        Object[] zzc = this.zzb.zzb;
        int zzb2 = zzb();
        int zza2 = zza();
        if (-1 == i2) {
            comparator = zzwa.zza;
        } else {
            comparator = zzwc.zza;
        }
        return 0 <= Arrays.binarySearch(zzc, zzb2, zza2, obj, comparator);
    }

    public Iterator iterator() {
        return new zzvy(this);
    }

    public int size() {
        return zza() - zzb();
    }

    
    public int zza() {
        return this.zzb.zzc[this.zza + 1];
    }

    
    public int zzb() {
        if (-1 == zza) {
            return 0;
        }
        return this.zzb.zzc[0];
    }
}
