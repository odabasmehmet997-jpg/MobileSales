package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzec implements OnTokenCanceledListener {
    private final zzdz zza;
    private final ListenerHolder.ListenerKey zzb;

    zzec(zzdz zzdz, ListenerHolder.ListenerKey listenerKey) {
        this.zza = zzdz;
        this.zzb = listenerKey;
    }

    public void onCanceled() {
        try {
            this.zza.zzw(this.zzb, true, new TaskCompletionSource());
        } catch (RemoteException unused) {
        }
    }
}
