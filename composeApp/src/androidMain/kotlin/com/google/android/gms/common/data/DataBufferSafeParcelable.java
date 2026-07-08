package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.messaging.Constants;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class DataBufferSafeParcelable<T extends SafeParcelable> extends AbstractDataBuffer<T> {
    private static final String[] zaa = {Constants.ScionAnalytics.MessageType.DATA_MESSAGE};
    private final Parcelable.Creator zab;

    @NonNull
    @KeepForSdk
    public T get(final int i2) {
        final DataHolder dataHolder = Preconditions.checkNotNull(mDataHolder);
        final byte[] byteArray = dataHolder.getByteArray(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, i2, dataHolder.getWindowIndex(i2));
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(byteArray, 0, byteArray.length);
        obtain.setDataPosition(0);
        final T t = (SafeParcelable) zab.createFromParcel(obtain);
        obtain.recycle();
        return t;
    }
}
