package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.view.CustomEditText;



public final class ItemReportParamsBinding implements ViewBinding {

   
    public final LinearLayout lnReportParams;

   
    private final CardView rootView;

   
    public final AppCompatTextView txtCaption;

   
    public final CustomEditText txtValue;

    private ItemReportParamsBinding(final CardView cardView, final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final CustomEditText customEditText) {
        rootView = cardView;
        lnReportParams = linearLayout;
        txtCaption = appCompatTextView;
        txtValue = customEditText;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemReportParamsBinding inflate(final LayoutInflater layoutInflater) {
        return ItemReportParamsBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemReportParamsBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_report_params, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemReportParamsBinding.bind(inflate);
    }

   
    public static ItemReportParamsBinding bind(final View view) {
        int i2 = R.id.ln_report_params;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_report_params);
        if (null != linearLayout) {
            i2 = R.id.txtCaption;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txtCaption);
            if (null != appCompatTextView) {
                i2 = R.id.txtValue;
                final CustomEditText customEditText = ViewBindings.findChildViewById(view, R.id.txtValue);
                if (null != customEditText) {
                    return new ItemReportParamsBinding((CardView) view, linearLayout, appCompatTextView, customEditText);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
