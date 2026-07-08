package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.firebase.messaging.Constants;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzr {
    private final zzft zza;

    public zzr(final zzft zzft) {
        zza = zzft;
    }

    
    @WorkerThread
    public void zza(String str, final Bundle bundle) {
        final String str2;
        zza.zzaz().zzg();
        if (!zza.zzJ()) {
            if (bundle.isEmpty()) {
                str2 = null;
            } else {
                if (str.isEmpty()) {
                    str = "auto";
                }
                final Uri.Builder builder = new Uri.Builder();
                builder.path(str);
                for (final String next : bundle.keySet()) {
                    builder.appendQueryParameter(next, bundle.getString(next));
                }
                str2 = builder.build().toString();
            }
            if (!TextUtils.isEmpty(str2)) {
                zza.zzm().zzp.zzb(str2);
                zza.zzm().zzq.zzb(zza.zzav().currentTimeMillis());
            }
        }
    }

    
    @WorkerThread
    public void zzb() {
        final String str;
        zza.zzaz().zzg();
        if (this.zzd()) {
            if (this.zze()) {
                zza.zzm().zzp.zzb(null);
                final Bundle bundle = new Bundle();
                bundle.putString("source", "(not set)");
                bundle.putString("medium", "(not set)");
                bundle.putString("_cis", "intent");
                bundle.putLong("_cc", 1);
                zza.zzq().zzG("auto", "_cmpx", bundle);
            } else {
                final String zza2 = zza.zzm().zzp.zza();
                if (TextUtils.isEmpty(zza2)) {
                    zza.zzay().zzh().zza("Cache still valid but referrer not found");
                } else {
                    final long zza3 = ((zza.zzm().zzq.zza() / 3600000) - 1) * 3600000;
                    final Uri parse = Uri.parse(zza2);
                    final Bundle bundle2 = new Bundle();
                    final Pair pair = new Pair(parse.getPath(), bundle2);
                    for (final String next : parse.getQueryParameterNames()) {
                        bundle2.putString(next, parse.getQueryParameter(next));
                    }
                    ((Bundle) pair.second).putLong("_cc", zza3);
                    final Object obj = pair.first;
                    if (null == obj) {
                        str = "app";
                    } else {
                        str = (String) obj;
                    }
                    zza.zzq().zzG(str, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, (Bundle) pair.second);
                }
                zza.zzm().zzp.zzb(null);
            }
            zza.zzm().zzq.zzb(0);
        }
    }

    
    public void zzc() {
        if (this.zzd() && this.zze()) {
            zza.zzm().zzp.zzb(null);
        }
    }

    
    public boolean zzd() {
        return 0 < this.zza.zzm().zzq.zza();
    }

    
    public boolean zze() {
        return this.zzd() && zza.zzav().currentTimeMillis() - zza.zzm().zzq.zza() > zza.zzf().zzi(null, zzdw.zzQ);
    }
}
