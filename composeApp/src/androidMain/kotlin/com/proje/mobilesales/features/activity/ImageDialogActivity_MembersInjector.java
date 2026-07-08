package com.proje.mobilesales.features.activity;

import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivity_MembersInjector;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ImageDialogActivity_MembersInjector implements MembersInjector<ImageDialogActivity> {
    private final Provider<BaseErp> baseErpProvider;
    private final Provider<AlertDialogBuilder> mBaseAlertDialogBuilderProvider;
    private final Provider<AlertDialogBuilder> mPhotoAlertDialogBuilderProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider;

    public ImageDialogActivity_MembersInjector(final Provider<BaseErp> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<AlertDialogBuilder> provider3, final Provider<AlertDialogBuilder> provider4) {
        baseErpProvider = provider;
        mProgressDialogBuilderProvider = provider2;
        mBaseAlertDialogBuilderProvider = provider3;
        mPhotoAlertDialogBuilderProvider = provider4;
    }

    public static MembersInjector<ImageDialogActivity> create(final Provider<BaseErp> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<AlertDialogBuilder> provider3, final Provider<AlertDialogBuilder> provider4) {
        return new ImageDialogActivity_MembersInjector(provider, provider2, provider3, provider4);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(final ImageDialogActivity imageDialogActivity) {
        BaseErpActivity_MembersInjector.injectBaseErp(imageDialogActivity, baseErpProvider.get());
        BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(imageDialogActivity, mProgressDialogBuilderProvider.get());
        BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(imageDialogActivity, mBaseAlertDialogBuilderProvider.get());
        ImageDialogActivity_MembersInjector.injectMPhotoAlertDialogBuilder(imageDialogActivity, mPhotoAlertDialogBuilderProvider.get());
    }

    public static void injectMPhotoAlertDialogBuilder(final ImageDialogActivity imageDialogActivity, final AlertDialogBuilder alertDialogBuilder) {
        imageDialogActivity.mPhotoAlertDialogBuilder = alertDialogBuilder;
    }
}
