package com.google.android.gms.tagmanager;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
record zzau(zzaz zzc, List zza, long zzb) implements Runnable {

    public void run() {
        this.zzc.zzl(this.zza, this.zzb);
    }
}
