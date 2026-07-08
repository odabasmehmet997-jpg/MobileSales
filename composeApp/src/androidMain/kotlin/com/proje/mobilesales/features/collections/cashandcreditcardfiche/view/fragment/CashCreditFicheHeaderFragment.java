package com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseFicheFragment;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFiche;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.repository.CashAndCreditCardFicheRepository;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.activity.CashAndCreditCardFicheActivity;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.viewmodel.CashAndCreditCardFicheViewModel;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.springframework.http.HttpHeaders;


/* compiled from: CashCreditFicheHeaderFragment.kt */

public final class CashCreditFicheHeaderFragment extends BaseFicheFragment {
    private CustomerOperation customerOperation;
    private int customerRef;
    private EditText etExplanation1;
    private EditText etExplanation2;
    private EditText etExplanation3;
    private EditText etExplanation4;
    private EditText etInstallment;
    private LinearLayout lnAccount;
    private LinearLayout lnAuthCode;
    private LinearLayout lnBank;
    private LinearLayout lnCustomerTradingGroup;
    private LinearLayout lnDepartment;
    private LinearLayout lnExplanation1;
    private LinearLayout lnExplanation2;
    private LinearLayout lnExplanation3;
    private LinearLayout lnExplanation4;
    private LinearLayout lnInstallment;
    private LinearLayout lnProjectCode;
    private LinearLayout lnPromiseCode;
    private LinearLayout lnSpecialCode;
    private LinearLayout lnWorkplace;
    private MatterSettings mMatterSettings;
    private boolean mNetsis;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private ReceiptType mReceiptType;
    private TextView txtAccount;
    private TextView txtAuthCode;
    private TextView txtBank;
    private TextView txtCustomerTradingGroup;
    private TextView txtDepartment;
    private TextView txtProjectCode;
    private TextView txtPromiseCode;
    private TextView txtSpecialCode;
    private TextView txtWorkplace;
    private final CashAndCreditCardFicheRepository repository = new CashAndCreditCardFicheRepository();
    private final CashAndCreditCardFicheViewModel viewModel = new CashAndCreditCardFicheViewModel(repository);

