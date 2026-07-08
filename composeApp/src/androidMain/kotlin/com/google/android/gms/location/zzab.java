package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzab implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final Parcel parcel2 = parcel;
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        zzal[] zzalArr = null;
        long j2 = 0;
        int i2 = 1;
        int i3 = 1;
        int i4 = 1000;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 2:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 3:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 4:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 5:
                    zzalArr = SafeParcelReader.createTypedArray(parcel2, readHeader, zzal.CREATOR);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new LocationAvailability(i4, i2, i3, j2, zzalArr, z);
    }

    public Object[] newArray(final int i2) {
        return new LocationAvailability[i2];
    }
}
