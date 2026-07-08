package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzj extends zzbp {
    private static final String zza = zza.APP_NAME.toString();
    private final Context zzb;

    public zzj(Context context) {
        super(zza);
        this.zzb = context;
    }

    public zzap zza(Map map) {
        try {
            PackageManager packageManager = this.zzb.getPackageManager();
            return zzfp.zzb(packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.zzb.getPackageName(), 0)).toString());
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e("GoogleTagManager", "App name is not found.", e2);
            return zzfp.zza();
        }
    }

    public boolean zzb() {
        return true;
    }
}
