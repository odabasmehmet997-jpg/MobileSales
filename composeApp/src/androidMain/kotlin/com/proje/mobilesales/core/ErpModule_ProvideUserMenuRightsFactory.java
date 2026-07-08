package com.proje.mobilesales.core;

import com.proje.mobilesales.features.model.UserMenuRights;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ErpModule_ProvideUserMenuRightsFactory implements Provider {
    private final ErpModule module;
    public ErpModule_ProvideUserMenuRightsFactory(ErpModule erpModule) {
        this.module = erpModule;
    }
    public UserMenuRights get() {
        return provideUserMenuRights(this.module);
    }
    public static ErpModule_ProvideUserMenuRightsFactory create(ErpModule erpModule) {
        return new ErpModule_ProvideUserMenuRightsFactory(erpModule);
    }
    public static UserMenuRights provideUserMenuRights(ErpModule erpModule) {
        return Preconditions.checkNotNullFromProvides(erpModule.provideUserMenuRights());
    }
}
