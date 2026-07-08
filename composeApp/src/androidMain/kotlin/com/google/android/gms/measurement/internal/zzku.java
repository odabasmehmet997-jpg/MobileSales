package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzku extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzku> CREATOR = new zzkv();
    @SafeParcelable.Field
    public final int zza;
    @SafeParcelable.Field
    public final String zzb;
    @SafeParcelable.Field
    public final long zzc;
    @SafeParcelable.Field
    @Nullable
    public final Long zzd;
    @SafeParcelable.Field
    @Nullable
    public final String zze;
    @SafeParcelable.Field
    public final String zzf;
    @SafeParcelable.Field
    @Nullable
    public final Double zzg;

    @SafeParcelable.Constructor
    zzku(@SafeParcelable.Param int i2, @SafeParcelable.Param String str, @SafeParcelable.Param long j2, @SafeParcelable.Param @Nullable Long l, @SafeParcelable.Param Float f2, @SafeParcelable.Param @Nullable String str2, @SafeParcelable.Param String str3, @SafeParcelable.Param @Nullable Double d2) {
        this.zza = i2;
        this.zzb = str;
        this.zzc = j2;
        this.zzd = l;
        if (1 == i2) {
            this.zzg = null != f2 ? Double.valueOf(f2.doubleValue()) : null;
        } else {
            this.zzg = d2;
        }
        this.zze = str2;
        this.zzf = str3;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        zzkv.zza(this, parcel, i2);
    }

    @Nullable
    public Object zza() {
        Long l = this.zzd;
        if (null != l) {
            return l;
        }
        Double d2 = this.zzg;
        if (null != d2) {
            return d2;
        }
        String str = this.zze;
        return str;
    }

    zzku(zzkw zzkw) {
        this(zzkw.zzc(), zzkw.zzd(), zzkw.zze(), zzkw.zzb());
    }

    zzku(String str, long j2, @Nullable Object obj, String str2) {
        Preconditions.checkNotEmpty(str);
        this.zza = 2;
        this.zzb = str;
        this.zzc = j2;
        this.zzf = str2;
        if (null == obj) {
            this.zzd = null;
            this.zzg = null;
            this.zze = null;
        } else if (obj instanceof Long) {
            this.zzd = (Long) obj;
            this.zzg = null;
            this.zze = null;
        } else if (obj instanceof String) {
            this.zzd = null;
            this.zzg = null;
            this.zze = (String) obj;
        } else if (obj instanceof Double) {
            this.zzd = null;
            this.zzg = (Double) obj;
            this.zze = null;
        } else {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
    }
}
