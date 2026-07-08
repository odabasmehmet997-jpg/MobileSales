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



public final class ItemCashcreditDetailRowBinding implements ViewBinding {

   
    public final LinearLayout lnDetailContainer;

   
    private final CardView rootView;

   
    public final TextView txtAmount;

   
    public final TextView txtMakbuzNo;

    private ItemCashcreditDetailRowBinding(final CardView cardView, final LinearLayout linearLayout, final TextView textView, final TextView textView2) {
        rootView = cardView;
        lnDetailContainer = linearLayout;
        txtAmount = textView;
        txtMakbuzNo = textView2;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemCashcreditDetailRowBinding inflate(final LayoutInflater layoutInflater) {
        return ItemCashcreditDetailRowBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemCashcreditDetailRowBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_cashcredit_detail_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemCashcreditDetailRowBinding.bind(inflate);
    }

   
    public static ItemCashcreditDetailRowBinding bind(final View view) {
        int i2 = R.id.ln_detailContainer;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_detailContainer);
        if (null != linearLayout) {
            i2 = R.id.txt_amount;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_amount);
            if (null != textView) {
                i2 = R.id.txt_MakbuzNo;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_MakbuzNo);
                if (null != textView2) {
                    return new ItemCashcreditDetailRowBinding((CardView) view, linearLayout, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
