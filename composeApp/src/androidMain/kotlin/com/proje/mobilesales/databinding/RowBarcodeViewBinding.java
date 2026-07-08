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



public final class RowBarcodeViewBinding implements ViewBinding {

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView txtItemCode;

   
    public final AppCompatTextView txtItemName;

    private RowBarcodeViewBinding(final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2) {
        rootView = linearLayout;
        txtItemCode = appCompatTextView;
        txtItemName = appCompatTextView2;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static RowBarcodeViewBinding inflate(final LayoutInflater layoutInflater) {
        return RowBarcodeViewBinding.inflate(layoutInflater, null, false);
    }

   
    public static RowBarcodeViewBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.row_barcode_view, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return RowBarcodeViewBinding.bind(inflate);
    }

   
    public static RowBarcodeViewBinding bind(final View view) {
        int i2 = R.id.txt_itemCode;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txt_itemCode);
        if (null != appCompatTextView) {
            i2 = R.id.txt_itemName;
            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.txt_itemName);
            if (null != appCompatTextView2) {
                return new RowBarcodeViewBinding((LinearLayout) view, appCompatTextView, appCompatTextView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
