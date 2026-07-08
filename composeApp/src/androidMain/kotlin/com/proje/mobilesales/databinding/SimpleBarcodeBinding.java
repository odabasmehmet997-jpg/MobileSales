package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.proje.mobilesales.R;
 
public final class SimpleBarcodeBinding implements ViewBinding {

   
    public final AppCompatButton btnCancel;

   
    public final AppCompatImageView imgSwitchFlashlight;

   
    public final LinearLayout lnBarcode;

   
    public final LinearLayout lnProductList;

   
    private final LinearLayout rootView;

   
    public final DecoratedBarcodeView zxingBarcodeScanner;

    private SimpleBarcodeBinding(final LinearLayout linearLayout, final AppCompatButton appCompatButton, final AppCompatImageView appCompatImageView, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final DecoratedBarcodeView decoratedBarcodeView) {
        rootView = linearLayout;
        btnCancel = appCompatButton;
        imgSwitchFlashlight = appCompatImageView;
        lnBarcode = linearLayout2;
        lnProductList = linearLayout3;
        zxingBarcodeScanner = decoratedBarcodeView;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static SimpleBarcodeBinding inflate(final LayoutInflater layoutInflater) {
        return SimpleBarcodeBinding.inflate(layoutInflater, null, false);
    }

   
    public static SimpleBarcodeBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.simple_barcode, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SimpleBarcodeBinding.bind(inflate);
    }

   
    public static SimpleBarcodeBinding bind(final View view) {
        int i2 = R.id.btnCancel;
        final AppCompatButton appCompatButton = ViewBindings.findChildViewById(view, R.id.btnCancel);
        if (null != appCompatButton) {
            i2 = R.id.img_switch_flashlight;
            final AppCompatImageView appCompatImageView = ViewBindings.findChildViewById(view, R.id.img_switch_flashlight);
            if (null != appCompatImageView) {
                i2 = R.id.ln_barcode;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_barcode);
                if (null != linearLayout) {
                    i2 = R.id.ln_product_list;
                    final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_product_list);
                    if (null != linearLayout2) {
                        i2 = R.id.zxing_barcode_scanner;
                        final DecoratedBarcodeView decoratedBarcodeView = ViewBindings.findChildViewById(view, R.id.zxing_barcode_scanner);
                        if (null != decoratedBarcodeView) {
                            return new SimpleBarcodeBinding((LinearLayout) view, appCompatButton, appCompatImageView, linearLayout, linearLayout2, decoratedBarcodeView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
