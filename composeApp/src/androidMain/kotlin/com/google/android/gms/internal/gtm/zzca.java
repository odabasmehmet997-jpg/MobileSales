package com.google.android.gms.internal.gtm;

import android.content.ComponentName;
import android.content.ServiceConnection;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzca implements ServiceConnection {
    final zzcb zza;
    private volatile boolean zzb;
    private volatile zzel zzc;

    zzca(final zzcb zzcb) {
        zza = zzcb;
    }

    public void onServiceConnected(final android.content.ComponentName r3, final android.os.IBinder r4) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzca.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public void onServiceDisconnected(final ComponentName componentName) {
        Preconditions.checkMainThread("AnalyticsServiceConnection.onServiceDisconnected");
        zza.zzq().zzi(new zzbz(this, componentName));
    }

    public com.google.android.gms.internal.gtm.zzel zza() {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzca.zza():com.google.android.gms.internal.gtm.zzel");
    }
}
