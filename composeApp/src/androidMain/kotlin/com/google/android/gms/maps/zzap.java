package com.google.android.gms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.RemoteException;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.zzcc;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzap extends DeferredLifecycleHelper {
    private OnDelegateCreatedListener zza;
    private final Fragment zzb;
    private Activity zzc;
    private final List zzd = new ArrayList();

    @VisibleForTesting
    zzap(final Fragment fragment) {
        zzb = fragment;
    }

    static void zza(final zzap zzap, final Activity activity) {
        zzap.zzc = activity;
        zzap.zzc();
    }

    
    public void createDelegate(final OnDelegateCreatedListener onDelegateCreatedListener) {
        zza = onDelegateCreatedListener;
        this.zzc();
    }

    public void zzc() {
        if (null != this.zzc && null != this.zza && null == getDelegate()) {
            try {
                MapsInitializer.initialize(zzc);
                zza.onDelegateCreated(new zzao(zzb, zzcc.zza(zzc, (MapsInitializer.Renderer) null).zzh(ObjectWrapper.wrap(zzc))));
                for (final OnStreetViewPanoramaReadyCallback streetViewPanoramaAsync : zzd) {
                    ((zzao) this.getDelegate()).getStreetViewPanoramaAsync(streetViewPanoramaAsync);
                }
                zzd.clear();
            } catch (final RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            } catch (final GooglePlayServicesNotAvailableException unused) {
            }
        }
    }
}
