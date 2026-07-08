package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class FragmentSalesHeaderBinding implements ViewBinding {

   
    public final View cabinDivider;

   
    public final CheckBox chkEDespatch;

   
    public final CheckBox chkInsteadOfEDespatch;

   
    public final CheckBox chkSalesConsignee;

   
    public final CheckBox chkSalesDetailVatInclude;

   
    public final CheckBox chkSalesDoReserve;

   
    public final CheckBox chkSalesPaymentOrder;

   
    public final View customerDivider;

   
    public final View dvdErpInvoiceType;

   
    public final View dvdSerial;

   
    public final View dvdShipAgent;

   
    public final View dvdspeCode;

   
    public final View dvdspeCode1;

   
    public final View dvdspeCode2;

   
    public final View eDespatchDivider;

   
    public final EditText edtDocumentNo;

   
    public final EditText edtSalesCustomerOrderNo;

   
    public final EditText edtSalesDocumentNo;

   
    public final EditText edtSalesDocumentTrackingNo;

   
    public final EditText edtSalesExplanation1;

   
    public final EditText edtSalesExplanation10;

   
    public final EditText edtSalesExplanation2;

   
    public final EditText edtSalesExplanation3;

   
    public final EditText edtSalesExplanation4;

   
    public final EditText edtSalesExplanation5;

   
    public final EditText edtSalesExplanation6;

   
    public final EditText edtSalesExplanation7;

   
    public final EditText edtSalesExplanation8;

   
    public final EditText edtSalesExplanation9;

   
    public final EditText edtTaxpayerCode;

   
    public final EditText edtTaxpayerName;

   
    public final ImageView imgSalesCustomerClear;

   
    public final View insteadOfEDespatchDivider;

   
    public final LinearLayout lnAdditionalInvoiceType;

   
    public final View lnAdditionalInvoiceTypeDivider;

   
    public final LinearLayout lnDocumentNo;

   
    public final View lnDocumentNoDivider;

   
    public final LinearLayout lnEDespatch;

   
    public final LinearLayout lnEndDate;

   
    public final View lnEndDateDivider;

   
    public final LinearLayout lnInsteadOfEDespatch;

   
    public final LinearLayout lnSalesBranch;

   
    public final LinearLayout lnSalesCabin;

   
    public final LinearLayout lnSalesConsignee;

   
    public final LinearLayout lnSalesCustomer;

   
    public final LinearLayout lnSalesCustomerOrderNo;

   
    public final LinearLayout lnSalesDeliveryDate;

   
    public final LinearLayout lnSalesDetailVatInclude;

   
    public final LinearLayout lnSalesDivision;

   
    public final LinearLayout lnSalesDoReserve;

   
    public final LinearLayout lnSalesDocumentNo;

   
    public final LinearLayout lnSalesDocumentTrackingNo;

   
    public final LinearLayout lnSalesErpInoviceType;

   
    public final LinearLayout lnSalesExplanation1;

   
    public final LinearLayout lnSalesExplanation10;

   
    public final LinearLayout lnSalesExplanation2;

   
    public final LinearLayout lnSalesExplanation3;

   
    public final LinearLayout lnSalesExplanation4;

   
    public final LinearLayout lnSalesExplanation5;

   
    public final LinearLayout lnSalesExplanation6;

   
    public final LinearLayout lnSalesExplanation7;

   
    public final LinearLayout lnSalesExplanation8;

   
    public final LinearLayout lnSalesExplanation9;

   
    public final LinearLayout lnSalesFactory;

   
    public final LinearLayout lnSalesPaymentOrder;

   
    public final LinearLayout lnSalesReturnWareHouse;

   
    public final LinearLayout lnSalesSourceWareHouse;

   
    public final LinearLayout lnSalesSpeCode;

   
    public final LinearLayout lnSalesSpeCode1;

   
    public final LinearLayout lnSalesSpeCode2;

   
    public final LinearLayout lnSalesWareHouse;

   
    public final LinearLayout lnSalesWarrantyCode;

   
    public final LinearLayout lnSerial;

   
    public final LinearLayout lnSgkEfaturaBilgileri;

   
    public final View lnSgkEinvoiceInformationDivider;

   
    public final LinearLayout lnShipAgent;

   
    public final LinearLayout lnStartingDate;

   
    public final View lnStartingDateDivider;

   
    public final View lnTaxPayerCodeDivider;

   
    public final View lnTaxPayerNameDivider;

   
    public final LinearLayout lnTaxpayerCode;

   
    public final LinearLayout lnTaxpayerName;

   
    private final LinearLayout rootView;

   
    public final View salesCustomerOrderNoDivider;

   
    public final View salesDeliveryDateDivider;

   
    public final View salesDetailVatIncludeDivider;

   
    public final View salesDoReserveDivider;

   
    public final View salesPaymentOrderDivider;

   
    public final TextView txtAdditionalInvoiceType;

   
    public final TextView txtBranchHeader;

   
    public final TextView txtEndDate;

   
    public final TextView txtErpInvoiceType;

   
    public final TextView txtErpInvoiceTypeHeader;

   
    public final TextView txtInsteadOfEDespatch;

   
    public final TextView txtSalesBranch;

   
    public final TextView txtSalesCabin;

   
    public final TextView txtSalesCustomer;

   
    public final TextView txtSalesDeliveryDate;

   
    public final TextView txtSalesDivision;

   
    public final TextView txtSalesDivisionHeader;

   
    public final TextView txtSalesFactory;

   
    public final TextView txtSalesFactoryHeader;

   
    public final TextView txtSalesReturnWareHouse;

   
    public final TextView txtSalesReturnWarehouse;

   
    public final TextView txtSalesShipAgent;

   
    public final TextView txtSalesSourceWareHouse;

   
    public final TextView txtSalesSourceWarehouseHeader;

   
    public final TextView txtSalesSpeCode;

   
    public final TextView txtSalesSpeCode1;

   
    public final TextView txtSalesSpeCode2;

   
    public final TextView txtSalesWareHouse;

   
    public final TextView txtSalesWarehouseHeader;

   
    public final TextView txtSalesWarrantyCode;

   
    public final TextView txtSerial;

   
    public final TextView txtStartingDate;

    private FragmentSalesHeaderBinding(final LinearLayout linearLayout, final View view, final CheckBox checkBox, final CheckBox checkBox2, final CheckBox checkBox3, final CheckBox checkBox4, final CheckBox checkBox5, final CheckBox checkBox6, final View view2, final View view3, final View view4, final View view5, final View view6, final View view7, final View view8, final View view9, final EditText editText, final EditText editText2, final EditText editText3, final EditText editText4, final EditText editText5, final EditText editText6, final EditText editText7, final EditText editText8, final EditText editText9, final EditText editText10, final EditText editText11, final EditText editText12, final EditText editText13, final EditText editText14, final EditText editText15, final EditText editText16, final ImageView imageView, final View view10, final LinearLayout linearLayout2, final View view11, final LinearLayout linearLayout3, final View view12, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final View view13, final LinearLayout linearLayout6, final LinearLayout linearLayout7, final LinearLayout linearLayout8, final LinearLayout linearLayout9, final LinearLayout linearLayout10, final LinearLayout linearLayout11, final LinearLayout linearLayout12, final LinearLayout linearLayout13, final LinearLayout linearLayout14, final LinearLayout linearLayout15, final LinearLayout linearLayout16, final LinearLayout linearLayout17, final LinearLayout linearLayout18, final LinearLayout linearLayout19, final LinearLayout linearLayout20, final LinearLayout linearLayout21, final LinearLayout linearLayout22, final LinearLayout linearLayout23, final LinearLayout linearLayout24, final LinearLayout linearLayout25, final LinearLayout linearLayout26, final LinearLayout linearLayout27, final LinearLayout linearLayout28, final LinearLayout linearLayout29, final LinearLayout linearLayout30, final LinearLayout linearLayout31, final LinearLayout linearLayout32, final LinearLayout linearLayout33, final LinearLayout linearLayout34, final LinearLayout linearLayout35, final LinearLayout linearLayout36, final LinearLayout linearLayout37, final LinearLayout linearLayout38, final LinearLayout linearLayout39, final View view14, final LinearLayout linearLayout40, final LinearLayout linearLayout41, final View view15, final View view16, final View view17, final LinearLayout linearLayout42, final LinearLayout linearLayout43, final View view18, final View view19, final View view20, final View view21, final View view22, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5, final TextView textView6, final TextView textView7, final TextView textView8, final TextView textView9, final TextView textView10, final TextView textView11, final TextView textView12, final TextView textView13, final TextView textView14, final TextView textView15, final TextView textView16, final TextView textView17, final TextView textView18, final TextView textView19, final TextView textView20, final TextView textView21, final TextView textView22, final TextView textView23, final TextView textView24, final TextView textView25, final TextView textView26, final TextView textView27) {
        rootView = linearLayout;
        cabinDivider = view;
        chkEDespatch = checkBox;
        chkInsteadOfEDespatch = checkBox2;
        chkSalesConsignee = checkBox3;
        chkSalesDetailVatInclude = checkBox4;
        chkSalesDoReserve = checkBox5;
        chkSalesPaymentOrder = checkBox6;
        customerDivider = view2;
        dvdErpInvoiceType = view3;
        dvdSerial = view4;
        dvdShipAgent = view5;
        dvdspeCode = view6;
        dvdspeCode1 = view7;
        dvdspeCode2 = view8;
        eDespatchDivider = view9;
        edtDocumentNo = editText;
        edtSalesCustomerOrderNo = editText2;
        edtSalesDocumentNo = editText3;
        edtSalesDocumentTrackingNo = editText4;
        edtSalesExplanation1 = editText5;
        edtSalesExplanation10 = editText6;
        edtSalesExplanation2 = editText7;
        edtSalesExplanation3 = editText8;
        edtSalesExplanation4 = editText9;
        edtSalesExplanation5 = editText10;
        edtSalesExplanation6 = editText11;
        edtSalesExplanation7 = editText12;
        edtSalesExplanation8 = editText13;
        edtSalesExplanation9 = editText14;
        edtTaxpayerCode = editText15;
        edtTaxpayerName = editText16;
        imgSalesCustomerClear = imageView;
        insteadOfEDespatchDivider = view10;
        lnAdditionalInvoiceType = linearLayout2;
        lnAdditionalInvoiceTypeDivider = view11;
        lnDocumentNo = linearLayout3;
        lnDocumentNoDivider = view12;
        lnEDespatch = linearLayout4;
        lnEndDate = linearLayout5;
        lnEndDateDivider = view13;
        lnInsteadOfEDespatch = linearLayout6;
        lnSalesBranch = linearLayout7;
        lnSalesCabin = linearLayout8;
        lnSalesConsignee = linearLayout9;
        lnSalesCustomer = linearLayout10;
        lnSalesCustomerOrderNo = linearLayout11;
        lnSalesDeliveryDate = linearLayout12;
        lnSalesDetailVatInclude = linearLayout13;
        lnSalesDivision = linearLayout14;
        lnSalesDoReserve = linearLayout15;
        lnSalesDocumentNo = linearLayout16;
        lnSalesDocumentTrackingNo = linearLayout17;
        lnSalesErpInoviceType = linearLayout18;
        lnSalesExplanation1 = linearLayout19;
        lnSalesExplanation10 = linearLayout20;
        lnSalesExplanation2 = linearLayout21;
        lnSalesExplanation3 = linearLayout22;
        lnSalesExplanation4 = linearLayout23;
        lnSalesExplanation5 = linearLayout24;
        lnSalesExplanation6 = linearLayout25;
        lnSalesExplanation7 = linearLayout26;
        lnSalesExplanation8 = linearLayout27;
        lnSalesExplanation9 = linearLayout28;
        lnSalesFactory = linearLayout29;
        lnSalesPaymentOrder = linearLayout30;
        lnSalesReturnWareHouse = linearLayout31;
        lnSalesSourceWareHouse = linearLayout32;
        lnSalesSpeCode = linearLayout33;
        lnSalesSpeCode1 = linearLayout34;
        lnSalesSpeCode2 = linearLayout35;
        lnSalesWareHouse = linearLayout36;
        lnSalesWarrantyCode = linearLayout37;
        lnSerial = linearLayout38;
        lnSgkEfaturaBilgileri = linearLayout39;
        lnSgkEinvoiceInformationDivider = view14;
        lnShipAgent = linearLayout40;
        lnStartingDate = linearLayout41;
        lnStartingDateDivider = view15;
        lnTaxPayerCodeDivider = view16;
        lnTaxPayerNameDivider = view17;
        lnTaxpayerCode = linearLayout42;
        lnTaxpayerName = linearLayout43;
        salesCustomerOrderNoDivider = view18;
        salesDeliveryDateDivider = view19;
        salesDetailVatIncludeDivider = view20;
        salesDoReserveDivider = view21;
        salesPaymentOrderDivider = view22;
        txtAdditionalInvoiceType = textView;
        txtBranchHeader = textView2;
        txtEndDate = textView3;
        txtErpInvoiceType = textView4;
        txtErpInvoiceTypeHeader = textView5;
        txtInsteadOfEDespatch = textView6;
        txtSalesBranch = textView7;
        txtSalesCabin = textView8;
        txtSalesCustomer = textView9;
        txtSalesDeliveryDate = textView10;
        txtSalesDivision = textView11;
        txtSalesDivisionHeader = textView12;
        txtSalesFactory = textView13;
        txtSalesFactoryHeader = textView14;
        txtSalesReturnWareHouse = textView15;
        txtSalesReturnWarehouse = textView16;
        txtSalesShipAgent = textView17;
        txtSalesSourceWareHouse = textView18;
        txtSalesSourceWarehouseHeader = textView19;
        txtSalesSpeCode = textView20;
        txtSalesSpeCode1 = textView21;
        txtSalesSpeCode2 = textView22;
        txtSalesWareHouse = textView23;
        txtSalesWarehouseHeader = textView24;
        txtSalesWarrantyCode = textView25;
        txtSerial = textView26;
        txtStartingDate = textView27;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static FragmentSalesHeaderBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentSalesHeaderBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentSalesHeaderBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_sales_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentSalesHeaderBinding.bind(inflate);
    }

   
    public static FragmentSalesHeaderBinding bind(final View view) {
        int i2 = R.id.cabinDivider;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.cabinDivider);
        if (null != findChildViewById) {
            i2 = R.id.chk_eDespatch;
            final CheckBox checkBox = ViewBindings.findChildViewById(view, R.id.chk_eDespatch);
            if (null != checkBox) {
                i2 = R.id.chk_insteadOfEDespatch;
                final CheckBox checkBox2 = ViewBindings.findChildViewById(view, R.id.chk_insteadOfEDespatch);
                if (null != checkBox2) {
                    i2 = R.id.chk_salesConsignee;
                    final CheckBox checkBox3 = ViewBindings.findChildViewById(view, R.id.chk_salesConsignee);
                    if (null != checkBox3) {
                        i2 = R.id.chk_salesDetailVatInclude;
                        final CheckBox checkBox4 = ViewBindings.findChildViewById(view, R.id.chk_salesDetailVatInclude);
                        if (null != checkBox4) {
                            i2 = R.id.chk_salesDoReserve;
                            final CheckBox checkBox5 = ViewBindings.findChildViewById(view, R.id.chk_salesDoReserve);
                            if (null != checkBox5) {
                                i2 = R.id.chk_salesPaymentOrder;
                                final CheckBox checkBox6 = ViewBindings.findChildViewById(view, R.id.chk_salesPaymentOrder);
                                if (null != checkBox6) {
                                    i2 = R.id.customerDivider;
                                    final View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.customerDivider);
                                    if (null != findChildViewById2) {
                                        i2 = R.id.dvdErpInvoiceType;
                                        final View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.dvdErpInvoiceType);
                                        if (null != findChildViewById3) {
                                            i2 = R.id.dvdSerial;
                                            final View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.dvdSerial);
                                            if (null != findChildViewById4) {
                                                i2 = R.id.dvdShipAgent;
                                                final View findChildViewById5 = ViewBindings.findChildViewById(view, R.id.dvdShipAgent);
                                                if (null != findChildViewById5) {
                                                    i2 = R.id.dvdspeCode;
                                                    final View findChildViewById6 = ViewBindings.findChildViewById(view, R.id.dvdspeCode);
                                                    if (null != findChildViewById6) {
                                                        i2 = R.id.dvdspeCode1;
                                                        final View findChildViewById7 = ViewBindings.findChildViewById(view, R.id.dvdspeCode1);
                                                        if (null != findChildViewById7) {
                                                            i2 = R.id.dvdspeCode2;
                                                            final View findChildViewById8 = ViewBindings.findChildViewById(view, R.id.dvdspeCode2);
                                                            if (null != findChildViewById8) {
                                                                i2 = R.id.eDespatchDivider;
                                                                final View findChildViewById9 = ViewBindings.findChildViewById(view, R.id.eDespatchDivider);
                                                                if (null != findChildViewById9) {
                                                                    i2 = R.id.edt_document_no;
                                                                    final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_document_no);
                                                                    if (null != editText) {
                                                                        i2 = R.id.edt_salesCustomerOrderNo;
                                                                        final EditText editText2 = ViewBindings.findChildViewById(view, R.id.edt_salesCustomerOrderNo);
                                                                        if (null != editText2) {
                                                                            i2 = R.id.edt_salesDocumentNo;
                                                                            final EditText editText3 = ViewBindings.findChildViewById(view, R.id.edt_salesDocumentNo);
                                                                            if (null != editText3) {
                                                                                i2 = R.id.edt_salesDocumentTrackingNo;
                                                                                final EditText editText4 = ViewBindings.findChildViewById(view, R.id.edt_salesDocumentTrackingNo);
                                                                                if (null != editText4) {
                                                                                    i2 = R.id.edt_salesExplanation1;
                                                                                    final EditText editText5 = ViewBindings.findChildViewById(view, R.id.edt_salesExplanation1);
                                                                                    if (null != editText5) {
                                                                                        i2 = R.id.edt_salesExplanation10;
                                                                                        final EditText editText6 = ViewBindings.findChildViewById(view, R.id.edt_salesExplanation10);
                                                                                        if (null != editText6) {
                                                                                            i2 = R.id.edt_salesExplanation2;
                                                                                            final EditText editText7 = ViewBindings.findChildViewById(view, R.id.edt_salesExplanation2);
                                                                                            if (null != editText7) {
                                                                                                i2 = R.id.edt_salesExplanation3;
                                                                                                final EditText editText8 = ViewBindings.findChildViewById(view, R.id.edt_salesExplanation3);
                                                                                                if (null != editText8) {
                                                                                                    i2 = R.id.edt_salesExplanation4;
                                                                                                    final EditText editText9 = ViewBindings.findChildViewById(view, R.id.edt_salesExplanation4);
                                                                                                    if (null != editText9) {
                                                                                                        i2 = R.id.edt_salesExplanation5;
                                                                                                        final EditText editText10 = ViewBindings.findChildViewById(view, R.id.edt_salesExplanation5);
                                                                                                        if (null != editText10) {
                                                                                                            i2 = R.id.edt_salesExplanation6;
                                                                                                            final EditText editText11 = ViewBindings.findChildViewById(view, R.id.edt_salesExplanation6);
                                                                                                            if (null != editText11) {
                                                                                                                i2 = R.id.edt_salesExplanation7;
                                                                                                                final EditText editText12 = ViewBindings.findChildViewById(view, R.id.edt_salesExplanation7);
                                                                                                                if (null != editText12) {
                                                                                                                    i2 = R.id.edt_salesExplanation8;
                                                                                                                    final EditText editText13 = ViewBindings.findChildViewById(view, R.id.edt_salesExplanation8);
                                                                                                                    if (null != editText13) {
                                                                                                                        i2 = R.id.edt_salesExplanation9;
                                                                                                                        final EditText editText14 = ViewBindings.findChildViewById(view, R.id.edt_salesExplanation9);
                                                                                                                        if (null != editText14) {
                                                                                                                            i2 = R.id.edt_taxpayer_code;
                                                                                                                            final EditText editText15 = ViewBindings.findChildViewById(view, R.id.edt_taxpayer_code);
                                                                                                                            if (null != editText15) {
                                                                                                                                i2 = R.id.edt_taxpayer_name;
                                                                                                                                final EditText editText16 = ViewBindings.findChildViewById(view, R.id.edt_taxpayer_name);
                                                                                                                                if (null != editText16) {
                                                                                                                                    i2 = R.id.img_salesCustomerClear;
                                                                                                                                    final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_salesCustomerClear);
                                                                                                                                    if (null != imageView) {
                                                                                                                                        i2 = R.id.insteadOfEDespatchDivider;
                                                                                                                                        final View findChildViewById10 = ViewBindings.findChildViewById(view, R.id.insteadOfEDespatchDivider);
                                                                                                                                        if (null != findChildViewById10) {
                                                                                                                                            i2 = R.id.ln_additional_invoice_type;
                                                                                                                                            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_additional_invoice_type);
                                                                                                                                            if (null != linearLayout) {
                                                                                                                                                i2 = R.id.lnAdditionalInvoiceTypeDivider;
                                                                                                                                                final View findChildViewById11 = ViewBindings.findChildViewById(view, R.id.lnAdditionalInvoiceTypeDivider);
                                                                                                                                                if (null != findChildViewById11) {
                                                                                                                                                    i2 = R.id.ln_document_no;
                                                                                                                                                    final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_document_no);
                                                                                                                                                    if (null != linearLayout2) {
                                                                                                                                                        i2 = R.id.lnDocumentNoDivider;
                                                                                                                                                        final View findChildViewById12 = ViewBindings.findChildViewById(view, R.id.lnDocumentNoDivider);
                                                                                                                                                        if (null != findChildViewById12) {
                                                                                                                                                            i2 = R.id.ln_eDespatch;
                                                                                                                                                            final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_eDespatch);
                                                                                                                                                            if (null != linearLayout3) {
                                                                                                                                                                i2 = R.id.ln_end_date;
                                                                                                                                                                final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_end_date);
                                                                                                                                                                if (null != linearLayout4) {
                                                                                                                                                                    i2 = R.id.lnEndDateDivider;
                                                                                                                                                                    final View findChildViewById13 = ViewBindings.findChildViewById(view, R.id.lnEndDateDivider);
                                                                                                                                                                    if (null != findChildViewById13) {
                                                                                                                                                                        i2 = R.id.ln_insteadOfEDespatch;
                                                                                                                                                                        final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_insteadOfEDespatch);
                                                                                                                                                                        if (null != linearLayout5) {
                                                                                                                                                                            i2 = R.id.ln_salesBranch;
                                                                                                                                                                            final LinearLayout linearLayout6 = ViewBindings.findChildViewById(view, R.id.ln_salesBranch);
                                                                                                                                                                            if (null != linearLayout6) {
                                                                                                                                                                                i2 = R.id.ln_salesCabin;
                                                                                                                                                                                final LinearLayout linearLayout7 = ViewBindings.findChildViewById(view, R.id.ln_salesCabin);
                                                                                                                                                                                if (null != linearLayout7) {
                                                                                                                                                                                    i2 = R.id.ln_salesConsignee;
                                                                                                                                                                                    final LinearLayout linearLayout8 = ViewBindings.findChildViewById(view, R.id.ln_salesConsignee);
                                                                                                                                                                                    if (null != linearLayout8) {
                                                                                                                                                                                        i2 = R.id.ln_salesCustomer;
                                                                                                                                                                                        final LinearLayout linearLayout9 = ViewBindings.findChildViewById(view, R.id.ln_salesCustomer);
                                                                                                                                                                                        if (null != linearLayout9) {
                                                                                                                                                                                            i2 = R.id.ln_salesCustomerOrderNo;
                                                                                                                                                                                            final LinearLayout linearLayout10 = ViewBindings.findChildViewById(view, R.id.ln_salesCustomerOrderNo);
                                                                                                                                                                                            if (null != linearLayout10) {
                                                                                                                                                                                                i2 = R.id.ln_salesDeliveryDate;
                                                                                                                                                                                                final LinearLayout linearLayout11 = ViewBindings.findChildViewById(view, R.id.ln_salesDeliveryDate);
                                                                                                                                                                                                if (null != linearLayout11) {
                                                                                                                                                                                                    i2 = R.id.ln_salesDetailVatInclude;
                                                                                                                                                                                                    final LinearLayout linearLayout12 = ViewBindings.findChildViewById(view, R.id.ln_salesDetailVatInclude);
                                                                                                                                                                                                    if (null != linearLayout12) {
                                                                                                                                                                                                        i2 = R.id.ln_salesDivision;
                                                                                                                                                                                                        final LinearLayout linearLayout13 = ViewBindings.findChildViewById(view, R.id.ln_salesDivision);
                                                                                                                                                                                                        if (null != linearLayout13) {
                                                                                                                                                                                                            i2 = R.id.ln_salesDoReserve;
                                                                                                                                                                                                            final LinearLayout linearLayout14 = ViewBindings.findChildViewById(view, R.id.ln_salesDoReserve);
                                                                                                                                                                                                            if (null != linearLayout14) {
                                                                                                                                                                                                                i2 = R.id.ln_salesDocumentNo;
                                                                                                                                                                                                                final LinearLayout linearLayout15 = ViewBindings.findChildViewById(view, R.id.ln_salesDocumentNo);
                                                                                                                                                                                                                if (null != linearLayout15) {
                                                                                                                                                                                                                    i2 = R.id.ln_salesDocumentTrackingNo;
                                                                                                                                                                                                                    final LinearLayout linearLayout16 = ViewBindings.findChildViewById(view, R.id.ln_salesDocumentTrackingNo);
                                                                                                                                                                                                                    if (null != linearLayout16) {
                                                                                                                                                                                                                        i2 = R.id.ln_salesErpInoviceType;
                                                                                                                                                                                                                        final LinearLayout linearLayout17 = ViewBindings.findChildViewById(view, R.id.ln_salesErpInoviceType);
                                                                                                                                                                                                                        if (null != linearLayout17) {
                                                                                                                                                                                                                            i2 = R.id.ln_salesExplanation1;
                                                                                                                                                                                                                            final LinearLayout linearLayout18 = ViewBindings.findChildViewById(view, R.id.ln_salesExplanation1);
                                                                                                                                                                                                                            if (null != linearLayout18) {
                                                                                                                                                                                                                                i2 = R.id.ln_salesExplanation10;
                                                                                                                                                                                                                                final LinearLayout linearLayout19 = ViewBindings.findChildViewById(view, R.id.ln_salesExplanation10);
                                                                                                                                                                                                                                if (null != linearLayout19) {
                                                                                                                                                                                                                                    i2 = R.id.ln_salesExplanation2;
                                                                                                                                                                                                                                    final LinearLayout linearLayout20 = ViewBindings.findChildViewById(view, R.id.ln_salesExplanation2);
                                                                                                                                                                                                                                    if (null != linearLayout20) {
                                                                                                                                                                                                                                        i2 = R.id.ln_salesExplanation3;
                                                                                                                                                                                                                                        final LinearLayout linearLayout21 = ViewBindings.findChildViewById(view, R.id.ln_salesExplanation3);
                                                                                                                                                                                                                                        if (null != linearLayout21) {
                                                                                                                                                                                                                                            i2 = R.id.ln_salesExplanation4;
                                                                                                                                                                                                                                            final LinearLayout linearLayout22 = ViewBindings.findChildViewById(view, R.id.ln_salesExplanation4);
                                                                                                                                                                                                                                            if (null != linearLayout22) {
                                                                                                                                                                                                                                                i2 = R.id.ln_salesExplanation5;
                                                                                                                                                                                                                                                final LinearLayout linearLayout23 = ViewBindings.findChildViewById(view, R.id.ln_salesExplanation5);
                                                                                                                                                                                                                                                if (null != linearLayout23) {
                                                                                                                                                                                                                                                    i2 = R.id.ln_salesExplanation6;
                                                                                                                                                                                                                                                    final LinearLayout linearLayout24 = ViewBindings.findChildViewById(view, R.id.ln_salesExplanation6);
                                                                                                                                                                                                                                                    if (null != linearLayout24) {
                                                                                                                                                                                                                                                        i2 = R.id.ln_salesExplanation7;
                                                                                                                                                                                                                                                        final LinearLayout linearLayout25 = ViewBindings.findChildViewById(view, R.id.ln_salesExplanation7);
                                                                                                                                                                                                                                                        if (null != linearLayout25) {
                                                                                                                                                                                                                                                            i2 = R.id.ln_salesExplanation8;
                                                                                                                                                                                                                                                            final LinearLayout linearLayout26 = ViewBindings.findChildViewById(view, R.id.ln_salesExplanation8);
                                                                                                                                                                                                                                                            if (null != linearLayout26) {
                                                                                                                                                                                                                                                                i2 = R.id.ln_salesExplanation9;
                                                                                                                                                                                                                                                                final LinearLayout linearLayout27 = ViewBindings.findChildViewById(view, R.id.ln_salesExplanation9);
                                                                                                                                                                                                                                                                if (null != linearLayout27) {
                                                                                                                                                                                                                                                                    i2 = R.id.ln_salesFactory;
                                                                                                                                                                                                                                                                    final LinearLayout linearLayout28 = ViewBindings.findChildViewById(view, R.id.ln_salesFactory);
                                                                                                                                                                                                                                                                    if (null != linearLayout28) {
                                                                                                                                                                                                                                                                        i2 = R.id.ln_salesPaymentOrder;
                                                                                                                                                                                                                                                                        final LinearLayout linearLayout29 = ViewBindings.findChildViewById(view, R.id.ln_salesPaymentOrder);
                                                                                                                                                                                                                                                                        if (null != linearLayout29) {
                                                                                                                                                                                                                                                                            i2 = R.id.ln_salesReturnWareHouse;
                                                                                                                                                                                                                                                                            final LinearLayout linearLayout30 = ViewBindings.findChildViewById(view, R.id.ln_salesReturnWareHouse);
                                                                                                                                                                                                                                                                            if (null != linearLayout30) {
                                                                                                                                                                                                                                                                                i2 = R.id.ln_salesSourceWareHouse;
                                                                                                                                                                                                                                                                                final LinearLayout linearLayout31 = ViewBindings.findChildViewById(view, R.id.ln_salesSourceWareHouse);
                                                                                                                                                                                                                                                                                if (null != linearLayout31) {
                                                                                                                                                                                                                                                                                    i2 = R.id.ln_salesSpeCode;
                                                                                                                                                                                                                                                                                    final LinearLayout linearLayout32 = ViewBindings.findChildViewById(view, R.id.ln_salesSpeCode);
                                                                                                                                                                                                                                                                                    if (null != linearLayout32) {
                                                                                                                                                                                                                                                                                        i2 = R.id.ln_salesSpeCode1;
                                                                                                                                                                                                                                                                                        final LinearLayout linearLayout33 = ViewBindings.findChildViewById(view, R.id.ln_salesSpeCode1);
                                                                                                                                                                                                                                                                                        if (null != linearLayout33) {
                                                                                                                                                                                                                                                                                            i2 = R.id.ln_salesSpeCode2;
                                                                                                                                                                                                                                                                                            final LinearLayout linearLayout34 = ViewBindings.findChildViewById(view, R.id.ln_salesSpeCode2);
                                                                                                                                                                                                                                                                                            if (null != linearLayout34) {
                                                                                                                                                                                                                                                                                                i2 = R.id.ln_salesWareHouse;
                                                                                                                                                                                                                                                                                                final LinearLayout linearLayout35 = ViewBindings.findChildViewById(view, R.id.ln_salesWareHouse);
                                                                                                                                                                                                                                                                                                if (null != linearLayout35) {
                                                                                                                                                                                                                                                                                                    i2 = R.id.ln_salesWarrantyCode;
                                                                                                                                                                                                                                                                                                    final LinearLayout linearLayout36 = ViewBindings.findChildViewById(view, R.id.ln_salesWarrantyCode);
                                                                                                                                                                                                                                                                                                    if (null != linearLayout36) {
                                                                                                                                                                                                                                                                                                        i2 = R.id.ln_Serial;
                                                                                                                                                                                                                                                                                                        final LinearLayout linearLayout37 = ViewBindings.findChildViewById(view, R.id.ln_Serial);
                                                                                                                                                                                                                                                                                                        if (null != linearLayout37) {
                                                                                                                                                                                                                                                                                                            i2 = R.id.ln_sgk_efatura_bilgileri;
                                                                                                                                                                                                                                                                                                            final LinearLayout linearLayout38 = ViewBindings.findChildViewById(view, R.id.ln_sgk_efatura_bilgileri);
                                                                                                                                                                                                                                                                                                            if (null != linearLayout38) {
                                                                                                                                                                                                                                                                                                                i2 = R.id.ln_sgk_einvoice_information_divider;
                                                                                                                                                                                                                                                                                                                final View findChildViewById14 = ViewBindings.findChildViewById(view, R.id.ln_sgk_einvoice_information_divider);
                                                                                                                                                                                                                                                                                                                if (null != findChildViewById14) {
                                                                                                                                                                                                                                                                                                                    i2 = R.id.ln_shipAgent;
                                                                                                                                                                                                                                                                                                                    final LinearLayout linearLayout39 = ViewBindings.findChildViewById(view, R.id.ln_shipAgent);
                                                                                                                                                                                                                                                                                                                    if (null != linearLayout39) {
                                                                                                                                                                                                                                                                                                                        i2 = R.id.ln_starting_date;
                                                                                                                                                                                                                                                                                                                        final LinearLayout linearLayout40 = ViewBindings.findChildViewById(view, R.id.ln_starting_date);
                                                                                                                                                                                                                                                                                                                        if (null != linearLayout40) {
                                                                                                                                                                                                                                                                                                                            i2 = R.id.lnStartingDateDivider;
                                                                                                                                                                                                                                                                                                                            final View findChildViewById15 = ViewBindings.findChildViewById(view, R.id.lnStartingDateDivider);
                                                                                                                                                                                                                                                                                                                            if (null != findChildViewById15) {
                                                                                                                                                                                                                                                                                                                                i2 = R.id.lnTaxPayerCodeDivider;
                                                                                                                                                                                                                                                                                                                                final View findChildViewById16 = ViewBindings.findChildViewById(view, R.id.lnTaxPayerCodeDivider);
                                                                                                                                                                                                                                                                                                                                if (null != findChildViewById16) {
                                                                                                                                                                                                                                                                                                                                    i2 = R.id.lnTaxPayerNameDivider;
                                                                                                                                                                                                                                                                                                                                    final View findChildViewById17 = ViewBindings.findChildViewById(view, R.id.lnTaxPayerNameDivider);
                                                                                                                                                                                                                                                                                                                                    if (null != findChildViewById17) {
                                                                                                                                                                                                                                                                                                                                        i2 = R.id.ln_taxpayer_code;
                                                                                                                                                                                                                                                                                                                                        final LinearLayout linearLayout41 = ViewBindings.findChildViewById(view, R.id.ln_taxpayer_code);
                                                                                                                                                                                                                                                                                                                                        if (null != linearLayout41) {
                                                                                                                                                                                                                                                                                                                                            i2 = R.id.ln_taxpayer_name;
                                                                                                                                                                                                                                                                                                                                            final LinearLayout linearLayout42 = ViewBindings.findChildViewById(view, R.id.ln_taxpayer_name);
                                                                                                                                                                                                                                                                                                                                            if (null != linearLayout42) {
                                                                                                                                                                                                                                                                                                                                                i2 = R.id.salesCustomerOrderNoDivider;
                                                                                                                                                                                                                                                                                                                                                final View findChildViewById18 = ViewBindings.findChildViewById(view, R.id.salesCustomerOrderNoDivider);
                                                                                                                                                                                                                                                                                                                                                if (null != findChildViewById18) {
                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.salesDeliveryDateDivider;
                                                                                                                                                                                                                                                                                                                                                    final View findChildViewById19 = ViewBindings.findChildViewById(view, R.id.salesDeliveryDateDivider);
                                                                                                                                                                                                                                                                                                                                                    if (null != findChildViewById19) {
                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.salesDetailVatIncludeDivider;
                                                                                                                                                                                                                                                                                                                                                        final View findChildViewById20 = ViewBindings.findChildViewById(view, R.id.salesDetailVatIncludeDivider);
                                                                                                                                                                                                                                                                                                                                                        if (null != findChildViewById20) {
                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.salesDoReserveDivider;
                                                                                                                                                                                                                                                                                                                                                            final View findChildViewById21 = ViewBindings.findChildViewById(view, R.id.salesDoReserveDivider);
                                                                                                                                                                                                                                                                                                                                                            if (null != findChildViewById21) {
                                                                                                                                                                                                                                                                                                                                                                i2 = R.id.salesPaymentOrderDivider;
                                                                                                                                                                                                                                                                                                                                                                final View findChildViewById22 = ViewBindings.findChildViewById(view, R.id.salesPaymentOrderDivider);
                                                                                                                                                                                                                                                                                                                                                                if (null != findChildViewById22) {
                                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txt_additional_invoice_type;
                                                                                                                                                                                                                                                                                                                                                                    final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_additional_invoice_type);
                                                                                                                                                                                                                                                                                                                                                                    if (null != textView) {
                                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txtBranchHeader;
                                                                                                                                                                                                                                                                                                                                                                        final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txtBranchHeader);
                                                                                                                                                                                                                                                                                                                                                                        if (null != textView2) {
                                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txt_end_date;
                                                                                                                                                                                                                                                                                                                                                                            final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_end_date);
                                                                                                                                                                                                                                                                                                                                                                            if (null != textView3) {
                                                                                                                                                                                                                                                                                                                                                                                i2 = R.id.txt_erp_invoice_type;
                                                                                                                                                                                                                                                                                                                                                                                final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_erp_invoice_type);
                                                                                                                                                                                                                                                                                                                                                                                if (null != textView4) {
                                                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txtErpInvoiceTypeHeader;
                                                                                                                                                                                                                                                                                                                                                                                    final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txtErpInvoiceTypeHeader);
                                                                                                                                                                                                                                                                                                                                                                                    if (null != textView5) {
                                                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txtInsteadOfEDespatch;
                                                                                                                                                                                                                                                                                                                                                                                        final TextView textView6 = ViewBindings.findChildViewById(view, R.id.txtInsteadOfEDespatch);
                                                                                                                                                                                                                                                                                                                                                                                        if (null != textView6) {
                                                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txt_salesBranch;
                                                                                                                                                                                                                                                                                                                                                                                            final TextView textView7 = ViewBindings.findChildViewById(view, R.id.txt_salesBranch);
                                                                                                                                                                                                                                                                                                                                                                                            if (null != textView7) {
                                                                                                                                                                                                                                                                                                                                                                                                i2 = R.id.txt_salesCabin;
                                                                                                                                                                                                                                                                                                                                                                                                final TextView textView8 = ViewBindings.findChildViewById(view, R.id.txt_salesCabin);
                                                                                                                                                                                                                                                                                                                                                                                                if (null != textView8) {
                                                                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txt_salesCustomer;
                                                                                                                                                                                                                                                                                                                                                                                                    final TextView textView9 = ViewBindings.findChildViewById(view, R.id.txt_salesCustomer);
                                                                                                                                                                                                                                                                                                                                                                                                    if (null != textView9) {
                                                                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txt_salesDeliveryDate;
                                                                                                                                                                                                                                                                                                                                                                                                        final TextView textView10 = ViewBindings.findChildViewById(view, R.id.txt_salesDeliveryDate);
                                                                                                                                                                                                                                                                                                                                                                                                        if (null != textView10) {
                                                                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txt_salesDivision;
                                                                                                                                                                                                                                                                                                                                                                                                            final TextView textView11 = ViewBindings.findChildViewById(view, R.id.txt_salesDivision);
                                                                                                                                                                                                                                                                                                                                                                                                            if (null != textView11) {
                                                                                                                                                                                                                                                                                                                                                                                                                i2 = R.id.txtSalesDivisionHeader;
                                                                                                                                                                                                                                                                                                                                                                                                                final TextView textView12 = ViewBindings.findChildViewById(view, R.id.txtSalesDivisionHeader);
                                                                                                                                                                                                                                                                                                                                                                                                                if (null != textView12) {
                                                                                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txt_salesFactory;
                                                                                                                                                                                                                                                                                                                                                                                                                    final TextView textView13 = ViewBindings.findChildViewById(view, R.id.txt_salesFactory);
                                                                                                                                                                                                                                                                                                                                                                                                                    if (null != textView13) {
                                                                                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txtSalesFactoryHeader;
                                                                                                                                                                                                                                                                                                                                                                                                                        final TextView textView14 = ViewBindings.findChildViewById(view, R.id.txtSalesFactoryHeader);
                                                                                                                                                                                                                                                                                                                                                                                                                        if (null != textView14) {
                                                                                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txt_salesReturnWareHouse;
                                                                                                                                                                                                                                                                                                                                                                                                                            final TextView textView15 = ViewBindings.findChildViewById(view, R.id.txt_salesReturnWareHouse);
                                                                                                                                                                                                                                                                                                                                                                                                                            if (null != textView15) {
                                                                                                                                                                                                                                                                                                                                                                                                                                i2 = R.id.txtSalesReturnWarehouse;
                                                                                                                                                                                                                                                                                                                                                                                                                                final TextView textView16 = ViewBindings.findChildViewById(view, R.id.txtSalesReturnWarehouse);
                                                                                                                                                                                                                                                                                                                                                                                                                                if (null != textView16) {
                                                                                                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txt_salesShipAgent;
                                                                                                                                                                                                                                                                                                                                                                                                                                    final TextView textView17 = ViewBindings.findChildViewById(view, R.id.txt_salesShipAgent);
                                                                                                                                                                                                                                                                                                                                                                                                                                    if (null != textView17) {
                                                                                                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txt_salesSourceWareHouse;
                                                                                                                                                                                                                                                                                                                                                                                                                                        final TextView textView18 = ViewBindings.findChildViewById(view, R.id.txt_salesSourceWareHouse);
                                                                                                                                                                                                                                                                                                                                                                                                                                        if (null != textView18) {
                                                                                                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txtSalesSourceWarehouseHeader;
                                                                                                                                                                                                                                                                                                                                                                                                                                            final TextView textView19 = ViewBindings.findChildViewById(view, R.id.txtSalesSourceWarehouseHeader);
                                                                                                                                                                                                                                                                                                                                                                                                                                            if (null != textView19) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                i2 = R.id.txt_salesSpeCode;
                                                                                                                                                                                                                                                                                                                                                                                                                                                final TextView textView20 = ViewBindings.findChildViewById(view, R.id.txt_salesSpeCode);
                                                                                                                                                                                                                                                                                                                                                                                                                                                if (null != textView20) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txt_salesSpeCode1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                    final TextView textView21 = ViewBindings.findChildViewById(view, R.id.txt_salesSpeCode1);
                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (null != textView21) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txt_salesSpeCode2;
                                                                                                                                                                                                                                                                                                                                                                                                                                                        final TextView textView22 = ViewBindings.findChildViewById(view, R.id.txt_salesSpeCode2);
                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (null != textView22) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txt_salesWareHouse;
                                                                                                                                                                                                                                                                                                                                                                                                                                                            final TextView textView23 = ViewBindings.findChildViewById(view, R.id.txt_salesWareHouse);
                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (null != textView23) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                i2 = R.id.txtSalesWarehouseHeader;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                final TextView textView24 = ViewBindings.findChildViewById(view, R.id.txtSalesWarehouseHeader);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (null != textView24) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                    i2 = R.id.txt_salesWarrantyCode;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                    final TextView textView25 = ViewBindings.findChildViewById(view, R.id.txt_salesWarrantyCode);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (null != textView25) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        i2 = R.id.txt_Serial;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        final TextView textView26 = ViewBindings.findChildViewById(view, R.id.txt_Serial);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (null != textView26) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                            i2 = R.id.txt_starting_date;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                            final TextView textView27 = ViewBindings.findChildViewById(view, R.id.txt_starting_date);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (null != textView27) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                return new FragmentSalesHeaderBinding((LinearLayout) view, findChildViewById, checkBox, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, findChildViewById2, findChildViewById3, findChildViewById4, findChildViewById5, findChildViewById6, findChildViewById7, findChildViewById8, findChildViewById9, editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9, editText10, editText11, editText12, editText13, editText14, editText15, editText16, imageView, findChildViewById10, linearLayout, findChildViewById11, linearLayout2, findChildViewById12, linearLayout3, linearLayout4, findChildViewById13, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12, linearLayout13, linearLayout14, linearLayout15, linearLayout16, linearLayout17, linearLayout18, linearLayout19, linearLayout20, linearLayout21, linearLayout22, linearLayout23, linearLayout24, linearLayout25, linearLayout26, linearLayout27, linearLayout28, linearLayout29, linearLayout30, linearLayout31, linearLayout32, linearLayout33, linearLayout34, linearLayout35, linearLayout36, linearLayout37, linearLayout38, findChildViewById14, linearLayout39, linearLayout40, findChildViewById15, findChildViewById16, findChildViewById17, linearLayout41, linearLayout42, findChildViewById18, findChildViewById19, findChildViewById20, findChildViewById21, findChildViewById22, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21, textView22, textView23, textView24, textView25, textView26, textView27);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
