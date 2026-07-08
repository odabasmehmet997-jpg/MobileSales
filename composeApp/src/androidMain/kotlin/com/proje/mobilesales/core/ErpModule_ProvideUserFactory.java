package com.proje.mobilesales.core;

import com.proje.mobilesales.features.dbmodel.User;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ErpModule_ProvideUserFactory implements Provider {
    private final ErpModule module;
    public ErpModule_ProvideUserFactory(ErpModule erpModule) {
        this.module = erpModule;
    }
    public User get() {
        return provideUser(module);
    }
    public static ErpModule_ProvideUserFactory create(ErpModule erpModule) {
        return new ErpModule_ProvideUserFactory(erpModule);
    }
    public static User provideUser(ErpModule erpModule) {
        return Preconditions.checkNotNullFromProvides(erpModule.provideUser());
    }
}
