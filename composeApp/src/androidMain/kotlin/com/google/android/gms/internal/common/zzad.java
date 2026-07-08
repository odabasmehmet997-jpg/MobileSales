package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzad extends zzaa {
    public zzad() {
        super(4);
    }

    @CanIgnoreReturnValue
    public zzad zzb(Object obj) {
        this.zza(obj);
        return this;
    }

    @CanIgnoreReturnValue
    public zzad zzc(Iterator it) {
        while (it.hasNext()) {
            this.zza(it.next());
        }
        return this;
    }

    zzad(int i2) {
        super(4);
    }
}
