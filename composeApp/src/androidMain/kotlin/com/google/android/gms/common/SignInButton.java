package com.google.android.gms.common;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zaaa;
import com.google.android.gms.common.internal.zaz;
import com.google.android.gms.dynamic.RemoteCreator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class SignInButton extends FrameLayout implements View.OnClickListener {
    private int zaa;
    private int zab;
    private View zac;
    @Nullable
    private View.OnClickListener zad;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public @interface ButtonSize {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public @interface ColorScheme {
    }

    private void zaa(final Context context) {
        final View view = zac;
        if (null != view) {
            this.removeView(view);
        }
        try {
            zac = zaz.zaa(context, zaa, zab);
        } catch (final RemoteCreator.RemoteCreatorException unused) {
            Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            final int i2 = zaa;
            final int i3 = zab;
            final zaaa zaaa = new zaaa(context, null);
            zaaa.zaa(context.getResources(), i2, i3);
            zac = zaaa;
        }
        this.addView(zac);
        zac.setEnabled(this.isEnabled());
        zac.setOnClickListener(this);
    }

    public void onClick(@NonNull final View view) {
        final View.OnClickListener onClickListener = zad;
        if (null != onClickListener && view == zac) {
            onClickListener.onClick(this);
        }
    }

    public void setColorScheme(final int i2) {
        this.setStyle(zaa, i2);
    }

    public void setEnabled(final boolean z) {
        super.setEnabled(z);
        zac.setEnabled(z);
    }

    public void setOnClickListener(@Nullable final View.OnClickListener onClickListener) {
        zad = onClickListener;
        final View view = zac;
        if (null != view) {
            view.setOnClickListener(this);
        }
    }

    @Deprecated
    public void setScopes(@NonNull final Scope[] scopeArr) {
        this.setStyle(zaa, zab);
    }

    public void setSize(final int i2) {
        this.setStyle(i2, zab);
    }

    public void setStyle(final int i2, final int i3) {
        zaa = i2;
        zab = i3;
        this.zaa(this.getContext());
    }
}
