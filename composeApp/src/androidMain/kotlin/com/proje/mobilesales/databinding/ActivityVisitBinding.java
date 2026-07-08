package com.proje.mobilesales.databinding;

import android.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.proje.mobilesales.R;

public final class ActivityVisitBinding implements ViewBinding {
    public final AppBarLayout appbar;
    public final CoordinatorLayout contentFrame;
    public final FrameLayout list;
    private final CoordinatorLayout rootView;
    public final Toolbar toolbar;
    private ActivityVisitBinding(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final CoordinatorLayout coordinatorLayout2, final FrameLayout frameLayout, final Toolbar toolbar) {
        rootView = coordinatorLayout;
        appbar = appBarLayout;
        contentFrame = coordinatorLayout2;
        list = frameLayout;
        this.toolbar = toolbar;
    }
    public CoordinatorLayout getRoot() {
        return rootView;
    }
    public static ActivityVisitBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityVisitBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityVisitBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_visit, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityVisitBinding.bind(inflate);
    }
    public static ActivityVisitBinding bind(final View view) {
        int i2 = R.id.appbar;
        final AppBarLayout appBarLayout = ViewBindings.findChildViewById(view, R.id.appbar);
        if (null != appBarLayout) {
            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
            i2 = R.id.list;
            final FrameLayout frameLayout = ViewBindings.findChildViewById(view, R.id.list);
            if (null != frameLayout) {
                i2 = R.id.toolbar;
                final Toolbar toolbar = ViewBindings.findChildViewById(view, R.id.toolbar);
                if (null != toolbar) {
                    return new ActivityVisitBinding(coordinatorLayout, appBarLayout, coordinatorLayout, frameLayout, toolbar);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
