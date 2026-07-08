package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzh implements Parcelable.Creator {
    zzh() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new BinderWrapper(parcel, null);
    }

    public Object[] newArray(int i2) {
        return new BinderWrapper[i2];
    }
}
