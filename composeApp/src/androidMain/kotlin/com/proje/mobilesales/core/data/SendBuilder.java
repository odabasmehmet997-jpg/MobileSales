package com.proje.mobilesales.core.data;

import com.proje.mobilesales.core.base.BaseServiceResult;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.sql.ISqlHelper;

public interface SendBuilder<T extends BaseServiceResult, S, P> {
    T buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, int i2, String str, int i3, String str2, String str3, S s, int i4);
    T buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, int i2, String str, String str2, S s, P p, int i3);
    T buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, int i2, String str, String str2, String str3, S s, int i3);
    T buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, String str, int i2, String str2, S s, P p, int i3);
    T buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, String str, String str2, int i2, String str3, String str4, S s, int i3);
    T buildServiceResult(ISqlHelper iSqlHelper, DataObjectType dataObjectType, String str, String str2, String str3, S s, P p, int i2);
}
