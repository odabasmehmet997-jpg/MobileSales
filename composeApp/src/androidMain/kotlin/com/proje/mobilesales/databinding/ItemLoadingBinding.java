package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ItemLoadingBinding implements ViewBinding {

   
    public final ProgressBar progressBar;

   
    private final LinearLayout rootView;

    private ItemLoadingBinding(final LinearLayout linearLayout, final ProgressBar progressBar) {
        rootView = linearLayout;
        this.progressBar = progressBar;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ItemLoadingBinding inflate(final LayoutInflater layoutInflater) {
        return ItemLoadingBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemLoadingBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_loading, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemLoadingBinding.bind(inflate);
    }

   
    public static ItemLoadingBinding bind(final View view) {
        final ProgressBar progressBar = ViewBindings.findChildViewById(view, R.id.progressBar);
        if (null != progressBar) {
            return new ItemLoadingBinding((LinearLayout) view, progressBar);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.progressBar));
    }
}
