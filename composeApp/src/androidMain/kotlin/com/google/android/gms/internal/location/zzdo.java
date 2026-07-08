package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.location.zzz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdo extends zzq {
    final TaskCompletionSource zza;
    final zzz zzb;

    zzdo(final TaskCompletionSource taskCompletionSource, final zzz zzz) {
        zza = taskCompletionSource;
        zzb = zzz;
    }

    public void zzd(final zzl zzl) {
        TaskUtil.setResultOrApiException(zzl.getStatus(), zza);
    }

    public void zze() throws RemoteException {
        zzb.zze();
    }
}
