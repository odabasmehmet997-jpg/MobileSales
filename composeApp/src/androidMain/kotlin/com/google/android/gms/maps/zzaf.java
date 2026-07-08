package com.google.android.gms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.RemoteException;
import androidx.annotation.VisibleForTesting;
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
final class zzaf extends DeferredLifecycleHelper {
    private OnDelegateCreatedListener zza;
    private final Fragment zzb;
    private Activity zzc;
    private final List zzd = new ArrayList();

    @VisibleForTesting
    zzaf(final Fragment fragment) {
        zzb = fragment;
    }

    static void zza(final zzaf zzaf, final Activity activity) {
        zzaf.zzc = activity;
        zzaf.zzc();
    }

    
    public void createDelegate(final OnDelegateCreatedListener onDelegateCreatedListener) {
        zza = onDelegateCreatedListener;
        this.zzc();
    }

    public void zzb(final OnMapReadyCallback onMapReadyCallback) {
        if (null != getDelegate()) {
            ((zzae) this.getDelegate()).getMapAsync(onMapReadyCallback);
        } else {
            zzd.add(onMapReadyCallback);
        }
    }

    public void zzc() {
        if (null != this.zzc && null != this.zza && null == getDelegate()) {
            try {
                MapsInitializer.initialize(zzc);
                final IMapFragmentDelegate zzf = zzcc.zza(zzc, (MapsInitializer.Renderer) null).zzf(ObjectWrapper.wrap(zzc));
                if (null != zzf) {
                    zza.onDelegateCreated(new zzae(zzb, zzf));
                    for (final OnMapReadyCallback mapAsync : zzd) {
                        ((zzae) this.getDelegate()).getMapAsync(mapAsync);
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
