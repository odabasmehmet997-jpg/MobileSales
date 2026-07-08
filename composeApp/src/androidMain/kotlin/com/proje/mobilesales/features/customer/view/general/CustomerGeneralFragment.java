package com.proje.mobilesales.features.customer.view.general;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.google.android.material.snackbar.Snackbar;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseFragment;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.CustomerRiskInfoUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.view.SnappyLinearLayoutManager;
import com.proje.mobilesales.databinding.FragmentCustomerGeneralBinding;
import com.proje.mobilesales.features.customer.model.CustomerDetail;
import com.proje.mobilesales.features.customer.model.CustomerProperty;
import com.proje.mobilesales.features.customer.model.database.ClRisk;
import com.proje.mobilesales.features.customer.repository.BaseCustomerRepository;
import com.proje.mobilesales.features.customer.utils.BalanceCalculateUtil;
import java.util.ArrayList;
import java.util.List;

import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

public final class CustomerGeneralFragment extends BaseFragment {
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_CUSTOMER_DETAIL = "extra:customerDetail";
    public static final String STATE_ADAPTER = "state:adapter";
    public static final String STATE_CUSTOMER_DETAIL = "state:customerDetail";
    public static final String STATE_CUSTOMER_GENERAL = "state:customerProperties";
    public static final String STATE_EDIT_MODE = "state:editMode";
    private FragmentCustomerGeneralBinding _binding;
    private final CustomerGeneralRecyclerViewAdapter mAdapter;
    private CustomerDetail mCustomerDetail;
    private ArrayList<CustomerProperty> mCustomerProperties;
    private double mCustomerRiskTotal;
    private boolean mEditMode;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private RecyclerView mRecyclerView;
    private final BaseCustomerRepository repository;
    private final CustomerGeneralViewModel viewModel;

