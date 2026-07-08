package com.proje.mobilesales.features.penetration.view.list;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
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
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.SearchAutoComplete;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseFicheActivity;
import com.proje.mobilesales.core.base.BaseFicheListFragment;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.GetLoaderSqlText;
import com.proje.mobilesales.core.interfaces.OnLoadMoreListener;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.interfaces.TransferAfterRefreshList;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.penetration.model.database.Penetration;
import com.proje.mobilesales.features.penetration.model.database.PenetrationShort;
import com.proje.mobilesales.features.penetration.repository.PenetrationListRepository;
import com.proje.mobilesales.features.penetration.view.detail.PenetrationActivity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class PenetrationListFragment extends BaseFicheListFragment implements SearchView.OnQueryTextListener, GetLoaderSqlText, TransferAfterRefreshList {
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private int mAutoVisitId;
    public AlertDialogBuilder<?> mNewPenetrationAlertDialogBuilder;
    private PenetrationRecyclerViewAdapter mAdapter = new PenetrationRecyclerViewAdapter();
    private final PenetrationListRepository repository = new PenetrationListRepository();
    private final PenetrationListViewModel viewModel = new PenetrationListViewModel(this.repository);
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        final   PenetrationListFragment this0 = null;
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            if (this.this0.isAttached()) {
                this.this0.refreshTransferAfterList(intent.getBooleanExtra(IntentExtraName.EXTRA_TRANSFER_STATUS, false));
            }
        }
    };
 
    public static void showDeleteAndInsertPenetrationDialoglambda2(DialogInterface dialogInterface, int i) {
    } 
    public boolean onQueryTextChange(String str) {
        Intrinsics.checkNotNullParameter(str, "newText");
        return true;
    } 
    public boolean onQueryTextSubmit(String str) {
        Intrinsics.checkNotNullParameter(str, "query");
        return false;
    }

    public PenetrationRecyclerViewAdapter getMAdapter() {
        return this.mAdapter;
    }

    public void setMAdapter(PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter) {
        Intrinsics.checkNotNullParameter(penetrationRecyclerViewAdapter, "<set-?>");
        this.mAdapter = penetrationRecyclerViewAdapter;
    }

    public int getMAutoVisitId() {
        return this.mAutoVisitId;
    }

    public void setMAutoVisitId(int i) {
        this.mAutoVisitId = i;
    }
     public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        LocalBroadcastManager.getInstance(context).registerReceiver(this.mReceiver, new IntentFilter(IntentExtraName.ACTION_TRANSFER_FICHE));
        Context requireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity);
        Context requireContext2 = requireContext();
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mNewPenetrationAlertDialogBuilder = new AlertDialogBuilder.Impl(requireContext2, (BaseInjectableActivity) activity2);
    }
 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        getMSwipeRefreshLayout().setEnabled(true);
        getMSwipeRefreshLayout().setRefreshing(false);
        getMSwipeRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { 
            public void onRefresh() {
                PenetrationListFragment.onCreateViewlambda0(PenetrationListFragment.this);
            }
        });
        return onCreateView;
    } 
    public static void onCreateViewlambda0(PenetrationListFragment penetrationListFragment) {
        Intrinsics.checkNotNullParameter(penetrationListFragment, "this0");
        penetrationListFragment.restartLoader();
    } 
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        createPenetrationFormSelectDialog();
        this.mAdapter.setPenetrationListFragment(this);
    } 
    public void onResume() {
        super.onResume();
        restartLoader();
    } 
    public void prepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.findItem(R.id.menu_filter).setVisible(false);
        menu.findItem(R.id.menu_sort).setVisible(false);
        super.prepareOptionsMenu(menu);
    } 
    public void createOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(menuInflater, "inflater");
        menuInflater.inflate(R.menu.menu_sales_list, menu);
        super.createOptionsMenu(menu, menuInflater);
    } 
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() == R.id.menu_new && ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            ProgressDialogBuilder<?> mProgressDialogBuilder = getMProgressDialogBuilder();
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
            this.viewModel.getCheckRemoteWorkTimeControl(WorkTimeControlProcessType.Penetration, new CheckWorkTimeListener(this));
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void checkAutoTimeAndTimeZoneEnabledAndShowDialog() {
        if (ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            ProgressDialogBuilder<?> mProgressDialogBuilder = getMProgressDialogBuilder();
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
            this.viewModel.getCheckRemoteWorkTimeControl(WorkTimeControlProcessType.Penetration, new CheckWorkTimeListener(this));
        }
    }

    private record CheckWorkTimeListener(
            WeakReference<PenetrationListFragment> mListFragmentWeakReference) implements ResponseListener<String> {
            private CheckWorkTimeListener(PenetrationListFragment mListFragmentWeakReference) {
                this.mListFragmentWeakReference = new WeakReference<>(mListFragmentWeakReference);
            }

            public void onResponse(PrintSlipModel str) {
                if (this.mListFragmentWeakReference.get() != null) {
                    PenetrationListFragment penetrationListFragment = this.mListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(penetrationListFragment);
                    if (penetrationListFragment.isAttached()) {
                        PenetrationListFragment penetrationListFragment2 = this.mListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(penetrationListFragment2);
                        penetrationListFragment2.getMProgressDialogBuilder().dismiss();
                        if (!TextUtils.isEmpty(str)) {
                            PenetrationListFragment penetrationListFragment3 = this.mListFragmentWeakReference.get();
                            Intrinsics.checkNotNull(penetrationListFragment3);
                            Toast.makeText(penetrationListFragment3.getContext(), str, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        PenetrationListFragment penetrationListFragment4 = this.mListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(penetrationListFragment4);
                        penetrationListFragment4.showPenetrationFormSelectDialog();
                    }
                }
            }

        public void onError(String str) {
                Intrinsics.checkNotNullParameter(str, "errorMessage");
                if (this.mListFragmentWeakReference.get() != null) {
                    PenetrationListFragment penetrationListFragment = this.mListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(penetrationListFragment);
                    if (penetrationListFragment.isAttached()) {
                        PenetrationListFragment penetrationListFragment2 = this.mListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(penetrationListFragment2);
                        penetrationListFragment2.getMProgressDialogBuilder().dismiss();
                    }
                }
            }
        }

    private void createPenetrationFormSelectDialog() {
        Cursor query = this.viewModel.getSqlBriteDatabase().query(getString(R.string.qry_get_penetration_form_list));
        if (query == null || query.getCount() == 0) {
            Toast.makeText(getActivity(), getString(R.string.str_not_found_penetration_form), Toast.LENGTH_SHORT).show();
            return;
        }
        if (!query.isClosed()) {
            query.close();
        }
        List<Penetration> tableWhereForPenetration = this.viewModel.getTableWhereForPenetration(Penetration.class, "STRFTIME('%Y-%m-%d ', DATE('now')) BETWEEN STRFTIME('%Y-%m-%d ', DATE(BEGINDATE)) AND STRFTIME('%Y-%m-%d ', DATE(ENDDATE))", new String[0], "LOGICALREF");
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle(R.string.str_select_penetration_form_title).setSingleChoiceItems(tableWhereForPenetration, 0, getString(R.string.column_code), new DialogInterface.OnClickListener(tableWhereForPenetration, this) { // from class: com.proje.mobilesales.features.penetration.view.list.PenetrationListFragmentExternalSyntheticLambda0
            public final List f0 = Collections.emptyList();
            public final PenetrationListFragment f1 = null;
  
            public void onClick(DialogInterface dialogInterface, int i) {
                PenetrationListFragment.createPenetrationFormSelectDialoglambda1(this.f0, this.f1, dialogInterface, i);
            }
        }).create();
    }
    public static void createPenetrationFormSelectDialoglambda1(List list, PenetrationListFragment penetrationListFragment, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(list, "penetrationList");
        Intrinsics.checkNotNullParameter(penetrationListFragment, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
        if (list.size() > i) {
            int logicalRef = ((Penetration) list.get(i)).getLogicalRef();
            Cursor isCustomerEnterPenetrationToday = penetrationListFragment.viewModel.getBaseErp().getLogoSqlHelper().isCustomerEnterPenetrationToday(penetrationListFragment.mCustomerRef, logicalRef);
            if (isCustomerEnterPenetrationToday == null || !isCustomerEnterPenetrationToday.moveToFirst())
                penetrationListFragment.startPenetrationActivity(logicalRef);
            else {
                int i2 = isCustomerEnterPenetrationToday.getInt(isCustomerEnterPenetrationToday.getColumnIndex("ID"));
                boolean convertIntToBoolean = StringUtils.convertIntToBoolean(isCustomerEnterPenetrationToday.getInt(isCustomerEnterPenetrationToday.getColumnIndex("ISTRANSFER")));
                String string = isCustomerEnterPenetrationToday.getString(isCustomerEnterPenetrationToday.getColumnIndex("GUID"));
                Intrinsics.checkNotNull(string);
                penetrationListFragment.showDeleteAndInsertPenetrationDialog(logicalRef, i2, convertIntToBoolean, string);
                if (!isCustomerEnterPenetrationToday.isClosed()) {
                    isCustomerEnterPenetrationToday.close();
                }
            }
        } else {
            String string2 = penetrationListFragment.getString(R.string.str_not_select_penetration_form);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            penetrationListFragment.showToast(string2);
        }
        dialogInterface.dismiss();
    }
    public void showPenetrationFormSelectDialog() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.show();
    }

    @SuppressLint("ResourceType")
    private void showDeleteAndInsertPenetrationDialog(int i, int i2, boolean z, String str) {
        AlertDialogBuilder<?> alertDialogBuilder = this.mNewPenetrationAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setMessage(StringsKt.trimIndent("\n    " + getString(R.string.str_enter_only_one_form_day) + "\n    " + getString(R.string.str_question_delete_old_entry_add) + "\n    ")).setTitle(R.string.str_warning).setNegativeButton(17039360, new DialogInterface.OnClickListener() { 

            public void onClick(DialogInterface dialogInterface, int i3) {
                PenetrationListFragment.showDeleteAndInsertPenetrationDialoglambda2(dialogInterface, i3);
            }
        }).setPositiveButton(17039370, new DialogInterface.OnClickListener(z, this, str, i2, i) {
            public final boolean f0 = false;
            public final PenetrationListFragment f1 = null;
            public final String f2 = "";
            public final int f3 = 0;
            public final int f4 = 0;
            public void onClick(DialogInterface dialogInterface, int i3) {
                PenetrationListFragment.showDeleteAndInsertPenetrationDialoglambda3(this.f0, this.f1, this.f2, this.f3, this.f4, dialogInterface, i3);
            }
        }).create().show();
    }
    public static void showDeleteAndInsertPenetrationDialoglambda3(boolean z, PenetrationListFragment penetrationListFragment, String str, int i, int i2, DialogInterface dialogInterface, int i3) {
        Intrinsics.checkNotNullParameter(penetrationListFragment, "this0");
        Intrinsics.checkNotNullParameter(str, "guid");
        if (z) {
            penetrationListFragment.viewModel.getBaseErp().deletePenetrationRemote(str, new ResponseListener<Boolean>(penetrationListFragment, i, i2) { // from class: com.proje.mobilesales.features.penetration.view.list.PenetrationListFragmentshowDeleteAndInsertPenetrationDialog21
                final int savedPenetration = 0;
                final int selectedPenetration = 0;
                final PenetrationListFragment this0 = null;

                public void onResponse(PrintSlipModel bool) {
                    this.this0.getMAdapter().deletePenetration(this.savedPenetration, 0);
                    this.this0.startPenetrationActivity(this.selectedPenetration);
                }
                public void onError(String str2) {
                    Intrinsics.checkNotNullParameter(str2, "errorMessage");
                    if (this.this0.getContext() != null) {
                        Toast.makeText(this.this0.getContext(), str2, Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return;
        }
        penetrationListFragment.mAdapter.deletePenetration(i, 0);
        penetrationListFragment.startPenetrationActivity(i2);
    }
    public void startPenetrationActivity(int i) {
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        customerOperation.setFicheMode(FicheMode.NEW);
        Intent intent = new Intent(getActivity(), PenetrationActivity.class);
        intent.putExtra(PenetrationActivity.Companion.getEXTRA_PENETRATION_ID(), i);
        intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, this.routeDayRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        context.startActivity(intent);
    }
    protected IListRecyclerView getAdapter() {
        return this.mAdapter;
    }
    @SuppressLint("ResourceType")
    protected void createSearchView(MenuItem menuItem) {
        Drawable drawable;
        ActionViewResolver actionViewResolver = this.mActionViewResolver;
        Intrinsics.checkNotNull(actionViewResolver);
        View actionView = actionViewResolver.getActionView(menuItem);
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        SearchView searchView = (SearchView) actionView;
        searchView.setQueryHint(getString(R.string.hint_search_penetration));
        FragmentActivity activity = getActivity();
        Object systemService = activity != null ? activity.getSystemService(Context.SEARCH_SERVICE) : null;
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.SearchManager");
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        searchView.setSearchableInfo(((SearchManager) systemService).getSearchableInfo(activity2.getComponentName()));
        searchView.setIconified(!this.mSearchViewExpanded);
        searchView.setQuery(this.mFilter, false);
        searchView.setOnQueryTextListener(this);
        View findViewById = searchView.findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView.SearchAutoComplete");
        SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) findViewById;
        searchAutoComplete.setHintTextColor(getResources().getColor(17170443));
        if (Build.VERSION.SDK_INT >= 29 && (drawable = searchAutoComplete.getTextCursorDrawable()) != null) {
            drawable.setColorFilter(ContextCompat.getColor(requireContext(), R.color.custom_cursor_color), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setTextCursorDrawable(drawable);
        }
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PenetrationListFragment.createSearchViewlambda4(PenetrationListFragment.this, view);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            public boolean onClose() {
                return PenetrationListFragment.createSearchViewlambda5(PenetrationListFragment.this);
            }
        });
    }
    public static void createSearchViewlambda4(PenetrationListFragment penetrationListFragment, View view) {
        Intrinsics.checkNotNullParameter(penetrationListFragment, "this0");
        Intrinsics.checkNotNullParameter(view, "v");
        penetrationListFragment.mSearchViewExpanded = true;
        view.requestFocus();
    }
    public static boolean createSearchViewlambda5(PenetrationListFragment penetrationListFragment) {
        Intrinsics.checkNotNullParameter(penetrationListFragment, "this0");
        penetrationListFragment.mFilter = "";
        AppCompatActivity appCompatActivity = (AppCompatActivity) penetrationListFragment.getActivity();
        Intrinsics.checkNotNull(appCompatActivity);
        ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setSubtitle(penetrationListFragment.mFilter);
        penetrationListFragment.restartLoader();
        return false;
    }
    public void filter(String str) {
        this.mSearchViewExpanded = false;
        this.mFilter = str;
        restartLoader();
    }
    public void restartLoader() {
        this.mAdapter.restartScroll();
        this.mAdapter.notifyDataSetChanged();
        this.viewModel.getPenetrationList(getLoaderSqlText(50, 0), new PenetrationListResponseListener(this));
        this.mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            public void onLoadMore(int i, int i2) {
                PenetrationListFragment.restartLoaderlambda6(PenetrationListFragment.this, i, i2);
            }
        });
    }
    public static void restartLoaderlambda6(PenetrationListFragment penetrationListFragment, int i, int i2) {
        Intrinsics.checkNotNullParameter(penetrationListFragment, "this0");
        penetrationListFragment.callLoader(i, i2);
    }

    private void callLoader(int i, int i2) {
        this.viewModel.getPenetrationList(getLoaderSqlText(i, i2), new PenetrationListResponseListener(this));
    }
    public String getLoaderSqlText(int i, int i2) throws IllegalArgumentException {
        String penetrationListSql = this.viewModel.getSqlHelper().getPenetrationListSql(getContext(), this.viewModel.getBaseErp().getLogoSqlHelper().getClCode(this.mCustomerRef), this.mCustomerRef, i, i2);
        Intrinsics.checkNotNullExpressionValue(penetrationListSql, "getPenetrationListSql(...)");
        return penetrationListSql;
    }
    public void refreshTransferAfterList(boolean z) {
        if (z) {
            restartLoader();
        }
    }

    private void onPenetrationsLoad(ArrayList<PenetrationShort> arrayList) {
        if (arrayList != null) {
            this.mAdapter.addItem(arrayList);
        }
        if (!isDetached()) {
            toggleEmptyView(this.mAdapter.getItemCount() == 0, this.mFilter);
        }
        getMProgressDialogBuilder().dismiss();
        if (getMSwipeRefreshLayout().isRefreshing()) {
            getMSwipeRefreshLayout().setRefreshing(false);
        }
    }
    public void showToast(String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
        }
    }
    public void openPenetrationFiche(int i, FicheMode ficheMode) {
        Intrinsics.checkNotNullParameter(ficheMode, "ficheMode");
        if (ficheMode == FicheMode.ANALYSE) {
            openPenetrationFicheAnalyzeMode(i);
        } else if (ficheMode == FicheMode.COPY) {
            openPenetrationFicheCopyMode(i);
        } else if (ficheMode == FicheMode.EDIT) {
            openPenetrationFicheEditMode(i);
        }
    }
    private void openPenetrationFicheAnalyzeMode(int i) {
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        customerOperation.setFicheMode(FicheMode.ANALYSE);
        Intent intent = new Intent(getActivity(), PenetrationActivity.class);
        intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        intent.putExtra(PenetrationActivity.Companion.getEXTRA_PENETRATION(), i);
        intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, this.routeDayRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        context.startActivity(intent);
    }
    private void openPenetrationFicheEditMode(int i) {
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        customerOperation.setFicheMode(FicheMode.EDIT);
        Intent intent = new Intent(getActivity(), PenetrationActivity.class);
        intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        intent.putExtra(PenetrationActivity.Companion.getEXTRA_PENETRATION(), i);
        intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, this.routeDayRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        context.startActivity(intent);
    }
    private void openPenetrationFicheCopyMode(int i) {
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        customerOperation.setFicheMode(FicheMode.COPY);
        Intent intent = new Intent(getActivity(), PenetrationActivity.class);
        intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        intent.putExtra(PenetrationActivity.Companion.getEXTRA_PENETRATION(), i);
        intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, this.routeDayRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        context.startActivity(intent);
    }
    public static final class PenetrationListResponseListener implements ResponseListener<ArrayList<PenetrationShort>> {
        private final WeakReference<PenetrationListFragment> mPenetrationListFragment;

        public PenetrationListResponseListener(PenetrationListFragment penetrationListFragment) {
            this.mPenetrationListFragment = new WeakReference<>(penetrationListFragment);
        }
        public void onResponse(PrintSlipModel arrayList) {
            if (this.mPenetrationListFragment.get() != null) {
                PenetrationListFragment penetrationListFragment = this.mPenetrationListFragment.get();
                Intrinsics.checkNotNull(penetrationListFragment);
                if (penetrationListFragment.isAttached()) {
                    PenetrationListFragment penetrationListFragment2 = this.mPenetrationListFragment.get();
                    Intrinsics.checkNotNull(penetrationListFragment2);
                    penetrationListFragment2.onPenetrationsLoad(arrayList);
                }
            }
        }
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mPenetrationListFragment.get() != null) {
                PenetrationListFragment penetrationListFragment = this.mPenetrationListFragment.get();
                Intrinsics.checkNotNull(penetrationListFragment);
                if (penetrationListFragment.isAttached()) {
                    Log.d(MobileSales.TAG, "onError: " + str);
                    PenetrationListFragment penetrationListFragment2 = this.mPenetrationListFragment.get();
                    Intrinsics.checkNotNull(penetrationListFragment2);
                    penetrationListFragment2.showToast(str);
                }
            }
        }
    }
}
