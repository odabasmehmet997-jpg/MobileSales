package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzc implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j2 = -1;
        int i2 = 0;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (2 == fieldId) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (3 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                j2 = SafeParcelReader.readLong(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new Feature(str, i2, j2);
    }

    public Object[] newArray(final int i2) {
        return new Feature[i2];
    }
}
