package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import androidx.annotation.Nullable;
import androidx.core.location.LocationRequestCompat;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.location.LocationRequest;
import java.util.Iterator;
import java.util.List;

@SafeParcelable.Class
@SafeParcelable.Reserved
@Deprecated
/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzeg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzeg> CREATOR = new zzeh();
    @SafeParcelable.Field
    LocationRequest zza;

    @SafeParcelable.Constructor
    zzeg(@SafeParcelable.Param final LocationRequest locationRequest, @SafeParcelable.RemovedParam @Nullable final List list, @SafeParcelable.RemovedParam final boolean z, @SafeParcelable.RemovedParam final boolean z2, @SafeParcelable.RemovedParam final boolean z3, @SafeParcelable.RemovedParam final boolean z4, @SafeParcelable.RemovedParam @Nullable final String str, @SafeParcelable.RemovedParam final long j2) {
        final WorkSource workSource;
        final LocationRequest.Builder builder = new LocationRequest.Builder(locationRequest);
        if (null != list) {
            if (list.isEmpty()) {
                workSource = null;
            } else {
                workSource = new WorkSource();
                final Iterator it = list.iterator();
                while (it.hasNext()) {
                    final ClientIdentity clientIdentity = (ClientIdentity) it.next();
                    WorkSourceUtil.add(workSource, clientIdentity.uid, clientIdentity.packageName);
                }
            }
            builder.zzc(workSource);
        }
        if (z) {
            builder.setGranularity(1);
        }
        if (z2) {
            builder.zza(2);
        }
        if (z3) {
            builder.zzb(true);
        }
        if (z4) {
            builder.setWaitForAccurateLocation(true);
        }
        if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
            builder.setMaxUpdateAgeMillis(j2);
        }
        zza = builder.build();
    }

    @Deprecated
    public static zzeg zza(@Nullable final String str, final LocationRequest locationRequest) {
        return new zzeg(locationRequest, null, false, false, false, false, null, LocationRequestCompat.PASSIVE_INTERVAL);
    }

    public boolean equals(@Nullable final Object obj) {
        if (obj instanceof zzeg) {
            return Objects.equal(zza, ((zzeg) obj).zza);
        }
        return false;
    }

    public int hashCode() {
        return zza.hashCode();
    }

    public String toString() {
        return zza.toString();
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, zza, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
