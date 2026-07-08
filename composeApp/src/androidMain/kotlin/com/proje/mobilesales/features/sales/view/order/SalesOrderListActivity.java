package com.proje.mobilesales.features.sales.view.order;

import android.R;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.widget.SlideInLeftAnimator;
import com.proje.mobilesales.databinding.EmptyListBinding;
import com.proje.mobilesales.databinding.SalesorderListBinding;
import com.proje.mobilesales.features.dbmodel.ManagerOrder;
import com.proje.mobilesales.features.sales.repository.SalesOrderRepository;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
 
public final class SalesOrderListActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = SalesOrderListActivity.class.getName();
    private final Lazy bindingdelegate = LazyKt.lazy(new Function0<SalesorderListBinding>() {
 
        public SalesorderListBinding invoke() {
            return SalesorderListBinding.inflate(SalesOrderListActivity.this.getLayoutInflater());
        }
    });
    private ActionViewResolver mActionViewResolver;
    private SalesOrderListRecyclerViewAdapter mAdapter;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private int mCustomerRef;
    private ArrayList<ManagerOrder> mData;
    private boolean mDetail;
    private EmptyListBinding mEmptyView;
    private ArrayList<Integer> mOrderDetailRefList;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private RecyclerView mRecyclerView;
    private SalesType mSalesType;
    private boolean mSearchViewExpanded;
    private int mWarehouse;
    private final SalesOrderRepository repository;
    private final SalesOrderViewModel viewModel;

    public SalesOrderListActivity() {
        SalesOrderRepository salesOrderRepository = new SalesOrderRepository();
        this.repository = salesOrderRepository;
        this.viewModel = new SalesOrderViewModel(salesOrderRepository);
        this.mSalesType = SalesType.FREE;
        this.mData = new ArrayList<>();
        this.mOrderDetailRefList = new ArrayList<>();
    }

    private SalesorderListBinding getBinding() {
        return (SalesorderListBinding) this.bindingdelegate.getValue();
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return this.mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilder = alertDialogBuilder;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return this.mProgressDialogBuilder;
    }

    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressDialogBuilder = progressDialogBuilder;
    }

    private ActionViewResolver getMActionViewResolver() {
        return this.mActionViewResolver;
    }

    private void setMActionViewResolver(ActionViewResolver actionViewResolver) {
        this.mActionViewResolver = actionViewResolver;
    }

    private EmptyListBinding getMEmptyView() {
        return this.mEmptyView;
    }

    private void setMEmptyView(EmptyListBinding emptyListBinding) {
        this.mEmptyView = emptyListBinding;
    }

    private boolean getMSearchViewExpanded() {
        return this.mSearchViewExpanded;
    }

    private void setMSearchViewExpanded(boolean z) {
        this.mSearchViewExpanded = z;
    }

     public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        setContentView(getBinding().getRoot());
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity);
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity2);
        this.mActionViewResolver = new ActionViewResolver();
        getWindow().setSoftInputMode(3);
        Bundle extras = getIntent().getExtras();
        Intrinsics.checkNotNull(extras);
        Object obj = extras.get(IntentExtraName.SALES_TYPE);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.proje.mobilesales.core.enums.SalesType");
        this.mSalesType = (SalesType) obj;
        this.mCustomerRef = getIntent().getIntExtra(IntentExtraName.EXTRA_CUSTOMER_REF, -1);
        this.mDetail = getIntent().getBooleanExtra(IntentExtraName.EXTRA_SALES_ORDER_DETAIL_LIST, false);
        this.mWarehouse = getIntent().getIntExtra(IntentExtraName.EXTRA_WAREHOUSE_REF, 0);
        this.mOrderDetailRefList = getIntent().getIntegerArrayListExtra(IntentExtraName.ORDER_DETAIL_REF_LIST);
        EmptyListBinding emptyListBinding = getBinding().empty;
        this.mEmptyView = emptyListBinding;
        Intrinsics.checkNotNull(emptyListBinding);
        emptyListBinding.getRoot().setVisibility(View.GONE);
        RecyclerView recyclerView = getBinding().rwSalesOrderListView;
        this.mRecyclerView = recyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView2);
        recyclerView2.setHasFixedSize(true);
        RecyclerView recyclerView3 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView3);
        recyclerView3.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(2.0f)));
        SalesOrderListRecyclerViewAdapter salesOrderListRecyclerViewAdapter = new SalesOrderListRecyclerViewAdapter();
        this.mAdapter = salesOrderListRecyclerViewAdapter;
        Intrinsics.checkNotNull(salesOrderListRecyclerViewAdapter);
        salesOrderListRecyclerViewAdapter.initDisplayOptions(this);
        SalesOrderListRecyclerViewAdapter salesOrderListRecyclerViewAdapter2 = this.mAdapter;
        Intrinsics.checkNotNull(salesOrderListRecyclerViewAdapter2);
        salesOrderListRecyclerViewAdapter2.setMyOrder(!Intrinsics.areEqual(this.viewModel.getOrderShipmentType(this.mSalesType), BuildConfig.NETSIS_DEMO_PASSWORD));
        SalesOrderListRecyclerViewAdapter salesOrderListRecyclerViewAdapter3 = this.mAdapter;
        Intrinsics.checkNotNull(salesOrderListRecyclerViewAdapter3);
        salesOrderListRecyclerViewAdapter3.setOrderDetail(this.mDetail);
        setToolbar();
        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
        this.viewModel.getOnlineSalesOrderList(this.mSalesType, this.mCustomerRef, this.mDetail, this.mOrderDetailRefList, new OnlineSalesOrderListener(this));
    }
     public void onBackPressed() {
        super.onBackPressed();
        RecyclerView recyclerView = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setAdapter(null);
    }
 
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
        } else if (item.getItemId() == R.id.menu_ok) {
            SalesOrderListRecyclerViewAdapter salesOrderListRecyclerViewAdapter = this.mAdapter;
            Intrinsics.checkNotNull(salesOrderListRecyclerViewAdapter);
            if (salesOrderListRecyclerViewAdapter.getSelectedOrders().size() > 0) {
                finishActivityResult();
            } else {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
 
    public void setData(ArrayList<ManagerOrder> arrayList) {
        this.mData = arrayList;
        SalesOrderListRecyclerViewAdapter salesOrderListRecyclerViewAdapter = this.mAdapter;
        Intrinsics.checkNotNull(salesOrderListRecyclerViewAdapter);
        salesOrderListRecyclerViewAdapter.setData(arrayList);
        RecyclerView recyclerView = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setAdapter(this.mAdapter);
        ArrayList<ManagerOrder> arrayList2 = this.mData;
        Intrinsics.checkNotNull(arrayList2);
        toggleEmptyView(arrayList2.size() == 0);
    }

    public void toggleEmptyView(boolean z) {
        if (!z) {
            EmptyListBinding emptyListBinding = this.mEmptyView;
            Intrinsics.checkNotNull(emptyListBinding);
            emptyListBinding.getRoot().setVisibility(View.GONE);
            RecyclerView recyclerView = this.mRecyclerView;
            Intrinsics.checkNotNull(recyclerView);
            recyclerView.setVisibility(View.VISIBLE);
            RecyclerView recyclerView2 = this.mRecyclerView;
            Intrinsics.checkNotNull(recyclerView2);
            recyclerView2.bringToFront();
            return;
        }
        EmptyListBinding emptyListBinding2 = this.mEmptyView;
        Intrinsics.checkNotNull(emptyListBinding2);
        emptyListBinding2.getRoot().setVisibility(View.VISIBLE);
        EmptyListBinding emptyListBinding3 = this.mEmptyView;
        Intrinsics.checkNotNull(emptyListBinding3);
        emptyListBinding3.getRoot().bringToFront();
        RecyclerView recyclerView3 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView3);
        recyclerView3.setVisibility(View.GONE);
    }

    public void finishActivityResult() {
        Intent intent = new Intent();
        String str = IntentExtraName.EXTRA_FICHE_ID;
        SalesOrderListRecyclerViewAdapter salesOrderListRecyclerViewAdapter = this.mAdapter;
        Intrinsics.checkNotNull(salesOrderListRecyclerViewAdapter);
        intent.putStringArrayListExtra(str, salesOrderListRecyclerViewAdapter.getSelectedOrders());
        setResult(-1, intent);
        finish();
    }
 
    private void createSearchView(MenuItem menuItem, Menu menu) {
        Drawable textCursorDrawable = null;
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
        View findViewById = searchView.findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView.SearchAutoComplete");
        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) findViewById;
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.white));
        if (Build.VERSION.SDK_INT >= 29 && textCursorDrawable != null) {
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
                SalesOrderListActivity.this.filterData(query);
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {  
            public void onClick(View view) {
                SalesOrderListActivity.createSearchViewlambda0(SalesOrderListActivity.this, view);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {  
            public boolean onClose() {
                boolean createSearchViewlambda1;
                createSearchViewlambda1 = SalesOrderListActivity.createSearchViewlambda1(SalesOrderListActivity.this);
                return createSearchViewlambda1;
            }
        });
    }
 
    public static void createSearchViewlambda0(SalesOrderListActivity this0, View v) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(v, "v");
        this0.mSearchViewExpanded = true;
        v.requestFocus();
    }
 
    public static boolean createSearchViewlambda1(SalesOrderListActivity this0) {
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
            SalesOrderListRecyclerViewAdapter salesOrderListRecyclerViewAdapter = this.mAdapter;
            Intrinsics.checkNotNull(salesOrderListRecyclerViewAdapter);
            salesOrderListRecyclerViewAdapter.setData(this.mData);
            return;
        }
        ArrayList<ManagerOrder> arrayList = new ArrayList<>();
        ArrayList<ManagerOrder> arrayList2 = this.mData;
        Intrinsics.checkNotNull(arrayList2);
        Iterator<ManagerOrder> it = arrayList2.iterator();
        while (it.hasNext()) {
            ManagerOrder next = it.next();
            String ficheNo = next.getFicheNo();
            Intrinsics.checkNotNullExpressionValue(ficheNo, "getFicheNo(...)");
            if (StringsKt.contains (ficheNo, query, false)) {
                arrayList.add(next);
            }
        }
        if (arrayList.size() > 0) {
            toggleEmptyView(false);
            SalesOrderListRecyclerViewAdapter salesOrderListRecyclerViewAdapter2 = this.mAdapter;
            Intrinsics.checkNotNull(salesOrderListRecyclerViewAdapter2);
            salesOrderListRecyclerViewAdapter2.setData(arrayList);
            return;
        }
        toggleEmptyView(true);
    } 
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        MenuInflater menuInflater = getMenuInflater();
        Intrinsics.checkNotNullExpressionValue(menuInflater, "getMenuInflater(...)");
        menuInflater.inflate(R.menu.menu_search, menu);
        menuInflater.inflate(R.menu.menu_ok, menu);
        createSearchView(menu.findItem(R.id.menu_search), menu);
        return super.onCreateOptionsMenu(menu);
    }

    private record OnlineSalesOrderListener(
            WeakReference<SalesOrderListActivity> mContent) implements ResponseListener<ArrayList<ManagerOrder>> {
            private OnlineSalesOrderListener(SalesOrderListActivity mContent) {
                Intrinsics.checkNotNullParameter(mContent, "mContent");
                this.mContent = new WeakReference<>(mContent);
            }

        public void onResponse(PrintSlipModel arrayList) {
                if (this.mContent.get() != null) {
                    SalesOrderListActivity salesOrderListActivity = this.mContent.get();
                    Intrinsics.checkNotNull(salesOrderListActivity);
                    salesOrderListActivity.setData(arrayList);
                    SalesOrderListActivity salesOrderListActivity2 = this.mContent.get();
                    Intrinsics.checkNotNull(salesOrderListActivity2);
                    salesOrderListActivity2.dismisprogress();
                }
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mContent.get() != null) {
                    SalesOrderListActivity salesOrderListActivity = this.mContent.get();
                    Intrinsics.checkNotNull(salesOrderListActivity);
                    salesOrderListActivity.dismisprogress();
                    Log.d(SalesOrderListActivity.TAG, "onError: " + errorMessage);
                    SalesOrderListActivity salesOrderListActivity2 = this.mContent.get();
                    Intrinsics.checkNotNull(salesOrderListActivity2);
                    salesOrderListActivity2.showMessage(errorMessage);
                }
            }
        }

    public void showMessage(String str) {
        dismisprogress();
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public void dismisprogress() {
        this.mProgressDialogBuilder.dismiss();
    }
 
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
