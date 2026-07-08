package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.SleepSegmentRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzao implements RemoteCall {
    private final zzaj zza;
    private final PendingIntent zzb;
    private final SleepSegmentRequest zzc;

    public void accept(final Object obj, final Object obj2) {
        ((zzv) ((zzg) obj).getService()).zzm(zzb, zzc, new zzah(zza, (TaskCompletionSource) obj2));
    }
}
