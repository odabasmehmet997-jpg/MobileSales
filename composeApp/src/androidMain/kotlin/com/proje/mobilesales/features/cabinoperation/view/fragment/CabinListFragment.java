package com.proje.mobilesales.features.cabinoperation.view.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseFicheListFragment;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.GetLoaderSqlText;
import com.proje.mobilesales.core.interfaces.OnLoadMoreListener;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.interfaces.TransferAfterRefreshList;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.cabinoperation.adapter.CabinListRecyclerViewAdapter;
import com.proje.mobilesales.features.cabinoperation.interfaces.ICabinOperationListener;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;
import com.proje.mobilesales.features.cabinoperation.repository.CabinRepository;
import com.proje.mobilesales.features.cabinoperation.view.activity.CabinNewListActivity;
import com.proje.mobilesales.features.cabinoperation.viewmodel.CabinViewModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CabinListFragment extends BaseFicheListFragment implements SearchView.OnQueryTextListener, GetLoaderSqlText, TransferAfterRefreshList, ICabinOperationListener {
    public static final Companion Companion = new Companion(null);
    public static final int REQUEST_NEW_CABIN = 999;
    private final CabinRepository repository = new CabinRepository();
    private final CabinViewModel viewModel = new CabinViewModel(repository);
    private CabinListRecyclerViewAdapter mAdapter = new CabinListRecyclerViewAdapter();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.proje.mobilesales.features.cabinoperation.view.fragment.CabinListFragmentmReceiver1
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            refreshTransferAfterList(intent.getBooleanExtra(IntentExtraName.EXTRA_TRANSFER_STATUS, false));
        }
    };
    protected void createSearchView(final MenuItem menuItem) {
    }
    public boolean onQueryTextChange(final String newText) {
        Intrinsics.checkNotNullParameter(newText, "newText");
        return true;
    }
    public boolean onQueryTextSubmit(final String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return false;
    }

    public CabinListRecyclerViewAdapter getMAdapter() {
        return mAdapter;
    }

    public void setMAdapter(final CabinListRecyclerViewAdapter cabinListRecyclerViewAdapter) {
        Intrinsics.checkNotNullParameter(cabinListRecyclerViewAdapter, "<set-?>");
        mAdapter = cabinListRecyclerViewAdapter;
    }
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        final Context context = this.getContext();
        Intrinsics.checkNotNull(context);
        LocalBroadcastManager.getInstance(context).registerReceiver(mReceiver, new IntentFilter(IntentExtraName.ACTION_TRANSFER_FICHE));
    }
    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final View onCreateView = super.onCreateView(inflater, viewGroup, bundle);
        this.getMSwipeRefreshLayout().setEnabled(true);
        this.getMSwipeRefreshLayout().setRefreshing(false);
        this.getMSwipeRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                onCreateViewlambda0(CabinListFragment.this);
            }
        });
        return onCreateView;
    }
    public static void onCreateViewlambda0(final CabinListFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.restartLoader();
    }
     public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        mAdapter.setCabinOperationListener(this);
         this.restartLoader();
    }

    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (999 == i2 && -1 == i3) {
            this.restartLoader();
        }
    }

    public void onResume() {
        super.onResume();
    }
    public void onDestroy() {
        super.onDestroy();
        final Context context = this.getContext();
        Intrinsics.checkNotNull(context);
        LocalBroadcastManager.getInstance(context).unregisterReceiver(mReceiver);
    }
    protected IListRecyclerView getAdapter() {
        return mAdapter;
    }
    protected void createOptionsMenu(final Menu menu, final MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        inflater.inflate(R.menu.menu_sales_list, menu);
        super.createOptionsMenu(menu, inflater);
    }
    protected void prepareOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.findItem(R.id.menu_filter).setVisible(false);
        menu.findItem(R.id.menu_sort).setVisible(false);
        super.prepareOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (R.id.menu_new == item.getItemId() && ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            final ProgressDialogBuilder<?> mProgressDialogBuilder = this.getMProgressDialogBuilder();
            final Context context = this.getContext();
            Intrinsics.checkNotNull(context);
            mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
            viewModel.getBaseErp().checkRemoteWorkTimeControl(WorkTimeControlProcessType.DataEntry, new CheckWorkTimeListener(this));
        }
        return super.onOptionsItemSelected(item);
    }

    private record CheckWorkTimeListener(
            WeakReference<CabinListFragment> mFragment) implements ResponseListener<String> {
            private CheckWorkTimeListener(final CabinListFragment mFragment) {
                this.mFragment = new WeakReference<>(mFragment);
            }

        public void onResponse(final String str) {
                if (null != this.mFragment.get()) {
                    final CabinListFragment cabinListFragment = mFragment.get();
                    Intrinsics.checkNotNull(cabinListFragment);
                    if (cabinListFragment.isAttached()) {
                        final CabinListFragment cabinListFragment2 = mFragment.get();
                        Intrinsics.checkNotNull(cabinListFragment2);
                        cabinListFragment2.getMProgressDialogBuilder().dismiss();
                        if (!TextUtils.isEmpty(str)) {
                            final CabinListFragment cabinListFragment3 = mFragment.get();
                            Intrinsics.checkNotNull(cabinListFragment3);
                            Toast.makeText(cabinListFragment3.getActivity(), str, 0).show();
                            return;
                        }
                        final CabinListFragment cabinListFragment4 = mFragment.get();
                        Intrinsics.checkNotNull(cabinListFragment4);
                        final Intent intent = new Intent(cabinListFragment4.getActivity(), CabinNewListActivity.class);
                        final String str2 = IntentExtraName.EXTRA_CUSTOMER_REF;
                        final CabinListFragment cabinListFragment5 = mFragment.get();
                        Intrinsics.checkNotNull(cabinListFragment5);
                        intent.putExtra(str2, cabinListFragment5.getmCustomerRef());
                        final CabinListFragment cabinListFragment6 = mFragment.get();
                        Intrinsics.checkNotNull(cabinListFragment6);
                        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, cabinListFragment6.getmCustomerOperation());
                        final CabinListFragment cabinListFragment7 = mFragment.get();
                        Intrinsics.checkNotNull(cabinListFragment7);
                        cabinListFragment7.startActivityForResult(intent, 999);
                    }
                }
            }

        public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mFragment.get()) {
                    final CabinListFragment cabinListFragment = mFragment.get();
                    Intrinsics.checkNotNull(cabinListFragment);
                    if (cabinListFragment.isAttached()) {
                        final CabinListFragment cabinListFragment2 = mFragment.get();
                        Intrinsics.checkNotNull(cabinListFragment2);
                        cabinListFragment2.getMProgressDialogBuilder().dismiss();
                    }
                }
            }
        }

    @Override // com.proje.mobilesales.core.base.BaseFicheListFragment
    @SuppressLint("NotifyDataSetChanged")
    public void restartLoader() {
        mAdapter.restartScroll();
        mAdapter.notifyDataSetChanged();
        viewModel.getCabinList(this.getLoaderSqlText(50, 0), new CabinListResponseListener(this));
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.proje.mobilesales.features.cabinoperation.view.fragment.CabinListFragmentExternalSyntheticLambda1
            @Override // com.proje.mobilesales.core.interfaces.OnLoadMoreListener
            public void onLoadMore(final int i2, final int i3) {
                restartLoaderlambda1(CabinListFragment.this, i2, i3);
            }
        });
    }

    
    public static void restartLoaderlambda1(final CabinListFragment this0, final int i2, final int i3) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.callLoader(i2, i3);
    }

    private void callLoader(final int i2, final int i3) {
        viewModel.getCabinList(this.getLoaderSqlText(i2, i3), new CabinListResponseListener(this));
    }

    @Override // com.proje.mobilesales.core.interfaces.GetLoaderSqlText
    public String getLoaderSqlText(final int i2, final int i3) throws IllegalArgumentException {
        final String cabinListSql = viewModel.getBaseErp().getLogoSqlHelper().getCabinListSql(this.getContext(), viewModel.getBaseErp().getLogoSqlHelper().getClCode(mCustomerRef), i2, i3);
        Intrinsics.checkNotNullExpressionValue(cabinListSql, "getCabinListSql(...)");
        return cabinListSql;
    }

    
    public void onCabinLoads(final ArrayList<Cabin> arrayList) {
        mAdapter.addItem(arrayList);
        if (!this.isDetached()) {
            this.toggleEmptyView(0 == this.mAdapter.getItemCount(), mFilter);
        }
        this.getMProgressDialogBuilder().dismiss();
        if (this.getMSwipeRefreshLayout().isRefreshing()) {
            this.getMSwipeRefreshLayout().setRefreshing(false);
        }
    }

    @Override // com.proje.mobilesales.core.interfaces.TransferAfterRefreshList
    public void refreshTransferAfterList(final boolean z) {
        if (this.isAttached() && z) {
            this.restartLoader();
        }
    }

    
    public void onCabinsLoadError(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this.getContext(), str, 1).show();
    }

    /* compiled from: CabinListFragment.kt */
        private record CabinListResponseListener(
            WeakReference<CabinListFragment> mCabinListFragment) implements ResponseListener<ArrayList<Cabin>> {
            private CabinListResponseListener(final CabinListFragment mCabinListFragment) {
                this.mCabinListFragment = new WeakReference<>(mCabinListFragment);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(final ArrayList<Cabin> arrayList) {
                if (null != this.mCabinListFragment.get()) {
                    final CabinListFragment cabinListFragment = mCabinListFragment.get();
                    Intrinsics.checkNotNull(cabinListFragment);
                    if (cabinListFragment.isAttached()) {
                        final CabinListFragment cabinListFragment2 = mCabinListFragment.get();
                        Intrinsics.checkNotNull(cabinListFragment2);
                        cabinListFragment2.onCabinLoads(arrayList);
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mCabinListFragment.get()) {
                    final CabinListFragment cabinListFragment = mCabinListFragment.get();
                    Intrinsics.checkNotNull(cabinListFragment);
                    if (cabinListFragment.isAttached()) {
                        Log.d(MobileSales.TAG, "onError: " + errorMessage);
                        final CabinListFragment cabinListFragment2 = mCabinListFragment.get();
                        Intrinsics.checkNotNull(cabinListFragment2);
                        cabinListFragment2.onCabinsLoadError(errorMessage);
                    }
                }
            }
        }

    @Override // com.proje.mobilesales.features.cabinoperation.interfaces.ICabinOperationListener
    public void getCabinDeliverToCustomer(final Cabin cabin) {
        if (null != cabin) {
            viewModel.deliverCabinToCustomer(cabin, mCustomerRef);
        }
        this.restartLoader();
    }

    @Override // com.proje.mobilesales.features.cabinoperation.interfaces.ICabinOperationListener
    public void getCabinDeliverToStorage(final Cabin cabin) {
        if (null != cabin) {
            viewModel.deliverCabinToStorage(cabin, mCustomerRef);
        }
        this.restartLoader();
    }

    @Override // com.proje.mobilesales.features.cabinoperation.interfaces.ICabinOperationListener
    public void getCabinReceivedFromCustomer(final Cabin cabin, final int i2) {
        if (null != cabin) {
            viewModel.receiveCabinFromCustomer(cabin, i2, mCustomerRef);
        }
        this.restartLoader();
    }

    /* compiled from: CabinListFragment.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
