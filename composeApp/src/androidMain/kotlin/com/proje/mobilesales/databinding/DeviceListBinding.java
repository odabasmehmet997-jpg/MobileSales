package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class DeviceListBinding implements ViewBinding {
    public final Button buttonScan;
    public final ListView newDevices;
    public final ListView pairedDevices;
    private final LinearLayout rootView;
    public final TextView titleNewDevices;
    public final TextView titlePairedDevices;
    private DeviceListBinding(final LinearLayout linearLayout, final Button button, final ListView listView, final ListView listView2, final TextView textView, final TextView textView2) {
        rootView = linearLayout;
        buttonScan = button;
        newDevices = listView;
        pairedDevices = listView2;
        titleNewDevices = textView;
        titlePairedDevices = textView2;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static DeviceListBinding inflate(final LayoutInflater layoutInflater) {
        return DeviceListBinding.inflate(layoutInflater, null, false);
    }
    public static DeviceListBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.device_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return DeviceListBinding.bind(inflate);
    }
    public static DeviceListBinding bind(final View view) {
        int i2 = R.id.button_scan;
        final Button button = ViewBindings.findChildViewById(view, R.id.button_scan);
        if (null != button) {
            i2 = R.id.new_devices;
            final ListView listView = ViewBindings.findChildViewById(view, R.id.new_devices);
            if (null != listView) {
                i2 = R.id.paired_devices;
                final ListView listView2 = ViewBindings.findChildViewById(view, R.id.paired_devices);
                if (null != listView2) {
                    i2 = R.id.title_new_devices;
                    final TextView textView = ViewBindings.findChildViewById(view, R.id.title_new_devices);
                    if (null != textView) {
                        i2 = R.id.title_paired_devices;
                        final TextView textView2 = ViewBindings.findChildViewById(view, R.id.title_paired_devices);
                        if (null != textView2) {
                            return new DeviceListBinding((LinearLayout) view, button, listView, listView2, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
