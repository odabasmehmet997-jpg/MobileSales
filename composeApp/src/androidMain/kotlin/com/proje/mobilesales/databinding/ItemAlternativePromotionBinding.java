package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ItemAlternativePromotionBinding implements ViewBinding {
    public final LinearLayout lnItemAlternativePromotion;
    private final CardView rootView;
    public final EditText tvPromotionAmount;
    public final TextView tvPromotionCode;
    public final TextView tvPromotionName;
    public final TextView tvPromotionStock;
    private ItemAlternativePromotionBinding(final CardView cardView, final LinearLayout linearLayout, final EditText editText, final TextView textView, final TextView textView2, final TextView textView3) {
        rootView = cardView;
        lnItemAlternativePromotion = linearLayout;
        tvPromotionAmount = editText;
        tvPromotionCode = textView;
        tvPromotionName = textView2;
        tvPromotionStock = textView3;
    }
    public CardView getRoot() {
        return rootView;
    }
    public static ItemAlternativePromotionBinding inflate(final LayoutInflater layoutInflater) {
        return ItemAlternativePromotionBinding.inflate(layoutInflater, null, false);
    }
    public static ItemAlternativePromotionBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_alternative_promotion, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemAlternativePromotionBinding.bind(inflate);
    }
    public static ItemAlternativePromotionBinding bind(final View view) {
        int i2 = R.id.ln_item_alternative_promotion;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_item_alternative_promotion);
        if (null != linearLayout) {
            i2 = R.id.tv_promotion_amount;
            final EditText editText = ViewBindings.findChildViewById(view, R.id.tv_promotion_amount);
            if (null != editText) {
                i2 = R.id.tv_promotion_code;
                final TextView textView = ViewBindings.findChildViewById(view, R.id.tv_promotion_code);
                if (null != textView) {
                    i2 = R.id.tv_promotion_name;
                    final TextView textView2 = ViewBindings.findChildViewById(view, R.id.tv_promotion_name);
                    if (null != textView2) {
                        i2 = R.id.tv_promotion_stock;
                        final TextView textView3 = ViewBindings.findChildViewById(view, R.id.tv_promotion_stock);
                        if (null != textView3) {
                            return new ItemAlternativePromotionBinding((CardView) view, linearLayout, editText, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
