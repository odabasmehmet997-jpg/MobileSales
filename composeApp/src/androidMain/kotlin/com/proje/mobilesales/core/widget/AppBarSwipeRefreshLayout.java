package com.proje.mobilesales.core.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.proje.mobilesales.R;

public class AppBarSwipeRefreshLayout extends SwipeRefreshLayout implements AppBarLayout.OnOffsetChangedListener {
    private AppBarLayout mAppBar;
    public AppBarSwipeRefreshLayout(Context context) {
        super(context);
    }
    public AppBarSwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getContext() instanceof Activity) {
            AppBarLayout appBarLayout = ((Activity) getContext()).findViewById(R.id.appbar);
            this.mAppBar = appBarLayout;
            if (appBarLayout != null) {
                appBarLayout.addOnOffsetChangedListener(this);
            }
        }
    }
    protected void onDetachedFromWindow() {
        AppBarLayout appBarLayout = this.mAppBar;
        if (appBarLayout != null) {
            appBarLayout.removeOnOffsetChangedListener(this);
            this.mAppBar = null;
        }
        super.onDetachedFromWindow();
    }
    public void onOffsetChanged(AppBarLayout appBarLayout, int i2) {
        setEnabled(i2 == 0);
    }
}
