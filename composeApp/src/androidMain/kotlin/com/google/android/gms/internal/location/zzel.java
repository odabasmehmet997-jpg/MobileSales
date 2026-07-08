package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzel implements Parcelable.Creator {
    public Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = -1;
        int i3 = 0;
        short s = 0;
        int i4 = 0;
        long j2 = 0;
        float f2 = 0.0f;
        double d2 = 0.0d;
        double d3 = 0.0d;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 2:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 3:
                    s = SafeParcelReader.readShort(parcel2, readHeader);
                    break;
                case 4:
                    d2 = SafeParcelReader.readDouble(parcel2, readHeader);
                    break;
                case 5:
                    d3 = SafeParcelReader.readDouble(parcel2, readHeader);
                    break;
                case 6:
                    f2 = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 7:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 8:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 9:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzek(str, i3, s, d2, d3, f2, j2, i4, i2);
    }

    public Object[] newArray(int i2) {
        return new zzek[i2];
    }
}
