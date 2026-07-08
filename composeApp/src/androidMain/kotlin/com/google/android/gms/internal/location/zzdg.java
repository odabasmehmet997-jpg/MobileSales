package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdg extends zzs {
    final TaskCompletionSource zza;

    zzdg(final TaskCompletionSource taskCompletionSource) {
        zza = taskCompletionSource;
    }

    public void zzb(final int i2, final String[] strArr) {
        TaskUtil.setResultOrApiException(new Status(GeofenceStatusCodes.zza(i2)), zza);
    }

    public void zzc(final int i2, final String[] strArr) {
        TaskUtil.setResultOrApiException(new Status(GeofenceStatusCodes.zza(i2)), zza);
    }

    public void zzd(final int i2, final PendingIntent pendingIntent) {
        TaskUtil.setResultOrApiException(new Status(GeofenceStatusCodes.zza(i2)), zza);
    }
}
