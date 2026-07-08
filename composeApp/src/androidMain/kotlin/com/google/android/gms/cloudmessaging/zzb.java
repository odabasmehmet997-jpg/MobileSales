package com.google.android.gms.cloudmessaging;

import android.os.Parcel;
import android.os.Parcelable;

final class zzb implements Parcelable.Creator<zzd> {
    zzb() {
    }
    public zzd createFromParcel(final Parcel parcel) {
        return new zzd(parcel.readStrongBinder());
    }
    public zzd[] newArray(final int i2) {
        return new zzd[i2];
    }
}
