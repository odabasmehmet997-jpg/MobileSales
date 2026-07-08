package com.proje.mobilesales.features.customer.view.list;

import android.R;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.zxing.integration.android.IntentIntegrator;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseListFragment;
import com.proje.mobilesales.core.enums.ColType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.SpecodeType;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.*;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
import com.proje.mobilesales.core.widget.FichePropertyEditView;
import com.proje.mobilesales.databinding.EmptyListBinding;
import com.proje.mobilesales.databinding.EmptySearchBinding;
import com.proje.mobilesales.databinding.FragmentListSwipeBinding;
import com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter;
import com.proje.mobilesales.features.cabinoperation.view.activity.BarcodeScannerCabinView;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.customer.repository.CustomerListRepository;
import com.proje.mobilesales.features.customer.view.newadd.CustomerNewActivity;
import com.proje.mobilesales.features.customer.view.route.CustomerRouteListActivity;
import com.proje.mobilesales.features.model.BarcodeCustomer;
import com.proje.mobilesales.features.model.Resource;
import com.proje.mobilesales.features.model.SpecodeGroup;
import com.proje.mobilesales.features.model.Status;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import static com.proje.mobilesales.core.utils.AppUtils.playBeepSound;

public final class CustomerListFragment extends BaseListFragment implements SearchView.OnQueryTextListener, GetLoaderSqlText {
    public static final int REQUEST_ADD_CUSTOMER = 105;
    private static final String STATE_CONST_ROUTE = "state:constRoute";
    private static final String STATE_CUSTOMER_SELECT_TYPE = "state:customerSelectType";
    private static final String STATE_CUSTOMER_TYPE = "state:customerType";
    private static final String STATE_CUSTOMER_UPDATE = "state:mCustomerUpdate";
    private static final String STATE_DAY_REF = "state:dayRef";
    private static final String STATE_FILTER = "state:filter";
    private static final String STATE_SEARCH_VIEW_EXPANDED = "state:searchViewExpanded";
    private static final String STATE_SHOW_DETAIL = "state:showDetail";
    private static final String STATE_SHOW_TITLE_TYPE = "state:showTitleType";
    private static final String STATE_SORT_TYPE = "state:sortType";
    private static final String STATE_USE_NEAR = "state:useNear";
    private static final String TAG = "CustomerListFragment";
    public static final int TRY_BARCODE_WITH_CUSTOMER = 1004;
    private FragmentListSwipeBinding _binding;
    private int cabinRef;
    private final CompositeDisposable compositeDisposable;
    private String filterCity;
    private String filterTown;
    private boolean isBarcodeSearch;
    private boolean isSelectedAllRoute;
    private ActionViewResolver mActionViewResolver;
    private CustomerRecyclerViewAdapter mAdapter;
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private boolean mConstRoute;
    private int mCustomerSelectType;
    private boolean mCustomerShowDetail;
    private int mCustomerType;
    private boolean mCustomerUpdate;
    private int mDayRef;
    private EmptySearchBinding mEmptySearchView;
    private EmptyListBinding mEmptyView;
    private String mFilter;
    private CharSequence[] mItemsRoute;
    private double mNearRpDistance;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private int mRoutePos;
    private int mRouteRef;
    private int mSearchType;
    private boolean mSearchViewExpanded;
    private int mSelectedDebitOrder;
    private int mShowTitleType;
    private int mSortType;
    private Disposable mSubscriptionGetCustomer;
    private AppBarSwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mUseNear;
    private int mUseNearType;
    private final boolean mUseOrderNameOrCode;
    private final CustomerListRepository repository;
    private SearchView searchView;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private SpecodeGroup specodeGroup;
    private final CustomerListViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_FILTER = CustomerListFragment.class.getName() + ".EXTRA_FILTER";
    private static final String EXTRA_CUSTOMER_SELECT_TYPE = CustomerListFragment.class.getName() + ".EXTRA_CUSTOMER_SELECT_TYPE";

    public boolean onQueryTextSubmit(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return false;
    }

    public CustomerListFragment() {
        CustomerListRepository customerListRepository = new CustomerListRepository();
        this.repository = customerListRepository;
        this.viewModel = new CustomerListViewModel(customerListRepository);
        this.mAdapter = new CustomerRecyclerViewAdapter();
        this.mConstRoute = true;
        this.specodeGroup = new SpecodeGroup(null, null, null, null, null, 31, null);
        this.compositeDisposable = new CompositeDisposable();
    }

    private FragmentListSwipeBinding getBinding() {
        FragmentListSwipeBinding fragmentListSwipeBinding = this._binding;
        Intrinsics.checkNotNull(fragmentListSwipeBinding);
        return fragmentListSwipeBinding;
    }

    public AppBarSwipeRefreshLayout getMSwipeRefreshLayout() {
        return this.mSwipeRefreshLayout;
    }

    public void setMSwipeRefreshLayout(AppBarSwipeRefreshLayout appBarSwipeRefreshLayout) {
        this.mSwipeRefreshLayout = appBarSwipeRefreshLayout;
    }

    public CustomerRecyclerViewAdapter getMAdapter() {
        return this.mAdapter;
    }

    public void setMAdapter(CustomerRecyclerViewAdapter customerRecyclerViewAdapter) {
        Intrinsics.checkNotNullParameter(customerRecyclerViewAdapter, "<set-?>");
        this.mAdapter = customerRecyclerViewAdapter;
    }

    public SharedPreferencesHelper getSharedPreferencesHelper() {
        return this.sharedPreferencesHelper;
    }

    public void setSharedPreferencesHelper(SharedPreferencesHelper sharedPreferencesHelper) {
        this.sharedPreferencesHelper = sharedPreferencesHelper;
    }

    public ActionViewResolver getMActionViewResolver() {
        return this.mActionViewResolver;
    }

