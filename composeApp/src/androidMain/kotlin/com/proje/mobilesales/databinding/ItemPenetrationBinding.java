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
import com.proje.mobilesales.features.penetration.view.list.PenetrationView;



public final class ItemPenetrationBinding implements ViewBinding {

   
    public final PenetrationView penetrationView;

   
    private final CardView rootView;

    private ItemPenetrationBinding(final CardView cardView, final PenetrationView penetrationView) {
        rootView = cardView;
        this.penetrationView = penetrationView;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemPenetrationBinding inflate(final LayoutInflater layoutInflater) {
        return ItemPenetrationBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemPenetrationBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_penetration, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemPenetrationBinding.bind(inflate);
    }

   
    public static ItemPenetrationBinding bind(final View view) {
        final PenetrationView penetrationView = ViewBindings.findChildViewById(view, R.id.penetration_view);
        if (null != penetrationView) {
            return new ItemPenetrationBinding((CardView) view, penetrationView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.penetration_view));
    }
}
