package com.proje.mobilesales.features.activity;

import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LoginActivity_MembersInjector implements MembersInjector<LoginActivity> {
    private final Provider<SharedPreferencesHelper> mSharedPreferencesHelperProvider;

    public LoginActivity_MembersInjector(final Provider<SharedPreferencesHelper> provider) {
        mSharedPreferencesHelperProvider = provider;
    }

    public static MembersInjector<LoginActivity> create(final Provider<SharedPreferencesHelper> provider) {
        return new LoginActivity_MembersInjector(provider);
    }

    public void injectMembers(final LoginActivity loginActivity) {
        LoginActivity_MembersInjector.injectMSharedPreferencesHelper(loginActivity, mSharedPreferencesHelperProvider.get());
    }

    public static void injectMSharedPreferencesHelper(final LoginActivity loginActivity, final SharedPreferencesHelper sharedPreferencesHelper) {
        loginActivity.mSharedPreferencesHelper = sharedPreferencesHelper;
    }
}
