package com.proje.mobilesales.features.collections.chequeanddeedfiche.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFiche;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.repository.ChequeAndDeedFicheRepository;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.activity.ChequeAndDeedFicheActivity;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.viewmodel.ChequeAndDeedFicheViewModel;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;


/* compiled from: ChequeDeedFicheHeaderFragment.kt */

public final class ChequeDeedFicheHeaderFragment extends BaseFicheFragment {
    private CustomerOperation customerOperation;
    private int customerRef;
    private EditText etDocumentNo;
    private EditText etExplanation1;
    private EditText etExplanation2;
    private EditText etExplanation3;
    private EditText etExplanation4;
    private LinearLayout lnAuthcode;
    private LinearLayout lnBordroTarihi;
    private LinearLayout lnCustomerTradingGroup;
    private LinearLayout lnDepartment;
    private LinearLayout lnDocumentNo;
    private LinearLayout lnExplanation1;
    private LinearLayout lnExplanation2;
    private LinearLayout lnExplanation3;
    private LinearLayout lnExplanation4;
    private LinearLayout lnProjectCode;
    private LinearLayout lnSpecialCode;
    private LinearLayout lnWorkplace;
    private MatterSettings mMatterSettings;
    private boolean mNetsis;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private ReceiptType mReceiptType;
    private TextView txtAuthcode;
    private TextView txtBordroTarihi;
    private TextView txtCustomerTradingGroup;
    private TextView txtDepartment;
    private TextView txtProjectCode;
    private TextView txtSpecialCode;
    private TextView txtWorkplace;
    private final ChequeAndDeedFicheRepository repository = new ChequeAndDeedFicheRepository();
    private final ChequeAndDeedFicheViewModel viewModel = new ChequeAndDeedFicheViewModel(repository);

