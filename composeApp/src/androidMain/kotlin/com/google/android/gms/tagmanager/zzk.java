package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzk extends zzbp {
    private static final String zza = zza.APP_VERSION.toString();
    private final Context zzb;

    public zzk(Context context) {
        super(zza);
        this.zzb = context;
    }

    public zzap zza(Map map) {
        try {
            return zzfp.zzb(Integer.valueOf(this.zzb.getPackageManager().getPackageInfo(this.zzb.getPackageName(), 0).versionCode));
        } catch (PackageManager.NameNotFoundException e2) {
            String packageName = this.zzb.getPackageName();
            String message = e2.getMessage();
            Log.e("GoogleTagManager", "Package name " + packageName + " not found. " + message);
            return zzfp.zza();
        }
    }

    public boolean zzb() {
        return true;
    }
}
