package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class LoadingItemBinding implements ViewBinding {

   
    public final TextView pbText;

   
    public final ProgressBar progressBar;

   
    private final LinearLayout rootView;

    private LoadingItemBinding(final LinearLayout linearLayout, final TextView textView, final ProgressBar progressBar) {
        rootView = linearLayout;
        pbText = textView;
        this.progressBar = progressBar;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static LoadingItemBinding inflate(final LayoutInflater layoutInflater) {
        return LoadingItemBinding.inflate(layoutInflater, null, false);
    }

   
    public static LoadingItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.loading_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return LoadingItemBinding.bind(inflate);
    }

   
    public static LoadingItemBinding bind(final View view) {
        int i2 = R.id.pbText;
        final TextView textView = ViewBindings.findChildViewById(view, R.id.pbText);
        if (null != textView) {
            i2 = R.id.progressBar;
            final ProgressBar progressBar = ViewBindings.findChildViewById(view, R.id.progressBar);
            if (null != progressBar) {
                return new LoadingItemBinding((LinearLayout) view, textView, progressBar);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
