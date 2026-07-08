package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzb implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        IBinder iBinder = null;
        int i2 = 0;
        Float f2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (2 == fieldId) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (3 == fieldId) {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            } else if (4 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                f2 = SafeParcelReader.readFloatObject(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new Cap(i2, iBinder, f2);
    }

    public Object[] newArray(final int i2) {
        return new Cap[i2];
    }
}
