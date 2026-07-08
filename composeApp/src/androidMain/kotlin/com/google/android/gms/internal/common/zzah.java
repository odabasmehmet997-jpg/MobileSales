package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.jspecify.nullness.NullMarked;

@NullMarked
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum zzah {
    ;

    @CanIgnoreReturnValue
    static Object[] zza(Object[] objArr, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            if (null != objArr[i3]) {
                i3++;
            } else {
                throw new NullPointerException("at index " + i3);
            }
        }
        return objArr;
    }
}
