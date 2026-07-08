package com.proje.mobilesales.features.visit.view.activity;

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
import com.proje.mobilesales.features.visit.view.fragment.VisitListFragment;
import kotlin.jvm.internal.Intrinsics;

public final class VisitListActivityNew extends BaseFicheListActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
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
            VisitListFragment visitListFragment = (VisitListFragment) getSupportFragmentManager().findFragmentByTag(BaseListActivity.LIST_FRAGMENT_TAG);
            if (visitListFragment != null) {
                visitListFragment.filter(this.mFilter);
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
        String string = getString(R.string.activity_title_visit_list);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }
    protected Fragment instantiateListFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(BaseFicheListFragment.EXTRA_FILTER, this.mFilter);
        bundle.putInt(BaseFicheListFragment.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        bundle.putParcelable(BaseFicheListFragment.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
        bundle.putInt(BaseFicheListFragment.EXTRA_ROUTEDAY_REF, this.routeDayRef);
        bundle.putInt(BaseFicheListFragment.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
        bundle.putInt(BaseFicheListFragment.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
        Fragment instantiate = Fragment.instantiate(this, VisitListFragment.class.getName(), bundle);
        Intrinsics.checkNotNullExpressionValue(instantiate, "instantiate(...)");
        return instantiate;
    }
}
