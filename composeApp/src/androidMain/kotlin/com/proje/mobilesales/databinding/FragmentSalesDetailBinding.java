package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class FragmentSalesDetailBinding implements ViewBinding {

   
    public final CheckBox chkSalesDetailPromotion;

   
    public final CheckBox chkSalesDetailReserve;

   
    public final CheckBox chkSalesDetailVatInclude;

   
    public final View currPriceDivider;

   
    public final View currencyRateDivider;

   
    public final View currencyTypeDivider;

   
    public final View deliveryDateDivider;

   
    public final View detailVatDivider;

   
    public final View dueDateDivider;

   
    public final View dvdAmount2;

   
    public final View dvdSecondUnit;

   
    public final View dvdSurplusAmount;

   
    public final EditText edtCustomerDiscountRatio;

   
    public final EditText edtSalesDetailAmount;

   
    public final EditText edtSalesDetailDiscountRatio1;

   
    public final EditText edtSalesDetailDiscountRatio2;

   
    public final EditText edtSalesDetailDiscountRatio3;

   
    public final EditText edtSalesDetailDiscountRatio4;

   
    public final EditText edtSalesDetailDiscountRatio5;

   
    public final EditText edtSalesDetailDiscountTotal1;

   
    public final EditText edtSalesDetailDiscountTotal2;

   
    public final EditText edtSalesDetailDiscountTotal3;

   
    public final EditText edtSalesDetailDiscountTotal4;

   
    public final EditText edtSalesDetailDiscountTotal5;

   
    public final EditText edtSalesDetailExplanation;

   
    public final EditText edtSalesDetailPrice;

   
    public final EditText edtSalesDetailSecondAmount;

   
    public final EditText edtSalesDetailVat;

   
    public final EditText edtSurplusAmount;

   
    public final View includeVatDivider;

   
    public final View lastCustomerSalesDateDivider;

   
    public final View lastPurchaseDateDivider;

   
    public final View lastPurchaseDivider;

   
    public final LinearLayout lnCustomerDiscountRate;

   
    public final LinearLayout lnDiscountContainer;

   
    public final LinearLayout lnDueDate;

   
    public final LinearLayout lnLastCustomerSalesDate;

   
    public final LinearLayout lnLastPurchaseDate;

   
    public final LinearLayout lnLastPurchasePrice;

   
    public final LinearLayout lnSalesDetailAmount;

   
    public final LinearLayout lnSalesDetailCurrPrice;

   
    public final LinearLayout lnSalesDetailCurrRate;

   
    public final LinearLayout lnSalesDetailCurrSelectType;

   
    public final LinearLayout lnSalesDetailDeliveryCode;

   
    public final LinearLayout lnSalesDetailDeliveryDate;

   
    public final LinearLayout lnSalesDetailDiscount1;

   
    public final LinearLayout lnSalesDetailDiscount2;

   
    public final LinearLayout lnSalesDetailDiscount3;

   
    public final LinearLayout lnSalesDetailDiscount4;

   
    public final LinearLayout lnSalesDetailDiscount5;

   
    public final LinearLayout lnSalesDetailExplanation;

   
    public final LinearLayout lnSalesDetailName;

   
    public final LinearLayout lnSalesDetailPayment;

   
    public final LinearLayout lnSalesDetailPrice;

   
    public final LinearLayout lnSalesDetailPriceSelectType;

   
    public final LinearLayout lnSalesDetailPromotion;

   
    public final LinearLayout lnSalesDetailReferenceCode;

   
    public final LinearLayout lnSalesDetailReserve;

   
    public final LinearLayout lnSalesDetailSecondAmount;

   
    public final LinearLayout lnSalesDetailSecondUnit;

   
    public final LinearLayout lnSalesDetailSelectablePrice;

   
    public final LinearLayout lnSalesDetailSerialLotNo;

   
    public final LinearLayout lnSalesDetailSpeCode;

   
    public final LinearLayout lnSalesDetailUnit;

   
    public final LinearLayout lnSalesDetailVariant;

   
    public final LinearLayout lnSalesDetailVat;

   
    public final LinearLayout lnSalesDetailVatInclude;

   
    public final LinearLayout lnSalesDetailWareHouse;

   
    public final LinearLayout lnSurplusAmount;

   
    public final View paymentDivider;

   
    public final View priceDivider;

   
    public final View priceSelectTypeDivider;

   
    public final View promotionDivider;

   
    public final View referenceCodeDivider;

   
    public final View reserveDivider;

   
    private final FrameLayout rootView;

   
    public final View selectablePriceDivider;

   
    public final View seriLotDivider;

   
    public final Spinner spnLineType;

   
    public final TextView textView;

   
    public final TextView textView2;

   
    public final TextView txtDiscountCardHeader;

   
    public final TextView txtDueDate;

   
    public final TextView txtLastCustomerSalesDate;

   
    public final TextView txtLastPurchaseDate;

   
    public final TextView txtLastPurchasePrice;

   
    public final TextView txtSalesDetailCurrPrice;

   
    public final TextView txtSalesDetailCurrRate;

   
    public final TextView txtSalesDetailCurrSelectType;

   
    public final TextView txtSalesDetailDeliveryCode;

   
    public final TextView txtSalesDetailDeliveryDate;

   
    public final TextView txtSalesDetailDiscountCart1;

   
    public final TextView txtSalesDetailDiscountCart2;

   
    public final TextView txtSalesDetailDiscountCart3;

   
    public final TextView txtSalesDetailDiscountCart4;

   
    public final TextView txtSalesDetailDiscountCart5;

   
    public final TextView txtSalesDetailName;

   
    public final TextView txtSalesDetailPayment;

   
    public final TextView txtSalesDetailPriceSelectType;

   
    public final TextView txtSalesDetailReferenceCode;

   
    public final TextView txtSalesDetailSecondUnit;

   
    public final TextView txtSalesDetailSelectablePrice;

   
    public final TextView txtSalesDetailSerialLotNo;

   
    public final TextView txtSalesDetailSerialLotNoHeader;

   
    public final TextView txtSalesDetailSpeCode;

   
    public final TextView txtSalesDetailUnit;

   
    public final TextView txtSalesDetailVariant;

   
    public final TextView txtSalesDetailWareHouse;

   
    public final TextView txtSalesDetailWareHouseHeader;

   
    public final View variantDivider;

   
    public final View wareHouseDivider;

    private FragmentSalesDetailBinding(final FrameLayout frameLayout, final CheckBox checkBox, final CheckBox checkBox2, final CheckBox checkBox3, final View view, final View view2, final View view3, final View view4, final View view5, final View view6, final View view7, final View view8, final View view9, final EditText editText, final EditText editText2, final EditText editText3, final EditText editText4, final EditText editText5, final EditText editText6, final EditText editText7, final EditText editText8, final EditText editText9, final EditText editText10, final EditText editText11, final EditText editText12, final EditText editText13, final EditText editText14, final EditText editText15, final EditText editText16, final EditText editText17, final View view10, final View view11, final View view12, final View view13, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final LinearLayout linearLayout6, final LinearLayout linearLayout7, final LinearLayout linearLayout8, final LinearLayout linearLayout9, final LinearLayout linearLayout10, final LinearLayout linearLayout11, final LinearLayout linearLayout12, final LinearLayout linearLayout13, final LinearLayout linearLayout14, final LinearLayout linearLayout15, final LinearLayout linearLayout16, final LinearLayout linearLayout17, final LinearLayout linearLayout18, final LinearLayout linearLayout19, final LinearLayout linearLayout20, final LinearLayout linearLayout21, final LinearLayout linearLayout22, final LinearLayout linearLayout23, final LinearLayout linearLayout24, final LinearLayout linearLayout25, final LinearLayout linearLayout26, final LinearLayout linearLayout27, final LinearLayout linearLayout28, final LinearLayout linearLayout29, final LinearLayout linearLayout30, final LinearLayout linearLayout31, final LinearLayout linearLayout32, final LinearLayout linearLayout33, final LinearLayout linearLayout34, final LinearLayout linearLayout35, final LinearLayout linearLayout36, final View view14, final View view15, final View view16, final View view17, final View view18, final View view19, final View view20, final View view21, final Spinner spinner, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9, final TextView textView10, final TextView textView11, final TextView textView12, final TextView textView13, final TextView textView14, final TextView textView15, final TextView textView16, final TextView textView17, final TextView textView18, final TextView textView19, final TextView textView20, final TextView textView21, final TextView textView22, final TextView textView23, final TextView textView24, final TextView textView25, final TextView textView26, final TextView textView27, final TextView textView28, final TextView textView29, final TextView textView30, final View view22, final View view23) {
        rootView = frameLayout;
        chkSalesDetailPromotion = checkBox;
        chkSalesDetailReserve = checkBox2;
        chkSalesDetailVatInclude = checkBox3;
        currPriceDivider = view;
        currencyRateDivider = view2;
        currencyTypeDivider = view3;
        deliveryDateDivider = view4;
        detailVatDivider = view5;
        dueDateDivider = view6;
        dvdAmount2 = view7;
        dvdSecondUnit = view8;
        dvdSurplusAmount = view9;
        edtCustomerDiscountRatio = editText;
        edtSalesDetailAmount = editText2;
        edtSalesDetailDiscountRatio1 = editText3;
        edtSalesDetailDiscountRatio2 = editText4;
        edtSalesDetailDiscountRatio3 = editText5;
        edtSalesDetailDiscountRatio4 = editText6;
        edtSalesDetailDiscountRatio5 = editText7;
        edtSalesDetailDiscountTotal1 = editText8;
        edtSalesDetailDiscountTotal2 = editText9;
        edtSalesDetailDiscountTotal3 = editText10;
        edtSalesDetailDiscountTotal4 = editText11;
        edtSalesDetailDiscountTotal5 = editText12;
        edtSalesDetailExplanation = editText13;
        edtSalesDetailPrice = editText14;
        edtSalesDetailSecondAmount = editText15;
        edtSalesDetailVat = editText16;
        edtSurplusAmount = editText17;
        includeVatDivider = view10;
        lastCustomerSalesDateDivider = view11;
        lastPurchaseDateDivider = view12;
        lastPurchaseDivider = view13;
        lnCustomerDiscountRate = linearLayout;
        lnDiscountContainer = linearLayout2;
        lnDueDate = linearLayout3;
        lnLastCustomerSalesDate = linearLayout4;
        lnLastPurchaseDate = linearLayout5;
        lnLastPurchasePrice = linearLayout6;
        lnSalesDetailAmount = linearLayout7;
        lnSalesDetailCurrPrice = linearLayout8;
        lnSalesDetailCurrRate = linearLayout9;
        lnSalesDetailCurrSelectType = linearLayout10;
        lnSalesDetailDeliveryCode = linearLayout11;
        lnSalesDetailDeliveryDate = linearLayout12;
        lnSalesDetailDiscount1 = linearLayout13;
        lnSalesDetailDiscount2 = linearLayout14;
        lnSalesDetailDiscount3 = linearLayout15;
        lnSalesDetailDiscount4 = linearLayout16;
        lnSalesDetailDiscount5 = linearLayout17;
        lnSalesDetailExplanation = linearLayout18;
        lnSalesDetailName = linearLayout19;
        lnSalesDetailPayment = linearLayout20;
        lnSalesDetailPrice = linearLayout21;
        lnSalesDetailPriceSelectType = linearLayout22;
        lnSalesDetailPromotion = linearLayout23;
        lnSalesDetailReferenceCode = linearLayout24;
        lnSalesDetailReserve = linearLayout25;
        lnSalesDetailSecondAmount = linearLayout26;
        lnSalesDetailSecondUnit = linearLayout27;
        lnSalesDetailSelectablePrice = linearLayout28;
        lnSalesDetailSerialLotNo = linearLayout29;
        lnSalesDetailSpeCode = linearLayout30;
        lnSalesDetailUnit = linearLayout31;
        lnSalesDetailVariant = linearLayout32;
        lnSalesDetailVat = linearLayout33;
        lnSalesDetailVatInclude = linearLayout34;
        lnSalesDetailWareHouse = linearLayout35;
        lnSurplusAmount = linearLayout36;
        paymentDivider = view14;
        priceDivider = view15;
        priceSelectTypeDivider = view16;
        promotionDivider = view17;
        referenceCodeDivider = view18;
        reserveDivider = view19;
        selectablePriceDivider = view20;
        seriLotDivider = view21;
        spnLineType = spinner;
        this.textView = textView;
        this.textView2 = textView2;
        txtDiscountCardHeader = textView3;
        txtDueDate = textView4;
        txtLastCustomerSalesDate = textView5;
        txtLastPurchaseDate = textView6;
        txtLastPurchasePrice = textView7;
        txtSalesDetailCurrPrice = textView8;
        txtSalesDetailCurrRate = textView9;
        txtSalesDetailCurrSelectType = textView10;
        txtSalesDetailDeliveryCode = textView11;
        txtSalesDetailDeliveryDate = textView12;
        txtSalesDetailDiscountCart1 = textView13;
        txtSalesDetailDiscountCart2 = textView14;
        txtSalesDetailDiscountCart3 = textView15;
        txtSalesDetailDiscountCart4 = textView16;
        txtSalesDetailDiscountCart5 = textView17;
        txtSalesDetailName = textView18;
        txtSalesDetailPayment = textView19;
        txtSalesDetailPriceSelectType = textView20;
        txtSalesDetailReferenceCode = textView21;
        txtSalesDetailSecondUnit = textView22;
        txtSalesDetailSelectablePrice = textView23;
        txtSalesDetailSerialLotNo = textView24;
        txtSalesDetailSerialLotNoHeader = textView25;
        txtSalesDetailSpeCode = textView26;
        txtSalesDetailUnit = textView27;
        txtSalesDetailVariant = textView28;
        txtSalesDetailWareHouse = textView29;
        txtSalesDetailWareHouseHeader = textView30;
        variantDivider = view22;
        wareHouseDivider = view23;
    }

    
   
    public FrameLayout getRoot() {
        return rootView;
    }

   
    public static FragmentSalesDetailBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentSalesDetailBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentSalesDetailBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_sales_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentSalesDetailBinding.bind(inflate);
    }

   
    public static FragmentSalesDetailBinding bind(final View view) {
        int i2 = R.id.chk_salesDetailPromotion;
        final CheckBox checkBox = ViewBindings.findChildViewById(view, R.id.chk_salesDetailPromotion);
        if (null != checkBox) {
            i2 = R.id.chk_salesDetailReserve;
            final CheckBox checkBox2 = ViewBindings.findChildViewById(view, R.id.chk_salesDetailReserve);
            if (null != checkBox2) {
                i2 = R.id.chk_salesDetailVatInclude;
                final CheckBox checkBox3 = ViewBindings.findChildViewById(view, R.id.chk_salesDetailVatInclude);
                if (null != checkBox3) {
                    i2 = R.id.currPriceDivider;
                    final View findChildViewById = ViewBindings.findChildViewById(view, R.id.currPriceDivider);
                    if (null != findChildViewById) {
                        i2 = R.id.currencyRateDivider;
                        final View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.currencyRateDivider);
                        if (null != findChildViewById2) {
                            i2 = R.id.currencyTypeDivider;
                            final View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.currencyTypeDivider);
                            if (null != findChildViewById3) {
                                i2 = R.id.deliveryDateDivider;
                                final View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.deliveryDateDivider);
                                if (null != findChildViewById4) {
                                    i2 = R.id.detailVatDivider;
                                    final View findChildViewById5 = ViewBindings.findChildViewById(view, R.id.detailVatDivider);
                                    if (null != findChildViewById5) {
                                        i2 = R.id.dueDateDivider;
                                        final View findChildViewById6 = ViewBindings.findChildViewById(view, R.id.dueDateDivider);
                                        if (null != findChildViewById6) {
                                            i2 = R.id.dvdAmount2;
                                            final View findChildViewById7 = ViewBindings.findChildViewById(view, R.id.dvdAmount2);
                                            if (null != findChildViewById7) {
                                                i2 = R.id.dvdSecondUnit;
                                                final View findChildViewById8 = ViewBindings.findChildViewById(view, R.id.dvdSecondUnit);
                                                if (null != findChildViewById8) {
                                                    i2 = R.id.dvdSurplusAmount;
                                                    final View findChildViewById9 = ViewBindings.findChildViewById(view, R.id.dvdSurplusAmount);
                                                    if (null != findChildViewById9) {
                                                        i2 = R.id.edt_customerDiscountRatio;
                                                        final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_customerDiscountRatio);
                                                        if (null != editText) {
                                                            i2 = R.id.edt_salesDetailAmount;
                                                            final EditText editText2 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailAmount);
                                                            if (null != editText2) {
                                                                i2 = R.id.edt_salesDetailDiscountRatio1;
                                                                final EditText editText3 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailDiscountRatio1);
                                                                if (null != editText3) {
                                                                    i2 = R.id.edt_salesDetailDiscountRatio2;
                                                                    final EditText editText4 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailDiscountRatio2);
                                                                    if (null != editText4) {
                                                                        i2 = R.id.edt_salesDetailDiscountRatio3;
                                                                        final EditText editText5 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailDiscountRatio3);
                                                                        if (null != editText5) {
                                                                            i2 = R.id.edt_salesDetailDiscountRatio4;
                                                                            final EditText editText6 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailDiscountRatio4);
                                                                            if (null != editText6) {
                                                                                i2 = R.id.edt_salesDetailDiscountRatio5;
                                                                                final EditText editText7 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailDiscountRatio5);
                                                                                if (null != editText7) {
                                                                                    i2 = R.id.edt_salesDetailDiscountTotal1;
                                                                                    final EditText editText8 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailDiscountTotal1);
                                                                                    if (null != editText8) {
                                                                                        i2 = R.id.edt_salesDetailDiscountTotal2;
                                                                                        final EditText editText9 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailDiscountTotal2);
                                                                                        if (null != editText9) {
                                                                                            i2 = R.id.edt_salesDetailDiscountTotal3;
                                                                                            final EditText editText10 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailDiscountTotal3);
                                                                                            if (null != editText10) {
                                                                                                i2 = R.id.edt_salesDetailDiscountTotal4;
                                                                                                final EditText editText11 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailDiscountTotal4);
                                                                                                if (null != editText11) {
                                                                                                    i2 = R.id.edt_salesDetailDiscountTotal5;
                                                                                                    final EditText editText12 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailDiscountTotal5);
                                                                                                    if (null != editText12) {
                                                                                                        i2 = R.id.edt_salesDetailExplanation;
                                                                                                        final EditText editText13 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailExplanation);
                                                                                                        if (null != editText13) {
                                                                                                            i2 = R.id.edt_salesDetailPrice;
                                                                                                            final EditText editText14 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailPrice);
                                                                                                            if (null != editText14) {
                                                                                                                i2 = R.id.edt_salesDetailSecondAmount;
                                                                                                                final EditText editText15 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailSecondAmount);
                                                                                                                if (null != editText15) {
                                                                                                                    i2 = R.id.edt_salesDetailVat;
                                                                                                                    final EditText editText16 = ViewBindings.findChildViewById(view, R.id.edt_salesDetailVat);
                                                                                                                    if (null != editText16) {
                                                                                                                        i2 = R.id.edt_surplusAmount;
                                                                                                                        final EditText editText17 = ViewBindings.findChildViewById(view, R.id.edt_surplusAmount);
                                                                                                                        if (null != editText17) {
                                                                                                                            i2 = R.id.includeVatDivider;
                                                                                                                            final View findChildViewById10 = ViewBindings.findChildViewById(view, R.id.includeVatDivider);
                                                                                                                            if (null != findChildViewById10) {
                                                                                                                                i2 = R.id.lastCustomerSalesDateDivider;
                                                                                                                                final View findChildViewById11 = ViewBindings.findChildViewById(view, R.id.lastCustomerSalesDateDivider);
                                                                                                                                if (null != findChildViewById11) {
                                                                                                                                    i2 = R.id.lastPurchaseDateDivider;
                                                                                                                                    final View findChildViewById12 = ViewBindings.findChildViewById(view, R.id.lastPurchaseDateDivider);
                                                                                                                                    if (null != findChildViewById12) {
                                                                                                                                        i2 = R.id.lastPurchaseDivider;
                                                                                                                                        final View findChildViewById13 = ViewBindings.findChildViewById(view, R.id.lastPurchaseDivider);
                                                                                                                                        if (null != findChildViewById13) {
                                                                                                                                            i2 = R.id.ln_customerDiscountRate;
                                                                                                                                            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_customerDiscountRate);
                                                                                                                                            if (null != linearLayout) {
                                                                                                                                                i2 = R.id.ln_discountContainer;
                                                                                                                                                final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_discountContainer);
                                                                                                                                                if (null != linearLayout2) {
                                                                                                                                                    i2 = R.id.ln_dueDate;
                                                                                                                                                    final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_dueDate);
                                                                                                                                                    if (null != linearLayout3) {
                                                                                                                                                        i2 = R.id.ln_LastCustomerSalesDate;
                                                                                                                                                        final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_LastCustomerSalesDate);
                                                                                                                                                        if (null != linearLayout4) {
                                                                                                                                                            i2 = R.id.ln_LastPurchaseDate;
                                                                                                                                                            final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_LastPurchaseDate);
                                                                                                                                                            if (null != linearLayout5) {
                                                                                                                                                                i2 = R.id.ln_LastPurchasePrice;
                                                                                                                                                                final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.ln_LastPurchasePrice);
                                                                                                                                                                if (null != linearLayout6) {
                                                                                                                                                                    i2 = R.id.ln_salesDetailAmount;
                                                                                                                                                                    final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailAmount);
                                                                                                                                                                    if (null != linearLayout7) {
                                                                                                                                                                        i2 = R.id.ln_salesDetailCurrPrice;
                                                                                                                                                                        final LinearLayout linearLayout8 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailCurrPrice);
                                                                                                                                                                        if (null != linearLayout8) {
                                                                                                                                                                            i2 = R.id.ln_salesDetailCurrRate;
                                                                                                                                                                            final LinearLayout linearLayout9 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailCurrRate);
                                                                                                                                                                            if (null != linearLayout9) {
                                                                                                                                                                                i2 = R.id.ln_salesDetailCurrSelectType;
                                                                                                                                                                                final LinearLayout linearLayout10 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailCurrSelectType);
                                                                                                                                                                                if (null != linearLayout10) {
                                                                                                                                                                                    i2 = R.id.ln_salesDetailDeliveryCode;
                                                                                                                                                                                    final LinearLayout linearLayout11 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailDeliveryCode);
                                                                                                                                                                                    if (null != linearLayout11) {
                                                                                                                                                                                        i2 = R.id.ln_salesDetailDeliveryDate;
                                                                                                                                                                                        final LinearLayout linearLayout12 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailDeliveryDate);
                                                                                                                                                                                        if (null != linearLayout12) {
                                                                                                                                                                                            i2 = R.id.ln_salesDetailDiscount1;
                                                                                                                                                                                            final LinearLayout linearLayout13 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailDiscount1);
                                                                                                                                                                                            if (null != linearLayout13) {
                                                                                                                                                                                                i2 = R.id.ln_salesDetailDiscount2;
                                                                                                                                                                                                final LinearLayout linearLayout14 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailDiscount2);
                                                                                                                                                                                                if (null != linearLayout14) {
                                                                                                                                                                                                    i2 = R.id.ln_salesDetailDiscount3;
                                                                                                                                                                                                    final LinearLayout linearLayout15 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailDiscount3);
                                                                                                                                                                                                    if (null != linearLayout15) {
                                                                                                                                                                                                        i2 = R.id.ln_salesDetailDiscount4;
                                                                                                                                                                                                        final LinearLayout linearLayout16 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailDiscount4);
                                                                                                                                                                                                        if (null != linearLayout16) {
                                                                                                                                                                                                            i2 = R.id.ln_salesDetailDiscount5;
                                                                                                                                                                                                            final LinearLayout linearLayout17 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailDiscount5);
                                                                                                                                                                                                            if (null != linearLayout17) {
                                                                                                                                                                                                                i2 = R.id.ln_salesDetailExplanation;
                                                                                                                                                                                                                final LinearLayout linearLayout18 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailExplanation);
                                                                                                                                                                                                                if (null != linearLayout18) {
                                                                                                                                                                                                                    i2 = R.id.ln_salesDetailName;
                                                                                                                                                                                                                    final LinearLayout linearLayout19 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailName);
                                                                                                                                                                                                                    if (null != linearLayout19) {
                                                                                                                                                                                                                        i2 = R.id.ln_salesDetailPayment;
                                                                                                                                                                                                                        final LinearLayout linearLayout20 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailPayment);
                                                                                                                                                                                                                        if (null != linearLayout20) {
                                                                                                                                                                                                                            i2 = R.id.ln_salesDetailPrice;
                                                                                                                                                                                                                            final LinearLayout linearLayout21 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailPrice);
                                                                                                                                                                                                                            if (null != linearLayout21) {
                                                                                                                                                                                                                                i2 = R.id.ln_salesDetailPriceSelectType;
                                                                                                                                                                                                                                final LinearLayout linearLayout22 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailPriceSelectType);
                                                                                                                                                                                                                                if (null != linearLayout22) {
                                                                                                                                                                                                                                    i2 = R.id.ln_salesDetailPromotion;
                                                                                                                                                                                                                                    final LinearLayout linearLayout23 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailPromotion);
                                                                                                                                                                                                                                    if (null != linearLayout23) {
                                                                                                                                                                                                                                        i2 = R.id.ln_salesDetailReferenceCode;
                                                                                                                                                                                                                                        final LinearLayout linearLayout24 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailReferenceCode);
                                                                                                                                                                                                                                        if (null != linearLayout24) {
                                                                                                                                                                                                                                            i2 = R.id.ln_salesDetailReserve;
                                                                                                                                                                                                                                            final LinearLayout linearLayout25 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailReserve);
                                                                                                                                                                                                                                            if (null != linearLayout25) {
                                                                                                                                                                                                                                                i2 = R.id.ln_salesDetailSecondAmount;
                                                                                                                                                                                                                                                final LinearLayout linearLayout26 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailSecondAmount);
                                                                                                                                                                                                                                                if (null != linearLayout26) {
                                                                                                                                                                                                                                                    i2 = R.id.ln_salesDetailSecondUnit;
                                                                                                                                                                                                                                                    final LinearLayout linearLayout27 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailSecondUnit);
                                                                                                                                                                                                                                                    if (null != linearLayout27) {
                                                                                                                                                                                                                                                        i2 = R.id.ln_salesDetailSelectablePrice;
                                                                                                                                                                                                                                                        final LinearLayout linearLayout28 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailSelectablePrice);
                                                                                                                                                                                                                                                        if (null != linearLayout28) {
                                                                                                                                                                                                                                                            i2 = R.id.ln_salesDetailSerialLotNo;
                                                                                                                                                                                                                                                            final LinearLayout linearLayout29 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailSerialLotNo);
                                                                                                                                                                                                                                                            if (null != linearLayout29) {
                                                                                                                                                                                                                                                                i2 = R.id.ln_salesDetailSpeCode;
                                                                                                                                                                                                                                                                final LinearLayout linearLayout30 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailSpeCode);
                                                                                                                                                                                                                                                                if (null != linearLayout30) {
                                                                                                                                                                                                                                                                    i2 = R.id.ln_salesDetailUnit;
                                                                                                                                                                                                                                                                    final LinearLayout linearLayout31 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailUnit);
                                                                                                                                                                                                                                                                    if (null != linearLayout31) {
                                                                                                                                                                                                                                                                        i2 = R.id.ln_salesDetailVariant;
                                                                                                                                                                                                                                                                        final LinearLayout linearLayout32 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailVariant);
                                                                                                                                                                                                                                                                        if (null != linearLayout32) {
                                                                                                                                                                                                                                                                            i2 = R.id.ln_salesDetailVat;
                                                                                                                                                                                                                                                                            final LinearLayout linearLayout33 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailVat);
                                                                                                                                                                                                                                                                            if (null != linearLayout33) {
                                                                                                                                                                                                                                                                                i2 = R.id.ln_salesDetailVatInclude;
                                                                                                                                                                                                                                                                                final LinearLayout linearLayout34 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailVatInclude);
                                                                                                                                                                                                                                                                                if (null != linearLayout34) {
                                                                                                                                                                                                                                                                                    i2 = R.id.ln_salesDetailWareHouse;
                                                                                                                                                                                                                                                                                    final LinearLayout linearLayout35 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailWareHouse);
                                                                                                                                                                                                                                                                                    if (null != linearLayout35) {
                                                                                                                                                                                                                                                                                        i2 = R.id.ln_surplusAmount;
                                                                                                                                                                                                                                                                                        final LinearLayout linearLayout36 = ViewBindings.findChildViewById(view, R.id.ln_surplusAmount);
                                                                                                                                                                                                                                                                                        if (null != linearLayout36) {
                                                                                                                                                                                                                                                                                            i2 = R.id.paymentDivider;
                                                                                                                                                                                                                                                                                            final View findChildViewById14 = ViewBindings.findChildViewById(view, R.id.paymentDivider);
                                                                                                                                                                                                                                                                                            if (null != findChildViewById14) {
                                                                                                                                                                                                                                                                                                i2 = R.id.priceDivider;
                                                                                                                                                                                                                                                                                                final View findChildViewById15 = ViewBindings.findChildViewById(view, R.id.priceDivider);
                                                                                                                                                                                                                                                                                                if (null != findChildViewById15) {
                                                                                                                                                                                                                                                                                                    i2 = R.id.priceSelectTypeDivider;
                                                                                                                                                                                                                                                                                                    final View findChildViewById16 = ViewBindings.findChildViewById(view, R.id.priceSelectTypeDivider);
                                                                                                                                                                                                                                                                                                    if (null != findChildViewById16) {
                                                                                                                                                                                                                                                                                                        i2 = R.id.promotionDivider;
                                                                                                                                                                                                                                                                                                        final View findChildViewById17 = ViewBindings.findChildViewById(view, R.id.promotionDivider);
                                                                                                                                                                                                                                                                                                        if (null != findChildViewById17) {
                                                                                                                                                                                                                                                                                                            i2 = R.id.referenceCodeDivider;
                                                                                                                                                                                                                                                                                                            final View findChildViewById18 = ViewBindings.findChildViewById(view, R.id.referenceCodeDivider);
                                                                                                                                                                                                                                                                                                            if (null != findChildViewById18) {
                                                                                                                                                                                                                                                                                                                i2 = R.id.reserveDivider;
                                                                                                                                                                                                                                                                                                                final View findChildViewById19 = ViewBindings.findChildViewById(view, R.id.reserveDivider);
                                                                                                                                                                                                                                                                                                                if (null != findChildViewById19) {
                                                                                                                                                                                                                                                                                                                    i2 = R.id.selectablePriceDivider;
                                                                                                                                                                                                                                                                                                                    final View findChildViewById20 = ViewBindings.findChildViewById(view, R.id.selectablePriceDivider);
                                                                                                                                                                                                                                                                                                                    if (null != findChildViewById20) {
                                                                                                                                                                                                                                                                                                                        i2 = R.id.seriLotDivider;
                                                                                                                                                                                                                                                                                                                        final View findChildViewById21 = ViewBindings.findChildViewById(view, R.id.seriLotDivider);
                                                                                                                                                                                                                                                                                                                        if (null != findChildViewById21) {
                                                                                                                                                                                                                                                                                                                            i2 = R.id.spn_lineType;
                                                                                                                                                                                                                                                                                                                            final Spinner spinner = ViewBindings.findChildViewById(view, R.id.spn_lineType);
                                                                                                                                                                                                                                                                                                                            if (null != spinner) {
                                                                                                                                                                                                                                                                                                                                i2 = R.id.textView;
                                                                                                                                                                                                                                                                                                                                final TextView textView = ViewBindings.findChildViewById(view, R.id.textView);
                                                                                                                                                                                                                                                                                                                                if (null != textView) {
                                                                                                                                                                                                                                                                                                                                    i2 = R.id.textView2;
                                                                                                                                                                                                                                                                                                                                    final TextView textView2 = ViewBindings.findChildViewById(view, R.id.textView2);
                                                                                                                                                                                                                                                                                                                                    if (null != textView2) {
                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txtDiscountCardHeader;
                                                                                                                                                                                                                                                                                                                                        final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txtDiscountCardHeader);
                                                                                                                                                                                                                                                                                                                                        if (null != textView3) {
                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txt_dueDate;
                                                                                                                                                                                                                                                                                                                                            final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_dueDate);
                                                                                                                                                                                                                                                                                                                                            if (null != textView4) {
                                                                                                                                                                                                                                                                                                                                                i2 = R.id.txtLastCustomerSalesDate;
                                                                                                                                                                                                                                                                                                                                                final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txtLastCustomerSalesDate);
                                                                                                                                                                                                                                                                                                                                                if (null != textView5) {
                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txtLastPurchaseDate;
                                                                                                                                                                                                                                                                                                                                                    final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txtLastPurchaseDate);
                                                                                                                                                                                                                                                                                                                                                    if (null != textView6) {
                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txtLastPurchasePrice;
                                                                                                                                                                                                                                                                                                                                                        final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txtLastPurchasePrice);
                                                                                                                                                                                                                                                                                                                                                        if (null != textView7) {
                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txt_salesDetailCurrPrice;
                                                                                                                                                                                                                                                                                                                                                            final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailCurrPrice);
                                                                                                                                                                                                                                                                                                                                                            if (null != textView8) {
                                                                                                                                                                                                                                                                                                                                                                i2 = R.id.txt_salesDetailCurrRate;
                                                                                                                                                                                                                                                                                                                                                                final TextView textView9 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailCurrRate);
                                                                                                                                                                                                                                                                                                                                                                if (null != textView9) {
                                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txt_salesDetailCurrSelectType;
                                                                                                                                                                                                                                                                                                                                                                    final TextView textView10 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailCurrSelectType);
                                                                                                                                                                                                                                                                                                                                                                    if (null != textView10) {
                                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txt_salesDetailDeliveryCode;
                                                                                                                                                                                                                                                                                                                                                                        final TextView textView11 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailDeliveryCode);
                                                                                                                                                                                                                                                                                                                                                                        if (null != textView11) {
                                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txt_salesDetailDeliveryDate;
                                                                                                                                                                                                                                                                                                                                                                            final TextView textView12 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailDeliveryDate);
                                                                                                                                                                                                                                                                                                                                                                            if (null != textView12) {
                                                                                                                                                                                                                                                                                                                                                                                i2 = R.id.txt_salesDetailDiscountCart1;
                                                                                                                                                                                                                                                                                                                                                                                final TextView textView13 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailDiscountCart1);
                                                                                                                                                                                                                                                                                                                                                                                if (null != textView13) {
                                                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txt_salesDetailDiscountCart2;
                                                                                                                                                                                                                                                                                                                                                                                    final TextView textView14 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailDiscountCart2);
                                                                                                                                                                                                                                                                                                                                                                                    if (null != textView14) {
                                                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txt_salesDetailDiscountCart3;
                                                                                                                                                                                                                                                                                                                                                                                        final TextView textView15 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailDiscountCart3);
                                                                                                                                                                                                                                                                                                                                                                                        if (null != textView15) {
                                                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txt_salesDetailDiscountCart4;
                                                                                                                                                                                                                                                                                                                                                                                            final TextView textView16 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailDiscountCart4);
                                                                                                                                                                                                                                                                                                                                                                                            if (null != textView16) {
                                                                                                                                                                                                                                                                                                                                                                                                i2 = R.id.txt_salesDetailDiscountCart5;
                                                                                                                                                                                                                                                                                                                                                                                                final TextView textView17 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailDiscountCart5);
                                                                                                                                                                                                                                                                                                                                                                                                if (null != textView17) {
                                                                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txt_salesDetailName;
                                                                                                                                                                                                                                                                                                                                                                                                    final TextView textView18 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailName);
                                                                                                                                                                                                                                                                                                                                                                                                    if (null != textView18) {
                                                                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txt_salesDetailPayment;
                                                                                                                                                                                                                                                                                                                                                                                                        final TextView textView19 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailPayment);
                                                                                                                                                                                                                                                                                                                                                                                                        if (null != textView19) {
                                                                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txt_salesDetailPriceSelectType;
                                                                                                                                                                                                                                                                                                                                                                                                            final TextView textView20 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailPriceSelectType);
                                                                                                                                                                                                                                                                                                                                                                                                            if (null != textView20) {
                                                                                                                                                                                                                                                                                                                                                                                                                i2 = R.id.txt_salesDetailReferenceCode;
                                                                                                                                                                                                                                                                                                                                                                                                                final TextView textView21 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailReferenceCode);
                                                                                                                                                                                                                                                                                                                                                                                                                if (null != textView21) {
                                                                                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txt_salesDetailSecondUnit;
                                                                                                                                                                                                                                                                                                                                                                                                                    final TextView textView22 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailSecondUnit);
                                                                                                                                                                                                                                                                                                                                                                                                                    if (null != textView22) {
                                                                                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txt_salesDetailSelectablePrice;
                                                                                                                                                                                                                                                                                                                                                                                                                        final TextView textView23 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailSelectablePrice);
                                                                                                                                                                                                                                                                                                                                                                                                                        if (null != textView23) {
                                                                                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txt_salesDetailSerialLotNo;
                                                                                                                                                                                                                                                                                                                                                                                                                            final TextView textView24 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailSerialLotNo);
                                                                                                                                                                                                                                                                                                                                                                                                                            if (null != textView24) {
                                                                                                                                                                                                                                                                                                                                                                                                                                i2 = R.id.txt_salesDetailSerialLotNoHeader;
                                                                                                                                                                                                                                                                                                                                                                                                                                final TextView textView25 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailSerialLotNoHeader);
                                                                                                                                                                                                                                                                                                                                                                                                                                if (null != textView25) {
                                                                                                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txt_salesDetailSpeCode;
                                                                                                                                                                                                                                                                                                                                                                                                                                    final TextView textView26 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailSpeCode);
                                                                                                                                                                                                                                                                                                                                                                                                                                    if (null != textView26) {
                                                                                                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txt_salesDetailUnit;
                                                                                                                                                                                                                                                                                                                                                                                                                                        final TextView textView27 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailUnit);
                                                                                                                                                                                                                                                                                                                                                                                                                                        if (null != textView27) {
                                                                                                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txt_salesDetailVariant;
                                                                                                                                                                                                                                                                                                                                                                                                                                            final TextView textView28 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailVariant);
                                                                                                                                                                                                                                                                                                                                                                                                                                            if (null != textView28) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                i2 = R.id.txt_salesDetailWareHouse;
                                                                                                                                                                                                                                                                                                                                                                                                                                                final TextView textView29 = ViewBindings.findChildViewById(view, R.id.txt_salesDetailWareHouse);
                                                                                                                                                                                                                                                                                                                                                                                                                                                if (null != textView29) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txtSalesDetailWareHouseHeader;
                                                                                                                                                                                                                                                                                                                                                                                                                                                    final TextView textView30 = ViewBindings.findChildViewById(view, R.id.txtSalesDetailWareHouseHeader);
                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (null != textView30) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.variantDivider;
                                                                                                                                                                                                                                                                                                                                                                                                                                                        final View findChildViewById22 = ViewBindings.findChildViewById(view, R.id.variantDivider);
                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (null != findChildViewById22) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.wareHouseDivider;
                                                                                                                                                                                                                                                                                                                                                                                                                                                            final View findChildViewById23 = ViewBindings.findChildViewById(view, R.id.wareHouseDivider);
                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (null != findChildViewById23) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                return new FragmentSalesDetailBinding((FrameLayout) view, checkBox, checkBox2, checkBox3, findChildViewById, findChildViewById2, findChildViewById3, findChildViewById4, findChildViewById5, findChildViewById6, findChildViewById7, findChildViewById8, findChildViewById9, editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9, editText10, editText11, editText12, editText13, editText14, editText15, editText16, editText17, findChildViewById10, findChildViewById11, findChildViewById12, findChildViewById13, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12, linearLayout13, linearLayout14, linearLayout15, linearLayout16, linearLayout17, linearLayout18, linearLayout19, linearLayout20, linearLayout21, linearLayout22, linearLayout23, linearLayout24, linearLayout25, linearLayout26, linearLayout27, linearLayout28, linearLayout29, linearLayout30, linearLayout31, linearLayout32, linearLayout33, linearLayout34, linearLayout35, linearLayout36, findChildViewById14, findChildViewById15, findChildViewById16, findChildViewById17, findChildViewById18, findChildViewById19, findChildViewById20, findChildViewById21, spinner, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21, textView22, textView23, textView24, textView25, textView26, textView27, textView28, textView29, textView30, findChildViewById22, findChildViewById23);
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
