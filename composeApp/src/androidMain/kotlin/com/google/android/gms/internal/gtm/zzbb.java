package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.firebase.messaging.Constants;
import java.util.HashMap;

@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzbb extends zzj {
    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("category", null);
        hashMap.put("action", null);
        hashMap.put(Constants.ScionAnalytics.PARAM_LABEL, null);
        hashMap.put("value", 0L);
        return zzj.zza(hashMap);
    }

    public void zzc(zzj zzj) {
        zzbb zzbb = (zzbb) zzj;
        TextUtils.isEmpty(null);
        TextUtils.isEmpty(null);
        TextUtils.isEmpty(null);
    }
}
