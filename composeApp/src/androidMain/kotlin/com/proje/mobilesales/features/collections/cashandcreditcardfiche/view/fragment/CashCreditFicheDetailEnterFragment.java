package com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import cardtek.masterpass.MasterPassServices;
import cardtek.masterpass.data.MasterPassCard;
import cardtek.masterpass.interfaces.LinkCardToClientListener;
import cardtek.masterpass.interfaces.PurchaseListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.PurchaseResult;
import cardtek.masterpass.util.MasterPassInfo;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseFicheFragment;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.MasterPassPinOption;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.PurchaseCompleteListener;
import com.proje.mobilesales.core.masterpass.Constants;
import com.proje.mobilesales.core.masterpass.MasterPassCardItem;
import com.proje.mobilesales.core.masterpass.MasterPassResult;
import com.proje.mobilesales.core.masterpass.TokenGenerator;
import com.proje.mobilesales.core.searchdialog.BottomSheetDialogSearchResultListener;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFicheDetail;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.repository.CashAndCreditCardFicheRepository;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.activity.CashAndCreditCardFicheActivity;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.viewmodel.CashAndCreditCardFicheViewModel;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.fragment.BaseSearchModelListDialogFragment;
import com.proje.mobilesales.features.fragment.BottomAlertDialogFragment;
import com.proje.mobilesales.features.fragment.PinSmsFragment;
import com.proje.mobilesales.features.model.BaseSearchModel;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;


/* compiled from: CashCreditFicheDetailEnterFragment.kt */

public final class CashCreditFicheDetailEnterFragment extends BaseFicheFragment {
    private static final String STATE_CUSTOMER_OPERATION = "state:customerOperation";
    private static final String STATE_CUSTOMER_REF = "state:mycustomerRef";
    private static final String STATE_DETAIL = "state:cashCreditDetail";
    private CashCreditFicheDetail cashCreditFicheDetail;
    private AppCompatCheckBox chkUse3d;
    private CustomerOperation customerOperation;
    private int customerRef;
    private EditText edtPhone;
    private EditText etAmount;
    private EditText etApprovalNo;
    private EditText etCreditCardNo;
    private EditText etVoucherNo;
    private View imgPhone;
    private LinearLayout lnAmount;
    private LinearLayout lnApprovalNo;
    private LinearLayout lnCreditCardNo;
    private LinearLayout lnPayments;
    private View lnPhoneNumber;
    private View lnUse3d;
    private LinearLayout lnVoucherNo;
    private MatterSettings mMatterSettings;
    private boolean mNetsis;
    private ReceiptType mReceiptType;
    public ProgressDialogBuilder<?> masterPassProgressDialogBuilder;
    private MasterPassServices masterPassServices;
    private String masterPassUserId;
    private PhoneMutableWatcher phoneTextWatcher;
    public PurchaseCompleteListener purchaseCompleteListener;
    private MasterPassCard selectedCard;
    private TextView txtPayments;
    public static final Companion Companion = new Companion(null);
    public static final String CASH_CREDIT_DETAIL_ENTER = "CHEQUE_DEED_DETAIL_ENTER." + CashCreditFicheDetailEnterFragment.class.getName();
    private final CashAndCreditCardFicheRepository repository = new CashAndCreditCardFicheRepository();
    private final CashAndCreditCardFicheViewModel viewModel = new CashAndCreditCardFicheViewModel(repository);

    public CashCreditFicheDetail getCashCreditFicheDetail() {
        return cashCreditFicheDetail;
    }

    public MasterPassServices getMasterPassServices() {
        return masterPassServices;
    }

    public MasterPassCard getSelectedCard() {
        return selectedCard;
    }

