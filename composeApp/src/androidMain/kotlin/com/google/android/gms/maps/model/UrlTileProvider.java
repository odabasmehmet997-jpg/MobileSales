package com.google.android.gms.maps.model;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzf;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class UrlTileProvider implements TileProvider {
    private final int zza;
    private final int zzb;

    @Nullable
    public final Tile getTile(final int i2, final int i3, final int i4) {
        final URL tileUrl = this.getTileUrl(i2, i3, i4);
        if (null == tileUrl) {
            return NO_TILE;
        }
        try {
            zzf.zzb(4352);
            final int i5 = zza;
            final int i6 = zzb;
            final InputStream inputStream = tileUrl.openConnection().getInputStream();
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Preconditions.checkNotNull(inputStream, "from must not be null.");
            Preconditions.checkNotNull(byteArrayOutputStream, "to must not be null.");
            final byte[] bArr = new byte[4096];
            while (true) {
                final int read = inputStream.read(bArr);
                if (-1 == read) {
                    final Tile tile = new Tile(i5, i6, byteArrayOutputStream.toByteArray());
                    zzf.zza();
                    return tile;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (final IOException unused) {
            zzf.zza();
            return null;
        } catch (final Throwable th) {
            zzf.zza();
            throw th;
        }
    }

    @Nullable
    public abstract URL getTileUrl(int i2, int i3, int i4);
}
