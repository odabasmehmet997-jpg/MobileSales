package com.google.android.gms.common.api.internal;

import android.os.Bundle;

/*  com.google.android.gms:play-services-basement@@18.3.0 */
final class zza implements Runnable {
    final  LifecycleCallback zza;
    final  String zzb;
    final   zzb zzc;

    zza(zzb zzbVar, LifecycleCallback lifecycleCallback, String str) {
        this.zzc = zzbVar;
        this.zza = lifecycleCallback;
        this.zzb = str;
    }
    public void run() {
        Bundle bundle;
        zzb zzbVar = this.zzc;
        if (zzbVar.zzc > 0) {
            LifecycleCallback lifecycleCallback = this.zza;
            if (zzbVar.zzd != null) {
                bundle = zzbVar.zzd.getBundle(this.zzb);
            } else {
                bundle = null;
            }
            lifecycleCallback.onCreate(bundle);
        }
        if (this.zzc.zzc >= 2) {
            this.zza.onStart();
        }
        if (this.zzc.zzc >= 3) {
            this.zza.onResume();
        }
        if (this.zzc.zzc >= 4) {
            this.zza.onStop();
        }
        if (this.zzc.zzc >= 5) {
            this.zza.onDestroy();
        }
    }
}
