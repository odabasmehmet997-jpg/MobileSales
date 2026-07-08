package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.proje.mobilesales.R;

public final class ToolbarBinding implements ViewBinding {
    public final AppBarLayout appbar;
    private final AppBarLayout rootView;
    public final Toolbar toolbar;
    private ToolbarBinding(final AppBarLayout appBarLayout, final AppBarLayout appBarLayout2, final Toolbar toolbar) {
        rootView = appBarLayout;
        appbar = appBarLayout2;
        this.toolbar = toolbar;
    }
    public AppBarLayout getRoot() {
        return rootView;
    }
    public static ToolbarBinding inflate(final LayoutInflater layoutInflater) {
        return ToolbarBinding.inflate(layoutInflater, null, false);
    }
    public static ToolbarBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.toolbar, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ToolbarBinding.bind(inflate);
    }
    public static ToolbarBinding bind(final View view) {
        final AppBarLayout appBarLayout = (AppBarLayout) view;
        final Toolbar toolbar = ViewBindings.findChildViewById(view, R.id.toolbar);
        if (null != toolbar) {
            return new ToolbarBinding(appBarLayout, appBarLayout, toolbar);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.toolbar));
    }
}
