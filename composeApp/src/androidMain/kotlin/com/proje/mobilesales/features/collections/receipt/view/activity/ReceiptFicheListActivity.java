package com.proje.mobilesales.features.collections.receipt.view.activity;

import android.app.Activity;
import android.content.Context;
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
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.collections.receipt.view.fragment.ReceiptFicheListFragment;
import kotlin.jvm.internal.Intrinsics;

public final class ReceiptFicheListActivity extends BaseFicheListActivity {
    private AlertDialogBuilder<?> mAlertDialogBuilder;

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(final AlertDialogBuilder<?> alertDialogBuilder) {
        mAlertDialogBuilder = alertDialogBuilder;
    }
     public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
         this.getActivityComponent().inject(this);
        final Context context = ContextUtils.getmContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mAlertDialogBuilder = new AlertDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
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
            final ReceiptFicheListFragment receiptFicheListFragment = (ReceiptFicheListFragment) this.getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
            if (null != receiptFicheListFragment) {
                receiptFicheListFragment.filter(mFilter);
            }
        }
    }

    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public String getDefaultTitle() {
        final String string = this.getString(R.string.str_collections);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    protected Fragment instantiateListFragment() {
        final Bundle bundle = new Bundle();
        bundle.putString(BaseFicheListFragment.EXTRA_FILTER, mFilter);
        bundle.putInt(BaseFicheListFragment.EXTRA_CUSTOMER_REF, mCustomerRef);
        bundle.putParcelable(BaseFicheListFragment.EXTRA_CUSTOMER_OPERATION, mCustomerOperation);
        bundle.putInt(BaseFicheListFragment.EXTRA_ROUTEDAY_REF, routeDayRef);
        bundle.putInt(BaseFicheListFragment.EXTRA_ROUTEPLAN_REF, routePlanRef);
        bundle.putInt(BaseFicheListFragment.EXTRA_ROUTEUSERCUSTOMER_REF, routeUserCustomerRef);
        final Fragment instantiate = Fragment.instantiate(this, ReceiptFicheListFragment.class.getName(), bundle);
        Intrinsics.checkNotNullExpressionValue(instantiate, "instantiate(...)");
        return instantiate;
    }
}
