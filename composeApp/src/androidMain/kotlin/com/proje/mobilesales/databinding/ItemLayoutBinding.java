package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ItemLayoutBinding implements ViewBinding {

   
    public final AppCompatImageView imItemSpinner;

   
    public final ProgressBar progressBarItem;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView tvItemError;

   
    public final AppCompatTextView tvItemStatus;

   
    public final AppCompatTextView tvItemTitle;

    private ItemLayoutBinding(final LinearLayout linearLayout, final AppCompatImageView appCompatImageView, final ProgressBar progressBar, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3) {
        rootView = linearLayout;
        imItemSpinner = appCompatImageView;
        progressBarItem = progressBar;
        tvItemError = appCompatTextView;
        tvItemStatus = appCompatTextView2;
        tvItemTitle = appCompatTextView3;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ItemLayoutBinding inflate(final LayoutInflater layoutInflater) {
        return ItemLayoutBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemLayoutBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemLayoutBinding.bind(inflate);
    }

   
    public static ItemLayoutBinding bind(final View view) {
        int i2 = R.id.imItemSpinner;
        final AppCompatImageView appCompatImageView = ViewBindings.findChildViewById(view, R.id.imItemSpinner);
        if (null != appCompatImageView) {
            i2 = R.id.progressBarItem;
            final ProgressBar progressBar = ViewBindings.findChildViewById(view, R.id.progressBarItem);
            if (null != progressBar) {
                i2 = R.id.tvItemError;
                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvItemError);
                if (null != appCompatTextView) {
                    i2 = R.id.tvItemStatus;
                    final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvItemStatus);
                    if (null != appCompatTextView2) {
                        i2 = R.id.tvItemTitle;
                        final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvItemTitle);
                        if (null != appCompatTextView3) {
                            return new ItemLayoutBinding((LinearLayout) view, appCompatImageView, progressBar, appCompatTextView, appCompatTextView2, appCompatTextView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
