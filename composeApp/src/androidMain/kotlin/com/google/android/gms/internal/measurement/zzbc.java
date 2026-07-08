package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzbc extends zzaw {
    zzbc() {
        this.zza.add(zzbl.AND);
        this.zza.add(zzbl.NOT);
        this.zza.add(zzbl.OR);
    }

    public zzap zza(String str, zzg zzg, List list) {
        final zzbl zzbl = com.google.android.gms.internal.measurement.zzbl.ADD;
        int ordinal = zzh.zze(str).ordinal();
        if (1 == ordinal) {
            zzh.zzh(com.google.android.gms.internal.measurement.zzbl.AND.name(), 2, list);
            zzap zzb = zzg.zzb((zzap) list.get(0));
            if (!zzb.zzg().booleanValue()) {
                return zzb;
            }
            return zzg.zzb((zzap) list.get(1));
        } else if (47 == ordinal) {
            zzh.zzh(com.google.android.gms.internal.measurement.zzbl.NOT.name(), 1, list);
            return new zzaf(Boolean.valueOf(!zzg.zzb((zzap) list.get(0)).zzg().booleanValue()));
        } else if (50 != ordinal) {
            return this.zzb(str);
        } else {
            zzh.zzh(com.google.android.gms.internal.measurement.zzbl.OR.name(), 2, list);
            zzap zzb2 = zzg.zzb((zzap) list.get(0));
            if (zzb2.zzg().booleanValue()) {
                return zzb2;
            }
            return zzg.zzb((zzap) list.get(1));
        }
    }
}
