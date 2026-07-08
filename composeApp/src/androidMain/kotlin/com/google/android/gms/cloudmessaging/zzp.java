package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzp<T> {
    final int zza;
    final TaskCompletionSource<T> zzb = new TaskCompletionSource<>();
    final int zzc;
    final Bundle zzd;
    zzp(final int i2, final int i3, final Bundle bundle) {
        zza = i2;
        zzc = i3;
        zzd = bundle;
    }
    public final String toString() {
        final int i2 = zzc;
        final int i3 = zza;
        String sb = "Request { what=" +
                i2 +
                " id=" +
                i3 +
                " oneWay=" +
                this.zzb() +
                "}";
        return sb;
    }
    public abstract void zza(Bundle bundle);
    public abstract boolean zzb();
    public final void zzc(final zzq zzq) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            final String valueOf = String.valueOf(this);
            final String valueOf2 = String.valueOf(zzq);
            String sb = "Failing " +
                    valueOf +
                    " with " +
                    valueOf2;
            Log.d("MessengerIpcClient", sb);
        }
        zzb.setException(zzq);
    }
    public final void zzd(final T t) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            final String valueOf = String.valueOf(this);
            final String valueOf2 = String.valueOf(t);
            String sb = "Finishing " +
                    valueOf +
                    " with " +
                    valueOf2;
            Log.d("MessengerIpcClient", sb);
        }
        zzb.setResult(t);
    }
}
