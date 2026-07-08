package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class FragmentSalesOrderLineBinding implements ViewBinding {

   
    public final AppCompatButton btnAddProduct;

   
    public final AppCompatButton btnAddProductBarcode;

   
    public final AppCompatButton btnAddProductForm;

   
    public final LinearLayout buttonsContainer;

   
    public final FrameLayout frmOrderLineTotal;

   
    public final ImageButton imgOrderLineTotalUp;

   
    public final LinearLayout lnOrderLineCountTotal;

   
    public final LinearLayout lnOrderLineDiscountTotal;

   
    public final LinearLayout lnOrderLineNetTotal;

   
    public final LinearLayout lnOrderLineSku;

   
    public final LinearLayout lnOrderLineTotal;

   
    public final LinearLayout lnOrderLineTotalAmount;

   
    public final LinearLayout lnOrderLineUnitTotal;

   
    public final LinearLayout lnOrderLineVatTotal;

   
    public final RecyclerView rcwList;

   
    public final FrameLayout rltProductContainer;

   
    private final RelativeLayout rootView;

   
    public final TextView txtOrderLineCount;

   
    public final TextView txtOrderLineDiscount;

   
    public final TextView txtOrderLineNet;

   
    public final TextView txtOrderLineSku;

   
    public final TextView txtOrderLineTotal;

   
    public final TextView txtOrderLineTotalAmount;

   
    public final TextView txtOrderLineTotalLabel;

   
    public final TextView txtOrderLineUnitTotal;

   
    public final TextView txtOrderLineVatTotal;

    private FragmentSalesOrderLineBinding(final RelativeLayout relativeLayout, final AppCompatButton appCompatButton, final AppCompatButton appCompatButton2, final AppCompatButton appCompatButton3, final LinearLayout linearLayout, final FrameLayout frameLayout, final ImageButton imageButton, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final LinearLayout linearLayout6, final LinearLayout linearLayout7, final LinearLayout linearLayout8, final LinearLayout linearLayout9, final RecyclerView recyclerView, final FrameLayout frameLayout2, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9) {
        rootView = relativeLayout;
        btnAddProduct = appCompatButton;
        btnAddProductBarcode = appCompatButton2;
        btnAddProductForm = appCompatButton3;
        buttonsContainer = linearLayout;
        frmOrderLineTotal = frameLayout;
        imgOrderLineTotalUp = imageButton;
        lnOrderLineCountTotal = linearLayout2;
        lnOrderLineDiscountTotal = linearLayout3;
        lnOrderLineNetTotal = linearLayout4;
        lnOrderLineSku = linearLayout5;
        lnOrderLineTotal = linearLayout6;
        lnOrderLineTotalAmount = linearLayout7;
        lnOrderLineUnitTotal = linearLayout8;
        lnOrderLineVatTotal = linearLayout9;
        rcwList = recyclerView;
        rltProductContainer = frameLayout2;
        txtOrderLineCount = textView;
        txtOrderLineDiscount = textView2;
        txtOrderLineNet = textView3;
        txtOrderLineSku = textView4;
        txtOrderLineTotal = textView5;
        txtOrderLineTotalAmount = textView6;
        txtOrderLineTotalLabel = textView7;
        txtOrderLineUnitTotal = textView8;
        txtOrderLineVatTotal = textView9;
    }

    
   
    public RelativeLayout getRoot() {
        return rootView;
    }

   
    public static FragmentSalesOrderLineBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentSalesOrderLineBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentSalesOrderLineBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_sales_order_line, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentSalesOrderLineBinding.bind(inflate);
    }

   
    public static FragmentSalesOrderLineBinding bind(final View view) {
        int i2 = R.id.btn_addProduct;
        final AppCompatButton appCompatButton = ViewBindings.findChildViewById(view, R.id.btn_addProduct);
        if (null != appCompatButton) {
            i2 = R.id.btn_addProductBarcode;
            final AppCompatButton appCompatButton2 = ViewBindings.findChildViewById(view, R.id.btn_addProductBarcode);
            if (null != appCompatButton2) {
                i2 = R.id.btn_addProductForm;
                final AppCompatButton appCompatButton3 = ViewBindings.findChildViewById(view, R.id.btn_addProductForm);
                if (null != appCompatButton3) {
                    i2 = R.id.buttons_container;
                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.buttons_container);
                    if (null != linearLayout) {
                        i2 = R.id.frm_OrderLineTotal;
                        final FrameLayout frameLayout = ViewBindings.findChildViewById(view, R.id.frm_OrderLineTotal);
                        if (null != frameLayout) {
                            i2 = R.id.img_orderLineTotalUp;
                            final ImageButton imageButton = ViewBindings.findChildViewById(view, R.id.img_orderLineTotalUp);
                            if (null != imageButton) {
                                i2 = R.id.ln_orderLineCountTotal;
                                final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_orderLineCountTotal);
                                if (null != linearLayout2) {
                                    i2 = R.id.ln_orderLineDiscountTotal;
                                    final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_orderLineDiscountTotal);
                                    if (null != linearLayout3) {
                                        i2 = R.id.ln_orderLineNetTotal;
                                        final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_orderLineNetTotal);
                                        if (null != linearLayout4) {
                                            i2 = R.id.ln_orderLineSku;
                                            final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_orderLineSku);
                                            if (null != linearLayout5) {
                                                i2 = R.id.ln_orderLineTotal;
                                                final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.ln_orderLineTotal);
                                                if (null != linearLayout6) {
                                                    i2 = R.id.ln_orderLineTotalAmount;
                                                    final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.ln_orderLineTotalAmount);
                                                    if (null != linearLayout7) {
                                                        i2 = R.id.ln_orderLineUnitTotal;
                                                        final LinearLayout linearLayout8 = ViewBindings.findChildViewById(view, R.id.ln_orderLineUnitTotal);
                                                        if (null != linearLayout8) {
                                                            i2 = R.id.ln_orderLineVatTotal;
                                                            final LinearLayout linearLayout9 = ViewBindings.findChildViewById(view, R.id.ln_orderLineVatTotal);
                                                            if (null != linearLayout9) {
                                                                i2 = R.id.rcwList;
                                                                final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcwList);
                                                                if (null != recyclerView) {
                                                                    i2 = R.id.rlt_product_container;
                                                                    final FrameLayout frameLayout2 = ViewBindings.findChildViewById(view, R.id.rlt_product_container);
                                                                    if (null != frameLayout2) {
                                                                        i2 = R.id.txt_orderLineCount;
                                                                        final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_orderLineCount);
                                                                        if (null != textView) {
                                                                            i2 = R.id.txt_orderLineDiscount;
                                                                            final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_orderLineDiscount);
                                                                            if (null != textView2) {
                                                                                i2 = R.id.txt_orderLineNet;
                                                                                final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_orderLineNet);
                                                                                if (null != textView3) {
                                                                                    i2 = R.id.txt_orderLineSku;
                                                                                    final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_orderLineSku);
                                                                                    if (null != textView4) {
                                                                                        i2 = R.id.txt_orderLineTotal;
                                                                                        final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_orderLineTotal);
                                                                                        if (null != textView5) {
                                                                                            i2 = R.id.txt_orderLineTotalAmount;
                                                                                            final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_orderLineTotalAmount);
                                                                                            if (null != textView6) {
                                                                                                i2 = R.id.txt_orderLineTotalLabel;
                                                                                                final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_orderLineTotalLabel);
                                                                                                if (null != textView7) {
                                                                                                    i2 = R.id.txt_orderLineUnitTotal;
                                                                                                    final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_orderLineUnitTotal);
                                                                                                    if (null != textView8) {
                                                                                                        i2 = R.id.txt_orderLineVatTotal;
                                                                                                        final TextView textView9 = ViewBindings.findChildViewById(view, R.id.txt_orderLineVatTotal);
                                                                                                        if (null != textView9) {
                                                                                                            return new FragmentSalesOrderLineBinding((RelativeLayout) view, appCompatButton, appCompatButton2, appCompatButton3, linearLayout, frameLayout, imageButton, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, recyclerView, frameLayout2, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9);
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
