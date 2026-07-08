package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfb {
    final zzft zza;

    zzfb(zzkr zzkr) {
        this.zza = zzkr.zzq();
    }

    
    @VisibleForTesting
    public boolean zza() {
        try {
            PackageManagerWrapper packageManager = Wrappers.packageManager(this.zza.zzau());
            if (null == packageManager) {
                this.zza.zzay().zzj().zza("Failed to get PackageManager for Install Referrer Play Store compatibility check");
                return false;
            } else return 80837300 <= packageManager.getPackageInfo("com.android.vending", 128).versionCode;
        } catch (Exception e2) {
            this.zza.zzay().zzj().zzb("Failed to retrieve Play Store version for Install Referrer", e2);
            return false;
        }
    }
}
