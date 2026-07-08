package com.proje.mobilesales.features.collections.chequeanddeedfiche.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseFicheFragment;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFicheDetail;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.repository.ChequeAndDeedFicheRepository;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.activity.ChequeAndDeedFicheActivity;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.viewmodel.ChequeAndDeedFicheViewModel;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: ChequeDeedFicheDetailEnterFragment.kt */

public final class ChequeDeedFicheDetailEnterFragment extends BaseFicheFragment {
    private static final String STATE_CUSTOMER_OPERATION = "state:customerOperation";
    private static final String STATE_DETAIL = "state:chequeDeedDetail";
    private ChequeDeedFicheDetail chequeDeedFicheDetail;
    private CustomerOperation customerOperation;
    private int customerRef;
    private EditText etAccountNo;
    private EditText etAmount;
    private EditText etBankName;
    private EditText etBranch;
    private final Object etCurr;
    private EditText etDebtor;
    private EditText etGuarantor;
    private EditText etPaymentPlace;
    private EditText etSerialNo;
    private EditText etStamp;
    private LinearLayout lnAccountNo;
    private LinearLayout lnAmount;
    private LinearLayout lnBankName;
    private LinearLayout lnBranch;
    private LinearLayout lnDebtor;
    private LinearLayout lnExpiry;
    private LinearLayout lnGuarantor;
    private LinearLayout lnPaymentPlace;
    private LinearLayout lnSerialNo;
    private LinearLayout lnStamp;
    private MatterSettings mMatterSettings;
    private boolean mNetsis;
    private ReceiptType mReceiptType;
    private TextView txtExpiry;
    public static final Companion Companion = new Companion(null);
    public static final String CHEQUE_DEED_DETAIL_ENTER = "CHEQUE_DEED_DETAIL_ENTER." + ChequeDeedFicheDetailEnterFragment.class.getName();
    private final ChequeAndDeedFicheRepository repository = new ChequeAndDeedFicheRepository();
    private final ChequeAndDeedFicheViewModel viewModel = new ChequeAndDeedFicheViewModel(repository);

