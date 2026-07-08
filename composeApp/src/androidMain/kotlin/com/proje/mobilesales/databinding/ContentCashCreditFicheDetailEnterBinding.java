package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.proje.mobilesales.R;

public final class ContentCashCreditFicheDetailEnterBinding implements ViewBinding {
    public final ConstraintLayout contentLayout;
    private final ConstraintLayout rootView;
    private ContentCashCreditFicheDetailEnterBinding(final ConstraintLayout constraintLayout, final ConstraintLayout constraintLayout2) {
        rootView = constraintLayout;
        contentLayout = constraintLayout2;
    }
    public ConstraintLayout getRoot() {
        return rootView;
    }
    public static ContentCashCreditFicheDetailEnterBinding inflate(final LayoutInflater layoutInflater) {
        return ContentCashCreditFicheDetailEnterBinding.inflate(layoutInflater, null, false);
    }
    public static ContentCashCreditFicheDetailEnterBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.content_cash_credit_fiche_detail_enter, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ContentCashCreditFicheDetailEnterBinding.bind(inflate);
    }
    public static ContentCashCreditFicheDetailEnterBinding bind(final View view) {
        if (null == view) {
            throw new NullPointerException("rootView");
        }
        final ConstraintLayout constraintLayout = (ConstraintLayout) view;
        return new ContentCashCreditFicheDetailEnterBinding(constraintLayout, constraintLayout);
    }
}
