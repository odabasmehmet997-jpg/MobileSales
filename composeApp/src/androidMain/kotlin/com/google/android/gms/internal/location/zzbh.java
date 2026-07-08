package com.google.android.gms.internal.location;

import android.os.RemoteException;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzbh implements RemoteCall, zzdr {
    final zzbi zza;
    private final zzbg zzb;
    @GuardedBy("this")
    private ListenerHolder zzc;
    @GuardedBy("this")
    private boolean zzd = true;

    zzbh(final zzbi zzbi, final ListenerHolder listenerHolder, final zzbg zzbg) {
        zza = zzbi;
        zzc = listenerHolder;
        zzb = zzbg;
    }

    public void accept(final Object obj, final Object obj2) throws RemoteException {
        final ListenerHolder.ListenerKey listenerKey;
        final boolean z;
        final zzdz zzdz = (zzdz) obj;
        final TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        synchronized (this) {
            listenerKey = zzc.getListenerKey();
            z = zzd;
            zzc.clear();
        }
        if (null == listenerKey) {
            taskCompletionSource.setResult(Boolean.FALSE);
        } else {
            zzb.zza(zzdz, listenerKey, z, taskCompletionSource);
        }
    }

    public synchronized ListenerHolder zza() {
        return zzc;
    }

    public synchronized void zzb(final ListenerHolder listenerHolder) {
        final ListenerHolder listenerHolder2 = zzc;
        if (listenerHolder2 != listenerHolder) {
            listenerHolder2.clear();
            zzc = listenerHolder;
        }
    }

    public void zzc() {
        final ListenerHolder.ListenerKey listenerKey;
        synchronized (this) {
            zzd = false;
            listenerKey = zzc.getListenerKey();
        }
        if (null != listenerKey) {
            zza.doUnregisterEventListener(listenerKey, 2441);
        }
    }
}
