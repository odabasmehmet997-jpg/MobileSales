package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ShowCampaignBinding implements ViewBinding {
    public final LinearLayout lnTotalGeneralDiscount;
    public final LinearLayout lnTotalLineDiscount;
    public final LinearLayout lnTotalSurplusDiscount;
    public final ListView lvShowCampaign;
    private final LinearLayout rootView;
    public final AppCompatTextView tvNetTotal;
    public final AppCompatTextView tvSurplusDiscount;
    public final AppCompatTextView tvTotal;
    public final AppCompatTextView tvTotalDiscount;
    public final AppCompatTextView tvTotalGeneralDiscount;
    public final AppCompatTextView tvTotalKdv;
    public final AppCompatTextView tvTotalLineDiscount;
    private ShowCampaignBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final ListView listView, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7) {
        rootView = linearLayout;
        lnTotalGeneralDiscount = linearLayout2;
        lnTotalLineDiscount = linearLayout3;
        lnTotalSurplusDiscount = linearLayout4;
        lvShowCampaign = listView;
        tvNetTotal = appCompatTextView;
        tvSurplusDiscount = appCompatTextView2;
        tvTotal = appCompatTextView3;
        tvTotalDiscount = appCompatTextView4;
        tvTotalGeneralDiscount = appCompatTextView5;
        tvTotalKdv = appCompatTextView6;
        tvTotalLineDiscount = appCompatTextView7;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ShowCampaignBinding inflate(final LayoutInflater layoutInflater) {
        return ShowCampaignBinding.inflate(layoutInflater, null, false);
    }
    public static ShowCampaignBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.show_campaign, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ShowCampaignBinding.bind(inflate);
    }
    public static ShowCampaignBinding bind(final View view) {
        int i2 = R.id.lnTotalGeneralDiscount;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.lnTotalGeneralDiscount);
        if (null != linearLayout) {
            i2 = R.id.lnTotalLineDiscount;
            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.lnTotalLineDiscount);
            if (null != linearLayout2) {
                i2 = R.id.lnTotalSurplusDiscount;
                final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.lnTotalSurplusDiscount);
                if (null != linearLayout3) {
                    i2 = R.id.lvShowCampaign;
                    final ListView listView = ViewBindings.findChildViewById(view, R.id.lvShowCampaign);
                    if (null != listView) {
                        i2 = R.id.tvNetTotal;
                        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvNetTotal);
                        if (null != appCompatTextView) {
                            i2 = R.id.tvSurplusDiscount;
                            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvSurplusDiscount);
                            if (null != appCompatTextView2) {
                                i2 = R.id.tvTotal;
                                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvTotal);
                                if (null != appCompatTextView3) {
                                    i2 = R.id.tvTotalDiscount;
                                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvTotalDiscount);
                                    if (null != appCompatTextView4) {
                                        i2 = R.id.tvTotalGeneralDiscount;
                                        final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvTotalGeneralDiscount);
                                        if (null != appCompatTextView5) {
                                            i2 = R.id.tvTotalKdv;
                                            final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvTotalKdv);
                                            if (null != appCompatTextView6) {
                                                i2 = R.id.tvTotalLineDiscount;
                                                final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.tvTotalLineDiscount);
                                                if (null != appCompatTextView7) {
                                                    return new ShowCampaignBinding((LinearLayout) view, linearLayout, linearLayout2, linearLayout3, listView, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7);
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
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
