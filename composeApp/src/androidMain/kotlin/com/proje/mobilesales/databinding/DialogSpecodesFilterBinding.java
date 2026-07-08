package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.FichePropertyEditView;
public final class DialogSpecodesFilterBinding implements ViewBinding {
    public final FichePropertyEditView fchSpeCode;
    public final FichePropertyEditView fchSpeCode2;
    public final FichePropertyEditView fchSpeCode3;
    public final FichePropertyEditView fchSpeCode4;
    public final FichePropertyEditView fchSpeCode5;
    private final LinearLayout rootView;
    private DialogSpecodesFilterBinding(final LinearLayout linearLayout, final FichePropertyEditView fichePropertyEditView, final FichePropertyEditView fichePropertyEditView2, final FichePropertyEditView fichePropertyEditView3, final FichePropertyEditView fichePropertyEditView4, final FichePropertyEditView fichePropertyEditView5) {
        rootView = linearLayout;
        fchSpeCode = fichePropertyEditView;
        fchSpeCode2 = fichePropertyEditView2;
        fchSpeCode3 = fichePropertyEditView3;
        fchSpeCode4 = fichePropertyEditView4;
        fchSpeCode5 = fichePropertyEditView5;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static DialogSpecodesFilterBinding inflate(final LayoutInflater layoutInflater) {
        return DialogSpecodesFilterBinding.inflate(layoutInflater, null, false);
    }
    public static DialogSpecodesFilterBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.dialog_specodes_filter, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return DialogSpecodesFilterBinding.bind(inflate);
    }
    public static DialogSpecodesFilterBinding bind(final View view) {
        int i2 = R.id.fch_spe_code;
        final FichePropertyEditView fichePropertyEditView = ViewBindings.findChildViewById(view, R.id.fch_spe_code);
        if (null != fichePropertyEditView) {
            i2 = R.id.fch_spe_code2;
            final FichePropertyEditView fichePropertyEditView2 = ViewBindings.findChildViewById(view, R.id.fch_spe_code2);
            if (null != fichePropertyEditView2) {
                i2 = R.id.fch_spe_code3;
                final FichePropertyEditView fichePropertyEditView3 = ViewBindings.findChildViewById(view, R.id.fch_spe_code3);
                if (null != fichePropertyEditView3) {
                    i2 = R.id.fch_spe_code4;
                    final FichePropertyEditView fichePropertyEditView4 = ViewBindings.findChildViewById(view, R.id.fch_spe_code4);
                    if (null != fichePropertyEditView4) {
                        i2 = R.id.fch_spe_code5;
                        final FichePropertyEditView fichePropertyEditView5 = ViewBindings.findChildViewById(view, R.id.fch_spe_code5);
                        if (null != fichePropertyEditView5) {
                            return new DialogSpecodesFilterBinding((LinearLayout) view, fichePropertyEditView, fichePropertyEditView2, fichePropertyEditView3, fichePropertyEditView4, fichePropertyEditView5);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
