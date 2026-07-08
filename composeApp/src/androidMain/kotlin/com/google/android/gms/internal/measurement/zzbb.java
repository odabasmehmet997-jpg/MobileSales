package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Iterator;

public enum zzbb {
    ;

    public static com.google.android.gms.internal.measurement.zzap zza(final java.lang.String r23, final com.google.android.gms.internal.measurement.zzae r24, final com.google.android.gms.internal.measurement.zzg r25, final java.util.List r26) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbb.zza(java.lang.String, com.google.android.gms.internal.measurement.zzae, com.google.android.gms.internal.measurement.zzg, java.util.List):com.google.android.gms.internal.measurement.zzap");
    }

    private static zzae zzb(final zzae zzae, final zzg zzg, final zzai zzai, final Boolean bool, final Boolean bool2) {
        final zzae zzae2 = new zzae();
        final Iterator zzk = zzae.zzk();
        while (zzk.hasNext()) {
            final int intValue = ((Integer) zzk.next()).intValue();
            if (zzae.zzs(intValue)) {
                final zzap zza = zzai.zza(zzg, Arrays.asList(zzae.zze(intValue), new zzah(Double.valueOf(intValue)), zzae));
                if (zza.zzg().equals(bool)) {
                    return zzae2;
                }
                if (null == bool2 || zza.zzg().equals(bool2)) {
                    zzae2.zzq(intValue, zza);
                }
            }
        }
        return zzae2;
    }

    private static com.google.android.gms.internal.measurement.zzap zzc(final com.google.android.gms.internal.measurement.zzae r10, final com.google.android.gms.internal.measurement.zzg r11, final java.util.List r12, final boolean r13) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbb.zzc(com.google.android.gms.internal.measurement.zzae, com.google.android.gms.internal.measurement.zzg, java.util.List, boolean):com.google.android.gms.internal.measurement.zzap");
    }
}
