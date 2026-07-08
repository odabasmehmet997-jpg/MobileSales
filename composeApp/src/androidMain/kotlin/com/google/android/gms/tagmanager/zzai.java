package com.google.android.gms.tagmanager;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzai extends zzbp {
    private static final String zza = zza.CONTAINER_VERSION.toString();
    private final String zzb;

    public zzai(String str) {
        super(zza);
        this.zzb = str;
    }

    public zzap zza(Map map) {
        String str = this.zzb;
        return str == null ? zzfp.zza() : zzfp.zzb(str);
    }

    public boolean zzb() {
        return true;
    }
}
