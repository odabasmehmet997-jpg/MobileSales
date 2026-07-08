package com.google.android.gms.tagmanager;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzfl implements Logger {
    zzfl() {
    }

    public int getLogLevel() {
        int i2 = zzdc.zza;
        if (i2 == 2) {
            return 0;
        }
        if (i2 == 3 || i2 == 4) {
            return 1;
        }
        return i2 != 5 ? 3 : 2;
    }

    public void verbose(String str) {
        zzdc.zzb.zzd(str);
    }

    public void warn(String str) {
        Log.w("GoogleTagManager", str);
    }

    public void error(String str) {
        Log.e("GoogleTagManager", str);
    }
}
