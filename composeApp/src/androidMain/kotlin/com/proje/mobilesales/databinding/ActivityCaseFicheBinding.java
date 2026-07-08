package com.proje.mobilesales.databinding;

import android.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proje.mobilesales.R;

public final class ActivityCaseFicheBinding implements ViewBinding {
    public final AppBarLayout appbar;
    public final CoordinatorLayout contentFrame;
    public final FloatingActionButton fabFicheSave;
    public final FrameLayout frmReceiptLineTotal;
    public final ImageButton imgReceipttotal;
    public final FrameLayout list;
    public final LinearLayout lnCustomerbalance;
    public final LinearLayout lnReceipttotal;
    public final LinearLayout lnUnallocatedbalance;
    private final CoordinatorLayout rootView;
    public final Toolbar toolbar;
    public final TextView txtCustomerbalance;
    public final TextView txtReceipttotal;
    public final TextView txtUnallocatedbalance;
    private ActivityCaseFicheBinding( final CoordinatorLayout coordinatorLayout,  final AppBarLayout appBarLayout,  final CoordinatorLayout coordinatorLayout2,  final FloatingActionButton floatingActionButton,  final FrameLayout frameLayout,  final ImageButton imageButton,  final FrameLayout frameLayout2,  final LinearLayout linearLayout,  final LinearLayout linearLayout2,  final LinearLayout linearLayout3,  final Toolbar toolbar,  final TextView textView,  final TextView textView2,  final TextView textView3) {
        rootView = coordinatorLayout;
        appbar = appBarLayout;
        contentFrame = coordinatorLayout2;
        fabFicheSave = floatingActionButton;
        frmReceiptLineTotal = frameLayout;
        imgReceipttotal = imageButton;
        list = frameLayout2;
        lnCustomerbalance = linearLayout;
        lnReceipttotal = linearLayout2;
        lnUnallocatedbalance = linearLayout3;
        this.toolbar = toolbar;
        txtCustomerbalance = textView;
        txtReceipttotal = textView2;
        txtUnallocatedbalance = textView3;
    }
    public CoordinatorLayout getRoot() {
        return rootView;
    }
    public static ActivityCaseFicheBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityCaseFicheBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityCaseFicheBinding inflate( final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_case_fiche, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
    public static ActivityCaseFicheBinding bind( final View view) {
        int i2 = R.id.appbar;
        final AppBarLayout appBarLayout = ViewBindings.findChildViewById(view, R.id.appbar);
        if (null != appBarLayout) {
            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
            i2 = R.id.fabFicheSave;
            final FloatingActionButton floatingActionButton = ViewBindings.findChildViewById(view, R.id.fabFicheSave);
            if (null != floatingActionButton) {
                i2 = R.id.frm_ReceiptLineTotal;
                final FrameLayout frameLayout = ViewBindings.findChildViewById(view, R.id.frm_ReceiptLineTotal);
                if (null != frameLayout) {
                    i2 = R.id.img_receipttotal;
                    final ImageButton imageButton = ViewBindings.findChildViewById(view, R.id.img_receipttotal);
                    if (null != imageButton) {
                        i2 = R.id.list;
                        final FrameLayout frameLayout2 = ViewBindings.findChildViewById(view, R.id.list);
                        if (null != frameLayout2) {
                            i2 = R.id.ln_customerbalance;
                            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_customerbalance);
                            if (null != linearLayout) {
                                i2 = R.id.ln_receipttotal;
                                final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_receipttotal);
                                if (null != linearLayout2) {
                                    i2 = R.id.ln_unallocatedbalance;
                                    final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_unallocatedbalance);
                                    if (null != linearLayout3) {
                                        i2 = R.id.toolbar;
                                        final Toolbar toolbar = ViewBindings.findChildViewById(view, R.id.toolbar);
                                        if (null != toolbar) {
                                            i2 = R.id.txt_customerbalance;
                                            final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_customerbalance);
                                            if (null != textView) {
                                                i2 = R.id.txt_receipttotal;
                                                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_receipttotal);
                                                if (null != textView2) {
                                                    i2 = R.id.txt_unallocatedbalance;
                                                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_unallocatedbalance);
                                                    if (null != textView3) {
                                                        return new ActivityCaseFicheBinding(coordinatorLayout, appBarLayout, coordinatorLayout, floatingActionButton, frameLayout, imageButton, frameLayout2, linearLayout, linearLayout2, linearLayout3, toolbar, textView, textView2, textView3);
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
