package com.proje.mobilesales.features.collections.casefiche.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseFicheFragment;
import com.proje.mobilesales.core.enums.CurrselDetailType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;
import com.proje.mobilesales.features.collections.casefiche.repository.CaseFicheRepository;
import com.proje.mobilesales.features.collections.casefiche.view.activity.CaseFicheActivity;
import com.proje.mobilesales.features.collections.casefiche.viewmodel.CaseFicheViewModel;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.dbmodel.UserCase;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.sales.model.SalesMan;
import com.proje.mobilesales.features.sales.view.mans.SalesMansListActivity;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus; 
 
public final class CaseFicheFragment extends BaseFicheFragment {
    private CustomerOperation customerOperation;
    private int customerRef;
    private EditText etAmount;
    private EditText etExplanation;
    private EditText etVoucherNo;
    private LinearLayout lnAmount;
    private LinearLayout lnAuthCode;
    private LinearLayout lnCase;
    private LinearLayout lnCurrency;
    private LinearLayout lnCustomerTradingGroup;
    private LinearLayout lnDepartment;
    private LinearLayout lnExplanation;
    private LinearLayout lnProjectCode;
    private LinearLayout lnSalesManCode;
    private LinearLayout lnSpecialCode;
    private LinearLayout lnVoucherNo;
    private LinearLayout lnWorkplace;
    private MatterSettings mMatterSettings;
    private boolean mNetsis;
    private ReceiptType mReceiptType;
    private TextView txtAuthCode;
    private TextView txtCurrency;
    private TextView txtCustomerTradingGroup;
    private TextView txtDepartment;
    private TextView txtProjectCode;
    private TextView txtSalesManCode;
    private TextView txtSpecialCode;
    private TextView txtWorkplace;
    private TextView txxCase;
    private final CaseFicheRepository repository = new CaseFicheRepository();
    private final CaseFicheViewModel viewModel = new CaseFicheViewModel(repository);
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        mNetsis = viewModel.getBaseErp().getErpType() == ErpType.NETSIS;
        final Bundle arguments = this.getArguments();
        Intrinsics.checkNotNull(arguments);
        customerOperation = arguments.getParcelable(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE);
        final Bundle arguments2 = this.getArguments();
        Intrinsics.checkNotNull(arguments2);
        customerRef = arguments2.getInt(IntentExtraName.EXTRA_CUSTOMER_REF);
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        mReceiptType = customerOperation.getReceiptType();
        final BaseErp<?> baseErp = viewModel.getBaseErp();
        final Context context = this.getContext();
        final CustomerOperation customerOperation2 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation2);
        mMatterSettings = baseErp.getMatterSettings(context, customerOperation2.getFicheType());
    }
    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final View inflate = this.getLayoutInflater(bundle).inflate(R.layout.fragment_casefiche, viewGroup, false);
        etAmount = inflate.findViewById(R.id.et_amount);
        etVoucherNo = inflate.findViewById(R.id.et_voucherNo);
        txtCustomerTradingGroup = inflate.findViewById(R.id.txt_customerTradingGroup);
        txtCurrency = inflate.findViewById(R.id.txt_currency);
        txtAuthCode = inflate.findViewById(R.id.txt_authCode);
        etExplanation = inflate.findViewById(R.id.et_explanation);
        txtWorkplace = inflate.findViewById(R.id.txt_workplace);
        txtDepartment = inflate.findViewById(R.id.txt_department);
        txtSpecialCode = inflate.findViewById(R.id.txt_specialCode);
        txtProjectCode = inflate.findViewById(R.id.txt_projectCode);
        txxCase = inflate.findViewById(R.id.txt_Kasa);
        txtSalesManCode = inflate.findViewById(R.id.txt_SalesManCode);
        lnAmount = inflate.findViewById(R.id.ln_amount);
        lnVoucherNo = inflate.findViewById(R.id.ln_voucherNo);
        lnCustomerTradingGroup = inflate.findViewById(R.id.ln_customerTradingGroup);
        lnCurrency = inflate.findViewById(R.id.ln_currency);
        lnAuthCode = inflate.findViewById(R.id.ln_authCode);
        lnExplanation = inflate.findViewById(R.id.ln_explanation);
        lnWorkplace = inflate.findViewById(R.id.ln_workplace);
        lnDepartment = inflate.findViewById(R.id.ln_department);
        lnSpecialCode = inflate.findViewById(R.id.ln_specialCode);
        lnProjectCode = inflate.findViewById(R.id.ln_projectCode);
        lnCase = inflate.findViewById(R.id.ln_case);
        lnSalesManCode = inflate.findViewById(R.id.ln_SalesManCode);
        return inflate;
    }
    public void onViewCreated(final View view, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
    }
    public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        this.load();
        this.setVisibility();
        final EditText editText = etAmount;
        Intrinsics.checkNotNull(editText);
        if (View.VISIBLE == editText.getVisibility()) {
            this.focusAndShowKeyBoard(etAmount);
        }
    }
    protected void load() {
        this.loadTotal();
        this.loadDocumentNo();
        this.loadDepartments();
        this.loadWorkplaces();
        this.loadExplanations();
        this.loadTradingGroup();
        this.loadCurrencies();
        this.loadSpecodes();
        this.loadProjects();
        this.loadAuthCode();
        this.loadCases();
        this.loadSalesMan();
    } 
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (-1 == i3 && 1073 == i2) {
            Intrinsics.checkNotNull(intent);
            final Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            final SalesMan salesMan = (SalesMan) extras.get(IntentExtraName.SELECTED_SALESMAN);
            final CaseFiche cashCreditFiche = this.getCashCreditFiche();
            Intrinsics.checkNotNull(cashCreditFiche);
            final FicheDiscountRefProp salesMan2 = cashCreditFiche.getSalesMan();
            Intrinsics.checkNotNull(salesMan);
            salesMan2.setLogicalRef(salesMan.component1());
            final CaseFiche cashCreditFiche2 = this.getCashCreditFiche();
            Intrinsics.checkNotNull(cashCreditFiche2);
            cashCreditFiche2.getSalesMan().setCode(salesMan.component2() + " - " + salesMan.component3());
            final TextView textView = txtSalesManCode;
            Intrinsics.checkNotNull(textView);
            final CaseFiche cashCreditFiche3 = this.getCashCreditFiche();
            Intrinsics.checkNotNull(cashCreditFiche3);
            textView.setText(cashCreditFiche3.getSalesMan().getCode());
        }
    }
    private void focusAndShowKeyBoard(EditText editText) {
        new Handler().postDelayed(new Runnable() {  
            public void run() {
                focusAndShowKeyBoardlambda0(editText, CaseFicheFragment.this);
            }
        }, 100L);
    } 
    public static void focusAndShowKeyBoardlambda0(final EditText editText, final CaseFicheFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNull(editText);
        editText.requestFocus();
        final Context context = this0.getContext();
        Intrinsics.checkNotNull(context);
        final Object systemService = context.getSystemService(Context.INPUT_METHOD_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }
    private void loadSalesMan() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        if (customerOperation.getFicheMode() != FicheMode.ANALYSE) {
            final LinearLayout linearLayout = lnSalesManCode;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    loadSalesManlambda1(CaseFicheFragment.this, view);
                }
            });
        }
        final TextView textView = txtSalesManCode;
        Intrinsics.checkNotNull(textView);
        final CaseFiche cashCreditFiche = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        textView.setText(cashCreditFiche.getSalesMan().getCode());
    }
    public static void loadSalesManlambda1(final CaseFicheFragment this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.startActivityForResult(new Intent(this0.getActivity(), SalesMansListActivity.class), 1073);
    }
    private void loadTotal() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final EditText editText = etAmount;
        final CaseFiche cashCreditFiche = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        this.createTutarEditTextListener(ficheMode, editText, cashCreditFiche.getTotal());
        final EditText editText2 = etAmount;
        final CaseFiche cashCreditFiche2 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche2);
        this.loadTextView(editText2, cashCreditFiche2.getTotal().toString());
    }
    private void createTutarEditTextListener(final FicheMode ficheMode, EditText editText, FicheStringProp ficheStringProp) {
        if (ficheMode == FicheMode.ANALYSE) {
            this.setViewDisabled(editText);
            return;
        }
        Intrinsics.checkNotNull(editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(final TextView textView, final int i2, final KeyEvent keyEvent) {
                final boolean createTutarEditTextListenerlambda2;
                createTutarEditTextListenerlambda2 = createTutarEditTextListenerlambda2(ficheStringProp, editText, textView, i2, keyEvent);
                return createTutarEditTextListenerlambda2;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(final CharSequence charSequence, final int i2, final int i3, final int i4) {
                Intrinsics.checkNotNullParameter(charSequence, "charSequence");
            }
            public void onTextChanged(final CharSequence charSequence, final int i2, final int i3, final int i4) {
                Intrinsics.checkNotNullParameter(charSequence, "charSequence");
            }
            public void afterTextChanged(final Editable editable) {
                Intrinsics.checkNotNullParameter(editable, "editable");
                FicheStringProp.setDefinition(editable.toString());
                final CaseFicheActivity caseFicheActivity = getCaseFicheActivity();
                if (null != caseFicheActivity) {
                    caseFicheActivity.setTotalLayoutWithNotify();
                }
            }
        });
    }
    public static boolean createTutarEditTextListenerlambda2(final FicheStringProp ficheStringProp, final EditText editText, final TextView textView, final int i2, final KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "ficheStringProp");
        if (5 != i2 && 6 != i2) {
            return false;
        }
        FicheStringProp.setDefinition(editText.getText().toString());
        return false;
    }
    private void loadDocumentNo() {
        final EditText editText = etVoucherNo;
        if (null != editText) {
            StringUtils.INSTANCE.removeAllSpacesFilter(editText);
        }
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final EditText editText2 = etVoucherNo;
        final CaseFiche cashCreditFiche = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        this.createEditTextAddTextChangedListener(ficheMode, editText2, cashCreditFiche.getDocNo());
        final EditText editText3 = etVoucherNo;
        final CaseFiche cashCreditFiche2 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche2);
        this.loadTextView(editText3, cashCreditFiche2.getDocNo().toString());
    }
    private void loadExplanations() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final EditText editText = etExplanation;
        final CaseFiche cashCreditFiche = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        this.createEditTextAddTextChangedListener(ficheMode, editText, cashCreditFiche.getExplanation());
        final EditText editText2 = etExplanation;
        final CaseFiche cashCreditFiche2 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche2);
        this.loadTextView(editText2, cashCreditFiche2.getExplanation().toString());
    }
    private void loadDepartments() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnDepartment;
        final TextView textView = txtDepartment;
        final CaseFiche cashCreditFiche = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, cashCreditFiche.getDivision(), true, R.string.str_department, R.string.qry_division, R.string.column_code);
        final TextView textView2 = txtDepartment;
        final CaseFiche cashCreditFiche2 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche2);
        this.loadTextView(textView2, cashCreditFiche2.getDivision().toString(), true);
    }
    private void loadWorkplaces() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnWorkplace;
        final TextView textView = txtWorkplace;
        final CaseFiche cashCreditFiche = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, cashCreditFiche.getBranch(), true, R.string.str_branch, R.string.qry_branch, R.string.column_code);
        final TextView textView2 = txtWorkplace;
        final CaseFiche cashCreditFiche2 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche2);
        this.loadTextView(textView2, cashCreditFiche2.getBranch().toString(), true);
    }
    private void loadTradingGroup() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnCustomerTradingGroup;
        final TextView textView = txtCustomerTradingGroup;
        final CaseFiche cashCreditFiche = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, cashCreditFiche.getTradinggrp(), true, R.string.str_trade_group, R.string.qry_trade_group, R.string.column_code);
        final TextView textView2 = txtCustomerTradingGroup;
        final CaseFiche cashCreditFiche2 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche2);
        this.loadTextView(textView2, cashCreditFiche2.getTradinggrp().toString(), true);
    }
    private void loadCurrencies() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnCurrency;
        final TextView textView = txtCurrency;
        final CaseFiche cashCreditFiche = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        this.createSearchDialogCursorSales(ficheMode, linearLayout, textView, cashCreditFiche.getCurrType(), true, R.string.str_report_currency_unit_transaction, R.string.qry_get_curr_type, R.string.column_code);
        final TextView textView2 = txtCurrency;
        final CaseFiche cashCreditFiche2 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche2);
        this.loadTextView(textView2, cashCreditFiche2.getCurrType().toString(), true);
    }
    private void loadSpecodes() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnSpecialCode;
        final TextView textView = txtSpecialCode;
        final CaseFiche cashCreditFiche = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, cashCreditFiche.getSpecode(), true, R.string.str_special_code, R.string.qry_spe_code, R.string.column_code, "44");
        final TextView textView2 = txtSpecialCode;
        final CaseFiche cashCreditFiche2 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche2);
        this.loadTextView(textView2, cashCreditFiche2.getSpecode().toString(), true);
    }
    private void loadProjects() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnProjectCode;
        final TextView textView = txtProjectCode;
        final CaseFiche cashCreditFiche = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, cashCreditFiche.getProjectCode(), true, R.string.str_project_code, R.string.qry_project_code, R.string.column_name);
        final TextView textView2 = txtProjectCode;
        final CaseFiche cashCreditFiche2 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche2);
        this.loadTextView(textView2, cashCreditFiche2.getProjectCode().toString(), true);
    }
    private void loadCases() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnCase;
        final TextView textView = txxCase;
        final CaseFiche cashCreditFiche = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        final FicheDiscountRefProp caseCode = cashCreditFiche.getCaseCode();
        final CaseFiche cashCreditFiche2 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche2);
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, caseCode, true, R.string.str_case, R.string.str_not_found_case, R.string.qry_case, R.string.column_name, cashCreditFiche2.getCaseCode());
        final TextView textView2 = txxCase;
        final CaseFiche cashCreditFiche3 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche3);
        this.loadTextView(textView2, cashCreditFiche3.getCaseCode().toString(), true);
        final CaseFiche cashCreditFiche4 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche4);
        if (0 < cashCreditFiche4.getCaseCode().getLogicalRef()) {
            final CustomerOperation customerOperation2 = this.customerOperation;
            Intrinsics.checkNotNull(customerOperation2);
            if (customerOperation2.getFicheMode() == FicheMode.NEW) {
                final CaseFiche cashCreditFiche5 = this.getCashCreditFiche();
                Intrinsics.checkNotNull(cashCreditFiche5);
                this.userCaseChangeEvent(cashCreditFiche5.getCaseCode());
            }
        }
    }
    private void loadAuthCode() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnAuthCode;
        final TextView textView = txtAuthCode;
        final CaseFiche cashCreditFiche = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, cashCreditFiche.getCyphcode(), true, R.string.str_auth_code, R.string.qry_warranty, R.string.column_code, "44");
        final TextView textView2 = txtAuthCode;
        final CaseFiche cashCreditFiche2 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche2);
        this.loadTextView(textView2, cashCreditFiche2.getCyphcode().toString(), true);
    }
    private void setVisibility() {
        final String upVal = viewModel.getBaseErp().getLogoSqlHelper().upVal("80");
        Intrinsics.checkNotNullExpressionValue(upVal, "upVal(...)");
        int length = upVal.length() - 1;
        int i2 = 0;
        boolean z = false;
        while (i2 <= length) {
            final boolean z2 = 0 >= Intrinsics.compare(upVal.charAt(!z ? i2 : length), 32);
            if (z) {
                if (!z2) {
                    break;
                } else {
                    length--;
                }
            } else if (z2) {
                i2++;
            } else {
                z = true;
            }
        }
        final String obj = upVal.subSequence(i2, length + 1).toString();
        if (!StringsKt.contains (obj, "0", false)) {
            final LinearLayout linearLayout = lnWorkplace;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(View.GONE);
        }
        if (!StringsKt.contains(obj, BuildConfig.NETSIS_DEMO_PASSWORD, false)) {
            final LinearLayout linearLayout2 = lnDepartment;
            Intrinsics.checkNotNull(linearLayout2);
            linearLayout2.setVisibility(View.GONE);
        }
        if (!StringsKt.contains(obj, ExifInterface.GPS_MEASUREMENT_2D, false)) {
            final LinearLayout linearLayout3 = lnSpecialCode;
            Intrinsics.checkNotNull(linearLayout3);
            linearLayout3.setVisibility(View.GONE);
        }
        if (!StringsKt.contains(obj, ExifInterface.GPS_MEASUREMENT_3D, false)) {
            final LinearLayout linearLayout4 = lnProjectCode;
            Intrinsics.checkNotNull(linearLayout4);
            linearLayout4.setVisibility(View.GONE);
        }
        if (!StringsKt.contains(obj, "4", false)) {
            final LinearLayout linearLayout5 = lnAuthCode;
            Intrinsics.checkNotNull(linearLayout5);
            linearLayout5.setVisibility(View.GONE);
        }
        if (!StringsKt.contains(obj, "5", false)) {
            final LinearLayout linearLayout6 = lnCustomerTradingGroup;
            Intrinsics.checkNotNull(linearLayout6);
            linearLayout6.setVisibility(View.GONE);
        }
        if (!StringsKt.contains(obj, "6", false)) {
            final LinearLayout linearLayout7 = lnSalesManCode;
            Intrinsics.checkNotNull(linearLayout7);
            linearLayout7.setVisibility(View.GONE);
        }
        final int caseCashCurrselDetails = viewModel.getBaseErp().getCaseCashCurrselDetails();
        if (mNetsis || caseCashCurrselDetails != CurrselDetailType.TRANSACTION_CURRENCY.getType()) {
            final LinearLayout linearLayout8 = lnCurrency;
            Intrinsics.checkNotNull(linearLayout8);
            linearLayout8.setVisibility(View.GONE);
        } else {
            final LinearLayout linearLayout9 = lnAmount;
            Intrinsics.checkNotNull(linearLayout9);
            final TextView textView = linearLayout9.findViewById(R.id.txt_amount);
            if (null != textView) {
                textView.setText(R.string.str_currency_amount);
            }
        }
    }
    public void onStop() {
        if (!mNetsis && viewModel.getBaseErp().getCaseCashCurrselDetails() == CurrselDetailType.TRANSACTION_CURRENCY.getType()) {
            EventBus.getDefault().unregister(this);
        }
        super.onStop();
    }
    public void userCaseChangeEvent(final FicheDiscountRefProp caseCodeProp) {
        final boolean z;
        Intrinsics.checkNotNullParameter(caseCodeProp, "caseCodeProp");
        final UserCase userCase = viewModel.getBaseErp().getUserCase(caseCodeProp.getLogicalRef());
        final CaseFiche cashCreditFiche = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        cashCreditFiche.getCurrType().reset();
        int i2 = userCase.getcCurrency();
        int localCurrType = viewModel.getBaseErp().getLocalCurrType();
        if (0 == i2) {
            i2 = localCurrType;
        }
        if (0 < i2) {
            z = StringUtils.convertIntToBoolean(userCase.getFixedCurrType());
            final ISqlHelper logoSqlHelper = viewModel.getBaseErp().getLogoSqlHelper();
            final CaseFicheActivity caseFicheActivity = this.getCaseFicheActivity();
            Intrinsics.checkNotNull(caseFicheActivity);
            final int clCardCurrency = logoSqlHelper.getClCardCurrency(caseFicheActivity.customerRef);
            if (0 != clCardCurrency) {
                localCurrType = clCardCurrency;
            }
            final CaseFicheActivity caseFicheActivity2 = this.getCaseFicheActivity();
            Intrinsics.checkNotNull(caseFicheActivity2);
            final CaseFiche cashCreditFiche2 = this.getCashCreditFiche();
            Intrinsics.checkNotNull(cashCreditFiche2);
            final FicheRefProp currType = cashCreditFiche2.getCurrType();
            if (!z) {
                i2 = localCurrType;
            }
            caseFicheActivity2.loadFicheDefaultParameter(currType, i2, R.string.column_code, this.getString(R.string.qry_get_curr_type_where));
        } else {
            z = false;
        }
        final TextView textView = txtCurrency;
        final CaseFiche cashCreditFiche3 = this.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche3);
        this.loadTextView(textView, cashCreditFiche3.getCurrType().toString(), true);
        final LinearLayout linearLayout = lnCurrency;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setClickable(!z);
        final LinearLayout linearLayout2 = lnCurrency;
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.setFocusable(!z);
    }
    private CaseFiche getCashCreditFiche() {
        final CaseFicheActivity caseFicheActivity = this.getCaseFicheActivity();
        Intrinsics.checkNotNull(caseFicheActivity);
        return caseFicheActivity.getCaseFiche();
    }
    public CaseFicheActivity getCaseFicheActivity() {
        return (CaseFicheActivity) this.getActivity();
    }
}
