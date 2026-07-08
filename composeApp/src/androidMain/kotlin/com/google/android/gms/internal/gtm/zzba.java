package com.google.android.gms.internal.gtm;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.ShowFirstParty;

import java.util.*;

@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzba extends zzj {
    private final List zza = new ArrayList();
    private final List zzb = new ArrayList();
    private final Map zzc = new HashMap();

    public String toString() {
        HashMap hashMap = new HashMap();
        if (!this.zza.isEmpty()) {
            hashMap.put("products", this.zza);
        }
        if (!this.zzb.isEmpty()) {
            hashMap.put("promotions", this.zzb);
        }
        if (!this.zzc.isEmpty()) {
            hashMap.put("impressions", this.zzc);
        }
        hashMap.put("productAction", null);
        return zzj.zza(hashMap);
    }

    public void zzc(zzj zzj) {
        zzba zzba = (zzba) zzj;
        zzba.zza.addAll(this.zza);
        zzba.zzb.addAll(this.zzb);
        for (Map.Entry entry : this.zzc.entrySet()) {
            String str = (String) entry.getKey();
            for (Product product : (List) entry.getValue()) {
                if (null != product) {
                    String str2 = null == str ? "" : str;
                    if (!zzba.zzc.containsKey(str2)) {
                        zzba.zzc.put(str2, new ArrayList());
                    }
                    ((List) zzba.zzc.get(str2)).add(product);
                }
            }
        }
    }

    public List zzd() {
        return Collections.unmodifiableList(this.zza);
    }

    public List zze() {
        return Collections.unmodifiableList(this.zzb);
    }

    public Map zzf() {
        return this.zzc;
    }
}
