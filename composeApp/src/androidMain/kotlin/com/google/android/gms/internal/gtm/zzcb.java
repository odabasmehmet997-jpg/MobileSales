package com.google.android.gms.internal.gtm;

import android.content.ComponentName;
import android.os.RemoteException;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.analytics.zzr;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.Collections;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzcb extends zzbr {
    
    public final zzca zza = new zzca(this);
    private final zzcv zzb;
    private final zzfb zzc;
    private zzel zzd;

    zzcb(final zzbu zzbu) {
        super(zzbu);
        zzc = new zzfb(zzbu.zzr());
        zzb = new zzbx(this, zzbu);
    }

    static void zzb(final zzcb zzcb, final ComponentName componentName) {
        zzr.zzh();
        if (null != zzcb.zzd) {
            zzcb.zzd = null;
            zzcb.zzO("Disconnected from device AnalyticsService", componentName);
            zzcb.zzs().zzk();
        }
    }

    static void zzi(final zzcb zzcb, final zzel zzel) {
        zzr.zzh();
        zzcb.zzd = zzel;
        zzcb.zzj();
        zzcb.zzs().zzj();
    }

    private void zzj() {
        zzc.zzb();
        this.zzw();
        zzb.zzg(((Long) zzeh.zzA.zzb()).longValue());
    }

    public void zzc() {
        zzr.zzh();
        this.zzV();
        try {
            ConnectionTracker.getInstance().unbindService(this.zzo(), zza);
        } catch (final IllegalArgumentException | IllegalStateException unused) {
        }
        if (null != this.zzd) {
            zzd = null;
            this.zzs().zzk();
        }
    }

    
    public void zzd() {
    }

    public boolean zze() {
        zzr.zzh();
        this.zzV();
        final zzel zzel = zzd;
        if (null == zzel) {
            return false;
        }
        try {
            zzel.zze();
            this.zzj();
            return true;
        } catch (final RemoteException unused) {
            this.zzN("Failed to clear hits from AnalyticsService");
            return false;
        }
    }

    public boolean zzf() {
        zzr.zzh();
        this.zzV();
        if (null != this.zzd) {
            return true;
        }
        final zzel zza2 = zza.zza();
        if (null == zza2) {
            return false;
        }
        zzd = zza2;
        this.zzj();
        return true;
    }

    public boolean zzg() {
        zzr.zzh();
        this.zzV();
        return null != this.zzd;
    }

    public boolean zzh(final zzek zzek) {
        final String zzk;
        Preconditions.checkNotNull(zzek);
        zzr.zzh();
        this.zzV();
        final zzel zzel = zzd;
        if (null == zzel) {
            return false;
        }
        if (zzek.zzh()) {
            this.zzw();
            zzk = zzcs.zzi();
        } else {
            this.zzw();
            zzk = zzcs.zzk();
        }
        try {
            zzel.zzf(zzek.zzg(), zzek.zzd(), zzk, Collections.emptyList());
            this.zzj();
            return true;
        } catch (final RemoteException unused) {
            this.zzN("Failed to send hits to AnalyticsService");
            return false;
        }
    }
}
