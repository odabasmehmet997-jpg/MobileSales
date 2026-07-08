package com.proje.mobilesales.core.base;

import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.utils.KeyDelegate;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BaseListDrawerActivity_MembersInjector implements MembersInjector<BaseListDrawerActivity> {
    private final Provider<BaseErp> baseErpProvider;
    private final Provider<ActionViewResolver> mActionViewResolverProvider;
    private final Provider<AlertDialogBuilder> mAlertDialogBuilderProvider;
    private final Provider<AlertDialogBuilder> mBaseAlertDialogBuilderProvider;
    private final Provider<KeyDelegate> mKeyDelegateProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider;
    public BaseListDrawerActivity_MembersInjector(Provider<BaseErp> provider, Provider<ProgressDialogBuilder> provider2, Provider<AlertDialogBuilder> provider3, Provider<AlertDialogBuilder> provider4, Provider<KeyDelegate> provider5, Provider<ActionViewResolver> provider6) {
        this.baseErpProvider = provider;
        this.mProgressDialogBuilderProvider = provider2;
        this.mBaseAlertDialogBuilderProvider = provider3;
        this.mAlertDialogBuilderProvider = provider4;
        this.mKeyDelegateProvider = provider5;
        this.mActionViewResolverProvider = provider6;
    }
    public static MembersInjector<BaseListDrawerActivity> create(Provider<BaseErp> provider, Provider<ProgressDialogBuilder> provider2, Provider<AlertDialogBuilder> provider3, Provider<AlertDialogBuilder> provider4, Provider<KeyDelegate> provider5, Provider<ActionViewResolver> provider6) {
        return new BaseListDrawerActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }
    public void injectMembers(BaseListDrawerActivity baseListDrawerActivity) {
        BaseErpActivity_MembersInjector.injectBaseErp(baseListDrawerActivity, this.baseErpProvider.get());
        BaseErpActivity_MembersInjector.injectMProgressDialogBuilder(baseListDrawerActivity, this.mProgressDialogBuilderProvider.get());
        BaseErpActivity_MembersInjector.injectMBaseAlertDialogBuilder(baseListDrawerActivity, this.mBaseAlertDialogBuilderProvider.get());
        BaseDrawerActivity_MembersInjector.injectMAlertDialogBuilder(baseListDrawerActivity, this.mAlertDialogBuilderProvider.get());
        injectMKeyDelegate(baseListDrawerActivity, this.mKeyDelegateProvider.get());
        injectMActionViewResolver(baseListDrawerActivity, this.mActionViewResolverProvider.get());
    }
    public static void injectMKeyDelegate(BaseListDrawerActivity baseListDrawerActivity, KeyDelegate keyDelegate) {
        baseListDrawerActivity.mKeyDelegate = keyDelegate;
    }
    public static void injectMActionViewResolver(BaseListDrawerActivity baseListDrawerActivity, ActionViewResolver actionViewResolver) {
        baseListDrawerActivity.mActionViewResolver = actionViewResolver;
    }
}
