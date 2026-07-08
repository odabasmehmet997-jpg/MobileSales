package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class FichePropertyEditBinding implements ViewBinding {
    public final EditText edtFichePropertyEdit;
    public final LinearLayout lnFicheProperty;
    private final View rootView;
    public final TextView txtFichePropertyTitle;
    private FichePropertyEditBinding(final View view, final EditText editText, final LinearLayout linearLayout, final TextView textView) {
        rootView = view;
        edtFichePropertyEdit = editText;
        lnFicheProperty = linearLayout;
        txtFichePropertyTitle = textView;
    }
    public View getRoot() {
        return rootView;
    }
    public static FichePropertyEditBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        if (null == viewGroup) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.fiche_property_edit, viewGroup);
        return FichePropertyEditBinding.bind(viewGroup);
    }
    public static FichePropertyEditBinding bind(final View view) {
        int i2 = R.id.edt_fiche_property_edit;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_fiche_property_edit);
        if (null != editText) {
            i2 = R.id.ln_fiche_property;
            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_fiche_property);
            if (null != linearLayout) {
                i2 = R.id.txt_fiche_property_title;
                final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_fiche_property_title);
                if (null != textView) {
                    return new FichePropertyEditBinding(view, editText, linearLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
