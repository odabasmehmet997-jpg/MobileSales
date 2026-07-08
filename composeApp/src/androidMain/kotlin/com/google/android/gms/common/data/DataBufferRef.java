package com.google.android.gms.common.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public abstract class DataBufferRef {
    @NonNull
    @KeepForSdk
    protected final DataHolder mDataHolder;
    @KeepForSdk
    protected int mDataRow;
    private int zaa;

    @KeepForSdk
    public boolean equals(@Nullable final Object obj) {
        if (obj instanceof DataBufferRef dataBufferRef) {
            return Objects.equal(Integer.valueOf(dataBufferRef.mDataRow), Integer.valueOf(mDataRow)) && Objects.equal(Integer.valueOf(dataBufferRef.zaa), Integer.valueOf(zaa)) && dataBufferRef.mDataHolder == mDataHolder;
        }
        return false;
    }

    @KeepForSdk
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(mDataRow), Integer.valueOf(zaa), mDataHolder);
    }

    
    public final void zaa(final int i2) {
        boolean z = 0 <= i2 && i2 < mDataHolder.getCount();
        Preconditions.checkState(z);
        mDataRow = i2;
        zaa = mDataHolder.getWindowIndex(i2);
    }
}
