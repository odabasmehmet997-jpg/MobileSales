package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.zzv;
import com.google.android.gms.location.zzw;
import com.google.android.gms.location.zzy;
import com.google.android.gms.location.zzz;

@SafeParcelable.Class
@SafeParcelable.Reserved
@Deprecated
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzei extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzei> CREATOR = new zzej();
    @SafeParcelable.Field
    private final int zza;
    @SafeParcelable.Field
    @Nullable
    private final zzeg zzb;
    @SafeParcelable.Field
    @Nullable
    private final zzz zzc;
    @SafeParcelable.Field
    @Nullable
    private final zzw zzd;
    @SafeParcelable.Field
    @Nullable
    private final PendingIntent zze;
    @SafeParcelable.Field
    @Nullable
    private final zzr zzf;
    @SafeParcelable.Field
    @Nullable
    private final String zzg;

    @SafeParcelable.Constructor
    zzei(@SafeParcelable.Param int i2, @SafeParcelable.Param @Nullable zzeg zzeg, @SafeParcelable.Param @Nullable IBinder iBinder, @SafeParcelable.Param @Nullable IBinder iBinder2, @SafeParcelable.Param @Nullable PendingIntent pendingIntent, @SafeParcelable.Param @Nullable IBinder iBinder3, @SafeParcelable.Param @Nullable String str) {
        this.zza = i2;
        this.zzb = zzeg;
        zzr zzr = null;
        this.zzc = null != iBinder ? zzy.zzb(iBinder) : null;
        this.zze = pendingIntent;
        this.zzd = null != iBinder2 ? zzv.zzb(iBinder2) : null;
        if (null != iBinder3) {
            IInterface queryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            zzr = queryLocalInterface instanceof zzr ? (zzr) queryLocalInterface : new zzp(iBinder3);
        }
        this.zzf = zzr;
        this.zzg = str;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        IBinder iBinder;
        IBinder iBinder2;
        int i3 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i2, false);
        zzz zzz = this.zzc;
        IBinder iBinder3 = null;
        if (null == zzz) {
            iBinder = null;
        } else {
            iBinder = zzz.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 3, iBinder, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zze, i2, false);
        zzw zzw = this.zzd;
        if (null == zzw) {
            iBinder2 = null;
        } else {
            iBinder2 = zzw.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 5, iBinder2, false);
        zzr zzr = this.zzf;
        if (null != zzr) {
            iBinder3 = zzr.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 6, iBinder3, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
