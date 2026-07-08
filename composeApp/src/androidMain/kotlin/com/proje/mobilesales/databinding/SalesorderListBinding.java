package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class SalesorderListBinding implements ViewBinding {

   
    public final EmptyListBinding empty;

   
    private final LinearLayout rootView;

   
    public final RecyclerView rwSalesOrderListView;

    private SalesorderListBinding(final LinearLayout linearLayout, final EmptyListBinding emptyListBinding, final RecyclerView recyclerView) {
        rootView = linearLayout;
        empty = emptyListBinding;
        rwSalesOrderListView = recyclerView;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static SalesorderListBinding inflate(final LayoutInflater layoutInflater) {
        return SalesorderListBinding.inflate(layoutInflater, null, false);
    }

   
    public static SalesorderListBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.salesorder_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SalesorderListBinding.bind(inflate);
    }

   
    public static SalesorderListBinding bind(final View view) {
        int i2 = R.id.empty;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.empty);
        if (null != findChildViewById) {
            final EmptyListBinding bind = EmptyListBinding.bind(findChildViewById);
            final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rwSalesOrderListView);
            if (null != recyclerView) {
                return new SalesorderListBinding((LinearLayout) view, bind, recyclerView);
            }
            i2 = R.id.rwSalesOrderListView;
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
