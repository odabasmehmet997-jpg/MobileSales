package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.WorkSource;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.core.location.LocationRequestCompat;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.internal.location.zze;
import com.google.android.gms.internal.location.zzeo;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<LocationRequest> CREATOR = new zzaf();
    @SafeParcelable.Field
    private int zza;
    @SafeParcelable.Field
    private long zzb;
    @SafeParcelable.Field
    private long zzc;
    @SafeParcelable.Field
    private final long zzd;
    @SafeParcelable.Field
    private final long zze;
    @SafeParcelable.Field
    private final int zzf;
    @SafeParcelable.Field
    private float zzg;
    @SafeParcelable.Field
    private final boolean zzh;
    @SafeParcelable.Field
    private long zzi;
    @SafeParcelable.Field
    private final int zzj;
    @SafeParcelable.Field
    private final int zzk;
    @SafeParcelable.Field
    private final boolean zzl;
    @SafeParcelable.Field
    private final WorkSource zzm;
    @SafeParcelable.Field
    @Nullable
    private final zze zzn;

    /*  WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LocationRequest() {
        /*
            r22 = this;
            r0 = r22
            android.os.WorkSource r1 = new android.os.WorkSource
            r20 = r1
            r1.<init>()
            r21 = 0
            r1 = 102(0x66, float:1.43E-43)
            r2 = 3600000(0x36ee80, double:1.7786363E-317)
            r4 = 600000(0x927c0, double:2.964394E-318)
            r6 = 0
            r10 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r8 = r10
            r12 = 2147483647(0x7fffffff, float:NaN)
            r13 = 0
            r14 = 1
            r15 = 3600000(0x36ee80, double:1.7786363E-317)
            r17 = 0
            r18 = 0
            r19 = 0
            r0.<init>(r1, r2, r4, r6, r8, r10, r12, r13, r14, r15, r17, r18, r19, r20, r21)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.LocationRequest.<init>():void");
    }

    @NonNull
    @Deprecated
    public static LocationRequest create() {
        final WorkSource workSource = r1;
        final WorkSource workSource2 = new WorkSource();
        return new LocationRequest(102, 3600000, 600000, 0, LocationRequestCompat.PASSIVE_INTERVAL, LocationRequestCompat.PASSIVE_INTERVAL, Integer.MAX_VALUE, 0.0f, true, 3600000, 0, 0, false, workSource, null);
    }

    private static String zze(final long j2) {
        return LocationRequestCompat.PASSIVE_INTERVAL == j2 ? "∞" : zzeo.zzb(j2);
    }

    public boolean equals(@Nullable final Object obj) {
        if (obj instanceof LocationRequest locationRequest) {
            return zza == locationRequest.zza && ((this.isPassive() || zzb == locationRequest.zzb) && zzc == locationRequest.zzc && this.isBatched() == locationRequest.isBatched() && ((!this.isBatched() || zzd == locationRequest.zzd) && zze == locationRequest.zze && zzf == locationRequest.zzf && zzg == locationRequest.zzg && zzh == locationRequest.zzh && zzj == locationRequest.zzj && zzk == locationRequest.zzk && zzl == locationRequest.zzl && zzm.equals(locationRequest.zzm) && Objects.equal(zzn, locationRequest.zzn)));
        }
        return false;
    }

    public long getDurationMillis() {
        return zze;
    }

    public int getGranularity() {
        return zzj;
    }

    public long getIntervalMillis() {
        return zzb;
    }

    public long getMaxUpdateAgeMillis() {
        return zzi;
    }

    public long getMaxUpdateDelayMillis() {
        return zzd;
    }

    public int getMaxUpdates() {
        return zzf;
    }

    public float getMinUpdateDistanceMeters() {
        return zzg;
    }

    public long getMinUpdateIntervalMillis() {
        return zzc;
    }

    public int getPriority() {
        return zza;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(zza), Long.valueOf(zzb), Long.valueOf(zzc), zzm);
    }

    public boolean isBatched() {
        final long j2 = zzd;
        return 0 < j2 && (j2 >> 1) >= zzb;
    }

    public boolean isPassive() {
        return 105 == this.zza;
    }

    public boolean isWaitForAccurateLocation() {
        return zzh;
    }

    @NonNull
    @Deprecated
    public LocationRequest setFastestInterval(final long j2) {
        Preconditions.checkArgument(0 <= j2, "illegal fastest interval: %d", Long.valueOf(j2));
        zzc = j2;
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setInterval(final long j2) {
        Preconditions.checkArgument(0 <= j2, "intervalMillis must be greater than or equal to 0");
        final long j3 = zzc;
        final long j4 = zzb;
        if (j3 == j4 / 6) {
            zzc = j2 / 6;
        }
        if (zzi == j4) {
            zzi = j2;
        }
        zzb = j2;
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setPriority(final int i2) {
        zzan.zza(i2);
        zza = i2;
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setSmallestDisplacement(final float f2) {
        if (0.0f <= f2) {
            zzg = f2;
            return this;
        }
        String sb = "invalid displacement: " +
                f2;
        throw new IllegalArgumentException(sb);
    }

    @NonNull
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Request[");
        if (this.isPassive()) {
            sb.append(zzan.zzb(zza));
            if (0 < this.zzd) {
                sb.append("/");
                zzeo.zzc(zzd, sb);
            }
        } else {
            sb.append("@");
            if (this.isBatched()) {
                zzeo.zzc(zzb, sb);
                sb.append("/");
                zzeo.zzc(zzd, sb);
            } else {
                zzeo.zzc(zzb, sb);
            }
            sb.append(" ");
            sb.append(zzan.zzb(zza));
        }
        if (this.isPassive() || zzc != zzb) {
            sb.append(", minUpdateInterval=");
            sb.append(LocationRequest.zze(zzc));
        }
        if (0.0d < ((double) this.zzg)) {
            sb.append(", minUpdateDistance=");
            sb.append(zzg);
        }
        if (!this.isPassive() ? zzi != zzb : LocationRequestCompat.PASSIVE_INTERVAL != this.zzi) {
            sb.append(", maxUpdateAge=");
            sb.append(LocationRequest.zze(zzi));
        }
        if (LocationRequestCompat.PASSIVE_INTERVAL != this.zze) {
            sb.append(", duration=");
            zzeo.zzc(zze, sb);
        }
        if (Integer.MAX_VALUE != this.zzf) {
            sb.append(", maxUpdates=");
            sb.append(zzf);
        }
        if (0 != this.zzk) {
            sb.append(", ");
            sb.append(zzar.zzb(zzk));
        }
        if (0 != this.zzj) {
            sb.append(", ");
            sb.append(zzq.zzb(zzj));
        }
        if (zzh) {
            sb.append(", waitForAccurateLocation");
        }
        if (zzl) {
            sb.append(", bypass");
        }
        if (!WorkSourceUtil.isEmpty(zzm)) {
            sb.append(", ");
            sb.append(zzm);
        }
        if (null != this.zzn) {
            sb.append(", impersonation=");
            sb.append(zzn);
        }
        sb.append(']');
        return sb.toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeLong(parcel, 3, this.zzc);
        SafeParcelWriter.writeInt(parcel, 6, this.zzf);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzg);
        SafeParcelWriter.writeLong(parcel, 8, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzh);
        SafeParcelWriter.writeLong(parcel, 10, this.zze);
        SafeParcelWriter.writeLong(parcel, 11, this.zzi);
        SafeParcelWriter.writeInt(parcel, 12, this.zzj);
        SafeParcelWriter.writeInt(parcel, 13, zzk);
        SafeParcelWriter.writeBoolean(parcel, 15, zzl);
        SafeParcelWriter.writeParcelable(parcel, 16, zzm, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 17, zzn, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public int zza() {
        return zzk;
    }

    public boolean zzb() {
        return zzl;
    }

    @NonNull
    public WorkSource zzc() {
        return zzm;
    }

    @Nullable
    public zze zzd() {
        return zzn;
    }

    /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
    public static final class Builder {
        private int zza;
        private long zzb;
        private long zzc;
        private long zzd;
        private long zze;
        private int zzf;
        private float zzg;
        private boolean zzh;
        private long zzi;
        private int zzj;
        private int zzk;
        private boolean zzl;
        @Nullable
        private WorkSource zzm;
        @Nullable
        private zze zzn;

        public Builder(final int i2, final long j2) {
            this(j2);
            this.setPriority(i2);
        }

        @NonNull
        public LocationRequest build() {
            final int i2 = zza;
            final long j2 = zzb;
            long j3 = zzc;
            if (-1 == j3) {
                j3 = j2;
            } else if (105 != i2) {
                j3 = Math.min(j3, j2);
            }
            final long max = Math.max(zzd, zzb);
            final long j4 = zze;
            final int i3 = zzf;
            final float f2 = zzg;
            final boolean z = zzh;
            final long j5 = zzi;
            final long j6 = -1 == j5 ? zzb : j5;
            final int i4 = zzj;
            final int i5 = zzk;
            final boolean z2 = zzl;
            final WorkSource workSource = r7;
            final WorkSource workSource2 = new WorkSource(zzm);
            return new LocationRequest(i2, j2, j3, max, LocationRequestCompat.PASSIVE_INTERVAL, j4, i3, f2, z, j6, i4, i5, z2, workSource, zzn);
        }

        @NonNull
        public Builder setDurationMillis(final long j2) {
            Preconditions.checkArgument(0 < j2, "durationMillis must be greater than 0");
            zze = j2;
            return this;
        }

        @NonNull
        public Builder setGranularity(final int i2) {
            zzq.zza(i2);
            zzj = i2;
            return this;
        }

        @NonNull
        public Builder setIntervalMillis(final long j2) {
            Preconditions.checkArgument(0 <= j2, "intervalMillis must be greater than or equal to 0");
            zzb = j2;
            return this;
        }

        @NonNull
        public Builder setMaxUpdateAgeMillis(final long j2) {
            boolean z = -1 == j2 || 0 <= j2;
            Preconditions.checkArgument(z, "maxUpdateAgeMillis must be greater than or equal to 0, or IMPLICIT_MAX_UPDATE_AGE");
            zzi = j2;
            return this;
        }

        @NonNull
        public Builder setMaxUpdateDelayMillis(final long j2) {
            Preconditions.checkArgument(0 <= j2, "maxUpdateDelayMillis must be greater than or equal to 0");
            zzd = j2;
            return this;
        }

        @NonNull
        public Builder setMaxUpdates(final int i2) {
            Preconditions.checkArgument(0 < i2, "maxUpdates must be greater than 0");
            zzf = i2;
            return this;
        }

        @NonNull
        public Builder setMinUpdateDistanceMeters(final float f2) {
            Preconditions.checkArgument(0.0f <= f2, "minUpdateDistanceMeters must be greater than or equal to 0");
            zzg = f2;
            return this;
        }

        @NonNull
        public Builder setMinUpdateIntervalMillis(final long j2) {
            boolean z = -1 == j2 || 0 <= j2;
            Preconditions.checkArgument(z, "minUpdateIntervalMillis must be greater than or equal to 0, or IMPLICIT_MIN_UPDATE_INTERVAL");
            zzc = j2;
            return this;
        }

        @NonNull
        public Builder setPriority(final int i2) {
            zzan.zza(i2);
            zza = i2;
            return this;
        }

        @NonNull
        public Builder setWaitForAccurateLocation(final boolean z) {
            zzh = z;
            return this;
        }

        @NonNull
        public Builder zza(final int i2) {
            zzar.zza(i2);
            zzk = i2;
            return this;
        }

        @RequiresPermission(anyOf = {"android.permission.WRITE_SECURE_SETTINGS", "android.permission.LOCATION_BYPASS"})
        @NonNull
        public Builder zzb(final boolean z) {
            zzl = z;
            return this;
        }

        @RequiresPermission("android.permission.UPDATE_DEVICE_STATS")
        @NonNull
        public Builder zzc(@Nullable final WorkSource workSource) {
            zzm = workSource;
            return this;
        }

        public Builder(final long j2) {
            zza = 102;
            zzc = -1;
            zzd = 0;
            zze = LocationRequestCompat.PASSIVE_INTERVAL;
            zzf = Integer.MAX_VALUE;
            zzg = 0.0f;
            zzh = true;
            zzi = -1;
            zzj = 0;
            zzk = 0;
            zzl = false;
            zzm = null;
            zzn = null;
            this.setIntervalMillis(j2);
        }

        public Builder(@NonNull final LocationRequest locationRequest) {
            this(locationRequest.getPriority(), locationRequest.getIntervalMillis());
            this.setMinUpdateIntervalMillis(locationRequest.getMinUpdateIntervalMillis());
            this.setMaxUpdateDelayMillis(locationRequest.getMaxUpdateDelayMillis());
            this.setDurationMillis(locationRequest.getDurationMillis());
            this.setMaxUpdates(locationRequest.getMaxUpdates());
            this.setMinUpdateDistanceMeters(locationRequest.getMinUpdateDistanceMeters());
            this.setWaitForAccurateLocation(locationRequest.isWaitForAccurateLocation());
            this.setMaxUpdateAgeMillis(locationRequest.getMaxUpdateAgeMillis());
            this.setGranularity(locationRequest.getGranularity());
            final int zza2 = locationRequest.zza();
            zzar.zza(zza2);
            zzk = zza2;
            zzl = locationRequest.zzb();
            zzm = locationRequest.zzc();
            final zze zzd2 = locationRequest.zzd();
            boolean z = null == zzd2 || !zzd2.zza();
            Preconditions.checkArgument(z);
            zzn = zzd2;
        }
    }

    @SafeParcelable.Constructor
    LocationRequest(@SafeParcelable.Param final int i2, @SafeParcelable.Param final long j2, @SafeParcelable.Param final long j3, @SafeParcelable.Param final long j4, @SafeParcelable.RemovedParam final long j5, @SafeParcelable.Param final long j6, @SafeParcelable.Param final int i3, @SafeParcelable.Param final float f2, @SafeParcelable.Param final boolean z, @SafeParcelable.Param final long j7, @SafeParcelable.Param final int i4, @SafeParcelable.Param final int i5, @SafeParcelable.Param final boolean z2, @SafeParcelable.Param final WorkSource workSource, @SafeParcelable.Param @Nullable final zze zze2) {
        final long j8;
        final long j9;
        final int i6 = i2;
        zza = i6;
        if (105 == i6) {
            zzb = LocationRequestCompat.PASSIVE_INTERVAL;
            j8 = j2;
        } else {
            j8 = j2;
            zzb = j8;
        }
        zzc = j3;
        zzd = j4;
        if (LocationRequestCompat.PASSIVE_INTERVAL == j5) {
            j9 = j6;
        } else {
            j9 = Math.min(Math.max(1, j5 - SystemClock.elapsedRealtime()), j6);
        }
        zze = j9;
        zzf = i3;
        zzg = f2;
        zzh = z;
        zzi = -1 != j7 ? j7 : j8;
        zzj = i4;
        zzk = i5;
        zzl = z2;
        zzm = workSource;
        zzn = zze2;
    }
}
