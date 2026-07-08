package com.proje.mobilesales.features.customer.view.communication.ship;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseListFragment;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.databinding.EmptyListBinding;
import com.proje.mobilesales.databinding.EmptySearchBinding;
import com.proje.mobilesales.databinding.FragmentListNonSwipeBinding;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.adapter.ShipAddressRecyclerViewAdapter;
import com.proje.mobilesales.features.customer.repository.BaseCustomerRepository;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public final class CustomerShipAddressFragment extends BaseListFragment implements LoaderManager.LoaderCallbacks<Cursor>, SearchView.OnQueryTextListener {
    private static final String STATE_CUSTOMER_REF = "state:customerRef";
    private static final String STATE_FILTER = "state:filter";
    private static final String STATE_SALES_SELECT = "state:salesSelect";
    private static final String STATE_SEARCH_VIEW_EXPANDED = "state:searchViewExpanded";
    private static final String TAG = "CustomerShipAddressFragment";
    private FragmentListNonSwipeBinding _binding;
    private ActionViewResolver mActionViewResolver;
    private final ShipAddressRecyclerViewAdapter mAdapter;
    private int mCustomerRef;
    private EmptySearchBinding mEmptySearchView;
    private EmptyListBinding mEmptyView;
    private String mFilter;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private boolean mSalesSelect;
    private boolean mSearchViewExpanded;
    private final BaseCustomerRepository repository;
    private final CustomerShipAddressViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_FILTER = CustomerShipAddressFragment.class.getName() + ".EXTRA_FILTER";
    private static final String EXTRA_CUSTOMER_REF = CustomerShipAddressFragment.class.getName() + ".EXTRA_CUSTOMER_REF";
    private static final String EXTRA_SALES_SELECT = CustomerShipAddressFragment.class.getName() + ".EXTRA_SALES_SELECT";
 
    public boolean onQueryTextSubmit(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return false;
    }

    public CustomerShipAddressFragment() {
        BaseCustomerRepository baseCustomerRepository = new BaseCustomerRepository();
        this.repository = baseCustomerRepository;
        this.viewModel = new CustomerShipAddressViewModel(baseCustomerRepository);
        this.mAdapter = new ShipAddressRecyclerViewAdapter();
    }

    private FragmentListNonSwipeBinding getBinding() {
        FragmentListNonSwipeBinding fragmentListNonSwipeBinding = this._binding;
        Intrinsics.checkNotNull(fragmentListNonSwipeBinding);
        return fragmentListNonSwipeBinding;
    }

    public ActionViewResolver getMActionViewResolver() {
        return this.mActionViewResolver;
    }

    public void setMActionViewResolver(ActionViewResolver actionViewResolver) {
        this.mActionViewResolver = actionViewResolver;
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
        this.mActionViewResolver = new ActionViewResolver();
    } 
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentListNonSwipeBinding.inflate(inflater, viewGroup, false);
        this.mRecyclerView = getBinding().rcwList;
        EmptyListBinding emptyListBinding = getBinding().empty;
        this.mEmptyView = emptyListBinding;
        Intrinsics.checkNotNull(emptyListBinding);
        emptyListBinding.getRoot().setVisibility(View.INVISIBLE);
        this.mEmptySearchView = getBinding().emptySearch;
        FrameLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }
     public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.mFilter = bundle.getString(STATE_FILTER);
            this.mCustomerRef = bundle.getInt("state:customerRef");
            this.mSearchViewExpanded = bundle.getBoolean(STATE_SEARCH_VIEW_EXPANDED);
            this.mSalesSelect = bundle.getBoolean(STATE_SALES_SELECT, this.mSalesSelect);
        } else {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            this.mFilter = arguments.getString(EXTRA_FILTER);
            Bundle arguments2 = getArguments();
            Intrinsics.checkNotNull(arguments2);
            this.mCustomerRef = arguments2.getInt(EXTRA_CUSTOMER_REF);
            Bundle arguments3 = getArguments();
            Intrinsics.checkNotNull(arguments3);
            this.mSalesSelect = arguments3.getBoolean(EXTRA_SALES_SELECT);
        }
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setmActivity(getActivity());
        this.mAdapter.setmSalesSelect(this.mSalesSelect);
        restartLoader();
    } 
    protected void createOptionsMenu(Menu menu, MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem findItem = menu.findItem(R.id.menu_search);
        Intrinsics.checkNotNullExpressionValue(findItem, "findItem(...)");
        createSearchView(findItem);
        if (this.mAdapter.getItemCount() > -1) {
            super.createOptionsMenu(menu, inflater);
        }
    } 
    protected void prepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.findItem(R.id.menu_search).setVisible(!TextUtils.isEmpty(this.mFilter) || this.mAdapter.getItemCount() > -1);
    }
     private   void createSearchView(MenuItem menuItem) {
        Drawable textCursorDrawable = null;
        ActionViewResolver actionViewResolver = this.mActionViewResolver;
        Intrinsics.checkNotNull(actionViewResolver);
        View actionView = actionViewResolver.getActionView(menuItem);
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        SearchView searchView = (SearchView) actionView;
        searchView.setQueryHint(getString(R.string.hint_search_ship_address));
        Object systemService = requireActivity().getSystemService(Context.SEARCH_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.SearchManager");
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        searchView.setSearchableInfo(((SearchManager) systemService).getSearchableInfo(activity.getComponentName()));
        searchView.setIconified(!this.mSearchViewExpanded);
        searchView.setQuery(this.mFilter, false);
        searchView.setOnQueryTextListener(this);
        View findViewById = searchView.findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView.SearchAutoComplete");
        @SuppressLint("RestrictedApi") SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) findViewById;
        searchAutoComplete.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        if (Build.VERSION.SDK_INT >= 29 && textCursorDrawable != null) {
            textCursorDrawable.setColorFilter(ContextCompat.getColor(requireContext(), R.color.custom_cursor_color), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setTextCursorDrawable(textCursorDrawable);
        }
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            public void CustomerShipAddressFragmentExternalSyntheticLambda0() {
            } 
            public void onClick(View view) {
                CustomerShipAddressFragment.createSearchViewlambda0(CustomerShipAddressFragment.this, view);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            public void CustomerShipAddressFragmentExternalSyntheticLambda1() {
            }
            public boolean onClose() {
                boolean createSearchViewlambda1;
                createSearchViewlambda1 = CustomerShipAddressFragment.createSearchViewlambda1(CustomerShipAddressFragment.this);
                return createSearchViewlambda1;
            }
        });
    }

    public static void createSearchViewlambda0(CustomerShipAddressFragment this0, View v) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(v, "v");
        this0.mSearchViewExpanded = true;
        v.requestFocus();
    }

    public static boolean createSearchViewlambda1(CustomerShipAddressFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mFilter = "";
        AppCompatActivity appCompatActivity = (AppCompatActivity) this0.getActivity();
        Intrinsics.checkNotNull(appCompatActivity);
        ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setSubtitle(this0.mFilter);
        this0.restartLoader();
        return false;
    }

    private   void restartLoader() {
        getMProgressDialogBuilder().setMessage("Loading").show();
        getLoaderManager().restartLoader(0, null, this);
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putString(STATE_FILTER, this.mFilter);
        outState.putInt("state:customerRef", this.mCustomerRef);
        outState.putBoolean(STATE_SEARCH_VIEW_EXPANDED, this.mSearchViewExpanded);
        outState.putBoolean(STATE_SALES_SELECT, this.mSalesSelect);
    }
     public void onDetach() {
        super.onDetach();
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
        }
    }

    protected IListRecyclerView getAdapter() {
        return this.mAdapter;
    }

    public void filter(String str) {
        this.mSearchViewExpanded = false;
        this.mFilter = str;
        restartLoader();
    }

    private void swapCursor(ISqlManager.ShipAddressCursor shipAddressCursor) {
        this.mAdapter.setmShipAddressCursor(shipAddressCursor);
        if (!isDetached()) {
            toggleEmptyView(this.mAdapter.getItemCount() == 0, this.mFilter);
        }
        getMProgressDialogBuilder().dismiss();
    }

    private void toggleEmptyView(boolean z, String str) {
        if (z) {
            if (TextUtils.isEmpty(str)) {
                EmptySearchBinding emptySearchBinding = this.mEmptySearchView;
                Intrinsics.checkNotNull(emptySearchBinding);
                emptySearchBinding.getRoot().setVisibility(View.INVISIBLE);
                EmptyListBinding emptyListBinding = this.mEmptyView;
                Intrinsics.checkNotNull(emptyListBinding);
                emptyListBinding.getRoot().setVisibility(View.VISIBLE);
                EmptyListBinding emptyListBinding2 = this.mEmptyView;
                Intrinsics.checkNotNull(emptyListBinding2);
                emptyListBinding2.getRoot().bringToFront();
                return;
            }
            EmptyListBinding emptyListBinding3 = this.mEmptyView;
            Intrinsics.checkNotNull(emptyListBinding3);
            emptyListBinding3.getRoot().setVisibility(View.INVISIBLE);
            EmptySearchBinding emptySearchBinding2 = this.mEmptySearchView;
            Intrinsics.checkNotNull(emptySearchBinding2);
            emptySearchBinding2.getRoot().setVisibility(View.VISIBLE);
            EmptySearchBinding emptySearchBinding3 = this.mEmptySearchView;
            Intrinsics.checkNotNull(emptySearchBinding3);
            emptySearchBinding3.getRoot().bringToFront();
            return;
        }
        EmptyListBinding emptyListBinding4 = this.mEmptyView;
        Intrinsics.checkNotNull(emptyListBinding4);
        emptyListBinding4.getRoot().setVisibility(View.INVISIBLE);
        EmptySearchBinding emptySearchBinding4 = this.mEmptySearchView;
        Intrinsics.checkNotNull(emptySearchBinding4);
        emptySearchBinding4.getRoot().setVisibility(View.INVISIBLE);
    }
    public boolean onQueryTextChange(String newText) {
        Intrinsics.checkNotNullParameter(newText, "newText");
        getLoaderManager().restartLoader(0, null, this);
        return true;
    }
    public Loader<Cursor> onCreateLoader(int i2, Bundle bundle) {
        return new ISqlManager.Loader(getActivity(), getShipAddressSql());
    }

    private String getShipAddressSql() {
        return this.viewModel.getCustomerShipAddressSql(getContext(), (this.viewModel.erpType() == ErpType.TIGER || this.viewModel.getBaseErp().getSevkiyatHesabiTeslimatCari()) ? this.mCustomerRef : 0, this.mFilter);
    }
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        swapCursor(cursor != null ? new ISqlManager.ShipAddressCursor(cursor) : null);
    }
    public void onLoaderReset(Loader<Cursor> loader) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        swapCursor(null);
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

        public String getEXTRA_FILTER() {
            return CustomerShipAddressFragment.EXTRA_FILTER;
        }

        public String getEXTRA_CUSTOMER_REF() {
            return CustomerShipAddressFragment.EXTRA_CUSTOMER_REF;
        }

        public String getEXTRA_SALES_SELECT() {
            return CustomerShipAddressFragment.EXTRA_SALES_SELECT;
        }
    }
}
