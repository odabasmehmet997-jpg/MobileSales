package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.TintableTextView;

public final class VisitViewBinding implements ViewBinding {

    
    public final ButtonMoreBinding buttonMore;

    
    public final ImageView imgVisit;

    
    public final LinearLayout lnVisit;

    
    public final RelativeLayout lnVisitInfoNot;

    
    public final RelativeLayout rltProductHeader;

    
    public final RelativeLayout rltVisitContainer;

    
    public final RelativeLayout rltVisitDetail;

    
    private final View rootView;

    
    public final TintableTextView txtVisitDate;

    
    public final TextView txtVisitNote;

    
    public final TintableTextView txtVisitReason;

    
    public final TextView txtVisitShipAddressDefinition;

    
    public final TextView txtVisitShipAddressExplanation;

    
    public final TintableTextView txtVisitTransfer;

    private VisitViewBinding(final View view, final ButtonMoreBinding buttonMoreBinding, final ImageView imageView, final LinearLayout linearLayout, final RelativeLayout relativeLayout, final RelativeLayout relativeLayout2, final RelativeLayout relativeLayout3, final RelativeLayout relativeLayout4, final TintableTextView tintableTextView, final TextView textView, final TintableTextView tintableTextView2, final TextView textView2, final TextView textView3, final TintableTextView tintableTextView3) {
        rootView = view;
        buttonMore = buttonMoreBinding;
        imgVisit = imageView;
        lnVisit = linearLayout;
        lnVisitInfoNot = relativeLayout;
        rltProductHeader = relativeLayout2;
        rltVisitContainer = relativeLayout3;
        rltVisitDetail = relativeLayout4;
        txtVisitDate = tintableTextView;
        txtVisitNote = textView;
        txtVisitReason = tintableTextView2;
        txtVisitShipAddressDefinition = textView2;
        txtVisitShipAddressExplanation = textView3;
        txtVisitTransfer = tintableTextView3;
    }

    
    
    public View getRoot() {
        return rootView;
    }

    
    public static VisitViewBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        if (null == viewGroup) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.visit_view, viewGroup);
        return VisitViewBinding.bind(viewGroup);
    }

    
    public static VisitViewBinding bind(final View view) {
        int i2 = R.id.button_more;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.button_more);
        if (null != findChildViewById) {
            final ButtonMoreBinding bind = ButtonMoreBinding.bind(findChildViewById);
            i2 = R.id.img_visit;
            final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_visit);
            if (null != imageView) {
                i2 = R.id.ln_visit;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_visit);
                if (null != linearLayout) {
                    i2 = R.id.ln_visit_info_not;
                    final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.ln_visit_info_not);
                    if (null != relativeLayout) {
                        i2 = R.id.rlt_product_header;
                        final RelativeLayout relativeLayout2 = ViewBindings.findChildViewById(view, R.id.rlt_product_header);
                        if (null != relativeLayout2) {
                            i2 = R.id.rlt_visit_container;
                            final RelativeLayout relativeLayout3 = ViewBindings.findChildViewById(view, R.id.rlt_visit_container);
                            if (null != relativeLayout3) {
                                i2 = R.id.rlt_visit_detail;
                                final RelativeLayout relativeLayout4 = ViewBindings.findChildViewById(view, R.id.rlt_visit_detail);
                                if (null != relativeLayout4) {
                                    i2 = R.id.txt_visit_date;
                                    final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.txt_visit_date);
                                    if (null != tintableTextView) {
                                        i2 = R.id.txt_visit_note;
                                        final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_visit_note);
                                        if (null != textView) {
                                            i2 = R.id.txt_visit_reason;
                                            final TintableTextView tintableTextView2 = ViewBindings.findChildViewById(view, R.id.txt_visit_reason);
                                            if (null != tintableTextView2) {
                                                i2 = R.id.txt_visit_ship_address_definition;
                                                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_visit_ship_address_definition);
                                                if (null != textView2) {
                                                    i2 = R.id.txt_visit_ship_address_explanation;
                                                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_visit_ship_address_explanation);
                                                    if (null != textView3) {
                                                        i2 = R.id.txt_visit_transfer;
                                                        final TintableTextView tintableTextView3 = ViewBindings.findChildViewById(view, R.id.txt_visit_transfer);
                                                        if (null != tintableTextView3) {
                                                            return new VisitViewBinding(view, bind, imageView, linearLayout, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, tintableTextView, textView, tintableTextView2, textView2, textView3, tintableTextView3);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
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
