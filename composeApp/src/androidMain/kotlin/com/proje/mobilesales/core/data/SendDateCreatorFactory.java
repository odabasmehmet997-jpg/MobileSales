package com.proje.mobilesales.core.data;

import com.proje.mobilesales.core.enums.ErpType;

public class SendDateCreatorFactory {
    public static SendDataCreator getSendDataCreater(ErpType erpType) {
        if (erpType == ErpType.TIGER || erpType == ErpType.GO) {
            return TigerSendDataCreator.getInstance();
        }
        if (erpType == ErpType.NETSIS) {
            return new NetsisSendDataCreator();
        }
        return null;
    }
}
