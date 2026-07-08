package com.google.android.gms.internal.gtm;

import android.annotation.SuppressLint;
import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzr;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;

@SuppressLint("StaticFieldLeak")
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzbu {
    private static volatile zzbu zza;
    private final Context zzb;
    private final Context zzc;
    private final Clock zzd = DefaultClock.getInstance();
    private final zzcs zze = new zzcs(this);
    private final zzeo zzf;
    private final zzr zzg;
    private final zzbp zzh;
    private final zzcx zzi;
    private final zzfg zzj;
    private final zzeu zzk;
    private final GoogleAnalytics zzl;
    private final zzcm zzm;
    private final zzbh zzn;
    private final zzce zzo;
    private final zzcw zzp;

    private zzbu(zzbv zzbv) {
        Context zza2 = zzbv.zza();
        Preconditions.checkNotNull(zza2, "Application context can't be null");
        Context zzb2 = zzbv.zzb();
        Preconditions.checkNotNull(zzb2);
        this.zzb = zza2;
        this.zzc = zzb2;
        zzeo zzeo = new zzeo(this);
        zzeo.zzW();
        this.zzf = zzeo;
        zzeo zzm2 = zzm();
        String str = zzbs.zza;
        zzm2.zzL("Google Analytics " + str + " is starting up. To enable debug logging on a device run:\n  adb shell setprop log.tag.GAv4 DEBUG\n  adb logcat -s GAv4");
        zzeu zzeu = new zzeu(this);
        zzeu.zzW();
        this.zzk = zzeu;
        zzfg zzfg = new zzfg(this);
        zzfg.zzW();
        this.zzj = zzfg;
        zzbp zzbp = new zzbp(this, zzbv);
        zzcm zzcm = new zzcm(this);
        zzbh zzbh = new zzbh(this);
        zzce zzce = new zzce(this);
        zzcw zzcw = new zzcw(this);
        zzr zzb3 = zzr.zzb(zza2);
        zzb3.zzj(new zzbt(this));
        this.zzg = zzb3;
        GoogleAnalytics googleAnalytics = new GoogleAnalytics(this);
        zzcm.zzW();
        this.zzm = zzcm;
        zzbh.zzW();
        this.zzn = zzbh;
        zzce.zzW();
        this.zzo = zzce;
        zzcw.zzW();
        this.zzp = zzcw;
        zzcx zzcx = new zzcx(this);
        zzcx.zzW();
        this.zzi = zzcx;
        zzbp.zzW();
        this.zzh = zzbp;
        googleAnalytics.zzg();
        this.zzl = googleAnalytics;
        zzbp.zzm();
    }

    public static zzbu zzg(Context context) {
        Preconditions.checkNotNull(context);
        if (null == zzbu.zza) {
            synchronized (zzbu.class) {
                try {
                    if (null == zzbu.zza) {
                        Clock instance = DefaultClock.getInstance();
                        long elapsedRealtime = instance.elapsedRealtime();
                        zzbu zzbu = new zzbu(new zzbv(context));
                        zza = zzbu;
                        GoogleAnalytics.zzf();
                        long elapsedRealtime2 = instance.elapsedRealtime() - elapsedRealtime;
                        Long l = (Long) zzeh.zzE.zzb();
                        if (elapsedRealtime2 > l.longValue()) {
                            zzbu.zzm().zzS("Slow initialization (ms)", Long.valueOf(elapsedRealtime2), l);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return zza;
    }

    private static void zzs(zzbr zzbr) {
        Preconditions.checkNotNull(zzbr, "Analytics service not created/initialized");
        Preconditions.checkArgument(zzbr.zzX(), "Analytics service not initialized");
    }

    public Context zza() {
        return this.zzb;
    }

    public Context zzb() {
        return this.zzc;
    }

    public GoogleAnalytics zzc() {
        Preconditions.checkNotNull(this.zzl);
        Preconditions.checkArgument(this.zzl.zzj(), "Analytics instance not initialized");
        return this.zzl;
    }

    public zzr zzd() {
        Preconditions.checkNotNull(this.zzg);
        return this.zzg;
    }

    public zzbh zze() {
        zzs(this.zzn);
        return this.zzn;
    }

    public zzbp zzf() {
        zzs(this.zzh);
        return this.zzh;
    }

    public zzce zzh() {
        zzs(this.zzo);
        return this.zzo;
    }

    public zzcm zzi() {
        zzs(this.zzm);
        return this.zzm;
    }

    public zzcs zzj() {
        return this.zze;
    }

    public zzcw zzk() {
        return this.zzp;
    }

    public zzcx zzl() {
        zzs(this.zzi);
        return this.zzi;
    }

    public zzeo zzm() {
        zzs(this.zzf);
        return this.zzf;
    }

    public zzeo zzn() {
        return this.zzf;
    }

    public zzeu zzo() {
        zzs(this.zzk);
        return this.zzk;
    }

    public zzeu zzp() {
        zzeu zzeu = this.zzk;
        if (null == zzeu || !zzeu.zzX()) {
            return null;
        }
        return zzeu;
    }

    public zzfg zzq() {
        zzs(this.zzj);
        return this.zzj;
    }

    public Clock zzr() {
        return this.zzd;
    }
}
