package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.TintableTextView;



public final class ProductOperationViewBinding implements ViewBinding {

   
    public final FrameLayout buttonToggle;

   
    public final CheckBox chkSalesDetailPromotion;

   
    public final EditText edtPrice;

   
    public final EditText edtProductAmount;

   
    public final EditText edtSurplusAmount;

   
    public final FrameLayout flSpnLineType;

   
    public final ImageButton imgBtnProductMinus;

   
    public final ImageButton imgBtnProductPlus;

   
    public final ProductOperationDiscountBinding incDiscount1;

   
    public final ProductOperationDiscountBinding incDiscount2;

   
    public final ProductOperationDiscountBinding incDiscount3;

   
    public final LinearLayout llOperationView;

   
    public final LinearLayout lnAmountDialogOpener;

   
    public final LinearLayout lnDiscountContainer;

   
    public final LinearLayout lnPrice;

   
    public final LinearLayout lnSalesDetailPromotion;

   
    public final LinearLayout lnSurplusAmount;

   
    public final LinearLayout priceContainer;

   
    public final LinearLayout priceInfoContainer;

   
    public final RelativeLayout rltProductContainer;

   
    public final RelativeLayout rltProductInfo;

   
    public final RelativeLayout rltProductOperationAmount;

   
    public final RelativeLayout rltProductUnitSelect;

   
    private final View rootView;

   
    public final Spinner spnLineType;

   
    public final AppCompatSpinner spnProductUnitSpinner;

   
    public final TintableTextView toggle;

   
    public final TextView txtDialogSelectedAmount;

   
    public final TextView txtPriceInfo;

   
    public final TextView txtProductAmountTitle;

   
    public final EditText txtProductExplanation;

   
    public final TextView txtProductName;

    private ProductOperationViewBinding(final View view, final FrameLayout frameLayout, final CheckBox checkBox, final EditText editText, final EditText editText2, final EditText editText3, final FrameLayout frameLayout2, final ImageButton imageButton, final ImageButton imageButton2, final ProductOperationDiscountBinding productOperationDiscountBinding, final ProductOperationDiscountBinding productOperationDiscountBinding2, final ProductOperationDiscountBinding productOperationDiscountBinding3, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final LinearLayout linearLayout6, final LinearLayout linearLayout7, final LinearLayout linearLayout8, final RelativeLayout relativeLayout, final RelativeLayout relativeLayout2, final RelativeLayout relativeLayout3, final RelativeLayout relativeLayout4, final Spinner spinner, final AppCompatSpinner appCompatSpinner, final TintableTextView tintableTextView, final TextView textView, final TextView textView2, final TextView textView3, final EditText editText4, final TextView textView4) {
        rootView = view;
        buttonToggle = frameLayout;
        chkSalesDetailPromotion = checkBox;
        edtPrice = editText;
        edtProductAmount = editText2;
        edtSurplusAmount = editText3;
        flSpnLineType = frameLayout2;
        imgBtnProductMinus = imageButton;
        imgBtnProductPlus = imageButton2;
        incDiscount1 = productOperationDiscountBinding;
        incDiscount2 = productOperationDiscountBinding2;
        incDiscount3 = productOperationDiscountBinding3;
        llOperationView = linearLayout;
        lnAmountDialogOpener = linearLayout2;
        lnDiscountContainer = linearLayout3;
        lnPrice = linearLayout4;
        lnSalesDetailPromotion = linearLayout5;
        lnSurplusAmount = linearLayout6;
        priceContainer = linearLayout7;
        priceInfoContainer = linearLayout8;
        rltProductContainer = relativeLayout;
        rltProductInfo = relativeLayout2;
        rltProductOperationAmount = relativeLayout3;
        rltProductUnitSelect = relativeLayout4;
        spnLineType = spinner;
        spnProductUnitSpinner = appCompatSpinner;
        toggle = tintableTextView;
        txtDialogSelectedAmount = textView;
        txtPriceInfo = textView2;
        txtProductAmountTitle = textView3;
        txtProductExplanation = editText4;
        txtProductName = textView4;
    }

    
   
