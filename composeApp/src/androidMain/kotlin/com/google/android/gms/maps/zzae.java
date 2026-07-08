package com.google.android.gms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzcb;
import com.google.android.gms.maps.model.RuntimeRemoteException;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzae implements MapLifecycleDelegate {
    private final Fragment zza;
    private final IMapFragmentDelegate zzb;

    public zzae(final Fragment fragment, final IMapFragmentDelegate iMapFragmentDelegate) {
        zzb = Preconditions.checkNotNull(iMapFragmentDelegate);
        zza = Preconditions.checkNotNull(fragment);
    }

    public void onCreate(@Nullable final Bundle bundle) {
        try {
            final Bundle bundle2 = new Bundle();
            zzcb.zzb(bundle, bundle2);
            final Bundle arguments = zza.getArguments();
            if (null != arguments && arguments.containsKey("MapOptions")) {
                zzcb.zzc(bundle2, "MapOptions", arguments.getParcelable("MapOptions"));
            }
            zzb.onCreate(bundle2);
            zzcb.zzb(bundle2, bundle);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public View onCreateView(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, @Nullable final Bundle bundle) {
        try {
            final Bundle bundle2 = new Bundle();
            zzcb.zzb(bundle, bundle2);
            final IObjectWrapper onCreateView = zzb.onCreateView(ObjectWrapper.wrap(layoutInflater), ObjectWrapper.wrap(viewGroup), bundle2);
            zzcb.zzb(bundle2, bundle);
            return ObjectWrapper.unwrap(onCreateView);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void onInflate(final Activity activity, final Bundle bundle, @Nullable final Bundle bundle2) {
        final GoogleMapOptions googleMapOptions = bundle.getParcelable("MapOptions");
        try {
            final Bundle bundle3 = new Bundle();
            zzcb.zzb(bundle2, bundle3);
            zzb.onInflate(ObjectWrapper.wrap(activity), googleMapOptions, bundle3);
            zzcb.zzb(bundle3, bundle2);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
        try {
            zzb.getMapAsync(new zzad(this, onMapReadyCallback));
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void onDestroy() {
        try {
            zzb.onDestroy();
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void onDestroyView() {
        try {
            zzb.onDestroyView();
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void onLowMemory() {
        try {
            zzb.onLowMemory();
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void onPause() {
        try {
            zzb.onPause();
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void onResume() {
        try {
            zzb.onResume();
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void onStart() {
        try {
            zzb.onStart();
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void onStop() {
        try {
            zzb.onStop();
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public void onSaveInstanceState(final Bundle bundle) {
        try {
            final Bundle bundle2 = new Bundle();
            zzcb.zzb(bundle, bundle2);
            zzb.onSaveInstanceState(bundle2);
            zzcb.zzb(bundle2, bundle);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}
