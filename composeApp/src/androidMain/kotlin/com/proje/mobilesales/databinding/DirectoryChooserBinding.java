package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class DirectoryChooserBinding implements ViewBinding {
    public final ImageButton btnCreateFolder;
    public final ImageButton btnNavUp;
    public final ListView listDirs;
    private final LinearLayout rootView;
    public final TextView txtListEmpty;
    public final TextView txtSelectedFolder;
    public final TextView txtSelectedFolderLabel;
    private DirectoryChooserBinding(final LinearLayout linearLayout, final ImageButton imageButton, final ImageButton imageButton2, final ListView listView, final TextView textView, final TextView textView2, final TextView textView3) {
        rootView = linearLayout;
        btnCreateFolder = imageButton;
        btnNavUp = imageButton2;
        listDirs = listView;
        txtListEmpty = textView;
        txtSelectedFolder = textView2;
        txtSelectedFolderLabel = textView3;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static DirectoryChooserBinding inflate(final LayoutInflater layoutInflater) {
        return DirectoryChooserBinding.inflate(layoutInflater, null, false);
    }
    public static DirectoryChooserBinding inflate(final LayoutInflater layoutInflater,final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.directory_chooser, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return DirectoryChooserBinding.bind(inflate);
    }
    public static DirectoryChooserBinding bind(final View view) {
        int i2 = R.id.btn_create_folder;
        final ImageButton imageButton = ViewBindings.findChildViewById(view, R.id.btn_create_folder);
        if (null != imageButton) {
            i2 = R.id.btn_nav_up;
            final ImageButton imageButton2 = ViewBindings.findChildViewById(view, R.id.btn_nav_up);
            if (null != imageButton2) {
                i2 = R.id.list_dirs;
                final ListView listView = ViewBindings.findChildViewById(view, R.id.list_dirs);
                if (null != listView) {
                    i2 = R.id.txt_list_empty;
                    final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_list_empty);
                    if (null != textView) {
                        i2 = R.id.txt_selected_folder;
                        final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_selected_folder);
                        if (null != textView2) {
                            i2 = R.id.txt_selected_folder_label;
                            final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_selected_folder_label);
                            if (null != textView3) {
                                return new DirectoryChooserBinding((LinearLayout) view, imageButton, imageButton2, listView, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
