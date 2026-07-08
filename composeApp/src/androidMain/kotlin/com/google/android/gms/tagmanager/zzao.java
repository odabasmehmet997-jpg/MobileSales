package com.google.android.gms.tagmanager;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
record zzao(DataLayer zza) implements zzar {

    public void zza(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzap zzap = (zzap) it.next();
            DataLayer dataLayer = this.zza;
            dataLayer.zzi(dataLayer.zza(zzap.zza(), zzap.zzb()));
        }
        this.zza.zzh.countDown();
    }
}
