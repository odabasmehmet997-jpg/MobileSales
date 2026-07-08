package com.proje.mobilesales.core.interfaces.di;

import com.proje.mobilesales.core.design.Netsis;
import com.proje.mobilesales.core.design.Tiger;


public interface ErpComponent extends IComponent {
    void inject(Netsis netsis);
    void inject(Tiger tiger);
}
