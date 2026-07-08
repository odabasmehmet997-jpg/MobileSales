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

public final class GroupLayoutBinding implements ViewBinding {

   
    public final AppCompatImageView imCategorySpinner;

   
    public final ProgressBar progressBarCategory;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView tvCategoryTitle;

    private GroupLayoutBinding(final LinearLayout linearLayout, final AppCompatImageView appCompatImageView, final ProgressBar progressBar, final AppCompatTextView appCompatTextView) {
        rootView = linearLayout;
        imCategorySpinner = appCompatImageView;
        progressBarCategory = progressBar;
        tvCategoryTitle = appCompatTextView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static GroupLayoutBinding inflate(final LayoutInflater layoutInflater) {
        return GroupLayoutBinding.inflate(layoutInflater, null, false);
    }
    public static GroupLayoutBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.group_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return GroupLayoutBinding.bind(inflate);
    }
    public static GroupLayoutBinding bind(final View view) {
        int i2 = R.id.imCategorySpinner;
        final AppCompatImageView appCompatImageView = ViewBindings.findChildViewById(view, R.id.imCategorySpinner);
        if (null != appCompatImageView) {
            i2 = R.id.progressBarCategory;
            final ProgressBar progressBar = ViewBindings.findChildViewById(view, R.id.progressBarCategory);
            if (null != progressBar) {
                i2 = R.id.tvCategoryTitle;
                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvCategoryTitle);
                if (null != appCompatTextView) {
                    return new GroupLayoutBinding((LinearLayout) view, appCompatImageView, progressBar, appCompatTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
