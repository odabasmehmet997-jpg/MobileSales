package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class SalesmanListItemBinding implements ViewBinding {

   
    public final LinearLayout lnSalesmanListContainer;

   
    private final CardView rootView;

   
    public final TextView tvSalesMan;

   
    public final TextView tvSalesManCode;

    private SalesmanListItemBinding(final CardView cardView, final LinearLayout linearLayout, final TextView textView, final TextView textView2) {
        rootView = cardView;
        lnSalesmanListContainer = linearLayout;
        tvSalesMan = textView;
        tvSalesManCode = textView2;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static SalesmanListItemBinding inflate(final LayoutInflater layoutInflater) {
        return SalesmanListItemBinding.inflate(layoutInflater, null, false);
    }

   
    public static SalesmanListItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.salesman_list_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SalesmanListItemBinding.bind(inflate);
    }

   
    public static SalesmanListItemBinding bind(final View view) {
        int i2 = R.id.ln_salesman_list_container;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_salesman_list_container);
        if (null != linearLayout) {
            i2 = R.id.tvSalesMan;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.tvSalesMan);
            if (null != textView) {
                i2 = R.id.tvSalesManCode;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.tvSalesManCode);
                if (null != textView2) {
                    return new SalesmanListItemBinding((CardView) view, linearLayout, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
