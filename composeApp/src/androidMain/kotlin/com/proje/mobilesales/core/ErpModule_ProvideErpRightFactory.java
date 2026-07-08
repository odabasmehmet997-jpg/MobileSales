package com.proje.mobilesales.core;

import com.proje.mobilesales.features.model.ErpRights;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ErpModule_ProvideErpRightFactory implements Provider {
    private final ErpModule module;
    public ErpModule_ProvideErpRightFactory(ErpModule erpModule) {
        this.module = erpModule;
    }
    public ErpRights get() {
        return provideErpRight(module);
    }
    public static ErpModule_ProvideErpRightFactory create(ErpModule erpModule) {
        return new ErpModule_ProvideErpRightFactory(erpModule);
    }
    public static ErpRights provideErpRight(ErpModule erpModule) {
        return Preconditions.checkNotNullFromProvides(erpModule.provideErpRight());
    }
}
