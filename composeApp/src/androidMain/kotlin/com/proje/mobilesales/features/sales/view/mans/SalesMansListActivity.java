package com.proje.mobilesales.features.sales.view.mans;

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
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.widget.SlideInLeftAnimator;
import com.proje.mobilesales.databinding.DistributionListBinding;
import com.proje.mobilesales.databinding.EmptyListBinding;
import com.proje.mobilesales.features.sales.model.SalesMan;
import com.proje.mobilesales.features.sales.repository.SalesMansRepository;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

import static androidx.appcompat.widget.SearchView.*;

public final class SalesMansListActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = SalesMansListActivity.class.getName();
    private DistributionListBinding binding;
    public ActionViewResolver mActionViewResolver;
    private SalesMansListRecyclerViewAdapter mAdapter;
    private ArrayList<SalesMan> mData;
    private EmptyListBinding mEmptyView;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private RecyclerView mRecyclerView;
    private boolean mSearchViewExpanded;
    private final SalesMansRepository repository;
    private final SalesMansViewModel viewModel;

    public SalesMansListActivity() {
        SalesMansRepository salesMansRepository = new SalesMansRepository();
        this.repository = salesMansRepository;
        this.viewModel = new SalesMansViewModel(salesMansRepository);
        this.mData = new ArrayList<>();
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
        DistributionListBinding inflate = DistributionListBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        DistributionListBinding distributionListBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity);
        this.mActionViewResolver = new ActionViewResolver();
        DistributionListBinding distributionListBinding2 = this.binding;
        if (distributionListBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            distributionListBinding2 = null;
        }
        EmptyListBinding emptyListBinding = distributionListBinding2.empty;
        this.mEmptyView = emptyListBinding;
        Intrinsics.checkNotNull(emptyListBinding);
        emptyListBinding.getRoot().setVisibility(View.GONE);
        DistributionListBinding distributionListBinding3 = this.binding;
        if (distributionListBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            distributionListBinding = distributionListBinding3;
        }
        RecyclerView recyclerView = distributionListBinding.rwDistributionListView;
        this.mRecyclerView = recyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView2);
        recyclerView2.setHasFixedSize(true);
        RecyclerView recyclerView3 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView3);
        recyclerView3.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(2.0f)));
        SalesMansListRecyclerViewAdapter salesMansListRecyclerViewAdapter = new SalesMansListRecyclerViewAdapter();
        this.mAdapter = salesMansListRecyclerViewAdapter;
        Intrinsics.checkNotNull(salesMansListRecyclerViewAdapter);
        salesMansListRecyclerViewAdapter.initDisplayOptions(this);
        setToolbar();
        refreshList();
    }

    @Override // com.proje.mobilesales.core.base.BaseInjectableActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        RecyclerView recyclerView = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setAdapter(null);
    }

    private void refreshList() {
        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
        this.viewModel.getOnlineSalesMans(new OnlineSalesMansListener(this));
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
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById;
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.white));
        if (Build.VERSION.SDK_INT >= 29 && textCursorDrawable != null) {
            textCursorDrawable.setColorFilter(ContextCompat.getColor(this, R.color.custom_cursor_color), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setTextCursorDrawable(textCursorDrawable);
        }
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                Intrinsics.checkNotNullParameter(newText, "newText");
                return false;
            } 
            public boolean onQueryTextSubmit(String query) {
                Intrinsics.checkNotNullParameter(query, "query");
                SalesMansListActivity.this.filterData(query);
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {  
            public void onClick(View view) {
                SalesMansListActivity.createSearchViewlambda0(SalesMansListActivity.this, view);
            }
        });
        searchView.setOnCloseListener(new OnCloseListener() {
            public boolean onClose() {
                boolean createSearchViewlambda1;
                createSearchViewlambda1 = SalesMansListActivity.createSearchViewlambda1(SalesMansListActivity.this);
                return createSearchViewlambda1;
            }
        });
    }

    
    public static void createSearchViewlambda0(SalesMansListActivity this0, View v) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(v, "v");
        this0.mSearchViewExpanded = true;
        v.requestFocus();
    }

    
    public static boolean createSearchViewlambda1(SalesMansListActivity this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        ActionBar supportActionBar = this0.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setSubtitle("");
        this0.filterData("");
        return false;
    }

    public void SelectSalesMan(SalesMan salesMan) {
        Intent intent = new Intent();
        intent.putExtra(IntentExtraName.SELECTED_SALESMAN, salesMan);
        setResult(-1, intent);
        finish();
    }

    public void filterData(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        if (Intrinsics.areEqual(query, "")) {
            toggleEmptyView(false);
            SalesMansListRecyclerViewAdapter salesMansListRecyclerViewAdapter = this.mAdapter;
            Intrinsics.checkNotNull(salesMansListRecyclerViewAdapter);
            salesMansListRecyclerViewAdapter.setData(this.mData);
            return;
        }
        ArrayList<SalesMan> arrayList = new ArrayList<>();
        ArrayList<SalesMan> arrayList2 = this.mData;
        Intrinsics.checkNotNull(arrayList2);
        Iterator<SalesMan> it = arrayList2.iterator();
        while (it.hasNext()) {
            SalesMan next = it.next();
            String str = next.component2();
            Intrinsics.checkNotNull(str);
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String upperCase = str.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            Locale locale2 = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
            String upperCase2 = query.toUpperCase(locale2);
            Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
            if (!StringsKt.contains(upperCase, upperCase2, false)) {
                String str2 = next.component3();
                Intrinsics.checkNotNull(str2);
                Locale locale3 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale3, "getDefault(...)");
                String upperCase3 = str2.toUpperCase(locale3);
                Intrinsics.checkNotNullExpressionValue(upperCase3, "toUpperCase(...)");
                Locale locale4 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale4, "getDefault(...)");
                String upperCase4 = query.toUpperCase(locale4);
                Intrinsics.checkNotNullExpressionValue(upperCase4, "toUpperCase(...)");
                if (StringsKt.contains(upperCase3, upperCase4, false)) {
                }
            }
            arrayList.add(next);
        }
        if (arrayList.size() > 0) {
            toggleEmptyView(false);
            SalesMansListRecyclerViewAdapter salesMansListRecyclerViewAdapter2 = this.mAdapter;
            Intrinsics.checkNotNull(salesMansListRecyclerViewAdapter2);
            salesMansListRecyclerViewAdapter2.setData(arrayList);
            return;
        }
        toggleEmptyView(true);
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

    
    public void setData(ArrayList<SalesMan> arrayList) {
        this.mData = arrayList;
        SalesMansListRecyclerViewAdapter salesMansListRecyclerViewAdapter = this.mAdapter;
        Intrinsics.checkNotNull(salesMansListRecyclerViewAdapter);
        salesMansListRecyclerViewAdapter.setData(arrayList);
        RecyclerView recyclerView = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setAdapter(this.mAdapter);
        ArrayList<SalesMan> arrayList2 = this.mData;
        Intrinsics.checkNotNull(arrayList2);
        toggleEmptyView(arrayList2.size() == 0);
    }

    private record OnlineSalesMansListener(
            WeakReference<SalesMansListActivity> mContent) implements ResponseListener<ArrayList<SalesMan>> {
            private OnlineSalesMansListener(SalesMansListActivity mContent) {
                Intrinsics.checkNotNullParameter(mContent, "mContent");
                this.mContent = new WeakReference<>(mContent);
            }


        public void onResponse(PrintSlipModel arrayList) {
                if (this.mContent.get() != null) {
                    SalesMansListActivity salesMansListActivity = this.mContent.get();
                    Intrinsics.checkNotNull(salesMansListActivity);
                    salesMansListActivity.setData(arrayList);
                    SalesMansListActivity salesMansListActivity2 = this.mContent.get();
                    Intrinsics.checkNotNull(salesMansListActivity2);
                    salesMansListActivity2.dismisprogress();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }


            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mContent.get() != null) {
                    SalesMansListActivity salesMansListActivity = this.mContent.get();
                    Intrinsics.checkNotNull(salesMansListActivity);
                    salesMansListActivity.dismisprogress();
                    Log.d(SalesMansListActivity.TAG, "onError: " + errorMessage);
                    SalesMansListActivity salesMansListActivity2 = this.mContent.get();
                    Intrinsics.checkNotNull(salesMansListActivity2);
                    salesMansListActivity2.showMessage(errorMessage);
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
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
