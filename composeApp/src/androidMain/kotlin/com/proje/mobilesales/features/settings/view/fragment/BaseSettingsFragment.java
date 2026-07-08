package com.proje.mobilesales.features.settings.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceFragmentCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.preferences.Preferences;
import kotlin.jvm.internal.Intrinsics;
 
public abstract class BaseSettingsFragment extends PreferenceFragmentCompat {
    private SharedPreferences.OnSharedPreferenceChangeListener mListener;
    private BaseSettingsFragment this0;

    protected final SharedPreferences.OnSharedPreferenceChangeListener getMListener() {
        return mListener;
    }
    protected final void setMListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        mListener = onSharedPreferenceChangeListener;
    }
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        assert context != null;
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.background_material_light));
        if (getListView() != null) {
            getListView().setVerticalScrollBarEnabled(false);
        }
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getPreferenceManager().getSharedPreferences() != null) {
            Preferences.sync(getPreferenceManager());
        }
    }
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                BaseSettingsFragment.onActivityCreatedlambda0(this0, sharedPreferences, str);
            }
        };
    }
    public static void onActivityCreatedlambda0(final BaseSettingsFragment this0, final SharedPreferences sharedPreferences, final String str) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (str != null) {
            Preferences.sync(this0.getPreferenceManager(), str);
        }
    }
    public void onResume() {
        super.onResume();
        final SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        assert sharedPreferences != null;
        sharedPreferences.registerOnSharedPreferenceChangeListener(mListener);
    }
    public void onPause() {
        final SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        assert sharedPreferences != null;
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(mListener);
        super.onPause();
    }
}
