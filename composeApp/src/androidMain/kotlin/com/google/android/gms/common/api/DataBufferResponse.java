package com.google.android.gms.common.api;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataBuffer;
import java.util.Iterator;

public class DataBufferResponse<T, R extends AbstractDataBuffer<T> & Result> extends Response<R> implements DataBuffer<T> {
    public final void close() {
        this.getResult().close();
    }
    public final T get(final int i2) {
        return (T) ((AbstractDataBuffer<?>) this.getResult()).get(i2);
    }
    public final int getCount() {
        return this.getResult().getCount();
    }
    public final Iterator<T> iterator() {
        return ((AbstractDataBuffer) this.getResult()).iterator();
    }
    public final void release() {
        this.getResult().release();
    }
}
