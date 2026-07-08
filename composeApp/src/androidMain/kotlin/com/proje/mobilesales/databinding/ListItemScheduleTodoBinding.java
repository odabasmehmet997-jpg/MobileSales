package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ListItemScheduleTodoBinding implements ViewBinding {

   
    public final ImageView imgType;

   
    private final LinearLayout rootView;

   
    public final TextView todoBegHour;

   
    public final TextView todoNote;

   
    public final TextView todoTitle;

    private ListItemScheduleTodoBinding(final LinearLayout linearLayout, final ImageView imageView, final TextView textView, final TextView textView2, final TextView textView3) {
        rootView = linearLayout;
        imgType = imageView;
        todoBegHour = textView;
        todoNote = textView2;
        todoTitle = textView3;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ListItemScheduleTodoBinding inflate(final LayoutInflater layoutInflater) {
        return ListItemScheduleTodoBinding.inflate(layoutInflater, null, false);
    }

   
    public static ListItemScheduleTodoBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.list_item_schedule_todo, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ListItemScheduleTodoBinding.bind(inflate);
    }

   
    public static ListItemScheduleTodoBinding bind(final View view) {
        int i2 = R.id.imgType;
        final ImageView imageView = ViewBindings.findChildViewById(view, R.id.imgType);
        if (null != imageView) {
            i2 = R.id.todoBegHour;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.todoBegHour);
            if (null != textView) {
                i2 = R.id.todoNote;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.todoNote);
                if (null != textView2) {
                    i2 = R.id.todoTitle;
                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.todoTitle);
                    if (null != textView3) {
                        return new ListItemScheduleTodoBinding((LinearLayout) view, imageView, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
