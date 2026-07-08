package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.errorprone.annotations.CheckReturnValue;
import java.security.MessageDigest;

@CheckReturnValue
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */ enum zzn {
    ;
    static final zzl zza = new zzf(zzj.zze("0\u0005È0\u0003° \u0003\u0002\u0001\u0002\u0002\u0014\u0010e\bsù/Qí"));
    static final zzl zzb = new zzg(zzj.zze("0\u0006\u00040\u0003ì \u0003\u0002\u0001\u0002\u0002\u0014\u0003£²­×árÊkì"));
    static final zzl zzc = new zzh(zzj.zze("0\u0004C0\u0003+ \u0003\u0002\u0001\u0002\u0002\t\u0000ÂàFdJ00"));
    static final zzl zzd = new zzi(zzj.zze("0\u0004¨0\u0003 \u0003\u0002\u0001\u0002\u0002\t\u0000Õ¸l}ÓNõ0"));
    private static volatile zzag zze;
    private static final Object zzf = new Object();
    private static Context zzg;

    static zzx zza(final String str, final zzj zzj, final boolean z, final boolean z2) {
        final StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return zzn.zzh(str, zzj, z, z2);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    static zzx zzb(final String str, final boolean z, final boolean z2, final boolean z3) {
        return zzn.zzi(str, z, false, false, true);
    }

    static String zzd(final boolean z, final String str, final zzj zzj) throws Exception {
        final String str2;
        if (z || !zzn.zzh(str, zzj, true, false).zza) {
            str2 = "not allowed";
        } else {
            str2 = "debug cert rejected";
        }
        final MessageDigest zza2 = AndroidUtilsLight.zza("SHA-256");
        Preconditions.checkNotNull(zza2);
        return String.format("%s: pkg=%s, sha256=%s, atk=%s, ver=%s", str2, str, Hex.bytesToStringLowercase(zza2.digest(zzj.zzf())), Boolean.valueOf(z), "12451000.false");
    }

    static synchronized void zze(final Context context) {
        synchronized (zzn.class) {
            if (null != zzg) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (null != context) {
                zzn.zzg = context.getApplicationContext();
            }
        }
    }

    static boolean zzg() {
        boolean z;
        final StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            zzn.zzj();
            z = zzn.zze.zzi();
        } catch (final RemoteException | DynamiteModule.LoadingException e2) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
            z = false;
        } catch (final Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return z;
    }

    private static zzx zzh(final String str, final zzj zzj, final boolean z, final boolean z2) {
        try {
            zzn.zzj();
            Preconditions.checkNotNull(zzn.zzg);
            try {
                return zzn.zze.zzh(new zzs(str, zzj, z, z2), ObjectWrapper.wrap(zzn.zzg.getPackageManager())) ? zzx.zzb() : new zzv(new zze(z, str, zzj), null);
            } catch (final RemoteException e2) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
                return zzx.zzd("module call", e2);
            }
        } catch (final DynamiteModule.LoadingException e3) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e3);
            return zzx.zzd("module init: " + e3.getMessage(), e3);
        }
    }

    private static zzx zzi(final String str, final boolean z, final boolean z2, final boolean z3, final boolean z4) {
        zzx zzx;
        final zzq zzq;
        final StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            Preconditions.checkNotNull(zzn.zzg);
            try {
                zzn.zzj();
                final zzo zzo = new zzo(str, z, false, ObjectWrapper.wrap(zzn.zzg), false, true);
                if (z4) {
                    zzq = zzn.zze.zze(zzo);
                } else {
                    zzq = zzn.zze.zzf(zzo);
                }
                if (zzq.zzb()) {
                    zzx = com.google.android.gms.common.zzx.zzf(zzq.zzc());
                } else {
                    String zza2 = zzq.zza();
                    final PackageManager.NameNotFoundException nameNotFoundException = 4 == zzq.zzd() ? new PackageManager.NameNotFoundException() : null;
                    if (null == zza2) {
                        zza2 = "error checking package certificate";
                    }
                    zzx = com.google.android.gms.common.zzx.zzg(zzq.zzc(), zzq.zzd(), zza2, nameNotFoundException);
                }
            } catch (final DynamiteModule.LoadingException e2) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
                zzx = com.google.android.gms.common.zzx.zzd("module init: " + e2.getMessage(), e2);
            }
        } catch (final RemoteException e3) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e3);
            zzx = com.google.android.gms.common.zzx.zzd("module call", e3);
        } catch (final Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return zzx;
    }

    private static void zzj() throws DynamiteModule.LoadingException {
        if (null == zze) {
            Preconditions.checkNotNull(zzn.zzg);
            synchronized (zzn.zzf) {
                try {
                    if (null == zze) {
                        zzn.zze = zzaf.zzb(DynamiteModule.load(zzn.zzg, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate(classLoader, "com.google.android.gms.common.GoogleCertificatesImpl"));
                    }
                } catch (final Throwable th) {
                    throw th;
                }
            }
        }
    }
}
