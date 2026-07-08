package com.google.android.gms.internal.gtm;

import androidx.annotation.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzrf {
    private final Map zza = new HashMap();
    private zzap zzb;

    private zzrf() {
    }

    public zzre zza() {
        return new zzre(this.zza, this.zzb, null);
    }

    public zzrf zzb(String str, zzap zzap) {
        this.zza.put(str, zzap);
        return this;
    }

    public zzrf zzc(zzap zzap) {
        this.zzb = zzap;
        return this;
    }

    zzrf(zzrl zzrl) {
    }
}
