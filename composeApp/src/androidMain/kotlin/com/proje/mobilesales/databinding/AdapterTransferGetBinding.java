package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class AdapterTransferGetBinding implements ViewBinding {
    public final AppCompatImageView imgGetAdd;
    public final AppCompatImageView imgGetResult;
    private final CardView rootView;
    public final AppCompatTextView twGetTitle;
    private AdapterTransferGetBinding(final CardView cardView, final AppCompatImageView appCompatImageView, final AppCompatImageView appCompatImageView2, final AppCompatTextView appCompatTextView) {
        rootView = cardView;
        imgGetAdd = appCompatImageView;
        imgGetResult = appCompatImageView2;
        twGetTitle = appCompatTextView;
    }
    public CardView getRoot() {
        return rootView;
    }
    public static AdapterTransferGetBinding inflate(final LayoutInflater layoutInflater) {
        return AdapterTransferGetBinding.inflate(layoutInflater, null, false);
    }
    public static AdapterTransferGetBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.adapter_transfer_get, viewGroup, false);
        if (z && viewGroup != null) {
            viewGroup.addView(inflate);
        }
        return AdapterTransferGetBinding.bind(inflate);
    }
    public static AdapterTransferGetBinding bind(final View view) {
        int i2 = R.id.imgGetAdd;
        final AppCompatImageView appCompatImageView = ViewBindings.findChildViewById(view, R.id.imgGetAdd);
        if (null != appCompatImageView) {
            i2 = R.id.imgGetResult;
            final AppCompatImageView appCompatImageView2 = ViewBindings.findChildViewById(view, R.id.imgGetResult);
            if (null != appCompatImageView2) {
                i2 = R.id.twGetTitle;
                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.twGetTitle);
                if (null != appCompatTextView) {
                    return new AdapterTransferGetBinding((CardView) view, appCompatImageView, appCompatImageView2, appCompatTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
