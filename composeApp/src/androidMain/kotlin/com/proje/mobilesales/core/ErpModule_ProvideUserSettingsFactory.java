package com.proje.mobilesales.core;

import com.proje.mobilesales.features.model.UserSettings;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ErpModule_ProvideUserSettingsFactory implements Provider {
    private final ErpModule module;
    public ErpModule_ProvideUserSettingsFactory(ErpModule erpModule) {
        this.module = erpModule;
    }
    public UserSettings get() {
        return provideUserSettings(this.module);
    }
    public static ErpModule_ProvideUserSettingsFactory create(ErpModule erpModule) {
        return new ErpModule_ProvideUserSettingsFactory(erpModule);
    }
    public static UserSettings provideUserSettings(ErpModule erpModule) {
        return Preconditions.checkNotNullFromProvides(ErpModule.provideUserSettings());
    }
}
