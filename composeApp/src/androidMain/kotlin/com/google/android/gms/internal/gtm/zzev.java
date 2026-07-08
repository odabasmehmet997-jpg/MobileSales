package com.google.android.gms.internal.gtm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.stats.WakeLock;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzev {
    static final Object zza = new Object();
    static WakeLock zzb;
    static Boolean zzc;

    public static boolean zzb(final Context context) {
        Preconditions.checkNotNull(context);
        final Boolean bool = zzev.zzc;
        if (null != bool) {
            return bool.booleanValue();
        }
        final boolean zzi = zzff.zzi(context, "com.google.android.gms.analytics.AnalyticsReceiver", false);
        zzev.zzc = Boolean.valueOf(zzi);
        return zzi;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void zza(final Context context, final Intent intent) {
        final zzbu zzg = zzbu.zzg(context);
        final zzeo zzm = zzg.zzm();
        if (null == intent) {
            zzm.zzQ("AnalyticsReceiver called with null intent");
            return;
        }
        final String action = intent.getAction();
        zzg.zzj();
        zzm.zzO("Local AnalyticsReceiver got", action);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            final boolean zzh = zzfa.zzh(context);
            final Intent intent2 = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent2.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
            intent2.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            synchronized (zzev.zza) {
                context.startService(intent2);
                if (zzh) {
                    try {
                        if (null == zzb) {
                            final WakeLock wakeLock = new WakeLock(context, 1, "Analytics WakeLock");
                            zzev.zzb = wakeLock;
                            wakeLock.setReferenceCounted(false);
                        }
                        zzev.zzb.acquire(1000);
                    } catch (final SecurityException unused) {
                        zzm.zzQ("Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                    }
                }
            }
        }
    }
}
