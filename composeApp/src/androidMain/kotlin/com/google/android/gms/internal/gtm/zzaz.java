package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.HashMap;

@VisibleForTesting
@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaz extends zzj {
    public int zza;
    public int zzb;
    private String zzc;

    public String toString() {
        final HashMap hashMap = new HashMap();
        hashMap.put("language", zzc);
        hashMap.put("screenColors", 0);
        hashMap.put("screenWidth", Integer.valueOf(zza));
        hashMap.put("screenHeight", Integer.valueOf(zzb));
        hashMap.put("viewportWidth", 0);
        hashMap.put("viewportHeight", 0);
        return zza(hashMap);
    }

    public void zzc(final zzj zzj) {
        final zzaz zzaz = (zzaz) zzj;
        final int i2 = zza;
        if (0 != i2) {
            zzaz.zza = i2;
        }
        final int i3 = zzb;
        if (0 != i3) {
            zzaz.zzb = i3;
        }
        if (!TextUtils.isEmpty(zzc)) {
            zzaz.zzc = zzc;
        }
    }

    public String zzd() {
        return zzc;
    }

    public void zze(final String str) {
        zzc = str;
    }
}
