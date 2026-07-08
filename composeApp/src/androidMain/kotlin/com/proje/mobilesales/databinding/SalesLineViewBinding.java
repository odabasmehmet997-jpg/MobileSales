package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class SalesLineViewBinding implements ViewBinding {

   
    public final ImageView btnProductDetail;

   
    public final ImageButton btnProductInfo;

   
    public final ImageView btnProductOperation;

   
    public final LinearLayout lnBarcodeCount;

   
    public final LinearLayout lnDiscountRate;

   
    public final RelativeLayout rltContainer;

   
    private final View rootView;

   
    public final TextView txtBarcodeCount;

   
    public final TextView txtBarcodeCountHeader;

   
    public final TextView txtDiscountRate;

   
    public final TextView txtDiscountRateHeader;

   
    public final TextView txtProductCode;

   
    public final TextView txtProductName;

   
    public final TextView txtProductTotal;

   
    public final TextView txtProductTotalWithCurrency;

   
    public final TextView txtProductUnitPrice;

   
    public final TextView txtProductVariant;

   
    public final TextView txtProductVariantHeader;

   
    public final TextView txtPromotion;

   
    public final TextView txtSurplusAmount;

   
    public final TextView txtSurplusAmountHeader;

    private SalesLineViewBinding(final View view, final ImageView imageView, final ImageButton imageButton, final ImageView imageView2, final LinearLayout linearLayout, final LinearLayout linearLayout2, final RelativeLayout relativeLayout, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9, final TextView textView10, final TextView textView11, final TextView textView12, final TextView textView13, final TextView textView14) {
        rootView = view;
        btnProductDetail = imageView;
        btnProductInfo = imageButton;
        btnProductOperation = imageView2;
        lnBarcodeCount = linearLayout;
        lnDiscountRate = linearLayout2;
        rltContainer = relativeLayout;
        txtBarcodeCount = textView;
        txtBarcodeCountHeader = textView2;
        txtDiscountRate = textView3;
        txtDiscountRateHeader = textView4;
        txtProductCode = textView5;
        txtProductName = textView6;
        txtProductTotal = textView7;
        txtProductTotalWithCurrency = textView8;
        txtProductUnitPrice = textView9;
        txtProductVariant = textView10;
        txtProductVariantHeader = textView11;
        txtPromotion = textView12;
        txtSurplusAmount = textView13;
        txtSurplusAmountHeader = textView14;
    }

    
   
    public View getRoot() {
        return rootView;
    }

   
    public static SalesLineViewBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        if (null == viewGroup) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.sales_line_view, viewGroup);
        return SalesLineViewBinding.bind(viewGroup);
    }

   
    public static SalesLineViewBinding bind(final View view) {
        int i2 = R.id.btn_productDetail;
        final ImageView imageView = ViewBindings.findChildViewById(view, R.id.btn_productDetail);
        if (null != imageView) {
            i2 = R.id.btn_productInfo;
            final ImageButton imageButton = ViewBindings.findChildViewById(view, R.id.btn_productInfo);
            if (null != imageButton) {
                i2 = R.id.btn_productOperation;
                final ImageView imageView2 = ViewBindings.findChildViewById(view, R.id.btn_productOperation);
                if (null != imageView2) {
                    i2 = R.id.ln_barcode_count;
                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_barcode_count);
                    if (null != linearLayout) {
                        i2 = R.id.ln_discount_rate;
                        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_discount_rate);
                        if (null != linearLayout2) {
                            i2 = R.id.rlt_container;
                            final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.rlt_container);
                            if (null != relativeLayout) {
                                i2 = R.id.txt_barcode_count;
                                final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_barcode_count);
                                if (null != textView) {
                                    i2 = R.id.txt_barcode_count_header;
                                    final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_barcode_count_header);
                                    if (null != textView2) {
                                        i2 = R.id.txt_discount_rate;
                                        final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_discount_rate);
                                        if (null != textView3) {
                                            i2 = R.id.txt_discount_rate_header;
                                            final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_discount_rate_header);
                                            if (null != textView4) {
                                                i2 = R.id.txt_productCode;
                                                final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_productCode);
                                                if (null != textView5) {
                                                    i2 = R.id.txt_productName;
                                                    final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_productName);
                                                    if (null != textView6) {
                                                        i2 = R.id.txt_productTotal;
                                                        final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_productTotal);
                                                        if (null != textView7) {
                                                            i2 = R.id.txt_productTotalWithCurrency;
                                                            final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_productTotalWithCurrency);
                                                            if (null != textView8) {
                                                                i2 = R.id.txt_productUnitPrice;
                                                                final TextView textView9 = ViewBindings.findChildViewById(view, R.id.txt_productUnitPrice);
                                                                if (null != textView9) {
                                                                    i2 = R.id.txt_productVariant;
                                                                    final TextView textView10 = ViewBindings.findChildViewById(view, R.id.txt_productVariant);
                                                                    if (null != textView10) {
                                                                        i2 = R.id.txt_productVariantHeader;
                                                                        final TextView textView11 = ViewBindings.findChildViewById(view, R.id.txt_productVariantHeader);
                                                                        if (null != textView11) {
                                                                            i2 = R.id.txt_promotion;
                                                                            final TextView textView12 = ViewBindings.findChildViewById(view, R.id.txt_promotion);
                                                                            if (null != textView12) {
                                                                                i2 = R.id.txt_surplusAmount;
                                                                                final TextView textView13 = ViewBindings.findChildViewById(view, R.id.txt_surplusAmount);
                                                                                if (null != textView13) {
                                                                                    i2 = R.id.txt_surplusAmountHeader;
                                                                                    final TextView textView14 = ViewBindings.findChildViewById(view, R.id.txt_surplusAmountHeader);
                                                                                    if (null != textView14) {
                                                                                        return new SalesLineViewBinding(view, imageView, imageButton, imageView2, linearLayout, linearLayout2, relativeLayout, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14);
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
