package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@KeepForSdk
@SafeParcelable.Class
@Deprecated
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class WakeLockEvent extends StatsEvent {
    @NonNull
    public static final Parcelable.Creator<WakeLockEvent> CREATOR = new zza();
    @SafeParcelable.VersionField
    final int zza;
    @SafeParcelable.Field
    private final long zzb;
    @SafeParcelable.Field
    private final int zzc;
    @SafeParcelable.Field
    private final String zzd;
    @SafeParcelable.Field
    private final String zze;
    @SafeParcelable.Field
    private final String zzf;
    @SafeParcelable.Field
    private final int zzg;
    @SafeParcelable.Field
    private final List zzh;
    @SafeParcelable.Field
    private final String zzi;
    @SafeParcelable.Field
    private final long zzj;
    @SafeParcelable.Field
    private final int zzk;
    @SafeParcelable.Field
    private final String zzl;
    @SafeParcelable.Field
    private final float zzm;
    @SafeParcelable.Field
    private final long zzn;
    @SafeParcelable.Field
    private final boolean zzo;

    @SafeParcelable.Constructor
    WakeLockEvent(@SafeParcelable.Param final int i2, @SafeParcelable.Param final long j2, @SafeParcelable.Param final int i3, @SafeParcelable.Param final String str, @SafeParcelable.Param final int i4, @SafeParcelable.Param final List list, @SafeParcelable.Param final String str2, @SafeParcelable.Param final long j3, @SafeParcelable.Param final int i5, @SafeParcelable.Param final String str3, @SafeParcelable.Param final String str4, @SafeParcelable.Param final float f2, @SafeParcelable.Param final long j4, @SafeParcelable.Param final String str5, @SafeParcelable.Param final boolean z) {
        zza = i2;
        zzb = j2;
        zzc = i3;
        zzd = str;
        zze = str3;
        zzf = str5;
        zzg = i4;
        zzh = list;
        zzi = str2;
        zzj = j3;
        zzk = i5;
        zzl = str4;
        zzm = f2;
        zzn = j4;
        zzo = z;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, zza);
        SafeParcelWriter.writeLong(parcel, 2, zzb);
        SafeParcelWriter.writeString(parcel, 4, zzd, false);
        SafeParcelWriter.writeInt(parcel, 5, zzg);
        SafeParcelWriter.writeStringList(parcel, 6, zzh, false);
        SafeParcelWriter.writeLong(parcel, 8, zzj);
        SafeParcelWriter.writeString(parcel, 10, zze, false);
        SafeParcelWriter.writeInt(parcel, 11, zzc);
        SafeParcelWriter.writeString(parcel, 12, zzi, false);
        SafeParcelWriter.writeString(parcel, 13, zzl, false);
        SafeParcelWriter.writeInt(parcel, 14, zzk);
        SafeParcelWriter.writeFloat(parcel, 15, zzm);
        SafeParcelWriter.writeLong(parcel, 16, zzn);
        SafeParcelWriter.writeString(parcel, 17, zzf, false);
        SafeParcelWriter.writeBoolean(parcel, 18, zzo);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public int zza() {
        return zzc;
    }

    public long zzb() {
        return zzb;
    }

    @NonNull
    public String zzc() {
        final String str;
        final List list = zzh;
        String str2 = "";
        if (null == list) {
            str = str2;
        } else {
            str = TextUtils.join(",", list);
        }
        final int i2 = zzk;
        String str3 = zze;
        String str4 = zzl;
        final float f2 = zzm;
        final String str5 = zzf;
        final int i3 = zzg;
        final String str6 = zzd;
        final boolean z = zzo;
        final StringBuilder sb = new StringBuilder();
        sb.append("\t");
        sb.append(str6);
        sb.append("\t");
        sb.append(i3);
        sb.append("\t");
        sb.append(str);
        sb.append("\t");
        sb.append(i2);
        sb.append("\t");
        if (null == str3) {
            str3 = str2;
        }
        sb.append(str3);
        sb.append("\t");
        if (null == str4) {
            str4 = str2;
        }
        sb.append(str4);
        sb.append("\t");
        sb.append(f2);
        sb.append("\t");
        if (null != str5) {
            str2 = str5;
        }
        sb.append(str2);
        sb.append("\t");
        sb.append(z);
        return sb.toString();
    }
}
