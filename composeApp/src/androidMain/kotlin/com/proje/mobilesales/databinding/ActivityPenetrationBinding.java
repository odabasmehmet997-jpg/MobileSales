package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.proje.mobilesales.R;

public final class ActivityPenetrationBinding implements ViewBinding {
    public final AppBarLayout appbar;
    public final CoordinatorLayout contentFrame;
    public final RecyclerView rcwList;
    private final CoordinatorLayout rootView;
    public final Toolbar toolbar;
    private ActivityPenetrationBinding(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final CoordinatorLayout coordinatorLayout2, final RecyclerView recyclerView, final Toolbar toolbar) {
        rootView = coordinatorLayout;
        appbar = appBarLayout;
        contentFrame = coordinatorLayout2;
        rcwList = recyclerView;
        this.toolbar = toolbar;
    }
    public CoordinatorLayout getRoot() {
        return rootView;
    }
    public static ActivityPenetrationBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityPenetrationBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityPenetrationBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_penetration, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityPenetrationBinding.bind(inflate);
    }
    public static ActivityPenetrationBinding bind(final View view) {
        int i2 = R.id.appbar;
        final AppBarLayout appBarLayout = ViewBindings.findChildViewById(view, R.id.appbar);
        if (null != appBarLayout) {
            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
            i2 = R.id.rcwList;
            final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcwList);
            if (null != recyclerView) {
                i2 = R.id.toolbar;
                final Toolbar toolbar = ViewBindings.findChildViewById(view, R.id.toolbar);
                if (null != toolbar) {
                    return new ActivityPenetrationBinding(coordinatorLayout, appBarLayout, coordinatorLayout, recyclerView, toolbar);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
