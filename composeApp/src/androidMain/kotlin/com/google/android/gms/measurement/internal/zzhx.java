package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.MainThread;

@TargetApi(14)
@MainThread
/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzhx implements Application.ActivityLifecycleCallbacks {
    final zzhy zza;

    zzhx(final zzhy zzhy, final zzhw zzhw) {
        zza = zzhy;
    }

    public void onActivityCreated(final Activity activity, final Bundle bundle) {
        zzft zzft;
        try {
            zza.zzs.zzay().zzj().zza("onActivityCreated");
            final Intent intent = activity.getIntent();
            if (null == intent) {
                zzft = zza.zzs;
            } else {
                final Uri data = intent.getData();
                if (null != data) {
                    if (data.isHierarchical()) {
                        zza.zzs.zzv();
                        final String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
                        zza.zzs.zzaz().zzp(new zzhv(this, null == bundle, data, ("android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra)) ? "gs" : "auto", data.getQueryParameter("referrer")));
                        zzft = zza.zzs;
                    }
                }
                zzft = zza.zzs;
            }
        } catch (final RuntimeException e2) {
            zza.zzs.zzay().zzd().zzb("Throwable caught in onActivityCreated", e2);
            zzft = zza.zzs;
        } catch (final Throwable th) {
            zza.zzs.zzs().zzr(activity, bundle);
            throw th;
        }
        zzft.zzs().zzr(activity, bundle);
    }

    public void onActivityDestroyed(final Activity activity) {
        zza.zzs.zzs().zzs(activity);
    }

    @MainThread
    public void onActivityPaused(final Activity activity) {
        zza.zzs.zzs().zzt(activity);
        final zzkc zzu = zza.zzs.zzu();
        zzu.zzs.zzaz().zzp(new zzjv(zzu, zzu.zzs.zzav().elapsedRealtime()));
    }

    @MainThread
    public void onActivityResumed(final Activity activity) {
        final zzkc zzu = zza.zzs.zzu();
        zzu.zzs.zzaz().zzp(new zzju(zzu, zzu.zzs.zzav().elapsedRealtime()));
        zza.zzs.zzs().zzu(activity);
    }

    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        zza.zzs.zzs().zzv(activity, bundle);
    }

    public void onActivityStarted(final Activity activity) {
    }

    public void onActivityStopped(final Activity activity) {
    }
}
