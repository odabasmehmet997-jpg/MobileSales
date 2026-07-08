package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.TintableTextView;



public final class PenetrationViewBinding implements ViewBinding {

   
    public final ButtonMoreBinding buttonMore;

   
    public final RelativeLayout rltPenetrationContainer;

   
    public final RelativeLayout rltPenetrationDetail;

   
    public final RelativeLayout rltPenetrationHeader;

   
    private final View rootView;

   
    public final TintableTextView txtPenetrationDate;

   
    public final TextView txtPenetrationDefinition;

   
    public final TintableTextView txtPenetrationTransfer;

    private PenetrationViewBinding(final View view, final ButtonMoreBinding buttonMoreBinding, final RelativeLayout relativeLayout, final RelativeLayout relativeLayout2, final RelativeLayout relativeLayout3, final TintableTextView tintableTextView, final TextView textView, final TintableTextView tintableTextView2) {
        rootView = view;
        buttonMore = buttonMoreBinding;
        rltPenetrationContainer = relativeLayout;
        rltPenetrationDetail = relativeLayout2;
        rltPenetrationHeader = relativeLayout3;
        txtPenetrationDate = tintableTextView;
        txtPenetrationDefinition = textView;
        txtPenetrationTransfer = tintableTextView2;
    }

    
   
    public View getRoot() {
        return rootView;
    }

   
    public static PenetrationViewBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        if (null == viewGroup) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.penetration_view, viewGroup);
        return PenetrationViewBinding.bind(viewGroup);
    }

   
    public static PenetrationViewBinding bind(final View view) {
        int i2 = R.id.button_more;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.button_more);
        if (null != findChildViewById) {
            final ButtonMoreBinding bind = ButtonMoreBinding.bind(findChildViewById);
            i2 = R.id.rlt_penetration_container;
            final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.rlt_penetration_container);
            if (null != relativeLayout) {
                i2 = R.id.rlt_penetrationDetail;
                final RelativeLayout relativeLayout2 = ViewBindings.findChildViewById(view, R.id.rlt_penetrationDetail);
                if (null != relativeLayout2) {
                    i2 = R.id.rlt_penetration_header;
                    final RelativeLayout relativeLayout3 = ViewBindings.findChildViewById(view, R.id.rlt_penetration_header);
                    if (null != relativeLayout3) {
                        i2 = R.id.txt_penetrationDate;
                        final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.txt_penetrationDate);
                        if (null != tintableTextView) {
                            i2 = R.id.txt_penetrationDefinition;
                            final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_penetrationDefinition);
                            if (null != textView) {
                                i2 = R.id.txt_penetrationTransfer;
                                final TintableTextView tintableTextView2 = ViewBindings.findChildViewById(view, R.id.txt_penetrationTransfer);
                                if (null != tintableTextView2) {
                                    return new PenetrationViewBinding(view, bind, relativeLayout, relativeLayout2, relativeLayout3, tintableTextView, textView, tintableTextView2);
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
