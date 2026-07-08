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



public final class ReportVehicleStatusRowBinding implements ViewBinding {

   
    public final LinearLayout linearMaterial2;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView tvCode;

   
    public final AppCompatTextView tvDefinition;

   
    public final AppCompatTextView tvStock;

    private ReportVehicleStatusRowBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3) {
        rootView = linearLayout;
        linearMaterial2 = linearLayout2;
        tvCode = appCompatTextView;
        tvDefinition = appCompatTextView2;
        tvStock = appCompatTextView3;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ReportVehicleStatusRowBinding inflate(final LayoutInflater layoutInflater) {
        return ReportVehicleStatusRowBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportVehicleStatusRowBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_vehicle_status_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportVehicleStatusRowBinding.bind(inflate);
    }

   
    public static ReportVehicleStatusRowBinding bind(final View view) {
        int i2 = R.id.linearMaterial2;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
        if (null != linearLayout) {
            i2 = R.id.tvCode;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvCode);
            if (null != appCompatTextView) {
                i2 = R.id.tvDefinition_;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvDefinition_);
                if (null != appCompatTextView2) {
                    i2 = R.id.tvStock;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvStock);
                    if (null != appCompatTextView3) {
                        return new ReportVehicleStatusRowBinding((LinearLayout) view, linearLayout, appCompatTextView, appCompatTextView2, appCompatTextView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
