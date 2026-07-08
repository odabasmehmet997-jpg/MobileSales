package com.proje.mobilesales.features.customer.view.list;

import android.R;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseListDrawerActivity;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.customer.repository.CustomerListRepository;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
public final class CustomerListActivity extends BaseListDrawerActivity {
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_CUSTOMER_SELECT_TYPE = CustomerListActivity.class.getName() + ".EXTRA_CUSTOMER_SELECT_TYPE";
    public static final String EXTRA_SALES_CUSTOMER_CODE = CustomerListActivity.class.getName() + ".EXTRA_SALES_CUSTOMER_CODE";
    private static final String STATE_CUSTOMER_LIST_TYPE = "state:customerSelectType";
    private static final String STATE_FILTER = "state:filter";
    private static final String STATE_SALES_CUSTOMER_CODE = "state:salesCustomerCode";
    private String mClCode;
    private int mCustomerSelectType;
    private String mFilter;
    private final CustomerListRepository repository;
    private final CustomerListViewModel viewModel;

    @Override // com.proje.mobilesales.core.base.BaseListDrawerActivity
    protected boolean isSearchable() {
        return false;
    }

    public CustomerListActivity() {
        final CustomerListRepository customerListRepository = new CustomerListRepository();
        repository = customerListRepository;
        viewModel = new CustomerListViewModel(customerListRepository);
    }

    @Override // com.proje.mobilesales.core.base.BaseListDrawerActivity, com.proje.mobilesales.core.base.BaseDrawerActivity, com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.CUSTOMERS;
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        if (null != bundle) {
            mFilter = bundle.getString(CustomerListActivity.STATE_FILTER);
            mCustomerSelectType = bundle.getInt(CustomerListActivity.STATE_CUSTOMER_LIST_TYPE);
            mClCode = bundle.getString(CustomerListActivity.STATE_SALES_CUSTOMER_CODE);
            final ActionBar supportActionBar = this.getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar);
            supportActionBar.setSubtitle(mFilter);
        } else {
            mCustomerSelectType = this.getIntent().getIntExtra(CustomerListActivity.EXTRA_CUSTOMER_SELECT_TYPE, 0);
            mClCode = this.getIntent().getStringExtra(CustomerListActivity.EXTRA_SALES_CUSTOMER_CODE);
            this.getSupportFragmentManager().beginTransaction().replace(R.id.list, this.instantiateListFragment(), LIST_FRAGMENT_TAG).commit();
        }
        final int i2 = mCustomerSelectType;
        if (1 == i2 || 2 == i2) {
            final ActionBar supportActionBar2 = this.getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar2);
            supportActionBar2.setDisplayHomeAsUpEnabled(false);
            final ActionBar supportActionBar3 = this.getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar3);
            supportActionBar3.setHomeButtonEnabled(false);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(final int i2, final int i3, final Intent intent) {
        final Fragment findFragmentByTag;
        super.onActivityResult(i2, i3, intent);
        if (7878 == i3 && null != (findFragmentByTag = getSupportFragmentManager().findFragmentByTag(BaseListDrawerActivity.LIST_FRAGMENT_TAG)) && (findFragmentByTag instanceof CustomerListFragment)) {
            Intrinsics.checkNotNull(intent);
            final int intExtra = intent.getIntExtra(IntentExtraName.EXTRA_CUSTOMER_REF, 0);
            if (0 >= intExtra) {
                return;
            }
            ((CustomerListFragment) findFragmentByTag).notifyAdapterToChangeIfHasDailyOperation(intExtra);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(final Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        this.setIntent(intent);
        if (intent.hasExtra("query")) {
            final String stringExtra = intent.getStringExtra("query");
            mFilter = stringExtra;
            if (TextUtils.equals(stringExtra, "")) {
                mFilter = null;
            }
            final ActionBar supportActionBar = this.getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar);
            supportActionBar.setSubtitle(mFilter);
            final Fragment findFragmentByTag = this.getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
            if (findFragmentByTag instanceof CustomerListFragment) {
                ((CustomerListFragment) findFragmentByTag).filter(mFilter);
            }
            if (findFragmentByTag instanceof CustomerListShipFragment) {
                ((CustomerListShipFragment) findFragmentByTag).filterData(mFilter);
            }
        }
    }

    @Override // com.proje.mobilesales.core.base.BaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putString(CustomerListActivity.STATE_FILTER, mFilter);
        outState.putInt(CustomerListActivity.STATE_CUSTOMER_LIST_TYPE, mCustomerSelectType);
    }

    @Override // com.proje.mobilesales.core.base.BaseListDrawerActivity
    protected String getDefaultTitle() {
        final String string = this.getString(R.string.activity_title_customer);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // com.proje.mobilesales.core.base.BaseListDrawerActivity
    protected Fragment instantiateListFragment() {
        final Bundle bundle = new Bundle();
        final CustomerListFragment.Companion companion = CustomerListFragment.Companion;
        bundle.putString(companion.getEXTRA_FILTER(), mFilter);
        bundle.putInt(companion.getEXTRA_CUSTOMER_SELECT_TYPE(), mCustomerSelectType);
        String name = CustomerListFragment.class.getName();
        final int i2 = mCustomerSelectType;
        if ((1 == i2 || 2 == i2) && viewModel.erpType() == ErpType.NETSIS) {
            name = CustomerListShipFragment.class.getName();
            bundle.putString(CustomerListShipFragment.Companion.getEXTRA_SALES_CUSTOMER_CODE(), mClCode);
        }
        final Fragment instantiate = Fragment.instantiate(this, name, bundle);
        Intrinsics.checkNotNullExpressionValue(instantiate, "instantiate(...)");
        return instantiate;
    }

    /* compiled from: CustomerListActivity.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
