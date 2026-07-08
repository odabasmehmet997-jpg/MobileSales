package com.google.android.gms.measurement.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.proje.mobilesales.BuildConfig;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzaf extends zzgm {
    private Boolean zza;
    private zzae zzb = zzad.zza;
    private Boolean zzc;

    zzaf(zzft zzft) {
        super(zzft);
    }

    public static long zzA() {
        return ((Long) zzdw.zzC.zza(null)).longValue();
    }

    private String zzB(String str, String str2) {
        final Class<String> cls = String.class;
        try {
            String str3 = (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{cls, cls}).invoke((Object) null, new Object[]{str, ""});
            Preconditions.checkNotNull(str3);
            return str3;
        } catch (ClassNotFoundException e2) {
            this.zzs.zzay().zzd().zzb("Could not find SystemProperties class", e2);
            return "";
        } catch (NoSuchMethodException e3) {
            this.zzs.zzay().zzd().zzb("Could not find SystemProperties.get() method", e3);
            return "";
        } catch (IllegalAccessException e4) {
            this.zzs.zzay().zzd().zzb("Could not access SystemProperties.get()", e4);
            return "";
        } catch (InvocationTargetException e5) {
            this.zzs.zzay().zzd().zzb("SystemProperties.get() threw an exception", e5);
            return "";
        }
    }

    public static long zzz() {
        return ((Long) zzdw.zzc.zza(null)).longValue();
    }

    @WorkerThread
    public double zza(String str, zzdv zzdv) {
        if (null == str) {
            return ((Double) zzdv.zza(null)).doubleValue();
        }
        String zza2 = this.zzb.zza(str, zzdv.zzb());
        if (TextUtils.isEmpty(zza2)) {
            return ((Double) zzdv.zza(null)).doubleValue();
        }
        try {
            return ((Double) zzdv.zza(Double.valueOf(Double.parseDouble(zza2)))).doubleValue();
        } catch (NumberFormatException unused) {
            return ((Double) zzdv.zza(null)).doubleValue();
        }
    }


    public int zzb(@Size(min = 1) String str) {
        return zzf(str, zzdw.zzG, 500, 2000);
    }

    public int zzc() {
        zzky zzv = this.zzs.zzv();
        Boolean zzj = zzv.zzs.zzt().zzj();
        if (201500 > zzv.zzm()) {
            return (null == zzj || zzj.booleanValue()) ? 25 : 100;
        }
        return 100;
    }

    public int zzd(@Size(min = 1) String str) {
        return zzf(str, zzdw.zzH, 25, 100);
    }

    @WorkerThread
    public int zze(String str, zzdv zzdv) {
        if (null == str) {
            return ((Integer) zzdv.zza(null)).intValue();
        }
        String zza2 = this.zzb.zza(str, zzdv.zzb());
        if (TextUtils.isEmpty(zza2)) {
            return ((Integer) zzdv.zza(null)).intValue();
        }
        try {
            return ((Integer) zzdv.zza(Integer.valueOf(Integer.parseInt(zza2)))).intValue();
        } catch (NumberFormatException unused) {
            return ((Integer) zzdv.zza(null)).intValue();
        }
    }

    @WorkerThread
    public int zzf(String str, zzdv zzdv, int i2, int i3) {
        return Math.max(Math.min(zze(str, zzdv), i3), i2);
    }

    public long zzh() {
        this.zzs.zzaw();
        return 60000;
    }

    @WorkerThread
    public long zzi(String str, zzdv zzdv) {
        if (null == str) {
            return ((Long) zzdv.zza(null)).longValue();
        }
        String zza2 = this.zzb.zza(str, zzdv.zzb());
        if (TextUtils.isEmpty(zza2)) {
            return ((Long) zzdv.zza(null)).longValue();
        }
        try {
            return ((Long) zzdv.zza(Long.valueOf(Long.parseLong(zza2)))).longValue();
        } catch (NumberFormatException unused) {
            return ((Long) zzdv.zza(null)).longValue();
        }
    }


    @VisibleForTesting
    public Bundle zzj() {
        try {
            if (null == zzs.zzau().getPackageManager()) {
                this.zzs.zzay().zzd().zza("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(this.zzs.zzau()).getApplicationInfo(this.zzs.zzau().getPackageName(), 128);
            if (null != applicationInfo) {
                return applicationInfo.metaData;
            }
            this.zzs.zzay().zzd().zza("Failed to load metadata: ApplicationInfo is null");
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            this.zzs.zzay().zzd().zzb("Failed to load metadata: Package name not found", e2);
            return null;
        }
    }


    @VisibleForTesting
    public Boolean zzk(@Size(min = 1) String str) {
        Preconditions.checkNotEmpty(str);
        Bundle zzj = zzj();
        if (null == zzj) {
            this.zzs.zzay().zzd().zza("Failed to load metadata: Metadata bundle is null");
            return null;
        } else if (!zzj.containsKey(str)) {
            return null;
        } else {
            return Boolean.valueOf(zzj.getBoolean(str));
        }
    }

    public String zzl() {
        return zzB("debug.firebase.analytics.app", "");
    }

    public String zzm() {
        return zzB("debug.deferred.deeplink", "");
    }


    public String zzn() {
        this.zzs.zzaw();
        return "FA";
    }

    @WorkerThread
    public String zzo(String str, zzdv zzdv) {
        if (null == str) {
            return (String) zzdv.zza(null);
        }
        return (String) zzdv.zza(this.zzb.zza(str, zzdv.zzb()));
    }


    /*  WARNING: Removed duplicated region for block: B:8:0x002e A[SYNTHETIC, Splitter:B:8:0x002e] */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List zzp(@androidx.annotation.Size(min = 1) java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r4 = "analytics.safelisted_events"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            android.os.Bundle r0 = r3.zzj()
            r1 = 0
            if (r0 != 0) goto L_0x001d
            com.google.android.gms.measurement.internal.zzft r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzej r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzd()
            java.lang.String r0 = "Failed to load metadata: Metadata bundle is null"
            r4.zza(r0)
        L_0x001b:
            r4 = r1
            goto L_0x002c
        L_0x001d:
            boolean r2 = r0.containsKey(r4)
            if (r2 != 0) goto L_0x0024
            goto L_0x001b
        L_0x0024:
            int r4 = r0.getInt(r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
        L_0x002c:
            if (r4 == 0) goto L_0x0058
            com.google.android.gms.measurement.internal.zzft r0 = r3.zzs     // Catch:{ NotFoundException -> 0x0048 }
            android.content.Context r0 = r0.zzau()     // Catch:{ NotFoundException -> 0x0048 }
            android.content.res.Resources r0 = r0.getResources()     // Catch:{ NotFoundException -> 0x0048 }
            int r4 = r4.intValue()     // Catch:{ NotFoundException -> 0x0048 }
            java.lang.String[] r4 = r0.getStringArray(r4)     // Catch:{ NotFoundException -> 0x0048 }
            if (r4 != 0) goto L_0x0043
            return r1
        L_0x0043:
            java.util.List r4 = java.util.Arrays.asList(r4)     // Catch:{ NotFoundException -> 0x0048 }
            return r4
        L_0x0048:
            r4 = move-exception
            com.google.android.gms.measurement.internal.zzft r0 = r3.zzs
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzd()
            java.lang.String r2 = "Failed to load string array from metadata: resource not found"
            r0.zzb(r2, r4)
        L_0x0058:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaf.zzp(java.lang.String):java.util.List");
    }


    public void zzq(zzae zzae) {
        this.zzb = zzae;
    }

    public boolean zzr() {
        Boolean zzk = zzk("google_analytics_adid_collection_enabled");
        return null == zzk || zzk.booleanValue();
    }

    @WorkerThread
    public boolean zzs(String str, zzdv zzdv) {
        if (null == str) {
            return ((Boolean) zzdv.zza(null)).booleanValue();
        }
        String zza2 = this.zzb.zza(str, zzdv.zzb());
        if (TextUtils.isEmpty(zza2)) {
            return ((Boolean) zzdv.zza(null)).booleanValue();
        }
        return ((Boolean) zzdv.zza(Boolean.valueOf(BuildConfig.NETSIS_DEMO_PASSWORD.equals(zza2)))).booleanValue();
    }

    public boolean zzt(String str) {
        return BuildConfig.NETSIS_DEMO_PASSWORD.equals(this.zzb.zza(str, "gaia_collection_enabled"));
    }

    public boolean zzu() {
        Boolean zzk = zzk("google_analytics_automatic_screen_reporting_enabled");
        return null == zzk || zzk.booleanValue();
    }

    public boolean zzv() {
        this.zzs.zzaw();
        Boolean zzk = zzk("firebase_analytics_collection_deactivated");
        return null != zzk && zzk.booleanValue();
    }

    public boolean zzw(String str) {
        return BuildConfig.NETSIS_DEMO_PASSWORD.equals(this.zzb.zza(str, "measurement.event_sampling_enabled"));
    }


    @WorkerThread
    public boolean zzx() {
        if (null == zza) {
            Boolean zzk = zzk("app_measurement_lite");
            this.zza = zzk;
            if (null == zzk) {
                this.zza = Boolean.FALSE;
            }
        }
        return this.zza.booleanValue() || !this.zzs.zzN();
    }

    public boolean zzy() {
        if (null == zzc) {
            synchronized (this) {
                try {
                    if (null == zzc) {
                        ApplicationInfo applicationInfo = this.zzs.zzau().getApplicationInfo();
                        String myProcessName = ProcessUtils.getMyProcessName();
                        if (null != applicationInfo) {
                            String str = applicationInfo.processName;
                            final boolean z = null != str && str.equals(myProcessName);
                            this.zzc = Boolean.valueOf(z);
                        }
                        if (null == zzc) {
                            this.zzc = Boolean.TRUE;
                            this.zzs.zzay().zzd().zza("My process not in the list of running processes");
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.zzc.booleanValue();
    }
}
