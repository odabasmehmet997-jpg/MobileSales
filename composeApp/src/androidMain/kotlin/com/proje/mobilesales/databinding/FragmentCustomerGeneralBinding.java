package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class FragmentCustomerGeneralBinding implements ViewBinding {

   
    public final RecyclerView rcw;

   
    private final RelativeLayout rootView;

    private FragmentCustomerGeneralBinding(final RelativeLayout relativeLayout, final RecyclerView recyclerView) {
        rootView = relativeLayout;
        rcw = recyclerView;
    }

    
   
    public RelativeLayout getRoot() {
        return rootView;
    }

   
    public static FragmentCustomerGeneralBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentCustomerGeneralBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentCustomerGeneralBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_customer_general, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentCustomerGeneralBinding.bind(inflate);
    }

   
    public static FragmentCustomerGeneralBinding bind(final View view) {
        final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcw);
        if (null != recyclerView) {
            return new FragmentCustomerGeneralBinding((RelativeLayout) view, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.rcw));
    }
}
