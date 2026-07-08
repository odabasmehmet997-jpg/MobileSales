package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzkq extends zzku {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzkq() {
        super(null);
    }

    zzkq(zzkp zzkp) {
        super(null);
    }

    
    public void zza(Object obj, long j2) {
        Object obj2;
        List list = (List) zzms.zzf(obj, j2);
        if (list instanceof zzko) {
            obj2 = ((zzko) list).zze();
        } else if (!zza.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzln) || !(list instanceof final zzkg zzkg)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                if (zzkg.zzc()) {
                    zzkg.zzb();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzms.zzs(obj, j2, obj2);
    }

    public void zzb(java.lang.Object r5, java.lang.Object r6, long r7) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzkq.zzb(java.lang.Object, java.lang.Object, long):void");
    }
}
