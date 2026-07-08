package com.proje.mobilesales.features.cabinoperation.view.fragment;

import android.R;
import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.zxing.integration.android.IntentIntegrator;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseFicheListFragment;
import com.proje.mobilesales.core.interfaces.GetLoaderSqlText;
import com.proje.mobilesales.core.interfaces.OnLoadMoreListener;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.PermissionUtils;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.cabinoperation.adapter.CabinNewListRecyclerViewAdapter;
import com.proje.mobilesales.features.cabinoperation.interfaces.INewCabinOperationListener;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;
import com.proje.mobilesales.features.cabinoperation.repository.CabinRepository;
import com.proje.mobilesales.features.cabinoperation.view.activity.BarcodeScannerCabinView;
import com.proje.mobilesales.features.cabinoperation.viewmodel.CabinViewModel;
import com.proje.mobilesales.features.model.BarcodeCustomer;
import kotlin.jvm.internal.Intrinsics;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.proje.mobilesales.core.utils.AppUtils.playBeepSound;

public final class CabinNewListFragment extends BaseFicheListFragment implements SearchView.OnQueryTextListener, GetLoaderSqlText, INewCabinOperationListener {
    private boolean isBarcodeSearch;
    private SearchView searchView;
    private final CabinRepository repository = new CabinRepository();
    private final CabinViewModel viewModel = new CabinViewModel(repository);
    private CabinNewListRecyclerViewAdapter mAdapter = new CabinNewListRecyclerViewAdapter();

    public boolean onQueryTextChange(final String newText) {
        Intrinsics.checkNotNullParameter(newText, "newText");
        return true;
    }

    public CabinNewListRecyclerViewAdapter getMAdapter() {
        return mAdapter;
    }

