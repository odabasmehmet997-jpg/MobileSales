package com.proje.mobilesales.core.base;

import android.Manifest;
import android.R;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.annotation.RequiresPermission;
import androidx.fragment.app.Fragment;
import com.google.android.material.appbar.AppBarLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.Scrollable;
import com.proje.mobilesales.core.utils.KeyDelegate;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class BaseListActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    public static final String LIST_FRAGMENT_TAG = BaseListActivity.class.getName() + ".LIST_FRAGMENT_TAG";
    private ActionViewResolver mActionViewResolver;
    private AppBarLayout mAppBar;
    private KeyDelegate mKeyDelegate;
    private View mListView;
    private final KeyDelegate.BackInterceptor getBackInterceptor() {
        return null;
    }
    protected abstract Fragment instantiateListFragment();
    public boolean isSearchable() {
        return true;
    }
    public final KeyDelegate getMKeyDelegate() {
        return this.mKeyDelegate;
    }
    public final void setMKeyDelegate(KeyDelegate keyDelegate) {
        this.mKeyDelegate = keyDelegate;
    }
    public final ActionViewResolver getMActionViewResolver() {
        return this.mActionViewResolver;
    }
    public final void setMActionViewResolver(ActionViewResolver actionViewResolver) {
        this.mActionViewResolver = actionViewResolver;
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_list);
        setToolbar();
        this.mKeyDelegate = new KeyDelegate();
        this.mActionViewResolver = new ActionViewResolver();
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            public  void BaseListActivityExternalSyntheticLambda0() {
            }

            public void onClick(View view) {
                BaseListActivity.onCreatelambda0(BaseListActivity.this, view);
            }
        });
        View findViewById = findViewById(R.id.appbar);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type com.google.android.material.appbar.AppBarLayout");
        this.mAppBar = (AppBarLayout) findViewById;
        this.mListView = findViewById(R.id.list);
    }
    public static final void onCreatelambda0(BaseListActivity this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Scrollable scrollableList = this0.getScrollableList();
        if (scrollableList != null) {
            scrollableList.scrollToTop();
        }
    }
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    public void onStart() {
        super.onStart();
        KeyDelegate keyDelegate = this.mKeyDelegate;
        Intrinsics.checkNotNull(keyDelegate);
        keyDelegate.attach(this);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    public void onStop() {
        super.onStop();
        KeyDelegate keyDelegate = this.mKeyDelegate;
        Intrinsics.checkNotNull(keyDelegate);
        keyDelegate.detach(this);
    }
    public void onDestroy() {
        super.onDestroy();
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    public boolean onKeyDown(int i2, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        KeyDelegate keyDelegate = this.mKeyDelegate;
        Intrinsics.checkNotNull(keyDelegate);
        keyDelegate.setScrollable(getScrollableList(), this.mAppBar);
        KeyDelegate keyDelegate2 = this.mKeyDelegate;
        Intrinsics.checkNotNull(keyDelegate2);
        keyDelegate2.setBackInterceptor(getBackInterceptor());
        KeyDelegate keyDelegate3 = this.mKeyDelegate;
        Intrinsics.checkNotNull(keyDelegate3);
        return keyDelegate3.onKeyDown(i2, event) || super.onKeyDown(i2, event);
    }
    public boolean onKeyUp(int i2, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        KeyDelegate keyDelegate = this.mKeyDelegate;
        Intrinsics.checkNotNull(keyDelegate);
        return keyDelegate.onKeyUp(i2, event) || super.onKeyUp(i2, event);
    }
    public boolean onKeyLongPress(int i2, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        KeyDelegate keyDelegate = this.mKeyDelegate;
        Intrinsics.checkNotNull(keyDelegate);
        return keyDelegate.onKeyLongPress(i2, event) || super.onKeyLongPress(i2, event);
    }

    private final Scrollable getScrollableList() {
        return (Scrollable) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
    }

    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public String getDefaultTitle() {
        return "";
    }
}
