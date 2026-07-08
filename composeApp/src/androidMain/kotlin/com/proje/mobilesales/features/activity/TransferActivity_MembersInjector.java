package com.proje.mobilesales.features.activity;

import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivity_MembersInjector;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class TransferActivity_MembersInjector implements MembersInjector<TransferActivity> {
    private final Provider<BaseErp> baseErpProvider;
    private final Provider<AlertDialogBuilder> mAlertDialogBuilderProvider;
    private final Provider<AlertDialogBuilder> mBaseAlertDialogBuilderProvider;
    private final Provider<AlertDialogBuilder> mDeleteDataAlertDialogBuilderProvider;
    private final Provider<AlertDialogBuilder> mDeleteDataConfirmDialogBuilderProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider;
    private final Provider<SharedPreferencesHelper> mSharedPreferencesHelperProvider;
    private final Provider<AlertDialogBuilder> mUnsentCabinAlertDialogBuilderProvider;

    public TransferActivity_MembersInjector(final Provider<BaseErp> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<AlertDialogBuilder> provider3, final Provider<SharedPreferencesHelper> provider4, final Provider<AlertDialogBuilder> provider5, final Provider<AlertDialogBuilder> provider6, final Provider<AlertDialogBuilder> provider7, final Provider<AlertDialogBuilder> provider8) {
        baseErpProvider = provider;
        mProgressDialogBuilderProvider = provider2;
        mBaseAlertDialogBuilderProvider = provider3;
        mSharedPreferencesHelperProvider = provider4;
        mAlertDialogBuilderProvider = provider5;
        mDeleteDataAlertDialogBuilderProvider = provider6;
        mDeleteDataConfirmDialogBuilderProvider = provider7;
        mUnsentCabinAlertDialogBuilderProvider = provider8;
    }

    public static MembersInjector<TransferActivity> create(final Provider<BaseErp> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<AlertDialogBuilder> provider3, final Provider<SharedPreferencesHelper> provider4, final Provider<AlertDialogBuilder> provider5, final Provider<AlertDialogBuilder> provider6, final Provider<AlertDialogBuilder> provider7, final Provider<AlertDialogBuilder> provider8) {
        return new TransferActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(final TransferActivity transferActivity) {
        BaseErpActivity_MembersInjector.injectBaseErp(transferActivity, baseErpProvider.get());
        BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(transferActivity, mProgressDialogBuilderProvider.get());
        BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(transferActivity, mBaseAlertDialogBuilderProvider.get());
        TransferActivity_MembersInjector.injectMSharedPreferencesHelper(transferActivity, mSharedPreferencesHelperProvider.get());
        TransferActivity_MembersInjector.injectMAlertDialogBuilder(transferActivity, mAlertDialogBuilderProvider.get());
        TransferActivity_MembersInjector.injectMDeleteDataAlertDialogBuilder(transferActivity, mDeleteDataAlertDialogBuilderProvider.get());
        TransferActivity_MembersInjector.injectMDeleteDataConfirmDialogBuilder(transferActivity, mDeleteDataConfirmDialogBuilderProvider.get());
        TransferActivity_MembersInjector.injectMUnsentCabinAlertDialogBuilder(transferActivity, mUnsentCabinAlertDialogBuilderProvider.get());
    }

    public static void injectMSharedPreferencesHelper(final TransferActivity transferActivity, final SharedPreferencesHelper sharedPreferencesHelper) {
        transferActivity.mSharedPreferencesHelper = sharedPreferencesHelper;
    }

    public static void injectMAlertDialogBuilder(final TransferActivity transferActivity, final AlertDialogBuilder alertDialogBuilder) {
        transferActivity.mAlertDialogBuilder = alertDialogBuilder;
    }

    public static void injectMDeleteDataAlertDialogBuilder(final TransferActivity transferActivity, final AlertDialogBuilder alertDialogBuilder) {
        transferActivity.mDeleteDataAlertDialogBuilder = alertDialogBuilder;
    }

    public static void injectMDeleteDataConfirmDialogBuilder(final TransferActivity transferActivity, final AlertDialogBuilder alertDialogBuilder) {
        transferActivity.mDeleteDataConfirmDialogBuilder = alertDialogBuilder;
    }

    public static void injectMUnsentCabinAlertDialogBuilder(final TransferActivity transferActivity, final AlertDialogBuilder alertDialogBuilder) {
        transferActivity.mUnsentCabinAlertDialogBuilder = alertDialogBuilder;
    }
}
