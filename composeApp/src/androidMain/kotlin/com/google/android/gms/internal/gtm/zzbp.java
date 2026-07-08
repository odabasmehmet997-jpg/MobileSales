package com.google.android.gms.internal.gtm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.zzr;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzbp extends zzbr {
    
    public final zzcj zza;

    public zzbp(final zzbu zzbu, final zzbv zzbv) {
        super(zzbu);
        Preconditions.checkNotNull(zzbv);
        zza = new zzcj(zzbu, zzbv);
    }

    public long zza(final zzbw zzbw) {
        this.zzV();
        Preconditions.checkNotNull(zzbw);
        zzr.zzh();
        final long zzb = zza.zzb(zzbw, true);
        if (0 != zzb) {
            return zzb;
        }
        zza.zzk(zzbw);
        return 0;
    }

    public void zzc() {
        this.zzV();
        final Context zzo = this.zzo();
        if (!zzev.zzb(zzo) || !zzfa.zzh(zzo)) {
            this.zze(null);
            return;
        }
        final Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        intent.setComponent(new ComponentName(zzo, "com.google.android.gms.analytics.AnalyticsService"));
        zzo.startService(intent);
    }

    
    public void zzd() {
        zza.zzW();
    }

    public void zze(final zzcy zzcy) {
        this.zzV();
        this.zzq().zzi(new zzbn(this, zzcy));
    }

    public void zzf(final String str, final Runnable runnable) {
        Preconditions.checkNotEmpty(str, "campaign param can't be empty");
        this.zzq().zzi(new zzbj(this, str, runnable));
    }

    public void zzh(final zzek zzek) {
        Preconditions.checkNotNull(zzek);
        this.zzV();
        this.zzF("Hit delivery requested", zzek);
        this.zzq().zzi(new zzbl(this, zzek));
    }

    
    public void zzi() {
        zzr.zzh();
        zza.zzl();
    }

    
    public void zzj() {
        zzr.zzh();
        zza.zzm();
    }

    public void zzk() {
        this.zzV();
        zzr.zzh();
        zzr.zzh();
        final zzcj zzcj = zza;
        zzcj.zzV();
        zzcj.zzN("Service disconnected");
    }

    public void zzm() {
        zza.zzZ();
    }

    public boolean zzn() {
        this.zzV();
        try {
            this.zzq().zzg(new zzbo(this)).get(4, TimeUnit.SECONDS);
            return true;
        } catch (final InterruptedException e2) {
            this.zzR("syncDispatchLocalHits interrupted", e2);
            return false;
        } catch (final ExecutionException e3) {
            this.zzJ("syncDispatchLocalHits failed", e3);
            return false;
        } catch (final TimeoutException e4) {
            this.zzR("syncDispatchLocalHits timed out", e4);
            return false;
        }
    }
}
