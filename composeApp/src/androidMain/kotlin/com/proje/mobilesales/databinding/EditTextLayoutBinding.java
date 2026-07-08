package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class EditTextLayoutBinding implements ViewBinding {
    public final EditText editValue;
    private final FrameLayout rootView;
    private EditTextLayoutBinding(final FrameLayout frameLayout, final EditText editText) {
        rootView = frameLayout;
        editValue = editText;
    }
    public FrameLayout getRoot() {
        return rootView;
    }
    public static EditTextLayoutBinding inflate(final LayoutInflater layoutInflater) {
        return EditTextLayoutBinding.inflate(layoutInflater, null, false);
    }
    public static EditTextLayoutBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.edit_text_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return EditTextLayoutBinding.bind(inflate);
    }
    public static EditTextLayoutBinding bind(final View view) {
        final EditText editText = ViewBindings.findChildViewById(view, R.id.edit_value);
        if (null != editText) {
            return new EditTextLayoutBinding((FrameLayout) view, editText);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.edit_value));
    }
}
