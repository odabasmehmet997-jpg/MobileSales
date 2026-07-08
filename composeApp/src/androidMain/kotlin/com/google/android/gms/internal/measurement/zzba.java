package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
record zzba(zzai zza, zzg zzb) implements Comparator, Serializable {

    public int compare(final Object obj, final Object obj2) {
        final zzap zzap = (zzap) obj;
        final zzap zzap2 = (zzap) obj2;
        final zzai zzai = zza;
        final zzg zzg = zzb;
        if (zzap instanceof zzau) {
            return !(zzap2 instanceof zzau) ? 1 : 0;
        }
        if (zzap2 instanceof zzau) {
            return -1;
        }
        if (null == zzai) {
            return zzap.zzi().compareTo(zzap2.zzi());
        }
        return (int) zzh.zza(zzai.zza(zzg, Arrays.asList(zzap, zzap2)).zzh(this.zzd, 3, list).doubleValue());
    }
}
