package com.proje.mobilesales.features.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.RequiresPermission;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.enums.AnalyticsEventType;

public class TransferGetSettingActivity extends BaseErpActivity {     @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.IMPORT_DATA;
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_transfer_get_options);
        this.setToolbar();
    }
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (16908332 == menuItem.getItemId()) {
            this.finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
