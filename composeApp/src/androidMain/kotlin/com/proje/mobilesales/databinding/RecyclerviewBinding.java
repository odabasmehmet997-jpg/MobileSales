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



public final class RecyclerviewBinding implements ViewBinding {

   
    public final RecyclerView recyclerView;

   
    private final LinearLayout rootView;

    private RecyclerviewBinding(final LinearLayout linearLayout, final RecyclerView recyclerView) {
        rootView = linearLayout;
        this.recyclerView = recyclerView;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static RecyclerviewBinding inflate(final LayoutInflater layoutInflater) {
        return RecyclerviewBinding.inflate(layoutInflater, null, false);
    }

   
    public static RecyclerviewBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.recyclerview, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return RecyclerviewBinding.bind(inflate);
    }

   
    public static RecyclerviewBinding bind(final View view) {
        final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.recycler_view);
        if (null != recyclerView) {
            return new RecyclerviewBinding((LinearLayout) view, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.recycler_view));
    }
}
