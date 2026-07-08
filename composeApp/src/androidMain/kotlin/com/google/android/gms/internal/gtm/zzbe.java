package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.HashMap;
import java.util.UUID;

@VisibleForTesting
@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzbe extends zzj {
    private int zza;

    public zzbe() {
        final UUID randomUUID = UUID.randomUUID();
        int leastSignificantBits = (int) (randomUUID.getLeastSignificantBits() & 2147483647L);
        if (0 == leastSignificantBits && 0 == (leastSignificantBits = (int) (randomUUID.getMostSignificantBits() & 2147483647L))) {
            Log.e("GAv4", "UUID.randomUUID() returned 0.");
            leastSignificantBits = Integer.MAX_VALUE;
        }
        Preconditions.checkNotZero(leastSignificantBits);
        zza = leastSignificantBits;
    }

    public String toString() {
        final HashMap hashMap = new HashMap();
        hashMap.put("screenName", null);
        final Boolean bool = Boolean.FALSE;
        hashMap.put("interstitial", bool);
        hashMap.put("automatic", bool);
        hashMap.put("screenId", Integer.valueOf(zza));
        hashMap.put("referrerScreenId", 0);
        hashMap.put("referrerScreenName", null);
        hashMap.put("referrerUri", null);
        return zza(hashMap);
    }

    public void zzc(final zzj zzj) {
        final zzbe zzbe = (zzbe) zzj;
        TextUtils.isEmpty(null);
        final int i2 = zza;
        if (0 != i2) {
            zzbe.zza = i2;
        }
        TextUtils.isEmpty(null);
        if (!TextUtils.isEmpty(null)) {
            TextUtils.isEmpty(null);
        }
    }

    public int zzd() {
        return zza;
    }
}
