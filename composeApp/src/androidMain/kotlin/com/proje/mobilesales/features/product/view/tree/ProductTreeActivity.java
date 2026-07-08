package com.proje.mobilesales.features.product.view.tree;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.SearchAutoComplete;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.widget.SlideInLeftAnimator;
import com.proje.mobilesales.databinding.DistributionListBinding;
import com.proje.mobilesales.databinding.EmptyListBinding;
import com.proje.mobilesales.features.product.model.ProductTreeItem;
import com.proje.mobilesales.features.product.repository.ProductTreeRepository;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
 
public final class ProductTreeActivity extends BaseInjectableActivity implements ProductTreeRecyclerViewAdapter.ProductTreeItemSelectedListener {
    private static final String TAG = ProductTreeActivity.class.getName();
    public ActionViewResolver mActionViewResolver;
    private ProductTreeRecyclerViewAdapter mAdapter;
    private EmptyListBinding mEmptyView;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private RecyclerView mRecyclerView;
    private boolean mSearchViewExpanded;
    private final ProductTreeRepository repository = null;
    private final ProductTreeViewModel viewModel;
    private final Lazy bindingdelegate = LazyKt.lazy(() -> DistributionListBinding.inflate(this.getLayoutInflater()));
    private ArrayList<ProductTreeItem> mData = new ArrayList<>();
    private final ArrayList<String> mGroupCodeList = new ArrayList<>();
    public ProductTreeActivity() {
        this.viewModel = new ProductTreeViewModel(this.repository);
    }
    private DistributionListBinding getBinding() {
        return (DistributionListBinding) this.bindingdelegate.getValue();
    }
    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder != null) {
            return progressDialogBuilder;
        }
        throw new IllegalStateException("mProgressDialogBuilder must be initialized");
    }
    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        this.mProgressDialogBuilder = progressDialogBuilder;
    }
    public ActionViewResolver getMActionViewResolver() {
        ActionViewResolver actionViewResolver = this.mActionViewResolver;
        if (actionViewResolver != null) {
            return actionViewResolver;
        }
        throw new IllegalStateException("mActionViewResolver must be initialized");
    }
    public void setMActionViewResolver(ActionViewResolver actionViewResolver) {
        Intrinsics.checkNotNullParameter(actionViewResolver, "<set-?>");
        this.mActionViewResolver = actionViewResolver;
    }
    public boolean getMSearchViewExpanded() {
        return this.mSearchViewExpanded;
    }
    public void setMSearchViewExpanded(boolean z) {
        this.mSearchViewExpanded = z;
    }
    public void onCreate(Bundle bundle, PersistableBundle persistableBundle) {
        super.onCreate(bundle, persistableBundle);
        getActivityComponent().inject(this);
        setContentView(getBinding().getRoot());
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMProgressDialogBuilder(new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity));
        setMActionViewResolver(new ActionViewResolver());
        EmptyListBinding emptyListBinding = getBinding().empty;
        Intrinsics.checkNotNullExpressionValue(emptyListBinding, "empty");
        this.mEmptyView = emptyListBinding;
        RecyclerView recyclerView = null;
        if (emptyListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
            emptyListBinding = null;
        }
        emptyListBinding.getRoot().setVisibility(View.GONE);
        RecyclerView recyclerView2 = getBinding().rwDistributionListView;
        Intrinsics.checkNotNullExpressionValue(recyclerView2, "rwDistributionListView");
        this.mRecyclerView = recyclerView2;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView2 = null;
        }
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView3 = this.mRecyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView3 = null;
        }
        recyclerView3.setHasFixedSize(true);
        RecyclerView recyclerView4 = this.mRecyclerView;
        if (recyclerView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
        } else {
            recyclerView = recyclerView4;
        }
        recyclerView.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(2.0f)));
        ProductTreeRecyclerViewAdapter productTreeRecyclerViewAdapter = new ProductTreeRecyclerViewAdapter(this);
        this.mAdapter = productTreeRecyclerViewAdapter;
        productTreeRecyclerViewAdapter.initDisplayOptions(this);
        setToolbar();
        refreshList();
    }
    public void onBackPressed() {
        if (this.mGroupCodeList.size() == 0) {
            super.onBackPressed();
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
                recyclerView = null;
            }
            recyclerView.setAdapter(null);
            return;
        }
        ArrayList<String> arrayList = this.mGroupCodeList;
        arrayList.remove(arrayList.size() - 1);
        refreshList();
    }
    private void refreshList() {
        getMProgressDialogBuilder().setMessage(getString(R.string.str_please_wait)).show();
        this.viewModel.getProductTreeListItems(this.mGroupCodeList, null, new ProductTreeListener(this));
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        MenuInflater menuInflater = getMenuInflater();
        Intrinsics.checkNotNullExpressionValue(menuInflater, "getMenuInflater(...)");
        menuInflater.inflate(R.menu.menu_search, menu);
        createSearchView(menu.findItem(R.id.menu_search));
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() == android.R.id.home) {
            if (this.mGroupCodeList.size() == 0) {
                finish();
            } else {
                ArrayList<String> arrayList = this.mGroupCodeList;
                arrayList.remove(arrayList.size() - 1);
                refreshList();
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }
    @SuppressLint("ResourceType")
    private void createSearchView(MenuItem menuItem) {
        Drawable drawable;
        View actionView = getMActionViewResolver().getActionView(menuItem);
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        SearchView searchView = (SearchView) actionView;
        searchView.setQueryHint(getString(R.string.hint_search_order));
        Object systemService = getSystemService(Context.SEARCH_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.SearchManager");
        searchView.setSearchableInfo(((SearchManager) systemService).getSearchableInfo(getComponentName()));
        searchView.setIconified(!this.mSearchViewExpanded);
        View findViewById = searchView.findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView.SearchAutoComplete");
        @SuppressLint("RestrictedApi") SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById;
        searchAutoComplete.setHintTextColor(getResources().getColor(17170443));
        if (Build.VERSION.SDK_INT >= 29 && (drawable = searchAutoComplete.getTextCursorDrawable()) != null) {
            drawable.setColorFilter(ContextCompat.getColor(this, R.color.custom_cursor_color), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setTextCursorDrawable(drawable);
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(this) {
            ProductTreeActivity this0;
            public boolean onQueryTextChange(String str) {
                Intrinsics.checkNotNullParameter(str, "newText");
                return false;
            }
            public boolean onQueryTextSubmit(String str) {
                Intrinsics.checkNotNullParameter(str, "query");
                str.equals(this.this0);
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProductTreeActivity.createSearchViewlambda0(ProductTreeActivity.this, view);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            public boolean onClose() {
                return ProductTreeActivity.createSearchViewlambda1(ProductTreeActivity.this);
            }
        });
    }
    public static void createSearchViewlambda0(ProductTreeActivity productTreeActivity, View view) {
        Intrinsics.checkNotNullParameter(productTreeActivity, "this0");
        Intrinsics.checkNotNullParameter(view, "v");
        productTreeActivity.mSearchViewExpanded = true;
        view.requestFocus();
    }
    public static boolean createSearchViewlambda1(ProductTreeActivity productTreeActivity) {
        Intrinsics.checkNotNullParameter(productTreeActivity, "this0");
        ActionBar supportActionBar = productTreeActivity.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setSubtitle("");
        productTreeActivity.filterData("");
        return false;
    }
    public void selectProductTreeItem(ProductTreeItem productTreeItem) {
        Intrinsics.checkNotNullParameter(productTreeItem, "item");
        String code = productTreeItem.getCode();
        if (!Intrinsics.equals(code, getString(R.string.str_all)) && !Intrinsics.equals(code, getString(R.string.str_others))) {
            this.mGroupCodeList.add(productTreeItem.getCode());
            refreshList();
        }
    }
    public void filterData(String str) {
        Intrinsics.checkNotNullParameter(str, "query");
        ProductTreeRecyclerViewAdapter productTreeRecyclerViewAdapter = null;
        if (Intrinsics.areEqual(str, "")) {
            toggleEmptyView(false);
            ProductTreeRecyclerViewAdapter productTreeRecyclerViewAdapter2 = this.mAdapter;
            if (productTreeRecyclerViewAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            } else {
                productTreeRecyclerViewAdapter = productTreeRecyclerViewAdapter2;
            }
            productTreeRecyclerViewAdapter.setData(this.mData);
            return;
        }
        ArrayList<ProductTreeItem> arrayList = new ArrayList<>();
        Iterator<ProductTreeItem> it = this.mData.iterator();
        while (it.hasNext()) {
            ProductTreeItem next = it.next();
            String description = next.getDescription();
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String upperCase = description.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            Locale locale2 = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
            String upperCase2 = str.toUpperCase(locale2);
            Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
            if (StringsKt.contains(upperCase, upperCase2, false)) {
                arrayList.add(next);
            }
        }
        if (arrayList.size() > 0) {
            toggleEmptyView(false);
            ProductTreeRecyclerViewAdapter productTreeRecyclerViewAdapter3 = this.mAdapter;
            if (productTreeRecyclerViewAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            } else {
                productTreeRecyclerViewAdapter = productTreeRecyclerViewAdapter3;
            }
            productTreeRecyclerViewAdapter.setData(arrayList);
            return;
        }
        toggleEmptyView(true);
    }
    public void toggleEmptyView(boolean z) {
        RecyclerView recyclerView = null;
        if (!z) {
            EmptyListBinding emptyListBinding = this.mEmptyView;
            if (emptyListBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
                emptyListBinding = null;
            }
            emptyListBinding.getRoot().setVisibility(View.GONE);
            RecyclerView recyclerView2 = this.mRecyclerView;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
                recyclerView2 = null;
            }
            recyclerView2.setVisibility(View.VISIBLE);
            RecyclerView recyclerView3 = this.mRecyclerView;
            if (recyclerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            } else {
                recyclerView = recyclerView3;
            }
            recyclerView.bringToFront();
            return;
        }
        EmptyListBinding emptyListBinding2 = this.mEmptyView;
        if (emptyListBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
            emptyListBinding2 = null;
        }
        emptyListBinding2.getRoot().setVisibility(View.VISIBLE);
        EmptyListBinding emptyListBinding3 = this.mEmptyView;
        if (emptyListBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
            emptyListBinding3 = null;
        }
        emptyListBinding3.getRoot().bringToFront();
        RecyclerView recyclerView4 = this.mRecyclerView;
        if (recyclerView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
        } else {
            recyclerView = recyclerView4;
        }
        recyclerView.setVisibility(View.GONE);
    } 
    public void setData(ArrayList<ProductTreeItem> arrayList) {
        this.mData = arrayList;
        ProductTreeRecyclerViewAdapter productTreeRecyclerViewAdapter = this.mAdapter;
        ProductTreeRecyclerViewAdapter productTreeRecyclerViewAdapter2 = null;
        if (productTreeRecyclerViewAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            productTreeRecyclerViewAdapter = null;
        }
        productTreeRecyclerViewAdapter.setData(arrayList);
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView = null;
        }
        ProductTreeRecyclerViewAdapter productTreeRecyclerViewAdapter3 = this.mAdapter;
        if (productTreeRecyclerViewAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            productTreeRecyclerViewAdapter2 = productTreeRecyclerViewAdapter3;
        }
        recyclerView.setAdapter(productTreeRecyclerViewAdapter2);
        toggleEmptyView(this.mData.size() == 0);
    } 
    public void productTreeItemSelected(ProductTreeItem productTreeItem) {
        Intrinsics.checkNotNullParameter(productTreeItem, "item");
        selectProductTreeItem(productTreeItem);
    } 
    public static final class ProductTreeListener implements ResponseListener<ArrayList<ProductTreeItem>> {
        private final WeakReference<ProductTreeActivity> mContent;

        public ProductTreeListener(ProductTreeActivity productTreeActivity) {
            Intrinsics.checkNotNullParameter(productTreeActivity, "mContent");
            this.mContent = new WeakReference<>(productTreeActivity);
        }

        public void onResponse(PrintSlipModel arrayList) {
            if (this.mContent.get() != null) {
                ProductTreeActivity productTreeActivity = this.mContent.get();
                Intrinsics.checkNotNull(productTreeActivity);
                Intrinsics.checkNotNull(arrayList);
                productTreeActivity.setData(arrayList);
                ProductTreeActivity productTreeActivity2 = this.mContent.get();
                Intrinsics.checkNotNull(productTreeActivity2);
                productTreeActivity2.dismissProgress();
            }
        } 
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mContent.get() != null) {
                ProductTreeActivity productTreeActivity = this.mContent.get();
                Intrinsics.checkNotNull(productTreeActivity);
                productTreeActivity.dismissProgress();
                String str2 = ProductTreeActivity.TAG;
                Log.d(str2, "onError: " + str);
                ProductTreeActivity productTreeActivity2 = this.mContent.get();
                Intrinsics.checkNotNull(productTreeActivity2);
                productTreeActivity2.showMessage(str);
            }
        }
    }
    public void showMessage(String str) {
        dismissProgress();
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
    public void dismissProgress() {
        getMProgressDialogBuilder().dismiss();
    }

}
