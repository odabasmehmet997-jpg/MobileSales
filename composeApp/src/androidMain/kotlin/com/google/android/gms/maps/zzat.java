package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.zzcc;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.List;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzat extends DeferredLifecycleHelper {
    private OnDelegateCreatedListener zza;
    private final ViewGroup zzb;
    private final Context zzc;
    @Nullable
    private final StreetViewPanoramaOptions zzd;
    private final List zze;

    
    public void createDelegate(final OnDelegateCreatedListener onDelegateCreatedListener) {
        zza = onDelegateCreatedListener;
        this.zzb();
    }

    public void zzb() {
        if (null != this.zza && null == getDelegate()) {
            try {
                MapsInitializer.initialize(zzc);
                zza.onDelegateCreated(new zzas(zzb, zzcc.zza(zzc, (MapsInitializer.Renderer) null).zzi(ObjectWrapper.wrap(zzc), zzd)));
                for (final OnStreetViewPanoramaReadyCallback streetViewPanoramaAsync : zze) {
                    ((zzas) this.getDelegate()).getStreetViewPanoramaAsync(streetViewPanoramaAsync);
                }
                zze.clear();
            } catch (final RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            } catch (final GooglePlayServicesNotAvailableException unused) {
            }
        }
    }
}