    public void setMAdapter(final CabinNewListRecyclerViewAdapter cabinNewListRecyclerViewAdapter) {
        Intrinsics.checkNotNullParameter(cabinNewListRecyclerViewAdapter, "<set-?>");
        mAdapter = cabinNewListRecyclerViewAdapter;
    }
     public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
         this.getActivityComponent().inject(this);
    }
    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final View onCreateView = super.onCreateView(inflater, viewGroup, bundle);
        this.getMSwipeRefreshLayout().setEnabled(true);
        this.getMSwipeRefreshLayout().setRefreshing(false);
        this.getMSwipeRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                onCreateViewlambda0(CabinNewListFragment.this);
            }
        });
        return onCreateView;
    }
    public static void onCreateViewlambda0(final CabinNewListFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.restartLoader();
    }
     public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        mAdapter.setCabinOperationListener(this);
         this.restartLoader();
    }
    protected IListRecyclerView getAdapter() {
        return mAdapter;
    }
    protected void createOptionsMenu(final Menu menu, final MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        inflater.inflate(R.menu.menu_search, menu);
        this.createSearchView(menu.findItem(R.id.menu_search));
        inflater.inflate(R.menu.menu_available_cabin_operation, menu);
        super.createOptionsMenu(menu, inflater);
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (R.id.menu_barcode == item.getItemId()) {
            this.scanBarcodeFromFragment();
        }
        return super.onOptionsItemSelected(item);
    }
    protected void createSearchView(final MenuItem menuItem) {
        final Drawable textCursorDrawable = null;
        final ActionViewResolver actionViewResolver = mActionViewResolver;
        Intrinsics.checkNotNull(actionViewResolver);
        final View actionView = actionViewResolver.getActionView(menuItem);
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        final SearchView searchView = (SearchView) actionView;
        this.searchView = searchView;
        Intrinsics.checkNotNull(searchView);
        searchView.setQueryHint(this.getString(R.string.str_hint_search_cabin));
        final SearchView searchView2 = this.searchView;
        Intrinsics.checkNotNull(searchView2);
        final FragmentActivity activity = this.getActivity();
        final Object systemService = null != activity ? activity.getSystemService(FirebaseAnalytics.Event.SEARCH) : null;
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.SearchManager");
        final FragmentActivity activity2 = this.getActivity();
        Intrinsics.checkNotNull(activity2);
        searchView2.setSearchableInfo(((SearchManager) systemService).getSearchableInfo(activity2.getComponentName()));
        final SearchView searchView3 = this.searchView;
        Intrinsics.checkNotNull(searchView3);
        searchView3.setIconified(!mSearchViewExpanded);
        final SearchView searchView4 = this.searchView;
        Intrinsics.checkNotNull(searchView4);
        searchView4.setQuery(mFilter, false);
        final SearchView searchView5 = this.searchView;
        Intrinsics.checkNotNull(searchView5);
        searchView5.setOnQueryTextListener(this);
        final SearchView searchView6 = this.searchView;
        Intrinsics.checkNotNull(searchView6);
        final View findViewById = searchView6.findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView.SearchAutoComplete");
        final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) findViewById;
        searchAutoComplete.setHintTextColor(this.getResources().getColor(R.color.white));
        if (29 <= Build.VERSION.SDK_INT && null != textCursorDrawable) {
            final Context context = this.getContext();
            Intrinsics.checkNotNull(context);
            textCursorDrawable.setColorFilter(ContextCompat.getColor(context, R.color.custom_cursor_color), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setTextCursorDrawable(textCursorDrawable);
        }
        final SearchView searchView7 = this.searchView;
        Intrinsics.checkNotNull(searchView7);
        searchView7.setOnSearchClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                createSearchViewlambda1(CabinNewListFragment.this, view);
            }
        });
        final SearchView searchView8 = this.searchView;
        Intrinsics.checkNotNull(searchView8);
        searchView8.setOnCloseListener(new SearchView.OnCloseListener() {
            public boolean onClose() {
                final boolean createSearchViewlambda2;
                createSearchViewlambda2 = createSearchViewlambda2(CabinNewListFragment.this);
                return createSearchViewlambda2;
            }
        });
    }

    private static void createSearchViewlambda1(final CabinNewListFragment this0, final View v) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(v, "v");
        this0.mSearchViewExpanded = true;
        v.requestFocus();
    }

    private static boolean createSearchViewlambda2(final CabinNewListFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mFilter = "";
        this0.isBarcodeSearch = false;
        final AppCompatActivity appCompatActivity = (AppCompatActivity) this0.getActivity();
        Intrinsics.checkNotNull(appCompatActivity);
        final ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setSubtitle(this0.mFilter);
        this0.restartLoader();
        return false;
    }
    public void filter(final String str) {
        mSearchViewExpanded = false;
        mFilter = str;
        this.restartLoader();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void restartLoader() {
        mAdapter.restartScroll();
        mAdapter.notifyDataSetChanged();
        viewModel.getCabinList(this.getLoaderSqlText(50, 0), new CabinListResponseListener(this));
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            public void onLoadMore(final int i2, final int i3) {
                restartLoaderlambda3(CabinNewListFragment.this, i2, i3);
            }
        });
    }
    public static void restartLoaderlambda3(final CabinNewListFragment this0, final int i2, final int i3) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.callLoader(i2, i3);
    }

    private void callLoader(final int i2, final int i3) {
        viewModel.getCabinList(this.getLoaderSqlText(i2, i3), new CabinListResponseListener(this));
    }
    public boolean onQueryTextSubmit(final String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        mFilter = query;
        this.restartLoader();
        return false;
    }
    public String getLoaderSqlText(final int i2, final int i3) throws IllegalArgumentException {
        final String availableCabinListOnTheStorageSql = viewModel.getBaseErp().getLogoSqlHelper().getAvailableCabinListOnTheStorageSql(this.getContext(), i2, i3, mFilter);
        Intrinsics.checkNotNullExpressionValue(availableCabinListOnTheStorageSql, "getAvailableCabinListOnTheStorageSql(...)");
        return availableCabinListOnTheStorageSql;
    }

    private void onCabinLoads(final ArrayList<Cabin> arrayList) {
        mAdapter.addItem(arrayList);
        if (!this.isDetached()) {
            this.toggleEmptyView(0 == this.mAdapter.getItemCount(), mFilter);
        }
        if (isBarcodeSearch && null != arrayList && !arrayList.isEmpty()) {
            playBeepSound();
        }
        this.getMProgressDialogBuilder().dismiss();
        if (this.getMSwipeRefreshLayout().isRefreshing()) {
            this.getMSwipeRefreshLayout().setRefreshing(false);
        }
        isBarcodeSearch = false;
    }
    public void onCabinsLoadError(final String str) {
        isBarcodeSearch = false;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this.getContext(), str, 1).show();
    }

    private record CabinListResponseListener(
            WeakReference<CabinNewListFragment> mCabinListFragment) implements ResponseListener<ArrayList<Cabin>> {
            private CabinListResponseListener(final CabinNewListFragment mCabinListFragment) {
                this.mCabinListFragment = new WeakReference<>(mCabinListFragment);
            }

        public void onResponse(final ArrayList<Cabin> arrayList) {
                if (null != this.mCabinListFragment.get()) {
                    final CabinNewListFragment cabinNewListFragment = mCabinListFragment.get();
                    Intrinsics.checkNotNull(cabinNewListFragment);
                    if (cabinNewListFragment.isAttached()) {
                        final CabinNewListFragment cabinNewListFragment2 = mCabinListFragment.get();
                        Intrinsics.checkNotNull(cabinNewListFragment2);
                        cabinNewListFragment2.onCabinLoads(arrayList);
                    }
                }
            }

        public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mCabinListFragment.get()) {
                    final CabinNewListFragment cabinNewListFragment = mCabinListFragment.get();
                    Intrinsics.checkNotNull(cabinNewListFragment);
                    if (cabinNewListFragment.isAttached()) {
                        Log.d(MobileSales.TAG, "onError: " + errorMessage);
                        final CabinNewListFragment cabinNewListFragment2 = mCabinListFragment.get();
                        Intrinsics.checkNotNull(cabinNewListFragment2);
                        cabinNewListFragment2.onCabinsLoadError(errorMessage);
                    }
                }
            }
        }
    public void addAvailableCabinToCustomer(final Cabin cabin) {
        viewModel.getBaseErp().addAvailableCabinToCustomer(cabin, mCustomerRef);
        final FragmentActivity activity = this.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.setResult(-1);
        final FragmentActivity activity2 = this.getActivity();
        Intrinsics.checkNotNull(activity2);
        activity2.finish();
    }
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (49374 == i2 && -1 == i3 && null != intent) {
            final BarcodeCustomer barcodeCustomer = intent.getParcelableExtra("SCAN_RESULT");
            Intrinsics.checkNotNull(barcodeCustomer);
            mFilter = barcodeCustomer.getCabinCode();
            isBarcodeSearch = true;
            final SearchView searchView = this.searchView;
            if (null != searchView) {
                Intrinsics.checkNotNull(searchView);
                searchView.setQuery(mFilter, false);
                final SearchView searchView2 = this.searchView;
                Intrinsics.checkNotNull(searchView2);
                searchView2.setIconified(false);
                final SearchView searchView3 = this.searchView;
                Intrinsics.checkNotNull(searchView3);
                searchView3.clearFocus();
            } else {
                mSearchViewExpanded = true;
            }
            this.restartLoader();
        }
    }

    private void scanBarcodeFromFragment() {
        if (PermissionUtils.checkPermissionFromFragment(this, "android.permission.CAMERA", this.getString(R.string.str_camera_permission_for_barcode))) {
            final ArrayList arrayList = new ArrayList();
            arrayList.add(IntentIntegrator.ONE_D_CODE_TYPES.toString());
            arrayList.add("QR_CODE");
            IntentIntegrator.forSupportFragment(this).setBeepEnabled(false).setDesiredBarcodeFormats(arrayList).setCaptureActivity(BarcodeScannerCabinView.class).addExtra(IntentExtraName.EXTRA_CUSTOMER_BARCODE_SEARCH, Boolean.FALSE).initiateScan();
        }
    }
    public void onRequestPermissionsResult(final int i2, final String[] permissions2, final int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions2, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        if (1074 == i2) {
            if (0 != grantResults.length && 0 == grantResults[0]) {
                this.scanBarcodeFromFragment();
            } else {
                Toast.makeText(this.getContext(), this.getString(R.string.str_permissions_denied), 1).show();
            }
        }
        super.onRequestPermissionsResult(i2, permissions2, grantResults);
    }
}
