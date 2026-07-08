package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class DialogCitytownFilterBinding implements ViewBinding {
    public final EditText edtCity;
    public final EditText edtTown;
    public final LinearLayout lnCity;
    public final LinearLayout lnTown;
    private final ConstraintLayout rootView;
    private DialogCitytownFilterBinding(final ConstraintLayout constraintLayout, final EditText editText, final EditText editText2, final LinearLayout linearLayout, final LinearLayout linearLayout2) {
        rootView = constraintLayout;
        edtCity = editText;
        edtTown = editText2;
        lnCity = linearLayout;
        lnTown = linearLayout2;
    }
    public ConstraintLayout getRoot() {
        return rootView;
    }
    public static DialogCitytownFilterBinding inflate(final LayoutInflater layoutInflater) {
        return DialogCitytownFilterBinding.inflate(layoutInflater, null, false);
    }
    public static DialogCitytownFilterBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.dialog_citytown_filter, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return DialogCitytownFilterBinding.bind(inflate);
    }
    public static DialogCitytownFilterBinding bind(final View view) {
        int i2 = R.id.edtCity;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.edtCity);
        if (null != editText) {
            i2 = R.id.edtTown;
            final EditText editText2 = ViewBindings.findChildViewById(view, R.id.edtTown);
            if (null != editText2) {
                i2 = R.id.ln_city;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_city);
                if (null != linearLayout) {
                    i2 = R.id.ln_town;
                    final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_town);
                    if (null != linearLayout2) {
                        return new DialogCitytownFilterBinding((ConstraintLayout) view, editText, editText2, linearLayout, linearLayout2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
