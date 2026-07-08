package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzac implements Parcelable.Creator {
    public Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        zzku zzku = null;
        String str3 = null;
        zzau zzau = null;
        zzau zzau2 = null;
        zzau zzau3 = null;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 4:
                    zzku = SafeParcelReader.createParcelable(parcel2, readHeader, com.google.android.gms.measurement.internal.zzku.CREATOR);
                    break;
                case 5:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 7:
                    str3 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 8:
                    zzau = SafeParcelReader.createParcelable(parcel2, readHeader, com.google.android.gms.measurement.internal.zzau.CREATOR);
                    break;
                case 9:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 10:
                    zzau2 = SafeParcelReader.createParcelable(parcel2, readHeader, com.google.android.gms.measurement.internal.zzau.CREATOR);
                    break;
                case 11:
                    j4 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 12:
                    zzau3 = SafeParcelReader.createParcelable(parcel2, readHeader, com.google.android.gms.measurement.internal.zzau.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzab(str, str2, zzku, j2, z, str3, zzau, j3, zzau2, j4, zzau3);
    }

    public Object[] newArray(int i2) {
        return new zzab[i2];
    }
}
