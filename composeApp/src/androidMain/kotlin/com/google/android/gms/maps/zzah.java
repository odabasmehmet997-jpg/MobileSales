package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzcb;
import com.google.android.gms.maps.model.RuntimeRemoteException;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzah implements MapLifecycleDelegate {
    private final ViewGroup zza;
    private final IMapViewDelegate zzb;
    private View zzc;

    public zzah(final ViewGroup viewGroup, final IMapViewDelegate iMapViewDelegate) {
        zzb = Preconditions.checkNotNull(iMapViewDelegate);
        zza = Preconditions.checkNotNull(viewGroup);
    }

    public View onCreateView(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, @Nullable final Bundle bundle) {
        throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
    }

    public void onDestroyView() {
        throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
    }

    public void onInflate(final Activity activity, final Bundle bundle, @Nullable final Bundle bundle2) {
        throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
    }

    public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
        try {
            zzb.getMapAsync(new zzag(this, onMapReadyCallback));
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

    public void onCreate(@Nullable final Bundle bundle) {
        try {
            final Bundle bundle2 = new Bundle();
            zzcb.zzb(bundle, bundle2);
            zzb.onCreate(bundle2);
            zzcb.zzb(bundle2, bundle);
            zzc = ObjectWrapper.unwrap(zzb.getView());
            zza.removeAllViews();
            zza.addView(zzc);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}
