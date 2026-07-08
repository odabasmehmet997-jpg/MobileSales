package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.journeyapps.barcodescanner.BarcodeView;
import com.journeyapps.barcodescanner.ViewfinderView;
import com.proje.mobilesales.R;

public final class CustomBarcodeScannerBinding implements ViewBinding {
    private final View rootView;
    public final BarcodeView zxingBarcodeSurface;
    public final TextView zxingStatusView;
    public final ViewfinderView zxingViewfinderView;
    private CustomBarcodeScannerBinding(final View view, final BarcodeView barcodeView, final TextView textView, final ViewfinderView viewfinderView) {
        rootView = view;
        zxingBarcodeSurface = barcodeView;
        zxingStatusView = textView;
        zxingViewfinderView = viewfinderView;
    }
    public View getRoot() {
        return rootView;
    }
    public static CustomBarcodeScannerBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        if (null == viewGroup) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.custom_barcode_scanner, viewGroup);
        return CustomBarcodeScannerBinding.bind(viewGroup);
    }
    public static CustomBarcodeScannerBinding bind(final View view) {
        int i2 = R.id.zxing_barcode_surface;
        final BarcodeView barcodeView = ViewBindings.findChildViewById(view, R.id.zxing_barcode_surface);
        if (null != barcodeView) {
            i2 = R.id.zxing_status_view;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.zxing_status_view);
            if (null != textView) {
                i2 = R.id.zxing_viewfinder_view;
                final ViewfinderView viewfinderView = ViewBindings.findChildViewById(view, R.id.zxing_viewfinder_view);
                if (null != viewfinderView) {
                    return new CustomBarcodeScannerBinding(view, barcodeView, textView, viewfinderView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
