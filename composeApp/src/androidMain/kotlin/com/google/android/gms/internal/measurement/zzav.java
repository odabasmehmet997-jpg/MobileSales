package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzav extends zzaw {
    public zzav() {
        zza.add(zzbl.BITWISE_AND);
        zza.add(zzbl.BITWISE_LEFT_SHIFT);
        zza.add(zzbl.BITWISE_NOT);
        zza.add(zzbl.BITWISE_OR);
        zza.add(zzbl.BITWISE_RIGHT_SHIFT);
        zza.add(zzbl.BITWISE_UNSIGNED_RIGHT_SHIFT);
        zza.add(zzbl.BITWISE_XOR);
    }

    public zzap zza(final String str, final zzg zzg, final List list) {
        zzbl zzbl = com.google.android.gms.internal.measurement.zzbl.ADD;
        switch (zzh.zze(str).ordinal()) {
            case 4:
                zzh.zzh(com.google.android.gms.internal.measurement.zzbl.BITWISE_AND.name(), 2, list);
                return new zzah(Double.valueOf(zzh.zzb(zzg.zzb((zzap) list.get(0)).zzh(this.zzd, 3, list).doubleValue()) & zzh.zzb(zzg.zzb((zzap) list.get(1)).zzh(this.zzd, 3, list).doubleValue())));
            case 5:
                zzh.zzh(com.google.android.gms.internal.measurement.zzbl.BITWISE_LEFT_SHIFT.name(), 2, list);
                return new zzah(Double.valueOf(zzh.zzb(zzg.zzb((zzap) list.get(0)).zzh(this.zzd, 3, list).doubleValue()) << (zzh.zzd(zzg.zzb((zzap) list.get(1)).zzh(this.zzd, 3, list).doubleValue()) & 31)));
            case 6:
                zzh.zzh(com.google.android.gms.internal.measurement.zzbl.BITWISE_NOT.name(), 1, list);
                return new zzah(Double.valueOf(~zzh.zzb(zzg.zzb((zzap) list.get(0)).zzh(this.zzd, 3, list).doubleValue())));
            case 7:
                zzh.zzh(com.google.android.gms.internal.measurement.zzbl.BITWISE_OR.name(), 2, list);
                return new zzah(Double.valueOf(zzh.zzb(zzg.zzb((zzap) list.get(0)).zzh(this.zzd, 3, list).doubleValue()) | zzh.zzb(zzg.zzb((zzap) list.get(1)).zzh(this.zzd, 3, list).doubleValue())));
            case 8:
                zzh.zzh(com.google.android.gms.internal.measurement.zzbl.BITWISE_RIGHT_SHIFT.name(), 2, list);
                return new zzah(Double.valueOf(zzh.zzb(zzg.zzb((zzap) list.get(0)).zzh(this.zzd, 3, list).doubleValue()) >> (zzh.zzd(zzg.zzb((zzap) list.get(1)).zzh(this.zzd, 3, list).doubleValue()) & 31)));
            case 9:
                zzh.zzh(com.google.android.gms.internal.measurement.zzbl.BITWISE_UNSIGNED_RIGHT_SHIFT.name(), 2, list);
                return new zzah(Double.valueOf((zzh.zzd(zzg.zzb((zzap) list.get(0)).zzh(this.zzd, 3, list).doubleValue()) >>> (zzh.zzd(zzg.zzb((zzap) list.get(1)).zzh(this.zzd, 3, list).doubleValue()) & 31))));
            case 10:
                zzh.zzh(com.google.android.gms.internal.measurement.zzbl.BITWISE_XOR.name(), 2, list);
                return new zzah(Double.valueOf(zzh.zzb(zzg.zzb((zzap) list.get(0)).zzh(this.zzd, 3, list).doubleValue()) ^ zzh.zzb(zzg.zzb((zzap) list.get(1)).zzh(this.zzd, 3, list).doubleValue())));
            default:
                return zzb(str);
        }
    }
}
