package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ItemReportsGridRowBinding implements ViewBinding {

   
    private final ConstraintLayout rootView;

   
    public final LinearLayout userReportsGridRow;

    private ItemReportsGridRowBinding(final ConstraintLayout constraintLayout, final LinearLayout linearLayout) {
        rootView = constraintLayout;
        userReportsGridRow = linearLayout;
    }

    
   
    public ConstraintLayout getRoot() {
        return rootView;
    }

   
    public static ItemReportsGridRowBinding inflate(final LayoutInflater layoutInflater) {
        return ItemReportsGridRowBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemReportsGridRowBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_reports_grid_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemReportsGridRowBinding.bind(inflate);
    }

   
    public static ItemReportsGridRowBinding bind(final View view) {
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.userReportsGridRow);
        if (null != linearLayout) {
            return new ItemReportsGridRowBinding((ConstraintLayout) view, linearLayout);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.userReportsGridRow));
    }
}
