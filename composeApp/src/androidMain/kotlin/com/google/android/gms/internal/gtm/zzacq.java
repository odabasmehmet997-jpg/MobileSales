package com.google.android.gms.internal.gtm;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public class zzacq extends IOException {
    private boolean zza;

    public zzacq(final IOException iOException) {
        super(iOException.getMessage(), iOException);
    }

    
    public final void zza() {
        zza = true;
    }

    
    public final boolean zzb() {
        return zza;
    }

    public zzacq(final String str) {
        super(str);
    }
}
