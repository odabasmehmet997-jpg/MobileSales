package com.google.android.gms.common.data;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.NoSuchElementException;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class SingleRefDataBufferIterator<T> extends DataBufferIterator<T> {
    private Object zac;

    @NonNull
    public final Object next() {
        if (this.hasNext()) {
            final int i2 = zab + 1;
            zab = i2;
            if (0 == i2) {
                final Object checkNotNull = Preconditions.checkNotNull(zaa.get(0));
                zac = checkNotNull;
                if (!(checkNotNull instanceof DataBufferRef)) {
                    final String valueOf = String.valueOf(checkNotNull.getClass());
                    throw new IllegalStateException("DataBuffer reference of type " + valueOf + " is not movable");
                }
            } else {
                ((DataBufferRef) Preconditions.checkNotNull(zac)).zaa(zab);
            }
            return zac;
        }
        final int i3 = zab;
        throw new NoSuchElementException("Cannot advance the iterator beyond " + i3);
    }
}
