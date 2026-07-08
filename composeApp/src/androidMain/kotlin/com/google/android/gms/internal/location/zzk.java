package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzk implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzh zzh = null;
        IBinder iBinder = null;
        IBinder iBinder2 = null;
        int i2 = 1;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (2 == fieldId) {
                zzh = SafeParcelReader.createParcelable(parcel, readHeader, com.google.android.gms.internal.location.zzh.CREATOR);
            } else if (3 == fieldId) {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            } else if (4 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                iBinder2 = SafeParcelReader.readIBinder(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzj(i2, zzh, iBinder, iBinder2);
    }

    public Object[] newArray(final int i2) {
        return new zzj[i2];
    }
}
