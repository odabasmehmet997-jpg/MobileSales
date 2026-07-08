package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzee extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzee> CREATOR = new zzef();
    @SafeParcelable.Field
    private final int zza;
    @SafeParcelable.Field
    @Nullable
    private final IBinder zzb;
    @SafeParcelable.Field
    @Nullable
    private final IBinder zzc;
    @SafeParcelable.Field
    @Nullable
    private final PendingIntent zzd;
    @SafeParcelable.Field
    @Nullable
    private final String zze;

    @SafeParcelable.Constructor
    zzee(@SafeParcelable.Param final int i2, @SafeParcelable.Param @Nullable final IBinder iBinder, @SafeParcelable.Param @Nullable final IBinder iBinder2, @SafeParcelable.Param @Nullable final PendingIntent pendingIntent, @SafeParcelable.Param @Nullable final String str) {
        zza = i2;
        zzb = iBinder;
        zzc = iBinder2;
        zzd = pendingIntent;
        zze = str;
    }


    public static com.google.android.gms.internal.location.zzee zza(@androidx.annotation.Nullable final android.os.IInterface r7, final com.google.android.gms.location.zzz r8, final java.lang.String r9) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzee.zza(android.os.IInterface, com.google.android.gms.location.zzz, java.lang.String):com.google.android.gms.internal.location.zzee");
    }

    public static com.google.android.gms.internal.location.zzee zzb(@androidx.annotation.Nullable final android.os.IInterface r7, final com.google.android.gms.location.zzw r8, final java.lang.String r9) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzee.zzb(android.os.IInterface, com.google.android.gms.location.zzw, java.lang.String):com.google.android.gms.internal.location.zzee");
    }

    public static zzee zzc(final PendingIntent pendingIntent) {
        return new zzee(3, null, null, pendingIntent, null);
    }

    public static com.google.android.gms.internal.location.zzee zzd(final com.google.android.gms.internal.location.zzz r7) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzee.zzd(com.google.android.gms.internal.location.zzz):com.google.android.gms.internal.location.zzee");
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final int i3 = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeIBinder(parcel, 2, zzb, false);
        SafeParcelWriter.writeIBinder(parcel, 3, zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, zzd, i2, false);
        SafeParcelWriter.writeString(parcel, 6, zze, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
