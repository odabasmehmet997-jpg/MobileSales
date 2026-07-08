package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class KeyValueViewBinding implements ViewBinding {

   
    public final LinearLayout lyKeyValueContainer;

   
    private final CardView rootView;

   
    public final TextView txtKey;

   
    public final TextView txtValue;

    private KeyValueViewBinding(final CardView cardView, final LinearLayout linearLayout, final TextView textView, final TextView textView2) {
        rootView = cardView;
        lyKeyValueContainer = linearLayout;
        txtKey = textView;
        txtValue = textView2;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static KeyValueViewBinding inflate(final LayoutInflater layoutInflater) {
        return KeyValueViewBinding.inflate(layoutInflater, null, false);
    }

   
    public static KeyValueViewBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.key_value_view, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return KeyValueViewBinding.bind(inflate);
    }

   
    public static KeyValueViewBinding bind(final View view) {
        int i2 = R.id.lyKeyValueContainer;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.lyKeyValueContainer);
        if (null != linearLayout) {
            i2 = R.id.txtKey;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.txtKey);
            if (null != textView) {
                i2 = R.id.txtValue;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txtValue);
                if (null != textView2) {
                    return new KeyValueViewBinding((CardView) view, linearLayout, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
