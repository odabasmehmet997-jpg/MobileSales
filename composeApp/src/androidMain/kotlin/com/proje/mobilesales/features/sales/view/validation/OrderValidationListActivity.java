package com.proje.mobilesales.features.sales.view.validation;

import android.R;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseListActivity;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class OrderValidationListActivity extends BaseListActivity {
    public static final Companion Companion = new Companion(null);
    private static final String STATE_FILTER = "state:filter";
    private String mFilter = "";

    public boolean isSearchable() {
        return false;
    }
    public void onCreate(Bundle bundle) {
        this.analyticsEventType = AnalyticsEventType.ORDER_APPROVAL;
        super.onCreate(bundle);
        if (bundle != null) {
            this.mFilter = bundle.getString(STATE_FILTER);
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.list, instantiateListFragment(), BaseListActivity.LIST_FRAGMENT_TAG).commit();
        }
    }
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putString(STATE_FILTER, this.mFilter);
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
            OrderValidationListFragment orderValidationListFragment = (OrderValidationListFragment) getSupportFragmentManager().findFragmentByTag(BaseListActivity.LIST_FRAGMENT_TAG);
            if (orderValidationListFragment != null) {
                orderValidationListFragment.filter(this.mFilter);
            }
        }
    }
    public String getDefaultTitle() {
        String string = getString(R.string.activity_title_order);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }
    protected Fragment instantiateListFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(OrderValidationListFragment.EXTRA_FILTER, this.mFilter);
        Fragment instantiate = Fragment.instantiate(this, OrderValidationListFragment.class.getName(), bundle);
        Intrinsics.checkNotNullExpressionValue(instantiate, "instantiate(...)");
        return instantiate;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
