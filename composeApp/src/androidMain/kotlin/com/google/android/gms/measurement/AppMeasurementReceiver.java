package com.google.android.gms.measurement;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzfc;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements zzfc.zza {
    private zzfc zza;

    @MainThread
    public void doStartService(@NonNull Context context, @NonNull Intent intent) {
        WakefulBroadcastReceiver.startWakefulService(context, intent);
    }

    @MainThread
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (this.zza == null) {
            this.zza = new zzfc(this);
        }
        this.zza.zza(context, intent);
    }
}
