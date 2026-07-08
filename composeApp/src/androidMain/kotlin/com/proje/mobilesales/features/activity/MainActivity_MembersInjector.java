package com.proje.mobilesales.features.activity;

import com.proje.mobilesales.core.base.BaseDrawerActivity_MembersInjector;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivity_MembersInjector;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
    private final Provider<BaseErp> baseErpProvider;
    private final Provider<AlertDialogBuilder> mAlertDialogBuilderProvider;
    private final Provider<AlertDialogBuilder> mAlertDialogBuilderProvider2;
    private final Provider<AlertDialogBuilder> mBaseAlertDialogBuilderProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider2;
    private final Provider<SharedPreferencesHelper> mSharedPreferencesHelperProvider;
    private final Provider<ISqlManager> sqlManagerProvider;

    public MainActivity_MembersInjector(final Provider<BaseErp> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<AlertDialogBuilder> provider3, final Provider<AlertDialogBuilder> provider4, final Provider<AlertDialogBuilder> provider5, final Provider<ProgressDialogBuilder> provider6, final Provider<ISqlManager> provider7, final Provider<SharedPreferencesHelper> provider8) {
        baseErpProvider = provider;
        mProgressDialogBuilderProvider = provider2;
        mBaseAlertDialogBuilderProvider = provider3;
        mAlertDialogBuilderProvider = provider4;
        mAlertDialogBuilderProvider2 = provider5;
        mProgressDialogBuilderProvider2 = provider6;
        sqlManagerProvider = provider7;
        mSharedPreferencesHelperProvider = provider8;
    }

    public static MembersInjector<MainActivity> create(final Provider<BaseErp> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<AlertDialogBuilder> provider3, final Provider<AlertDialogBuilder> provider4, final Provider<AlertDialogBuilder> provider5, final Provider<ProgressDialogBuilder> provider6, final Provider<ISqlManager> provider7, final Provider<SharedPreferencesHelper> provider8) {
        return new MainActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(final MainActivity mainActivity) {
        BaseErpActivity_MembersInjector.injectBaseErp(mainActivity, baseErpProvider.get());
        BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(mainActivity, mProgressDialogBuilderProvider.get());
        BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(mainActivity, mBaseAlertDialogBuilderProvider.get());
        BaseDrawerActivity_MembersInjector.injectMAlertDialogBuilder(mainActivity, mAlertDialogBuilderProvider.get());
        MainActivity_MembersInjector.injectMAlertDialogBuilder(mainActivity, mAlertDialogBuilderProvider2.get());
        MainActivity_MembersInjector.injectMProgressDialogBuilder(mainActivity, mProgressDialogBuilderProvider2.get());
        MainActivity_MembersInjector.injectSqlManager(mainActivity, sqlManagerProvider.get());
        MainActivity_MembersInjector.injectMSharedPreferencesHelper(mainActivity, mSharedPreferencesHelperProvider.get());
    }

    public static void injectMAlertDialogBuilder(final MainActivity mainActivity, final AlertDialogBuilder alertDialogBuilder) {
        mainActivity.mAlertDialogBuilder = alertDialogBuilder;
    }

    public static void injectMProgressDialogBuilder(final MainActivity mainActivity, final ProgressDialogBuilder progressDialogBuilder) {
        mainActivity.mProgressDialogBuilder = progressDialogBuilder;
    }

    public static void injectSqlManager(final MainActivity mainActivity, final ISqlManager iSqlManager) {
        mainActivity.sqlManager = iSqlManager;
    }

    public static void injectMSharedPreferencesHelper(final MainActivity mainActivity, final SharedPreferencesHelper sharedPreferencesHelper) {
        mainActivity.mSharedPreferencesHelper = sharedPreferencesHelper;
    }
}
