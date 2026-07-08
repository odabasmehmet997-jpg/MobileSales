package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
 
public final class OrderAvgBinding implements ViewBinding {
    public final AppCompatImageButton btnCalAvgInv;
    public final AppCompatCheckBox chkBoxReportAvg;
    public final AppCompatImageButton imgList;
    public final ActivityBaseReportWcfBinding linearDateLayout;
    public final LinearLayout linearFilterDiv;
    public final LinearLayout linearMaterial2;
    public final LinearLayout linearProgress;
    public final LinearLayout linearProgressSpin;
    public final ListView lvReportOrder;
    private final LinearLayout rootView;
    public final AppCompatSpinner spUser;
    public final AppCompatTextView txtAciklama;
    public final AppCompatTextView txtIndirimTutari;
    public final AppCompatTextView txtNetTutar;
    public final AppCompatTextView txtViewCalDate;
    public final AppCompatTextView txtViewCalDay;
    public final AppCompatTextView txtViewCalTotal;
    public final AppCompatTextView txtViewFisNo;
    public final AppCompatTextView txtViewTarih;
    public final AppCompatTextView txtViewTutar;
    private OrderAvgBinding( final LinearLayout linearLayout,  final AppCompatImageButton appCompatImageButton,  final AppCompatCheckBox appCompatCheckBox,  final AppCompatImageButton appCompatImageButton2,  final ActivityBaseReportWcfBinding activityBaseReportWcfBinding,  final LinearLayout linearLayout2,  final LinearLayout linearLayout3,  final LinearLayout linearLayout4,  final LinearLayout linearLayout5,  final ListView listView,  final AppCompatSpinner appCompatSpinner,  final AppCompatTextView appCompatTextView,  final AppCompatTextView appCompatTextView2,  final AppCompatTextView appCompatTextView3,  final AppCompatTextView appCompatTextView4,  final AppCompatTextView appCompatTextView5,  final AppCompatTextView appCompatTextView6,  final AppCompatTextView appCompatTextView7,  final AppCompatTextView appCompatTextView8,  final AppCompatTextView appCompatTextView9) {
        rootView = linearLayout;
        btnCalAvgInv = appCompatImageButton;
        chkBoxReportAvg = appCompatCheckBox;
        imgList = appCompatImageButton2;
        linearDateLayout = activityBaseReportWcfBinding;
        linearFilterDiv = linearLayout2;
        linearMaterial2 = linearLayout3;
        linearProgress = linearLayout4;
        linearProgressSpin = linearLayout5;
        lvReportOrder = listView;
        spUser = appCompatSpinner;
        txtAciklama = appCompatTextView;
        txtIndirimTutari = appCompatTextView2;
        txtNetTutar = appCompatTextView3;
        txtViewCalDate = appCompatTextView4;
        txtViewCalDay = appCompatTextView5;
        txtViewCalTotal = appCompatTextView6;
        txtViewFisNo = appCompatTextView7;
        txtViewTarih = appCompatTextView8;
        txtViewTutar = appCompatTextView9;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static OrderAvgBinding inflate( final LayoutInflater layoutInflater) {
        return OrderAvgBinding.inflate(layoutInflater, null, false);
    }
    public static OrderAvgBinding inflate( final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.order_avg, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return OrderAvgBinding.bind(inflate);
    }
    public static OrderAvgBinding bind( final View view) {
        int i2 = R.id.btnCalAvgInv;
        final AppCompatImageButton appCompatImageButton = ViewBindings.findChildViewById(view, R.id.btnCalAvgInv);
        if (null != appCompatImageButton) {
            i2 = R.id.chkBoxReportAvg;
            final AppCompatCheckBox appCompatCheckBox = ViewBindings.findChildViewById(view, R.id.chkBoxReportAvg);
            if (null != appCompatCheckBox) {
                i2 = R.id.imgList;
                final AppCompatImageButton appCompatImageButton2 = ViewBindings.findChildViewById(view, R.id.imgList);
                if (null != appCompatImageButton2) {
                    i2 = R.id.linearDateLayout;
                    final View findChildViewById = ViewBindings.findChildViewById(view, R.id.linearDateLayout);
                    if (null != findChildViewById) {
                        final ActivityBaseReportWcfBinding bind = ActivityBaseReportWcfBinding.bind(findChildViewById);
                        i2 = R.id.linearFilterDiv;
                        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearFilterDiv);
                        if (null != linearLayout) {
                            i2 = R.id.linearMaterial2;
                            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
                            if (null != linearLayout2) {
                                i2 = R.id.linearProgress;
                                final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.linearProgress);
                                if (null != linearLayout3) {
                                    i2 = R.id.linearProgressSpin;
                                    final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.linearProgressSpin);
                                    if (null != linearLayout4) {
                                        i2 = R.id.lvReportOrder;
                                        final ListView listView = ViewBindings.findChildViewById(view, R.id.lvReportOrder);
                                        if (null != listView) {
                                            i2 = R.id.spUser;
                                            final AppCompatSpinner appCompatSpinner = ViewBindings.findChildViewById(view, R.id.spUser);
                                            if (null != appCompatSpinner) {
                                                i2 = R.id.txtAciklama;
                                                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txtAciklama);
                                                if (null != appCompatTextView) {
                                                    i2 = R.id.txtIndirimTutari;
                                                    final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.txtIndirimTutari);
                                                    if (null != appCompatTextView2) {
                                                        i2 = R.id.txtNetTutar;
                                                        final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.txtNetTutar);
                                                        if (null != appCompatTextView3) {
                                                            i2 = R.id.txtViewCalDate;
                                                            final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.txtViewCalDate);
                                                            if (null != appCompatTextView4) {
                                                                i2 = R.id.txtViewCalDay;
                                                                final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.txtViewCalDay);
                                                                if (null != appCompatTextView5) {
                                                                    i2 = R.id.txtViewCalTotal;
                                                                    final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.txtViewCalTotal);
                                                                    if (null != appCompatTextView6) {
                                                                        i2 = R.id.txtViewFisNo;
                                                                        final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.txtViewFisNo);
                                                                        if (null != appCompatTextView7) {
                                                                            i2 = R.id.txtViewTarih;
                                                                            final AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.txtViewTarih);
                                                                            if (null != appCompatTextView8) {
                                                                                i2 = R.id.txtViewTutar;
                                                                                final AppCompatTextView appCompatTextView9 = ViewBindings.findChildViewById(view, R.id.txtViewTutar);
                                                                                if (null != appCompatTextView9) {
                                                                                    return new OrderAvgBinding((LinearLayout) view, appCompatImageButton, appCompatCheckBox, appCompatImageButton2, bind, linearLayout, linearLayout2, linearLayout3, linearLayout4, listView, appCompatSpinner, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8, appCompatTextView9);
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
