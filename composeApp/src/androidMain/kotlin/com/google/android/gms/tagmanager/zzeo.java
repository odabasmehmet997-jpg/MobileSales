package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzre;
import com.google.android.gms.internal.gtm.zzri;

import java.util.*;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzeo {
    private final Set zza = new HashSet();
    private final Map zzb = new HashMap();
    private final Map zzc = new HashMap();
    private final Map zzd = new HashMap();
    private final Map zze = new HashMap();
    private zzre zzf;

    public zzre zza() {
        return this.zzf;
    }

    public Map zzb() {
        return this.zzd;
    }

    public Map zzc() {
        return this.zzb;
    }

    public Map zzd() {
        return this.zze;
    }

    public Map zze() {
        return this.zzc;
    }

    public Set zzf() {
        return this.zza;
    }

    public void zzg(zzri zzri, zzre zzre) {
        List list = (List) this.zzb.get(zzri);
        if (list == null) {
            list = new ArrayList();
            this.zzb.put(zzri, list);
        }
        list.add(zzre);
    }

    public void zzh(zzri zzri, String str) {
        List list = (List) this.zzd.get(zzri);
        if (list == null) {
            list = new ArrayList();
            this.zzd.put(zzri, list);
        }
        list.add("Unknown");
    }

    public void zzi(zzri zzri, zzre zzre) {
        List list = (List) this.zzc.get(zzri);
        if (list == null) {
            list = new ArrayList();
            this.zzc.put(zzri, list);
        }
        list.add(zzre);
    }

    public void zzj(zzri zzri, String str) {
        List list = (List) this.zze.get(zzri);
        if (list == null) {
            list = new ArrayList();
            this.zze.put(zzri, list);
        }
        list.add("Unknown");
    }

    public void zzk(zzri zzri) {
        this.zza.add(zzri);
    }

    public void zzl(zzre zzre) {
        this.zzf = zzre;
    }
}
