package com.google.android.gms.measurement.internal;

import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public record zzff(zzfk zza, String zzb) implements Callable {
    /* synthetic */

    public Object call() {
        zzfk zzfk = this.zza;
        String str = this.zzb;
        zzg zzj = zzfk.zzf.zzi().zzj(str);
        HashMap hashMap = new HashMap();
        hashMap.put("platform", "android");
        hashMap.put("package_name", str);
        zzfk.zzs.zzf().zzh();
        hashMap.put("gmp_version", 60000L);
        if (null != zzj) {
            String zzw = zzj.zzw();
            if (null != zzw) {
                hashMap.put("app_version", zzw);
            }
            hashMap.put("app_version_int", Long.valueOf(zzj.zzb()));
            hashMap.put("dynamite_version", Long.valueOf(zzj.zzk()));
        }
        return hashMap;
    }
}
