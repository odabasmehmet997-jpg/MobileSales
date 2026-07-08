package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;

public final class FragmentCustomerRiskBinding implements ViewBinding {
    public final RelativeLayout contentFrame;
    public final LinearLayout lyHeader;
    public final RecyclerView rcw;
    private final RelativeLayout rootView;
    public final AppBarSwipeRefreshLayout swipeLayout;
    public final TextView txtRiskLimitClosedTotal;
    public final TextView txtRiskLimitTotal;
    public final TextView txtRiskTotal;
    private FragmentCustomerRiskBinding(final RelativeLayout relativeLayout, final RelativeLayout relativeLayout2, final LinearLayout linearLayout, final RecyclerView recyclerView, final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout, final TextView textView, final TextView textView2, final TextView textView3) {
        rootView = relativeLayout;
        contentFrame = relativeLayout2;
        lyHeader = linearLayout;
        rcw = recyclerView;
        swipeLayout = appBarSwipeRefreshLayout;
        txtRiskLimitClosedTotal = textView;
        txtRiskLimitTotal = textView2;
        txtRiskTotal = textView3;
    }
    public RelativeLayout getRoot() {
        return rootView;
    }
    public static FragmentCustomerRiskBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentCustomerRiskBinding.inflate(layoutInflater, null, false);
    }
    public static FragmentCustomerRiskBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_customer_risk, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentCustomerRiskBinding.bind(inflate);
    }
    public static FragmentCustomerRiskBinding bind(final View view) {
        int i2 = R.id.content_frame;
        final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.content_frame);
        if (null != relativeLayout) {
            i2 = R.id.lyHeader;
            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.lyHeader);
            if (null != linearLayout) {
                i2 = R.id.rcw;
                final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcw);
                if (null != recyclerView) {
                    i2 = R.id.swipe_layout;
                    final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = ViewBindings.findChildViewById(view, R.id.swipe_layout);
                    if (null != appBarSwipeRefreshLayout) {
                        i2 = R.id.txt_riskLimitClosedTotal;
                        final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_riskLimitClosedTotal);
                        if (null != textView) {
                            i2 = R.id.txt_riskLimitTotal;
                            final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_riskLimitTotal);
                            if (null != textView2) {
                                i2 = R.id.txt_riskTotal;
                                final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_riskTotal);
                                if (null != textView3) {
                                    return new FragmentCustomerRiskBinding((RelativeLayout) view, relativeLayout, linearLayout, recyclerView, appBarSwipeRefreshLayout, textView, textView2, textView3);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
