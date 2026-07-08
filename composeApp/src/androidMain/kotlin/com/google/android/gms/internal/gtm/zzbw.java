package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzbw {
    private final String zza;
    private final String zzb;
    private final boolean zzc;
    private long zzd;
    private final Map zze;

    public zzbw(long j2, String str, String str2, boolean z, long j3, Map map) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = j3;
        if (null != map) {
            this.zze = new HashMap(map);
        } else {
            this.zze = Collections.emptyMap();
        }
    }

    public long zza() {
        return this.zzd;
    }

    public String zzb() {
        return this.zza;
    }

    public String zzc() {
        return this.zzb;
    }

    public Map zzd() {
        return this.zze;
    }

    public void zze(long j2) {
        this.zzd = j2;
    }

    public boolean zzf() {
        return this.zzc;
    }
}
