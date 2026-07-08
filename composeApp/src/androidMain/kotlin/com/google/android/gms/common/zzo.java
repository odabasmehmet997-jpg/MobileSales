package com.google.android.gms.common;

import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();
    @SafeParcelable.Field
    private final String zza;
    @SafeParcelable.Field
    private final boolean zzb;
    @SafeParcelable.Field
    private final boolean zzc;
    @SafeParcelable.Field
    private final Context zzd;
    @SafeParcelable.Field
    private final boolean zze;
    @SafeParcelable.Field
    private final boolean zzf;

    @SafeParcelable.Constructor
    zzo(@SafeParcelable.Param final String str, @SafeParcelable.Param final boolean z, @SafeParcelable.Param final boolean z2, @SafeParcelable.Param final IBinder iBinder, @SafeParcelable.Param final boolean z3, @SafeParcelable.Param final boolean z4) {
        zza = str;
        zzb = z;
        zzc = z2;
        zzd = ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder));
        zze = z3;
        zzf = z4;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final String str = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, str, false);
        SafeParcelWriter.writeBoolean(parcel, 2, zzb);
        SafeParcelWriter.writeBoolean(parcel, 3, zzc);
        SafeParcelWriter.writeIBinder(parcel, 4, ObjectWrapper.wrap(zzd), false);
        SafeParcelWriter.writeBoolean(parcel, 5, zze);
        SafeParcelWriter.writeBoolean(parcel, 6, zzf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
