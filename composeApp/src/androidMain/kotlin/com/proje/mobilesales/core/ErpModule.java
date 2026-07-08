package com.proje.mobilesales.core;

import android.content.Context;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.model.ErpRights;
import com.proje.mobilesales.features.model.UserMenuRights;
import com.proje.mobilesales.features.model.UserSettings;

public class ErpModule {
    Context mContext;
    ErpType mErpType;
    public ErpModule(Context context, ErpType erpType) {
        this.mContext = context;
        this.mErpType = erpType;
    }
    public UserSettings provideUserSettings() {
        Context context = null;
        if (this.mErpType == ErpType.NETSIS) {
            return new Preferences.NetsisUserSettings(context);
        }
        return new Preferences.TigerUserSettings(context);
    }
    public User provideUser() {
        return new SharedPreferencesHelper(mContext).getUser();
    }
    public ErpRights provideErpRight() {
        return new SharedPreferencesHelper(mContext).getErpRights();
    }
    public UserMenuRights provideUserMenuRights() {
        return new SharedPreferencesHelper(mContext).getUserMenuRights();
    }
    public SharedPreferencesHelper provideSharedPreferencesHelper() {
        return new SharedPreferencesHelper(mContext);
    }
}
