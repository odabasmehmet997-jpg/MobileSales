package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.location.zze;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzae implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zze zze = null;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            } else if (2 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zze = SafeParcelReader.createParcelable(parcel, readHeader, com.google.android.gms.internal.location.zze.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzad(z, zze);
    }

    public Object[] newArray(final int i2) {
        return new zzad[i2];
    }
}
