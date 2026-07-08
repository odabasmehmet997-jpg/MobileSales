package com.proje.mobilesales.core.base;

import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.sql.ISqlManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BaseFicheFragment_MembersInjector implements MembersInjector<BaseFicheFragment> {
    private final Provider<AlertDialogBuilder> mAlertDialogBuilderProvider;
    private final Provider<AlertDialogBuilder> mArrayAlertDialogBuilderProvider;
    private final Provider<AlertDialogBuilder> mDateAlertDialogBuilderProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider;
    private final Provider<ISqlManager> mSqlManagerProvider;
    public BaseFicheFragment_MembersInjector(Provider<AlertDialogBuilder> provider, Provider<AlertDialogBuilder> provider2, Provider<AlertDialogBuilder> provider3, Provider<ISqlManager> provider4, Provider<ProgressDialogBuilder> provider5) {
        this.mAlertDialogBuilderProvider = provider;
        this.mArrayAlertDialogBuilderProvider = provider2;
        this.mDateAlertDialogBuilderProvider = provider3;
        this.mSqlManagerProvider = provider4;
        this.mProgressDialogBuilderProvider = provider5;
    }
    public static MembersInjector<BaseFicheFragment> create(Provider<AlertDialogBuilder> provider, Provider<AlertDialogBuilder> provider2, Provider<AlertDialogBuilder> provider3, Provider<ISqlManager> provider4, Provider<ProgressDialogBuilder> provider5) {
        return new BaseFicheFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }
    public void injectMembers(BaseFicheFragment baseFicheFragment) {
        injectMAlertDialogBuilder(baseFicheFragment, this.mAlertDialogBuilderProvider.get());
        injectMArrayAlertDialogBuilder(baseFicheFragment, this.mArrayAlertDialogBuilderProvider.get());
        injectMDateAlertDialogBuilder(baseFicheFragment, this.mDateAlertDialogBuilderProvider.get());
        injectMSqlManager(baseFicheFragment, this.mSqlManagerProvider.get());
        injectMProgressDialogBuilder(baseFicheFragment, this.mProgressDialogBuilderProvider.get());
    }
    public static void injectMAlertDialogBuilder(BaseFicheFragment baseFicheFragment, AlertDialogBuilder alertDialogBuilder) {
        baseFicheFragment.mAlertDialogBuilder = alertDialogBuilder;
    }
    public static void injectMArrayAlertDialogBuilder(BaseFicheFragment baseFicheFragment, AlertDialogBuilder alertDialogBuilder) {
        baseFicheFragment.mArrayAlertDialogBuilder = alertDialogBuilder;
    }
    public static void injectMDateAlertDialogBuilder(BaseFicheFragment baseFicheFragment, AlertDialogBuilder alertDialogBuilder) {
        baseFicheFragment.mDateAlertDialogBuilder = alertDialogBuilder;
    }
    public static void injectMSqlManager(BaseFicheFragment baseFicheFragment, ISqlManager iSqlManager) {
        baseFicheFragment.mSqlManager = iSqlManager;
    }
    public static void injectMProgressDialogBuilder(BaseFicheFragment baseFicheFragment, ProgressDialogBuilder progressDialogBuilder) {
        baseFicheFragment.mProgressDialogBuilder = progressDialogBuilder;
    }
}
