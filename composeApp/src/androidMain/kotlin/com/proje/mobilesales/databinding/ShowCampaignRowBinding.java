package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ShowCampaignRowBinding implements ViewBinding {

   
    public final AppCompatImageView imSpinner;

   
    public final LinearLayout linearMaterial1;

   
    public final LinearLayout linearMaterial2;

   
    public final LinearLayout lnCampaignPoint;

   
    public final LinearLayout lnDiscount1;

   
    public final LinearLayout lnDiscount2;

   
    public final LinearLayout lnDiscount3;

   
    public final LinearLayout lnDiscountRatio;

   
    public final LinearLayout lnSurplus;

   
    public final LinearLayout lnSurplusDiscount;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView tvCampaignPoint;

   
    public final AppCompatTextView tvCode;

   
    public final AppCompatTextView tvDiscount1;

   
    public final AppCompatTextView tvDiscount2;

   
    public final AppCompatTextView tvDiscount3;

   
    public final AppCompatTextView tvDiscountRate;

   
    public final AppCompatTextView tvName;

   
    public final AppCompatTextView tvQuantityUnit;

   
    public final AppCompatTextView tvSurplusDiscount;

   
    public final AppCompatTextView tvSurplusQuantityUnit;

   
    public final AppCompatTextView tvType;

    private ShowCampaignRowBinding(final LinearLayout linearLayout, final AppCompatImageView appCompatImageView, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final LinearLayout linearLayout6, final LinearLayout linearLayout7, final LinearLayout linearLayout8, final LinearLayout linearLayout9, final LinearLayout linearLayout10, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7, final AppCompatTextView appCompatTextView8, final AppCompatTextView appCompatTextView9, final AppCompatTextView appCompatTextView10, final AppCompatTextView appCompatTextView11) {
        rootView = linearLayout;
        imSpinner = appCompatImageView;
        linearMaterial1 = linearLayout2;
        linearMaterial2 = linearLayout3;
        lnCampaignPoint = linearLayout4;
        lnDiscount1 = linearLayout5;
        lnDiscount2 = linearLayout6;
        lnDiscount3 = linearLayout7;
        lnDiscountRatio = linearLayout8;
        lnSurplus = linearLayout9;
        lnSurplusDiscount = linearLayout10;
        tvCampaignPoint = appCompatTextView;
        tvCode = appCompatTextView2;
        tvDiscount1 = appCompatTextView3;
        tvDiscount2 = appCompatTextView4;
        tvDiscount3 = appCompatTextView5;
        tvDiscountRate = appCompatTextView6;
        tvName = appCompatTextView7;
        tvQuantityUnit = appCompatTextView8;
        tvSurplusDiscount = appCompatTextView9;
        tvSurplusQuantityUnit = appCompatTextView10;
        tvType = appCompatTextView11;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ShowCampaignRowBinding inflate(final LayoutInflater layoutInflater) {
        return ShowCampaignRowBinding.inflate(layoutInflater, null, false);
    }

   
    public static ShowCampaignRowBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.show_campaign_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ShowCampaignRowBinding.bind(inflate);
    }

   
    public static ShowCampaignRowBinding bind(final View view) {
        int i2 = R.id.imSpinner;
        final AppCompatImageView appCompatImageView = ViewBindings.findChildViewById(view, R.id.imSpinner);
        if (null != appCompatImageView) {
            i2 = R.id.linearMaterial1;
            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial1);
            if (null != linearLayout) {
                i2 = R.id.linearMaterial2;
                final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
                if (null != linearLayout2) {
                    i2 = R.id.lnCampaignPoint;
                    final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.lnCampaignPoint);
                    if (null != linearLayout3) {
                        i2 = R.id.lnDiscount1;
                        final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.lnDiscount1);
                        if (null != linearLayout4) {
                            i2 = R.id.lnDiscount2;
                            final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.lnDiscount2);
                            if (null != linearLayout5) {
                                i2 = R.id.lnDiscount3;
                                final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.lnDiscount3);
                                if (null != linearLayout6) {
                                    i2 = R.id.lnDiscountRatio;
                                    final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.lnDiscountRatio);
                                    if (null != linearLayout7) {
                                        i2 = R.id.lnSurplus;
                                        final LinearLayout linearLayout8 = ViewBindings.findChildViewById(view, R.id.lnSurplus);
                                        if (null != linearLayout8) {
                                            i2 = R.id.lnSurplusDiscount;
                                            final LinearLayout linearLayout9 = ViewBindings.findChildViewById(view, R.id.lnSurplusDiscount);
                                            if (null != linearLayout9) {
                                                i2 = R.id.tvCampaignPoint;
                                                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvCampaignPoint);
                                                if (null != appCompatTextView) {
                                                    i2 = R.id.tvCode;
                                                    final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvCode);
                                                    if (null != appCompatTextView2) {
                                                        i2 = R.id.tvDiscount1;
                                                        final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvDiscount1);
                                                        if (null != appCompatTextView3) {
                                                            i2 = R.id.tvDiscount2;
                                                            final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvDiscount2);
                                                            if (null != appCompatTextView4) {
                                                                i2 = R.id.tvDiscount3;
                                                                final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvDiscount3);
                                                                if (null != appCompatTextView5) {
                                                                    i2 = R.id.tvDiscountRate;
                                                                    final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvDiscountRate);
                                                                    if (null != appCompatTextView6) {
                                                                        i2 = R.id.tvName;
                                                                        final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.tvName);
                                                                        if (null != appCompatTextView7) {
                                                                            i2 = R.id.tvQuantityUnit;
                                                                            final AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.tvQuantityUnit);
                                                                            if (null != appCompatTextView8) {
                                                                                i2 = R.id.tvSurplusDiscount;
                                                                                final AppCompatTextView appCompatTextView9 = ViewBindings.findChildViewById(view, R.id.tvSurplusDiscount);
                                                                                if (null != appCompatTextView9) {
                                                                                    i2 = R.id.tvSurplusQuantityUnit;
                                                                                    final AppCompatTextView appCompatTextView10 = ViewBindings.findChildViewById(view, R.id.tvSurplusQuantityUnit);
                                                                                    if (null != appCompatTextView10) {
                                                                                        i2 = R.id.tvType;
                                                                                        final AppCompatTextView appCompatTextView11 = ViewBindings.findChildViewById(view, R.id.tvType);
                                                                                        if (null != appCompatTextView11) {
                                                                                            return new ShowCampaignRowBinding((LinearLayout) view, appCompatImageView, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8, appCompatTextView9, appCompatTextView10, appCompatTextView11);
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
