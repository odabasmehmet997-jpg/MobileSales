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



public final class ItemChequedeedDetailRowBinding implements ViewBinding {

   
    public final LinearLayout lnDetailContainer;

   
    private final CardView rootView;

   
    public final TextView txtAccountNo;

   
    public final TextView txtAmount;

   
    public final TextView txtBranch;

   
    public final TextView txtDebtor;

   
    public final TextView txtExpiry;

    private ItemChequedeedDetailRowBinding(final CardView cardView, final LinearLayout linearLayout, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5) {
        rootView = cardView;
        lnDetailContainer = linearLayout;
        txtAccountNo = textView;
        txtAmount = textView2;
        txtBranch = textView3;
        txtDebtor = textView4;
        txtExpiry = textView5;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemChequedeedDetailRowBinding inflate(final LayoutInflater layoutInflater) {
        return ItemChequedeedDetailRowBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemChequedeedDetailRowBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_chequedeed_detail_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemChequedeedDetailRowBinding.bind(inflate);
    }

   
    public static ItemChequedeedDetailRowBinding bind(final View view) {
        int i2 = R.id.ln_detailContainer;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_detailContainer);
        if (null != linearLayout) {
            i2 = R.id.txt_accountNo;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_accountNo);
            if (null != textView) {
                i2 = R.id.txt_amount;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_amount);
                if (null != textView2) {
                    i2 = R.id.txt_branch;
                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_branch);
                    if (null != textView3) {
                        i2 = R.id.txt_debtor;
                        final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_debtor);
                        if (null != textView4) {
                            i2 = R.id.txt_expiry;
                            final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_expiry);
                            if (null != textView5) {
                                return new ItemChequedeedDetailRowBinding((CardView) view, linearLayout, textView, textView2, textView3, textView4, textView5);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
