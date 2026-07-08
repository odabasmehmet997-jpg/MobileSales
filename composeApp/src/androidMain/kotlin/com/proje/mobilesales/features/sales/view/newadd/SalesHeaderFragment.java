package com.proje.mobilesales.features.sales.view.newadd;

import android.R;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.StringRes;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseFicheFragment;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseSalesFragment;
import com.proje.mobilesales.core.enums.ErpInvoiceType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.IFichePropChangeListener;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.databinding.FragmentSalesHeaderBinding;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.view.list.CustomerListActivity;
import com.proje.mobilesales.features.dbmodel.Branch;
import com.proje.mobilesales.features.dbmodel.Specodes;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.model.EDocInfoModel;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDateProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.SalesFicheUserRights;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheHeaderFields;
import com.proje.mobilesales.features.sales.repository.SalesNewRepository;
import com.proje.mobilesales.features.sales.utils.SalesHeaderWarehouseUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


public final class SalesHeaderFragment extends BaseSalesFragment {
    public static final int CUSTOMER_CODE = 999;
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_CUSTOMER_REF = BaseSalesFragment.class.getName() + ".EXTRA_CUSTOMER_REF";
    private FragmentSalesHeaderBinding _binding;
    private ArrayList<String> allErpInvoiceTypes;
    private ArrayList<String> availableErpInvoiceTypes;
    private boolean branchEArchieve;
    private int branchEArchieveType;
    private boolean branchEDespatch;
    private boolean branchEInvoice;
    private View cabinDivider;
    private CheckBox chkEdespatch;
    private CheckBox chkInsteadOfEdespatch;
    private CheckBox chkPaymentOrder;
    private CheckBox chkSalesConsignee;
    private CheckBox chkSalesDetailIncludeVat;
    private CheckBox chkSalesDoReserve;
    private View customerDivider;
    private View dvdErpInvoiceType;
    private View dvdSerial;
    private View dvdShipAgent;
    private View dvdSpeCode;
    private View dvdSpeCode1;
    private View dvdSpeCode2;
    private View eDespatchDivider;
    private EditText edtCustomerOrderNo;
    private EditText edtDocumentNo;
    private EditText edtDocumentTrackNo;
    private EditText edtExplanation1;
    private EditText edtExplanation10;
    private EditText edtExplanation2;
    private EditText edtExplanation3;
    private EditText edtExplanation4;
    private EditText edtExplanation5;
    private EditText edtExplanation6;
    private EditText edtExplanation7;
    private EditText edtExplanation8;
    private EditText edtExplanation9;
    private EditText edt_document_no;
    private EditText edt_taxpayer_code;
    private EditText edt_taxpayer_name;
    private boolean firmUseEDespatch;
    private boolean hasOrderReference;
    private ImageView imgClearCustomer;
    private View insteadOfDEdespatchDivider;
    private boolean insteadOfEDespatch;
    private View lnAdditionalInvoiceTypeDivider;
    private LinearLayout lnBranch;
    private LinearLayout lnCustomer;
    private LinearLayout lnCustomerOrderNo;
    private LinearLayout lnDeliveryDate;
    private LinearLayout lnDivision;
    private LinearLayout lnDocumentNo;
    private View lnDocumentNoDivider;
    private LinearLayout lnDocumentTrackNo;
    private View lnEdespatch;
    private View lnEndDateDivider;
    private LinearLayout lnErpInvoiceType;
    private LinearLayout lnExplanation1;
    private LinearLayout lnExplanation10;
    private LinearLayout lnExplanation2;
    private LinearLayout lnExplanation3;
    private LinearLayout lnExplanation4;
    private LinearLayout lnExplanation5;
    private LinearLayout lnExplanation6;
    private LinearLayout lnExplanation7;
    private LinearLayout lnExplanation8;
    private LinearLayout lnExplanation9;
    private LinearLayout lnFactory;
    private View lnInsteadOfEdespatch;
    private LinearLayout lnPaymentOrder;
    private LinearLayout lnReturnWareHouse;
    private LinearLayout lnSalesConsignee;
    private LinearLayout lnSalesDetailIncludeVat;
    private LinearLayout lnSalesDoReserve;
    private View lnSerial;
    private LinearLayout lnShipAgent;
    private LinearLayout lnSourceWareHouse;
    private LinearLayout lnSpeCode;
    private LinearLayout lnSpeCode1;
    private LinearLayout lnSpeCode2;
    private View lnStartingDateDivider;
    private View lnTaxPayerCodeDivider;
    private View lnTaxPayerNameDivider;
    private LinearLayout lnWareHouse;
    private LinearLayout lnWarrantyCode;
    private LinearLayout ln_additional_invoice_type;
    private LinearLayout ln_cabin;
    private LinearLayout ln_document_no;
    private LinearLayout ln_end_date;
    private LinearLayout ln_sgk_efatura_bilgileri;
    private View ln_sgk_einvoice_information_divider;
    private LinearLayout ln_starting_date;
    private LinearLayout ln_taxpayer_code;
    private LinearLayout ln_taxpayer_name;
    private boolean mAcceptEArchive;
    private boolean mAcceptEInvoice;
    public AlertDialogBuilder<?> mAlertDialogBuilderB;
    public AlertDialogBuilder<?> mAlertDialogBuilderB2;
    public AlertDialogBuilder<?> mAlertDialogBuilderB3;
    private IVatChangeListener mCallback;
    private EDocInfoModel mEDocInfoModel;
    private final SalesNewRepository repository;
    private View salesCustomerOrderNoDivider;
    private View salesDeliveryDateDivider;
    private View salesDetailVatIncludeDivider;
    private View salesDoReserveDivider;
    private View salesPaymentOrderDivider;
    private TextView txtBranch;
    private TextView txtBranchHeader;
    private TextView txtCustomer;
    private TextView txtDeliveryDate;
    private TextView txtDivision;
    private TextView txtErpInvoiceType;
    private TextView txtFactory;
    private TextView txtReturnWareHouse;
    private TextView txtSalesDivisionHeader;
    private TextView txtSalesFactoryHeader;
    private TextView txtSalesWarehouseHeader;
    private TextView txtSerial;
    private TextView txtShipAgent;
    private TextView txtSourceWareHouse;
    private TextView txtSpeCode;
    private TextView txtSpeCode1;
    private TextView txtSpeCode2;
    private TextView txtWareHouse;
    private TextView txtWarrantyCode;
    private TextView txt_additional_invoice_type;
    private TextView txt_cabin;
    private TextView txt_end_date;
    private TextView txt_starting_date;
    private final boolean useSerial;
    private final SalesNewViewModel viewModel;

    /* compiled from: SalesHeaderFragment.kt */
    public interface IVatChangeListener {
        void onHeaderVatChange();
    }

    public SalesHeaderFragment() {
        SalesNewRepository salesNewRepository = new SalesNewRepository();
        this.repository = salesNewRepository;
        this.viewModel = new SalesNewViewModel(salesNewRepository);
        this.allErpInvoiceTypes = new ArrayList<>();
        this.availableErpInvoiceTypes = new ArrayList<>();
        this.mEDocInfoModel = new EDocInfoModel();
    }

    private FragmentSalesHeaderBinding getBinding() {
        FragmentSalesHeaderBinding fragmentSalesHeaderBinding = this._binding;
        Intrinsics.checkNotNull(fragmentSalesHeaderBinding);
        return fragmentSalesHeaderBinding;
    }

    public EditText getEdtDocumentNo() {
        return this.edtDocumentNo;
    }

    public void setEdtDocumentNo(EditText editText) {
        this.edtDocumentNo = editText;
    }

    public IVatChangeListener getMCallback() {
        return this.mCallback;
    }

    public void setMCallback(IVatChangeListener iVatChangeListener) {
        this.mCallback = iVatChangeListener;
    }

