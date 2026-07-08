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
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.zzcc;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.List;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzai extends DeferredLifecycleHelper {
    private OnDelegateCreatedListener zza;
    private final ViewGroup zzb;
    private final Context zzc;
    @Nullable
    private final GoogleMapOptions zzd;
    private final List zze;

    
    public void createDelegate(final OnDelegateCreatedListener onDelegateCreatedListener) {
        zza = onDelegateCreatedListener;
        this.zzb();
    }

    public void zzb() {
        if (null != this.zza && null == getDelegate()) {
            try {
                MapsInitializer.initialize(zzc);
                final IMapViewDelegate zzg = zzcc.zza(zzc, (MapsInitializer.Renderer) null).zzg(ObjectWrapper.wrap(zzc), zzd);
                if (null != zzg) {
                    zza.onDelegateCreated(new zzah(zzb, zzg));
                    for (final OnMapReadyCallback mapAsync : zze) {
                        ((zzah) this.getDelegate()).getMapAsync(mapAsync);
                    }
                    zze.clear();
                }
            } catch (final RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            } catch (final GooglePlayServicesNotAvailableException unused) {
            }
        }
    }
}
