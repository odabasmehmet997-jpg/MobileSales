package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.measurement.internal.zzgt;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdu extends zzch {
    private final zzgt zza;

    zzdu(final zzgt zzgt) {
        zza = zzgt;
    }

    public int zzd() {
        return System.identityHashCode(zza);
    }

    public void zze(final String str, final String str2, final Bundle bundle, final long j2) {
        zza.interceptEvent(str, str2, bundle, j2);
    }
}
