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



public final class FragmentChequedeedficheDetaillistBinding implements ViewBinding {

   
    public final RecyclerView rcwChequedeeddetaillist;

   
    private final RelativeLayout rootView;

    private FragmentChequedeedficheDetaillistBinding(final RelativeLayout relativeLayout, final RecyclerView recyclerView) {
        rootView = relativeLayout;
        rcwChequedeeddetaillist = recyclerView;
    }

    
   
    public RelativeLayout getRoot() {
        return rootView;
    }

   
    public static FragmentChequedeedficheDetaillistBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentChequedeedficheDetaillistBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentChequedeedficheDetaillistBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_chequedeedfiche_detaillist, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentChequedeedficheDetaillistBinding.bind(inflate);
    }

   
    public static FragmentChequedeedficheDetaillistBinding bind(final View view) {
        final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcw_chequedeeddetaillist);
        if (null != recyclerView) {
            return new FragmentChequedeedficheDetaillistBinding((RelativeLayout) view, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.rcw_chequedeeddetaillist));
    }
}
