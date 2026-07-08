package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zza implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        float f2 = 0.0f;
        float f3 = 0.0f;
        LatLng latLng = null;
        float f4 = 0.0f;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (2 == fieldId) {
                latLng = SafeParcelReader.createParcelable(parcel, readHeader, LatLng.CREATOR);
            } else if (3 == fieldId) {
                f2 = SafeParcelReader.readFloat(parcel, readHeader);
            } else if (4 == fieldId) {
                f4 = SafeParcelReader.readFloat(parcel, readHeader);
            } else if (5 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                f3 = SafeParcelReader.readFloat(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new CameraPosition(latLng, f2, f4, f3);
    }

    public Object[] newArray(final int i2) {
        return new CameraPosition[i2];
    }
}
