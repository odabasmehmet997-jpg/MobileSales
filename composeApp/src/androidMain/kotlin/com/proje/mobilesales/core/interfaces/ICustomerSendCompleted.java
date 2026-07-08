package com.proje.mobilesales.core.interfaces;

import com.proje.mobilesales.core.event.ResponseEvent;

public interface ICustomerSendCompleted {
    void onCustomerSendCompleted(ResponseEvent responseEvent);
}
