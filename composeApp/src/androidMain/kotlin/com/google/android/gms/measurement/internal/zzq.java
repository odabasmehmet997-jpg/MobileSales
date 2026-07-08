package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzq implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final Parcel parcel2 = parcel;
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = "";
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        Boolean bool = null;
        ArrayList<String> arrayList = null;
        String str9 = null;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        long j6 = 0;
        boolean z = true;
        boolean z2 = true;
        boolean z3 = false;
        int i2 = 0;
        boolean z4 = false;
        long j7 = -2147483648L;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 3:
                    str3 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 4:
                    str4 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 5:
                    str5 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 6:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 7:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 8:
                    str6 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 9:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 10:
                    z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 11:
                    j7 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 12:
                    str7 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 13:
                    j4 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 14:
                    j5 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 15:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 16:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 18:
                    z4 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 19:
                    str8 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 21:
                    bool = SafeParcelReader.readBooleanObject(parcel2, readHeader);
                    break;
                case 22:
                    j6 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 23:
                    arrayList = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 24:
                    str9 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 25:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzp(str2, str3, str4, str5, j2, j3, str6, z, z3, j7, str7, j4, j5, i2, z2, z4, str8, bool, j6, arrayList, str9, str);
    }

    public Object[] newArray(final int i2) {
        return new zzp[i2];
    }
}
