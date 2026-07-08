package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proje.mobilesales.R;
public final class ActivityChequeDeedFicheDetailEnterBinding implements ViewBinding {
    public final FloatingActionButton fabFicheSave;
    private final CoordinatorLayout rootView;
    public final Toolbar toolbar;
    private ActivityChequeDeedFicheDetailEnterBinding(final CoordinatorLayout coordinatorLayout, final FloatingActionButton floatingActionButton, final Toolbar toolbar) {
        rootView = coordinatorLayout;
        fabFicheSave = floatingActionButton;
        this.toolbar = toolbar;
    }
    public CoordinatorLayout getRoot() {
        return rootView;
    }
    public static ActivityChequeDeedFicheDetailEnterBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityChequeDeedFicheDetailEnterBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityChequeDeedFicheDetailEnterBinding inflate(final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_cheque_deed_fiche_detail_enter, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityChequeDeedFicheDetailEnterBinding.bind(inflate);
    }
    public static ActivityChequeDeedFicheDetailEnterBinding bind(final View view) {
        int i2 = R.id.fabFicheSave;
        final FloatingActionButton floatingActionButton = ViewBindings.findChildViewById(view, R.id.fabFicheSave);
        if (null != floatingActionButton) {
            i2 = R.id.toolbar;
            final Toolbar toolbar = ViewBindings.findChildViewById(view, R.id.toolbar);
            if (null != toolbar) {
                return new ActivityChequeDeedFicheDetailEnterBinding((CoordinatorLayout) view, floatingActionButton, toolbar);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
