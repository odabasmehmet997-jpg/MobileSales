package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzp extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzp> CREATOR = new zzq();
    @SafeParcelable.Field
    @Nullable
    public final String zza;
    @SafeParcelable.Field
    @Nullable
    public final String zzb;
    @SafeParcelable.Field
    @Nullable
    public final String zzc;
    @SafeParcelable.Field
    @Nullable
    public final String zzd;
    @SafeParcelable.Field
    public final long zze;
    @SafeParcelable.Field
    public final long zzf;
    @SafeParcelable.Field
    @Nullable
    public final String zzg;
    @SafeParcelable.Field
    public final boolean zzh;
    @SafeParcelable.Field
    public final boolean zzi;
    @SafeParcelable.Field
    public final long zzj;
    @SafeParcelable.Field
    @Nullable
    public final String zzk;
    @SafeParcelable.Field
    public final long zzl;
    @SafeParcelable.Field
    public final long zzm;
    @SafeParcelable.Field
    public final int zzn;
    @SafeParcelable.Field
    public final boolean zzo;
    @SafeParcelable.Field
    public final boolean zzp;
    @SafeParcelable.Field
    @Nullable
    public final String zzq;
    @SafeParcelable.Field
    @Nullable
    public final Boolean zzr;
    @SafeParcelable.Field
    public final long zzs;
    @SafeParcelable.Field
    @Nullable
    public final List zzt;
    @SafeParcelable.Field
    @Nullable
    public final String zzu;
    @SafeParcelable.Field
    public final String zzv;

    zzp(@Nullable final String str, @Nullable final String str2, @Nullable final String str3, final long j2, @Nullable final String str4, final long j3, final long j4, @Nullable final String str5, final boolean z, final boolean z2, @Nullable final String str6, final long j5, final long j6, final int i2, final boolean z3, final boolean z4, @Nullable final String str7, @Nullable final Boolean bool, final long j7, @Nullable final List list, @Nullable final String str8, final String str9) {
        Preconditions.checkNotEmpty(str);
        zza = str;
        zzb = !TextUtils.isEmpty(str2) ? str2 : null;
        zzc = str3;
        zzj = j2;
        zzd = str4;
        zze = j3;
        zzf = j4;
        zzg = str5;
        zzh = z;
        zzi = z2;
        zzk = str6;
        zzl = j5;
        zzm = j6;
        zzn = i2;
        zzo = z3;
        zzp = z4;
        zzq = str7;
        zzr = bool;
        zzs = j7;
        zzt = list;
        zzu = null;
        zzv = str9;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, zza, false);
        SafeParcelWriter.writeString(parcel, 3, zzb, false);
        SafeParcelWriter.writeString(parcel, 4, zzc, false);
        SafeParcelWriter.writeString(parcel, 5, zzd, false);
        SafeParcelWriter.writeLong(parcel, 6, zze);
        SafeParcelWriter.writeLong(parcel, 7, zzf);
        SafeParcelWriter.writeString(parcel, 8, zzg, false);
        SafeParcelWriter.writeBoolean(parcel, 9, zzh);
        SafeParcelWriter.writeBoolean(parcel, 10, zzi);
        SafeParcelWriter.writeLong(parcel, 11, zzj);
        SafeParcelWriter.writeString(parcel, 12, zzk, false);
        SafeParcelWriter.writeLong(parcel, 13, zzl);
        SafeParcelWriter.writeLong(parcel, 14, zzm);
        SafeParcelWriter.writeInt(parcel, 15, zzn);
        SafeParcelWriter.writeBoolean(parcel, 16, zzo);
        SafeParcelWriter.writeBoolean(parcel, 18, zzp);
        SafeParcelWriter.writeString(parcel, 19, zzq, false);
        SafeParcelWriter.writeBooleanObject(parcel, 21, zzr, false);
        SafeParcelWriter.writeLong(parcel, 22, zzs);
        SafeParcelWriter.writeStringList(parcel, 23, zzt, false);
        SafeParcelWriter.writeString(parcel, 24, zzu, false);
        SafeParcelWriter.writeString(parcel, 25, zzv, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    zzp(@SafeParcelable.Param @Nullable final String str, @SafeParcelable.Param @Nullable final String str2, @SafeParcelable.Param @Nullable final String str3, @SafeParcelable.Param @Nullable final String str4, @SafeParcelable.Param final long j2, @SafeParcelable.Param final long j3, @SafeParcelable.Param @Nullable final String str5, @SafeParcelable.Param final boolean z, @SafeParcelable.Param final boolean z2, @SafeParcelable.Param final long j4, @SafeParcelable.Param @Nullable final String str6, @SafeParcelable.Param final long j5, @SafeParcelable.Param final long j6, @SafeParcelable.Param final int i2, @SafeParcelable.Param final boolean z3, @SafeParcelable.Param final boolean z4, @SafeParcelable.Param @Nullable final String str7, @SafeParcelable.Param @Nullable final Boolean bool, @SafeParcelable.Param final long j7, @SafeParcelable.Param @Nullable final List list, @SafeParcelable.Param @Nullable final String str8, @SafeParcelable.Param final String str9) {
        zza = str;
        zzb = str2;
        zzc = str3;
        zzj = j4;
        zzd = str4;
        zze = j2;
        zzf = j3;
        zzg = str5;
        zzh = z;
        zzi = z2;
        zzk = str6;
        zzl = j5;
        zzm = j6;
        zzn = i2;
        zzo = z3;
        zzp = z4;
        zzq = str7;
        zzr = bool;
        zzs = j7;
        zzt = list;
        zzu = str8;
        zzv = str9;
    }
}
