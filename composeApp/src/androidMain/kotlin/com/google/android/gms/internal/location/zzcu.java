package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcu implements RemoteCall {
    private final PendingIntent zza;

    public void accept(final Object obj, final Object obj2) {
        ((zzdz) obj).zzF(zzem.zzb(zza), (TaskCompletionSource) obj2);
    }
}
