package com.proje.mobilesales.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import androidx.preference.PreferenceManager;
import java.util.Locale;

public class LanguageHelper {
    private static final String SELECTED_LANGUAGE = "pref_language_key";
    public static Context onAttach(Context context) {
        return setLanguage(context, getPersistedData(context, Locale.getDefault().getLanguage()));
    }
    public static Context onAttach(Context context, String str) {
        return setLanguage(context, getPersistedData(context, str));
    }
    public static String getLanguage(Context context) {
        return getPersistedData(context, Locale.getDefault().getLanguage());
    }
    private static Context setLanguage(Context context, String str) {
        persist(context, str);
        return updateResources(context, str);
    }
    private static String getPersistedData(Context context, String str) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(SELECTED_LANGUAGE, str);
    }
    private static void persist(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(SELECTED_LANGUAGE, str);
        edit.apply();
    }
    private static Context updateResources(Context context, String str) {
        Locale locale = new Locale(str);
        Configuration configuration = context.getResources().getConfiguration();
        LocaleList localeList = new LocaleList(locale);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        return context.createConfigurationContext(configuration);
    }
    private static Context updateResourcesLegacy(Context context, String str) {
        Locale locale = new Locale(str);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }
}
