package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzer extends zzbp {
    private static final String zza = zza.SDK_VERSION.toString();

    public zzer() {
        super(zza);
    }

    public zzap zza(Map map) {
        return zzfp.zzb(Integer.valueOf(Build.VERSION.SDK_INT));
    }

    public boolean zzb() {
        return true;
    }
}
