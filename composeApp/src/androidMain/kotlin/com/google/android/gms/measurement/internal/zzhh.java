package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzhh(zzhy zzb, Bundle zza) implements Runnable {

    public void run() {
        final zzhy zzhy = zzb;
        final Bundle bundle = zza;
        zzhy.zzg();
        zzhy.zza();
        Preconditions.checkNotNull(bundle);
        final String string = bundle.getString("name");
        final String string2 = bundle.getString(FirebaseAnalytics.Param.ORIGIN);
        Preconditions.checkNotEmpty(string);
        Preconditions.checkNotEmpty(string2);
        Preconditions.checkNotNull(bundle.get("value"));
        if (!zzhy.zzs.zzJ()) {
            zzhy.zzs.zzay().zzj().zza("Conditional property not set since app measurement is disabled");
            return;
        }
        final zzku zzku = new zzku(string, bundle.getLong("triggered_timestamp"), bundle.get("value"), string2);
        try {
            final zzau zzz = zzhy.zzs.zzv().zzz(bundle.getString("app_id"), bundle.getString("triggered_event_name"), bundle.getBundle("triggered_event_params"), string2, 0, true, true);
            final zzau zzz2 = zzhy.zzs.zzv().zzz(bundle.getString("app_id"), bundle.getString("timed_out_event_name"), bundle.getBundle("timed_out_event_params"), string2, 0, true, true);
            final zzau zzz3 = zzhy.zzs.zzv().zzz(bundle.getString("app_id"), bundle.getString("expired_event_name"), bundle.getBundle("expired_event_params"), string2, 0, true, true);
            zzhy.zzs.zzt().zzE(new zzab(bundle.getString("app_id"), string2, zzku, bundle.getLong("creation_timestamp"), false, bundle.getString("trigger_event_name"), zzz2, bundle.getLong("trigger_timeout"), zzz, bundle.getLong("time_to_live"), zzz3));
        } catch (final IllegalArgumentException unused) {
        }
    }
}
