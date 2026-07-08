package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
record zzko(zzkr zza) implements zzkx {

    public void zza(final String str, final String str2, final Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            final zzkr zzkr = zza;
            if (null != zzkr.zzn) {
                zzkr.zzn.zzay().zzd().zzb("AppId not known when logging event", "_err");
                return;
            }
            return;
        }
        zza.zzaz().zzp(new zzkn(this, str, "_err", bundle));
    }
}
