package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public final class zzcl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcl> CREATOR = new zzcm();
    @SafeParcelable.Field
    public final long zza;
    @SafeParcelable.Field
    public final long zzb;
    @SafeParcelable.Field
    public final boolean zzc;
    @SafeParcelable.Field
    @Nullable
    public final String zzd;
    @SafeParcelable.Field
    @Nullable
    public final String zze;
    @SafeParcelable.Field
    @Nullable
    public final String zzf;
    @SafeParcelable.Field
    @Nullable
    public final Bundle zzg;
    @SafeParcelable.Field
    @Nullable
    public final String zzh;

    @SafeParcelable.Constructor
    public zzcl(@SafeParcelable.Param long j2, @SafeParcelable.Param long j3, @SafeParcelable.Param boolean z, @SafeParcelable.Param @Nullable String str, @SafeParcelable.Param @Nullable String str2, @SafeParcelable.Param @Nullable String str3, @SafeParcelable.Param @Nullable Bundle bundle, @SafeParcelable.Param @Nullable String str4) {
        this.zza = j2;
        this.zzb = j3;
        this.zzc = z;
        this.zzd = str;
        this.zze = str2;
        this.zzf = str3;
        this.zzg = bundle;
        this.zzh = str4;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzh, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
