package com.proje.mobilesales.features.sales.view.validation;

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
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.SearchAutoComplete;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseListFragment;
import com.proje.mobilesales.core.interfaces.ActionModeDelegate;
import com.proje.mobilesales.core.interfaces.OnLoadMoreListener;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
import com.proje.mobilesales.databinding.EmptyListBinding;
import com.proje.mobilesales.databinding.EmptySearchBinding;
import com.proje.mobilesales.databinding.FragmentOrderValidationBinding;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.dbmodel.ManagerOrder;
import com.proje.mobilesales.features.sales.repository.SalesValidationRepository;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class OrderValidationListFragment extends BaseListFragment implements SearchView.OnQueryTextListener, ActionModeDelegate {
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_FILTER = OrderValidationListFragment.class.getName() + ".EXTRA_FILTER";
    private static final String STATE_FILTER = "state:filter";
    private static final String STATE_SEARCH_VIEW_EXPANDED = "state:searchViewExpanded";
    private FragmentOrderValidationBinding _binding;
    private ActionMode mActionMode;
    private ActionViewResolver mActionViewResolver;
    private final OrderValidationRecyclerViewAdapter mAdapter;
    private EmptySearchBinding mEmptySearchView;
    private EmptyListBinding mEmptyView;
    private String mFilter;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private boolean mSearchViewExpanded;
    private AppBarSwipeRefreshLayout mSwipeRefreshLayout;
    private final SalesValidationViewModel viewModel;
 
    public boolean onQueryTextChange(String newText) {
        Intrinsics.checkNotNullParameter(newText, "newText");
        return false;
    }
 
    public boolean onQueryTextSubmit(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return false;
    }

    public OrderValidationListFragment() {
        SalesValidationRepository salesValidationRepository = new SalesValidationRepository();
        this.viewModel = new SalesValidationViewModel(salesValidationRepository);
        this.mActionViewResolver = new ActionViewResolver();
        this.mAdapter = new OrderValidationRecyclerViewAdapter(this);
    }

    private FragmentOrderValidationBinding getBinding() {
        FragmentOrderValidationBinding fragmentOrderValidationBinding = this._binding;
        Intrinsics.checkNotNull(fragmentOrderValidationBinding);
        return fragmentOrderValidationBinding;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return this.mProgressDialogBuilder;
    }

    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressDialogBuilder = progressDialogBuilder;
    }

    public ActionViewResolver getMActionViewResolver() {
        return this.mActionViewResolver;
    }

    public void setMActionViewResolver(ActionViewResolver actionViewResolver) {
        this.mActionViewResolver = actionViewResolver;
    }

    public ActionMode getMActionMode() {
        return this.mActionMode;
    }

    public void setMActionMode(ActionMode actionMode) {
        this.mActionMode = actionMode;
    }

    public AppBarSwipeRefreshLayout getMSwipeRefreshLayout() {
        return this.mSwipeRefreshLayout;
    }

    public void setMSwipeRefreshLayout(AppBarSwipeRefreshLayout appBarSwipeRefreshLayout) {
        this.mSwipeRefreshLayout = appBarSwipeRefreshLayout;
    }
 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        if (bundle != null) {
            this.mFilter = bundle.getString(STATE_FILTER);
            this.mSearchViewExpanded = bundle.getBoolean(STATE_SEARCH_VIEW_EXPANDED);
        } else {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            this.mFilter = arguments.getString(EXTRA_FILTER);
            this.mSearchViewExpanded = false;
        }
        Context requireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentOrderValidationBinding.inflate(inflater, viewGroup, false);
        this.mRecyclerView = getBinding().rcwList;
        this.mEmptyView = getBinding().empty;
        this.mEmptySearchView = getBinding().emptySearch;
        this.mSwipeRefreshLayout = getBinding().swipeLayout;
        EmptyListBinding emptyListBinding = this.mEmptyView;
        Intrinsics.checkNotNull(emptyListBinding);
        emptyListBinding.getRoot().setVisibility(View.INVISIBLE);
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        appBarSwipeRefreshLayout.setColorSchemeResources(R.color.white);
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = this.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
        appBarSwipeRefreshLayout2.setProgressBackgroundColorSchemeResource(R.color.redA200);
        if (bundle == null) {
            AppBarSwipeRefreshLayout appBarSwipeRefreshLayout3 = this.mSwipeRefreshLayout;
            Intrinsics.checkNotNull(appBarSwipeRefreshLayout3);
            appBarSwipeRefreshLayout3.post(new Runnable() { // from class: com.proje.mobilesales.features.sales.view.validation.OrderValidationListFragmentExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public void run() {
                    OrderValidationListFragment.onCreateViewlambda0(OrderValidationListFragment.this);
                }
            });
        }
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout4 = this.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout4);
        appBarSwipeRefreshLayout4.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {  
            public void onRefresh() {
                OrderValidationListFragment.onCreateViewlambda1(OrderValidationListFragment.this);
            }
        });
        FrameLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    } 
    public static void onCreateViewlambda0(OrderValidationListFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this0.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        appBarSwipeRefreshLayout.setRefreshing(true);
    }

    private static void onCreateViewlambda1(OrderValidationListFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.restartLoader();
    }
 
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mAdapter.getItemCount() > 0) {
            this.mAdapter.notifyDataSetChanged();
        } else {
            restartLoader();
        }
    } 
    public void onDetach() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
        }
        super.onDetach();
    }

    protected void createOptionsMenu(Menu menu, MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem findItem = menu.findItem(R.id.menu_search);
        Intrinsics.checkNotNullExpressionValue(findItem, "findItem(...)");
        createSearchView(findItem);
        super.createOptionsMenu(menu, inflater);
    }

    protected IListRecyclerView getAdapter() {
        return this.mAdapter;
    }
 
    private void createSearchView(MenuItem menuItem) {
        Drawable textCursorDrawable = null;
        ActionViewResolver actionViewResolver = this.mActionViewResolver;
        Intrinsics.checkNotNull(actionViewResolver);
        View actionView = actionViewResolver.getActionView(menuItem);
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        SearchView searchView = (SearchView) actionView;
        searchView.setQueryHint(getString(R.string.hint_search_order));
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        Object systemService = activity.getSystemService(Context.SEARCH_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.SearchManager");
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        searchView.setSearchableInfo(((SearchManager) systemService).getSearchableInfo(activity2.getComponentName()));
        searchView.setIconified(!this.mSearchViewExpanded);
        searchView.setQuery(this.mFilter, false);
        searchView.setOnQueryTextListener(this);
        View findViewById = searchView.findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView.SearchAutoComplete");
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById;
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.white));
        if (Build.VERSION.SDK_INT >= 29 && textCursorDrawable != null) {
            textCursorDrawable.setColorFilter(ContextCompat.getColor(requireContext(), R.color.custom_cursor_color), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setTextCursorDrawable(textCursorDrawable);
        }
        searchView.setOnSearchClickListener(new View.OnClickListener() {  
            public void onClick(View view) {
                OrderValidationListFragment.createSearchViewlambda2(OrderValidationListFragment.this, view);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {   
            public boolean onClose() {
                boolean createSearchViewlambda3;
                createSearchViewlambda3 = OrderValidationListFragment.createSearchViewlambda3(OrderValidationListFragment.this);
                return createSearchViewlambda3;
            }
        });
    }
 
    public static void createSearchViewlambda2(OrderValidationListFragment this0, View v) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(v, "v");
        this0.mSearchViewExpanded = true;
        v.requestFocus();
    }
    public static boolean createSearchViewlambda3(OrderValidationListFragment this0) {
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

    public void filter(String str) {
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        if (appBarSwipeRefreshLayout.isRefreshing()) {
            return;
        }
        this.mSearchViewExpanded = false;
        this.mFilter = str;
        restartLoader();
    }

    private void restartLoader() {
        this.mAdapter.restartScroll();
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        appBarSwipeRefreshLayout.setRefreshing(true);
        SalesValidationViewModel salesValidationViewModel = this.viewModel;
        String str = this.mFilter;
        Intrinsics.checkNotNull(str);
        salesValidationViewModel.getOrderValidationList(1, 50, str, new OrderValidationFragmentResponseListener(this));
        this.mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            public void onLoadMore(int i2, int i3) {
                OrderValidationListFragment.restartLoaderlambda4(OrderValidationListFragment.this, i2, i3);
            }
        });
    }

    public static void restartLoaderlambda4(OrderValidationListFragment this0, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        SalesValidationViewModel salesValidationViewModel = this0.viewModel;
        int i4 = i3 + 1;
        int i5 = i2 + i3;
        String str = this0.mFilter;
        Intrinsics.checkNotNull(str);
        salesValidationViewModel.getOrderValidationList(i4, i5, str, new OrderValidationFragmentResponseListener(this0));
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putString(STATE_FILTER, this.mFilter);
        outState.putBoolean(STATE_SEARCH_VIEW_EXPANDED, this.mSearchViewExpanded);
    }
    public void onOrderListLoad(final ArrayList<ManagerOrder> arrayList, String str) {
        if (isAttached()) {
            AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
            Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
            if (appBarSwipeRefreshLayout.isRefreshing()) {
                AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = this.mSwipeRefreshLayout;
                Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
                appBarSwipeRefreshLayout2.setRefreshing(false);
            }
            this.mRecyclerView.post(new Runnable() {
                public void run() {
                    OrderValidationListFragment.onOrderListLoadlambda5(OrderValidationListFragment.this, arrayList);
                }
            });
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Toast.makeText(getActivity(), str, Toast.LENGTH_LONG).show();
        }
    }

    public static void onOrderListLoadlambda5(OrderValidationListFragment this0, ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.swapCursor(arrayList);
    }

    private void swapCursor(List<? extends ManagerOrder> list) {
        OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter = this.mAdapter;
        if (list == null) {
            list = new ArrayList<>();
        }
        orderValidationRecyclerViewAdapter.addItem((List<ManagerOrder>) list);
        if (isDetached()) {
            return;
        }
        toggleEmptyView(this.mAdapter.getItemCount() == 0, this.mFilter);
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

    @Override // com.proje.mobilesales.core.interfaces.ActionModeDelegate
    public boolean startActionMode(ActionMode.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (this.mActionMode != null) {
            return true;
        }
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        Intrinsics.checkNotNull(appCompatActivity);
        this.mActionMode = appCompatActivity.startSupportActionMode(callback);
        return true;
    }

    public boolean isInActionMode() {
        return this.mActionMode != null;
    }

    public void stopActionMode() {
        this.mActionMode = null;
    }

    private record OrderValidationFragmentResponseListener(
            WeakReference<OrderValidationListFragment> mOrderValidationFragment) implements ResponseListener<ArrayList<ManagerOrder>> {
            private OrderValidationFragmentResponseListener(OrderValidationListFragment mOrderValidationFragment) {
                this.mOrderValidationFragment = new WeakReference<>(mOrderValidationFragment);
            }

            public void onResponse(PrintSlipModel arrayList) {
                if (this.mOrderValidationFragment.get() != null) {
                    OrderValidationListFragment orderValidationListFragment = this.mOrderValidationFragment.get();
                    Intrinsics.checkNotNull(orderValidationListFragment);
                    if (orderValidationListFragment.isAttached()) {
                        OrderValidationListFragment orderValidationListFragment2 = this.mOrderValidationFragment.get();
                        Intrinsics.checkNotNull(orderValidationListFragment2);
                        orderValidationListFragment2.onOrderListLoad(arrayList, "");
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mOrderValidationFragment.get() != null) {
                    OrderValidationListFragment orderValidationListFragment = this.mOrderValidationFragment.get();
                    Intrinsics.checkNotNull(orderValidationListFragment);
                    if (orderValidationListFragment.isAttached()) {
                        Log.d(MobileSales.TAG, "onError: " + errorMessage);
                        OrderValidationListFragment orderValidationListFragment2 = this.mOrderValidationFragment.get();
                        Intrinsics.checkNotNull(orderValidationListFragment2);
                        orderValidationListFragment2.onOrderListLoad(null, errorMessage);
                    }
                }
            }
        }
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
