package com.google.android.gms.internal.gtm;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.HttpUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.BuildConfig;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzff {
    ;

    public static long zza(String str) {
        if (null == str) {
            return 0;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static zzav zzb(zzeo zzeo, String str) {
        Preconditions.checkNotNull(zzeo);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        new HashMap();
        try {
            Map<String, String> parse = HttpUtils.parse(new URI("?" + str), "UTF-8");
            zzav zzav = new zzav();
            zzav.zzp(parse.get("utm_content"));
            zzav.zzu(parse.get("utm_medium"));
            zzav.zzv(parse.get("utm_campaign"));
            zzav.zzw(parse.get("utm_source"));
            zzav.zzt(parse.get("utm_term"));
            zzav.zzs(parse.get("utm_id"));
            zzav.zzo(parse.get("anid"));
            zzav.zzr(parse.get("gclid"));
            zzav.zzq(parse.get("dclid"));
            zzav.zzn(parse.get(FirebaseAnalytics.Param.ACLID));
            return zzav;
        } catch (URISyntaxException e2) {
            zzeo.zzR("No valid campaign data found", e2);
            return null;
        }
    }

    public static String zzc(boolean z) {
        return !z ? "0" : BuildConfig.NETSIS_DEMO_PASSWORD;
    }

    public static String zzd(Locale locale) {
        if (null == locale) {
            return null;
        }
        String language = locale.getLanguage();
        if (TextUtils.isEmpty(language)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(language.toLowerCase(locale));
        if (!TextUtils.isEmpty(locale.getCountry())) {
            sb.append("-");
            sb.append(locale.getCountry().toLowerCase(locale));
        }
        return sb.toString();
    }

    public static MessageDigest zze(String str) {
        int i2 = 0;
        while (2 > i2) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                if (null != instance) {
                    return instance;
                }
                i2++;
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }

    public static void zzg(Map map, String str, String str2) {
        if (null != str2 && !map.containsKey(str)) {
            map.put(str, str2);
        }
    }

    public static void zzh(Map map, String str, Map map2) {
        zzg(map, str, (String) map2.get(str));
    }

    public static boolean zzi(Context context, String str, boolean z) {
        try {
            ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, str), 0);
            return null != receiverInfo && receiverInfo.enabled && (!z || receiverInfo.exported);
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public static boolean zzj(double d2, String str) {
        int i2;
        if (0.0d < d2 && 100.0d > d2) {
            if (!TextUtils.isEmpty(str)) {
                i2 = 0;
                for (int length = str.length() - 1; 0 <= length; length--) {
                    char charAt = str.charAt(length);
                    i2 = ((i2 << 6) & 65535) + charAt + (charAt << 14);
                    int i3 = 266338304 & i2;
                    if (0 != i3) {
                        i2 ^= i3 >> 21;
                    }
                }
            } else {
                i2 = 1;
            }
            return (i2 % 10000) >= d2 * 100.0d;
        }
        return false;
    }
}
