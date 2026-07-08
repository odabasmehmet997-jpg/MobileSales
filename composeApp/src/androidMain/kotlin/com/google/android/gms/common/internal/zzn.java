package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzn implements Parcelable.Creator {
    static void zza(GetServiceRequest getServiceRequest, Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getServiceRequest.zzc);
        SafeParcelWriter.writeInt(parcel, 2, getServiceRequest.zzd);
        SafeParcelWriter.writeInt(parcel, 3, getServiceRequest.zze);
        SafeParcelWriter.writeString(parcel, 4, getServiceRequest.zzf, false);
        SafeParcelWriter.writeIBinder(parcel, 5, getServiceRequest.zzg, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, getServiceRequest.zzh, i2, false);
        SafeParcelWriter.writeBundle(parcel, 7, getServiceRequest.zzi, false);
        SafeParcelWriter.writeParcelable(parcel, 8, getServiceRequest.zzj, i2, false);
        SafeParcelWriter.writeTypedArray(parcel, 10, getServiceRequest.zzk, i2, false);
        SafeParcelWriter.writeTypedArray(parcel, 11, getServiceRequest.zzl, i2, false);
        SafeParcelWriter.writeBoolean(parcel, 12, getServiceRequest.zzm);
        SafeParcelWriter.writeInt(parcel, 13, getServiceRequest.zzn);
        SafeParcelWriter.writeBoolean(parcel, 14, getServiceRequest.zzo);
        SafeParcelWriter.writeString(parcel, 15, getServiceRequest.zza(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public java.lang.Object createFromParcel(android.os.Parcel r23) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzn.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public Object[] newArray(int i2) {
        return new GetServiceRequest[i2];
    }
}
