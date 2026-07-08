package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcj implements RemoteCall {
    private final ListenerHolder zza;

    public void accept(final Object obj, final Object obj2) {
        final TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        final zzdz zzdz = (zzdz) obj;
        final ListenerHolder.ListenerKey listenerKey = zza.getListenerKey();
        if (null != listenerKey) {
            zzdz.zzD(listenerKey, taskCompletionSource);
        }
    }
}