    @Override // com.proje.mobilesales.core.base.BaseFicheFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Context contextRequireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilderB = new AlertDialogBuilder.Impl(contextRequireContext, (BaseInjectableActivity) activity);
        Context contextRequireContext2 = requireContext();
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilderB2 = new AlertDialogBuilder.Impl(contextRequireContext2, (BaseInjectableActivity) activity2);
        Context contextRequireContext3 = requireContext();
        Activity activity3 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilderB3 = new AlertDialogBuilder.Impl(contextRequireContext3, (BaseInjectableActivity) activity3);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentSalesHeaderBinding.inflate(inflater, viewGroup, false);
        this.lnSourceWareHouse = getBinding().lnSalesSourceWareHouse;
        this.txtSourceWareHouse = getBinding().txtSalesSourceWareHouse;
        this.lnBranch = getBinding().lnSalesBranch;
        this.txtBranch = getBinding().txtSalesBranch;
        this.lnDivision = getBinding().lnSalesDivision;
        this.txtDivision = getBinding().txtSalesDivision;
        this.lnFactory = getBinding().lnSalesFactory;
        this.txtFactory = getBinding().txtSalesFactory;
        this.lnWareHouse = getBinding().lnSalesWareHouse;
        this.txtWareHouse = getBinding().txtSalesWareHouse;
        this.lnReturnWareHouse = getBinding().lnSalesReturnWareHouse;
        this.txtReturnWareHouse = getBinding().txtSalesReturnWareHouse;
        this.lnSpeCode = getBinding().lnSalesSpeCode;
        this.txtSpeCode = getBinding().txtSalesSpeCode;
        this.lnSpeCode1 = getBinding().lnSalesSpeCode1;
        this.dvdSpeCode1 = getBinding().dvdspeCode1;
        this.txtSpeCode1 = getBinding().txtSalesSpeCode1;
        this.lnSpeCode2 = getBinding().lnSalesSpeCode2;
        this.txtSpeCode2 = getBinding().txtSalesSpeCode2;
        this.dvdSpeCode2 = getBinding().dvdspeCode2;
        this.dvdSpeCode = getBinding().dvdspeCode;
        this.lnWarrantyCode = getBinding().lnSalesWarrantyCode;
        this.txtWarrantyCode = getBinding().txtSalesWarrantyCode;
        this.lnDocumentNo = getBinding().lnSalesDocumentNo;
        this.edtDocumentNo = getBinding().edtSalesDocumentNo;
        this.lnDocumentTrackNo = getBinding().lnSalesDocumentTrackingNo;
        this.edtDocumentTrackNo = getBinding().edtSalesDocumentTrackingNo;
        this.lnCustomerOrderNo = getBinding().lnSalesCustomerOrderNo;
        this.edtCustomerOrderNo = getBinding().edtSalesCustomerOrderNo;
        this.lnDeliveryDate = getBinding().lnSalesDeliveryDate;
        this.txtDeliveryDate = getBinding().txtSalesDeliveryDate;
        this.lnPaymentOrder = getBinding().lnSalesPaymentOrder;
        this.chkPaymentOrder = getBinding().chkSalesPaymentOrder;
        this.lnExplanation1 = getBinding().lnSalesExplanation1;
        this.edtExplanation1 = getBinding().edtSalesExplanation1;
        this.lnExplanation2 = getBinding().lnSalesExplanation2;
        this.edtExplanation2 = getBinding().edtSalesExplanation2;
        this.lnExplanation3 = getBinding().lnSalesExplanation3;
        this.edtExplanation3 = getBinding().edtSalesExplanation3;
        this.lnExplanation4 = getBinding().lnSalesExplanation4;
        this.edtExplanation4 = getBinding().edtSalesExplanation4;
        this.lnExplanation5 = getBinding().lnSalesExplanation5;
        this.edtExplanation5 = getBinding().edtSalesExplanation5;
        this.lnExplanation6 = getBinding().lnSalesExplanation6;
        this.edtExplanation6 = getBinding().edtSalesExplanation6;
        this.lnExplanation7 = getBinding().lnSalesExplanation7;
        this.edtExplanation7 = getBinding().edtSalesExplanation7;
        this.lnExplanation8 = getBinding().lnSalesExplanation8;
        this.edtExplanation8 = getBinding().edtSalesExplanation8;
        this.lnExplanation9 = getBinding().lnSalesExplanation9;
        this.edtExplanation9 = getBinding().edtSalesExplanation9;
        this.lnExplanation10 = getBinding().lnSalesExplanation10;
        this.edtExplanation10 = getBinding().edtSalesExplanation10;
        this.lnSalesConsignee = getBinding().lnSalesConsignee;
        this.chkSalesConsignee = getBinding().chkSalesConsignee;
        this.lnSalesDetailIncludeVat = getBinding().lnSalesDetailVatInclude;
        this.chkSalesDetailIncludeVat = getBinding().chkSalesDetailVatInclude;
        this.ln_sgk_efatura_bilgileri = getBinding().lnSgkEfaturaBilgileri;
        this.ln_sgk_einvoice_information_divider = getBinding().lnSgkEinvoiceInformationDivider;
        this.ln_additional_invoice_type = getBinding().lnAdditionalInvoiceType;
        this.txt_additional_invoice_type = getBinding().txtAdditionalInvoiceType;
        this.ln_taxpayer_code = getBinding().lnTaxpayerCode;
        this.edt_taxpayer_code = getBinding().edtTaxpayerCode;
        this.ln_taxpayer_name = getBinding().lnTaxpayerName;
        this.edt_taxpayer_name = getBinding().edtTaxpayerName;
        this.ln_document_no = getBinding().lnDocumentNo;
        this.edt_document_no = getBinding().edtDocumentNo;
        this.ln_starting_date = getBinding().lnStartingDate;
        this.txt_starting_date = getBinding().txtStartingDate;
        this.ln_end_date = getBinding().lnEndDate;
        this.txt_end_date = getBinding().txtEndDate;
        this.ln_cabin = getBinding().lnSalesCabin;
        this.txt_cabin = getBinding().txtSalesCabin;
        this.lnDocumentNoDivider = getBinding().lnDocumentNoDivider;
        this.lnStartingDateDivider = getBinding().lnStartingDateDivider;
        this.lnTaxPayerNameDivider = getBinding().lnTaxPayerNameDivider;
        this.lnTaxPayerCodeDivider = getBinding().lnTaxPayerCodeDivider;
        this.lnAdditionalInvoiceTypeDivider = getBinding().lnAdditionalInvoiceTypeDivider;
        this.lnEndDateDivider = getBinding().lnEndDateDivider;
        this.salesPaymentOrderDivider = getBinding().salesPaymentOrderDivider;
        this.salesDetailVatIncludeDivider = getBinding().salesDetailVatIncludeDivider;
        this.salesDeliveryDateDivider = getBinding().salesDeliveryDateDivider;
        this.salesCustomerOrderNoDivider = getBinding().salesCustomerOrderNoDivider;
        this.cabinDivider = getBinding().cabinDivider;
        this.txtSalesWarehouseHeader = getBinding().txtSalesWarehouseHeader;
        this.txtSalesFactoryHeader = getBinding().txtSalesFactoryHeader;
        this.txtSalesDivisionHeader = getBinding().txtSalesDivisionHeader;
        this.txtBranchHeader = getBinding().txtBranchHeader;
        this.lnSerial = getBinding().lnSerial;
        this.txtSerial = getBinding().txtSerial;
        this.dvdSerial = getBinding().dvdSerial;
        this.lnInsteadOfEdespatch = getBinding().lnInsteadOfEDespatch;
        this.chkInsteadOfEdespatch = getBinding().chkInsteadOfEDespatch;
        this.insteadOfDEdespatchDivider = getBinding().insteadOfEDespatchDivider;
        this.lnEdespatch = getBinding().lnEDespatch;
        this.chkEdespatch = getBinding().chkEDespatch;
        this.eDespatchDivider = getBinding().eDespatchDivider;
        this.lnCustomer = getBinding().lnSalesCustomer;
        this.txtCustomer = getBinding().txtSalesCustomer;
        this.imgClearCustomer = getBinding().imgSalesCustomerClear;
        this.customerDivider = getBinding().customerDivider;
        this.lnShipAgent = getBinding().lnShipAgent;
        this.txtShipAgent = getBinding().txtSalesShipAgent;
        this.dvdShipAgent = getBinding().dvdShipAgent;
        this.lnErpInvoiceType = getBinding().lnSalesErpInoviceType;
        this.txtErpInvoiceType = getBinding().txtErpInvoiceType;
        this.dvdErpInvoiceType = getBinding().dvdErpInvoiceType;
        this.lnSalesDoReserve = getBinding().lnSalesDoReserve;
        this.chkSalesDoReserve = getBinding().chkSalesDoReserve;
        this.salesDoReserveDivider = getBinding().salesDoReserveDivider;
        LinearLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public void onViewCreated(View view, Bundle bundle) {
        int logicalRef;
        int r15;
        String[] strArr;
        String[] strArr2;
        boolean z;
        String[] strArr3;
        boolean z2;
        boolean z3;
        boolean z4;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.allErpInvoiceTypes = new ArrayList<String>(this) { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragment.onViewCreated.1
            {
                add(this.getString(R.string.str_paper_invoice_type_for_erp));
                add(this.getString(R.string.str_eInvoice_invoice_type_for_erp));
                add(this.getString(R.string.str_eArchiveInvoice_invoice_type_for_erp));
                add(this.getString(R.string.str_eArchiveInternetInvoice_invoice_type_for_erp));
            }

            @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
            public boolean contains(Object obj) {
                if (obj instanceof String) {
                    return contains((String) obj);
                }
                return false;
            }

            public boolean contains(String str) {
                return super.contains(str);
            }

            public int getSize() {
                return super.size();
            }

            @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
            public int indexOf(Object obj) {
                if (obj instanceof String) {
                    return indexOf((String) obj);
                }
                return -1;
            }

            public int indexOf(String str) {
                return super.indexOf(str);
            }

            @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
            public int lastIndexOf(Object obj) {
                if (obj instanceof String) {
                    return lastIndexOf((String) obj);
                }
                return -1;
            }

            public int lastIndexOf(String str) {
                return super.lastIndexOf(str);
            }

            @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
            public String remove(int r1) {
                return removeAt(r1);
            }

            @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
            public boolean remove(Object obj) {
                if (obj instanceof String) {
                    return remove((String) obj);
                }
                return false;
            }

            public boolean remove(String str) {
                return super.remove(str);
            }

            public String removeAt(int r1) {
                return super.remove(r1);
            }

            @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return getSize();
            }
        };
        this.availableErpInvoiceTypes = getAvailableErpInvoiceTypes();
        SalesNewViewModel salesNewViewModel = this.viewModel;
        int r1 = this.mCustomerRef;
        ErpType erpType = salesNewViewModel.erpType();
        ErpType erpType2 = ErpType.NETSIS;
        if (erpType == erpType2) {
            logicalRef = -1;
        } else {
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            logicalRef = sales.getBranch().getLogicalRef();
        }
        this.mEDocInfoModel = salesNewViewModel.getEDocInfo(r1, logicalRef);
        if (this.viewModel.erpType() == erpType2) {
            SalesType salesType = getSalesType();
            SalesType salesType2 = SalesType.INVOICE;
            this.mAcceptEInvoice = salesType == salesType2 && this.mEDocInfoModel.isAcceptEInvoice();
            this.mAcceptEArchive = getSalesType() == salesType2 && this.mEDocInfoModel.isAcceptEArchive();
            this.firmUseEDespatch = getSalesType() == SalesType.DISPATCH && this.mEDocInfoModel.isAcceptEDespatch();
        } else {
            this.firmUseEDespatch = getSalesType() == SalesType.WHTRANSFER && this.mEDocInfoModel.isAcceptEDespatch();
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            if (SalesUtils.isSalesTypeOrderOrInvoiceOrRetailInvoice(sales2.getmSalesType())) {
                Sales sales3 = getmSales();
                Intrinsics.checkNotNull(sales3);
                this.insteadOfEDespatch = sales3.getInsteadOfEDespatch().isSelect();
            }
        }
        Sales sales4 = getmSales();
        Intrinsics.checkNotNull(sales4);
        this.hasOrderReference = sales4.hasOrderReference();
        if (this.viewModel.erpType() != erpType2) {
            Sales sales5 = getmSales();
            Intrinsics.checkNotNull(sales5);
            sales5.getBranch().setPropChangeListener(new IFichePropChangeListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda20
                @Override // com.proje.mobilesales.core.interfaces.IFichePropChangeListener
                public void onChange() throws InterruptedException {
                    SalesHeaderFragment.onViewCreatedlambda0(this.f0);
                }
            });
        }
        FicheMode salesFicheMode = this.hasOrderReference ? FicheMode.ANALYSE : getSalesFicheMode();
        Intrinsics.checkNotNull(salesFicheMode);
        LinearLayout linearLayout = this.lnBranch;
        TextView textView = this.txtBranch;
        Sales sales6 = getmSales();
        Intrinsics.checkNotNull(sales6);
        FicheRefProp branch = sales6.getBranch();
        Sales sales7 = getmSales();
        Intrinsics.checkNotNull(sales7);
        FicheRefProp factory = sales7.getFactory();
        TextView textView2 = this.txtFactory;
        Sales sales8 = getmSales();
        Intrinsics.checkNotNull(sales8);
        FicheRefProp wareHouse = sales8.getWareHouse();
        TextView textView3 = this.txtWareHouse;
        Sales sales9 = getmSales();
        FicheRefProp returnWareHouse = sales9 != null ? sales9.getReturnWareHouse() : null;
        TextView textView4 = this.txtReturnWareHouse;
        SalesFicheHeaderFields salesFicheHeaderFields = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields);
        createSingleAlertCursorSalesBranch(salesFicheMode, linearLayout, textView, branch, factory, textView2, wareHouse, textView3, returnWareHouse, textView4, salesFicheHeaderFields.isBranch(), R.string.str_department, -1, R.string.qry_branch, R.string.column_code, -1, true);
        FicheMode salesFicheMode2 = this.hasOrderReference ? FicheMode.ANALYSE : getSalesFicheMode();
        LinearLayout linearLayout2 = this.lnDivision;
        TextView textView5 = this.txtDivision;
        Sales sales10 = getmSales();
        Intrinsics.checkNotNull(sales10);
        FicheRefProp division = sales10.getDivision();
        SalesFicheHeaderFields salesFicheHeaderFields2 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields2);
        createSingleAlertCursorSales(salesFicheMode2, linearLayout2, textView5, division, salesFicheHeaderFields2.isDivision(), R.string.str_department, R.string.qry_division, R.string.column_code);
        FicheMode salesFicheMode3 = this.hasOrderReference ? FicheMode.ANALYSE : getSalesFicheMode();
        Intrinsics.checkNotNull(salesFicheMode3);
        LinearLayout linearLayout3 = this.lnFactory;
        TextView textView6 = this.txtFactory;
        Sales sales11 = getmSales();
        Intrinsics.checkNotNull(sales11);
        FicheRefProp factory2 = sales11.getFactory();
        Sales sales12 = getmSales();
        Intrinsics.checkNotNull(sales12);
        FicheRefProp wareHouse2 = sales12.getWareHouse();
        TextView textView7 = this.txtWareHouse;
        Sales sales13 = getmSales();
        FicheRefProp returnWareHouse2 = sales13 != null ? sales13.getReturnWareHouse() : null;
        TextView textView8 = this.txtReturnWareHouse;
        SalesFicheHeaderFields salesFicheHeaderFields3 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields3);
        createSingleAlertCursorSalesFactory(salesFicheMode3, linearLayout3, textView6, factory2, wareHouse2, textView7, returnWareHouse2, textView8, salesFicheHeaderFields3.isFactory(), R.string.str_factory, -1, R.string.qry_factory, R.string.column_code, -1, true);
        if (this.baseErp.getErpType() != erpType2) {
            if (salesLinesWarehouseCanBeChangedAndErpTiger()) {
                SalesType salesType3 = getSalesType();
                Intrinsics.checkNotNull(salesType3);
                if (!SalesUtils.isSalesTypeDemand(salesType3) && !SalesUtils.isSalesTypeWhTransfer(getSalesType())) {
                }
            }
        } else {
            Sales sales14 = getmSales();
            Intrinsics.checkNotNull(sales14);
            sales14.getWareHouse().setPropChangeListener(new IFichePropChangeListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda21
                @Override // com.proje.mobilesales.core.interfaces.IFichePropChangeListener
                public void onChange() {
                    SalesHeaderFragment.onViewCreatedlambda1(this.f0);
                }
            });
        }
        if (this.baseErp.getErpType() == erpType2 || salesLinesWarehouseCanBeChangedAndErpTiger()) {
            Sales sales15 = getmSales();
            Intrinsics.checkNotNull(sales15);
            sales15.getReturnWareHouse().setPropChangeListener(new IFichePropChangeListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda22
                @Override // com.proje.mobilesales.core.interfaces.IFichePropChangeListener
                public void onChange() {
                    SalesHeaderFragment.onViewCreatedlambda2(this.f0);
                }
            });
        }
        if (demandLinesWarehouseCanBeChangedAndErpTiger()) {
            Sales sales16 = getmSales();
            Intrinsics.checkNotNull(sales16);
            sales16.getSourceWareHouse().setPropChangeListener(new IFichePropChangeListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda23
                @Override // com.proje.mobilesales.core.interfaces.IFichePropChangeListener
                public void onChange() {
                    SalesHeaderFragment.onViewCreatedlambda3(this.f0);
                }
            });
        }
        FicheMode salesFicheMode4 = this.hasOrderReference ? FicheMode.ANALYSE : getSalesFicheMode();
        LinearLayout linearLayout4 = this.lnWareHouse;
        TextView textView9 = this.txtWareHouse;
        Sales sales17 = getmSales();
        Intrinsics.checkNotNull(sales17);
        FicheRefProp wareHouse3 = sales17.getWareHouse();
        SalesFicheUserRights salesFicheUserRights = getSalesFicheUserRights();
        Intrinsics.checkNotNull(salesFicheUserRights);
        int r7 = salesFicheUserRights.isDivFactWareHouseControl() ? R.string.qry_warehouse_div_fact : R.string.qry_warehouse;
        SalesFicheUserRights salesFicheUserRights2 = getSalesFicheUserRights();
        Intrinsics.checkNotNull(salesFicheUserRights2);
        if (!salesFicheUserRights2.isDivFactWareHouseControl()) {
            r15 = 0;
            strArr = new String[0];
        } else {
            Sales sales18 = getmSales();
            Intrinsics.checkNotNull(sales18);
            r15 = 0;
            Sales sales19 = getmSales();
            Intrinsics.checkNotNull(sales19);
            strArr = new String[]{StringUtils.convertIntToString(sales18.getFactory().getLogicalRef()), StringUtils.convertIntToString(sales19.getBranch().getLogicalRef())};
        }
        createSingleAlertCursorSales(salesFicheMode4, linearLayout4, textView9, wareHouse3, true, R.string.str_ware_house, r7, R.string.column_code, Arrays.copyOf(strArr, strArr.length));
        FicheMode salesFicheMode5 = this.hasOrderReference ? FicheMode.ANALYSE : getSalesFicheMode();
        LinearLayout linearLayout5 = this.lnReturnWareHouse;
        TextView textView10 = this.txtReturnWareHouse;
        Sales sales20 = getmSales();
        Intrinsics.checkNotNull(sales20);
        FicheRefProp returnWareHouse3 = sales20.getReturnWareHouse();
        SalesFicheUserRights salesFicheUserRights3 = getSalesFicheUserRights();
        Intrinsics.checkNotNull(salesFicheUserRights3);
        int r72 = salesFicheUserRights3.isDivFactWareHouseControl() ? R.string.qry_warehouse_div_fact : R.string.qry_warehouse;
        SalesFicheUserRights salesFicheUserRights4 = getSalesFicheUserRights();
        Intrinsics.checkNotNull(salesFicheUserRights4);
        if (salesFicheUserRights4.isDivFactWareHouseControl()) {
            strArr2 = new String[2];
            Sales sales21 = getmSales();
            Intrinsics.checkNotNull(sales21);
            strArr2[r15] = StringUtils.convertIntToString(sales21.getFactory().getLogicalRef());
            Sales sales22 = getmSales();
            Intrinsics.checkNotNull(sales22);
            strArr2[1] = StringUtils.convertIntToString(sales22.getBranch().getLogicalRef());
        } else {
            strArr2 = new String[r15];
        }
        createSingleAlertCursorSales(salesFicheMode5, linearLayout5, textView10, returnWareHouse3, true, R.string.str_return_ware_house, r72, R.string.column_code, Arrays.copyOf(strArr2, strArr2.length));
        FicheMode salesFicheMode6 = this.hasOrderReference ? FicheMode.ANALYSE : getSalesFicheMode();
        LinearLayout linearLayout6 = this.lnSourceWareHouse;
        TextView textView11 = this.txtSourceWareHouse;
        Sales sales23 = getmSales();
        Intrinsics.checkNotNull(sales23);
        FicheRefProp sourceWareHouse = sales23.getSourceWareHouse();
        ErpType erpType3 = this.baseErp.getErpType();
        ErpType erpType4 = ErpType.TIGER;
        if (erpType3 == erpType4) {
            SalesType salesType4 = getSalesType();
            Intrinsics.checkNotNull(salesType4);
            z = SalesUtils.isSalesTypeDemand(salesType4) ? true : r15;
        }
        SalesFicheUserRights salesFicheUserRights5 = getSalesFicheUserRights();
        Intrinsics.checkNotNull(salesFicheUserRights5);
        int r73 = salesFicheUserRights5.isDivFactWareHouseControl() ? R.string.qry_warehouse_div_fact : R.string.qry_warehouse;
        SalesFicheUserRights salesFicheUserRights6 = getSalesFicheUserRights();
        Intrinsics.checkNotNull(salesFicheUserRights6);
        if (salesFicheUserRights6.isDivFactWareHouseControl()) {
            strArr3 = new String[2];
            Sales sales24 = getmSales();
            Intrinsics.checkNotNull(sales24);
            strArr3[r15] = StringUtils.convertIntToString(sales24.getFactory().getLogicalRef());
            Sales sales25 = getmSales();
            Intrinsics.checkNotNull(sales25);
            strArr3[1] = StringUtils.convertIntToString(sales25.getBranch().getLogicalRef());
        } else {
            strArr3 = new String[r15];
        }
        createSingleAlertCursorSales(salesFicheMode6, linearLayout6, textView11, sourceWareHouse, z, R.string.str_source_ware_house, r73, R.string.column_code, Arrays.copyOf(strArr3, strArr3.length));
        FicheMode salesFicheMode7 = getSalesFicheMode();
        Intrinsics.checkNotNullExpressionValue(salesFicheMode7, "getSalesFicheMode(...)");
        LinearLayout linearLayout7 = this.lnSpeCode;
        TextView textView12 = this.txtSpeCode;
        Sales sales26 = getmSales();
        Intrinsics.checkNotNull(sales26);
        FicheRefProp speCode = sales26.getSpeCode();
        SalesFicheHeaderFields salesFicheHeaderFields4 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields4);
        boolean zIsSpeCode = salesFicheHeaderFields4.isSpeCode();
        SalesNewViewModel salesNewViewModel2 = this.viewModel;
        Sales sales27 = getmSales();
        Intrinsics.checkNotNull(sales27);
        createSingleAlertCursorSalesWithCampaignControl(salesFicheMode7, linearLayout7, textView12, speCode, zIsSpeCode, R.string.str_spe_code, -1, R.string.qry_spe_code, R.string.column_code, -1, true, salesNewViewModel2.getSpeCodeTypeHeader(sales27.getmSalesType()));
        FicheMode salesFicheMode8 = getSalesFicheMode();
        Intrinsics.checkNotNullExpressionValue(salesFicheMode8, "getSalesFicheMode(...)");
        LinearLayout linearLayout8 = this.lnWarrantyCode;
        TextView textView13 = this.txtWarrantyCode;
        Sales sales28 = getmSales();
        Intrinsics.checkNotNull(sales28);
        FicheRefProp warrantyCode = sales28.getWarrantyCode();
        SalesFicheHeaderFields salesFicheHeaderFields5 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields5);
        boolean zIsWarrantyCode = salesFicheHeaderFields5.isWarrantyCode();
        SalesNewViewModel salesNewViewModel3 = this.viewModel;
        Sales sales29 = getmSales();
        Intrinsics.checkNotNull(sales29);
        createSingleAlertCursorSalesWithCampaignControl(salesFicheMode8, linearLayout8, textView13, warrantyCode, zIsWarrantyCode, R.string.str_warranty_code, -1, R.string.qry_warranty, R.string.column_code, -1, true, salesNewViewModel3.getSpeCodeTypeHeader(sales29.getmSalesType()));
        FicheMode salesFicheMode9 = getSalesFicheMode();
        LinearLayout linearLayout9 = this.lnDocumentNo;
        EditText editText = this.edtDocumentNo;
        Sales sales30 = getmSales();
        Intrinsics.checkNotNull(sales30);
        FicheStringProp documentNo = sales30.getDocumentNo();
        SalesFicheHeaderFields salesFicheHeaderFields6 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields6);
        createEditTextAddTextChangedListener(salesFicheMode9, linearLayout9, editText, documentNo, salesFicheHeaderFields6.isDocumentNo());
        FicheMode salesFicheMode10 = getSalesFicheMode();
        LinearLayout linearLayout10 = this.lnDocumentTrackNo;
        EditText editText2 = this.edtDocumentTrackNo;
        Sales sales31 = getmSales();
        Intrinsics.checkNotNull(sales31);
        FicheStringProp documentTrackingNo = sales31.getDocumentTrackingNo();
        SalesFicheHeaderFields salesFicheHeaderFields7 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields7);
        createEditTextAddTextChangedListener(salesFicheMode10, linearLayout10, editText2, documentTrackingNo, salesFicheHeaderFields7.isDocumentTrackingNo());
        FicheMode salesFicheMode11 = getSalesFicheMode();
        LinearLayout linearLayout11 = this.lnCustomerOrderNo;
        EditText editText3 = this.edtCustomerOrderNo;
        Sales sales32 = getmSales();
        Intrinsics.checkNotNull(sales32);
        FicheStringProp customerOrderNo = sales32.getCustomerOrderNo();
        SalesFicheHeaderFields salesFicheHeaderFields8 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields8);
        createEditTextAddTextChangedListener(salesFicheMode11, linearLayout11, editText3, customerOrderNo, salesFicheHeaderFields8.isCustomerOrderNo());
        FicheMode salesFicheMode12 = getSalesFicheMode();
        Intrinsics.checkNotNullExpressionValue(salesFicheMode12, "getSalesFicheMode(...)");
        LinearLayout linearLayout12 = this.lnDeliveryDate;
        TextView textView14 = this.txtDeliveryDate;
        Sales sales33 = getmSales();
        Intrinsics.checkNotNull(sales33);
        FicheDateProp deliveryDate = sales33.getDeliveryDate();
        SalesFicheHeaderFields salesFicheHeaderFields9 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields9);
        createDateAlertDialogDeliveryDate(salesFicheMode12, linearLayout12, textView14, deliveryDate, salesFicheHeaderFields9.isDeliveryDate(), R.string.str_delivery_date);
        FicheMode salesFicheMode13 = getSalesFicheMode();
        LinearLayout linearLayout13 = this.lnPaymentOrder;
        CheckBox checkBox = this.chkPaymentOrder;
        Sales sales34 = getmSales();
        Intrinsics.checkNotNull(sales34);
        FicheBooleanProp paymentOrder = sales34.getPaymentOrder();
        SalesFicheHeaderFields salesFicheHeaderFields10 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields10);
        createCheckboxSetCheckedChangeListener(salesFicheMode13, linearLayout13, checkBox, paymentOrder, salesFicheHeaderFields10.isReserveOrder());
        FicheMode salesFicheMode14 = getSalesFicheMode();
        LinearLayout linearLayout14 = this.lnExplanation1;
        EditText editText4 = this.edtExplanation1;
        Sales sales35 = getmSales();
        Intrinsics.checkNotNull(sales35);
        FicheStringProp explanation1 = sales35.getExplanation1();
        SalesFicheHeaderFields salesFicheHeaderFields11 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields11);
        createEditTextAddTextChangedListener(salesFicheMode14, linearLayout14, editText4, explanation1, salesFicheHeaderFields11.isExplanation1());
        FicheMode salesFicheMode15 = getSalesFicheMode();
        LinearLayout linearLayout15 = this.lnExplanation2;
        EditText editText5 = this.edtExplanation2;
        Sales sales36 = getmSales();
        Intrinsics.checkNotNull(sales36);
        FicheStringProp explanation2 = sales36.getExplanation2();
        SalesFicheHeaderFields salesFicheHeaderFields12 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields12);
        createEditTextAddTextChangedListener(salesFicheMode15, linearLayout15, editText5, explanation2, salesFicheHeaderFields12.isExplanation2());
        FicheMode salesFicheMode16 = getSalesFicheMode();
        LinearLayout linearLayout16 = this.lnExplanation3;
        EditText editText6 = this.edtExplanation3;
        Sales sales37 = getmSales();
        Intrinsics.checkNotNull(sales37);
        FicheStringProp explanation3 = sales37.getExplanation3();
        SalesFicheHeaderFields salesFicheHeaderFields13 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields13);
        createEditTextAddTextChangedListener(salesFicheMode16, linearLayout16, editText6, explanation3, salesFicheHeaderFields13.isExplanation3());
        FicheMode salesFicheMode17 = getSalesFicheMode();
        LinearLayout linearLayout17 = this.lnExplanation4;
        EditText editText7 = this.edtExplanation4;
        Sales sales38 = getmSales();
        Intrinsics.checkNotNull(sales38);
        FicheStringProp explanation4 = sales38.getExplanation4();
        SalesFicheHeaderFields salesFicheHeaderFields14 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields14);
        createEditTextAddTextChangedListener(salesFicheMode17, linearLayout17, editText7, explanation4, salesFicheHeaderFields14.isExplanation4());
        if (this.viewModel.erpType() == erpType2) {
            FicheMode salesFicheMode18 = getSalesFicheMode();
            LinearLayout linearLayout18 = this.lnExplanation5;
            EditText editText8 = this.edtExplanation5;
            Sales sales39 = getmSales();
            Intrinsics.checkNotNull(sales39);
            FicheStringProp explanation5 = sales39.getExplanation5();
            SalesFicheHeaderFields salesFicheHeaderFields15 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields15);
            createEditTextAddTextChangedListener(salesFicheMode18, linearLayout18, editText8, explanation5, salesFicheHeaderFields15.isExplanation5());
            FicheMode salesFicheMode19 = getSalesFicheMode();
            LinearLayout linearLayout19 = this.lnExplanation6;
            EditText editText9 = this.edtExplanation6;
            Sales sales40 = getmSales();
            Intrinsics.checkNotNull(sales40);
            FicheStringProp explanation6 = sales40.getExplanation6();
            SalesFicheHeaderFields salesFicheHeaderFields16 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields16);
            createEditTextAddTextChangedListener(salesFicheMode19, linearLayout19, editText9, explanation6, salesFicheHeaderFields16.isExplanation6());
            FicheMode salesFicheMode20 = getSalesFicheMode();
            LinearLayout linearLayout20 = this.lnExplanation7;
            EditText editText10 = this.edtExplanation7;
            Sales sales41 = getmSales();
            Intrinsics.checkNotNull(sales41);
            FicheStringProp explanation7 = sales41.getExplanation7();
            SalesFicheHeaderFields salesFicheHeaderFields17 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields17);
            createEditTextAddTextChangedListener(salesFicheMode20, linearLayout20, editText10, explanation7, salesFicheHeaderFields17.isExplanation7());
            FicheMode salesFicheMode21 = getSalesFicheMode();
            LinearLayout linearLayout21 = this.lnExplanation8;
            EditText editText11 = this.edtExplanation8;
            Sales sales42 = getmSales();
            Intrinsics.checkNotNull(sales42);
            FicheStringProp explanation8 = sales42.getExplanation8();
            SalesFicheHeaderFields salesFicheHeaderFields18 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields18);
            createEditTextAddTextChangedListener(salesFicheMode21, linearLayout21, editText11, explanation8, salesFicheHeaderFields18.isExplanation8());
            FicheMode salesFicheMode22 = getSalesFicheMode();
            LinearLayout linearLayout22 = this.lnExplanation9;
            EditText editText12 = this.edtExplanation9;
            Sales sales43 = getmSales();
            Intrinsics.checkNotNull(sales43);
            FicheStringProp explanation9 = sales43.getExplanation9();
            SalesFicheHeaderFields salesFicheHeaderFields19 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields19);
            createEditTextAddTextChangedListener(salesFicheMode22, linearLayout22, editText12, explanation9, salesFicheHeaderFields19.isExplanation9());
            FicheMode salesFicheMode23 = getSalesFicheMode();
            LinearLayout linearLayout23 = this.lnExplanation10;
            EditText editText13 = this.edtExplanation10;
            Sales sales44 = getmSales();
            Intrinsics.checkNotNull(sales44);
            FicheStringProp explanation10 = sales44.getExplanation10();
            SalesFicheHeaderFields salesFicheHeaderFields20 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields20);
            createEditTextAddTextChangedListener(salesFicheMode23, linearLayout23, editText13, explanation10, salesFicheHeaderFields20.isExplanation10());
        }
        FicheMode salesFicheMode24 = getSalesFicheMode();
        LinearLayout linearLayout24 = this.lnSalesConsignee;
        CheckBox checkBox2 = this.chkSalesConsignee;
        Sales sales45 = getmSales();
        Intrinsics.checkNotNull(sales45);
        FicheBooleanProp consignee = sales45.getConsignee();
        SalesFicheHeaderFields salesFicheHeaderFields21 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields21);
        createCheckboxSetCheckedChangeListener(salesFicheMode24, linearLayout24, checkBox2, consignee, salesFicheHeaderFields21.isConsignee());
        BaseFicheFragment.OnCheckBoxClickInterface onCheckBoxClickInterface = new BaseFicheFragment.OnCheckBoxClickInterface() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda24
            @Override // com.proje.mobilesales.core.base.BaseFicheFragment.OnCheckBoxClickInterface
            public void onCheckBoxClicked(boolean z5) {
                SalesHeaderFragment.onViewCreatedlambda4(this.f0, z5);
            }
        };
        FicheMode salesFicheMode25 = getSalesFicheMode();
        LinearLayout linearLayout25 = this.lnSalesDetailIncludeVat;
        CheckBox checkBox3 = this.chkSalesDetailIncludeVat;
        Sales sales46 = getmSales();
        Intrinsics.checkNotNull(sales46);
        FicheBooleanProp includeVat = sales46.getIncludeVat();
        SalesFicheHeaderFields salesFicheHeaderFields22 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields22);
        boolean zIsIncludeVAT = salesFicheHeaderFields22.isIncludeVAT();
        Sales sales47 = getmSales();
        Intrinsics.checkNotNull(sales47);
        boolean zIsSaleVatDefaultChecked = sales47.isSaleVatDefaultChecked();
        Sales sales48 = getmSales();
        Intrinsics.checkNotNull(sales48);
        createCheckboxSetCheckedChangeListener(salesFicheMode25, linearLayout25, checkBox3, includeVat, zIsIncludeVAT, zIsSaleVatDefaultChecked, sales48.isSaleVatCanBeChange(), onCheckBoxClickInterface);
        FicheMode salesFicheMode26 = getSalesFicheMode();
        LinearLayout linearLayout26 = this.ln_document_no;
        EditText editText14 = this.edt_document_no;
        Sales sales49 = getmSales();
        Intrinsics.checkNotNull(sales49);
        FicheStringProp eInvoiceSGKDocumentNo = sales49.getEInvoiceSGKDocumentNo();
        SalesActivityNew salesActivity = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity);
        createEditTextAddTextChangedListener(salesFicheMode26, linearLayout26, editText14, eInvoiceSGKDocumentNo, salesActivity.isCustomerEInvoiceSgkType());
        FicheMode salesFicheMode27 = getSalesFicheMode();
        LinearLayout linearLayout27 = this.ln_taxpayer_name;
        EditText editText15 = this.edt_taxpayer_name;
        Sales sales50 = getmSales();
        Intrinsics.checkNotNull(sales50);
        FicheStringProp taxPayerName = sales50.getTaxPayerName();
        SalesActivityNew salesActivity2 = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity2);
        createEditTextAddTextChangedListener(salesFicheMode27, linearLayout27, editText15, taxPayerName, salesActivity2.isCustomerEInvoiceSgkType());
        FicheMode salesFicheMode28 = getSalesFicheMode();
        LinearLayout linearLayout28 = this.ln_taxpayer_code;
        EditText editText16 = this.edt_taxpayer_code;
        Sales sales51 = getmSales();
        Intrinsics.checkNotNull(sales51);
        FicheStringProp taxPayerCode = sales51.getTaxPayerCode();
        SalesActivityNew salesActivity3 = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity3);
        createEditTextAddTextChangedListener(salesFicheMode28, linearLayout28, editText16, taxPayerCode, salesActivity3.isCustomerEInvoiceSgkType());
        FicheMode salesFicheMode29 = getSalesFicheMode();
        LinearLayout linearLayout29 = this.ln_additional_invoice_type;
        TextView textView15 = this.txt_additional_invoice_type;
        Sales sales52 = getmSales();
        Intrinsics.checkNotNull(sales52);
        FicheRefProp eInvoiceTypSgk = sales52.getEInvoiceTypSgk();
        SalesActivityNew salesActivity4 = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity4);
        createSingleAlertCursorSalesArray(salesFicheMode29, linearLayout29, textView15, eInvoiceTypSgk, salesActivity4.isCustomerEInvoiceSgkType(), R.string.str_additional_invoice_type, R.array.additional_invoice_type_values, true);
        FicheMode salesFicheMode30 = getSalesFicheMode();
        Intrinsics.checkNotNullExpressionValue(salesFicheMode30, "getSalesFicheMode(...)");
        LinearLayout linearLayout30 = this.ln_starting_date;
        TextView textView16 = this.txt_starting_date;
        Sales sales53 = getmSales();
        Intrinsics.checkNotNull(sales53);
        FicheDateProp beginDate = sales53.getBeginDate();
        SalesActivityNew salesActivity5 = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity5);
        createDateAlertDialogDeliveryDate(salesFicheMode30, linearLayout30, textView16, beginDate, salesActivity5.isCustomerEInvoiceSgkType(), R.string.str_begin_date);
        FicheMode salesFicheMode31 = getSalesFicheMode();
        Intrinsics.checkNotNullExpressionValue(salesFicheMode31, "getSalesFicheMode(...)");
        LinearLayout linearLayout31 = this.ln_end_date;
        TextView textView17 = this.txt_end_date;
        Sales sales54 = getmSales();
        Intrinsics.checkNotNull(sales54);
        FicheDateProp endDate = sales54.getEndDate();
        SalesActivityNew salesActivity6 = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity6);
        createDateAlertDialogDeliveryDate(salesFicheMode31, linearLayout31, textView17, endDate, salesActivity6.isCustomerEInvoiceSgkType(), R.string.str_end_date);
        FicheMode salesFicheMode32 = getSalesFicheMode();
        LinearLayout linearLayout32 = this.ln_cabin;
        TextView textView18 = this.txt_cabin;
        Sales sales55 = getmSales();
        Intrinsics.checkNotNull(sales55);
        FicheDiscountRefProp cabin = sales55.getCabin();
        Intrinsics.checkNotNull(getSalesType());
        createSingleAlertCursorSales(salesFicheMode32, linearLayout32, textView18, cabin, !SalesUtils.isSalesTypeOrderOrDemandOrWhTransfer(r0), R.string.str_cabin, -1, R.string.qry_cabin, R.string.column_name, null, this.viewModel.getCustomerClCode(this.mCustomerRef), this.viewModel.user().getFirmNr());
        FicheMode salesFicheMode33 = getSalesFicheMode();
        LinearLayout linearLayout33 = this.lnSpeCode1;
        TextView textView19 = this.txtSpeCode1;
        Sales sales56 = getmSales();
        Intrinsics.checkNotNull(sales56);
        createSingleAlertCursorSales(salesFicheMode33, linearLayout33, textView19, sales56.getFirstSpeCode(), true, R.string.str_spe_code, R.string.str_specode_not_found, R.string.qry_spe_code, R.string.column_name, null, false, "1");
        FicheMode salesFicheMode34 = getSalesFicheMode();
        LinearLayout linearLayout34 = this.lnSpeCode2;
        TextView textView20 = this.txtSpeCode2;
        Sales sales57 = getmSales();
        Intrinsics.checkNotNull(sales57);
        createSingleAlertCursorSales(salesFicheMode34, linearLayout34, textView20, sales57.getSecondSpeCode(), true, R.string.str_spe_code, R.string.str_specode_not_found, R.string.qry_spe_code, R.string.column_name, null, false, ExifInterface.GPS_MEASUREMENT_2D);
        Sales sales58 = getmSales();
        Intrinsics.checkNotNull(sales58);
        if (SalesUtils.isSalesTypeWhTransfer(sales58.getmSalesType())) {
            TextView textView21 = this.txtBranchHeader;
            Intrinsics.checkNotNull(textView21);
            textView21.setText(R.string.str_dest_branch);
            TextView textView22 = this.txtSalesDivisionHeader;
            Intrinsics.checkNotNull(textView22);
            textView22.setText(R.string.str_dest_division);
            TextView textView23 = this.txtSalesFactoryHeader;
            Intrinsics.checkNotNull(textView23);
            textView23.setText(R.string.str_dest_factory);
            TextView textView24 = this.txtSalesWarehouseHeader;
            Intrinsics.checkNotNull(textView24);
            textView24.setText(R.string.str_dest_warehouse);
        }
        createCursorForEDocSerial();
        BaseFicheFragment.OnCheckBoxClickInterface onCheckBoxClickInterface2 = new BaseFicheFragment.OnCheckBoxClickInterface() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda25
            @Override // com.proje.mobilesales.core.base.BaseFicheFragment.OnCheckBoxClickInterface
            public void onCheckBoxClicked(boolean z5) {
                SalesHeaderFragment.onViewCreatedlambda5(this.f0, z5);
            }
        };
        BaseFicheFragment.OnCheckBoxClickInterface onCheckBoxClickInterface3 = new BaseFicheFragment.OnCheckBoxClickInterface() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda26
            @Override // com.proje.mobilesales.core.base.BaseFicheFragment.OnCheckBoxClickInterface
            public void onCheckBoxClicked(boolean z5) {
                SalesHeaderFragment.onViewCreatedlambda6(this.f0, z5);
            }
        };
        FicheMode salesFicheMode35 = getSalesFicheMode();
        View view2 = this.lnEdespatch;
        CheckBox checkBox4 = this.chkEdespatch;
        Sales sales59 = getmSales();
        Intrinsics.checkNotNull(sales59);
        createCheckboxSetCheckedChangeListener(salesFicheMode35, view2, checkBox4, sales59.getEDespatch(), this.firmUseEDespatch, false, true, onCheckBoxClickInterface2);
        FicheMode salesFicheMode36 = getSalesFicheMode();
        View view3 = this.lnInsteadOfEdespatch;
        CheckBox checkBox5 = this.chkInsteadOfEdespatch;
        Sales sales60 = getmSales();
        Intrinsics.checkNotNull(sales60);
        FicheBooleanProp insteadOfEDespatch = sales60.getInsteadOfEDespatch();
        if (this.viewModel.erpType() == erpType4) {
            Sales sales61 = getmSales();
            Intrinsics.checkNotNull(sales61);
            if (SalesUtils.isSalesTypeOrderOrInvoiceOrRetailInvoice(sales61.getmSalesType())) {
                Sales sales62 = getmSales();
                Intrinsics.checkNotNull(sales62);
                z2 = sales62.getErpInvoiceType().getLogicalRef() > 0;
            }
        }
        createCheckboxSetCheckedChangeListener(salesFicheMode36, view3, checkBox5, insteadOfEDespatch, z2, isDefaultCheckedInsteadOfEdespatch(), true, onCheckBoxClickInterface3);
        if (getSalesFicheMode() != FicheMode.ANALYSE) {
            LinearLayout linearLayout35 = this.lnCustomer;
            Intrinsics.checkNotNull(linearLayout35);
            linearLayout35.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda27
                @Override // android.view.View.OnClickListener
                public void onClick(View view4) {
                    SalesHeaderFragment.onViewCreatedlambda7(this.f0, view4);
                }
            });
            ImageView imageView = this.imgClearCustomer;
            Intrinsics.checkNotNull(imageView);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda28
                @Override // android.view.View.OnClickListener
                public void onClick(View view4) {
                    SalesHeaderFragment.onViewCreatedlambda8(this.f0, view4);
                }
            });
        } else {
            ImageView imageView2 = this.imgClearCustomer;
            Intrinsics.checkNotNull(imageView2);
            imageView2.setVisibility(8);
            ImageView imageView3 = this.imgClearCustomer;
            Intrinsics.checkNotNull(imageView3);
            imageView3.setVisibility(8);
        }
        Sales sales63 = getmSales();
        if (SalesUtils.isSalesTypeWhTransfer(sales63 != null ? sales63.getmSalesType() : null)) {
            FicheMode salesFicheMode37 = getSalesFicheMode();
            Intrinsics.checkNotNullExpressionValue(salesFicheMode37, "getSalesFicheMode(...)");
            LinearLayout linearLayout36 = this.lnShipAgent;
            Intrinsics.checkNotNull(linearLayout36);
            TextView textView25 = this.txtShipAgent;
            Intrinsics.checkNotNull(textView25);
            Sales sales64 = getmSales();
            Intrinsics.checkNotNull(sales64);
            z3 = false;
            createSingleAlertCursorForShipAgent(salesFicheMode37, linearLayout36, textView25, sales64.getShipAgent(), true, R.string.str_shipAgent, -1, R.string.qry_shipAgent, R.string.column_code, -1, true);
        } else {
            z3 = false;
        }
        FicheMode salesFicheMode38 = getSalesFicheMode();
        Intrinsics.checkNotNullExpressionValue(salesFicheMode38, "getSalesFicheMode(...)");
        LinearLayout linearLayout37 = this.lnErpInvoiceType;
        TextView textView26 = this.txtErpInvoiceType;
        Sales sales65 = getmSales();
        Intrinsics.checkNotNull(sales65);
        createSingleAlertForErpInvoiceType(salesFicheMode38, linearLayout37, textView26, sales65.getErpInvoiceType(), true, R.string.str_erp_invoice_type, true);
        BaseFicheFragment.OnCheckBoxClickInterface onCheckBoxClickInterface4 = new BaseFicheFragment.OnCheckBoxClickInterface() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda29
            @Override // com.proje.mobilesales.core.base.BaseFicheFragment.OnCheckBoxClickInterface
            public void onCheckBoxClicked(boolean z5) {
                SalesHeaderFragment.onViewCreatedlambda9(this.f0, z5);
            }
        };
        FicheMode salesFicheMode39 = getSalesFicheMode();
        LinearLayout linearLayout38 = this.lnSalesDoReserve;
        CheckBox checkBox6 = this.chkSalesDoReserve;
        Sales sales66 = getmSales();
        Intrinsics.checkNotNull(sales66);
        FicheBooleanProp reserved = sales66.getReserved();
        Sales sales67 = getmSales();
        Intrinsics.checkNotNull(sales67);
        boolean z5 = sales67.getmSalesType() == SalesType.ORDER && this.viewModel.erpType() == erpType2 || z3;
        if (Intrinsics.areEqual(this.viewModel.getSqlHelper().getLogoParamValue("STOK_AYIR"), ExifInterface.LONGITUDE_EAST)) {
            SalesFicheUserRights salesFicheUserRights7 = getSalesFicheUserRights();
            Intrinsics.checkNotNull(salesFicheUserRights7);
            z4 = !salesFicheUserRights7.isNotChangeReserve() || z3;
        }
        createCheckboxSetCheckedChangeListener(salesFicheMode39, linearLayout38, checkBox6, reserved, z5, false, z4, onCheckBoxClickInterface4, "OK");
    }

    public static void onViewCreatedlambda0(SalesHeaderFragment this0) throws InterruptedException {
        ErpInvoiceType erpInvoiceTypeFromErpInvoiceType;
        Intrinsics.checkNotNullParameter(this0, "this0");
        Sales sales = this0.getmSales();
        Intrinsics.checkNotNull(sales);
        ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        if (!mSalesDetailList.isEmpty()) {
            SalesActivityNew salesActivity = this0.getSalesActivity();
            Intrinsics.checkNotNull(salesActivity);
            salesActivity.resetPrices();
        }
        if (this0.viewModel.eDocControlTypeBranch()) {
            SalesNewViewModel salesNewViewModel = this0.viewModel;
            Sales sales2 = this0.getmSales();
            Intrinsics.checkNotNull(sales2);
            List<Branch> iSqlHelperBranch = salesNewViewModel.getISqlHelperBranch(Branch.class, "NR=?", new String[]{String.valueOf(sales2.getBranch().getLogicalRef())});
            if (iSqlHelperBranch == null || iSqlHelperBranch.isEmpty()) {
                return;
            }
            this0.branchEInvoice = iSqlHelperBranch.get(0).getUseEinvoice() == 1;
            this0.branchEDespatch = iSqlHelperBranch.get(0).getUseEdespatch() == 1;
            this0.branchEArchieve = iSqlHelperBranch.get(0).getUseEarchive() == 1;
            this0.branchEArchieveType = iSqlHelperBranch.get(0).geteArchiveType();
            this0.availableErpInvoiceTypes = this0.getAvailableErpInvoiceTypes();
            Sales sales3 = this0.getmSales();
            Intrinsics.checkNotNull(sales3);
            if (sales3.getErpInvoiceType().getLogicalRef() < 0) {
                erpInvoiceTypeFromErpInvoiceType = this0.baseErp.getErpTypeFromSales(this0.getmSales());
            } else {
                ErpInvoiceType.Companion companion = ErpInvoiceType.Companion;
                Sales sales4 = this0.getmSales();
                Intrinsics.checkNotNull(sales4);
                erpInvoiceTypeFromErpInvoiceType = companion.fromErpInvoiceType(sales4.getErpInvoiceType().getLogicalRef());
            }
            ArrayList<String> arrayList = this0.availableErpInvoiceTypes;
            Intrinsics.checkNotNull(arrayList);
            String str = arrayList.get(0);
            Intrinsics.checkNotNullExpressionValue(str, "get(...)");
            String str2 = str;
            if (erpInvoiceTypeFromErpInvoiceType != ErpInvoiceType.None) {
                if (this0.branchEArchieve && erpInvoiceTypeFromErpInvoiceType != ErpInvoiceType.EInvoice) {
                    Sales sales5 = this0.getmSales();
                    Intrinsics.checkNotNull(sales5);
                    if (!SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(sales5.getmSalesType())) {
                        ArrayList<String> arrayList2 = this0.availableErpInvoiceTypes;
                        Intrinsics.checkNotNull(arrayList2);
                        String str3 = arrayList2.get(this0.branchEArchieveType - 1);
                        Intrinsics.checkNotNullExpressionValue(str3, "get(...)");
                        str2 = str3;
                    }
                } else if (erpInvoiceTypeFromErpInvoiceType == ErpInvoiceType.EInvoice) {
                    Sales sales6 = this0.getmSales();
                    Intrinsics.checkNotNull(sales6);
                    if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoice(sales6.getmSalesType())) {
                        ArrayList<String> arrayList3 = this0.availableErpInvoiceTypes;
                        Intrinsics.checkNotNull(arrayList3);
                        String str4 = arrayList3.get(1);
                        Intrinsics.checkNotNullExpressionValue(str4, "get(...)");
                        str2 = str4;
                    }
                }
                Sales sales7 = this0.getmSales();
                Intrinsics.checkNotNull(sales7);
                sales7.getErpInvoiceType().setLogicalRef(this0.allErpInvoiceTypes.indexOf(str2));
                Sales sales8 = this0.getmSales();
                Intrinsics.checkNotNull(sales8);
                FicheStringProp.setDefinition(str2);
                Sales sales9 = this0.getmSales();
                Intrinsics.checkNotNull(sales9);
                FicheRefProp erpInvoiceType = sales9.getErpInvoiceType();
                ArrayList<String> arrayList4 = this0.availableErpInvoiceTypes;
                Intrinsics.checkNotNull(arrayList4);
                erpInvoiceType.setWhich(arrayList4.indexOf(str2));
                TextView textView = this0.txtErpInvoiceType;
                Intrinsics.checkNotNull(textView);
                Sales sales10 = this0.getmSales();
                Intrinsics.checkNotNull(sales10);
                textView.setText(sales10.getErpInvoiceType().toString());
            }
        }
    }

    
    public static void onViewCreatedlambda1(SalesHeaderFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.showDialogToChangeDetailWarehouse();
    }

    
    public static void onViewCreatedlambda2(SalesHeaderFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.showDialogToChangeDetailWarehouse();
    }

    
    public static void onViewCreatedlambda3(SalesHeaderFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.showDialogToChangeDetailWarehouse();
    }

    
    public static void onViewCreatedlambda4(SalesHeaderFragment this0, boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        SalesActivityNew salesActivity = this0.getSalesActivity();
        Intrinsics.checkNotNull(salesActivity);
        Sales sales = salesActivity.getmSales();
        Intrinsics.checkNotNull(sales);
        sales.setSaleVatDefaultChecked(z);
        ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        Iterator<SalesDetail> it = mSalesDetailList.iterator();
        while (it.hasNext()) {
            SalesDetail next = it.next();
            next.getIncludeVat().setSelect(z);
            next.calculateFiche(sales.isNotUseGattribKdv());
        }
        sales.calculateSalesTotal();
        IVatChangeListener iVatChangeListener = this0.mCallback;
        Intrinsics.checkNotNull(iVatChangeListener);
        iVatChangeListener.onHeaderVatChange();
    }

    
    public static void onViewCreatedlambda5(SalesHeaderFragment this0, boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (this0.viewModel.erpType() == ErpType.TIGER) {
            return;
        }
        if (z) {
            View view = this0.lnSerial;
            Intrinsics.checkNotNull(view);
            view.setVisibility(0);
            View view2 = this0.dvdSerial;
            Intrinsics.checkNotNull(view2);
            view2.setVisibility(0);
            this0.createCursorForEDocSerial();
            return;
        }
        TextView textView = this0.txtSerial;
        Intrinsics.checkNotNull(textView);
        textView.setText("");
        Sales sales = this0.getmSales();
        Intrinsics.checkNotNull(sales);
        sales.setEDocSerial(new FicheRefProp());
        Sales sales2 = this0.getmSales();
        Intrinsics.checkNotNull(sales2);
        sales2.setEDispatchAdditionalInfo(new EDispatchAdditionalInfo());
        View view3 = this0.lnSerial;
        Intrinsics.checkNotNull(view3);
        view3.setVisibility(8);
        View view4 = this0.dvdSerial;
        Intrinsics.checkNotNull(view4);
        view4.setVisibility(8);
    }

    
    public static void onViewCreatedlambda6(SalesHeaderFragment this0, boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (this0.baseErp.getErpType() != ErpType.NETSIS && z) {
            this0.insteadOfEDespatch = true;
        }
    }

    
    public static void onViewCreatedlambda7(SalesHeaderFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intent intent = new Intent(this0.getActivity(), CustomerListActivity.class);
        intent.putExtra(CustomerListActivity.EXTRA_CUSTOMER_SELECT_TYPE, 1);
        this0.startActivityForResult(intent, 999);
    }

    
    public static void onViewCreatedlambda8(SalesHeaderFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Sales sales = this0.getmSales();
        Intrinsics.checkNotNull(sales);
        sales.setClCode("");
        Sales sales2 = this0.getmSales();
        Intrinsics.checkNotNull(sales2);
        sales2.setClRef(0);
        TextView textView = this0.txtCustomer;
        Intrinsics.checkNotNull(textView);
        textView.setText("");
        ImageView imageView = this0.imgClearCustomer;
        Intrinsics.checkNotNull(imageView);
        imageView.setVisibility(8);
    }

    
    public static void onViewCreatedlambda9(SalesHeaderFragment this0, boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        SalesActivityNew salesActivity = this0.getSalesActivity();
        Intrinsics.checkNotNull(salesActivity);
        Sales sales = salesActivity.getmSales();
        Intrinsics.checkNotNull(sales);
        sales.getReserved().setSelect(z);
        ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        Iterator<SalesDetail> it = mSalesDetailList.iterator();
        while (it.hasNext()) {
            SalesDetail next = it.next();
            next.getReserve().setSelect(z);
            FicheStringProp.setDefinition(this0.getString(z ? R.string.str_yes : R.string.str_no));
        }
    }

    private boolean isDefaultCheckedInsteadOfEdespatch() {
        SalesType salesType = getSalesType();
        Intrinsics.checkNotNull(salesType);
        if (!SalesUtils.isSalesTypeOrderOrInvoiceOrRetailInvoice(salesType)) {
            return false;
        }
        if (getSalesFicheMode() == FicheMode.NEW) {
            ISqlHelper logoSqlHelper = this.baseErp.getLogoSqlHelper();
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            return logoSqlHelper.getClCardEArchiveInsteadOfDesp(sales.getClRef()) == 1;
        }
        Sales sales2 = getmSales();
        Intrinsics.checkNotNull(sales2);
        return sales2.getInsteadOfEDespatch().isSelect();
    }

    private void showDialogToChangeDetailWarehouse() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilderB3;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle(getString(R.string.str_question_change_detail_warehouse)).setPositiveButton(R.string.str_yes, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r2) {
                SalesHeaderFragment.showDialogToChangeDetailWarehouselambda10(this.f0, dialogInterface, r2);
            }
        }).setNegativeButton(R.string.str_no, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r2) {
                SalesHeaderFragment.showDialogToChangeDetailWarehouselambda11(dialogInterface, r2);
            }
        }).create().show();
    }

    
    public static void showDialogToChangeDetailWarehouselambda10(SalesHeaderFragment this0, DialogInterface dialog, int r2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        Sales sales = this0.getmSales();
        Intrinsics.checkNotNull(sales);
        sales.onWarehouseChange();
        dialog.dismiss();
    }

    
    public static void showDialogToChangeDetailWarehouselambda11(DialogInterface dialog, int r1) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    private void createCursorForEDocSerial() {
        if (this.firmUseEDespatch) {
            FicheMode salesFicheMode = getSalesFicheMode();
            View view = this.lnSerial;
            TextView textView = this.txtSerial;
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            createSearchDialogCursorSales(salesFicheMode, view, textView, sales.getEDocSerial(), this.firmUseEDespatch, R.string.str_serial, R.string.qry_serial_edespatch, R.string.column_code);
            return;
        }
        if (this.mAcceptEInvoice) {
            FicheMode salesFicheMode2 = getSalesFicheMode();
            View view2 = this.lnSerial;
            TextView textView2 = this.txtSerial;
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            createSearchDialogCursorSales(salesFicheMode2, view2, textView2, sales2.getEDocSerial(), this.mAcceptEInvoice, R.string.str_serial, R.string.qry_serial_einvoice, R.string.column_code);
            return;
        }
        if (this.mAcceptEArchive) {
            FicheMode salesFicheMode3 = getSalesFicheMode();
            View view3 = this.lnSerial;
            TextView textView3 = this.txtSerial;
            Sales sales3 = getmSales();
            Intrinsics.checkNotNull(sales3);
            createSearchDialogCursorSales(salesFicheMode3, view3, textView3, sales3.getEDocSerial(), this.mAcceptEArchive, R.string.str_serial, R.string.qry_serial_earchive, R.string.column_code);
        }
    }

    
    @Override // com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        try {
            this.mCallback = (IVatChangeListener) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override // com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) throws InterruptedException, Resources.NotFoundException {
        super.onActivityCreated(bundle);
        setVisible();
        load();
    }


    public void onActivityResult(int r3, int r4, Intent intent) {
        super.onActivityResult(r3, r4, intent);
        if (r3 == 999 && -1 == r4) {
            Intrinsics.checkNotNull(intent);
            Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            int r32 = extras.getInt(IntentExtraName.EXTRA_CUSTOMER_REF);
            Bundle extras2 = intent.getExtras();
            Intrinsics.checkNotNull(extras2);
            String string = extras2.getString(IntentExtraName.EXTRA_CUSTOMER_TITLE);
            Bundle extras3 = intent.getExtras();
            Intrinsics.checkNotNull(extras3);
            String string2 = extras3.getString(IntentExtraName.EXTRA_CUSTOMER_CODE);
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            sales.setClCode(string2 == null ? "" : string2);
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            sales2.setClRef(r32);
            TextView textView = this.txtCustomer;
            Intrinsics.checkNotNull(textView);
            if (TextUtils.isEmpty(string)) {
                string = string2;
            }
            textView.setText(string);
            Sales sales3 = getmSales();
            Intrinsics.checkNotNull(sales3);
            if (sales3.getClRef() <= 0) {
                Sales sales4 = getmSales();
                Intrinsics.checkNotNull(sales4);
                if (!TextUtils.isEmpty(sales4.getClCode())) {
                    ImageView imageView = this.imgClearCustomer;
                    Intrinsics.checkNotNull(imageView);
                    imageView.setVisibility(0);
                }
            }
            Sales sales5 = getmSales();
            Intrinsics.checkNotNull(sales5);
            if (sales5.getClRef() > 0) {
                Sales sales6 = getmSales();
                Intrinsics.checkNotNull(sales6);
                FicheRefProp shipAgent = sales6.getShipAgent();
                Sales sales7 = getmSales();
                Intrinsics.checkNotNull(sales7);
                loadShipAgent(shipAgent, R.string.column_code, R.string.qry_ship_agent_clcard, StringUtils.convertIntToString(sales7.getClRef()));
                Sales sales8 = getmSales();
                Intrinsics.checkNotNull(sales8);
                if (sales8.getShipAgent().getLogicalRef() > 0) {
                    TextView textView2 = this.txtShipAgent;
                    Sales sales9 = getmSales();
                    Intrinsics.checkNotNull(sales9);
                    loadTextView(textView2, sales9.getShipAgent().toString(), true);
                }
            }
        }
    }

    private void setVisible() {
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        showShipAgentSelection();
        showCustomerSelection();
        showEDocFields();
        showErpInvoiceType();
        SalesType salesType = getSalesType();
        Intrinsics.checkNotNull(salesType);
        if (SalesUtils.isSalesTypeOrder(salesType)) {
            SalesFicheHeaderFields salesFicheHeaderFields = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields);
            if (salesFicheHeaderFields.isReserveOrder()) {
                setViewVisible(this.lnPaymentOrder);
            }
            setViewVisible(this.lnCustomerOrderNo);
        } else {
            setViewVisibilityGone(this.lnPaymentOrder);
            setViewVisibilityGone(this.lnCustomerOrderNo);
            SalesActivityNew salesActivity = getSalesActivity();
            Intrinsics.checkNotNull(salesActivity);
            if (salesActivity.isCustomerEInvoiceSgkType()) {
                setViewVisible(this.ln_sgk_efatura_bilgileri);
                setViewVisible(this.ln_sgk_einvoice_information_divider);
            }
        }
        if (this.viewModel.erpType() != ErpType.NETSIS) {
            LinearLayout linearLayout3 = this.lnExplanation5;
            Intrinsics.checkNotNull(linearLayout3);
            linearLayout3.setVisibility(8);
            EditText editText = this.edtExplanation5;
            Intrinsics.checkNotNull(editText);
            editText.setVisibility(8);
            LinearLayout linearLayout4 = this.lnExplanation6;
            Intrinsics.checkNotNull(linearLayout4);
            linearLayout4.setVisibility(8);
            EditText editText2 = this.edtExplanation6;
            Intrinsics.checkNotNull(editText2);
            editText2.setVisibility(8);
            LinearLayout linearLayout5 = this.lnExplanation7;
            Intrinsics.checkNotNull(linearLayout5);
            linearLayout5.setVisibility(8);
            EditText editText3 = this.edtExplanation7;
            Intrinsics.checkNotNull(editText3);
            editText3.setVisibility(8);
            LinearLayout linearLayout6 = this.lnExplanation8;
            Intrinsics.checkNotNull(linearLayout6);
            linearLayout6.setVisibility(8);
            EditText editText4 = this.edtExplanation8;
            Intrinsics.checkNotNull(editText4);
            editText4.setVisibility(8);
            LinearLayout linearLayout7 = this.lnExplanation9;
            Intrinsics.checkNotNull(linearLayout7);
            linearLayout7.setVisibility(8);
            EditText editText5 = this.edtExplanation9;
            Intrinsics.checkNotNull(editText5);
            editText5.setVisibility(8);
            LinearLayout linearLayout8 = this.lnExplanation10;
            Intrinsics.checkNotNull(linearLayout8);
            linearLayout8.setVisibility(8);
            EditText editText6 = this.edtExplanation10;
            Intrinsics.checkNotNull(editText6);
            editText6.setVisibility(8);
            LinearLayout linearLayout9 = this.lnSpeCode1;
            Intrinsics.checkNotNull(linearLayout9);
            linearLayout9.setVisibility(8);
            View view = this.dvdSpeCode1;
            Intrinsics.checkNotNull(view);
            view.setVisibility(8);
            LinearLayout linearLayout10 = this.lnSpeCode2;
            Intrinsics.checkNotNull(linearLayout10);
            linearLayout10.setVisibility(8);
            View view2 = this.dvdSpeCode2;
            Intrinsics.checkNotNull(view2);
            view2.setVisibility(8);
        } else {
            String paramValue = this.viewModel.getSqlHelper().getParamValue(ParameterTypes.ptSpeCodeUsage);
            Intrinsics.checkNotNullExpressionValue(paramValue, "getParamValue(...)");
            String[] strArrSplitInitValue = StringUtils.splitInitValue(paramValue, ",", 2);
            ArrayList arrayList = new ArrayList();
            for (String str : strArrSplitInitValue) {
                Intrinsics.checkNotNull(str);
                int length = str.length() - 1;
                int r9 = 0;
                boolean z = false;
                while (r9 <= length) {
                    boolean z2 = Intrinsics.compare(str.charAt(!z ? r9 : length), 32) <= 0;
                    if (z) {
                        if (!z2) {
                            break;
                        } else {
                            length--;
                        }
                    } else if (z2) {
                        r9++;
                    } else {
                        z = true;
                    }
                }
                arrayList.add(str.subSequence(r9, length + 1).toString());
            }
            LinearLayout linearLayout11 = this.lnSpeCode;
            Intrinsics.checkNotNull(linearLayout11);
            linearLayout11.setVisibility(8);
            View view3 = this.dvdSpeCode;
            Intrinsics.checkNotNull(view3);
            view3.setVisibility(8);
            LinearLayout linearLayout12 = this.lnSpeCode1;
            Intrinsics.checkNotNull(linearLayout12);
            linearLayout12.setVisibility(arrayList.contains("1") ? 0 : 8);
            View view4 = this.dvdSpeCode1;
            Intrinsics.checkNotNull(view4);
            view4.setVisibility(arrayList.contains("1") ? 0 : 8);
            LinearLayout linearLayout13 = this.lnSpeCode2;
            Intrinsics.checkNotNull(linearLayout13);
            linearLayout13.setVisibility(arrayList.contains(ExifInterface.GPS_MEASUREMENT_2D) ? 0 : 8);
            View view5 = this.dvdSpeCode2;
            Intrinsics.checkNotNull(view5);
            view5.setVisibility(arrayList.contains(ExifInterface.GPS_MEASUREMENT_2D) ? 0 : 8);
        }
        SalesActivityNew salesActivity2 = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity2);
        if (!salesActivity2.isCustomerEInvoiceSgkType()) {
            View view6 = this.lnDocumentNoDivider;
            Intrinsics.checkNotNull(view6);
            view6.setVisibility(8);
            View view7 = this.lnStartingDateDivider;
            Intrinsics.checkNotNull(view7);
            view7.setVisibility(8);
            View view8 = this.ln_sgk_einvoice_information_divider;
            Intrinsics.checkNotNull(view8);
            view8.setVisibility(8);
            View view9 = this.lnTaxPayerNameDivider;
            Intrinsics.checkNotNull(view9);
            view9.setVisibility(8);
            View view10 = this.lnTaxPayerCodeDivider;
            Intrinsics.checkNotNull(view10);
            view10.setVisibility(8);
            View view11 = this.lnAdditionalInvoiceTypeDivider;
            Intrinsics.checkNotNull(view11);
            view11.setVisibility(8);
            View view12 = this.lnEndDateDivider;
            Intrinsics.checkNotNull(view12);
            view12.setVisibility(8);
        }
        SalesFicheHeaderFields salesFicheHeaderFields2 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields2);
        if (!salesFicheHeaderFields2.isReserveOrder()) {
            View view13 = this.salesPaymentOrderDivider;
            Intrinsics.checkNotNull(view13);
            view13.setVisibility(8);
        }
        SalesFicheHeaderFields salesFicheHeaderFields3 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields3);
        if (!salesFicheHeaderFields3.isIncludeVAT()) {
            View view14 = this.salesDetailVatIncludeDivider;
            Intrinsics.checkNotNull(view14);
            view14.setVisibility(8);
        }
        SalesFicheHeaderFields salesFicheHeaderFields4 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields4);
        if (!salesFicheHeaderFields4.isDeliveryDate()) {
            View view15 = this.salesDeliveryDateDivider;
            Intrinsics.checkNotNull(view15);
            view15.setVisibility(8);
        }
        SalesFicheHeaderFields salesFicheHeaderFields5 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields5);
        if (!salesFicheHeaderFields5.isCustomerOrderNo()) {
            View view16 = this.salesCustomerOrderNoDivider;
            Intrinsics.checkNotNull(view16);
            view16.setVisibility(8);
        }
        SalesType salesType2 = getSalesType();
        Intrinsics.checkNotNull(salesType2);
        if (SalesUtils.isSalesTypeOrderOrDemandOrWhTransfer(salesType2)) {
            View view17 = this.cabinDivider;
            Intrinsics.checkNotNull(view17);
            view17.setVisibility(8);
        }
        SalesType salesType3 = getSalesType();
        Intrinsics.checkNotNull(salesType3);
        if (!SalesUtils.isSalesTypeOrder(salesType3)) {
            View view18 = this.salesDoReserveDivider;
            Intrinsics.checkNotNull(view18);
            view18.setVisibility(8);
        }
        SalesType salesType4 = getSalesType();
        Intrinsics.checkNotNull(salesType4);
        if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(salesType4) && (linearLayout2 = this.lnWareHouse) != null) {
            linearLayout2.setVisibility(8);
        }
        SalesType salesType5 = getSalesType();
        Intrinsics.checkNotNull(salesType5);
        if (SalesUtils.m415xd12e7b8b(salesType5) || (linearLayout = this.lnReturnWareHouse) == null) {
            return;
        }
        linearLayout.setVisibility(8);
    }

    private void showCustomerSelection() {
        LinearLayout linearLayout = this.lnCustomer;
        Intrinsics.checkNotNull(linearLayout);
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        int r2 = 8;
        linearLayout.setVisibility((SalesUtils.isSalesTypeWhTransfer(sales.getmSalesType()) && this.viewModel.erpType() == ErpType.TIGER) ? 0 : 8);
        View view = this.customerDivider;
        Intrinsics.checkNotNull(view);
        Sales sales2 = getmSales();
        Intrinsics.checkNotNull(sales2);
        if (SalesUtils.isSalesTypeWhTransfer(sales2.getmSalesType()) && this.viewModel.erpType() == ErpType.TIGER) {
            r2 = 0;
        }
        view.setVisibility(r2);
    }

    private void showShipAgentSelection() {
        LinearLayout linearLayout = this.lnShipAgent;
        Intrinsics.checkNotNull(linearLayout);
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        int r2 = 8;
        linearLayout.setVisibility((SalesUtils.isSalesTypeWhTransfer(sales.getmSalesType()) && this.viewModel.erpType() == ErpType.TIGER) ? 0 : 8);
        View view = this.dvdShipAgent;
        Intrinsics.checkNotNull(view);
        Sales sales2 = getmSales();
        Intrinsics.checkNotNull(sales2);
        if (SalesUtils.isSalesTypeWhTransfer(sales2.getmSalesType()) && this.viewModel.erpType() == ErpType.TIGER) {
            r2 = 0;
        }
        view.setVisibility(View.VISIBLE);
    }


    private void showEDocFields() {
        int r4;
        View view = this.lnEdespatch;
        Intrinsics.checkNotNull(view);
        view.setVisibility(this.firmUseEDespatch ? 0 : 8);
        View view2 = this.eDespatchDivider;
        Intrinsics.checkNotNull(view2);
        view2.setVisibility(this.firmUseEDespatch ? 0 : 8);
        ErpType erpType = this.viewModel.erpType();
        ErpType erpType2 = ErpType.NETSIS;
        if (erpType == erpType2) {
            if (this.firmUseEDespatch) {
                View view3 = this.lnSerial;
                Intrinsics.checkNotNull(view3);
                if (this.viewModel.erpType() == erpType2 && this.firmUseEDespatch) {
                    Sales sales = getmSales();
                    Intrinsics.checkNotNull(sales);
                    if (sales.getEDespatch().isSelect()) {
                        r4 = 0;
                    }
                } else {
                    r4 = 8;
                }
                view3.setVisibility(r4);
                View view4 = this.dvdSerial;
                Intrinsics.checkNotNull(view4);
                if (this.viewModel.erpType() == erpType2 && this.firmUseEDespatch) {
                    Sales sales2 = getmSales();
                    Intrinsics.checkNotNull(sales2);
                    if (!sales2.getEDespatch().isSelect()) {
                    }
                } else {
                    ı = 8;
                }
                view4.setVisibility(ı);
                return;
            }
            if (this.mAcceptEInvoice || this.mAcceptEArchive) {
                View view5 = this.lnSerial;
                Intrinsics.checkNotNull(view5);
                view5.setVisibility(this.viewModel.erpType() == erpType2 ? 0 : 8);
                View view6 = this.dvdSerial;
                Intrinsics.checkNotNull(view6);
                view6.setVisibility(this.viewModel.erpType() != erpType2 ? 8 : 0);
                return;
            }
            View view7 = this.lnSerial;
            Intrinsics.checkNotNull(view7);
            view7.setVisibility(8);
            View view8 = this.dvdSerial;
            Intrinsics.checkNotNull(view8);
            view8.setVisibility(8);
            return;
        }
        View view9 = this.lnSerial;
        Intrinsics.checkNotNull(view9);
        view9.setVisibility(8);
        View view10 = this.dvdSerial;
        Intrinsics.checkNotNull(view10);
        view10.setVisibility(View.GONE);
    }

    private void showErpInvoiceType() {
        boolean z;
        if (this.viewModel.erpType() != ErpType.NETSIS) {
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            if (!SalesUtils.isSalesTypeInvoiceOrRetailInvoiceOrDispatch(sales.getmSalesType())) {
                Sales sales2 = getmSales();
                Intrinsics.checkNotNull(sales2);
                if (!SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(sales2.getmSalesType())) {
                    Sales sales3 = getmSales();
                    Intrinsics.checkNotNull(sales3);
                    z = SalesUtils.isSalesTypeOrder(sales3.getmSalesType()) && this.viewModel.canOrderCanTransferEInvoiceOrEArchive();
                }
            }
        }
        LinearLayout linearLayout = this.lnErpInvoiceType;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(z ? 0 : 8);
        TextView textView = this.txtErpInvoiceType;
        Intrinsics.checkNotNull(textView);
        textView.setVisibility(z ? 0 : 8);
        View view = this.dvdErpInvoiceType;
        Intrinsics.checkNotNull(view);
        view.setVisibility(z ? 0 : 8);
    }

    @Override // com.proje.mobilesales.core.base.BaseFicheFragment
    public void load() throws InterruptedException, Resources.NotFoundException {
        TextView textView = this.txtBranch;
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        String string = sales.getBranch().toString();
        SalesFicheHeaderFields salesFicheHeaderFields = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields);
        loadTextView(textView, string, salesFicheHeaderFields.isBranch());
        TextView textView2 = this.txtDivision;
        Sales sales2 = getmSales();
        Intrinsics.checkNotNull(sales2);
        String string2 = sales2.getDivision().toString();
        SalesFicheHeaderFields salesFicheHeaderFields2 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields2);
        loadTextView(textView2, string2, salesFicheHeaderFields2.isDivision());
        TextView textView3 = this.txtFactory;
        Sales sales3 = getmSales();
        Intrinsics.checkNotNull(sales3);
        String string3 = sales3.getFactory().toString();
        SalesFicheHeaderFields salesFicheHeaderFields3 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields3);
        loadTextView(textView3, string3, salesFicheHeaderFields3.isFactory());
        loadWarehouses();
        TextView textView4 = this.txtSpeCode;
        Sales sales4 = getmSales();
        Intrinsics.checkNotNull(sales4);
        String string4 = sales4.getSpeCode().toString();
        SalesFicheHeaderFields salesFicheHeaderFields4 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields4);
        loadTextView(textView4, string4, salesFicheHeaderFields4.isSpeCode());
        TextView textView5 = this.txtWarrantyCode;
        Sales sales5 = getmSales();
        Intrinsics.checkNotNull(sales5);
        String string5 = sales5.getWarrantyCode().toString();
        SalesFicheHeaderFields salesFicheHeaderFields5 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields5);
        loadTextView(textView5, string5, salesFicheHeaderFields5.isWarrantyCode());
        EditText editText = this.edtDocumentNo;
        Sales sales6 = getmSales();
        Intrinsics.checkNotNull(sales6);
        String string6 = sales6.getDocumentNo().toString();
        SalesFicheHeaderFields salesFicheHeaderFields6 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields6);
        loadTextView(editText, string6, salesFicheHeaderFields6.isDocumentNo());
        EditText editText2 = this.edtDocumentTrackNo;
        Sales sales7 = getmSales();
        Intrinsics.checkNotNull(sales7);
        loadTextView(editText2, sales7.getDocumentTrackingNo().toString());
        EditText editText3 = this.edtExplanation1;
        Sales sales8 = getmSales();
        Intrinsics.checkNotNull(sales8);
        String string7 = sales8.getExplanation1().toString();
        SalesFicheHeaderFields salesFicheHeaderFields7 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields7);
        loadTextView(editText3, string7, salesFicheHeaderFields7.isExplanation1());
        EditText editText4 = this.edtExplanation2;
        Sales sales9 = getmSales();
        Intrinsics.checkNotNull(sales9);
        String string8 = sales9.getExplanation2().toString();
        SalesFicheHeaderFields salesFicheHeaderFields8 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields8);
        loadTextView(editText4, string8, salesFicheHeaderFields8.isExplanation2());
        EditText editText5 = this.edtExplanation3;
        Sales sales10 = getmSales();
        Intrinsics.checkNotNull(sales10);
        String string9 = sales10.getExplanation3().toString();
        SalesFicheHeaderFields salesFicheHeaderFields9 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields9);
        loadTextView(editText5, string9, salesFicheHeaderFields9.isExplanation3());
        EditText editText6 = this.edtExplanation4;
        Sales sales11 = getmSales();
        Intrinsics.checkNotNull(sales11);
        String string10 = sales11.getExplanation4().toString();
        SalesFicheHeaderFields salesFicheHeaderFields10 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields10);
        loadTextView(editText6, string10, salesFicheHeaderFields10.isExplanation4());
        ErpType erpType = this.viewModel.erpType();
        ErpType erpType2 = ErpType.NETSIS;
        if (erpType == erpType2) {
            EditText editText7 = this.edtExplanation5;
            Sales sales12 = getmSales();
            Intrinsics.checkNotNull(sales12);
            String string11 = sales12.getExplanation5().toString();
            SalesFicheHeaderFields salesFicheHeaderFields11 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields11);
            loadTextView(editText7, string11, salesFicheHeaderFields11.isExplanation5());
            EditText editText8 = this.edtExplanation6;
            Sales sales13 = getmSales();
            Intrinsics.checkNotNull(sales13);
            String string12 = sales13.getExplanation6().toString();
            SalesFicheHeaderFields salesFicheHeaderFields12 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields12);
            loadTextView(editText8, string12, salesFicheHeaderFields12.isExplanation6());
            EditText editText9 = this.edtExplanation7;
            Sales sales14 = getmSales();
            Intrinsics.checkNotNull(sales14);
            String string13 = sales14.getExplanation7().toString();
            SalesFicheHeaderFields salesFicheHeaderFields13 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields13);
            loadTextView(editText9, string13, salesFicheHeaderFields13.isExplanation7());
            EditText editText10 = this.edtExplanation8;
            Sales sales15 = getmSales();
            Intrinsics.checkNotNull(sales15);
            String string14 = sales15.getExplanation8().toString();
            SalesFicheHeaderFields salesFicheHeaderFields14 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields14);
            loadTextView(editText10, string14, salesFicheHeaderFields14.isExplanation8());
            EditText editText11 = this.edtExplanation9;
            Sales sales16 = getmSales();
            Intrinsics.checkNotNull(sales16);
            String string15 = sales16.getExplanation9().toString();
            SalesFicheHeaderFields salesFicheHeaderFields15 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields15);
            loadTextView(editText11, string15, salesFicheHeaderFields15.isExplanation9());
            EditText editText12 = this.edtExplanation10;
            Sales sales17 = getmSales();
            Intrinsics.checkNotNull(sales17);
            String string16 = sales17.getExplanation10().toString();
            SalesFicheHeaderFields salesFicheHeaderFields16 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields16);
            loadTextView(editText12, string16, salesFicheHeaderFields16.isExplanation10());
        }
        SalesType salesType = getSalesType();
        Intrinsics.checkNotNull(salesType);
        boolean z = false;
        if (SalesUtils.isSalesTypeOrderOrDemand(salesType)) {
            EditText editText13 = this.edtCustomerOrderNo;
            Sales sales18 = getmSales();
            Intrinsics.checkNotNull(sales18);
            loadTextView(editText13, sales18.getCustomerOrderNo().toString());
            CheckBox checkBox = this.chkPaymentOrder;
            Sales sales19 = getmSales();
            Intrinsics.checkNotNull(sales19);
            boolean zIsSelect = sales19.getPaymentOrder().isSelect();
            SalesFicheHeaderFields salesFicheHeaderFields17 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields17);
            loadCheckView(checkBox, zIsSelect, salesFicheHeaderFields17.isPayment());
            TextView textView6 = this.txtDeliveryDate;
            Sales sales20 = getmSales();
            Intrinsics.checkNotNull(sales20);
            String string17 = sales20.getDeliveryDate().toString();
            SalesFicheHeaderFields salesFicheHeaderFields18 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields18);
            loadTextView(textView6, string17, salesFicheHeaderFields18.isDeliveryDate());
            SalesType salesType2 = getSalesType();
            Intrinsics.checkNotNull(salesType2);
            if (SalesUtils.isSalesTypeOrder(salesType2)) {
                CheckBox checkBox2 = this.chkSalesDoReserve;
                Intrinsics.checkNotNull(checkBox2);
                checkBox2.setTag("NOK");
                CheckBox checkBox3 = this.chkSalesDoReserve;
                Sales sales21 = getmSales();
                Intrinsics.checkNotNull(sales21);
                loadCheckView(checkBox3, sales21.getReserved().isSelect(), this.viewModel.erpType() == erpType2);
                CheckBox checkBox4 = this.chkSalesDoReserve;
                Intrinsics.checkNotNull(checkBox4);
                checkBox4.setTag("OK");
            }
        } else if (!SalesUtils.isSalesTypeWhTransfer(getSalesType())) {
            CheckBox checkBox5 = this.chkSalesConsignee;
            Sales sales22 = getmSales();
            Intrinsics.checkNotNull(sales22);
            boolean zIsSelect2 = sales22.getConsignee().isSelect();
            SalesFicheHeaderFields salesFicheHeaderFields19 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields19);
            loadCheckView(checkBox5, zIsSelect2, salesFicheHeaderFields19.isConsignee());
        }
        CheckBox checkBox6 = this.chkSalesDetailIncludeVat;
        Sales sales23 = getmSales();
        Intrinsics.checkNotNull(sales23);
        boolean zIsSelect3 = sales23.getIncludeVat().isSelect();
        SalesFicheHeaderFields salesFicheHeaderFields20 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields20);
        loadCheckView(checkBox6, zIsSelect3, salesFicheHeaderFields20.isIncludeVAT());
        EditText editText14 = this.edt_document_no;
        Sales sales24 = getmSales();
        Intrinsics.checkNotNull(sales24);
        String string18 = sales24.getEInvoiceSGKDocumentNo().toString();
        SalesActivityNew salesActivity = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity);
        loadTextView(editText14, string18, salesActivity.isCustomerEInvoiceSgkType());
        EditText editText15 = this.edt_taxpayer_code;
        Sales sales25 = getmSales();
        Intrinsics.checkNotNull(sales25);
        String string19 = sales25.getTaxPayerCode().toString();
        SalesActivityNew salesActivity2 = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity2);
        loadTextView(editText15, string19, salesActivity2.isCustomerEInvoiceSgkType());
        EditText editText16 = this.edt_taxpayer_name;
        Sales sales26 = getmSales();
        Intrinsics.checkNotNull(sales26);
        String string20 = sales26.getTaxPayerName().toString();
        SalesActivityNew salesActivity3 = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity3);
        loadTextView(editText16, string20, salesActivity3.isCustomerEInvoiceSgkType());
        TextView textView7 = this.txt_additional_invoice_type;
        Sales sales27 = getmSales();
        Intrinsics.checkNotNull(sales27);
        int logicalRef = sales27.getEInvoiceTypSgk().getLogicalRef();
        SalesActivityNew salesActivity4 = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity4);
        loadTextViewStringArray(textView7, R.array.additional_invoice_type_values, logicalRef, salesActivity4.isCustomerEInvoiceSgkType());
        TextView textView8 = this.txt_starting_date;
        Sales sales28 = getmSales();
        Intrinsics.checkNotNull(sales28);
        String string21 = sales28.getBeginDate().toString();
        SalesActivityNew salesActivity5 = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity5);
        loadTextView(textView8, string21, salesActivity5.isCustomerEInvoiceSgkType());
        TextView textView9 = this.txt_end_date;
        Sales sales29 = getmSales();
        Intrinsics.checkNotNull(sales29);
        String string22 = sales29.getEndDate().toString();
        SalesActivityNew salesActivity6 = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity6);
        loadTextView(textView9, string22, salesActivity6.isCustomerEInvoiceSgkType());
        TextView textView10 = this.txt_cabin;
        Sales sales30 = getmSales();
        Intrinsics.checkNotNull(sales30);
        String string23 = sales30.getCabin().toString();
        Intrinsics.checkNotNull(getSalesType());
        loadTextView(textView10, string23, !SalesUtils.isSalesTypeOrderOrDemand(r5));
        loadExtraSpecodes();
        CheckBox checkBox7 = this.chkEdespatch;
        Sales sales31 = getmSales();
        Intrinsics.checkNotNull(sales31);
        loadCheckView(checkBox7, sales31.getEDespatch().isSelect(), this.firmUseEDespatch);
        Sales sales32 = getmSales();
        Intrinsics.checkNotNull(sales32);
        if (SalesUtils.isSalesTypeOrderOrInvoiceOrRetailInvoice(sales32.getmSalesType())) {
            CheckBox checkBox8 = this.chkInsteadOfEdespatch;
            Sales sales33 = getmSales();
            Intrinsics.checkNotNull(sales33);
            loadCheckView(checkBox8, sales33.getInsteadOfEDespatch().isSelect(), this.insteadOfEDespatch);
        }
        TextView textView11 = this.txtSerial;
        Sales sales34 = getmSales();
        Intrinsics.checkNotNull(sales34);
        String string24 = sales34.getEDocSerial().toString();
        if ((this.firmUseEDespatch || this.mAcceptEArchive || this.mAcceptEInvoice) && this.viewModel.erpType() == erpType2) {
            z = true;
        }
        loadTextView(textView11, string24, z);
        loadCustomerInfo();
        TextView textView12 = this.txtShipAgent;
        Sales sales35 = getmSales();
        Intrinsics.checkNotNull(sales35);
        loadTextView(textView12, sales35.getShipAgent().toString(), true);
        Sales sales36 = getmSales();
        Intrinsics.checkNotNull(sales36);
        if (sales36.getErpInvoiceType().getLogicalRef() >= 0) {
            Sales sales37 = getmSales();
            Intrinsics.checkNotNull(sales37);
            if (TextUtils.isEmpty(sales37.getErpInvoiceType().getDefinition())) {
                Sales sales38 = getmSales();
                Intrinsics.checkNotNull(sales38);
                FicheRefProp erpInvoiceType = sales38.getErpInvoiceType();
                ArrayList<String> arrayList = this.allErpInvoiceTypes;
                Sales sales39 = getmSales();
                Intrinsics.checkNotNull(sales39);
                FicheStringProp.setDefinition(arrayList.get(sales39.getErpInvoiceType().getLogicalRef()));
            }
            Sales sales40 = getmSales();
            Intrinsics.checkNotNull(sales40);
            if (sales40.getErpInvoiceType().getWhich() == -1) {
                ArrayList<String> arrayList2 = this.availableErpInvoiceTypes;
                Intrinsics.checkNotNull(arrayList2);
                Sales sales41 = getmSales();
                Intrinsics.checkNotNull(sales41);
                int r0 = arrayList2.indexOf(sales41.getErpInvoiceType().getDefinition());
                Sales sales42 = getmSales();
                Intrinsics.checkNotNull(sales42);
                sales42.getErpInvoiceType().setWhich(r0);
            }
            TextView textView13 = this.txtErpInvoiceType;
            Sales sales43 = getmSales();
            Intrinsics.checkNotNull(sales43);
            loadTextView(textView13, sales43.getErpInvoiceType().toString(), true);
        }
    }

    private void loadWarehouses() {
        SalesType salesType = getSalesType();
        Intrinsics.checkNotNull(salesType);
        if (SalesUtils.isSalesTypeOneToOne(salesType)) {
            loadWarehousesForOneToOneSales();
        } else {
            SalesType salesType2 = getSalesType();
            Intrinsics.checkNotNull(salesType2);
            if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(salesType2)) {
                loadReturnWarehouseForSales();
            } else {
                loadWarehouseForOtherSales();
            }
        }
        if (this.baseErp.getErpType() == ErpType.TIGER) {
            SalesType salesType3 = getSalesType();
            Intrinsics.checkNotNull(salesType3);
            if (SalesUtils.isSalesTypeDemand(salesType3)) {
                loadSourceWarehouseForTiger();
            }
        }
    }

    private void loadWarehousesForOneToOneSales() {
        if (getSalesFicheMode() == FicheMode.ANALYSE) {
            loadWarehouseTextViews(SalesHeaderWarehouseUtils.getWarehouseName("Invoice", getmSales()), SalesHeaderWarehouseUtils.getReturnWarehouseName("Invoice", getmSales()));
            return;
        }
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        String string = sales.getWareHouse().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        Sales sales2 = getmSales();
        Intrinsics.checkNotNull(sales2);
        String string2 = sales2.getReturnWareHouse().toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        loadWarehouseTextViews(string, string2);
    }

    private void loadReturnWarehouseForSales() {
        String string;
        if (getSalesFicheMode() == FicheMode.ANALYSE) {
            string = SalesHeaderWarehouseUtils.getReturnWarehouseName("Invoice", getmSales());
        } else {
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            string = sales.getReturnWareHouse().toString();
            Intrinsics.checkNotNull(string);
        }
        loadTextView(this.txtReturnWareHouse, string);
    }

    private void loadWarehouseForOtherSales() {
        String string;
        if (getSalesFicheMode() == FicheMode.ANALYSE) {
            SalesType salesType = getSalesType();
            Intrinsics.checkNotNull(salesType);
            if (SalesUtils.isSalesTypeOrderOrDemand(salesType)) {
                string = SalesHeaderWarehouseUtils.getWarehouseName("Order", getmSales());
            } else if (SalesUtils.isSalesTypeWhTransfer(getSalesType())) {
                string = SalesHeaderWarehouseUtils.getWarehouseName("WhTransfer", getmSales());
            } else {
                string = SalesHeaderWarehouseUtils.getWarehouseName("Invoice", getmSales());
            }
        } else {
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            string = sales.getWareHouse().toString();
            Intrinsics.checkNotNull(string);
        }
        loadTextView(this.txtWareHouse, string);
    }

    private void loadSourceWarehouseForTiger() {
        String string;
        if (getSalesFicheMode() == FicheMode.ANALYSE) {
            string = SalesHeaderWarehouseUtils.getSourceWarehouseName("Order", getmSales());
        } else {
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            string = sales.getSourceWareHouse().toString();
            Intrinsics.checkNotNull(string);
        }
        loadTextView(this.txtSourceWareHouse, string);
    }

    private void loadWarehouseTextViews(String str, String str2) {
        loadTextView(this.txtWareHouse, str);
        loadTextView(this.txtReturnWareHouse, str2);
    }

    private void loadCustomerInfo() throws InterruptedException {
        String definition;
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        if (SalesUtils.isSalesTypeWhTransfer(sales.getmSalesType()) && this.viewModel.erpType() == ErpType.TIGER) {
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            if (sales2.getClRef() != 0) {
                SalesNewViewModel salesNewViewModel = this.viewModel;
                Sales sales3 = getmSales();
                Intrinsics.checkNotNull(sales3);
                ClCard customerInfo = salesNewViewModel.getCustomerInfo(sales3.getClRef());
                if (customerInfo != null) {
                    definition = !TextUtils.isEmpty(customerInfo.getDefinition()) ? customerInfo.getDefinition() : customerInfo.getCode();
                }
            }
        } else {
            definition = "";
        }
        TextView textView = this.txtCustomer;
        Sales sales4 = getmSales();
        Intrinsics.checkNotNull(sales4);
        loadTextView(textView, definition, SalesUtils.isSalesTypeWhTransfer(sales4.getmSalesType()) && this.viewModel.erpType() == ErpType.TIGER);
        if (getSalesFicheMode() != FicheMode.ANALYSE) {
            if (TextUtils.isEmpty(definition)) {
                ImageView imageView = this.imgClearCustomer;
                Intrinsics.checkNotNull(imageView);
                imageView.setVisibility(8);
            } else {
                ImageView imageView2 = this.imgClearCustomer;
                Intrinsics.checkNotNull(imageView2);
                imageView2.setVisibility(0);
            }
        }
    }

    private void loadExtraSpecodes() throws InterruptedException {
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        if (!TextUtils.isEmpty(sales.getFirstSpeCode().getCode())) {
            SalesNewViewModel salesNewViewModel = this.viewModel;
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            String code = sales2.getFirstSpeCode().getCode();
            Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
            List<Specodes> iSqlHelperSpecodes = salesNewViewModel.getISqlHelperSpecodes(Specodes.class, "SPECODETYPE=1 AND SPECODE=?", new String[]{code});
            if (iSqlHelperSpecodes != null && !iSqlHelperSpecodes.isEmpty()) {
                Sales sales3 = getmSales();
                Intrinsics.checkNotNull(sales3);
                FicheStringProp.setDefinition(iSqlHelperSpecodes.get(0).getDefinition());
                TextView textView = this.txtSpeCode1;
                Sales sales4 = getmSales();
                Intrinsics.checkNotNull(sales4);
                loadTextView(textView, sales4.getFirstSpeCode().toString(), true);
            }
        }
        Sales sales5 = getmSales();
        Intrinsics.checkNotNull(sales5);
        if (TextUtils.isEmpty(sales5.getSecondSpeCode().getCode())) {
            return;
        }
        SalesNewViewModel salesNewViewModel2 = this.viewModel;
        Sales sales6 = getmSales();
        Intrinsics.checkNotNull(sales6);
        String code2 = sales6.getSecondSpeCode().getCode();
        Intrinsics.checkNotNullExpressionValue(code2, "getCode(...)");
        List<Specodes> iSqlHelperSpecodes2 = salesNewViewModel2.getISqlHelperSpecodes(Specodes.class, "SPECODETYPE=2 AND SPECODE=?", new String[]{code2});
        if (iSqlHelperSpecodes2 == null || iSqlHelperSpecodes2.isEmpty()) {
            return;
        }
        Sales sales7 = getmSales();
        Intrinsics.checkNotNull(sales7);
        FicheStringProp.setDefinition(iSqlHelperSpecodes2.get(0).getDefinition());
        TextView textView2 = this.txtSpeCode2;
        Sales sales8 = getmSales();
        Intrinsics.checkNotNull(sales8);
        loadTextView(textView2, sales8.getSecondSpeCode().toString(), true);
    }

    public void createDateAlertDialogDeliveryDate(FicheMode ficheMode, final View view, final TextView textView, final FicheDateProp ficheDateProp, boolean z, @StringRes final int r12) {
        Intrinsics.checkNotNullParameter(ficheMode, "ficheMode");
        Intrinsics.checkNotNullParameter(ficheDateProp, "ficheDateProp");
        if (!z) {
            setViewVisibilityGone(view);
        } else if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else {
            Intrinsics.checkNotNull(view);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SalesHeaderFragment.createDateAlertDialogDeliveryDatelambda15(this.f0, r12, ficheDateProp, view, textView, view2);
                }
            });
        }
    }

    
    public static void createDateAlertDialogDeliveryDatelambda15(final SalesHeaderFragment this0, int r8, final FicheDateProp ficheDateProp, final View view, final TextView textView, View view2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(ficheDateProp, "ficheDateProp");
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda8
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public void onDateSet(DatePicker datePicker, int r11, int r12, int r13) {
                SalesHeaderFragment.createDateAlertDialogDeliveryDatelambda15lambda13(calendar, ficheDateProp, view, this0, textView, datePicker, r11, r12, r13);
            }
        };
        Context context = this0.getContext();
        Intrinsics.checkNotNull(context);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, onDateSetListener, calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.setTitle(StringUtils.catStringSpace(this0.getString(r8), this0.getString(R.string.str_select_text)));
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda9
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                SalesHeaderFragment.createDateAlertDialogDeliveryDatelambda15lambda14(ficheDateProp, textView, dialogInterface);
            }
        });
        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis() - 1000);
        datePickerDialog.show();
    }

    
    public static void createDateAlertDialogDeliveryDatelambda15lambda13(Calendar calendar, FicheDateProp ficheDateProp, View view, SalesHeaderFragment this0, TextView textView, DatePicker datePicker, int r8, int r9, int r10) {
        Intrinsics.checkNotNullParameter(ficheDateProp, "ficheDateProp");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(datePicker, "datePicker");
        calendar.set(1, r8);
        calendar.set(2, r9);
        calendar.set(5, r10);
        datePicker.setMinDate(calendar.getTimeInMillis() - 1000);
        FicheStringProp.setDefinition(DateAndTimeUtils.calendarDateDMY(calendar));
        if (Intrinsics.areEqual(view, this0.lnDeliveryDate)) {
            Sales sales = this0.getmSales();
            Intrinsics.checkNotNull(sales);
            ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList);
            Iterator<SalesDetail> it = mSalesDetailList.iterator();
            while (it.hasNext()) {
                it.next();
                FicheStringProp.setDefinition(ficheDateProp.toString());
            }
        }
        Intrinsics.checkNotNull(textView);
        textView.setText(ficheDateProp.toString());
    }

    
    public static void createDateAlertDialogDeliveryDatelambda15lambda14(FicheDateProp ficheDateProp, TextView textView, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(ficheDateProp, "ficheDateProp");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        ficheDateProp.reset();
        Intrinsics.checkNotNull(textView);
        textView.setText(ficheDateProp.toString());
        dialogInterface.dismiss();
    }

    private void createSingleAlertCursorSalesBranch(FicheMode ficheMode, View view, final TextView textView, final FicheRefProp ficheRefProp, final FicheRefProp ficheRefProp2, final TextView textView2, final FicheRefProp ficheRefProp3, final TextView textView3, final FicheRefProp ficheRefProp4, final TextView textView4, boolean z, @StringRes final int r29, @StringRes final int r30, @StringRes final int r31, @StringRes final int r32, @StringRes int r33, final boolean z2, final String... strArr) {
        if (!z) {
            setViewVisibilityGone(view);
        } else {
            if (ficheMode == FicheMode.ANALYSE) {
                setViewDisabled(textView);
                return;
            }
            loadTextView(textView, ficheRefProp.toString());
            Intrinsics.checkNotNull(view);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SalesHeaderFragment.createSingleAlertCursorSalesBranchlambda18(this.f0, r31, strArr, r30, r29, z2, ficheRefProp, r32, textView, ficheRefProp2, textView2, ficheRefProp3, textView3, ficheRefProp4, textView4, view2);
                }
            });
        }
    }

    
    public static void createSingleAlertCursorSalesBranchlambda18(final SalesHeaderFragment this0, int r18, String[] args, int r20, int r21, boolean z, final FicheRefProp ficheRefProp, final int r24, final TextView textView, final FicheRefProp resetFactoryFicheRefProp, final TextView textView2, final FicheRefProp resetWareHouseFicheRefProp, final TextView textView3, final FicheRefProp ficheRefProp2, final TextView textView4, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(resetFactoryFicheRefProp, "resetFactoryFicheRefProp");
        Intrinsics.checkNotNullParameter(resetWareHouseFicheRefProp, "resetWareHouseFicheRefProp");
        AlertDialogBuilder<?> alertDialogBuilder = this0.mAlertDialogBuilderB;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.dismiss();
        final Cursor cursor = this0.getCursor(this0.getString(r18), Arrays.copyOf(args, args.length));
        if (cursor == null || cursor.getCount() == 0) {
            if (r20 != -1) {
                Toast.makeText(this0.getActivity(), this0.getString(r20), 0).show();
                return;
            } else {
                Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), 0).show();
                return;
            }
        }
        String string = this0.getString(r21);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (z) {
            string = StringUtils.catStringSpace(this0.getString(r21), this0.getString(R.string.str_select_text));
        }
        AlertDialogBuilder<?> alertDialogBuilder2 = this0.mAlertDialogBuilderB;
        Intrinsics.checkNotNull(alertDialogBuilder2);
        alertDialogBuilder2.setTitle(string).setSingleChoiceItems(cursor, ficheRefProp.getWhich(), this0.getString(r24), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda14
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r15) {
                SalesHeaderFragment.createSingleAlertCursorSalesBranchlambda18lambda16(cursor, ficheRefProp, this0, r24, textView, resetFactoryFicheRefProp, textView2, resetWareHouseFicheRefProp, textView3, ficheRefProp2, textView4, dialogInterface, r15);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda15
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r14) {
                SalesHeaderFragment.createSingleAlertCursorSalesBranchlambda18lambda17(ficheRefProp, textView, this0, resetFactoryFicheRefProp, textView2, resetWareHouseFicheRefProp, textView3, ficheRefProp2, textView4, cursor, dialogInterface, r14);
            }
        }).create().show();
    }

    
    public static void createSingleAlertCursorSalesBranchlambda18lambda16(Cursor cursor, FicheRefProp ficheRefProp, SalesHeaderFragment this0, int r23, TextView textView, FicheRefProp resetFactoryFicheRefProp, TextView textView2, FicheRefProp resetWareHouseFicheRefProp, TextView textView3, FicheRefProp ficheRefProp2, TextView textView4, DialogInterface dialog, int r32) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(resetFactoryFicheRefProp, "resetFactoryFicheRefProp");
        Intrinsics.checkNotNullParameter(resetWareHouseFicheRefProp, "resetWareHouseFicheRefProp");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (cursor.moveToPosition(r32)) {
            ficheRefProp.setWhich(r32);
            ficheRefProp.setLogicalRef(cursor.getInt(cursor.getColumnIndex(this0.getString(R.string.column_id))));
            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(this0.getString(r23))));
            Intrinsics.checkNotNull(textView);
            textView.setText(ficheRefProp.getDefinition());
            SalesFicheUserRights salesFicheUserRights = this0.getSalesFicheUserRights();
            Intrinsics.checkNotNull(salesFicheUserRights);
            if (salesFicheUserRights.isDivFactWareHouseControl()) {
                resetFactoryFicheRefProp.reset();
                Intrinsics.checkNotNull(textView2);
                textView2.setText(resetFactoryFicheRefProp.toString());
                resetWareHouseFicheRefProp.reset();
                Intrinsics.checkNotNull(textView3);
                textView3.setText(resetWareHouseFicheRefProp.toString());
                SalesType salesType = this0.getSalesType();
                Intrinsics.checkNotNull(salesType);
                if (SalesUtils.m415xd12e7b8b(salesType)) {
                    if (ficheRefProp2 != null) {
                        ficheRefProp2.reset();
                    }
                    if (textView4 != null) {
                        textView4.setText(String.valueOf(ficheRefProp2));
                    }
                }
                FicheMode salesFicheMode = this0.getSalesFicheMode();
                Intrinsics.checkNotNullExpressionValue(salesFicheMode, "getSalesFicheMode(...)");
                LinearLayout linearLayout = this0.lnFactory;
                TextView textView5 = this0.txtFactory;
                Sales sales = this0.getmSales();
                Intrinsics.checkNotNull(sales);
                FicheRefProp factory = sales.getFactory();
                Sales sales2 = this0.getmSales();
                Intrinsics.checkNotNull(sales2);
                FicheRefProp wareHouse = sales2.getWareHouse();
                TextView textView6 = this0.txtWareHouse;
                Sales sales3 = this0.getmSales();
                FicheRefProp returnWareHouse = sales3 != null ? sales3.getReturnWareHouse() : null;
                TextView textView7 = this0.txtReturnWareHouse;
                SalesFicheHeaderFields salesFicheHeaderFields = this0.getSalesFicheHeaderFields();
                Intrinsics.checkNotNull(salesFicheHeaderFields);
                this0.createSingleAlertCursorSalesFactory(salesFicheMode, linearLayout, textView5, factory, wareHouse, textView6, returnWareHouse, textView7, salesFicheHeaderFields.isFactory(), R.string.str_factory, -1, R.string.qry_factory, R.string.column_code, -1, true);
            }
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialog.dismiss();
        if (ficheRefProp.getPropChangeListener() != null) {
            ficheRefProp.getPropChangeListener().onChange();
        }
    }

    
    public static void createSingleAlertCursorSalesBranchlambda18lambda17(FicheRefProp ficheRefProp, TextView textView, SalesHeaderFragment this0, FicheRefProp resetFactoryFicheRefProp, TextView textView2, FicheRefProp resetWareHouseFicheRefProp, TextView textView3, FicheRefProp ficheRefProp2, TextView textView4, Cursor cursor, DialogInterface dialog, int r29) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(resetFactoryFicheRefProp, "resetFactoryFicheRefProp");
        Intrinsics.checkNotNullParameter(resetWareHouseFicheRefProp, "resetWareHouseFicheRefProp");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        ficheRefProp.reset();
        Intrinsics.checkNotNull(textView);
        textView.setText(ficheRefProp.toString());
        SalesFicheUserRights salesFicheUserRights = this0.getSalesFicheUserRights();
        Intrinsics.checkNotNull(salesFicheUserRights);
        if (salesFicheUserRights.isDivFactWareHouseControl()) {
            resetFactoryFicheRefProp.reset();
            Intrinsics.checkNotNull(textView2);
            textView2.setText(resetFactoryFicheRefProp.toString());
            resetWareHouseFicheRefProp.reset();
            Intrinsics.checkNotNull(textView3);
            textView3.setText(resetWareHouseFicheRefProp.toString());
            SalesType salesType = this0.getSalesType();
            Intrinsics.checkNotNull(salesType);
            if (SalesUtils.m415xd12e7b8b(salesType)) {
                if (ficheRefProp2 != null) {
                    ficheRefProp2.reset();
                }
                if (textView4 != null) {
                    textView4.setText(String.valueOf(ficheRefProp2));
                }
            }
            FicheMode salesFicheMode = this0.getSalesFicheMode();
            Intrinsics.checkNotNullExpressionValue(salesFicheMode, "getSalesFicheMode(...)");
            LinearLayout linearLayout = this0.lnFactory;
            TextView textView5 = this0.txtFactory;
            Sales sales = this0.getmSales();
            Intrinsics.checkNotNull(sales);
            FicheRefProp factory = sales.getFactory();
            Sales sales2 = this0.getmSales();
            Intrinsics.checkNotNull(sales2);
            FicheRefProp wareHouse = sales2.getWareHouse();
            TextView textView6 = this0.txtWareHouse;
            Sales sales3 = this0.getmSales();
            FicheRefProp returnWareHouse = sales3 != null ? sales3.getReturnWareHouse() : null;
            TextView textView7 = this0.txtReturnWareHouse;
            SalesFicheHeaderFields salesFicheHeaderFields = this0.getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields);
            this0.createSingleAlertCursorSalesFactory(salesFicheMode, linearLayout, textView5, factory, wareHouse, textView6, returnWareHouse, textView7, salesFicheHeaderFields.isFactory(), R.string.str_factory, -1, R.string.qry_factory, R.string.column_code, -1, true);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialog.dismiss();
    }

    private void createSingleAlertCursorSalesFactory(FicheMode ficheMode, View view, final TextView textView, final FicheRefProp ficheRefProp, final FicheRefProp ficheRefProp2, final TextView textView2, final FicheRefProp ficheRefProp3, final TextView textView3, boolean z, @StringRes final int r26, @StringRes final int r27, @StringRes final int r28, @StringRes final int r29, @StringRes int r30, final boolean z2, final String... strArr) {
        if (!z) {
            setViewVisibilityGone(view);
        } else {
            if (ficheMode == FicheMode.ANALYSE) {
                setViewDisabled(textView);
                return;
            }
            loadTextView(textView, ficheRefProp.toString());
            Intrinsics.checkNotNull(view);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda13
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SalesHeaderFragment.createSingleAlertCursorSalesFactorylambda21(this.f0, r28, strArr, r27, r26, z2, ficheRefProp, r29, textView, ficheRefProp2, textView2, ficheRefProp3, textView3, view2);
                }
            });
        }
    }

    
    public static void createSingleAlertCursorSalesFactorylambda21(final SalesHeaderFragment this0, int r17, String[] args, int r19, int r20, boolean z, final FicheRefProp ficheRefProp, final int r23, final TextView textView, final FicheRefProp resetWareHouseFicheRefProp, final TextView textView2, final FicheRefProp ficheRefProp2, final TextView textView3, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(resetWareHouseFicheRefProp, "resetWareHouseFicheRefProp");
        AlertDialogBuilder<?> alertDialogBuilder = this0.mAlertDialogBuilderB;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.dismiss();
        final Cursor cursor = this0.getCursor(this0.getString(r17), Arrays.copyOf(args, args.length));
        if (cursor == null || cursor.getCount() == 0) {
            if (r19 != -1) {
                Toast.makeText(this0.getActivity(), this0.getString(r19), 0).show();
                return;
            } else {
                Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), 0).show();
                return;
            }
        }
        String string = this0.getString(r20);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (z) {
            string = StringUtils.catStringSpace(this0.getString(r20), this0.getString(R.string.str_select_text));
        }
        AlertDialogBuilder<?> alertDialogBuilder2 = this0.mAlertDialogBuilderB;
        Intrinsics.checkNotNull(alertDialogBuilder2);
        alertDialogBuilder2.setTitle(string).setSingleChoiceItems(cursor, ficheRefProp.getWhich(), this0.getString(r23), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda16
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r13) {
                SalesHeaderFragment.createSingleAlertCursorSalesFactorylambda21lambda19(cursor, ficheRefProp, this0, r23, textView, resetWareHouseFicheRefProp, textView2, ficheRefProp2, textView3, dialogInterface, r13);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda17
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r12) {
                SalesHeaderFragment.createSingleAlertCursorSalesFactorylambda21lambda20(ficheRefProp, textView, this0, resetWareHouseFicheRefProp, textView2, ficheRefProp2, textView3, cursor, dialogInterface, r12);
            }
        }).create().show();
    }

    
    public static void createSingleAlertCursorSalesFactorylambda21lambda19(Cursor cursor, FicheRefProp ficheRefProp, SalesHeaderFragment this0, int r21, TextView textView, FicheRefProp resetWareHouseFicheRefProp, TextView textView2, FicheRefProp ficheRefProp2, TextView textView3, DialogInterface dialog, int r28) {
        String[] strArr;
        String[] strArr2;
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(resetWareHouseFicheRefProp, "resetWareHouseFicheRefProp");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (cursor.moveToPosition(r28)) {
            ficheRefProp.setWhich(r28);
            ficheRefProp.setLogicalRef(cursor.getInt(cursor.getColumnIndex(this0.getString(R.string.column_id))));
            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(this0.getString(r21))));
            Intrinsics.checkNotNull(textView);
            textView.setText(ficheRefProp.getDefinition());
            SalesFicheUserRights salesFicheUserRights = this0.getSalesFicheUserRights();
            Intrinsics.checkNotNull(salesFicheUserRights);
            if (salesFicheUserRights.isDivFactWareHouseControl()) {
                resetWareHouseFicheRefProp.reset();
                Intrinsics.checkNotNull(textView2);
                textView2.setText(resetWareHouseFicheRefProp.toString());
                SalesType salesType = this0.getSalesType();
                Intrinsics.checkNotNull(salesType);
                if (SalesUtils.m415xd12e7b8b(salesType)) {
                    if (ficheRefProp2 != null) {
                        ficheRefProp2.reset();
                    }
                    if (textView3 != null) {
                        textView3.setText(String.valueOf(ficheRefProp2));
                    }
                }
                FicheMode salesFicheMode = this0.getSalesFicheMode();
                LinearLayout linearLayout = this0.lnWareHouse;
                TextView textView4 = this0.txtWareHouse;
                Sales sales = this0.getmSales();
                Intrinsics.checkNotNull(sales);
                FicheRefProp wareHouse = sales.getWareHouse();
                SalesFicheUserRights salesFicheUserRights2 = this0.getSalesFicheUserRights();
                Intrinsics.checkNotNull(salesFicheUserRights2);
                int r8 = salesFicheUserRights2.isDivFactWareHouseControl() ? R.string.qry_warehouse_div_fact : R.string.qry_warehouse;
                SalesFicheUserRights salesFicheUserRights3 = this0.getSalesFicheUserRights();
                Intrinsics.checkNotNull(salesFicheUserRights3);
                if (!salesFicheUserRights3.isDivFactWareHouseControl()) {
                    strArr = new String[0];
                } else {
                    Sales sales2 = this0.getmSales();
                    Intrinsics.checkNotNull(sales2);
                    Sales sales3 = this0.getmSales();
                    Intrinsics.checkNotNull(sales3);
                    strArr = new String[]{StringUtils.convertIntToString(sales2.getFactory().getLogicalRef()), StringUtils.convertIntToString(sales3.getBranch().getLogicalRef())};
                }
                this0.createSingleAlertCursorSales(salesFicheMode, linearLayout, textView4, wareHouse, true, R.string.str_ware_house, r8, R.string.column_code, Arrays.copyOf(strArr, strArr.length));
                SalesType salesType2 = this0.getSalesType();
                Intrinsics.checkNotNull(salesType2);
                if (SalesUtils.m415xd12e7b8b(salesType2)) {
                    FicheMode salesFicheMode2 = this0.getSalesFicheMode();
                    LinearLayout linearLayout2 = this0.lnReturnWareHouse;
                    TextView textView5 = this0.txtReturnWareHouse;
                    Sales sales4 = this0.getmSales();
                    Intrinsics.checkNotNull(sales4);
                    FicheRefProp returnWareHouse = sales4.getReturnWareHouse();
                    SalesFicheUserRights salesFicheUserRights4 = this0.getSalesFicheUserRights();
                    Intrinsics.checkNotNull(salesFicheUserRights4);
                    int r82 = salesFicheUserRights4.isDivFactWareHouseControl() ? R.string.qry_warehouse_div_fact : R.string.qry_warehouse;
                    SalesFicheUserRights salesFicheUserRights5 = this0.getSalesFicheUserRights();
                    Intrinsics.checkNotNull(salesFicheUserRights5);
                    if (!salesFicheUserRights5.isDivFactWareHouseControl()) {
                        strArr2 = new String[0];
                    } else {
                        Sales sales5 = this0.getmSales();
                        Intrinsics.checkNotNull(sales5);
                        Sales sales6 = this0.getmSales();
                        Intrinsics.checkNotNull(sales6);
                        strArr2 = new String[]{StringUtils.convertIntToString(sales5.getFactory().getLogicalRef()), StringUtils.convertIntToString(sales6.getBranch().getLogicalRef())};
                    }
                    this0.createSingleAlertCursorSales(salesFicheMode2, linearLayout2, textView5, returnWareHouse, true, R.string.str_return_ware_house, r82, R.string.column_code, Arrays.copyOf(strArr2, strArr2.length));
                }
            }
        }
        cursor.close();
        dialog.dismiss();
    }

    
    public static void createSingleAlertCursorSalesFactorylambda21lambda20(FicheRefProp ficheRefProp, TextView textView, SalesHeaderFragment this0, FicheRefProp resetWareHouseFicheRefProp, TextView textView2, FicheRefProp ficheRefProp2, TextView textView3, Cursor cursor, DialogInterface dialog, int r21) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(resetWareHouseFicheRefProp, "resetWareHouseFicheRefProp");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        ficheRefProp.reset();
        Intrinsics.checkNotNull(textView);
        textView.setText(ficheRefProp.toString());
        SalesFicheUserRights salesFicheUserRights = this0.getSalesFicheUserRights();
        Intrinsics.checkNotNull(salesFicheUserRights);
        if (salesFicheUserRights.isDivFactWareHouseControl()) {
            resetWareHouseFicheRefProp.reset();
            Intrinsics.checkNotNull(textView2);
            textView2.setText(resetWareHouseFicheRefProp.toString());
            SalesType salesType = this0.getSalesType();
            Intrinsics.checkNotNull(salesType);
            if (SalesUtils.m415xd12e7b8b(salesType)) {
                if (ficheRefProp2 != null) {
                    ficheRefProp2.reset();
                }
                if (textView3 != null) {
                    textView3.setText(String.valueOf(ficheRefProp2));
                }
            }
            FicheMode salesFicheMode = this0.getSalesFicheMode();
            LinearLayout linearLayout = this0.lnWareHouse;
            TextView textView4 = this0.txtWareHouse;
            Sales sales = this0.getmSales();
            Intrinsics.checkNotNull(sales);
            FicheRefProp wareHouse = sales.getWareHouse();
            Sales sales2 = this0.getmSales();
            Intrinsics.checkNotNull(sales2);
            String strConvertIntToString = StringUtils.convertIntToString(sales2.getFactory().getLogicalRef());
            Sales sales3 = this0.getmSales();
            Intrinsics.checkNotNull(sales3);
            this0.createSingleAlertCursorSales(salesFicheMode, linearLayout, textView4, wareHouse, true, R.string.str_ware_house, R.string.qry_warehouse_div_fact, R.string.column_code, strConvertIntToString, StringUtils.convertIntToString(sales3.getBranch().getLogicalRef()));
            SalesType salesType2 = this0.getSalesType();
            Intrinsics.checkNotNull(salesType2);
            if (SalesUtils.m415xd12e7b8b(salesType2)) {
                FicheMode salesFicheMode2 = this0.getSalesFicheMode();
                LinearLayout linearLayout2 = this0.lnReturnWareHouse;
                TextView textView5 = this0.txtReturnWareHouse;
                Sales sales4 = this0.getmSales();
                Intrinsics.checkNotNull(sales4);
                FicheRefProp returnWareHouse = sales4.getReturnWareHouse();
                Sales sales5 = this0.getmSales();
                Intrinsics.checkNotNull(sales5);
                String strConvertIntToString2 = StringUtils.convertIntToString(sales5.getFactory().getLogicalRef());
                Sales sales6 = this0.getmSales();
                Intrinsics.checkNotNull(sales6);
                this0.createSingleAlertCursorSales(salesFicheMode2, linearLayout2, textView5, returnWareHouse, true, R.string.str_return_ware_house, R.string.qry_warehouse_div_fact, R.string.column_code, strConvertIntToString2, StringUtils.convertIntToString(sales6.getBranch().getLogicalRef()));
            }
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialog.dismiss();
    }

    private void createSingleAlertCursorSalesWithCampaignControl(FicheMode ficheMode, View view, final TextView textView, final FicheRefProp ficheRefProp, boolean z, @StringRes final int r18, @StringRes final int r19, @StringRes final int r20, @StringRes final int r21, @StringRes int r22, final boolean z2, final String... strArr) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else {
            if (ficheRefProp == null) {
                setViewDisabled(textView);
                return;
            }
            loadTextView(textView, ficheRefProp.toString());
            Intrinsics.checkNotNull(view);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda12
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SalesHeaderFragment.createSingleAlertCursorSalesWithCampaignControllambda24(this.f0, r20, strArr, r19, r18, z2, ficheRefProp, r21, textView, view2);
                }
            });
        }
    }

    
    public static void createSingleAlertCursorSalesWithCampaignControllambda24(final SalesHeaderFragment this0, int r15, String[] args, int r17, int r18, boolean z, final FicheRefProp ficheRefProp, final int r21, final TextView textView, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(args, "args");
        try {
            AlertDialogBuilder<?> alertDialogBuilder = this0.mAlertDialogBuilderB;
            Intrinsics.checkNotNull(alertDialogBuilder);
            alertDialogBuilder.dismiss();
            final Cursor cursor = this0.getCursor(this0.getString(r15), Arrays.copyOf(args, args.length));
            if (cursor != null && cursor.getCount() != 0) {
                String string = this0.getString(r18);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                if (z) {
                    string = StringUtils.catStringSpace(this0.getString(r18), this0.getString(R.string.str_select_text));
                }
                final String[] strArr = {ficheRefProp.getDefinition(), ""};
                AlertDialogBuilder<?> alertDialogBuilder2 = this0.mAlertDialogBuilderB;
                Intrinsics.checkNotNull(alertDialogBuilder2);
                alertDialogBuilder2.setTitle(string).setSingleChoiceItems(cursor, ficheRefProp.getWhich(), this0.getString(r21), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda6
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r10) {
                        SalesHeaderFragment.m418x532fbd(cursor, ficheRefProp, this0, r21, strArr, textView, dialogInterface, r10);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda7
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r9) {
                        SalesHeaderFragment.m419x532fbe(strArr, ficheRefProp, textView, cursor, this0, dialogInterface, r9);
                    }
                }).create().show();
                return;
            }
            if (cursor != null) {
                cursor.close();
            }
            if (r17 != -1) {
                Toast.makeText(this0.getActivity(), this0.getString(r17), 0).show();
            } else {
                Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), 0).show();
            }
        } catch (Exception e2) {
            Log.e("HeaderFragment", "createSingleAlertCursorSales: ", e2);
        }
    }

    
    /* renamed from: createSingleAlertCursorSalesWithCampaignControllambda24lambda22 */
    public static void m418x532fbd(Cursor cursor, FicheRefProp ficheRefProp, SalesHeaderFragment this0, int r4, String[] valueList, TextView textView, DialogInterface dialog, int r8) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(valueList, "valueList");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (cursor.moveToPosition(r8)) {
            ficheRefProp.setWhich(r8);
            ficheRefProp.setLogicalRef(cursor.getInt(cursor.getColumnIndex(this0.getString(R.string.column_id))));
            String string = cursor.getString(cursor.getColumnIndex(this0.getString(r4)));
            FicheStringProp.setDefinition(string);
            valueList[1] = string;
            Intrinsics.checkNotNull(textView);
            textView.setText(ficheRefProp.getDefinition());
        }
        cursor.close();
        dialog.dismiss();
        SalesActivityNew salesActivity = this0.getSalesActivity();
        Intrinsics.checkNotNull(salesActivity);
        salesActivity.notifyReApplyCampaignIfAppliedBeforeIfValuesHaveChanged(valueList);
    }

    
    /* renamed from: createSingleAlertCursorSalesWithCampaignControllambda24lambda23 */
    public static void m419x532fbe(String[] valueList, FicheRefProp ficheRefProp, TextView textView, Cursor cursor, SalesHeaderFragment this0, DialogInterface dialog, int r7) {
        Intrinsics.checkNotNullParameter(valueList, "valueList");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        valueList[1] = "";
        ficheRefProp.reset();
        Intrinsics.checkNotNull(textView);
        textView.setText(ficheRefProp.toString());
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialog.dismiss();
        SalesActivityNew salesActivity = this0.getSalesActivity();
        Intrinsics.checkNotNull(salesActivity);
        salesActivity.notifyReApplyCampaignIfAppliedBeforeIfValuesHaveChanged(valueList);
    }

    private void loadShipAgent(FicheRefProp ficheRefProp, @StringRes int r5, @StringRes int r6, String... args) {
        int r62;
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(args, "args");
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.viewModel.getSqlBriteDatabase().query(getString(r6), Arrays.copyOf(args, args.length));
                if (cursorQuery != null && cursorQuery.moveToFirst() && (r62 = cursorQuery.getInt(cursorQuery.getColumnIndex(getString(R.string.column_id)))) > 0) {
                    ficheRefProp.setLogicalRef(r62);
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex(getString(r5))));
                }
                if (cursorQuery == null) {
                    return;
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "loadFicheDefaultParameter: ", e2);
                if (cursorQuery == null) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    private void createSingleAlertCursorForShipAgent(FicheMode ficheMode, View view, final TextView textView, final FicheRefProp ficheRefProp, boolean z, @StringRes final int r18, final int r19, @StringRes final int r20, @StringRes final int r21, int r22, final boolean z2, final String... strArr) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else if (ficheRefProp == null) {
            setViewDisabled(textView);
        } else {
            loadTextView(textView, ficheRefProp.toString());
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SalesHeaderFragment.createSingleAlertCursorForShipAgentlambda27(this.f0, r20, strArr, r19, r18, z2, ficheRefProp, r21, textView, view2);
                }
            });
        }
    }

    
    public static void createSingleAlertCursorForShipAgentlambda27(final SalesHeaderFragment this0, int r7, String[] args, int r9, int r10, boolean z, final FicheRefProp ficheRefProp, final int r13, final TextView showView, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(showView, "showView");
        try {
            AlertDialogBuilder<?> alertDialogBuilder = this0.mAlertDialogBuilderB;
            Intrinsics.checkNotNull(alertDialogBuilder);
            alertDialogBuilder.dismiss();
            final Cursor cursor = this0.getCursor(this0.getString(r7), Arrays.copyOf(args, args.length));
            if (cursor != null && cursor.getCount() != 0) {
                String string = this0.getString(r10);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                if (z) {
                    string = StringUtils.catStringSpace(this0.getString(r10), this0.getString(R.string.str_select_text));
                }
                AlertDialogBuilder<?> alertDialogBuilder2 = this0.mAlertDialogBuilderB;
                Intrinsics.checkNotNull(alertDialogBuilder2);
                alertDialogBuilder2.setTitle(string).setSingleChoiceItems(cursor, ficheRefProp.getWhich(), this0.getString(r13), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda18
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r92) {
                        SalesHeaderFragment.createSingleAlertCursorForShipAgentlambda27lambda25(cursor, ficheRefProp, this0, r13, showView, dialogInterface, r92);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda19
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r8) {
                        SalesHeaderFragment.createSingleAlertCursorForShipAgentlambda27lambda26(this.f0, ficheRefProp, showView, cursor, dialogInterface, r8);
                    }
                }).create().show();
                return;
            }
            if (cursor != null) {
                cursor.close();
            }
            if (r9 != -1) {
                Toast.makeText(this0.getActivity(), this0.getString(r9), 0).show();
            } else {
                Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), 0).show();
            }
        } catch (Exception e2) {
            Log.e("HeaderFragment", "createSingleAlertCursorForShipAgent: ", e2);
        }
    }

    
    public static void createSingleAlertCursorForShipAgentlambda27lambda25(Cursor cursor, FicheRefProp ficheRefProp, SalesHeaderFragment this0, int r4, TextView showView, DialogInterface dialogInterface, int r7) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(showView, "showView");
        if (cursor.moveToPosition(r7)) {
            ficheRefProp.setWhich(r7);
            ficheRefProp.setLogicalRef(cursor.getInt(cursor.getColumnIndex(this0.getString(R.string.column_id))));
            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(this0.getString(r4))));
            showView.setText(ficheRefProp.getDefinition());
        }
        cursor.close();
        dialogInterface.dismiss();
    }

    
    public static void createSingleAlertCursorForShipAgentlambda27lambda26(SalesHeaderFragment this0, FicheRefProp ficheRefProp, TextView showView, Cursor cursor, DialogInterface dialogInterface, int r5) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(showView, "showView");
        Sales sales = this0.getmSales();
        Intrinsics.checkNotNull(sales);
        if (!sales.getEDespatch().isSelect()) {
            ficheRefProp.reset();
            showView.setText(ficheRefProp.toString());
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        dialogInterface.dismiss();
    }

    private boolean salesLinesWarehouseCanBeChangedAndErpTiger() {
        return this.baseErp.getErpType() == ErpType.TIGER && this.viewModel.changeSalesLinesWarehouse();
    }

    private boolean demandLinesWarehouseCanBeChangedAndErpTiger() {
        return this.baseErp.getErpType() == ErpType.TIGER && this.viewModel.changeDemandLinesWarehouse();
    }

    private void createSingleAlertForErpInvoiceType(FicheMode ficheMode, View view, final TextView textView, final FicheRefProp ficheRefProp, boolean z, @StringRes final int r12, final boolean z2) {
        Intrinsics.checkNotNullParameter(ficheMode, "ficheMode");
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else {
            if (ficheRefProp == null) {
                setViewDisabled(textView);
                return;
            }
            loadTextView(textView, ficheRefProp.toString());
            Intrinsics.checkNotNull(view);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SalesHeaderFragment.createSingleAlertForErpInvoiceTypelambda30(this.f0, r12, z2, ficheRefProp, textView, view2);
                }
            });
        }
    }

    
    public static void createSingleAlertForErpInvoiceTypelambda30(final SalesHeaderFragment this0, int r2, boolean z, final FicheRefProp ficheRefProp, final TextView textView, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        try {
            AlertDialogBuilder<?> alertDialogBuilder = this0.mAlertDialogBuilderB2;
            Intrinsics.checkNotNull(alertDialogBuilder);
            alertDialogBuilder.dismiss();
            String string = this0.getString(r2);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            if (z) {
                string = StringUtils.catStringSpace(this0.getString(r2), this0.getString(R.string.str_select_text));
            }
            AlertDialogBuilder<?> alertDialogBuilder2 = this0.mAlertDialogBuilderB2;
            Intrinsics.checkNotNull(alertDialogBuilder2);
            AlertDialogBuilder title = alertDialogBuilder2.setTitle(string);
            ArrayList<String> arrayList = this0.availableErpInvoiceTypes;
            Intrinsics.checkNotNull(arrayList);
            title.setSingleChoice(arrayList.toArray(new CharSequence[0]), ficheRefProp.getWhich(), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda10
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int r4) {
                    SalesHeaderFragment.createSingleAlertForErpInvoiceTypelambda30lambda28(this.f0, ficheRefProp, textView, dialogInterface, r4);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragmentExternalSyntheticLambda11
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int r22) {
                    SalesHeaderFragment.createSingleAlertForErpInvoiceTypelambda30lambda29(dialogInterface, r22);
                }
            }).create().show();
        } catch (Exception e2) {
            Log.e("SalesHeaderFragment", "createSingleAlertForErpInvoiceType: ", e2);
        }
    }

    
    public static void createSingleAlertForErpInvoiceTypelambda30lambda28(SalesHeaderFragment this0, FicheRefProp ficheRefProp, TextView textView, DialogInterface dialog, int r6) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        ArrayList<String> arrayList = this0.availableErpInvoiceTypes;
        Intrinsics.checkNotNull(arrayList);
        String str = arrayList.get(r6);
        Intrinsics.checkNotNullExpressionValue(str, "get(...)");
        String str2 = str;
        ArrayList<String> arrayList2 = this0.allErpInvoiceTypes;
        Intrinsics.checkNotNull(arrayList2);
        ficheRefProp.setLogicalRef(arrayList2.indexOf(str2));
        FicheStringProp.setDefinition(str2);
        ficheRefProp.setWhich(r6);
        Intrinsics.checkNotNull(textView);
        textView.setText(ficheRefProp.toString());
        if (ficheRefProp.getDefinition().equals(this0.getString(R.string.str_paper_invoice_type_for_erp))) {
            View view = this0.lnInsteadOfEdespatch;
            Intrinsics.checkNotNull(view);
            view.setVisibility(8);
            View view2 = this0.insteadOfDEdespatchDivider;
            Intrinsics.checkNotNull(view2);
            view2.setVisibility(8);
        } else {
            View view3 = this0.lnInsteadOfEdespatch;
            Intrinsics.checkNotNull(view3);
            view3.setVisibility(0);
            View view4 = this0.insteadOfDEdespatchDivider;
            Intrinsics.checkNotNull(view4);
            view4.setVisibility(0);
        }
        dialog.dismiss();
    }

    
    public static void createSingleAlertForErpInvoiceTypelambda30lambda29(DialogInterface dialog, int r1) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    private ArrayList<String> getAvailableErpInvoiceTypes() {
        boolean useEarchive;
        ISqlHelper<?> sqlHelper = this.viewModel.getSqlHelper();
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        boolean z = sqlHelper.getClEInvoiceUser(sales.getClRef()) == 1;
        if (this.baseErp.eDocControlTypeFirm()) {
            useEarchive = this.baseErp.firmUseEArchive();
        } else {
            BaseErp baseErp = this.baseErp;
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            useEarchive = baseErp.getUseEarchive(sales2.getBranch().getLogicalRef());
        }
        if (z) {
            Sales sales3 = getmSales();
            Intrinsics.checkNotNull(sales3);
            return SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoice(sales3.getmSalesType()) ? new ArrayList<String>(this) { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragment.getAvailableErpInvoiceTypes.1
                @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
                public boolean contains(Object obj) {
                    if (obj instanceof String) {
                        return contains((String) obj);
                    }
                    return false;
                }

                public boolean contains(String str) {
                    return super.contains(str);
                }

                public int getSize() {
                    return super.size();
                }

                @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
                public int indexOf(Object obj) {
                    if (obj instanceof String) {
                        return indexOf((String) obj);
                    }
                    return -1;
                }

                public int indexOf(String str) {
                    return super.indexOf(str);
                }

                @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
                public int lastIndexOf(Object obj) {
                    if (obj instanceof String) {
                        return lastIndexOf((String) obj);
                    }
                    return -1;
                }

                public int lastIndexOf(String str) {
                    return super.lastIndexOf(str);
                }

                @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
                public String remove(int r1) {
                    return removeAt(r1);
                }

                @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
                public boolean remove(Object obj) {
                    if (obj instanceof String) {
                        return remove((String) obj);
                    }
                    return false;
                }

                public boolean remove(String str) {
                    return super.remove(str);
                }

                public String removeAt(int r1) {
                    return super.remove(r1);
                }

                @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return getSize();
                }

                {
                    add(this.getString(R.string.str_paper_invoice_type_for_erp));
                    add(this.getString(R.string.str_eInvoice_invoice_type_for_erp));
                }
            } : new ArrayList<String>(this) { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragment.getAvailableErpInvoiceTypes.2
                {
                    add(this.getString(R.string.str_eInvoice_invoice_type_for_erp));
                }

                @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
                public boolean contains(Object obj) {
                    if (obj instanceof String) {
                        return contains((String) obj);
                    }
                    return false;
                }

                public boolean contains(String str) {
                    return super.contains(str);
                }

                public int getSize() {
                    return super.size();
                }

                @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
                public int indexOf(Object obj) {
                    if (obj instanceof String) {
                        return indexOf((String) obj);
                    }
                    return -1;
                }

                public int indexOf(String str) {
                    return super.indexOf(str);
                }

                @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
                public int lastIndexOf(Object obj) {
                    if (obj instanceof String) {
                        return lastIndexOf((String) obj);
                    }
                    return -1;
                }

                public int lastIndexOf(String str) {
                    return super.lastIndexOf(str);
                }

                @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
                public String remove(int r1) {
                    return removeAt(r1);
                }

                @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
                public boolean remove(Object obj) {
                    if (obj instanceof String) {
                        return remove((String) obj);
                    }
                    return false;
                }

                public boolean remove(String str) {
                    return super.remove(str);
                }

                public String removeAt(int r1) {
                    return super.remove(r1);
                }

                @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return getSize();
                }
            };
        }
        if (useEarchive) {
            Sales sales4 = getmSales();
            Intrinsics.checkNotNull(sales4);
            if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(sales4.getmSalesType())) {
                return new ArrayList<String>(this) { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragment.getAvailableErpInvoiceTypes.3
                    {
                        add(this.getString(R.string.str_paper_invoice_type_for_erp));
                    }

                    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
                    public boolean contains(Object obj) {
                        if (obj instanceof String) {
                            return contains((String) obj);
                        }
                        return false;
                    }

                    public boolean contains(String str) {
                        return super.contains(str);
                    }

                    public int getSize() {
                        return super.size();
                    }

                    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
                    public int indexOf(Object obj) {
                        if (obj instanceof String) {
                            return indexOf((String) obj);
                        }
                        return -1;
                    }

                    public int indexOf(String str) {
                        return super.indexOf(str);
                    }

                    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
                    public int lastIndexOf(Object obj) {
                        if (obj instanceof String) {
                            return lastIndexOf((String) obj);
                        }
                        return -1;
                    }

                    public int lastIndexOf(String str) {
                        return super.lastIndexOf(str);
                    }

                    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
                    public String remove(int r1) {
                        return removeAt(r1);
                    }

                    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
                    public boolean remove(Object obj) {
                        if (obj instanceof String) {
                            return remove((String) obj);
                        }
                        return false;
                    }

                    public boolean remove(String str) {
                        return super.remove(str);
                    }

                    public String removeAt(int r1) {
                        return super.remove(r1);
                    }

                    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
                    public int size() {
                        return getSize();
                    }
                };
            }
            return new ArrayList<String>(this) { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragment.getAvailableErpInvoiceTypes.4
                {
                    add(this.getString(R.string.str_paper_invoice_type_for_erp));
                    add(this.getString(R.string.str_eArchiveInvoice_invoice_type_for_erp));
                    add(this.getString(R.string.str_eArchiveInternetInvoice_invoice_type_for_erp));
                }

                @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
                public boolean contains(Object obj) {
                    if (obj instanceof String) {
                        return contains((String) obj);
                    }
                    return false;
                }

                public boolean contains(String str) {
                    return super.contains(str);
                }

                public int getSize() {
                    return super.size();
                }

                @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
                public int indexOf(Object obj) {
                    if (obj instanceof String) {
                        return indexOf((String) obj);
                    }
                    return -1;
                }

                public int indexOf(String str) {
                    return super.indexOf(str);
                }

                @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
                public int lastIndexOf(Object obj) {
                    if (obj instanceof String) {
                        return lastIndexOf((String) obj);
                    }
                    return -1;
                }

                public int lastIndexOf(String str) {
                    return super.lastIndexOf(str);
                }

                @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
                public String remove(int r1) {
                    return removeAt(r1);
                }

                @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
                public boolean remove(Object obj) {
                    if (obj instanceof String) {
                        return remove((String) obj);
                    }
                    return false;
                }

                public boolean remove(String str) {
                    return super.remove(str);
                }

                public String removeAt(int r1) {
                    return super.remove(r1);
                }

                @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return getSize();
                }
            };
        }
        return new ArrayList<String>(this) { // from class: com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragment.getAvailableErpInvoiceTypes.5
            {
                add(this.getString(R.string.str_paper_invoice_type_for_erp));
            }

            @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
            public boolean contains(Object obj) {
                if (obj instanceof String) {
                    return contains((String) obj);
                }
                return false;
            }

            public boolean contains(String str) {
                return super.contains(str);
            }

            public int getSize() {
                return super.size();
            }

            @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
            public int indexOf(Object obj) {
                if (obj instanceof String) {
                    return indexOf((String) obj);
                }
                return -1;
            }

            public int indexOf(String str) {
                return super.indexOf(str);
            }

            @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
            public int lastIndexOf(Object obj) {
                if (obj instanceof String) {
                    return lastIndexOf((String) obj);
                }
                return -1;
            }

            public int lastIndexOf(String str) {
                return super.lastIndexOf(str);
            }

            @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
            public String remove(int r1) {
                return removeAt(r1);
            }

            @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
            public boolean remove(Object obj) {
                if (obj instanceof String) {
                    return remove((String) obj);
                }
                return false;
            }

            public boolean remove(String str) {
                return super.remove(str);
            }

            public String removeAt(int r1) {
                return super.remove(r1);
            }

            @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return getSize();
            }
        };
    }

    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getEXTRA_CUSTOMER_REF() {
            return SalesHeaderFragment.EXTRA_CUSTOMER_REF;
        }
    }
}
