package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzcs {
    @VisibleForTesting
    static final Map zza = new HashMap();
    private static String zzb;

    public static String zza(String str, String str2) {
        if (str2 != null) {
            return Uri.parse("http://hostname/?".concat(String.valueOf(str))).getQueryParameter(str2);
        }
        if (str.length() > 0) {
            return str;
        }
        return null;
    }

    public static String zzb(Context context, String str) {
        if (zzb == null) {
            synchronized (zzcs.class) {
                try {
                    if (zzb == null) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                        if (sharedPreferences != null) {
                            zzb = sharedPreferences.getString("referrer", "");
                        } else {
                            zzb = "";
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return zza(zzb, str);
    }

    public static void zzc(Context context, String str) {
        String zza2 = zza(str, "conv");
        if (zza2 != null && zza2.length() > 0) {
            zza.put(zza2, str);
            zzfb.zza(context, "gtm_click_referrers", zza2, str);
        }
    }

    public static void zzd(String str) {
        synchronized (zzcs.class) {
            zzb = str;
        }
    }
}
