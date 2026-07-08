package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.maps.zzax;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzai implements TileProvider {
    final TileOverlayOptions zza;
    private final zzax zzb;

    zzai(final TileOverlayOptions tileOverlayOptions) {
        zza = tileOverlayOptions;
        zzb = tileOverlayOptions.zza;
    }

    @Nullable
    public Tile getTile(final int i2, final int i3, final int i4) {
        try {
            return zzb.zzb(i2, i3, i4);
        } catch (final RemoteException unused) {
            return null;
        }
    }
}
