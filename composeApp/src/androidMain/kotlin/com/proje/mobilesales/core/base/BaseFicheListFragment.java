package com.proje.mobilesales.core.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
import com.proje.mobilesales.databinding.EmptyListBinding;
import com.proje.mobilesales.databinding.EmptySearchBinding;
import com.proje.mobilesales.databinding.FragmentSalesListBinding;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;


public abstract class BaseFicheListFragment extends BaseListFragment {
    protected static final String STATE_CABIN_REF = "state:cabinRef";
    protected static final String STATE_CUSTOMER_OPERATION = "state:customerOperation";
    protected static final String STATE_CUSTOMER_REF = "state:customerRef";
    protected static final String STATE_E_INVOICE_USER = "state:eInvoiceUser";
    protected static final String STATE_FILTER = "state:filter";
    protected static final String STATE_ROUTEDAY_REF = "state:routeDayRef";
    protected static final String STATE_ROUTEPLAN_REF = "state:routePlanRef";
    protected static final String STATE_ROUTEUSERCUSTOMER_REF = "state:routeUserCustomerRef";
    protected static final String STATE_SEARCH_VIEW_EXPANDED = "state:searchViewExpanded";
    private FragmentSalesListBinding _binding;
    private int cabinRef;
    private boolean isMeInvoiceUser;
    public ActionViewResolver mActionViewResolver;
    protected CustomerOperation mCustomerOperation;
    protected int mCustomerRef;
    private EmptySearchBinding mEmptySearchView;
    private EmptyListBinding mEmptyView;
    protected String mFilter;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    protected boolean mSearchViewExpanded;
    protected AppBarSwipeRefreshLayout mSwipeRefreshLayout;
    private final BaseRepository repository;
    protected int routeDayRef;
    protected int routePlanRef;
    protected int routeUserCustomerRef;
    private final BaseViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_FILTER = BaseFicheListFragment.class.getName() + ".EXTRA_FILTER";
    public static final String EXTRA_CUSTOMER_REF = BaseFicheListFragment.class.getName() + ".EXTRA_CUSTOMER_REF";
    public static final String EXTRA_CUSTOMER_OPERATION = BaseFicheListFragment.class.getName() + ".EXTRA_CUSTOMER_OPERATION";
    public static final String EXTRA_ROUTEPLAN_REF = BaseFicheListFragment.class.getName() + ".EXTRA_ROUTEPLAN_REF";
    public static final String EXTRA_ROUTEDAY_REF = BaseFicheListFragment.class.getName() + ".EXTRA_ROUTEDAY_REF";
    public static final String EXTRA_ROUTEUSERCUSTOMER_REF = BaseFicheListFragment.class.getName() + ".EXTRA_ROUTEUSERCUSTOMER_REF";
    private static final String EXTRA_CABIN_REF = BaseFicheListFragment.class.getName() + ".EXTRA_CABIN_REF";
    protected abstract void createSearchView(MenuItem menuItem);
    protected abstract void restartLoader();
    public BaseFicheListFragment() {
        BaseRepository baseRepository = new BaseRepository(baseRepositorybaseErp2);
        this.repository = baseRepository;
        this.viewModel = new BaseViewModel(baseRepository);
    }
    private final FragmentSalesListBinding getBinding() {
        FragmentSalesListBinding fragmentSalesListBinding = this._binding;
        Intrinsics.checkNotNull(fragmentSalesListBinding);
        return fragmentSalesListBinding;
    }
    public final boolean isMeInvoiceUser() {
        return this.isMeInvoiceUser;
    }
    protected final void setMeInvoiceUser(boolean z) {
        this.isMeInvoiceUser = z;
    }
    public final int getCabinRef() {
        return this.cabinRef;
    }
    public final void setCabinRef(int i) {
        this.cabinRef = i;
    }
    public final AppBarSwipeRefreshLayout getMSwipeRefreshLayout() {
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
        if (appBarSwipeRefreshLayout != null) {
            return appBarSwipeRefreshLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
        return null;
    }
    protected final void setMSwipeRefreshLayout(AppBarSwipeRefreshLayout appBarSwipeRefreshLayout) {
        Intrinsics.checkNotNullParameter(appBarSwipeRefreshLayout, "<set-?>");
        this.mSwipeRefreshLayout = appBarSwipeRefreshLayout;
    }
    public final ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder != null) {
            return progressDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
        return null;
    }
    public final void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        this.mProgressDialogBuilder = progressDialogBuilder;
    }
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        this._binding = FragmentSalesListBinding.inflate(layoutInflater, viewGroup, false);
        Context requireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMProgressDialogBuilder(new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity));
        this.mActionViewResolver = new ActionViewResolver();
        this.mRecyclerView = getBinding().rcwList;
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = getBinding().swipeLayout;
        Intrinsics.checkNotNullExpressionValue(appBarSwipeRefreshLayout, "swipeLayout");
        setMSwipeRefreshLayout(appBarSwipeRefreshLayout);
        EmptyListBinding emptyListBinding = getBinding().empty;
        Intrinsics.checkNotNullExpressionValue(emptyListBinding, "empty");
        this.mEmptyView = emptyListBinding;
        EmptySearchBinding emptySearchBinding = getBinding().emptySearch;
        Intrinsics.checkNotNullExpressionValue(emptySearchBinding, "emptySearch");
        this.mEmptySearchView = emptySearchBinding;
        EmptyListBinding emptyListBinding2 = this.mEmptyView;
        if (emptyListBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
            emptyListBinding2 = null;
        }
        emptyListBinding2.getRoot().setVisibility(View.INVISIBLE);
        getMSwipeRefreshLayout().setColorSchemeResources(R.color.white);
        getMSwipeRefreshLayout().setProgressBackgroundColorSchemeResource(R.color.redA200);
        getMSwipeRefreshLayout().setEnabled(false);
        return getBinding().getRoot();
    }
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.mFilter = bundle.getString(STATE_FILTER);
            this.mCustomerRef = bundle.getInt("state:customerRef");
            this.mCustomerOperation = bundle.getParcelable(STATE_CUSTOMER_OPERATION);
            this.mSearchViewExpanded = bundle.getBoolean(STATE_SEARCH_VIEW_EXPANDED);
            this.isMeInvoiceUser = bundle.getBoolean(STATE_E_INVOICE_USER);
            this.routeDayRef = bundle.getInt("state:routeDayRef");
            this.routePlanRef = bundle.getInt("state:routePlanRef");
            this.routeUserCustomerRef = bundle.getInt("state:routeUserCustomerRef");
            this.cabinRef = bundle.getInt("state:cabinRef");
            return;
        }
        Bundle arguments = getArguments();
        Intrinsics.checkNotNull(arguments);
        this.mFilter = arguments.getString(EXTRA_FILTER);
        Bundle arguments2 = getArguments();
        Intrinsics.checkNotNull(arguments2);
        this.mCustomerRef = arguments2.getInt(EXTRA_CUSTOMER_REF);
        Bundle arguments3 = getArguments();
        Intrinsics.checkNotNull(arguments3);
        this.mCustomerOperation = arguments3.getParcelable(EXTRA_CUSTOMER_OPERATION);
        this.mSearchViewExpanded = false;
        ISqlHelper<?> sqlHelper = this.viewModel.getSqlHelper();
        this.isMeInvoiceUser = sqlHelper.getSingleFieldBoolean(ClCard.class, "ACCEPTEINV", "LOGICALREF=" + this.mCustomerRef);
        Bundle arguments4 = getArguments();
        Intrinsics.checkNotNull(arguments4);
        this.routeDayRef = arguments4.getInt(EXTRA_ROUTEDAY_REF);
        Bundle arguments5 = getArguments();
        Intrinsics.checkNotNull(arguments5);
        this.routePlanRef = arguments5.getInt(EXTRA_ROUTEPLAN_REF);
        Bundle arguments6 = getArguments();
        Intrinsics.checkNotNull(arguments6);
        this.routeUserCustomerRef = arguments6.getInt(EXTRA_ROUTEUSERCUSTOMER_REF);
        Bundle arguments7 = getArguments();
        Intrinsics.checkNotNull(arguments7);
        this.cabinRef = arguments7.getInt(EXTRA_CABIN_REF, 0);
    }
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        bundle.putParcelable(STATE_CUSTOMER_OPERATION, this.mCustomerOperation);
        bundle.putString(STATE_FILTER, this.mFilter);
        bundle.putInt("state:customerRef", this.mCustomerRef);
        bundle.putBoolean(STATE_SEARCH_VIEW_EXPANDED, this.mSearchViewExpanded);
        bundle.putBoolean(STATE_E_INVOICE_USER, this.isMeInvoiceUser);
        bundle.putInt("state:routeDayRef", this.routeDayRef);
        bundle.putInt("state:routePlanRef", this.routePlanRef);
        bundle.putInt("state:routeUserCustomerRef", this.routeUserCustomerRef);
        bundle.putInt("state:cabinRef", this.cabinRef);
        super.onSaveInstanceState(bundle);
    }
    public void filter(String str) {
        this.mSearchViewExpanded = false;
        this.mFilter = str;
        restartLoader();
    }
    public final void toggleEmptyView(boolean z, String str) {
        EmptySearchBinding emptySearchBinding = null;
        EmptyListBinding emptyListBinding = null;
        EmptySearchBinding emptySearchBinding2 = null;
        if (!z) {
            EmptyListBinding emptyListBinding2 = this.mEmptyView;
            if (emptyListBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
                emptyListBinding2 = null;
            }
            emptyListBinding2.getRoot().setVisibility(View.INVISIBLE);
            EmptySearchBinding emptySearchBinding3 = this.mEmptySearchView;
            if (emptySearchBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptySearchView");
            } else {
                emptySearchBinding = emptySearchBinding3;
            }
            emptySearchBinding.getRoot().setVisibility(View.INVISIBLE);
        } else if (TextUtils.isEmpty(str)) {
            EmptySearchBinding emptySearchBinding4 = this.mEmptySearchView;
            if (emptySearchBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptySearchView");
                emptySearchBinding4 = null;
            }
            emptySearchBinding4.getRoot().setVisibility(View.INVISIBLE);
            EmptyListBinding emptyListBinding3 = this.mEmptyView;
            if (emptyListBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
                emptyListBinding3 = null;
            }
            emptyListBinding3.getRoot().setVisibility(View.VISIBLE);
            EmptyListBinding emptyListBinding4 = this.mEmptyView;
            if (emptyListBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
            } else {
                emptyListBinding = emptyListBinding4;
            }
            emptyListBinding.getRoot().bringToFront();
        } else {
            EmptyListBinding emptyListBinding5 = this.mEmptyView;
            if (emptyListBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
                emptyListBinding5 = null;
            }
            emptyListBinding5.getRoot().setVisibility(View.INVISIBLE);
            EmptySearchBinding emptySearchBinding5 = this.mEmptySearchView;
            if (emptySearchBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptySearchView");
                emptySearchBinding5 = null;
            }
            emptySearchBinding5.getRoot().setVisibility(View.VISIBLE);
            EmptySearchBinding emptySearchBinding6 = this.mEmptySearchView;
            if (emptySearchBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptySearchView");
            } else {
                emptySearchBinding2 = emptySearchBinding6;
            }
            emptySearchBinding2.getRoot().bringToFront();
        }
    }
    public final CustomerOperation getmCustomerOperation() {
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        return customerOperation;
    }
    public final int getmCustomerRef() {
        return this.mCustomerRef;
    }
    public final void setmCustomerRef(int i) {
        this.mCustomerRef = i;
    }
    public final void selectAllAndFocusOfEditText(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "userInput");
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable(editText, this) { 
            public final  EditText f0;
            public final Runnable f1;

            {
                this.f0 = editText;
                this.f1 = this;
            } 
            public void run() {
                BaseFicheListFragment.selectAllAndFocusOfEditTextlambda0(this.f0, (BaseFicheListFragment) this.f1);
            }
        }, 200);
    }
    public static final void selectAllAndFocusOfEditTextlambda0(EditText editText, BaseFicheListFragment baseFicheListFragment) {
        Intrinsics.checkNotNullParameter(editText, "userInput");
        Intrinsics.checkNotNullParameter(baseFicheListFragment, "this0");
        editText.requestFocus();
        editText.selectAll();
        Context context = baseFicheListFragment.getContext();
        Intrinsics.checkNotNull(context);
        Object systemService = context.getSystemService(Context.INPUT_METHOD_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getEXTRA_CABIN_REF() {
            return BaseFicheListFragment.EXTRA_CABIN_REF;
        }
    }
}