package com.proje.mobilesales.features.reports.view.activity;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.databinding.ActivityItemReportsBinding;
import com.proje.mobilesales.features.product.model.ProductInfo;
import com.proje.mobilesales.features.product.view.detail.ProductDetailFragment;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.GlobalScope;

public final class ItemReportsActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    private static final String INTENT_PRODUCT_INFO = "product_info";
    private static final String STATE_PRODUCT_INFO = "state_product_info";
    public ActivityItemReportsBinding binding;
    List<UserReports> itemReports = new ArrayList();
    ProductInfo productInfo;

    public static Intent newIntent(final Context context, final ProductInfo productInfo) {
        return ItemReportsActivity.Companion.newIntent(context, productInfo);
    }

    public ActivityItemReportsBinding getBinding() {
        final ActivityItemReportsBinding activityItemReportsBinding = binding;
        if (null != activityItemReportsBinding) {
            return activityItemReportsBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public void setBinding(final ActivityItemReportsBinding activityItemReportsBinding) {
        Intrinsics.checkNotNullParameter(activityItemReportsBinding, "<set-?>");
        binding = activityItemReportsBinding;
    }
     public void onCreate(final Bundle bundle) {
        final ProductInfo productInfo;
        super.onCreate(bundle);
        String str = null;
        if (null != bundle) {
            productInfo = bundle.getParcelable(ItemReportsActivity.STATE_PRODUCT_INFO);
        } else {
            final Intent intent = this.getIntent();
            productInfo = null != intent ? (ProductInfo) intent.getParcelableExtra(ItemReportsActivity.INTENT_PRODUCT_INFO) : null;
        }
        this.productInfo = productInfo;
        final ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_item_reports);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
         this.setBinding(contentView);
         this.getActivityComponent().inject(this);
         this.setToolbar();
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (null != supportActionBar) {
            supportActionBar.setTitle(this.getString(R.string.str_stock_reports));
        }
        final TextView textView = this.getBinding().itemCode;
        final ProductInfo productInfo2 = this.productInfo;
        final String itemName = null != productInfo2 ? productInfo2.getItemName() : null;
        if (null == itemName || 0 == itemName.length()) {
            final ProductInfo productInfo3 = this.productInfo;
            if (null != productInfo3) {
                str = productInfo3.getItemCode();
            }
        } else {
            final ProductInfo productInfo4 = this.productInfo;
            if (null != productInfo4) {
                str = productInfo4.getItemName();
            }
        }
        textView.setText(str);
         this.getBinding().swiperefresh.setColorSchemeResources(R.color.white);
         this.getBinding().swiperefresh.setProgressBackgroundColorSchemeResource(R.color.redA200);
         this.getBinding().swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ItemReportsActivityExternalSyntheticLambda1
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                onCreatelambda0(ItemReportsActivity.this);
            }
        });
         this.loadItemReportsFromLocal();
    }
    public static void onCreatelambda0(final ItemReportsActivity this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.setRefreshing(true);
        this0.updateUserReportsOnline();
    }

    private void setRefreshing(final boolean z) {
        if (this.getBinding().swiperefresh.isRefreshing()) {
            if (z) {
                return;
            }
            this.getBinding().swiperefresh.setRefreshing(false);
        } else if (z) {
            this.getBinding().swiperefresh.setRefreshing(z);
        }
    }
     public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putParcelable(ItemReportsActivity.STATE_PRODUCT_INFO, productInfo);
    }

    
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(savedInstanceState, "savedInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        productInfo = savedInstanceState.getParcelable(ItemReportsActivity.STATE_PRODUCT_INFO);
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    } 
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Intent newIntent(final Context context, final ProductInfo productInfo) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(productInfo, "productInfo");
            final Intent intent = new Intent(context, ItemReportsActivity.class);
            intent.putExtra(INTENT_PRODUCT_INFO, productInfo);
            return intent;
        }
    }

    public void loadItemReportsFromLocal() {
        this.setRefreshing(true);
        BuildersKt.launch(GlobalScope.INSTANCE, null, null, new ItemReportsActivityloadItemReportsFromLocal1(this, null));
    }

    private void updateUserReportsOnline() {
        baseErp.getReportOnline(new ReportOnLineListener(new WeakReference(this)));
    } 
    public static final class ReportOnLineListener implements ResponseListener<Boolean> {
        private final WeakReference<ItemReportsActivity> activityReference;

        public ReportOnLineListener(final WeakReference<ItemReportsActivity> activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            activityReference = activity;
        } 
        public void onResponse(final PrintSlipModel bool) {
            final ItemReportsActivity itemReportsActivity = activityReference.get();
            if (null != itemReportsActivity) {
                itemReportsActivity.loadItemReportsFromLocal();
            }
        } 
        public void onError(final String str) {
            String str2 = str;
            if (null != this.activityReference.get()) {
                if (null == str) {
                    final ItemReportsActivity itemReportsActivity = activityReference.get();
                    Intrinsics.checkNotNull(itemReportsActivity);
                    str2 = (String) itemReportsActivity.getText(R.string.exp_10_transfer_get_error);
                }
                Intrinsics.checkNotNull(str2);
                Toast.makeText(activityReference.get(), str2, Toast.LENGTH_LONG).show();
            }
        }
    }
    public void showData(List<UserReports> list) {
        if (list.isEmpty()) {
            Toast.makeText(this, this.getText(R.string.str_data_not_found), Toast.LENGTH_LONG).show();
        }
        final ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (final UserReports userReports : list) {
            Intrinsics.checkNotNull(userReports);
            arrayList.add(userReports.reportName);
        }
        this.getBinding().list.setAdapter(new ArrayAdapter(this, R.layout.simple_list_item_1, arrayList));
        this.setRefreshing(false);
        this.getBinding().list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
                showDatalambda6(ItemReportsActivity.this, list, adapterView, view, i2, j2);
            }
        });
    }

    
    public static void showDatalambda6(final ItemReportsActivity this0, final List userReports, final AdapterView adapterView, final View view, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(userReports, "userReports");
        final UserReports userReports2 = (UserReports) userReports.get(i2);
        if (null != userReports2) {
            if (0 == i2) {
                final Bundle bundle = new Bundle();
                final ProductInfo productInfo = this0.productInfo;
                if (null != productInfo) {
                    bundle.putInt(ProductDetailFragment.PRODUCT_CODE, productInfo.getItemRef());
                }
                final ProductInfo productInfo2 = this0.productInfo;
                if (null != productInfo2) {
                    bundle.putBoolean(ProductDetailFragment.PRODUCT_ISSERVICE, productInfo2.isService());
                }
                this0.getSupportFragmentManager().beginTransaction().add(R.id.constLayout, ProductDetailFragment.class, bundle, "PRODUCT DETAIL").addToBackStack("ITEM REPORTS").commit();
                return;
            }
            this0.openReport(userReports2);
        }
    }

    private void openReport(final UserReports userReports) {
        BuildersKt.launch(GlobalScope.INSTANCE, null, null, new ItemReportsActivityopenReport1(this, userReports, null));
    }
}
