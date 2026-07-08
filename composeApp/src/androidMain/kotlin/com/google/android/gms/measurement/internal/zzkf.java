package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzbt;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzkf extends zzkh {
    private final AlarmManager zza = ((AlarmManager) zzs.zzau().getSystemService(NotificationCompat.CATEGORY_ALARM));
    private zzan zzb;
    private Integer zzc;

    zzkf(final zzkr zzkr) {
        super(zzkr);
    }

    private int zzf() {
        if (null == this.zzc) {
            final String valueOf = String.valueOf(zzs.zzau().getPackageName());
            zzc = Integer.valueOf((0 != valueOf.length() ? "measurement" + valueOf : "measurement").hashCode());
        }
        return zzc.intValue();
    }

    private PendingIntent zzh() {
        final Context zzau = zzs.zzau();
        return zzbs.zza(zzau, 0, new Intent().setClassName(zzau, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), zzbs.zza);
    }

    private zzan zzi() {
        if (null == this.zzb) {
            zzb = new zzke(this, zzf.zzq());
        }
        return zzb;
    }

    @TargetApi(24)
    private void zzj() {
        final JobScheduler jobScheduler = (JobScheduler) zzs.zzau().getSystemService("jobscheduler");
        if (null != jobScheduler) {
            jobScheduler.cancel(this.zzf());
        }
    }

    public void zza() {
        this.zzW();
        zzs.zzay().zzj().zza("Unscheduling upload");
        final AlarmManager alarmManager = zza;
        if (null != alarmManager) {
            alarmManager.cancel(this.zzh());
        }
        this.zzi().zzb();
        this.zzj();
    }

    
    public boolean zzb() {
        final AlarmManager alarmManager = zza;
        if (null != alarmManager) {
            alarmManager.cancel(this.zzh());
        }
        this.zzj();
        return false;
    }

    public void zzd(final long j2) {
        this.zzW();
        zzs.zzaw();
        final Context zzau = zzs.zzau();
        if (!zzky.zzai(zzau)) {
            zzs.zzay().zzc().zza("Receiver not registered/enabled");
        }
        if (!zzky.zzaj(zzau, false)) {
            zzs.zzay().zzc().zza("Service not registered/enabled");
        }
        this.zza();
        zzs.zzay().zzj().zzb("Scheduling upload, millis", Long.valueOf(j2));
        zzs.zzav().elapsedRealtime();
        zzs.zzf();
        if (j2 < Math.max(0, ((Long) zzdw.zzw.zza(null)).longValue()) && !this.zzi().zze()) {
            this.zzi().zzd(j2);
        }
        zzs.zzaw();
        final Context zzau2 = zzs.zzau();
        final ComponentName componentName = new ComponentName(zzau2, "com.google.android.gms.measurement.AppMeasurementJobService");
        final int zzf = this.zzf();
        final PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
        zzbt.zza(zzau2, new JobInfo.Builder(zzf, componentName).setMinimumLatency(j2).setOverrideDeadline(j2 + j2).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
    }
}
