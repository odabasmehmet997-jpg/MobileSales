package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proje.mobilesales.R;



public final class FragmentPenetrationBinding implements ViewBinding {

   
    public final CoordinatorLayout cordFrame;

   
    public final FloatingActionButton fabPenetration;

   
    public final RecyclerView rcwList;

   
    private final CoordinatorLayout rootView;

    private FragmentPenetrationBinding(final CoordinatorLayout coordinatorLayout, final CoordinatorLayout coordinatorLayout2, final FloatingActionButton floatingActionButton, final RecyclerView recyclerView) {
        rootView = coordinatorLayout;
        cordFrame = coordinatorLayout2;
        fabPenetration = floatingActionButton;
        rcwList = recyclerView;
    }

    
   
    public CoordinatorLayout getRoot() {
        return rootView;
    }

   
    public static FragmentPenetrationBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentPenetrationBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentPenetrationBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_penetration, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentPenetrationBinding.bind(inflate);
    }

   
    public static FragmentPenetrationBinding bind(final View view) {
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
        int i2 = R.id.fab_penetration;
        final FloatingActionButton floatingActionButton = ViewBindings.findChildViewById(view, R.id.fab_penetration);
        if (null != floatingActionButton) {
            i2 = R.id.rcwList;
            final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcwList);
            if (null != recyclerView) {
                return new FragmentPenetrationBinding(coordinatorLayout, coordinatorLayout, floatingActionButton, recyclerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
