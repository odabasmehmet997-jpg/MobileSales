package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zaf extends zag {
    private final WeakReference zac;

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof final zaf zaf)) {
            return false;
        }
        ImageManager.OnImageLoadedListener onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.zac.get();
        ImageManager.OnImageLoadedListener onImageLoadedListener2 = (ImageManager.OnImageLoadedListener) zaf.zac.get();
        return null != onImageLoadedListener2 && null != onImageLoadedListener && Objects.equal(onImageLoadedListener2, onImageLoadedListener) && Objects.equal(zaf.zaa, this.zaa);
    }

    public int hashCode() {
        return Objects.hashCode(this.zaa);
    }

    
    public void zaa(@Nullable Drawable drawable, boolean z, boolean z2, boolean z3) {
        ImageManager.OnImageLoadedListener onImageLoadedListener;
        if (!z2 && null != (onImageLoadedListener = (ImageManager.OnImageLoadedListener) zac.get())) {
            onImageLoadedListener.onImageLoaded(this.zaa.zaa(), drawable, z3);
        }
    }
}
