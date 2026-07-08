package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public abstract class zzaw {
    final List zza = new ArrayList();

    protected zzaw() {
    }

    public abstract zzap zza(String str, zzg zzg, List list);

    
    public final zzap zzb(final String str) {
        if (zza.contains(zzh.zze(str))) {
            final String valueOf = String.valueOf(str);
            throw new UnsupportedOperationException(0 != valueOf.length() ? "Command not implemented: " + valueOf : "Command not implemented: ");
        }
        throw new IllegalArgumentException("Command not supported");
    }
}
