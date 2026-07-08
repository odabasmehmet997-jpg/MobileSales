package com.google.android.gms.tagmanager;

import android.content.Context;
import androidx.annotation.VisibleForTesting;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzeb implements Runnable {
    private final Context zza;
    private final String zzb;
    private final String zzc;
    private zzdb zzd;
    private final zzak zze;
    private volatile String zzf;
    private volatile String zzg = null;

    public zzeb(Context context, String str, zzak zzak) {
        this.zza = context;
        this.zzb = str;
        this.zze = zzak;
        String concat = "/r?id=".concat(String.valueOf(str));
        this.zzc = concat;
        this.zzf = concat;
    }

    public void run() {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzeb.run():void");
    }

    public void zza(String str) {
        if (str == null) {
            str = this.zzc;
        } else {
            zzdc.zzb.zza("Setting CTFE URL path: ".concat(str));
        }
        this.zzf = str;
    }

    public void zzb(zzdb zzdb) {
        this.zzd = zzdb;
    }

    public void zzc(String str) {
        zzdc.zzb.zza("Setting previous container version: ".concat(String.valueOf(str)));
        this.zzg = str;
    }
}
