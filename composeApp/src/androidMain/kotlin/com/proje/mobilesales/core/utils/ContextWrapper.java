package com.proje.mobilesales.core.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.LocaleList;
import java.util.Locale;

public class ContextWrapper extends android.content.ContextWrapper {
    public ContextWrapper(Context context) {
        super(context);
    }
    public static ContextWrapper wrap(Context context, Locale locale) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        LocaleList localeList = new LocaleList(locale);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        return new ContextWrapper(context.createConfigurationContext(configuration));
    }
}
