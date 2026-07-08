package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewSource;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class StreetViewPanoramaOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<StreetViewPanoramaOptions> CREATOR = new zzaq();
    @SafeParcelable.Field
    @Nullable
    private StreetViewPanoramaCamera zza;
    @SafeParcelable.Field
    @Nullable
    private String zzb;
    @SafeParcelable.Field
    @Nullable
    private LatLng zzc;
    @SafeParcelable.Field
    @Nullable
    private Integer zzd;
    @SafeParcelable.Field
    @Nullable
    private Boolean zze;
    @SafeParcelable.Field
    @Nullable
    private Boolean zzf;
    @SafeParcelable.Field
    @Nullable
    private Boolean zzg;
    @SafeParcelable.Field
    @Nullable
    private Boolean zzh;
    @SafeParcelable.Field
    @Nullable
    private Boolean zzi;
    @SafeParcelable.Field
    private StreetViewSource zzj = StreetViewSource.DEFAULT;

    public StreetViewPanoramaOptions() {
        final Boolean bool = Boolean.TRUE;
        zze = bool;
        zzf = bool;
        zzg = bool;
        zzh = bool;
    }

    @Nullable
    public String getPanoramaId() {
        return zzb;
    }

    @Nullable
    public LatLng getPosition() {
        return zzc;
    }

    @Nullable
    public Integer getRadius() {
        return zzd;
    }

    @NonNull
    public StreetViewSource getSource() {
        return zzj;
    }

    @Nullable
    public StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return zza;
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("PanoramaId", zzb).add("Position", zzc).add("Radius", zzd).add("Source", zzj).add("StreetViewPanoramaCamera", zza).add("UserNavigationEnabled", zze).add("ZoomGesturesEnabled", zzf).add("PanningGesturesEnabled", zzg).add("StreetNamesEnabled", zzh).add("UseViewLifecycleInFragment", zzi).toString();
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zza, i2, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i2, false);
        SafeParcelWriter.writeIntegerObject(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeByte(parcel, 6, this.zza.zza(zze));
        SafeParcelWriter.writeByte(parcel, 7, this.zza.zza(zzf));
        SafeParcelWriter.writeByte(parcel, 8, this.zza.zza(zzg));
        SafeParcelWriter.writeByte(parcel, 9, this.zza.zza(zzh));
        SafeParcelWriter.writeByte(parcel, 10, this.zza.zza(zzi));
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzj, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    StreetViewPanoramaOptions(@SafeParcelable.Param @Nullable final StreetViewPanoramaCamera streetViewPanoramaCamera, @SafeParcelable.Param @Nullable final String str, @SafeParcelable.Param @Nullable final LatLng latLng, @SafeParcelable.Param @Nullable final Integer num, @SafeParcelable.Param final byte b2, @SafeParcelable.Param final byte b3, @SafeParcelable.Param final byte b4, @SafeParcelable.Param final byte b5, @SafeParcelable.Param final byte b6, @SafeParcelable.Param final StreetViewSource streetViewSource) {
        final Boolean bool = Boolean.TRUE;
        zze = bool;
        zzf = bool;
        zzg = bool;
        zzh = bool;
        zza = streetViewPanoramaCamera;
        zzc = latLng;
        zzd = num;
        zzb = str;
        zze = this.zza.zzb(b2);
        zzf = this.zza.zzb(b3);
        zzg = this.zza.zzb(b4);
        zzh = this.zza.zzb(b5);
        zzi = this.zza.zzb(b6);
        zzj = streetViewSource;
    }
}
