package com.google.android.gms.location;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzd implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList<DetectedActivity> arrayList = null;
        Bundle bundle = null;
        int i2 = 0;
        long j2 = 0;
        long j3 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, DetectedActivity.CREATOR);
            } else if (2 == fieldId) {
                j2 = SafeParcelReader.readLong(parcel, readHeader);
            } else if (3 == fieldId) {
                j3 = SafeParcelReader.readLong(parcel, readHeader);
            } else if (4 == fieldId) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (5 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                bundle = SafeParcelReader.createBundle(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ActivityRecognitionResult(arrayList, j2, j3, i2, bundle);
    }

    public Object[] newArray(final int i2) {
        return new ActivityRecognitionResult[i2];
    }
}
