package com.google.android.gms.internal.gtm;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tagmanager.zzfp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzrh {
    private final List zza = new ArrayList();
    private final Map zzb = new HashMap();
    private String zzc = "";
    private int zzd;

    private zzrh() {
    }

    public zzrg zza() {
        return new zzrg(this.zza, this.zzb, this.zzc, this.zzd, null);
    }

    public zzrh zzb(zzre zzre) {
        String zzm = zzfp.zzm(zzfp.zzk((zzap) zzre.zzc().get(zzb.INSTANCE_NAME.toString())));
        List list = (List) this.zzb.get(zzm);
        if (null == list) {
            list = new ArrayList();
            this.zzb.put(zzm, list);
        }
        list.add(zzre);
        return this;
    }

    public zzrh zzc(zzri zzri) {
        this.zza.add(zzri);
        return this;
    }

    public zzrh zzd(int i2) {
        this.zzd = i2;
        return this;
    }

    public zzrh zze(String str) {
        this.zzc = str;
        return this;
    }

    zzrh(zzrl zzrl) {
    }
}
