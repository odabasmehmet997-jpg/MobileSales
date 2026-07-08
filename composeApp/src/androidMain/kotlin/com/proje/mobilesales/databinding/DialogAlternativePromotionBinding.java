package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class DialogAlternativePromotionBinding implements ViewBinding {
    public final LinearLayout lnItemAlternativePromotionFooter;
    public final LinearLayout lnItemAlternativePromotionHeader;
    public final RecyclerView recyclerViewList;
    private final ConstraintLayout rootView;
    public final TextView tvAlternativeLineQuantity;
    public final TextView tvAlternativeSelectedQuantity;
    private DialogAlternativePromotionBinding(final ConstraintLayout constraintLayout, final LinearLayout linearLayout, final LinearLayout linearLayout2, final RecyclerView recyclerView, final TextView textView, final TextView textView2) {
        rootView = constraintLayout;
        lnItemAlternativePromotionFooter = linearLayout;
        lnItemAlternativePromotionHeader = linearLayout2;
        recyclerViewList = recyclerView;
        tvAlternativeLineQuantity = textView;
        tvAlternativeSelectedQuantity = textView2;
    }
    public ConstraintLayout getRoot() {
        return rootView;
    }
    public static DialogAlternativePromotionBinding inflate(final LayoutInflater layoutInflater) {
        return DialogAlternativePromotionBinding.inflate(layoutInflater, null, false);
    }
    public static DialogAlternativePromotionBinding inflate(final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.dialog_alternative_promotion, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return DialogAlternativePromotionBinding.bind(inflate);
    }
    public static DialogAlternativePromotionBinding bind(final View view) {
        int i2 = R.id.ln_item_alternative_promotion_footer;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_item_alternative_promotion_footer);
        if (null != linearLayout) {
            i2 = R.id.ln_item_alternative_promotion_header;
            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_item_alternative_promotion_header);
            if (null != linearLayout2) {
                i2 = R.id.recyclerViewList;
                final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.recyclerViewList);
                if (null != recyclerView) {
                    i2 = R.id.tv_alternative_line_quantity;
                    final TextView textView = ViewBindings.findChildViewById(view, R.id.tv_alternative_line_quantity);
                    if (null != textView) {
                        i2 = R.id.tv_alternative_selected_quantity;
                        final TextView textView2 = ViewBindings.findChildViewById(view, R.id.tv_alternative_selected_quantity);
                        if (null != textView2) {
                            return new DialogAlternativePromotionBinding((ConstraintLayout) view, linearLayout, linearLayout2, recyclerView, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
