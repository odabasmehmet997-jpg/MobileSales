package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.StatusCallback;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzan implements RemoteCall {
    private final PendingIntent zza;

    public void accept(final Object obj, final Object obj2) {
        final Api api = zzaj.zzb;
        final zzai zzai = new zzai((TaskCompletionSource) obj2);
        final PendingIntent pendingIntent = zza;
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        Preconditions.checkNotNull(zzai, "ResultHolder not provided.");
        ((zzv) ((zzg) obj).getService()).zzn(pendingIntent, new StatusCallback(zzai));
    }
}
