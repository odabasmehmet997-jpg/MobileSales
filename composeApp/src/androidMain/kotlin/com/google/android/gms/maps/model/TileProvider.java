package com.google.android.gms.maps.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public interface TileProvider {
    @NonNull
    Tile NO_TILE = new Tile(-1, -1, null);

    @Nullable
    Tile getTile(int i2, int i3, int i4);
}
