package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class FichePropertyTextViewBinding implements ViewBinding {
    public final TextView edtFichePropertyText;
    public final LinearLayout lnFicheProperty;
    private final View rootView;
    public final TextView txtFichePropertyTitle;
    private FichePropertyTextViewBinding(final View view, final TextView textView, final LinearLayout linearLayout, final TextView textView2) {
        rootView = view;
        edtFichePropertyText = textView;
        lnFicheProperty = linearLayout;
        txtFichePropertyTitle = textView2;
    }
    public View getRoot() {
        return rootView;
    }
    public static FichePropertyTextViewBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        if (null == viewGroup) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.fiche_property_text_view, viewGroup);
        return FichePropertyTextViewBinding.bind(viewGroup);
    }
    public static FichePropertyTextViewBinding bind(final View view) {
        int i2 = R.id.edt_fiche_property_text;
        final TextView textView = ViewBindings.findChildViewById(view, R.id.edt_fiche_property_text);
        if (null != textView) {
            i2 = R.id.ln_fiche_property;
            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_fiche_property);
            if (null != linearLayout) {
                i2 = R.id.txt_fiche_property_title;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_fiche_property_title);
                if (null != textView2) {
                    return new FichePropertyTextViewBinding(view, textView, linearLayout, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
