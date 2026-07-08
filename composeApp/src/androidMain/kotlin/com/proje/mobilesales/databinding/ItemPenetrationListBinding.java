package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.penetration.view.detail.PenetrationLineView;



public final class ItemPenetrationListBinding implements ViewBinding {

   
    public final PenetrationLineView penetrationLineView;

   
    private final CardView rootView;

    private ItemPenetrationListBinding(final CardView cardView, final PenetrationLineView penetrationLineView) {
        rootView = cardView;
        this.penetrationLineView = penetrationLineView;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemPenetrationListBinding inflate(final LayoutInflater layoutInflater) {
        return ItemPenetrationListBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemPenetrationListBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_penetration_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemPenetrationListBinding.bind(inflate);
    }

   
    public static ItemPenetrationListBinding bind(final View view) {
        final PenetrationLineView penetrationLineView = ViewBindings.findChildViewById(view, R.id.penetration_line_view);
        if (null != penetrationLineView) {
            return new ItemPenetrationListBinding((CardView) view, penetrationLineView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.penetration_line_view));
    }
}
