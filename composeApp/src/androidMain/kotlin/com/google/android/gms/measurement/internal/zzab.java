package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzab extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzab> CREATOR = new zzac();
    @SafeParcelable.Field
    @Nullable
    public String zza;
    @SafeParcelable.Field
    public String zzb;
    @SafeParcelable.Field
    public zzku zzc;
    @SafeParcelable.Field
    public long zzd;
    @SafeParcelable.Field
    public boolean zze;
    @SafeParcelable.Field
    @Nullable
    public String zzf;
    @SafeParcelable.Field
    @Nullable
    public final zzau zzg;
    @SafeParcelable.Field
    public long zzh;
    @SafeParcelable.Field
    @Nullable
    public zzau zzi;
    @SafeParcelable.Field
    public final long zzj;
    @SafeParcelable.Field
    @Nullable
    public final zzau zzk;

    zzab(zzab zzab) {
        Preconditions.checkNotNull(zzab);
        this.zza = zzab.zza;
        this.zzb = zzab.zzb;
        this.zzc = zzab.zzc;
        this.zzd = zzab.zzd;
        this.zze = zzab.zze;
        this.zzf = zzab.zzf;
        this.zzg = zzab.zzg;
        this.zzh = zzab.zzh;
        this.zzi = zzab.zzi;
        this.zzj = zzab.zzj;
        this.zzk = zzab.zzk;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i2, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zze);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i2, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzh);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzi, i2, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzk, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    zzab(@SafeParcelable.Param @Nullable String str, @SafeParcelable.Param String str2, @SafeParcelable.Param zzku zzku, @SafeParcelable.Param long j2, @SafeParcelable.Param boolean z, @SafeParcelable.Param @Nullable String str3, @SafeParcelable.Param @Nullable zzau zzau, @SafeParcelable.Param long j3, @SafeParcelable.Param @Nullable zzau zzau2, @SafeParcelable.Param long j4, @SafeParcelable.Param @Nullable zzau zzau3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzku;
        this.zzd = j2;
        this.zze = z;
        this.zzf = str3;
        this.zzg = zzau;
        this.zzh = j3;
        this.zzi = zzau2;
        this.zzj = j4;
        this.zzk = zzau3;
    }
}
