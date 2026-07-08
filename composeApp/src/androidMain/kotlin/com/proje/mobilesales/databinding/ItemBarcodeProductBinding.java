package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.TintableTextView;



public final class ItemBarcodeProductBinding implements ViewBinding {

   
    public final FrameLayout buttonToggle;

   
    public final CheckBox chkSalesDetailPromotion;

   
    public final EditText edtAmount;

   
    public final EditText edtExplanation;

   
    public final EditText edtPrice;

   
    public final EditText edtSecondAmount;

   
    public final EditText edtSurplusAmount;

   
    public final ImageView imgAddToList;

   
    public final ImageView imgRemoveFromList;

   
    public final ProductOperationDiscountBinding incDiscount1;

   
    public final ProductOperationDiscountBinding incDiscount2;

   
    public final ProductOperationDiscountBinding incDiscount3;

   
    public final LinearLayout lnAmount;

   
    public final LinearLayout lnButton;

   
    public final LinearLayout lnDefinedPrice;

   
    public final LinearLayout lnDetailContainer;

   
    public final LinearLayout lnDetailSecondContainer;

   
    public final LinearLayout lnDiscountHolder;

   
    public final LinearLayout lnLine;

   
    public final LinearLayout lnPrice;

   
    public final LinearLayout lnPriceContainer;

   
    public final LinearLayout lnProductActualStock;

   
    public final LinearLayout lnProductRealStock;

   
    public final LinearLayout lnProductStock;

   
    public final LinearLayout lnPromotion;

   
    public final LinearLayout lnSecondAmount;

   
    public final LinearLayout lnSecondUnit;

   
    public final LinearLayout lnSurplusAmount;

   
    public final LinearLayout lnUnit;

   
    public final LinearLayout rltProductContainer;

   
    public final LinearLayout rltProductHeader;

   
    private final CardView rootView;

   
    public final AppCompatSpinner spnLineType;

   
    public final AppCompatSpinner spnProductUnitSpinner;

   
    public final AppCompatSpinner spnSecondProductUnitSpinner;

   
    public final TintableTextView toggle;

   
    public final TextView txtDefinedPrice;

   
    public final TextView txtProductActualStock;

   
    public final TextView txtProductActualStockText;

   
    public final TextView txtProductCode;

   
    public final TextView txtProductName;

   
    public final TextView txtProductRealStockText;

   
    public final TextView txtPromotion;

   
    public final TextView txtRealStock;

   
    public final TextView txtSelectablePrice;

   
    public final TextView txtSurplusAmountHeader;

