package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.core.content.PermissionChecker;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzhh implements zzhe {
    @GuardedBy("GservicesLoader.class")
    private static zzhh zza;
    private final Context zzb;
    private final ContentObserver zzc;

    private zzhh() {
        this.zzb = null;
        this.zzc = null;
    }

    private zzhh(Context context) {
        this.zzb = context;
        zzhg zzhg = new zzhg(this, null);
        this.zzc = zzhg;
        context.getContentResolver().registerContentObserver(zzgv.zza, true, zzhg);
    }

    static zzhh zza(Context context) {
        zzhh zzhh;
        synchronized (zzhh.class) {
            try {
                if (null == zza) {
                    zza = 0 == PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") ? new zzhh(context) : new zzhh();
                }
                zzhh = zza;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzhh;
    }

    static synchronized void zze() {
        Context context;
        synchronized (zzhh.class) {
            try {
                zzhh zzhh = zza;
                if (!(null == zzhh || null == (context = zzhh.zzb) || null == zzhh.zzc)) {
                    context.getContentResolver().unregisterContentObserver(zza.zzc);
                }
                zza = null;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    /* renamed from: zzc */
    public String zzb(String str) {
        if (null == zzb) {
            return null;
        }
        try {
            return (String) zzhc.zza(new zzhf(this, str));
        } catch (IllegalStateException | NullPointerException | SecurityException e2) {
            String valueOf = String.valueOf(str);
            Log.e("GservicesLoader", 0 != valueOf.length() ? "Unable to read GServices for: " + valueOf : "Unable to read GServices for: ", e2);
            return null;
        }
    }

    
    public String zzd(String str) {
        return zzgv.zza(this.zzb.getContentResolver(), str, null);
    }
}
