package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzm implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Status status = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            if (1 != SafeParcelReader.getFieldId(readHeader)) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                status = SafeParcelReader.createParcelable(parcel, readHeader, Status.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzl(status);
    }

    public Object[] newArray(final int i2) {
        return new zzl[i2];
    }
}
