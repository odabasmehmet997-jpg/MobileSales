package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.proje.mobilesales.R;
 
public final class FragmentNotifiedUserListDialogBinding implements ViewBinding {

 
    public final RecyclerView list; 
    private final RecyclerView rootView;
    private FragmentNotifiedUserListDialogBinding(final RecyclerView recyclerView, final RecyclerView recyclerView2) {
        rootView = recyclerView;
        list = recyclerView2;
    } 
    public RecyclerView getRoot() {
        return rootView;
    }
    public static FragmentNotifiedUserListDialogBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentNotifiedUserListDialogBinding.inflate(layoutInflater, null, false);
    } 
    public static FragmentNotifiedUserListDialogBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_notified_user_list_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentNotifiedUserListDialogBinding.bind(inflate);
    }
    public static FragmentNotifiedUserListDialogBinding bind(final View view) {
        if (null == view) {
            throw new NullPointerException("rootView");
        }
        final RecyclerView recyclerView = (RecyclerView) view;
        return new FragmentNotifiedUserListDialogBinding(recyclerView, recyclerView);
    }
}
