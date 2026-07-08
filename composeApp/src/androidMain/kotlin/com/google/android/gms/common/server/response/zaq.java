package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zaq implements Parcelable.Creator {
    public Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Parcel parcel2 = null;
        int i2 = 0;
        zan zan = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (2 == fieldId) {
                parcel2 = SafeParcelReader.createParcel(parcel, readHeader);
            } else if (3 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zan = SafeParcelReader.createParcelable(parcel, readHeader, com.google.android.gms.common.server.response.zan.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SafeParcelResponse(i2, parcel2, zan);
    }

    public Object[] newArray(int i2) {
        return new SafeParcelResponse[i2];
    }
}
