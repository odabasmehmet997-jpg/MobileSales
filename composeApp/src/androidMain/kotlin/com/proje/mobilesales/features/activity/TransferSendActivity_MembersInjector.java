package com.proje.mobilesales.features.activity;

import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivity_MembersInjector;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.sql.ISqlManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class TransferSendActivity_MembersInjector implements MembersInjector<TransferSendActivity> {
    private final Provider<BaseErp> baseErpProvider;
    private final Provider<AlertDialogBuilder> mAlertDialogBuilderProvider;
    private final Provider<AlertDialogBuilder> mBaseAlertDialogBuilderProvider;
    private final Provider<BaseErp> mBaseErpProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider2;
    private final Provider<ISqlManager> mSqlManagerProvider;

    public TransferSendActivity_MembersInjector(final Provider<BaseErp> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<AlertDialogBuilder> provider3, final Provider<AlertDialogBuilder> provider4, final Provider<BaseErp> provider5, final Provider<ISqlManager> provider6, final Provider<ProgressDialogBuilder> provider7) {
        baseErpProvider = provider;
        mProgressDialogBuilderProvider = provider2;
        mBaseAlertDialogBuilderProvider = provider3;
        mAlertDialogBuilderProvider = provider4;
        mBaseErpProvider = provider5;
        mSqlManagerProvider = provider6;
        mProgressDialogBuilderProvider2 = provider7;
    }

    public static MembersInjector<TransferSendActivity> create(final Provider<BaseErp> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<AlertDialogBuilder> provider3, final Provider<AlertDialogBuilder> provider4, final Provider<BaseErp> provider5, final Provider<ISqlManager> provider6, final Provider<ProgressDialogBuilder> provider7) {
        return new TransferSendActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
    public void injectMembers(final TransferSendActivity transferSendActivity) {
        BaseErpActivity_MembersInjector.injectBaseErp(transferSendActivity, baseErpProvider.get());
        BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(transferSendActivity, mProgressDialogBuilderProvider.get());
        BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(transferSendActivity, mBaseAlertDialogBuilderProvider.get());
        TransferSendActivity_MembersInjector.injectMAlertDialogBuilder(transferSendActivity, mAlertDialogBuilderProvider.get());
        TransferSendActivity_MembersInjector.injectMBaseErp(transferSendActivity, mBaseErpProvider.get());
        TransferSendActivity_MembersInjector.injectMSqlManager(transferSendActivity, mSqlManagerProvider.get());
        TransferSendActivity_MembersInjector.injectMProgressDialogBuilder(transferSendActivity, mProgressDialogBuilderProvider2.get());
    }

    public static void injectMAlertDialogBuilder(final TransferSendActivity transferSendActivity, final AlertDialogBuilder alertDialogBuilder) {
        transferSendActivity.mAlertDialogBuilder = alertDialogBuilder;
    }

    public static void injectMBaseErp(final TransferSendActivity transferSendActivity, final BaseErp baseErp) {
        transferSendActivity.mBaseErp = baseErp;
    }

    public static void injectMSqlManager(final TransferSendActivity transferSendActivity, final ISqlManager iSqlManager) {
        transferSendActivity.mSqlManager = iSqlManager;
    }

    public static void injectMProgressDialogBuilder(final TransferSendActivity transferSendActivity, final ProgressDialogBuilder progressDialogBuilder) {
        transferSendActivity.mProgressDialogBuilder = progressDialogBuilder;
    }
}
