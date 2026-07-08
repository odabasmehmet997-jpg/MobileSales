package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ReportVehicleStatusBinding implements ViewBinding {

   
    public final LinearLayout linearMaterial2;

   
    public final LinearLayout linearProgress;

   
    public final ListView lvReportVehicleStatus;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView tvCode;

   
    public final AppCompatTextView tvDefinition;

   
    public final AppCompatTextView tvStock;

    private ReportVehicleStatusBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final ListView listView, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3) {
        rootView = linearLayout;
        linearMaterial2 = linearLayout2;
        linearProgress = linearLayout3;
        lvReportVehicleStatus = listView;
        tvCode = appCompatTextView;
        tvDefinition = appCompatTextView2;
        tvStock = appCompatTextView3;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ReportVehicleStatusBinding inflate(final LayoutInflater layoutInflater) {
        return ReportVehicleStatusBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportVehicleStatusBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_vehicle_status, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportVehicleStatusBinding.bind(inflate);
    }

   
    public static ReportVehicleStatusBinding bind(final View view) {
        int i2 = R.id.linearMaterial2;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
        if (null != linearLayout) {
            i2 = R.id.linearProgress;
            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.linearProgress);
            if (null != linearLayout2) {
                i2 = R.id.lvReportVehicleStatus;
                final ListView listView = ViewBindings.findChildViewById(view, R.id.lvReportVehicleStatus);
                if (null != listView) {
                    i2 = R.id.tvCode;
                    final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvCode);
                    if (null != appCompatTextView) {
                        i2 = R.id.tvDefinition_;
                        final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvDefinition_);
                        if (null != appCompatTextView2) {
                            i2 = R.id.tvStock;
                            final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvStock);
                            if (null != appCompatTextView3) {
                                return new ReportVehicleStatusBinding((LinearLayout) view, linearLayout, linearLayout2, listView, appCompatTextView, appCompatTextView2, appCompatTextView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
