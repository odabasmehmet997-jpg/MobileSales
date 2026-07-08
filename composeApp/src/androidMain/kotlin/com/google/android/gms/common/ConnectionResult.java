package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class ConnectionResult extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ConnectionResult> CREATOR = new zzb();
    @ShowFirstParty
    @NonNull
    @KeepForSdk
    public static final ConnectionResult RESULT_SUCCESS = new ConnectionResult(0);
    @SafeParcelable.VersionField
    final int zza;
    @SafeParcelable.Field
    private final int zzb;
    @SafeParcelable.Field
    @Nullable
    private final PendingIntent zzc;
    @SafeParcelable.Field
    @Nullable
    private final String zzd;

    public ConnectionResult(final int i2) {
        this(i2, null, null);
    }

    @SafeParcelable.Constructor
    ConnectionResult(@SafeParcelable.Param final int i2, @SafeParcelable.Param final int i3, @SafeParcelable.Param @Nullable final PendingIntent pendingIntent, @SafeParcelable.Param @Nullable final String str) {
        zza = i2;
        zzb = i3;
        zzc = pendingIntent;
        zzd = str;
    }

    public ConnectionResult(final int i2, @Nullable final PendingIntent pendingIntent) {
        this(i2, pendingIntent, null);
    }

    @NonNull
    static String zza(final int i2) {
        if (99 == i2) {
            return "UNFINISHED";
        }
        if (1500 == i2) {
            return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
        }
        switch (i2) {
            case -1:
                return "UNKNOWN";
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 9:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case 11:
                return "LICENSE_CHECK_FAILED";
            default:
                switch (i2) {
                    case 13:
                        return "CANCELED";
                    case 14:
                        return "TIMEOUT";
                    case 15:
                        return "INTERRUPTED";
                    case 16:
                        return "API_UNAVAILABLE";
                    case 17:
                        return "SIGN_IN_FAILED";
                    case 18:
                        return "SERVICE_UPDATING";
                    case 19:
                        return "SERVICE_MISSING_PERMISSION";
                    case 20:
                        return "RESTRICTED_PROFILE";
                    case 21:
                        return "API_VERSION_UPDATE_REQUIRED";
                    case 22:
                        return "RESOLUTION_ACTIVITY_NOT_FOUND";
                    case 23:
                        return "API_DISABLED";
                    case 24:
                        return "API_DISABLED_FOR_CONNECTION";
                    default:
                        return "UNKNOWN_ERROR_CODE(" + i2 + ")";
                }
        }
    }

    public boolean equals(@Nullable final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConnectionResult connectionResult)) {
            return false;
        }
        return zzb == connectionResult.zzb && Objects.equal(zzc, connectionResult.zzc) && Objects.equal(zzd, connectionResult.zzd);
    }

    public int getErrorCode() {
        return zzb;
    }

    @Nullable
    public String getErrorMessage() {
        return zzd;
    }

    @Nullable
    public PendingIntent getResolution() {
        return zzc;
    }

    public boolean hasResolution() {
        return 0 != this.zzb && null != this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(zzb), zzc, zzd);
    }

    public boolean isSuccess() {
        return 0 == this.zzb;
    }

    @NonNull
    public String toString() {
        final Objects.ToStringHelper stringHelper = Objects.toStringHelper(this);
        stringHelper.add("statusCode", ConnectionResult.zza(zzb));
        stringHelper.add("resolution", zzc);
        stringHelper.add("message", zzd);
        return stringHelper.toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int i3 = zza;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i2, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public ConnectionResult(final int i2, @Nullable final PendingIntent pendingIntent, @Nullable final String str) {
        this(1, i2, pendingIntent, str);
    }
}
