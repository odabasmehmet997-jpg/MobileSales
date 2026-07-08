package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.proje.mobilesales.R;

public final class ContentChequeDeedFicheDetailEnterBinding implements ViewBinding {
    public final ConstraintLayout contentLayout;
    private final ConstraintLayout rootView;
    private ContentChequeDeedFicheDetailEnterBinding(final ConstraintLayout constraintLayout, final ConstraintLayout constraintLayout2) {
        rootView = constraintLayout;
        contentLayout = constraintLayout2;
    }
    public ConstraintLayout getRoot() {
        return rootView;
    }
    public static ContentChequeDeedFicheDetailEnterBinding inflate(final LayoutInflater layoutInflater) {
        return ContentChequeDeedFicheDetailEnterBinding.inflate(layoutInflater, null, false);
    }
    public static ContentChequeDeedFicheDetailEnterBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.content_cheque_deed_fiche_detail_enter, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ContentChequeDeedFicheDetailEnterBinding.bind(inflate);
    }
    public static ContentChequeDeedFicheDetailEnterBinding bind(final View view) {
        if (null == view) {
            throw new NullPointerException("rootView");
        }
        final ConstraintLayout constraintLayout = (ConstraintLayout) view;
        return new ContentChequeDeedFicheDetailEnterBinding(constraintLayout, constraintLayout);
    }
}
