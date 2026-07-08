package com.proje.mobilesales.features.sales.view.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
import com.proje.mobilesales.databinding.ActivityLastOrderProductsBinding;
import com.proje.mobilesales.features.sales.model.database.LastOrderProducts;
import com.proje.mobilesales.features.sales.repository.LastOrderProductsRepository;
import com.proje.mobilesales.features.sales.view.order.LastOrderProductViewModel;
import com.proje.mobilesales.features.sales.view.order.LastOrderProductsRecyclerViewAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
 
public final class LastOrderProductsActivity extends BaseErpActivity {
    private ActivityLastOrderProductsBinding binding;
    private boolean isAllSalesmen;
    private LastOrderProductsRecyclerViewAdapter mAdapter;
    private int mCustomerRef;
    private RecyclerView mRecyclerView;
    private AppBarSwipeRefreshLayout mSwipeRefreshLayout;
    public ProgressDialogBuilder<?> progressDialogBuilder;
    private final LastOrderProductsRepository repository;
    private final LastOrderProductViewModel viewModel;

    public LastOrderProductsActivity() {
        final LastOrderProductsRepository lastOrderProductsRepository = new LastOrderProductsRepository();
        repository = lastOrderProductsRepository;
        viewModel = new LastOrderProductViewModel(lastOrderProductsRepository);
        mAdapter = new LastOrderProductsRecyclerViewAdapter();
    }

    public ProgressDialogBuilder<?> getProgressDialogBuilder() {
        final ProgressDialogBuilder<?> progressDialogBuilder = this.progressDialogBuilder;
        if (null != progressDialogBuilder) {
            return progressDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("progressDialogBuilder");
        return null;
    }

    public void setProgressDialogBuilder(final ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        this.progressDialogBuilder = progressDialogBuilder;
    }

    public AppBarSwipeRefreshLayout getMSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    public void setMSwipeRefreshLayout(final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout) {
        mSwipeRefreshLayout = appBarSwipeRefreshLayout;
    }

    public LastOrderProductsRecyclerViewAdapter getMAdapter() {
        return mAdapter;
    }

    public void setMAdapter(final LastOrderProductsRecyclerViewAdapter lastOrderProductsRecyclerViewAdapter) {
        Intrinsics.checkNotNullParameter(lastOrderProductsRecyclerViewAdapter, "<set-?>");
        mAdapter = lastOrderProductsRecyclerViewAdapter;
    }

    @Override // com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        ActivityLastOrderProductsBinding inflate = ActivityLastOrderProductsBinding.inflate(this.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        binding = inflate;
        ActivityLastOrderProductsBinding activityLastOrderProductsBinding = null;
        if (null == inflate) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        this.setContentView(inflate.getRoot());
        this.getActivityComponent().inject(this);
        this.setToolbar();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.setProgressDialogBuilder(new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity));
        mCustomerRef = this.getIntent().getIntExtra(IntentExtraName.EXTRA_CUSTOMER_REF, -1);
        ActivityLastOrderProductsBinding activityLastOrderProductsBinding2 = binding;
        if (null == activityLastOrderProductsBinding2) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLastOrderProductsBinding2 = null;
        }
        mRecyclerView = activityLastOrderProductsBinding2.rcwList;
        final ActivityLastOrderProductsBinding activityLastOrderProductsBinding3 = binding;
        if (null == activityLastOrderProductsBinding3) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityLastOrderProductsBinding = activityLastOrderProductsBinding3;
        }
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = activityLastOrderProductsBinding.swipeLayout;
        mSwipeRefreshLayout = appBarSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        appBarSwipeRefreshLayout.setColorSchemeResources(R.color.white);
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
        appBarSwipeRefreshLayout2.setProgressBackgroundColorSchemeResource(R.color.redA200);
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout3 = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout3);
        appBarSwipeRefreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {  
            public void onRefresh() {
                onCreatelambda0(LastOrderProductsActivity.this);
            }
        });
        final RecyclerView recyclerView = mRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final RecyclerView recyclerView2 = mRecyclerView;
        Intrinsics.checkNotNull(recyclerView2);
        recyclerView2.setHasFixedSize(true);
        mAdapter = new LastOrderProductsRecyclerViewAdapter();
        final RecyclerView recyclerView3 = mRecyclerView;
        Intrinsics.checkNotNull(recyclerView3);
        recyclerView3.setAdapter(mAdapter);
        this.getProducts();
    }
 
    public static void onCreatelambda0(final LastOrderProductsActivity this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.getProducts();
    }

    private record LastOrderProductsListener(
            WeakReference<LastOrderProductsActivity> mContent) implements ResponseListener<ArrayList<LastOrderProducts>> {
            private LastOrderProductsListener(final LastOrderProductsActivity mContent) {
                this(new WeakReference<>(mContent));
            }

        public void onResponse(final ArrayList<LastOrderProducts> arrayList) {
                if (null != this.mContent.get()) {
                    final LastOrderProductsActivity lastOrderProductsActivity = mContent.get();
                    Intrinsics.checkNotNull(lastOrderProductsActivity);
                    lastOrderProductsActivity.setData(arrayList);
                    final LastOrderProductsActivity lastOrderProductsActivity2 = mContent.get();
                    Intrinsics.checkNotNull(lastOrderProductsActivity2);
                    lastOrderProductsActivity2.dismissProgress();
                }
            }

        @Override
        public void onFailure(Throwable throwable) {

        }

        public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mContent.get()) {
                    final LastOrderProductsActivity lastOrderProductsActivity = mContent.get();
                    Intrinsics.checkNotNull(lastOrderProductsActivity);
                    lastOrderProductsActivity.dismissProgress();
                    final LastOrderProductsActivity lastOrderProductsActivity2 = mContent.get();
                    Intrinsics.checkNotNull(lastOrderProductsActivity2);
                    lastOrderProductsActivity2.showMessage(errorMessage);
                }
            }
        }

    
    public void setData(final ArrayList<LastOrderProducts> arrayList) {
        final LastOrderProductsRecyclerViewAdapter lastOrderProductsRecyclerViewAdapter = mAdapter;
        Intrinsics.checkNotNull(arrayList);
        lastOrderProductsRecyclerViewAdapter.addItem(arrayList);
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        if (appBarSwipeRefreshLayout.isRefreshing()) {
            final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = mSwipeRefreshLayout;
            Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
            appBarSwipeRefreshLayout2.setRefreshing(false);
        }
        ActivityLastOrderProductsBinding activityLastOrderProductsBinding = binding;
        if (null == activityLastOrderProductsBinding) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLastOrderProductsBinding = null;
        }
        activityLastOrderProductsBinding.chkAllSalesmen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.proje.mobilesales.features.sales.view.list.LastOrderProductsActivityExternalSyntheticLambda1
        
            public   void onCheckedChanged(final CompoundButton compoundButton, final boolean z) {
                setDatalambda1(LastOrderProductsActivity.this, compoundButton, z);
            }
        });
    }
 
    public static   void setDatalambda1(final LastOrderProductsActivity this0, final CompoundButton compoundButton, final boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.isAllSalesmen = z;
        this0.getProducts();
    }

    public   void showMessage(final String str) {
        this.dismissProgress();
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public   void dismissProgress() {
        this.getProgressDialogBuilder().dismiss();
    }

    private   void getProducts() {
        mAdapter.restartAdapterAndScroll();
        mAdapter.notifyDataSetChanged();
        this.getProgressDialogBuilder().setMessage(this.getString(R.string.str_please_wait)).show();
        viewModel.getLastOrderProductList(mCustomerRef, isAllSalesmen, new LastOrderProductsListener(this));
    }
 
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
