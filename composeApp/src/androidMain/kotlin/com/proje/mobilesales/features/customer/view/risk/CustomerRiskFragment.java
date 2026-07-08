package com.proje.mobilesales.features.customer.view.risk;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseListFragment;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.sql.netsis.NetsisSqlHelper;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
import com.proje.mobilesales.core.widget.SlideInLeftAnimator;
import com.proje.mobilesales.databinding.FragmentCustomerRiskBinding;
import com.proje.mobilesales.databinding.FragmentCustomerRiskNetsisBinding;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.adapter.KeyValueAdapter;
import com.proje.mobilesales.features.customer.model.CustomerRisk;
import com.proje.mobilesales.features.customer.model.CustomerRiskProperty;
import com.proje.mobilesales.features.customer.repository.BaseCustomerRepository;
import com.proje.mobilesales.features.model.KeyValueProperty;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerRiskFragment extends BaseListFragment {
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_CUSTOMER_REF = "extra:customerRef";
    public static final String STATE_CUSTOMER_REF = "state:customerRef";
    private static final String STATE_RISK_NAMES = "state:mRiskNames";
    private FragmentCustomerRiskBinding _bindingRiskBinding;
    private FragmentCustomerRiskNetsisBinding _bindingRiskBindingNetsis;
    private CheckBox chbFatRiskDavran;
    private CheckBox chbIrsRiskDavran;
    private CheckBox chbSevkRiskDavran;
    private CheckBox chbSipRiskDavran;
    private CheckBox chbYukRiskDavran;
    private final KeyValueAdapter kAdapter;
    private final CustomerRiskRecyclerViewAdapter mAdapter;
    private int mCustomerRef;
    private ArrayList<CustomerRiskProperty> mCustomerRiskProperties;
    private ArrayList<KeyValueProperty> mKeyValueProperties;
    private String[] mRiskNames;
    private AppBarSwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTxtRiskClosedTotal;
    private TextView mTxtRiskLimitTotal;
    private TextView mTxtRiskTotal;
    private final BaseCustomerRepository repository;
    private TextView txtRiskToplami;
    private final CustomerRiskViewModel viewModel;

    public CustomerRiskFragment() {
        final BaseCustomerRepository baseCustomerRepository = new BaseCustomerRepository();
        repository = baseCustomerRepository;
        viewModel = new CustomerRiskViewModel(baseCustomerRepository);
        mAdapter = new CustomerRiskRecyclerViewAdapter();
        kAdapter = new KeyValueAdapter();
    }

    private FragmentCustomerRiskBinding getBindingRiskBinding() {
        final FragmentCustomerRiskBinding fragmentCustomerRiskBinding = _bindingRiskBinding;
        Intrinsics.checkNotNull(fragmentCustomerRiskBinding);
        return fragmentCustomerRiskBinding;
    }

    private FragmentCustomerRiskNetsisBinding getBindingRiskBindingNetsis() {
        final FragmentCustomerRiskNetsisBinding fragmentCustomerRiskNetsisBinding = _bindingRiskBindingNetsis;
        Intrinsics.checkNotNull(fragmentCustomerRiskNetsisBinding);
        return fragmentCustomerRiskNetsisBinding;
    }
     public void onCreate(final Bundle bundle) {
        final Resources resources;
        final int i2;
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        if (null != bundle) {
            mRiskNames = bundle.getStringArray(CustomerRiskFragment.STATE_RISK_NAMES);
            mCustomerRef = bundle.getInt("state:customerRef");
            return;
        }
        if (viewModel.erpType() == ErpType.NETSIS) {
            final FragmentActivity activity = this.getActivity();
            Intrinsics.checkNotNull(activity);
            resources = activity.getResources();
            i2 = R.array.customer_risk_name_values_for_netsis;
        } else {
            final FragmentActivity activity2 = this.getActivity();
            Intrinsics.checkNotNull(activity2);
            resources = activity2.getResources();
            i2 = R.array.customer_risk_name_values_for_tiger;
        }
        mRiskNames = resources.getStringArray(i2);
        final Bundle arguments = this.getArguments();
        Intrinsics.checkNotNull(arguments);
        mCustomerRef = arguments.getInt("extra:customerRef");
        this.initList();
    }
    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        if (viewModel.erpType() == ErpType.NETSIS) {
            _bindingRiskBindingNetsis = FragmentCustomerRiskNetsisBinding.inflate(inflater, viewGroup, false);
            chbIrsRiskDavran = this.getBindingRiskBindingNetsis().chbIrsRiskDavran;
            chbFatRiskDavran = this.getBindingRiskBindingNetsis().chbFatRiskDavran;
            chbSevkRiskDavran = this.getBindingRiskBindingNetsis().chbSevkRiskDavran;
            chbSipRiskDavran = this.getBindingRiskBindingNetsis().chbSipRiskDavran;
            chbYukRiskDavran = this.getBindingRiskBindingNetsis().chbYukRiskDavran;
            txtRiskToplami = this.getBindingRiskBindingNetsis().txtRiskToplami;
            mSwipeRefreshLayout = this.getBindingRiskBindingNetsis().swipeLayout;
            final RecyclerView recyclerView = this.getBindingRiskBindingNetsis().rcw;
            mRecyclerView = recyclerView;
            recyclerView.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(2.0f)));
            final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = mSwipeRefreshLayout;
            Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
            appBarSwipeRefreshLayout.setColorSchemeResources(R.color.white);
            final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = mSwipeRefreshLayout;
            Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
            appBarSwipeRefreshLayout2.setProgressBackgroundColorSchemeResource(R.color.redA200);
            final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout3 = mSwipeRefreshLayout;
            Intrinsics.checkNotNull(appBarSwipeRefreshLayout3);
            appBarSwipeRefreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                public void onRefresh() {
                    onCreateViewlambda0(CustomerRiskFragment.this);
                }
            });
            final RelativeLayout root = this.getBindingRiskBindingNetsis().getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            return root;
        }
        _bindingRiskBinding = FragmentCustomerRiskBinding.inflate(inflater, viewGroup, false);
        mTxtRiskLimitTotal = this.getBindingRiskBinding().txtRiskLimitTotal;
        mTxtRiskClosedTotal = this.getBindingRiskBinding().txtRiskLimitClosedTotal;
        mTxtRiskTotal = this.getBindingRiskBinding().txtRiskTotal;
        mSwipeRefreshLayout = this.getBindingRiskBinding().swipeLayout;
        final RecyclerView recyclerView2 = this.getBindingRiskBinding().rcw;
        mRecyclerView = recyclerView2;
        recyclerView2.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(2.0f)));
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout4 = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout4);
        appBarSwipeRefreshLayout4.setColorSchemeResources(R.color.white);
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout5 = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout5);
        appBarSwipeRefreshLayout5.setProgressBackgroundColorSchemeResource(R.color.redA200);
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout6 = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout6);
        appBarSwipeRefreshLayout6.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                onCreateViewlambda1(CustomerRiskFragment.this);
            }
        });
        final RelativeLayout root2 = this.getBindingRiskBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root2, "getRoot(...)");
        return root2;
    }
    public static void onCreateViewlambda0(final CustomerRiskFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.getCustomerRiskInfo();
    }
    public static void onCreateViewlambda1(final CustomerRiskFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.getCustomerRiskInfo();
    }
     public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        this.getCustomerRiskInfo();
    }

    private Unit getCustomerRiskInfo() {
        viewModel.getCustomerRiskInfo(mCustomerRef, new CustomerRiskResponseListener(this));
        return Unit.INSTANCE;
    }
     public void onDetach() {
        super.onDetach();
        final RecyclerView recyclerView = mRecyclerView;
        if (null != recyclerView) {
            recyclerView.setAdapter(null);
        }
    }
    protected IListRecyclerView getAdapter() {
        return viewModel.erpType() == ErpType.NETSIS ? kAdapter : mAdapter;
    }

    private void initList() {
        int i2 = 0;
        if (viewModel.erpType() == ErpType.NETSIS) {
            mKeyValueProperties = new ArrayList<>();
            final String[] strArr = mRiskNames;
            Intrinsics.checkNotNull(strArr);
            final int length = strArr.length;
            while (i2 < length) {
                final ArrayList<KeyValueProperty> arrayList = mKeyValueProperties;
                Intrinsics.checkNotNull(arrayList);
                final String[] strArr2 = mRiskNames;
                Intrinsics.checkNotNull(strArr2);
                arrayList.add(i2, new KeyValueProperty(strArr2[i2], ""));
                i2++;
            }
            return;
        }
        mCustomerRiskProperties = new ArrayList<>();
        final String[] strArr3 = mRiskNames;
        Intrinsics.checkNotNull(strArr3);
        final int length2 = strArr3.length;
        while (i2 < length2) {
            final ArrayList<CustomerRiskProperty> arrayList2 = mCustomerRiskProperties;
            Intrinsics.checkNotNull(arrayList2);
            final String[] strArr4 = mRiskNames;
            Intrinsics.checkNotNull(strArr4);
            arrayList2.add(i2, new CustomerRiskProperty(strArr4[i2], 0.0d, 0.0d, 0.0d, true));
            i2++;
        }
    }
    public void loadData(final List<CustomerRisk> list) {
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        if (appBarSwipeRefreshLayout.isRefreshing()) {
            final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = mSwipeRefreshLayout;
            Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
            appBarSwipeRefreshLayout2.setRefreshing(false);
        }
        if (null == list || list.isEmpty()) {
            return;
        }
        if (viewModel.erpType() == ErpType.NETSIS) {
            this.loadDataNetsis(list);
        } else {
            this.loadDataTiger(list);
        }
    }

    private void loadDataNetsis(final List<CustomerRisk> list) {
        final ArrayList<KeyValueProperty> arrayList = mKeyValueProperties;
        if (null != arrayList) {
            Intrinsics.checkNotNull(arrayList);
        }
        this.initList();
        final CustomerRisk customerRisk = list.get(0);
        final CheckBox checkBox = chbSipRiskDavran;
        Intrinsics.checkNotNull(checkBox);
        checkBox.setChecked(0 == customerRisk.getSipRiskDavran());
        final CheckBox checkBox2 = chbSevkRiskDavran;
        Intrinsics.checkNotNull(checkBox2);
        checkBox2.setChecked(0 == customerRisk.getSevkRiskDavran());
        final CheckBox checkBox3 = chbYukRiskDavran;
        Intrinsics.checkNotNull(checkBox3);
        checkBox3.setChecked(0 == customerRisk.getYukRiskDavran());
        final CheckBox checkBox4 = chbIrsRiskDavran;
        Intrinsics.checkNotNull(checkBox4);
        checkBox4.setChecked(0 == customerRisk.getIrsRiskDavran());
        final CheckBox checkBox5 = chbFatRiskDavran;
        Intrinsics.checkNotNull(checkBox5);
        checkBox5.setChecked(0 == customerRisk.getFatRiskDavran());
        final TextView textView = txtRiskToplami;
        Intrinsics.checkNotNull(textView);
        textView.setText(StringUtils.formatDouble(customerRisk.getRiskToplami()));
        this.swapRisk(0, StringUtils.formatDouble(customerRisk.getRiskTeminat()));
        this.swapRisk(1, StringUtils.formatDouble(customerRisk.getSenetAsilRisk()));
        this.swapRisk(2, StringUtils.formatDouble(customerRisk.getSenetCiroRisk()));
        this.swapRisk(3, StringUtils.formatDouble(customerRisk.getCekAsilRisk()));
        this.swapRisk(4, StringUtils.formatDouble(customerRisk.getCekCiroRisk()));
        this.swapRisk(5, StringUtils.formatDouble(customerRisk.getSiparisRiski()));
        this.swapRisk(6, StringUtils.formatDouble(customerRisk.getSevkRiski()));
        this.swapRisk(7, StringUtils.formatDouble(customerRisk.getYuklemeRiski()));
        this.swapRisk(8, StringUtils.formatDouble(customerRisk.getIrsaliyeRiski()));
        final ISqlHelper<?> sqlHelper = viewModel.getSqlHelper();
        Intrinsics.checkNotNull(sqlHelper, "null cannot be cast to non-null type com.proje.mobilesales.core.sql.netsis.NetsisSqlHelper<*>");
        ((NetsisSqlHelper) sqlHelper).updateCustomerRiskValues(customerRisk, mCustomerRef);
        kAdapter.setKeyValueProperties(mKeyValueProperties);
    }

    private void loadDataTiger(final List<CustomerRisk> list) {
        final ArrayList<CustomerRiskProperty> arrayList = mCustomerRiskProperties;
        if (null != arrayList) {
            Intrinsics.checkNotNull(arrayList);
        }
        this.initList();
        this.clearTotalLayout();
        final CustomerRisk customerRisk = list.get(0);
        this.swapRisk(0, customerRisk.getAccRiskLimit(), customerRisk.getAccRiskClosed(), customerRisk.getAccRiskTotal(), customerRisk.getAccRisk());
        this.swapRisk(1, customerRisk.getMyCsRiskLimit(), customerRisk.getMyCsRiskClosed(), customerRisk.getMyRiskTotal(), customerRisk.getMyCsRisk());
        this.swapRisk(2, customerRisk.getCsTcsRiskLimit(), customerRisk.getCsTcsRiskClosed(), customerRisk.getCsTcsRiskTotal(), customerRisk.getCsTcsRisk());
        this.swapRisk(3, customerRisk.getCsTcsCiroRiskLimit(), customerRisk.getCsTcsCiroRiskClosed(), customerRisk.getCsTcsCiroRiskTotal(), customerRisk.getCsTcsCiroRisk());
        this.swapRisk(4, customerRisk.getDespRiskLimit(), customerRisk.getDespRiskClosed(), customerRisk.getDespRiskTotal(), customerRisk.getDespRisk());
        this.swapRisk(5, customerRisk.getDespSugRiskLimit(), customerRisk.getDespSugRiskClosed(), customerRisk.getDespSugRiskTotal(), customerRisk.getDespSugRisk());
        this.swapRisk(6, customerRisk.getOrdRiskLimit(), customerRisk.getOrdRiskClosed(), customerRisk.getOrdRiskTotal(), customerRisk.getOrdRisk());
        this.swapRisk(7, customerRisk.getOrdSugRiskLimit(), customerRisk.getOrdSugRiskClosed(), customerRisk.getOrdSugRiskTotal(), customerRisk.getOrdSugRisk());
        this.setTotalLayout(customerRisk);
        mAdapter.setCustomerRiskProperties(mCustomerRiskProperties);
    }

    private void swapRisk(final int i2, final String str) {
        final ArrayList<KeyValueProperty> arrayList = mKeyValueProperties;
        Intrinsics.checkNotNull(arrayList);
        final KeyValueProperty keyValueProperty = arrayList.get(i2);
        Intrinsics.checkNotNullExpressionValue(keyValueProperty, "get(...)");
        keyValueProperty.setValue(str);
    }

    private void swapRisk(final int i2, final double d2, final double d3, final double d4, final int i3) {
        final ArrayList<CustomerRiskProperty> arrayList = mCustomerRiskProperties;
        Intrinsics.checkNotNull(arrayList);
        final CustomerRiskProperty customerRiskProperty = arrayList.get(i2);
        Intrinsics.checkNotNullExpressionValue(customerRiskProperty, "get(...)");
        final CustomerRiskProperty customerRiskProperty2 = customerRiskProperty;
        customerRiskProperty2.setLimit(d2);
        customerRiskProperty2.setClosed(d3);
        customerRiskProperty2.setTotal(d4 - d3);
        customerRiskProperty2.setRisk(1 == i3);
    }

    private void setTotalLayout(final CustomerRisk customerRisk) {
        final TextView textView = mTxtRiskLimitTotal;
        Intrinsics.checkNotNull(textView);
        textView.setText(StringUtils.formatDouble(customerRisk.getRiskLimit()));
        final TextView textView2 = mTxtRiskClosedTotal;
        Intrinsics.checkNotNull(textView2);
        textView2.setText(StringUtils.formatDouble(customerRisk.getRiskClosed()));
        if (viewModel.getBaseErp().isHideCustomerBalance()) {
            final TextView textView3 = mTxtRiskTotal;
            Intrinsics.checkNotNull(textView3);
            textView3.setText("-");
        } else {
            final TextView textView4 = mTxtRiskTotal;
            Intrinsics.checkNotNull(textView4);
            textView4.setText(StringUtils.formatDouble(customerRisk.getRiskTotal() - customerRisk.getRiskClosed()));
        }
    }

    private void clearTotalLayout() {
        final TextView textView = mTxtRiskLimitTotal;
        Intrinsics.checkNotNull(textView);
        textView.setText("");
        final TextView textView2 = mTxtRiskClosedTotal;
        Intrinsics.checkNotNull(textView2);
        textView2.setText("");
        final TextView textView3 = mTxtRiskTotal;
        Intrinsics.checkNotNull(textView3);
        textView3.setText("");
    }
    public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putStringArray(CustomerRiskFragment.STATE_RISK_NAMES, mRiskNames);
        outState.putInt("state:customerRef", mCustomerRef);
        super.onSaveInstanceState(outState);
    }

    public void showError(final String str) {
        Toast.makeText(this.getContext(), str, 1).show();
    }
        private record CustomerRiskResponseListener(
            WeakReference<CustomerRiskFragment> mCustomerRiskFragmentWeakReference) implements ResponseListener<List<CustomerRisk>> {
            private CustomerRiskResponseListener(final CustomerRiskFragment mCustomerRiskFragmentWeakReference) {
                this(new WeakReference<>(mCustomerRiskFragmentWeakReference));
            }
            public   void onResponse(final List<? extends CustomerRisk> list) {
                this.onResponse2((List<CustomerRisk>) list);
            }
            public void onResponse2(final List<CustomerRisk> list) {
                if (null != this.mCustomerRiskFragmentWeakReference.get()) {
                    final CustomerRiskFragment customerRiskFragment = mCustomerRiskFragmentWeakReference.get();
                    Intrinsics.checkNotNull(customerRiskFragment);
                    if (customerRiskFragment.isAttached()) {
                        final CustomerRiskFragment customerRiskFragment2 = mCustomerRiskFragmentWeakReference.get();
                        Intrinsics.checkNotNull(customerRiskFragment2);
                        customerRiskFragment2.loadData(list);
                    }
                }
            }
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mCustomerRiskFragmentWeakReference.get()) {
                    final CustomerRiskFragment customerRiskFragment = mCustomerRiskFragmentWeakReference.get();
                    Intrinsics.checkNotNull(customerRiskFragment);
                    if (customerRiskFragment.isAttached()) {
                        Log.d(MobileSales.TAG, "onError: " + errorMessage);
                        final CustomerRiskFragment customerRiskFragment2 = mCustomerRiskFragmentWeakReference.get();
                        Intrinsics.checkNotNull(customerRiskFragment2);
                        customerRiskFragment2.showError(errorMessage);
                    }
                }
            }
            public boolean equals( Object obj) {
                return false;
            }
            public int hashCode() {
                return 0;
            }
            public String toString() {
                return "";
            }
        }
    public void onDestroyView() {
        super.onDestroyView();
        _bindingRiskBinding = null;
        _bindingRiskBindingNetsis = null;
    }

    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