    private ItemBarcodeProductBinding(final CardView cardView, final FrameLayout frameLayout, final CheckBox checkBox, final EditText editText, final EditText editText2, final EditText editText3, final EditText editText4, final EditText editText5, final ImageView imageView, final ImageView imageView2, final ProductOperationDiscountBinding productOperationDiscountBinding, final ProductOperationDiscountBinding productOperationDiscountBinding2, final ProductOperationDiscountBinding productOperationDiscountBinding3, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final LinearLayout linearLayout6, final LinearLayout linearLayout7, final LinearLayout linearLayout8, final LinearLayout linearLayout9, final LinearLayout linearLayout10, final LinearLayout linearLayout11, final LinearLayout linearLayout12, final LinearLayout linearLayout13, final LinearLayout linearLayout14, final LinearLayout linearLayout15, final LinearLayout linearLayout16, final LinearLayout linearLayout17, final LinearLayout linearLayout18, final LinearLayout linearLayout19, final AppCompatSpinner appCompatSpinner, final AppCompatSpinner appCompatSpinner2, final AppCompatSpinner appCompatSpinner3, final TintableTextView tintableTextView, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9, final TextView textView10) {
        rootView = cardView;
        buttonToggle = frameLayout;
        chkSalesDetailPromotion = checkBox;
        edtAmount = editText;
        edtExplanation = editText2;
        edtPrice = editText3;
        edtSecondAmount = editText4;
        edtSurplusAmount = editText5;
        imgAddToList = imageView;
        imgRemoveFromList = imageView2;
        incDiscount1 = productOperationDiscountBinding;
        incDiscount2 = productOperationDiscountBinding2;
        incDiscount3 = productOperationDiscountBinding3;
        lnAmount = linearLayout;
        lnButton = linearLayout2;
        lnDefinedPrice = linearLayout3;
        lnDetailContainer = linearLayout4;
        lnDetailSecondContainer = linearLayout5;
        lnDiscountHolder = linearLayout6;
        lnLine = linearLayout7;
        lnPrice = linearLayout8;
        lnPriceContainer = linearLayout9;
        lnProductActualStock = linearLayout10;
        lnProductRealStock = linearLayout11;
        lnProductStock = linearLayout12;
        lnPromotion = linearLayout13;
        lnSecondAmount = linearLayout14;
        lnSecondUnit = linearLayout15;
        lnSurplusAmount = linearLayout16;
        lnUnit = linearLayout17;
        rltProductContainer = linearLayout18;
        rltProductHeader = linearLayout19;
        spnLineType = appCompatSpinner;
        spnProductUnitSpinner = appCompatSpinner2;
        spnSecondProductUnitSpinner = appCompatSpinner3;
        toggle = tintableTextView;
        txtDefinedPrice = textView;
        txtProductActualStock = textView2;
        txtProductActualStockText = textView3;
        txtProductCode = textView4;
        txtProductName = textView5;
        txtProductRealStockText = textView6;
        txtPromotion = textView7;
        txtRealStock = textView8;
        txtSelectablePrice = textView9;
        txtSurplusAmountHeader = textView10;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemBarcodeProductBinding inflate(final LayoutInflater layoutInflater) {
        return ItemBarcodeProductBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemBarcodeProductBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_barcode_product, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemBarcodeProductBinding.bind(inflate);
    }

   
    public static ItemBarcodeProductBinding bind(final View view) {
        int i2 = R.id.button_toggle;
        final FrameLayout frameLayout = ViewBindings.findChildViewById(view, R.id.button_toggle);
        if (null != frameLayout) {
            i2 = R.id.chk_salesDetailPromotion;
            final CheckBox checkBox = ViewBindings.findChildViewById(view, R.id.chk_salesDetailPromotion);
            if (null != checkBox) {
                i2 = R.id.edt_amount;
                final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_amount);
                if (null != editText) {
                    i2 = R.id.edtExplanation;
                    final EditText editText2 = ViewBindings.findChildViewById(view, R.id.edtExplanation);
                    if (null != editText2) {
                        i2 = R.id.edtPrice;
                        final EditText editText3 = ViewBindings.findChildViewById(view, R.id.edtPrice);
                        if (null != editText3) {
                            i2 = R.id.edt_secondAmount;
                            final EditText editText4 = ViewBindings.findChildViewById(view, R.id.edt_secondAmount);
                            if (null != editText4) {
                                i2 = R.id.edt_surplusAmount;
                                final EditText editText5 = ViewBindings.findChildViewById(view, R.id.edt_surplusAmount);
                                if (null != editText5) {
                                    i2 = R.id.img_add_to_list;
                                    final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_add_to_list);
                                    if (null != imageView) {
                                        i2 = R.id.img_remove_from_list;
                                        final ImageView imageView2 = ViewBindings.findChildViewById(view, R.id.img_remove_from_list);
                                        if (null != imageView2) {
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
                                                        i2 = R.id.ln_amount;
                                                        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_amount);
                                                        if (null != linearLayout) {
                                                            i2 = R.id.lnButton;
                                                            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.lnButton);
                                                            if (null != linearLayout2) {
                                                                i2 = R.id.ln_definedPrice;
                                                                final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_definedPrice);
                                                                if (null != linearLayout3) {
                                                                    i2 = R.id.ln_detailContainer;
                                                                    final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_detailContainer);
                                                                    if (null != linearLayout4) {
                                                                        i2 = R.id.ln_detailSecondContainer;
                                                                        final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_detailSecondContainer);
                                                                        if (null != linearLayout5) {
                                                                            i2 = R.id.lnDiscountHolder;
                                                                            final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.lnDiscountHolder);
                                                                            if (null != linearLayout6) {
                                                                                i2 = R.id.ln_Line;
                                                                                final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.ln_Line);
                                                                                if (null != linearLayout7) {
                                                                                    i2 = R.id.ln_price;
                                                                                    final LinearLayout linearLayout8 = ViewBindings.findChildViewById(view, R.id.ln_price);
                                                                                    if (null != linearLayout8) {
                                                                                        i2 = R.id.ln_priceContainer;
                                                                                        final LinearLayout linearLayout9 = ViewBindings.findChildViewById(view, R.id.ln_priceContainer);
                                                                                        if (null != linearLayout9) {
                                                                                            i2 = R.id.ln_product_actual_stock;
                                                                                            final LinearLayout linearLayout10 = ViewBindings.findChildViewById(view, R.id.ln_product_actual_stock);
                                                                                            if (null != linearLayout10) {
                                                                                                i2 = R.id.ln_product_real_stock;
                                                                                                final LinearLayout linearLayout11 = ViewBindings.findChildViewById(view, R.id.ln_product_real_stock);
                                                                                                if (null != linearLayout11) {
                                                                                                    i2 = R.id.ln_product_stock;
                                                                                                    final LinearLayout linearLayout12 = ViewBindings.findChildViewById(view, R.id.ln_product_stock);
                                                                                                    if (null != linearLayout12) {
                                                                                                        i2 = R.id.lnPromotion;
                                                                                                        final LinearLayout linearLayout13 = ViewBindings.findChildViewById(view, R.id.lnPromotion);
                                                                                                        if (null != linearLayout13) {
                                                                                                            i2 = R.id.ln_secondAmount;
                                                                                                            final LinearLayout linearLayout14 = ViewBindings.findChildViewById(view, R.id.ln_secondAmount);
                                                                                                            if (null != linearLayout14) {
                                                                                                                i2 = R.id.ln_secondUnit;
                                                                                                                final LinearLayout linearLayout15 = ViewBindings.findChildViewById(view, R.id.ln_secondUnit);
                                                                                                                if (null != linearLayout15) {
                                                                                                                    i2 = R.id.ln_surplusAmount;
                                                                                                                    final LinearLayout linearLayout16 = ViewBindings.findChildViewById(view, R.id.ln_surplusAmount);
                                                                                                                    if (null != linearLayout16) {
                                                                                                                        i2 = R.id.ln_unit;
                                                                                                                        final LinearLayout linearLayout17 = ViewBindings.findChildViewById(view, R.id.ln_unit);
                                                                                                                        if (null != linearLayout17) {
                                                                                                                            i2 = R.id.rlt_product_container;
                                                                                                                            final LinearLayout linearLayout18 = ViewBindings.findChildViewById(view, R.id.rlt_product_container);
                                                                                                                            if (null != linearLayout18) {
                                                                                                                                i2 = R.id.rlt_product_header;
                                                                                                                                final LinearLayout linearLayout19 = ViewBindings.findChildViewById(view, R.id.rlt_product_header);
                                                                                                                                if (null != linearLayout19) {
                                                                                                                                    i2 = R.id.spn_lineType;
                                                                                                                                    final AppCompatSpinner appCompatSpinner = ViewBindings.findChildViewById(view, R.id.spn_lineType);
                                                                                                                                    if (null != appCompatSpinner) {
                                                                                                                                        i2 = R.id.spn_productUnitSpinner;
                                                                                                                                        final AppCompatSpinner appCompatSpinner2 = ViewBindings.findChildViewById(view, R.id.spn_productUnitSpinner);
                                                                                                                                        if (null != appCompatSpinner2) {
                                                                                                                                            i2 = R.id.spn_secondProductUnitSpinner;
                                                                                                                                            final AppCompatSpinner appCompatSpinner3 = ViewBindings.findChildViewById(view, R.id.spn_secondProductUnitSpinner);
                                                                                                                                            if (null != appCompatSpinner3) {
                                                                                                                                                i2 = R.id.toggle;
                                                                                                                                                final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.toggle);
                                                                                                                                                if (null != tintableTextView) {
                                                                                                                                                    i2 = R.id.txt_definedPrice;
                                                                                                                                                    final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_definedPrice);
                                                                                                                                                    if (null != textView) {
                                                                                                                                                        i2 = R.id.txt_product_actual_stock;
                                                                                                                                                        final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_product_actual_stock);
                                                                                                                                                        if (null != textView2) {
                                                                                                                                                            i2 = R.id.txt_product_actual_stock_text;
                                                                                                                                                            final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_product_actual_stock_text);
                                                                                                                                                            if (null != textView3) {
                                                                                                                                                                i2 = R.id.txt_productCode;
                                                                                                                                                                final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_productCode);
                                                                                                                                                                if (null != textView4) {
                                                                                                                                                                    i2 = R.id.txt_productName;
                                                                                                                                                                    final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_productName);
                                                                                                                                                                    if (null != textView5) {
                                                                                                                                                                        i2 = R.id.txt_product_real_stock_text;
                                                                                                                                                                        final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txt_product_real_stock_text);
                                                                                                                                                                        if (null != textView6) {
                                                                                                                                                                            i2 = R.id.txt_promotion;
                                                                                                                                                                            final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_promotion);
                                                                                                                                                                            if (null != textView7) {
                                                                                                                                                                                i2 = R.id.txt_real_stock;
                                                                                                                                                                                final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_real_stock);
                                                                                                                                                                                if (null != textView8) {
                                                                                                                                                                                    i2 = R.id.txtSelectablePrice;
                                                                                                                                                                                    final TextView textView9 = ViewBindings.findChildViewById(view, R.id.txtSelectablePrice);
                                                                                                                                                                                    if (null != textView9) {
                                                                                                                                                                                        i2 = R.id.txt_surplusAmountHeader;
                                                                                                                                                                                        final TextView textView10 = ViewBindings.findChildViewById(view, R.id.txt_surplusAmountHeader);
                                                                                                                                                                                        if (null != textView10) {
                                                                                                                                                                                            return new ItemBarcodeProductBinding((CardView) view, frameLayout, checkBox, editText, editText2, editText3, editText4, editText5, imageView, imageView2, bind, bind2, bind3, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12, linearLayout13, linearLayout14, linearLayout15, linearLayout16, linearLayout17, linearLayout18, linearLayout19, appCompatSpinner, appCompatSpinner2, appCompatSpinner3, tintableTextView, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10);
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
