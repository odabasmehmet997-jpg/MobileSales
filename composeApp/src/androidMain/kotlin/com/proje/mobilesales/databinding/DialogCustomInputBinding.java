package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class DialogCustomInputBinding implements ViewBinding {
    public final EditText edtDialogUserInput;
    public final LinearLayout layoutRoot;
    private final LinearLayout rootView;
    public final TextView txtViewOldMatter;
    public final TextView txtViewTitle;
    private DialogCustomInputBinding(final LinearLayout linearLayout, final EditText editText, final LinearLayout linearLayout2, final TextView textView, final TextView textView2) {
        rootView = linearLayout;
        edtDialogUserInput = editText;
        layoutRoot = linearLayout2;
        txtViewOldMatter = textView;
        txtViewTitle = textView2;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static DialogCustomInputBinding inflate(final LayoutInflater layoutInflater) {
        return DialogCustomInputBinding.inflate(layoutInflater, null, false);
    }
    public static DialogCustomInputBinding inflate(final LayoutInflater layoutInflater,final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.dialog_custom_input, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return DialogCustomInputBinding.bind(inflate);
    }
    public static DialogCustomInputBinding bind(final View view) {
        int i2 = R.id.edtDialogUserInput;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.edtDialogUserInput);
        if (null != editText) {
            final LinearLayout linearLayout = (LinearLayout) view;
            i2 = R.id.txtViewOldMatter;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.txtViewOldMatter);
            if (null != textView) {
                i2 = R.id.txtViewTitle;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txtViewTitle);
                if (null != textView2) {
                    return new DialogCustomInputBinding(linearLayout, editText, linearLayout, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
