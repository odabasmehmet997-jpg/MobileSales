package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzaa {
    private String zza;
    private final long zzb;
    private final Map zzc;

    public zzaa(String str, long j2, Map map) {
        this.zza = str;
        this.zzb = j2;
        HashMap hashMap = new HashMap();
        this.zzc = hashMap;
        if (null != map) {
            hashMap.putAll(map);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof final zzaa zzaa)) {
            return false;
        }
        if (this.zzb == zzaa.zzb && this.zza.equals(zzaa.zza)) {
            return this.zzc.equals(zzaa.zzc);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.zza.hashCode();
        long j2 = this.zzb;
        return (((hashCode * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.zzc.hashCode();
    }

    public String toString() {
        String str = this.zza;
        long j2 = this.zzb;
        String obj = this.zzc.toString();
        final String sb = "Event{name='" +
                str +
                "', timestamp=" +
                j2 +
                ", params=" +
                obj +
                '}';
        return sb;
    }

    public long zza() {
        return this.zzb;
    }

    /* renamed from: zzb */
    public zzaa clone() {
        return new zzaa(this.zza, this.zzb, new HashMap(this.zzc));
    }

    public Object zzc(String str) {
        if (this.zzc.containsKey(str)) {
            return this.zzc.get(str);
        }
        return null;
    }

    public String zzd() {
        return this.zza;
    }

    public Map zze() {
        return this.zzc;
    }

    public void zzf(String str) {
        this.zza = str;
    }

    public void zzg(String str, Object obj) {
        if (null == obj) {
            this.zzc.remove(str);
        } else {
            this.zzc.put(str, obj);
        }
    }
}
