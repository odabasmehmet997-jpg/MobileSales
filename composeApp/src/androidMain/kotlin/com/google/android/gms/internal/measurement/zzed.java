package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
record zzed(zzee zza) implements Application.ActivityLifecycleCallbacks {

    public void onActivityCreated(final Activity activity, final Bundle bundle) {
        zza.zzU(new zzdw(this, bundle, activity));
    }

    public void onActivityDestroyed(final Activity activity) {
        zza.zzU(new zzec(this, activity));
    }

    public void onActivityPaused(final Activity activity) {
        zza.zzU(new zzdz(this, activity));
    }

    public void onActivityResumed(final Activity activity) {
        zza.zzU(new zzdy(this, activity));
    }

    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        final zzbz zzbz = new zzbz();
        zza.zzU(new zzeb(this, activity, zzbz));
        final Bundle zzb = zzbz.zzb(50);
        if (null != zzb) {
            bundle.putAll(zzb);
        }
    }

    public void onActivityStarted(final Activity activity) {
        zza.zzU(new zzdx(this, activity));
    }

    public void onActivityStopped(final Activity activity) {
        zza.zzU(new zzea(this, activity));
    }
}
