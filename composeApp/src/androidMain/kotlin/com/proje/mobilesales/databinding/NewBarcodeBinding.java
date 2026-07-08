package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.proje.mobilesales.R;



public final class NewBarcodeBinding implements ViewBinding {

   
    public final EditText edtLaserBarcode;

   
    public final AppCompatImageView imgSwitchFlashlight;

   
    public final LinearLayout lnBarcode;

   
    public final LinearLayout lnLaser;

   
    public final LinearLayout lnProductList;

   
    private final LinearLayout rootView;

   
    public final RecyclerView rwProductList;

   
    public final TextView txtEmptyList;

   
    public final DecoratedBarcodeView zxingBarcodeScanner;

    private NewBarcodeBinding(final LinearLayout linearLayout, final EditText editText, final AppCompatImageView appCompatImageView, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final RecyclerView recyclerView, final TextView textView, final DecoratedBarcodeView decoratedBarcodeView) {
        rootView = linearLayout;
        edtLaserBarcode = editText;
        imgSwitchFlashlight = appCompatImageView;
        lnBarcode = linearLayout2;
        lnLaser = linearLayout3;
        lnProductList = linearLayout4;
        rwProductList = recyclerView;
        txtEmptyList = textView;
        zxingBarcodeScanner = decoratedBarcodeView;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static NewBarcodeBinding inflate(final LayoutInflater layoutInflater) {
        return NewBarcodeBinding.inflate(layoutInflater, null, false);
    }

   
    public static NewBarcodeBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.new_barcode, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return NewBarcodeBinding.bind(inflate);
    }

   
    public static NewBarcodeBinding bind(final View view) {
        int i2 = R.id.edtLaserBarcode;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.edtLaserBarcode);
        if (null != editText) {
            i2 = R.id.img_switch_flashlight;
            final AppCompatImageView appCompatImageView = ViewBindings.findChildViewById(view, R.id.img_switch_flashlight);
            if (null != appCompatImageView) {
                i2 = R.id.ln_barcode;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_barcode);
                if (null != linearLayout) {
                    i2 = R.id.ln_laser;
                    final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_laser);
                    if (null != linearLayout2) {
                        i2 = R.id.ln_product_list;
                        final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_product_list);
                        if (null != linearLayout3) {
                            i2 = R.id.rwProductList;
                            final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rwProductList);
                            if (null != recyclerView) {
                                i2 = R.id.txtEmptyList;
                                final TextView textView = ViewBindings.findChildViewById(view, R.id.txtEmptyList);
                                if (null != textView) {
                                    i2 = R.id.zxing_barcode_scanner;
                                    final DecoratedBarcodeView decoratedBarcodeView = ViewBindings.findChildViewById(view, R.id.zxing_barcode_scanner);
                                    if (null != decoratedBarcodeView) {
                                        return new NewBarcodeBinding((LinearLayout) view, editText, appCompatImageView, linearLayout, linearLayout2, linearLayout3, recyclerView, textView, decoratedBarcodeView);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
