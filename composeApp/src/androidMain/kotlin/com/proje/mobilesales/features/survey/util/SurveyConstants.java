package com.proje.mobilesales.features.survey.util;

import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.ContextUtils;
import dagger.Lazy;
import kotlin.LazyKt;

public final class SurveyConstants {
    public static final String BASE_URL = "https://productservices.proje.com.tr/Survey/";
    public static final SurveyConstants INSTANCE = new SurveyConstants();
    private static final Lazy CLIENT_SECRETdelegate = (Lazy) LazyKt.lazy(() -> Preferences.getClientSecret(Preferences.getSecurePreferences(ContextUtils.getmContext())));
    private SurveyConstants() {
    }
    public String getCLIENT_SECRET() {
        return CLIENT_SECRETdelegate.getValue().toString();
    }
}
