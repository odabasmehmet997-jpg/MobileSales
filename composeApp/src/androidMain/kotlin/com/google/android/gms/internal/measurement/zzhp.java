package com.google.android.gms.internal.measurement;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzhp extends zzhu {
    zzhp(final zzhr zzhr, final String str, final Double d2, final boolean z) {
        super(zzhr, "measurement.test.double_flag", d2, true, null);
    }

    
    public Object zza(final Object obj) {
        try {
            return Double.valueOf(Double.parseDouble((String) obj));
        } catch (final NumberFormatException unused) {
            final String zzc = zzc();
            final String str = (String) obj;
            String sb = "Invalid double value for " +
                    zzc +
                    ": " +
                    str;
            Log.e("PhenotypeFlag", sb);
            return null;
        }
    }
}
