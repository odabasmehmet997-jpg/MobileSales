package com.proje.mobilesales.features.customer.view.communication.ship;

import android.R;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseListActivity;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerShipAddressActivity extends BaseListActivity {
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_CUSTOMER_REF = "extra:customerRef";
    public static final String EXTRA_SALES_SELECT = "extra:customerSalesSelect";
    private static final String STATE_CUSTOMER_REF = "state:customerRef";
    private static final String STATE_FILTER = "state:filter";
    private static final String STATE_SALES_SELECT = "state:salesSelect";
    private int mCustomerRef;
    private String mFilter;
    private boolean mSalesSelect;
     public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mFilter = bundle.getString(STATE_FILTER);
            this.mCustomerRef = bundle.getInt("state:customerRef");
            this.mSalesSelect = bundle.getBoolean(STATE_SALES_SELECT);
            ActionBar supportActionBar = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar);
            supportActionBar.setSubtitle(this.mFilter);
            return;
        }
        this.mCustomerRef = getIntent().getIntExtra("extra:customerRef", 0);
        this.mSalesSelect = getIntent().getBooleanExtra(EXTRA_SALES_SELECT, false);
        getSupportFragmentManager().beginTransaction().replace(R.id.list, instantiateListFragment(), BaseListActivity.LIST_FRAGMENT_TAG).commit();
    }
    public void onNewIntent(Intent intent) {
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
            CustomerShipAddressFragment customerShipAddressFragment = (CustomerShipAddressFragment) getSupportFragmentManager().findFragmentByTag(BaseListActivity.LIST_FRAGMENT_TAG);
            if (customerShipAddressFragment != null) {
                customerShipAddressFragment.filter(this.mFilter);
            }
        }
    }
     public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putString(STATE_FILTER, this.mFilter);
        outState.putBoolean(STATE_SALES_SELECT, this.mSalesSelect);
        outState.putInt("state:customerRef", this.mCustomerRef);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public String getDefaultTitle() {
        String string = getString(R.string.activity_title_customer_ship_address);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public Fragment instantiateListFragment() {
        Bundle bundle = new Bundle();
        CustomerShipAddressFragment.Companion companion = CustomerShipAddressFragment.Companion;
        bundle.putString(companion.getEXTRA_FILTER(), this.mFilter);
        bundle.putInt(companion.getEXTRA_CUSTOMER_REF(), this.mCustomerRef);
        bundle.putBoolean(companion.getEXTRA_SALES_SELECT(), this.mSalesSelect);
        Fragment instantiate = Fragment.instantiate(this, CustomerShipAddressFragment.class.getName(), bundle);
        Intrinsics.checkNotNullExpressionValue(instantiate, "instantiate(...)");
        return instantiate;
    }

    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
