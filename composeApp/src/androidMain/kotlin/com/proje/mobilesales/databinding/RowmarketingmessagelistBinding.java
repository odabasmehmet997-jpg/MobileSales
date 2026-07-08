package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class RowmarketingmessagelistBinding implements ViewBinding {

   
    public final AppCompatTextView MMDESC;

   
    public final AppCompatTextView MMID;

   
    private final RelativeLayout rootView;

    private RowmarketingmessagelistBinding(final RelativeLayout relativeLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2) {
        rootView = relativeLayout;
        MMDESC = appCompatTextView;
        MMID = appCompatTextView2;
    }

    
   
    public RelativeLayout getRoot() {
        return rootView;
    }

   
    public static RowmarketingmessagelistBinding inflate(final LayoutInflater layoutInflater) {
        return RowmarketingmessagelistBinding.inflate(layoutInflater, null, false);
    }

   
    public static RowmarketingmessagelistBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.rowmarketingmessagelist, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return RowmarketingmessagelistBinding.bind(inflate);
    }

   
    public static RowmarketingmessagelistBinding bind(final View view) {
        int i2 = R.id.MMDESC;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.MMDESC);
        if (null != appCompatTextView) {
            i2 = R.id.MMID;
            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.MMID);
            if (null != appCompatTextView2) {
                return new RowmarketingmessagelistBinding((RelativeLayout) view, appCompatTextView, appCompatTextView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
