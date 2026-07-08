package com.proje.mobilesales.core.utils;

import androidx.preference.Preference;
import com.proje.mobilesales.core.preferences.Preferences;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class ObservableUtils {
    public static Observable<String> preferenceObserveText(Preference preference) {
        final PublishSubject create = PublishSubject.create();
        preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference2, Object obj) {
                boolean lambdapreferenceObserveText0;

                lambdapreferenceObserveText0 = ObservableUtils.lambdapreferenceObserveText0(create, preference2, obj);
                return lambdapreferenceObserveText0;
            }
        });
        return create;
    }
    public static boolean lambdapreferenceObserveText0(PublishSubject create    , Preference preference, Object obj) {
        String obj2 = obj.toString();
        Preferences.getPrefs(preference.getContext(), preference.getKey()).edit().putString(preference.getKey(), obj2).apply();
        create.onNext(obj2);
        return true;
    }
    public static Observable<Boolean> preferenceObserveClick(Preference preference) {
        final PublishSubject create = PublishSubject.create();
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference2) {
                boolean lambdapreferenceObserveClick1;
                lambdapreferenceObserveClick1 = ObservableUtils.lambdapreferenceObserveClick1(create, preference2);
                return lambdapreferenceObserveClick1;
            }
        });
        return create;
    }
    public static boolean lambdapreferenceObserveClick1(PublishSubject create, Preference preference) {
        create.onNext(Boolean.TRUE);
        return true;
    }
}
