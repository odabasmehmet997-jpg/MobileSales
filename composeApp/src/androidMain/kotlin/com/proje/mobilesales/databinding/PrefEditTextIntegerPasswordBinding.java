package com.proje.mobilesales.databinding;

import android.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class PrefEditTextIntegerPasswordBinding implements ViewBinding {

   
    public final EditText edit;

   
    public final TextView message;

   
    private final ScrollView rootView;

    private PrefEditTextIntegerPasswordBinding(final ScrollView scrollView, final EditText editText, final TextView textView) {
        rootView = scrollView;
        edit = editText;
        message = textView;
    }

    
   
    public ScrollView getRoot() {
        return rootView;
    }

   
    public static PrefEditTextIntegerPasswordBinding inflate(final LayoutInflater layoutInflater) {
        return PrefEditTextIntegerPasswordBinding.inflate(layoutInflater, null, false);
    }

   
    public static PrefEditTextIntegerPasswordBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.pref_edit_text_integer_password, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return PrefEditTextIntegerPasswordBinding.bind(inflate);
    }

   
    public static PrefEditTextIntegerPasswordBinding bind(final View view) {
        int i2 = R.id.edit;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.edit);
        if (null != editText) {
            i2 = R.id.message;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.message);
            if (null != textView) {
                return new PrefEditTextIntegerPasswordBinding((ScrollView) view, editText, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
