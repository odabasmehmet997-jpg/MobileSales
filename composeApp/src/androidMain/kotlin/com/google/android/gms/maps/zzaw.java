package com.google.android.gms.maps;

import android.app.Activity;
import android.os.RemoteException;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.zzcc;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzaw extends DeferredLifecycleHelper {
    private OnDelegateCreatedListener zza;
    private final Fragment zzb;
    private Activity zzc;
    private final List zzd = new ArrayList();

    @VisibleForTesting
    zzaw(final Fragment fragment) {
        zzb = fragment;
    }

    static void zza(final zzaw zzaw, final Activity activity) {
        zzaw.zzc = activity;
        zzaw.zzc();
    }

    
    public void createDelegate(final OnDelegateCreatedListener onDelegateCreatedListener) {
        zza = onDelegateCreatedListener;
        this.zzc();
    }

    public void zzc() {
        if (null != this.zzc && null != this.zza && null == getDelegate()) {
            try {
                MapsInitializer.initialize(zzc);
                final IMapFragmentDelegate zzf = zzcc.zza(zzc, (MapsInitializer.Renderer) null).zzf(ObjectWrapper.wrap(zzc));
                if (null != zzf) {
                    zza.onDelegateCreated(new zzav(zzb, zzf));
                    for (final OnMapReadyCallback mapAsync : zzd) {
                        ((zzav) this.getDelegate()).getMapAsync(mapAsync);
                    }
                    zzd.clear();
                }
            } catch (final RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            } catch (final GooglePlayServicesNotAvailableException unused) {
            }
        }
    }
}
