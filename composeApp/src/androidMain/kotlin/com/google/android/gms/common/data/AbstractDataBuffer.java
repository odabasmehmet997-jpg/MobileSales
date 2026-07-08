package com.google.android.gms.common.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public abstract class AbstractDataBuffer<T> extends AbstractDataBuffer<Object> implements DataBuffer<T>, Result {
    @KeepForSdk
    @Nullable
    protected final DataHolder mDataHolder;

    public final void close() {
        this.release();
    }

    @NonNull
    public abstract T get(int i2);

    public int getCount() {
        final DataHolder dataHolder = mDataHolder;
        if (null == dataHolder) {
            return 0;
        }
        return dataHolder.getCount();
    }

    @NonNull
    public Iterator<T> iterator() {
        return new DataBufferIterator(this);
    }

    public void release() {
        final DataHolder dataHolder = mDataHolder;
        if (null != dataHolder) {
            dataHolder.close();
        }
    }
}
