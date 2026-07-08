package com.proje.mobilesales.databinding;

import android.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ActivityMainBinding implements ViewBinding {
    public final FrameLayout container;
    public final CoordinatorLayout contentFrame;
    public final FrameLayout list;
    public final LinearLayout llTodoMesaj;
    public final LinearLayout lnMenuContainer;
    public final LinearLayout lyMainFirst;
    public final LinearLayout lyMainSecond;
    public final LinearLayout lyMainThird;
    private final CoordinatorLayout rootView;
    public final RecyclerView rvToDo;
    public final View txtCustomer;
    public final View txtDistribution;
    public final View txtOrder;
    public final View txtProduct;
    public final View txtReport;
    public final View txtRequest;
    public final View txtTransfer;
    public final View txtUtil;
    public final View txtWhTransfer;
    private ActivityMainBinding(final CoordinatorLayout coordinatorLayout, final FrameLayout frameLayout, final CoordinatorLayout coordinatorLayout2, final FrameLayout frameLayout2, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final RecyclerView recyclerView, final View view, final View view2, final View view3, final View view4, final View view5, final View view6, final View view7, final View view8, final View view9) {
        rootView = coordinatorLayout;
        container = frameLayout;
        contentFrame = coordinatorLayout2;
        list = frameLayout2;
        llTodoMesaj = linearLayout;
        lnMenuContainer = linearLayout2;
        lyMainFirst = linearLayout3;
        lyMainSecond = linearLayout4;
        lyMainThird = linearLayout5;
        rvToDo = recyclerView;
        txtCustomer = view;
        txtDistribution = view2;
        txtOrder = view3;
        txtProduct = view4;
        txtReport = view5;
        txtRequest = view6;
        txtTransfer = view7;
        txtUtil = view8;
        txtWhTransfer = view9;
    }
    public CoordinatorLayout getRoot() {
        return rootView;
    }
    public static ActivityMainBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityMainBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityMainBinding inflate(final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityMainBinding.bind(inflate);
    }
    public static ActivityMainBinding bind(final View view) {
        int i2 = R.id.container;
        final FrameLayout frameLayout = ViewBindings.findChildViewById(view, R.id.container);
        if (null != frameLayout) {
            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
            i2 = R.id.list;
            final FrameLayout frameLayout2 = ViewBindings.findChildViewById(view, R.id.list);
            if (null != frameLayout2) {
                i2 = R.id.llTodoMesaj;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.llTodoMesaj);
                if (null != linearLayout) {
                    i2 = R.id.lnMenuContainer;
                    final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.lnMenuContainer);
                    if (null != linearLayout2) {
                        i2 = R.id.lyMainFirst;
                        final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.lyMainFirst);
                        if (null != linearLayout3) {
                            i2 = R.id.lyMainSecond;
                            final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.lyMainSecond);
                            if (null != linearLayout4) {
                                i2 = R.id.lyMainThird;
                                final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.lyMainThird);
                                if (null != linearLayout5) {
                                    i2 = R.id.rv_to_do;
                                    final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rv_to_do);
                                    if (null != recyclerView) {
                                        i2 = R.id.txtCustomer;
                                        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.txtCustomer);
                                        if (null != findChildViewById) {
                                            i2 = R.id.txtDistribution;
                                            final View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.txtDistribution);
                                            if (null != findChildViewById2) {
                                                i2 = R.id.txtOrder;
                                                final View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.txtOrder);
                                                if (null != findChildViewById3) {
                                                    i2 = R.id.txtProduct;
                                                    final View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.txtProduct);
                                                    if (null != findChildViewById4) {
                                                        i2 = R.id.txtReport;
                                                        final View findChildViewById5 = ViewBindings.findChildViewById(view, R.id.txtReport);
                                                        if (null != findChildViewById5) {
                                                            i2 = R.id.txtRequest;
                                                            final View findChildViewById6 = ViewBindings.findChildViewById(view, R.id.txtRequest);
                                                            if (null != findChildViewById6) {
                                                                i2 = R.id.txtTransfer;
                                                                final View findChildViewById7 = ViewBindings.findChildViewById(view, R.id.txtTransfer);
                                                                if (null != findChildViewById7) {
                                                                    i2 = R.id.txtUtil;
                                                                    final View findChildViewById8 = ViewBindings.findChildViewById(view, R.id.txtUtil);
                                                                    if (null != findChildViewById8) {
                                                                        i2 = R.id.txtWhTransfer;
                                                                        final View findChildViewById9 = ViewBindings.findChildViewById(view, R.id.txtWhTransfer);
                                                                        if (null != findChildViewById9) {
                                                                            return new ActivityMainBinding(coordinatorLayout, frameLayout, coordinatorLayout, frameLayout2, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, recyclerView, findChildViewById, findChildViewById2, findChildViewById3, findChildViewById4, findChildViewById5, findChildViewById6, findChildViewById7, findChildViewById8, findChildViewById9);
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
