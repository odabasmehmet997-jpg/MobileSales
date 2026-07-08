package com.proje.mobilesales.core.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.core.widget.NestedScrollView;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.Scrollable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.kxml2.wap.Wbxml;

public class KeyDelegate {
    private static final int DIRECTION_DOWN = 2;
    private static final int DIRECTION_NONE = 0;
    private static final int DIRECTION_UP = 1;
    private AppBarLayout mAppBarLayout;
    private BackInterceptor mBackInterceptor;
    private boolean mEnabled;
    private String mPreferenceKey;
    private Scrollable mScrollable;
    private boolean mAppBarEnabled = true;
    private final SharedPreferences.OnSharedPreferenceChangeListener mPreferenceListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            KeyDelegate.this.lambdanew0(sharedPreferences, str);
        }
    };
    public interface BackInterceptor {
        boolean onBackPressed();
    }
    public @interface Direction {}
    public void lambdanew0(SharedPreferences sharedPreferences, String str) {
        if (TextUtils.equals(str, this.mPreferenceKey)) {
            this.mEnabled = sharedPreferences.getBoolean(str, true);
        }
    }
    public void attach(Activity activity) {
        this.mPreferenceKey = activity.getString(R.string.pref_volume);
        this.mEnabled = PreferenceManager.getDefaultSharedPreferences(activity).getBoolean(this.mPreferenceKey, true);
        PreferenceManager.getDefaultSharedPreferences(activity).registerOnSharedPreferenceChangeListener(this.mPreferenceListener);
    }
    public void detach(Activity activity) {
        PreferenceManager.getDefaultSharedPreferences(activity).unregisterOnSharedPreferenceChangeListener(this.mPreferenceListener);
        this.mScrollable = null;
        this.mAppBarLayout = null;
    }
    public void setScrollable(Scrollable scrollable, AppBarLayout appBarLayout) {
        this.mScrollable = scrollable;
        this.mAppBarLayout = appBarLayout;
    }
    public void setAppBarEnabled(boolean z) {
        this.mAppBarEnabled = z;
    }
    public void setBackInterceptor(BackInterceptor backInterceptor) {
        this.mBackInterceptor = backInterceptor;
    }
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4 && keyEvent.getAction() == 0) {
            BackInterceptor backInterceptor = this.mBackInterceptor;
            return backInterceptor != null && backInterceptor.onBackPressed();
        }
        if (!this.mEnabled) {
            return false;
        }
        if (i2 != 24 && i2 != 25) {
            return false;
        }
        keyEvent.startTracking();
        return true;
    }
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (!this.mEnabled) {
            return false;
        }
        boolean z = (keyEvent.getFlags() & 256) == 0;
        if (i2 == 24 && z) {
            shortPress(1);
            return true;
        }
        if (i2 != 25 || !z) {
            return false;
        }
        shortPress(2);
        return true;
    }
    public boolean onKeyLongPress(int i2, KeyEvent keyEvent) {
        if (!this.mEnabled) {
            return false;
        }
        if (i2 == 24) {
            longPress(1);
            return true;
        }
        if (i2 != 25) {
            return false;
        }
        longPress(2);
        return true;
    }
    private void shortPress(int i2) {
        AppBarLayout appBarLayout;
        AppBarLayout appBarLayout2;
        Scrollable scrollable = this.mScrollable;
        if (scrollable == null) {
            return;
        }
        if (i2 == 1) {
            if (scrollable.scrollToPrevious() || !this.mAppBarEnabled || (appBarLayout = this.mAppBarLayout) == null) {
                return;
            }
            appBarLayout.setExpanded(true, true);
            return;
        }
        if (i2 != 2) {
            return;
        }
        if (this.mAppBarEnabled && (appBarLayout2 = this.mAppBarLayout) != null && appBarLayout2.getHeight() == this.mAppBarLayout.getBottom()) {
            this.mAppBarLayout.setExpanded(false, true);
        } else {
            this.mScrollable.scrollToNext();
        }
    }
    private void longPress(int i2) {
        AppBarLayout appBarLayout;
        if (i2 != 1) {
            return;
        }
        if (this.mAppBarEnabled && (appBarLayout = this.mAppBarLayout) != null) {
            appBarLayout.setExpanded(true, true);
        }
        Scrollable scrollable = this.mScrollable;
        if (scrollable != null) {
            scrollable.scrollToTop();
        }
    }
    public static class RecyclerViewHelper implements Scrollable {
        public static final int SCROLL_ITEM = 0;
        public static final int SCROLL_PAGE = 1;
        private final LinearLayoutManager mLayoutManager;
        private final RecyclerView mRecyclerView;
        private final int mScrollMode;

        @Retention(RetentionPolicy.SOURCE)
        @interface ScrollMode {
        }

        public RecyclerViewHelper(RecyclerView recyclerView, int i2) {
            this.mRecyclerView = recyclerView;
            if (!(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
                throw new IllegalArgumentException("Only LinearLayoutManager supported");
            }
            this.mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            this.mScrollMode = i2;
        }

        @Override // com.proje.mobilesales.core.interfaces.Scrollable
        public void scrollToTop() {
            this.mRecyclerView.smoothScrollToPosition(0);
        }

        @Override // com.proje.mobilesales.core.interfaces.Scrollable
        public boolean scrollToNext() {
            int findLastCompletelyVisibleItemPosition;
            if (this.mScrollMode == 0) {
                findLastCompletelyVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
            } else {
                findLastCompletelyVisibleItemPosition = this.mLayoutManager.findLastCompletelyVisibleItemPosition();
            }
            int i2 = findLastCompletelyVisibleItemPosition != -1 ? findLastCompletelyVisibleItemPosition + 1 : -1;
            if (i2 == -1 || i2 >= this.mRecyclerView.getAdapter().getItemCount()) {
                return false;
            }
            this.mRecyclerView.smoothScrollToPosition(i2);
            return true;
        }

        @Override // com.proje.mobilesales.core.interfaces.Scrollable
        public boolean scrollToPrevious() {
            if (this.mScrollMode != 1) {
                int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
                int i2 = findFirstVisibleItemPosition != -1 ? findFirstVisibleItemPosition - 1 : -1;
                if (i2 < 0) {
                    return false;
                }
                this.mRecyclerView.smoothScrollToPosition(i2);
                return true;
            }
            if (this.mLayoutManager.findFirstVisibleItemPosition() <= 0) {
                return false;
            }
            RecyclerView recyclerView = this.mRecyclerView;
            recyclerView.smoothScrollBy(0, -recyclerView.getHeight());
            return true;
        }
    }
    public static class NestedScrollViewHelper implements Scrollable {
        private final NestedScrollView mScrollView;

        public NestedScrollViewHelper(NestedScrollView nestedScrollView) {
            this.mScrollView = nestedScrollView;
        }

        @Override // com.proje.mobilesales.core.interfaces.Scrollable
        public void scrollToTop() {
            this.mScrollView.smoothScrollTo(0, 0);
        }

        @Override // com.proje.mobilesales.core.interfaces.Scrollable
        public boolean scrollToNext() {
            return this.mScrollView.pageScroll(Wbxml.EXT_T_2);
        }

        @Override // com.proje.mobilesales.core.interfaces.Scrollable
        public boolean scrollToPrevious() {
            return this.mScrollView.pageScroll(33);
        }
    }
}
