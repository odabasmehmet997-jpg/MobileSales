package com.google.android.gms.internal.gtm;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzvp extends zzvd {
    
    public static final Set zzb;
    
    public static final zzuw zzc;
    private static final zzvm zzd = new zzvm(null);

    static {
        final Set unmodifiableSet = Collections.unmodifiableSet(new HashSet(Arrays.asList(zzuf.zza, zzuk.zza, zzul.zza)));
        zzb = unmodifiableSet;
        zzc = zzuz.zza(unmodifiableSet).zza();
    }
}