    public View getRoot() {
        return rootView;
    }

   
    public static ProductOperationViewBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        if (null == viewGroup) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.product_operation_view, viewGroup);
        return ProductOperationViewBinding.bind(viewGroup);
    }

   
    public static ProductOperationViewBinding bind(final View view) {
        int i2 = R.id.button_toggle;
        final FrameLayout frameLayout = ViewBindings.findChildViewById(view, R.id.button_toggle);
        if (null != frameLayout) {
            i2 = R.id.chk_salesDetailPromotion;
            final CheckBox checkBox = ViewBindings.findChildViewById(view, R.id.chk_salesDetailPromotion);
            if (null != checkBox) {
                i2 = R.id.edtPrice;
                final EditText editText = ViewBindings.findChildViewById(view, R.id.edtPrice);
                if (null != editText) {
                    i2 = R.id.edt_product_amount;
                    final EditText editText2 = ViewBindings.findChildViewById(view, R.id.edt_product_amount);
                    if (null != editText2) {
                        i2 = R.id.edt_surplusAmount;
                        final EditText editText3 = ViewBindings.findChildViewById(view, R.id.edt_surplusAmount);
                        if (null != editText3) {
                            i2 = R.id.fl_spn_lineType;
                            final FrameLayout frameLayout2 = ViewBindings.findChildViewById(view, R.id.fl_spn_lineType);
                            if (null != frameLayout2) {
                                i2 = R.id.imgBtn_product_minus;
                                final ImageButton imageButton = ViewBindings.findChildViewById(view, R.id.imgBtn_product_minus);
                                if (null != imageButton) {
                                    i2 = R.id.imgBtn_product_plus;
                                    final ImageButton imageButton2 = ViewBindings.findChildViewById(view, R.id.imgBtn_product_plus);
                                    if (null != imageButton2) {
                                        i2 = R.id.inc_discount1;
                                        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.inc_discount1);
                                        if (null != findChildViewById) {
                                            final ProductOperationDiscountBinding bind = ProductOperationDiscountBinding.bind(findChildViewById);
                                            i2 = R.id.inc_discount2;
                                            final View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.inc_discount2);
                                            if (null != findChildViewById2) {
                                                final ProductOperationDiscountBinding bind2 = ProductOperationDiscountBinding.bind(findChildViewById2);
                                                i2 = R.id.inc_discount3;
                                                final View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.inc_discount3);
                                                if (null != findChildViewById3) {
                                                    final ProductOperationDiscountBinding bind3 = ProductOperationDiscountBinding.bind(findChildViewById3);
                                                    i2 = R.id.ll_operation_view;
                                                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ll_operation_view);
                                                    if (null != linearLayout) {
                                                        i2 = R.id.lnAmountDialogOpener;
                                                        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.lnAmountDialogOpener);
                                                        if (null != linearLayout2) {
                                                            i2 = R.id.ln_discountContainer;
                                                            final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_discountContainer);
                                                            if (null != linearLayout3) {
                                                                i2 = R.id.ln_price;
                                                                final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_price);
                                                                if (null != linearLayout4) {
                                                                    i2 = R.id.ln_salesDetailPromotion;
                                                                    final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailPromotion);
                                                                    if (null != linearLayout5) {
                                                                        i2 = R.id.ln_surplusAmount;
                                                                        final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.ln_surplusAmount);
                                                                        if (null != linearLayout6) {
                                                                            i2 = R.id.priceContainer;
                                                                            final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.priceContainer);
                                                                            if (null != linearLayout7) {
                                                                                i2 = R.id.priceInfoContainer;
                                                                                final LinearLayout linearLayout8 = ViewBindings.findChildViewById(view, R.id.priceInfoContainer);
                                                                                if (null != linearLayout8) {
                                                                                    i2 = R.id.rlt_product_container;
                                                                                    final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.rlt_product_container);
                                                                                    if (null != relativeLayout) {
                                                                                        i2 = R.id.rlt_product_info;
                                                                                        final RelativeLayout relativeLayout2 = ViewBindings.findChildViewById(view, R.id.rlt_product_info);
                                                                                        if (null != relativeLayout2) {
                                                                                            i2 = R.id.rlt_productOperationAmount;
                                                                                            final RelativeLayout relativeLayout3 = ViewBindings.findChildViewById(view, R.id.rlt_productOperationAmount);
                                                                                            if (null != relativeLayout3) {
                                                                                                i2 = R.id.rlt_productUnitSelect;
                                                                                                final RelativeLayout relativeLayout4 = ViewBindings.findChildViewById(view, R.id.rlt_productUnitSelect);
                                                                                                if (null != relativeLayout4) {
                                                                                                    i2 = R.id.spn_lineType;
                                                                                                    final Spinner spinner = ViewBindings.findChildViewById(view, R.id.spn_lineType);
                                                                                                    if (null != spinner) {
                                                                                                        i2 = R.id.spn_productUnitSpinner;
                                                                                                        final AppCompatSpinner appCompatSpinner = ViewBindings.findChildViewById(view, R.id.spn_productUnitSpinner);
                                                                                                        if (null != appCompatSpinner) {
                                                                                                            i2 = R.id.toggle;
                                                                                                            final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.toggle);
                                                                                                            if (null != tintableTextView) {
                                                                                                                i2 = R.id.txtDialogSelectedAmount;
                                                                                                                final TextView textView = ViewBindings.findChildViewById(view, R.id.txtDialogSelectedAmount);
                                                                                                                if (null != textView) {
                                                                                                                    i2 = R.id.txtPriceInfo;
                                                                                                                    final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txtPriceInfo);
                                                                                                                    if (null != textView2) {
                                                                                                                        i2 = R.id.txt_product_amount_title;
                                                                                                                        final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_product_amount_title);
                                                                                                                        if (null != textView3) {
                                                                                                                            i2 = R.id.txt_productExplanation;
                                                                                                                            final EditText editText4 = ViewBindings.findChildViewById(view, R.id.txt_productExplanation);
                                                                                                                            if (null != editText4) {
                                                                                                                                i2 = R.id.txt_productName;
                                                                                                                                final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_productName);
                                                                                                                                if (null != textView4) {
                                                                                                                                    return new ProductOperationViewBinding(view, frameLayout, checkBox, editText, editText2, editText3, frameLayout2, imageButton, imageButton2, bind, bind2, bind3, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, spinner, appCompatSpinner, tintableTextView, textView, textView2, textView3, editText4, textView4);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
