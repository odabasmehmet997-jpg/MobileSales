package com.proje.mobilesales.features.sales.view.list;

import android.R;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseFicheListActivity;
import com.proje.mobilesales.core.base.BaseFicheListFragment;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseListActivity;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import kotlin.jvm.internal.Intrinsics;

public final class SalesListActivity extends BaseFicheListActivity {
    private CustomerOperation customerOperation;
    private AlertDialogBuilder<?> mAlertDialogBuilder;

    public CustomerOperation getCustomerOperation() {
        return this.customerOperation;
    }

    public void setCustomerOperation(CustomerOperation customerOperation) {
        this.customerOperation = customerOperation;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return this.mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilder = alertDialogBuilder;
    }
     public void onCreate(Bundle bundle) {
        CustomerOperation customerOperation = getIntent().getParcelableExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE);
        this.customerOperation = customerOperation;
        if (customerOperation == null) {
            finish();
            return;
        }
        if (customerOperation.getSalesType() == SalesType.DEMAND) {
            this.analyticsEventType = AnalyticsEventType.DEMAND;
        } else if (this.customerOperation.getSalesType() == SalesType.WHTRANSFER) {
            this.analyticsEventType = AnalyticsEventType.WH_TRANSFER;
        }
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity);
    }

    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent.hasExtra("query")) {
            String stringExtra = intent.getStringExtra("query");
            this.mFilter = stringExtra;
            if (TextUtils.equals(stringExtra, "")) {
                this.mFilter = null;
            }
            ActionBar supportActionBar = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar);
            supportActionBar.setSubtitle(this.mFilter);
            SalesListFragment salesListFragment = (SalesListFragment) getSupportFragmentManager().findFragmentByTag(BaseListActivity.LIST_FRAGMENT_TAG);
            if (salesListFragment != null) {
                salesListFragment.filter(this.mFilter);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public String getDefaultTitle() {
        String string = getString(R.string.str_sales);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    protected Fragment instantiateListFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(BaseFicheListFragment.EXTRA_FILTER, this.mFilter);
        bundle.putInt(BaseFicheListFragment.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        String str = IntentExtraName.EXTRA_CUSTOMER_SHIPREF;
        bundle.putInt(str, getIntent().getIntExtra(str, 0));
        bundle.putInt(BaseFicheListFragment.EXTRA_ROUTEDAY_REF, this.routeDayRef);
        bundle.putInt(BaseFicheListFragment.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
        bundle.putInt(BaseFicheListFragment.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
        bundle.putParcelable(BaseFicheListFragment.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
        bundle.putInt(BaseFicheListFragment.Companion.getEXTRA_CABIN_REF(), getIntent().getIntExtra(IntentExtraName.EXTRA_CABIN_REF, 0));
        Fragment instantiate = Fragment.instantiate(this, SalesListFragment.class.getName(), bundle);
        Intrinsics.checkNotNullExpressionValue(instantiate, "instantiate(...)");
        return instantiate;
    }

    public void customerRefChanged() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setMessage(getString(R.string.str_customer_transferred)).setCancelable(false).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesListActivityExternalSyntheticLambda0
            public   void SalesListActivityExternalSyntheticLambda0() {
            }

            public void onClick(DialogInterface dialogInterface, int i2) {
                SalesListActivity.customerRefChangedlambda0(SalesListActivity.this, dialogInterface, i2);
            }
        }).create().show();
        AlertDialogBuilder<?> alertDialogBuilder2 = this.mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder2);
        alertDialogBuilder2.setCancelOnTouchOutside(false);
    }

    public static void customerRefChangedlambda0(SalesListActivity this0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.finishActivity();
    }

    private void finishActivity() {
        Intent intent = new Intent();
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF_CHANGED, true);
        setResult(IntentExtraName.NEW_CUSTOMERREF_CHANGED_RESULT_CODE, intent);
        finish();
    }
     protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
    }
}
