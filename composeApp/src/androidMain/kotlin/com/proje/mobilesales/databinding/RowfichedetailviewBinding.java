package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class RowfichedetailviewBinding implements ViewBinding {

   
    public final AppCompatTextView FVAMOUNT;

   
    public final AppCompatTextView FVCODE;

   
    public final AppCompatTextView FVNAME;

   
    public final AppCompatTextView FVNETTOTAL;

   
    public final AppCompatTextView FVPRICE;

   
    public final AppCompatTextView FVUNIT;

   
    public final AppCompatTextView FVVAT;

   
    public final AppCompatTextView LOGICALREF;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView txtAmount;

   
    public final AppCompatTextView txtCode;

   
    public final AppCompatTextView txtName;

   
    public final AppCompatTextView txtNetTotal;

   
    public final AppCompatTextView txtUnit;

   
    public final AppCompatTextView txtUnitPrice;

   
    public final AppCompatTextView txtVat;

    private RowfichedetailviewBinding(final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7, final AppCompatTextView appCompatTextView8, final AppCompatTextView appCompatTextView9, final AppCompatTextView appCompatTextView10, final AppCompatTextView appCompatTextView11, final AppCompatTextView appCompatTextView12, final AppCompatTextView appCompatTextView13, final AppCompatTextView appCompatTextView14, final AppCompatTextView appCompatTextView15) {
        rootView = linearLayout;
        FVAMOUNT = appCompatTextView;
        FVCODE = appCompatTextView2;
        FVNAME = appCompatTextView3;
        FVNETTOTAL = appCompatTextView4;
        FVPRICE = appCompatTextView5;
        FVUNIT = appCompatTextView6;
        FVVAT = appCompatTextView7;
        LOGICALREF = appCompatTextView8;
        txtAmount = appCompatTextView9;
        txtCode = appCompatTextView10;
        txtName = appCompatTextView11;
        txtNetTotal = appCompatTextView12;
        txtUnit = appCompatTextView13;
        txtUnitPrice = appCompatTextView14;
        txtVat = appCompatTextView15;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static RowfichedetailviewBinding inflate(final LayoutInflater layoutInflater) {
        return RowfichedetailviewBinding.inflate(layoutInflater, null, false);
    }

   
    public static RowfichedetailviewBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.rowfichedetailview, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return RowfichedetailviewBinding.bind(inflate);
    }

   
    public static RowfichedetailviewBinding bind(final View view) {
        int i2 = R.id.FVAMOUNT;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.FVAMOUNT);
        if (null != appCompatTextView) {
            i2 = R.id.FVCODE;
            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.FVCODE);
            if (null != appCompatTextView2) {
                i2 = R.id.FVNAME;
                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.FVNAME);
                if (null != appCompatTextView3) {
                    i2 = R.id.FVNETTOTAL;
                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.FVNETTOTAL);
                    if (null != appCompatTextView4) {
                        i2 = R.id.FVPRICE;
                        final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.FVPRICE);
                        if (null != appCompatTextView5) {
                            i2 = R.id.FVUNIT;
                            final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.FVUNIT);
                            if (null != appCompatTextView6) {
                                i2 = R.id.FVVAT;
                                final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.FVVAT);
                                if (null != appCompatTextView7) {
                                    i2 = R.id.LOGICALREF;
                                    final AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.LOGICALREF);
                                    if (null != appCompatTextView8) {
                                        i2 = R.id.txt_amount;
                                        final AppCompatTextView appCompatTextView9 = ViewBindings.findChildViewById(view, R.id.txt_amount);
                                        if (null != appCompatTextView9) {
                                            i2 = R.id.txt_code;
                                            final AppCompatTextView appCompatTextView10 = ViewBindings.findChildViewById(view, R.id.txt_code);
                                            if (null != appCompatTextView10) {
                                                i2 = R.id.txt_name;
                                                final AppCompatTextView appCompatTextView11 = ViewBindings.findChildViewById(view, R.id.txt_name);
                                                if (null != appCompatTextView11) {
                                                    i2 = R.id.txt_netTotal;
                                                    final AppCompatTextView appCompatTextView12 = ViewBindings.findChildViewById(view, R.id.txt_netTotal);
                                                    if (null != appCompatTextView12) {
                                                        i2 = R.id.txt_unit;
                                                        final AppCompatTextView appCompatTextView13 = ViewBindings.findChildViewById(view, R.id.txt_unit);
                                                        if (null != appCompatTextView13) {
                                                            i2 = R.id.txt_unit_price;
                                                            final AppCompatTextView appCompatTextView14 = ViewBindings.findChildViewById(view, R.id.txt_unit_price);
                                                            if (null != appCompatTextView14) {
                                                                i2 = R.id.txt_vat;
                                                                final AppCompatTextView appCompatTextView15 = ViewBindings.findChildViewById(view, R.id.txt_vat);
                                                                if (null != appCompatTextView15) {
                                                                    return new RowfichedetailviewBinding((LinearLayout) view, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8, appCompatTextView9, appCompatTextView10, appCompatTextView11, appCompatTextView12, appCompatTextView13, appCompatTextView14, appCompatTextView15);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
