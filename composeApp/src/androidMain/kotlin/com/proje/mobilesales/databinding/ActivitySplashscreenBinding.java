package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ActivitySplashscreenBinding implements ViewBinding {
    public final AppCompatImageView logo;
    private final FrameLayout rootView;
    private ActivitySplashscreenBinding(final FrameLayout frameLayout, final AppCompatImageView appCompatImageView) {
        rootView = frameLayout;
        logo = appCompatImageView;
    }
    public FrameLayout getRoot() {
        return rootView;
    }
    public static ActivitySplashscreenBinding inflate(final LayoutInflater layoutInflater) {
        return ActivitySplashscreenBinding.inflate(layoutInflater, null, false);
    }
    public static ActivitySplashscreenBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_splashscreen, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivitySplashscreenBinding.bind(inflate);
    }
    public static ActivitySplashscreenBinding bind(final View view) {
        final AppCompatImageView appCompatImageView = ViewBindings.findChildViewById(view, R.id.logo);
        if (null != appCompatImageView) {
            return new ActivitySplashscreenBinding((FrameLayout) view, appCompatImageView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.logo));
    }
}
