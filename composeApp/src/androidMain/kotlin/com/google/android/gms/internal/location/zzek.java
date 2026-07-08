package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzek extends AbstractSafeParcelable implements Geofence {
    public static final Parcelable.Creator<zzek> CREATOR = new zzel();
    @SafeParcelable.Field
    private final String zza;
    @SafeParcelable.Field
    private final long zzb;
    @SafeParcelable.Field
    private final short zzc;
    @SafeParcelable.Field
    private final double zzd;
    @SafeParcelable.Field
    private final double zze;
    @SafeParcelable.Field
    private final float zzf;
    @SafeParcelable.Field
    private final int zzg;
    @SafeParcelable.Field
    private final int zzh;
    @SafeParcelable.Field
    private final int zzi;

    @SafeParcelable.Constructor
    public zzek(@SafeParcelable.Param final String str, @SafeParcelable.Param final int i2, @SafeParcelable.Param final short s, @SafeParcelable.Param final double d2, @SafeParcelable.Param final double d3, @SafeParcelable.Param final float f2, @SafeParcelable.Param final long j2, @SafeParcelable.Param final int i3, @SafeParcelable.Param final int i4) {
        if (null == str || 100 < str.length()) {
            throw new IllegalArgumentException("requestId is null or too long: " + str);
        } else if (0.0f >= f2) {
            String sb = "invalid radius: " +
                    f2;
            throw new IllegalArgumentException(sb);
        } else if (90.0d < d2 || -90.0d > d2) {
            String sb2 = "invalid latitude: " +
                    d2;
            throw new IllegalArgumentException(sb2);
        } else if (180.0d < d3 || -180.0d > d3) {
            String sb3 = "invalid longitude: " +
                    d3;
            throw new IllegalArgumentException(sb3);
        } else {
            final int i5 = i2 & 7;
            if (0 != i5) {
                zzc = s;
                zza = str;
                zzd = d2;
                zze = d3;
                zzf = f2;
                zzb = j2;
                zzg = i5;
                zzh = i3;
                zzi = i4;
                return;
            }
            String sb4 = "No supported transition specified: " +
                    i2;
            throw new IllegalArgumentException(sb4);
        }
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzek zzek) {
            return zzf == zzek.zzf && zzd == zzek.zzd && zze == zzek.zze && zzc == zzek.zzc && zzg == zzek.zzg;
        }
    }

    public int hashCode() {
        final long doubleToLongBits = Double.doubleToLongBits(zzd);
        final long j2 = doubleToLongBits ^ (doubleToLongBits >>> 32);
        final long doubleToLongBits2 = Double.doubleToLongBits(zze);
        return ((((((((((int) j2) + 31) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(zzf)) * 31) + zzc) * 31) + zzg;
    }

    public String toString() {
        final short s = zzc;
        return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", -1 != s ? 1 != s ? "UNKNOWN" : "CIRCLE" : "INVALID", zza.replaceAll("\\p{C}", "?"), Integer.valueOf(zzg), Double.valueOf(zzd), Double.valueOf(zze), Float.valueOf(zzf), Integer.valueOf(zzh / 1000), Integer.valueOf(zzi), Long.valueOf(zzb));
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final String str = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, str, false);
        SafeParcelWriter.writeLong(parcel, 2, zzb);
        SafeParcelWriter.writeShort(parcel, 3, zzc);
        SafeParcelWriter.writeDouble(parcel, 4, zzd);
        SafeParcelWriter.writeDouble(parcel, 5, zze);
        SafeParcelWriter.writeFloat(parcel, 6, zzf);
        SafeParcelWriter.writeInt(parcel, 7, zzg);
        SafeParcelWriter.writeInt(parcel, 8, zzh);
        SafeParcelWriter.writeInt(parcel, 9, zzi);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
