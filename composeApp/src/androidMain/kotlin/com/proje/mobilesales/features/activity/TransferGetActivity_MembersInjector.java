package com.proje.mobilesales.features.activity;

import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivity_MembersInjector;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import dagger.MembersInjector;
import javax.inject.Provider;



public final class TransferGetActivity_MembersInjector implements MembersInjector<TransferGetActivity> {
    private final Provider<BaseErp> baseErpProvider;
    private final Provider<AlertDialogBuilder> mAlertDialogBuilderProvider;
    private final Provider<AlertDialogBuilder> mBaseAlertDialogBuilderProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider;
    private final Provider<SharedPreferencesHelper> mSharedPreferencesHelperProvider;

    public TransferGetActivity_MembersInjector(final Provider<BaseErp> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<AlertDialogBuilder> provider3, final Provider<SharedPreferencesHelper> provider4, final Provider<AlertDialogBuilder> provider5) {
        baseErpProvider = provider;
        mProgressDialogBuilderProvider = provider2;
        mBaseAlertDialogBuilderProvider = provider3;
        mSharedPreferencesHelperProvider = provider4;
        mAlertDialogBuilderProvider = provider5;
    }

    public static MembersInjector<TransferGetActivity> create(final Provider<BaseErp> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<AlertDialogBuilder> provider3, final Provider<SharedPreferencesHelper> provider4, final Provider<AlertDialogBuilder> provider5) {
        return new TransferGetActivity_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(final TransferGetActivity transferGetActivity) {
        BaseErpActivity_MembersInjector.injectBaseErp(transferGetActivity, baseErpProvider.get());
        BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(transferGetActivity, mProgressDialogBuilderProvider.get());
        BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(transferGetActivity, mBaseAlertDialogBuilderProvider.get());
        TransferGetActivity_MembersInjector.injectMSharedPreferencesHelper(transferGetActivity, mSharedPreferencesHelperProvider.get());
        TransferGetActivity_MembersInjector.injectMAlertDialogBuilder(transferGetActivity, mAlertDialogBuilderProvider.get());
    }

    public static void injectMSharedPreferencesHelper(final TransferGetActivity transferGetActivity, final SharedPreferencesHelper sharedPreferencesHelper) {
        transferGetActivity.mSharedPreferencesHelper = sharedPreferencesHelper;
    }

    public static void injectMAlertDialogBuilder(final TransferGetActivity transferGetActivity, final AlertDialogBuilder alertDialogBuilder) {
        transferGetActivity.mAlertDialogBuilder = alertDialogBuilder;
    }
}
