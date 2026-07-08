package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zae implements Parcelable.Creator {
    public Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        Long l = null;
        Long l2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (2 == fieldId) {
                i3 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (3 == fieldId) {
                l = SafeParcelReader.readLongObject(parcel, readHeader);
            } else if (4 == fieldId) {
                l2 = SafeParcelReader.readLongObject(parcel, readHeader);
            } else if (5 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                i4 = SafeParcelReader.readInt(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ModuleInstallStatusUpdate(i2, i3, l, l2, i4);
    }

    public Object[] newArray(int i2) {
        return new ModuleInstallStatusUpdate[i2];
    }
}
