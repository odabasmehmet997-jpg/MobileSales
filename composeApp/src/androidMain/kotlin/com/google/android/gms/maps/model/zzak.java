package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzak implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        float f2 = 0.0f;
        float f3 = 0.0f;
        boolean z = true;
        boolean z2 = false;
        IBinder iBinder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (2 == fieldId) {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            } else if (3 == fieldId) {
                z2 = SafeParcelReader.readBoolean(parcel, readHeader);
            } else if (4 == fieldId) {
                f2 = SafeParcelReader.readFloat(parcel, readHeader);
            } else if (5 == fieldId) {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            } else if (6 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                f3 = SafeParcelReader.readFloat(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new TileOverlayOptions(iBinder, z2, f2, z, f3);
    }

    public Object[] newArray(final int i2) {
        return new TileOverlayOptions[i2];
    }
}
