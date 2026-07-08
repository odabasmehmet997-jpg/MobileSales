package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzao extends zzai {
    private final List zza;
    private final List zzb;
    private final zzg zzc;

    private zzao(zzao zzao) {
        super(zzao.zzd);
        ArrayList arrayList = new ArrayList(zzao.zza.size());
        this.zza = arrayList;
        arrayList.addAll(zzao.zza);
        ArrayList arrayList2 = new ArrayList(zzao.zzb.size());
        this.zzb = arrayList2;
        arrayList2.addAll(zzao.zzb);
        this.zzc = zzao.zzc;
    }

    public zzap zza(zzg zzg, List list) {
        zzg zza2 = this.zzc.zza();
        for (int i2 = 0; i2 < this.zza.size(); i2++) {
            if (i2 < list.size()) {
                zza2.zze((String) this.zza.get(i2), zzg.zzb((zzap) list.get(i2)));
            } else {
                zza2.zze((String) this.zza.get(i2), zzap.zzf);
            }
        }
        for (zzap zzap : this.zzb) {
            zzap zzb2 = zza2.zzb(zzap);
            if (zzb2 instanceof zzaq) {
                zzb2 = zza2.zzb(zzap);
            }
            if (zzb2 instanceof zzag) {
                return ((zzag) zzb2).zzb();
            }
        }
        return zzap.zzf;
    }

    public zzap zzd() {
        return new zzao(this);
    }

    public zzao(String str, List list, List list2, zzg zzg) {
        super(str);
        this.zza = new ArrayList();
        this.zzc = zzg;
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                this.zza.add(((zzap) it.next()).zzi());
            }
        }
        this.zzb = new ArrayList(list2);
    }
}
