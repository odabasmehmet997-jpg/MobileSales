package com.google.android.gms.tagmanager;

import android.os.Build;
import androidx.core.os.EnvironmentCompat;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzbe extends zzbp {
    private static final String zza = zza.DEVICE_NAME.toString();

    public zzbe() {
        super(zza);
    }

    public zzap zza(Map map) {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (!str2.startsWith(str) && !str.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
            str2 = str + " " + str2;
        }
        return zzfp.zzb(str2);
    }

    public boolean zzb() {
        return true;
    }
}
