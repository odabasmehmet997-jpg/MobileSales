package com.proje.mobilesales.features.about.view.activity;

import android.os.Bundle;
import android.view.MenuItem;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import kotlin.jvm.internal.Intrinsics;

public final class AboutActivity extends BaseErpActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_about);
        getActivityComponent().inject(this);
        setToolbar();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
