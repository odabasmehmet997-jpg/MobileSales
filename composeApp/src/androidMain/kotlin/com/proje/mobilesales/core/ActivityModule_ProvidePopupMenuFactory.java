package com.proje.mobilesales.core;

import com.proje.mobilesales.core.interfaces.PopupMenu;
import dagger.internal.Preconditions;
import javax.inject.Provider;
public final class ActivityModule_ProvidePopupMenuFactory implements Provider {
    private final ActivityModule module;

    public ActivityModule_ProvidePopupMenuFactory(ActivityModule activityModule) {
        this.module = activityModule;
    }
 
    public PopupMenu get() {
        return providePopupMenu(this.module);
    }

    public static ActivityModule_ProvidePopupMenuFactory create(ActivityModule activityModule) {
        return new ActivityModule_ProvidePopupMenuFactory(activityModule);
    }

    public static PopupMenu providePopupMenu(ActivityModule activityModule) {
        return Preconditions.checkNotNullFromProvides(activityModule.providePopupMenu());
    }
}
