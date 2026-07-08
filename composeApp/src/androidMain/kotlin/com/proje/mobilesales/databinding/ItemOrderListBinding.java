package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ItemOrderListBinding implements ViewBinding {

   
    public final AppCompatCheckedTextView CNAME;

   
    public final AppCompatTextView CNAME2;

   
    public final AppCompatTextView CNAME2STR;

   
    public final AppCompatTextView CNAME2STR2;

   
    public final AppCompatTextView DATE;

   
    public final AppCompatTextView FICHENO;

   
    public final AppCompatTextView SLCODESTR;

   
    public final AppCompatTextView SLCODESTR2;

   
    public final AppCompatTextView SLSCODE;

   
    public final AppCompatTextView SNAME;

   
    public final AppCompatTextView SNAMESTR;

   
    public final AppCompatTextView SNAMESTR2;

   
    public final ImageView imgSales;

   
    private final CardView rootView;

    private ItemOrderListBinding(final CardView cardView, final AppCompatCheckedTextView appCompatCheckedTextView, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7, final AppCompatTextView appCompatTextView8, final AppCompatTextView appCompatTextView9, final AppCompatTextView appCompatTextView10, final AppCompatTextView appCompatTextView11, final ImageView imageView) {
        rootView = cardView;
        CNAME = appCompatCheckedTextView;
        CNAME2 = appCompatTextView;
        CNAME2STR = appCompatTextView2;
        CNAME2STR2 = appCompatTextView3;
        DATE = appCompatTextView4;
        FICHENO = appCompatTextView5;
        SLCODESTR = appCompatTextView6;
        SLCODESTR2 = appCompatTextView7;
        SLSCODE = appCompatTextView8;
        SNAME = appCompatTextView9;
        SNAMESTR = appCompatTextView10;
        SNAMESTR2 = appCompatTextView11;
        imgSales = imageView;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemOrderListBinding inflate(final LayoutInflater layoutInflater) {
        return ItemOrderListBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemOrderListBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_order_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemOrderListBinding.bind(inflate);
    }

   
    public static ItemOrderListBinding bind(final View view) {
        int i2 = R.id.CNAME;
        final AppCompatCheckedTextView appCompatCheckedTextView = ViewBindings.findChildViewById(view, R.id.CNAME);
        if (null != appCompatCheckedTextView) {
            i2 = R.id.CNAME2;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.CNAME2);
            if (null != appCompatTextView) {
                i2 = R.id.CNAME2STR;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.CNAME2STR);
                if (null != appCompatTextView2) {
                    i2 = R.id.CNAME2STR2;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.CNAME2STR2);
                    if (null != appCompatTextView3) {
                        i2 = R.id.DATE;
                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.DATE);
                        if (null != appCompatTextView4) {
                            i2 = R.id.FICHENO;
                            final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.FICHENO);
                            if (null != appCompatTextView5) {
                                i2 = R.id.SLCODESTR;
                                final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.SLCODESTR);
                                if (null != appCompatTextView6) {
                                    i2 = R.id.SLCODESTR2;
                                    final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.SLCODESTR2);
                                    if (null != appCompatTextView7) {
                                        i2 = R.id.SLSCODE;
                                        final AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.SLSCODE);
                                        if (null != appCompatTextView8) {
                                            i2 = R.id.SNAME;
                                            final AppCompatTextView appCompatTextView9 = ViewBindings.findChildViewById(view, R.id.SNAME);
                                            if (null != appCompatTextView9) {
                                                i2 = R.id.SNAMESTR;
                                                final AppCompatTextView appCompatTextView10 = ViewBindings.findChildViewById(view, R.id.SNAMESTR);
                                                if (null != appCompatTextView10) {
                                                    i2 = R.id.SNAMESTR2;
                                                    final AppCompatTextView appCompatTextView11 = ViewBindings.findChildViewById(view, R.id.SNAMESTR2);
                                                    if (null != appCompatTextView11) {
                                                        i2 = R.id.img_sales;
                                                        final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_sales);
                                                        if (null != imageView) {
                                                            return new ItemOrderListBinding((CardView) view, appCompatCheckedTextView, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8, appCompatTextView9, appCompatTextView10, appCompatTextView11, imageView);
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
