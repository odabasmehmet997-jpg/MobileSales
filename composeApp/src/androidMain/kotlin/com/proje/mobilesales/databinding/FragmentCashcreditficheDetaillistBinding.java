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
public final class FragmentCashcreditficheDetaillistBinding implements ViewBinding {
    public final RecyclerView rcwCashcreditdetaillist;
    private final RelativeLayout rootView;
    private FragmentCashcreditficheDetaillistBinding(final RelativeLayout relativeLayout, final RecyclerView recyclerView) {
        rootView = relativeLayout;
        rcwCashcreditdetaillist = recyclerView;
    }
    public RelativeLayout getRoot() {
        return rootView;
    }
    public static FragmentCashcreditficheDetaillistBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentCashcreditficheDetaillistBinding.inflate(layoutInflater, null, false);
    }
    public static FragmentCashcreditficheDetaillistBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_cashcreditfiche_detaillist, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentCashcreditficheDetaillistBinding.bind(inflate);
    }
    public static FragmentCashcreditficheDetaillistBinding bind(final View view) {
        final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcw_cashcreditdetaillist);
        if (null != recyclerView) {
            return new FragmentCashcreditficheDetaillistBinding((RelativeLayout) view, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.rcw_cashcreditdetaillist));
    }
}
