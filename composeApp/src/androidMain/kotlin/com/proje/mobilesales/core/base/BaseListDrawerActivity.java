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

public abstract class BaseListDrawerActivity extends BaseDrawerActivity {
    protected static final String LIST_FRAGMENT_TAG = BaseListActivity.class.getName() + ".LIST_FRAGMENT_TAG";
    ActionViewResolver mActionViewResolver;
    private AppBarLayout mAppBar;
    KeyDelegate mKeyDelegate;
    private View mListView;
    private KeyDelegate.BackInterceptor getBackInterceptor() {
        return null;
    }
    protected abstract String getDefaultTitle();
    protected abstract Fragment instantiateListFragment();
    protected boolean isSearchable() {
        return true;
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_list);
        setToolbar();
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseListDrawerActivity.this.lambdaonCreate0(view);
            }
        });
        this.mAppBar = findViewById(R.id.appbar);
        this.mListView = findViewById(R.id.list);
    }
    public   void lambdaonCreate0(View view) {
        Scrollable scrollableList = getScrollableList();
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
        this.mKeyDelegate.attach(this);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    public void onStop() {
        super.onStop();
        this.mKeyDelegate.detach(this);
    }
    public void onDestroy() {
        super.onDestroy();
    }
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        this.mKeyDelegate.setScrollable(getScrollableList(), this.mAppBar);
        this.mKeyDelegate.setBackInterceptor(getBackInterceptor());
        return this.mKeyDelegate.onKeyDown(i2, keyEvent) || super.onKeyDown(i2, keyEvent);
    }
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        return this.mKeyDelegate.onKeyUp(i2, keyEvent) || super.onKeyUp(i2, keyEvent);
    }
    public boolean onKeyLongPress(int i2, KeyEvent keyEvent) {
        return this.mKeyDelegate.onKeyLongPress(i2, keyEvent) || super.onKeyLongPress(i2, keyEvent);
    }
    private Scrollable getScrollableList() {
        return (Scrollable) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
    }
}
