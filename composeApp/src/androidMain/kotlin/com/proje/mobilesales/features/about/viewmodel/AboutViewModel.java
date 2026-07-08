package com.proje.mobilesales.features.about.viewmodel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.extensions.ViewExtensionsKt;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.features.about.model.contants.AboutConstants;
import com.proje.mobilesales.features.about.repository.IAboutRepository;
import com.proje.mobilesales.features.about.view.fragment.AboutFragment;
import kotlin.jvm.internal.Intrinsics;
  
public final class AboutViewModel extends BaseViewModel { 
    public AboutViewModel(IAboutRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
    }
    public void bindPreferences(final AboutFragment fragment) {
        String str;
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Preference findPreference = fragment.findPreference(fragment.getString(R.string.pref_pVersion));
        Intrinsics.checkNotNull(findPreference);
        findPreference.setSummary(BuildConfig.VERSION_NAME);
        Preference findPreference2 = fragment.findPreference(fragment.getString(R.string.pref_pDBVersion));
        Intrinsics.checkNotNull(findPreference2);
        findPreference2.setSummary(getBaseErp().getLogoSqlHelper().getDatabaseVersion() + " - 665");
        Preference findPreference3 = fragment.findPreference(fragment.getString(R.string.pref_pLink));
        Intrinsics.checkNotNull(findPreference3);
        findPreference3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {  
            public boolean onPreferenceClick(Preference preference) {
                boolean bindPreferenceslambda0;
                bindPreferenceslambda0 = AboutViewModel.bindPreferenceslambda0(fragment, preference);
                return bindPreferenceslambda0;
            }
        });
        Preference findPreference4 = fragment.findPreference("pLicence");
        if (!getBaseErp().getErpRights().isPro()) {
            str = AboutConstants.BASIC;
        } else {
            str = AboutConstants.PRO;
        }
        Intrinsics.checkNotNull(findPreference4);
        findPreference4.setSummary(str);
        Preference findPreference5 = fragment.findPreference("pErpType");
        Intrinsics.checkNotNull(findPreference5);
        findPreference5.setSummary(Preferences.getErpType(fragment.getContext()).erpName);
        Preference findPreference6 = fragment.findPreference("pPrivacyLink");
        Intrinsics.checkNotNull(findPreference6);
        findPreference6.setSummary(ViewExtensionsKt.privacyPolicyUrl(fragment.getContext()));
        findPreference6.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                boolean bindPreferenceslambda1;
                bindPreferenceslambda1 = AboutViewModel.bindPreferenceslambda1(fragment, preference);
                return bindPreferenceslambda1;
            }
        });
    }
    public static boolean bindPreferenceslambda0(PreferenceFragmentCompat fragment, Preference preference) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        @SuppressLint("UnsafeImplicitIntentLaunch") Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(AboutConstants.URL));
        fragment.startActivity(intent);
        return true;
    }
    public static boolean bindPreferenceslambda1(PreferenceFragmentCompat fragment, Preference preference) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(preference, "preference");
        String valueOf = String.valueOf(preference.getSummary());
        @SuppressLint("UnsafeImplicitIntentLaunch") Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(valueOf));
        fragment.startActivity(intent);
        return true;
    }
}
