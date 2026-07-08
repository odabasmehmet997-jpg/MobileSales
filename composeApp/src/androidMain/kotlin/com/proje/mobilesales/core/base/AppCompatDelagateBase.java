package com.proje.mobilesales.core.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatCallback;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.settings.view.activity.BaseInjectablePreferencesActivity;
 
public abstract class AppCompatDelagateBase extends BaseInjectablePreferencesActivity {
    private AppCompatDelegate mDelegate;
    protected void onCreate(Bundle bundle) {
        getDelegate().installViewFactory();
        getDelegate().onCreate(bundle);
        super.onCreate(bundle);
        getDelegate().setContentView(R.layout.activity_preference_all);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        try {
            getDelegate().onPostCreate(bundle);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ActionBar getSupportActionBar() {
        try {
            return getDelegate().getSupportActionBar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        try {
            getDelegate().setSupportActionBar(toolbar);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public MenuInflater getMenuInflater() {
        try {
            return getDelegate().getMenuInflater();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void setContentView(@LayoutRes int i2) {
        try {
            getDelegate().setContentView(i2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void setContentView(View view) {
        try {
            getDelegate().setContentView(view);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        try {
            getDelegate().setContentView(view, layoutParams);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        try {
            getDelegate().addContentView(view, layoutParams);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    protected void onPostResume() {
        super.onPostResume();
        try {
            getDelegate().onPostResume();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    protected void onTitleChanged(CharSequence charSequence, int i2) {
        super.onTitleChanged(charSequence, i2);
        try {
            getDelegate().setTitle(charSequence);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            getDelegate().onConfigurationChanged(configuration);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void onDestroy() {
         super.onDestroy();
         try {
            getDelegate().onDestroy();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void invalidateOptionsMenu() {
        try {
            getDelegate().invalidateOptionsMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public AppCompatDelegate getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = AppCompatDelegate.create(this, null);
        }
        return this.mDelegate;
    }
}
