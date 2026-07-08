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
import com.proje.mobilesales.features.visit.view.VisitView;



public final class ItemVisitBinding implements ViewBinding {

   
    private final CardView rootView;

   
    public final VisitView visitView;

    private ItemVisitBinding(final CardView cardView, final VisitView visitView) {
        rootView = cardView;
        this.visitView = visitView;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemVisitBinding inflate(final LayoutInflater layoutInflater) {
        return ItemVisitBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemVisitBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_visit, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemVisitBinding.bind(inflate);
    }

   
    public static ItemVisitBinding bind(final View view) {
        final VisitView visitView = ViewBindings.findChildViewById(view, R.id.visit_view);
        if (null != visitView) {
            return new ItemVisitBinding((CardView) view, visitView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.visit_view));
    }
}
