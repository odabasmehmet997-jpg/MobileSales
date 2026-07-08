package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.Log;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzbc {
    private static zzbc zza;
    private static final Object zzb = new Object();
    private final zzdx zzc;
    private final zzbx zzd;

    private zzbc(Context context) {
        zzbx zzb2 = zzbx.zzb(context);
        zzes zzes = new zzes();
        this.zzd = zzb2;
        this.zzc = zzes;
    }

    public static zzbc zzb(Context context) {
        zzbc zzbc;
        synchronized (zzb) {
            try {
                if (zza == null) {
                    zza = new zzbc(context);
                }
                zzbc = zza;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzbc;
    }

    public boolean zza(String str) {
        if (!this.zzc.zza()) {
            Log.w("GoogleTagManager", "Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        this.zzd.zzf(str, System.currentTimeMillis());
        return true;
    }
}
