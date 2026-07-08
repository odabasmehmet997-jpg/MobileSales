package com.google.android.gms.maps.model;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.maps.zzaw;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzaj extends zzaw {
    final TileProvider zza;

    @Nullable
    public Tile zzb(final int i2, final int i3, final int i4) {
        return zza.getTile(i2, i3, i4);
    }
}
