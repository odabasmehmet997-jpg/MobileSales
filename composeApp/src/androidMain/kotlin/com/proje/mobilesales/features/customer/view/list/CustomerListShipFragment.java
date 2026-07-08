package com.proje.mobilesales.features.customer.view.list;

import android.R;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseListFragment;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
import com.proje.mobilesales.databinding.EmptyListBinding;
import com.proje.mobilesales.databinding.EmptySearchBinding;
import com.proje.mobilesales.databinding.FragmentListSwipeBinding;
import com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.repository.CustomerListRepository;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;


/* compiled from: CustomerListShipFragment.kt */

public final class CustomerListShipFragment extends BaseListFragment {
    public static final int REQUEST_ADD_CUSTOMER = 105;
    private static final String STATE_CUSTOMER_SELECT_TYPE = "state:customerSelectType";
    private static final String STATE_CUSTOMER_TYPE = "state:customerType";
    private static final String STATE_CUSTOMER_UPDATE = "state:mCustomerUpdate";
    private static final String STATE_DAY_REF = "state:customerType";
    private static final String STATE_FILTER = "state:filter";
    private static final String STATE_SALES_CUSTOMER_CODE = "state:salesCustomerCode";
    private static final String STATE_SEARCH_VIEW_EXPANDED = "state:searchViewExpanded";
    private static final String STATE_SHOW_DETAIL = "state:showDetail";
    private static final String STATE_SHOW_TITLE_TYPE = "state:showTitleType";
    private static final String STATE_SORT_TYPE = "state:sortType";
    private static final String STATE_USE_NEAR = "state:useNear";
    private static final String TAG = "CustomerListFragment";
    private FragmentListSwipeBinding _binding;
    private final ArrayList<Customer> customerList;
    private ActionViewResolver mActionViewResolver;
    private CustomerRecyclerViewAdapter mAdapter;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private String mClCode;
    private int mCustomerSelectType;
    private boolean mCustomerShowDetail;
    private int mCustomerType;
    private boolean mCustomerUpdate;
    private int mDayRef;
    private EmptySearchBinding mEmptySearchView;
    private EmptyListBinding mEmptyView;
    private String mFilter;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private boolean mSearchViewExpanded;
    private int mShowTitleType;
    private int mSortType;
    private AppBarSwipeRefreshLayout mSwipeRefreshLayout;
    private int mUseNearType;
    private final CustomerListRepository repository;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private final CustomerListViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_FILTER = CustomerListFragment.class.getName() + ".EXTRA_FILTER";
    private static final String EXTRA_CUSTOMER_SELECT_TYPE = CustomerListFragment.class.getName() + ".EXTRA_CUSTOMER_SELECT_TYPE";
    private static final String EXTRA_SALES_CUSTOMER_CODE = CustomerListShipFragment.class.getName() + ".EXTRA_SALES_CUSTOMER_CODE";

    public CustomerListShipFragment() {
        final CustomerListRepository customerListRepository = new CustomerListRepository();
        repository = customerListRepository;
        viewModel = new CustomerListViewModel(customerListRepository);
        mAdapter = new CustomerRecyclerViewAdapter();
        customerList = new ArrayList<>();
    }

    private FragmentListSwipeBinding getBinding() {
        final FragmentListSwipeBinding fragmentListSwipeBinding = _binding;
        Intrinsics.checkNotNull(fragmentListSwipeBinding);
        return fragmentListSwipeBinding;
    }

    public SharedPreferencesHelper getSharedPreferencesHelper() {
        return sharedPreferencesHelper;
    }

    public void setSharedPreferencesHelper(final SharedPreferencesHelper sharedPreferencesHelper) {
        this.sharedPreferencesHelper = sharedPreferencesHelper;
    }

    public ActionViewResolver getMActionViewResolver() {
        return mActionViewResolver;
    }

