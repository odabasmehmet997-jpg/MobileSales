package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.MainThread;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzjt {
    private final Context zza;

    public zzjt(final Context context) {
        Preconditions.checkNotNull(context);
        zza = context;
    }

    private zzej zzk() {
        return zzft.zzp(zza, null, null).zzay();
    }

    @MainThread
    public int zza(final Intent intent, final int i2, final int i3) {
        final zzft zzp = zzft.zzp(zza, null, null);
        final zzej zzay = zzp.zzay();
        if (null == intent) {
            zzay.zzk().zza("AppMeasurementService started with null intent");
            return 2;
        }
        final String action = intent.getAction();
        zzp.zzaw();
        zzay.zzj().zzc("Local AppMeasurementService called. startId, action", Integer.valueOf(i3), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            this.zzh(new zzjp(this, i3, zzay, intent));
        }
        return 2;
    }

    @MainThread
    public IBinder zzb(final Intent intent) {
        if (null == intent) {
            this.zzk().zzd().zza("onBind called with null intent");
            return null;
        }
        final String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzgl(zzkr.zzt(zza), null);
        }
        this.zzk().zzk().zzb("onBind received unknown action", action);
        return null;
    }

    
    public void zzc(final int i2, final zzej zzej, final Intent intent) {
        if (((zzjs) zza).zzc(i2)) {
            zzej.zzj().zzb("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i2));
            this.zzk().zzj().zza("Completed wakeful intent.");
            ((zzjs) zza).zza(intent);
        }
    }

    
    public void zzd(final zzej zzej, final JobParameters jobParameters) {
        zzej.zzj().zza("AppMeasurementJobService processed last upload request.");
        ((zzjs) zza).zzb(jobParameters, false);
    }

    @MainThread
    public void zze() {
        final zzft zzp = zzft.zzp(zza, null, null);
        final zzej zzay = zzp.zzay();
        zzp.zzaw();
        zzay.zzj().zza("Local AppMeasurementService is starting up");
    }

    @MainThread
    public void zzf() {
        final zzft zzp = zzft.zzp(zza, null, null);
        final zzej zzay = zzp.zzay();
        zzp.zzaw();
        zzay.zzj().zza("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public void zzg(final Intent intent) {
        if (null == intent) {
            this.zzk().zzd().zza("onRebind called with null intent");
            return;
        }
        this.zzk().zzj().zzb("onRebind called. action", intent.getAction());
    }

    public void zzh(final Runnable runnable) {
        final zzkr zzt = zzkr.zzt(zza);
        zzt.zzaz().zzp(new zzjr(this, zzt, runnable));
    }

    @TargetApi(24)
    @MainThread
    public boolean zzi(final JobParameters jobParameters) {
        final zzft zzp = zzft.zzp(zza, null, null);
        final zzej zzay = zzp.zzay();
        final String string = jobParameters.getExtras().getString("action");
        zzp.zzaw();
        zzay.zzj().zzb("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        this.zzh(new zzjq(this, zzay, jobParameters));
        return true;
    }

    @MainThread
    public boolean zzj(final Intent intent) {
        if (null == intent) {
            this.zzk().zzd().zza("onUnbind called with null intent");
            return true;
        }
        this.zzk().zzj().zzb("onUnbind called for intent. action", intent.getAction());
        return true;
    }
}
