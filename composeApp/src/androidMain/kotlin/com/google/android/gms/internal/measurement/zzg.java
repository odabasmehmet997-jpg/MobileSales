package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzg {
    public final zzg zza;
    final zzax zzb;
    final Map zzc = new HashMap();
    final Map zzd = new HashMap();

    public zzg(final zzg zzg, final zzax zzax) {
        zza = zzg;
        zzb = zzax;
    }

    public zzg zza() {
        return new zzg(this, zzb);
    }

    public zzap zzb(final zzap zzap) {
        return zzb.zza(this, zzap);
    }

    public zzap zzc(final zzae zzae) {
        zzap zzap = com.google.android.gms.internal.measurement.zzap.zzf;
        final Iterator zzk = zzae.zzk();
        while (zzk.hasNext()) {
            zzap = zzb.zza(this, zzae.zze(((Integer) zzk.next()).intValue()));
            if (zzap instanceof zzag) {
                break;
            }
        }
        return zzap;
    }

    public zzap zzd(final String str) {
        if (zzc.containsKey(str)) {
            return (zzap) zzc.get(str);
        }
        final zzg zzg = zza;
        if (null != zzg) {
            return zzg.zzd(str);
        }
        throw new IllegalArgumentException(String.format("%s is not defined", str));
    }

    public void zze(final String str, final zzap zzap) {
        if (!zzd.containsKey(str)) {
            if (null == zzap) {
                zzc.remove(str);
            } else {
                zzc.put(str, zzap);
            }
        }
    }

    public void zzf(final String str, final zzap zzap) {
        this.zze(str, zzap);
        zzd.put(str, Boolean.TRUE);
    }

    public void zzg(final String str, final zzap zzap) {
        final zzg zzg;
        if (!zzc.containsKey(str) && null != (zzg = this.zza) && zzg.zzh(str)) {
            zza.zzg(str, zzap);
        } else if (!zzd.containsKey(str)) {
            if (null == zzap) {
                zzc.remove(str);
            } else {
                zzc.put(str, zzap);
            }
        }
    }

    public boolean zzh(final String str) {
        if (zzc.containsKey(str)) {
            return true;
        }
        final zzg zzg = zza;
        if (null != zzg) {
            return zzg.zzh(str);
        }
        return false;
    }
}
