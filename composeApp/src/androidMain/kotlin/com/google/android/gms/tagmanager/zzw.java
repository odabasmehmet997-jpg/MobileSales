package com.google.android.gms.tagmanager;

import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.gtm.zzfy;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzw extends zzfy {
    final zzx zza;
    private final ContainerHolder.ContainerAvailableListener zzb;

    public void handleMessage(Message message) {
        if (message.what != 1) {
            Log.e("GoogleTagManager", "Don't know how to handle this message.");
            return;
        }
        this.zzb.onContainerAvailable(this.zza, (String) message.obj);
    }
}
