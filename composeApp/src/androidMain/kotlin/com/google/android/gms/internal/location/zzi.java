package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.location.DeviceOrientationRequest;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzi implements Parcelable.Creator {
    public Object createFromParcel(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DeviceOrientationRequest deviceOrientationRequest = zzh.zzb;
        List list = zzh.zza;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int readHeader = SafeParcelReader.readHeader(parcel);
            final int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (1 == fieldId) {
                deviceOrientationRequest = SafeParcelReader.createParcelable(parcel, readHeader, DeviceOrientationRequest.CREATOR);
            } else if (2 == fieldId) {
                list = SafeParcelReader.createTypedList(parcel, readHeader, ClientIdentity.CREATOR);
            } else if (3 != fieldId) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                str = SafeParcelReader.createString(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzh(deviceOrientationRequest, list, str);
    }

    public Object[] newArray(final int i2) {
        return new zzh[i2];
    }
}
