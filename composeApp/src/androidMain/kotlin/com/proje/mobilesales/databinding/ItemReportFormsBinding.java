package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ItemReportFormsBinding implements ViewBinding {

   
    public final LinearLayout lnForm;

   
    private final CardView rootView;

    private ItemReportFormsBinding(final CardView cardView, final LinearLayout linearLayout) {
        rootView = cardView;
        lnForm = linearLayout;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemReportFormsBinding inflate(final LayoutInflater layoutInflater) {
        return ItemReportFormsBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemReportFormsBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_report_forms, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemReportFormsBinding.bind(inflate);
    }

   
    public static ItemReportFormsBinding bind(final View view) {
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_form);
        if (null != linearLayout) {
            return new ItemReportFormsBinding((CardView) view, linearLayout);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.ln_form));
    }
}
