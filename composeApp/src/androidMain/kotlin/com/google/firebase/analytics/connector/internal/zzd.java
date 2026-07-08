package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.api.AppMeasurementSdk;

/*  INFO: compiled from: com.google.android.gms:play-services-measurement-api@@20.1.1 */
/*  INFO: loaded from: classes2.dex */
final class zzd implements AppMeasurementSdk.OnEventListener {
    final /* synthetic */ zze zza;

    public zzd(zze zzeVar) {
        this.zza = zzeVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final void onEvent(String str, String str2, Bundle bundle, long j2) {
        if (this.zza.zza.contains(str2)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("events", zzc.zzc(str2));
            this.zza.zzb.onMessageTriggered(2, bundle2);
        }
    }
}
