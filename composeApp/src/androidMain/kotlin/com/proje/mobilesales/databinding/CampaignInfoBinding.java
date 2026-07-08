package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class CampaignInfoBinding implements ViewBinding {
    public final AppCompatTextView FVAMOUNT;
    public final AppCompatTextView FVCODE;
    public final AppCompatTextView FVNAME;
    public final AppCompatTextView FVPOINT;
    private final LinearLayout rootView;
    private CampaignInfoBinding(final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4) {
        rootView = linearLayout;
        FVAMOUNT = appCompatTextView;
        FVCODE = appCompatTextView2;
        FVNAME = appCompatTextView3;
        FVPOINT = appCompatTextView4;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static CampaignInfoBinding inflate(final LayoutInflater layoutInflater) {
        return CampaignInfoBinding.inflate(layoutInflater, null, false);
    }
    public static CampaignInfoBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.campaign_info, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return CampaignInfoBinding.bind(inflate);
    }
    public static CampaignInfoBinding bind(final View view) {
        int i2 = R.id.FVAMOUNT;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.FVAMOUNT);
        if (null != appCompatTextView) {
            i2 = R.id.FVCODE;
            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.FVCODE);
            if (null != appCompatTextView2) {
                i2 = R.id.FVNAME;
                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.FVNAME);
                if (null != appCompatTextView3) {
                    i2 = R.id.FVPOINT;
                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.FVPOINT);
                    if (null != appCompatTextView4) {
                        return new CampaignInfoBinding((LinearLayout) view, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
