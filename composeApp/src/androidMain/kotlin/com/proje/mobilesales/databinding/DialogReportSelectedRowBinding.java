package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class DialogReportSelectedRowBinding implements ViewBinding {
    public final LinearLayout dialogRowLayout;
    private final ConstraintLayout rootView;
    private DialogReportSelectedRowBinding(final ConstraintLayout constraintLayout, final LinearLayout linearLayout) {
        rootView = constraintLayout;
        dialogRowLayout = linearLayout;
    }
    public ConstraintLayout getRoot() {
        return rootView;
    }
    public static DialogReportSelectedRowBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.dialog_report_selected_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
    public static DialogReportSelectedRowBinding bind(final View view) {
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.dialog_row_layout);
        if (null != linearLayout) {
            return new DialogReportSelectedRowBinding((ConstraintLayout) view, linearLayout);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.dialog_row_layout));
    }
}