    @Override // com.proje.mobilesales.core.base.BaseFicheFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        mNetsis = baseErp.getErpType() == ErpType.NETSIS;
        final Bundle arguments = this.getArguments();
        Intrinsics.checkNotNull(arguments);
        customerOperation = arguments.getParcelable(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE);
        final Bundle arguments2 = this.getArguments();
        Intrinsics.checkNotNull(arguments2);
        customerRef = arguments2.getInt(IntentExtraName.EXTRA_CUSTOMER_REF);
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        mReceiptType = customerOperation.getReceiptType();
        final BaseErp baseErp = this.baseErp;
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
        final View inflate = this.getLayoutInflater(bundle).inflate(R.layout.fragment_chequedeedfiche_header, viewGroup, false);
        txtCustomerTradingGroup = inflate.findViewById(R.id.txt_customerTradingGroup);
        txtAuthcode = inflate.findViewById(R.id.txt_authCode);
        etExplanation1 = inflate.findViewById(R.id.etExplanation1);
        etExplanation2 = inflate.findViewById(R.id.etExplanation2);
        etExplanation3 = inflate.findViewById(R.id.etExplanation3);
        etExplanation4 = inflate.findViewById(R.id.etExplanation4);
        txtWorkplace = inflate.findViewById(R.id.txt_workplace);
        txtDepartment = inflate.findViewById(R.id.txt_department);
        txtSpecialCode = inflate.findViewById(R.id.txt_specialCode);
        txtProjectCode = inflate.findViewById(R.id.txt_projectCode);
        txtBordroTarihi = inflate.findViewById(R.id.txt_BordroTarihi);
        etDocumentNo = inflate.findViewById(R.id.et_documentNo);
        lnCustomerTradingGroup = inflate.findViewById(R.id.ln_customerTradingGroup);
        lnAuthcode = inflate.findViewById(R.id.ln_authCode);
        lnExplanation1 = inflate.findViewById(R.id.ln_explanation1);
        lnExplanation2 = inflate.findViewById(R.id.ln_Explanation2);
        lnExplanation3 = inflate.findViewById(R.id.ln_explanation3);
        lnExplanation4 = inflate.findViewById(R.id.ln_explanation4);
        lnWorkplace = inflate.findViewById(R.id.ln_workplace);
        lnDepartment = inflate.findViewById(R.id.ln_department);
        lnSpecialCode = inflate.findViewById(R.id.ln_specialCode);
        lnProjectCode = inflate.findViewById(R.id.ln_projectCode);
        lnDocumentNo = inflate.findViewById(R.id.ln_documentNo);
        lnBordroTarihi = inflate.findViewById(R.id.ln_BordroTarihi);
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
        this.loadChequeDeedDate();
        this.loadDepartment();
        this.loadWorkplace();
        this.loadTradingGroup();
        this.loadSpecialCodes();
        this.loadProjects();
        this.loadAuthCodes();
        this.loadExplanations();
        this.loadDocumentNo();
    }

    private void loadChequeDeedDate() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnBordroTarihi;
        final TextView textView = txtBordroTarihi;
        final ChequeDeedFiche chequeDeedFiche = this.getChequeDeedFiche();
        this.createDateAlertDialog(ficheMode, linearLayout, textView, null != chequeDeedFiche ? chequeDeedFiche.getChequeDeedDate() : null, true, false, R.string.str_expiry);
        final TextView textView2 = txtBordroTarihi;
        final ChequeDeedFiche chequeDeedFiche2 = this.getChequeDeedFiche();
        this.loadTextView(textView2, String.valueOf(null != chequeDeedFiche2 ? chequeDeedFiche2.getChequeDeedDate() : null), true);
    }

    private void loadDepartment() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnDepartment;
        final TextView textView = txtDepartment;
        final ChequeDeedFiche chequeDeedFiche = this.getChequeDeedFiche();
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, null != chequeDeedFiche ? chequeDeedFiche.getDivision() : null, true, R.string.str_department, R.string.qry_division, R.string.column_code);
        final TextView textView2 = txtDepartment;
        final ChequeDeedFiche chequeDeedFiche2 = this.getChequeDeedFiche();
        this.loadTextView(textView2, String.valueOf(null != chequeDeedFiche2 ? chequeDeedFiche2.getDivision() : null), true);
    }

    private void loadWorkplace() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnWorkplace;
        final TextView textView = txtWorkplace;
        final ChequeDeedFiche chequeDeedFiche = this.getChequeDeedFiche();
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, null != chequeDeedFiche ? chequeDeedFiche.getBranch() : null, true, R.string.str_branch, R.string.qry_branch, R.string.column_code);
        final TextView textView2 = txtWorkplace;
        final ChequeDeedFiche chequeDeedFiche2 = this.getChequeDeedFiche();
        this.loadTextView(textView2, String.valueOf(null != chequeDeedFiche2 ? chequeDeedFiche2.getBranch() : null), true);
    }

    private void loadTradingGroup() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnCustomerTradingGroup;
        final TextView textView = txtCustomerTradingGroup;
        final ChequeDeedFiche chequeDeedFiche = this.getChequeDeedFiche();
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, null != chequeDeedFiche ? chequeDeedFiche.getTradinggrp() : null, true, R.string.str_trade_group, R.string.qry_trade_group, R.string.column_code);
        final TextView textView2 = txtCustomerTradingGroup;
        final ChequeDeedFiche chequeDeedFiche2 = this.getChequeDeedFiche();
        this.loadTextView(textView2, String.valueOf(null != chequeDeedFiche2 ? chequeDeedFiche2.getTradinggrp() : null), true);
    }

    private void loadSpecialCodes() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnSpecialCode;
        final TextView textView = txtSpecialCode;
        final ChequeDeedFiche chequeDeedFiche = this.getChequeDeedFiche();
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, null != chequeDeedFiche ? chequeDeedFiche.getSpecode() : null, true, R.string.str_special_code, R.string.qry_spe_code, R.string.column_code, "44");
        final TextView textView2 = txtSpecialCode;
        final ChequeDeedFiche chequeDeedFiche2 = this.getChequeDeedFiche();
        this.loadTextView(textView2, String.valueOf(null != chequeDeedFiche2 ? chequeDeedFiche2.getSpecode() : null), true);
    }

    private void loadProjects() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnProjectCode;
        final TextView textView = txtProjectCode;
        final ChequeDeedFiche chequeDeedFiche = this.getChequeDeedFiche();
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, null != chequeDeedFiche ? chequeDeedFiche.getProjectCode() : null, true, R.string.str_special_code, R.string.qry_project_code, R.string.column_name);
        final TextView textView2 = txtProjectCode;
        final ChequeDeedFiche chequeDeedFiche2 = this.getChequeDeedFiche();
        this.loadTextView(textView2, String.valueOf(null != chequeDeedFiche2 ? chequeDeedFiche2.getProjectCode() : null), true);
    }

    private void loadDocumentNo() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnDocumentNo;
        final EditText editText = etDocumentNo;
        final ChequeDeedFiche chequeDeedFiche = this.getChequeDeedFiche();
        this.createEditTextAddTextChangedListener(ficheMode, linearLayout, editText, null != chequeDeedFiche ? chequeDeedFiche.getDocNo() : null, true);
        final EditText editText2 = etDocumentNo;
        final ChequeDeedFiche chequeDeedFiche2 = this.getChequeDeedFiche();
        this.loadTextView(editText2, String.valueOf(null != chequeDeedFiche2 ? chequeDeedFiche2.getDocNo() : null), true);
    }

    private void loadAuthCodes() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnAuthcode;
        final TextView textView = txtAuthcode;
        final ChequeDeedFiche chequeDeedFiche = this.getChequeDeedFiche();
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, null != chequeDeedFiche ? chequeDeedFiche.getCyphcode() : null, true, R.string.str_auth_code, R.string.qry_warranty, R.string.column_code, "44");
        final TextView textView2 = txtAuthcode;
        final ChequeDeedFiche chequeDeedFiche2 = this.getChequeDeedFiche();
        this.loadTextView(textView2, String.valueOf(null != chequeDeedFiche2 ? chequeDeedFiche2.getCyphcode() : null), true);
    }

    private void loadExplanations() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnExplanation1;
        final EditText editText = etExplanation1;
        final ChequeDeedFiche chequeDeedFiche = this.getChequeDeedFiche();
        this.createEditTextAddTextChangedListener(ficheMode, linearLayout, editText, null != chequeDeedFiche ? chequeDeedFiche.getExplanation1() : null, true);
        final EditText editText2 = etExplanation1;
        final ChequeDeedFiche chequeDeedFiche2 = this.getChequeDeedFiche();
        this.loadTextView(editText2, String.valueOf(null != chequeDeedFiche2 ? chequeDeedFiche2.getExplanation1() : null), true);
        final CustomerOperation customerOperation2 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation2);
        final FicheMode ficheMode2 = customerOperation2.getFicheMode();
        final LinearLayout linearLayout2 = lnExplanation2;
        final EditText editText3 = etExplanation2;
        final ChequeDeedFiche chequeDeedFiche3 = this.getChequeDeedFiche();
        this.createEditTextAddTextChangedListener(ficheMode2, linearLayout2, editText3, null != chequeDeedFiche3 ? chequeDeedFiche3.getExplanation2() : null, true);
        final EditText editText4 = etExplanation2;
        final ChequeDeedFiche chequeDeedFiche4 = this.getChequeDeedFiche();
        this.loadTextView(editText4, String.valueOf(null != chequeDeedFiche4 ? chequeDeedFiche4.getExplanation2() : null), true);
        final CustomerOperation customerOperation3 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation3);
        final FicheMode ficheMode3 = customerOperation3.getFicheMode();
        final LinearLayout linearLayout3 = lnExplanation3;
        final EditText editText5 = etExplanation3;
        final ChequeDeedFiche chequeDeedFiche5 = this.getChequeDeedFiche();
        this.createEditTextAddTextChangedListener(ficheMode3, linearLayout3, editText5, null != chequeDeedFiche5 ? chequeDeedFiche5.getExplanation3() : null, true);
        final EditText editText6 = etExplanation3;
        final ChequeDeedFiche chequeDeedFiche6 = this.getChequeDeedFiche();
        this.loadTextView(editText6, String.valueOf(null != chequeDeedFiche6 ? chequeDeedFiche6.getExplanation3() : null), true);
        final CustomerOperation customerOperation4 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation4);
        final FicheMode ficheMode4 = customerOperation4.getFicheMode();
        final LinearLayout linearLayout4 = lnExplanation4;
        final EditText editText7 = etExplanation4;
        final ChequeDeedFiche chequeDeedFiche7 = this.getChequeDeedFiche();
        this.createEditTextAddTextChangedListener(ficheMode4, linearLayout4, editText7, null != chequeDeedFiche7 ? chequeDeedFiche7.getExplanation4() : null, true);
        final EditText editText8 = etExplanation4;
        final ChequeDeedFiche chequeDeedFiche8 = this.getChequeDeedFiche();
        this.loadTextView(editText8, String.valueOf(null != chequeDeedFiche8 ? chequeDeedFiche8.getExplanation4() : null), true);
    }

    private ChequeDeedFiche getChequeDeedFiche() {
        final ChequeAndDeedFicheActivity chequeDeedFicheActivity = this.getChequeDeedFicheActivity();
        Intrinsics.checkNotNull(chequeDeedFicheActivity);
        return chequeDeedFicheActivity.getChequeDeedFiche();
    }

    private ChequeAndDeedFicheActivity getChequeDeedFicheActivity() {
        return (ChequeAndDeedFicheActivity) this.getActivity();
    }

    private void setVisibility() {
        final String obj;
        if (mReceiptType == ReceiptType.CHEQUE) {
            final String upVal = viewModel.getBaseErp().getLogoSqlHelper().upVal("119");
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
            final String upVal2 = viewModel.getBaseErp().getLogoSqlHelper().upVal("132");
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
        if (!StringsKt.containsdefault((CharSequence) obj, (CharSequence) "0", false, 2, (Object) null)) {
            final LinearLayout linearLayout = lnWorkplace;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(8);
        }
        if (!StringsKt.containsdefault((CharSequence) obj, (CharSequence) BuildConfig.NETSIS_DEMO_PASSWORD, false, 2, (Object) null)) {
            final LinearLayout linearLayout2 = lnDepartment;
            Intrinsics.checkNotNull(linearLayout2);
            linearLayout2.setVisibility(8);
        }
        if (!StringsKt.containsdefault((CharSequence) obj, (CharSequence) ExifInterface.GPS_MEASUREMENT_2D, false, 2, (Object) null)) {
            final LinearLayout linearLayout3 = lnSpecialCode;
            Intrinsics.checkNotNull(linearLayout3);
            linearLayout3.setVisibility(8);
        }
        if (!StringsKt.containsdefault((CharSequence) obj, (CharSequence) ExifInterface.GPS_MEASUREMENT_3D, false, 2, (Object) null)) {
            final LinearLayout linearLayout4 = lnProjectCode;
            Intrinsics.checkNotNull(linearLayout4);
            linearLayout4.setVisibility(8);
        }
        if (!StringsKt.containsdefault((CharSequence) obj, (CharSequence) "4", false, 2, (Object) null)) {
            final LinearLayout linearLayout5 = lnAuthcode;
            Intrinsics.checkNotNull(linearLayout5);
            linearLayout5.setVisibility(8);
        }
        if (StringsKt.containsdefault((CharSequence) obj, (CharSequence) "5", false, 2, (Object) null)) {
            return;
        }
        final LinearLayout linearLayout6 = lnCustomerTradingGroup;
        Intrinsics.checkNotNull(linearLayout6);
        linearLayout6.setVisibility(8);
    }
}
