package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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



public final class FragmentCustomerRiskNetsisBinding implements ViewBinding {

   
    public final CheckBox chbFatRiskDavran;

   
    public final CheckBox chbIrsRiskDavran;

   
    public final CheckBox chbSevkRiskDavran;

   
    public final CheckBox chbSipRiskDavran;

   
    public final CheckBox chbYukRiskDavran;

   
    public final RelativeLayout contentFrame;

   
    public final LinearLayout lyChecks;

   
    public final RecyclerView rcw;

   
    private final RelativeLayout rootView;

   
    public final AppBarSwipeRefreshLayout swipeLayout;

   
    public final TextView txtRiskToplami;

    private FragmentCustomerRiskNetsisBinding(final RelativeLayout relativeLayout, final CheckBox checkBox, final CheckBox checkBox2, final CheckBox checkBox3, final CheckBox checkBox4, final CheckBox checkBox5, final RelativeLayout relativeLayout2, final LinearLayout linearLayout, final RecyclerView recyclerView, final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout, final TextView textView) {
        rootView = relativeLayout;
        chbFatRiskDavran = checkBox;
        chbIrsRiskDavran = checkBox2;
        chbSevkRiskDavran = checkBox3;
        chbSipRiskDavran = checkBox4;
        chbYukRiskDavran = checkBox5;
        contentFrame = relativeLayout2;
        lyChecks = linearLayout;
        rcw = recyclerView;
        swipeLayout = appBarSwipeRefreshLayout;
        txtRiskToplami = textView;
    }

    
   
    public RelativeLayout getRoot() {
        return rootView;
    }

   
    public static FragmentCustomerRiskNetsisBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentCustomerRiskNetsisBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentCustomerRiskNetsisBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_customer_risk_netsis, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentCustomerRiskNetsisBinding.bind(inflate);
    }

   
    public static FragmentCustomerRiskNetsisBinding bind(final View view) {
        int i2 = R.id.chbFatRiskDavran;
        final CheckBox checkBox = ViewBindings.findChildViewById(view, R.id.chbFatRiskDavran);
        if (null != checkBox) {
            i2 = R.id.chbIrsRiskDavran;
            final CheckBox checkBox2 = ViewBindings.findChildViewById(view, R.id.chbIrsRiskDavran);
            if (null != checkBox2) {
                i2 = R.id.chbSevkRiskDavran;
                final CheckBox checkBox3 = ViewBindings.findChildViewById(view, R.id.chbSevkRiskDavran);
                if (null != checkBox3) {
                    i2 = R.id.chbSipRiskDavran;
                    final CheckBox checkBox4 = ViewBindings.findChildViewById(view, R.id.chbSipRiskDavran);
                    if (null != checkBox4) {
                        i2 = R.id.chbYukRiskDavran;
                        final CheckBox checkBox5 = ViewBindings.findChildViewById(view, R.id.chbYukRiskDavran);
                        if (null != checkBox5) {
                            i2 = R.id.content_frame;
                            final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.content_frame);
                            if (null != relativeLayout) {
                                i2 = R.id.lyChecks;
                                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.lyChecks);
                                if (null != linearLayout) {
                                    i2 = R.id.rcw;
                                    final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcw);
                                    if (null != recyclerView) {
                                        i2 = R.id.swipe_layout;
                                        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = ViewBindings.findChildViewById(view, R.id.swipe_layout);
                                        if (null != appBarSwipeRefreshLayout) {
                                            i2 = R.id.txtRiskToplami;
                                            final TextView textView = ViewBindings.findChildViewById(view, R.id.txtRiskToplami);
                                            if (null != textView) {
                                                return new FragmentCustomerRiskNetsisBinding((RelativeLayout) view, checkBox, checkBox2, checkBox3, checkBox4, checkBox5, relativeLayout, linearLayout, recyclerView, appBarSwipeRefreshLayout, textView);
                                            }
                                        }
                                    }
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
