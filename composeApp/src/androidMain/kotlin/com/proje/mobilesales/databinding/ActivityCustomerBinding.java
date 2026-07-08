package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.ViewPager;
 
public final class ActivityCustomerBinding implements ViewBinding {

    public final AppBarLayout appbar;
    public final CoordinatorLayout contentFrame;
    private final CoordinatorLayout rootView;
    public final TabLayout tabLayout;
    public final Toolbar toolbar;
    public final ViewPager viewPager;
    private ActivityCustomerBinding(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final CoordinatorLayout coordinatorLayout2, final TabLayout tabLayout, final Toolbar toolbar, final ViewPager viewPager) {
        rootView = coordinatorLayout;
        appbar = appBarLayout;
        contentFrame = coordinatorLayout2;
        this.tabLayout = tabLayout;
        this.toolbar = toolbar;
        this.viewPager = viewPager;
    }
    public CoordinatorLayout getRoot() {
        return rootView;
    }
    public static ActivityCustomerBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityCustomerBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityCustomerBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_customer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityCustomerBinding.bind(inflate);
    }
    public static ActivityCustomerBinding bind(final View view) {
        int i2 = R.id.appbar;
        final AppBarLayout appBarLayout = ViewBindings.findChildViewById(view, R.id.appbar);
        if (null != appBarLayout) {
            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
            i2 = R.id.tab_layout;
            final TabLayout tabLayout = ViewBindings.findChildViewById(view, R.id.tab_layout);
            if (null != tabLayout) {
                i2 = R.id.toolbar;
                final Toolbar toolbar = ViewBindings.findChildViewById(view, R.id.toolbar);
                if (null != toolbar) {
                    i2 = R.id.view_pager;
                    final ViewPager viewPager = ViewBindings.findChildViewById(view, R.id.view_pager);
                    if (null != viewPager) {
                        return new ActivityCustomerBinding(coordinatorLayout, appBarLayout, coordinatorLayout, tabLayout, toolbar, viewPager);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
