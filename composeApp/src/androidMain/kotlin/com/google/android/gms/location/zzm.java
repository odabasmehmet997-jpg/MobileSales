package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzm implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final Parcel parcel2 = parcel;
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        byte b2 = 0;
        long j2 = 0;
        float[] fArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 != fieldId) {
                switch (fieldId) {
                    case 4:
                        f2 = SafeParcelReader.readFloat(parcel2, readHeader);
                        break;
                    case 5:
                        f3 = SafeParcelReader.readFloat(parcel2, readHeader);
                        break;
                    case 6:
                        j2 = SafeParcelReader.readLong(parcel2, readHeader);
                        break;
                    case 7:
                        b2 = SafeParcelReader.readByte(parcel2, readHeader);
                        break;
                    case 8:
                        f4 = SafeParcelReader.readFloat(parcel2, readHeader);
                        break;
                    case 9:
                        f5 = SafeParcelReader.readFloat(parcel2, readHeader);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel2, readHeader);
                        break;
                }
            } else {
                fArr = SafeParcelReader.createFloatArray(parcel2, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new DeviceOrientation(fArr, f2, f3, j2, b2, f4, f5);
    }

    public Object[] newArray(final int i2) {
        return new DeviceOrientation[i2];
    }
}
