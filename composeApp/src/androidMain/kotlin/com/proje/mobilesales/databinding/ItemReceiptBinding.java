package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ItemReceiptBinding implements ViewBinding {

   
    public final ButtonMoreBinding buttonMore;

   
    public final ImageView imgFicheTransfer;

   
    public final LinearLayout lnFicheContainer;

   
    public final LinearLayout lnFicheLeftContainer;

   
    private final CardView rootView;

   
    public final TextView txtExplanation;

   
    public final TextView txtFichedate;

   
    public final TextView txtFicheref;

   
    public final TextView txtTotal;

    private ItemReceiptBinding(final CardView cardView, final ButtonMoreBinding buttonMoreBinding, final ImageView imageView, final LinearLayout linearLayout, final LinearLayout linearLayout2, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4) {
        rootView = cardView;
        buttonMore = buttonMoreBinding;
        imgFicheTransfer = imageView;
        lnFicheContainer = linearLayout;
        lnFicheLeftContainer = linearLayout2;
        txtExplanation = textView;
        txtFichedate = textView2;
        txtFicheref = textView3;
        txtTotal = textView4;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemReceiptBinding inflate(final LayoutInflater layoutInflater) {
        return ItemReceiptBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemReceiptBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_receipt, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemReceiptBinding.bind(inflate);
    }

   
    public static ItemReceiptBinding bind(final View view) {
        int i2 = R.id.button_more;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.button_more);
        if (null != findChildViewById) {
            final ButtonMoreBinding bind = ButtonMoreBinding.bind(findChildViewById);
            i2 = R.id.img_fiche_transfer;
            final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_fiche_transfer);
            if (null != imageView) {
                i2 = R.id.ln_ficheContainer;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_ficheContainer);
                if (null != linearLayout) {
                    i2 = R.id.ln_ficheLeftContainer;
                    final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_ficheLeftContainer);
                    if (null != linearLayout2) {
                        i2 = R.id.txt_explanation;
                        final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_explanation);
                        if (null != textView) {
                            i2 = R.id.txt_fichedate;
                            final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_fichedate);
                            if (null != textView2) {
                                i2 = R.id.txt_ficheref;
                                final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_ficheref);
                                if (null != textView3) {
                                    i2 = R.id.txt_total;
                                    final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_total);
                                    if (null != textView4) {
                                        return new ItemReceiptBinding((CardView) view, bind, imageView, linearLayout, linearLayout2, textView, textView2, textView3, textView4);
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