    @Override // com.proje.mobilesales.core.base.BaseFicheFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(final Bundle bundle) {
        final String sb;
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        if (null != bundle) {
            cashCreditFicheDetail = bundle.getParcelable(CashCreditFicheDetailEnterFragment.STATE_DETAIL);
            customerOperation = bundle.getParcelable(CashCreditFicheDetailEnterFragment.STATE_CUSTOMER_OPERATION);
            customerRef = bundle.getInt(CashCreditFicheDetailEnterFragment.STATE_CUSTOMER_REF);
        } else {
            final Bundle arguments = this.getArguments();
            Intrinsics.checkNotNull(arguments);
            cashCreditFicheDetail = arguments.getParcelable(CashAndCreditCardFicheActivity.Companion.getCASH_CREDIT_DETAIL());
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
        final ErpType erpType = viewModel.getBaseErp().getErpType();
        final ErpType erpType2 = ErpType.NETSIS;
        mNetsis = erpType == erpType2;
        final BaseErp<?> baseErp = viewModel.getBaseErp();
        final Context context = this.getContext();
        final CustomerOperation customerOperation2 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation2);
        mMatterSettings = baseErp.getMatterSettings(context, customerOperation2.getFicheType());
        this.setupMasterpassConfiguration();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(viewModel.getBaseErp().getCustomerClCode(customerRef));
        if (viewModel.getBaseErp().getErpType() == erpType2) {
            sb = "";
        } else {
            String sb3 = "_" +
                    customerRef;
            sb = sb3;
        }
        sb2.append(sb);
        masterPassUserId = sb2.toString();
        final Context requireContext = this.requireContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        masterPassProgressDialogBuilder = new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity);
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint("RestrictedApi")
    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final View inflate = this.getLayoutInflater(bundle).inflate(R.layout.fragment_cashcreditfiche_detailenter, viewGroup, false);
        txtPayments = inflate.findViewById(R.id.txt_payments);
        etCreditCardNo = inflate.findViewById(R.id.et_creditCardNo);
        etAmount = inflate.findViewById(R.id.et_amount);
        etVoucherNo = inflate.findViewById(R.id.et_voucherNo);
        etApprovalNo = inflate.findViewById(R.id.et_approvalNo);
        lnPayments = inflate.findViewById(R.id.ln_payments);
        lnCreditCardNo = inflate.findViewById(R.id.ln_creditCardNo);
        lnAmount = inflate.findViewById(R.id.ln_amount);
        lnVoucherNo = inflate.findViewById(R.id.ln_voucherNo);
        lnPhoneNumber = inflate.findViewById(R.id.ln_PhoneNumber);
        edtPhone = inflate.findViewById(R.id.edt_phone);
        imgPhone = inflate.findViewById(R.id.img_Phone);
        chkUse3d = inflate.findViewById(R.id.chkUse3d);
        lnUse3d = inflate.findViewById(R.id.ln_use3d);
        lnApprovalNo = inflate.findViewById(R.id.ln_approvalNo);
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

    protected void load() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnAmount;
        final EditText editText = etAmount;
        final CashCreditFicheDetail cashCreditFicheDetail = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail);
        this.createEditTextAddTextChangedListener(ficheMode, linearLayout, editText, cashCreditFicheDetail.getTotal(), true);
        final EditText editText2 = etAmount;
        final CashCreditFicheDetail cashCreditFicheDetail2 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail2);
        this.loadTextView(editText2, cashCreditFicheDetail2.getTotal().toString(), true);
        final EditText editText3 = etVoucherNo;
        if (null != editText3) {
            StringUtils.INSTANCE.removeAllSpacesFilter(editText3);
        }
        final CustomerOperation customerOperation2 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation2);
        final FicheMode ficheMode2 = customerOperation2.getFicheMode();
        final LinearLayout linearLayout2 = lnVoucherNo;
        final EditText editText4 = etVoucherNo;
        final CashCreditFicheDetail cashCreditFicheDetail3 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail3);
        this.createEditTextAddTextChangedListener(ficheMode2, linearLayout2, editText4, cashCreditFicheDetail3.getDocNo(), true);
        final EditText editText5 = etVoucherNo;
        final CashCreditFicheDetail cashCreditFicheDetail4 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail4);
        this.loadTextView(editText5, cashCreditFicheDetail4.getDocNo().toString(), true);
        final CustomerOperation customerOperation3 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation3);
        final FicheMode ficheMode3 = customerOperation3.getFicheMode();
        final LinearLayout linearLayout3 = lnCreditCardNo;
        final EditText editText6 = etCreditCardNo;
        final CashCreditFicheDetail cashCreditFicheDetail5 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail5);
        final FicheStringProp creditCardNo = cashCreditFicheDetail5.getCreditCardNo();
        final ReceiptType receiptType = mReceiptType;
        final ReceiptType receiptType2 = ReceiptType.CREDIT;
        this.createEditTextAddTextChangedListener(ficheMode3, linearLayout3, editText6, creditCardNo, receiptType == receiptType2);
        final EditText editText7 = etCreditCardNo;
        final CashCreditFicheDetail cashCreditFicheDetail6 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail6);
        this.loadTextView(editText7, cashCreditFicheDetail6.getCreditCardNo().toString(), mReceiptType == receiptType2);
        this.uploadPaymentInformation();
        int i2 = 8;
        if (viewModel.getBaseErp().useOnlinePayment() && mReceiptType == receiptType2) {
            this.prepareForOnlinePayment();
            this.createPhoneAddTextChangedListener();
            final EditText editText8 = edtPhone;
            final CashCreditFicheDetail cashCreditFicheDetail7 = this.cashCreditFicheDetail;
            Intrinsics.checkNotNull(cashCreditFicheDetail7);
            this.loadTextView(editText8, cashCreditFicheDetail7.getPhoneNumber().toString(), mReceiptType == receiptType2);
            final AppCompatCheckBox appCompatCheckBox = chkUse3d;
            Intrinsics.checkNotNull(appCompatCheckBox);
            appCompatCheckBox.setEnabled(false);
            final AppCompatCheckBox appCompatCheckBox2 = chkUse3d;
            Intrinsics.checkNotNull(appCompatCheckBox2);
            final CashCreditFicheDetail cashCreditFicheDetail8 = this.cashCreditFicheDetail;
            Intrinsics.checkNotNull(cashCreditFicheDetail8);
            appCompatCheckBox2.setChecked(cashCreditFicheDetail8.isUse3d());
            final AppCompatCheckBox appCompatCheckBox3 = chkUse3d;
            Intrinsics.checkNotNull(appCompatCheckBox3);
            appCompatCheckBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentExternalSyntheticLambda5
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(final CompoundButton compoundButton, final boolean z) {
                    loadlambda1(CashCreditFicheDetailEnterFragment.this, compoundButton, z);
                }
            });
            final View view = lnUse3d;
            Intrinsics.checkNotNull(view);
            if (mReceiptType == receiptType2) {
                if (null == this.selectedCard) {
                    final CashCreditFicheDetail cashCreditFicheDetail9 = this.cashCreditFicheDetail;
                    Intrinsics.checkNotNull(cashCreditFicheDetail9);
                }
                i2 = 0;
            }
            view.setVisibility(i2);
        } else {
            final View view2 = lnPhoneNumber;
            Intrinsics.checkNotNull(view2);
            view2.setVisibility(8);
            final View view3 = lnUse3d;
            Intrinsics.checkNotNull(view3);
            view3.setVisibility(8);
        }
        final CustomerOperation customerOperation4 = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation4);
        final FicheMode ficheMode4 = customerOperation4.getFicheMode();
        final LinearLayout linearLayout4 = lnApprovalNo;
        final EditText editText9 = etApprovalNo;
        final CashCreditFicheDetail cashCreditFicheDetail10 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail10);
        final FicheStringProp approvalNo = cashCreditFicheDetail10.getApprovalNo();
        final ErpType erpType = viewModel.getBaseErp().getErpType();
        final ErpType erpType2 = ErpType.TIGER;
        this.createEditTextAddTextChangedListener(ficheMode4, linearLayout4, editText9, approvalNo, erpType == erpType2 && mReceiptType == receiptType2);
        final EditText editText10 = etApprovalNo;
        final CashCreditFicheDetail cashCreditFicheDetail11 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail11);
        this.loadTextView(editText10, cashCreditFicheDetail11.getApprovalNo().toString(), viewModel.getBaseErp().getErpType() == erpType2 && mReceiptType == receiptType2);
    }

    
    public static void loadlambda1(final CashCreditFicheDetailEnterFragment this0, final CompoundButton compoundButton, final boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final CashCreditFicheDetail cashCreditFicheDetail = this0.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail);
        cashCreditFicheDetail.setUse3d(z);
    }

    private void prepareForOnlinePayment() {
        final View view = lnPhoneNumber;
        Intrinsics.checkNotNull(view);
        view.setVisibility(0);
        this.createImageButtonForCreditCardNumber();
        StringUtils.convertIntToString(customerRef);
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        if (customerOperation.getFicheMode() == FicheMode.ANALYSE) {
            return;
        }
        this.closeOpenFragmentsIfAny();
        final View view2 = imgPhone;
        Intrinsics.checkNotNull(view2);
        view2.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public void onClick(final View view3) {
                prepareForOnlinePaymentlambda2(CashCreditFicheDetailEnterFragment.this, view3);
            }
        });
    }

    
    public static void prepareForOnlinePaymentlambda2(final CashCreditFicheDetailEnterFragment this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.createSearchDialogForPhone();
    }

    private void createSearchDialogForPhone() {
        final String convertIntToString = StringUtils.convertIntToString(customerRef);
        final BaseErp<?> baseErp = viewModel.getBaseErp();
        final String string = this.getString(R.string.qry_customer_phoneNumbers);
        final String string2 = this.getString(R.string.column_id);
        final String string3 = this.getString(R.string.column_phone);
        final CashCreditFicheDetail cashCreditFicheDetail = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail);
        final int which = cashCreditFicheDetail.getPhoneNumber().getWhich();
        final CashCreditFicheDetail cashCreditFicheDetail2 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail2);
        ArrayList<BaseSearchModel> baseSearchModelList = baseErp.getBaseSearchModelList(string, string2, string3, which, cashCreditFicheDetail2.getPhoneNumber().getLogicalRef(), convertIntToString, convertIntToString);
        if (null == baseSearchModelList || baseSearchModelList.isEmpty()) {
            Toast.makeText(this.getActivity(), this.getString(R.string.str_data_not_found), 0).show();
            return;
        }
        final BaseSearchModelListDialogFragment<?> newInstance = BaseSearchModelListDialogFragment.Companion.newInstance(StringUtils.catStringSpace(this.getString(R.string.str_phone_number), this.getString(R.string.str_select_text)), baseSearchModelList, false);
        newInstance.setSearchResultListener(new BottomSheetDialogSearchResultListener<BaseSearchModel>() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentcreateSearchDialogForPhone1
            @Override // com.proje.mobilesales.core.searchdialog.BottomSheetDialogSearchResultListener
            public void onSelected(final BottomSheetDialog dialog, final BaseSearchModel item, final int i2) {
                final CashCreditFicheDetailEnterFragment.PhoneMutableWatcher phoneMutableWatcher;
                final EditText editText;
                final CashCreditFicheDetailEnterFragment.PhoneMutableWatcher phoneMutableWatcher2;
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                Intrinsics.checkNotNullParameter(item, "item");
                final int logicalRef = item.getLogicalRef();
                final CashCreditFicheDetail cashCreditFicheDetail3 = getCashCreditFicheDetail();
                Intrinsics.checkNotNull(cashCreditFicheDetail3);
                if (logicalRef != cashCreditFicheDetail3.getPhoneNumber().getLogicalRef() && null != CashCreditFicheDetailEnterFragment.this.getSelectedCard()) {
                    clearMasterPassCardSelection();
                }
                final CashCreditFicheDetail cashCreditFicheDetail4 = getCashCreditFicheDetail();
                Intrinsics.checkNotNull(cashCreditFicheDetail4);
                cashCreditFicheDetail4.getPhoneNumber().setWhich(findSearchModelPosition(baseSearchModelList, item.getLogicalRef()));
                final CashCreditFicheDetail cashCreditFicheDetail5 = getCashCreditFicheDetail();
                Intrinsics.checkNotNull(cashCreditFicheDetail5);
                cashCreditFicheDetail5.getPhoneNumber().setLogicalRef(item.getLogicalRef());
                final CashCreditFicheDetail cashCreditFicheDetail6 = getCashCreditFicheDetail();
                Intrinsics.checkNotNull(cashCreditFicheDetail6);
                FicheStringProp.setDefinition(item.getTitle());
                phoneMutableWatcher = phoneTextWatcher;
                Intrinsics.checkNotNull(phoneMutableWatcher);
                phoneMutableWatcher.setActive(false);
                editText = edtPhone;
                Intrinsics.checkNotNull(editText);
                editText.setText(item.getTitle());
                phoneMutableWatcher2 = phoneTextWatcher;
                Intrinsics.checkNotNull(phoneMutableWatcher2);
                phoneMutableWatcher2.setActive(true);
                dialog.dismiss();
            }

            @Override // com.proje.mobilesales.core.searchdialog.BottomSheetDialogSearchResultListener
            public void onCancelled(final BottomSheetDialog dialog) {
                final EditText editText;
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                final CashCreditFicheDetail cashCreditFicheDetail3 = getCashCreditFicheDetail();
                Intrinsics.checkNotNull(cashCreditFicheDetail3);
                cashCreditFicheDetail3.getPhoneNumber().reset();
                editText = edtPhone;
                Intrinsics.checkNotNull(editText);
                editText.setText("");
                clearMasterPassCardSelection();
                dialog.dismiss();
            }
        });
        newInstance.setCancelable(false);
        final FragmentActivity activity = this.getActivity();
        Intrinsics.checkNotNull(activity);
        newInstance.show(activity.getSupportFragmentManager(), "BaseSearchFragment");
    }

    private void createPhoneAddTextChangedListener() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        if (customerOperation.getFicheMode() == FicheMode.ANALYSE) {
            this.setViewDisabled(edtPhone);
            return;
        }
        final EditText editText = edtPhone;
        Intrinsics.checkNotNull(editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentExternalSyntheticLambda0
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(final TextView textView, final int i2, final KeyEvent keyEvent) {
                final boolean createPhoneAddTextChangedListenerlambda4;
                createPhoneAddTextChangedListenerlambda4 = createPhoneAddTextChangedListenerlambda4(CashCreditFicheDetailEnterFragment.this, textView, i2, keyEvent);
                return createPhoneAddTextChangedListenerlambda4;
            }
        });
        phoneTextWatcher = new PhoneMutableWatcher();
        final EditText editText2 = edtPhone;
        Intrinsics.checkNotNull(editText2);
        editText2.addTextChangedListener(phoneTextWatcher);
    }

    
    public static boolean createPhoneAddTextChangedListenerlambda4(final CashCreditFicheDetailEnterFragment this0, final TextView textView, final int i2, final KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (5 != i2 && 6 != i2) {
            return false;
        }
        final CashCreditFicheDetail cashCreditFicheDetail = this0.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail);
        final FicheRefProp phoneNumber = cashCreditFicheDetail.getPhoneNumber();
        final EditText editText = this0.edtPhone;
        Intrinsics.checkNotNull(editText);
        FicheStringProp.setDefinition(editText.getText().toString());
        return false;
    }

    private void closeOpenFragmentsIfAny() {
        final FragmentActivity activity = this.getActivity();
        Intrinsics.checkNotNull(activity);
        if (1 < activity.getSupportFragmentManager().getFragments().size()) {
            final ArrayList<String> arrayList = new ArrayList();
            final FragmentActivity activity2 = this.getActivity();
            Intrinsics.checkNotNull(activity2);
            for (final Fragment fragment : activity2.getSupportFragmentManager().getFragments()) {
                if (!Intrinsics.areEqual(fragment.getClass(), CashCreditFicheDetailEnterFragment.class)) {
                    arrayList.add(fragment.getTag());
                }
            }
            final FragmentActivity activity3 = this.getActivity();
            Intrinsics.checkNotNull(activity3);
            final FragmentTransaction beginTransaction = activity3.getSupportFragmentManager().beginTransaction();
            Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction(...)");
            for (final String str : arrayList) {
                final FragmentActivity activity4 = this.getActivity();
                Intrinsics.checkNotNull(activity4);
                final Fragment findFragmentByTag = activity4.getSupportFragmentManager().findFragmentByTag(str);
                if (null != findFragmentByTag) {
                    beginTransaction.remove(findFragmentByTag);
                }
            }
            beginTransaction.commit();
            beginTransaction.addToBackStack(null);
        }
    }

    private void createImageButtonForCreditCardNumber() {
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, 0.45f);
        final EditText editText = etCreditCardNo;
        Intrinsics.checkNotNull(editText);
        editText.setLayoutParams(layoutParams);
        final ImageButton imageButton = new ImageButton(this.getContext());
        imageButton.setImageResource(R.drawable.ic_magnify_black_18dp);
        final LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1, 0.1f);
        imageButton.setPadding(5, 0, 5, 0);
        imageButton.setBackgroundColor(0);
        imageButton.setLayoutParams(layoutParams2);
        final LinearLayout linearLayout = lnCreditCardNo;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.addView(imageButton);
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        if (customerOperation.getFicheMode() == FicheMode.ANALYSE) {
            return;
        }
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                createImageButtonForCreditCardNumberlambda5(CashCreditFicheDetailEnterFragment.this, view);
            }
        });
    }

    
    public static void createImageButtonForCreditCardNumberlambda5(final CashCreditFicheDetailEnterFragment this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final CashCreditFicheDetail cashCreditFicheDetail = this0.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail);
        if (TextUtils.isEmpty(cashCreditFicheDetail.getPhoneNumber().getDefinition())) {
            Toast.makeText(this0.getContext(), R.string.str_phone_must_be_selected_for_online_payment, 0).show();
            return;
        }
        if (ContextUtils.checkInternetConnection()) {
            final MasterPassServices masterPassServices = this0.masterPassServices;
            if (null == masterPassServices) {
                final FragmentActivity activity = this0.getActivity();
                final CashCreditFicheDetail cashCreditFicheDetail2 = this0.cashCreditFicheDetail;
                Intrinsics.checkNotNull(cashCreditFicheDetail2);
                this0.masterPassServices = new MasterPassServices(activity, cashCreditFicheDetail2.getPhoneNumber().getDefinition());
            } else {
                Intrinsics.checkNotNull(masterPassServices);
                final CashCreditFicheDetail cashCreditFicheDetail3 = this0.cashCreditFicheDetail;
                Intrinsics.checkNotNull(cashCreditFicheDetail3);
                masterPassServices.setMsisdn(cashCreditFicheDetail3.getPhoneNumber().getDefinition());
            }
            final ProgressDialogBuilder<?> progressDialogBuilder = this0.masterPassProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setCancelable(false).setMessage(this0.getString(R.string.str_please_wait)).show();
            final String generateReqRefNo = TokenGenerator.generateReqRefNo();
            final MasterPassServices masterPassServices2 = this0.masterPassServices;
            Intrinsics.checkNotNull(masterPassServices2);
            final CashCreditFicheDetail cashCreditFicheDetail4 = this0.cashCreditFicheDetail;
            Intrinsics.checkNotNull(cashCreditFicheDetail4);
            masterPassServices2.checkMasterPass(TokenGenerator.generateTokenWithoutUserId(cashCreditFicheDetail4.getPhoneNumber().getDefinition(), generateReqRefNo), generateReqRefNo, new C2648x4a82d6e2(this0));
        }
    }

    private void setupMasterpassConfiguration() {
        MasterPassInfo.setUrl("https://test.masterpassturkiye.com/MasterpassJsonServerHandler/v2/");
        MasterPassInfo.setClientID(BuildConfig.CLIENTID);
        MasterPassInfo.setLanguage("tur");
        MasterPassInfo.setMacroMerchantId("06824207921211043736577");
        MasterPassInfo.setCardIOInfoLanguage("tr");
    }

    private void uploadPaymentInformation() {
        final CustomerOperation customerOperation = this.customerOperation;
        Intrinsics.checkNotNull(customerOperation);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final LinearLayout linearLayout = lnPayments;
        final TextView textView = txtPayments;
        final CashCreditFicheDetail cashCreditFicheDetail = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail);
        final FicheDiscountRefProp payment = cashCreditFicheDetail.getPayment();
        final ReceiptType receiptType = mReceiptType;
        final ReceiptType receiptType2 = ReceiptType.CREDIT;
        final boolean z = receiptType == receiptType2;
        final CashCreditFicheDetail cashCreditFicheDetail2 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail2);
        this.createSingleAlertCursorSales(ficheMode, linearLayout, textView, payment, z, R.string.str_special_code, R.string.str_payment_plan_not_found, R.string.qry_pay_plan, R.string.column_name, cashCreditFicheDetail2.getPayment());
        final TextView textView2 = txtPayments;
        final CashCreditFicheDetail cashCreditFicheDetail3 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail3);
        this.loadTextView(textView2, cashCreditFicheDetail3.getPayment().toString(), mReceiptType == receiptType2);
    }

    
    public void showMasterPassAlertDialog(String str, String str2) {
        final FragmentActivity activity = this.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.runOnUiThread(new Runnable() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public void run() {
                showMasterPassAlertDialoglambda7(str2, str, this);
            }
        });
    }

    
    public static void showMasterPassAlertDialoglambda7(final String message, final String title, final CashCreditFicheDetailEnterFragment this0) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(this0, "this0");
        BottomAlertDialogFragment bottomAlertDialogFragment = new BottomAlertDialogFragment();
        bottomAlertDialogFragment.setMessage(message);
        bottomAlertDialogFragment.setTitle(title);
        bottomAlertDialogFragment.setCancelable(true);
        bottomAlertDialogFragment.setShowNegativeButton(false);
        bottomAlertDialogFragment.setPositiveButton("", new View.OnClickListener() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                showMasterPassAlertDialoglambda7lambda6(BottomAlertDialogFragment.this, view);
            }
        });
        final FragmentActivity activity = this0.getActivity();
        Intrinsics.checkNotNull(activity);
        bottomAlertDialogFragment.show(activity.getSupportFragmentManager(), "BottomAlertDialogFragment");
    }

    
    public static void showMasterPassAlertDialoglambda7lambda6(final BottomAlertDialogFragment errorDialog, final View view) {
        Intrinsics.checkNotNullParameter(errorDialog, "errorDialog");
        errorDialog.dismiss();
    }

    
    public void linkCardToClient() {
        final ProgressDialogBuilder<?> progressDialogBuilder = masterPassProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setCancelable(false).setMessage(this.getString(R.string.str_please_wait)).show();
        final String generateReqRefNo = TokenGenerator.generateReqRefNo();
        final MasterPassServices masterPassServices = this.masterPassServices;
        Intrinsics.checkNotNull(masterPassServices);
        final String str = masterPassUserId;
        final CashCreditFicheDetail cashCreditFicheDetail = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail);
        masterPassServices.linkCardToClient(TokenGenerator.generateTokenWithUserId(str, cashCreditFicheDetail.getPhoneNumber().toString(), generateReqRefNo), generateReqRefNo, new LinkCardToClientListener() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentlinkCardToClient1
            @Override // cardtek.masterpass.interfaces.LinkCardToClientListener
            public void onVerifyUser(final ServiceResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                final ProgressDialogBuilder<?> progressDialogBuilder2 = masterPassProgressDialogBuilder;
                Intrinsics.checkNotNull(progressDialogBuilder2);
                progressDialogBuilder2.dismiss();
                verifyUser(result, MasterPassPinOption.NONE);
            }

            @Override // cardtek.masterpass.interfaces.LinkCardToClientListener
            public void onServiceError(final ServiceError result) {
                Intrinsics.checkNotNullParameter(result, "result");
                final ProgressDialogBuilder<?> progressDialogBuilder2 = masterPassProgressDialogBuilder;
                Intrinsics.checkNotNull(progressDialogBuilder2);
                progressDialogBuilder2.dismiss();
                final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = CashCreditFicheDetailEnterFragment.this;
                final String responseCode = result.getResponseCode();
                Intrinsics.checkNotNullExpressionValue(responseCode, "getResponseCode(...)");
                final String responseDesc = result.getResponseDesc();
                Intrinsics.checkNotNullExpressionValue(responseDesc, "getResponseDesc(...)");
                cashCreditFicheDetailEnterFragment.showMasterPassAlertDialog(responseCode, responseDesc);
            }

            @Override // cardtek.masterpass.interfaces.LinkCardToClientListener
            public void onInternalError(final InternalError result) {
                Intrinsics.checkNotNullParameter(result, "result");
                final ProgressDialogBuilder<?> progressDialogBuilder2 = masterPassProgressDialogBuilder;
                Intrinsics.checkNotNull(progressDialogBuilder2);
                progressDialogBuilder2.dismiss();
                final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = CashCreditFicheDetailEnterFragment.this;
                final String errorCode = result.getErrorCode();
                Intrinsics.checkNotNullExpressionValue(errorCode, "getErrorCode(...)");
                final String errorDesc = result.getErrorDesc();
                Intrinsics.checkNotNullExpressionValue(errorDesc, "getErrorDesc(...)");
                cashCreditFicheDetailEnterFragment.showMasterPassAlertDialog(errorCode, errorDesc);
            }
        });
    }

    public void verifyUser(ServiceResult result, MasterPassPinOption pinOption) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(pinOption, "pinOption");
        final FragmentActivity activity = this.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.runOnUiThread(new Runnable() {
            public void run() {
                String str;
                final String responseCode = ServiceResult.this.getResponseCode();
                if (null != responseCode) {
                    switch (responseCode.hashCode()) {
                        case 1626588:
                            if ("5001".equals(responseCode)) {
                                str = Constants.PIN_ENTER_SMS;
                                break;
                            }
                            break;
                        case 1626589:
                            if ("5002".equals(responseCode)) {
                                str = "pin_enter";
                                break;
                            }
                            break;
                        case 1626594:
                            break;
                        case 1626595:
                            break;
                        case 1626618:
                            if ("5010".equals(responseCode)) {
                                str = "pin_enter_3d";
                                break;
                            }
                            break;
                        case 1626623:
                            if ("5015".equals(responseCode)) {
                                str = "pin_set";
                                break;
                            }
                            break;
                    }
                    if (TextUtils.isEmpty(str)) {
                        switch (str.hashCode()) {
                            case -867831737:
                                if (!str.equals(Constants.PIN_ENTER_SMS_MPASS)) {
                                    return;
                                }
                                break;
                            case -602351698:
                                "pin_enter".equals(str);
                                return;
                            case -568210760:
                                "pin_set".equals(str);
                                return;
                            case -285979454:
                                "pin_enter_3d".equals(str);
                                return;
                            case -275366584:
                                if (!str.equals(Constants.PIN_ENTER_SMS)) {
                                    return;
                                }
                                break;
                            default:
                                return;
                        }
                        if (pinOption == MasterPassPinOption.VALIDATESMSPURCHASE) {
                            str = Constants.PIN_ENTER_SMS_FOR_PURCHASE;
                        }
                        final PinSmsFragment pinSmsFragment = new PinSmsFragment(getMasterPassServices());
                        pinSmsFragment.setCancelable(false);
                        pinSmsFragment.setTargetFragment(this, 0);
                        final FragmentActivity activity2 = getActivity();
                        Intrinsics.checkNotNull(activity2);
                        pinSmsFragment.show(activity2.getSupportFragmentManager(), str);
                        return;
                    }
                    final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = this;
                    final String responseCode2 = ServiceResult.this.getResponseCode();
                    Intrinsics.checkNotNullExpressionValue(responseCode2, "getResponseCode(...)");
                    final String responseDesc = ServiceResult.this.getResponseDesc();
                    Intrinsics.checkNotNullExpressionValue(responseDesc, "getResponseDesc(...)");
                    cashCreditFicheDetailEnterFragment.showMasterPassAlertDialog(responseCode2, responseDesc);
                    return;
                }
                str = "";
                if (TextUtils.isEmpty(str)) {
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        if (0 == i2 && -1 == i3) {
            Intrinsics.checkNotNull(intent);
            final ServiceResult serviceResult = (ServiceResult) intent.getSerializableExtra("ServiceResult");
            final MasterPassResult masterPassResult = (MasterPassResult) intent.getSerializableExtra("MasterPassResult");
            final MasterPassPinOption masterPassPinOption = (MasterPassPinOption) intent.getSerializableExtra("MasterPassPinOption");
            if (null == masterPassResult) {
                if (null != serviceResult) {
                    this.verifyUser(serviceResult, MasterPassPinOption.NONE);
                    return;
                }
                return;
            }
            if (masterPassResult.is3DPay()) {
                final String string = this.getString(R.string.str_masterpass);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                final String string2 = this.getString(R.string.str_process_completed_successfully);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                this.showMasterPassAlertDialog(string, string2);
                return;
            }
            if (null != masterPassPinOption && masterPassPinOption == MasterPassPinOption.VALIDATESMSPURCHASE) {
                final PurchaseCompleteListener purchaseCompleteListener = this.purchaseCompleteListener;
                Intrinsics.checkNotNull(purchaseCompleteListener);
                purchaseCompleteListener.onComplete(masterPassResult.getValidateTransactionResult().getRefNo());
                return;
            } else {
                final String string3 = this.getString(R.string.str_masterpass);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                final String string4 = this.getString(R.string.str_process_completed_successfully);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                this.showMasterPassAlertDialog(string3, string4);
                return;
            }
        }
        final CashCreditFicheDetail cashCreditFicheDetail = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail);
        cashCreditFicheDetail.setDesc("");
    }

    
    public Unit getCards() {
        final ProgressDialogBuilder<?> progressDialogBuilder = masterPassProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setCancelable(false).setMessage(this.getString(R.string.str_please_wait)).show();
        final String generateReqRefNo = TokenGenerator.generateReqRefNo();
        final MasterPassServices masterPassServices = this.masterPassServices;
        Intrinsics.checkNotNull(masterPassServices);
        final String str = masterPassUserId;
        final CashCreditFicheDetail cashCreditFicheDetail = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail);
        masterPassServices.getCards(TokenGenerator.generateTokenWithUserId(str, cashCreditFicheDetail.getPhoneNumber().getDefinition(), generateReqRefNo), generateReqRefNo, new CashCreditFicheDetailEnterFragmentcards1(this));
        return Unit.INSTANCE;
    }

    
    public void clearMasterPassCardSelection() {
        if (null != this.selectedCard) {
            selectedCard = null;
            final CashCreditFicheDetail cashCreditFicheDetail = this.cashCreditFicheDetail;
            Intrinsics.checkNotNull(cashCreditFicheDetail);
            cashCreditFicheDetail.setCreditCardNo(new FicheStringProp(""));
            final EditText editText = etCreditCardNo;
            Intrinsics.checkNotNull(editText);
            editText.setText("");
            final EditText editText2 = etCreditCardNo;
            Intrinsics.checkNotNull(editText2);
            editText2.setEnabled(true);
            final EditText editText3 = edtPhone;
            Intrinsics.checkNotNull(editText3);
            editText3.setEnabled(true);
            final AppCompatCheckBox appCompatCheckBox = chkUse3d;
            Intrinsics.checkNotNull(appCompatCheckBox);
            appCompatCheckBox.setEnabled(false);
            final AppCompatCheckBox appCompatCheckBox2 = chkUse3d;
            Intrinsics.checkNotNull(appCompatCheckBox2);
            appCompatCheckBox2.setChecked(false);
            final View view = lnUse3d;
            Intrinsics.checkNotNull(view);
            view.setVisibility(8);
        }
    }

    
    public ArrayList<MasterPassCardItem> getCardItems(final ArrayList<MasterPassCard> arrayList) {
        boolean areEqual;
        final ArrayList<MasterPassCardItem> arrayList2 = new ArrayList<>();
        final Iterator<MasterPassCard> it = arrayList.iterator();
        while (it.hasNext()) {
            final MasterPassCard next = it.next();
            final MasterPassCardItem masterPassCardItem = new MasterPassCardItem();
            masterPassCardItem.setBankIca(next.getBankIca());
            masterPassCardItem.setCardStatus(next.getCardStatus());
            masterPassCardItem.setEftCode(next.getEftCode());
            masterPassCardItem.setLoyaltyCode(next.getLoyaltyCode());
            masterPassCardItem.setIsMasterPassMember(next.getIsMasterPassMember());
            masterPassCardItem.setMaskedPan(next.getMaskedPan());
            masterPassCardItem.setName(next.getName());
            masterPassCardItem.setProductName(next.getProductName());
            masterPassCardItem.setPromtCpin(next.getPromtCpin());
            masterPassCardItem.setUniqueId(next.getUniqueId());
            masterPassCardItem.setValue2(next.getValue2());
            if (null == this.selectedCard) {
                areEqual = false;
            } else {
                final String uniqueId = next.getUniqueId();
                final MasterPassCard masterPassCard = selectedCard;
                Intrinsics.checkNotNull(masterPassCard);
                areEqual = Intrinsics.areEqual(uniqueId, masterPassCard.getUniqueId());
            }
            masterPassCardItem.setSelected(areEqual);
            arrayList2.add(masterPassCardItem);
        }
        return arrayList2;
    }

    @Override // com.proje.mobilesales.core.base.BaseFicheFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putParcelable(CashCreditFicheDetailEnterFragment.STATE_DETAIL, cashCreditFicheDetail);
        outState.putParcelable(CashCreditFicheDetailEnterFragment.STATE_CUSTOMER_OPERATION, customerOperation);
        outState.putInt(CashCreditFicheDetailEnterFragment.STATE_CUSTOMER_REF, customerRef);
        super.onSaveInstanceState(outState);
    }

    @Override // com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    /* compiled from: CashCreditFicheDetailEnterFragment.kt */
    public final class PhoneMutableWatcher implements TextWatcher {
        private boolean isActive;

        @Override // android.text.TextWatcher
        public void beforeTextChanged(final CharSequence s, final int i2, final int i3, final int i4) {
            Intrinsics.checkNotNullParameter(s, "s");
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(final CharSequence s, final int i2, final int i3, final int i4) {
            Intrinsics.checkNotNullParameter(s, "s");
        }

        public PhoneMutableWatcher() {
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(final boolean z) {
            isActive = z;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(final Editable s) {
            Intrinsics.checkNotNullParameter(s, "s");
            if (isActive) {
                final CashCreditFicheDetail cashCreditFicheDetail = getCashCreditFicheDetail();
                Intrinsics.checkNotNull(cashCreditFicheDetail);
                cashCreditFicheDetail.getPhoneNumber().reset();
            }
            final CashCreditFicheDetail cashCreditFicheDetail2 = getCashCreditFicheDetail();
            Intrinsics.checkNotNull(cashCreditFicheDetail2);
            FicheStringProp.setDefinition(s.toString());
        }
    }

    public void purchase() {
        final ProgressDialogBuilder<?> progressDialogBuilder = masterPassProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setCancelable(false).setMessage(this.getString(R.string.str_please_wait)).show();
        final CashCreditFicheDetail cashCreditFicheDetail = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail);
        final String replacedefault = StringsKt.replacedefault(StringsKt.replacedefault(StringUtils.formatDouble(cashCreditFicheDetail.getTotal().getDefinitionDouble()), ",", "", false, 4, (Object) null), ".", "", false, 4, (Object) null);
        final String generateReqRefNo = TokenGenerator.generateReqRefNo();
        final String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        final CashCreditFicheDetail cashCreditFicheDetail2 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail2);
        cashCreditFicheDetail2.setDesc(uuid);
        final MasterPassServices masterPassServices = this.masterPassServices;
        Intrinsics.checkNotNull(masterPassServices);
        final String str = masterPassUserId;
        final CashCreditFicheDetail cashCreditFicheDetail3 = this.cashCreditFicheDetail;
        Intrinsics.checkNotNull(cashCreditFicheDetail3);
        final String generateTokenForPurchase = TokenGenerator.generateTokenForPurchase(str, cashCreditFicheDetail3.getPhoneNumber().getDefinition(), generateReqRefNo);
        final MasterPassCard masterPassCard = selectedCard;
        Intrinsics.checkNotNull(masterPassCard);
        masterPassServices.purchase(generateTokenForPurchase, masterPassCard.getName(), Integer.parseInt(replacedefault), uuid, generateReqRefNo, new PurchaseListener() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailEnterFragmentpurchase1
            @Override // cardtek.masterpass.interfaces.PurchaseListener
            public void onSuccess(final PurchaseResult purchaseResult) {
                Intrinsics.checkNotNullParameter(purchaseResult, "purchaseResult");
                final ProgressDialogBuilder<?> progressDialogBuilder2 = masterPassProgressDialogBuilder;
                Intrinsics.checkNotNull(progressDialogBuilder2);
                progressDialogBuilder2.dismiss();
                final PurchaseCompleteListener purchaseCompleteListener = CashCreditFicheDetailEnterFragment.this.purchaseCompleteListener;
                Intrinsics.checkNotNull(purchaseCompleteListener);
                purchaseCompleteListener.onComplete(purchaseResult.getRefNo());
            }

            @Override // cardtek.masterpass.interfaces.PurchaseListener
            public void onVerifyUser(final ServiceResult serviceResult) {
                Intrinsics.checkNotNullParameter(serviceResult, "serviceResult");
                final ProgressDialogBuilder<?> progressDialogBuilder2 = masterPassProgressDialogBuilder;
                Intrinsics.checkNotNull(progressDialogBuilder2);
                progressDialogBuilder2.dismiss();
                verifyUser(serviceResult, MasterPassPinOption.VALIDATESMSPURCHASE);
            }

            @Override // cardtek.masterpass.interfaces.PurchaseListener
            public void onServiceError(final ServiceError serviceError) {
                Intrinsics.checkNotNullParameter(serviceError, "serviceError");
                final ProgressDialogBuilder<?> progressDialogBuilder2 = masterPassProgressDialogBuilder;
                Intrinsics.checkNotNull(progressDialogBuilder2);
                progressDialogBuilder2.dismiss();
                final CashCreditFicheDetail cashCreditFicheDetail4 = getCashCreditFicheDetail();
                Intrinsics.checkNotNull(cashCreditFicheDetail4);
                cashCreditFicheDetail4.setDesc("");
                final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = CashCreditFicheDetailEnterFragment.this;
                final String responseCode = serviceError.getResponseCode();
                Intrinsics.checkNotNullExpressionValue(responseCode, "getResponseCode(...)");
                final String responseDesc = serviceError.getResponseDesc();
                Intrinsics.checkNotNullExpressionValue(responseDesc, "getResponseDesc(...)");
                cashCreditFicheDetailEnterFragment.showMasterPassAlertDialog(responseCode, responseDesc);
            }

            @Override // cardtek.masterpass.interfaces.PurchaseListener
            public void onInternalError(final InternalError internalError) {
                Intrinsics.checkNotNullParameter(internalError, "internalError");
                final ProgressDialogBuilder<?> progressDialogBuilder2 = masterPassProgressDialogBuilder;
                Intrinsics.checkNotNull(progressDialogBuilder2);
                progressDialogBuilder2.dismiss();
                final CashCreditFicheDetail cashCreditFicheDetail4 = getCashCreditFicheDetail();
                Intrinsics.checkNotNull(cashCreditFicheDetail4);
                cashCreditFicheDetail4.setDesc("");
                final CashCreditFicheDetailEnterFragment cashCreditFicheDetailEnterFragment = CashCreditFicheDetailEnterFragment.this;
                final String errorCode = internalError.getErrorCode();
                Intrinsics.checkNotNullExpressionValue(errorCode, "getErrorCode(...)");
                final String errorDesc = internalError.getErrorDesc();
                Intrinsics.checkNotNullExpressionValue(errorDesc, "getErrorDesc(...)");
                cashCreditFicheDetailEnterFragment.showMasterPassAlertDialog(errorCode, errorDesc);
            }
        });
    }

    /* compiled from: CashCreditFicheDetailEnterFragment.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
