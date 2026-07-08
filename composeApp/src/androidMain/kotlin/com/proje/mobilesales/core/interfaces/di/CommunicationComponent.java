package com.proje.mobilesales.core.interfaces.di;

import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.asynctask.TigerWcfAsyncTask;


public interface CommunicationComponent extends IComponent {
    void inject(NetsisRestAsyncTask netsisRestAsyncTask);

    void inject(TigerWcfAsyncTask tigerWcfAsyncTask);
}
