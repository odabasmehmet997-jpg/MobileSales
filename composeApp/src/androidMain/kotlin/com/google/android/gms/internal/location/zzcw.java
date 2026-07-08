package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcw implements RemoteCall {
    private final GeofencingRequest zza;
    private final PendingIntent zzb;

    public void accept(Object obj, Object obj2) {
        ((zzdz) obj).zzE(this.zza, this.zzb, (TaskCompletionSource) obj2);
    }
}
