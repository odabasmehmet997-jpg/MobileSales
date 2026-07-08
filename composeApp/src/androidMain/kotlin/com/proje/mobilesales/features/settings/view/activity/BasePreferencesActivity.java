package com.proje.mobilesales.features.settings.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.CallSuper;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;
import kotlin.jvm.internal.Intrinsics;

public abstract class BasePreferencesActivity extends android.preference.PreferenceActivity {
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.overridePendingTransition(R.anim.pull_in_from_left, R.anim.pull_out_to_left);
        ContextUtils.setmContext(this);
    }
    public void onConfigurationChanged(final Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
    }
    protected void onStart() {
        super.onStart();
        ContextUtils.setmContext(this);
    }
    protected void onResume() {
        super.onResume();
        ContextUtils.setmContext(this);
    }
    protected void onStop() {
        super.onStop();
        ContextUtils.setmContext(null);
    }
    public final void startActivity(final Class<?> cls) {
        this.startActivity(new Intent(this, cls));
        ContextUtils.setmContext(this);
    }
}
