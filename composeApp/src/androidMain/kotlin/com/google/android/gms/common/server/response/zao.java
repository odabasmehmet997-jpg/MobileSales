package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zao implements Parcelable.Creator {
    public Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList<zal> arrayList = null;
        int i2 = 0;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (2 == fieldId) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, zal.CREATOR);
            } else if (3 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                str = SafeParcelReader.createString(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zan(i2, arrayList, str);
    }

    public Object[] newArray(int i2) {
        return new zan[i2];
    }
}
