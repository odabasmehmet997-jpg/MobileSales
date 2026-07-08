package com.google.android.gms.internal.gtm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.PersistableBundle;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzcx extends zzbr {
    private boolean zza;
    private boolean zzb;
    private final AlarmManager zzc = ((AlarmManager) this.zzo().getSystemService(NotificationCompat.CATEGORY_ALARM));
    private Integer zzd;

    zzcx(final zzbu zzbu) {
        super(zzbu);
    }

    private int zzf() {
        if (null == this.zzd) {
            zzd = Integer.valueOf(("analytics" + this.zzo().getPackageName()).hashCode());
        }
        return zzd.intValue();
    }

    private PendingIntent zzg() {
        final Context zzo = this.zzo();
        return PendingIntent.getBroadcast(zzo, 0, new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH").setComponent(new ComponentName(zzo, "com.google.android.gms.analytics.AnalyticsReceiver")), zzfs.zza);
    }

    public void zza() {
        zzb = false;
        try {
            zzc.cancel(this.zzg());
        } catch (final NullPointerException unused) {
        }
        final int zzf = this.zzf();
        this.zzO("Cancelling job. JobID", Integer.valueOf(zzf));
        ((JobScheduler) this.zzo().getSystemService("jobscheduler")).cancel(zzf);
    }

    public void zzb() {
        this.zzV();
        Preconditions.checkState(zza, "Receiver not registered");
        this.zzw();
        final long zzd2 = zzcs.zzd();
        if (0 < zzd2) {
            this.zza();
            this.zzC().elapsedRealtime();
            zzb = true;
            ((Boolean) zzeh.zzF.zzb()).booleanValue();
            this.zzN("Scheduling upload with JobScheduler");
            final Context zzo = this.zzo();
            final ComponentName componentName = new ComponentName(zzo, "com.google.android.gms.analytics.AnalyticsJobService");
            final int zzf = this.zzf();
            final PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("action", "com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            final JobInfo build = new JobInfo.Builder(zzf, componentName).setMinimumLatency(zzd2).setOverrideDeadline(zzd2 + zzd2).setExtras(persistableBundle).build();
            this.zzO("Scheduling job. JobID", Integer.valueOf(zzf));
            zzft.zza(zzo, build, "com.google.android.gms", "DispatchAlarm");
        }
    }

    public boolean zzc() {
        return zza;
    }

    
    public void zzd() {
        try {
            this.zza();
            this.zzw();
            if (0 < zzcs.zzd()) {
                final Context zzo = this.zzo();
                final ActivityInfo receiverInfo = zzo.getPackageManager().getReceiverInfo(new ComponentName(zzo, "com.google.android.gms.analytics.AnalyticsReceiver"), 0);
                if (null != receiverInfo && receiverInfo.enabled) {
                    this.zzN("Receiver registered for local dispatch.");
                    zza = true;
                }
            }
        } catch (final PackageManager.NameNotFoundException unused) {
        }
    }

    public boolean zze() {
        return zzb;
    }
}
