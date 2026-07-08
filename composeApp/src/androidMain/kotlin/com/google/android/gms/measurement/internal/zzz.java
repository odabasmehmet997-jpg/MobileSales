package com.google.android.gms.measurement.internal;

import java.util.Map;
import java.util.Set;

final class zzz extends zzkh {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;
    zzz(final zzkr zzkr) {
        super(zzkr);
    }
    private zzt zzd(final Integer num) {
        if (zzc.containsKey(num)) {
            return (zzt) zzc.get(num);
        }
        final zzt zzt = new zzt(this, zza, null);
        zzc.put(num, zzt);
        return zzt;
    }
    private boolean zzf(final int i2, final int i3) {
        final zzt zzt = (zzt) zzc.get(Integer.valueOf(i2));
        if (null == zzt) {
            return false;
        }
        return zzt.zze.get(i3);
    }

    public java.util.List zza(final java.lang.String r66, final java.util.List r67, final java.util.List r68, final java.lang.Long r69, final java.lang.Long r70) {
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzz.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long):java.util.List");
    }

    
    public boolean zzb() {
        return false;
    }
}
