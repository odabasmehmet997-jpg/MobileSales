package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class SimpleDialogCheckListItemBinding implements ViewBinding {
    public final CheckedTextView checkedTextItem;
    public final LinearLayout linearLayoutContainer;
    private final LinearLayout rootView;
    public final AppCompatTextView textViewBottomDefinition;
    private SimpleDialogCheckListItemBinding(final LinearLayout linearLayout, final CheckedTextView checkedTextView, final LinearLayout linearLayout2, final AppCompatTextView appCompatTextView) {
        rootView = linearLayout;
        checkedTextItem = checkedTextView;
        linearLayoutContainer = linearLayout2;
        textViewBottomDefinition = appCompatTextView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static SimpleDialogCheckListItemBinding inflate(final LayoutInflater layoutInflater) {
        return SimpleDialogCheckListItemBinding.inflate(layoutInflater, null, false);
    }
    public static SimpleDialogCheckListItemBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.simple_dialog_check_list_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SimpleDialogCheckListItemBinding.bind(inflate);
    }
    public static SimpleDialogCheckListItemBinding bind(final View view) {
        int i2 = R.id.checked_text_item;
        final CheckedTextView checkedTextView = ViewBindings.findChildViewById(view, R.id.checked_text_item);
        if (null != checkedTextView) {
            final LinearLayout linearLayout = (LinearLayout) view;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.textViewBottomDefinition);
            if (null != appCompatTextView) {
                return new SimpleDialogCheckListItemBinding(linearLayout, checkedTextView, linearLayout, appCompatTextView);
            }
            i2 = R.id.textViewBottomDefinition;
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
