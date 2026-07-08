package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzi implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Integer num = null;
        Integer num2 = null;
        Float f2 = null;
        Float f3 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                num = SafeParcelReader.readIntegerObject(parcel, readHeader);
            } else if (2 == fieldId) {
                num2 = SafeParcelReader.readIntegerObject(parcel, readHeader);
            } else if (3 == fieldId) {
                f2 = SafeParcelReader.readFloatObject(parcel, readHeader);
            } else if (4 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                f3 = SafeParcelReader.readFloatObject(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new FeatureStyle(num, num2, f2, f3);
    }

    public Object[] newArray(final int i2) {
        return new FeatureStyle[i2];
    }
}
