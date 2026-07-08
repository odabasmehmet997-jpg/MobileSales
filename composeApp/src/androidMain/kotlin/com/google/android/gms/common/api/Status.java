package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;

public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<Status> CREATOR = new zzb();

    public static final Status RESULT_CANCELED = new Status(16);
    public static final Status RESULT_DEAD_CLIENT = new Status(18);
    public static final Status RESULT_INTERNAL_ERROR = new Status(8);
    public static final Status RESULT_INTERRUPTED = new Status(14);
    public static final Status RESULT_SUCCESS = new Status(0);
    public static final Status RESULT_SUCCESS_CACHE = new Status(-1);
    public static final Status RESULT_TIMEOUT = new Status(15);
    public static final Status zza = new Status(17);
    private final int zzb;
    private final String zzc;
    private final PendingIntent zzd;
    private final ConnectionResult zze;

    public Status(final int i2) {
        this(i2, null);
    }
    Status(@SafeParcelable.Param final int i2, @SafeParcelable.Param @Nullable final String str, @SafeParcelable.Param @Nullable final PendingIntent pendingIntent, @SafeParcelable.Param @Nullable final ConnectionResult connectionResult) {
        zzb = i2;
        zzc = str;
        zzd = pendingIntent;
        zze = connectionResult;
    }

    public Status(@NonNull final ConnectionResult connectionResult, @NonNull final String str) {
        this(connectionResult, str, 17);
    }

    public boolean equals(@Nullable final Object obj) {
        if (!(obj instanceof Status status)) {
            return false;
        }
        return zzb == status.zzb && Objects.equal(zzc, status.zzc) && Objects.equal(zzd, status.zzd) && Objects.equal(zze, status.zze);
    }

    @Nullable
    public ConnectionResult getConnectionResult() {
        return zze;
    }

    @Nullable
    public PendingIntent getResolution() {
        return zzd;
    }

    @NonNull
    @CanIgnoreReturnValue
    public Status getStatus() {
        return this;
    }

    
    public int getStatusCode() {
        return zzb;
    }

    @Nullable
    public String getStatusMessage() {
        return zzc;
    }

    public boolean hasResolution() {
        return null != this.zzd;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(zzb), zzc, zzd, zze);
    }

    public boolean isCanceled() {
        return 16 == this.zzb;
    }

    @CheckReturnValue
    public boolean isSuccess() {
        return 0 >= this.zzb;
    }

    public void startResolutionForResult(@NonNull final Activity activity, final int i2) throws IntentSender.SendIntentException {
        if (this.hasResolution()) {
            final PendingIntent pendingIntent = zzd;
            Preconditions.checkNotNull(pendingIntent);
            activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i2, (Intent) null, 0, 0, 0);
        }
    }

    @NonNull
    public String toString() {
        final Objects.ToStringHelper stringHelper = Objects.toStringHelper(this);
        stringHelper.add("statusCode", this.zza());
        stringHelper.add("resolution", zzd);
        return stringHelper.toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzb);
        SafeParcelWriter.writeString(parcel, 2, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 3, zzd, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zze, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public String zza() {
        final String str = zzc;
        return null != str ? str : CommonStatusCodes.getStatusCodeString(zzb);
    }

    public Status(final int i2, @Nullable final String str) {
        this(i2, str, null);
    }

    @KeepForSdk
    @Deprecated
    public Status(@NonNull final ConnectionResult connectionResult, @NonNull final String str, final int i2) {
        this(i2, str, connectionResult.getResolution(), connectionResult);
    }

    public Status(final int i2, @Nullable final String str, @Nullable final PendingIntent pendingIntent) {
        this(i2, str, pendingIntent, null);
    }
}
