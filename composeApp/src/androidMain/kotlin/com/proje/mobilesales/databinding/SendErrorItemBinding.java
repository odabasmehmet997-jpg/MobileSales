package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class SendErrorItemBinding implements ViewBinding {

   
    public final AppCompatImageView imSpinner;

   
    private final LinearLayout rootView;

   
    public final Toolbar sendErrorToolbar;

   
    public final AppCompatTextView tvSendErrorItem;

    private SendErrorItemBinding(final LinearLayout linearLayout, final AppCompatImageView appCompatImageView, final Toolbar toolbar, final AppCompatTextView appCompatTextView) {
        rootView = linearLayout;
        imSpinner = appCompatImageView;
        sendErrorToolbar = toolbar;
        tvSendErrorItem = appCompatTextView;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static SendErrorItemBinding inflate(final LayoutInflater layoutInflater) {
        return SendErrorItemBinding.inflate(layoutInflater, null, false);
    }

   
    public static SendErrorItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.send_error_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SendErrorItemBinding.bind(inflate);
    }

   
    public static SendErrorItemBinding bind(final View view) {
        int i2 = R.id.imSpinner;
        final AppCompatImageView appCompatImageView = ViewBindings.findChildViewById(view, R.id.imSpinner);
        if (null != appCompatImageView) {
            i2 = R.id.sendErrorToolbar;
            final Toolbar toolbar = ViewBindings.findChildViewById(view, R.id.sendErrorToolbar);
            if (null != toolbar) {
                i2 = R.id.tvSendErrorItem;
                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvSendErrorItem);
                if (null != appCompatTextView) {
                    return new SendErrorItemBinding((LinearLayout) view, appCompatImageView, toolbar, appCompatTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
