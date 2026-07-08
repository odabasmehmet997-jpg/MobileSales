package com.proje.mobilesales.core.interfaces.di;

import com.proje.mobilesales.core.ActivityModule;
import com.proje.mobilesales.core.CommunicationModule;
import com.proje.mobilesales.core.ErpModule;
import dagger.Component;

public interface ApplicationComponent {
    ActivityComponent plus(ActivityModule activityModule);

    CommunicationComponent plus(CommunicationModule communicationModule);

    ErpComponent plus(ErpModule erpModule);
}
