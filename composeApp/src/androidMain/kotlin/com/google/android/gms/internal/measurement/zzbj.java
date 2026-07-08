package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzbj extends zzaw {
    public zzap zza(String str, zzg zzg, List list) {
        if (null == str || str.isEmpty() || !zzg.zzh(str)) {
            throw new IllegalArgumentException(String.format("Command not found: %s", str));
        }
        zzap zzd = zzg.zzd(str);
        if (zzd instanceof zzai) {
            return ((zzai) zzd).zza(zzg, list);
        }
        throw new IllegalArgumentException(String.format("Function %s is not defined", str));
    }
}
