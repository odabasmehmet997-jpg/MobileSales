package com.google.android.gms.internal.measurement;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzhn extends zzhu {
    zzhn(final zzhr zzhr, final String str, final Long l, final boolean z) {
        super(zzhr, str, l, true, null);
    }

    
    public Object zza(final Object obj) {
        try {
            return Long.valueOf(Long.parseLong((String) obj));
        } catch (final NumberFormatException unused) {
            final String zzc = zzc();
            final String str = (String) obj;
            String sb = "Invalid long value for " +
                    zzc +
                    ": " +
                    str;
            Log.e("PhenotypeFlag", sb);
            return null;
        }
    }
}
