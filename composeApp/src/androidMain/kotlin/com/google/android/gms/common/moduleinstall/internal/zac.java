package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zac implements Parcelable.Creator {
    public Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList<Feature> arrayList = null;
        String str = null;
        boolean z = false;
        String str2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, Feature.CREATOR);
            } else if (2 == fieldId) {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            } else if (3 == fieldId) {
                str2 = SafeParcelReader.createString(parcel, readHeader);
            } else if (4 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                str = SafeParcelReader.createString(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ApiFeatureRequest(arrayList, z, str2, str);
    }

    public Object[] newArray(int i2) {
        return new ApiFeatureRequest[i2];
    }
}
