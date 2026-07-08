package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.maps.zzbm;

@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class FeatureLayerOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<FeatureLayerOptions> CREATOR = new zzg();
    private static final zzbm zza = zzbm.zzi("ADMINISTRATIVE_AREA_LEVEL_1", "ADMINISTRATIVE_AREA_LEVEL_2", "COUNTRY", "LOCALITY", "POSTAL_CODE", "SCHOOL_DISTRICT", "DATASET");
    @SafeParcelable.Field
    @FeatureType
    private final String zzb;
    @SafeParcelable.Field
    private final String zzc;

    /* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
    public static final class Builder {
    }

    @NonNull
    public String getDatasetId() {
        return zzc;
    }

    @NonNull
    @FeatureType
    public String getFeatureType() {
        return zzb;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    FeatureLayerOptions(@SafeParcelable.Param @FeatureType final String str, @SafeParcelable.Param final String str2) {
        zzb = str;
        zzc = str2;
    }
}
