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

public final class AllSendLogoRowBinding implements ViewBinding {
    public final AppCompatImageView imSpinner;
    public final ProgressBar progressBar1;
    private final LinearLayout rootView;
    public final AppCompatTextView tvClCard;
    public final AppCompatTextView tvError;
    public final AppCompatTextView tvStatus;
    public final AppCompatTextView tvTitle;
    private AllSendLogoRowBinding(final LinearLayout linearLayout, final AppCompatImageView appCompatImageView, final ProgressBar progressBar, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4) {
        rootView = linearLayout;
        imSpinner = appCompatImageView;
        progressBar1 = progressBar;
        tvClCard = appCompatTextView;
        tvError = appCompatTextView2;
        tvStatus = appCompatTextView3;
        tvTitle = appCompatTextView4;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static AllSendLogoRowBinding inflate(final LayoutInflater layoutInflater) {
        return AllSendLogoRowBinding.inflate(layoutInflater, null, false);
    }
    public static AllSendLogoRowBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.all_send_logo_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return AllSendLogoRowBinding.bind(inflate);
    }
    public static AllSendLogoRowBinding bind(final View view) {
        int i2 = R.id.imSpinner;
        final AppCompatImageView appCompatImageView = ViewBindings.findChildViewById(view, R.id.imSpinner);
        if (null != appCompatImageView) {
            i2 = R.id.progressBar1;
            final ProgressBar progressBar = ViewBindings.findChildViewById(view, R.id.progressBar1);
            if (null != progressBar) {
                i2 = R.id.tvClCard;
                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvClCard);
                if (null != appCompatTextView) {
                    i2 = R.id.tvError;
                    final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvError);
                    if (null != appCompatTextView2) {
                        i2 = R.id.tvStatus;
                        final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvStatus);
                        if (null != appCompatTextView3) {
                            i2 = R.id.tvTitle;
                            final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvTitle);
                            if (null != appCompatTextView4) {
                                return new AllSendLogoRowBinding((LinearLayout) view, appCompatImageView, progressBar, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
