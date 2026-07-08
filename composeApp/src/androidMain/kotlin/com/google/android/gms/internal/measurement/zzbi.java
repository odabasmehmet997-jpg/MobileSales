package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzbi extends zzaw {
    zzbi() {
        this.zza.add(zzbl.ADD);
        this.zza.add(zzbl.DIVIDE);
        this.zza.add(zzbl.MODULUS);
        this.zza.add(zzbl.MULTIPLY);
        this.zza.add(zzbl.NEGATE);
        this.zza.add(zzbl.POST_DECREMENT);
        this.zza.add(zzbl.POST_INCREMENT);
        this.zza.add(zzbl.PRE_DECREMENT);
        this.zza.add(zzbl.PRE_INCREMENT);
        this.zza.add(zzbl.SUBTRACT);
    }

    public zzap zza(String str, zzg zzg, List list) {
        final zzbl zzbl = com.google.android.gms.internal.measurement.zzbl.ADD;
        int ordinal = zzh.zze(str).ordinal();
        if (0 == ordinal) {
            zzh.zzh(com.google.android.gms.internal.measurement.zzbl.ADD.name(), 2, list);
            zzap zzb = zzg.zzb((zzap) list.get(0));
            zzap zzb2 = zzg.zzb((zzap) list.get(1));
            if (!(zzb instanceof zzal) && !(zzb instanceof zzat) && !(zzb2 instanceof zzal) && !(zzb2 instanceof zzat)) {
                return new zzah(Double.valueOf(zzb.zzh(this.zzd, 3, list).doubleValue() + zzb2.zzh(this.zzd, 3, list).doubleValue()));
            }
            String valueOf = String.valueOf(zzb.zzi());
            String valueOf2 = String.valueOf(zzb2.zzi());
            return new zzat(0 != valueOf2.length() ? valueOf + valueOf2 : valueOf);
        } else if (21 == ordinal) {
            zzh.zzh(com.google.android.gms.internal.measurement.zzbl.DIVIDE.name(), 2, list);
            return new zzah(Double.valueOf(zzg.zzb((zzap) list.get(0)).zzh(this.zzd, 3, list).doubleValue() / zzg.zzb((zzap) list.get(1)).zzh(this.zzd, 3, list).doubleValue()));
        } else if (59 == ordinal) {
            zzh.zzh(com.google.android.gms.internal.measurement.zzbl.SUBTRACT.name(), 2, list);
            return new zzah(Double.valueOf(zzg.zzb((zzap) list.get(0)).zzh(this.zzd, 3, list).doubleValue() + new zzah(Double.valueOf(-zzg.zzb((zzap) list.get(1)).zzh(this.zzd, 3, list).doubleValue())).zzh(this.zzd, 3, list).doubleValue()));
        } else if (52 == ordinal || 53 == ordinal) {
            zzh.zzh(str, 2, list);
            zzap zzb3 = zzg.zzb((zzap) list.get(0));
            zzg.zzb((zzap) list.get(1));
            return zzb3;
        } else if (55 == ordinal || 56 == ordinal) {
            zzh.zzh(str, 1, list);
            return zzg.zzb((zzap) list.get(0));
        } else {
            switch (ordinal) {
                case 44:
                    zzh.zzh(com.google.android.gms.internal.measurement.zzbl.MODULUS.name(), 2, list);
                    return new zzah(Double.valueOf(zzg.zzb((zzap) list.get(0)).zzh(this.zzd, 3, list).doubleValue() % zzg.zzb((zzap) list.get(1)).zzh(this.zzd, 3, list).doubleValue()));
                case 45:
                    zzh.zzh(com.google.android.gms.internal.measurement.zzbl.MULTIPLY.name(), 2, list);
                    return new zzah(Double.valueOf(zzg.zzb((zzap) list.get(0)).zzh(this.zzd, 3, list).doubleValue() * zzg.zzb((zzap) list.get(1)).zzh(this.zzd, 3, list).doubleValue()));
                case 46:
                    zzh.zzh(com.google.android.gms.internal.measurement.zzbl.NEGATE.name(), 1, list);
                    return new zzah(Double.valueOf(-zzg.zzb((zzap) list.get(0)).zzh(this.zzd, 3, list).doubleValue()));
                default:
                    return this.zzb(str);
            }
        }
    }
}
