package com.example.privacy_policy_lib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.privacy_policy_lib.R;
import com.google.android.material.appbar.AppBarLayout;
 
public final class ToolbarBinding implements ViewBinding {
    public final AppBarLayout appbar;
    private final AppBarLayout rootView;
    public final Toolbar toolbar;
    private ToolbarBinding( AppBarLayout appBarLayout,  AppBarLayout appBarLayout2,  Toolbar toolbar) {
        this.rootView = appBarLayout;
        this.appbar = appBarLayout2;
        this.toolbar = toolbar;
    }
    public AppBarLayout getRoot() {
        return this.rootView;
    }
    public static ToolbarBinding inflate( LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }
    public static ToolbarBinding inflate( LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.toolbar, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
    public static ToolbarBinding bind( View view) {
        AppBarLayout appBarLayout = (AppBarLayout) view;
        int i2 = R.id.toolbar;
        Toolbar toolbar = ViewBindings.findChildViewById(view, i2);
        if (toolbar != null) {
            return new ToolbarBinding(appBarLayout, appBarLayout, toolbar);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }
}