    @Override // com.proje.mobilesales.core.base.BaseFicheFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
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
        final Context requireContext = this.requireContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mProgressDialogBuilder = new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity);
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint("RestrictedApi")
    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final View inflate = this.getLayoutInflater(bundle).inflate(R.layout.fragment_cashcreditfiche_header, viewGroup, false);
        txtCustomerTradingGroup = inflate.findViewById(R.id.txt_customerTradingGroup);
        txtAuthCode = inflate.findViewById(R.id.txt_authCode);
        etExplanation1 = inflate.findViewById(R.id.etExplanation1);
        etExplanation2 = inflate.findViewById(R.id.etExplanation2);
        etExplanation3 = inflate.findViewById(R.id.etExplanation3);
        etExplanation4 = inflate.findViewById(R.id.etExplanation4);
        txtWorkplace = inflate.findViewById(R.id.txt_workplace);
        txtDepartment = inflate.findViewById(R.id.txt_department);
        txtSpecialCode = inflate.findViewById(R.id.txt_specialCode);
        txtProjectCode = inflate.findViewById(R.id.txt_projectCode);
        txtPromiseCode = inflate.findViewById(R.id.txt_promiseCode);
        etInstallment = inflate.findViewById(R.id.etInstallment);
        lnCustomerTradingGroup = inflate.findViewById(R.id.ln_customerTradingGroup);
        lnAuthCode = inflate.findViewById(R.id.ln_authCode);
        lnExplanation1 = inflate.findViewById(R.id.ln_explanation1);
        lnExplanation2 = inflate.findViewById(R.id.ln_Explanation2);
        lnExplanation3 = inflate.findViewById(R.id.ln_explanation3);
        lnExplanation4 = inflate.findViewById(R.id.ln_explanation4);
        lnWorkplace = inflate.findViewById(R.id.ln_workplace);
        lnDepartment = inflate.findViewById(R.id.ln_department);
        lnSpecialCode = inflate.findViewById(R.id.ln_specialCode);
        lnProjectCode = inflate.findViewById(R.id.ln_projectCode);
        lnPromiseCode = inflate.findViewById(R.id.ln_promiseCode);
        lnInstallment = inflate.findViewById(R.id.ln_installment);
        lnBank = inflate.findViewById(R.id.ln_bank);
        txtBank = inflate.findViewById(R.id.txt_bank);
        lnAccount = inflate.findViewById(R.id.ln_account);
        txtAccount = inflate.findViewById(R.id.txt_account);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(final View view, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
    }

    @Override // com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        this.load();
        this.setVisibility();
    }

    @Override // com.proje.mobilesales.core.base.BaseFicheFragment
    protected void load() {
        this.loadDepartment();
        this.loadWorkplace();
        this.loadTradingGroup();
        this.loadSpecialCodes();
        this.loadProjects();
        this.loadBanks();
        final String code = mNetsis ? this.getCashCreditFiche().getBank().getCode() : String.valueOf(this.getCashCreditFiche().getBank().getLogicalRef());
        Intrinsics.checkNotNull(code);
        this.loadBankAccounts(code, false);
        if (mNetsis && mReceiptType != ReceiptType.CASH) {
            this.loadPromises();
            this.loadInstallment();
        }
        this.loadAuthCodes();
        this.loadExplanations();
    }

    private void loadDepartment() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        this.createSingleAlertCursorSales(customerOperation.getFicheMode(), lnDepartment, txtDepartment, this.getCashCreditFiche().getDivision(), true, R.string.str_department, R.string.qry_division, R.string.column_code);
        this.loadTextView(txtDepartment, this.getCashCreditFiche().getDivision().toString(), true);
    }

    private void loadWorkplace() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        this.createSingleAlertCursorSales(customerOperation.getFicheMode(), lnWorkplace, txtWorkplace, this.getCashCreditFiche().getBranch(), true, R.string.str_branch, R.string.qry_branch, R.string.column_code);
        this.loadTextView(txtWorkplace, this.getCashCreditFiche().getBranch().toString(), true);
    }

    private void loadTradingGroup() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        this.createSingleAlertCursorSales(customerOperation.getFicheMode(), lnCustomerTradingGroup, txtCustomerTradingGroup, this.getCashCreditFiche().getTradinggrp(), true, R.string.str_trade_group, R.string.qry_trade_group, R.string.column_code);
        this.loadTextView(txtCustomerTradingGroup, this.getCashCreditFiche().getTradinggrp().toString(), true);
    }

    private void loadSpecialCodes() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        this.createSingleAlertCursorSales(customerOperation.getFicheMode(), lnSpecialCode, txtSpecialCode, this.getCashCreditFiche().getSpecode(), true, R.string.str_special_code, R.string.qry_spe_code, R.string.column_code, "44");
        this.loadTextView(txtSpecialCode, this.getCashCreditFiche().getSpecode().toString(), true);
    }

    private void loadProjects() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        this.createSingleAlertCursorSales(customerOperation.getFicheMode(), lnProjectCode, txtProjectCode, this.getCashCreditFiche().getProjectCode(), true, R.string.str_project_code, R.string.qry_project_code, R.string.column_name);
        this.loadTextView(txtProjectCode, this.getCashCreditFiche().getProjectCode().toString(), true);
    }

    private void loadPromises() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnPromiseCode;
        final TextView textView = txtPromiseCode;
        final FicheDiscountRefProp aggrCode = this.getCashCreditFiche().getAggrCode();
        final FicheDiscountRefProp bank = this.getCashCreditFiche().getBank();
        final Boolean bool = Boolean.TRUE;
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, aggrCode, true, R.string.str_contract_code, R.string.str_not_found_bank_agreement, R.string.qry_aggr_code, R.string.column_name, bank, DateAndTimeUtils.getSqlDate(bool), DateAndTimeUtils.getSqlDate(bool));
        this.loadTextView(txtPromiseCode, this.getCashCreditFiche().getAggrCode().toString(), true);
    }

    private void loadInstallment() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        boolean z = false;
        this.createEditTextAddTextChangedListener(customerOperation.getFicheMode(), lnInstallment, etInstallment, this.getCashCreditFiche().getInstallmentCount(), mNetsis && mReceiptType != ReceiptType.CASH);
        final EditText editText = etInstallment;
        final String definition = this.getCashCreditFiche().getInstallmentCount().getDefinition();
        if (mNetsis && mReceiptType != ReceiptType.CASH) {
            z = true;
        }
        this.loadTextView(editText, definition, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void bankChangeEvent(final FicheDiscountRefProp bankProp) {
        Intrinsics.checkNotNullParameter(bankProp, "bankProp");
        this.getCashCreditFiche().getBankAcc().reset();
        final String code = mNetsis ? bankProp.getCode() : String.valueOf(bankProp.getLogicalRef());
        Intrinsics.checkNotNull(code);
        this.loadBankAccounts(code, true);
    }

    private void loadBanks() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnBank;
        final TextView textView = txtBank;
        final FicheDiscountRefProp bank = this.getCashCreditFiche().getBank();
        final ReceiptType receiptType = mReceiptType;
        final ReceiptType receiptType2 = ReceiptType.CREDIT;
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, bank, receiptType == receiptType2, R.string.str_banks, R.string.str_not_found_bank, R.string.qry_bank, R.string.column_name, this.getCashCreditFiche().getBank());
        this.loadTextView(txtBank, this.getCashCreditFiche().getBank().toString(), mReceiptType == receiptType2);
    }

    @SuppressLint(HttpHeaders.RANGE)
    private void loadBankAccounts(final String str, final boolean z) {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnAccount;
        final TextView textView = txtAccount;
        final FicheDiscountRefProp bankAcc = this.getCashCreditFiche().getBankAcc();
        final ReceiptType receiptType = mReceiptType;
        final ReceiptType receiptType2 = ReceiptType.CREDIT;
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, bankAcc, receiptType == receiptType2, R.string.str_bank_accounts, -1 == getCashCreditFiche().getBank().getLogicalRef() ? R.string.str_question_select_bank : R.string.str_not_found_bank_account, mNetsis ? R.string.net_qry_bankacc : R.string.qry_bankacc, R.string.column_name, null, str);
        if (z) {
            final Cursor cursor = this.getCursor(this.getString(mNetsis ? R.string.net_qry_bankacc : R.string.qry_bankacc), str);
            if (null != cursor && cursor.moveToFirst()) {
                this.getCashCreditFiche().getBankAcc().setWhich(0);
                this.getCashCreditFiche().getBankAcc().setLogicalRef(cursor.getInt(cursor.getColumnIndex(this.getString(R.string.column_id))));
                this.getCashCreditFiche();
                FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(this.getString(R.string.column_name))));
                this.getCashCreditFiche().getBankAcc().setCode(cursor.getString(cursor.getColumnIndex(this.getString(R.string.column_code))));
            }
        }
        this.loadTextView(txtAccount, this.getCashCreditFiche().getBankAcc().toString(), mReceiptType == receiptType2);
    }

    private void loadAuthCodes() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        this.createSingleAlertCursorSales(customerOperation.getFicheMode(), lnAuthCode, txtAuthCode, this.getCashCreditFiche().getCyphcode(), true, R.string.str_auth_code, R.string.qry_warranty, R.string.column_code, "44");
        this.loadTextView(txtAuthCode, this.getCashCreditFiche().getCyphcode().toString(), true);
    }

    private void loadExplanations() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        this.createEditTextAddTextChangedListener(customerOperation.getFicheMode(), lnExplanation1, etExplanation1, this.getCashCreditFiche().getExplanation1(), true);
        this.loadTextView(etExplanation1, this.getCashCreditFiche().getExplanation1().toString(), true);
        final CustomerOperation customerOperation2 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation2);
        this.createEditTextAddTextChangedListener(customerOperation2.getFicheMode(), lnExplanation2, etExplanation2, this.getCashCreditFiche().getExplanation2(), true);
        this.loadTextView(etExplanation2, this.getCashCreditFiche().getExplanation2().toString(), true);
        final CustomerOperation customerOperation3 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation3);
        this.createEditTextAddTextChangedListener(customerOperation3.getFicheMode(), lnExplanation3, etExplanation3, this.getCashCreditFiche().getExplanation3(), true);
        this.loadTextView(etExplanation3, this.getCashCreditFiche().getExplanation3().toString(), true);
        final CustomerOperation customerOperation4 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation4);
        this.createEditTextAddTextChangedListener(customerOperation4.getFicheMode(), lnExplanation4, etExplanation4, this.getCashCreditFiche().getExplanation4(), true);
        this.loadTextView(etExplanation4, this.getCashCreditFiche().getExplanation4().toString(), true);
    }

    private void setVisibility() {
        final String obj;
        if (mReceiptType == ReceiptType.CASH) {
            final String upVal = viewModel.getBaseErp().getLogoSqlHelper().upVal("93");
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
            obj = upVal.subSequence(i2, length + 1).toString();
        } else {
            final String upVal2 = viewModel.getBaseErp().getLogoSqlHelper().upVal("106");
            Intrinsics.checkNotNullExpressionValue(upVal2, "upVal(...)");
            int length2 = upVal2.length() - 1;
            int i3 = 0;
            boolean z3 = false;
            while (i3 <= length2) {
                final boolean z4 = 0 >= Intrinsics.compare(upVal2.charAt(!z3 ? i3 : length2), 32);
                if (z3) {
                    if (!z4) {
                        break;
                    } else {
                        length2--;
                    }
                } else if (z4) {
                    i3++;
                } else {
                    z3 = true;
                }
            }
            obj = upVal2.subSequence(i3, length2 + 1).toString();
        }
        final boolean z5 = mNetsis;
        if (!z5 || (z5 && mReceiptType == ReceiptType.CASH)) {
            final LinearLayout linearLayout = lnPromiseCode;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(8);
            final LinearLayout linearLayout2 = lnInstallment;
            Intrinsics.checkNotNull(linearLayout2);
            linearLayout2.setVisibility(8);
        }
        if (!StringsKt.containsdefault((CharSequence) obj, (CharSequence) "0", false, 2, (Object) null)) {
            final LinearLayout linearLayout3 = lnWorkplace;
            Intrinsics.checkNotNull(linearLayout3);
            linearLayout3.setVisibility(8);
        }
        if (!StringsKt.containsdefault((CharSequence) obj, (CharSequence) BuildConfig.NETSIS_DEMO_PASSWORD, false, 2, (Object) null)) {
            final LinearLayout linearLayout4 = lnDepartment;
            Intrinsics.checkNotNull(linearLayout4);
            linearLayout4.setVisibility(8);
        }
        if (!StringsKt.containsdefault((CharSequence) obj, (CharSequence) ExifInterface.GPS_MEASUREMENT_2D, false, 2, (Object) null)) {
            final LinearLayout linearLayout5 = lnSpecialCode;
            Intrinsics.checkNotNull(linearLayout5);
            linearLayout5.setVisibility(8);
        }
        if (!StringsKt.containsdefault((CharSequence) obj, (CharSequence) ExifInterface.GPS_MEASUREMENT_3D, false, 2, (Object) null)) {
            final LinearLayout linearLayout6 = lnProjectCode;
            Intrinsics.checkNotNull(linearLayout6);
            linearLayout6.setVisibility(8);
        }
        if (!StringsKt.containsdefault((CharSequence) obj, (CharSequence) "4", false, 2, (Object) null)) {
            final LinearLayout linearLayout7 = lnAuthCode;
            Intrinsics.checkNotNull(linearLayout7);
            linearLayout7.setVisibility(8);
        }
        if (StringsKt.containsdefault((CharSequence) obj, (CharSequence) "5", false, 2, (Object) null)) {
            return;
        }
        final LinearLayout linearLayout8 = lnCustomerTradingGroup;
        Intrinsics.checkNotNull(linearLayout8);
        linearLayout8.setVisibility(8);
    }

    private CashCreditFiche getCashCreditFiche() {
        final CashAndCreditCardFicheActivity cashCreditFicheActivity = this.getCashCreditFicheActivity();
        Intrinsics.checkNotNull(cashCreditFicheActivity);
        final CashCreditFiche cashCreditFiche = cashCreditFicheActivity.getCashCreditFiche();
        Intrinsics.checkNotNull(cashCreditFiche);
        return cashCreditFiche;
    }

    private CashAndCreditCardFicheActivity getCashCreditFicheActivity() {
        return (CashAndCreditCardFicheActivity) this.getActivity();
    }
}