    public void setMActionViewResolver(ActionViewResolver actionViewResolver) {
        this.mActionViewResolver = actionViewResolver;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
        if (alertDialogBuilder != null) {
            return alertDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAlertDialogBuilder");
        return null;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        Intrinsics.checkNotNullParameter(alertDialogBuilder, "<set-?>");
        this.mAlertDialogBuilder = alertDialogBuilder;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder != null) {
            return progressDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
        return null;
    }

    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        this.mProgressDialogBuilder = progressDialogBuilder;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Context requireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMProgressDialogBuilder(new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity));
        Context requireContext2 = requireContext();
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMAlertDialogBuilder(new AlertDialogBuilder.Impl(requireContext2, (BaseInjectableActivity) activity2));
        this.mActionViewResolver = new ActionViewResolver();
        this.sharedPreferencesHelper = new SharedPreferencesHelper(getContext());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentListSwipeBinding.inflate(inflater, viewGroup, false);
        this.mSwipeRefreshLayout = getBinding().swipeLayout;
        this.mRecyclerView = getBinding().rcwList;
        EmptyListBinding empty = getBinding().empty;
        Intrinsics.checkNotNullExpressionValue(empty, "empty");
        this.mEmptyView = empty;
        EmptySearchBinding emptySearch = getBinding().emptySearch;
        Intrinsics.checkNotNullExpressionValue(emptySearch, "emptySearch");
        this.mEmptySearchView = emptySearch;
        EmptyListBinding emptyListBinding = this.mEmptyView;
        if (emptyListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
            emptyListBinding = null;
        }
        emptyListBinding.getRoot().setVisibility(4);
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        appBarSwipeRefreshLayout.setColorSchemeResources(R.color.white);
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = this.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
        appBarSwipeRefreshLayout2.setProgressBackgroundColorSchemeResource(R.color.redA200);
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout3 = this.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout3);
        appBarSwipeRefreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public   void CustomerListFragmentExternalSyntheticLambda14() {
            }
            public void onRefresh() {
                CustomerListFragment.onCreateViewlambda0(CustomerListFragment.this);
            }
        });
        FrameLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public static void onCreateViewlambda0(CustomerListFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.getOnlyChangeCustomer();
    }

    private Unit getOnlyChangeCustomer() {
        this.viewModel.getCheckRemoteWorkTimeControl(WorkTimeControlProcessType.TransferGet, new ResponseListener<String>() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentonlyChangeCustomer1
            void CustomerListFragmentonlyChangeCustomer1() {
            }
            public void onResponse(PrintSlipModel str) {
                CustomerListViewModel customerListViewModel;
                CustomerListFragmentcustomerOnlineUpdateListener1 customerOnlineUpdateListener;
                if (!TextUtils.isEmpty(str) || !ContextUtils.checkConnectionWithoutMessage()) {
                    CustomerListFragment.this.onOnlineCustomerUpdateDone(false, str);
                    return;
                }
                CustomerListFragment.this.getMProgressDialogBuilder().setMessage(CustomerListFragment.this.getString(R.string.str_customer_update_please_wait)).show();
                customerListViewModel = CustomerListFragment.this.viewModel;
                CustomerListFragment customerListFragment = CustomerListFragment.this;
                customerOnlineUpdateListener = customerListFragment.customerOnlineUpdateListener(customerListFragment);
                customerListViewModel.getOnlineUpdateCustomer(customerOnlineUpdateListener, -1);
            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.e("CustomerListFragment", "onlyChangeCustomer");
            }
        });
        return Unit.INSTANCE;
    }
     public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.mFilter = bundle.getString(STATE_FILTER);
            this.mCustomerShowDetail = bundle.getBoolean(STATE_SHOW_DETAIL);
            this.mSearchViewExpanded = bundle.getBoolean(STATE_SEARCH_VIEW_EXPANDED);
            this.mUseNearType = bundle.getInt(STATE_USE_NEAR);
            this.mCustomerType = bundle.getInt(STATE_CUSTOMER_TYPE);
            this.mDayRef = bundle.getInt(STATE_DAY_REF);
            this.mConstRoute = bundle.getBoolean(STATE_CONST_ROUTE);
            this.mCustomerSelectType = bundle.getInt(STATE_CUSTOMER_SELECT_TYPE);
            this.mCustomerUpdate = bundle.getBoolean(STATE_CUSTOMER_UPDATE, false);
            int i2 = bundle.getInt(STATE_SORT_TYPE, this.mSortType);
            this.mSortType = i2;
            this.mShowTitleType = bundle.getInt(STATE_SHOW_TITLE_TYPE, i2);
        } else {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            this.mFilter = arguments.getString(EXTRA_FILTER);
            this.mUseNearType = this.viewModel.getBaseErp().getUseNearRp();
            this.mCustomerType = this.viewModel.getBaseErp().getCustomerAccountType();
            this.mSearchType = this.viewModel.getBaseErp().getSearchType();
            this.mShowTitleType = this.viewModel.getBaseErp().getClCardTitleShowType();
            this.mSortType = this.viewModel.getBaseErp().getCustomerSortType() > -1 ? this.viewModel.getBaseErp().getCustomerSortType() : this.viewModel.getBaseErp().getSortListType();
            if (this.mUseNearType != 1) {
                this.mUseNear = true;
            }
            this.mNearRpDistance = this.viewModel.getBaseErp().getUseNearDistance();
            int i3 = this.mCustomerType;
            if (i3 == 1) {
                SharedPreferencesHelper sharedPreferencesHelper = this.sharedPreferencesHelper;
                Intrinsics.checkNotNull(sharedPreferencesHelper);
                this.mDayRef = StringUtils.convertStringToInt(sharedPreferencesHelper.getUserCustomerSelectType());
                this.mConstRoute = this.viewModel.getBaseErp().getOffRoadVisit();
            } else if (i3 == 2) {
                SharedPreferencesHelper sharedPreferencesHelper2 = this.sharedPreferencesHelper;
                Intrinsics.checkNotNull(sharedPreferencesHelper2);
                this.mRouteRef = StringUtils.convertStringToInt(sharedPreferencesHelper2.getUserCustomerSelectType());
                SharedPreferencesHelper sharedPreferencesHelper3 = this.sharedPreferencesHelper;
                Intrinsics.checkNotNull(sharedPreferencesHelper3);
                this.mRoutePos = StringUtils.convertStringToInt(sharedPreferencesHelper3.getUserCustomerSelectRoute());
            }
            Bundle arguments2 = getArguments();
            Intrinsics.checkNotNull(arguments2);
            int i4 = arguments2.getInt(EXTRA_CUSTOMER_SELECT_TYPE);
            this.mCustomerSelectType = i4;
            this.mCustomerShowDetail = i4 != 1 && i4 != 2 && this.viewModel.getBaseErp().getCustomerShowDetail();
            this.mSearchViewExpanded = false;
        }
        this.mAdapter.setActivity(getActivity());
        this.mAdapter.setCustomerSelectType(this.mCustomerSelectType);
        if (!this.mConstRoute) {
            this.mDayRef = this.viewModel.getBaseErp().getDayOfWeek();
            SharedPreferencesHelper sharedPreferencesHelper4 = this.sharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper4);
            sharedPreferencesHelper4.saveUserCustomerSelectType(String.valueOf(this.mDayRef));
        } else {
            SharedPreferencesHelper sharedPreferencesHelper5 = this.sharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper5);
            sharedPreferencesHelper5.saveUserCustomerSelectType("-1");
        }
        restartLoader();
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, com.proje.mobilesales.core.base.BaseFragment
    protected void createOptionsMenu(Menu menu, MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem findItem = menu.findItem(R.id.menu_search);
        Intrinsics.checkNotNullExpressionValue(findItem, "findItem(...)");
        createSearchView(findItem);
        if (this.mAdapter.getItemCount() > -1) {
            inflater.inflate(R.menu.menu_customer_list, menu);
            super.createOptionsMenu(menu, inflater);
        }
    }

    @Override // com.proje.mobilesales.core.base.BaseFragment
    protected void prepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        if (this.mAdapter.getItemCount() > -1) {
            if (this.viewModel.getBaseErp().getRouteNewSystem()) {
                menu.findItem(R.id.menu_filter_debit).setVisible(false);
                menu.findItem(R.id.menu_filter_day).setVisible(false);
                menu.findItem(R.id.menu_near_rp).setVisible(false);
                menu.findItem(R.id.menu_sort).setVisible(false);
                menu.findItem(R.id.menu_selected_route).setVisible(this.viewModel.getBaseErp().getOffRoadVisit());
                menu.findItem(R.id.menu_selected_allroute).setVisible(this.viewModel.getBaseErp().getOffRoadVisit());
                menu.findItem(R.id.menu_follow_route).setVisible(true);
            } else {
                controlCustomerType(menu);
                controlNearType(menu);
                menu.findItem(R.id.menu_new_customer).setVisible(this.viewModel.getBaseErp().isNewCustomer() && this.viewModel.getBaseErp().getAddNewCustomer());
                controlCustomerSelectType(menu);
            }
            menu.findItem(R.id.menu_filter_specode).setVisible(this.viewModel.erpType() != ErpType.NETSIS);
            super.prepareOptionsMenu(menu);
        }
    }

    private void controlCustomerSelectType(Menu menu) {
        int i2 = this.mCustomerSelectType;
        if (i2 == 1 || i2 == 2) {
            menu.findItem(R.id.menu_barcode).setVisible(false);
        }
    }

    private void controlNearType(Menu menu) {
        if (this.mUseNearType == 0) {
            menu.findItem(R.id.menu_near_rp).setVisible(false);
            return;
        }
        int i2 = this.mUseNear ? R.string.str_show_all_rp : R.string.str_show_near_rp_gps;
        menu.findItem(R.id.menu_near_rp).setVisible(true);
        menu.findItem(R.id.menu_near_rp).setTitle(i2);
    }

    private void controlCustomerType(Menu menu) {
        int i2 = this.mCustomerType;
        if (i2 == 1) {
            menu.findItem(R.id.menu_filter_day).setVisible(this.mConstRoute);
        } else {
            if (i2 != 2) {
                return;
            }
            menu.findItem(R.id.menu_filter_route).setVisible(true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        int itemId = item.getItemId();
        if (itemId == R.id.menu_near_rp) {
            boolean z = this.mUseNear;
            this.mUseNear = !z;
            if (!z) {
                item.setTitle(getString(R.string.str_show_all_rp));
            } else {
                item.setTitle(getString(R.string.str_show_near_rp_gps));
            }
            restartLoader();
        } else if (itemId == R.id.menu_new_customer) {
            ProgressDialogBuilder<?> mProgressDialogBuilder = getMProgressDialogBuilder();
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
            this.viewModel.getCheckRemoteWorkTimeControl(WorkTimeControlProcessType.DataEntry, checkWorkTimeListener(this));
        } else if (itemId != R.id.menu_sort) {
            switch (itemId) {
                case R.id.menu_filter_city_town /* 2131297346 */:
                    filterCityTown();
                    break;
                case R.id.menu_filter_day /* 2131297347 */:
                    getDays();
                    break;
                case R.id.menu_filter_debit /* 2131297348 */:
                    filterDebit();
                    break;
                case R.id.menu_filter_route /* 2131297349 */:
                    getRoutes();
                    break;
                case R.id.menu_filter_specode /* 2131297350 */:
                    filterSpecodes();
                    break;
            }
        } else {
            openSortCustomerMenuOptions();
        }
        switch (item.getItemId()) {
            case R.id.menu_barcode /* 2131297297 */:
                scanBarcodeFromFragment();
                break;
            case R.id.menu_follow_route /* 2131297351 */:
                startActivity(new Intent(requireActivity(), CustomerRouteListActivity.class));
                break;
            case R.id.menu_selected_allroute /* 2131297398 */:
                this.isSelectedAllRoute = true;
                restartLoader();
                break;
            case R.id.menu_selected_route /* 2131297399 */:
                this.isSelectedAllRoute = false;
                restartLoader();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private CustomerListFragmentcheckWorkTimeListener1 checkWorkTimeListener(CustomerListFragment customerListFragment) {
        return new ResponseListener<String>(customerListFragment) { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentcheckWorkTimeListener1
            private final WeakReference<CustomerListFragment> mFragment;

            CustomerListFragmentcheckWorkTimeListener1(CustomerListFragment customerListFragment2) {
                this.mFragment = new WeakReference<>(customerListFragment2);
            } 
            public void onResponse(PrintSlipModel str) {
                if (this.mFragment.get() != null) {
                    CustomerListFragment customerListFragment2 = this.mFragment.get();
                    Intrinsics.checkNotNull(customerListFragment2);
                    if (customerListFragment2.isAttached()) {
                        CustomerListFragment customerListFragment3 = this.mFragment.get();
                        Intrinsics.checkNotNull(customerListFragment3);
                        customerListFragment3.getMProgressDialogBuilder().dismiss();
                        if (!TextUtils.isEmpty(str)) {
                            CustomerListFragment customerListFragment4 = this.mFragment.get();
                            Intrinsics.checkNotNull(customerListFragment4);
                            Toast.makeText(customerListFragment4.getActivity(), str, 0).show();
                        } else {
                            CustomerListFragment customerListFragment5 = this.mFragment.get();
                            Intrinsics.checkNotNull(customerListFragment5);
                            CustomerListFragment customerListFragment6 = this.mFragment.get();
                            Intrinsics.checkNotNull(customerListFragment6);
                            customerListFragment5.startActivityForResult(new Intent(customerListFragment6.getActivity(), CustomerNewActivity.class), 105);
                        }
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mFragment.get() != null) {
                    CustomerListFragment customerListFragment2 = this.mFragment.get();
                    Intrinsics.checkNotNull(customerListFragment2);
                    if (customerListFragment2.isAttached()) {
                        CustomerListFragment customerListFragment3 = this.mFragment.get();
                        Intrinsics.checkNotNull(customerListFragment3);
                        customerListFragment3.getMProgressDialogBuilder().dismiss();
                    }
                }
            }
        };
    }

    private void createImageSingleAlertCursorSales(View view, EditText editText, @StringRes int i2, @StringRes int i3, String str, @StringRes int i4) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda0
            public final String f1;
            public final int f2;
            public final int f3;
            public final int f4;
            public final EditText f5;

            public CustomerListFragmentExternalSyntheticLambda0(String str2, int i32, int i22, int i42, EditText editText2) {
                r2 = str2;
                r3 = i32;
                r4 = i22;
                r5 = i42;
                r6 = editText2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                CustomerListFragment.createImageSingleAlertCursorSaleslambda1(CustomerListFragment.this, r2, r3, r4, r5, r6, view2);
            }
        });
    }

    public static void createImageSingleAlertCursorSaleslambda1(CustomerListFragment this0, String query, int i2, int i3, int i4, EditText showView, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(showView, "showView");
        try {
            this0.getMAlertDialogBuilder().dismiss();
            Cursor query2 = this0.viewModel.getBaseErp().getLogoSqlBriteDatabase().query(query);
            if (this0.controlCustomer(query2, i2)) {
                return;
            }
            String string = this0.getString(i3);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            Intrinsics.checkNotNull(query2);
            this0.createCursorAlertDialog(string, query2, i4, showView);
        } catch (Exception e2) {
            Log.e(TAG, "createSingleAlertCursorSales: ", e2);
        }
    }

    private boolean controlCustomer(Cursor cursor, int i2) {
        if (cursor != null && cursor.getCount() != 0) {
            return false;
        }
        if (i2 != -1) {
            Toast.makeText(getActivity(), getString(i2), 0).show();
            return true;
        }
        Toast.makeText(getActivity(), getString(R.string.empty_text), 0).show();
        return true;
    }

    private void createCursorAlertDialog(String str, Cursor cursor, int i2, EditText editText) {
        getMAlertDialogBuilder().setTitle(str).setSingleChoiceItems(cursor, -1, getString(i2), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda21
            public final Cursor f0;
            public final EditText f1;
            public final CustomerListFragment f2;

            public CustomerListFragmentExternalSyntheticLambda21(Cursor cursor2, EditText editText2, CustomerListFragment this) {
                r1 = cursor2;
                r2 = editText2;
                r3 = this;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                CustomerListFragment.createCursorAlertDialoglambda2(r1, r2, r3, dialogInterface, i3);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda22
            public final Cursor f0;

            public CustomerListFragmentExternalSyntheticLambda22(Cursor cursor2) {
                r1 = cursor2;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                CustomerListFragment.createCursorAlertDialoglambda3(r1, dialogInterface, i3);
            }
        }).create().show();
    }

    public static void createCursorAlertDialoglambda2(Cursor mCursor, EditText showView, CustomerListFragment this0, DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(mCursor, "mCursor");
        Intrinsics.checkNotNullParameter(showView, "showView");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (mCursor.moveToPosition(i2)) {
            showView.setText(mCursor.getString(mCursor.getColumnIndex(this0.getString(R.string.column_code))));
            showView.requestFocus();
            showView.setSelection(showView.getText().length());
        }
        mCursor.close();
        dialog.dismiss();
    }

    public static void createCursorAlertDialoglambda3(Cursor mCursor, DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(mCursor, "mCursor");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (!mCursor.isClosed()) {
            mCursor.close();
        }
        dialog.dismiss();
    }

    private void filterSpecodes() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_specodes_filter, null);
        ArrayList arrayList = new ArrayList();
        SpecodeType[] values = SpecodeType.values();
        List listOf = CollectionsKt.listOf((Object[]) new String[]{this.specodeGroup.getSpecode1(), this.specodeGroup.getSpecode2(), this.specodeGroup.getSpecode3(), this.specodeGroup.getSpecode4(), this.specodeGroup.getSpecode5()});
        int length = values.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i4 < length) {
            SpecodeType specodeType = values[i4];
            int i5 = i3 + 1;
            FichePropertyEditView fichePropertyEditView = inflate.findViewById(specodeType.getViewId());
            EditText editText = fichePropertyEditView.findViewById(R.id.edt_fiche_property_edit);
            LinearLayout linearLayout = fichePropertyEditView.findViewById(R.id.ln_fiche_property);
            CharSequence charSequence = (String) CollectionsKt.getOrNull(listOf, i3);
            if (charSequence == null) {
                charSequence = "";
            }
            editText.setText(charSequence);
            editText.setLayoutParams(new LinearLayout.LayoutParams(i2, -1, 0.5f));
            ImageButton imageButton = new ImageButton(getContext());
            imageButton.setImageResource(R.drawable.ic_magnify_black_18dp);
            imageButton.setBackgroundColor(i2);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, -1, 0.05f);
            layoutParams.rightMargin = 5;
            imageButton.setLayoutParams(layoutParams);
            linearLayout.addView(imageButton);
            String formatStringEnglish = ContextUtils.formatStringEnglish(R.string.qry_distinct_clcard_speCode_filter, specodeType.getQuerySuffix());
            Intrinsics.checkNotNull(editText);
            int titleRes = specodeType.getTitleRes();
            Intrinsics.checkNotNull(formatStringEnglish);
            createImageSingleAlertCursorSales(imageButton, editText, titleRes, -1, formatStringEnglish, R.string.column_code);
            arrayList.add(editText);
            i4++;
            i3 = i5;
            i2 = 0;
        }
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        AlertDialog create = new AlertDialog.Builder(context).setView(inflate).setPositiveButton(R.string.ok, null).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i6) {
                dialogInterface.dismiss();
            }
        }).setNeutralButton(R.string.str_all, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda8
            public final List f0;
            public final CustomerListFragment f1;

            public CustomerListFragmentExternalSyntheticLambda8(List arrayList2, CustomerListFragment this) {
                r1 = arrayList2;
                r2 = this;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i6) {
                CustomerListFragment.filterSpecodeslambda9(r1, r2, dialogInterface, i6);
            }
        }).create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda9
            public final List f0;
            public final CustomerListFragment f1;

            public CustomerListFragmentExternalSyntheticLambda9(List arrayList2, CustomerListFragment this) {
                r1 = arrayList2;
                r2 = this;
            }

            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                CustomerListFragment.filterSpecodeslambda12(r1, r2, dialogInterface);
            }
        });
        create.show();
        Window window = create.getWindow();
        if (window != null) {
            window.clearFlags(131080);
        }
        Window window2 = create.getWindow();
        if (window2 != null) {
            window2.setSoftInputMode(4);
        }
    }

    public static void filterSpecodeslambda12(List editTextList, CustomerListFragment this0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(editTextList, "editTextList");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNull(dialogInterface, "null cannot be cast to non-null type androidx.appcompat.app.AlertDialog");
        ((AlertDialog) dialogInterface).getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda23
            public final List f0;
            public final CustomerListFragment f1;
            public final DialogInterface f2;

            public CustomerListFragmentExternalSyntheticLambda23(List editTextList2, CustomerListFragment this02, DialogInterface dialogInterface2) {
                r1 = editTextList2;
                r2 = this02;
                r3 = dialogInterface2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomerListFragment.filterSpecodeslambda12lambda11(r1, r2, r3, view);
            }
        });
    }

    private void filterCityTown() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_citytown_filter, null);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(inflate);
        builder.setPositiveButton(R.string.ok, null);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                CustomerListFragment.filterCityTownlambda13(dialogInterface, i2);
            }
        });
        builder.setNeutralButton(R.string.str_all, null);
        AlertDialog create = builder.create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda5
            public final AlertDialog f1;

            public CustomerListFragmentExternalSyntheticLambda5(AlertDialog create2) {
                r2 = create2;
            }

            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                CustomerListFragment.filterCityTownlambda16(CustomerListFragment.this, r2, dialogInterface);
            }
        });
        create2.show();
        Window window = create2.getWindow();
        Intrinsics.checkNotNull(window);
        window.clearFlags(131080);
        Window window2 = create2.getWindow();
        Intrinsics.checkNotNull(window2);
        window2.setSoftInputMode(4);
    }

    public static void filterCityTownlambda13(DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    public static void filterCityTownlambda16(CustomerListFragment this0, AlertDialog dialog, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        AlertDialog alertDialog = (AlertDialog) dialogInterface;
        Button button = alertDialog.getButton(-1);
        EditText editText = alertDialog.findViewById(R.id.edtCity);
        Intrinsics.checkNotNull(editText);
        editText.setText(TextUtils.isEmpty(this0.filterCity) ? "" : this0.filterCity);
        EditText editText2 = alertDialog.findViewById(R.id.edtTown);
        Intrinsics.checkNotNull(editText2);
        editText2.setText(TextUtils.isEmpty(this0.filterTown) ? "" : this0.filterTown);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda11
            public final AlertDialog f1;
            public final DialogInterface f2;

            public CustomerListFragmentExternalSyntheticLambda11(AlertDialog dialog2, DialogInterface dialogInterface2) {
                r2 = dialog2;
                r3 = dialogInterface2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomerListFragment.filterCityTownlambda16lambda14(CustomerListFragment.this, r2, r3, view);
            }
        });
        alertDialog.getButton(-3).setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda12
            public final DialogInterface f1;

            public CustomerListFragmentExternalSyntheticLambda12(DialogInterface dialogInterface2) {
                r2 = dialogInterface2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomerListFragment.filterCityTownlambda16lambda15(CustomerListFragment.this, r2, view);
            }
        });
    }

    public static void filterCityTownlambda16lambda14(CustomerListFragment this0, AlertDialog dialog, DialogInterface dialogInterface, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        EditText editText = dialog.findViewById(R.id.edtCity);
        Intrinsics.checkNotNull(editText);
        this0.filterCity = editText.getText().toString();
        EditText editText2 = dialog.findViewById(R.id.edtTown);
        Intrinsics.checkNotNull(editText2);
        this0.filterTown = editText2.getText().toString();
        dialogInterface.dismiss();
        this0.restartLoader();
    }

    public static void filterCityTownlambda16lambda15(CustomerListFragment this0, DialogInterface dialogInterface, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        this0.filterCity = "";
        this0.filterTown = "";
        dialogInterface.dismiss();
        this0.restartLoader();
    }

    private void filterDebit() {
        getMAlertDialogBuilder().setTitle(R.string.menu_filter_debit).setSingleChoice(R.array.sort_by_debt, this.mSelectedDebitOrder, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda18
            public CustomerListFragmentExternalSyntheticLambda18() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                CustomerListFragment.filterDebitlambda17(CustomerListFragment.this, dialogInterface, i2);
            }
        }).create().show();
    }

    public static void filterDebitlambda17(CustomerListFragment this0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mSelectedDebitOrder = i2;
        this0.restartLoader();
        dialogInterface.dismiss();
    }

    private void openSortCustomerMenuOptions() {
        getMAlertDialogBuilder().setTitle(R.string.menu_sort).setSingleChoice(R.array.customer_sort_type, this.mSortType, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda10
            public CustomerListFragmentExternalSyntheticLambda10() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                CustomerListFragment.openSortCustomerMenuOptionslambda18(CustomerListFragment.this, dialogInterface, i2);
            }
        }).create().show();
    }

    public static void openSortCustomerMenuOptionslambda18(CustomerListFragment this0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        this0.mSortType = i2;
        this0.viewModel.getSaveCustomerSortType(i2);
        this0.restartLoader();
        dialogInterface.dismiss();
    }

    private Unit getDays() {
        String string = getString(R.string.str_all);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = getString(R.string.str_monday);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = getString(R.string.str_tuesday);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String string4 = getString(R.string.str_wednesday);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        String string5 = getString(R.string.str_thursday);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        String string6 = getString(R.string.str_friday);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
        String string7 = getString(R.string.str_saturday);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
        String string8 = getString(R.string.str_sunday);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
        CharSequence[] charSequenceArr = {string, string2, string3, string4, string5, string6, string7, string8};
        getMAlertDialogBuilder().setTitle(R.string.str_day).setSingleChoice(charSequenceArr, this.mDayRef, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda6
            public final CharSequence[] f1;

            public CustomerListFragmentExternalSyntheticLambda6(CharSequence[] charSequenceArr2) {
                r2 = charSequenceArr2;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                CustomerListFragment._get_days_lambda19(CustomerListFragment.this, r2, dialogInterface, i2);
            }
        }).create().show();
        return Unit.INSTANCE;
    }

    public static void _get_days_lambda19(CustomerListFragment this0, CharSequence[] itemsDay, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(itemsDay, "itemsDay");
        this0.mDayRef = this0.getDayRef(itemsDay[i2].toString());
        SharedPreferencesHelper sharedPreferencesHelper = this0.sharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper);
        sharedPreferencesHelper.saveUserCustomerSelectType(String.valueOf(this0.mDayRef));
        this0.restartLoader();
        dialogInterface.dismiss();
    }

    private int getDayRef(String str) {
        if (Intrinsics.areEqual(str, getString(R.string.str_monday))) {
            return 1;
        }
        if (Intrinsics.areEqual(str, getString(R.string.str_tuesday))) {
            return 2;
        }
        if (Intrinsics.areEqual(str, getString(R.string.str_wednesday))) {
            return 3;
        }
        if (Intrinsics.areEqual(str, getString(R.string.str_thursday))) {
            return 4;
        }
        if (Intrinsics.areEqual(str, getString(R.string.str_friday))) {
            return 5;
        }
        if (Intrinsics.areEqual(str, getString(R.string.str_saturday))) {
            return 6;
        }
        return Intrinsics.areEqual(str, getString(R.string.str_sunday)) ? 7 : -1;
    }

    private Unit getRoutes() {
        this.mItemsRoute = null;
        Cursor rowQueryInReadableDatabase = this.viewModel.getRowQueryInReadableDatabase("SELECT DEFINITION_ FROM ROUTE", null);
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.str_all) + ';');
        int count = rowQueryInReadableDatabase.getCount();
        for (int i2 = 0; i2 < count; i2++) {
            if (rowQueryInReadableDatabase.moveToPosition(i2)) {
                StringBuilder sb2 = new StringBuilder();
                Object dBVal = this.viewModel.getDBVal(rowQueryInReadableDatabase, "DEFINITION_", ColType.metin);
                Intrinsics.checkNotNull(dBVal, "null cannot be cast to non-null type kotlin.String");
                sb2.append((String) dBVal);
                sb2.append(';');
                sb.append(sb2);
            }
        }
        String sb3 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb3, "toString(...)");
        this.mItemsRoute = (CharSequence[]) StringsKt.splitdefault((CharSequence) sb3, new String[]{";"}, false, 0, 6, (Object) null).toArray(new CharSequence[0]);
        getMAlertDialogBuilder().setTitle(R.string.str_routes).setSingleChoice(this.mItemsRoute, this.mRoutePos, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda1
            public CustomerListFragmentExternalSyntheticLambda1() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                CustomerListFragment._get_routes_lambda20(CustomerListFragment.this, dialogInterface, i3);
            }
        }).create().show();
        return Unit.INSTANCE;
    }

    public static void _get_routes_lambda20(CustomerListFragment this0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        CharSequence[] charSequenceArr = this0.mItemsRoute;
        Intrinsics.checkNotNull(charSequenceArr);
        this0.mRouteRef = this0.getRouteRef(charSequenceArr[i2].toString());
        SharedPreferencesHelper sharedPreferencesHelper = this0.sharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper);
        sharedPreferencesHelper.saveUserCustomerSelectType(String.valueOf(this0.mRouteRef));
        SharedPreferencesHelper sharedPreferencesHelper2 = this0.sharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper2);
        sharedPreferencesHelper2.saveUserCustomerSelectRoute(String.valueOf(this0.mRoutePos));
        this0.restartLoader();
        dialogInterface.dismiss();
    }

    private int getRouteRef(String str) {
        Exception e2;
        String str2;
        String str3 = "-1";
        this.mRoutePos = 0;
        Cursor cursor = null;
        try {
            try {
                cursor = this.viewModel.getRowQueryInReadableDatabase("SELECT LOGICALREF, ROWID AS POS  FROM ROUTE WHERE DEFINITION_='" + str + '\'', null);
                if (cursor.moveToPosition(0)) {
                    Object dBVal = this.viewModel.getDBVal(cursor, "LOGICALREF", ColType.metin);
                    Intrinsics.checkNotNull(dBVal, "null cannot be cast to non-null type kotlin.String");
                    str2 = (String) dBVal;
                    try {
                        Object dBVal2 = this.viewModel.getDBVal(cursor, "POS", ColType.sayi);
                        Intrinsics.checkNotNull(dBVal2, "null cannot be cast to non-null type kotlin.Int");
                        this.mRoutePos = ((Integer) dBVal2).intValue();
                        str3 = str2;
                    } catch (Exception e3) {
                        e2 = e3;
                        e2.printStackTrace();
                        if (cursor != null) {
                            cursor.close();
                        }
                        return StringUtils.convertStringToInt(str2);
                    }
                }
                cursor.close();
                return StringUtils.convertStringToInt(str3);
            } catch (Exception e4) {
                e2 = e4;
                str2 = "-1";
            } catch (Throwable unused) {
                if (cursor != null) {
                }
                return StringUtils.convertStringToInt(str3);
            }
        } catch (Throwable unused2) {
            str3 = str;
            if (cursor != null) {
                cursor.close();
            }
            return StringUtils.convertStringToInt(str3);
        }
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putString(STATE_FILTER, this.mFilter);
        outState.putBoolean(STATE_SEARCH_VIEW_EXPANDED, this.mSearchViewExpanded);
        outState.putBoolean(STATE_SHOW_DETAIL, this.mCustomerShowDetail);
        outState.putInt(STATE_USE_NEAR, this.mUseNearType);
        outState.putInt(STATE_CUSTOMER_TYPE, this.mCustomerType);
        outState.putInt(STATE_DAY_REF, this.mDayRef);
        outState.putInt(STATE_CUSTOMER_SELECT_TYPE, this.mCustomerSelectType);
        outState.putBoolean(STATE_CUSTOMER_UPDATE, this.mCustomerUpdate);
        outState.putInt(STATE_SORT_TYPE, this.mSortType);
        outState.putInt(STATE_SHOW_TITLE_TYPE, this.mShowTitleType);
    }

    private void createSearchView(MenuItem menuItem) {
        Drawable textCursorDrawable;
        ActionViewResolver actionViewResolver = this.mActionViewResolver;
        Intrinsics.checkNotNull(actionViewResolver);
        View actionView = actionViewResolver.getActionView(menuItem);
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        SearchView searchView = (SearchView) actionView;
        this.searchView = searchView;
        Intrinsics.checkNotNull(searchView);
        searchView.setQueryHint(getString(R.string.hint_search_customer));
        SearchView searchView2 = this.searchView;
        Intrinsics.checkNotNull(searchView2);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        Object systemService = activity.getSystemService(FirebaseAnalytics.Event.SEARCH);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.SearchManager");
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        searchView2.setSearchableInfo(((SearchManager) systemService).getSearchableInfo(activity2.getComponentName()));
        SearchView searchView3 = this.searchView;
        Intrinsics.checkNotNull(searchView3);
        searchView3.setIconified(!this.mSearchViewExpanded);
        SearchView searchView4 = this.searchView;
        Intrinsics.checkNotNull(searchView4);
        searchView4.setQuery(this.mFilter, false);
        SearchView searchView5 = this.searchView;
        Intrinsics.checkNotNull(searchView5);
        searchView5.setOnQueryTextListener(this);
        SearchView searchView6 = this.searchView;
        Intrinsics.checkNotNull(searchView6);
        View findViewById = searchView6.findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView.SearchAutoComplete");
        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) findViewById;
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.white));
        if (Build.VERSION.SDK_INT >= 29 && textCursorDrawable != null) {
            textCursorDrawable.setColorFilter(ContextCompat.getColor(requireContext(), R.color.custom_cursor_color), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setTextCursorDrawable(textCursorDrawable);
        }
        SearchView searchView7 = this.searchView;
        Intrinsics.checkNotNull(searchView7);
        searchView7.setOnSearchClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda16
            public CustomerListFragmentExternalSyntheticLambda16() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomerListFragment.createSearchViewlambda21(CustomerListFragment.this, view);
            }
        });
        SearchView searchView8 = this.searchView;
        Intrinsics.checkNotNull(searchView8);
        searchView8.setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda17
            public CustomerListFragmentExternalSyntheticLambda17() {
            }

            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            public boolean onClose() {
                boolean createSearchViewlambda22;
                createSearchViewlambda22 = CustomerListFragment.createSearchViewlambda22(CustomerListFragment.this);
                return createSearchViewlambda22;
            }
        });
    }

    public static void createSearchViewlambda21(CustomerListFragment this0, View v) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(v, "v");
        this0.mSearchViewExpanded = true;
        v.requestFocus();
    }

    public static boolean createSearchViewlambda22(CustomerListFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.clearFilter();
        return false;
    }

    private void clearFilter() {
        this.mFilter = "";
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        Intrinsics.checkNotNull(appCompatActivity);
        ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setSubtitle(this.mFilter);
        this.isBarcodeSearch = false;
        restartLoader();
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment, com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
        }
    }

    public void filter(String str) {
        this.mSearchViewExpanded = false;
        this.mFilter = str;
        restartLoader();
    }

    private void restartLoader() {
        if (ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            Disposable disposable = this.mSubscriptionGetCustomer;
            if (disposable != null) {
                Intrinsics.checkNotNull(disposable);
                disposable.dispose();
            }
            this.mAdapter.restartScroll();
            this.mAdapter.notifyDataSetChanged();
            this.mSubscriptionGetCustomer = this.viewModel.getCustomerList(getLoaderSqlText(50, 0), customerListResponseListener(this));
            this.mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda13
                public CustomerListFragmentExternalSyntheticLambda13() {
                }

                @Override // com.proje.mobilesales.core.interfaces.OnLoadMoreListener
                public void onLoadMore(int i2, int i3) {
                    CustomerListFragment.restartLoaderlambda23(CustomerListFragment.this, i2, i3);
                }
            });
        }
    }

    public static void restartLoaderlambda23(CustomerListFragment this0, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.callLoader(i2, i3);
    }

    private void callLoader(int i2, int i3) {
        this.viewModel.getCustomerList(getLoaderSqlText(i2, i3), customerListResponseListener(this));
    }

    public void onCustomersLoad(ArrayList<Customer> arrayList, String str) {
        if (this.isBarcodeSearch && arrayList != null && arrayList.size() > 0) {
            playBeepSound();
        }
        this.isBarcodeSearch = false;
        swapCursor(arrayList);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(getActivity(), str, 1).show();
    }

    private void swapCursor(List<Customer> list) {
        this.mAdapter.addItem(list);
        this.mAdapter.setDisplayOptions(this.mCustomerShowDetail);
        if (isDetached()) {
            return;
        }
        toggleEmptyView(this.mAdapter.getItemCount() == 0, this.mFilter);
    }

    private void toggleEmptyView(boolean z, String str) {
        ViewBinding viewBinding = null;
        if (z) {
            if (TextUtils.isEmpty(str)) {
                EmptySearchBinding emptySearchBinding = this.mEmptySearchView;
                if (emptySearchBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mEmptySearchView");
                    emptySearchBinding = null;
                }
                emptySearchBinding.getRoot().setVisibility(4);
                EmptyListBinding emptyListBinding = this.mEmptyView;
                if (emptyListBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
                    emptyListBinding = null;
                }
                emptyListBinding.getRoot().setVisibility(0);
                EmptyListBinding emptyListBinding2 = this.mEmptyView;
                if (emptyListBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
                } else {
                    viewBinding = emptyListBinding2;
                }
                viewBinding.getRoot().bringToFront();
                return;
            }
            EmptyListBinding emptyListBinding3 = this.mEmptyView;
            if (emptyListBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
                emptyListBinding3 = null;
            }
            emptyListBinding3.getRoot().setVisibility(4);
            EmptySearchBinding emptySearchBinding2 = this.mEmptySearchView;
            if (emptySearchBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptySearchView");
                emptySearchBinding2 = null;
            }
            emptySearchBinding2.getRoot().setVisibility(0);
            EmptySearchBinding emptySearchBinding3 = this.mEmptySearchView;
            if (emptySearchBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptySearchView");
            } else {
                viewBinding = emptySearchBinding3;
            }
            viewBinding.getRoot().bringToFront();
            return;
        }
        EmptyListBinding emptyListBinding4 = this.mEmptyView;
        if (emptyListBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
            emptyListBinding4 = null;
        }
        emptyListBinding4.getRoot().setVisibility(4);
        EmptySearchBinding emptySearchBinding4 = this.mEmptySearchView;
        if (emptySearchBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEmptySearchView");
        } else {
            viewBinding = emptySearchBinding4;
        }
        viewBinding.getRoot().setVisibility(4);
    }

    @Override // com.proje.mobilesales.core.base.BaseListFragment
    protected ListRecyclerViewAdapter<?, ?> getAdapter() {
        return this.mAdapter;
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(String newText) {
        Intrinsics.checkNotNullParameter(newText, "newText");
        if (newText.length() != 0) {
            return true;
        }
        clearFilter();
        return true;
    }

    @Override // com.proje.mobilesales.core.interfaces.GetLoaderSqlText
    public String getLoaderSqlText(int i2, int i3) {
        return this.viewModel.getCustomerListSql(getContext(), new CustomerFilter(this.mUseNearType, this.mUseNear, this.mNearRpDistance, this.mDayRef, this.mCustomerType, this.mFilter, this.mRouteRef, this.mSelectedDebitOrder, this.mUseOrderNameOrCode, this.mSortType, this.mSearchType, this.mShowTitleType, this.isSelectedAllRoute, this.filterCity, this.filterTown, this.specodeGroup)) + " LIMIT " + i2 + " OFFSET " + i3;
    }

    public CustomerListFragmentcustomerOnlineUpdateListener1 customerOnlineUpdateListener(CustomerListFragment customerListFragment) {
        return new ResponseListener<Boolean>(customerListFragment) { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentcustomerOnlineUpdateListener1
            private final WeakReference<CustomerListFragment> mCustomerListFragment;

            CustomerListFragmentcustomerOnlineUpdateListener1(CustomerListFragment customerListFragment2) {
                this.mCustomerListFragment = new WeakReference<>(customerListFragment2);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel bool) {
                if (this.mCustomerListFragment.get() != null) {
                    CustomerListFragment customerListFragment2 = this.mCustomerListFragment.get();
                    Intrinsics.checkNotNull(customerListFragment2);
                    if (customerListFragment2.isAttached()) {
                        CustomerListFragment customerListFragment3 = this.mCustomerListFragment.get();
                        Intrinsics.checkNotNull(customerListFragment3);
                        Intrinsics.checkNotNull(bool);
                        customerListFragment3.onOnlineCustomerUpdateDone(bool.booleanValue(), "");
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mCustomerListFragment.get() != null) {
                    CustomerListFragment customerListFragment2 = this.mCustomerListFragment.get();
                    Intrinsics.checkNotNull(customerListFragment2);
                    if (customerListFragment2.isAttached()) {
                        Log.d("AA", "onError: " + errorMessage);
                        CustomerListFragment customerListFragment3 = this.mCustomerListFragment.get();
                        Intrinsics.checkNotNull(customerListFragment3);
                        customerListFragment3.onOnlineCustomerUpdateDone(false, errorMessage);
                    }
                }
            }
        };
    }

    public void onOnlineCustomerUpdateDone(boolean z, String str) {
        this.mCustomerUpdate = true;
        getMProgressDialogBuilder().dismiss();
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        if (appBarSwipeRefreshLayout.isRefreshing()) {
            AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = this.mSwipeRefreshLayout;
            Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
            appBarSwipeRefreshLayout2.setRefreshing(false);
        }
        restartLoader();
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(getActivity(), str, 1).show();
        }
        if (z) {
            this.viewModel.getSaveClCardLastTransDate();
        }
    }

    private CustomerListFragmentcustomerListResponseListener1 customerListResponseListener(CustomerListFragment customerListFragment) {
        return new ResponseListener<ArrayList<Customer>>(customerListFragment) { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentcustomerListResponseListener1
            private final WeakReference<CustomerListFragment> mCustomerListFragment;

            CustomerListFragmentcustomerListResponseListener1(CustomerListFragment customerListFragment2) {
                this.mCustomerListFragment = new WeakReference<>(customerListFragment2);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel arrayList) {
                if (this.mCustomerListFragment.get() != null) {
                    CustomerListFragment customerListFragment2 = this.mCustomerListFragment.get();
                    Intrinsics.checkNotNull(customerListFragment2);
                    if (customerListFragment2.isAttached()) {
                        CustomerListFragment customerListFragment3 = this.mCustomerListFragment.get();
                        Intrinsics.checkNotNull(customerListFragment3);
                        customerListFragment3.onCustomersLoad(arrayList, "");
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mCustomerListFragment.get() != null) {
                    CustomerListFragment customerListFragment2 = this.mCustomerListFragment.get();
                    Intrinsics.checkNotNull(customerListFragment2);
                    if (customerListFragment2.isAttached()) {
                        Log.d("AA", "onError: " + errorMessage);
                        CustomerListFragment customerListFragment3 = this.mCustomerListFragment.get();
                        Intrinsics.checkNotNull(customerListFragment3);
                        customerListFragment3.onCustomersLoad(null, errorMessage);
                    }
                }
            }
        };
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 105 && i3 == -1) {
            restartLoader();
        } else if (i2 == 49374) {
            requestCodeIfResultOK(i3, intent);
            requestCodeIfBarcodeCustomer(i3, intent);
        }
    }

    private void requestCodeIfBarcodeCustomer(int i2, Intent intent) {
        if (i2 != 1004 || intent == null) {
            return;
        }
        barcodeCustomer(intent);
        restartLoader();
    }

    private void requestCodeIfResultOK(int i2, Intent intent) {
        if (i2 != -1 || intent == null) {
            return;
        }
        this.cabinRef = barcodeCustomer(intent).getCabinRef();
        restartLoaderAndOpenFirstCustomer();
    }

    private BarcodeCustomer barcodeCustomer(Intent intent) {
        BarcodeCustomer barcodeCustomer = intent.getParcelableExtra("SCAN_RESULT");
        Intrinsics.checkNotNull(barcodeCustomer);
        this.mFilter = barcodeCustomer.getCode();
        this.isBarcodeSearch = true;
        SearchView searchView = this.searchView;
        if (searchView != null) {
            Intrinsics.checkNotNull(searchView);
            searchView.setQuery(this.mFilter, false);
            SearchView searchView2 = this.searchView;
            Intrinsics.checkNotNull(searchView2);
            searchView2.setIconified(false);
            SearchView searchView3 = this.searchView;
            Intrinsics.checkNotNull(searchView3);
            searchView3.clearFocus();
        } else {
            this.mSearchViewExpanded = true;
        }
        return barcodeCustomer;
    }

    private void scanBarcodeFromFragment() {
        if (PermissionUtils.checkPermissionFromFragment(this, "android.permission.CAMERA", getString(R.string.str_camera_permission_for_barcode))) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(IntentIntegrator.ONE_D_CODE_TYPES.toString());
            arrayList.add("QR_CODE");
            IntentIntegrator.forSupportFragment(this).setBeepEnabled(false).setDesiredBarcodeFormats(arrayList).setCaptureActivity(BarcodeScannerCabinView.class).addExtra(IntentExtraName.EXTRA_CUSTOMER_BARCODE_SEARCH, Boolean.TRUE).initiateScan();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i2, String[] permissions2, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions2, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        if (i2 == 1074) {
            if (!(grantResults.length == 0) && grantResults[0] == 0) {
                scanBarcodeFromFragment();
            } else {
                Toast.makeText(getContext(), getString(R.string.str_permissions_denied), 1).show();
            }
        }
        super.onRequestPermissionsResult(i2, permissions2, grantResults);
    }

    private void restartLoaderAndOpenFirstCustomer() {
        if (ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            Disposable disposable = this.mSubscriptionGetCustomer;
            if (disposable != null) {
                Intrinsics.checkNotNull(disposable);
                disposable.dispose();
            }
            this.mAdapter.restartScroll();
            this.mAdapter.notifyDataSetChanged();
            this.mSubscriptionGetCustomer = this.viewModel.getCustomerList(getLoaderSqlText(50, 0), customerListCabinBarcodeResponseListener(this));
            this.mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda15
                public void CustomerListFragmentExternalSyntheticLambda15() {
                }
                public void onLoadMore(int i2, int i3) {
                    CustomerListFragment.restartLoaderAndOpenFirstCustomerlambda24(CustomerListFragment.this, i2, i3);
                }
            });
        }
    }

    public static void restartLoaderAndOpenFirstCustomerlambda24(CustomerListFragment this0, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.callLoader(i2, i3);
    }

    private CustomerListFragmentcustomerListCabinBarcodeResponseListener1 customerListCabinBarcodeResponseListener(CustomerListFragment customerListFragment) {
        return new ResponseListener<ArrayList<Customer>>(customerListFragment) { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentcustomerListCabinBarcodeResponseListener1
            private final WeakReference<CustomerListFragment> mCustomerListFragment;

            CustomerListFragmentcustomerListCabinBarcodeResponseListener1(CustomerListFragment customerListFragment2) {
                this.mCustomerListFragment = new WeakReference<>(customerListFragment2);
            }

            public void onResponse(PrintSlipModel arrayList) {
                if (this.mCustomerListFragment.get() != null) {
                    CustomerListFragment customerListFragment2 = this.mCustomerListFragment.get();
                    Intrinsics.checkNotNull(customerListFragment2);
                    if (customerListFragment2.isAttached()) {
                        CustomerListFragment customerListFragment3 = this.mCustomerListFragment.get();
                        Intrinsics.checkNotNull(customerListFragment3);
                        customerListFragment3.onCabinCustomersLoad(arrayList, "");
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mCustomerListFragment.get() != null) {
                    CustomerListFragment customerListFragment2 = this.mCustomerListFragment.get();
                    Intrinsics.checkNotNull(customerListFragment2);
                    if (customerListFragment2.isAttached()) {
                        Log.d("AA", "onError: " + errorMessage);
                        CustomerListFragment customerListFragment3 = this.mCustomerListFragment.get();
                        Intrinsics.checkNotNull(customerListFragment3);
                        customerListFragment3.onCabinCustomersLoad(null, errorMessage);
                    }
                }
            }
        };
    }

    public void onCabinCustomersLoad(ArrayList<Customer> arrayList, String str) {
        if (arrayList != null) {
            if (this.isBarcodeSearch && arrayList.size() > 0) {
                playBeepSound();
            }
            this.mAdapter.addItem(arrayList);
            this.mAdapter.setDisplayOptions(this.mCustomerShowDetail);
            if (!isDetached()) {
                toggleEmptyView(this.mAdapter.getItemCount() == 0, this.mFilter);
            }
            this.mAdapter.openFirstCustomer(this.cabinRef);
        } else if (!TextUtils.isEmpty(str)) {
            Toast.makeText(getActivity(), str, 1).show();
        }
        this.isBarcodeSearch = false;
    }

    public void notifyAdapterToChangeIfHasDailyOperation(int i2) {
        List<Customer> customerCursor = this.mAdapter.getCustomerCursor();
        Objects.requireNonNull(customerCursor);
        OptionalInt findFirst = IntStream.range(0, customerCursor.size()).filter(new IntPredicate() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda2
            public final int f0;
            public final CustomerListFragment f1;

            public CustomerListFragmentExternalSyntheticLambda2(int i22, CustomerListFragment this) {
                r1 = i22;
                r2 = this;
            }

            @Override // java.util.function.IntPredicate
            public boolean test(int i3) {
                boolean notifyAdapterToChangeIfHasDailyOperationlambda25;
                notifyAdapterToChangeIfHasDailyOperationlambda25 = CustomerListFragment.notifyAdapterToChangeIfHasDailyOperationlambda25(r1, r2, i3);
                return notifyAdapterToChangeIfHasDailyOperationlambda25;
            }
        }).findFirst();
        findFirst.ifPresent(new IntConsumer() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerListFragmentExternalSyntheticLambda3
            public final OptionalInt f1;
            public final int f2;

            public CustomerListFragmentExternalSyntheticLambda3(OptionalInt findFirst2, int i22) {
                r2 = findFirst2;
                r3 = i22;
            }

            @Override // java.util.function.IntConsumer
            public void accept(int i3) {
                CustomerListFragment.notifyAdapterToChangeIfHasDailyOperationlambda28(CustomerListFragment.this, r2, r3, i3);
            }
        });
    }

    public static boolean notifyAdapterToChangeIfHasDailyOperationlambda25(int i2, CustomerListFragment this0, int i3) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Customer customer = this0.mAdapter.getCustomerCursor().get(i3);
        Intrinsics.checkNotNull(customer);
        return i2 == customer.getLogicalRef();
    }

    public static void notifyAdapterToChangeIfHasDailyOperationlambda28(com.proje.mobilesales.features.customer.view.list.CustomerListFragment r1, java.util.OptionalInt r2, int r3, int r4) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.customer.view.list.CustomerListFragment.notifyAdapterToChangeIfHasDailyOperationlambda28(com.proje.mobilesales.features.customer.view.list.CustomerListFragment, java.util.OptionalInt, int, int):void");
    }

    public static void notifyAdapterToChangeIfHasDailyOperationlambda28lambda26(DailyOperationResponseListener responseListener, Resource resource) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        responseListener.onResponse((Resource<Boolean>) resource);
    }

    public static void notifyAdapterToChangeIfHasDailyOperationlambda28lambda27(DailyOperationResponseListener responseListener, Throwable throwable) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        String message = throwable.getMessage();
        Intrinsics.checkNotNull(message);
        responseListener.onError(message);
    }

    private record DailyOperationResponseListener(WeakReference<CustomerListFragment> mContent, Customer mCustomer,
                                                  int mPosition) implements ResponseListener<Resource<Boolean>> {
            /* compiled from: CustomerListFragment.kt */
            public class WhenMappings {
                public static final int[] EnumSwitchMapping0;

                static {
                    int[] iArr = new int[Status.values().length];
                    try {
                        iArr[Status.LOADING.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[Status.ERROR.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[Status.SUCCESS.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    EnumSwitchMapping0 = iArr;
                }
            }

            private DailyOperationResponseListener(CustomerListFragment mContent, Customer mCustomer, int mPosition) {
                Intrinsics.checkNotNullParameter(mContent, "mContent");
                Intrinsics.checkNotNullParameter(mCustomer, "customer");
                this.mContent = new WeakReference<>(mContent);
                this.mPosition = mPosition;
                this.mCustomer = mCustomer;
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel resource) {
                if (this.mContent.get() == null) {
                    return;
                }
                Intrinsics.checkNotNull(resource);
                int i2 = WhenMappings.EnumSwitchMapping0[resource.getStatus().ordinal()];
                if (i2 == 1) {
                    CustomerListFragment customerListFragment = this.mContent.get();
                    Intrinsics.checkNotNull(customerListFragment);
                    ProgressDialogBuilder<?> mProgressDialogBuilder = customerListFragment.getMProgressDialogBuilder();
                    CustomerListFragment customerListFragment2 = this.mContent.get();
                    Intrinsics.checkNotNull(customerListFragment2);
                    mProgressDialogBuilder.setMessage(customerListFragment2.getString(R.string.str_please_wait)).show();
                    return;
                }
                if (i2 == 2) {
                    CustomerListFragment customerListFragment3 = this.mContent.get();
                    Intrinsics.checkNotNull(customerListFragment3);
                    customerListFragment3.getMProgressDialogBuilder().dismiss();
                    CustomerListFragment customerListFragment4 = this.mContent.get();
                    Intrinsics.checkNotNull(customerListFragment4);
                    Toast.makeText(customerListFragment4.getContext(), resource.getException(), 0).show();
                    return;
                }
                if (i2 != 3) {
                    return;
                }
                if (resource.getData() != null) {
                    this.mCustomer.setHasDailyOperation(resource.getData().booleanValue());
                    CustomerListFragment customerListFragment5 = this.mContent.get();
                    Intrinsics.checkNotNull(customerListFragment5);
                    customerListFragment5.getMAdapter().notifyItemChanged(this.mPosition);
                }
                CustomerListFragment customerListFragment6 = this.mContent.get();
                Intrinsics.checkNotNull(customerListFragment6);
                customerListFragment6.getMProgressDialogBuilder().dismiss();
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mContent.get() == null) {
                    return;
                }
                CustomerListFragment customerListFragment = this.mContent.get();
                Intrinsics.checkNotNull(customerListFragment);
                customerListFragment.getMProgressDialogBuilder().dismiss();
                CustomerListFragment customerListFragment2 = this.mContent.get();
                Intrinsics.checkNotNull(customerListFragment2);
                Toast.makeText(customerListFragment2.getContext(), errorMessage, 0).show();
            }
        }

    @Override // com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.compositeDisposable.clear();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    /* compiled from: CustomerListFragment.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getEXTRA_FILTER() {
            return CustomerListFragment.EXTRA_FILTER;
        }

        public String getEXTRA_CUSTOMER_SELECT_TYPE() {
            return CustomerListFragment.EXTRA_CUSTOMER_SELECT_TYPE;
        }
    }

    public static void filterSpecodeslambda12lambda11(List editTextList, CustomerListFragment this0, DialogInterface dialogInterface, View view) {
        Intrinsics.checkNotNullParameter(editTextList, "editTextList");
        Intrinsics.checkNotNullParameter(this0, "this0");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(editTextList, 10));
        Iterator it = editTextList.iterator();
        while (it.hasNext()) {
            Editable text = ((EditText) it.next()).getText();
            String obj = text != null ? text.toString() : null;
            if (obj == null) {
                obj = "";
            }
            arrayList.add(obj);
        }
        this0.specodeGroup = new SpecodeGroup((String) CollectionsKt.getOrNull(arrayList, 0), (String) CollectionsKt.getOrNull(arrayList, 1), (String) CollectionsKt.getOrNull(arrayList, 2), (String) CollectionsKt.getOrNull(arrayList, 3), (String) CollectionsKt.getOrNull(arrayList, 4));
        dialogInterface.dismiss();
        this0.restartLoader();
    }

    public static void filterSpecodeslambda9(List editTextList, CustomerListFragment this0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(editTextList, "editTextList");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Iterator it = editTextList.iterator();
        while (it.hasNext()) {
            Editable text = ((EditText) it.next()).getText();
            if (text != null) {
                text.clear();
            }
        }
        this0.specodeGroup = new SpecodeGroup(null, null, null, null, null, 31, null);
        this0.restartLoader();
        dialogInterface.dismiss();
    }
}
