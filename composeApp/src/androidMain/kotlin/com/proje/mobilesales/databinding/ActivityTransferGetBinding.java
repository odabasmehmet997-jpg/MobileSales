package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proje.mobilesales.R;

public final class ActivityTransferGetBinding implements ViewBinding {
    public final FloatingActionButton fabStartTransfer;
    public final RecyclerView rcwTransferGet;
    private final CoordinatorLayout rootView;
    public final AppCompatTextView twFabText;
    private ActivityTransferGetBinding(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final RecyclerView recyclerView, final AppCompatTextView appCompatTextView) {
        rootView = coordinatorLayout;
        fabStartTransfer = floatingActionButton;
        rcwTransferGet = recyclerView;
        twFabText = appCompatTextView;
    }
    public CoordinatorLayout getRoot() {
        return rootView;
    }
    public static ActivityTransferGetBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityTransferGetBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityTransferGetBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_transfer_get, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityTransferGetBinding.bind(inflate);
    }
    public static ActivityTransferGetBinding bind(final View view) {
        int i2 = R.id.fabStartTransfer;
        final FloatingActionButton floatingActionButton = ViewBindings.findChildViewById(view, R.id.fabStartTransfer);
        if (null != floatingActionButton) {
            i2 = R.id.rcwTransferGet;
            final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcwTransferGet);
            if (null != recyclerView) {
                i2 = R.id.twFabText;
                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.twFabText);
                if (null != appCompatTextView) {
                    return new ActivityTransferGetBinding((CoordinatorLayout) view, floatingActionButton, recyclerView, appCompatTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
