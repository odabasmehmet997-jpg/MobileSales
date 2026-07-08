package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapColorScheme;

@SafeParcelable.Class
@SafeParcelable.Reserved
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class GoogleMapOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<GoogleMapOptions> CREATOR = new zzac();
    private static final Integer zza = Integer.valueOf(Color.argb(255, 236, 233, 225));
    @SafeParcelable.Field
    @Nullable
    private Boolean zzb;
    @SafeParcelable.Field
    @Nullable
    private Boolean zzc;
    @SafeParcelable.Field
    private int zzd = -1;
    @SafeParcelable.Field
    @Nullable
    private CameraPosition zze;
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
    @Nullable
    private Boolean zzj;
    @SafeParcelable.Field
    @Nullable
    private Boolean zzk;
    @SafeParcelable.Field
    @Nullable
    private Boolean zzl;
    @SafeParcelable.Field
    @Nullable
    private Boolean zzm;
    @SafeParcelable.Field
    @Nullable
    private Boolean zzn;
    @SafeParcelable.Field
    @Nullable
    private Float zzo;
    @SafeParcelable.Field
    @Nullable
    private Float zzp;
    @SafeParcelable.Field
    @Nullable
    private LatLngBounds zzq;
    @SafeParcelable.Field
    @Nullable
    private Boolean zzr;
    @SafeParcelable.Field
    @ColorInt
    @Nullable
    private Integer zzs;
    @SafeParcelable.Field
    @Nullable
    private String zzt;
    @SafeParcelable.Field
    @MapColorScheme
    private int zzu;

    public GoogleMapOptions() {
    }

    @Nullable
    public static GoogleMapOptions createFromAttributes(@Nullable final Context context, @Nullable final AttributeSet attributeSet) {
        final String string;
        if (null == context || null == attributeSet) {
            return null;
        }
        final TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        final GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        final int i2 = R.styleable.MapAttrs_mapType;
        if (obtainAttributes.hasValue(i2)) {
            googleMapOptions.mapType(obtainAttributes.getInt(i2, -1));
        }
        final int i3 = R.styleable.MapAttrs_zOrderOnTop;
        if (obtainAttributes.hasValue(i3)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(i3, false));
        }
        final int i4 = R.styleable.MapAttrs_useViewLifecycle;
        if (obtainAttributes.hasValue(i4)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(i4, false));
        }
        final int i5 = R.styleable.MapAttrs_uiCompass;
        if (obtainAttributes.hasValue(i5)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(i5, true));
        }
        final int i6 = R.styleable.MapAttrs_uiRotateGestures;
        if (obtainAttributes.hasValue(i6)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(i6, true));
        }
        final int i7 = R.styleable.MapAttrs_uiScrollGesturesDuringRotateOrZoom;
        if (obtainAttributes.hasValue(i7)) {
            googleMapOptions.scrollGesturesEnabledDuringRotateOrZoom(obtainAttributes.getBoolean(i7, true));
        }
        final int i8 = R.styleable.MapAttrs_uiScrollGestures;
        if (obtainAttributes.hasValue(i8)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(i8, true));
        }
        final int i9 = R.styleable.MapAttrs_uiTiltGestures;
        if (obtainAttributes.hasValue(i9)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(i9, true));
        }
        final int i10 = R.styleable.MapAttrs_uiZoomGestures;
        if (obtainAttributes.hasValue(i10)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(i10, true));
        }
        final int i11 = R.styleable.MapAttrs_uiZoomControls;
        if (obtainAttributes.hasValue(i11)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(i11, true));
        }
        final int i12 = R.styleable.MapAttrs_liteMode;
        if (obtainAttributes.hasValue(i12)) {
            googleMapOptions.liteMode(obtainAttributes.getBoolean(i12, false));
        }
        final int i13 = R.styleable.MapAttrs_uiMapToolbar;
        if (obtainAttributes.hasValue(i13)) {
            googleMapOptions.mapToolbarEnabled(obtainAttributes.getBoolean(i13, true));
        }
        final int i14 = R.styleable.MapAttrs_ambientEnabled;
        if (obtainAttributes.hasValue(i14)) {
            googleMapOptions.ambientEnabled(obtainAttributes.getBoolean(i14, false));
        }
        final int i15 = R.styleable.MapAttrs_cameraMinZoomPreference;
        if (obtainAttributes.hasValue(i15)) {
            googleMapOptions.minZoomPreference(obtainAttributes.getFloat(i15, Float.NEGATIVE_INFINITY));
        }
        if (obtainAttributes.hasValue(i15)) {
            googleMapOptions.maxZoomPreference(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY));
        }
        final int i16 = R.styleable.MapAttrs_backgroundColor;
        if (obtainAttributes.hasValue(i16)) {
            googleMapOptions.backgroundColor(Integer.valueOf(obtainAttributes.getColor(i16, GoogleMapOptions.zza.intValue())));
        }
        final int i17 = R.styleable.MapAttrs_mapId;
        if (obtainAttributes.hasValue(i17) && null != (string = obtainAttributes.getString(i17)) && !string.isEmpty()) {
            googleMapOptions.mapId(string);
        }
        final int i18 = R.styleable.MapAttrs_mapColorScheme;
        if (obtainAttributes.hasValue(i18)) {
            googleMapOptions.mapColorScheme(obtainAttributes.getInt(i18, 0));
        }
        googleMapOptions.latLngBoundsForCameraTarget(GoogleMapOptions.zzb(context, attributeSet));
        googleMapOptions.camera(GoogleMapOptions.zza(context, attributeSet));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    @Nullable
    public static CameraPosition zza(@Nullable final Context context, @Nullable final AttributeSet attributeSet) {
        if (null == context || null == attributeSet) {
            return null;
        }
        final TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        final int i2 = R.styleable.MapAttrs_cameraTargetLat;
        final float f2 = obtainAttributes.hasValue(i2) ? obtainAttributes.getFloat(i2, 0.0f) : 0.0f;
        final int i3 = R.styleable.MapAttrs_cameraTargetLng;
        final LatLng latLng = new LatLng(f2, obtainAttributes.hasValue(i3) ? obtainAttributes.getFloat(i3, 0.0f) : 0.0f);
        final CameraPosition.Builder builder = CameraPosition.builder();
        builder.target(latLng);
        final int i4 = R.styleable.MapAttrs_cameraZoom;
        if (obtainAttributes.hasValue(i4)) {
            builder.zoom(obtainAttributes.getFloat(i4, 0.0f));
        }
        final int i5 = R.styleable.MapAttrs_cameraBearing;
        if (obtainAttributes.hasValue(i5)) {
            builder.bearing(obtainAttributes.getFloat(i5, 0.0f));
        }
        final int i6 = R.styleable.MapAttrs_cameraTilt;
        if (obtainAttributes.hasValue(i6)) {
            builder.tilt(obtainAttributes.getFloat(i6, 0.0f));
        }
        obtainAttributes.recycle();
        return builder.build();
    }

    @Nullable
    public static LatLngBounds zzb(@Nullable final Context context, @Nullable final AttributeSet attributeSet) {
        if (null == context || null == attributeSet) {
            return null;
        }
        final TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        final int i2 = R.styleable.MapAttrs_latLngBoundsSouthWestLatitude;
        final Float valueOf = obtainAttributes.hasValue(i2) ? Float.valueOf(obtainAttributes.getFloat(i2, 0.0f)) : null;
        final int i3 = R.styleable.MapAttrs_latLngBoundsSouthWestLongitude;
        final Float valueOf2 = obtainAttributes.hasValue(i3) ? Float.valueOf(obtainAttributes.getFloat(i3, 0.0f)) : null;
        final int i4 = R.styleable.MapAttrs_latLngBoundsNorthEastLatitude;
        final Float valueOf3 = obtainAttributes.hasValue(i4) ? Float.valueOf(obtainAttributes.getFloat(i4, 0.0f)) : null;
        final int i5 = R.styleable.MapAttrs_latLngBoundsNorthEastLongitude;
        final Float valueOf4 = obtainAttributes.hasValue(i5) ? Float.valueOf(obtainAttributes.getFloat(i5, 0.0f)) : null;
        obtainAttributes.recycle();
        if (null == valueOf || null == valueOf2 || null == valueOf3 || null == valueOf4) {
            return null;
        }
        return new LatLngBounds(new LatLng(valueOf.floatValue(), valueOf2.floatValue()), new LatLng(valueOf3.floatValue(), valueOf4.floatValue()));
    }

    @NonNull
    public GoogleMapOptions ambientEnabled(final boolean z) {
        zzn = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions backgroundColor(@ColorInt @Nullable final Integer num) {
        zzs = num;
        return this;
    }

    @NonNull
    public GoogleMapOptions camera(@Nullable final CameraPosition cameraPosition) {
        zze = cameraPosition;
        return this;
    }

    @NonNull
    public GoogleMapOptions compassEnabled(final boolean z) {
        zzg = Boolean.valueOf(z);
        return this;
    }

    @ColorInt
    @Nullable
    public Integer getBackgroundColor() {
        return zzs;
    }

    @Nullable
    public CameraPosition getCamera() {
        return zze;
    }

    @Nullable
    public LatLngBounds getLatLngBoundsForCameraTarget() {
        return zzq;
    }

    @MapColorScheme
    public int getMapColorScheme() {
        return zzu;
    }

    @Nullable
    public String getMapId() {
        return zzt;
    }

    public int getMapType() {
        return zzd;
    }

    @Nullable
    public Float getMaxZoomPreference() {
        return zzp;
    }

    @Nullable
    public Float getMinZoomPreference() {
        return zzo;
    }

    @NonNull
    public GoogleMapOptions latLngBoundsForCameraTarget(@Nullable final LatLngBounds latLngBounds) {
        zzq = latLngBounds;
        return this;
    }

    @NonNull
    public GoogleMapOptions liteMode(final boolean z) {
        zzl = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions mapColorScheme(@MapColorScheme final int i2) {
        zzu = i2;
        return this;
    }

    @NonNull
    public GoogleMapOptions mapId(@NonNull final String str) {
        zzt = str;
        return this;
    }

    @NonNull
    public GoogleMapOptions mapToolbarEnabled(final boolean z) {
        zzm = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions mapType(final int i2) {
        zzd = i2;
        return this;
    }

    @NonNull
    public GoogleMapOptions maxZoomPreference(final float f2) {
        zzp = Float.valueOf(f2);
        return this;
    }

    @NonNull
    public GoogleMapOptions minZoomPreference(final float f2) {
        zzo = Float.valueOf(f2);
        return this;
    }

    @NonNull
    public GoogleMapOptions rotateGesturesEnabled(final boolean z) {
        zzk = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions scrollGesturesEnabled(final boolean z) {
        zzh = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions scrollGesturesEnabledDuringRotateOrZoom(final boolean z) {
        zzr = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions tiltGesturesEnabled(final boolean z) {
        zzj = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("MapType", Integer.valueOf(zzd)).add("LiteMode", zzl).add("Camera", zze).add("CompassEnabled", zzg).add("ZoomControlsEnabled", zzf).add("ScrollGesturesEnabled", zzh).add("ZoomGesturesEnabled", zzi).add("TiltGesturesEnabled", zzj).add("RotateGesturesEnabled", zzk).add("ScrollGesturesEnabledDuringRotateOrZoom", zzr).add("MapToolbarEnabled", zzm).add("AmbientEnabled", zzn).add("MinZoomPreference", zzo).add("MaxZoomPreference", zzp).add("BackgroundColor", zzs).add("LatLngBoundsForCameraTarget", zzq).add("ZOrderOnTop", zzb).add("UseViewLifecycleInFragment", zzc).add("mapColorScheme", Integer.valueOf(zzu)).toString();
    }

    @NonNull
    public GoogleMapOptions useViewLifecycleInFragment(final boolean z) {
        zzc = Boolean.valueOf(z);
        return this;
    }

    public void writeToParcel(@NonNull final Parcel parcel, final int i2) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByte(parcel, 2, GoogleMapOptions.zza.zza(zzb));
        SafeParcelWriter.writeByte(parcel, 3, GoogleMapOptions.zza.zza(zzc));
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zze, i2, false);
        SafeParcelWriter.writeByte(parcel, 6, GoogleMapOptions.zza.zza(zzf));
        SafeParcelWriter.writeByte(parcel, 7, GoogleMapOptions.zza.zza(zzg));
        SafeParcelWriter.writeByte(parcel, 8, GoogleMapOptions.zza.zza(zzh));
        SafeParcelWriter.writeByte(parcel, 9, GoogleMapOptions.zza.zza(zzi));
        SafeParcelWriter.writeByte(parcel, 10, GoogleMapOptions.zza.zza(zzj));
        SafeParcelWriter.writeByte(parcel, 11, GoogleMapOptions.zza.zza(zzk));
        SafeParcelWriter.writeByte(parcel, 12, GoogleMapOptions.zza.zza(zzl));
        SafeParcelWriter.writeByte(parcel, 14, GoogleMapOptions.zza.zza(zzm));
        SafeParcelWriter.writeByte(parcel, 15, GoogleMapOptions.zza.zza(zzn));
        SafeParcelWriter.writeFloatObject(parcel, 16, this.zzo, false);
        SafeParcelWriter.writeFloatObject(parcel, 17, this.zzp, false);
        SafeParcelWriter.writeParcelable(parcel, 18, this.zzq, i2, false);
        SafeParcelWriter.writeByte(parcel, 19, GoogleMapOptions.zza.zza(zzr));
        SafeParcelWriter.writeIntegerObject(parcel, 20, this.zzs, false);
        SafeParcelWriter.writeString(parcel, 21, this.zzt, false);
        SafeParcelWriter.writeInt(parcel, 23, this.zzu);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public GoogleMapOptions zOrderOnTop(final boolean z) {
        zzb = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions zoomControlsEnabled(final boolean z) {
        zzf = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions zoomGesturesEnabled(final boolean z) {
        zzi = Boolean.valueOf(z);
        return this;
    }

    @SafeParcelable.Constructor
    GoogleMapOptions(@SafeParcelable.Param final byte b2, @SafeParcelable.Param final byte b3, @SafeParcelable.Param final int i2, @SafeParcelable.Param @Nullable final CameraPosition cameraPosition, @SafeParcelable.Param final byte b4, @SafeParcelable.Param final byte b5, @SafeParcelable.Param final byte b6, @SafeParcelable.Param final byte b7, @SafeParcelable.Param final byte b8, @SafeParcelable.Param final byte b9, @SafeParcelable.Param final byte b10, @SafeParcelable.Param final byte b11, @SafeParcelable.Param final byte b12, @SafeParcelable.Param @Nullable final Float f2, @SafeParcelable.Param @Nullable final Float f3, @SafeParcelable.Param @Nullable final LatLngBounds latLngBounds, @SafeParcelable.Param final byte b13, @SafeParcelable.Param @ColorInt @Nullable final Integer num, @SafeParcelable.Param @Nullable final String str, @SafeParcelable.Param @MapColorScheme final int i3) {
        zzb = GoogleMapOptions.zza.zzb(b2);
        zzc = GoogleMapOptions.zza.zzb(b3);
        zzd = i2;
        zze = cameraPosition;
        zzf = GoogleMapOptions.zza.zzb(b4);
        zzg = GoogleMapOptions.zza.zzb(b5);
        zzh = GoogleMapOptions.zza.zzb(b6);
        zzi = GoogleMapOptions.zza.zzb(b7);
        zzj = GoogleMapOptions.zza.zzb(b8);
        zzk = GoogleMapOptions.zza.zzb(b9);
        zzl = GoogleMapOptions.zza.zzb(b10);
        zzm = GoogleMapOptions.zza.zzb(b11);
        zzn = GoogleMapOptions.zza.zzb(b12);
        zzo = f2;
        zzp = f3;
        zzq = latLngBounds;
        zzr = GoogleMapOptions.zza.zzb(b13);
        zzs = num;
        zzt = str;
        zzu = i3;
    }
}