    public void setMActionViewResolver(final ActionViewResolver actionViewResolver) {
        mActionViewResolver = actionViewResolver;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(final AlertDialogBuilder<?> alertDialogBuilder) {
        mAlertDialogBuilder = alertDialogBuilder;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return mProgressDialogBuilder;
    }

    public void setMProgressDialogBuilder(final ProgressDialogBuilder<?> progressDialogBuilder) {
        mProgressDialogBuilder = progressDialogBuilder;
    }

    public AppBarSwipeRefreshLayout getMSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    public void setMSwipeRefreshLayout(final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout) {
        mSwipeRefreshLayout = appBarSwipeRefreshLayout;
    }

    public CustomerRecyclerViewAdapter getMAdapter() {
        return mAdapter;
    }

    public void setMAdapter(final CustomerRecyclerViewAdapter customerRecyclerViewAdapter) {
        Intrinsics.checkNotNullParameter(customerRecyclerViewAdapter, "<set-?>");
        mAdapter = customerRecyclerViewAdapter;
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        final Context requireContext = this.requireContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mProgressDialogBuilder = new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity);
        final Context requireContext2 = this.requireContext();
        final Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mAlertDialogBuilder = new AlertDialogBuilder.Impl(requireContext2, (BaseInjectableActivity) activity2);
        mActionViewResolver = new ActionViewResolver();
        sharedPreferencesHelper = new SharedPreferencesHelper(this.getContext());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        _binding = FragmentListSwipeBinding.inflate(inflater, viewGroup, false);
        mSwipeRefreshLayout = this.getBinding().swipeLayout;
        mRecyclerView = this.getBinding().rcwList;
        mEmptyView = this.getBinding().empty;
        mEmptySearchView = this.getBinding().emptySearch;
        final EmptyListBinding emptyListBinding = mEmptyView;
        Intrinsics.checkNotNull(emptyListBinding);
        emptyListBinding.getRoot().setVisibility(4);
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        appBarSwipeRefreshLayout.setColorSchemeResources(R.color.white);
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
        appBarSwipeRefreshLayout2.setProgressBackgroundColorSchemeResource(R.color.redA200);
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout3 = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout3);
        appBarSwipeRefreshLayout3.setEnabled(false);
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout4 = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout4);
        appBarSwipeRefreshLayout4.setRefreshing(false);
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout5 = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout5);
        appBarSwipeRefreshLayout5.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListShipFragmentExternalSyntheticLambda0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                onCreateViewlambda0(CustomerListShipFragment.this);
            }
        });
        final FrameLayout root = this.getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    
    public static void onCreateViewlambda0(final CustomerListShipFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this0.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        appBarSwipeRefreshLayout.setRefreshing(false);
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        if (null != bundle) {
            mFilter = bundle.getString(CustomerListShipFragment.STATE_FILTER);
            mCustomerShowDetail = bundle.getBoolean(CustomerListShipFragment.STATE_SHOW_DETAIL);
            mSearchViewExpanded = bundle.getBoolean(CustomerListShipFragment.STATE_SEARCH_VIEW_EXPANDED);
            mUseNearType = bundle.getInt(CustomerListShipFragment.STATE_USE_NEAR);
            mCustomerType = bundle.getInt("state:customerType");
            mDayRef = bundle.getInt("state:customerType");
            mCustomerSelectType = bundle.getInt(CustomerListShipFragment.STATE_CUSTOMER_SELECT_TYPE);
            mCustomerUpdate = bundle.getBoolean(CustomerListShipFragment.STATE_CUSTOMER_UPDATE, false);
            final int i2 = bundle.getInt(CustomerListShipFragment.STATE_SORT_TYPE, mSortType);
            mSortType = i2;
            mShowTitleType = bundle.getInt(CustomerListShipFragment.STATE_SHOW_TITLE_TYPE, i2);
            mClCode = bundle.getString(CustomerListShipFragment.STATE_SALES_CUSTOMER_CODE);
        } else {
            final Bundle arguments = this.getArguments();
            Intrinsics.checkNotNull(arguments);
            mFilter = arguments.getString(CustomerListShipFragment.EXTRA_FILTER);
            final Bundle arguments2 = this.getArguments();
            Intrinsics.checkNotNull(arguments2);
            mClCode = arguments2.getString(CustomerListShipFragment.EXTRA_SALES_CUSTOMER_CODE);
            mUseNearType = viewModel.getBaseErp().getUseNearRp();
            mCustomerType = viewModel.getBaseErp().getCustomerAccountType();
            mShowTitleType = viewModel.getBaseErp().getClCardTitleShowType();
            mSortType = -1 < this.viewModel.getBaseErp().getCustomerSortType() ? viewModel.getBaseErp().getCustomerSortType() : viewModel.getBaseErp().getSortListType();
            if (1 == this.mCustomerType) {
                final SharedPreferencesHelper sharedPreferencesHelper = this.sharedPreferencesHelper;
                Intrinsics.checkNotNull(sharedPreferencesHelper);
                mDayRef = StringUtils.convertStringToInt(sharedPreferencesHelper.getUserCustomerSelectType());
            }
            final Bundle arguments3 = this.getArguments();
            Intrinsics.checkNotNull(arguments3);
            final int i3 = arguments3.getInt(CustomerListShipFragment.EXTRA_CUSTOMER_SELECT_TYPE);
            mCustomerSelectType = i3;
            mCustomerShowDetail = 1 != i3 && 2 != i3 && viewModel.getBaseErp().getCustomerShowDetail();
            mSearchViewExpanded = false;
        }
        mAdapter.setActivity(this.getActivity());
        mAdapter.setCustomerSelectType(mCustomerSelectType);
        mAdapter.restartScroll();
        mAdapter.notifyDataSetChanged();
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setMessage(this.getString(R.string.str_please_wait)).show();
        if (ContextUtils.checkInternetConnection()) {
            if (2 == this.mCustomerSelectType) {
                viewModel.getListAllCustomersOnline("", this.customerShipListResponseListener(this));
            } else {
                viewModel.getOnlineShipCustomer(mClCode, this.customerShipListResponseListener(this));
            }
        } else {
            final ProgressDialogBuilder<?> progressDialogBuilder2 = mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder2);
            progressDialogBuilder2.dismiss();
        }
        mAdapter.clearScrollListeners();
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, com.proje.mobilesales.core.base.BaseFragment
    protected void createOptionsMenu(final Menu menu, final MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        inflater.inflate(R.menu.menu_search, menu);
        final MenuItem findItem = menu.findItem(R.id.menu_search);
        Intrinsics.checkNotNullExpressionValue(findItem, "findItem(...)");
        this.createSearchView(findItem);
        super.createOptionsMenu(menu, inflater);
    }

    @Override // com.proje.mobilesales.core.base.BaseFragment
    protected void prepareOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.prepareOptionsMenu(menu);
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putString(CustomerListShipFragment.STATE_FILTER, mFilter);
        outState.putBoolean(CustomerListShipFragment.STATE_SEARCH_VIEW_EXPANDED, mSearchViewExpanded);
        outState.putBoolean(CustomerListShipFragment.STATE_SHOW_DETAIL, mCustomerShowDetail);
        outState.putInt(CustomerListShipFragment.STATE_USE_NEAR, mUseNearType);
        outState.putInt("state:customerType", mCustomerType);
        outState.putInt("state:customerType", mDayRef);
        outState.putInt(CustomerListShipFragment.STATE_CUSTOMER_SELECT_TYPE, mCustomerSelectType);
        outState.putBoolean(CustomerListShipFragment.STATE_CUSTOMER_UPDATE, mCustomerUpdate);
        outState.putInt(CustomerListShipFragment.STATE_SORT_TYPE, mSortType);
        outState.putInt(CustomerListShipFragment.STATE_SHOW_TITLE_TYPE, mShowTitleType);
    }

    private void createSearchView(final MenuItem menuItem) {
        Drawable textCursorDrawable;
        final ActionViewResolver actionViewResolver = mActionViewResolver;
        Intrinsics.checkNotNull(actionViewResolver);
        final View actionView = actionViewResolver.getActionView(menuItem);
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        final SearchView searchView = (SearchView) actionView;
        searchView.setQueryHint(this.getString(R.string.hint_search_customer));
        final Object systemService = this.requireActivity().getSystemService(FirebaseAnalytics.Event.SEARCH);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.SearchManager");
        final FragmentActivity activity = this.getActivity();
        Intrinsics.checkNotNull(activity);
        searchView.setSearchableInfo(((SearchManager) systemService).getSearchableInfo(activity.getComponentName()));
        searchView.setIconified(!mSearchViewExpanded);
        searchView.setQuery(mFilter, false);
        final View findViewById = searchView.findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView.SearchAutoComplete");
        final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) findViewById;
        searchAutoComplete.setHintTextColor(ContextCompat.getColor(this.requireContext(), R.color.white));
        if (29 <= Build.VERSION.SDK_INT && null != textCursorDrawable) {
            textCursorDrawable.setColorFilter(ContextCompat.getColor(this.requireContext(), R.color.custom_cursor_color), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setTextCursorDrawable(textCursorDrawable);
        }
        searchView.setOnSearchClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListShipFragmentExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                createSearchViewlambda1(CustomerListShipFragment.this, view);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListShipFragmentExternalSyntheticLambda2
            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            public boolean onClose() {
                final boolean createSearchViewlambda2;
                createSearchViewlambda2 = createSearchViewlambda2(CustomerListShipFragment.this);
                return createSearchViewlambda2;
            }
        });
    }

    
    public static void createSearchViewlambda1(final CustomerListShipFragment this0, final View v) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(v, "v");
        this0.mSearchViewExpanded = true;
        v.requestFocus();
    }

    
    public static boolean createSearchViewlambda2(final CustomerListShipFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mFilter = "";
        final AppCompatActivity appCompatActivity = (AppCompatActivity) this0.getActivity();
        Intrinsics.checkNotNull(appCompatActivity);
        final ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setSubtitle(this0.mFilter);
        this0.filterData("");
        return false;
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        final RecyclerView recyclerView = mRecyclerView;
        if (null != recyclerView) {
            recyclerView.setAdapter(null);
        }
    }

    
    public void onCustomersLoad(final ArrayList<ClCard> arrayList, final String str) {
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        if (appBarSwipeRefreshLayout.isRefreshing()) {
            final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = mSwipeRefreshLayout;
            Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
            appBarSwipeRefreshLayout2.setRefreshing(false);
        }
        if (null != arrayList) {
            final Iterator<ClCard> it = arrayList.iterator();
            while (it.hasNext()) {
                customerList.add(it.next().convertCustomer());
            }
        }
        this.swapCursor(customerList);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this.getActivity(), str, 1).show();
    }

    private void swapCursor(final List<Customer> list) {
        mAdapter.addItem(list);
        mAdapter.setDisplayOptions(mCustomerShowDetail);
        if (this.isDetached()) {
            return;
        }
        this.toggleEmptyView(0 == this.mAdapter.getItemCount(), mFilter);
    }

    private void toggleEmptyView(final boolean z, final String str) {
        if (z) {
            if (TextUtils.isEmpty(str)) {
                mRecyclerView.setVisibility(8);
                final EmptySearchBinding emptySearchBinding = mEmptySearchView;
                Intrinsics.checkNotNull(emptySearchBinding);
                emptySearchBinding.getRoot().setVisibility(4);
                final EmptyListBinding emptyListBinding = mEmptyView;
                Intrinsics.checkNotNull(emptyListBinding);
                emptyListBinding.getRoot().setVisibility(0);
                final EmptyListBinding emptyListBinding2 = mEmptyView;
                Intrinsics.checkNotNull(emptyListBinding2);
                emptyListBinding2.getRoot().bringToFront();
                return;
            }
            mRecyclerView.setVisibility(8);
            final EmptyListBinding emptyListBinding3 = mEmptyView;
            Intrinsics.checkNotNull(emptyListBinding3);
            emptyListBinding3.getRoot().setVisibility(4);
            final EmptySearchBinding emptySearchBinding2 = mEmptySearchView;
            Intrinsics.checkNotNull(emptySearchBinding2);
            emptySearchBinding2.getRoot().setVisibility(0);
            final EmptySearchBinding emptySearchBinding3 = mEmptySearchView;
            Intrinsics.checkNotNull(emptySearchBinding3);
            emptySearchBinding3.getRoot().bringToFront();
            return;
        }
        final EmptyListBinding emptyListBinding4 = mEmptyView;
        Intrinsics.checkNotNull(emptyListBinding4);
        emptyListBinding4.getRoot().setVisibility(4);
        final EmptySearchBinding emptySearchBinding4 = mEmptySearchView;
        Intrinsics.checkNotNull(emptySearchBinding4);
        emptySearchBinding4.getRoot().setVisibility(4);
        mRecyclerView.setVisibility(0);
        mRecyclerView.bringToFront();
    }

    public void filterData(final String str) {
        mAdapter.restartAdapterAndScroll();
        if (null == str || 0 == str.length()) {
            this.swapCursor(customerList);
            return;
        }
        final ArrayList arrayList = new ArrayList();
        final Iterator<Customer> it = customerList.iterator();
        while (it.hasNext()) {
            final Customer next = it.next();
            final String code = next.getCode();
            Intrinsics.checkNotNull(code);
            final Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            final String upperCase = code.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            final Locale locale2 = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
            final String upperCase2 = str.toUpperCase(locale2);
            Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
            if (!StringsKt.containsdefault((CharSequence) upperCase, (CharSequence) upperCase2, false, 2, (Object) null)) {
                final String title = next.getTitle();
                Intrinsics.checkNotNull(title);
                final Locale locale3 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale3, "getDefault(...)");
                final String upperCase3 = title.toUpperCase(locale3);
                Intrinsics.checkNotNullExpressionValue(upperCase3, "toUpperCase(...)");
                final Locale locale4 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale4, "getDefault(...)");
                final String upperCase4 = str.toUpperCase(locale4);
                Intrinsics.checkNotNullExpressionValue(upperCase4, "toUpperCase(...)");
                if (StringsKt.containsdefault((CharSequence) upperCase3, (CharSequence) upperCase4, false, 2, (Object) null)) {
                }
            }
            arrayList.add(next);
        }
        this.swapCursor(arrayList);
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment
    protected ListRecyclerViewAdapter<?, ?> getAdapter() {
        return mAdapter;
    }

    private CustomerListShipFragmentcustomerShipListResponseListener1 customerShipListResponseListener(CustomerListShipFragment customerListShipFragment) {
        return new ResponseListener<ArrayList<ClCard>>(customerListShipFragment) {
            private final WeakReference<CustomerListShipFragment> mCustomerListFragment;

            {
                mCustomerListFragment = new WeakReference<>(customerListShipFragment);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(final PrintSlipModel arrayList) {
                if (null != this.mCustomerListFragment.get()) {
                    final CustomerListShipFragment customerListShipFragment2 = mCustomerListFragment.get();
                    Intrinsics.checkNotNull(customerListShipFragment2);
                    if (customerListShipFragment2.isAttached()) {
                        final CustomerListShipFragment customerListShipFragment3 = mCustomerListFragment.get();
                        Intrinsics.checkNotNull(customerListShipFragment3);
                        customerListShipFragment3.onCustomersLoad(arrayList, "");
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mCustomerListFragment.get()) {
                    final CustomerListShipFragment customerListShipFragment2 = mCustomerListFragment.get();
                    Intrinsics.checkNotNull(customerListShipFragment2);
                    if (customerListShipFragment2.isAttached()) {
                        Log.d("AA", "onError: " + errorMessage);
                        final CustomerListShipFragment customerListShipFragment3 = mCustomerListFragment.get();
                        Intrinsics.checkNotNull(customerListShipFragment3);
                        customerListShipFragment3.onCustomersLoad(null, errorMessage);
                    }
                }
            }
        };
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    /* compiled from: CustomerListShipFragment.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getEXTRA_FILTER() {
            return EXTRA_FILTER;
        }

        public String getEXTRA_CUSTOMER_SELECT_TYPE() {
            return EXTRA_CUSTOMER_SELECT_TYPE;
        }

        public String getEXTRA_SALES_CUSTOMER_CODE() {
            return EXTRA_SALES_CUSTOMER_CODE;
        }
    }
}
