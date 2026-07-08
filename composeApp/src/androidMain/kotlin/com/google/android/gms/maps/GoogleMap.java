package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzan;
import com.google.android.gms.maps.internal.zzax;
import com.google.android.gms.maps.internal.zzaz;
import com.google.android.gms.maps.model.*;
import java.util.HashMap;
import java.util.Map;

public class GoogleMap {
    private final IGoogleMapDelegate zza;
    private final Map zzc = new HashMap();
    private final Map zzd = new HashMap();
    private UiSettings zze;
    public void setMapType(int i) {
    }
    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }
    public interface InfoWindowAdapter {
        View getInfoContents(Marker marker);

        View getInfoWindow(Marker marker);
    }
    public interface OnCameraChangeListener {
        void onCameraChange(CameraPosition cameraPosition);
    }
    public interface OnCameraIdleListener {
        void onCameraIdle();
    }
    public interface OnCameraMoveCanceledListener {
        void onCameraMoveCanceled();
    }
    public interface OnCameraMoveListener {
        void onCameraMove();
    }
    public interface OnCameraMoveStartedListener {
        void onCameraMoveStarted(int i2);
    }
    public interface OnCircleClickListener {
        void onCircleClick(Circle circle);
    }
    public interface OnGroundOverlayClickListener {
        void onGroundOverlayClick(GroundOverlay groundOverlay);
    }
    public interface OnIndoorStateChangeListener {
        void onIndoorBuildingFocused();
        void onIndoorLevelActivated(IndoorBuilding indoorBuilding);
    }
    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker marker);
    }
    public interface OnInfoWindowCloseListener {
        void onInfoWindowClose(Marker marker);
    }
    public interface OnInfoWindowLongClickListener {
        void onInfoWindowLongClick(Marker marker);
    }
    public interface OnMapCapabilitiesChangedListener {
        void onMapCapabilitiesChanged(MapCapabilities mapCapabilities);
    }
    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);
    }
    public interface OnMapLoadedCallback {
        void onMapLoaded();
    }
    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }
    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }
    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker marker);
        void onMarkerDragEnd(Marker marker);
        void onMarkerDragStart(Marker marker);
    }
    public interface OnMyLocationButtonClickListener {
        boolean onMyLocationButtonClick();
    } 
    public interface OnMyLocationChangeListener {
        void onMyLocationChange(Location location);
    }
    public interface OnMyLocationClickListener {
        void onMyLocationClick(Location location);
    }
    public interface OnPoiClickListener {
        void onPoiClick(PointOfInterest pointOfInterest);
    }
    public interface OnPolygonClickListener {
        void onPolygonClick(Polygon polygon);
    }
    public interface OnPolylineClickListener {
        void onPolylineClick(Polyline polyline);
    }
    public interface SnapshotReadyCallback {
        void onSnapshotReady(Bitmap bitmap);
    }
    public GoogleMap(final IGoogleMapDelegate iGoogleMapDelegate) {
        zza = Preconditions.checkNotNull(iGoogleMapDelegate);
    }
    public final Marker addMarker(final MarkerOptions markerOptions) {
        if (markerOptions instanceof AdvancedMarkerOptions) {
            markerOptions.zzf(1);
        }
        try {
            Preconditions.checkNotNull(markerOptions, "MarkerOptions must not be null.");
            final zzah addMarker = zza.addMarker(markerOptions);
            if (null == addMarker) {
                return null;
            }
            if (1 == markerOptions.zzb()) {
                return new AdvancedMarker(addMarker);
            }
            return new Marker(addMarker);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    public final UiSettings getUiSettings() {
        try {
            if (null == this.zze) {
                zze = new UiSettings(zza.getUiSettings());
            }
            return zze;
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    public final void setIndoorEnabled(final boolean z) {
        try {
            zza.setIndoorEnabled(z);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    public final void setOnMapClickListener(final OnMapClickListener onMapClickListener) {
        if (null == onMapClickListener) {
            try {
                zza.setOnMapClickListener(null);
            } catch (final RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            }
        } else {
            try {
                zza.setOnMapClickListener(new zzz(this, onMapClickListener));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public final void setOnMarkerDragListener(final OnMarkerDragListener onMarkerDragListener) {
        if (null == onMarkerDragListener) {
            try {
                zza.setOnMarkerDragListener(null);
            } catch (final RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            }
        } else {
            try {
                zza.setOnMarkerDragListener(new zzb(this, onMarkerDragListener));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public final void setOnMyLocationButtonClickListener(final OnMyLocationButtonClickListener onMyLocationButtonClickListener) {
        if (null == onMyLocationButtonClickListener) {
            try {
                zza.setOnMyLocationButtonClickListener(null);
            } catch (final RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            }
        } else {
            try {
                zza.setOnMyLocationButtonClickListener(new zzh(this, onMyLocationButtonClickListener));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public final void setBuildingsEnabled(final boolean z) {
        try {
            zza.setBuildingsEnabled(z);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public final void setMyLocationEnabled(final boolean z) {
        try {
            zza.setMyLocationEnabled(z);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    public final void setTrafficEnabled(final boolean z) {
        try {
            zza.setTrafficEnabled(z);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    public final void animateCamera(final CameraUpdate cameraUpdate) {
        try {
            Preconditions.checkNotNull(cameraUpdate, "CameraUpdate must not be null.");
            zza.animateCamera(cameraUpdate.zza());
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}
