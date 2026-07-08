package com.google.android.gms.internal.gtm;

import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ProcessUtils;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzcs {
    private final zzbu zza;
    private volatile Boolean zzb;
    private String zzc;
    private Set zzd;

    zzcs(zzbu zzbu) {
        Preconditions.checkNotNull(zzbu);
        this.zza = zzbu;
    }

    public static long zzc() {
        return ((Long) zzeh.zzy.zzb()).longValue();
    }

    public static long zzd() {
        return ((Long) zzeh.zzg.zzb()).longValue();
    }

    public static long zze() {
        return ((Long) zzeh.zzf.zzb()).longValue();
    }

    public static int zzf() {
        return ((Integer) zzeh.zzr.zzb()).intValue();
    }

    public static int zzg() {
        return ((Integer) zzeh.zzj.zzb()).intValue();
    }

    public static int zzh() {
        return ((Integer) zzeh.zzi.zzb()).intValue();
    }

    @VisibleForTesting
    public static String zzi() {
        return (String) zzeh.zzl.zzb();
    }

    public static String zzj() {
        return (String) zzeh.zzm.zzb();
    }

    @VisibleForTesting
    public static String zzk() {
        return (String) zzeh.zzk.zzb();
    }

    public static boolean zzl() {
        return ((Boolean) zzeh.zza.zzb()).booleanValue();
    }

    public Set zza() {
        String str;
        String str2 = (String) zzeh.zzu.zzb();
        if (null == zzd || null == (str = zzc) || !str.equals(str2)) {
            String[] split = TextUtils.split(str2, ",");
            HashSet hashSet = new HashSet();
            for (String parseInt : split) {
                try {
                    hashSet.add(Integer.valueOf(Integer.parseInt(parseInt)));
                } catch (NumberFormatException unused) {
                }
            }
            this.zzc = str2;
            this.zzd = hashSet;
        }
        return this.zzd;
    }

    public boolean zzb() {
        if (null == zzb) {
            synchronized (this) {
                try {
                    if (null == zzb) {
                        ApplicationInfo applicationInfo = this.zza.zza().getApplicationInfo();
                        String myProcessName = ProcessUtils.getMyProcessName();
                        if (null != applicationInfo) {
                            String str = applicationInfo.processName;
                            final boolean z = null != str && str.equals(myProcessName);
                            this.zzb = Boolean.valueOf(z);
                        }
                        if ((null == zzb || !this.zzb.booleanValue()) && "com.google.android.gms.analytics".equals(myProcessName)) {
                            this.zzb = Boolean.TRUE;
                        }
                        if (null == zzb) {
                            this.zzb = Boolean.TRUE;
                            this.zza.zzm().zzI("My process not in the list of running processes");
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.zzb.booleanValue();
    }
}
