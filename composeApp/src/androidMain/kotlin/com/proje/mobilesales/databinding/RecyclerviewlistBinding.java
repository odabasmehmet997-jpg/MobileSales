package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class RecyclerviewlistBinding implements ViewBinding {
    public final RecyclerView recyclerViewList;
    private final ConstraintLayout rootView;
    private RecyclerviewlistBinding(final ConstraintLayout constraintLayout, final RecyclerView recyclerView) {
        rootView = constraintLayout;
        recyclerViewList = recyclerView;
    }
    public ConstraintLayout getRoot() {
        return rootView;
    }
    public static RecyclerviewlistBinding inflate(final LayoutInflater layoutInflater) {
        return RecyclerviewlistBinding.inflate(layoutInflater, null, false);
    }
    public static RecyclerviewlistBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.recyclerviewlist, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return RecyclerviewlistBinding.bind(inflate);
    }
    public static RecyclerviewlistBinding bind(final View view) {
        final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.recyclerViewList);
        if (null != recyclerView) {
            return new RecyclerviewlistBinding((ConstraintLayout) view, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.recyclerViewList));
    }
}
