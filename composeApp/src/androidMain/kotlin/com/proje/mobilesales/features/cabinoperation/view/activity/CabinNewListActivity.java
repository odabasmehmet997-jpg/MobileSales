package com.proje.mobilesales.features.cabinoperation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseFicheListActivity;
import com.proje.mobilesales.core.base.BaseFicheListFragment;
import com.proje.mobilesales.core.base.BaseListActivity;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.features.cabinoperation.view.fragment.CabinNewListFragment;
import kotlin.jvm.internal.Intrinsics;

public final class CabinNewListActivity extends BaseFicheListActivity {
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.CABIN_OPERATION;
        super.onCreate(bundle);
    }
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
            final CabinNewListFragment cabinNewListFragment = (CabinNewListFragment) this.getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
            if (null != cabinNewListFragment) {
                cabinNewListFragment.filter(mFilter);
            }
        }
    }

    @Override // com.proje.mobilesales.core.base.BaseFicheListActivity, android.app.Activity
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // com.proje.mobilesales.core.base.BaseListActivity
    public String getDefaultTitle() {
        final String string = this.getString(R.string.activity_title_cabin_list);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // com.proje.mobilesales.core.base.BaseListActivity
    protected Fragment instantiateListFragment() {
        final Bundle bundle = new Bundle();
        bundle.putString(BaseFicheListFragment.EXTRA_FILTER, mFilter);
        bundle.putInt(BaseFicheListFragment.EXTRA_CUSTOMER_REF, mCustomerRef);
        bundle.putParcelable(BaseFicheListFragment.EXTRA_CUSTOMER_OPERATION, mCustomerOperation);
        bundle.putInt(BaseFicheListFragment.EXTRA_ROUTEDAY_REF, routeDayRef);
        bundle.putInt(BaseFicheListFragment.EXTRA_ROUTEPLAN_REF, routePlanRef);
        bundle.putInt(BaseFicheListFragment.EXTRA_ROUTEUSERCUSTOMER_REF, routeUserCustomerRef);
        final Fragment instantiate = Fragment.instantiate(this, CabinNewListFragment.class.getName(), bundle);
        Intrinsics.checkNotNullExpressionValue(instantiate, "instantiate(...)");
        return instantiate;
    }
}
