package com.google.android.gms.tagmanager;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzap;

import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzam extends zzbp {
    private static final String zza = zza.FUNCTION_CALL.toString();
    private static final String zzb = zzb.FUNCTION_CALL_NAME.toString();
    private static final String zzc = zzb.ADDITIONAL_PARAMS.toString();
    private final zzal zzd;

    public zzam(zzal zzal) {
        super(zza, zzb);
        this.zzd = zzal;
    }

    public zzap zza(Map map) {
        String zzm = zzfp.zzm(zzfp.zzk((zzap) map.get(zzb)));
        HashMap hashMap = new HashMap();
        zzap zzap = (zzap) map.get(zzc);
        if (zzap != null) {
            Object zzk = zzfp.zzk(zzap);
            if (!(zzk instanceof Map)) {
                Log.w("GoogleTagManager", "FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return zzfp.zza();
            }
            for (Map.Entry entry : ((Map) zzk).entrySet()) {
                hashMap.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return zzfp.zzb(this.zzd.zza(zzm, hashMap));
        } catch (Exception e2) {
            String message = e2.getMessage();
            Log.w("GoogleTagManager", "Custom macro/tag " + zzm + " threw exception " + message);
            return zzfp.zza();
        }
    }

    public boolean zzb() {
        return false;
    }
}
