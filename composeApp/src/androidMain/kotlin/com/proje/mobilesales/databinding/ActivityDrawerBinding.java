package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ActivityDrawerBinding implements ViewBinding {
    public final ScrollView drawer;
    public final DrawerLayout drawerLayout;
    private final DrawerLayout rootView;
    private ActivityDrawerBinding( final DrawerLayout drawerLayout,  final ScrollView scrollView,  final DrawerLayout drawerLayout2) {
        rootView = drawerLayout;
        drawer = scrollView;
        this.drawerLayout = drawerLayout2;
    } 
    public DrawerLayout getRoot() {
        return rootView;
    }
    public static ActivityDrawerBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityDrawerBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityDrawerBinding inflate( final LayoutInflater layoutInflater,final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_drawer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityDrawerBinding.bind(inflate);
    }
    public static ActivityDrawerBinding bind( final View view) {
        final ScrollView scrollView = ViewBindings.findChildViewById(view, R.id.drawer);
        if (scrollView == null) {
            throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.drawer));
        }
        final DrawerLayout drawerLayout = (DrawerLayout) view;
        return new ActivityDrawerBinding(drawerLayout, scrollView, drawerLayout);
    }
}
