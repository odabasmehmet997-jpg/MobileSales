package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.location.zzer;
import java.util.Arrays;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class DeviceOrientation extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<DeviceOrientation> CREATOR = new zzm();
    @SafeParcelable.Field
    private final float[] zza;
    @SafeParcelable.Field
    private final float zzb;
    @SafeParcelable.Field
    private final float zzc;
    @SafeParcelable.Field
    private final long zzd;
    @SafeParcelable.Field
    private final byte zze;
    @SafeParcelable.Field
    private final float zzf;
    @SafeParcelable.Field
    private final float zzg;

    /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
    public static final class Builder {
    }

    @SafeParcelable.Constructor
    DeviceOrientation(@SafeParcelable.Param final float[] fArr, @SafeParcelable.Param final float f2, @SafeParcelable.Param final float f3, @SafeParcelable.Param final long j2, @SafeParcelable.Param final byte b2, @SafeParcelable.Param final float f4, @SafeParcelable.Param final float f5) {
        DeviceOrientation.zzj(fArr);
        final boolean z = true;
        zzer.zza(0.0f <= f2 && 360.0f > f2);
        zzer.zza(0.0f <= f3 && 180.0f >= f3);
        zzer.zza(0.0f <= f5 && 180.0f >= f5);
        zzer.zza(0 <= j2 && z);
        zza = fArr;
        zzb = f2;
        zzc = f3;
        zzf = f4;
        zzg = f5;
        zzd = j2;
        zze = (byte) (((byte) (((byte) (b2 | 16)) | 4)) | 8);
    }

    private static void zzj(final float[] fArr) {
        boolean z = true;
        zzer.zzb(null != fArr && 4 == fArr.length, "Input attitude array should be of length 4.");
        if (Float.isNaN(fArr[0]) || Float.isNaN(fArr[1]) || Float.isNaN(fArr[2]) || Float.isNaN(fArr[3])) {
            z = false;
        }
        zzer.zzb(z, "Input attitude cannot contain NaNs.");
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceOrientation deviceOrientation)) {
            return false;
        }
        return 0 == Float.compare(this.zzb, deviceOrientation.zzb) && 0 == Float.compare(this.zzc, deviceOrientation.zzc) && (this.zza() == deviceOrientation.zza() && (!this.zza() || 0 == Float.compare(this.zzf, deviceOrientation.zzf))) && (this.hasConservativeHeadingErrorDegrees() == deviceOrientation.hasConservativeHeadingErrorDegrees() && (!this.hasConservativeHeadingErrorDegrees() || 0 == Float.compare(getConservativeHeadingErrorDegrees(), deviceOrientation.getConservativeHeadingErrorDegrees()))) && zzd == deviceOrientation.zzd && Arrays.equals(zza, deviceOrientation.zza);
    }

    @NonNull
    public float[] getAttitude() {
        return zza.clone();
    }

    public float getConservativeHeadingErrorDegrees() {
        return zzg;
    }

    public long getElapsedRealtimeNs() {
        return zzd;
    }

    public float getHeadingDegrees() {
        return zzb;
    }

    public float getHeadingErrorDegrees() {
        return zzc;
    }

    public boolean hasConservativeHeadingErrorDegrees() {
        return 0 != (this.zze & 64);
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(zzb), Float.valueOf(zzc), Float.valueOf(zzg), Long.valueOf(zzd), zza, Byte.valueOf(zze));
    }

    @NonNull
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeviceOrientation[");
        sb.append("attitude=");
        sb.append(Arrays.toString(zza));
        sb.append(", headingDegrees=");
        sb.append(zzb);
        sb.append(", headingErrorDegrees=");
        sb.append(zzc);
        if (this.hasConservativeHeadingErrorDegrees()) {
            sb.append(", conservativeHeadingErrorDegrees=");
            sb.append(zzg);
        }
        sb.append(", elapsedRealtimeNs=");
        sb.append(zzd);
        sb.append(']');
        return sb.toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloatArray(parcel, 1, this.getAttitude(), false);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzb);
        SafeParcelWriter.writeFloat(parcel, 5, this.zzc);
        SafeParcelWriter.writeLong(parcel, 6, this.zzd);
        SafeParcelWriter.writeByte(parcel, 7, zze);
        SafeParcelWriter.writeFloat(parcel, 8, zzf);
        SafeParcelWriter.writeFloat(parcel, 9, this.zzg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean zza() {
        return 0 != (this.zze & 32);
    }
}
