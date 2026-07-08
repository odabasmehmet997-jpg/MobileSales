package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zzc implements Runnable {
    final  LifecycleCallback zza;
    final  String zzb;
    final  zzd zzc;

    zzc(zzd zzdVar, LifecycleCallback lifecycleCallback, String str) {
        this.zzc = zzdVar;
        this.zza = lifecycleCallback;
        this.zzb = str;
    }
    public void run() {
        Bundle bundle;
        zzd zzdVar = this.zzc;
        if (zzdVar.zzc > 0) {
            LifecycleCallback lifecycleCallback = this.zza;
            if (zzdVar.zzd != null) {
                bundle = zzdVar.zzd.getBundle(this.zzb);
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
