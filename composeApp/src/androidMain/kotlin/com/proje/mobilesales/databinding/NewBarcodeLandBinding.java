package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.proje.mobilesales.R;

public final class NewBarcodeLandBinding implements ViewBinding {
    public final AppCompatImageView imgSwitchFlashlight;
    private final LinearLayout rootView;
    public final RecyclerView rwProductList;
    public final TextView txtEmptyList;
    public final DecoratedBarcodeView zxingBarcodeScanner;
    private NewBarcodeLandBinding(final LinearLayout linearLayout, final AppCompatImageView appCompatImageView, final RecyclerView recyclerView, final TextView textView, final DecoratedBarcodeView decoratedBarcodeView) {
        rootView = linearLayout;
        imgSwitchFlashlight = appCompatImageView;
        rwProductList = recyclerView;
        txtEmptyList = textView;
        zxingBarcodeScanner = decoratedBarcodeView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static NewBarcodeLandBinding inflate(final LayoutInflater layoutInflater) {
        return NewBarcodeLandBinding.inflate(layoutInflater, null, false);
    }
    public static NewBarcodeLandBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.new_barcode_land, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return NewBarcodeLandBinding.bind(inflate);
    }
    public static NewBarcodeLandBinding bind(final View view) {
        int i2 = R.id.img_switch_flashlight;
        final AppCompatImageView appCompatImageView = ViewBindings.findChildViewById(view, R.id.img_switch_flashlight);
        if (null != appCompatImageView) {
            i2 = R.id.rwProductList;
            final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rwProductList);
            if (null != recyclerView) {
                i2 = R.id.txtEmptyList;
                final TextView textView = ViewBindings.findChildViewById(view, R.id.txtEmptyList);
                if (null != textView) {
                    i2 = R.id.zxing_barcode_scanner;
                    final DecoratedBarcodeView decoratedBarcodeView = ViewBindings.findChildViewById(view, R.id.zxing_barcode_scanner);
                    if (null != decoratedBarcodeView) {
                        return new NewBarcodeLandBinding((LinearLayout) view, appCompatImageView, recyclerView, textView, decoratedBarcodeView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
