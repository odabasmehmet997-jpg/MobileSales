package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzap;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
abstract class zzbp {
    private final Set zzr;
    private final String zzs;

    public zzbp(String str, String... strArr) {
        this.zzs = str;
        this.zzr = new HashSet(strArr.length);
        Collections.addAll(this.zzr, strArr);
    }

    public abstract zzap zza(Map map);

    public abstract boolean zzb();

    public final String zze() {
        return this.zzs;
    }

    public final Set zzf() {
        return this.zzr;
    }

    
    public final boolean zzg(Set set) {
        return set.containsAll(this.zzr);
    }
}
