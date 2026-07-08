package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.TintableTextView;



public final class ProductViewBinding implements ViewBinding {

   
    public final ImageButton btnProductInfo;

   
    public final FrameLayout buttonToggle;

   
    public final LinearLayout codeAndPriceContainer;

   
    public final EditText edtAmount;

   
    public final ImageView imgProduct;

   
    public final ImageView imgProductSelect;

   
    public final LinearLayout lnAmount;

   
    public final LinearLayout lnDetailContainer;

   
    public final LinearLayout lnProductActualStock;

   
    public final LinearLayout lnProductPrice;

   
    public final LinearLayout lnProductRealStock;

   
    public final LinearLayout lnProductStock;

   
    public final LinearLayout lnUnit;

   
    public final RelativeLayout rltProductContainer;

   
    public final RelativeLayout rltProductHeader;

   
    private final View rootView;

   
    public final AppCompatSpinner spnProductUnitSpinner;

   
    public final TintableTextView toggle;

   
    public final TextView txtProductActualStock;

   
    public final TextView txtProductActualStockText;

   
    public final TextView txtProductCode;

   
    public final TextView txtProductName;

   
    public final TextView txtProductName2;

   
    public final TextView txtProductPrice;

   
    public final TextView txtProductRealStockText;

   
    public final TextView txtRealStock;

    private ProductViewBinding(final View view, final ImageButton imageButton, final FrameLayout frameLayout, final LinearLayout linearLayout, final EditText editText, final ImageView imageView, final ImageView imageView2, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final LinearLayout linearLayout6, final LinearLayout linearLayout7, final LinearLayout linearLayout8, final RelativeLayout relativeLayout, final RelativeLayout relativeLayout2, final AppCompatSpinner appCompatSpinner, final TintableTextView tintableTextView, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8) {
        rootView = view;
        btnProductInfo = imageButton;
        buttonToggle = frameLayout;
        codeAndPriceContainer = linearLayout;
        edtAmount = editText;
        imgProduct = imageView;
        imgProductSelect = imageView2;
        lnAmount = linearLayout2;
        lnDetailContainer = linearLayout3;
        lnProductActualStock = linearLayout4;
        lnProductPrice = linearLayout5;
        lnProductRealStock = linearLayout6;
        lnProductStock = linearLayout7;
        lnUnit = linearLayout8;
        rltProductContainer = relativeLayout;
        rltProductHeader = relativeLayout2;
        spnProductUnitSpinner = appCompatSpinner;
        toggle = tintableTextView;
        txtProductActualStock = textView;
        txtProductActualStockText = textView2;
        txtProductCode = textView3;
        txtProductName = textView4;
        txtProductName2 = textView5;
        txtProductPrice = textView6;
        txtProductRealStockText = textView7;
        txtRealStock = textView8;
    }

    
   
    public View getRoot() {
        return rootView;
    }

   
    public static ProductViewBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        if (null == viewGroup) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.product_view, viewGroup);
        return ProductViewBinding.bind(viewGroup);
    }

   
    public static ProductViewBinding bind(final View view) {
        int i2 = R.id.btn_productInfo;
        final ImageButton imageButton = ViewBindings.findChildViewById(view, R.id.btn_productInfo);
        if (null != imageButton) {
            i2 = R.id.button_toggle;
            final FrameLayout frameLayout = ViewBindings.findChildViewById(view, R.id.button_toggle);
            if (null != frameLayout) {
                i2 = R.id.codeAndPriceContainer;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.codeAndPriceContainer);
                if (null != linearLayout) {
                    i2 = R.id.edt_amount;
                    final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_amount);
                    if (null != editText) {
                        i2 = R.id.img_product;
                        final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_product);
                        if (null != imageView) {
                            i2 = R.id.img_product_select;
                            final ImageView imageView2 = ViewBindings.findChildViewById(view, R.id.img_product_select);
                            if (null != imageView2) {
                                i2 = R.id.ln_amount;
                                final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_amount);
                                if (null != linearLayout2) {
                                    i2 = R.id.ln_detailContainer;
                                    final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_detailContainer);
                                    if (null != linearLayout3) {
                                        i2 = R.id.ln_product_actual_stock;
                                        final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_product_actual_stock);
                                        if (null != linearLayout4) {
                                            i2 = R.id.ln_product_price;
                                            final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_product_price);
                                            if (null != linearLayout5) {
                                                i2 = R.id.ln_product_real_stock;
                                                final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.ln_product_real_stock);
                                                if (null != linearLayout6) {
                                                    i2 = R.id.ln_product_stock;
                                                    final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.ln_product_stock);
                                                    if (null != linearLayout7) {
                                                        i2 = R.id.ln_unit;
                                                        final LinearLayout linearLayout8 = ViewBindings.findChildViewById(view, R.id.ln_unit);
                                                        if (null != linearLayout8) {
                                                            i2 = R.id.rlt_product_container;
                                                            final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.rlt_product_container);
                                                            if (null != relativeLayout) {
                                                                i2 = R.id.rlt_product_header;
                                                                final RelativeLayout relativeLayout2 = ViewBindings.findChildViewById(view, R.id.rlt_product_header);
                                                                if (null != relativeLayout2) {
                                                                    i2 = R.id.spn_productUnitSpinner;
                                                                    final AppCompatSpinner appCompatSpinner = ViewBindings.findChildViewById(view, R.id.spn_productUnitSpinner);
                                                                    if (null != appCompatSpinner) {
                                                                        i2 = R.id.toggle;
                                                                        final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.toggle);
                                                                        if (null != tintableTextView) {
                                                                            i2 = R.id.txt_product_actual_stock;
                                                                            final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_product_actual_stock);
                                                                            if (null != textView) {
                                                                                i2 = R.id.txt_product_actual_stock_text;
                                                                                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_product_actual_stock_text);
                                                                                if (null != textView2) {
                                                                                    i2 = R.id.txt_productCode;
                                                                                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_productCode);
                                                                                    if (null != textView3) {
                                                                                        i2 = R.id.txt_productName;
                                                                                        final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_productName);
                                                                                        if (null != textView4) {
                                                                                            i2 = R.id.txt_productName2;
                                                                                            final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_productName2);
                                                                                            if (null != textView5) {
                                                                                                i2 = R.id.txt_product_price;
                                                                                                final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_product_price);
                                                                                                if (null != textView6) {
                                                                                                    i2 = R.id.txt_product_real_stock_text;
                                                                                                    final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_product_real_stock_text);
                                                                                                    if (null != textView7) {
                                                                                                        i2 = R.id.txt_real_stock;
                                                                                                        final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_real_stock);
                                                                                                        if (null != textView8) {
                                                                                                            return new ProductViewBinding(view, imageButton, frameLayout, linearLayout, editText, imageView, imageView2, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, relativeLayout, relativeLayout2, appCompatSpinner, tintableTextView, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
