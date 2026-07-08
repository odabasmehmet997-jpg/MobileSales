package com.google.android.gms.internal.gtm;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.analytics.Logger;

@VisibleForTesting
@Deprecated
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzen {
    ;
    private static volatile Logger zza = new zzct();

    @SuppressLint("LogTagMismatch")
    public static void zzb(final String str, final Object obj) {
        final String str2;
        final zzeo zza2 = zzeo.zza();
        if (null != zza2) {
            zza2.zzJ(str, obj);
        } else if (zzen.zzf(3)) {
            if (null != obj) {
                str2 = str + ":" + obj;
            } else {
                str2 = str;
            }
            Log.e((String) zzeh.zzb.zzb(), str2);
        }
        final Logger logger = zzen.zza;
        if (null != logger) {
            logger.error(str);
        }
    }

    @VisibleForTesting
    public static void zzc(final Logger logger) {
        zzen.zza = logger;
    }

    @SuppressLint("LogTagMismatch")
    public static void zzd(final String str) {
        final zzeo zza2 = zzeo.zza();
        if (null != zza2) {
            zza2.zzN(str);
        } else if (zzen.zzf(0)) {
            Log.v((String) zzeh.zzb.zzb(), str);
        }
        final Logger logger = zzen.zza;
        if (null != logger) {
            logger.verbose(str);
        }
    }

    @SuppressLint("LogTagMismatch")
    public static void zze(final String str) {
        final zzeo zza2 = zzeo.zza();
        if (null != zza2) {
            zza2.zzQ(str);
        } else if (zzen.zzf(2)) {
            Log.w((String) zzeh.zzb.zzb(), str);
        }
        final Logger logger = zzen.zza;
        if (null != logger) {
            logger.warn(str);
        }
    }

    public static boolean zzf(final int i2) {
        return null != zza && zzen.zza.getLogLevel() <= i2;
    }
}
