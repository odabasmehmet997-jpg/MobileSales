package com.google.android.gms.internal.gtm;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzri {
    private final List zza;
    private final List zzb;
    private final List zzc;
    private final List zzd;
    private final List zze;
    private final List zzf;
    private final List zzg;
    private final List zzh;

    zzri(List list, List list2, List list3, List list4, List list5, List list6, List list7, List list8, List list9, List list10, zzrl zzrl) {
        this.zza = Collections.unmodifiableList(list);
        this.zzb = Collections.unmodifiableList(list2);
        this.zzc = Collections.unmodifiableList(list3);
        this.zzd = Collections.unmodifiableList(list4);
        this.zze = Collections.unmodifiableList(list5);
        this.zzf = Collections.unmodifiableList(list6);
        Collections.unmodifiableList(list7);
        Collections.unmodifiableList(list8);
        this.zzg = Collections.unmodifiableList(list9);
        this.zzh = Collections.unmodifiableList(list10);
    }

    public String toString() {
        List list = this.zzf;
        List list2 = this.zze;
        List list3 = this.zzd;
        List list4 = this.zzc;
        List list5 = this.zzb;
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(list5);
        String valueOf3 = String.valueOf(list4);
        String valueOf4 = String.valueOf(list3);
        String valueOf5 = String.valueOf(list2);
        String valueOf6 = String.valueOf(list);
        return "Positive predicates: " + valueOf + "  Negative predicates: " + valueOf2 + "  Add tags: " + valueOf3 + "  Remove tags: " + valueOf4 + "  Add macros: " + valueOf5 + "  Remove macros: " + valueOf6;
    }

    public List zza() {
        return this.zze;
    }

    public List zzb() {
        return this.zzg;
    }

    public List zzc() {
        return this.zzc;
    }

    public List zzd() {
        return this.zzb;
    }

    public List zze() {
        return this.zza;
    }

    public List zzf() {
        return this.zzf;
    }

    public List zzg() {
        return this.zzh;
    }

    public List zzh() {
        return this.zzd;
    }
}
