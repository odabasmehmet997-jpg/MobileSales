package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class ExtractInvoiceHeaderBinding implements ViewBinding {
    private final ScrollView rootView;
    public final AppCompatTextView tvBranch;
    public final AppCompatTextView tvCustomer;
    public final AppCompatTextView tvCyphcode;
    public final AppCompatTextView tvDate;
    public final AppCompatTextView tvDepartment;
    public final AppCompatTextView tvDesc1;
    public final AppCompatTextView tvDesc2;
    public final AppCompatTextView tvDesc3;
    public final AppCompatTextView tvDesc4;
    public final AppCompatTextView tvDocode;
    public final AppCompatTextView tvFactory;
    public final AppCompatTextView tvFicheNo;
    public final AppCompatTextView tvPayment;
    public final AppCompatTextView tvProjectCode;
    public final AppCompatTextView tvSalesman;
    public final AppCompatTextView tvShipAccount;
    public final AppCompatTextView tvShipAddress;
    public final AppCompatTextView tvShipAgent;
    public final AppCompatTextView tvShipDeliveryMethod;
    public final AppCompatTextView tvShipTransType;
    public final AppCompatTextView tvSpecode;
    public final AppCompatTextView tvTradinggrp;
    public final AppCompatTextView tvWarehouse;
    private ExtractInvoiceHeaderBinding(final ScrollView scrollView, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7, final AppCompatTextView appCompatTextView8, final AppCompatTextView appCompatTextView9, final AppCompatTextView appCompatTextView10, final AppCompatTextView appCompatTextView11, final AppCompatTextView appCompatTextView12, final AppCompatTextView appCompatTextView13, final AppCompatTextView appCompatTextView14, final AppCompatTextView appCompatTextView15, final AppCompatTextView appCompatTextView16, final AppCompatTextView appCompatTextView17, final AppCompatTextView appCompatTextView18, final AppCompatTextView appCompatTextView19, final AppCompatTextView appCompatTextView20, final AppCompatTextView appCompatTextView21, final AppCompatTextView appCompatTextView22, final AppCompatTextView appCompatTextView23) {
        rootView = scrollView;
        tvBranch = appCompatTextView;
        tvCustomer = appCompatTextView2;
        tvCyphcode = appCompatTextView3;
        tvDate = appCompatTextView4;
        tvDepartment = appCompatTextView5;
        tvDesc1 = appCompatTextView6;
        tvDesc2 = appCompatTextView7;
        tvDesc3 = appCompatTextView8;
        tvDesc4 = appCompatTextView9;
        tvDocode = appCompatTextView10;
        tvFactory = appCompatTextView11;
        tvFicheNo = appCompatTextView12;
        tvPayment = appCompatTextView13;
        tvProjectCode = appCompatTextView14;
        tvSalesman = appCompatTextView15;
        tvShipAccount = appCompatTextView16;
        tvShipAddress = appCompatTextView17;
        tvShipAgent = appCompatTextView18;
        tvShipDeliveryMethod = appCompatTextView19;
        tvShipTransType = appCompatTextView20;
        tvSpecode = appCompatTextView21;
        tvTradinggrp = appCompatTextView22;
        tvWarehouse = appCompatTextView23;
    }
    public ScrollView getRoot() {
        return rootView;
    }
    public static ExtractInvoiceHeaderBinding inflate(final LayoutInflater layoutInflater) {
        return ExtractInvoiceHeaderBinding.inflate(layoutInflater, null, false);
    }
    public static ExtractInvoiceHeaderBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.extract_invoice_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ExtractInvoiceHeaderBinding.bind(inflate);
    }
    public static ExtractInvoiceHeaderBinding bind(final View view) {
        int i2 = R.id.tvBranch;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvBranch);
        if (null != appCompatTextView) {
            i2 = R.id.tvCustomer;
            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvCustomer);
            if (null != appCompatTextView2) {
                i2 = R.id.tvCyphcode;
                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvCyphcode);
                if (null != appCompatTextView3) {
                    i2 = R.id.tvDate;
                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvDate);
                    if (null != appCompatTextView4) {
                        i2 = R.id.tvDepartment;
                        final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvDepartment);
                        if (null != appCompatTextView5) {
                            i2 = R.id.tvDesc1;
                            final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvDesc1);
                            if (null != appCompatTextView6) {
                                i2 = R.id.tvDesc2;
                                final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.tvDesc2);
                                if (null != appCompatTextView7) {
                                    i2 = R.id.tvDesc3;
                                    final AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.tvDesc3);
                                    if (null != appCompatTextView8) {
                                        i2 = R.id.tvDesc4;
                                        final AppCompatTextView appCompatTextView9 = ViewBindings.findChildViewById(view, R.id.tvDesc4);
                                        if (null != appCompatTextView9) {
                                            i2 = R.id.tvDocode;
                                            final AppCompatTextView appCompatTextView10 = ViewBindings.findChildViewById(view, R.id.tvDocode);
                                            if (null != appCompatTextView10) {
                                                i2 = R.id.tvFactory;
                                                final AppCompatTextView appCompatTextView11 = ViewBindings.findChildViewById(view, R.id.tvFactory);
                                                if (null != appCompatTextView11) {
                                                    i2 = R.id.tvFicheNo;
                                                    final AppCompatTextView appCompatTextView12 = ViewBindings.findChildViewById(view, R.id.tvFicheNo);
                                                    if (null != appCompatTextView12) {
                                                        i2 = R.id.tvPayment;
                                                        final AppCompatTextView appCompatTextView13 = ViewBindings.findChildViewById(view, R.id.tvPayment);
                                                        if (null != appCompatTextView13) {
                                                            i2 = R.id.tvProjectCode;
                                                            final AppCompatTextView appCompatTextView14 = ViewBindings.findChildViewById(view, R.id.tvProjectCode);
                                                            if (null != appCompatTextView14) {
                                                                i2 = R.id.tvSalesman;
                                                                final AppCompatTextView appCompatTextView15 = ViewBindings.findChildViewById(view, R.id.tvSalesman);
                                                                if (null != appCompatTextView15) {
                                                                    i2 = R.id.tvShipAccount;
                                                                    final AppCompatTextView appCompatTextView16 = ViewBindings.findChildViewById(view, R.id.tvShipAccount);
                                                                    if (null != appCompatTextView16) {
                                                                        i2 = R.id.tvShipAddress;
                                                                        final AppCompatTextView appCompatTextView17 = ViewBindings.findChildViewById(view, R.id.tvShipAddress);
                                                                        if (null != appCompatTextView17) {
                                                                            i2 = R.id.tvShipAgent;
                                                                            final AppCompatTextView appCompatTextView18 = ViewBindings.findChildViewById(view, R.id.tvShipAgent);
                                                                            if (null != appCompatTextView18) {
                                                                                i2 = R.id.tvShipDeliveryMethod;
                                                                                final AppCompatTextView appCompatTextView19 = ViewBindings.findChildViewById(view, R.id.tvShipDeliveryMethod);
                                                                                if (null != appCompatTextView19) {
                                                                                    i2 = R.id.tvShipTransType;
                                                                                    final AppCompatTextView appCompatTextView20 = ViewBindings.findChildViewById(view, R.id.tvShipTransType);
                                                                                    if (null != appCompatTextView20) {
                                                                                        i2 = R.id.tvSpecode;
                                                                                        final AppCompatTextView appCompatTextView21 = ViewBindings.findChildViewById(view, R.id.tvSpecode);
                                                                                        if (null != appCompatTextView21) {
                                                                                            i2 = R.id.tvTradinggrp;
                                                                                            final AppCompatTextView appCompatTextView22 = ViewBindings.findChildViewById(view, R.id.tvTradinggrp);
                                                                                            if (null != appCompatTextView22) {
                                                                                                i2 = R.id.tvWarehouse;
                                                                                                final AppCompatTextView appCompatTextView23 = ViewBindings.findChildViewById(view, R.id.tvWarehouse);
                                                                                                if (null != appCompatTextView23) {
                                                                                                    return new ExtractInvoiceHeaderBinding((ScrollView) view, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8, appCompatTextView9, appCompatTextView10, appCompatTextView11, appCompatTextView12, appCompatTextView13, appCompatTextView14, appCompatTextView15, appCompatTextView16, appCompatTextView17, appCompatTextView18, appCompatTextView19, appCompatTextView20, appCompatTextView21, appCompatTextView22, appCompatTextView23);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
