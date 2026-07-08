package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzcl;
import com.google.android.gms.internal.measurement.zzhu;
import com.google.android.gms.internal.measurement.zzob;
import com.google.firebase.messaging.Constants;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzft implements zzgo {
    private static volatile zzft zzd;
    private zzea zzA;
    private boolean zzB;
    private Boolean zzC;
    private long zzD;
    private volatile Boolean zzE;
    private volatile boolean zzF;
    private int zzG;
    private final AtomicInteger zzH = new AtomicInteger(0);
    @VisibleForTesting
    private Boolean zza;
    @VisibleForTesting
    private Boolean zzb;
    @VisibleForTesting
    final long zzc;
    private final Context zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final boolean zzi;
    private final zzaa zzj;
    private final zzaf zzk;
    private final zzey zzl;
    private final zzej zzm;
    private final zzfq zzn;
    private final zzkc zzo;
    private final zzky zzp;
    private final zzee zzq;
    private final Clock zzr;
    private final zzim zzs;
    private final zzhy zzt;
    private final zzd zzu;
    private final zzic zzv;
    private final String zzw;
    private zzec zzx;
    private zzjm zzy;
    private zzao zzz;

    zzft(zzgw zzgw) {
        long j2;
        Bundle bundle;
        boolean z = false;
        Preconditions.checkNotNull(zzgw);
        Context context = zzgw.zza;
        zzaa zzaa = new zzaa(context);
        this.zzj = zzaa;
        zzdt.zza = zzaa;
        this.zze = context;
        this.zzf = zzgw.zzb;
        this.zzg = zzgw.zzc;
        this.zzh = zzgw.zzd;
        this.zzi = zzgw.zzh;
        this.zzE = zzgw.zze;
        this.zzw = zzgw.zzj;
        this.zzF = true;
        zzcl zzcl = zzgw.zzg;
        if (!(null == zzcl || null == (bundle = zzcl.zzg))) {
            Object obj = bundle.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zza = (Boolean) obj;
            }
            Object obj2 = zzcl.zzg.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzb = (Boolean) obj2;
            }
        }
        zzhu.zze(context);
        Clock instance = DefaultClock.getInstance();
        this.zzr = instance;
        Long l = zzgw.zzi;
        if (null != l) {
            j2 = l.longValue();
        } else {
            j2 = instance.currentTimeMillis();
        }
        this.zzc = j2;
        this.zzk = new zzaf(this);
        zzey zzey = new zzey(this);
        zzey.zzv();
        this.zzl = zzey;
        zzej zzej = new zzej(this);
        zzej.zzv();
        this.zzm = zzej;
        zzky zzky = new zzky(this);
        zzky.zzv();
        this.zzp = zzky;
        this.zzq = new zzee(new zzgv(zzgw, this));
        this.zzu = new zzd(this);
        zzim zzim = new zzim(this);
        zzim.zzb();
        this.zzs = zzim;
        zzhy zzhy = new zzhy(this);
        zzhy.zzb();
        this.zzt = zzhy;
        zzkc zzkc = new zzkc(this);
        zzkc.zzb();
        this.zzo = zzkc;
        zzic zzic = new zzic(this);
        zzic.zzv();
        this.zzv = zzic;
        zzfq zzfq = new zzfq(this);
        zzfq.zzv();
        this.zzn = zzfq;
        zzcl zzcl2 = zzgw.zzg;
        z = null == zzcl2 || 0 == zzcl2.zzb || z;
        if (context.getApplicationContext() instanceof Application) {
            zzhy zzq2 = zzq();
            if (zzq2.zzs.zze.getApplicationContext() instanceof final Application application) {
                if (null == zzq2.zza) {
                    zzq2.zza = new zzhx(zzq2, null);
                }
                if (z) {
                    application.unregisterActivityLifecycleCallbacks(zzq2.zza);
                    application.registerActivityLifecycleCallbacks(zzq2.zza);
                    zzq2.zzs.zzay().zzj().zza("Registered activity lifecycle callback");
                }
            }
        } else {
            zzay().zzk().zza("Application context is not an Application");
        }
        zzfq.zzp(new zzfs(this, zzgw));
    }

    static void zzA(zzft zzft, zzgw zzgw) {
        zzft.zzaz().zzg();
        zzft.zzk.zzn();
        zzao zzao = new zzao(zzft);
        zzao.zzv();
        zzft.zzz = zzao;
        zzea zzea = new zzea(zzft, zzgw.zzf);
        zzea.zzb();
        zzft.zzA = zzea;
        zzec zzec = new zzec(zzft);
        zzec.zzb();
        zzft.zzx = zzec;
        zzjm zzjm = new zzjm(zzft);
        zzjm.zzb();
        zzft.zzy = zzjm;
        zzft.zzp.zzw();
        zzft.zzl.zzw();
        zzft.zzA.zzc();
        zzeh zzi2 = zzft.zzay().zzi();
        zzft.zzk.zzh();
        zzi2.zzb("App measurement initialized, version", 60000L);
        zzft.zzay().zzi().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String zzl2 = zzea.zzl();
        if (TextUtils.isEmpty(zzft.zzf)) {
            if (zzft.zzv().zzad(zzl2)) {
                zzft.zzay().zzi().zza("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
            } else {
                zzeh zzi3 = zzft.zzay().zzi();
                String valueOf = String.valueOf(zzl2);
                zzi3.zza(0 != valueOf.length() ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app " + valueOf : "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ");
            }
        }
        zzft.zzay().zzc().zza("Debug-level message logging enabled");
        if (zzft.zzG != zzft.zzH.get()) {
            zzft.zzay().zzd().zzc("Not all components initialized", Integer.valueOf(zzft.zzG), Integer.valueOf(zzft.zzH.get()));
        }
        zzft.zzB = true;
    }

    static void zzO() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    private static void zzP(zzgm zzgm) {
        if (null == zzgm) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static void zzQ(zzf zzf2) {
        if (null == zzf2) {
            throw new IllegalStateException("Component not created");
        } else if (!zzf2.zze()) {
            throw new IllegalStateException("Component not initialized: " + zzf2.getClass());
        }
    }

    private static void zzR(zzgn zzgn) {
        if (null == zzgn) {
            throw new IllegalStateException("Component not created");
        } else if (!zzgn.zzx()) {
            throw new IllegalStateException("Component not initialized: " + zzgn.getClass());
        }
    }

    public static zzft zzp(Context context, zzcl zzcl, Long l) {
        Bundle bundle;
        if (null != zzcl && (null == zzcl.zze || null == zzcl.zzf)) {
            zzcl = new zzcl(zzcl.zza, zzcl.zzb, zzcl.zzc, zzcl.zzd, null, null, zzcl.zzg, null);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (null == zzft.zzd) {
            synchronized (zzft.class) {
                try {
                    if (null == zzft.zzd) {
                        zzd = new zzft(new zzgw(context, zzcl, l));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else if (!(null == zzcl || null == (bundle = zzcl.zzg) || !bundle.containsKey("dataCollectionDefaultEnabled"))) {
            Preconditions.checkNotNull(zzd);
            zzd.zzE = Boolean.valueOf(zzcl.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        Preconditions.checkNotNull(zzd);
        return zzd;
    }

    
    public void zzB() {
        this.zzH.incrementAndGet();
    }

    
    public void zzC(String str, int i2, Throwable th, byte[] bArr, Map map) {
        if (!(200 == i2 || 204 == i2)) {
            if (304 == i2) {
                i2 = 304;
            }
            zzay().zzk().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i2), th);
        }
        if (null == th) {
            zzm().zzm.zza(true);
            if (null == bArr || 0 == bArr.length) {
                zzay().zzc().zza("Deferred Deep Link response empty.");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(new String(bArr, StandardCharsets.UTF_8));
                String optString = jSONObject.optString("deeplink", "");
                String optString2 = jSONObject.optString("gclid", "");
                double optDouble = jSONObject.optDouble("timestamp", 0.0d);
                if (TextUtils.isEmpty(optString)) {
                    zzay().zzc().zza("Deferred Deep Link is empty.");
                    return;
                }
                zzky zzv2 = zzv();
                zzft zzft = zzv2.zzs;
                if (!TextUtils.isEmpty(optString)) {
                    List<ResolveInfo> queryIntentActivities = zzv2.zzs.zze.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(optString)), 0);
                    if (null != queryIntentActivities && !queryIntentActivities.isEmpty()) {
                        Bundle bundle = new Bundle();
                        bundle.putString("gclid", optString2);
                        bundle.putString("_cis", "ddp");
                        this.zzt.zzG("auto", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundle);
                        zzky zzv3 = zzv();
                        if (!TextUtils.isEmpty(optString)) {
                            try {
                                SharedPreferences.Editor edit = zzv3.zzs.zze.getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
                                edit.putString("deeplink", optString);
                                edit.putLong("timestamp", Double.doubleToRawLongBits(optDouble));
                                if (edit.commit()) {
                                    zzv3.zzs.zze.sendBroadcast(new Intent("android.google.analytics.action.DEEPLINK_ACTION"));
                                    return;
                                }
                                return;
                            } catch (RuntimeException e2) {
                                zzv3.zzs.zzay().zzd().zzb("Failed to persist Deferred Deep Link. exception", e2);
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
                zzay().zzk().zzc("Deferred Deep Link validation failed. gclid, deep link", optString2, optString);
                return;
            } catch (JSONException e3) {
                zzay().zzd().zzb("Failed to parse the Deferred Deep Link response. exception", e3);
                return;
            }
        }
        zzay().zzk().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i2), th);
    }

    
    public void zzD() {
        this.zzG++;
    }

    @WorkerThread
    public void zzE() {
        zzaz().zzg();
        zzR(zzr());
        String zzl2 = zzh().zzl();
        Pair zzb2 = zzm().zzb(zzl2);
        if (!this.zzk.zzr() || ((Boolean) zzb2.second).booleanValue() || TextUtils.isEmpty((CharSequence) zzb2.first)) {
            zzay().zzc().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
            return;
        }
        zzic zzr2 = zzr();
        zzr2.zzu();
        ConnectivityManager connectivityManager = (ConnectivityManager) zzr2.zzs.zze.getSystemService("connectivity");
        NetworkInfo networkInfo = null;
        if (null != connectivityManager) {
            try {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            } catch (SecurityException unused) {
            }
        }
        if (null == networkInfo || !networkInfo.isConnected()) {
            zzay().zzk().zza("Network is not available for Deferred Deep Link request. Skipping");
            return;
        }
        zzky zzv2 = zzv();
        zzh().zzs.zzk.zzh();
        URL zzD2 = zzv2.zzD(60000, zzl2, (String) zzb2.first, -1 + zzm().zzn.zza());
        if (null != zzD2) {
            zzic zzr3 = zzr();
            zzfr zzfr = new zzfr(this);
            zzr3.zzg();
            zzr3.zzu();
            Preconditions.checkNotNull(zzD2);
            Preconditions.checkNotNull(zzfr);
            zzr3.zzs.zzaz().zzo(new zzib(zzr3, zzl2, zzD2, null, null, zzfr, null));
        }
    }

    
    @WorkerThread
    public void zzF(boolean z) {
        this.zzE = Boolean.valueOf(z);
    }

    @WorkerThread
    public void zzG(boolean z) {
        zzaz().zzg();
        this.zzF = z;
    }

    
    @WorkerThread
    public void zzH(zzcl zzcl) {
        zzah zzah;
        zzaz().zzg();
        zzah zzc2 = zzm().zzc();
        zzey zzm2 = zzm();
        zzft zzft = zzm2.zzs;
        zzm2.zzg();
        int i2 = 100;
        int i3 = zzm2.zza().getInt("consent_source", 100);
        zzaf zzaf = this.zzk;
        zzft zzft2 = zzaf.zzs;
        Boolean zzk2 = zzaf.zzk("google_analytics_default_allow_ad_storage");
        zzaf zzaf2 = this.zzk;
        zzft zzft3 = zzaf2.zzs;
        Boolean zzk3 = zzaf2.zzk("google_analytics_default_allow_analytics_storage");
        if (!(null == zzk2 && null == zzk3) && zzm().zzl(-10)) {
            zzah = new zzah(zzk2, zzk3);
            i2 = -10;
        } else {
            if (!TextUtils.isEmpty(zzh().zzm()) && (0 == i3 || 30 == i3 || 10 == i3 || 30 == i3 || 30 == i3 || 40 == i3)) {
                zzq().zzS(com.google.android.gms.measurement.internal.zzah.zza, -10, this.zzc);
            } else if (TextUtils.isEmpty(zzh().zzm()) && null != zzcl && null != zzcl.zzg && zzm().zzl(30)) {
                zzah = com.google.android.gms.measurement.internal.zzah.zza(zzcl.zzg);
                if (!zzah.equals(com.google.android.gms.measurement.internal.zzah.zza)) {
                    i2 = 30;
                }
            }
            zzah = null;
        }
        if (null != zzah) {
            zzq().zzS(zzah, i2, this.zzc);
            zzc2 = zzah;
        }
        zzq().zzW(zzc2);
        if (0 == this.zzm().zzc.zza()) {
            zzay().zzj().zzb("Persisting first open", Long.valueOf(this.zzc));
            zzm().zzc.zzb(this.zzc);
        }
        zzq().zzb.zzc();
        if (zzM()) {
            if (!TextUtils.isEmpty(zzh().zzm()) || !TextUtils.isEmpty(zzh().zzk())) {
                zzky zzv2 = zzv();
                String zzm3 = zzh().zzm();
                zzey zzm4 = zzm();
                zzm4.zzg();
                String string = zzm4.zza().getString("gmp_app_id", null);
                String zzk4 = zzh().zzk();
                zzey zzm5 = zzm();
                zzm5.zzg();
                if (zzv2.zzam(zzm3, string, zzk4, zzm5.zza().getString("admob_app_id", null))) {
                    zzay().zzi().zza("Rechecking which service to use due to a GMP App Id change");
                    zzey zzm6 = zzm();
                    zzm6.zzg();
                    Boolean zzd2 = zzm6.zzd();
                    SharedPreferences.Editor edit = zzm6.zza().edit();
                    edit.clear();
                    edit.apply();
                    if (null != zzd2) {
                        zzm6.zzh(zzd2);
                    }
                    zzi().zzj();
                    this.zzy.zzs();
                    this.zzy.zzr();
                    zzm().zzc.zzb(this.zzc);
                    zzm().zze.zzb(null);
                }
                zzey zzm7 = zzm();
                String zzm8 = zzh().zzm();
                zzm7.zzg();
                SharedPreferences.Editor edit2 = zzm7.zza().edit();
                edit2.putString("gmp_app_id", zzm8);
                edit2.apply();
                zzey zzm9 = zzm();
                String zzk5 = zzh().zzk();
                zzm9.zzg();
                SharedPreferences.Editor edit3 = zzm9.zza().edit();
                edit3.putString("admob_app_id", zzk5);
                edit3.apply();
            }
            if (!zzm().zzc().zzi(zzag.ANALYTICS_STORAGE)) {
                zzm().zze.zzb(null);
            }
            zzq().zzO(zzm().zze.zza());
            zzob.zzc();
            if (this.zzk.zzs(null, zzdw.zzac)) {
                try {
                    zzv().zzs.zze.getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
                } catch (ClassNotFoundException unused) {
                    if (!TextUtils.isEmpty(zzm().zzo.zza())) {
                        zzay().zzk().zza("Remote config removed with active feature rollouts");
                        zzm().zzo.zzb(null);
                    }
                }
            }
            if (!TextUtils.isEmpty(zzh().zzm()) || !TextUtils.isEmpty(zzh().zzk())) {
                boolean zzJ = zzJ();
                if (!zzm().zzj() && !this.zzk.zzv()) {
                    zzm().zzi(!zzJ);
                }
                if (zzJ) {
                    zzq().zzy();
                }
                zzu().zza.zza();
                zzt().zzu(new AtomicReference());
                zzt().zzH(zzm().zzr.zza());
            }
        } else if (zzJ()) {
            if (!zzv().zzac("android.permission.INTERNET")) {
                zzay().zzd().zza("App is missing INTERNET permission");
            }
            if (!zzv().zzac("android.permission.ACCESS_NETWORK_STATE")) {
                zzay().zzd().zza("App is missing ACCESS_NETWORK_STATE permission");
            }
            if (!Wrappers.packageManager(this.zze).isCallerInstantApp() && !this.zzk.zzx()) {
                if (!zzky.zzai(this.zze)) {
                    zzay().zzd().zza("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzky.zzaj(this.zze, false)) {
                    zzay().zzd().zza("AppMeasurementService not registered/enabled");
                }
            }
            zzay().zzd().zza("Uploading is not possible. App measurement disabled");
        }
        zzm().zzi.zza(true);
    }

    @WorkerThread
    public boolean zzI() {
        return null != zzE && this.zzE.booleanValue();
    }

    @WorkerThread
    public boolean zzJ() {
        return 0 == this.zza();
    }

    @WorkerThread
    public boolean zzK() {
        zzaz().zzg();
        return this.zzF;
    }

    public boolean zzL() {
        return TextUtils.isEmpty(this.zzf);
    }

    
    @WorkerThread
    public boolean zzM() {
        if (this.zzB) {
            zzaz().zzg();
            Boolean bool = this.zzC;
            if (null == bool || 0 == zzD || (!bool.booleanValue() && 1000 < Math.abs(zzr.elapsedRealtime() - zzD))) {
                this.zzD = this.zzr.elapsedRealtime();
                boolean z = true;
                Boolean valueOf = Boolean.valueOf(zzv().zzac("android.permission.INTERNET") && zzv().zzac("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zze).isCallerInstantApp() || this.zzk.zzx() || (zzky.zzai(this.zze) && zzky.zzaj(this.zze, false))));
                this.zzC = valueOf;
                if (valueOf.booleanValue()) {
                    if (!zzv().zzW(zzh().zzm(), zzh().zzk()) && TextUtils.isEmpty(zzh().zzk())) {
                        z = false;
                    }
                    this.zzC = Boolean.valueOf(z);
                }
            }
            return this.zzC.booleanValue();
        }
        throw new IllegalStateException("AppMeasurement is not initialized");
    }

    public boolean zzN() {
        return this.zzi;
    }

    @WorkerThread
    public int zza() {
        zzaz().zzg();
        if (this.zzk.zzv()) {
            return 1;
        }
        Boolean bool = this.zzb;
        if (null != bool && bool.booleanValue()) {
            return 2;
        }
        zzaz().zzg();
        if (!this.zzF) {
            return 8;
        }
        Boolean zzd2 = zzm().zzd();
        if (null == zzd2) {
            zzaf zzaf = this.zzk;
            zzaa zzaa = zzaf.zzs.zzj;
            Boolean zzk2 = zzaf.zzk("firebase_analytics_collection_enabled");
            if (null == zzk2) {
                Boolean bool2 = this.zza;
                if (null != bool2) {
                    if (bool2.booleanValue()) {
                        return 0;
                    }
                    return 5;
                } else if (null == zzE || this.zzE.booleanValue()) {
                    return 0;
                } else {
                    return 7;
                }
            } else if (zzk2.booleanValue()) {
                return 0;
            } else {
                return 4;
            }
        } else if (zzd2.booleanValue()) {
            return 0;
        } else {
            return 3;
        }
    }

    public Context zzau() {
        return this.zze;
    }

    public Clock zzav() {
        return this.zzr;
    }

    public zzaa zzaw() {
        return this.zzj;
    }

    public zzej zzay() {
        zzR(this.zzm);
        return this.zzm;
    }

    public zzfq zzaz() {
        zzR(this.zzn);
        return this.zzn;
    }

    public zzd zzd() {
        zzd zzd2 = this.zzu;
        if (null != zzd2) {
            return zzd2;
        }
        throw new IllegalStateException("Component not created");
    }

    public zzaf zzf() {
        return this.zzk;
    }

    public zzao zzg() {
        zzR(this.zzz);
        return this.zzz;
    }

    public zzea zzh() {
        zzQ(this.zzA);
        return this.zzA;
    }

    public zzec zzi() {
        zzQ(this.zzx);
        return this.zzx;
    }

    public zzee zzj() {
        return this.zzq;
    }

    public zzej zzl() {
        zzej zzej = this.zzm;
        if (null == zzej || !zzej.zzx()) {
            return null;
        }
        return zzej;
    }

    public zzey zzm() {
        zzP(this.zzl);
        return this.zzl;
    }

    
    public zzfq zzo() {
        return this.zzn;
    }

    public zzhy zzq() {
        zzQ(this.zzt);
        return this.zzt;
    }

    public zzic zzr() {
        zzR(this.zzv);
        return this.zzv;
    }

    public zzim zzs() {
        zzQ(this.zzs);
        return this.zzs;
    }

    public zzjm zzt() {
        zzQ(this.zzy);
        return this.zzy;
    }

    public zzkc zzu() {
        zzQ(this.zzo);
        return this.zzo;
    }

    public zzky zzv() {
        zzP(this.zzp);
        return this.zzp;
    }

    public String zzw() {
        return this.zzf;
    }

    public String zzx() {
        return this.zzg;
    }

    public String zzy() {
        return this.zzh;
    }

    public String zzz() {
        return this.zzw;
    }
}
