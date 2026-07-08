package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzhi(zzhy zzb, Bundle zza) implements Runnable {

    public void run() {
        final zzhy zzhy = zzb;
        final Bundle bundle = zza;
        zzhy.zzg();
        zzhy.zza();
        Preconditions.checkNotNull(bundle);
        final String checkNotEmpty = Preconditions.checkNotEmpty(bundle.getString("name"));
        if (!zzhy.zzs.zzJ()) {
            zzhy.zzs.zzay().zzj().zza("Conditional property not cleared since app measurement is disabled");
            return;
        }
        final zzku zzku = new zzku(checkNotEmpty, 0, null, "");
        try {
            final zzab zzab = r4;
            final zzab zzab2 = new zzab(bundle.getString("app_id"), "", zzku, bundle.getLong("creation_timestamp"), bundle.getBoolean("active"), bundle.getString("trigger_event_name"), null, bundle.getLong("trigger_timeout"), null, bundle.getLong("time_to_live"), zzhy.zzs.zzv().zzz(bundle.getString("app_id"), bundle.getString("expired_event_name"), bundle.getBundle("expired_event_params"), "", bundle.getLong("creation_timestamp"), true, true));
            zzhy.zzs.zzt().zzE(zzab);
        } catch (final IllegalArgumentException unused) {
        }
    }
}
