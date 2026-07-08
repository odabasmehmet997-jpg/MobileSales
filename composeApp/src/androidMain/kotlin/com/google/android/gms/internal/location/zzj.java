package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.zzs;
import com.google.android.gms.location.zzt;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = new zzk();
    @SafeParcelable.Field
    final int zza;
    @SafeParcelable.Field
    @Nullable
    final zzh zzb;
    @SafeParcelable.Field
    @Nullable
    final zzt zzc;
    @SafeParcelable.Field
    @Nullable
    final zzr zzd;

    @SafeParcelable.Constructor
    zzj(@SafeParcelable.Param int i2, @SafeParcelable.Param @Nullable zzh zzh, @SafeParcelable.Param IBinder iBinder, @SafeParcelable.Param @Nullable IBinder iBinder2) {
        zzt zzt;
        this.zza = i2;
        this.zzb = zzh;
        zzr zzr = null;
        if (null == iBinder) {
            zzt = null;
        } else {
            zzt = zzs.zzb(iBinder);
        }
        this.zzc = zzt;
        if (null != iBinder2) {
            IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            zzr = queryLocalInterface instanceof zzr ? (zzr) queryLocalInterface : new zzp(iBinder2);
        }
        this.zzd = zzr;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        IBinder iBinder;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i2, false);
        zzt zzt = this.zzc;
        IBinder iBinder2 = null;
        if (null == zzt) {
            iBinder = null;
        } else {
            iBinder = zzt.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 3, iBinder, false);
        zzr zzr = this.zzd;
        if (null != zzr) {
            iBinder2 = zzr.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 4, iBinder2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
