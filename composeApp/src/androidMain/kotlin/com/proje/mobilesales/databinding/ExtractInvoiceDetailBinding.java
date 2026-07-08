package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class ExtractInvoiceDetailBinding implements ViewBinding {
    public final ListView lvList;
    private final LinearLayout rootView;
    private ExtractInvoiceDetailBinding(final LinearLayout linearLayout, final ListView listView) {
        rootView = linearLayout;
        lvList = listView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ExtractInvoiceDetailBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.extract_invoice_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
    public static ExtractInvoiceDetailBinding bind(final View view) {
        final ListView listView = ViewBindings.findChildViewById(view, R.id.lvList);
        if (null != listView) {
            return new ExtractInvoiceDetailBinding((LinearLayout) view, listView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.lvList));
    }
}
