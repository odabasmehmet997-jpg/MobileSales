package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.TintableTextView;
 
public final class ActivitySettingsBinding implements ViewBinding {
    public final TintableTextView drawerPrintSettings;
    public final TintableTextView drawerPrinterServiceSettings;
    public final TintableTextView drawerPrinterSettings;
    public final TintableTextView drawerTransferSettings;
    private final LinearLayout rootView;
    private ActivitySettingsBinding( final LinearLayout linearLayout,  final TintableTextView tintableTextView,  final TintableTextView tintableTextView2,  final TintableTextView tintableTextView3,  final TintableTextView tintableTextView4) {
        rootView = linearLayout;
        drawerPrintSettings = tintableTextView;
        drawerPrinterServiceSettings = tintableTextView2;
        drawerPrinterSettings = tintableTextView3;
        drawerTransferSettings = tintableTextView4;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivitySettingsBinding inflate( final LayoutInflater layoutInflater) {
        return ActivitySettingsBinding.inflate(layoutInflater, null, false);
    }
    public static ActivitySettingsBinding inflate( final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_settings, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivitySettingsBinding.bind(inflate);
    }
    public static ActivitySettingsBinding bind( final View view) {
        int i2 = R.id.drawer_print_settings;
        final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.drawer_print_settings);
        if (null != tintableTextView) {
            i2 = R.id.drawer_printer_service_settings;
            final TintableTextView tintableTextView2 = ViewBindings.findChildViewById(view, R.id.drawer_printer_service_settings);
            if (null != tintableTextView2) {
                i2 = R.id.drawer_printer_settings;
                final TintableTextView tintableTextView3 = ViewBindings.findChildViewById(view, R.id.drawer_printer_settings);
                if (null != tintableTextView3) {
                    i2 = R.id.drawer_transfer_settings;
                    final TintableTextView tintableTextView4 = ViewBindings.findChildViewById(view, R.id.drawer_transfer_settings);
                    if (null != tintableTextView4) {
                        return new ActivitySettingsBinding((LinearLayout) view, tintableTextView, tintableTextView2, tintableTextView3, tintableTextView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
