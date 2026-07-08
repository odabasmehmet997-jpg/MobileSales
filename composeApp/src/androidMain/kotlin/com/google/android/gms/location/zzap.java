package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzap implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        long j2 = 0;
        long j3 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                j2 = SafeParcelReader.readLong(parcel, readHeader);
            } else if (2 == fieldId) {
                j3 = SafeParcelReader.readLong(parcel, readHeader);
            } else if (3 == fieldId) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (4 == fieldId) {
                i3 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (5 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                i4 = SafeParcelReader.readInt(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SleepSegmentEvent(j2, j3, i2, i3, i4);
    }

    public Object[] newArray(final int i2) {
        return new SleepSegmentEvent[i2];
    }
}
