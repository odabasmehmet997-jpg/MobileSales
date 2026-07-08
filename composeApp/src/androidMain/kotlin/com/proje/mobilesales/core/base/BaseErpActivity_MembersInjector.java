package com.proje.mobilesales.core.base;

import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BaseErpActivity_MembersInjector implements MembersInjector<BaseErpActivity> {
    private final Provider<BaseErp> baseErpProvider;
    private final Provider<AlertDialogBuilder> mBaseAlertDialogBuilderProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider;
    public BaseErpActivity_MembersInjector(Provider<BaseErp> provider, Provider<ProgressDialogBuilder> provider2, Provider<AlertDialogBuilder> provider3) {
        this.baseErpProvider = provider;
        this.mProgressDialogBuilderProvider = provider2;
        this.mBaseAlertDialogBuilderProvider = provider3;
    }
    public static MembersInjector<BaseErpActivity> create(Provider<BaseErp> provider, Provider<ProgressDialogBuilder> provider2, Provider<AlertDialogBuilder> provider3) {
        return new BaseErpActivity_MembersInjector(provider, provider2, provider3);
    }
    public void injectMembers(BaseErpActivity baseErpActivity) {
        injectBaseErp(baseErpActivity, this.baseErpProvider.get());
        injectMProgressDialogBuilder(baseErpActivity, this.mProgressDialogBuilderProvider.get());
        injectMBaseAlertDialogBuilder(baseErpActivity, this.mBaseAlertDialogBuilderProvider.get());
    }
    public static void injectBaseErp(BaseErpActivity baseErpActivity, BaseErp baseErp) {
        baseErpActivity.baseErp = baseErp;
    }
    public static void injectMProgressDialogBuilder(BaseErpActivity baseErpActivity, ProgressDialogBuilder progressDialogBuilder) {
        baseErpActivity.mProgressDialogBuilder = progressDialogBuilder;
    }
    public static void injectMBaseAlertDialogBuilder(BaseErpActivity baseErpActivity, AlertDialogBuilder alertDialogBuilder) {
        baseErpActivity.mBaseAlertDialogBuilder = alertDialogBuilder;
    }
}