    @Override // com.proje.mobilesales.core.base.BaseFicheFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (null != bundle) {
            chequeDeedFicheDetail = bundle.getParcelable(ChequeDeedFicheDetailEnterFragment.STATE_DETAIL);
            customerOperation = bundle.getParcelable(ChequeDeedFicheDetailEnterFragment.STATE_CUSTOMER_OPERATION);
        } else {
            final Bundle arguments = this.getArguments();
            Intrinsics.checkNotNull(arguments);
            chequeDeedFicheDetail = arguments.getParcelable(ChequeAndDeedFicheActivity.Companion.getCHEQUE_DEED_DETAIL());
            final Bundle arguments2 = this.getArguments();
            Intrinsics.checkNotNull(arguments2);
            customerOperation = arguments2.getParcelable(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE);
            final Bundle arguments3 = this.getArguments();
            Intrinsics.checkNotNull(arguments3);
            customerRef = arguments3.getInt(IntentExtraName.EXTRA_CUSTOMER_REF);
        }
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        mReceiptType = customerOperation.getReceiptType();
        mNetsis = viewModel.getBaseErp().getErpType() == ErpType.NETSIS;
        final BaseErp<?> baseErp = viewModel.getBaseErp();
        final Context context = this.getContext();
        final CustomerOperation customerOperation2 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation2);
        mMatterSettings = baseErp.getMatterSettings(context, customerOperation2.getFicheType());
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint("RestrictedApi")
    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final View inflate = this.getLayoutInflater(bundle).inflate(R.layout.fragment_chequedeedfiche_detailenter, viewGroup, false);
        etAmount = inflate.findViewById(R.id.et_amount);
        txtExpiry = inflate.findViewById(R.id.txt_expiry);
        lnAmount = inflate.findViewById(R.id.ln_amount);
        lnExpiry = inflate.findViewById(R.id.ln_expiry);
        etSerialNo = inflate.findViewById(R.id.et_serialNo);
        etDebtor = inflate.findViewById(R.id.et_debtor);
        lnSerialNo = inflate.findViewById(R.id.ln_serialNo);
        lnDebtor = inflate.findViewById(R.id.ln_debtor);
        etBankName = inflate.findViewById(R.id.et_bankName);
        etBranch = inflate.findViewById(R.id.et_branch);
        lnBankName = inflate.findViewById(R.id.ln_bankName);
        lnBranch = inflate.findViewById(R.id.ln_branch);
        etAccountNo = inflate.findViewById(R.id.et_accountNo);
        etGuarantor = inflate.findViewById(R.id.et_guarantor);
        lnAccountNo = inflate.findViewById(R.id.ln_accountNo);
        lnGuarantor = inflate.findViewById(R.id.ln_guarantor);
        etPaymentPlace = inflate.findViewById(R.id.et_paymentPlace);
        etStamp = inflate.findViewById(R.id.et_stamp);
        lnPaymentPlace = inflate.findViewById(R.id.ln_paymentPlace);
        lnStamp = inflate.findViewById(R.id.ln_stamp);
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
    }

    @Override // com.proje.mobilesales.core.base.BaseFicheFragment
    protected void load() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnAmount;
        final EditText editText = etAmount;
        final ChequeDeedFicheDetail chequeDeedFicheDetail = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail);
        this.createEditTextAddTextChangedListener(ficheMode, linearLayout, editText, chequeDeedFicheDetail.getTotal(), true);
        final EditText editText2 = etAmount;
        final ChequeDeedFicheDetail chequeDeedFicheDetail2 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail2);
        this.loadTextView(editText2, chequeDeedFicheDetail2.getTotal().toString(), true);
        final CustomerOperation customerOperation2 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation2);
        final FicheMode ficheMode2 = customerOperation2.getFicheMode();
        final LinearLayout linearLayout2 = lnExpiry;
        final TextView textView = txtExpiry;
        final ChequeDeedFicheDetail chequeDeedFicheDetail3 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail3);
        this.createDateAlertDialog(ficheMode2, linearLayout2, textView, chequeDeedFicheDetail3.getDueDate(), true, true, R.string.str_expiry);
        final TextView textView2 = txtExpiry;
        final ChequeDeedFicheDetail chequeDeedFicheDetail4 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail4);
        this.loadTextView(textView2, chequeDeedFicheDetail4.getDueDate().toString(), true);
        final CustomerOperation customerOperation3 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation3);
        final FicheMode ficheMode3 = customerOperation3.getFicheMode();
        final LinearLayout linearLayout3 = lnSerialNo;
        final EditText editText3 = etSerialNo;
        final ChequeDeedFicheDetail chequeDeedFicheDetail5 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail5);
        this.createEditTextAddTextChangedListener(ficheMode3, linearLayout3, editText3, chequeDeedFicheDetail5.getSerialNo(), true);
        final EditText editText4 = etSerialNo;
        final ChequeDeedFicheDetail chequeDeedFicheDetail6 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail6);
        this.loadTextView(editText4, chequeDeedFicheDetail6.getSerialNo().toString(), true);
        final CustomerOperation customerOperation4 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation4);
        final FicheMode ficheMode4 = customerOperation4.getFicheMode();
        final LinearLayout linearLayout4 = lnDebtor;
        final EditText editText5 = etDebtor;
        final ChequeDeedFicheDetail chequeDeedFicheDetail7 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail7);
        this.createEditTextAddTextChangedListener(ficheMode4, linearLayout4, editText5, chequeDeedFicheDetail7.getDebited(), true);
        this.loadTextView(etDebtor, viewModel.getSqlHelper().getClName(customerRef), true);
        final CustomerOperation customerOperation5 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation5);
        final FicheMode ficheMode5 = customerOperation5.getFicheMode();
        final LinearLayout linearLayout5 = lnBankName;
        final EditText editText6 = etBankName;
        final ChequeDeedFicheDetail chequeDeedFicheDetail8 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail8);
        this.createEditTextAddTextChangedListener(ficheMode5, linearLayout5, editText6, chequeDeedFicheDetail8.getBankName(), true);
        final EditText editText7 = etBankName;
        final ChequeDeedFicheDetail chequeDeedFicheDetail9 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail9);
        this.loadTextView(editText7, chequeDeedFicheDetail9.getBankName().toString(), true);
        final CustomerOperation customerOperation6 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation6);
        final FicheMode ficheMode6 = customerOperation6.getFicheMode();
        final LinearLayout linearLayout6 = lnBranch;
        final EditText editText8 = etBranch;
        final ChequeDeedFicheDetail chequeDeedFicheDetail10 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail10);
        this.createEditTextAddTextChangedListener(ficheMode6, linearLayout6, editText8, chequeDeedFicheDetail10.getBankBranchName(), true);
        final EditText editText9 = etBranch;
        final ChequeDeedFicheDetail chequeDeedFicheDetail11 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail11);
        this.loadTextView(editText9, chequeDeedFicheDetail11.getBankBranchName().toString(), true);
        final CustomerOperation customerOperation7 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation7);
        final FicheMode ficheMode7 = customerOperation7.getFicheMode();
        final LinearLayout linearLayout7 = lnAccountNo;
        final EditText editText10 = etAccountNo;
        final ChequeDeedFicheDetail chequeDeedFicheDetail12 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail12);
        this.createEditTextAddTextChangedListener(ficheMode7, linearLayout7, editText10, chequeDeedFicheDetail12.getAccNo(), true);
        final EditText editText11 = etAccountNo;
        final ChequeDeedFicheDetail chequeDeedFicheDetail13 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail13);
        this.loadTextView(editText11, chequeDeedFicheDetail13.getAccNo().toString(), true);
        final CustomerOperation customerOperation8 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation8);
        final FicheMode ficheMode8 = customerOperation8.getFicheMode();
        final LinearLayout linearLayout8 = lnGuarantor;
        final EditText editText12 = etGuarantor;
        final ChequeDeedFicheDetail chequeDeedFicheDetail14 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail14);
        final FicheStringProp inCharge = chequeDeedFicheDetail14.getInCharge();
        final ReceiptType receiptType = mReceiptType;
        final ReceiptType receiptType2 = ReceiptType.DEED;
        this.createEditTextAddTextChangedListener(ficheMode8, linearLayout8, editText12, inCharge, receiptType == receiptType2);
        final EditText editText13 = etGuarantor;
        final ChequeDeedFicheDetail chequeDeedFicheDetail15 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail15);
        this.loadTextView(editText13, chequeDeedFicheDetail15.getInCharge().toString(), true);
        final CustomerOperation customerOperation9 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation9);
        final FicheMode ficheMode9 = customerOperation9.getFicheMode();
        final LinearLayout linearLayout9 = lnPaymentPlace;
        final EditText editText14 = etPaymentPlace;
        final ChequeDeedFicheDetail chequeDeedFicheDetail16 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail16);
        this.createEditTextAddTextChangedListener(ficheMode9, linearLayout9, editText14, chequeDeedFicheDetail16.getPayWhere(), mReceiptType == receiptType2);
        final EditText editText15 = etPaymentPlace;
        final ChequeDeedFicheDetail chequeDeedFicheDetail17 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail17);
        this.loadTextView(editText15, chequeDeedFicheDetail17.getPayWhere().toString(), true);
        final CustomerOperation customerOperation10 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation10);
        final FicheMode ficheMode10 = customerOperation10.getFicheMode();
        final LinearLayout linearLayout10 = lnStamp;
        final EditText editText16 = etStamp;
        final ChequeDeedFicheDetail chequeDeedFicheDetail18 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail18);
        this.createEditTextAddTextChangedListener(ficheMode10, linearLayout10, editText16, chequeDeedFicheDetail18.getPul(), mReceiptType == receiptType2);
        final EditText editText17 = etStamp;
        final ChequeDeedFicheDetail chequeDeedFicheDetail19 = this.chequeDeedFicheDetail;
        Intrinsics.checkNotNull(chequeDeedFicheDetail19);
        this.loadTextView(editText17, chequeDeedFicheDetail19.getPul().toString(), true);
    }

    /* compiled from: ChequeDeedFicheDetailEnterFragment.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
