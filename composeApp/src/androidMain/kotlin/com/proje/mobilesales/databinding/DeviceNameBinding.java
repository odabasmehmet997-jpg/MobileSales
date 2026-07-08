package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.proje.mobilesales.R;

public final class DeviceNameBinding implements ViewBinding {
    private final TextView rootView;
    private DeviceNameBinding(final TextView textView) {
        rootView = textView;
    }
    public TextView getRoot() {
        return rootView;
    }
    public static DeviceNameBinding inflate(final LayoutInflater layoutInflater) {
        return DeviceNameBinding.inflate(layoutInflater, null, false);
    }
    public static DeviceNameBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.device_name, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return DeviceNameBinding.bind(inflate);
    }
    public static DeviceNameBinding bind(final View view) {
        if (null == view) {
            throw new NullPointerException("rootView");
        }
        return new DeviceNameBinding((TextView) view);
    }
}
