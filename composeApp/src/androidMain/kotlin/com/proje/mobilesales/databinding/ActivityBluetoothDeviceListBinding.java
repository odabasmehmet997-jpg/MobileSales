package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
 
public final class ActivityBluetoothDeviceListBinding implements ViewBinding {
    public final Button btnScan;
    public final ListView lstDevice;
    private final LinearLayout rootView;
    public final TextView txtTitleDevice;
    private ActivityBluetoothDeviceListBinding( final LinearLayout linearLayout,  final Button button,  final ListView listView,  final TextView textView) {
        rootView = linearLayout;
        btnScan = button;
        lstDevice = listView;
        txtTitleDevice = textView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityBluetoothDeviceListBinding inflate( final LayoutInflater layoutInflater,  final ViewGroup viewGroup) {
        return ActivityBluetoothDeviceListBinding.inflate(layoutInflater, viewGroup, false);
    }
    public static ActivityBluetoothDeviceListBinding inflate( final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_bluetooth_device_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityBluetoothDeviceListBinding.bind(inflate);
    }
    public static ActivityBluetoothDeviceListBinding bind( final View view) {
        int i2 = R.id.btn_scan;
        final Button button = ViewBindings.findChildViewById(view, R.id.btn_scan);
        if (null != button) {
            i2 = R.id.lst_device;
            final ListView listView = ViewBindings.findChildViewById(view, R.id.lst_device);
            if (null != listView) {
                i2 = R.id.txt_title_device;
                final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_title_device);
                if (null != textView) {
                    return new ActivityBluetoothDeviceListBinding((LinearLayout) view, button, listView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
