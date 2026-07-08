package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.location.LocationRequestCompat;
import androidx.work.WorkRequest;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.internal.location.zze;
import com.google.android.gms.internal.location.zzeo;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class CurrentLocationRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<CurrentLocationRequest> CREATOR = new zzj();
    @SafeParcelable.Field
    private final long zza;
    @SafeParcelable.Field
    private final int zzb;
    @SafeParcelable.Field
    private final int zzc;
    @SafeParcelable.Field
    private final long zzd;
    @SafeParcelable.Field
    private final boolean zze;
    @SafeParcelable.Field
    private final int zzf;
    @SafeParcelable.Field
    private final WorkSource zzg;
    @SafeParcelable.Field
    @Nullable
    private final zze zzh;

    /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
    public static final class Builder {
        private final long zza = WorkRequest.MIN_BACKOFF_MILLIS;
        private final int zzb;
        private final int zzc = 102;
        private final long zzd = LocationRequestCompat.PASSIVE_INTERVAL;
        private final boolean zze = false;
        private final int zzf = 0;
        @Nullable
        private final WorkSource zzg = null;
        @Nullable
        private final zze zzh = null;
    }

    @SafeParcelable.Constructor
    CurrentLocationRequest(@SafeParcelable.Param final long j2, @SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3, @SafeParcelable.Param final long j3, @SafeParcelable.Param final boolean z, @SafeParcelable.Param final int i4, @SafeParcelable.Param final WorkSource workSource, @SafeParcelable.Param @Nullable final zze zze2) {
        zza = j2;
        zzb = i2;
        zzc = i3;
        zzd = j3;
        zze = z;
        zzf = i4;
        zzg = workSource;
        zzh = zze2;
    }

    public boolean equals(@Nullable final Object obj) {
        if (!(obj instanceof CurrentLocationRequest currentLocationRequest)) {
            return false;
        }
        return zza == currentLocationRequest.zza && zzb == currentLocationRequest.zzb && zzc == currentLocationRequest.zzc && zzd == currentLocationRequest.zzd && zze == currentLocationRequest.zze && zzf == currentLocationRequest.zzf && Objects.equal(zzg, currentLocationRequest.zzg) && Objects.equal(zzh, currentLocationRequest.zzh);
    }

    public long getDurationMillis() {
        return zzd;
    }

    public int getGranularity() {
        return zzb;
    }

    public long getMaxUpdateAgeMillis() {
        return zza;
    }

    public int getPriority() {
        return zzc;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(zza), Integer.valueOf(zzb), Integer.valueOf(zzc), Long.valueOf(zzd));
    }

    @NonNull
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CurrentLocationRequest[");
        sb.append(zzan.zzb(zzc));
        if (LocationRequestCompat.PASSIVE_INTERVAL != this.zza) {
            sb.append(", maxAge=");
            zzeo.zzc(zza, sb);
        }
        if (LocationRequestCompat.PASSIVE_INTERVAL != this.zzd) {
            sb.append(", duration=");
            sb.append(zzd);
            sb.append("ms");
        }
        if (0 != this.zzb) {
            sb.append(", ");
            sb.append(zzq.zzb(zzb));
        }
        if (zze) {
            sb.append(", bypass");
        }
        if (0 != this.zzf) {
            sb.append(", ");
            sb.append(zzar.zzb(zzf));
        }
        if (!WorkSourceUtil.isEmpty(zzg)) {
            sb.append(", workSource=");
            sb.append(zzg);
        }
        if (null != this.zzh) {
            sb.append(", impersonation=");
            sb.append(zzh);
        }
        sb.append(']');
        return sb.toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeLong(parcel, 4, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 5, zze);
        SafeParcelWriter.writeParcelable(parcel, 6, zzg, i2, false);
        SafeParcelWriter.writeInt(parcel, 7, zzf);
        SafeParcelWriter.writeParcelable(parcel, 9, zzh, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public boolean zza() {
        return zze;
    }

    public int zzb() {
        return zzf;
    }

    @NonNull
    public WorkSource zzc() {
        return zzg;
    }
}
