package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.location.zzek;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzp implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList<zzek> arrayList = null;
        int i2 = 0;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, zzek.CREATOR);
            } else if (2 == fieldId) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (4 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                str = SafeParcelReader.createString(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new GeofencingRequest(arrayList, i2, str);
    }

    public Object[] newArray(final int i2) {
        return new GeofencingRequest[i2];
    }
}
