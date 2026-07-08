package com.google.android.gms.cloudmessaging;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.cloudmessaging.zzf;

final class zzaa extends zzf {
    final Rpc zza;
    zzaa(final Rpc rpc, final Looper looper) {
        super(looper);
        zza = rpc;
    }
    public void handleMessage(final Message message) {
        Rpc.zzc(zza, message);
    }
}
