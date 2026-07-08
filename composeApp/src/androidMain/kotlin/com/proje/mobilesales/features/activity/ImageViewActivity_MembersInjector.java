package com.proje.mobilesales.features.activity;

import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivity_MembersInjector;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ImageViewActivity_MembersInjector implements MembersInjector<ImageViewActivity> {
    private final Provider<BaseErp> baseErpProvider;
    private final Provider<AlertDialogBuilder> mBaseAlertDialogBuilderProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider2;

    public ImageViewActivity_MembersInjector(final Provider<BaseErp> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<AlertDialogBuilder> provider3, final Provider<ProgressDialogBuilder> provider4) {
        baseErpProvider = provider;
        mProgressDialogBuilderProvider = provider2;
        mBaseAlertDialogBuilderProvider = provider3;
        mProgressDialogBuilderProvider2 = provider4;
    }

    public static MembersInjector<ImageViewActivity> create(final Provider<BaseErp> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<AlertDialogBuilder> provider3, final Provider<ProgressDialogBuilder> provider4) {
        return new ImageViewActivity_MembersInjector(provider, provider2, provider3, provider4);
    }
    public void injectMembers(final ImageViewActivity imageViewActivity) {
        BaseErpActivity_MembersInjector.injectBaseErp(imageViewActivity, baseErpProvider.get());
        BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(imageViewActivity, mProgressDialogBuilderProvider.get());
        BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(imageViewActivity, mBaseAlertDialogBuilderProvider.get());
        ImageViewActivity_MembersInjector.injectMProgressDialogBuilder(imageViewActivity, mProgressDialogBuilderProvider2.get());
    }

    public static void injectMProgressDialogBuilder(final ImageViewActivity imageViewActivity, final ProgressDialogBuilder progressDialogBuilder) {
        imageViewActivity.mProgressDialogBuilder = progressDialogBuilder;
    }
}
