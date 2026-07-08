package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proje.mobilesales.R;
 
public final class ActivityGpsInfoBinding implements ViewBinding {
    public final AppCompatCheckBox chkOpenEditModeMap;
    public final FloatingActionButton fabRouteCustomer;
    private final CoordinatorLayout rootView;
    private ActivityGpsInfoBinding( final CoordinatorLayout coordinatorLayout,  final AppCompatCheckBox appCompatCheckBox,  final FloatingActionButton floatingActionButton) {
        rootView = coordinatorLayout;
        chkOpenEditModeMap = appCompatCheckBox;
        fabRouteCustomer = floatingActionButton;
    }
    public CoordinatorLayout getRoot() {
        return rootView;
    }
    public static ActivityGpsInfoBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityGpsInfoBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityGpsInfoBinding inflate( final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_gps_info, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityGpsInfoBinding.bind(inflate);
    }
    public static ActivityGpsInfoBinding bind( final View view) {
        int i2 = R.id.chk_openEditModeMap;
        final AppCompatCheckBox appCompatCheckBox = ViewBindings.findChildViewById(view, R.id.chk_openEditModeMap);
        if (null != appCompatCheckBox) {
            i2 = R.id.fab_routeCustomer;
            final FloatingActionButton floatingActionButton = ViewBindings.findChildViewById(view, R.id.fab_routeCustomer);
            if (null != floatingActionButton) {
                return new ActivityGpsInfoBinding((CoordinatorLayout) view, appCompatCheckBox, floatingActionButton);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
