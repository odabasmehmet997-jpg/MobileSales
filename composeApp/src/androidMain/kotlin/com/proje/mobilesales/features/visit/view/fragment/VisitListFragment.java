package com.proje.mobilesales.features.visit.view.fragment;


import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
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
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseFicheActivity;
import com.proje.mobilesales.core.base.BaseFicheListFragment;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.GetLoaderSqlText;
import com.proje.mobilesales.core.interfaces.OnLoadMoreListener;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.interfaces.TransferAfterRefreshList;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.visit.adapter.VisitRecyclerViewAdapter;
import com.proje.mobilesales.features.visit.model.Visit;
import com.proje.mobilesales.features.visit.model.VisitInfoShort;
import com.proje.mobilesales.features.visit.repository.VisitRepository;
import com.proje.mobilesales.features.visit.view.activity.VisitEnterActivityNew;
import com.proje.mobilesales.features.visit.viewmodel.VisitViewModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class VisitListFragment extends BaseFicheListFragment implements SearchView.OnQueryTextListener, GetLoaderSqlText, TransferAfterRefreshList {
  private boolean isSearchUsed;
  private VisitRepository repository = new VisitRepository();
  private VisitViewModel viewModel = new VisitViewModel(this.repository);
  private VisitRecyclerViewAdapter mAdapter = new VisitRecyclerViewAdapter();
  private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
    private VisitListFragment this0;
    public void onReceive(Context context, Intent intent) {
      Intrinsics.checkNotNullParameter(context, "context");
      Intrinsics.checkNotNullParameter(intent, "intent");
      this.this0.refreshTransferAfterList(intent.getBooleanExtra(IntentExtraName.EXTRA_TRANSFER_STATUS, false));
    }
  };
  public boolean onQueryTextChange(String newText) {
    Intrinsics.checkNotNullParameter(newText, "newText");
    return true;
  }
  public final VisitRecyclerViewAdapter getMAdapter() {
    return this.mAdapter;
  }
  public final void setMAdapter(VisitRecyclerViewAdapter visitRecyclerViewAdapter) {
    Intrinsics.checkNotNullParameter(visitRecyclerViewAdapter, "<set-?>");
    this.mAdapter = visitRecyclerViewAdapter;
  }
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    getActivityComponent().inject(this);
    Context context = getContext();
    Intrinsics.checkNotNull(context);
    LocalBroadcastManager.getInstance(context).registerReceiver(this.mReceiver, new IntentFilter(IntentExtraName.ACTION_TRANSFER_FICHE));
  }
  public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
    Intrinsics.checkNotNullParameter(inflater, "inflater");
    View viewOnCreateView = super.onCreateView(inflater, viewGroup, bundle);
    getMSwipeRefreshLayout().setEnabled(true);
    getMSwipeRefreshLayout().setRefreshing(false);
    getMSwipeRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      public final void onRefresh() {
        VisitListFragment.onCreateViewlambda0(this.f0);
      }
    });
    return viewOnCreateView;
  }
  public static final void onCreateViewlambda0(VisitListFragment this0) {
    Intrinsics.checkNotNullParameter(this0, "this0");
    this0.restartLoader();
  }
  public void onActivityCreated(Bundle bundle) {
    super.onActivityCreated(bundle);
    this.mAdapter.setmActivity(getActivity());
    this.mAdapter.setmVisitListFragment(this);
  }
  public void onResume() {
    super.onResume();
    restartLoader();
  }
  public void onDestroy() {
    super.onDestroy();
    Context context = getContext();
    Intrinsics.checkNotNull(context);
    LocalBroadcastManager.getInstance(context).unregisterReceiver(this.mReceiver);
  }
  protected IListRecyclerView getAdapter() {
    return this.mAdapter;
  }
  protected void createOptionsMenu(Menu menu, MenuInflater inflater) {
    Intrinsics.checkNotNullParameter(menu, "menu");
    Intrinsics.checkNotNullParameter(inflater, "inflater");
    inflater.inflate(R.menu.menu_search, menu);
    createSearchView(menu.findItem(R.id.menu_search));
    inflater.inflate(R.menu.menu_sales_list, menu);
    super.createOptionsMenu(menu, inflater);
  }
  public boolean onOptionsItemSelected(MenuItem item) {
    Intrinsics.checkNotNullParameter(item, "item");
    if (item.getItemId() == R.id.menu_new && ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
      ProgressDialogBuilder<?> mProgressDialogBuilder = getMProgressDialogBuilder();
      Context context = getContext();
      Intrinsics.checkNotNull(context);
      mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
      this.viewModel.getBaseErp().checkRemoteWorkTimeControl(WorkTimeControlProcessType.Visit, new CheckWorkTimeListener(this));
    }
    return super.onOptionsItemSelected(item);
  }
  private static final class CheckWorkTimeListener implements ResponseListener<String> {
    private final WeakReference<VisitListFragment> mFragment;

    public CheckWorkTimeListener(VisitListFragment visitListFragment) {
      this.mFragment = new WeakReference<>(visitListFragment);
    }

    @Override // com.proje.mobilesales.core.interfaces.ResponseListener
    public void onResponse(String str) {
      if (this.mFragment.get() != null) {
        VisitListFragment visitListFragment = this.mFragment.get();
        Intrinsics.checkNotNull(visitListFragment);
        if (visitListFragment.isAttached()) {
          VisitListFragment visitListFragment2 = this.mFragment.get();
          Intrinsics.checkNotNull(visitListFragment2);
          visitListFragment2.getMProgressDialogBuilder().dismiss();
          if (!TextUtils.isEmpty(str)) {
            VisitListFragment visitListFragment3 = this.mFragment.get();
            Intrinsics.checkNotNull(visitListFragment3);
            Toast.makeText(visitListFragment3.getActivity(), str, 0).show();
          } else {
            VisitListFragment visitListFragment4 = this.mFragment.get();
            Intrinsics.checkNotNull(visitListFragment4);
            visitListFragment4.startVisitActivity();
          }
        }
      }
    }

    @Override
    public void onResponse(ArrayList<String> obj) {

    }

    @Override
    public void onResponse() {

    }

    @Override
    public void onResponse(Integer integer) {

    }

    @Override // com.proje.mobilesales.core.interfaces.ResponseListener
    public void onError(String errorMessage) {
      Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
      if (this.mFragment.get() != null) {
        VisitListFragment visitListFragment = this.mFragment.get();
        Intrinsics.checkNotNull(visitListFragment);
        if (visitListFragment.isAttached()) {
          VisitListFragment visitListFragment2 = this.mFragment.get();
          Intrinsics.checkNotNull(visitListFragment2);
          visitListFragment2.getMProgressDialogBuilder().dismiss();
        }
      }
    }

    @Override
    public void onFailure(Throwable throwable) {

    }

    @Override
    public void onResponse(Boolean aBoolean) {

    }

    @Override
    public void onResponse(Sales sales) {

    }

    @Override
    public void onResponse(TigerServiceResult tigerServiceResult) {

    }
  }
  public final void startVisitActivity() {
    CustomerOperation customerOperation = this.mCustomerOperation;
    Intrinsics.checkNotNull(customerOperation);
    customerOperation.setFicheMode(FicheMode.NEW);
    Intent intent = new Intent(getActivity(), (Class<?>) VisitEnterActivityNew.class);
    intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_REF, this.mCustomerRef);
    intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
    intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
    intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, this.routeDayRef);
    intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
    Context context = getContext();
    Intrinsics.checkNotNull(context);
    context.startActivity(intent);
  }
  protected void createSearchView(MenuItem menuItem) {
    ActionViewResolver actionViewResolver = this.mActionViewResolver;
    Intrinsics.checkNotNull(actionViewResolver);
    View actionView = actionViewResolver.getActionView(menuItem);
    Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
    SearchView searchView = (SearchView) actionView;
    searchView.setQueryHint(getString(R.string.hint_search_visit));
    FragmentActivity activity = getActivity();
    Object systemService = activity != null ? activity.getSystemService(FirebaseAnalytics.Event.SEARCH) : null;
    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.SearchManager");
    FragmentActivity activity2 = getActivity();
    Intrinsics.checkNotNull(activity2);
    searchView.setSearchableInfo(((SearchManager) systemService).getSearchableInfo(activity2.getComponentName()));
    searchView.setIconified(!this.mSearchViewExpanded);
    searchView.setQuery(this.mFilter, false);
    searchView.setOnQueryTextListener(this);
    View viewFindViewById = searchView.findViewById(R.id.search_src_text);
    Intrinsics.checkNotNull(viewFindViewById, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView.SearchAutoComplete");
    SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) viewFindViewById;
    searchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.white));
    Drawable textCursorDrawable = searchAutoComplete.getTextCursorDrawable();
    if (textCursorDrawable != null) {
      Context context = getContext();
      Intrinsics.checkNotNull(context);
      textCursorDrawable.setColorFilter(ContextCompat.getColor(context, R.color.custom_cursor_color), PorterDuff.Mode.SRC_IN);
      searchAutoComplete.setTextCursorDrawable(textCursorDrawable);
    }
    searchView.setOnSearchClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.visit.view.fragment.VisitListFragmentExternalSyntheticLambda2
      @Override // android.view.View.OnClickListener
      public final void onClick(View view) {
        VisitListFragment.createSearchViewlambda1(this.f0, view);
      }
    });
    searchView.setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.proje.mobilesales.features.visit.view.fragment.VisitListFragmentExternalSyntheticLambda3
      @Override // androidx.appcompat.widget.SearchView.OnCloseListener
      public final boolean onClose() {
        return VisitListFragment.createSearchViewlambda2(this.f0);
      }
    });
  }
  public static final void createSearchViewlambda1(VisitListFragment this0, View v) {
    Intrinsics.checkNotNullParameter(this0, "this0");
    Intrinsics.checkNotNullParameter(v, "v");
    this0.mSearchViewExpanded = true;
    v.requestFocus();
  }
  public static final boolean createSearchViewlambda2(VisitListFragment this0) {
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
    this.mSearchViewExpanded = false;
    this.mFilter = str;
    if (this.isSearchUsed) {
      return;
    }
    restartLoader();
  }
  public void restartLoader() {
    this.mAdapter.restartScroll();
    this.mAdapter.notifyDataSetChanged();
    this.viewModel.getVisitList(getLoaderSqlText(50, 0), new VisitListResponseListener(this));
    this.mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
      public final void onLoadMore(int i2, int i3) {
        VisitListFragment.restartLoaderlambda3(f0,
            i2,
            i3);
      }
    });
  }
  public static final void restartLoaderlambda3(VisitListFragment this0, int i2, int i3) {
    Intrinsics.checkNotNullParameter(this0, "this0");
    this0.callLoader(i2, i3);
  }
  private final void callLoader(int i2, int i3) {
    this.viewModel.getVisitList(getLoaderSqlText(i2, i3), new VisitListResponseListener(this));
  }
  public boolean onQueryTextSubmit(String query) {
    Intrinsics.checkNotNullParameter(query, "query");
    this.isSearchUsed = true;
    return false;
  }
  public String getLoaderSqlText(int i2, int i3) throws IllegalArgumentException {
    String visitListSql = this.viewModel.getBaseErp().getLogoSqlHelper().getVisitListSql(getContext(), this.viewModel.getBaseErp().getLogoSqlHelper().getClCode(this.mCustomerRef), this.mCustomerRef, this.mFilter, i2, i3);
    Intrinsics.checkNotNullExpressionValue(visitListSql, "getVisitListSql(...)");
    return visitListSql;
  }
  public final void onVisitLoads(ArrayList<ArrayList<VisitInfoShort>> arrayList) {
    this.mAdapter.addItem(arrayList);
    if (!isDetached()) {
      toggleEmptyView(this.mAdapter.getItemCount() == 0, this.mFilter);
    }
    getMProgressDialogBuilder().dismiss();
    if (getMSwipeRefreshLayout().isRefreshing()) {
      getMSwipeRefreshLayout().setRefreshing(false);
    }
  }
  public void refreshTransferAfterList(boolean z) {
    if (isAttached() && z) {
      restartLoader();
    }
  }
  public final void onVisitsLoadError(String str) {
    if (TextUtils.isEmpty(str)) {
      return;
    }
    Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
  }
  public final void openVisitFiche(Visit visit, FicheMode ficheMode) {
    Intrinsics.checkNotNullParameter(visit, "visit");
    Intrinsics.checkNotNullParameter(ficheMode, "ficheMode");
    if (ficheMode == FicheMode.ANALYSE) {
      openVisitFicheAnalyzeMode(visit);
      return;
    }
    if (visit.getAuto() == 0) {
      if (ficheMode == FicheMode.COPY) {
        openVisitFicheCopyMode(visit);
        return;
      } else {
        if (ficheMode == FicheMode.EDIT) {
          openVisitFicheEditMode(visit);
          return;
        }
        return;
      }
    }
    Toast.makeText(getContext(), R.string.exp_93_error_auto_visit_operations, Toast.LENGTH_LONG).show();
  }
  private final void openVisitFicheAnalyzeMode(Visit visit) {
    CustomerOperation customerOperation = this.mCustomerOperation;
    Intrinsics.checkNotNull(customerOperation);
    customerOperation.setFicheMode(FicheMode.ANALYSE);
    Intent intent = new Intent(getActivity(), (Class<?>) VisitEnterActivityNew.class);
    intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_REF, this.mCustomerRef);
    intent.putExtra(VisitEnterActivityNew.EXTRA_VISIT, visit);
    intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
    intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
    intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, this.routeDayRef);
    intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
    Context context = getContext();
    Intrinsics.checkNotNull(context);
    context.startActivity(intent);
  }
  private final void openVisitFicheEditMode(Visit visit) {
    CustomerOperation customerOperation = this.mCustomerOperation;
    Intrinsics.checkNotNull(customerOperation);
    customerOperation.setFicheMode(FicheMode.EDIT);
    Intent intent = new Intent(getActivity(), (Class<?>) VisitEnterActivityNew.class);
    intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_REF, this.mCustomerRef);
    intent.putExtra(VisitEnterActivityNew.EXTRA_VISIT, visit);
    intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
    intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
    intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, this.routeDayRef);
    intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
    Context context = getContext();
    Intrinsics.checkNotNull(context);
    context.startActivity(intent);
  }
  private final void openVisitFicheCopyMode(Visit visit) {
    CustomerOperation customerOperation = this.mCustomerOperation;
    Intrinsics.checkNotNull(customerOperation);
    customerOperation.setFicheMode(FicheMode.COPY);
    Intent intent = new Intent(getActivity(), (Class<?>) VisitEnterActivityNew.class);
    intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_REF, this.mCustomerRef);
    intent.putExtra(VisitEnterActivityNew.EXTRA_VISIT, visit);
    intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
    intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
    intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, this.routeDayRef);
    intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
    Context context = getContext();
    Intrinsics.checkNotNull(context);
    context.startActivity(intent);
  }
  private static final class VisitListResponseListener implements ResponseListener<ArrayList<VisitInfoShort>> {
    private final WeakReference<VisitListFragment> mVisitListFragment;

    public VisitListResponseListener(VisitListFragment visitListFragment) {
      this.mVisitListFragment = new WeakReference<>(visitListFragment);
    }
    public void onResponse(ArrayList<ArrayList<VisitInfoShort>> arrayList) {
      if (this.mVisitListFragment.get() != null) {
        VisitListFragment visitListFragment = this.mVisitListFragment.get();
        Intrinsics.checkNotNull(visitListFragment);
        if (visitListFragment.isAttached()) {
          VisitListFragment visitListFragment2 = this.mVisitListFragment.get();
          Intrinsics.checkNotNull(visitListFragment2);
          visitListFragment2.onVisitLoads(arrayList);
        }
      }
    }

    @Override
    public void onResponse() {

    }

    @Override
    public void onResponse(Integer integer) {

    }

    public void onError(String errorMessage) {
      Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
      if (this.mVisitListFragment.get() != null) {
        VisitListFragment visitListFragment = this.mVisitListFragment.get();
        Intrinsics.checkNotNull(visitListFragment);
        if (visitListFragment.isAttached()) {
          Log.d(MobileSales.TAG, "onError: " + errorMessage);
          VisitListFragment visitListFragment2 = this.mVisitListFragment.get();
          Intrinsics.checkNotNull(visitListFragment2);
          visitListFragment2.onVisitsLoadError(errorMessage);
        }
      }
    }

    @Override
    public void onFailure(Throwable throwable) {

    }

    @Override
    public void onResponse(Boolean aBoolean) {

    }

    @Override
    public void onResponse(Sales sales) {

    }

    @Override
    public void onResponse(TigerServiceResult tigerServiceResult) {

    }
  }
}
