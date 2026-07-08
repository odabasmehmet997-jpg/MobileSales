package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.location.zze;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzj implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final Parcel parcel2 = parcel;
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        WorkSource workSource = new WorkSource();
        zze zze = null;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        long j2 = Long.MAX_VALUE;
        long j3 = Long.MAX_VALUE;
        int i4 = 102;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 3:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 4:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 5:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 6:
                    workSource = SafeParcelReader.createParcelable(parcel2, readHeader, WorkSource.CREATOR);
                    break;
                case 7:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 9:
                    zze = SafeParcelReader.createParcelable(parcel2, readHeader, com.google.android.gms.internal.location.zze.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new CurrentLocationRequest(j2, i2, i4, j3, z, i3, workSource, zze);
    }

    public Object[] newArray(final int i2) {
        return new CurrentLocationRequest[i2];
    }
}
