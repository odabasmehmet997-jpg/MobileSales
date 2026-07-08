package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzfj;
import com.google.android.gms.internal.measurement.zzfk;
import com.google.android.gms.internal.measurement.zzfl;
import com.google.android.gms.internal.measurement.zzfm;
import com.google.android.gms.internal.measurement.zzgc;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzge;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzoh;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzt {
    final zzz zza;
    private final String zzb;
    private final boolean zzc;
    private zzgd zzd;
    
    public BitSet zze;
    private final BitSet zzf;
    private final Map zzg;
    private final Map zzh;

    zzt(zzz zzz, String str, zzs zzs) {
        this.zza = zzz;
        this.zzb = str;
        this.zzc = true;
        this.zze = new BitSet();
        this.zzf = new BitSet();
        this.zzg = new ArrayMap();
        this.zzh = new ArrayMap();
    }

    
    @NonNull
    public zzfk zza(int i2) {
        ArrayList arrayList;
        List list;
        zzfj zzb2 = zzfk.zzb();
        zzb2.zza(i2);
        zzb2.zzc(this.zzc);
        zzgd zzgd = this.zzd;
        if (null != zzgd) {
            zzb2.zzd(zzgd);
        }
        zzgc zzf2 = com.google.android.gms.internal.measurement.zzgd.zzf();
        zzf2.zzb(zzkt.zzr(this.zze));
        zzf2.zzd(zzkt.zzr(this.zzf));
        Map map = this.zzg;
        if (null == map) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(map.size());
            for (Integer num : this.zzg.keySet()) {
                int intValue = num.intValue();
                Long l = (Long) this.zzg.get(num);
                if (null != l) {
                    zzfl zzc2 = zzfm.zzc();
                    zzc2.zzb(intValue);
                    zzc2.zza(l.longValue());
                    arrayList2.add(zzc2.zzay());
                }
            }
            arrayList = arrayList2;
        }
        if (null != arrayList) {
            zzf2.zza(arrayList);
        }
        Map map2 = this.zzh;
        if (null == map2) {
            list = Collections.emptyList();
        } else {
            ArrayList arrayList3 = new ArrayList(map2.size());
            for (Integer num2 : this.zzh.keySet()) {
                zzge zzd2 = zzgf.zzd();
                zzd2.zzb(num2.intValue());
                List list2 = (List) this.zzh.get(num2);
                if (null != list2) {
                    Collections.sort(list2);
                    zzd2.zza(list2);
                }
                arrayList3.add(zzd2.zzay());
            }
            list = arrayList3;
        }
        zzf2.zzc(list);
        zzb2.zzb(zzf2);
        return (zzfk) zzb2.zzay();
    }

    
    public void zzc(@NonNull zzx zzx) {
        int zza2 = zzx.zza();
        Boolean bool = zzx.zzd;
        if (null != bool) {
            this.zzf.set(zza2, bool.booleanValue());
        }
        Boolean bool2 = zzx.zze;
        if (null != bool2) {
            this.zze.set(zza2, bool2.booleanValue());
        }
        if (null != zzx.zzf) {
            Map map = this.zzg;
            Integer valueOf = Integer.valueOf(zza2);
            Long l = (Long) map.get(valueOf);
            long longValue = zzx.zzf.longValue() / 1000;
            if (null == l || longValue > l.longValue()) {
                this.zzg.put(valueOf, Long.valueOf(longValue));
            }
        }
        if (null != zzx.zzg) {
            Map map2 = this.zzh;
            Integer valueOf2 = Integer.valueOf(zza2);
            List list = (List) map2.get(valueOf2);
            if (null == list) {
                list = new ArrayList();
                this.zzh.put(valueOf2, list);
            }
            if (zzx.zzc()) {
                list.clear();
            }
            zzoh.zzc();
            zzaf zzf2 = this.zza.zzs.zzf();
            String str = this.zzb;
            zzdv zzdv = zzdw.zzW;
            if (zzf2.zzs(str, zzdv) && zzx.zzb()) {
                list.clear();
            }
            zzoh.zzc();
            if (this.zza.zzs.zzf().zzs(this.zzb, zzdv)) {
                Long valueOf3 = Long.valueOf(zzx.zzg.longValue() / 1000);
                if (!list.contains(valueOf3)) {
                    list.add(valueOf3);
                    return;
                }
                return;
            }
            list.add(Long.valueOf(zzx.zzg.longValue() / 1000));
        }
    }

    zzt(zzz zzz, String str, zzgd zzgd, BitSet bitSet, BitSet bitSet2, Map map, Map map2, zzs zzs) {
        this.zza = zzz;
        this.zzb = str;
        this.zze = bitSet;
        this.zzf = bitSet2;
        this.zzg = map;
        this.zzh = new ArrayMap();
        for (Integer num : map2.keySet()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(map2.get(num));
            this.zzh.put(num, arrayList);
        }
        this.zzc = false;
        this.zzd = zzgd;
    }
}
