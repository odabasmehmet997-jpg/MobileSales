package com.proje.mobilesales.core.base;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class BaseErpActivityPreferences_MembersInjector implements MembersInjector<BaseErpActivityPreferences> {
    private final Provider<BaseErp> baseErpProvider;
    public BaseErpActivityPreferences_MembersInjector(Provider<BaseErp> provider) {
        this.baseErpProvider = provider;
    }
    public static MembersInjector<BaseErpActivityPreferences> create(Provider<BaseErp> provider) {
        return new BaseErpActivityPreferences_MembersInjector(provider);
    }
    public void injectMembers(BaseErpActivityPreferences baseErpActivityPreferences) {
        injectBaseErp(baseErpActivityPreferences, this.baseErpProvider.get());
    }
    public static void injectBaseErp(BaseErpActivityPreferences baseErpActivityPreferences, BaseErp baseErp) {
        baseErpActivityPreferences.baseErp = baseErp;
    }
}
