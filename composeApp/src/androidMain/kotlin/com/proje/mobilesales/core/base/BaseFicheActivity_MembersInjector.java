package com.proje.mobilesales.core.base;

import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BaseFicheActivity_MembersInjector implements MembersInjector<BaseFicheActivity> {
    private final Provider<BaseErp> baseErpProvider;
    private final Provider<AlertDialogBuilder> mAlertDialogBuilderProvider;
    private final Provider<AlertDialogBuilder> mBaseAlertDialogBuilderProvider;
    private final Provider<AlertDialogBuilder> mPhotoShowAlertDialogBuilderProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider;
    public BaseFicheActivity_MembersInjector(Provider<BaseErp> provider, Provider<ProgressDialogBuilder> provider2, Provider<AlertDialogBuilder> provider3, Provider<AlertDialogBuilder> provider4, Provider<AlertDialogBuilder> provider5) {
        this.baseErpProvider = provider;
        this.mProgressDialogBuilderProvider = provider2;
        this.mBaseAlertDialogBuilderProvider = provider3;
        this.mAlertDialogBuilderProvider = provider4;
        this.mPhotoShowAlertDialogBuilderProvider = provider5;
    }
    public static MembersInjector<BaseFicheActivity> create(Provider<BaseErp> provider, Provider<ProgressDialogBuilder> provider2, Provider<AlertDialogBuilder> provider3, Provider<AlertDialogBuilder> provider4, Provider<AlertDialogBuilder> provider5) {
        return new BaseFicheActivity_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }
    public void injectMembers(BaseFicheActivity baseFicheActivity) {
        BaseErpActivity_MembersInjector.injectBaseErp(baseFicheActivity, this.baseErpProvider.get());
        BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(baseFicheActivity, this.mProgressDialogBuilderProvider.get());
        BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(baseFicheActivity, this.mBaseAlertDialogBuilderProvider.get());
        injectMAlertDialogBuilder(baseFicheActivity, this.mAlertDialogBuilderProvider.get());
        injectMPhotoShowAlertDialogBuilder(baseFicheActivity, this.mPhotoShowAlertDialogBuilderProvider.get());
    }
    public static void injectMAlertDialogBuilder(BaseFicheActivity baseFicheActivity, AlertDialogBuilder alertDialogBuilder) {
        baseFicheActivity.mAlertDialogBuilder = alertDialogBuilder;
    }
    public static void injectMPhotoShowAlertDialogBuilder(BaseFicheActivity baseFicheActivity, AlertDialogBuilder alertDialogBuilder) {
        baseFicheActivity.mPhotoShowAlertDialogBuilder = alertDialogBuilder;
    }
}
