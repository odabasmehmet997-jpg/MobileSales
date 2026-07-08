package com.proje.mobilesales.features.sales.view.distribution;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.widget.SlideInLeftAnimator;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.sales.model.Distribution;
import com.proje.mobilesales.features.sales.view.newadd.SalesActivityNew;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
 
public final class DistributionListActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = DistributionListActivity.class.getName();
    public ActionViewResolver mActionViewResolver;
    private DistributionListRecyclerViewAdapter mAdapter;
    private int mCustomerRef;
    private View mEmptyView;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private RecyclerView mRecyclerView;
    private boolean mSearchViewExpanded;
    private boolean result;
    private SalesType selection;
    private ArrayList<Distribution> mData = new ArrayList<>();
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void setThis0(DistributionListActivity this0) {
            this.this0 = this0;
        }

        private DistributionListActivity this0;

        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            this.this0.refreshList();
        }
    };

    public   ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return this.mProgressDialogBuilder;
    }

    public   void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressDialogBuilder = progressDialogBuilder;
    }

    public   SalesType getSelection() {
        return this.selection;
    }

    public   void setSelection(SalesType salesType) {
        this.selection = salesType;
    }

    public   boolean getResult() {
        return this.result;
    }

    public   void setResult(boolean z) {
        this.result = z;
    }

    public BroadcastReceiver getMReceiver() {
        return this.mReceiver;
    }

    public void setMReceiver(BroadcastReceiver broadcastReceiver) {
        Intrinsics.checkNotNullParameter(broadcastReceiver, "<set-?>");
        this.mReceiver = broadcastReceiver;
    }
     public void onCreate(Bundle bundle) {
        this.analyticsEventType = AnalyticsEventType.DISTRIBUTION;
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Context context = ContextUtils.getmContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
        this.mActionViewResolver = new ActionViewResolver();
        Bundle extras = getIntent().getExtras();
        Intrinsics.checkNotNull(extras);
        this.selection = (SalesType) extras.get(IntentExtraName.EXTRA_SALES_TYPE);
        this.mCustomerRef = getIntent().getIntExtra(IntentExtraName.EXTRA_CUSTOMER_REF, -1);
        setContentView(R.layout.distribution_list);
        View viewFindViewById = findViewById(R.id.empty);
        this.mEmptyView = viewFindViewById;
        Intrinsics.checkNotNull(viewFindViewById);
        viewFindViewById.setVisibility(View.GONE);
        RecyclerView recyclerView = findViewById(R.id.rwDistributionListView);
        this.mRecyclerView = recyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView2);
        recyclerView2.setHasFixedSize(true);
        RecyclerView recyclerView3 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView3);
        recyclerView3.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(2.0f)));
        DistributionListRecyclerViewAdapter distributionListRecyclerViewAdapter = new DistributionListRecyclerViewAdapter();
        this.mAdapter = distributionListRecyclerViewAdapter;
        Intrinsics.checkNotNull(distributionListRecyclerViewAdapter);
        distributionListRecyclerViewAdapter.initDisplayOptions(this);
        DistributionListRecyclerViewAdapter distributionListRecyclerViewAdapter2 = this.mAdapter;
        Intrinsics.checkNotNull(distributionListRecyclerViewAdapter2);
        distributionListRecyclerViewAdapter2.setSalesType(this.selection);
        setToolbar();
        refreshList();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mReceiver, new IntentFilter(IntentExtraName.ACTION_TRANSFER_FICHE));
    }
   public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mReceiver);
    } 
    public void onBackPressed() {
        super.onBackPressed();
        RecyclerView recyclerView = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setAdapter(null);
    } 
    private void refreshList() {
        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
        this.baseErp.getOnlineDistributionList(this.selection != SalesType.FREE ? this.mCustomerRef : -9999, new OnlineDistributionListener(this));
    }
 
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        MenuInflater menuInflater = getMenuInflater();
        Intrinsics.checkNotNullExpressionValue(menuInflater, "getMenuInflater(...)");
        menuInflater.inflate(R.menu.menu_search, menu);
        createSearchView(menu.findItem(R.id.menu_search), menu);
        return super.onCreateOptionsMenu(menu);
    } 
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void createSearchView(MenuItem menuItem, Menu menu) {
        ActionViewResolver actionViewResolver = this.mActionViewResolver;
        Intrinsics.checkNotNull(actionViewResolver);
        View actionView = actionViewResolver.getActionView(menuItem);
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        SearchView searchView = (SearchView) actionView;
        searchView.setQueryHint(getString(R.string.hint_search_order));
        Object systemService = getSystemService(Context.SEARCH_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.SearchManager");
        searchView.setSearchableInfo(((SearchManager) systemService).getSearchableInfo(getComponentName()));
        searchView.setIconified(!this.mSearchViewExpanded);
        View viewFindViewById = searchView.findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(viewFindViewById, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView.SearchAutoComplete");
        @SuppressLint("RestrictedApi") SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) viewFindViewById;
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.white));
        Drawable textCursorDrawable = searchAutoComplete.getTextCursorDrawable();
        if (textCursorDrawable != null) {
            textCursorDrawable.setColorFilter(ContextCompat.getColor(this, R.color.custom_cursor_color), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setTextCursorDrawable(textCursorDrawable);
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {  
            public boolean onQueryTextChange(String newText) {
                Intrinsics.checkNotNullParameter(newText, "newText");
                return false;
            } 
            public boolean onQueryTextSubmit(String query) {
                Intrinsics.checkNotNullParameter(query, "query");
                DistributionListActivity.this.filterData(query);
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() { 
            public void onClick(View view) {
                DistributionListActivity.createSearchViewlambda0(DistributionListActivity.this, view);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {  
            public boolean onClose() {
                return DistributionListActivity.createSearchViewlambda1(DistributionListActivity.this);
            }
        });
    }

    
    public static void createSearchViewlambda0(DistributionListActivity this0, View v) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(v, "v");
        this0.mSearchViewExpanded = true;
        v.requestFocus();
    }

    
    public static boolean createSearchViewlambda1(DistributionListActivity this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        ActionBar supportActionBar = this0.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setSubtitle("");
        this0.filterData("");
        return false;
    }

    public void filterData(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        if (Intrinsics.areEqual(query, "")) {
            toggleEmptyView(false);
            DistributionListRecyclerViewAdapter distributionListRecyclerViewAdapter = this.mAdapter;
            Intrinsics.checkNotNull(distributionListRecyclerViewAdapter);
            distributionListRecyclerViewAdapter.setData(this.mData);
            return;
        }
        ArrayList<Distribution> arrayList = new ArrayList<>();
        ArrayList<Distribution> arrayList2 = this.mData;
        Intrinsics.checkNotNull(arrayList2);
        Iterator<Distribution> it = arrayList2.iterator();
        while (it.hasNext()) {
            Distribution next = it.next();
            String str = next.ficheNo;
            if (str != null) {
                Intrinsics.checkNotNull(str);
                if (!StringsKt.contains(str, query, false)) {
                    String str2 = next.code;
                    if (str2 != null) {
                        Intrinsics.checkNotNull(str2);
                        if (!StringsKt.contains(str2, query, false)) {
                            String str3 = next.definition;
                            if (str3 != null) {
                                Intrinsics.checkNotNull(str3);
                                if (StringsKt.contains(str3, query, false)) {
                                }
                            }
                        }
                    }
                }
                arrayList.add(next);
            }
        }
        if (arrayList.size() > 0) {
            toggleEmptyView(false);
            DistributionListRecyclerViewAdapter distributionListRecyclerViewAdapter2 = this.mAdapter;
            Intrinsics.checkNotNull(distributionListRecyclerViewAdapter2);
            distributionListRecyclerViewAdapter2.setData(arrayList);
            return;
        }
        toggleEmptyView(true);
    }

    public void toggleEmptyView(boolean z) {
        if (!z) {
            View view = this.mEmptyView;
            Intrinsics.checkNotNull(view);
            view.setVisibility(View.GONE);
            RecyclerView recyclerView = this.mRecyclerView;
            Intrinsics.checkNotNull(recyclerView);
            recyclerView.setVisibility(View.VISIBLE);
            RecyclerView recyclerView2 = this.mRecyclerView;
            Intrinsics.checkNotNull(recyclerView2);
            recyclerView2.bringToFront();
            return;
        }
        View view2 = this.mEmptyView;
        Intrinsics.checkNotNull(view2);
        view2.setVisibility(View.VISIBLE);
        View view3 = this.mEmptyView;
        Intrinsics.checkNotNull(view3);
        view3.bringToFront();
        RecyclerView recyclerView3 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView3);
        recyclerView3.setVisibility(View.GONE);
    }

    public void finishActivityResult(ArrayList<Distribution> arrayList) {
        if (this.result) {
            Intent intent = new Intent();
            intent.putExtra(IntentExtraName.EXTRA_DISTRIBUTION_FICHE_ID, arrayList);
            setResult(-1, intent);
            finish();
            return;
        }
        FicheType ficheType = FicheType.DISPATCH;
        SalesType salesType = this.selection;
        Intrinsics.checkNotNull(salesType);
        if (SalesUtils.isSalesTypeOnlyRetailInvoice(salesType)) {
            ficheType = FicheType.RETAILINVOICE;
        } else {
            SalesType salesType2 = this.selection;
            Intrinsics.checkNotNull(salesType2);
            if (SalesUtils.isSalesTypeOnlyInvoice(salesType2)) {
                ficheType = FicheType.INVOICE;
            }
        }
        SalesType salesType3 = this.selection;
        Intrinsics.checkNotNull(salesType3);
        CustomerOperation customerOperation = new CustomerOperation(salesType3, ficheType, FicheMode.NEW);
        Intent intent2 = new Intent(this, SalesActivityNew.class);
        String str = SalesActivityNew.EXTRA_CUSTOMER_REF;
        Intrinsics.checkNotNull(arrayList);
        intent2.putExtra(str, arrayList.get(0).clientRef);
        intent2.putExtra(IntentExtraName.EXTRA_DISTRIBUTION_FICHE_ID, arrayList);
        intent2.putExtra(SalesActivityNew.EXTRA_CUSTOMER_OPERATION, customerOperation);
        startActivity(intent2);
    }

    
    public void setData(ArrayList<Distribution> arrayList) {
        this.mData = arrayList;
        DistributionListRecyclerViewAdapter distributionListRecyclerViewAdapter = this.mAdapter;
        Intrinsics.checkNotNull(distributionListRecyclerViewAdapter);
        distributionListRecyclerViewAdapter.setData(arrayList);
        RecyclerView recyclerView = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setAdapter(this.mAdapter);
        ArrayList<Distribution> arrayList2 = this.mData;
        Intrinsics.checkNotNull(arrayList2);
        toggleEmptyView(arrayList2.isEmpty());
    }

    public void goToFiche(int r1, int r2, SalesType salesType, boolean z) {
        this.selection = salesType;
        this.result = z;
        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
        this.baseErp.getSelectedDistributions(r1, r2, new SelectedDistributionListener(this));
    }

    private record SelectedDistributionListener(
            WeakReference<DistributionListActivity> mContent) implements ResponseListener<ArrayList<Distribution>> {
            private SelectedDistributionListener(DistributionListActivity mContent) {
                Intrinsics.checkNotNullParameter(mContent, "mContent");
                this.mContent = new WeakReference<>(mContent);
            }

        public void onResponse(PrintSlipModel arrayList) {
                if (this.mContent.get() != null) {
                    DistributionListActivity distributionListActivity = this.mContent.get();
                    Intrinsics.checkNotNull(distributionListActivity);
                    distributionListActivity.finishActivityResult(arrayList);
                    DistributionListActivity distributionListActivity2 = this.mContent.get();
                    Intrinsics.checkNotNull(distributionListActivity2);
                    distributionListActivity2.dismisprogress();
                }
            }

        public void onFailure(Throwable throwable) {

            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mContent.get() != null) {
                    DistributionListActivity distributionListActivity = this.mContent.get();
                    Intrinsics.checkNotNull(distributionListActivity);
                    distributionListActivity.dismisprogress();
                    Log.d(DistributionListActivity.TAG, "onError: " + errorMessage);
                    DistributionListActivity distributionListActivity2 = this.mContent.get();
                    Intrinsics.checkNotNull(distributionListActivity2);
                    distributionListActivity2.showMessage(errorMessage);
                }
            }
        }

    private record OnlineDistributionListener(
            WeakReference<DistributionListActivity> mContent) implements ResponseListener<ArrayList<Distribution>> {
            private OnlineDistributionListener(DistributionListActivity mContent) {
                Intrinsics.checkNotNullParameter(mContent, "mContent");
                this.mContent = new WeakReference<>(mContent);
            }

        public void onResponse(PrintSlipModel arrayList) {
                if (this.mContent.get() != null) {
                    DistributionListActivity distributionListActivity = this.mContent.get();
                    Intrinsics.checkNotNull(distributionListActivity);
                    distributionListActivity.setData(arrayList);
                    DistributionListActivity distributionListActivity2 = this.mContent.get();
                    Intrinsics.checkNotNull(distributionListActivity2);
                    distributionListActivity2.dismisprogress();
                }
            }

        public void onFailure(Throwable throwable) {
                if (this.mContent.get() != null) {
                    DistributionListActivity distributionListActivity = this.mContent.get();
                    Intrinsics.checkNotNull(distributionListActivity);
                    distributionListActivity.dismisprogress();
                }
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mContent.get() != null) {
                    DistributionListActivity distributionListActivity = this.mContent.get();
                    Intrinsics.checkNotNull(distributionListActivity);
                    distributionListActivity.dismisprogress();
                    Log.d(DistributionListActivity.TAG, "onError: " + errorMessage);
                    DistributionListActivity distributionListActivity2 = this.mContent.get();
                    Intrinsics.checkNotNull(distributionListActivity2);
                    distributionListActivity2.showMessage(errorMessage);
                }
            }
        }

    public   void showMessage(String str) {
        dismisprogress();
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public   void dismisprogress() {
        this.mProgressDialogBuilder.dismiss();
    }
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
