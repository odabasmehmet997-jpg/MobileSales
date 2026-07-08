package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.gms.base.R;
import com.google.android.gms.common.util.DeviceProperties;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zaaa extends Button {
    public zaaa(Context context, @Nullable AttributeSet attributeSet) {
        super(context, null, 16842824);
    }

    private static int zab(int i2, int i3, int i4, int i5) {
        if (0 == i2) {
            return i3;
        }
        if (1 == i2) {
            return i4;
        }
        if (2 == i2) {
            return i5;
        }
        throw new IllegalStateException("Unknown color scheme: " + i2);
    }

    public void zaa(Resources resources, int i2, int i3) {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        int i4 = (int) ((resources.getDisplayMetrics().density * 48.0f) + 0.5f);
        setMinHeight(i4);
        setMinWidth(i4);
        int i5 = R.drawable.common_google_signin_btn_icon_dark;
        int i6 = R.drawable.common_google_signin_btn_icon_light;
        int zab = zab(i3, i5, i6, i6);
        int i7 = R.drawable.common_google_signin_btn_text_dark;
        int i8 = R.drawable.common_google_signin_btn_text_light;
        int zab2 = zab(i3, i7, i8, i8);
        if (0 == i2 || 1 == i2) {
            zab = zab2;
        } else if (2 != i2) {
            throw new IllegalStateException("Unknown button size: " + i2);
        }
        Drawable wrap = DrawableCompat.wrap(resources.getDrawable(zab));
        DrawableCompat.setTintList(wrap, resources.getColorStateList(R.color.common_google_signin_btn_tint));
        DrawableCompat.setTintMode(wrap, PorterDuff.Mode.SRC_ATOP);
        setBackgroundDrawable(wrap);
        int i9 = R.color.common_google_signin_btn_text_dark;
        int i10 = R.color.common_google_signin_btn_text_light;
        setTextColor(Preconditions.checkNotNull(resources.getColorStateList(zab(i3, i9, i10, i10))));
        if (0 == i2) {
            setText(resources.getString(R.string.common_signin_button_text));
        } else if (1 == i2) {
            setText(resources.getString(R.string.common_signin_button_text_long));
        } else if (2 == i2) {
            setText(null);
        } else {
            throw new IllegalStateException("Unknown button size: " + i2);
        }
        setTransformationMethod(null);
        if (DeviceProperties.isWearable(getContext())) {
            setGravity(19);
        }
    }
}
