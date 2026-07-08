package com.proje.mobilesales.core.base;

import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.model.ErpRights;
import com.proje.mobilesales.features.model.UserMenuRights;
import com.proje.mobilesales.features.model.UserSettings;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BaseErp_MembersInjector<T> implements MembersInjector<BaseErp<T>> {
    private final Provider<ErpRights> erpRightsProvider;
    private final Provider<UserSettings> mUserSettingsProvider;
    private final Provider<UserMenuRights> userMenuRightsProvider;
    private final Provider<User> userProvider;
    public BaseErp_MembersInjector(Provider<User> provider, Provider<UserMenuRights> provider2, Provider<ErpRights> provider3, Provider<UserSettings> provider4) {
        this.userProvider = provider;
        this.userMenuRightsProvider = provider2;
        this.erpRightsProvider = provider3;
        this.mUserSettingsProvider = provider4;
    }
    public static <T> MembersInjector<BaseErp<T>> create(Provider<User> provider, Provider<UserMenuRights> provider2, Provider<ErpRights> provider3, Provider<UserSettings> provider4) {
        return new BaseErp_MembersInjector(provider, provider2, provider3, provider4);
    }
    public void injectMembers(BaseErp<T> baseErp) {
        injectUser(baseErp, this.userProvider.get());
        injectUserMenuRights(baseErp, this.userMenuRightsProvider.get());
        injectErpRights(baseErp, this.erpRightsProvider.get());
        injectMUserSettings(baseErp, this.mUserSettingsProvider.get());
    }
    public static <T> void injectUser(BaseErp<T> baseErp, User user) {
        baseErp.user = user;
    }
    public static <T> void injectUserMenuRights(BaseErp<T> baseErp, UserMenuRights userMenuRights) {
        baseErp.userMenuRights = userMenuRights;
    }
    public static <T> void injectErpRights(BaseErp<T> baseErp, ErpRights erpRights) {
        baseErp.erpRights = erpRights;
    }
    public static <T> void injectMUserSettings(BaseErp<T> baseErp, UserSettings userSettings) {
        baseErp.mUserSettings = userSettings;
    }
}
