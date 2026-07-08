package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzax {
    final Map zza = new HashMap();
    final zzbj zzb = new zzbj();

    public zzax() {
        zzb(new zzav());
        zzb(new zzay());
        zzb(new zzaz());
        zzb(new zzbc());
        zzb(new zzbh());
        zzb(new zzbi());
        zzb(new zzbk());
    }

    public zzap zza(zzg zzg, zzap zzap) {
        zzaw zzaw;
        zzh.zzc(zzg);
        if (!(zzap instanceof final zzaq zzaq)) {
            return zzap;
        }
        ArrayList zzc = zzaq.zzc();
        String zzb2 = zzaq.zzb();
        if (this.zza.containsKey(zzb2)) {
            zzaw = (zzaw) this.zza.get(zzb2);
        } else {
            zzaw = this.zzb;
        }
        return zzaw.zza(zzb2, zzg, zzc);
    }

    
    public void zzb(zzaw zzaw) {
        for (zzbl zzb2 : zzaw.zza) {
            this.zza.put(zzb2.zzb().toString(), zzaw);
        }
    }
}
