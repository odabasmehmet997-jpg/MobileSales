package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzri;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzek implements zzem {
    final Map zza;
    final Map zzb;
    final Map zzc;
    final Map zzd;

    zzek(zzep zzep, Map map, Map map2, Map map3, Map map4) {
        this.zza = map;
        this.zzb = map2;
        this.zzc = map3;
        this.zzd = map4;
    }

    public void zza(zzri zzri, Set set, Set set2, zzdj zzdj) {
        List list = (List) this.zza.get(zzri);
        List list2 = (List) this.zzb.get(zzri);
        if (list != null) {
            set.addAll(list);
        }
        List list3 = (List) this.zzc.get(zzri);
        List list4 = (List) this.zzd.get(zzri);
        if (list3 != null) {
            set2.addAll(list3);
        }
    }
}
