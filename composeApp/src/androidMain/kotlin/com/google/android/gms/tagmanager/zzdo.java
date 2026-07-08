package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzdo extends zzbp {
    private static final String zza = zza.OS_VERSION.toString();

    public zzdo() {
        super(zza);
    }

    public zzap zza(Map map) {
        return zzfp.zzb(Build.VERSION.RELEASE);
    }

    public boolean zzb() {
        return true;
    }
}
