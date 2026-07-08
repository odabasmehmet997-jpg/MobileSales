package com.google.android.gms.internal.gtm;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzaea extends zzaef {
    zzaea() {
        super(null);
    }

    public void zza() {
        if (!zzj()) {
            for (int i2 = 0; i2 < zzc(); i2++) {
                Map.Entry zzg = zzg(i2);
                if (((zzabu) ((zzaeb) zzg).zza()).zzg()) {
                    zzg.setValue(Collections.unmodifiableList((List) zzg.getValue()));
                }
            }
            for (Map.Entry entry : zzd()) {
                if (((zzabu) entry.getKey()).zzg()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
