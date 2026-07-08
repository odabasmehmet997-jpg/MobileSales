package com.google.android.gms.common.data;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Releasable;
import java.io.Closeable;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public interface DataBuffer<T> extends Iterable<T>, Releasable, Closeable {
    @NonNull
    T get(int i2);

    int getCount();
}
