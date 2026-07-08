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
public final class DistributionListItemBinding implements ViewBinding {
    public final ButtonMoreBinding buttonMore;
    public final TextView lblCari;
    public final LinearLayout lnCari;
    public final LinearLayout lnDistributionListContainer;
    private final CardView rootView;
    public final TextView tvCustomer;
    public final TextView tvDateTime;
    public final TextView tvFicheNo;
    private DistributionListItemBinding(final CardView cardView, final ButtonMoreBinding buttonMoreBinding, final TextView textView, final LinearLayout linearLayout, final LinearLayout linearLayout2, final TextView textView2, final TextView textView3, final TextView textView4) {
        rootView = cardView;
        buttonMore = buttonMoreBinding;
        lblCari = textView;
        lnCari = linearLayout;
        lnDistributionListContainer = linearLayout2;
        tvCustomer = textView2;
        tvDateTime = textView3;
        tvFicheNo = textView4;
    }
    public CardView getRoot() {
        return rootView;
    }
    public static DistributionListItemBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.distribution_list_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return DistributionListItemBinding.bind(inflate);
    }
    public static DistributionListItemBinding bind(final View view) {
        int i2 = R.id.button_more;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.button_more);
        if (null != findChildViewById) {
            final ButtonMoreBinding bind = ButtonMoreBinding.bind(findChildViewById);
            i2 = R.id.lblCari;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.lblCari);
            if (null != textView) {
                i2 = R.id.ln_cari;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_cari);
                if (null != linearLayout) {
                    i2 = R.id.ln_distribution_list_container;
                    final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_distribution_list_container);
                    if (null != linearLayout2) {
                        i2 = R.id.tvCustomer;
                        final TextView textView2 = ViewBindings.findChildViewById(view, R.id.tvCustomer);
                        if (null != textView2) {
                            i2 = R.id.tvDateTime;
                            final TextView textView3 = ViewBindings.findChildViewById(view, R.id.tvDateTime);
                            if (null != textView3) {
                                i2 = R.id.tvFicheNo;
                                final TextView textView4 = ViewBindings.findChildViewById(view, R.id.tvFicheNo);
                                if (null != textView4) {
                                    return new DistributionListItemBinding((CardView) view, bind, textView, linearLayout, linearLayout2, textView2, textView3, textView4);
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
