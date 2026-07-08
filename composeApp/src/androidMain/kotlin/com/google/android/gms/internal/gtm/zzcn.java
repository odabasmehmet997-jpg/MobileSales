package com.google.android.gms.internal.gtm;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzcn implements Parcelable.Creator {
    zzcn() {
    }

    @Deprecated
    public Object createFromParcel(Parcel parcel) {
        return new zzco(parcel);
    }

    @Deprecated
    public Object[] newArray(int i2) {
        return new zzco[i2];
    }
}
