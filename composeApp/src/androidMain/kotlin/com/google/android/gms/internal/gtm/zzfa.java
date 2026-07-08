package com.google.android.gms.internal.gtm;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.stats.WakeLock;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzfa {
    private static Boolean zza;
    
    public final Handler zzb = new zzfy();
    private final Context zzc;

    public zzfa(final Context context) {
        Preconditions.checkNotNull(context);
        zzc = context;
    }

    public static boolean zzh(final Context context) {
        Preconditions.checkNotNull(context);
        final Boolean bool = zzfa.zza;
        if (null != bool) {
            return bool.booleanValue();
        }
        boolean z = false;
        try {
            final ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"), 0);
            if (null != serviceInfo && serviceInfo.enabled) {
                z = true;
            }
        } catch (final PackageManager.NameNotFoundException unused) {
        }
        zzfa.zza = Boolean.valueOf(z);
        return z;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public int zza(final Intent intent, final int i2, final int i3) {
        try {
            synchronized (zzev.zza) {
                final WakeLock wakeLock = zzev.zzb;
                if (null != wakeLock && wakeLock.isHeld()) {
                    wakeLock.release();
                }
            }
        } catch (final SecurityException unused) {
        } catch (final Throwable th) {
            throw th;
        }
        final zzbu zzg = zzbu.zzg(zzc);
        final zzeo zzm = zzg.zzm();
        if (null == intent) {
            zzm.zzQ("AnalyticsService started with null intent");
            return 2;
        }
        final String action = intent.getAction();
        zzg.zzj();
        zzm.zzP("Local AnalyticsService called. startId, action", Integer.valueOf(i3), action);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            this.zzg(new zzew(this, i3, zzm));
        }
        return 2;
    }

    
    public void zzc(final int i2, final zzeo zzeo) {
        if (((zzez) zzc).callServiceStopSelfResult(i2)) {
            zzeo.zzN("Local AnalyticsService processed last dispatch request");
        }
    }

    
    public void zzd(final zzeo zzeo, final JobParameters jobParameters) {
        zzeo.zzN("AnalyticsJobService processed last dispatch request");
        ((zzez) zzc).zza(jobParameters, false);
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void zze() {
        final zzbu zzg = zzbu.zzg(zzc);
        final zzeo zzm = zzg.zzm();
        zzg.zzj();
        zzm.zzN("Local AnalyticsService is starting up");
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void zzf() {
        final zzbu zzg = zzbu.zzg(zzc);
        final zzeo zzm = zzg.zzm();
        zzg.zzj();
        zzm.zzN("Local AnalyticsService is shutting down");
    }

    public void zzg(final Runnable runnable) {
        zzbu.zzg(zzc).zzf().zze(new zzey(this, runnable));
    }

    @TargetApi(24)
    public boolean zzi(final JobParameters jobParameters) {
        final zzbu zzg = zzbu.zzg(zzc);
        final zzeo zzm = zzg.zzm();
        final String string = jobParameters.getExtras().getString("action");
        zzg.zzj();
        zzm.zzO("Local AnalyticsJobService called. action", string);
        if (!"com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(string)) {
            return true;
        }
        this.zzg(new zzex(this, zzm, jobParameters));
        return true;
    }
}
