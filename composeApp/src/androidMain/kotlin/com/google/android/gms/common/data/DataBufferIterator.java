package com.google.android.gms.common.data;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class DataBufferIterator<T> implements Iterator<T> {
    @NonNull
    protected final DataBuffer zaa;
    protected int zab = -1;

    public DataBufferIterator(@NonNull final DataBuffer dataBuffer) {
        zaa = Preconditions.checkNotNull(dataBuffer);
    }

    public final boolean hasNext() {
        return zab < zaa.getCount() + -1;
    }

    @NonNull
    public Object next() {
        if (this.hasNext()) {
            final DataBuffer dataBuffer = zaa;
            final int i2 = zab + 1;
            zab = i2;
            return dataBuffer.get(i2);
        }
        final int i3 = zab;
        throw new NoSuchElementException("Cannot advance the iterator beyond " + i3);
    }

    public final void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
