package com.proje.mobilesales.core.base;

import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.interfaces.IErp;

public abstract class BaseErpFactory {
    public abstract IErp getErp(ErpType erpType);
}
