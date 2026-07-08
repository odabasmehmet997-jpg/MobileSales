package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzcb;
import com.google.android.gms.maps.model.RuntimeRemoteException;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzay implements StreetViewLifecycleDelegate {
    private final Fragment zza;
    private final IStreetViewPanoramaFragmentDelegate zzb;

    public zzay(final Fragment fragment, final IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate) {
        zzb = Preconditions.checkNotNull(iStreetViewPanoramaFragmentDelegate);
        zza = Preconditions.checkNotNull(fragment);
    }

    public void onCreate(@Nullable final Bundle bundle) {
        try {
            final Bundle bundle2 = new Bundle();
            zzcb.zzb(bundle, bundle2);
            final Bundle arguments = zza.getArguments();
            if (null != arguments && arguments.containsKey("StreetViewPanoramaOptions")) {
                zzcb.zzc(bundle2, "StreetViewPanoramaOptions", arguments.getParcelable("StreetViewPanoramaOptions"));
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

    public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        try {
            zzb.getStreetViewPanoramaAsync(new zzax(this, onStreetViewPanoramaReadyCallback));
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

    public void onInflate(final Activity activity, final Bundle bundle, @Nullable final Bundle bundle2) {
        try {
            final Bundle bundle3 = new Bundle();
            zzcb.zzb(bundle2, bundle3);
            zzb.onInflate(ObjectWrapper.wrap(activity), null, bundle3);
            zzcb.zzb(bundle3, bundle2);
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
