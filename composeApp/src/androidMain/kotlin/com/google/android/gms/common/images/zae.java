package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.base.zak;
import com.google.android.gms.internal.base.zal;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zae extends zag {
    private final WeakReference zac;

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zae)) {
            return false;
        }
        ImageView imageView = (ImageView) this.zac.get();
        ImageView imageView2 = (ImageView) ((zae) obj).zac.get();
        return null != imageView2 && null != imageView && Objects.equal(imageView2, imageView);
    }

    public int hashCode() {
        return 0;
    }

    
    public void zaa(@Nullable Drawable drawable, boolean z, boolean z2, boolean z3) {
        ImageView imageView = (ImageView) this.zac.get();
        if (null == imageView) {
            return;
        }
        if (z2 || z3 || !(imageView instanceof final zal zal2)) {
            final boolean z4 = !z2 && !z;
            if (z4) {
                Drawable drawable2 = imageView.getDrawable();
                if (null == drawable2) {
                    drawable2 = null;
                } else if (drawable2 instanceof zak) {
                    drawable2 = ((zak) drawable2).zaa();
                }
                drawable = new zak(drawable2, drawable);
            }
            imageView.setImageDrawable(drawable);
            if (imageView instanceof final zal zal) {
                throw null;
            } else if (null != drawable && z4) {
                ((zak) drawable).zab(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            }
        } else {
            throw null;
        }
    }
}
