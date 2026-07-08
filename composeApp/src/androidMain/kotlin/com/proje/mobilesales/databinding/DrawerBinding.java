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
public final class DrawerBinding implements ViewBinding {
    public final AppCompatTextView drawerAbout;
    public final AppCompatTextView drawerCustomer;
    public final AppCompatTextView drawerDistribution;
    public final AppCompatTextView drawerFiche;
    public final AppCompatTextView drawerMain;
    public final AppCompatTextView drawerNotifications;
    public final AppCompatTextView drawerOrder;
    public final AppCompatTextView drawerProduct;
    public final AppCompatTextView drawerQuit;
    public final AppCompatTextView drawerReport;
    public final AppCompatTextView drawerRequest;
    public final AppCompatTextView drawerSettings;
    public final AppCompatTextView drawerTransfer;
    public final AppCompatTextView drawerUtil;
    public final AppCompatTextView drawerVehicleTransfer;
    private final LinearLayout rootView;
    private DrawerBinding(final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7, final AppCompatTextView appCompatTextView8, final AppCompatTextView appCompatTextView9, final AppCompatTextView appCompatTextView10, final AppCompatTextView appCompatTextView11, final AppCompatTextView appCompatTextView12, final AppCompatTextView appCompatTextView13, final AppCompatTextView appCompatTextView14, final AppCompatTextView appCompatTextView15) {
        rootView = linearLayout;
        drawerAbout = appCompatTextView;
        drawerCustomer = appCompatTextView2;
        drawerDistribution = appCompatTextView3;
        drawerFiche = appCompatTextView4;
        drawerMain = appCompatTextView5;
        drawerNotifications = appCompatTextView6;
        drawerOrder = appCompatTextView7;
        drawerProduct = appCompatTextView8;
        drawerQuit = appCompatTextView9;
        drawerReport = appCompatTextView10;
        drawerRequest = appCompatTextView11;
        drawerSettings = appCompatTextView12;
        drawerTransfer = appCompatTextView13;
        drawerUtil = appCompatTextView14;
        drawerVehicleTransfer = appCompatTextView15;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static DrawerBinding inflate(final LayoutInflater layoutInflater) {
        return DrawerBinding.inflate(layoutInflater, null, false);
    }
    public static DrawerBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.drawer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return DrawerBinding.bind(inflate);
    }
    public static DrawerBinding bind(final View view) {
        int i2 = R.id.drawer_about;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.drawer_about);
        if (null != appCompatTextView) {
            i2 = R.id.drawer_customer;
            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.drawer_customer);
            if (null != appCompatTextView2) {
                i2 = R.id.drawer_distribution;
                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.drawer_distribution);
                if (null != appCompatTextView3) {
                    i2 = R.id.drawer_fiche;
                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.drawer_fiche);
                    if (null != appCompatTextView4) {
                        i2 = R.id.drawer_main;
                        final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.drawer_main);
                        if (null != appCompatTextView5) {
                            i2 = R.id.drawer_notifications;
                            final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.drawer_notifications);
                            if (null != appCompatTextView6) {
                                i2 = R.id.drawer_order;
                                final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.drawer_order);
                                if (null != appCompatTextView7) {
                                    i2 = R.id.drawer_product;
                                    final AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.drawer_product);
                                    if (null != appCompatTextView8) {
                                        i2 = R.id.drawer_quit;
                                        final AppCompatTextView appCompatTextView9 = ViewBindings.findChildViewById(view, R.id.drawer_quit);
                                        if (null != appCompatTextView9) {
                                            i2 = R.id.drawer_report;
                                            final AppCompatTextView appCompatTextView10 = ViewBindings.findChildViewById(view, R.id.drawer_report);
                                            if (null != appCompatTextView10) {
                                                i2 = R.id.drawer_request;
                                                final AppCompatTextView appCompatTextView11 = ViewBindings.findChildViewById(view, R.id.drawer_request);
                                                if (null != appCompatTextView11) {
                                                    i2 = R.id.drawer_settings;
                                                    final AppCompatTextView appCompatTextView12 = ViewBindings.findChildViewById(view, R.id.drawer_settings);
                                                    if (null != appCompatTextView12) {
                                                        i2 = R.id.drawer_transfer;
                                                        final AppCompatTextView appCompatTextView13 = ViewBindings.findChildViewById(view, R.id.drawer_transfer);
                                                        if (null != appCompatTextView13) {
                                                            i2 = R.id.drawer_util;
                                                            final AppCompatTextView appCompatTextView14 = ViewBindings.findChildViewById(view, R.id.drawer_util);
                                                            if (null != appCompatTextView14) {
                                                                i2 = R.id.drawer_vehicle_transfer;
                                                                final AppCompatTextView appCompatTextView15 = ViewBindings.findChildViewById(view, R.id.drawer_vehicle_transfer);
                                                                if (null != appCompatTextView15) {
                                                                    return new DrawerBinding((LinearLayout) view, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8, appCompatTextView9, appCompatTextView10, appCompatTextView11, appCompatTextView12, appCompatTextView13, appCompatTextView14, appCompatTextView15);
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
