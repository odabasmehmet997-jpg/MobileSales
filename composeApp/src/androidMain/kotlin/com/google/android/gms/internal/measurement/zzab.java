package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzab {
    private zzaa zza;
    private zzaa zzb;
    private final List zzc;

    public zzab() {
        this.zza = new zzaa("", 0, null);
        this.zzb = new zzaa("", 0, null);
        this.zzc = new ArrayList();
    }

    public Object clone() throws CloneNotSupportedException {
        zzab zzab = new zzab(this.zza.clone());
        for (zzaa zzb2 : this.zzc) {
            zzab.zzc.add(zzb2.clone());
        }
        return zzab;
    }

    public zzaa zza() {
        return this.zza;
    }

    public zzaa zzb() {
        return this.zzb;
    }

    public List zzc() {
        return this.zzc;
    }

    public void zzd(zzaa zzaa) {
        this.zza = zzaa;
        this.zzb = zzaa.clone();
        this.zzc.clear();
    }

    public void zze(String str, long j2, Map map) {
        this.zzc.add(new zzaa(str, j2, map));
    }

    public void zzf(zzaa zzaa) {
        this.zzb = zzaa;
    }

    public zzab(zzaa zzaa) {
        this.zza = zzaa;
        this.zzb = zzaa.clone();
        this.zzc = new ArrayList();
    }
}
