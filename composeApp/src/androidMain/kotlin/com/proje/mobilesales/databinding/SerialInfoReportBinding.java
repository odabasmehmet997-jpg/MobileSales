package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class SerialInfoReportBinding implements ViewBinding {

   
    public final LinearLayout linearProgress;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView tvSerialNo1;

   
    public final AppCompatTextView tvSerialNo2;

   
    public final AppCompatTextView tvUserAmount;

    private SerialInfoReportBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3) {
        rootView = linearLayout;
        linearProgress = linearLayout2;
        tvSerialNo1 = appCompatTextView;
        tvSerialNo2 = appCompatTextView2;
        tvUserAmount = appCompatTextView3;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static SerialInfoReportBinding inflate(final LayoutInflater layoutInflater) {
        return SerialInfoReportBinding.inflate(layoutInflater, null, false);
    }

   
    public static SerialInfoReportBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.serial_info_report, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SerialInfoReportBinding.bind(inflate);
    }

   
    public static SerialInfoReportBinding bind(final View view) {
        int i2 = R.id.linearProgress;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearProgress);
        if (null != linearLayout) {
            i2 = R.id.tvSerialNo1;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvSerialNo1);
            if (null != appCompatTextView) {
                i2 = R.id.tvSerialNo2;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvSerialNo2);
                if (null != appCompatTextView2) {
                    i2 = R.id.tvUserAmount;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvUserAmount);
                    if (null != appCompatTextView3) {
                        return new SerialInfoReportBinding((LinearLayout) view, linearLayout, appCompatTextView, appCompatTextView2, appCompatTextView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
