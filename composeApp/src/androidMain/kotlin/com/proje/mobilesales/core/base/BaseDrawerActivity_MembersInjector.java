package com.proje.mobilesales.core.base;

import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BaseDrawerActivity_MembersInjector implements MembersInjector<BaseDrawerActivity> {
    private final Provider<BaseErp> baseErpProvider;
    private final Provider<AlertDialogBuilder> mAlertDialogBuilderProvider;
    private final Provider<AlertDialogBuilder> mBaseAlertDialogBuilderProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider;

    public BaseDrawerActivity_MembersInjector(Provider<BaseErp> provider, Provider<ProgressDialogBuilder> provider2, Provider<AlertDialogBuilder> provider3, Provider<AlertDialogBuilder> provider4) {
        this.baseErpProvider = provider;
        this.mProgressDialogBuilderProvider = provider2;
        this.mBaseAlertDialogBuilderProvider = provider3;
        this.mAlertDialogBuilderProvider = provider4;
    }

    public static MembersInjector<BaseDrawerActivity> create(Provider<BaseErp> provider, Provider<ProgressDialogBuilder> provider2, Provider<AlertDialogBuilder> provider3, Provider<AlertDialogBuilder> provider4) {
        return new BaseDrawerActivity_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(BaseDrawerActivity baseDrawerActivity) {
        BaseErpActivity_MembersInjector.injectBaseErp(baseDrawerActivity, this.baseErpProvider.get());
        BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(baseDrawerActivity, this.mProgressDialogBuilderProvider.get());
        BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(baseDrawerActivity, this.mBaseAlertDialogBuilderProvider.get());
        injectMAlertDialogBuilder(baseDrawerActivity, this.mAlertDialogBuilderProvider.get());
    }

    public static void injectMAlertDialogBuilder(BaseDrawerActivity baseDrawerActivity, AlertDialogBuilder alertDialogBuilder) {
        baseDrawerActivity.mAlertDialogBuilder = alertDialogBuilder;
    }
}
