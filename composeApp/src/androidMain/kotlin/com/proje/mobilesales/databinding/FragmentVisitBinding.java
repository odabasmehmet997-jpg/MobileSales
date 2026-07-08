package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
 
public final class FragmentVisitBinding implements ViewBinding {
    public final EditText edtVisitNote;
    public final ImageView imgVisit;
    public final ImageView imgVisitShipAddressDelete;
    public final LinearLayout lnVisitNote;
    public final LinearLayout lnVisitReasonSelect;
    public final LinearLayout lnVisitShipAddress;
    public final LinearLayout lnVisitUserTitle;
    private final LinearLayout rootView;
    public final TextView textView3;
    public final TextView textView4;
    public final TextView txtVisitReasonSelect;
    public final TextView txtVisitShipAddress;
    public final TextView txtVisitUserTitle;
    private FragmentVisitBinding(final LinearLayout linearLayout, final EditText editText, final ImageView imageView, final ImageView imageView2, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5) {
        this.rootView = linearLayout;
        edtVisitNote = editText;
        imgVisit = imageView;
        imgVisitShipAddressDelete = imageView2;
        lnVisitNote = linearLayout2;
        lnVisitReasonSelect = linearLayout3;
        lnVisitShipAddress = linearLayout4;
        lnVisitUserTitle = linearLayout5;
        this.textView3 = textView;
        this.textView4 = textView2;
        txtVisitReasonSelect = textView3;
        txtVisitShipAddress = textView4;
        txtVisitUserTitle = textView5;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static FragmentVisitBinding inflate(final LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }
    public static FragmentVisitBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_visit, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
    public static FragmentVisitBinding bind(final View view) {
        int i2 = R.id.edt_visitNote;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_visitNote);
        if (null != editText) {
            i2 = R.id.img_visit;
            final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_visit);
            if (null != imageView) {
                i2 = R.id.img_visitShipAddressDelete;
                final ImageView imageView2 = ViewBindings.findChildViewById(view, R.id.img_visitShipAddressDelete);
                if (null != imageView2) {
                    i2 = R.id.ln_visitNote;
                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_visitNote);
                    if (null != linearLayout) {
                        i2 = R.id.ln_visitReasonSelect;
                        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_visitReasonSelect);
                        if (null != linearLayout2) {
                            i2 = R.id.ln_visitShipAddress;
                            final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_visitShipAddress);
                            if (null != linearLayout3) {
                                i2 = R.id.ln_visitUserTitle;
                                final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_visitUserTitle);
                                if (null != linearLayout4) {
                                    i2 = R.id.ln_visitUserTitle;
                                    final TextView textView = ViewBindings.findChildViewById(view, R.id.textView3);
                                    if (null != textView) {
                                        i2 = R.id.textView4;
                                        final TextView textView2 = ViewBindings.findChildViewById(view, R.id.textView4);
                                        if (null != textView2) {
                                            i2 = R.id.txt_visitReasonSelect;
                                            final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_visitReasonSelect);
                                            if (null != textView3) {
                                                i2 = R.id.txt_visitShipAddress;
                                                final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_visitShipAddress);
                                                if (null != textView4) {
                                                    i2 = R.id.txt_visitUserTitle;
                                                    final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_visitUserTitle);
                                                    if (null != textView5) {
                                                        return new FragmentVisitBinding((LinearLayout) view, editText, imageView, imageView2, linearLayout, linearLayout2, linearLayout3, linearLayout4, textView, textView2, textView3, textView4, textView5);
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
