package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class SalesorderListItemBinding implements ViewBinding {

   
    public final CheckBox chbOrder;

   
    public final LinearLayout lnFicheContainer;

   
    public final LinearLayout lnFicheDetailContainer;

   
    public final LinearLayout lnSalesman;

   
    public final LinearLayout lnSalesorderDetailheader;

   
    public final LinearLayout lnSalesorderlistContainer;

   
    private final CardView rootView;

   
    public final TextView tvAmount;

   
    public final TextView tvDateTime;

   
    public final TextView tvDetailHeaderFicheNo;

   
    public final TextView tvDetailHeaderOrderDate;

   
    public final TextView tvFicheNo;

   
    public final TextView tvSalesMan;

   
    public final TextView tvStockName;

   
    public final TextView tvStokCode;

    private SalesorderListItemBinding(final CardView cardView, final CheckBox checkBox, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8) {
        rootView = cardView;
        chbOrder = checkBox;
        lnFicheContainer = linearLayout;
        lnFicheDetailContainer = linearLayout2;
        lnSalesman = linearLayout3;
        lnSalesorderDetailheader = linearLayout4;
        lnSalesorderlistContainer = linearLayout5;
        tvAmount = textView;
        tvDateTime = textView2;
        tvDetailHeaderFicheNo = textView3;
        tvDetailHeaderOrderDate = textView4;
        tvFicheNo = textView5;
        tvSalesMan = textView6;
        tvStockName = textView7;
        tvStokCode = textView8;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static SalesorderListItemBinding inflate(final LayoutInflater layoutInflater) {
        return SalesorderListItemBinding.inflate(layoutInflater, null, false);
    }

   
    public static SalesorderListItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.salesorder_list_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SalesorderListItemBinding.bind(inflate);
    }

   
    public static SalesorderListItemBinding bind(final View view) {
        int i2 = R.id.chbOrder;
        final CheckBox checkBox = ViewBindings.findChildViewById(view, R.id.chbOrder);
        if (null != checkBox) {
            i2 = R.id.ln_fiche_container;
            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_fiche_container);
            if (null != linearLayout) {
                i2 = R.id.ln_fiche_detail_container;
                final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_fiche_detail_container);
                if (null != linearLayout2) {
                    i2 = R.id.ln_salesman;
                    final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_salesman);
                    if (null != linearLayout3) {
                        i2 = R.id.ln_salesorder_detailheader;
                        final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_salesorder_detailheader);
                        if (null != linearLayout4) {
                            i2 = R.id.ln_salesorderlist_container;
                            final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_salesorderlist_container);
                            if (null != linearLayout5) {
                                i2 = R.id.tvAmount;
                                final TextView textView = ViewBindings.findChildViewById(view, R.id.tvAmount);
                                if (null != textView) {
                                    i2 = R.id.tvDateTime;
                                    final TextView textView2 = ViewBindings.findChildViewById(view, R.id.tvDateTime);
                                    if (null != textView2) {
                                        i2 = R.id.tvDetailHeader_FicheNo;
                                        final TextView textView3 = ViewBindings.findChildViewById(view, R.id.tvDetailHeader_FicheNo);
                                        if (null != textView3) {
                                            i2 = R.id.tvDetailHeader_OrderDate;
                                            final TextView textView4 = ViewBindings.findChildViewById(view, R.id.tvDetailHeader_OrderDate);
                                            if (null != textView4) {
                                                i2 = R.id.tvFicheNo;
                                                final TextView textView5 = ViewBindings.findChildViewById(view, R.id.tvFicheNo);
                                                if (null != textView5) {
                                                    i2 = R.id.tvSalesMan;
                                                    final TextView textView6 = ViewBindings.findChildViewById(view, R.id.tvSalesMan);
                                                    if (null != textView6) {
                                                        i2 = R.id.tvStockName;
                                                        final TextView textView7 = ViewBindings.findChildViewById(view, R.id.tvStockName);
                                                        if (null != textView7) {
                                                            i2 = R.id.tvStokCode;
                                                            final TextView textView8 = ViewBindings.findChildViewById(view, R.id.tvStokCode);
                                                            if (null != textView8) {
                                                                return new SalesorderListItemBinding((CardView) view, checkBox, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
