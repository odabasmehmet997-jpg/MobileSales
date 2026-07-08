package com.proje.mobilesales.core.base;

import android.Manifest;
import android.R;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class BaseFicheListActivity extends BaseListActivity {
    public static final Companion Companion = new Companion(null);
    private static final String STATE_CUSTOMER_OPERATION = "state:customerOperation";
    private static final String STATE_CUSTOMER_REF = "state:customerRef";
    private static final String STATE_FILTER = "state:filter";
    protected CustomerOperation mCustomerOperation;
    protected int mCustomerRef;
    protected String mFilter;
    public boolean isSearchable() {
        return false;
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(Bundle bundle) {
        String str;
        String operationName;
        super.onCreate(bundle);
        if (bundle != null) {
            this.mFilter = bundle.getString(STATE_FILTER);
            this.mCustomerOperation = bundle.getParcelable(STATE_CUSTOMER_OPERATION);
            this.mCustomerRef = bundle.getInt("state:customerRef");
            ActionBar supportActionBar = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar);
            supportActionBar.setSubtitle(this.mFilter);
        } else {
            Bundle extras = getIntent().getExtras();
            Intrinsics.checkNotNull(extras);
            this.mCustomerOperation = extras.getParcelable(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE);
            Bundle extras2 = getIntent().getExtras();
            Intrinsics.checkNotNull(extras2);
            this.mCustomerRef = extras2.getInt(IntentExtraName.EXTRA_CUSTOMER_REF);
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            Fragment instantiateListFragment = instantiateListFragment();
            Intrinsics.checkNotNull(instantiateListFragment);
            beginTransaction.replace(R.id.list, instantiateListFragment, BaseListActivity.LIST_FRAGMENT_TAG).commit();
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar2);
        CustomerOperation customerOperation = this.mCustomerOperation;
        if (customerOperation == null || (operationName = customerOperation.getOperationName()) == null) {
            str = null;
        } else {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            str = operationName.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(str, "toUpperCase(...)");
        }
        supportActionBar2.setTitle(str);
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putString(STATE_FILTER, this.mFilter);
        outState.putParcelable(STATE_CUSTOMER_OPERATION, this.mCustomerOperation);
        outState.putInt("state:customerRef", this.mCustomerRef);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public final void setmCustomerRef(int i2) {
        this.mCustomerRef = i2;
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