    public CustomerGeneralFragment() {
        BaseCustomerRepository baseCustomerRepository = new BaseCustomerRepository();
        this.repository = baseCustomerRepository;
        this.viewModel = new CustomerGeneralViewModel(baseCustomerRepository);
        this.mAdapter = new CustomerGeneralRecyclerViewAdapter();
    }
    private FragmentCustomerGeneralBinding getBinding() {
        FragmentCustomerGeneralBinding fragmentCustomerGeneralBinding = this._binding;
        Intrinsics.checkNotNull(fragmentCustomerGeneralBinding);
        return fragmentCustomerGeneralBinding;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        if (bundle != null) {
            this.mCustomerDetail = bundle.getParcelable("state:customerDetail");
            this.mEditMode = bundle.getBoolean(STATE_EDIT_MODE);
            this.mCustomerProperties = bundle.getParcelableArrayList("state:customerProperties");
            this.mAdapter.restoreState(bundle.getBundle(STATE_ADAPTER));
            return;
        }
        Bundle arguments = getArguments();
        Intrinsics.checkNotNull(arguments);
        this.mCustomerDetail = arguments.getParcelable("extra:customerDetail");
        this.mEditMode = false;
        this.mCustomerProperties = new ArrayList<>();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentCustomerGeneralBinding.inflate(inflater, viewGroup, false);
        RecyclerView rcw = getBinding().rcw;
        Intrinsics.checkNotNullExpressionValue(rcw, "rcw");
        this.mRecyclerView = rcw;
        RecyclerView recyclerView = null;
        if (rcw == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            rcw = null;
        }
        rcw.setLayoutManager(new SnappyLinearLayoutManager(getActivity()));
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView2 = null;
        }
        recyclerView2.setHasFixedSize(true);
        RecyclerView recyclerView3 = this.mRecyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
        } else {
            recyclerView = recyclerView3;
        }
        recyclerView.setAdapter(this.mAdapter);
        Context requireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity);
        RelativeLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getRiskInfoBeforeLoadData();
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putParcelable("state:customerDetail", this.mCustomerDetail);
        outState.putBoolean(STATE_EDIT_MODE, this.mEditMode);
        outState.putParcelableArrayList("state:customerProperties", this.mCustomerProperties);
        outState.putBundle(STATE_ADAPTER, this.mAdapter.saveState());
        super.onSaveInstanceState(outState);
    }
    protected void createOptionsMenu(Menu menu, MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.createOptionsMenu(menu, inflater);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == R.id.menu_edit) {
            toggleEditMode();
        }
        if (item.getItemId() == R.id.menu_update) {
            ArrayList<CustomerProperty> arrayList = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList);
            arrayList.clear();
            getRiskInfoBeforeLoadData();
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleEditMode() {
        boolean z = !this.mEditMode;
        this.mEditMode = z;
        this.mAdapter.setMEditMode(z);
        if (this.mEditMode) {
            CustomerActivity customerActivity = (CustomerActivity) getActivity();
            Intrinsics.checkNotNull(customerActivity);
            Snackbar.make(customerActivity.getMCoordinatorLayout(), "Edit Mode Open", 0).show();
        } else {
            CustomerActivity customerActivity2 = (CustomerActivity) getActivity();
            Intrinsics.checkNotNull(customerActivity2);
            Snackbar.make(customerActivity2.getMCoordinatorLayout(), "Edit Mode Close", 0).show();
        }
    }
    public void onResume() {
        super.onResume();
        ArrayList<CustomerProperty> arrayList = this.mCustomerProperties;
        Intrinsics.checkNotNull(arrayList);
        arrayList.clear();
        getRiskInfoBeforeLoadData();
    }
    public void onDetach() {
        super.onDetach();
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView = null;
        }
        recyclerView.setAdapter(null);
    }

    private void getRiskInfoBeforeLoadData() {
        if (this.viewModel.getCheckCustomerRiskControl()) {
            CustomerGeneralViewModel customerGeneralViewModel = this.viewModel;
            CustomerDetail customerDetail = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail);
            customerGeneralViewModel.getCustomerRiskCalculate(customerDetail.getLogicalRef(), new CustomerRiskInfoUtils.CommonRiskResponseListener(this, new Function2<CustomerGeneralFragment, List<? extends ClRisk>, Unit>() { // from class: com.proje.mobilesales.features.customer.view.general.CustomerGeneralFragmentgetRiskInfoBeforeLoadData1

                public Unit invoke(CoroutineScope customerGeneralFragment, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> list) {
                    invoke2((CustomerGeneralFragment) customerGeneralFragment, (List<ClRisk>) list);
                    return Unit.INSTANCE;
                }
                public void invoke2(CustomerGeneralFragment activity, List<ClRisk> list) {
                    Intrinsics.checkNotNullParameter(activity, "activity");
                    if (activity.isAttached()) {
                        activity.onRiskCalculateResponse(list, "");
                    }
                }
            }, new Function2<CustomerGeneralFragment, String, Unit>() {
                public   Unit invoke(CoroutineScope customerGeneralFragment, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> str) {
                    invoke2(customerGeneralFragment, str.toString());
                    return Unit.INSTANCE;
                }
                public void invoke2(CustomerGeneralFragment activity, String errorMessage) {
                    Intrinsics.checkNotNullParameter(activity, "activity");
                    Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                    if (activity.isAttached()) {
                        activity.onRiskCalculateResponse(null, errorMessage);
                    }
                }
            }));
            return;
        }
        this.mCustomerRiskTotal = getCustomerRiskTotal();
        loadData();
    }

    private void loadData() {
        ArrayList<CustomerProperty> arrayList = this.mCustomerProperties;
        Intrinsics.checkNotNull(arrayList);
        if (arrayList.size() == 0) {
            ArrayList<CustomerProperty> arrayList2 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList2);
            String string = getString(R.string.str_customer_specode_1);
            CustomerDetail customerDetail = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail);
            arrayList2.add(new CustomerProperty(string, customerDetail.getSpeCode1(), true));
            ArrayList<CustomerProperty> arrayList3 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList3);
            String string2 = getString(R.string.str_customer_specode_2);
            CustomerDetail customerDetail2 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail2);
            arrayList3.add(new CustomerProperty(string2, customerDetail2.getSpeCode2(), true));
            ArrayList<CustomerProperty> arrayList4 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList4);
            String string3 = getString(R.string.str_customer_specode_3);
            CustomerDetail customerDetail3 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail3);
            arrayList4.add(new CustomerProperty(string3, customerDetail3.getSpeCode3(), true));
            ArrayList<CustomerProperty> arrayList5 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList5);
            String string4 = getString(R.string.str_customer_specode_4);
            CustomerDetail customerDetail4 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail4);
            arrayList5.add(new CustomerProperty(string4, customerDetail4.getSpeCode4(), true));
            ArrayList<CustomerProperty> arrayList6 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList6);
            String string5 = getString(R.string.str_customer_specode_5);
            CustomerDetail customerDetail5 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail5);
            arrayList6.add(new CustomerProperty(string5, customerDetail5.getSpeCode5(), true));
            ArrayList<CustomerProperty> arrayList7 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList7);
            String string6 = getString(R.string.str_customer_trading_group);
            CustomerDetail customerDetail6 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail6);
            arrayList7.add(new CustomerProperty(string6, customerDetail6.getTradingGroup(), true));
            ArrayList<CustomerProperty> arrayList8 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList8);
            String string7 = getString(R.string.str_customer_tax_office);
            CustomerDetail customerDetail7 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail7);
            arrayList8.add(new CustomerProperty(string7, customerDetail7.getTaxOffice(), true));
            CustomerDetail customerDetail8 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail8);
            if (!customerDetail8.isPersonalCompany()) {
                ArrayList<CustomerProperty> arrayList9 = this.mCustomerProperties;
                Intrinsics.checkNotNull(arrayList9);
                String string8 = getString(R.string.str_customer_tax_no);
                CustomerDetail customerDetail9 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail9);
                arrayList9.add(new CustomerProperty(string8, customerDetail9.getTaxNo(), true));
            }
            if (ErpCreator.getInstance().getmBaseErp().getErpType() != ErpType.NETSIS) {
                ArrayList<CustomerProperty> arrayList10 = this.mCustomerProperties;
                Intrinsics.checkNotNull(arrayList10);
                String string9 = getString(R.string.str_warranty_code);
                CustomerDetail customerDetail10 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail10);
                arrayList10.add(new CustomerProperty(string9, customerDetail10.getWarrantyCode(), true));
                ArrayList<CustomerProperty> arrayList11 = this.mCustomerProperties;
                Intrinsics.checkNotNull(arrayList11);
                String string10 = getString(R.string.str_personal_company);
                CustomerDetail customerDetail11 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail11);
                arrayList11.add(new CustomerProperty(string10, checkIsPersonalCompany(customerDetail11.isPersonalCompany()), true));
                CustomerDetail customerDetail12 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail12);
                if (customerDetail12.isPersonalCompany()) {
                    ArrayList<CustomerProperty> arrayList12 = this.mCustomerProperties;
                    Intrinsics.checkNotNull(arrayList12);
                    String string11 = getString(R.string.str_customer_tc_no);
                    CustomerDetail customerDetail13 = this.mCustomerDetail;
                    Intrinsics.checkNotNull(customerDetail13);
                    arrayList12.add(new CustomerProperty(string11, customerDetail13.getTcNo(), true));
                    ArrayList<CustomerProperty> arrayList13 = this.mCustomerProperties;
                    Intrinsics.checkNotNull(arrayList13);
                    String string12 = getString(R.string.str_name);
                    CustomerDetail customerDetail14 = this.mCustomerDetail;
                    Intrinsics.checkNotNull(customerDetail14);
                    arrayList13.add(new CustomerProperty(string12, customerDetail14.getName(), false));
                    ArrayList<CustomerProperty> arrayList14 = this.mCustomerProperties;
                    Intrinsics.checkNotNull(arrayList14);
                    String string13 = getString(R.string.str_surname);
                    CustomerDetail customerDetail15 = this.mCustomerDetail;
                    Intrinsics.checkNotNull(customerDetail15);
                    arrayList14.add(new CustomerProperty(string13, customerDetail15.getSurname(), false));
                }
            } else {
                ArrayList<CustomerProperty> arrayList15 = this.mCustomerProperties;
                Intrinsics.checkNotNull(arrayList15);
                String string14 = getString(R.string.str_group_code);
                CustomerDetail customerDetail16 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail16);
                arrayList15.add(new CustomerProperty(string14, customerDetail16.getGroupCode(), true));
                ArrayList<CustomerProperty> arrayList16 = this.mCustomerProperties;
                Intrinsics.checkNotNull(arrayList16);
                String string15 = getString(R.string.str_customer_tc_no);
                CustomerDetail customerDetail17 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail17);
                arrayList16.add(new CustomerProperty(string15, customerDetail17.getTcNo(), true));
            }
            ArrayList<CustomerProperty> arrayList17 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList17);
            String string16 = getString(R.string.str_country);
            CustomerDetail customerDetail18 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail18);
            arrayList17.add(new CustomerProperty(string16, customerDetail18.getCountry(), true));
            ArrayList<CustomerProperty> arrayList18 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList18);
            String string17 = getString(R.string.str_city);
            CustomerDetail customerDetail19 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail19);
            arrayList18.add(new CustomerProperty(string17, customerDetail19.getCity(), true));
            ArrayList<CustomerProperty> arrayList19 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList19);
            String string18 = getString(R.string.str_town);
            CustomerDetail customerDetail20 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail20);
            arrayList19.add(new CustomerProperty(string18, customerDetail20.getTown(), true));
            ArrayList<CustomerProperty> arrayList20 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList20);
            String string19 = getString(R.string.str_payment_type);
            CustomerDetail customerDetail21 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail21);
            arrayList20.add(new CustomerProperty(string19, customerDetail21.getPaymentTypeDefinition(), true));
            ArrayList<CustomerProperty> arrayList21 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList21);
            String string20 = getString(R.string.str_discount_rate);
            CustomerDetail customerDetail22 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail22);
            arrayList21.add(new CustomerProperty(string20, StringUtils.formatDouble(customerDetail22.getDiscRate()), false, true));
            ArrayList<CustomerProperty> arrayList22 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList22);
            String string21 = getString(R.string.str_expiry);
            CustomerDetail customerDetail23 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail23);
            arrayList22.add(new CustomerProperty(string21, StringUtils.convertIntToString(customerDetail23.getDueDate()), false, true));
            ArrayList<CustomerProperty> arrayList23 = this.mCustomerProperties;
            Intrinsics.checkNotNull(arrayList23);
            String string22 = getString(R.string.str_customer_pending_order_total);
            CustomerDetail customerDetail24 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail24);
            arrayList23.add(new CustomerProperty(string22, StringUtils.formatDouble(customerDetail24.getPendingOrderTotal()), false, true));
            if (!ErpCreator.getInstance().getmBaseErp().isHideCustomerBalance()) {
                ArrayList<CustomerProperty> arrayList24 = this.mCustomerProperties;
                Intrinsics.checkNotNull(arrayList24);
                String string23 = getString(R.string.str_customer_total_debit);
                BalanceCalculateUtil balanceCalculateUtil = BalanceCalculateUtil.INSTANCE;
                CustomerDetail customerDetail25 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail25);
                int logicalRef = customerDetail25.getLogicalRef();
                CustomerDetail customerDetail26 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail26);
                arrayList24.add(new CustomerProperty(string23, StringUtils.formatDouble(balanceCalculateUtil.getTotalDebit(logicalRef, customerDetail26.getDebit())), false, true));
                ArrayList<CustomerProperty> arrayList25 = this.mCustomerProperties;
                Intrinsics.checkNotNull(arrayList25);
                String string24 = getString(R.string.str_customer_total_credit);
                CustomerDetail customerDetail27 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail27);
                int logicalRef2 = customerDetail27.getLogicalRef();
                CustomerDetail customerDetail28 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail28);
                arrayList25.add(new CustomerProperty(string24, StringUtils.formatDouble(balanceCalculateUtil.getTotalCredit(logicalRef2, customerDetail28.getCredit())), false, true));
                ArrayList<CustomerProperty> arrayList26 = this.mCustomerProperties;
                Intrinsics.checkNotNull(arrayList26);
                String string25 = getString(R.string.str_customer_balance);
                CustomerDetail customerDetail29 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail29);
                int logicalRef3 = customerDetail29.getLogicalRef();
                CustomerDetail customerDetail30 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail30);
                arrayList26.add(new CustomerProperty(string25, StringUtils.formatDouble(BalanceCalculateUtil.getTotalBalance(logicalRef3, customerDetail30.getBalance())), false, true));
                ArrayList<CustomerProperty> arrayList27 = this.mCustomerProperties;
                Intrinsics.checkNotNull(arrayList27);
                arrayList27.add(new CustomerProperty(getString(R.string.str_risk_total), StringUtils.formatDouble(this.mCustomerRiskTotal), false, true));
            }
        }
        this.mAdapter.setCustomerProperties(this.mCustomerProperties);
    }

    private double getCustomerRiskTotal() {
        CustomerRiskInfoUtils customerRiskInfoUtils = CustomerRiskInfoUtils.INSTANCE;
        CustomerDetail customerDetail = this.mCustomerDetail;
        Intrinsics.checkNotNull(customerDetail);
        String code = customerDetail.getCode();
        Intrinsics.checkNotNull(code);
        CustomerDetail customerDetail2 = this.mCustomerDetail;
        Intrinsics.checkNotNull(customerDetail2);
        List<ClRisk> clRiskList = customerRiskInfoUtils.getClRiskList(code, customerDetail2.getLogicalRef());
        if (clRiskList == null || clRiskList.isEmpty()) {
            return 0.0d;
        }
        return clRiskList.get(0).getRiskTotal();
    }
    public void onRiskCalculateResponse(List<ClRisk> list, String str) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.dismiss();
        if (list != null && !list.isEmpty()) {
            Intrinsics.checkNotNull(list);
            this.mCustomerRiskTotal = list.get(0).getRiskTotal();
        }
        loadData();
    }

    private String checkIsPersonalCompany(boolean z) {
        if (z) {
            String string = getString(R.string.str_yes);
            Intrinsics.checkNotNull(string);
            return string;
        }
        String string2 = getString(R.string.str_no);
        Intrinsics.checkNotNull(string2);
        return string2;
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
    }
}
