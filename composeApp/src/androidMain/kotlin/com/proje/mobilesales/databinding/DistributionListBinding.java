package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class DistributionListBinding implements ViewBinding {
    public final EmptyListBinding empty;
    private final LinearLayout rootView;
    public final RecyclerView rwDistributionListView;
    private DistributionListBinding(final LinearLayout linearLayout, final EmptyListBinding emptyListBinding, final RecyclerView recyclerView) {
        rootView = linearLayout;
        empty = emptyListBinding;
        rwDistributionListView = recyclerView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static DistributionListBinding inflate(final LayoutInflater layoutInflater) {
        return DistributionListBinding.inflate(layoutInflater, null, false);
    }
    public static DistributionListBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.distribution_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return DistributionListBinding.bind(inflate);
    }
    public static DistributionListBinding bind(final View view) {
        int i2 = R.id.empty;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.empty);
        if (null != findChildViewById) {
            final EmptyListBinding bind = EmptyListBinding.bind(findChildViewById);
            final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rwDistributionListView);
            if (null != recyclerView) {
                return new DistributionListBinding((LinearLayout) view, bind, recyclerView);
            }
            i2 = R.id.rwDistributionListView;
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
